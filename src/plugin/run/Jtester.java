package plugin.run;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import core.jtester.api.RuleSet;

import plugin.ui.dialog.progress.ProgressDialog;
import plugin.ui.window.configuration.ConfigurationWindow;
import plugin.util.Const;
import plugin.util.IOUtil;

public class Jtester implements IWorkbenchWindowActionDelegate{
	private ISelection selection;

	public void run(IAction action) {
		String id = action.getId();
		switch(id){
		case Const.JTESTER_ALL:
			runAll(false);
			break;
		case Const.JTESTER_CONFIGURATION:
			runConfiguration();
			break;
		case Const.JTESTER_ONTOLOGY:
			runAll(true);
		default:
			List<String> rules = new ArrayList<String>();
			rules.add(RuleSet.FUNCTION_INFO_VISITOR);
			rules.add(id);
			run(rules);
			break;
		}
	}

	private void run(List<String> rules) {
		List<String> filePaths = IOUtil.getSelectionPath(selection);
		JtesterProgress progress = new JtesterProgress(filePaths.size());
		
		JtesterCaller caller = new JtesterCaller(filePaths, rules, progress);
		caller.start();
		
		ProgressDialog dialog = new ProgressDialog(null, progress);
		progress.register(dialog);
		dialog.run();
	}

	public void runAll(boolean ontologyReasoner){
		List<String> filePaths = IOUtil.getSelectionPath(selection);
		List<String> rules = getAllRules(ontologyReasoner);
		
		JtesterProgress progress = new JtesterProgress(filePaths.size(), ontologyReasoner);
		
		JtesterCaller caller = new JtesterCaller(filePaths, rules, progress);
		caller.start();
		
		ProgressDialog dialog = new ProgressDialog(null, progress);
		progress.register(dialog);
		dialog.run();
	}
	
	public void runConfiguration(){
		ConfigurationWindow cfgWindow = new ConfigurationWindow();
		cfgWindow.open();
	}
	
	private List<String> getAllRules(boolean ontologyReason){
		List<String> rules = new ArrayList<String>();
		rules.add(RuleSet.FUNCTION_INFO_VISITOR);
		rules.add(RuleSet.AVAILABLE_EXP);
		rules.add(RuleSet.VERY_BUSY_EXP);
		rules.add(RuleSet.REACHING_DEF);
		rules.add(RuleSet.LIVE_VAR);
		rules.add(RuleSet.CONST_PROPAGATION);
		rules.add(RuleSet.SHAPE_ANALYSIS);
		if(ontologyReason){
			rules.add(RuleSet.ONTOLOGY_REASONER);
		}
		return rules;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		 this.selection = selection;
	}
	
	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
	}
}
