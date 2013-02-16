package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ReviewersTab {
	TabItem tabItem;
	private Table table;

	public ReviewersTab(TabFolder tabFolder) {
		// TODO Auto-generated constructor stub
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Reviewers");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeAuthors = new Composite(scrolledComposite, SWT.NONE);
		compositeAuthors.setLayout(new FormLayout());
		
		Composite actionsCompositeAuthors = new Composite(compositeAuthors, SWT.NONE);
		actionsCompositeAuthors.setLayout(new FillLayout(SWT.VERTICAL));
		FormData fd_actionsCompositeAuthors = new FormData();
		fd_actionsCompositeAuthors.right = new FormAttachment(100, -5);
		fd_actionsCompositeAuthors.top = new FormAttachment(0, 5);
		fd_actionsCompositeAuthors.width = 60;
		actionsCompositeAuthors.setLayoutData(fd_actionsCompositeAuthors);
		
		Button btnEdit_1 = new Button(actionsCompositeAuthors, SWT.NONE);
		btnEdit_1.setText("Edit");
		
		Button btnNewAuthor = new Button(actionsCompositeAuthors, SWT.NONE);
		btnNewAuthor.setText("New");
		
		Button btnRemove = new Button(actionsCompositeAuthors, SWT.NONE);
		btnRemove.setText("Remove");
		
		Composite tableCompositeAuthors = new Composite(compositeAuthors, SWT.NONE);
		tableCompositeAuthors.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_tableCompositeAuthors = new FormData();
		fd_tableCompositeAuthors.bottom = new FormAttachment(100, -5);
		fd_tableCompositeAuthors.top = new FormAttachment(0, 5);
		fd_tableCompositeAuthors.right = new FormAttachment(actionsCompositeAuthors,-3,SWT.LEFT);
		fd_tableCompositeAuthors.left = new FormAttachment(0, 5);
		tableCompositeAuthors.setLayoutData(fd_tableCompositeAuthors);
		
		table = new Table(tableCompositeAuthors, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
		
		TableColumn tblclmnReviewers = new TableColumn(table, SWT.NONE);
		tblclmnReviewers.setWidth(100);
		tblclmnReviewers.setText("Reviewed Authors");
		
		TableColumn tblclmnMonitors = new TableColumn(table, SWT.NONE);
		tblclmnMonitors.setWidth(100);
		tblclmnMonitors.setText("Reviewed Paths");
		
		scrolledComposite.setContent(compositeAuthors);
		scrolledComposite.setMinSize(compositeAuthors.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}