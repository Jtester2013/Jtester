package core.common.model.semantics;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;

public class DeclarationSemantics {
	private int line;
	private Type type;
	private Name name;
	private boolean isArgument;
	private Expression value;

	public int getLine() {
		return line;
	}

	public Type getType() {
		return type;
	}

	public Name getName() {
		return name;
	}

	public Expression getValue() {
		return value;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
	public boolean isArument() {
		return isArgument;
	}

	public void setIsArument(boolean isArument) {
		this.isArgument = isArument;
	}

	@Override
	public String toString(){
		if(isArgument){
			return line + ": " + type + " " + name;
		}
		return line + ": " + name.getParent().getParent().toString().replace("\n", "");
	}
}
