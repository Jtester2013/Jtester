package core.common.worklist;

import java.util.Map;
import java.util.Set;

import core.common.model.om.Job;

/**
 * 
 * @author Bin
 *
 * Template Worklist Algorithm
 */

public abstract class Worklist<T extends Job> {
	private T[] jobs;
	private Map<T, Set<T>> infl;
	
	public Worklist(T[] jobs){
		this.jobs = jobs;
	}
	
	public T[] executeAnalysis(){
		for (T var : this.jobs) {
			this.insert(var);
		}
		
		infl = GraphUtil.generateInfluenceSet(jobs);
		
		T current = null;
		while ((current = extract()) != null) {
			if(eval(current)){
				for(Job var : infl.get(current)){
					this.insert(var);
				}
			}
		}
		
		return this.jobs;
	}
	
	public abstract void insert(Job variable);
	public abstract T extract();
	public abstract boolean eval(T variable);

}
