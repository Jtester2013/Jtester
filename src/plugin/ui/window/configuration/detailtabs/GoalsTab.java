package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class GoalsTab {

	private TabItem tbtmGoals;
	TabFolder subTabFolder;
	StaticInGoalsTab staticTabItem;
	ExecutionInGoalsTab executionTabItem;

	public GoalsTab(TabFolder tabFolder, int style) {
		tbtmGoals = new TabItem(tabFolder, SWT.NONE);
		tbtmGoals.setImage(SWTResourceManager.getImage(Const.GOALS_ICON_PATH));
		tbtmGoals.setText("Goals");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmGoals.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		subTabFolder = new TabFolder(compositeInScrolledComposite, SWT.None);
		FormData fd_subTabFolder = new FormData();
		fd_subTabFolder.left = new FormAttachment(0,5);
		fd_subTabFolder.right = new FormAttachment(100,-5);
		fd_subTabFolder.top = new FormAttachment(0,5);
		fd_subTabFolder.bottom = new FormAttachment(100,-5);
		subTabFolder.setLayoutData(fd_subTabFolder);
		staticTabItem = new StaticInGoalsTab(subTabFolder);
		executionTabItem = new ExecutionInGoalsTab(subTabFolder);
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
