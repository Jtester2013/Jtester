package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.util.Abacus;
import core.jtester.ontology.reasoner.IChecker;

public class DividedByZeroChecker implements IChecker{

	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<InferenceSemantics> exceptions = handleSemantics(store);

		generateReport(exceptions);
	}
	
	private List<InferenceSemantics> handleSemantics(SemanticsStore store){
		List<InferenceSemantics> toCheck = new ArrayList<InferenceSemantics>();
		List<InferenceSemantics> violations = new ArrayList<InferenceSemantics>();
		
		Iterator<InferenceSemantics> ir = store.iterator2();
		while(ir.hasNext()){
			InferenceSemantics is = ir.next();
			ASTNode context = is.getContext();
			if(!(context instanceof InfixExpression)){
				// not binary expression
				continue;
			}
			
			InfixExpression ie = (InfixExpression)context;
			if(!ie.getOperator().equals(InfixExpression.Operator.DIVIDE)){
				// not division expression
				continue;
			}
			
			if(!ie.getRightOperand().toString().equals(is.getName().toString())){
				// not divisor
				continue;
			}
			
			toCheck.add(is);
		}
		
		for(InferenceSemantics is: toCheck){
			Iterator<DeclarationSemantics> ir2 = store.iterator1();
			while(ir2.hasNext()){
				DeclarationSemantics ds = ir2.next();
				if(ds.getName().toString().equals(is.getName().toString())){
					boolean isZero = isExpressionZero(ds.getValue(), store);
					if(isZero){
						violations.add(is);
					}
				}
			}
		}
		
		return violations;
	}
	
	private boolean isExpressionZero(Expression exp, SemanticsStore store){
		if(exp == null){
			return false;
		}
		
		Map<String, Integer> fields = new HashMap<String, Integer>();
		Iterator<DeclarationSemantics> ir = store.iterator1();
		while(ir.hasNext()){
			DeclarationSemantics ds = ir.next();
			if(!ds.getType().isPrimitiveType()){
				// not primitive type
				continue;
			}
			setFields(ds, fields);
		}
		
		return Abacus.compute(exp, fields) == 0;
	}
	
	// try but not promise to get value of all variables
	private void setFields(DeclarationSemantics ds, Map<String, Integer> fields){
		Expression exp = ds.getValue();
		switch(exp.getNodeType()){
		case ASTNode.NUMBER_LITERAL:
			fields.put(ds.getName().toString(), Integer.parseInt(exp.toString()));
			break;
		case ASTNode.SIMPLE_NAME:
			int infer = fields.get(exp.toString());
			fields.put(ds.getName().toString(), infer);
			break;
		case ASTNode.INFIX_EXPRESSION:
			InfixExpression ie = (InfixExpression)exp;
			int result = Abacus.compute(exp, fields);
			fields.put(ds.getName().toString(), result);
			break;
		}
	}
	
	private void generateReport(List<InferenceSemantics> exceptions){
		if(exceptions != null && !exceptions.isEmpty()){
			System.err.println("Error: ³ýÊýÎª0!");
			for(InferenceSemantics ds: exceptions){
				System.err.println("\t" + ds.toStringWithContext());
			}
		}
	}
}
