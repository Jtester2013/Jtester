package core.common.svd.execution;

import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.ControlFlowGraphBuilder;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.cfg.model.DecisionNode;
import core.common.cfg.model.ExitNode;
import core.common.cfg.model.PlainNode;
import core.common.svd.path.Path;
import core.common.svd.path.PathGenerator;
import core.common.svd.path.ProgramEnv;

public class SymbolExecutor {
	
	public LinkedList<Path> execute(MethodDeclaration method){
		LinkedList<Path> executablePaths=new LinkedList<>();
		// ����method��cfg,������cfg�õ�·����
		ControlFlowGraphBuilder builder = new ControlFlowGraphBuilder();
		JavaControlFlowGraph cfg = builder.build(method);
		Path[] pathCollection = PathGenerator.abstractPath(cfg);
		// ��ÿ��·�����з���ִ��
		Path tempPath;
		for (int i = 0; i < pathCollection.length; i++) {
			tempPath = pathCollection[i];
			// TODO ��tempPath���з���ִ�У�������ִ����ȥ��path��ӵ�executablePaths��
			if (execute(tempPath)) {
				executablePaths.add(tempPath);
			}
		}
		return executablePaths;
	}
	
	/**
	 * ��path���з���ִ��
	 * @param path ������ִ�е�·��
	 * @return ��path�Ƿ�ɱ�ִ�е����������Է���true�������Է���false
	 */
	public boolean execute(Path path){
		// ��Decision���ռ�
		ProgramEnv env = path.getEnv();
		SymbolicExeVisitor visitor = new SymbolicExeVisitor(env);
		IBasicBlock currentNode;
		for (Iterator iterator = path.getPathNodes().iterator(); iterator.hasNext();) {
			currentNode = (IBasicBlock)iterator.next();
			if (currentNode instanceof DecisionNode) {
				// ��DecisionNode����Լ����⣬�����ɽ�ʱbreak
				
			} else if (currentNode instanceof PlainNode && !(currentNode instanceof DecisionNode)) {
				// ����ͨ�ڵ���з���ִ��
				ASTNode dataNode = (ASTNode)(((PlainNode)currentNode).getData());
				dataNode.accept(visitor);
			} else if (currentNode instanceof ExitNode) {
				// ��������ExitNode������true
				
			}
		}
		return false;
	}

	public static void main(String[] args){
		
	}
}
