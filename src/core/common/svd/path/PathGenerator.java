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
			// ��ȡ����·��
			cfgInstance = cfgsArray[i];
			Path[] paths = abstractPath(cfgInstance);
			System.out.println(paths.length);
			// ��ӡpath
			for (int j = 0; j < paths.length; j++) {
				System.out.println("Print path��"+i);
				printPath(paths[i].pathNodes);
			}
		}
	}

	public static Path[] abstractPath(JavaControlFlowGraph cfg){
		ArrayList<Path> paths = new ArrayList<>();
		LinkedList<IBasicBlock> path = new LinkedList<>();
		IBasicBlock start = cfg.getStartNode();
		IBasicBlock currentNode = start;
		// �Ը�����cfg����·����ȡ
		System.out.println("Begin path abstraction");
		
		while (currentNode!=null || path.size()!=0) {// 
			
			if (currentNode instanceof BranchNode) {// must solve BranchNode before PlainNode.
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
				PlainNode tempNode = (PlainNode)currentNode;
				path.add(currentNode);
				currentNode = ((PlainNode) currentNode).getOutgoing();
				continue;
				
			}else if (currentNode instanceof DecisionNode) {// ά��detected��Ϣ
				DecisionNode tempNode = (DecisionNode)currentNode;
				if (tempNode.isDetected()) {// ������DecisionNode�ķ�֧�Ѿ���ȫ��̽���꣬����ݵ���һ��DecisionNode
					tempNode.cleanBranch();//�������branchnode ��detected����Ϊfalse
					currentNode = findUpperDecision(path);
				}else {
					path.add(tempNode);
					currentNode = tempNode.getUndetectedBranch();
				}
				continue;
				
			}else if (currentNode instanceof JumpNode) {// TODO
				JumpNode tempNode = (JumpNode)currentNode;
				path.add(tempNode);
				/*if (tempNode.isBackwardArc()) {// �������ѭ��������ת��
					
				}*/
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof ExitNode) {
				ExitNode tempNode = (ExitNode)currentNode;
				path.add(tempNode);
				LinkedList<IBasicBlock> completePath = (LinkedList<IBasicBlock>)path.clone();
				paths.add(new Path(completePath));
				currentNode = findUpperDecision(path);
				continue;
				
			}else if (currentNode instanceof ConnectorNode) {
				ConnectorNode tempNode = (ConnectorNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
				
			}else if (currentNode instanceof StartNode) {
				StartNode tempNode = (StartNode)currentNode;
				path.add(currentNode);
				currentNode = tempNode.getOutgoing();
				continue;
			}
		}
		
		// ����·����
		Path[] pathArray = new Path[paths.size()];
		return paths.toArray(pathArray);
	}
	
	
	private static void printPath(LinkedList<IBasicBlock> path){
		System.out.println("find a path, its size is: " + path.size()+" and its content is: ");
		for (int i = 0; i < path.size(); i++) {// TODO extracted
			IBasicBlock dataBasicBlock=path.get(i);
			if (dataBasicBlock instanceof JavaPlainNode) {
				System.out.print("node "+i+"is: "+((JavaPlainNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof JavaBranchNode){
				System.out.print("node "+i+"is: "+((JavaBranchNode)dataBasicBlock).getData());
			}else if(dataBasicBlock instanceof JavaExitNode){
				JavaExitNode dataNode = (JavaExitNode)dataBasicBlock;
				if (dataNode.getData()!=null) {
					System.out.print("node "+i+"is: "+dataNode.getData());
				}
			}
		}
	}

	// ����path�е�����һ��DecisionNode����ɾ����DecisionNode֮������нڵ�
	private static  DecisionNode findUpperDecision(LinkedList<IBasicBlock> path){
		IBasicBlock tempBasicBlock;
		for (int i = path.size(); i>0 ; i--) {
			tempBasicBlock = path.remove();
			if (tempBasicBlock instanceof DecisionNode) {
				return (DecisionNode)tempBasicBlock;
			}
		}
		return null;
	}
	/**
	 * ��ȡjavaԴ�ļ�
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static JavaControlFlowGraph[] read(String filePath) throws IOException {
		// ��ȡԴ�ļ�
		File file = new File(filePath);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		fis.close();
		String content = new String(b);
		// ����Դ�ļ����ɱ��뵥Ԫ
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(content.toCharArray());
		CompilationUnit resultUnit = (CompilationUnit) parser.createAST(null);
		// ��ȡ��һ����������
		List types = resultUnit.types();
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// ȡ�õ�һ���������ķ�������
		MethodDeclaration[] methods = typeDec.getMethods();
		// ���ظ����������ɵ�CFG
		LinkedList<JavaControlFlowGraph> cfgs = new LinkedList<>();
		ControlFlowGraphBuilder builder = new ControlFlowGraphBuilder();
		for (int i = 0; i < methods.length; i++) {
			cfgs.add(builder.build(methods[i]));
		}
		JavaControlFlowGraph[] cfgArrayControlFlowGraphs = new JavaControlFlowGraph[methods.length];
		return cfgs.toArray(cfgArrayControlFlowGraphs);
	}
}