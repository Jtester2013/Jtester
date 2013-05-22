package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.util.ASTUtil;
import core.jtester.ontology.reasoner.IChecker;

public class RemoveInIterationChecker implements IChecker{
	private CompilationUnit root;
	
	@Override
	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		root = (CompilationUnit) file.get(JobConst.AST);
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<InferenceSemantics> exceptions = handleSemantics(store);
		
		if(generateReport(exceptions)){
			data.getTestResult().addViolation(JobConst.ONTOLOGY_REMOVE_IN_ITERATION);
		}
	}

	/**
	 * deal with lines like: _hConsoles.remove(console); // VIOLATION
	 * @param store
	 * @return
	 */
	private List<InferenceSemantics> handleSemantics(SemanticsStore store) {
		List<InferenceSemantics> toCheck = new ArrayList<InferenceSemantics>();
		List<InferenceSemantics> violations = new ArrayList<InferenceSemantics>();
		
		Iterator<InferenceSemantics> ir = store.iterator2();
		while(ir.hasNext()){
			InferenceSemantics is = ir.next();
			Name methodInvocation = is.getMethod();
			// deal with lines like: _hConsoles.remove(console); // VIOLATION
			if(methodInvocation != null && methodInvocation.toString().equals(JobConst.REMOVE_TYPE_1)){
				toCheck.add(is);
			}
		}
		
		for(InferenceSemantics is: toCheck){
			List<Expression> arguments = is.getArguments();
			// remove方法应该只包含一个参数 _hConsoles.remove(console)
			if(arguments == null || arguments.size() != 1){
				continue;
			}
			
			// 参数应该是从Iterator的next方法得到 IConsole console = (IConsole) iterator.next();
			InferenceSemantics semantics = getIteratorSemantics(arguments.get(0), store);
			if(semantics == null || !semantics.getMethod().toString().equals(JobConst.ITERATOR_NEXT)){
				continue;
			}
			
			// iterator应该是从集合的iterator方法得到: Iterator iterator = _hConsoles.iterator();
			boolean result = false;
			List<DeclarationSemantics> declarations = semantics.getDeclaraions();
			for(DeclarationSemantics ds: declarations){
				if(!ds.getType().toString().equals(JobConst.ITERATOR_TYPE)){
					continue;
				}
				
				InferenceSemantics set = getIterator(ds.getValue(), store);
				
				// _hConsoles.remove(console) and Iterator iterator = _hConsoles.iterator();
				// must have the same _hConsoles
				if(!set.getName().toString().equals(is.getName().toString())){
					continue;
				}
				
				if(set.getMethod().toString().equals(JobConst.ITERATOR_METHOD)){
					violations.add(is);
				}
			}
		}
		return violations;
	}
	
	/**
	 * deal with lines like: IConsole console = (IConsole) iterator.next();
	 * @param store
	 * @return
	 */
	private InferenceSemantics getIteratorSemantics(Expression exp, SemanticsStore store){
		InferenceSemantics semantics = null;
		Iterator<DeclarationSemantics> ir = store.iterator1();
		while(ir.hasNext()){
			DeclarationSemantics ds = ir.next();
			// try to find the definition for an expression:  IConsole console
			if(!ds.getName().toString().equals(exp.toString())){
				continue;
			}
			// try to get the inference semantics of an expression:  (IConsole) iterator.next();
			InferenceSemantics is = getIterator(ds.getValue(), store);
			if(is == null){
				continue;
			}
			
			semantics = is;
		}
		return semantics;
	}
	
	private InferenceSemantics getIterator(Expression exp, SemanticsStore store){
		InferenceSemantics is = null;
		String name = getIteratorHelper(exp, store);
		
		Iterator<InferenceSemantics> ir = store.iterator2();
		while(ir.hasNext()){
			InferenceSemantics semantics = ir.next();
			
			if(semantics.getName().toString().equals(name) 
					&& semantics.getLine() == ASTUtil.getLineNumber(root, exp)){
				is = semantics;
			}
		}
		
		return is;
	}
	
	private String getIteratorHelper(Expression exp, SemanticsStore store){
		String name = null;
		switch(exp.getNodeType()){
		case ASTNode.SIMPLE_NAME:
			name = exp.toString();
			break;
		case ASTNode.CAST_EXPRESSION:
			CastExpression ce = (CastExpression)exp;
			Expression irExp = ce.getExpression();
			name = getIteratorHelper(irExp, store);
			break;
		case ASTNode.METHOD_INVOCATION:
			MethodInvocation mi = (MethodInvocation)exp;
			name = getIteratorHelper(mi.getExpression(), store);
			break;
		default:
			name = null;
			System.err.println("can't handle iterator expression: " + exp);
			break;
		}
		return name;
	}
	
	private boolean generateReport(List<InferenceSemantics> exceptions) {
		if(exceptions == null || exceptions.isEmpty()){
			return false;
		}
		
		boolean report = false;
		System.err.println("Warning: 不正确迭代操作！");
		for(InferenceSemantics is : exceptions){
			System.err.println("\t" + is.toStringWithContext());
			if(report == false){
				report = true;
			}
		}
		return report;
	}
}
