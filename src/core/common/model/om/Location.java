package core.common.model.om;

/**
 * 
 * @author Bin
 * 
 * Record constraint location in source code.
 *
 */
public class Location {
	private String fileName;
	private int lineNum;
	private int startPosition;
	private int length;
	
	public Location(String fileName, int lineNum){
		this.fileName = fileName;
		this.lineNum = lineNum;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getLineNum() {
		return lineNum;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}
}
