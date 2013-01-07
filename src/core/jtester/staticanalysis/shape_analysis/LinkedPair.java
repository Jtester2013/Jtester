package core.jtester.staticanalysis.shape_analysis;

public class LinkedPair {
	AbstractLocation fst;
	AbstractLocation snd;
	
	public LinkedPair(AbstractLocation fst2, AbstractLocation snd2) {
		this.fst = fst2;
		this.snd = snd2;
	}
	public void setfst(AbstractLocation fst){
		this.fst = fst;
	}
	public AbstractLocation getfst(){
		return this.fst;
	}
	
	public void setsnd(AbstractLocation snd){
		this.snd = snd;
	}
	public AbstractLocation getsnd(){
		return this.snd;
	}

}
