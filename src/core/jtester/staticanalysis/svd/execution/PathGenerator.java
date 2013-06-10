package core.jtester.staticanalysis.svd.execution;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	private Path path;
	public static final int limitOfloop = 3;

	public static LinkedList<Path> getPaths(JavaControlFlowGraph cfg) {
		LinkedList<Path> paths = new LinkedList<>();
		// TODO
		return paths;
	}

	public static void main(String[] args) throws IOException{
		String filePath = System.getProperty("user.dir")+"\\src\\core\\jtester\\staticanalysis\\svd\\test\\SVDTest.java";
		System.out.println(filePath);
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
		while(true){
			if (currentNode instanceof PlainNode && !(currentNode instanceof BranchNode)) {
//				System.out.println("PlainNode: " + ((AbstractBasicBlock)currentNode).getData());
				PlainNode tempNode = (PlainNode)currentNode;
				path.add(tempNode);
				currentNode = ((PlainNode) currentNode).getOutgoing();
				continue;
				
			}else if (currentNode instanceof DecisionNode) {// 维护detected信息
				DecisionNode tempNode = (DecisionNode) currentNode;
				if (tempNode.isDetected() && !hasUpperDecisionNode(path)) {// 如果所有的decision都被探测完，则路径搜索完毕
					System.out.println(cfg.getSignature()+" is manipulated");
					break;
				}else if (tempNode.getType()==DecisionType.switch_type || tempNode.getType()==DecisionType.if_type) {// 对于非循环节点
					if (tempNode.isDetected() && hasUpperDecisionNode(path)) {// 如果这个DecisionNode的分支已经被全部探测完，则回溯到上一个DecisionNode
						tempNode.cleanBranch();//将其各个branchnode 的detected设置为false
						currentNode = findUpperDecision(path);
					}else if(!tempNode.isDetected()){
						path.add(tempNode);
						currentNode = tempNode.getUndetectedBranch();
					}
					continue;
				}else if (tempNode.getType()==DecisionType.while_type) {
					if (tempNode.isDetected()) {// 如果这个DecisionNode的分支已经被全部探测完，清空所有while循环体在path中的节点
						ConnectorNode continueConn = (ConnectorNode)tempNode.getContinueConn();
						findFirstConn(continueConn, path);
						if(hasUpperDecisionNode(path)){
							currentNode = findUpperDecision(path);
							tempNode.cleanBranch();
						}else {
							System.out.println(cfg.getSignature()+" is manipulated");
							break;
						}
					}else if(!tempNode.isDetected()){
						path.add(tempNode);
						BranchNode loopBranchNode = tempNode.getLoopBranch();
						if (loopBranchNode.getVisitTime()<limitOfloop) {// 如果未达循环次数限制，则继续循环
							System.out.println("loop time: " + (loopBranchNode.getVisitTime()));
							currentNode = loopBranchNode;
						}else {// 已达循环限制，则标注loop branch 为 detected，并将currentNode设置为break branch
							currentNode =  tempNode.getBreakBranch();
						}
					}
				}else if (tempNode.getType() == DecisionType.dowhile_type) {

				}else if (tempNode.getType()==DecisionType.for_type) {
					
				}else if (tempNode.getType()==DecisionType.unleagal_type) {
					System.err.println("Unlegal DecisionNode: "+((Expression)tempNode.getData()));
				}
				continue;

			}else if (currentNode instanceof BranchNode) {// must solve BranchNode before PlainNode.
				BranchNode tempNode = (BranchNode)currentNode;
				if (tempNode.isDetected()) {
					currentNode=findUpperDecision(path);
				}else {
					if (tempNode.getLabel().equals(IBranchNode.WHILE_THEN)) {// 如果是whilethen分支
						tempNode.increaseVisitTime();
						if (tempNode.getVisitTime() >= limitOfloop) {
							tempNode.setDetected(true);
						}
						
					} else { // 如果是其他类型的分支，标记以探测，添加节点
						tempNode.setDetected(true);
					}
					path.add(tempNode);
					currentNode = tempNode.getOutgoing();
				}
				continue;
				
			}else if (currentNode instanceof JumpNode) {// TODO
				JumpNode tempNode = (JumpNode)currentNode;
				path.add(tempNode);
				/*if (tempNode.isBackwardArc()) {// 如果这是循环语句的跳转边
					
				}*/
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof ConnectorNode) {
				ConnectorNode tempNode = (ConnectorNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof StartNode) {
//				System.out.println("if there is a DecisionNode in path when meet a StartNode " + hasUpperDecisionNode(path));
//				System.out.println("currentNode is " + "StartNode");
				StartNode tempNode = (StartNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
			}else if (currentNode instanceof ExitNode) {
//				System.out.println("if there is a DecisionNode in path when meet a ExitNode " + hasUpperDecisionNode(path));
				ExitNode tempNode = (ExitNode)currentNode;
				path.add(tempNode);
				LinkedList<IBasicBlock> completePath = (LinkedList<IBasicBlock>)path.clone();
				Path pathFinded = new Path(completePath);
				pathFinded.env = new ProgramEnv();
				paths.add(pathFinded);
				if(!hasUpperDecisionNode(path)){
					break;
				}else {
					currentNode = findUpperDecision(path);
					DecisionNode tempDecisionNode=(DecisionNode)currentNode;
					continue;
				}
				
			}else {
				System.err.println("currentNode is " + "NOT RECOGNIZED");
				break;
			}
		}
		
		// 返回路径集
		Path[] pathArray = new Path[paths.size()];
		return paths.toArray(pathArray);
	}
	
	
	public static Path[] abstractPath(JavaControlFlowGraph cfg, int loopTimeLimit){
		ArrayList<Path> paths = new ArrayList<>();
		LinkedList<IBasicBlock> path = new LinkedList<>();
		IBasicBlock start = cfg.getStartNode();
		IBasicBlock currentNode = start;
		// 对给定的cfg进行路径抽取
		System.out.println("Begin path abstraction");
		while(true){
			if (currentNode instanceof PlainNode && !(currentNode instanceof BranchNode)) {
//				System.out.println("PlainNode: " + ((AbstractBasicBlock)currentNode).getData());
				PlainNode tempNode = (PlainNode)currentNode;
				path.add(tempNode);
				currentNode = ((PlainNode) currentNode).getOutgoing();
				continue;
				
			}else if (currentNode instanceof DecisionNode) {// 维护detected信息
				DecisionNode tempNode = (DecisionNode) currentNode;
				if (tempNode.isDetected() && !hasUpperDecisionNode(path)) {// 如果所有的decision都被探测完，则路径搜索完毕
					System.out.println(cfg.getSignature()+" is manipulated");
					break;
				}else if (tempNode.getType()==DecisionType.switch_type || tempNode.getType()==DecisionType.if_type) {// 对于非循环节点
					if (tempNode.isDetected() && hasUpperDecisionNode(path)) {// 如果这个DecisionNode的分支已经被全部探测完，则回溯到上一个DecisionNode
						tempNode.cleanBranch();//将其各个branchnode 的detected设置为false
						currentNode = findUpperDecision(path);
					}else if(!tempNode.isDetected()){
						path.add(tempNode);
						currentNode = tempNode.getUndetectedBranch();
					}
					continue;
				}else if (tempNode.getType()==DecisionType.while_type) {
					if (tempNode.isDetected()) {// 如果这个DecisionNode的分支已经被全部探测完，清空所有while循环体在path中的节点
						ConnectorNode continueConn = (ConnectorNode)tempNode.getContinueConn();
						findFirstConn(continueConn, path);
						if(hasUpperDecisionNode(path)){
							currentNode = findUpperDecision(path);
							tempNode.cleanBranch();
						}else {
							System.out.println(cfg.getSignature()+" is manipulated");
							break;
						}
					}else if(!tempNode.isDetected()){
						path.add(tempNode);
						BranchNode loopBranchNode = tempNode.getLoopBranch();
						if (loopBranchNode.getVisitTime()<loopTimeLimit) {// 如果未达循环次数限制，则继续循环
							System.out.println("loop time: " + (loopBranchNode.getVisitTime()));
							currentNode = loopBranchNode;
						}else {// 已达循环限制，则标注loop branch 为 detected，并将currentNode设置为break branch
							currentNode =  tempNode.getBreakBranch();
						}
					}
				}else if (tempNode.getType() == DecisionType.dowhile_type) {

				}else if (tempNode.getType()==DecisionType.for_type) {
					
				}else if (tempNode.getType()==DecisionType.unleagal_type) {
					System.err.println("Unlegal DecisionNode: "+((Expression)tempNode.getData()));
				}
				continue;

			}else if (currentNode instanceof BranchNode) {// must solve BranchNode before PlainNode.
				BranchNode tempNode = (BranchNode)currentNode;
				if (tempNode.isDetected()) {
					if (tempNode.getLabel().equals(BranchNode.WHILE_ELSE)) {
						currentNode = findUpperDecision(path);
						currentNode = findUpperDecision(path);
					}
					currentNode=findUpperDecision(path);
				}else {
					if (tempNode.getLabel().equals(IBranchNode.WHILE_THEN)) {// 如果是whilethen分支
						tempNode.increaseVisitTime();
						if (tempNode.getVisitTime() >= loopTimeLimit) {
							tempNode.setDetected(true);
						}
						
					} else { // 如果是其他类型的分支，标记以探测，添加节点
						tempNode.setDetected(true);
					}
					path.add(tempNode);
					currentNode = tempNode.getOutgoing();
				}
				continue;
				
			}else if (currentNode instanceof JumpNode) {// TODO
				JumpNode tempNode = (JumpNode)currentNode;
				path.add(tempNode);
				/*if (tempNode.isBackwardArc()) {// 如果这是循环语句的跳转边
					
				}*/
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof ConnectorNode) {
				ConnectorNode tempNode = (ConnectorNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof StartNode) {
//				System.out.println("if there is a DecisionNode in path when meet a StartNode " + hasUpperDecisionNode(path));
//				System.out.println("currentNode is " + "StartNode");
				StartNode tempNode = (StartNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
			}else if (currentNode instanceof ExitNode) {
//				System.out.println("if there is a DecisionNode in path when meet a ExitNode " + hasUpperDecisionNode(path));
				ExitNode tempNode = (ExitNode)currentNode;
				path.add(tempNode);
				LinkedList<IBasicBlock> completePath = (LinkedList<IBasicBlock>)path.clone();
				Path pathFinded = new Path(completePath);
				pathFinded.env = new ProgramEnv();
				paths.add(pathFinded);
				if(!hasUpperDecisionNode(path)){
					break;
				}else {
					currentNode = findUpperDecision(path);
					DecisionNode tempDecisionNode=(DecisionNode)currentNode;
					continue;
				}
				
			}else {
//				System.err.println("currentNode is " + "NOT RECOGNIZED");
				break;
			}
		}
		
		// 返回路径集
		Path[] pathArray = new Path[paths.size()];
		return paths.toArray(pathArray);
	}
	
	private static void printExpression(DecisionNode decisionNode){
		System.out.println("DecisionNode "+((Expression)decisionNode.getData()));// print Decision Expression
	}
	
	/**
	 * find the first connector node in path, and remove following node
	 * @param conn
	 * @param path
	 * @return
	 */
	private static ConnectorNode findFirstConn(ConnectorNode conn, LinkedList<IBasicBlock> path){
		boolean delete = false;
		ConnectorNode resultConnectorNode = null;
		IBasicBlock iBasicBlock;
		int persition = 0;
		for (; persition < path.size(); persition++) {
			iBasicBlock = path.get(persition);
			if (iBasicBlock == conn) {
				resultConnectorNode = (ConnectorNode) iBasicBlock;// 暂存并返回此connector节点
				break;
			}
		}
		// delete nodes
		for (int i = path.size()-1; i >= persition; i--) {
			path.remove(i);
		}
		return resultConnectorNode;
	}
	
	/**
	 * print the elements with data in a path
	 * @param path
	 */
	public static void printPath(LinkedList<IBasicBlock> path){
		System.out.println("find a path, its size is: " + path.size()+" and its content is: ");
		for (int i = 0; i < path.size(); i++) {// TODO extracted
			IBasicBlock dataBasicBlock=path.get(i);
			if (dataBasicBlock instanceof PlainNode && !(dataBasicBlock instanceof BranchNode)) {
				System.out.println("plain node"+i+" is: "+((PlainNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof BranchNode){// TODO print the condition
				System.out.println("branch node"+i+" is: "+((BranchNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof ExitNode){
				ExitNode dataNode = (ExitNode)dataBasicBlock;
				if (dataNode.getData()!=null) {
					System.out.println("exit node"+i+" is: "+dataNode.getData());
				}
			}
		}
	}
	/**
	 * print the elements with data in a path
	 * @param path
	 */
	public static void printPath(Path path){
		printPath(path.getPathNodes());
	}

	/**
	 *  返回path中倒数第一的DecisionNode，并删除此DecisionNode之后的所有节点
	 * @param path
	 * @return
	 */
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
	/**
	 * 判断path里是否存在更上层的DecisionNode，本算法采用从path的开始节点往后查找（也可以从后向前）
	 * @param path
	 * @return
	 */
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
	public static JavaControlFlowGraph[] read(String filePath) throws IOException {
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