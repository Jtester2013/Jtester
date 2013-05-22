package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.NullLiteral;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.jtester.ontology.reasoner.IChecker;

public class CloseStreamChecker implements IChecker{

	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<DeclarationSemantics> exceptions = handleSemantics(store);

		if(generateReport(exceptions)){
			data.getTestResult().addViolation(JobConst.ONTOLOGY_STREAM_NOT_CLOSED);
		}
	}
	
	private List<DeclarationSemantics> handleSemantics(SemanticsStore store){
		List<String> rules = getRules();
		List<DeclarationSemantics> toCheck = new ArrayList<DeclarationSemantics>();
		List<DeclarationSemantics> violations = new ArrayList<DeclarationSemantics>();
		
		// add variables to check
		Iterator<DeclarationSemantics> ir1 = store.iterator1();
		while(ir1.hasNext()){
			DeclarationSemantics ds = ir1.next();
			if(ds.getValue() == null || ds.getValue() instanceof NullLiteral){
				// no need to check uninitialized variable
				continue;
			}
			
			// match rule
			for(String rule: rules){
				if(ds.getType().toString().equals(rule)){
					toCheck.add(ds);
				}
			}
		}

		// check variables
		for(DeclarationSemantics ds: toCheck){
			boolean closed = false;
			Iterator<InferenceSemantics> ir2 = store.iterator2();
			while(ir2.hasNext()){
				InferenceSemantics is = ir2.next();
				if(is.getName().toString().equals(ds.getName().toString()) && JobConst.STREAM_CLOSE.equals(is.getMethod().toString())){
					closed = true;
				}
			}
			if(!closed){
				violations.add(ds);
			}
		}

		return violations;
	}
	
	private List<String> getRules(){
		List<String> rules = new ArrayList<String>();
		rules.add(JobConst.STREAM_TYPE_1);
		rules.add(JobConst.STREAM_TYPE_2);
		return rules;	
	}
	
	private boolean generateReport(List<DeclarationSemantics> exceptions){
		boolean report = false;
		if(exceptions != null && !exceptions.isEmpty()){
			System.err.println("Warning: 未关闭流变量!");
			for(DeclarationSemantics ds: exceptions){
				System.err.println("\t" + ds + " \t");
				if(report == false){
					report = true;
				}
			}
		}
		return report;
	}
}
