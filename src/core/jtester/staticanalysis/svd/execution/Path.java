package core.jtester.staticanalysis.svd.execution;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.jdt.core.dom.Expression;

import core.common.cfg.interfaces.IBasicBlock;

public class Path {
	LinkedList<IBasicBlock> pathNodes = new LinkedList<>();
	ProgramEnv env = new ProgramEnv();
	
	public Path(LinkedList<IBasicBlock> path){
		pathNodes = path;
	}
	
	public Iterator<IBasicBlock> iterator(){
		return pathNodes.iterator();
	}
	
	public int size(){
		return pathNodes.size();
	}
	
	public void add(IBasicBlock node){
		pathNodes.add(node);
		pathNodes.clone();
	}
	
	public Path clone(){
		LinkedList<IBasicBlock> clonePathNodes = (LinkedList<IBasicBlock>) this.pathNodes.clone();
		Path copyPath=new Path(clonePathNodes);
		return copyPath;
	}

	public ProgramEnv getEnv() {
		return env;
	}

	public void setEnv(ProgramEnv env) {
		this.env = env;
	}

	public LinkedList<IBasicBlock> getPathNodes() {
		return pathNodes;
	}

	public void setPathNodes(LinkedList<IBasicBlock> pathNodes) {
		this.pathNodes = pathNodes;
	}
	
}
