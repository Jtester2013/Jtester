package core.common.path;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.WhileStatement;

public class PathSelectVisitor extends ASTVisitor {
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

	public static void main(String[] args) throws IOException {
		LinkedList<String> filePaths = new LinkedList<String>();
		CompilationUnit unit = null;
		ASTParser parsert = ASTParser.newParser(AST.JLS3);
		// ��ȡjavaԴ�ļ�
		String content = read("D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java");
		// �趨��������Դ�����ַ�
		// ʹ�ý��������н���������AST�����Ľ��(CompilationUnitΪ���ڵ�)
		parsert.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parsert.createAST(null);
		// ��ȡ���Ͳ� ȡ�õ�һ���������������
		List types = resultUnit.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// ȡ�ú���(Method)�����б�
		MethodDeclaration methodDec[] = typeDec.getMethods();
		for (MethodDeclaration method : methodDec) {
			List<String> parametersList = extraParametersInMethodDec(method);// ��ȡ�����Ĳ����б�
			selectPath(method);
		}
	}

	/**
	 * ѡȡһ��������ĳ���·����
	 * 
	 * @param methodDeclaration
	 * @return ����·����
	 */
	public static LinkedList<ExecutionPath> selectPath(MethodDeclaration methodDeclaration) {
		// �洢��method�ҵ�������·��
		LinkedList<ExecutionPath> pathCollection = null;
		// System.out.println(methodDeclaration);// ���method������֤
		// ����method���Ȳ��ܺ������ã���Ҫ�����Ĳ��־��ǳ�����䡢if������䡢while������䡢�ȡ�
		// Ȼ����Ծ����������Ӧ��Visitorģʽ���д���
		// print(methodDeclaration.parameters().get(0));//��ӡ�����ĵ�һ������
		for (Statement statement : (List<Statement>) (methodDeclaration.getBody().statements())) {
			LinkedList<Statement> pathSource = new LinkedList<>();
			pathSource.clone();
			if (statement instanceof IfStatement) {
				PathPrintVisitor printVisitor = new PathPrintVisitor();
				// print("hahaha");
				// statement.accept(printVisitor);
				// print("hahaha");
				print(statement);
//				print(((IfStatement) statement).getExpression() instanceof InfixExpression);// �жϸñ��ʽ�ǲ������ñ��ʽ
//				print(((InfixExpression) ((IfStatement) statement).getExpression()).getOperator().equals(InfixExpression.Operator.GREATER));
				print(((IfStatement)statement).getExpression());
				InfixExpression.Operator opt = ((InfixExpression) ((IfStatement) statement).getExpression()).getOperator();
//				print(opt);
				((InfixExpression)(((IfStatement)statement).getExpression())).setOperator(infixOperator.get(opt));
				print(((IfStatement)statement).getExpression());
//				InfixExpression newinfix = ((InfixExpression) ((IfStatement) statement).getExpression());
//				newinfix.setOperator(infixOperator.get(newinfix.getOperator()));
//				print(newinfix.getOperator());
//				print(((InfixExpression) ((IfStatement) statement).getExpression()).getOperator());
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

		// print(methodDeclaration.getBody());//��ӡ������
		// print(methodDeclaration.getBody().statements());
		return pathCollection;
	}

	public static void acceccIfStatement(ASTNode node) {

	}

	public static void acceccWhileStatement(ASTNode node) {

	}

	public static void acceccOtherStatement(ASTNode node) {

	}

	/**
	 * ���ش����жϱ��ʽ�ķ���ʽ��
	 * 
	 * @param expr
	 * @return
	 */
	private static Expression getNegationExpr(Expression expr) {

		InfixExpression.Operator opt = ((InfixExpression) expr).getOperator();
		InfixExpression.Operator negationOpt = infixOperator.get(opt);
//		InfixExpression newInfixExpression = new InfixExpression(expr.getAST());
//		newInfixExpression.setOperator(negationOpt);

		return null;
	}

	/**
	 * ��ȡһ�������Ĳ����б�ı�����
	 * 
	 * @param methodDeclaration
	 * @return �������в�����������List<String>
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

	private static String read(String filePath) throws IOException {
		File file = new File(filePath);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		return new String(b);

	}

	private static void printSkeletonOfJavaFile(String javaFilePath) throws IOException {
		CompilationUnit unit = null;
		ASTParser parsert = ASTParser.newParser(AST.JLS3);
		String content = read(javaFilePath);
		// �趨��������Դ�����ַ�
		parsert.setSource(content.toCharArray());
		// ʹ�ý��������н���������AST�����Ľ��(CompilationUnitΪ���ڵ�)
		CompilationUnit result = (CompilationUnit) parsert.createAST(null);
		// ��ȡ����
		List types = result.types();
		// ȡ����������
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// ##############��ȡԴ����ṹ��Ϣ#################
		// ����import
		List importList = result.imports();
		// ȡ�ð���
		PackageDeclaration packetDec = result.getPackage();
		// ȡ������
		String className = typeDec.getName().toString();
		// ȡ�ú���(Method)�����б�
		MethodDeclaration methodDec[] = typeDec.getMethods();
		// ȡ�ú���(Field)�����б�
		FieldDeclaration fieldDec[] = typeDec.getFields();
		// for (MethodDeclaration method : methodDec) {
		// printCFG(method);
		// }
		System.out.println("��:");
		System.out.println(packetDec.getName());
		System.out.println("����import:");
		for (Object obj : importList) {
			ImportDeclaration importDec = (ImportDeclaration) obj;
			System.out.println(importDec.getName());
		}
		// �������
		System.out.println("��:");
		System.out.println(className);
		// ѭ�������������
		System.out.println("========================");
		System.out.println("����:");
		for (MethodDeclaration method : methodDec) {
			System.out.println(method.getName());
			System.out.println("body:");
			System.out.println(method.getBody());
			System.out.println("Javadoc:" + method.getJavadoc());
			System.out.println("Body:" + method.getBody());
			System.out.println("ReturnType:" + method.getReturnType());
			System.out.println("=============");
			System.out.println(method);
		}

		// ѭ���������
		System.out.println("����:");
		for (FieldDeclaration fieldDecEle : fieldDec) {
			// public static
			for (Object modifiObj : fieldDecEle.modifiers()) {
				Modifier modify = (Modifier) modifiObj;
				System.out.print(modify + "-");
			}
			System.out.println(fieldDecEle.getType());
			for (Object obj : fieldDecEle.fragments()) {
				VariableDeclarationFragment frag = (VariableDeclarationFragment) obj;
				System.out.println("[FIELD_NAME:]" + frag.getName());
			}
		}
	}

	private static void print(Object o) {
		System.out.println(o);
	}

}
