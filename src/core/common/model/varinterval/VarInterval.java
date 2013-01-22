package core.common.model.varinterval;

public class VarInterval {
	private String name;
	private IntegerInterval interval;
	
	public VarInterval(){
		
	}
	
	public VarInterval(String name){
		this.name = name;
	}
	
	public VarInterval(String name, IntegerInterval interval){
		this.name = name;
		this.interval = interval;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public IntegerInterval getInterval(){
		return this.interval;
	}
	public void setInterval(IntegerInterval interval){
		this.interval = interval;
	}
	
	public VarInterval clone(){
		VarInterval rs = new VarInterval();
		rs.interval = this.interval.clone();
		rs.name = this.name;
		return rs;
	}
	
	public boolean equals(Object obj){
		if(this.name.equals(((VarInterval)obj).getName()) && this.interval.equals(((VarInterval)obj).getInterval()))
				return true;
		else 
			return false;
	}
	
	public String toString(){
		return "{" + this.name + " , " + this.interval.getMin()+" , "+this.interval.getMax()+"}";
	}

}
