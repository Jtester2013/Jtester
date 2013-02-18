package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class ExecutionTab {

	private TabItem tbtmExecution;
	private TabFolder tabFolder;
	SearchInExecutionTab searchInExecutionTab;
	OptionsInExecutionTab optionsInExecutionTab;
	SeveritiesInExecutionTab severitiesInExecutionTab;

	public ExecutionTab(TabFolder tabFolder, int style) {
		tbtmExecution = new TabItem(tabFolder, SWT.NONE);
		tbtmExecution.setImage(SWTResourceManager.getImage(Const.EXECUTION_ICON_PATH));
		tbtmExecution.setText("Execution");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmExecution.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		
		Button btnEnableTestExecution = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnEnableTestExecution = new FormData();
		fd_btnEnableTestExecution.top = new FormAttachment(0);
		fd_btnEnableTestExecution.left = new FormAttachment(0);
		btnEnableTestExecution.setLayoutData(fd_btnEnableTestExecution);
		btnEnableTestExecution.setText("Enable Test Execution");
		
		tabFolder = new TabFolder(compositeInScrolledComposite, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -5);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(btnEnableTestExecution, 3);
		fd_tabFolder.left = new FormAttachment(btnEnableTestExecution, 5, SWT.LEFT);
		tabFolder.setLayoutData(fd_tabFolder);
		
		searchInExecutionTab = new SearchInExecutionTab(tabFolder);
		optionsInExecutionTab = new OptionsInExecutionTab(tabFolder);
		severitiesInExecutionTab = new SeveritiesInExecutionTab(tabFolder);
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

}
