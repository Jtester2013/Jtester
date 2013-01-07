package plugin.run;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import plugin.ui.window.configuration.ConfigurationWindow;

public class Configuration implements IWorkbenchWindowActionDelegate{
	private IWorkbenchWindow window;
	
	public void run(IAction arg0) {
		ConfigurationWindow cfgWindow = new ConfigurationWindow();
		cfgWindow.open();
	}

	public void selectionChanged(IAction arg0, ISelection arg1) {
	}

	// 下面的方法是 IWorkbenchWindowActionDelegate 实现的
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}
