package core.jtester.staticanalysis.svd.execution;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.jdt.core.dom.Expression;


public class ProgramEnv {
	private Map<String, ExpressionNode> svt;

	public ProgramEnv() {
		svt = new HashMap<String, ExpressionNode>(); // its key means value zzj
	}

	public ExpressionNode getValue(String var) {
		if (svt.containsKey(var)) {
			return svt.get(var);
		} else {
			return null;
		}
	}
	public void setValue(String string, ExpressionNode value) {
		svt.put(string, value);
	}

	public Map<String, ExpressionNode> getMap() {
		return this.svt;
	}

	public boolean containsValue(String var) {
		return (svt.containsKey(var));
	}

	public void printValue() {
		System.out.println("About to print the symbol variable table:");
		Set<String> keys = svt.keySet();
		for (String key : keys) {
			System.out.print("\t Variable: " + key);
			System.out.println("\t Value:" + svt.get(key).toString());
		}
	}
	
	public boolean existVar(String varName){
		return svt.containsKey(varName);
	}
}
