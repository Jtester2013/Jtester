package core.common.model.semantics;

import java.util.HashMap;
import java.util.Map;

public class ViolationAxiom {
	private String name;
	private String context;
	private String ruleName;
	
	private Map<String,String> dataProperties;
	private Map<String,String> objectProperties;
	
	public ViolationAxiom(String ruleName){
		this();
		this.ruleName = ruleName;
	}
	
	public ViolationAxiom(){
		dataProperties = new HashMap<String, String>();
		objectProperties = new HashMap<String, String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Map<String, String> getDataProperties() {
		return dataProperties;
	}

	public Map<String, String> getObjectProperties() {
		return objectProperties;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	public void addDataProperty(String key, String value){
		this.dataProperties.put(key, value);
	}
	
	public void addObjectProperty(String key, String value){
		this.objectProperties.put(key, value);
	}
}
