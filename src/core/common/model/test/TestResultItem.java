package core.common.model.test;

import java.util.ArrayList;
import java.util.List;

public class TestResultItem {
	private String filePath;
	private String rule;
	private String type;
	
	private boolean isPrinted;

	private List<String> contents;
	
	public TestResultItem(String filePath, String rule, String type) {
		this.filePath = filePath;
		this.rule = rule;
		this.type = type;
		contents = new ArrayList<String>();
	}

	public String getFilePath() {
		return filePath;
	}

	public String getRule() {
		return rule;
	}

	public String getType() {
		return type;
	}
	
	public void add(String detail){
		contents.add(detail);
	}
	
	public List<String> getDetail(){
		return contents;
	}
	
	public boolean isPrinted(){
		if(isPrinted == false){
			isPrinted = true;
			return false;
		}
		return true;
	}
}