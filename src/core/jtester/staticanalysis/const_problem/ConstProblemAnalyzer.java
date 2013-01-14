package core.jtester.staticanalysis.const_problem;

import java.io.File;

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

public class ConstProblemAnalyzer implements IJob{
	private String name = this.getClass().getSimpleName();
	private Configuration conf;
	
	public boolean run(TestData data) {
		conf = ConfigurationReader.get(new File(JobConst.CONST_PROBLEM_PATH));
		TestFile file = data.getCurrentTestFile();
		
		Src src = new Src(file);
		ASTVisitor visitor = new AnnotationASTVisitor(conf, src);
		CompilationUnit cu = (CompilationUnit) src.getAstTree();
		System.out.println("src path: "+ src.getFileAbsolutePath());
		cu.accept(visitor);
		
//		IProblem[] problems = cu.getProblems();
//		if (problems != null && problems.length > 0) {
//			for (int i = 0; i < problems.length; ++i) {
//				IProblem problem = problems[i];
//				String message = String.format("%s : %s line: %s\n",
//						src.getFileAbsolutePath(), problem.getMessage(),
//						problem.getSourceLineNumber());
//				System.out.println(message);
//			}
//		}		
		
		
//		DeclarationVisitor dv = new DeclarationVisitor(OMShared.getConstraintTable(), src);
//		src.accept(dv);
//		
//		ConstraintVisitor cv = new ConstraintVisitor(OMShared.getConstraintTable(), src);
//		src.accept(cv);
//		
//		TypeWorklist worklist = new TypeWorklist(OMShared.getConstraintTable().values().toArray(new ConstraintVariable[0]));
//		try{
//			worklist.executeAnalysis();
//		}catch(ConstraintError err){
//			System.out.println("source: "+src.getSrc());
//			System.out.println("error: "+err.getMessage());
//		}
		
		return true;
	}

	public String getName() {
		return name;
	}
}
