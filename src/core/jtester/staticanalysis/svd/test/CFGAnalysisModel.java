package core.jtester.staticanalysis.svd.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;
/*import org.eclipse.jdt.core.dom.AST;
//import org.eclipse.jdt.core.dom.ASTParser;
//import org.eclipse.jdt.core.dom.CompilationUnit;
//import org.eclipse.jdt.core.dom.FieldDeclaration;
//import org.eclipse.jdt.core.dom.ImportDeclaration;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
//import org.eclipse.jdt.core.dom.Modifier;
//import org.eclipse.jdt.core.dom.PackageDeclaration;
//import org.eclipse.jdt.core.dom.TypeDeclaration;
//import org.eclipse.jdt.core.dom.VariableDeclarationFragment;*/

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.ControlFlowGraphBuilder;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.model.functionblock.AnalysisBlock;
import core.jtester.staticanalysis.available_exp.AvbExpBlock;

public class CFGAnalysisModel {
	public static void main(String[] args) throws IOException {
//		LinkedList<String> filePaths = new LinkedList<String>();
		CompilationUnit unit = null;
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		String content = read("D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java");
		parser.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parser.createAST(null);
		// 获取类型并 取得第一个定义的类型声明
		List types = resultUnit.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// 取得函数(Method)声明列表
		MethodDeclaration methodDec[] = typeDec.getMethods();
		for (MethodDeclaration method : methodDec) {
			printCFG(method);
		}

	}

	private static String read(String filename) throws IOException {
		File file = new File(filename);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		return new String(b);

	}

	private static void printCFG(MethodDeclaration method) {
		ControlFlowGraphBuilder cfgBuilder = new ControlFlowGraphBuilder();
		JavaControlFlowGraph cfg = cfgBuilder.build(method);
		print(cfg.getStartNode().getOutgoing());
		/*Collection<IBasicBlock> bbRes = cfg.getNodes();
		// 将每一个node放到一个AnalysisBlock里面
		Iterator<IBasicBlock> itr = bbRes.iterator();
		int i = 0;
		while (itr.hasNext()) {
			IBasicBlock bb = itr.next();
			System.out.println(bb);
		}*/
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
		for (MethodDeclaration method : methodDec) {
			printCFG(method);
		}
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
	public static void print(Object o){
		System.out.println(o);
	}
}
