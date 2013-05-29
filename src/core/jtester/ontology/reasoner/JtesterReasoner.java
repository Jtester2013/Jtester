package core.jtester.ontology.reasoner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;


import plugin.util.Const;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.semantics.ViolationAxiom;
import core.common.model.test.TestData;
import core.jtester.ontology.checker.CloseStreamChecker;
import core.jtester.ontology.checker.DividedByZeroChecker;
import core.jtester.ontology.checker.ConditionAlwaysSameValueChecker;
import core.jtester.ontology.checker.FileContentInjectionChecker;
import core.jtester.ontology.checker.MethodOverrideChecker;
import core.jtester.ontology.checker.NullPointerChecker;
import core.jtester.ontology.checker.OutOfBoundaryChecker;
import core.jtester.ontology.checker.RemoveInIterationChecker;
import core.jtester.ontology.checker.SensitiveDataExposureChecker;
import core.jtester.ontology.checker.UnusedVariableChecker;

public class JtesterReasoner implements IJob{
	protected String name = this.getClass().getSimpleName();
	private List<IChecker> checkers;
	
	@Override
	public boolean run(TestData data) {
		init();
		produceABox(data);
		reasonOntology(data);
		return true;
	}

	private void init() {
		checkers = new ArrayList<IChecker>();
		
		// 针对缺陷的checker
		ConditionAlwaysSameValueChecker iasvc = new ConditionAlwaysSameValueChecker();
		UnusedVariableChecker uvc = new UnusedVariableChecker();
		NullPointerChecker npc = new NullPointerChecker(); 
		CloseStreamChecker cfc = new CloseStreamChecker();
		MethodOverrideChecker moc = new MethodOverrideChecker();
		OutOfBoundaryChecker oobc = new OutOfBoundaryChecker();
		RemoveInIterationChecker riic = new RemoveInIterationChecker();
		DividedByZeroChecker dbzc = new DividedByZeroChecker();
		
		// 针对漏洞的checker
		FileContentInjectionChecker fcic = new FileContentInjectionChecker();
		SensitiveDataExposureChecker sdec = new SensitiveDataExposureChecker();
		
		// 使用本体推理检测漏洞和缺陷的checker
		
		checkers.add(iasvc);
		checkers.add(uvc);
		checkers.add(npc);
		checkers.add(moc);
		checkers.add(cfc);
		checkers.add(oobc);
		checkers.add(riic);
		checkers.add(dbzc);
		
		checkers.add(fcic);
		checkers.add(sdec);
	}
	
	private void produceABox(TestData data) {
		for(IChecker checker : checkers){
			checker.check(data);
		}
	}

	private void reasonOntology(TestData data) {
		File owlFile = getOWLFile();
		try {
			
			// 加载owl文件
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			IRI docIRI = IRI.create(owlFile);
			OWLOntology ont = manager.loadOntologyFromOntologyDocument(docIRI);
			System.out.println("\nLoad: " + ont.getOntologyID());
			
			// 获取ABox
			Set<ViolationAxiom> atoms = data.getTestResult().getViolations();
			
			// 获取owl中缺陷模式
			Set<OWLNamedIndividual> individuals = ont.getIndividualsInSignature();
			Set<OWLNamedIndividual> toCheck = new HashSet<OWLNamedIndividual>();
			for(OWLNamedIndividual one: individuals){
				if(matchRule(one.getDataPropertyValues(ont), atoms)){
					toCheck.add(one);
				}
			}
			
			// 推理
			PelletReasoner reasoner = PelletReasonerFactory.getInstance().createReasoner(ont);
			Set<OWLClass> owlClasses = new HashSet<OWLClass>();
			for(OWLNamedIndividual individual : toCheck){
				NodeSet<OWLClass> owlcls =  reasoner.getTypes(individual, true);
				Iterator<Node<OWLClass>> ir = owlcls.iterator();
				while(ir.hasNext()){
					owlClasses.addAll(ir.next().getEntities());
				}
			}
			
			// 报告
			generateReport(atoms, owlClasses, ont);
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	
	// to complex 
	// need refinement
	private boolean matchRule(Map<OWLDataPropertyExpression, Set<OWLLiteral>> properties, Set<ViolationAxiom> atoms){
		Iterator<OWLDataPropertyExpression> ir = properties.keySet().iterator();
		while(ir.hasNext()){
			OWLDataPropertyExpression de = ir.next();
			Iterator<ViolationAxiom> ir2 = atoms.iterator();
			while(ir2.hasNext()){
				ViolationAxiom atom = ir2.next();
				Map<String, String> dataProperties = atom.getDataProperties();
				Set<String> keys = dataProperties.keySet();
				for(String key: keys){
					// 匹配 data property的属性名称
					if(de.toString().contains(key)){
						Set<OWLLiteral> values = properties.get(de);
						for(OWLLiteral val: values){
							if(val.getLiteral().equals(dataProperties.get(key))){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	private File getOWLFile(){
		File file = null;
		try { 
			String filePath = "";
			Bundle bundle = Platform.getBundle(Const.JTESTER);
			
			if(bundle != null){
				// used in plug in
				URL url = bundle.getResource(Const.OWL_PATH);
				filePath = Const.FILE_Type + FileLocator.toFileURL(url).getFile();
			}else{
				// test in console
				filePath = Const.OWL_PATH;
			}
			file = new File(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return file;
	}
	
	private void generateReport(Set<ViolationAxiom> violations,  Set<OWLClass> owls, OWLOntology ont){
		for(OWLClass owl: owls){
			String owlName = owl.toString();
			for(ViolationAxiom violation: violations){
				if(owlName.contains(violation.getRuleName())){
					// violation name
					System.out.println(owlName);
					
					// violation description
					Set<OWLAnnotation> annotations = owl.getAnnotations(ont);
					for(OWLAnnotation annotation: annotations){
						String desc = trim(annotation.getValue().toString());
						if(!desc.isEmpty()){
							System.out.println(desc);
						}
					}
					
					// line seperator
					System.out.println("");
				}
			}
		}
	}
	
	private String trim(String text){
		// do not print example
		if(text.contains(JobConst.ONTOLOGY_EXAMPLE_CN)){
			return "";
		}
		
		String content = text.substring(1, text.length()-1);
		return content;
	}

	@Override
	public String getName() {
		return name;
	}
}
