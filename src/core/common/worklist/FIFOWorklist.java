package core.common.worklist;

import java.util.LinkedList;
import java.util.Queue;

import core.common.model.om.Job;

public abstract class FIFOWorklist<T extends Job> extends Worklist<T> {
	
	Queue<T> worklist;

	public FIFOWorklist(T[] constraintVariables) {
		super(constraintVariables);
		this.worklist = new LinkedList<T>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(Job variable) {
		this.worklist.offer((T) variable);
	}

	@Override
	public T extract() {
		return this.worklist.poll();
	}
	
}
