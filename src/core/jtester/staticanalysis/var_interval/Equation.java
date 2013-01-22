package core.jtester.staticanalysis.var_interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.interfaces.IBranchNode;
import core.common.model.varinterval.IntegerInterval;
import core.common.model.varinterval.VarInterval;

public class Equation {
	private ArrayList<Integer> inLabels;
	private int outLabel;
	private ASTNode rule;
	private String condition;
	private int label;
	
	private HashMap<String,Environment> env;
	private ArrayList<VarInterval> in; 
	private ArrayList<VarInterval> temp;
	
	private boolean isFirstCompute = true;
	
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public void setInLabels(ArrayList<Integer> ins){
		inLabels = ins;
	}
	
	public void setOutLabel(int label){
		outLabel = label;
	}
	
	public void setRule(ASTNode node){
		rule = node;
	}
	
	public ArrayList<Integer> getInLabels() {
		return inLabels;
	}

	public int getOutLabel() {
		return outLabel;
	}

	public ASTNode getRule(){
		return rule;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void transfer(HashMap<String, Environment> environment, int flag) {
		if(label == 0){
			return;
		}
		
		env = environment;
		temp = new ArrayList<VarInterval>();

		in = union(getInLabels());
		for (int i = 0; i < in.size(); i++) {
			temp.add(in.get(i));
		}

		// 调用相应地规则
		rules(flag);
		isFirstCompute = false;
		getEnv(label + "").setEnv(temp);
	}
	
	private void rules(int flag) {
		if(rule instanceof VariableDeclarationStatement){
			genVar((VariableDeclarationStatement)rule);
		} else if( rule instanceof ExpressionStatement){
			updateVar((ExpressionStatement)rule);
		} else if ( rule instanceof Assignment){
			updateVar((ExpressionStatement)rule);
		} else if( rule instanceof Expression){
			if( condition != null){
				boolean isTrue = condition.equals(IBranchNode.IF_THEN) || condition.equals(IBranchNode.WHILE_THEN);
				processCond((Expression)rule, isTrue);
			}
		}
	}
	
	// 目前只能处理形如 "x < 10" 或者 "x > 10" 的表达式
	private void processCond(Expression exp, boolean isTrue) {
		if(exp instanceof InfixExpression){
			InfixExpression infix = (InfixExpression) exp;
			Expression leftOprand = infix.getLeftOperand();
			Expression rightOprand = infix.getRightOperand();
			IntegerInterval leftInterval = null;
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getName().toString().equals(leftOprand.toString())) {
					leftInterval = temp.get(i).getInterval();
					break;
				}
			}
			
			IntegerInterval rightInterval = null;
			int value = 0;
			if (rightOprand instanceof NumberLiteral) {
				value = Integer.parseInt(rightOprand.toString());
			} else {
				System.err.println("we can't process expressions expect they are formatted like x > 1 or x < 1 ");
			}
			
			Operator op = infix.getOperator();
			IntegerInterval result = null;
			switch (op.toString()) {
			case "<":
				if(isTrue){
					result = new IntegerInterval(leftInterval.getMin(), value);
				}else{
					result = new IntegerInterval(value, Integer.MAX_VALUE);
				}
				break;
			case ">":
				if(isTrue){
					rightInterval = new IntegerInterval(value, Integer.MAX_VALUE);
				}else{
					rightInterval = new IntegerInterval(Integer.MIN_VALUE, value);
				}
				break;
			default:
				System.err.println("invalid oprator");
				break;
			}
			
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).getName().toString().equals(leftOprand.toString())) {
					temp.get(i).setInterval(result);
					break;
				}
			}
		}
	}

	private void updateVar(Assignment exp) {
		Expression leftOperand = ((Assignment) exp).getLeftHandSide();
		Expression rightOperand = ((Assignment) exp).getRightHandSide();
		for (VarInterval var : temp) {
			if (var.getName().toString().equals(leftOperand.toString())) {
				if (rightOperand instanceof NumberLiteral) {
					IntegerInterval interval;
					int value = Integer.parseInt(rightOperand.toString());
					interval = new IntegerInterval(value, value);
					var.setInterval(interval);
				} else if (rightOperand instanceof InfixExpression) {
					InfixExpression infix = (InfixExpression) rightOperand;
					Expression loprand = infix.getLeftOperand();
					Expression roprand = infix.getRightOperand();

					IntegerInterval leftInterval = null;
					if (loprand instanceof NumberLiteral) {
						int value = Integer.parseInt(loprand.toString());
						leftInterval = new IntegerInterval(value, value);
					} else {
						for (int i = 0; i < temp.size(); i++) {
							if (temp.get(i).getName()
									.equals(loprand.toString())) {
								leftInterval = temp.get(i).getInterval();
								break;
							}
						}
					}

					IntegerInterval rightInterval = null;
					if (roprand instanceof NumberLiteral) {
						int value = Integer.parseInt(roprand.toString());
						rightInterval = new IntegerInterval(value, value);
					} else {
						for (int i = 0; i < temp.size(); i++) {
							if (temp.get(i).toString().equals(roprand.toString())) {
								rightInterval = temp.get(i).getInterval();
								break;
							}
						}
					}

					if(leftInterval == null || rightInterval == null){
						return;
					}
					
					Operator op = infix.getOperator();
					IntegerInterval result = null;
					switch (op.toString()) {
					case "+":
						result = new IntegerInterval(leftInterval.getMin() + rightInterval.getMax(), getMax(leftInterval.getMax(), rightInterval.getMax()));
						break;
					case "*":
						result = IntegerInterval.mul(leftInterval,
								rightInterval);
						break;
					case "-":
						result = IntegerInterval.sub(leftInterval,
								rightInterval);
						break;
					case "/":
						result = IntegerInterval.div(leftInterval,
								rightInterval);
						break;
					default:
						System.err.println("undefined operator" + op);
						break;
					}

					var.setInterval(result);
				}
			}
		}
	}
	
	private void updateVar(ExpressionStatement statement) {
		Expression exp = statement.getExpression();
		if(exp instanceof Assignment){
			updateVar((Assignment)exp);
		}
	}

	private void genVar(VariableDeclarationStatement statement){
		List<VariableDeclarationFragment> fragments = statement.fragments();
		for(VariableDeclarationFragment fragment : fragments){
			Expression exp = fragment.getInitializer();
			
			IntegerInterval interval;
			if(exp instanceof NumberLiteral){
				int value = Integer.parseInt(exp.toString());
				interval = new IntegerInterval(value, value);
			}else{
				interval = new IntegerInterval(Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			
			VarInterval v= new VarInterval(fragment.getName().toString(), interval);
			temp.add(v);
		}
	
		
	}

	private ArrayList<VarInterval> union(ArrayList<Integer> inLabels){
		ArrayList<VarInterval> temp = new ArrayList<VarInterval>();
		for (int i = 0; i < inLabels.size(); i++) {
			for (int j = 0; j < env.get(inLabels.get(i)+"").getEnv().size(); j++) {
				String varName = env.get(inLabels.get(i)+"").getEnv().get(j).getName();
				IntegerInterval interval1 = env.get(inLabels.get(i)+"").getEnv().get(j).getInterval().clone();
				boolean flag = true;
				for (int k = 0; k < temp.size(); k++) {
					if (temp.get(k).getName().equals(varName)) {
						flag = false;
						IntegerInterval interval2 = temp.get(k).getInterval();
						temp.get(k).setInterval(IntegerInterval.union(interval1, interval2));
						break;
					}
				}
				if (flag)
					temp.add(env.get(inLabels.get(i)+"").getEnv().get(j).clone());
			}
		}
		return temp; 
	}
	
	private Environment getEnv(String key){
		return env.get(key);
	}

	public void setCondition(IBasicBlock basicBlock) {
		if(basicBlock instanceof IBranchNode){
			setCondition(((IBranchNode)basicBlock).getLabel());
		}
	}
	
	private long getMax(long a, long b){
		return a > b ? a: b;
	}
	
	public String toString(){
		return "in labels: " + inLabels + "\n out label: " + outLabel + "\n rule: " + rule;
	}
}
