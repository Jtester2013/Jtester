package core.common.model.annotator;

import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import core.common.model.om.OMShared;
import core.common.util.ASTUtil;

/**
 * 
 * @author Bin
 *
 *	Configuration File Ast Visitor
 */
public class ConfigurationReaderVisitor extends ASTVisitor {
	
	private Configuration conf;
	private Stack<ASTNode> path;
	private EnumVisitor enumVisitor;
	

	public ConfigurationReaderVisitor(Configuration configuration) {
		super();
		this.conf = configuration;
		this.path = new Stack<ASTNode>();
		enumVisitor = new EnumVisitor();
	}
	
	@Override
	public void preVisit(ASTNode node){
		this.path.push(node);
	}
	
	@Override
	public void postVisit(ASTNode node) {
		this.path.pop();
	}
	
	@Override
	public boolean visit(AnnotationTypeDeclaration node){
		this.conf.getAnnotations().put(node.getName().toString(), node);
		String annotName = node.getName().toString();
		this.enumVisitor.values.clear();
		node.accept(this.enumVisitor);
		String[] values = this.enumVisitor.values.toArray(new String[0]);
		OMShared.putNewAnnotationType(annotName, values);
		return super.visit(node);
	}
	
	class EnumVisitor extends ASTVisitor{
		ArrayList<String> values = new ArrayList<String>();
		public boolean visit(EnumConstantDeclaration node){
			values.add(node.getName().toString());
			return super.visit(node);
		}
	}
	
	@Override
	public boolean visit(VariableDeclarationStatement node){
		String scope = ASTUtil.getVariableNodeScope(path, node);
		this.conf.getVariables().put(scope, node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(MethodDeclaration node){
		String scope = ASTUtil.getMethodNodeScope(path, node);
		this.conf.getMethods().put(scope, node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(FieldDeclaration node){
		String scope = ASTUtil.getFieldNodeScope(path, node);
		this.conf.getFields().put(scope, node);
		return super.visit(node);
	}
	
	@Override
	public boolean visit(SingleVariableDeclaration node){
		String scope = ASTUtil.getArgumentNodeScope(path, node);
		this.conf.getArguments().put(scope, node);
		return super.visit(node);
	}
	
}
