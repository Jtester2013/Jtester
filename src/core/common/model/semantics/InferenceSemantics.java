package core.common.model.semantics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Name;

public class InferenceSemantics {
	private int line;
	private Name name;
	private int index;
	private Name method;
	private List<DeclarationSemantics> declarations = new ArrayList<DeclarationSemantics>();
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Name getMethod() {
		return method;
	}

	public void setMethod(Name method) {
		this.method = method;
	}
	
	public ASTNode getContext(){
		return name.getParent();
	}
	
	public void addDeclaration(DeclarationSemantics ds){
		declarations.add(ds);
	}
	
	public List<DeclarationSemantics> getDeclaraions(){
		return declarations;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(line);
		sb.append(": ");
		sb.append(name);
		if(index != 0){
			sb.append("[");
			sb.append(index);
			sb.append("]");
		}
		
		if(method!=null){
			sb.append(".");
			sb.append(method);
		}
		
		sb.append(" (");
		for(DeclarationSemantics ds: declarations){
			sb.append(ds.getLine() + " ");
		}
		sb.append(")");

		return sb.toString();
	}
	
	public String toStringWithContext(){
		StringBuilder sb = new StringBuilder();
		sb.append(line);
		sb.append(": ");
		sb.append(name.getParent().getParent());
		
		return sb.toString();
	}
}
