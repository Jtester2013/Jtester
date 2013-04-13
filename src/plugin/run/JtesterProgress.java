package plugin.run;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import plugin.util.ConsoleFactory;

import core.common.model.jobflow.ICaller;
import core.common.model.test.TestResult;
import core.common.model.test.TestResultItem;

public class JtesterProgress implements IRunnableWithProgress, ICaller{
	private boolean stop = false;
	private int worked = 0;
	private int lastTimeNum = 0;
	private boolean ontologyReasoner;
	
	private int progressSize;
	
	private List<ICaller> callers = new ArrayList<ICaller>();
	
	public JtesterProgress(int size, boolean reasoner) {
		progressSize = size;
		ontologyReasoner = reasoner;
	}
	
	public JtesterProgress(int size) {
		progressSize = size;
		ontologyReasoner = false;
	}

	public void run(IProgressMonitor monitor) {
		monitor.beginTask("开始执行......", progressSize);
		while(true){
			if (monitor.isCanceled()) // 随时监控是否选择了对话框的“取消”按钮
				return;// 中断处理
			try {
				if(!stop){
					if(worked > 0){
						monitor.worked(worked);// 进度条前进一步
						worked = 0;
					}
				}else{
					Thread.sleep(1000);
					continue;
				}
			} catch (Throwable t) {
			}
		}
	}
	
	static String lastRule="";
	static String lastFile="";
	public boolean update(TestResult result) {
		
		for(int i=0;i<callers.size();i++){
			if(!callers.get(i).update(result)){
				System.err.println("caller "+ callers.get(i).getClass() + " updates failed!");
				return false;
			}
		}
		
		if(result.getFilesCheckNum() > lastTimeNum){
			lastTimeNum = result.getFilesCheckNum();
			worked = 1;
		}
		
		// TODO 
		// use UI for information output instead of console
		if(lastFile != result.getCurrentFilePath()){
			lastFile = result.getCurrentFilePath();
		}
		
		if(lastRule != result.getCurrentRule()){
			lastRule = result.getCurrentRule();
		}
		
		ArrayList<TestResultItem> items = result.getResult().get(lastFile);
		
		if(items != null){
			if(!ontologyReasoner){
				ConsoleFactory.printToConsole("=======================\n");
				ConsoleFactory.printToConsole("path: " + lastFile + " \nrule: " + lastRule +" \ntype: "+ items.get(0).getType());
				ConsoleFactory.printToConsole("=======================\n");
			}
			
			for (int i = 0; i < items.size(); i++) {
				if (lastRule != items.get(i).getRule()) {
					if(!ontologyReasoner){
						ConsoleFactory.printToConsole("======\npath: " + items.get(i).getFilePath() + " \nrule: " + items.get(i).getRule() + " title: " + items.get(i).getType() + "\n====");
					}else if(items.get(i).getRule().equals("Ontology Reasoning")){
						ConsoleFactory.printToConsole("======\npath: " + items.get(i).getFilePath() + " \nrule: " + items.get(i).getRule() + " title: " + items.get(i).getType() + "\n====");
						ConsoleFactory.printToConsole(formatReport(items.get(i).getDetail()));
					}
					lastRule = items.get(i).getRule();
				}else if(ontologyReasoner){
					if(items.get(i).getRule().equals("Ontology Reasoning")){
						ConsoleFactory.printToConsole("======\npath: " + items.get(i).getFilePath() + " \nrule: " + items.get(i).getRule() + " title: " + items.get(i).getType() + "\n====");
						ConsoleFactory.printToConsole(formatReport(items.get(i).getDetail()));
					}
				}
				
				if(!ontologyReasoner){
					ConsoleFactory.printToConsole(formatReport(items.get(i).getDetail()));
				}
			}
		}
		
		return true;
	}	
	
	private String formatReport(List<String> report){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<report.size();i++){
			String item = report.get(i);
			formatReportHelper(sb, item);
		}
		return sb.toString();
	}
	
	private void formatReportHelper(StringBuilder sb, String item){
		if(item.length() < 3){
			sb.append(item);
			sb.append(createSpace(5-item.length()));
		} else if(item.length() < 15){
			sb.append(item);
			sb.append(createSpace(15-item.length()));
		} else{
			sb.append(item + createSpace(10));
		}
	}
	
	private String createSpace(int number){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<number;i++){
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public void stop(){
		stop = true;
	}

	public void register(ICaller caller) {
		callers.add(caller);
	}
}
