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

public class FileContentInjectionChecker implements IChecker {
	private CompilationUnit root;
	
	@Override
	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		root = (CompilationUnit) file.get(JobConst.AST);
		
		SemanticsStore store = (SemanticsStore) file.get(JobConst.SEMANTICS);
		List<InferenceSemantics> exceptions = handleSemantics(store);
		
		if(generateReport(exceptions)){
			data.getTestResult().addViolation(JobConst.ONTOLOGY_FILE_CONTENT_INJECTION);
		}
	}
	
	private List<InferenceSemantics> handleSemantics(SemanticsStore store){
		List<InferenceSemantics> toCheck = new ArrayList<InferenceSemantics>();
		List<InferenceSemantics> violations = new ArrayList<InferenceSemantics>();
		
		Iterator<InferenceSemantics> ir1 = store.iterator2();
		while(ir1.hasNext()){
			InferenceSemantics semantics = ir1.next();
			// deal with lines like: fOut.write(sData.getBytes())
			if(semantics.getMethod() == null){
				continue;
			}
			
			// deal with lines like: FileOutputStream fOut = new FileOutputStream(fileName);
			if(semantics.getMethod().toString().equals(JobConst.INJECTION_WRITE)){
				List<DeclarationSemantics> declarations = semantics.getDeclaraions();
				if(checkStreamType(declarations)){
					toCheck.add(semantics);
				}
			}
		}
		
		// deal with lines like: HttpServletRequest req
		for(InferenceSemantics is: toCheck){
			List<Expression> arguments = is.getArguments();
			for(Expression arg: arguments){
				InferenceSemantics argSemantics = handleInputArgument(arg, store);
				if(checkInputArgument(argSemantics, store)){
					violations.add(is);
				}
			}
		}
		
		return violations;
	}
	
	private boolean checkInputArgument(InferenceSemantics arg, SemanticsStore store){
		if (arg == null) {
			return false;
		}
		
		List<DeclarationSemantics> declarations = arg.getDeclaraions();
		if(checkRequestType(declarations)){
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

	private boolean checkStreamType(List<DeclarationSemantics> declarations){
		String types[] = {JobConst.INJECTION_STREAM_TYPE1};
		for(DeclarationSemantics ds: declarations){
			for(String type: types){
				if(ds.getType().toString().equals(type)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkRequestType(List<DeclarationSemantics> declarations){
		String types[] = {JobConst.INJECTION_REQUEST_TYPE1};
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
			System.err.println("Warning: 文件内容可能被注入恶意代码!");
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
