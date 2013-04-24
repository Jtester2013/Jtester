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
		prepare();// ����SVDTest.java�ļ�����ȡλ�����еĵ�һ������max(int, int),���ش˷�����CFG��methodemo
		if(methodemo!=null){// �Ի�ȡ�ķ����������з���
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
			// ��ȡ���Ͳ� ȡ�õ�һ���������ĵ�һ����������
			List types = resultUnit.types();
			TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
			methodemo = typeDec.getMethods()[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡjavaԴ�ļ�
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
}
