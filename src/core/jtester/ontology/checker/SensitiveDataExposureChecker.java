package core.jtester.ontology.checker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;

import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.util.ASTUtil;
import core.jtester.ontology.reasoner.IChecker;

public class SensitiveDataExposureChecker implements IChecker {
	private CompilationUnit root;
	
	@Override
	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		root = (CompilationUnit) file.get(JobConst.AST);
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<InferenceSemantics> exceptions = handleSemantics(store);
		
		if(generateReport(exceptions)){
			data.getTestResult().addViolation(JobConst.ONTOLOGY_SENSITIVE_DATA_EXPOSURE);
		}
	}
	
	private List<InferenceSemantics> handleSemantics(SemanticsStore store){
		List<InferenceSemantics> toCheck = new ArrayList<InferenceSemantics>();
		List<InferenceSemantics> violations = new ArrayList<InferenceSemantics>();
		
		Iterator<InferenceSemantics> ir1 = store.iterator2();
		while(ir1.hasNext()){
			InferenceSemantics semantics = ir1.next();
			if(semantics.getMethod() == null){
				continue;
			}
			
			// deal with lines like: writer.write(e.getMessage())
			if(semantics.getMethod().toString().equals(JobConst.EXPOSURE_WRITE)){
				List<Expression> arguments = semantics.getArguments();
				for(Expression arg: arguments) {
					InferenceSemantics argSemantics = handleInputArgument(arg, store);
					// deal with lines like: Exception e
					if(checkInputArgument(argSemantics, store)){
						toCheck.add(semantics);
					}
				}
			}
		}
		
		// deal with lines like: PrintWriter writer = resp.getWriter();
		for(InferenceSemantics is: toCheck){
			List<DeclarationSemantics> declarations = is.getDeclaraions();
			if(checkResponseType(declarations)){
				violations.add(is);
				continue;
			}
			
			for(DeclarationSemantics ds: declarations){
				InferenceSemantics decIs = handleInputArgument(ds.getValue(), store);
				if(checkInferenceType(decIs, store)){
					violations.add(is);
				}
			}
		}
		
		return violations;
	}
	
	private boolean checkInferenceType(InferenceSemantics arg, SemanticsStore store){
		if (arg == null) {
			return false;
		}
		
		List<DeclarationSemantics> declarations = arg.getDeclaraions();
		if(checkResponseType(declarations)){
			return true;
		}
		
		for(DeclarationSemantics ds: declarations){
			Expression exp = ds.getValue();
			InferenceSemantics is = handleInputArgument(exp,store);
			if(checkInferenceType(is, store)){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkInputArgument(InferenceSemantics arg, SemanticsStore store){
		if (arg == null) {
			return false;
		}
		
		List<DeclarationSemantics> declarations = arg.getDeclaraions();
		if(checkExposureType(declarations)){
			return true;
		}
		
		for(DeclarationSemantics ds: declarations){
			Expression exp = ds.getValue();
			InferenceSemantics is = handleInputArgument(exp,store);
			if(checkInputArgument(is, store)){
				return true;
			}
		}
		return false;
	}
	
	
	private InferenceSemantics handleInputArgument(Expression arg, SemanticsStore store){
		int line = ASTUtil.getLineNumber(root, arg);
		InferenceSemantics semantics = null;
		
		Iterator<InferenceSemantics> ir1 = store.iterator2();
		while(ir1.hasNext()){
			InferenceSemantics is = ir1.next();
			
			if(is.getLine()!=line){
				continue;
			}
			
			if(is.getName() == null){
				continue;
			}
			
			if(arg.toString().contains(is.getName().toString()) && is.toString().contains(arg.toString())){
				semantics = is;
				break;
			}
		}
		return semantics;
	}

	private boolean checkExposureType(List<DeclarationSemantics> declarations){
		String types[] = {JobConst.EXPOSURE_TYPE1, JobConst.EXPOSURE_TYPE2};
		for(DeclarationSemantics ds: declarations){
			for(String type: types){
				if(ds.getType().toString().equals(type)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkResponseType(List<DeclarationSemantics> declarations){
		String types[] = {JobConst.EXPOSURE_RESPONSE_TYPE1};
		for(DeclarationSemantics ds: declarations){
			for(String type: types){
				if(ds.getType().toString().equals(type)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean generateReport(List<InferenceSemantics> exceptions){
		boolean report = false;
		if(exceptions != null && !exceptions.isEmpty()){
			System.err.println("Warning: 可能暴露系统敏感信息!");
			for(InferenceSemantics ds: exceptions){
				System.err.println("\t" + ds.toStringWithContext());
				if(report == false){
					report = true;
				}
			}
		}
		
		return report;
	}
}
