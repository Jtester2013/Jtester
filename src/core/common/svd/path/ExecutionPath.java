package core.common.svd.path;

import java.util.Iterator;
import java.util.LinkedList;
import core.common.cfg.javacfg.*;
import core.common.cfg.model.*;

import org.eclipse.jdt.core.dom.ASTNode;

public class ExecutionPath {
	// a program execution path formed with several ASTNodes
	private LinkedList<AbstractBasicBlock> pathAstNodes;
	private PathEnvironment environment;

	public PathEnvironment getEnvironment() {
		return environment;
	}


	public void setEnvironment(PathEnvironment environment) {
		this.environment = environment;
	}


	public LinkedList<AbstractBasicBlock> getPathAstNodes() {
		return pathAstNodes;
	}


	public void setPathAstNodes(LinkedList<AbstractBasicBlock> pathAstNodes) {
		this.pathAstNodes = pathAstNodes;
	}
	
	/**
	 * 向pathAstNodes中添加后继要执行的节点
	 * @param node
	 * @return
	 */
	public boolean add(AbstractBasicBlock node){
		if (node==null) {
			return false;
		}
		pathAstNodes.add(node);
		return true;
	}
	
	/**
	 * 返回pathAstNodes的iterator，供遍历
	 * @return
	 */
	public Iterator<AbstractBasicBlock> getIterator(){
		if (pathAstNodes == null) {
			return null;
		}
		return pathAstNodes.iterator();
	}
	
	/**
	 * 
	 */
	public ExecutionPath clone(){
		ExecutionPath newPath = new ExecutionPath();
		newPath.setPathAstNodes((LinkedList<AbstractBasicBlock>)(this.getPathAstNodes().clone()));
		newPath.setEnvironment(this.getEnvironment().clone());
		if (this!=null) {
			LinkedList<AbstractBasicBlock> temp = this.getPathAstNodes();
			LinkedList<AbstractBasicBlock> newNodes = new LinkedList<AbstractBasicBlock>();
			for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
				AbstractBasicBlock astNode = (AbstractBasicBlock) iterator.next();
				newNodes.add(astNode);
			}
		}
		return newPath;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
