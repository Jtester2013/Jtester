package data_flow;

public class ShapeAnalysis {
	
	Link foo6(Link p){
		Link q =  null;
		Link temp = null;
		temp = p;
		q = p;
		p = p.next;
		q.next = temp;
		return q;
	}
}

class Link {
	int data;
	Link next;
}