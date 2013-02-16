package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class FiltersTab {
	TabItem tabItem;
	private Text text_filesAccept;
	private Text text_filesReject;
	private Text text_pathsAccept;
	private Text text_pathsReject;
	private Text text_acceptPostToPrecommitAccept;
	
	public FiltersTab(TabFolder tabFolder) {
		// TODO Auto-generated constructor stub
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Filters");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());

		Button btnIncludeFilesAdded = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnIncludeFilesAdded = new FormData();
		fd_btnIncludeFilesAdded.top = new FormAttachment(0, 10);
		fd_btnIncludeFilesAdded.left = new FormAttachment(0, 10);
		btnIncludeFilesAdded.setLayoutData(fd_btnIncludeFilesAdded);
		btnIncludeFilesAdded.setText("Include files added or modified locally");
		// set the group: Files (wildcards)
		Group grpFiles = new Group(compositeInScrolledComposite, SWT.NONE);
		grpFiles.setText("Files (wildcards)");
		grpFiles.setLayout(new FormLayout());
		FormData fd_grpFileswildcards = new FormData();
		fd_grpFileswildcards.right = new FormAttachment(100, -5);
		fd_grpFileswildcards.top = new FormAttachment(btnIncludeFilesAdded, 6);
		fd_grpFileswildcards.left = new FormAttachment(btnIncludeFilesAdded, 0, SWT.LEFT);
		grpFiles.setLayoutData(fd_grpFileswildcards);

		Label lblAcceptFiles = new Label(grpFiles, SWT.NONE);
		FormData fd_lblAccept = new FormData();
		fd_lblAccept.left = new FormAttachment(0, 5);
		lblAcceptFiles.setLayoutData(fd_lblAccept);
		lblAcceptFiles.setText("Accept");

		text_filesAccept = new Text(grpFiles, SWT.BORDER);
		FormData fd_text_filesAccept = new FormData();
		fd_text_filesAccept.right = new FormAttachment(100, -5);
		fd_text_filesAccept.left = new FormAttachment(lblAcceptFiles, 5);
		text_filesAccept.setLayoutData(fd_text_filesAccept);

		Label lblReject = new Label(grpFiles, SWT.NONE);
		FormData fd_lblReject = new FormData();
		fd_lblReject.top = new FormAttachment(lblAcceptFiles, 10, SWT.BOTTOM);
		fd_lblReject.left = new FormAttachment(lblAcceptFiles, 0, SWT.LEFT);
		lblReject.setLayoutData(fd_lblReject);
		lblReject.setText("Reject");

		text_filesReject = new Text(grpFiles, SWT.BORDER);
		FormData fd_text_filesReject = new FormData();
		fd_text_filesReject.right = new FormAttachment(100, -5);
		fd_text_filesReject.left = new FormAttachment(lblReject, 5);
		fd_text_filesReject.top = new FormAttachment(lblReject, 0, SWT.TOP);
		text_filesReject.setLayoutData(fd_text_filesReject);
		// set the group: Paths (regular expressions)
		Group grpPath = new Group(compositeInScrolledComposite, SWT.None);
		grpPath.setText("Paths (regular expressions)");
		grpPath.setLayout(new FormLayout());
		FormData fd_grpPath = new FormData();
		fd_grpPath.right = new FormAttachment(100, -5);
		fd_grpPath.top = new FormAttachment(grpFiles, 5, SWT.BOTTOM);
		fd_grpPath.left = new FormAttachment(grpFiles, 0, SWT.LEFT);
		grpPath.setLayoutData(fd_grpPath);
		
		Label lblAcceptPaths = new Label(grpPath, SWT.NONE);
		FormData fd_lblAcceptPath = new FormData();
		fd_lblAcceptPath.left = new FormAttachment(0, 5);
		lblAcceptPaths.setLayoutData(fd_lblAcceptPath);
		lblAcceptPaths.setText("Accept");

		text_pathsAccept = new Text(grpPath, SWT.BORDER);
		FormData fd_text_pathsAccept = new FormData();
		fd_text_pathsAccept.left = new FormAttachment(lblAcceptPaths, 5, SWT.RIGHT);
		fd_text_pathsAccept.right = new FormAttachment(100, -5);
		text_pathsAccept.setLayoutData(fd_text_pathsAccept);

		Label lblRejectPaths = new Label(grpPath, SWT.NONE);
		FormData fd_lblRejectPaths = new FormData();
		fd_lblRejectPaths.top = new FormAttachment(lblAcceptPaths, 10, SWT.BOTTOM);
		fd_lblRejectPaths.left = new FormAttachment(lblAcceptPaths, 0, SWT.LEFT);
		lblRejectPaths.setLayoutData(fd_lblRejectPaths);
		lblRejectPaths.setText("Reject");

		text_pathsReject = new Text(grpPath, SWT.BORDER);
		FormData fd_text_pathsReject = new FormData();
		fd_text_pathsReject.right = new FormAttachment(100, -5);
		fd_text_pathsReject.left = new FormAttachment(lblRejectPaths, 5);
		fd_text_pathsReject.top = new FormAttachment(lblRejectPaths, 0, SWT.TOP);
		text_pathsReject.setLayoutData(fd_text_pathsReject);
		// set the group: Post to Pre-Commit matching (regular expressions)
		Group grpPostToPrecommitMatching = new Group(compositeInScrolledComposite, SWT.None);
		grpPostToPrecommitMatching.setText("Post to Pre-Commit matching (regular expressions)");
		grpPostToPrecommitMatching.setLayout(new FormLayout());
		FormData fd_grpPostToPrecommitMatching = new FormData();
		fd_grpPostToPrecommitMatching.right = new FormAttachment(100, -5);
		fd_grpPostToPrecommitMatching.top = new FormAttachment(grpPath, 5, SWT.BOTTOM);
		fd_grpPostToPrecommitMatching.left = new FormAttachment(grpPath, 0, SWT.LEFT);
		grpPostToPrecommitMatching.setLayoutData(fd_grpPostToPrecommitMatching);
		
		Label lblAcceptPostToPrecommit = new Label(grpPostToPrecommitMatching, SWT.NONE);
		FormData fd_lblAcceptPostToPrecommit = new FormData();
		fd_lblAcceptPostToPrecommit.left = new FormAttachment(0, 5);
		lblAcceptPostToPrecommit.setLayoutData(fd_lblAcceptPostToPrecommit);
		lblAcceptPostToPrecommit.setText("Accept");

		text_acceptPostToPrecommitAccept = new Text(grpPostToPrecommitMatching, SWT.BORDER);
		FormData fd_text_postToPrecommitAccept = new FormData();
		fd_text_postToPrecommitAccept.left = new FormAttachment(lblAcceptPostToPrecommit, 5, SWT.RIGHT);
		fd_text_postToPrecommitAccept.right = new FormAttachment(100, -5);
		text_acceptPostToPrecommitAccept.setLayoutData(fd_text_postToPrecommitAccept);
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
