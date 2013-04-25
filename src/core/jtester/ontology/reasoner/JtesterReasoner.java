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
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;


import plugin.util.Const;
import core.common.model.jobflow.IJob;
import core.common.model.test.TestData;
import core.jtester.ontology.array_out_of_boundary.ArrayOutOfBoundaryChecker;
import core.jtester.ontology.checker.CloseStreamChecker;
import core.jtester.ontology.checker.DividedByZeroChecker;
import core.jtester.ontology.checker.MethodOverrideChecker;
import core.jtester.ontology.checker.NullPointerChecker;
import core.jtester.ontology.checker.OutOfBoundaryChecker;
import core.jtester.ontology.checker.UnusedVariableChecker;
import core.jtester.ontology.empty_loop.EmptyLoopChecker;
import core.jtester.ontology.ifalwaystrueorfalse.IfAlwaysTrueOrFalseChecker;
import core.jtester.ontology.return_from_finally.FinallyBlockChecker;

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
		UnusedVariableChecker uvc = new UnusedVariableChecker();
		NullPointerChecker npc = new NullPointerChecker(); 
		CloseStreamChecker cfc = new CloseStreamChecker();
		MethodOverrideChecker moc = new MethodOverrideChecker();
		OutOfBoundaryChecker oobc = new OutOfBoundaryChecker();
		DividedByZeroChecker dbzc = new DividedByZeroChecker();
		
		checkers.add(uvc);
		checkers.add(npc);
		checkers.add(moc);
		checkers.add(cfc);
		checkers.add(oobc);
		checkers.add(dbzc);
		
//		EmptyLoopChecker elc = new EmptyLoopChecker();
//		FinallyBlockChecker fbc = new FinallyBlockChecker();
//		IfAlwaysTrueOrFalseChecker iatofc = new IfAlwaysTrueOrFalseChecker();
//		ArrayOutOfBoundaryChecker aoobc = new ArrayOutOfBoundaryChecker();
//		checkers.add(elc);
//		checkers.add(fbc);
//		checkers.add(iatofc);
//		checkers.add(aoobc);
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
		    System.out.println("Loaded " + ont.getOntologyID());
		    
		    // setup variables
		    OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
            OWLReasoner reasoner = reasonerFactory.createReasoner(ont);
            reasoner.precomputeInferences();
            
            OWLDataFactory fac = manager.getOWLDataFactory();
            OWLClass results = fac.getOWLClass(IRI.create(Const.OntologyID + Const.OWL_WARNING));
            
            Set<OWLClass> owls = ont.getClassesInSignature();
            for(OWLClass cls: owls){
            	System.out.println(reasoner.getInstances(cls, false));
            }
            
            //System.out.println(results);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public String getName() {
		return name;
	}
}
