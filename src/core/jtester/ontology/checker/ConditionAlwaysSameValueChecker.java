package core.jtester.ontology.checker;

import java.util.List;

import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.model.functionblock.ConditionExpression;
import core.common.model.jobflow.JobConst;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.jtester.ontology.reasoner.IChecker;

public class ConditionAlwaysSameValueChecker implements IChecker{

	public void check(TestData data) {
		TestFile file = data.getCurrentTestFile();
		List<JavaControlFlowGraph> cfgs = (List<JavaControlFlowGraph>) file.get(JobConst.CONTROL_FLOW_GRAPH);
		
		if(generateReport(cfgs)){
			data.getTestResult().addViolation(JobConst.ONTOLOGY_CONDITION_ALWAYS_SAME_VALUE);
		}
	}
	
	private boolean generateReport(List<JavaControlFlowGraph> cfgs){
		boolean report = false;
		
		boolean tipped = false;
		for(JavaControlFlowGraph cfg: cfgs){
			List<ConditionExpression> conditions = cfg.getConditions();
			if(conditions != null && !conditions.isEmpty()){
				for(ConditionExpression ce: conditions){
					if(tipped == false){
						tipped = true;
						System.err.println("Warning: 条件值始终不变！");
					}
					System.err.println("\t" + ce + " \t");
					if(report == false){
						report = true;
					}
				}
			}
		}
		
		return report;
	}
}
