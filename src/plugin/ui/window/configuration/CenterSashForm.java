package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;

public class CenterSashForm extends SashForm {
	
	public Composite leftComposite;
	public Composite rightComposite;

	public CenterSashForm(Composite shell, int style) {
		super(shell, style);
		// TODO Auto-generated constructor stub
		leftComposite = new Composite(this, SWT.NONE);
		rightComposite = new Composite(this, SWT.NONE);
		this.setWeights(new int[] { 3, 7 });
	}

}
