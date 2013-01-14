package core.common.model.annotator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

import core.common.model.jobflow.JobConst;
import core.common.model.test.TestFile;

/**
 * 
 * @author Bin
 * 
 * Every Source code file represents to this model. 
 *
 */
public class Src {
	
	private String filePath;
	private String src;
	private CompilationUnit astTree;
	
	public Src(TestFile file){
		filePath = file.getPath();
		astTree = (CompilationUnit) file.get(JobConst.AST);
		src = file.getCode();
	}
	
	public Src(String fileAbsolutePath) {
		File file = new File(fileAbsolutePath);
		if(!file.exists()){
			throw new InvalidParameterException("This file is not existed!");
			
		}
		if(!file.getName().endsWith("java")){
			throw new InvalidParameterException("This file is not java source file");
		}
		this.filePath = fileAbsolutePath;
		
		try {
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while (null != (line = reader.readLine())) {
				buffer.append(line);
				buffer.append("\n");
			}
			reader.close();
			this.src = buffer.toString();
			
		} catch (FileNotFoundException e) {
			throw new InvalidParameterException("This file is not existed!");
		} catch (IOException e) {
			throw new RuntimeException("IO problem");
		}
		
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		@SuppressWarnings("rawtypes")
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_5,
				options);
		parser.setCompilerOptions(options);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(this.src.toCharArray());
		this.astTree = (CompilationUnit) parser.createAST(null);
	}

	public String getFileAbsolutePath() {
		return filePath;
	}

	public String getSrc() {
		return src;
	}

	public ASTNode getAstTree() {
		return astTree;
	}
	
	public int getLineNumber(ASTNode node){
		return astTree.getLineNumber(node.getStartPosition());
	}
	
	public void accept(ASTVisitor visitor){
		this.astTree.accept(visitor);
	}
	
}
