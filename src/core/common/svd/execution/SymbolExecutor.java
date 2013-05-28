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
		// 构建method的cfg,并根据cfg得到路径集
		ControlFlowGraphBuilder builder = new ControlFlowGraphBuilder();
		JavaControlFlowGraph cfg = builder.build(method);
		Path[] pathCollection = PathGenerator.abstractPath(cfg);
		// 对每条路径进行符号执行
		Path tempPath;
		for (int i = 0; i < pathCollection.length; i++) {
			tempPath = pathCollection[i];
			// TODO 对tempPath进行符号执行，将可以执行下去的path添加到executablePaths中
			if (execute(tempPath)) {
				executablePaths.add(tempPath);
			}
		}
		return executablePaths;
	}
	
	/**
	 * 对path进行符号执行
	 * @param path 被符号执行的路径
	 * @return 此path是否可被执行到结束，可以返回true，不可以返回false
	 */
	public boolean execute(Path path){
		// 对Decision的收集
		ProgramEnv env = path.getEnv();
		SymbolicExeVisitor visitor = new SymbolicExeVisitor(env);
		IBasicBlock currentNode;
		for (Iterator iterator = path.getPathNodes().iterator(); iterator.hasNext();) {
			currentNode = (IBasicBlock)iterator.next();
			if (currentNode instanceof DecisionNode) {
				// 对DecisionNode进行约束求解，当不可解时break
				
			} else if (currentNode instanceof PlainNode && !(currentNode instanceof DecisionNode)) {
				// 对普通节点进行符号执行
				ASTNode dataNode = (ASTNode)(((PlainNode)currentNode).getData());
				dataNode.accept(visitor);
			} else if (currentNode instanceof ExitNode) {
				// 当运行至ExitNode，返回true
				
			}
		}
		return false;
	}

	public static void main(String[] args){
		
	}
}
