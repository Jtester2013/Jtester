package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class GenerationInputTab {
	private TabItem tbtmInputs;
	private TabItem tbtmGeneration;
	private Text text_monitoringNewApp;
	private Text text_ConnectingToRunningApp;
	private Text text_ReadPrerecordFile;
	
	public GenerationInputTab(TabFolder tabFolderInGeneration) {
		tbtmInputs = new TabItem(tabFolderInGeneration, SWT.NONE);
		tbtmInputs.setText("Inputs");

		Composite inputsOfGenerationComposite = new Composite(tabFolderInGeneration, SWT.NONE);
		tbtmInputs.setControl(inputsOfGenerationComposite);
		inputsOfGenerationComposite.setLayout(new FormLayout());

		Group groupGenerateUnitTest = new Group(inputsOfGenerationComposite, SWT.NONE);
		groupGenerateUnitTest.setText("Generate unit tests");
		groupGenerateUnitTest.setLayout(new FormLayout());
		FormData fd_groupGenerateUnitTest = new FormData();
		fd_groupGenerateUnitTest.right = new FormAttachment(100, -5);
		fd_groupGenerateUnitTest.top = new FormAttachment(0, 5);
		fd_groupGenerateUnitTest.left = new FormAttachment(0, 5);
		groupGenerateUnitTest.setLayoutData(fd_groupGenerateUnitTest);

		Button btnAsTemplateWithoutAnalysis = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnAsTemplateWithoutAnalysis = new FormData();
		fd_btnAsTemplateWithoutAnalysis.left = new FormAttachment(0, 5);
		fd_btnAsTemplateWithoutAnalysis.top = new FormAttachment(0, 5);
		btnAsTemplateWithoutAnalysis.setLayoutData(fd_btnAsTemplateWithoutAnalysis);
		btnAsTemplateWithoutAnalysis.setText("As templates of default inputs without any analysis");

		Button btnAutomaticallyWithNormal = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnAutomaticallyWithNormal = new FormData();
		fd_btnAutomaticallyWithNormal.top = new FormAttachment(btnAsTemplateWithoutAnalysis, 5);
		fd_btnAutomaticallyWithNormal.left = new FormAttachment(btnAsTemplateWithoutAnalysis, 0, SWT.LEFT);
		btnAutomaticallyWithNormal.setLayoutData(fd_btnAutomaticallyWithNormal);
		btnAutomaticallyWithNormal.setText("Automatically with normal symbolic analysis");

		Button btnAutomaticallyWithThorough = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnAutomaticallyWithThorForm = new FormData();
		fd_btnAutomaticallyWithThorForm.top = new FormAttachment(btnAutomaticallyWithNormal, 5);
		fd_btnAutomaticallyWithThorForm.left = new FormAttachment(btnAutomaticallyWithNormal, 0, SWT.LEFT);
		btnAutomaticallyWithThorough.setLayoutData(fd_btnAutomaticallyWithThorForm);
		btnAutomaticallyWithThorough.setText("Automatically with thorough symbolic analysis");
		// add components related with "Monitoring a New Application Launch"
		Button btnMonitoringANew = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnMonitoringANewForm = new FormData();
		fd_btnMonitoringANewForm.top = new FormAttachment(btnAutomaticallyWithThorough, 15);
		fd_btnMonitoringANewForm.left = new FormAttachment(btnAutomaticallyWithThorough, 0, SWT.LEFT);
		btnMonitoringANew.setLayoutData(fd_btnMonitoringANewForm);
		btnMonitoringANew.setText("Monitoring a New Application Launch: ");

		text_monitoringNewApp = new Text(groupGenerateUnitTest, SWT.BORDER);
		FormData fd_text_monitoringNewAppLaunch = new FormData();
		fd_text_monitoringNewAppLaunch.left = new FormAttachment(btnMonitoringANew);
		fd_text_monitoringNewAppLaunch.bottom = new FormAttachment(btnMonitoringANew, 0, SWT.BOTTOM);
		fd_text_monitoringNewAppLaunch.top = new FormAttachment(btnMonitoringANew, 0, SWT.TOP);
		text_monitoringNewApp.setLayoutData(fd_text_monitoringNewAppLaunch);

		Button btn_edit_monitoringNewAppLaunch = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_edit_monitoringNewAppLaunch = new FormData();
		fd_btn_edit_monitoringNewAppLaunch.bottom = new FormAttachment(text_monitoringNewApp, 0, SWT.BOTTOM);
		fd_btn_edit_monitoringNewAppLaunch.left = new FormAttachment(text_monitoringNewApp, 6);
		fd_btn_edit_monitoringNewAppLaunch.top = new FormAttachment(btnMonitoringANew, 0, SWT.TOP);
		btn_edit_monitoringNewAppLaunch.setLayoutData(fd_btn_edit_monitoringNewAppLaunch);
		btn_edit_monitoringNewAppLaunch.setText("Edit...");

		Button btn_clear_monitoringNewAppLaunch = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_clear_monitoringNewAppLaunch = new FormData();
		fd_btn_clear_monitoringNewAppLaunch.bottom = new FormAttachment(text_monitoringNewApp, 0, SWT.BOTTOM);
		fd_btn_clear_monitoringNewAppLaunch.top = new FormAttachment(btnMonitoringANew, 0, SWT.TOP);
		fd_btn_clear_monitoringNewAppLaunch.left = new FormAttachment(btn_edit_monitoringNewAppLaunch, 6);
		btn_clear_monitoringNewAppLaunch.setLayoutData(fd_btn_clear_monitoringNewAppLaunch);
		btn_clear_monitoringNewAppLaunch.setText("Clear");

		// add components related with
		// "Connecting to a Running Applications:"
		Button btnConnectingToRunningApp = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnConnectingToRunningApp = new FormData();
		fd_btnConnectingToRunningApp.top = new FormAttachment(btnMonitoringANew, 15);
		fd_btnConnectingToRunningApp.left = new FormAttachment(btnAutomaticallyWithThorough, 0, SWT.LEFT);
		btnConnectingToRunningApp.setLayoutData(fd_btnConnectingToRunningApp);
		btnConnectingToRunningApp.setText("Connecting to a Running Applications: ");

		text_ConnectingToRunningApp = new Text(groupGenerateUnitTest, SWT.BORDER);
		FormData fd_text_ConnectingToRunningApp = new FormData();
		fd_text_ConnectingToRunningApp.left = new FormAttachment(btnConnectingToRunningApp);
		fd_text_ConnectingToRunningApp.bottom = new FormAttachment(btnConnectingToRunningApp, 0, SWT.BOTTOM);
		fd_text_ConnectingToRunningApp.top = new FormAttachment(btnConnectingToRunningApp, 0, SWT.TOP);
		text_ConnectingToRunningApp.setLayoutData(fd_text_ConnectingToRunningApp);

		Button btn_edit_ConnectingToRunningApp = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_edit_ConnectingToRunningApp = new FormData();
		fd_btn_edit_ConnectingToRunningApp.bottom = new FormAttachment(text_ConnectingToRunningApp, 0, SWT.BOTTOM);
		fd_btn_edit_ConnectingToRunningApp.left = new FormAttachment(text_ConnectingToRunningApp, 6);
		fd_btn_edit_ConnectingToRunningApp.top = new FormAttachment(btnConnectingToRunningApp, 0, SWT.TOP);
		btn_edit_ConnectingToRunningApp.setLayoutData(fd_btn_edit_ConnectingToRunningApp);
		btn_edit_ConnectingToRunningApp.setText("Edit...");

		Button btn_clear_ConnectingToRunningApp = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_clear_ConnectingToRunningApp = new FormData();
		fd_btn_clear_ConnectingToRunningApp.bottom = new FormAttachment(text_ConnectingToRunningApp, 0, SWT.BOTTOM);
		fd_btn_clear_ConnectingToRunningApp.top = new FormAttachment(btnConnectingToRunningApp, 0, SWT.TOP);
		fd_btn_clear_ConnectingToRunningApp.left = new FormAttachment(btn_edit_ConnectingToRunningApp, 6);
		btn_clear_ConnectingToRunningApp.setLayoutData(fd_btn_clear_ConnectingToRunningApp);
		btn_clear_ConnectingToRunningApp.setText("Clear");

		// add components related with "Reading from a Pre-recorded Files:"
		Button btnReadPrerecordFile = new Button(groupGenerateUnitTest, SWT.RADIO);
		FormData fd_btnReadPrerecordFile = new FormData();
		fd_btnReadPrerecordFile.top = new FormAttachment(btnConnectingToRunningApp, 15);
		fd_btnReadPrerecordFile.left = new FormAttachment(btnAutomaticallyWithThorough, 0, SWT.LEFT);
		btnReadPrerecordFile.setLayoutData(fd_btnReadPrerecordFile);
		btnReadPrerecordFile.setText("Reading from a Pre-recorded Files: ");

		text_ReadPrerecordFile = new Text(groupGenerateUnitTest, SWT.BORDER);
		FormData fd_text_ReadPrerecordFile = new FormData();
		fd_text_ReadPrerecordFile.left = new FormAttachment(btnReadPrerecordFile);
		fd_text_ReadPrerecordFile.bottom = new FormAttachment(btnReadPrerecordFile, 0, SWT.BOTTOM);
		fd_text_ReadPrerecordFile.top = new FormAttachment(btnReadPrerecordFile, 0, SWT.TOP);
		text_ReadPrerecordFile.setLayoutData(fd_text_ReadPrerecordFile);

		Button btn_edit_ReadPrerecordFile = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_edit_ReadPrerecordFile = new FormData();
		fd_btn_edit_ReadPrerecordFile.bottom = new FormAttachment(text_ReadPrerecordFile, 0, SWT.BOTTOM);
		fd_btn_edit_ReadPrerecordFile.left = new FormAttachment(text_ReadPrerecordFile, 6);
		fd_btn_edit_ReadPrerecordFile.top = new FormAttachment(btnReadPrerecordFile, 0, SWT.TOP);
		btn_edit_ReadPrerecordFile.setLayoutData(fd_btn_edit_ReadPrerecordFile);
		btn_edit_ReadPrerecordFile.setText("‰Ø¿¿(B)...");

		Button btn_clear_ReadPrerecordFile = new Button(groupGenerateUnitTest, SWT.NONE);
		FormData fd_btn_clear_ReadPrerecordFile = new FormData();
		fd_btn_clear_ReadPrerecordFile.bottom = new FormAttachment(text_ReadPrerecordFile, 0, SWT.BOTTOM);
		fd_btn_clear_ReadPrerecordFile.top = new FormAttachment(btnReadPrerecordFile, 0, SWT.TOP);
		fd_btn_clear_ReadPrerecordFile.left = new FormAttachment(btn_edit_ReadPrerecordFile, 6);
		btn_clear_ReadPrerecordFile.setLayoutData(fd_btn_clear_ReadPrerecordFile);
		btn_clear_ReadPrerecordFile.setText("Clear");

		Button btnGenerateOnlyOne = new Button(inputsOfGenerationComposite, SWT.CHECK);
		FormData fd_btnGenerateOnlyOne = new FormData();
		fd_btnGenerateOnlyOne.top = new FormAttachment(groupGenerateUnitTest, 5, SWT.BOTTOM);
		btnGenerateOnlyOne.setLayoutData(fd_btnGenerateOnlyOne);
		btnGenerateOnlyOne.setText("Generate only one test case to test all selected classes");

	}

}
