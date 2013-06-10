package core.jtester.staticanalysis.svd.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.booleanLiteral_return;
import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.object_return;

import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.ControlFlowGraphBuilder;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.cfg.model.BranchNode;
import core.common.cfg.model.DecisionNode;
import core.common.cfg.model.ExitNode;
import core.common.cfg.model.PlainNode;

public class SymbolExecutor {
	public static HashSet<InfixExpression.Operator> feasible_operators = new HashSet<>();
	static {
		feasible_operators.add(InfixExpression.Operator.LESS);
		feasible_operators.add(InfixExpression.Operator.LESS_EQUALS);
		feasible_operators.add(InfixExpression.Operator.GREATER);
		feasible_operators.add(InfixExpression.Operator.GREATER_EQUALS);
		feasible_operators.add(InfixExpression.Operator.EQUALS);
		feasible_operators.add(InfixExpression.Operator.NOT_EQUALS);
	}
	public static HashMap<InfixExpression.Operator, InfixExpression.Operator> operator_map = new HashMap<>();
	static{
		operator_map.put(InfixExpression.Operator.LESS, InfixExpression.Operator.GREATER_EQUALS);
		operator_map.put(InfixExpression.Operator.GREATER_EQUALS, InfixExpression.Operator.LESS);
		operator_map.put(InfixExpression.Operator.GREATER, InfixExpression.Operator.LESS_EQUALS);
		operator_map.put(InfixExpression.Operator.LESS_EQUALS, InfixExpression.Operator.GREATER);
		operator_map.put(InfixExpression.Operator.LESS_EQUALS, InfixExpression.Operator.GREATER);
		operator_map.put(InfixExpression.Operator.EQUALS, InfixExpression.Operator.NOT_EQUALS);
		operator_map.put(InfixExpression.Operator.NOT_EQUALS, InfixExpression.Operator.EQUALS);

	}
	
	public static InfixExpression.Operator getCorrespondOperator(InfixExpression.Operator operator, boolean positive){
		InfixExpression.Operator resultOperator = operator_map.get(operator);
		if (positive) {
			resultOperator = operator;
		}
		return resultOperator;
	}
	
	ArrayList<Expression> decisionExpressions = new ArrayList<>();
	ProgramEnv env;
	public SymbolExecutor(Path path){
		this.env = path.getEnv();
	}
	
	public LinkedList<Path> execute(MethodDeclaration method){
		LinkedList<Path> executablePaths=new LinkedList<>();
		// 构建method的cfg,并根据cfg得到路径集
		ControlFlowGraphBuilder builder = new ControlFlowGraphBuilder();
		JavaControlFlowGraph cfg = builder.build(method);
		Path[] pathCollection = PathGenerator.abstractPath(cfg);
		
		// 打印pathCollection的所有path的条件分支情况
		for (int i = 0; i < pathCollection.length; i++) {
			Path tempPath = pathCollection[i];
			System.out.println("For path "+i);
			for (Iterator iterator = tempPath.iterator(); iterator
					.hasNext();) {
				Object block = iterator.next();
				if (block instanceof BranchNode) {
					BranchNode branchNode = (BranchNode)block;
					System.out.println("Expression:"+branchNode.getData()+"\t Label:"+branchNode.getLabel());
				}
				
			}
		}
		// 对每条路径进行符号执行
		Path tempPath;
		for (int i = 0; i < pathCollection.length; i++) {
			tempPath = pathCollection[i];
			// TODO 对tempPath进行符号执行，将可以执行下去的path添加到executablePaths中
			if (execute(tempPath)) {
				executablePaths.add(tempPath);
			}
		}
		return executablePaths;
	}
	
	/**
	 * 对path进行符号执行
	 * @param path 被符号执行的路径
	 * @return 此path是否可被执行到结束，可以返回true，不可以返回false
	 */
	public boolean execute(Path path){
		// 用path的env初始化visitor
		ConstraintSolver cpExecutor = new ConstraintSolver();
		SymbolExeVisitor visitor = new SymbolExeVisitor(path.getEnv());
		IBasicBlock currentNode;
		for (Iterator iterator = path.getPathNodes().iterator(); iterator.hasNext();) {
			currentNode = (IBasicBlock)iterator.next();
			// 设置节点的可达性
			if (currentNode instanceof AbstractBasicBlock && !((AbstractBasicBlock)currentNode).isReachable()) {
				((AbstractBasicBlock) currentNode).setReachable(true);
			}
			if (currentNode instanceof BranchNode) {
				// 对DecisionNode进行约束求解，当不可解时break
				BranchNode decisionNode = (BranchNode)currentNode;
				String label= decisionNode.getLabel();
				Expression deciExpression = (Expression)decisionNode.getData();
				if (deciExpression instanceof InfixExpression) {
					InfixExpression infixExpression = (InfixExpression)deciExpression;
					if (feasible_operators.contains(infixExpression.getOperator())) {
//						System.out.println("the operator of InfixExpression.Operator "+infixExpression.getOperator()+" is contained");
						// TODO:将infixexpression的两端转化为ExpressionNode，可能需要传入path的环境。然后根据positive和expressionnode构造约束
						ExpressionNode leftNode = expression2ExpressionNode(infixExpression.getLeftOperand());
						ExpressionNode rightNode = expression2ExpressionNode(infixExpression.getRightOperand());
						if (leftNode.getType() == ExpressionType.single_variable || leftNode.getType() == ExpressionType.expression) {
							cpExecutor.updateVariable(leftNode);
						}
						if (rightNode.getType() == ExpressionType.single_variable || rightNode.getType() == ExpressionType.expression) {
							cpExecutor.updateVariable(rightNode);
						}
						boolean positive = true;
						BranchNode currentBranchNode = (BranchNode) currentNode;
						String branchLabel = currentBranchNode.getLabel();
						if (branchLabel.equals(BranchNode.IF_ELSE) || branchLabel.equals(BranchNode.WHILE_ELSE)) {
							positive = false;
						}
						InfixExpression.Operator operator = getCorrespondOperator(infixExpression.getOperator(), positive);
						
						Constraint constraint = cpExecutor.generateConstraint(leftNode, rightNode, operator);
						cpExecutor.model.addConstraint(constraint);
						// TODO 此处对约束求解模块的model及constraint进行验证
						boolean solvable = cpExecutor.solve();
						System.out.println("is solvable: "+solvable);
						if(!solvable){ // 如果不可解，则此路径不可解
							return false;
						}
					}else {
						System.err.println("the operator of InfixExpression.Operator "+infixExpression.getOperator()+" is not supported");
					}
				}
			} else if (currentNode instanceof PlainNode && !(currentNode instanceof DecisionNode)) {
				// 对普通节点进行符号执行
//				System.out.println("Current node is: "+currentNode);
				ASTNode dataNode = (ASTNode)(((PlainNode)currentNode).getData());
				dataNode.accept(visitor);
			} else if (currentNode instanceof ExitNode) {
				// 当运行至ExitNode，返回true
				return true;
			}
		}
		return false;
	}
	
	public ExpressionNode expression2ExpressionNode(Expression expression){
		ExpressionNode resultNode = null;
		if (expression instanceof InfixExpression) {
			resultNode = InfixExpression2ExpressionNode((InfixExpression)expression);
		}else if (expression instanceof SimpleName) {
			resultNode = simpleName2ExpressionNode((SimpleName)expression);
		}else if (expression instanceof NumberLiteral) {
			resultNode = numberLiteral2ExpressionNode((NumberLiteral)expression);
		}
		return resultNode;
	}
	
	public ExpressionNode numberLiteral2ExpressionNode(NumberLiteral numberLiteral){
		ExpressionNode resultNode = null;
		String value = numberLiteral.getToken();
		resultNode = new ExpressionNode(ExpressionType.single_int, value);
		return resultNode;
	}
	public ExpressionNode simpleName2ExpressionNode(SimpleName simpleName){
		ExpressionNode resultNode = null;
		String nameString = ((SimpleName)simpleName).getIdentifier();
		resultNode = env.getMap().get(nameString);
		return resultNode;
	}
	
	public ExpressionNode InfixExpression2ExpressionNode(InfixExpression exp){
		
		Expression leftPart = exp.getLeftOperand(),
				rightPart = exp.getRightOperand();
		org.eclipse.jdt.core.dom.InfixExpression.Operator operator = exp.getOperator();
		ExpressionNode resultNode =  new ExpressionNode(ExpressionType.not_defined, ""),
				leftNode = new ExpressionNode(ExpressionType.not_defined, ""),
				rightNode = new ExpressionNode(ExpressionType.not_defined, "");
		
		// 构造左操作数
		if (leftPart instanceof NumberLiteral) {
			leftNode = new ExpressionNode(ExpressionType.single_int, ((NumberLiteral)leftPart).getToken());
		} else if (leftPart instanceof SimpleName) {
			String sourceVarNameLeft = ((SimpleName)leftPart).getIdentifier();
			leftNode = env.getMap().get(sourceVarNameLeft).clone();
		} else if (leftPart instanceof InfixExpression) {
			leftNode = InfixExpression2ExpressionNode((InfixExpression)leftPart);
		}
		// 构造右操作数
		if (rightPart instanceof NumberLiteral) {
			rightNode = new ExpressionNode(ExpressionType.single_int, ((NumberLiteral)rightPart).getToken());
		} else if (rightPart instanceof SimpleName) {
			String sourceVarNameRight = ((SimpleName)rightPart).getIdentifier();
			rightNode = env.getMap().get(sourceVarNameRight).clone();
		} else if (rightPart instanceof InfixExpression) {
			rightNode = InfixExpression2ExpressionNode((InfixExpression)rightPart);
		}
		
		if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.TIMES)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.multi, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.PLUS)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.plus, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.MINUS)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.minus, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.DIVIDE)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.div, leftNode, rightNode);
		}else {
			System.out.println("not supported operator : "+ operator);
			return null;
		}
		return resultNode;
	}

	public static void main(String[] args){
		
	}
}
