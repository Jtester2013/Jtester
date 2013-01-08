package plugin.ui.window.configuration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;

import plugin.util.LayoutFactory;

public class ConfigWindowComposite extends Composite {
	
	TitleComposite titleComposite;
	ActionComposite actionComposite;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigWindowComposite(Composite parent, int style) {
		super(parent, style);
		this.setLayout(LayoutFactory.getFormLayout());
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
