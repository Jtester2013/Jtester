package core.common.path;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.WhileStatement;

import core.common.cfg.javacfg.*;

public class PathGenerator {

	CompilationUnit unit;
	CompilationUnit negaUnit;
	public static HashMap<InfixExpression.Operator, InfixExpression.Operator> infixOperator = new HashMap<>();
	static {
		// �洢�෴�߼��������hashmap������������Ҫ��չ���˴���ʱ����ô��
		infixOperator.put(InfixExpression.Operator.LESS, InfixExpression.Operator.GREATER_EQUALS);
		infixOperator.put(InfixExpression.Operator.GREATER_EQUALS, InfixExpression.Operator.LESS);
		infixOperator.put(InfixExpression.Operator.GREATER, InfixExpression.Operator.LESS_EQUALS);
		infixOperator.put(InfixExpression.Operator.LESS_EQUALS, InfixExpression.Operator.GREATER);
		infixOperator.put(InfixExpression.Operator.EQUALS, InfixExpression.Operator.NOT_EQUALS);
		infixOperator.put(InfixExpression.Operator.NOT_EQUALS, InfixExpression.Operator.EQUALS);
	}

	public static void adf(MethodDeclaration medc) {
		JavaControlFlowGraph cfg = new ControlFlowGraphBuilder().build(medc);
		print(cfg);
		// print(cfg.getStartNode());
		print(cfg.getNodes());
	}

	public void inverse(MethodDeclaration methDec) {
		Block bodyBlock = methDec.getBody();
		inverse(bodyBlock);
	}

	private void inverse(Block body) {
		for (Object statement : body.statements()) {
			inverse((Statement) statement);
			// inverse(body);
		}
	}

	private void inverse(Statement statement) {
		InfixExpression expression = null;
		Operator operator = null;
		if (statement instanceof IfStatement) {
			if (((IfStatement) statement).getExpression() instanceof InfixExpression) {
				expression = (InfixExpression) (((IfStatement) statement).getExpression());
				operator = expression.getOperator();
				expression.setOperator(infixOperator.get(operator));
				// inverse(((IfStatement)statement).getThenStatement())
			}

		} else if (statement instanceof WhileStatement) {
			if (((WhileStatement) statement).getExpression() instanceof InfixExpression) {
				expression = (InfixExpression) (((WhileStatement) statement).getExpression());
				operator = expression.getOperator();
				expression.setOperator(infixOperator.get(operator));
				// ����whileѭ����
				inverse(((WhileStatement) statement).getBody());
			}
		} else if (statement instanceof ForStatement) {
			if (((IfStatement) statement).getExpression() instanceof InfixExpression) {
				expression = (InfixExpression) (((IfStatement) statement).getExpression());
				operator = expression.getOperator();
				expression.setOperator(infixOperator.get(operator));
				// inverse(((IfStatement)statement).getThenStatement())
			}
		} else {

		}
	}

	public static LinkedList<ExecutionPath> getPaths(MethodDeclaration[] methDecs) {
		LinkedList<ExecutionPath> pathCollection = new LinkedList<>();
		/*
		 * �˴���Ҫ��Լ����ض�javaԪ�ؽ��з����ʹ�����Ϊ���ν������Բ���Ҫ��visitorģʽ if else ģ�� while ģ�� do
		 * while ģ�� switch ģ�� ע���˴�����visitor�������ڷ���ִ�е�ʱ�����Ҫ�ˣ�����Բ�ͬjavaԪ�ضԱ������з���������
		 * ���⻹��Ҫ���ض������ν��д�����ifǶ�ף�ѭ��Ƕ�ף��Լ�if��ѭ�������໥Ƕ�׵�
		 */
		Block methBlock = methDecs[0].getBody();
		Block methBlock1 = methDecs[1].getBody();
		// ��methBlock1��������������÷�

		/*
		 * for (Statement statement : (List<Statement>)
		 * (methDec.getBody().statements())) { LinkedList<Statement> pathSource
		 * = new LinkedList<>(); pathSource.clone(); if (statement instanceof
		 * IfStatement) { PathPrintVisitor printVisitor = new
		 * PathPrintVisitor(); // statement.accept(printVisitor);//��ӡstatements
		 * in a path print(statement); print(((IfStatement)
		 * statement).getExpression()); print(((IfStatement)
		 * statement).getThenStatement()); // print(((IfStatement)
		 * statement).getElseStatement()); ((IfStatement) ((IfStatement)
		 * statement).getElseStatement()).getElseStatement();
		 * 
		 * }
		 * 
		 * if (statement instanceof WhileStatement) { // print(((WhileStatement)
		 * statement).getExpression()); print("......."); // print(((Block)
		 * ((WhileStatement) // statement).getBody()).statements().get(1)); }
		 * 
		 * } List<Statement> statements = methDec.getBody().statements();
		 */
		return pathCollection;
	}

	/**
	 * ���뷽���壬����˷���������ִ��·����
	 * 
	 * @param methDec
	 *            ����ķ�����
	 * @return
	 */
	public static LinkedList<ExecutionPath> getPaths(MethodDeclaration methDec) {
		LinkedList<ExecutionPath> pathCollection = new LinkedList<>();
		/*
		 * �˴���Ҫ��Լ����ض�javaԪ�ؽ��з����ʹ�����Ϊ���ν������Բ���Ҫ��visitorģʽ if else ģ�� while ģ�� do
		 * while ģ�� switch ģ�� ע���˴�����visitor�������ڷ���ִ�е�ʱ�����Ҫ�ˣ�����Բ�ͬjavaԪ�ضԱ������з���������
		 * ���⻹��Ҫ���ض������ν��д�����ifǶ�ף�ѭ��Ƕ�ף��Լ�if��ѭ�������໥Ƕ�׵�
		 */
		Block methBlock = methDec.getBody();
		for (Statement statement : (List<Statement>) (methDec.getBody().statements())) {
			LinkedList<Statement> pathSource = new LinkedList<>();
			pathSource.clone();
			if (statement instanceof IfStatement) {
				PathPrintVisitor printVisitor = new PathPrintVisitor();
				// statement.accept(printVisitor);//��ӡstatements in a path
				print(statement);
				print(((IfStatement) statement).getExpression());
				print(((IfStatement) statement).getThenStatement());
				// print(((IfStatement) statement).getElseStatement());
				((IfStatement) ((IfStatement) statement).getElseStatement()).getElseStatement();

			}

			if (statement instanceof WhileStatement) {
				// print(((WhileStatement) statement).getExpression());
				print(".......");
				// print(((Block) ((WhileStatement)
				// statement).getBody()).statements().get(1));
			}

		}
		List<Statement> statements = methDec.getBody().statements();
		return pathCollection;
	}

	/**
	 * ����while��if�������Ƚṹ��block
	 * 
	 * @param block
	 */
	private static void analyse(ASTNode node, LinkedList<ExecutionPath> pathCollection, ExecutionPath path) {
		if (node instanceof MethodDeclaration) {
			// ��ȡ�����ӵ�path�Ļ����У�PathEnvironment��
			Block block = ((MethodDeclaration) node).getBody();
			List<Statement> statsList = block.statements();
			for (Statement statement : statsList) {
				analyse(statement, pathCollection, path);
			}
		} else if (node instanceof IfStatement) {
			Expression ifExpression = ((IfStatement) node).getExpression();
			if (ifExpression instanceof InfixExpression) {
				// InfixExpression negationExpression =
				// getNegationExpr(ifExpression);
			}

			ExecutionPath negationPath = path.clone();
//			path.add(((IfStatement) node).getExpression());// ����Ҫ����������ͼ�ڵ��AST�ڵ��ת��

		} else if (node instanceof WhileStatement) {

		} else {// ������䣬������ʱû��ʵ����δ�������
//			path.getPathAstNodes().add(node);// ����Ҫ����������ͼ�ڵ��AST�ڵ��ת��
		}

		// ����������������˳��㣺return ���� ���һ�䣬�����pathCollection
	}

	/**
	 * �ж�����Ƿ������һ��
	 * 
	 * @param statement
	 */
	private static void ifLastStatement(Statement statement) {

	}

	/**
	 * ���ش����жϱ��ʽ�ķ���ʽ��
	 * 
	 * @param expr
	 * @return
	 */
	private static Expression getNegationExpr(Expression expr) {
		// �洢�෴�߼��������hashmap������������Ҫ��չ���˴���ʱ����ô��
		HashMap<InfixExpression.Operator, InfixExpression.Operator> infixOperator = new HashMap<>();
		infixOperator.put(InfixExpression.Operator.LESS, InfixExpression.Operator.GREATER_EQUALS);
		infixOperator.put(InfixExpression.Operator.GREATER_EQUALS, InfixExpression.Operator.LESS);
		infixOperator.put(InfixExpression.Operator.GREATER, InfixExpression.Operator.LESS_EQUALS);
		infixOperator.put(InfixExpression.Operator.LESS_EQUALS, InfixExpression.Operator.GREATER);
		infixOperator.put(InfixExpression.Operator.EQUALS, InfixExpression.Operator.NOT_EQUALS);
		infixOperator.put(InfixExpression.Operator.NOT_EQUALS, InfixExpression.Operator.EQUALS);
		InfixExpression.Operator opt = ((InfixExpression) expr).getOperator();
		InfixExpression.Operator negationOpt = infixOperator.get(opt);
		// InfixExpression newInfixExpression = new
		// InfixExpression(expr.getAST());
		// newInfixExpression.setOperator(negationOpt);

		return null;
	}

	/**
	 * ��ȡ�����Ĳ����б�
	 * 
	 * @param methodDeclaration
	 * @return
	 */
	public static List<String> extraParametersInMethodDec(MethodDeclaration methodDeclaration) {
		List<Object> varsList = methodDeclaration.parameters();
		int length = varsList.size();
		List<String> varsName = new ArrayList<>(length);
		for (int i = 0; i < varsList.size(); i++) {
			SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) varsList.get(i);
			String nameString = variableDeclaration.getName().toString();
			varsName.add(nameString);
		}
		return varsName;
	}

	/**
	 * ��ȡjavaԴ�ļ�
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static String read(String filePath) throws IOException {
		File file = new File(filePath);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		return new String(b);

	}

	// "D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java"
	private static CompilationUnit getCompilationUnit(String path) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		// ��ȡjavaԴ�ļ�
		String content = null;
		try {
			content = read(path);
			parser.setSource(content.toCharArray());
		} catch (IOException e) {
			print("can't find the java source file at : " + path);
			e.printStackTrace();
		}
		parser.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parser.createAST(null);
		return resultUnit;
		// // ��ȡ���Ͳ� ȡ�õ�һ���������������
		// List types = resultUnit.types();
		// TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// // ȡ�ú���(Method)�����б�
		// MethodDeclaration methodDec[] = typeDec.getMethods();
		// for (MethodDeclaration method : methodDec) {
		// List<String> parametersList = extraParametersInMethodDec(method);//
		// ��ȡ�����Ĳ����б�
		// }
	}

	/**
	 * �򻯵Ĵ�ӡ����
	 * 
	 * @param o
	 */
	private static void print(Object o) {
		System.out.println(o);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java";
		CompilationUnit unit = getCompilationUnit(path);
		MethodDeclaration[] methods = ((TypeDeclaration) (unit.types().get(0))).getMethods();
		// print(methods[0]);
		System.out.println(methods[0]);
		new PathGenerator().inverse(methods[0]);
		System.out.println(methods[0]);
	}

}
