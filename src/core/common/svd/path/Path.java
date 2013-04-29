package core.common.svd.path;

import java.util.Iterator;
import java.util.LinkedList;

public class Path {
	LinkedList<PathNode> pathNodes;
	public Path clone(){
		Path copyPath=new Path();
		LinkedList<PathNode> copyPathNodes=new LinkedList<>();
		for (Iterator iterator = pathNodes.iterator(); iterator.hasNext();) {
			PathNode pathNode = (PathNode) iterator.next();
			
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<PathNode> list = new LinkedList<>();

	}

}
