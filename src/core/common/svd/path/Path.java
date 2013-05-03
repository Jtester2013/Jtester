package core.common.svd.path;

import java.util.Iterator;
import java.util.LinkedList;

import core.common.cfg.interfaces.IBasicBlock;

public class Path {
	LinkedList<IBasicBlock> pathNodes = new LinkedList<>();
	
	public Path(LinkedList<IBasicBlock> path){
		pathNodes = path;
	}
	
	public int size(){
		return pathNodes.size();
	}
	
	public void add(IBasicBlock node){
		pathNodes.add(node);
		pathNodes.clone();
	}
	
	/*public Path clone(){
		Path copyPath=new Path();
		LinkedList<IBasicBlock> copyPathNodes=new LinkedList<>();
		for (Iterator iterator = pathNodes.iterator(); iterator.hasNext();) {
			IBasicBlock pathNode = (IBasicBlock) iterator.next();
		}
		return null;
	}*/
}
