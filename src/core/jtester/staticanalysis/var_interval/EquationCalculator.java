package core.jtester.staticanalysis.var_interval;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.model.functionblock.AnalysisBlock;
import core.common.model.functionblock.FunctionInfo;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.model.test.TestResult;
import core.common.model.test.TestResultItem;
import core.common.model.varinterval.IntegerInterval;
import core.common.model.varinterval.VarInterval;
import core.jtester.api.RuleSet;

public class EquationCalculator implements IJob{
	protected String name = this.getClass().getName();
	
	private HashMap<String, FunctionInfo> functionsInfo;
	
	private HashMap<String, List<Equation>> equationMap = new HashMap<String, List<Equation>>();
	private HashMap<String, List<ASTNode>> argumentsMap = new HashMap<String, List<ASTNode>>();
	private HashMap<String, HashMap<String, Environment>> environmentMap = new HashMap<String, HashMap<String,Environment>>();
	
	private ArrayList<Equation> worklist;
	
	protected void initAnalysisBlocks(String key){
		FunctionInfo functionInfo = functionsInfo.get(key);
		List<ASTNode> arguments = functionInfo.getContextInfo().getCallerArguments();
		
		ArrayList<AnalysisBlock> analysisBlocks = initAnalysisBlocksHelper(functionInfo);
		ArrayList<Equation> equations = calculateEquation(analysisBlocks);
		
		equationMap.put(key, equations);
		argumentsMap.put(key, arguments);
		
		// 初始化 Environment
		HashMap<String, Environment> environments = new HashMap<String, Environment>();
		environments.put("0", new Environment("0", new ArrayList<VarInterval>()));
		for(int i=0;i< equations.size();i++){
			String env_key = equations.get(i).getOutLabel() + "";
			ArrayList<VarInterval> intervals = new ArrayList<VarInterval>();
			environments.put(env_key, new Environment(env_key,intervals));
		}
		if(arguments != null && arguments.size() > 0){
			generateParams(arguments,environments);
		}
		environmentMap.put(key, environments);
		
//		// TODO
//		// delete the following print information
//		// this is only for debug
//		System.out.println("arguments: " + arguments);
//		for(int i=0;i< equations.size();i++){
//			System.out.println("equations: " + equations.get(i).getInLabels() 
//					+ " rule: "+ equations.get(i).getRule()
//					+ " out: " + equations.get(i).getOutLabel());
//		}
//		System.out.println("environments: " + environments);
	} 
	
	private void generateParams(List<ASTNode> arguments, HashMap<String, Environment> environments) {
		for(ASTNode node: arguments){
			if(node instanceof SingleVariableDeclaration){
				SingleVariableDeclaration declaration = (SingleVariableDeclaration)node;
				IntegerInterval default_interval = new IntegerInterval(Integer.MIN_VALUE,Integer.MAX_VALUE);
				VarInterval v= new VarInterval(declaration.getName().toString(), default_interval);
				environments.get("0").getEnv().add(v);
			}
		}
	}

	private ArrayList<Equation> calculateEquation(ArrayList<AnalysisBlock> analysisBlocks) {
		ArrayList<Equation> equations = new ArrayList<Equation>();
		
		for (int i = 0; i < analysisBlocks.size(); i++) {
			EquationBlock equationBlock = (EquationBlock) analysisBlocks.get(i);

			ArrayList<Integer> inLabels = new ArrayList<Integer>();
			for (int j = 0; j < equationBlock.getIncoming().size(); j++) {
				inLabels.add(equationBlock.getIncoming().get(j).getLable());
			}

			for (int j = 0; j < equationBlock.getOutgoing().size(); j++) {
				Equation equation =new Equation();
				equation.setInLabels(inLabels);
				equation.setLabel(equationBlock.getLable());
				equation.setOutLabel((equationBlock.getOutgoing().get(j).getLable()));
				equation.setRule((ASTNode)((AbstractBasicBlock)(equationBlock.getBasicBlock())).getData());
				equation.setCondition(equationBlock.getBasicBlock());
				equations.add(equation);
				//System.out.println("equation: " + equation);
			}
		}
		return equations;
	}

	private ArrayList<AnalysisBlock> initAnalysisBlocksHelper(FunctionInfo functionInfo){
		JavaControlFlowGraph cfg = functionInfo.getJavaControlFlowGraph();
		Collection<IBasicBlock> nodes = cfg.getNodes();
		Iterator<IBasicBlock> itr = nodes.iterator();

		ArrayList<AnalysisBlock> analysisBlocks =  new ArrayList<AnalysisBlock>();
		int i = 0;
		while (itr.hasNext()) {
			IBasicBlock bb = itr.next();
			EquationBlock block = new EquationBlock(bb, i++);
			analysisBlocks.add(block);
		}
		
		// 计算每一个AnalysisBlock的_incoming 和 _outgoing
		for (i = 0; i < analysisBlocks.size(); i++) {
			IBasicBlock[] incoming = analysisBlocks.get(i).getBasicBlock().getIncomingNodes();
			IBasicBlock[] outgoing = analysisBlocks.get(i).getBasicBlock().getOutgoingNodes();
			// 加_incoming
			for (int m = 0; m < incoming.length; m++) {
				for (int n = 0; n < analysisBlocks.size(); n++) {
					if (analysisBlocks.get(n).getBasicBlock().equals(incoming[m])) {
						analysisBlocks.get(i).addIncoming(analysisBlocks.get(n));
					}
				}
			}

			// 加_outgoing
			for (int m = 0; m < outgoing.length; m++) {
				for (int n = 0; n < analysisBlocks.size(); n++) {
					if (analysisBlocks.get(n).getBasicBlock().equals(outgoing[m])) {
						analysisBlocks.get(i).addOutgoing(analysisBlocks.get(n));
					}
				}
			}
		}
		
		return analysisBlocks;
	}

	public String getName() {
		return name;
	}
	
	protected void initWorklist(String key) {
		worklist = new ArrayList<Equation>();
		ArrayList<Equation> flow_content = (ArrayList<Equation>) equationMap.get(key);
		for(int i=0;i< flow_content.size();i++){
			Equation equation =new Equation();
			equation.setInLabels(flow_content.get(i).getInLabels());
			equation.setOutLabel(flow_content.get(i).getOutLabel());
			equation.setLabel(flow_content.get(i).getLabel());
			equation.setRule(flow_content.get(i).getRule());
			equation.setCondition(flow_content.get(i).getCondition());
			worklist.add(equation);
		}
	}

	protected void calculateSolution(String key, int flag) {
		HashMap<String, Environment> env = environmentMap.get(key);
		ArrayList<Equation> flow_content = (ArrayList<Equation>) equationMap.get(key);
		
		//flag =0正常执行, flag=1 加宽 , flag =2 收缩
		//WorkList算法
		//worklist是一个ArrayList<Equation>对象，在前面已经初始化
		while(worklist.size()!=0){
			Equation equation = worklist.get(0);
			String outEnvKey = worklist.get(0).getOutLabel() + "";
			ArrayList<VarInterval> oldEnv = env.get(outEnvKey).getEnv();
		
			transfer(equation, env, flag);
			
			ArrayList<VarInterval> newEnv = env.get(outEnvKey).getEnv();
			//计算完成后移除参与计算的Equation
			worklist.remove(0);
			if(!oldEnv.equals(newEnv)){
				//如果得到的新的环境信息跟上次的不相等，则将依赖于信环境的其他的Equation加入到worklist
				for(int i=0;i< flow_content.size();i++){
					for(int j=0;j< flow_content.get(i).getInLabels().size();j++){
						if(outEnvKey == flow_content.get(i).getInLabels().get(j) + "" ){
							worklist.add(flow_content.get(i));
							break;
						}
							
					}
				}
			}
		}
	}
	
	
	protected void displaySolution(String key, TestData data){
		TestResult result = data.getTestResult();
		HashMap<String, Environment> env = environmentMap.get(key);
		ArrayList<Equation> flow_content = (ArrayList<Equation>) equationMap.get(key);
		
		for(int i=0;i< flow_content.size();i++){
			if(flow_content.get(i).getRule() == null){
				continue;
			}
			
			String src= "";
			String condition =  "";
			String variables="";
			
			condition = flow_content.get(i).getCondition() == null ? "" : flow_content.get(i).getCondition();
			src = flow_content.get(i).getRule() == null ? "" : flow_content.get(i).getRule()+"";
			variables = env.get(flow_content.get(i).getOutLabel() + "") + "";
			
			TestResultItem item = new TestResultItem(data.getCurrentTestFile().getPath(), getName(), RuleSet.RESULT);
			//item.add(condition);
			//item.add(src);
			item.add(variables);
			result.add(data.getCurrentTestFile().getPath(), item);
		}
	}
	
	private void transfer(Equation rule,HashMap<String,Environment> env,int flag) {
		rule.transfer(env,flag);
	}
	
	public boolean run(TestData data) {
		TestFile file = data.getCurrentTestFile();
		functionsInfo = (HashMap<String, FunctionInfo>) file.get(JobConst.FUNCTIONSINFO);
		Set<String> keys = functionsInfo.keySet();
		for (String key : keys) {
			// for each method
			initAnalysisBlocks(key);
			initWorklist(key);
			calculateSolution(key, 0);
			displaySolution(key, data);
		}
		return true;
	}
}
