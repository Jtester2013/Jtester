package core.common.model.semantics;

import java.util.List;

import org.eclipse.jdt.core.dom.Name;

public class MethodSemantics {
	private int line;
	private Name name;
	private List parameters;
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Name getName() {
		return name;
	}
	
	public void setParametors(List parametors){
		this.parameters = parametors;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(line);
		sb.append(": ");
		sb.append(name);
		sb.append("(");
		
		int i=0;
		while(i<parameters.size()){
			sb.append(parameters.get(i));
			sb.append(",");
			i++;
		}
		if(i > 0){
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append(")");
		return sb.toString();
	}
}
