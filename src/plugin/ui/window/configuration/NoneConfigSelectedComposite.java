package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class NoneConfigSelectedComposite extends Composite {

	String noSelectedMessage = "Select a configuration to view it";

	public NoneConfigSelectedComposite(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		this.setLayout(new FillLayout());
		Label messageLabel = new Label(this, SWT.None);
		messageLabel.setText(noSelectedMessage);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NoneConfigSelectedComposite(new Shell(),SWT.None);
	}

}
