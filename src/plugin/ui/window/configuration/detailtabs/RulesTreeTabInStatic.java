package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class RulesTreeTabInStatic {
	TabItem tabItem;
	Tree rulesTree;
	
	public RulesTreeTabInStatic(TabFolder tabFolder) {
		// add components into tabItem
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Rules Tree");
		Composite compositeRulesTree = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(compositeRulesTree);
		compositeRulesTree.setLayout(new FormLayout());

		Label lblNumberOfRules = new Label(compositeRulesTree, SWT.NONE);
		FormData fd_lblNumberOfRules = new FormData();
		fd_lblNumberOfRules.top = new FormAttachment(0, 5);
		fd_lblNumberOfRules.left = new FormAttachment(0, 5);
		lblNumberOfRules.setLayoutData(fd_lblNumberOfRules);
		lblNumberOfRules.setText("Number of total rules: ");

		Composite compositeActionBar = new Composite(compositeRulesTree, SWT.NONE);
		FillLayout fl_compositeActionBar = new FillLayout(SWT.VERTICAL);
		fl_compositeActionBar.spacing = 5;
		compositeActionBar.setLayout(fl_compositeActionBar);
		FormData fd_compositeActionBar = new FormData();
		fd_compositeActionBar.height = 200;
		fd_compositeActionBar.width = 90;
		fd_compositeActionBar.top = new FormAttachment(lblNumberOfRules, 5);
		fd_compositeActionBar.right = new FormAttachment(50, -5);// for change
		compositeActionBar.setLayoutData(fd_compositeActionBar);

		Button btnHideDisabled = new Button(compositeActionBar, SWT.NONE);
		btnHideDisabled.setText("Hide Disabled");

		Button btnShowAll = new Button(compositeActionBar, SWT.NONE);
		btnShowAll.setText("Show All");

		Button btnNew_1 = new Button(compositeActionBar, SWT.NONE);
		btnNew_1.setText("New");

		Button btnImport = new Button(compositeActionBar, SWT.NONE);
		btnImport.setText("Import");

		Button btnFind = new Button(compositeActionBar, SWT.NONE);
		btnFind.setText("Find");

		Button btnReload = new Button(compositeActionBar, SWT.NONE);
		btnReload.setText("Reload");

		Button btnEditRulemap = new Button(compositeActionBar, SWT.NONE);
		btnEditRulemap.setText("Edit Rulemap");

		Button btnPrintableDocs = new Button(compositeActionBar, SWT.NONE);
		btnPrintableDocs.setText("Printable Docs");

		ScrolledComposite scrolledCompositeRulesTree = new ScrolledComposite(compositeRulesTree, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledCompositeRulesTree = new FormData();
		fd_scrolledCompositeRulesTree.bottom = new FormAttachment(100, -5);
		fd_scrolledCompositeRulesTree.top = new FormAttachment(compositeActionBar, 0, SWT.TOP);
		fd_scrolledCompositeRulesTree.right = new FormAttachment(compositeActionBar, -5, SWT.LEFT);
		fd_scrolledCompositeRulesTree.left = new FormAttachment(0, 5);
		scrolledCompositeRulesTree.setLayoutData(fd_scrolledCompositeRulesTree);
		scrolledCompositeRulesTree.setExpandHorizontal(true);
		scrolledCompositeRulesTree.setExpandVertical(true);

		Tree rulesTree = new Tree(scrolledCompositeRulesTree, SWT.CHECK | SWT.MULTI);
		scrolledCompositeRulesTree.setContent(rulesTree);
		scrolledCompositeRulesTree.setMinSize(rulesTree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TreeItem item = new TreeItem(rulesTree, SWT.None);
		item.setText("Code Style");
		TreeItem item2 = new TreeItem(item, SWT.None);
		item2.setText("Find Duplicated Code");
		TreeItem item6 = new TreeItem(item, SWT.None);
		item6.setText("Find Unused Code");
		
		TreeItem item3 = new TreeItem(rulesTree, SWT.None);
		item3.setText("Static Vulnerability");
		TreeItem item4 = new TreeItem(item3, SWT.None);
		item4.setText("Find Null Point ");
		TreeItem item5 = new TreeItem(item3, SWT.None);
		item5.setText("Find Memory Problems ");
		
	}

}
