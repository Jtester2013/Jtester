package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class CodeReviewTab {

	private TabItem tbtmCodeReview;
	private Text text_Identifier;
	AuthorsTab authorsTab;
	ReviewersTab reviewersTab;
	MonitorsTab monitorsTab;
	FiltersTab filtersTab;
	
	public CodeReviewTab(TabFolder tabFolder, int style) {
		tbtmCodeReview = new TabItem(tabFolder, SWT.NONE);
		tbtmCodeReview.setImage(SWTResourceManager.getImage(Const.CODEREVIEW_ICON_PATH));
		tbtmCodeReview.setText("Code Review");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmCodeReview.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		Button btnEnableCodeReview = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnEnableCodeReview = new FormData();
		fd_btnEnableCodeReview.top = new FormAttachment(0, 10);
		fd_btnEnableCodeReview.left = new FormAttachment(0, 10);
		btnEnableCodeReview.setLayoutData(fd_btnEnableCodeReview);
		btnEnableCodeReview.setText("Enable code review scanner");
		
		Label lblIdentifier = new Label(compositeInScrolledComposite, SWT.NONE);
		FormData fd_lblIdentifier = new FormData();
		fd_lblIdentifier.top = new FormAttachment(btnEnableCodeReview, 6);
		fd_lblIdentifier.left = new FormAttachment(btnEnableCodeReview, 0, SWT.LEFT);
		lblIdentifier.setLayoutData(fd_lblIdentifier);
		lblIdentifier.setText("Identifier");
		
		Button btnUseUniqueuserhost = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnUseUniqueuserhost = new FormData();
		fd_btnUseUniqueuserhost.right = new FormAttachment(100, -5);
		fd_btnUseUniqueuserhost.bottom = new FormAttachment(lblIdentifier, 0, SWT.BOTTOM);
		btnUseUniqueuserhost.setLayoutData(fd_btnUseUniqueuserhost);
		btnUseUniqueuserhost.setText("use unique [user@host] pattern");
		
		text_Identifier = new Text(compositeInScrolledComposite, SWT.BORDER);
		FormData fd_text_Identifier = new FormData();
		fd_text_Identifier.left = new FormAttachment(lblIdentifier,3,SWT.RIGHT);
		fd_text_Identifier.right = new FormAttachment(btnUseUniqueuserhost,-3,SWT.LEFT);
		fd_text_Identifier.top = new FormAttachment(lblIdentifier, -2, SWT.TOP);
		text_Identifier.setLayoutData(fd_text_Identifier);
		
		Button btnGenerateComprehensiveReport = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnGenerateComprehensiveReport = new FormData();
		fd_btnGenerateComprehensiveReport.top = new FormAttachment(text_Identifier, 6);
		fd_btnGenerateComprehensiveReport.left = new FormAttachment(0, 10);
		btnGenerateComprehensiveReport.setLayoutData(fd_btnGenerateComprehensiveReport);
		btnGenerateComprehensiveReport.setText("Generate comprehensive report (includes all scanners)");
		
		Button btnAutoPublishReviews = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnAutoPublishReviews = new FormData();
		fd_btnAutoPublishReviews.top = new FormAttachment(btnGenerateComprehensiveReport, 6);
		fd_btnAutoPublishReviews.left = new FormAttachment(0, 10);
		btnAutoPublishReviews.setLayoutData(fd_btnAutoPublishReviews);
		btnAutoPublishReviews.setText("Auto publish reviews");
		
		TabFolder tabFolderCodeReviews = new TabFolder(compositeInScrolledComposite, SWT.NONE);
		FormData fd_tabFolderReviews = new FormData();
		fd_tabFolderReviews.top = new FormAttachment(btnAutoPublishReviews, 6);
		fd_tabFolderReviews.left = new FormAttachment(btnEnableCodeReview, 0, SWT.LEFT);
		fd_tabFolderReviews.right = new FormAttachment(100,-5);
		fd_tabFolderReviews.bottom = new FormAttachment(100,-5);
		tabFolderCodeReviews.setLayoutData(fd_tabFolderReviews);
		// add tabs into code review tab folder
		authorsTab = new AuthorsTab(tabFolderCodeReviews);
		reviewersTab = new ReviewersTab(tabFolderCodeReviews);
		monitorsTab = new MonitorsTab(tabFolderCodeReviews);
		filtersTab = new FiltersTab(tabFolderCodeReviews);
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
