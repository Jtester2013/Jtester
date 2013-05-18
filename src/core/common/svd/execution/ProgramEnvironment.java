package core.common.svd.execution;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.NullLiteral;



public class ProgramEnvironment {
	private Map<String, Expression> svt;
	private Map<String, String> vt;

	public ProgramEnvironment() {
		vt = new HashMap<String, String>(); // its key means variable zzj
		svt = new HashMap<String, Expression>(); // its key means value zzj
	}

	public String getVariableName(String var) {
		if (vt.containsKey(var)) {
			return vt.get(var);
		} else {
			return null;
		}
	}

	public void setVariableName(String var, String pvar) {
		this.vt.put(var, pvar);
	}

	public Expression getValue(String var) {
		if (svt.containsKey(var)) {
			return svt.get(var);
		} else {
//			return new NullLiteral();
			return null;
		}
	}

	public Map<String, Expression> getMap() {
		return this.svt;
	}

	public Map<String, String> getVMap() {
		return this.vt;
	}

	public boolean containsVariable(String var) {
		return vt.containsKey(var);
	}

	public boolean containsValue(String var) {
		return (svt.containsKey(var));
	}

	public boolean containsLiteralVariable(String var) {
		return vt.containsValue(var);
	}

	public String getNameByLiteralVariable(String variableName) {
		for (String key : vt.keySet()) {
			if (vt.get(key).equalsIgnoreCase(variableName)) {
				return key;
			}
		}
		return variableName;
	}

	public void setValue(String string, Expression value) {
		svt.put(string, value);
	}

	public void printValue() {
		System.out.println("About to print the symbol variable table:");
		Set<String> keys = svt.keySet();
		for (String key : keys) {
			System.out.print("\t Variable: " + key);
			System.out.println("\t Value:" + svt.get(key).toString());
		}
	}
}
