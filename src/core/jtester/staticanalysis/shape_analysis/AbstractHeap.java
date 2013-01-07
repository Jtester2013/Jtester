package core.jtester.staticanalysis.shape_analysis;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.QualifiedName;

public class AbstractHeap {
	ArrayList<LinkedPair> _heap;

	public AbstractHeap(){
		this._heap = new ArrayList<LinkedPair>();
	}
	
	public AbstractHeap(AbstractHeap _heap2) {
		this._heap = new ArrayList<LinkedPair>();
		ArrayList<LinkedPair> lp = _heap2.getHeap();
		for (int i = 0; i < lp.size(); i++){
			AbstractLocation fst = new AbstractLocation(lp.get(i).getfst());
			AbstractLocation snd = new AbstractLocation(lp.get(i).getsnd());
			LinkedPair temp = new LinkedPair(fst, snd);
			this._heap.add(temp);
		}
	}

	public ArrayList<LinkedPair> getHeap(){
		return this._heap;
	}
	
	public void removeVar(Expression var) {
		//迭代去除AbstarctLocation中的var所对应的抽象地址，如果去除以后_locName为空，那么将节点类型修改为SUMMARY
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair temp = this._heap.get(i);
			temp.getfst().removeLocName(var.toString());
			temp.getsnd().removeLocName(var.toString());
//			if ((temp.getfst().getLocName().size() == 0)||(temp.getsnd().getLocName().size() == 0)){
//				this._heap.remove(i--);
//			}
		}
	}
	
	public void addLinkedPair(LinkedPair lp) {
		this._heap.add(lp);		
	}
	
	public void removeLinkedPair(AbstractLocation xLoc, AbstractLocation xSel) {
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair lp = this._heap.get(i);
			if ((lp.getfst().compareLoc(xLoc)==0) && (lp.getsnd().compareLoc(xSel) == 0)){
				this._heap.remove(i);
				break;
			}
		}
	}
	
	
	public AbstractLocation getSndbyFst(AbstractLocation xLoc) {
		AbstractLocation result = null;
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair lp = this._heap.get(i);
			if (lp.getfst().compareLoc(xLoc) == 0){
				result = lp.getsnd();
				break;
			}		
		}
		return result;
	}

	public void addLinkedPair(AbstractLocation fst, AbstractLocation snd) {
		LinkedPair lp = new LinkedPair(fst, snd);
		this._heap.add(lp);		
	}

	public boolean isExist(String type1, String type2) {
		boolean result = false;
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair lp = this._heap.get(i);
			if ((lp.getfst().getType().compareTo(type1) == 0) && (lp.getsnd().getType().compareTo(type2)==0)){
				result = true;
				break;
			}
		}
		return result;
	}

	
	public void removeLinkedPair(String summary1, String summary2) {
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair lp = this._heap.get(i);
			if ((lp.getfst().getType().compareTo(summary1) == 0) && (lp.getsnd().getType().compareTo(summary2)==0)){
				this._heap.remove(i);
				break;
			}
		}
	}

	public void removeLinkedPair(AbstractLocation fst, String summary) {
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair lp = this._heap.get(i);
			if ((lp.getfst().compareLoc(fst) == 0) && (lp.getsnd().getType().compareTo(summary)==0)){
				this._heap.remove(i);
				break;
			}
		}		
	}

	public void addLinkedPair(AbstractLocation fst, String type) {
		ArrayList<String> ln = new ArrayList<String>();
		AbstractLocation snd = new AbstractLocation (type, ln);
		LinkedPair lp = new LinkedPair(fst, snd);
		this._heap.add(lp);
	}

	public void removeLink2Var(Expression var) {
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair temp = this._heap.get(i);
			temp.getfst().removeLocName(var.toString());
			temp.getsnd().removeLocName(var.toString());
			
			if ((temp.getfst().getLocName().size() == 0)){
				temp.getfst().setType("SUMMARY");
			}
			
			if ((temp.getsnd().getLocName().size() == 0)){
				temp.getsnd().setType("SUMMARY");
			}
		}
	}

	public void changeXSel2Y(Expression oprand1, String nameY, AbstractLocation locY) {
		
		String nameX = ((QualifiedName)oprand1).getQualifier().toString();
		
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair temp = this._heap.get(i);
			AbstractLocation fst = temp.getfst();
			AbstractLocation snd = temp.getsnd();
			if (fst.getLocName().contains(nameX)){
				if (snd.getType().compareTo("NILL") == 0){
					if (locY.getType().compareTo("NILL") == 0){
						//do nothing
					} else if (locY.getType().compareTo("CONCRETE") == 0){
						this._heap.remove(i);
						AbstractLocation fst2 = new AbstractLocation(fst);
						AbstractLocation snd2 = new AbstractLocation(locY);
						this.addLinkedPair(fst2,snd2);
					}				
				} else if (snd.getType().compareTo("CONCRETE") == 0){
					if (locY.getType().compareTo("NILL") == 0){
						this._heap.remove(i);
						AbstractLocation fst2 = new AbstractLocation(fst);
						AbstractLocation snd2 = new AbstractLocation("NILL", null);
					} else if (locY.getType().compareTo("CONCRETE") == 0){
						this._heap.remove(i);
						AbstractLocation fst2 = new AbstractLocation(fst);
						AbstractLocation snd2 = new AbstractLocation(locY);
						this.addLinkedPair(fst2,snd2);
					}
				} else if (snd.getType().compareTo("SUMMARY") == 0){
					if (locY.getType().compareTo("NILL") == 0){
						this._heap.remove(i);
						AbstractLocation fst2 = new AbstractLocation(fst);
						ArrayList<String> ln = new ArrayList<String>();
						AbstractLocation snd2 = new AbstractLocation("NILL", ln);
						this.addLinkedPair(fst2,snd2);
					} else if (locY.getType().compareTo("CONCRETE") == 0){
						this._heap.remove(i);
						AbstractLocation fst2 = new AbstractLocation(fst);
						AbstractLocation snd2 = new AbstractLocation(locY);
						this.addLinkedPair(fst2,snd2);
					}
				} else {
					
				}
				
				break;
			}
		}
		
	}

	public void addLink2Var(Expression oprand1, Expression oprand2) {
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair temp = this._heap.get(i);
			ArrayList<String> locName = temp.getfst().getLocName();
			for (int j = 0; j < locName.size(); j ++){
				if (locName.get(j).compareTo(oprand2.toString()) == 0){
					locName.add(oprand1.toString());
					break;
				}
			}
			temp.getfst().setLocName(locName);
		}
	}

	public String displayHeap() {
		String result = "";
		for (int i = 0; i < this._heap.size(); i++){
			LinkedPair temp = this._heap.get(i);
			result += " ( ";
			result += temp.getfst().displayLoc();
			result += ", ";
			result += temp.getsnd().displayLoc();
			result += " ); ";
		}
		return result;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
