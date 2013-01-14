package core.jtester.staticanalysis.const_problem;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import core.common.model.annotator.AnnotationASTVisitor;
import core.common.model.annotator.Configuration;
import core.common.model.annotator.ConfigurationReader;
import core.common.model.annotator.ConstraintError;
import core.common.model.annotator.ConstraintVisitor;
import core.common.model.annotator.DeclarationVisitor;
import core.common.model.annotator.Src;
import core.common.model.annotator.TypeWorklist;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.om.ConstraintVariable;
import core.common.model.om.OMShared;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.model.test.TestResult;
import core.common.model.test.TestResultItem;
import core.jtester.api.RuleSet;

public class ConstProblemAnalyzer implements IJob{
	private String name = this.getClass().getSimpleName();
	private Configuration conf = ConfigurationReader.get(new File(JobConst.CONST_PROBLEM_PATH));
	
	public boolean run(TestData data) {
		TestFile file = data.getCurrentTestFile();
		if(file.isCheckedByRule(name)){
			return true;
		}
		
		Map<String, Src> srcs = annotate(data);
		
		decorate(srcs);
		
		constraint(srcs);
		
		worklist(data);
		
		return true;
	}
	
	private Map<String, Src> annotate(TestData data){
		TestResult result = data.getTestResult();
		Map<String, Src> srcs = new Hashtable<String, Src>();
		
		List<TestFile> files = data.getFiles();
		for (TestFile file: files) {
			file.setCheckedByRule(name);
			Src src = new Src(file);
			srcs.put(src.getFileAbsolutePath(), src);
			
			ASTVisitor visitor = new AnnotationASTVisitor(conf, src);

			CompilationUnit cu = (CompilationUnit) src.getAstTree();
			cu.accept(visitor);
			IProblem[] problems = cu.getProblems();
			if (problems != null && problems.length > 0) {
				for (int i = 0; i < problems.length; ++i) {
					IProblem problem = problems[i];
					String message = String.format("%s : %s line: %s\n",
							src.getFileAbsolutePath(), problem.getMessage(),
							problem.getSourceLineNumber());
					TestResultItem item = new TestResultItem(file.getPath(), getName(), RuleSet.WARNING);
					item.add(message);
					result.add(file.getPath(), item);
				}
			}
		}
		
		return srcs;
	}
	
	private void decorate(Map<String, Src> srcs){
		for (Src src : srcs.values()) {
			DeclarationVisitor dv = new DeclarationVisitor(
					OMShared.getConstraintTable(), src);
			src.accept(dv);
		}
	}
	
	private void constraint(Map<String, Src> srcs){
		for (Src src : srcs.values()) {
			ConstraintVisitor cv = new ConstraintVisitor(
					OMShared.getConstraintTable(), src);
			src.accept(cv);
		}
	}
	
	private void worklist(TestData data){
		TestResult result = data.getTestResult();
		
		TypeWorklist worklist = new TypeWorklist(OMShared.getConstraintTable().values().toArray(new ConstraintVariable[0]));
		try{
			worklist.executeAnalysis();
		}catch(ConstraintError err){
			TestResultItem item = new TestResultItem(data.getCurrentTestFile().getPath(), getName(), RuleSet.ERROR);
			item.add(err.getMessage());
			result.add(data.getCurrentTestFile().getPath(), item);
		}
		
		OMShared.reset();
	}

	public String getName() {
		return name;
	}
	
}