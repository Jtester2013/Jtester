package core.jtester.ontology.empty_loop;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;

import core.common.model.jobflow.JobConst;
import core.common.model.test.TestFile;
import core.jtester.ontology.reasoner.IChecker;

public class EmptyLoopChecker extends ASTVisitor implements IChecker{
	public void check(TestFile file){
		Object astObj = file.get(JobConst.AST);
		ASTNode n = (ASTNode) astObj;
		
		n.accept(this);
	}

	public boolean visit(MethodDeclaration n) {
		Block body = n.getBody();
		checkSubTree(body);
		return true;
	}
	
	private void checkSubTree(ASTNode body) {
		if (body instanceof Block) {
			Block block = (Block) body;
			List children = block.statements();
			for (int i = 0; i < children.size(); i++) {
				ASTNode node = (ASTNode) children.get(i);
				checkSubTree(node);
			}
		} else if (body instanceof WhileStatement){
			Block statements = (Block) ((WhileStatement)body).getBody();
			if(statements.statements().isEmpty()){
				System.out.println("===Empty While Loop===\n" + body);
			}
		} 
	}

	// visit methods
	public boolean visit(CompilationUnit n) {
		return true;
	}

	// leave methods
	public boolean leave(CompilationUnit tu) {
		return true;
	}

	public boolean visit(ASTNode astAmbiguousNode) {
		return true;
	}
}
