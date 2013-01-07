package plugin.ui.window.configuration;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;

public class ConfigWindowComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ConfigWindowComposite(Composite parent, int style) {
		super(parent, style);
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setBounds(56, 48, 0, 0);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
