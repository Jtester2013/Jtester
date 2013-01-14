package core.common.parser;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;

public class JavaParser implements IJob{
	private String name = this.getClass().getName();

	@Override
	public boolean run(final TestData data) {
		if(data.isParsed()){
			return true;
		}

		FileASTRequestor requestor = new FileASTRequestor() {
			public void acceptAST(String sourceFilePath, CompilationUnit ast) {
				TestFile file = data.getFile(sourceFilePath);
				file.put(JobConst.AST, ast);
			}
		};
		
		parseCode(data.getFilePaths(), data.getEnvPaths(), requestor);
		data.setIsParsed(true);
		return true;
	}

	public void parseCode(List<String> filePaths, List<String> envPaths, FileASTRequestor requestor) {
		@SuppressWarnings("unchecked")
		Map<String, String> options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_5, options);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setCompilerOptions(options);
		parser.setEnvironment(null, envPaths.toArray(new String[0]), null, true);
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(true);
		parser.setBindingsRecovery(true);
		parser.createASTs(filePaths.toArray(new String[0]), null, new String[0], requestor, null);
	}

	@Override
	public String getName() {
		return name;
	}

}
