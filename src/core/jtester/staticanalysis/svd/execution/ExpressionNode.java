package core.jtester.staticanalysis.svd.execution;

import java.util.HashMap;

import choco.kernel.model.variables.integer.IntegerVariable;

public class ExpressionNode {
	ExpressionType type;// 这个ExpressionNode的类型，可以是int值/符号变量（此时right和left为null）、和复杂表达式（此时left和right不为null）
	String value;
	ExpressionOperator operator;
	ExpressionNode left;
	ExpressionNode right;
	
	public ExpressionNode(ExpressionType type, String value){
		if (type!=ExpressionType.single_int && type!=ExpressionType.single_variable &&  type!=ExpressionType.not_defined) {
//			System.err.println("The type is not suitable for this construct method: "+ type);
		}else{
			this.type = type;
			this.value=value;
			this.left=null;
			this.right=null;
			this.operator=null;
		}
	}
	
	public ExpressionNode(ExpressionType type, String value, ExpressionOperator operator, ExpressionNode left, ExpressionNode right){
		if (type==ExpressionType.expression) {
			this.type=type;
			this.value=null;
			this.operator=operator;
			this.left=left;
			this.right=right;
		}else if(type==ExpressionType.single_int||type==ExpressionType.single_variable){
			this.type=type;
			this.value=value;
			this.operator=null;
			this.right=this.left=null;
		}else{
			System.out.println("This type is illegal: "+type);
		}
	}
	public ExpressionType getType() {
		return type;
	}
	public void setType(ExpressionType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ExpressionOperator getOperator() {
		return operator;
	}
	public void setOperator(ExpressionOperator operator) {
		this.operator = operator;
	}
	public ExpressionNode getLeft() {
		return left;
	}
	public void setLeft(ExpressionNode left) {
		this.left = left;
	}
	public ExpressionNode getRight() {
		return right;
	}
	public void setRight(ExpressionNode right) {
		this.right = right;
	}
	
	public ExpressionNode clone(){
		ExpressionNode copyExpressionNode = new ExpressionNode(this.type, this.value);
		if (this.left!=null) {
			copyExpressionNode.left = this.left.clone();
		}
		if (this.right!=null) {
			copyExpressionNode.right = this.right.clone();
		}
		if (this.operator!=null) {
			copyExpressionNode.operator = this.operator;
		}
		if (this.type != null){
			copyExpressionNode.type = this.type;
		}
		if (this.value != null){
			copyExpressionNode.value = this.value;
		}
		return copyExpressionNode;
	}
	
	public String toString(){
		
		return preVisitExpressionTree();
	}
	private String preVisitExpressionTree(){
		String result = "";
		HashMap<ExpressionOperator, String> expression_operator = new HashMap<>();
		expression_operator.put(ExpressionOperator.div, "\\");
		expression_operator.put(ExpressionOperator.plus, "+");
		expression_operator.put(ExpressionOperator.minus, "-");
		expression_operator.put(ExpressionOperator.multi, "*");
		if (operator != null) {
			result ="(" + left.preVisitExpressionTree()
					+ expression_operator.get(operator)
					+right.preVisitExpressionTree() + ")";
		} else {
			result = value;
		}
		return result;
	}
}
