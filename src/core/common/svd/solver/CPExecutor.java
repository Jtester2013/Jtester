package core.common.svd.solver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SimpleName;

//import static choco.Choco;
import choco.Choco;
import choco.cp.model.CPModel;
import choco.cp.solver.CPSolver;
import choco.cp.solver.search.real.CyclicRealVarSelector;
import choco.cp.solver.search.real.RealIncreasingDomain;
import choco.cp.solver.search.set.MinDomSet;
import choco.cp.solver.search.set.MinEnv;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import choco.kernel.model.variables.real.RealExpressionVariable;
import choco.kernel.model.variables.real.RealVariable;
import choco.kernel.model.variables.set.SetVariable;
import choco.kernel.solver.Configuration;

public class CPExecutor {
	// int��float��double�������ݵ�ֵ��
	public static final int intupperlimit = Integer.MAX_VALUE;
	public static final int intlowerlimit = Integer.MIN_VALUE;

	public static final float floatupperlimit = Float.MAX_VALUE;
	public static final float floatlowerlimit = Float.MIN_VALUE;

	public static final double doubleupperlimit = Double.MAX_VALUE;
	public static final double doublelowerlimit = Double.MIN_VALUE;
	
	public static HashMap envVariableValueHashMap=new HashMap<String, IntegerVariable>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExpressionNode root = null;
		root = prepare();
		// �������rootΪ���������ڱ�����ͬʱ����envVariableValueHashMap����
		preVisitExpressionTree(root);
		// ����ExpressionNodeΪ���ı��ʽ������Լ����ģ��Constraint Modeling��,������Լ��
		println("");
		println(expressionModeling(root));
		Constraint constraint = Choco.lt(5, expressionModeling(root));
		// �������
		CPModel model = new CPModel();
		model.addConstraint(constraint);
		CPSolver solver = new CPSolver();
		solver.read(model);
		boolean solverable = solver.solve();
		println(solverable);
	}
	
	
	public static boolean solve(Expression e){
		if (e instanceof InfixExpression) {
			// ����Expression��ߵı��ʽ��������Ӧ��ExpressionNode
			
			// ����Expression��ߵı��ʽ��������Ӧ��ExpressionNode
			
			// �õ�Expression���߼��жϷ��ţ������������߽������
			
			// ���ؿɷ����
			
		}else {
			System.out.println("Expression: " + e +" is not applicable.");
		}
		
		return false;
	}
	
	/**
	 * ����envVariableValueHashMap���key
	 */
	private static void transEnv(){
		Set variableSet=envVariableValueHashMap.keySet();
		for (Iterator iterator = variableSet.iterator(); iterator.hasNext();) {
			println(iterator.next());
		}
	}
	
	/**
	 * ��һ��Expression��������ʽ����������
	 * @param root the root of a expression tree which provided by symbolic execution environment
	 * @return
	 */
	public static IntegerExpressionVariable expressionModeling(ExpressionNode root){
		IntegerExpressionVariable result = null;
		ExpressionType type=root.getType();
		if (type==ExpressionType.expression) {
			ExpressionOperator operator = root.getOperator();
			ExpressionNode leftPart = root.getLeft();
			ExpressionNode rightPart = root.getRight();
			ExpressionType leftType = leftPart.getType();
			ExpressionType rightType = rightPart.getType();
			/*
			 * ���������ǽ����ʽ�ݹ�Ĺ�����IntegerExpressionVariable�����ṩ������Constraint��
			 * ��Ҫ˼·��������������ֲ�ͬ�����ͽ��б��ʽ�����Ĺ�������int��variable��expression���и������
			 * ��Ҫע������������û��(int, int)��������Ϊ��������ӽڵ㶼��int������ֵ�Ļ�����ô������ʽ���ǿ���
			 * ����������ģ��佫�ᱻ���������档��һ���Ĺ��������ڷ���ִ�й�������ɣ�Ҳ����ͨ��һ�������Ա��ʽ������
			 * ������������
			 */
			switch (operator) {
			case minus: {
				// int and variable
				if (leftType == ExpressionType.single_int && rightType == ExpressionType.single_variable) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					print((IntegerVariable) envVariableValueHashMap.get(rightPart.getValue()));
					result = Choco.minus(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.single_variable) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					print((IntegerVariable) envVariableValueHashMap.get(leftPart.getValue()));
					result = Choco.minus(leftValue, rightValue);
				}
				// int and expression
				else if (leftType == ExpressionType.single_int && rightType == ExpressionType.expression) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.minus(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.expression) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.minus(leftValue, rightValue);
				}
				// expression and variable
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.single_variable) {
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.minus(leftValue, rightValue);
				}else if (rightType == ExpressionType.expression && leftType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.minus(leftValue, rightValue);
				}
				// both are expression
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.expression) {
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.minus(leftValue, rightValue);
				}
				// both are variable
				else if (leftType == ExpressionType.single_variable && rightType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.minus(leftValue, rightValue);
				}
				break;
			}
			case plus: {
				// int and variable
				if (leftType == ExpressionType.single_int && rightType == ExpressionType.single_variable) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.plus(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.single_variable) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					result = Choco.plus(leftValue, rightValue);
				}
				// int and expression
				else if (leftType == ExpressionType.single_int && rightType == ExpressionType.expression) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.plus(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.expression) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.plus(leftValue, rightValue);
				}
				// expression and variable
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.single_variable) {
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.plus(leftValue, rightValue);
				}else if (rightType == ExpressionType.expression && leftType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.plus(leftValue, rightValue);
				}
				// both are expression
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.expression) {
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.plus(leftValue, rightValue);
				}
				// both are variable
				else if (leftType == ExpressionType.single_variable && rightType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.plus(leftValue, rightValue);
				}
				break;
			}
			case multi: {
				// int and variable
				if (leftType == ExpressionType.single_int && rightType == ExpressionType.single_variable) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.mult(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.single_variable) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					result = Choco.mult(leftValue, rightValue);
				}
				// int and expression
				else if (leftType == ExpressionType.single_int && rightType == ExpressionType.expression) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.mult(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.expression) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.mult(leftValue, rightValue);
				}
				// expression and variable
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.single_variable) {
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.mult(leftValue, rightValue);
				}else if (rightType == ExpressionType.expression && leftType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.mult(leftValue, rightValue);
				}
				// both are expression
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.expression) {
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.mult(leftValue, rightValue);
				}
				// both are variable
				else if (leftType == ExpressionType.single_variable && rightType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.mult(leftValue, rightValue);
				}
				break;
			}
			case div: {
				// int and variable
				if (leftType == ExpressionType.single_int && rightType == ExpressionType.single_variable) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.div(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.single_variable) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					result = Choco.div(leftValue, rightValue);
				}
				// int and expression
				else if (leftType == ExpressionType.single_int && rightType == ExpressionType.expression) {
					int leftValue = Integer.parseInt(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.div(leftValue, rightValue);
				}else if (rightType == ExpressionType.single_int && leftType == ExpressionType.expression) {
					int rightValue = Integer.parseInt(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.div(leftValue, rightValue);
				}
				// expression and variable
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.single_variable) {
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					result = Choco.div(leftValue, rightValue);
				}else if (rightType == ExpressionType.expression && leftType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.div(leftValue, rightValue);
				}
				// both are expression
				else if (leftType == ExpressionType.expression && rightType == ExpressionType.expression) {
					IntegerExpressionVariable leftValue = expressionModeling(leftPart);
					IntegerExpressionVariable rightValue = expressionModeling(rightPart);
					result = Choco.div(leftValue, rightValue);
				}
				// both are variable
				else if (leftType == ExpressionType.single_variable && rightType == ExpressionType.single_variable) {
					IntegerVariable leftValue = (IntegerVariable) envVariableValueHashMap.get(leftPart.getValue());
					IntegerVariable rightValue = (IntegerVariable) envVariableValueHashMap.get(rightPart.getValue());
					result = Choco.div(leftValue, rightValue);
				}
				break;
			}
			}

		}
		/*
		 * ��ע�͵������Ӧ�����ò��ŵ�
		 */
		return result;
	}
	
	/**
	 * ΪԼ�����ʽ����Լ��
	 * @param exp
	 * @return
	 */
	public static Constraint constraintModelingaf(InfixExpression exp){
		Constraint constraint = null;
		exp.getOperator();
		exp.getLeftOperand();
		exp.getRightOperand();
		return constraint;
	}
	
	public static ExpressionNode getExpressionNode(Expression exp) {
		ExpressionNode result = null;
		ExpressionNode leftPart = null;
		ExpressionNode rightPart = null;
		ExpressionType type = null;
		ExpressionOperator operator = null;
		// InfixExpression.Operator operator;

		if (exp instanceof InfixExpression) {
			((InfixExpression) exp).getOperator();
		} else if (exp instanceof SimpleName) {

		} else if (exp instanceof NumberLiteral) {

		}
		return result;
	}
	
	// ׼����������Expression
	public static ExpressionNode prepare(){
		ExpressionNode leaf1= new ExpressionNode(ExpressionType.single_int, "58",ExpressionOperator.plus, null, null);
		ExpressionNode leaf2= new ExpressionNode(ExpressionType.single_variable, "a",ExpressionOperator.minus,null, null);
		ExpressionNode branch1= new ExpressionNode(ExpressionType.expression, null,ExpressionOperator.plus,leaf1, leaf2);
		ExpressionNode leaf3= new ExpressionNode(ExpressionType.single_int, "29",ExpressionOperator.minus, null, null);
		return new ExpressionNode(ExpressionType.expression, null,ExpressionOperator.minus,branch1, leaf3);
	}
	
	
	// �������ExpressionNode����ı��ʽ��
	public static void preVisitExpressionTree(ExpressionNode root){
		if (root!=null) {
			if(root.operator!=null){
				print("(");
				preVisitExpressionTree(root.getLeft());
				print(root.getOperator());
				preVisitExpressionTree(root.getRight());
				print(")");
			}else {
				print(root.getValue());
				if(root.getType()==ExpressionType.single_variable){
					String variableName = root.getValue();
					if (!envVariableValueHashMap.containsKey(variableName)) {
						envVariableValueHashMap.put(variableName, new IntegerVariable(variableName, intlowerlimit, intupperlimit));
					}
				}
			}
		}
	}

	private static void println(Object o) {
		System.out.println(o);
	}

	private static void print(Object o) {
		System.out.print(o);
	}

	// a simple demo of solve exhibition
	private static void simpleDemo() {
		IntegerVariable v = Choco.makeIntVar("v", new Integer(1), new Integer(10));
		Constraint c = Choco.eq(v, new Integer(1));
		CPModel model = new CPModel();
		model.addConstraint(c);
		CPSolver solver = new CPSolver();
		solver.read(model);
		boolean solverable = solver.solve();
		println(solverable);
	}

	// a demo to exhibit the process of Choco solving
	private static void chocoStreamline() {
		// Constant declaration
		int n = 3; // Order of the magic square
		int magicSum = n * (n * n + 1) / 2; // Magic sum
		// Build the model
		CPModel m = new CPModel();
		// Creation of an array of variables
		IntegerVariable[][] var = new IntegerVariable[n][n];
		// For each variable, we define its name and the boundaries of its
		// domain.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				var[i][j] = Choco.makeIntVar("var_" + i + "_" + j, 1, n * n);
				// Associate the variable to the model.
				m.addVariable(var[i][j]);
			}
		}
		// All cells of the matrix must be different
		for (int i = 0; i < n * n; i++) {
			for (int j = i + 1; j < n * n; j++) {
				Constraint c = (Choco.neq(var[i / n][i % n], var[j / n][j % n]));
				m.addConstraint(c);
			}
		}
	}

	/**
	 * the n-queens problem
	 */
	private static void problem1() {
		int nbQueen = 3;
		// 1- Create the model
		CPModel m = new CPModel();
		// 2- Create the variables
		IntegerVariable[] queens = Choco.makeIntVarArray("Q", nbQueen, 1, nbQueen);// �ĸ��ʺ�������У����������������е����
		// 3- Post constraints
		for (int i = 0; i < nbQueen; i++) {
			for (int j = i + 1; j < nbQueen; j++) {
				int k = j - i;
				m.addConstraint(Choco.neq(queens[i], queens[j]));// queens[i]��ֵ���ǵ�i���ʺ����ڵ��б��
				m.addConstraint(Choco.neq(queens[i], Choco.plus(queens[j], k))); // ���Խ���Լ����Ϊʲô����ȡChoco.neq(k, Choco.minus(queens[j], queens[i]))
//				m.addConstraint(Choco.neq(k, Choco.minus(queens[j], queens[i]))); // ����Choco.neq(k, Choco.minus(queens[j], queens[i]))�����ʧ�ܣ�����������
				m.addConstraint(Choco.neq(queens[i], Choco.minus(queens[j], k))); // ���Խ���Լ��
			}
		}
		// 4- Create the solver
		CPSolver s = new CPSolver();
		s.read(m);
		s.solveAll();
		// 5- Print the number of solutions found
		System.out.println("Number of solutions found:" + s.getSolutionCount());
	}
	
	/**
	 *  Steiner problem
	 */
	private static void problem2() {
		// 1- Create the problem
		CPModel mod = new CPModel();
		int m = 7;
		int n = m * (m - 1) / 6;
		// 2- Create Variables
		SetVariable[] vars = new SetVariable[n]; // A variable for each set
		SetVariable[] intersect = new SetVariable[n * n]; // A variable for each
															// pair of sets
		for (int i = 0; i < n; i++)
			vars[i] = Choco.makeSetVar("set " + i, 1, n);
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				intersect[i * n + j] = Choco.makeSetVar("interSet " + i + " " + j, 1, n);
		// 3- Post constraints
		for (int i = 0; i < n; i++)
			mod.addConstraint(Choco.eqCard(vars[i], 3));
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				// the cardinality of the intersection of each pair is equal to
				// one
				mod.addConstraint(Choco.setInter(vars[i], vars[j], intersect[i * n + j]));
				mod.addConstraint(Choco.leqCard(intersect[i * n + j], 1));
			}
		// 4- Search for a solution
		CPSolver s = new CPSolver();
		s.read(mod);
		s.setVarSetSelector(new MinDomSet(s, s.getVar(vars)));
		s.setValSetSelector(new MinEnv());
		s.solve();
		// 5- Print the solution found
		for (SetVariable var : vars) {
			System.out.println(s.getVar(var).pretty());
		}
	}

	/**
	 * Descripton: Example 3: the CycloHexane problem. The problem consists in
	 * nding the 3D con guration of a cyclohexane molecule. It is described with
	 * a system of three non linear equations: Variables: x; y; z. Domain:[8, 8]
	 * Constraints: 
	 * y2 * (1 + z2) + z * (z - 24 * y) = -13 
	 * x2 * (1 + y2) + y * (y - 24 * x) = -13 
	 * z2 * (1 + x2) + x * (x - 24 * z) = -13
	 */
	private static void problem3() {
		// 1- Create the problem
		CPModel pb = new CPModel();
		// 2- Create the variable
		RealVariable x = Choco.makeRealVar("x", -1.0e8, 1.0e8);
		RealVariable y = Choco.makeRealVar("y", -1.0e8, 1.0e8);
		RealVariable z = Choco.makeRealVar("z", -1.0e8, 1.0e8);
		// 3- Create and post the constraints
		RealExpressionVariable exp1 = Choco.plus(// ��Լ�����ʽ�Ĺ�������ͨ��������XXXExpressionVariable�ĺ���ʵ�ֵ�
				Choco.mult(Choco.power(y, 2), Choco.plus(1, Choco.power(z, 2))),// y2 *(1 + z2)
				Choco.mult(z, Choco.minus(z, Choco.mult(24, y)))// z * (z - 24 * y)
				);
		RealExpressionVariable exp2 = Choco.plus(Choco.mult(Choco.power(z, 2), Choco.plus(1, Choco.power(x, 2))),
				Choco.mult(x, Choco.minus(x, Choco.mult(24, z))));
		RealExpressionVariable exp3 = Choco.plus(Choco.mult(Choco.power(x, 2), Choco.plus(1, Choco.power(y, 2))),
				Choco.mult(y, Choco.minus(y, Choco.mult(24, x))));
		Constraint eq1 = Choco.eq(exp1, -13);
		Constraint eq2 = Choco.eq(exp2, -13);
		Constraint eq3 = Choco.eq(exp3, -13);
		pb.addConstraint(eq1);
		pb.addConstraint(eq2);
		pb.addConstraint(eq3);
		// 4- Search for all solution
		CPSolver s = new CPSolver();
		s.getConfiguration().putDouble(Configuration.REAL_PRECISION, 1e-8);
		s.read(pb);
		s.setVarRealSelector(new CyclicRealVarSelector(s));
		s.setValRealIterator(new RealIncreasingDomain());
		s.solve();
		// 5- print the solution found
		System.out.println("x " + s.getVar(x).getValue());
		System.out.println("y " + s.getVar(y).getValue());
		System.out.println("z " + s.getVar(z).getValue());
	}
}
