package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class StaticInnerTabFolder {
	TabFolder tabFolder;
	RulesTreeTabInStatic rulesTreeTabInStatic;
	MetricsTabInStatic metricsTabInStatic;
	OptionsTabInStatic optionsTabInStatic;
	
	public StaticInnerTabFolder(Composite parent) {
		// TODO Auto-generated constructor stub
		tabFolder = new TabFolder(parent, SWT.None);
		// set tabFolder's layout
		
		// create the tabs in static's inner TabFolder
		rulesTreeTabInStatic = new RulesTreeTabInStatic(tabFolder);
		metricsTabInStatic = new MetricsTabInStatic(tabFolder);
		optionsTabInStatic = new OptionsTabInStatic(tabFolder);
	}
}
