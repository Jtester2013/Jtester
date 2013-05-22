package core.common.model.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestResult {
	private int totalFileNum;
	private int filesCheckNum;
	private String filePath;
	private String rule;
	
	private HashMap<String, ArrayList<TestResultItem>> result;
	private HashSet<String> violations; // Ontology rules violations

	public TestResult() {
		result = new HashMap<String, ArrayList<TestResultItem>>();
		violations = new HashSet<String>();
	}

	public void add(String filePath, TestResultItem item) {
		ArrayList<TestResultItem> list = result.get(filePath);
		if(list == null){
			list = new ArrayList<TestResultItem>();
		}

		list.add(item);
		result.put(filePath, list);
	}
	
	public ArrayList<TestResultItem> getFileTestResult(String filePath){
		return result.get(filePath);
	}
	
	public ArrayList<TestResultItem> getCurrentFileTestResult(){
		return result.get(filePath);
	}
	
	public HashMap<String, ArrayList<TestResultItem>> getResult(){
		return result;
	}
	
	public int getTotalFileNum(){
		return totalFileNum;
	}
	
	public int getFilesCheckNum(){
		return filesCheckNum;
	}
	
	public void increaseFileCheckNum(){
		filesCheckNum++;
	}
	
	public void setTotalFileNum(int num){
		totalFileNum = num;
	}
	
	public void setCurrentFilePath(String path){
		filePath = path;
	}
	
	public String getCurrentFilePath(){
		return filePath;
	}
	
	public void setCurrentRule(String name){
		rule = name;
	}
	public String getCurrentRule(){
		return rule;
	}
	public void addViolation(String name){
		violations.add(name);
	}
	public Set<String> getViolations(){
		return violations;
	}
}
