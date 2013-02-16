package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class CommonTab {

	private TabItem tbtmCommon;
	private Text txt_localDir;
	private Text txt_workspace;
	private Text text_GenerateAndExecute;
	private Text text_RecordTestCoverage;
	private Text txtVmArguments;

	public CommonTab(TabFolder tabFolder, int style) {
		tbtmCommon = new TabItem(tabFolder, SWT.NONE);
		tbtmCommon.setImage(SWTResourceManager.getImage(Const.COMMON_ICON_PATH));
		tbtmCommon.setText("Common");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmCommon.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeCommon = new Composite(scrolledComposite, SWT.NONE);
		compositeCommon.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite

		Group grpBeforeTesting = new Group(compositeCommon, SWT.NONE);
		grpBeforeTesting.setText("Before Testing");
		grpBeforeTesting.setLayout(new FormLayout());
		FormData fd_grpBeforeTesting = new FormData();
		fd_grpBeforeTesting.right = new FormAttachment(100, -5);
		fd_grpBeforeTesting.top = new FormAttachment(0, 5);
		fd_grpBeforeTesting.left = new FormAttachment(0, 5);
		grpBeforeTesting.setLayoutData(fd_grpBeforeTesting);

		Button btnRefreshProjectsalways = new Button(grpBeforeTesting, SWT.CHECK);
		FormData fd_btnRefreshProjectsalways = new FormData();
		fd_btnRefreshProjectsalways.left = new FormAttachment(0, 5);
		btnRefreshProjectsalways.setLayoutData(fd_btnRefreshProjectsalways);
		btnRefreshProjectsalways.setText("Refresh projects (always true in command-line mode)");

		Button btnUpdateProjectsFrom = new Button(grpBeforeTesting, SWT.CHECK);
		btnUpdateProjectsFrom.setLayoutData(new FormData());
		FormData fd_btnUpdateProjectsFrom = new FormData();
		fd_btnUpdateProjectsFrom.top = new FormAttachment(btnRefreshProjectsalways, 3, SWT.BOTTOM);
		fd_btnUpdateProjectsFrom.left = new FormAttachment(btnRefreshProjectsalways, 0, SWT.LEFT);
		btnUpdateProjectsFrom.setLayoutData(fd_btnUpdateProjectsFrom);
		btnUpdateProjectsFrom.setText("Update projects from source control");
		// set content of grpBuild
		Group grpBuild = new Group(grpBeforeTesting, SWT.None);
		grpBuild.setLayoutData(new FormData());
		grpBuild.setText("Build");
		grpBuild.setLayout(new FormLayout());
		FormData fd_grpBuild = new FormData();
		fd_grpBuild.right = new FormAttachment(100, -5);
		fd_grpBuild.top = new FormAttachment(btnUpdateProjectsFrom, 3, SWT.BOTTOM);
		fd_grpBuild.left = new FormAttachment(btnRefreshProjectsalways, 0, SWT.LEFT);
		grpBuild.setLayoutData(fd_grpBuild);

		Button btnFullrebuildAllFiles = new Button(grpBuild, SWT.CHECK);
		FormData fd_btnFullrebuildAllFiles = new FormData();
		fd_btnFullrebuildAllFiles.left = new FormAttachment(0, 5);
		btnFullrebuildAllFiles.setLayoutData(fd_btnFullrebuildAllFiles);
		btnFullrebuildAllFiles.setText("Full(rebuild all files)");

		Button btnIncre = new Button(grpBuild, SWT.CHECK);
		FormData fd_btnIncre = new FormData();
		fd_btnIncre.left = new FormAttachment(btnFullrebuildAllFiles, 5);
		btnIncre.setLayoutData(fd_btnIncre);
		btnIncre.setText("Incremental(build files changed since last build)");

		Button btnOnlyIfGeneration = new Button(grpBuild, SWT.CHECK);
		FormData fd_btnOnlyIfGeneration = new FormData();
		fd_btnOnlyIfGeneration.left = new FormAttachment(btnFullrebuildAllFiles, 10, SWT.LEFT);
		fd_btnOnlyIfGeneration.top = new FormAttachment(btnFullrebuildAllFiles, 3, SWT.BOTTOM);
		btnOnlyIfGeneration.setLayoutData(fd_btnOnlyIfGeneration);
		btnOnlyIfGeneration.setText("Only if Generation or Execution is enabled");

		Button btnStopTestingOn = new Button(grpBuild, SWT.CHECK);
		FormData fd_btnStopTestingOn = new FormData();
		fd_btnStopTestingOn.top = new FormAttachment(btnOnlyIfGeneration, 3, SWT.BOTTOM);
		fd_btnStopTestingOn.left = new FormAttachment(btnOnlyIfGeneration, 0, SWT.LEFT);
		btnStopTestingOn.setLayoutData(fd_btnStopTestingOn);
		btnStopTestingOn.setText("Stop testing on build errors");

		Group grpWorkingDirectory = new Group(compositeCommon, SWT.NONE);
		grpWorkingDirectory.setText("Working Directory");
		grpWorkingDirectory.setLayout(new FormLayout());
		FormData fd_grpWorkingDirectory = new FormData();
		fd_grpWorkingDirectory.top = new FormAttachment(grpBeforeTesting, 5, SWT.BOTTOM);
		fd_grpWorkingDirectory.right = new FormAttachment(grpBeforeTesting, 0, SWT.RIGHT);
		fd_grpWorkingDirectory.left = new FormAttachment(grpBeforeTesting, 0, SWT.LEFT);
		grpWorkingDirectory.setLayoutData(fd_grpWorkingDirectory);

		Button btnLocalDirectory = new Button(grpWorkingDirectory, SWT.RADIO);
		btnLocalDirectory.setText("Local Directory: ");
		FormData fd_btnLocalDirectory = new FormData();
		fd_btnLocalDirectory.left = new FormAttachment(0, 5);
		fd_btnLocalDirectory.top = new FormAttachment(0, 5);
		btnLocalDirectory.setLayoutData(fd_btnLocalDirectory);

		Button btnWorkspace = new Button(grpWorkingDirectory, SWT.RADIO);
		btnWorkspace.setText("Workspace: ");
		FormData fd_btnWorkspace = new FormData();
		fd_btnWorkspace.left = new FormAttachment(btnLocalDirectory, 0, SWT.LEFT);
		fd_btnWorkspace.top = new FormAttachment(btnLocalDirectory, 10, SWT.BOTTOM);
		btnWorkspace.setLayoutData(fd_btnWorkspace);

		Button btnUseTestClass = new Button(grpWorkingDirectory, SWT.RADIO);
		btnUseTestClass.setText("Use test class project as working directory");
		FormData fd_btnUseTestClass = new FormData();
		fd_btnUseTestClass.left = new FormAttachment(btnWorkspace, 0, SWT.LEFT);
		fd_btnUseTestClass.top = new FormAttachment(btnWorkspace, 5, SWT.BOTTOM);
		btnUseTestClass.setLayoutData(fd_btnUseTestClass);

		Button btnView = new Button(grpWorkingDirectory, SWT.NONE);
		FormData fd_btnView = new FormData();
		fd_btnView.right = new FormAttachment(100, -5);
		fd_btnView.width = 70;
		btnView.setLayoutData(fd_btnView);
		btnView.setText("\u6D4F\u89C8(B)...");

		txt_localDir = new Text(grpWorkingDirectory, SWT.BORDER);
		FormData fd_txt_localDir = new FormData();
		fd_txt_localDir.right = new FormAttachment(btnView, -10, SWT.LEFT);
		fd_txt_localDir.left = new FormAttachment(btnLocalDirectory, 10, SWT.RIGHT);
		fd_txt_localDir.top = new FormAttachment(btnLocalDirectory, -3, SWT.TOP);
		txt_localDir.setLayoutData(fd_txt_localDir);

		txt_workspace = new Text(grpWorkingDirectory, SWT.BORDER);
		FormData fd_txtWorkspace = new FormData();
		fd_txtWorkspace.right = new FormAttachment(100, -5);
		fd_txtWorkspace.left = new FormAttachment(btnWorkspace, 10, SWT.RIGHT);
		fd_txtWorkspace.top = new FormAttachment(btnWorkspace, -3, SWT.TOP);
		txt_workspace.setLayoutData(fd_txtWorkspace);
		
		Group grpInContainerTesting = new Group(compositeCommon, SWT.None);
		grpInContainerTesting.setText("In-Container Testing");
		grpInContainerTesting.setLayout(new FormLayout());
		FormData fd_grpInContainerTesting = new FormData();
		fd_grpInContainerTesting.top = new FormAttachment(grpWorkingDirectory, 5, SWT.BOTTOM);
		fd_grpInContainerTesting.right = new FormAttachment(grpWorkingDirectory, 0, SWT.RIGHT);
		fd_grpInContainerTesting.left = new FormAttachment(grpWorkingDirectory, 0, SWT.LEFT);
		grpInContainerTesting.setLayoutData(fd_grpInContainerTesting);
		
		Button btnGenerateAndExecute = new Button(grpInContainerTesting, SWT.CHECK);
		FormData fd_btnGenerateAndExecute = new FormData();
		fd_btnGenerateAndExecute.top = new FormAttachment(0, 5);
		fd_btnGenerateAndExecute.left = new FormAttachment(0, 5);
		btnGenerateAndExecute.setLayoutData(fd_btnGenerateAndExecute);
		btnGenerateAndExecute.setText("Generate and execute Catus tests on server: ");
		
		Button btnRecordTestCoverage = new Button(grpInContainerTesting, SWT.CHECK);
		FormData fd_btnRecordTestCoverage = new FormData();
		fd_btnRecordTestCoverage.top = new FormAttachment(btnGenerateAndExecute, 10);
		fd_btnRecordTestCoverage.left = new FormAttachment(btnGenerateAndExecute, 0, SWT.LEFT);
		btnRecordTestCoverage.setLayoutData(fd_btnRecordTestCoverage);
		btnRecordTestCoverage.setText("Record test coverage on server: ");
		
		Button btnClearGenerate = new Button(grpInContainerTesting, SWT.NONE);
		FormData fd_btnClearGenerate = new FormData();
		fd_btnClearGenerate.width = 60;
		fd_btnClearGenerate.right = new FormAttachment(100, -5);
		fd_btnClearGenerate.top = new FormAttachment(btnGenerateAndExecute,0,SWT.TOP);
		btnClearGenerate.setLayoutData(fd_btnClearGenerate);
		btnClearGenerate.setText("Clear");
		
		Button btnClearRecordCoverage = new Button(grpInContainerTesting, SWT.NONE);
		FormData fd_btnClearRecordCoverage = new FormData();
		fd_btnClearRecordCoverage.width = 60;
		fd_btnClearRecordCoverage.right = new FormAttachment(100, -5);
		fd_btnClearRecordCoverage.top = new FormAttachment(btnRecordTestCoverage,0,SWT.TOP);
		btnClearRecordCoverage.setLayoutData(fd_btnClearRecordCoverage);
		btnClearRecordCoverage.setText("Clear");
		
		Button btnServer = new Button(grpInContainerTesting, SWT.NONE);
		FormData fd_btnServer = new FormData();
		fd_btnServer.width=60;
		fd_btnServer.right = new FormAttachment(btnClearGenerate,-3,SWT.LEFT);
		fd_btnServer.top = new FormAttachment(btnGenerateAndExecute,0,SWT.TOP);
		btnServer.setLayoutData(fd_btnServer);
		btnServer.setText("Server...");
		
		Button btnEditRecordTestCoverage = new Button(grpInContainerTesting, SWT.NONE);
		FormData fd_btnEditRecordTestCoverage = new FormData();
		fd_btnEditRecordTestCoverage.width = 60;
		fd_btnEditRecordTestCoverage.right = new FormAttachment(btnClearRecordCoverage,-3,SWT.LEFT);
		fd_btnEditRecordTestCoverage.top = new FormAttachment(btnRecordTestCoverage,0,SWT.TOP);
		btnEditRecordTestCoverage.setLayoutData(fd_btnEditRecordTestCoverage);
		btnEditRecordTestCoverage.setText("Edit...");
		
		text_GenerateAndExecute = new Text(grpInContainerTesting, SWT.BORDER);
		FormData fd_text_GenerateAndExecute = new FormData();
		fd_text_GenerateAndExecute.left = new FormAttachment(btnGenerateAndExecute,3,SWT.RIGHT);
		fd_text_GenerateAndExecute.right = new FormAttachment(btnServer,-3,SWT.LEFT);
		fd_text_GenerateAndExecute.top = new FormAttachment(btnGenerateAndExecute,0,SWT.TOP);
		text_GenerateAndExecute.setLayoutData(fd_text_GenerateAndExecute);
		
		text_RecordTestCoverage = new Text(grpInContainerTesting, SWT.BORDER);
		FormData fd_text_RecordTestCoverage = new FormData();
		fd_text_RecordTestCoverage.left = new FormAttachment(btnRecordTestCoverage,3,SWT.RIGHT);
		fd_text_RecordTestCoverage.right = new FormAttachment(btnEditRecordTestCoverage,-3,SWT.LEFT);
		fd_text_RecordTestCoverage.top = new FormAttachment(btnRecordTestCoverage,0,SWT.TOP);
		text_RecordTestCoverage.setLayoutData(fd_text_RecordTestCoverage);
		
		Group grpVmArguments = new Group(compositeCommon, SWT.NONE);
		grpVmArguments.setText("VM Arguments");
		grpVmArguments.setLayout(new FormLayout());
		FormData fd_grpVmArguments = new FormData();
		fd_grpVmArguments.top = new FormAttachment(grpInContainerTesting, 5, SWT.BOTTOM);
		fd_grpVmArguments.right = new FormAttachment(grpInContainerTesting, 0, SWT.RIGHT);
		fd_grpVmArguments.left = new FormAttachment(grpInContainerTesting, 0, SWT.LEFT);
		grpVmArguments.setLayoutData(fd_grpVmArguments);
		
		txtVmArguments = new Text(grpVmArguments, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtVmArguments.setText("-ea");
		FormData fd_text_VmArguments = new FormData();
		fd_text_VmArguments.left = new FormAttachment(0,5);
		fd_text_VmArguments.right = new FormAttachment(100,-5);
		txtVmArguments.setLayoutData(fd_text_VmArguments);
		
		scrolledComposite.setContent(compositeCommon);
		scrolledComposite.setMinSize(compositeCommon.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
