package core.jtester.staticanalysis.data_detained;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.osgi.framework.Bundle;

import plugin.util.Const;

import core.common.model.annotator.AnnotationASTVisitor;
import core.common.model.annotator.Configuration;
import core.common.model.annotator.ConfigurationReader;
import core.common.model.annotator.ConstraintError;
import core.common.model.annotator.ConstraintVisitor;
import core.common.model.annotator.DeclarationVisitor;
import core.common.model.annotator.Src;
import core.common.model.annotator.TypeWorklist;
import core.common.model.jobflow.IJob;
import core.common.model.om.ConstraintVariable;
import core.common.model.om.OMShared;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;

public class DataDetainedAnalyzer implements IJob{
	private String name = this.getClass().getSimpleName();
	private Configuration conf;
	
	public DataDetainedAnalyzer(){
		Bundle bundle = Platform.getBundle(Const.JTESTER);  
		URL url = bundle.getResource(Const.DATA_DETAINED_PATH);  
		try {
			String filePath = FileLocator.toFileURL(url).getFile();
			conf = ConfigurationReader.get(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public boolean run(TestData data) {
		TestFile f = data.getCurrentTestFile();
		if(f.isCheckedByRule(name)){
			return true;
		}
		
		Map<String, Src> srcs = annotate(data);
		
		decorate(srcs);
		
		constraint(srcs);
		
		worklist();
		
		return true;
	}
	
	private Map<String, Src> annotate(TestData data){
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
					System.out.println(message);
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
	
	private void worklist(){
		TypeWorklist worklist = new TypeWorklist(OMShared.getConstraintTable().values().toArray(new ConstraintVariable[0]));
		try{
			worklist.executeAnalysis();
		}catch(ConstraintError err){
			System.out.println("error: "+err.getMessage());
		}
		OMShared.reset();
	}

	public String getName() {
		return name;
	}
}
