package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.jdom.Parent;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class ConfigDetailWindow{
	Composite showedComposite;
	NoneConfigSelectedComposite noneConfigSelected;
	OneConfigSelectedComposite oneConfigSelectedComposite;
	Composite selectedComposite;
	StackLayout detaiLayout;

	public ConfigDetailWindow(Composite parent, int style, String configName) {
		showedComposite = new Composite(parent, style);
		detaiLayout = new StackLayout();
		showedComposite.setLayout(detaiLayout);
		// generate the showing composite
		noneConfigSelected = new NoneConfigSelectedComposite(showedComposite, style);
		oneConfigSelectedComposite = new OneConfigSelectedComposite(showedComposite, style, configName);
		if (configName.equals("") || configName == null) {
			selectedComposite = noneConfigSelected;
		} else {// TO DO: select config name and load configuration from xml file
			// this is a mock
			selectedComposite = oneConfigSelectedComposite;
		}
		// this must be called when detail composite generated totally.
		detaiLayout.topControl = selectedComposite;
	}
	
	public Composite getComposite(){
		return showedComposite;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new ConfigDetailComposite(new Shell(), SWT.None, "");
	}
}
