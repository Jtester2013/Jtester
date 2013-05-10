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
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import core.common.cfg.interfaces.*;
import core.common.cfg.javacfg.*;
import core.common.cfg.model.*;

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
			Path[] paths = abstractPath(cfgInstance);
			System.out.println(paths.length);
			// 打印path
			for (int j = 0; j < paths.length; j++) {
				System.out.println("Print path："+ j);
				printPath(paths[j].pathNodes);
			}
		}
	}

	public static Path[] abstractPath(JavaControlFlowGraph cfg){
		ArrayList<Path> paths = new ArrayList<>();
		LinkedList<IBasicBlock> path = new LinkedList<>();
		IBasicBlock start = cfg.getStartNode();
		IBasicBlock currentNode = start;
		// 对给定的cfg进行路径抽取
		System.out.println("Begin path abstraction");
		
//		while (!(currentNode instanceof DecisionNode && (()currentNode) && .!=0) {// 
//		while (currentNode!=null || path.size()!=0) {// 
		while(true){
			if (currentNode instanceof BranchNode) {// must solve BranchNode before PlainNode.
				System.out.println("currentNode is " + "BranchNode");
				BranchNode tempNode = (BranchNode)currentNode;
				if (tempNode.isDetected()) {
					currentNode=findUpperDecision(path);
				}else {
					tempNode.setDetected(true);
					path.add(tempNode);
					currentNode = tempNode.getOutgoing();
				}
				continue;
				
			}else if (currentNode instanceof PlainNode) {
				System.out.println("currentNode is " + "PlainNode");
				PlainNode tempNode = (PlainNode)currentNode;
				path.add(currentNode);
				currentNode = ((PlainNode) currentNode).getOutgoing();
				continue;
				
			}else if (currentNode instanceof DecisionNode) {// 维护detected信息
				DecisionNode tempNode = (DecisionNode) currentNode;
//				System.out.println("currentNode is DecisionNode "+((Expression)tempNode.getData()));// print Decision Expression
				if (tempNode.getType()==DecisionType.switch_type || tempNode.getType()==DecisionType.if_type) {// 对于非循环节点
					if (tempNode.isDetected() && !hasUpperDecisionNode(path)) {
						System.out.println(cfg.getSignature()+" is manipulated");
						break;
					}else if (tempNode.isDetected() && hasUpperDecisionNode(path)) {// 如果这个DecisionNode的分支已经被全部探测完，则回溯到上一个DecisionNode
						tempNode.cleanBranch();//将其各个branchnode 的detected设置为false
						currentNode = findUpperDecision(path);
					}else if(!tempNode.isDetected()){
						path.add(tempNode);
						currentNode = tempNode.getUndetectedBranch();
					}
					continue;
				}else if (tempNode.getType()==DecisionType.while_type) {
					

				} else if (tempNode.getType() == DecisionType.dowhile_type) {

				}else if (tempNode.getType()==DecisionType.for_type) {
					
				}else if (tempNode.getType()==DecisionType.unleagal_type) {
					System.out.println("Unlegal DecisionNode: "+((Expression)tempNode.getData()));
				}

			}else if (currentNode instanceof JumpNode) {// TODO
				System.out.println("currentNode is " + "JumpNode");
				JumpNode tempNode = (JumpNode)currentNode;
				path.add(tempNode);
				/*if (tempNode.isBackwardArc()) {// 如果这是循环语句的跳转边
					
				}*/
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof ExitNode) {
				System.out.println("if there is a DecisionNode in path when meet a ExitNode " + hasUpperDecisionNode(path));
				System.out.println("currentNode is " + "ExitNode");
				ExitNode tempNode = (ExitNode)currentNode;
				path.add(tempNode);
				LinkedList<IBasicBlock> completePath = (LinkedList<IBasicBlock>)path.clone();
				paths.add(new Path(completePath));
				if(!hasUpperDecisionNode(path)){
					break;
				}else {
					currentNode = findUpperDecision(path);
					continue;
				}
				
			}else if (currentNode instanceof ConnectorNode) {
				System.out.println("currentNode is " + "ConnectorNode");
				ConnectorNode tempNode = (ConnectorNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof StartNode) {
				System.out.println("if there is a DecisionNode in path when meet a StartNode " + hasUpperDecisionNode(path));
				System.out.println("currentNode is " + "StartNode");
				StartNode tempNode = (StartNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
			}else {
				System.out.println("currentNode is " + "NOT RECOGNIZED");
				break;
			}
		}
		
		// 返回路径集
		Path[] pathArray = new Path[paths.size()];
		return paths.toArray(pathArray);
	}
	
	
	private static void printPath(LinkedList<IBasicBlock> path){
		System.out.println("find a path, its size is: " + path.size()+" and its content is: ");
		for (int i = 0; i < path.size(); i++) {// TODO extracted
			IBasicBlock dataBasicBlock=path.get(i);
			if (dataBasicBlock instanceof JavaPlainNode) {
				System.out.print("node "+i+"is: "+((JavaPlainNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof JavaBranchNode){// TODO print the condition
				System.out.print("node "+i+"is: "+((JavaBranchNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof JavaExitNode){
				JavaExitNode dataNode = (JavaExitNode)dataBasicBlock;
				if (dataNode.getData()!=null) {
					System.out.print("node "+i+"is: "+dataNode.getData());
				}
			}
		}
	}

	// 返回path中倒数第一的DecisionNode，并删除此DecisionNode之后的所有节点
	private static  DecisionNode findUpperDecision(LinkedList<IBasicBlock> path){
		if (!hasUpperDecisionNode(path)) {
			return null;
		}
		IBasicBlock tempBasicBlock;
		DecisionNode upperDecisionNode = null;
		for (int i = path.size()-1; i>=0 ; i--) {
			tempBasicBlock = path.remove(i);
			if (tempBasicBlock instanceof DecisionNode) {
				upperDecisionNode = (DecisionNode)tempBasicBlock;
				break;
			}
		}
		return upperDecisionNode;
	}
	// 判断path里是否存在更上层的DecisionNode，本算法采用从path的开始节点往后查找（也可以从后向前）
	private static boolean hasUpperDecisionNode(LinkedList<IBasicBlock> path){
		boolean has = false;
		IBasicBlock tempBasicBlock;
		for (int i = 0; i<path.size() ; i++) {
			tempBasicBlock = path.get(i);
			if (tempBasicBlock instanceof DecisionNode) {
				has = true;
			}
		}
		return has;
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