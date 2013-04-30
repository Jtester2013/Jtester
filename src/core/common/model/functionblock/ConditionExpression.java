package core.common.model.functionblock;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;

/**
 * this class is only used to store 
 * condition expressions and its values from control flow graph  
 * 
 * @author XingxuWu
 *
 */
public class ConditionExpression {
	private Expression expression;
	private boolean condition;
	private int line;
	
	public ConditionExpression(Expression expression, boolean condition) {
		this.expression = expression;
		this.condition = condition;
	}

	public Expression getExpression() {
		return expression;
	}
	
	public boolean isCondition() {
		return condition;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setCondition(boolean condition) {
		this.condition = condition;
	}
	
	public void setLine(int lineNumber){
		this.line = lineNumber;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(line);
		sb.append(": ");

		switch(expression.getParent().getNodeType()){
		case ASTNode.IF_STATEMENT:
			sb.append("if(");
			sb.append(expression);
			sb.append(")");
			break;
		case ASTNode.WHILE_STATEMENT:
			sb.append("while(");
			sb.append(expression);
			sb.append(")");
			break;
		}
		sb.append("\t");
		sb.append(condition);
		
		return sb.toString();
	}
}
