package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class GeneralInOptionsTab {
	TabItem tabItem;
	public GeneralInOptionsTab(TabFolder tabFolder){
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("General");
		
	}
}
