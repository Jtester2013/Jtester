package core.common.svd.path;

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
import org.eclipse.jdt.core.dom.TypeDeclaration;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.ControlFlowGraphBuilder;
import core.common.cfg.javacfg.JavaBranchNode;
import core.common.cfg.javacfg.JavaConnectorNode;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.javacfg.JavaDecisionNode;
import core.common.cfg.javacfg.JavaExitNode;
import core.common.cfg.javacfg.JavaJumpNode;
import core.common.cfg.javacfg.JavaPlainNode;
import core.common.cfg.javacfg.JavaStartNode;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.cfg.model.ControlFlowGraph;

public class PathGenerator {

	public static LinkedList<Path> getPaths(JavaControlFlowGraph cfg) {
		LinkedList<Path> paths = new LinkedList<>();
		// TODO
		return paths;
	}

	public static void main(String[] args) throws IOException{
		String filePath = "D:\\GitHub\\Jtester\\src\\core\\common\\svd\\test\\SVDTest.java";
		JavaControlFlowGraph[] cfgsArray = read(filePath);
		JavaControlFlowGraph cfgInstance;
		for (int i = 0; i < cfgsArray.length; i++) {
			System.out.println("Begin to analyse CFG: "+cfgsArray[i].getSignature());
			// 抽取程序路径
			cfgInstance = cfgsArray[i];
			abstractPath(cfgInstance);
		}
	}

	public static Path[] abstractPath(JavaControlFlowGraph cfg){
		ArrayList<Path> paths = new ArrayList<>();
		LinkedList<IBasicBlock> path = new LinkedList<>();
		IBasicBlock start = cfg.getStartNode();
		path.add(start);
		IBasicBlock currentNode = start;
		IBasicBlock nextBasicBlock;
		// 对给定的cfg进行路径抽取
		while (paths.size()!=0) {
			if (currentNode instanceof JavaPlainNode) {
				path.add(currentNode);
				currentNode = ((JavaPlainNode) currentNode).getOutgoing();
				continue;
			}else if (currentNode instanceof JavaDecisionNode) {
				
			}else if (currentNode instanceof JavaBranchNode) {
				
			}else if (currentNode instanceof JavaJumpNode) {
				
			}else if (currentNode instanceof JavaExitNode) {
				
			}else if (currentNode instanceof JavaConnectorNode) {
				
			}else if (currentNode instanceof JavaStartNode) {
				currentNode = ((JavaStartNode) currentNode).getOutgoing();
				continue;
			}
		}
		
		// 返回路径集
		Path[] pathArray = new Path[paths.size()];
		return paths.toArray(pathArray);
	}

	/**
	 * 读取java源文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static JavaControlFlowGraph[] read(String filePath) throws IOException {
		// 读取源文件
		File file = new File(filePath);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		fis.close();
		String content = new String(b);
		// 解析源文件生成编译单元
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parser.createAST(null);
		// 获取第一个类型类型
		List types = resultUnit.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// 取得第一个类声明的方法声明
		MethodDeclaration[] methods = typeDec.getMethods();
		// 返回各个方法生成的CFG
		LinkedList<JavaControlFlowGraph> cfgs = new LinkedList<>();
		ControlFlowGraphBuilder builder = new ControlFlowGraphBuilder();
		for (int i = 0; i < methods.length; i++) {
			cfgs.add(builder.build(methods[i]));
		}
		JavaControlFlowGraph[] cfgArrayControlFlowGraphs = new JavaControlFlowGraph[methods.length];
		return cfgs.toArray(cfgArrayControlFlowGraphs);
	}

}
