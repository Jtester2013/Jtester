package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class TitleConfigWindow {
	public static final String TITLE_STRING = "Create, manage and run test configuration.";
	public CLabel lblTitleImage;
	public CLabel lblTitle;
	public Composite titleComposite;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public TitleConfigWindow(Composite shell, int style) {
		titleComposite = new Composite(shell, style);
		int tempValue = ConstantcLayoutData.botton_margin;
		lblTitle = new CLabel(titleComposite, SWT.NONE);
		lblTitleImage = new CLabel(titleComposite, SWT.NONE);

		titleComposite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
		fl_composite.marginWidth = 5;
		fl_composite.marginHeight = 3;
		fl_composite.spacing = 5;
		titleComposite.setLayout(fl_composite);
		FormData fd_composite = new FormData();
		fd_composite.height = 60;
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		titleComposite.setLayoutData(fd_composite);

		// set top composite
		lblTitle.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		lblTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitle.setText(TITLE_STRING);

		lblTitleImage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitleImage.setAlignment(SWT.RIGHT);
		lblTitleImage.setImage(SWTResourceManager.getImage(Const.CONFIGURATION_ICON_PATH));
	}

	public Composite getComposite() {
		return titleComposite;
	}
}
