package core.jtester.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.common.model.jobflow.ICaller;
import core.common.model.jobflow.JobFlow;
import core.common.model.test.TestData;
import core.common.model.test.TestResult;

public class API {
	public static TestResult analyze(List<String> filePaths, List<String> rules, ICaller caller){
		if(filePaths == null || rules == null){
			System.err.println("Null filePaths or methods");
			return null;
		}
		
		TestData data = new TestData();
		
		try {
			data.accept(filePaths);
		} catch (IOException e) {
			System.err.println("Invalid filePaths");
		}
		
		try {
			data.getTestConfiguration().accept(rules);
		} catch (ClassNotFoundException e) {
			System.err.println("undefined testing rules");
		} catch (InstantiationException e) {
			System.err.println("rule can't create an instant :"+e);
		} catch (IllegalAccessException e) {
			System.err.println("rule can't be accessed :"+e);
		}
		
		JobFlow flow = new JobFlow(caller, data);
		flow.run(data);
		return data.getTestResult();
	}
	
	public static void main(String args[]) {
		List<String> paths = new ArrayList<String>();
//		paths.add("tested_files/ontology/Pet.java");
//		paths.add("tested_files/ontology/PetStore.java");
//		paths.add("tested_files/ontology/SimpleIteration.java");
//		
//		paths.add("tested_files/ontology/Syndrome.java");
//		paths.add("tested_files/ontology/People.java");
		paths.add("tested_files/ontology/Vulnerabilities.java");
		
		List<String> rules = new ArrayList<String>();
		rules.add(RuleSet.FUNCTION_INFO_VISITOR);
		rules.add(RuleSet.SEMANTICS_EXTRACTION);
		rules.add(RuleSet.REACHING_DEF);
		rules.add(RuleSet.SEMANTICS_ADAPTER);
		rules.add(RuleSet.ONTOLOGY_REASONER);
		
		API.analyze(paths, rules, null);
	}
}
