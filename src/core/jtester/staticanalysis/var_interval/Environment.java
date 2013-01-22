package core.jtester.staticanalysis.var_interval;

/**
 * 存储环境信息，包含变量对应的区间等信息
 */
import java.util.ArrayList;

import core.common.model.varinterval.VarInterval;

public class Environment {
	private String envKey;
	private ArrayList<VarInterval> env;
	
	public Environment(String key, ArrayList<VarInterval> interval){
		this.envKey = key;
		this.env = interval;
	}
	
	public String getEnvKey(){
		return this.envKey;
	}
	public void setEnvKey(String key){
		this.envKey = key;
	}
	
	public ArrayList<VarInterval> getEnv(){
		return this.env;
	}
	public void setEnv(ArrayList<VarInterval> interval){
		this.env = interval;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("{").append(this.envKey).append(" , ").append("[");
		for(int i=0;i<this.env.size();i++){
			if(i+1 == this.env.size()){
				s.append(this.env.get(i).toString());
			}
			else{
				s.append(this.env.get(i).toString()).append(" , ");
			}
		}
		s.append("]").append("}").append(",");
		
		return s.toString();
	}

}
