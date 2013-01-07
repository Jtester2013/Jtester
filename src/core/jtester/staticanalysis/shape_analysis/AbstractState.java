package core.jtester.staticanalysis.shape_analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jdt.core.dom.Expression;

public class AbstractState {
	HashMap<String, AbstractLocation> _state;
	
	public AbstractState(){
		this._state = new HashMap<String, AbstractLocation>();
	}
	public AbstractState(String name, AbstractLocation al){
		this._state = new HashMap<String, AbstractLocation>();
		this._state.put(name, al);
	}
	
	public AbstractState(AbstractState _state2) {
		this._state = new HashMap<String, AbstractLocation>();
		Iterator it = _state2.getState().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			String key = (String) e.getKey();
			AbstractLocation value = new AbstractLocation((AbstractLocation) e.getValue());
			this._state.put(key, value);			
		}
	}
	
	public HashMap<String, AbstractLocation> getState(){
		return this._state;
	}
	public void remove(Expression var) {
		this._state.remove(var.toString());
	}
	
	public void add(String key, AbstractLocation value) {
		this._state.put(key, value);	
	}
	
	
	public void add(Expression oprand1, Expression oprand2) {
		AbstractLocation tempValue = this._state.get(oprand2.toString());
		tempValue.addLocName(oprand1.toString());
		this._state.put(oprand2.toString(), tempValue);
	}

	public AbstractLocation getLocation(String key) {
		return this._state.get(key);
	}

	public void removeBinding2Var(Expression var) {
		/* 1.迭代去掉AbstractLocation中的var
		 * 2.去除HashMap中的var
		 */
		Iterator it = this._state.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			String tempKey = (String) e.getKey();
			AbstractLocation tempValue = (AbstractLocation) e.getValue();
			tempValue.removeLocName(var.toString());
			this._state.put(tempKey, tempValue);			
		}
		this._state.remove(var.toString());
	}

	public void addBinding2Var(Expression oprand1, Expression oprand2) {
		AbstractLocation tempValue = this._state.get(oprand2.toString());
		if(tempValue == null){
			return;
		}
		
		tempValue.addLocName(oprand1.toString());
		this._state.put(oprand2.toString(), tempValue);
		
		AbstractLocation tempValue2 = new AbstractLocation(tempValue);
		this._state.put(oprand1.toString(), tempValue2);
	}
	
	public String displayState() {
		String result = "";
		Iterator it = this._state.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			String key = (String) e.getKey();
			AbstractLocation value = (AbstractLocation) e.getValue();
			result += " ( " + key + ", " ;
			result += value.displayLoc();
			result += " ); ";
		}
		return result;
	}
	
}
