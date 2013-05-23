package core.common.model.semantics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Name;

public class InferenceSemantics {
	private int line;
	private Name name;
	private int index;
	private Name method;
	private List<Expression> arguments = new ArrayList<Expression>();
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
		if(name != null){
			return name.getParent();
		}
		return null;
	}
	
	public void addDeclaration(DeclarationSemantics ds){
		declarations.add(ds);
	}
	
	public List<DeclarationSemantics> getDeclaraions(){
		return declarations;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
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
			sb.append("(");
			
			if(!arguments.isEmpty()){
				sb.append(arguments.get(0));
			}
			for(int i=1;i<arguments.size();i++){
				sb.append(",");
				sb.append(arguments.get(i));
			}
			sb.append(")");
		}

		return sb.toString();
	}
	
	public String toStringWithContext(){
		StringBuilder sb = new StringBuilder();
		sb.append(line);
		sb.append(": ");
		sb.append(name.getParent().getParent());
		
		return sb.toString().replace("\n", "");
	}
}
