package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class BottomComposite extends Composite {

	public Button btnClose;
	public Button btnRunTest;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public BottomComposite(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FormLayout());
		FormData fd_bottomComposite = new FormData();
		fd_bottomComposite.bottom = new FormAttachment(100);
		fd_bottomComposite.height = 30;
		fd_bottomComposite.left = new FormAttachment(0);
		fd_bottomComposite.right = new FormAttachment(100);
		this.setLayoutData(fd_bottomComposite);

		btnClose = new Button(this, SWT.CENTER);
		btnClose.setAlignment(SWT.CENTER);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.width = 70;
		fd_btnNewButton.right = new FormAttachment(100);
		btnClose.setLayoutData(fd_btnNewButton);
		btnClose.setText("Close");

		btnRunTest = new Button(this, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(btnClose);
		fd_btnNewButton_1.width = 70;
		btnRunTest.setLayoutData(fd_btnNewButton_1);
		btnRunTest.setText("Run Test");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
