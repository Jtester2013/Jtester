package core.common.model.semantics;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;

public class DeclarationSemantics {
	private int line;
	private Type type;
	private Name name;
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
	
	@Override
	public String toString(){
		return line + ": " + name.getParent().getParent().toString().replace("\n", "");
	}
}
