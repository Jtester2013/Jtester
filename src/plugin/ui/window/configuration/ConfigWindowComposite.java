package plugin.ui.window.configuration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;

import plugin.util.LayoutFactory;

public class ConfigWindowComposite extends Composite {
	
	Composite titleComposite;
	Composite actionComposite;
	SashForm contentSashForm;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigWindowComposite(Composite parent, int style) {
		super(parent, style);
		this.setLayout(LayoutFactory.getFormLayout());
		titleComposite = new TitleComposite(this, SWT.NONE);
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
