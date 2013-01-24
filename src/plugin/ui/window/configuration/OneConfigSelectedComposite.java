package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import plugin.ui.window.configuration.detailtabs.DetailTabFolder;
import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class OneConfigSelectedComposite extends Composite {
	Composite nameparentComposite;
	Composite rightActionComposite;
	DetailTabFolder detailTabFolder;

	private Text textName;
	private Text textParent;
	private Label lblName;
	private Label lblParent;
	private Button btnDisconnect;
	private Button btnApply;
	private Button btnRevert;
	// for analysis tabs
	private TabFolder tabFolder;

	public OneConfigSelectedComposite(Composite parent, int style, String configName) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		// when a configuration selected, this composite will appear in the
		// detail composite
		// otherwise the message "Select a configuration to view it." will
		// appear
		// Composite oneConfigSelectedComposite = new Composite(this, SWT.NONE);
		this.setLayout(new FormLayout());
		setNameAndParentComposite();
		setBottemComposite();
		// set detail configuration page
		detailTabFolder = new DetailTabFolder(this, SWT.None);
		tabFolder = detailTabFolder.tabFolder;
	}
	
	private void setNameAndParentComposite() {
		// set the composite contains name and parent components
		nameparentComposite = new Composite(this, SWT.NONE);
		FormData fd_nameparentComposite = new FormData();
		fd_nameparentComposite.height = 65;
		fd_nameparentComposite.right = new FormAttachment(100);
		fd_nameparentComposite.left = new FormAttachment(0);
		nameparentComposite.setLayoutData(fd_nameparentComposite);
		nameparentComposite.setLayout(new FormLayout());

		lblName = new Label(nameparentComposite, SWT.CENTER);
		lblName.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		FormData fd_lblName = new FormData();
		fd_lblName.width = 60;
		fd_lblName.height = 25;
		fd_lblName.top = new FormAttachment(0, 5);
		fd_lblName.left = new FormAttachment(0);
		lblName.setLayoutData(fd_lblName);
		lblName.setText("Name");

		lblParent = new Label(nameparentComposite, SWT.CENTER);
		lblParent.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		FormData fd_lblParent = new FormData();
		fd_lblParent.width = 60;
		fd_lblParent.height = 25;
		fd_lblParent.top = new FormAttachment(0, 35);
		lblParent.setLayoutData(fd_lblParent);
		lblParent.setText("Parent");

		textName = new Text(nameparentComposite, SWT.BORDER);
		FormData fd_textName = new FormData();
		fd_textName.right = new FormAttachment(100, -5);
		fd_textName.top = new FormAttachment(lblName, 0, SWT.TOP);
		fd_textName.left = new FormAttachment(lblName, 6);
		textName.setLayoutData(fd_textName);

		textParent = new Text(nameparentComposite, SWT.BORDER);
		FormData fd_textParent = new FormData();
		fd_textParent.right = new FormAttachment(100, -80);
		fd_textParent.top = new FormAttachment(textName, 6);
		fd_textParent.left = new FormAttachment(textName, 0, SWT.LEFT);
		textParent.setLayoutData(fd_textParent);

		btnDisconnect = new Button(nameparentComposite, SWT.NONE);
		FormData fd_btnDisconnect = new FormData();
		fd_btnDisconnect.bottom = new FormAttachment(100, -7);
		fd_btnDisconnect.right = new FormAttachment(100, -5);
		fd_btnDisconnect.width = 70;
		fd_btnDisconnect.height = 25;
		fd_btnDisconnect.top = new FormAttachment(0, 30);
		btnDisconnect.setLayoutData(fd_btnDisconnect);
		btnDisconnect.setText("Disconnect");
	}

	private void setBottemComposite() {
		// set action composite in detail composite
		{
			rightActionComposite = new Composite(this, SWT.NONE);
			rightActionComposite.setLayout(new FormLayout());
			FormData fd_composite_2 = new FormData();
			fd_composite_2.right = new FormAttachment(100);
			fd_composite_2.bottom = new FormAttachment(100);
			fd_composite_2.height = 30;
			fd_composite_2.left = new FormAttachment(0, 6);
			rightActionComposite.setLayoutData(fd_composite_2);

			btnApply = new Button(rightActionComposite, SWT.NONE);
			FormData fd_btnApply = new FormData();
			fd_btnApply.width = 60;
			fd_btnApply.right = new FormAttachment(100, -5);
			btnApply.setLayoutData(fd_btnApply);
			btnApply.setText("Apply");

			btnRevert = new Button(rightActionComposite, SWT.NONE);
			FormData fd_btnRevert = new FormData();
			fd_btnRevert.right = new FormAttachment(btnApply);
			fd_btnRevert.width = 60;
			btnRevert.setLayoutData(fd_btnRevert);
			btnRevert.setText("Revert");
		}
	}

}
