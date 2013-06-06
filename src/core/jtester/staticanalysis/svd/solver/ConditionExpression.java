package core.jtester.staticanalysis.svd.solver;

import core.jtester.staticanalysis.svd.execution.ExpressionNode;

public class ConditionExpression {
	ExpressionNode leftExpression;
	ExpressionNode rightExpression;
	ConditionOperator operator;
	

	public ExpressionNode getLeftExpression() {
		return leftExpression;
	}


	public void setLeftExpression(ExpressionNode leftExpression) {
		this.leftExpression = leftExpression;
	}


	public ExpressionNode getRightExpression() {
		return rightExpression;
	}


	public void setRightExpression(ExpressionNode rightExpression) {
		this.rightExpression = rightExpression;
	}


	public ConditionOperator getOperator() {
		return operator;
	}


	public void setOperator(ConditionOperator operator) {
		this.operator = operator;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
