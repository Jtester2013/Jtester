package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class DetailTabFolder {
	public TabFolder tabFolder;
	
	ScopeTab scopeTab;
	StaticTab staticTab;
	GenerationTab generationTab;
	ExecutionTab executionTab;
	CommonTab commonTab;
	CodeReviewTab codeReviewTab;
	GoalsTab goalsTab;
	
	public DetailTabFolder(Composite parent, int style) {
		// TODO Auto-generated constructor stub
		tabFolder = new TabFolder(parent, style);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -35);
		fd_tabFolder.top = new FormAttachment(0, 70);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.left = new FormAttachment(0, 5);
		tabFolder.setLayoutData(fd_tabFolder);
		// add tab items
		scopeTab = new ScopeTab(tabFolder, SWT.None);
		staticTab = new StaticTab(tabFolder, SWT.None);
		generationTab = new GenerationTab(tabFolder, SWT.None);
		executionTab = new ExecutionTab(tabFolder, SWT.None);
		commonTab = new CommonTab(tabFolder, SWT.None);
		codeReviewTab = new CodeReviewTab(tabFolder, SWT.None);
		goalsTab = new GoalsTab(tabFolder, SWT.None);
	}
	
	TabFolder getTabFolder(){
		return tabFolder;
	}

}
