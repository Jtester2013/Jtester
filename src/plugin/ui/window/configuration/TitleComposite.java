package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class TitleComposite extends Composite {
	String titleString;
	public CLabel lblTitleImage;
	public CLabel lblTitle;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TitleComposite(Composite shell, int style) {
		super(shell, style);
		
		titleString = "Create, manage and run test configuration.";
		lblTitle = new CLabel(this, SWT.NONE);
		lblTitleImage = new CLabel(this, SWT.NONE);
		
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
		fl_composite.marginWidth = 5;
		fl_composite.marginHeight = 3;
		fl_composite.spacing = 5;
		this.setLayout(fl_composite);
		FormData fd_composite = new FormData();
		fd_composite.height = 60;
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		this.setLayoutData(fd_composite);

		// set top composite
		lblTitle.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitle.setText(titleString);
		
		lblTitleImage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitleImage.setAlignment(SWT.RIGHT);
		lblTitleImage.setImage(SWTResourceManager.getImage(Const.CONFIGURATION_ICON_PATH));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
