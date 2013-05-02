package core.jtester.ontology.ifalwaystrueorfalse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;

import plugin.util.Const;

import core.common.model.jobflow.JobConst;
import core.common.model.test.TestData;
import core.common.model.test.TestResultItem;
import core.common.util.Abacus;
import core.jtester.api.RuleSet;
import core.jtester.ontology.reasoner.IChecker;

public class IfAlwaysTrueOrFalseChecker extends ASTVisitor implements IChecker{
	TestData data;
	// only deal with NumberLiteral field
	private Map<String, Long> fields;
	
	public IfAlwaysTrueOrFalseChecker(){
		fields = new HashMap<String, Long>();
	}
	
	public void check(TestData data){
		this.data = data;
		Object astObj = data.getCurrentTestFile().get(JobConst.AST);
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
		} if (body instanceof ExpressionStatement || body instanceof TypeDeclarationStatement || body instanceof EmptyStatement){
			parseField((ExpressionStatement)body);
		}
		else if (body instanceof IfStatement){
			IfStatement ifStatement = (IfStatement)body;
			boolean result = parseDecision(ifStatement.getExpression());
			if(!result){
				System.err.println("If Condition Always True!");
				TestResultItem item = new TestResultItem(data.getCurrentTestFile().getPath(), Const.OWL_REASONING, RuleSet.WARNING);
				item.add(Const.IF_ALWAYS_TRUE + ifStatement.getExpression());
				data.getTestResult().add(data.getCurrentTestFile().getPath(), item);
			}else {
				System.err.println("If Condition Always False!");
				TestResultItem item = new TestResultItem(data.getCurrentTestFile().getPath(), Const.OWL_REASONING, RuleSet.WARNING);
				item.add(Const.IF_ALWAYS_FALSE + ifStatement.getExpression());
				data.getTestResult().add(data.getCurrentTestFile().getPath(), item);
			}
		} 
	}
	
	private boolean parseDecision(Expression exp) {
		if(exp instanceof InfixExpression){
			Expression left = ((InfixExpression) exp).getLeftOperand();
			Expression right = ((InfixExpression) exp).getRightOperand();
			
			long leftVal = Abacus.compute(left, fields);
			long rightVal = Abacus.compute(right, fields);
			
			Operator op = ((InfixExpression) exp).getOperator();
			
			return Abacus.compare(op, leftVal, rightVal);
		}
		return false;
	}
	
	private void parseField(VariableDeclarationStatement body) {
		// deal with int field only
		if(!body.getType().toString().equals(JobConst.INT)){
			return;
		}
		
		@SuppressWarnings("unchecked")
		List<VariableDeclarationFragment> fragments = body.fragments();
		for(int i=0;i< fragments.size();i++){
			SimpleName var = fragments.get(i).getName();
			Expression exp = fragments.get(i).getInitializer();
			long value = Abacus.compute(exp, fields);
			fields.put(var.toString(), value);
		}
	}
	
	private void parseField(ExpressionStatement body){
		Expression exp = body.getExpression();
		if(exp instanceof Assignment){
			Assignment assignment = (Assignment) exp;
			Expression var = assignment.getLeftHandSide();
			if(fields.get(var.toString()) == null){
				return;
			}
			Expression rightHandExp = assignment.getRightHandSide();
			long value = Abacus.compute(rightHandExp, fields);
			fields.put(var.toString(), value);
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
