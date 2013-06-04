package core.common.svd.path;

import org.eclipse.jdt.core.dom.Expression;


public class ConcreateExpression {
	Expression exp;
	boolean positive;
	public ConcreateExpression(Expression e, boolean objective){
		this.exp=e;
		this.positive=objective;
	}
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	public boolean isPositive() {
		return positive;
	}
	public void setPositive(boolean objective) {
		this.positive = objective;
	}
}
