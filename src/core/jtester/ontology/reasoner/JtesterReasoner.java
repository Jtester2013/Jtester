package core.jtester.ontology.reasoner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;


import plugin.util.Const;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.test.TestData;
import core.jtester.ontology.checker.CloseStreamChecker;
import core.jtester.ontology.checker.DividedByZeroChecker;
import core.jtester.ontology.checker.ConditionAlwaysSameValueChecker;
import core.jtester.ontology.checker.FileContentInjectionChecker;
import core.jtester.ontology.checker.MethodOverrideChecker;
import core.jtester.ontology.checker.NullPointerChecker;
import core.jtester.ontology.checker.OutOfBoundaryChecker;
import core.jtester.ontology.checker.RemoveInIterationChecker;
import core.jtester.ontology.checker.UnusedVariableChecker;

public class JtesterReasoner implements IJob{
	protected String name = this.getClass().getSimpleName();
	private List<IChecker> checkers;
	
	@Override
	public boolean run(TestData data) {
		init();
		produceABox(data);
		//reasonOntology(data);
		return true;
	}

	private void init() {
		checkers = new ArrayList<IChecker>();
		ConditionAlwaysSameValueChecker iasvc = new ConditionAlwaysSameValueChecker();
		UnusedVariableChecker uvc = new UnusedVariableChecker();
		NullPointerChecker npc = new NullPointerChecker(); 
		CloseStreamChecker cfc = new CloseStreamChecker();
		MethodOverrideChecker moc = new MethodOverrideChecker();
		OutOfBoundaryChecker oobc = new OutOfBoundaryChecker();
		RemoveInIterationChecker riic = new RemoveInIterationChecker();
		DividedByZeroChecker dbzc = new DividedByZeroChecker();
		
		FileContentInjectionChecker fcic = new FileContentInjectionChecker();
		
		checkers.add(iasvc);
		checkers.add(uvc);
		checkers.add(npc);
		checkers.add(moc);
		checkers.add(cfc);
		checkers.add(oobc);
		checkers.add(riic);
		checkers.add(dbzc);
		
		checkers.add(fcic);
	}
	
	private void produceABox(TestData data) {
		for(IChecker checker : checkers){
			checker.check(data);
		}
	}

	private void reasonOntology(TestData data) {
		try { 
			File file = null;
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

			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			IRI docIRI = IRI.create(file);
			OWLOntology ont = manager.loadOntologyFromOntologyDocument(docIRI);
			ont.getABoxAxioms(true);
		    System.out.println("Load: " + ont.getOntologyID());
		
		    Set<String> violations = data.getTestResult().getViolations();
            Set<OWLClass> owls = ont.getClassesInSignature();
            
            generateReport(violations, owls, ont);
            
            //System.out.println(results);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		} 
	}
	
	private void generateReport(Set<String> violations,  Set<OWLClass> owls, OWLOntology ont){
		for(OWLClass owl: owls){
			String owlName = owl.toString();
			for(String violation: violations){
				if(owlName.contains(violation)){
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
