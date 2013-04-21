package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.jtester.ontology.reasoner.IChecker;

public class UnusedVariableChecker implements IChecker{

	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<DeclarationSemantics> exceptions = handleSemantics(store);

		generateReport(exceptions);
	}
	
	private List<DeclarationSemantics> handleSemantics(SemanticsStore store){
		List<DeclarationSemantics> violations = new ArrayList<DeclarationSemantics>();
		
		// add variables to check
		Iterator<DeclarationSemantics> ir1 = store.iterator1();
		while(ir1.hasNext()){
			DeclarationSemantics ds = ir1.next();
			
			boolean used = false;
			String name = ds.getName().toString();
			Iterator<InferenceSemantics> ir2 = store.iterator2();
			while(ir2.hasNext()){
				InferenceSemantics is = ir2.next();
				if(name.equals(is.getName().toString())){
					used = true;
					break;
				}
			}
			
			if(!used){
				violations.add(ds);
			}
		}
		return violations;
	}
	
	private void generateReport(List<DeclarationSemantics> exceptions){
		if(exceptions != null && !exceptions.isEmpty()){
			System.err.println("Warning: ���±���δ��ʹ�ã�");
			for(DeclarationSemantics ds: exceptions){
				System.err.println("\t" + ds);
			}
		}
	}
}