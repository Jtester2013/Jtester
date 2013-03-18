package core.jtester.ontology.reasoner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;


import plugin.util.Const;

import core.common.model.jobflow.IJob;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.jtester.ontology.empty_loop.EmptyLoopChecker;

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
		EmptyLoopChecker elc = new EmptyLoopChecker();
		checkers.add(elc);
	}
	
	private void produceABox(TestData data) {
		TestFile file = data.getCurrentTestFile();
		EmptyLoopChecker checker = new EmptyLoopChecker();
		checker.check(file);
	}

	private void reasonOntology(TestData data) {
		try { 
			Bundle bundle = Platform.getBundle(Const.JTESTER);  
			URL url = bundle.getResource(Const.OWL_PATH);  
			String filePath = Const.FILE_Type + FileLocator.toFileURL(url).getFile();
			System.out.println("file path: " + filePath);
				
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			IRI docIRI = IRI.create(filePath);
//			OWLOntology ont = manager.loadOntologyFromOntologyDocument(docIRI);
//		    System.out.println("Loaded " + ont.getOntologyID());
		    
//		    // setup variables
//		    OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
//		    ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
//            OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
//            
//            OWLReasoner reasoner = reasonerFactory.createReasoner(ont, config);
//            reasoner.precomputeInferences();
//            
//            OWLDataFactory fac = manager.getOWLDataFactory();
//            OWLClass vegPizza = fac.getOWLClass(IRI.create("http://owl.man.ac.uk/2005/07/sssw/people#vegetarian"));
//            NodeSet<OWLClass> subClses = reasoner.getSubClasses(vegPizza, true);
//         
//            // set of sets and print the result
//            Set<OWLClass> clses = subClses.getFlattened();
//            System.out.println("Subclasses of vegetarian: ");
//            for(OWLClass cls : clses) {
//                System.out.println("    " + cls);
//            }
//            System.out.println("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public String getName() {
		return name;
	}
}
