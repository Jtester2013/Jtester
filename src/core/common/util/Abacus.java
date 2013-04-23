package core.common.util;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;

/**
 * Abacus is used to compute values from Expression
 * 
 * it can handle compounded Arithmetic problems.
 * 
 * ex. q * q  - 100 * p * q + p - 100
 * 
 * however, it can't handle more advanced computations like square, log... and parentheses, too.
 * 
 * @author XingxuWu
 *
 */
public class Abacus {
	
	/**
	 * compute values for an given expression
	 * 
	 * @param exp
	 * @param fields contains all the (variable, value) pairs.
	 * @return
	 */
	public static int compute(Expression exp, Map<String, Integer> fields){
		int returnValue = 0;
		if(exp instanceof InfixExpression){
			InfixExpression ie = (InfixExpression)exp;
			Expression left = ie.getLeftOperand();
			Expression right = ie.getRightOperand();
			
			int leftValue = 0;
			int rightValue = 0;
			
			if(left instanceof InfixExpression){
				leftValue = compute(left, fields);
			}else{
				leftValue = computeHelper(left, fields);
			}
			
			if(right instanceof InfixExpression){
				rightValue = compute(right, fields);
			}else{
				rightValue = computeHelper(right, fields);
			}
			
			returnValue = computeValue(leftValue, rightValue, ie.getOperator());
			
			List<Name> extendedOprands = ie.extendedOperands();
			for(Name var : extendedOprands){
				int varValue = computeHelper(var, fields);
				returnValue = computeValue(returnValue, varValue, ie.getOperator());
			}
		}else{
			returnValue = computeHelper(exp, fields);
		}
		
		//System.out.println("exp: " + exp + " value: " + returnValue + "\n");
		return returnValue;
	}
	
	private static int computeHelper(Expression exp, Map<String, Integer> fields){
		int result = 0;
		try{
			if(exp instanceof NumberLiteral){
				result = Integer.parseInt(exp.toString());
			}else if(exp instanceof InfixExpression){
				// only deal with binary expression
				InfixExpression infix = (InfixExpression)exp;
				Expression left = infix.getLeftOperand();
				Expression right = infix.getRightOperand();
				
				int leftVal = 0;
				int rightVal = 0;
				
				if(left instanceof NumberLiteral){
					leftVal = Integer.parseInt(left.toString());
				}else{
					leftVal = fields.get(left.toString());
				}
				
				if(right instanceof NumberLiteral){
					rightVal = Integer.parseInt(right.toString());
				}else{
					rightVal = fields.get(right.toString());
				}
				
				Operator op = infix.getOperator();
				result = computeValue(leftVal, rightVal, op);
			} else if( exp == null){
				result = 0;
			} else {
				Object r = fields.get(exp.toString());
				if(r != null){
					result = (Integer)r;
				}
			}
		}catch(Exception e){
			System.err.println("can't compute arithmetic expression: " + exp);
		}
		return result;
	}
	
	private static int computeValue(int leftVal, int rightVal, Operator op){
		int result = 0;
		if(op.equals(InfixExpression.Operator.PLUS)){
			result = leftVal + rightVal;
		}else if(op.equals(InfixExpression.Operator.MINUS)){
			result = leftVal - rightVal;
		}else if(op.equals(InfixExpression.Operator.TIMES)){
			result = leftVal * rightVal;
		}else if(op.equals(InfixExpression.Operator.DIVIDE)){
			if(rightVal != 0){
				result = leftVal / rightVal;
			}else{
				System.err.println("divided by zero!");
			}
		}else{
			System.err.println("undefined operator" + op);
		}
		return result;
	}
	
	/**
	 * compare two integers
	 * 
	 * @param op operator
	 * @param left
	 * @param right
	 * @return
	 */
	public static boolean compare(Operator op, int left, int right){
		boolean result = false;
		if(op.equals(InfixExpression.Operator.GREATER)){
			result = left > right ? true: false;
		}else if(op.equals(InfixExpression.Operator.LESS)){
			result = left < right ? true: false;
		}else if(op.equals(InfixExpression.Operator.EQUALS)){
			result = left == right ? true: false;
		}else if(op.equals(InfixExpression.Operator.NOT_EQUALS)){
			result = left != right ? true: false;
		}else{
			System.err.println("undefined operator" + op);
		}
		return result;
	}
}
