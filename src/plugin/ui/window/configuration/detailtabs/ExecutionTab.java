package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class ExecutionTab {

	private TabItem tbtmExecution;

	public ExecutionTab(TabFolder tabFolder, int style) {
		tbtmExecution = new TabItem(tabFolder, SWT.NONE);
		tbtmExecution.setImage(SWTResourceManager.getImage(Const.EXECUTION_ICON_PATH));
		tbtmExecution.setText("Execution");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmExecution.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		
		
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

}
