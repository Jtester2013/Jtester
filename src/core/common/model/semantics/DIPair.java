package core.common.model.semantics;

/**
 * This class contains information about 
 * 
 * 1. the line number that a variable is used
 * 2. variable name
 * 3. the line number that the variable is defined 
 * 
 * Above information could be used to refine semantics
 * 
 * @author XingxuWu
 *
 */
public class DIPair {
	private int line;
	private String name;
	private int declarationLine;
	
	public DIPair(int inferenceLine, String name, int declarationLine) {
		this.line = inferenceLine;
		this.declarationLine = declarationLine;
		this.name = name;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeclarationLine() {
		return declarationLine;
	}

	public void setDeclarationLine(int declarationLine) {
		this.declarationLine = declarationLine;
	}
	
	@Override
	public String toString(){
		return line + ": " + name + " -> " + declarationLine;
	}
}
