package core.common.svd.path.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import core.common.cfg.model.*;
import core.common.cfg.interfaces.*;
import core.common.cfg.javacfg.*;

public class CFGLearning {

	public static MethodDeclaration methodemo;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		prepare();// 读入SVDTest.java文件，抽取位于其中的第一个方法max(int, int),返回此方法的CFG给methodemo
		if(methodemo!=null){// 对获取的方法声明进行分析
			JavaControlFlowGraph cfg = new ControlFlowGraphBuilder().build(methodemo);
			IBasicBlock start= cfg.getStartNode();
			while(!(start instanceof JavaExitNode)){
				System.out.println(((AbstractBasicBlock)start).getData());
				start=((AbstractBasicBlock)start).getOutgoingNodes()[0];
			}
			for(IBasicBlock node:cfg.getNodes()){
				if (node instanceof JavaExitNode) {
					System.out.println("Exit node found: " + node);
				}
			}
			System.out.println("Exit node not found");
		}
	}

	private static void prepare() {
		// TODO Auto-generated method stub
		LinkedList<String> filePaths = new LinkedList<String>();
		CompilationUnit unit = null;
		ASTParser parsert = ASTParser.newParser(AST.JLS3);
		String content;
		try {
			content = read("D:\\eclipse\\junoee\\workspace\\Jtester\\src\\test\\SVDTest.java");
			parsert.setSource(content.toCharArray());
			CompilationUnit resultUnit = (CompilationUnit) parsert.createAST(null);
			// 获取类型并 取得第一个类声明的第一个方法声明
			List types = resultUnit.types();
			TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
			methodemo = typeDec.getMethods()[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取java源文件
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
}
