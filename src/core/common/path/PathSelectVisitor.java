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
		// 存储相反逻辑运算符的hashmap，将来根据需要扩展，此处暂时就这么多
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
		// 读取java源文件
		String content = read("D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java");
		// 设定解析器的源代码字符
		// 使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)
		parsert.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parsert.createAST(null);
		// 获取类型并 取得第一个定义的类型声明
		List types = resultUnit.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// 取得函数(Method)声明列表
		MethodDeclaration methodDec[] = typeDec.getMethods();
		for (MethodDeclaration method : methodDec) {
			List<String> parametersList = extraParametersInMethodDec(method);// 获取方法的参数列表
			selectPath(method);
		}
	}

	/**
	 * 选取一个方法里的程序路径集
	 * 
	 * @param methodDeclaration
	 * @return 程序路径集
	 */
	public static LinkedList<ExecutionPath> selectPath(MethodDeclaration methodDeclaration) {
		// 存储从method找到的所有路径
		LinkedList<ExecutionPath> pathCollection = null;
		// System.out.println(methodDeclaration);// 输出method进行验证
		// 解析method，先不管函数调用，需要解析的部分就是常用语句、if条件语句、while条件语句、等。
		// 然后针对具体语句类型应用Visitor模式进行处理。
		// print(methodDeclaration.parameters().get(0));//打印函数的第一个参数
		for (Statement statement : (List<Statement>) (methodDeclaration.getBody().statements())) {
			LinkedList<Statement> pathSource = new LinkedList<>();
			pathSource.clone();
			if (statement instanceof IfStatement) {
				PathPrintVisitor printVisitor = new PathPrintVisitor();
				// print("hahaha");
				// statement.accept(printVisitor);
				// print("hahaha");
				print(statement);
//				print(((IfStatement) statement).getExpression() instanceof InfixExpression);// 判断该表达式是不是中置表达式
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

		// print(methodDeclaration.getBody());//打印函数体
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
	 * 返回传入判断表达式的反义式子
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
	 * 获取一个方法的参数列表的变量名
	 * 
	 * @param methodDeclaration
	 * @return 包含所有参数变量名的List<String>
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
		// 设定解析器的源代码字符
		parsert.setSource(content.toCharArray());
		// 使用解析器进行解析并返回AST上下文结果(CompilationUnit为根节点)
		CompilationUnit result = (CompilationUnit) parsert.createAST(null);
		// 获取类型
		List types = result.types();
		// 取得类型声明
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// ##############获取源代码结构信息#################
		// 引用import
		List importList = result.imports();
		// 取得包名
		PackageDeclaration packetDec = result.getPackage();
		// 取得类名
		String className = typeDec.getName().toString();
		// 取得函数(Method)声明列表
		MethodDeclaration methodDec[] = typeDec.getMethods();
		// 取得函数(Field)声明列表
		FieldDeclaration fieldDec[] = typeDec.getFields();
		// for (MethodDeclaration method : methodDec) {
		// printCFG(method);
		// }
		System.out.println("包:");
		System.out.println(packetDec.getName());
		System.out.println("引用import:");
		for (Object obj : importList) {
			ImportDeclaration importDec = (ImportDeclaration) obj;
			System.out.println(importDec.getName());
		}
		// 输出类名
		System.out.println("类:");
		System.out.println(className);
		// 循环输出函数名称
		System.out.println("========================");
		System.out.println("函数:");
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

		// 循环输出变量
		System.out.println("变量:");
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
