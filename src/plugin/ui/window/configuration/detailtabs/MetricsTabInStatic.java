package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class MetricsTabInStatic {

	TabItem tabItem;

	public MetricsTabInStatic(TabFolder tabFolder) {
		// add components into tabItem
		tabItem = new TabItem(tabFolder, SWT.None);
		
	}

}
