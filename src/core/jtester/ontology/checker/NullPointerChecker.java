package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.PrimitiveType;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.semantics.ViolationAxiom;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.jtester.ontology.reasoner.IChecker;

public class NullPointerChecker implements IChecker{

	@Override
	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<InferenceSemantics> exceptions = handleSemantics(store);
		
		generateReport(exceptions);
		produceAbox(data, exceptions);
		
	}

	private List<InferenceSemantics> handleSemantics(SemanticsStore store) {
		List<DeclarationSemantics> toCheck = new ArrayList<DeclarationSemantics>();
		List<InferenceSemantics> violations = new ArrayList<InferenceSemantics>();
		
		Iterator<DeclarationSemantics> ir = store.iterator1();
		while(ir.hasNext()){
			DeclarationSemantics semantics = ir.next();
			if(semantics.getType() instanceof PrimitiveType){
				// primitive type can't be null
				continue;
			}
			
			if(semantics.isArument()){
				// argument value is unknown
				continue;
			}

			if(semantics.getValue() == null || semantics.getValue() instanceof NullLiteral){
				toCheck.add(semantics);
			}
		}
		
		for(DeclarationSemantics ds : toCheck){
			Iterator<InferenceSemantics> ir2 = store.iterator2();
			while(ir2.hasNext()){
				InferenceSemantics is = ir2.next();
				if(is.getName().toString().equals(ds.getName().toString())){
					violations.add(is);
				}
			}
		}
		
		return violations;
	}
	
	private boolean generateReport(List<InferenceSemantics> exceptions) {
		if(exceptions == null || exceptions.isEmpty()){
			return false;
		}
		
		boolean report = false;
		System.err.println("Warning: “˝”√ø’÷∏’Î£°");
		for(InferenceSemantics is : exceptions){
			System.err.println("\t" + is.toStringWithContext());
			if(report == false){
				report = true;
			}
		}
		return report;
	}
	
	private void produceAbox(TestData data, List<InferenceSemantics> exceptions) {
		if(exceptions != null && !exceptions.isEmpty()){
			for(InferenceSemantics ds: exceptions){
				ViolationAxiom axiom = new ViolationAxiom(JobConst.ONTOLOGY_USE_NULL_POINTER);
				axiom.setName(ds.getName().toString());
				axiom.setContext(ds.toStringWithContext());
				axiom.addDataProperty(JobConst.ONTOLOGY_DATA_PROPERTY_ASSIGNED, Boolean.toString(false));
				axiom.addDataProperty(JobConst.ONTOLOGY_DATA_PROPERTY_USED, Boolean.toString(true));
				data.getTestResult().addViolation(axiom);
			}
		}
	}
}
