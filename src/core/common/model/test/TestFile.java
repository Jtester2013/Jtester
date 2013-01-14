package core.common.model.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.common.util.IOUtil;

public class TestFile {
	private String path;
	private String code;
	
	private HashMap<String, Object> attachments;
	private List<String> rulesChecked;

	public TestFile(String path) {
		this.path = path;
		this.code = "";
		attachments = new HashMap<String, Object>();
		rulesChecked = new ArrayList<String>();
	}

	public void accept(String path) throws IOException{
		this.code = IOUtil.read(path);
	}
	
	public Object get(String key){
		return attachments.get(key);
	}
	
	public String getPath(){
		return path;
	}
	
	public void put(String key, Object value){
		attachments.put(key, value);
	}
	
	public String getCode(){
		return code;
	}
	
	public boolean isCheckedByRule(String ruleName){
		if(rulesChecked.contains(ruleName)){
			return true;
		}
		return false;
	}

	public void setCheckedByRule(String name) {
		rulesChecked.add(name);
	}
}