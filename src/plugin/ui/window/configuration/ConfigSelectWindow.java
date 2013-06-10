package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ConfigSelectWindow {
	public Composite configSelectComposite;
	public Composite treeComposite;
	public Composite actionComposite;
	ScrolledComposite scrolledComposite;
	Button btnNew, btnDelete;
	ConfigTree configTree;

	public ConfigSelectWindow(Composite parent, int style) {
		int tempValue = ConstantcLayoutData.botton_margin;
		configSelectComposite = new Composite(parent, style);
		configSelectComposite.setLayout(new FormLayout());
		// set actionComposite
		actionComposite = new Composite(configSelectComposite, SWT.NONE);
		actionComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_operationComposite = new FormData();
		fd_operationComposite.left = new FormAttachment(0);
		fd_operationComposite.right = new FormAttachment(100);
		fd_operationComposite.height = 30;
		fd_operationComposite.bottom = new FormAttachment(100);
		actionComposite.setLayoutData(fd_operationComposite);

		btnNew = new Button(actionComposite, SWT.NONE);
		btnNew.setText("New");

		btnDelete = new Button(actionComposite, SWT.NONE);
		btnDelete.setText("Delete");

		// set treeComposite
		scrolledComposite = new ScrolledComposite(configSelectComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.bottom = new FormAttachment(actionComposite);
		fd_scrolledComposite.right = new FormAttachment(100);
		fd_scrolledComposite.top = new FormAttachment(0);
		fd_scrolledComposite.left = new FormAttachment(0);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		// set the config item tree
		configTree = new ConfigTree(scrolledComposite, SWT.BORDER);
		configTree.contentCreate();
		scrolledComposite.setContent(configTree.tree);
		scrolledComposite.setMinSize(configTree.tree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
	}

}
