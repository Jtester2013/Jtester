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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class OptionsInExecutionTab {
	TabItem tabItem;
	private Text text_ReportFailureLessThanCoverage;
	private Text text_ReportUnverifiedOutcomeMaxLimit;
	private Text text_TimeoutMaxLimitForEachClassOrSuite;
	private Text text_TimeoutMaxLimit;
	private Table table_PluginTestsToRun;

	public OptionsInExecutionTab(TabFolder tabFolder) {
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Options");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		Button btnReportExecutableLineCoverage = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnReportExecutableLineCoverage = new FormData();
		fd_btnReportExecutableLineCoverage.top = new FormAttachment(0, 5);
		fd_btnReportExecutableLineCoverage.left = new FormAttachment(0, 5);
		btnReportExecutableLineCoverage.setLayoutData(fd_btnReportExecutableLineCoverage);
		btnReportExecutableLineCoverage.setText("Report executable line coverage");

		Button btnReportDecisionCoverage = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnReportDecisionCoverage = new FormData();
		fd_btnReportDecisionCoverage.top = new FormAttachment(btnReportExecutableLineCoverage, 5);
		fd_btnReportDecisionCoverage.left = new FormAttachment(btnReportExecutableLineCoverage, 10, SWT.LEFT);
		btnReportDecisionCoverage.setLayoutData(fd_btnReportDecisionCoverage);
		btnReportDecisionCoverage.setText("Report decision (branch) coverage");

		Button btnReportFailureOrFileWithCoverageLessThan = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnReportFailureOrFileWithCoverageLessThan = new FormData();
		fd_btnReportFailureOrFileWithCoverageLessThan.top = new FormAttachment(btnReportDecisionCoverage, 5);
		fd_btnReportFailureOrFileWithCoverageLessThan.left = new FormAttachment(btnReportDecisionCoverage, 0, SWT.LEFT);
		btnReportFailureOrFileWithCoverageLessThan.setLayoutData(fd_btnReportFailureOrFileWithCoverageLessThan);
		btnReportFailureOrFileWithCoverageLessThan.setText("Report failure for files with line coverage less than %:");

		text_ReportFailureLessThanCoverage = new Text(compositeInScrolledComposite, SWT.BORDER);
		FormData fd_text_ReportFailureLessThanCoverage = new FormData();
		fd_text_ReportFailureLessThanCoverage.bottom = new FormAttachment(btnReportFailureOrFileWithCoverageLessThan, 0, SWT.BOTTOM);
		fd_text_ReportFailureLessThanCoverage.left = new FormAttachment(btnReportFailureOrFileWithCoverageLessThan, 6);
		text_ReportFailureLessThanCoverage.setLayoutData(fd_text_ReportFailureLessThanCoverage);

		Button btnPublishMethodCoverageInfo = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnPublishMethodCoverageInfo = new FormData();
		fd_btnPublishMethodCoverageInfo.top = new FormAttachment(btnReportFailureOrFileWithCoverageLessThan, 6);
		fd_btnPublishMethodCoverageInfo.left = new FormAttachment(btnReportDecisionCoverage, 0, SWT.LEFT);
		btnPublishMethodCoverageInfo.setLayoutData(fd_btnPublishMethodCoverageInfo);
		btnPublishMethodCoverageInfo.setText("Publish method coverage detail in reports (slow, memory intensive, and generates large reports)");

		Button btnClearPreviousCoverage = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnClearPreviousCoverage = new FormData();
		fd_btnClearPreviousCoverage.top = new FormAttachment(btnPublishMethodCoverageInfo, 6);
		fd_btnClearPreviousCoverage.left = new FormAttachment(btnReportDecisionCoverage, 0, SWT.LEFT);
		btnClearPreviousCoverage.setLayoutData(fd_btnClearPreviousCoverage);
		btnClearPreviousCoverage.setText("Clear previous coverage information when launching new tests");

		Button btnReportUnverifiedOutcomesMaxLimit = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnReportUnverifiedOutcomesMaxLimit = new FormData();
		fd_btnReportUnverifiedOutcomesMaxLimit.top = new FormAttachment(btnClearPreviousCoverage, 6);
		fd_btnReportUnverifiedOutcomesMaxLimit.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnReportUnverifiedOutcomesMaxLimit.setLayoutData(fd_btnReportUnverifiedOutcomesMaxLimit);
		btnReportUnverifiedOutcomesMaxLimit.setText("Report unverified outcomes up to this maximum limit:");

		text_ReportUnverifiedOutcomeMaxLimit = new Text(compositeInScrolledComposite, SWT.BORDER);
		FormData fd_text_ReportUnverifiedOutcomeMaxLimit = new FormData();
		fd_text_ReportUnverifiedOutcomeMaxLimit.bottom = new FormAttachment(btnReportUnverifiedOutcomesMaxLimit, 0, SWT.BOTTOM);
		fd_text_ReportUnverifiedOutcomeMaxLimit.left = new FormAttachment(btnReportUnverifiedOutcomesMaxLimit, 2, SWT.RIGHT);
		text_ReportUnverifiedOutcomeMaxLimit.setLayoutData(fd_text_ReportUnverifiedOutcomeMaxLimit);

		Button btnReportUnverifiedOutcomeDistributions = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnReportUnverifiedOutcomeDistributions = new FormData();
		fd_btnReportUnverifiedOutcomeDistributions.top = new FormAttachment(btnReportUnverifiedOutcomesMaxLimit, 6);
		fd_btnReportUnverifiedOutcomeDistributions.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnReportUnverifiedOutcomeDistributions.setLayoutData(fd_btnReportUnverifiedOutcomeDistributions);
		btnReportUnverifiedOutcomeDistributions.setText("Report unverified outcome distributions");

		Button btnAddNewlyOutcomesToExcel = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnAddNewlyOutcomesToExcel = new FormData();
		fd_btnAddNewlyOutcomesToExcel.top = new FormAttachment(btnReportUnverifiedOutcomeDistributions, 6);
		fd_btnAddNewlyOutcomesToExcel.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnAddNewlyOutcomesToExcel.setLayoutData(fd_btnAddNewlyOutcomesToExcel);
		btnAddNewlyOutcomesToExcel.setText("Add newly observed outcomes of parameterized tests to Excel file (slows down execution)");

		Button btnDetectMemoryLeaks = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnDetectMemoryLeaks = new FormData();
		fd_btnDetectMemoryLeaks.top = new FormAttachment(btnAddNewlyOutcomesToExcel, 6);
		fd_btnDetectMemoryLeaks.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnDetectMemoryLeaks.setLayoutData(fd_btnDetectMemoryLeaks);
		btnDetectMemoryLeaks.setText("Detect memory leaks (slow and memory intensive)");

		Button btnCheckDesignByContract = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnCheckDesignByContract = new FormData();
		fd_btnCheckDesignByContract.top = new FormAttachment(btnDetectMemoryLeaks, 6);
		fd_btnCheckDesignByContract.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnCheckDesignByContract.setLayoutData(fd_btnCheckDesignByContract);
		btnCheckDesignByContract.setText("Check Design by Contract");

		Button btnTimeoutForEachTest = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTimeoutForEachTest = new FormData();
		fd_btnTimeoutForEachTest.top = new FormAttachment(btnCheckDesignByContract, 6);
		fd_btnTimeoutForEachTest.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnTimeoutForEachTest.setLayoutData(fd_btnTimeoutForEachTest);
		btnTimeoutForEachTest.setText("Timeout for each test before executing the next test class or suite (in minutes):");

		text_TimeoutMaxLimitForEachClassOrSuite = new Text(compositeInScrolledComposite, SWT.BORDER);
		FormData fd_text_TimeoutMaxLimitForEachClassOrSuite = new FormData();
		fd_text_TimeoutMaxLimitForEachClassOrSuite.bottom = new FormAttachment(btnTimeoutForEachTest, 0, SWT.BOTTOM);
		fd_text_TimeoutMaxLimitForEachClassOrSuite.left = new FormAttachment(btnTimeoutForEachTest, 6);
		text_TimeoutMaxLimitForEachClassOrSuite.setLayoutData(fd_text_TimeoutMaxLimitForEachClassOrSuite);

		Button btnTotalTimeoutForTestExecution = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTotalTimeoutForTestExecution = new FormData();
		fd_btnTotalTimeoutForTestExecution.top = new FormAttachment(btnTimeoutForEachTest, 6);
		fd_btnTotalTimeoutForTestExecution.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnTotalTimeoutForTestExecution.setLayoutData(fd_btnTotalTimeoutForTestExecution);
		btnTotalTimeoutForTestExecution.setText("Total timeout for test execution (in minutes):");

		text_TimeoutMaxLimit = new Text(compositeInScrolledComposite, SWT.BORDER);
		FormData fd_text_TimeoutMaxLimit = new FormData();
		fd_text_TimeoutMaxLimit.bottom = new FormAttachment(btnTotalTimeoutForTestExecution, 0, SWT.BOTTOM);
		fd_text_TimeoutMaxLimit.left = new FormAttachment(btnTotalTimeoutForTestExecution, 6);
		text_TimeoutMaxLimit.setLayoutData(fd_text_TimeoutMaxLimit);

		Button btnSeperateLaunchForEachClassSuite = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnSeperateLaunchForEachClassSuite = new FormData();
		fd_btnSeperateLaunchForEachClassSuite.top = new FormAttachment(btnTotalTimeoutForTestExecution, 6);
		fd_btnSeperateLaunchForEachClassSuite.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnSeperateLaunchForEachClassSuite.setLayoutData(fd_btnSeperateLaunchForEachClassSuite);
		btnSeperateLaunchForEachClassSuite.setText("Seperate launch for each test class or suite");

		Button btnRunTestsInDebugMode = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnRunTestsInDebugMode = new FormData();
		fd_btnRunTestsInDebugMode.top = new FormAttachment(btnSeperateLaunchForEachClassSuite, 6);
		fd_btnRunTestsInDebugMode.left = new FormAttachment(btnReportExecutableLineCoverage, 0, SWT.LEFT);
		btnRunTestsInDebugMode.setLayoutData(fd_btnRunTestsInDebugMode);
		btnRunTestsInDebugMode.setText("Run tests in debug mode");

		Group grpPluginTests = new Group(compositeInScrolledComposite, SWT.NONE);
		grpPluginTests.setText("Plugin Tests");
		grpPluginTests.setLayout(new FormLayout());
		FormData fd_grpPluginTests = new FormData();
//		fd_grpPluginTests.bottom = new FormAttachment(btnRunTestsInDebugMode, 67, SWT.BOTTOM);
		fd_grpPluginTests.top = new FormAttachment(btnRunTestsInDebugMode, 6);
		fd_grpPluginTests.left = new FormAttachment(0, 5);
		fd_grpPluginTests.right = new FormAttachment(100, -5);
		grpPluginTests.setLayoutData(fd_grpPluginTests);
		
		Button btnDoNotRun = new Button(grpPluginTests, SWT.RADIO);
		FormData fd_btnDoNotRun = new FormData();
		fd_btnDoNotRun.top = new FormAttachment(0);
		fd_btnDoNotRun.left = new FormAttachment(0, 10);
		btnDoNotRun.setLayoutData(fd_btnDoNotRun);
		btnDoNotRun.setText("Do not run plugin tests");
		
		Button btnAutomaticallySearchThe = new Button(grpPluginTests, SWT.RADIO);
		FormData fd_btnAutomaticallySearchThe = new FormData();
		fd_btnAutomaticallySearchThe.top = new FormAttachment(btnDoNotRun, 6);
		fd_btnAutomaticallySearchThe.left = new FormAttachment(btnDoNotRun, 0, SWT.LEFT);
		btnAutomaticallySearchThe.setLayoutData(fd_btnAutomaticallySearchThe);
		btnAutomaticallySearchThe.setText("Automatically search the project for JUnit Plug-in Test launch files to run");
		
		Button btnLaunchSelectedTests = new Button(grpPluginTests, SWT.RADIO);
		FormData fd_btnLaunchSelectedTests = new FormData();
		fd_btnLaunchSelectedTests.top = new FormAttachment(btnAutomaticallySearchThe, 6);
		fd_btnLaunchSelectedTests.left = new FormAttachment(btnDoNotRun, 0, SWT.LEFT);
		btnLaunchSelectedTests.setLayoutData(fd_btnLaunchSelectedTests);
		btnLaunchSelectedTests.setText("Launch selected tests as plug-in tests");
		
		Button btnSpecifyPluginTests = new Button(grpPluginTests, SWT.RADIO);
		FormData fd_btnSpecifyPluginTests = new FormData();
		fd_btnSpecifyPluginTests.top = new FormAttachment(btnLaunchSelectedTests, 55, SWT.BOTTOM);
		fd_btnSpecifyPluginTests.left = new FormAttachment(btnDoNotRun, 0, SWT.LEFT);
		btnSpecifyPluginTests.setLayoutData(fd_btnSpecifyPluginTests);
		btnSpecifyPluginTests.setText("Specify plugin tests to run:");
		
		table_PluginTestsToRun = new Table(grpPluginTests, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_PluginTestsToRun = new FormData();
		fd_table_PluginTestsToRun.right = new FormAttachment(100, -70);
		fd_table_PluginTestsToRun.top = new FormAttachment(btnSpecifyPluginTests, -50, SWT.TOP);
		fd_table_PluginTestsToRun.bottom = new FormAttachment(btnSpecifyPluginTests, 50, SWT.BOTTOM);
		fd_table_PluginTestsToRun.left = new FormAttachment(btnSpecifyPluginTests, 9);
		table_PluginTestsToRun.setLayoutData(fd_table_PluginTestsToRun);
		table_PluginTestsToRun.setHeaderVisible(false);
		
		Button btnNewPluginTest = new Button(grpPluginTests, SWT.NONE);
		FormData fd_btnNewPluginTest = new FormData();
		fd_btnNewPluginTest.width = 60;
		fd_btnNewPluginTest.top = new FormAttachment(table_PluginTestsToRun, 0, SWT.TOP);
		fd_btnNewPluginTest.left = new FormAttachment(table_PluginTestsToRun, 6);
		btnNewPluginTest.setLayoutData(fd_btnNewPluginTest);
		btnNewPluginTest.setText("New");
		
		Button btnRemovePluginTest = new Button(grpPluginTests, SWT.NONE);
		FormData fd_btnRemovePluginTest = new FormData();
		fd_btnRemovePluginTest.width = 60;
		fd_btnRemovePluginTest.top = new FormAttachment(btnNewPluginTest, 6);
		fd_btnRemovePluginTest.left = new FormAttachment(table_PluginTestsToRun, 6);
		btnRemovePluginTest.setLayoutData(fd_btnRemovePluginTest);
		btnRemovePluginTest.setText("Remove");

		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
