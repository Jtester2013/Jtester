package plugin.ui.window.configuration;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;

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

}
