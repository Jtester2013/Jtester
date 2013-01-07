package core.jtester.staticanalysis.shape_analysis;

import java.util.ArrayList;

public class AbstractLocation {
	/*  abstract location 就三个类型，一个是直接被变量名映射的，称为concrete；
	 *	一个是不能由变量名直接映射到，称为summary
	 *   最后一个是变量指向空，称为，nill
	 */

	String _type;
	ArrayList<String> _locName;

	public AbstractLocation(String type, ArrayList<String> locName) {
		this._type = type;
		this._locName = (ArrayList<String>) locName.clone();

		// this._selector = null;
	}

	public AbstractLocation(AbstractLocation xLoc) {
		this._type = xLoc._type;
		this._locName = (ArrayList<String>) xLoc._locName.clone();
	}

	public void addLocName(String name){
		this._locName.add(name);
	}
	
	public void removeLocName(String name) {
		for (int i = 0; i < this._locName.size(); i++) {
			if (name.compareTo(this._locName.get(i)) == 0) {
				this._locName.remove(i);
			}
		}
	}

	public void setType(String type) {
		this._type = type;
	}

	public void setLocName(ArrayList<String> locName){
		this._locName = locName;
	}
	public ArrayList<String> getLocName() {
		return this._locName;
	}

	public String getType() {
		return this._type;
	}

	public int compareLoc(AbstractLocation xLoc) {
		int result = -1;
		if ((this._type.compareTo(xLoc.getType()) == 0) && (this._locName.size() == xLoc.getLocName().size())){
			result = 0;
			boolean flag = false;
			for (int i = 0; i < this._locName.size(); i++){
				flag = false;
				for (int j = 0; j < xLoc._locName.size(); j++){
					if (this._locName.get(i).compareTo(xLoc.getLocName().get(j)) == 0){
						flag = true;
						break;
					}
				}
				if (!flag){
					result = -1;
					break;
				}
			}
		}
		return result;
	}
	
	public String displayLoc(){
		String result = "";
		if (this._type.compareTo("NILL") == 0){
			result += "loc(0)";
		} else	if (this._type.compareTo("SUMMARY") == 0){
			result += "loc(2)";
		} else	if (this._type.compareTo("CONCRETE") == 0){
			result += "loc(1| ";
			for (int i = 0; i < this._locName.size(); i++){
				result += this._locName.get(i) + " ";
			}
			result += ")";
		}
		return result;
	}
}
