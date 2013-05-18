package core.common.svd.solver;

public class ExpressionNode {
	ExpressionType type;// ���ExpressionNode�����ͣ�������intֵ/���ű�������ʱright��leftΪnull�����͸��ӱ��ʽ����ʱleft��right��Ϊnull��
	String value;
	ExpressionOperator operator;
	ExpressionNode left;
	ExpressionNode right;
	
	public ExpressionNode(ExpressionType type, String value){
		if (type!=ExpressionType.single_int||type!=ExpressionType.single_variable) {
			System.out.println("The type is not suitable for this construct method: "+ type);
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
	
	public static void main(String[] args){
	}
}
