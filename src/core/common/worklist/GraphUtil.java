package core.common.worklist;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import core.common.model.om.Job;

public class GraphUtil {
	
	public static <T extends Job> Map<T, Set<T>> generateInfluenceSet(T[] jobs){
		Map<T, Set<T>> result = new Hashtable<T, Set<T>>();
		
		for(T key : jobs){
			Set<T> set = new HashSet<T>();
			result.put(key, set);
		}
		
		for(T key : jobs){
			for(Job t : key.getConstraint()){
				result.get(t).add(key);
			}
		}
		return result;
	}

}