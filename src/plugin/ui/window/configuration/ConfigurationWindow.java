package plugin.ui.window.configuration;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import plugin.util.Const;
import plugin.util.LayoutFactory;
import plugin.util.SWTResourceManager;

public class ConfigurationWindow {

	protected Shell shell;
	private Text textName;
	private Text textParent;
	private Text text_lastDaysInLineFilter;
	private Text text_authorNameInLineFilter;
	private Text text_minMethodNum;
	private Table tableMethodsPattern;
	private Text text_monitoringNewApp;
	private Text text_ConnectingToRunningApp;
	private Text text_ReadPrerecordFile;
	
	/**
	 * a method for add image to tree items of a tree
	 * 
	 * @param tree
	 *            the tree to be trimmed
	 * @param folderIconPath
	 *            the path of icon for tree nodes which are not leaves
	 * @param leafIconPath
	 *            the path of icon for leaves
	 */
	public static void trim(Tree tree, String folderIconPath, String leafIconPath) {

	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		String titleString = "Create, manage and run test configuration.";

		shell = new Shell();
		shell.setSize(796, 609);
		shell.setText("Test Configurations");
		FormLayout fl_shell = LayoutFactory.getFormLayout();
		shell.setLayout(fl_shell);

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FillLayout fl_composite = new FillLayout(SWT.HORIZONTAL);
		fl_composite.marginWidth = 5;
		fl_composite.marginHeight = 3;
		fl_composite.spacing = 5;
		composite.setLayout(fl_composite);
		FormData fd_composite = new FormData();
		fd_composite.height = 60;
		fd_composite.right = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);

		// set top composite
		CLabel lblTitle = new CLabel(composite, SWT.NONE);
		lblTitle.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.NORMAL));
		lblTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitle.setText(titleString);
		lblTitle.setImage(null);
		CLabel lblTitleImage = new CLabel(composite, SWT.NONE);
		lblTitleImage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTitleImage.setAlignment(SWT.RIGHT);
		lblTitleImage.setImage(SWTResourceManager.getImage(Const.CONFIGURATION_ICON_PATH));

		// set bottom composite
		Composite bottomComposite = new Composite(shell, SWT.NONE);
		bottomComposite.setLayout(new FormLayout());
		FormData fd_bottomComposite = new FormData();
		fd_bottomComposite.bottom = new FormAttachment(100);
		fd_bottomComposite.height = 30;
		fd_bottomComposite.left = new FormAttachment(0);
		fd_bottomComposite.right = new FormAttachment(100);
		bottomComposite.setLayoutData(fd_bottomComposite);

		Button btnClose = new Button(bottomComposite, SWT.CENTER);
		btnClose.setAlignment(SWT.CENTER);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.width = 70;
		fd_btnNewButton.right = new FormAttachment(100);
		btnClose.setLayoutData(fd_btnNewButton);
		btnClose.setText("Close");

		Button btnRunTest = new Button(bottomComposite, SWT.NONE);
		FormData fd_btnNewButton_1 = new FormData();
		fd_btnNewButton_1.right = new FormAttachment(btnClose);
		fd_btnNewButton_1.width = 70;
		btnRunTest.setLayoutData(fd_btnNewButton_1);
		btnRunTest.setText("Run Test");

		// set center composite
		Composite centerComposite = new Composite(shell, SWT.NONE);
		centerComposite.setLayout(new FormLayout());
		FormData fd_centerComposite = new FormData();
		fd_centerComposite.bottom = new FormAttachment(bottomComposite);
		fd_centerComposite.left = new FormAttachment(0, 1);
		fd_centerComposite.top = new FormAttachment(0, 62);
		fd_centerComposite.right = new FormAttachment(100);
		centerComposite.setLayoutData(fd_centerComposite);

		SashForm sashForm = new SashForm(centerComposite, SWT.HORIZONTAL | SWT.SMOOTH);
		FormData fd_sashForm = new FormData();
		fd_sashForm.top = new FormAttachment(0, 5);
		fd_sashForm.left = new FormAttachment(0, 5);
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.right = new FormAttachment(100, -5);
		sashForm.setLayoutData(fd_sashForm);
		// add list composite(left part) and detail composite(right part) into
		// sashform
		Composite listComposite = new Composite(sashForm, SWT.NONE);
		Composite detailComposite = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] { 3, 7 });// must be called after all
												// parts added in sashForm.

		// set left part of sash form
		listComposite.setLayout(new FormLayout());
		Composite operationComposite = new Composite(listComposite, SWT.NONE);
		operationComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_operationComposite = new FormData();
		fd_operationComposite.left = new FormAttachment(0);
		fd_operationComposite.right = new FormAttachment(100);
		fd_operationComposite.height = 30;
		fd_operationComposite.bottom = new FormAttachment(100);
		operationComposite.setLayoutData(fd_operationComposite);

		Button btnNew = new Button(operationComposite, SWT.NONE);
		btnNew.setText("New");

		Button btnDelete = new Button(operationComposite, SWT.NONE);
		btnDelete.setText("Delete");
		ScrolledComposite scrolledComposite = new ScrolledComposite(listComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.bottom = new FormAttachment(operationComposite);
		fd_scrolledComposite.right = new FormAttachment(100);
		fd_scrolledComposite.top = new FormAttachment(0);
		fd_scrolledComposite.left = new FormAttachment(0);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		/*
		 * construct the configuration tree one tree item is contacted with one
		 * configuration(saved as a xml record)
		 */
		{
			Tree configTree = new Tree(scrolledComposite, SWT.BORDER);

			TreeItem userConfigTreeitem = new TreeItem(configTree, SWT.NONE);
			userConfigTreeitem.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			userConfigTreeitem.setText("User-defined");

			TreeItem userConfig1 = new TreeItem(userConfigTreeitem, SWT.NONE);
			userConfig1.setImage(SWTResourceManager.getImage(Const.USER_ICON_PATH));
			userConfig1.setText("user configuration 1");
			userConfigTreeitem.setExpanded(true);

			TreeItem builtinConfigTreeitem = new TreeItem(configTree, SWT.NONE);
			builtinConfigTreeitem.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			builtinConfigTreeitem.setText("Builtin");

			TreeItem trtmCodeReview = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmCodeReview.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmCodeReview.setText("Code Review");

			TreeItem trtmPostcommit = new TreeItem(trtmCodeReview, SWT.NONE);
			trtmPostcommit.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmPostcommit.setText("Post-Commit");

			TreeItem trtmPrecommit = new TreeItem(trtmCodeReview, SWT.NONE);
			trtmPrecommit.setText("Pre-Commit");
			trtmPrecommit.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			trtmCodeReview.setExpanded(true);

			TreeItem trtmParasoftsAep = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmParasoftsAep.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmParasoftsAep.setText("Parasoft's AEP");

			TreeItem trtmPhaseI = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmPhaseI.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmPhaseI.setText("Phase I");

			TreeItem trtmBuildMachineNightlyStatic = new TreeItem(trtmPhaseI, SWT.NONE);
			trtmBuildMachineNightlyStatic.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmBuildMachineNightlyStatic.setText("Build Machine Nightly (static)");

			TreeItem trtmDeveloperstatic = new TreeItem(trtmPhaseI, SWT.NONE);
			trtmDeveloperstatic.setText("Developer (static)");
			trtmDeveloperstatic.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmPhaseI.setExpanded(true);

			TreeItem trtmPhaseIi = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmPhaseIi.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmPhaseIi.setText("Phase II");

			TreeItem trtmBuildMachineNightlySGE = new TreeItem(trtmPhaseIi, SWT.NONE);
			trtmBuildMachineNightlySGE.setText("Build Machine Nightly (static+generation+execution)");
			trtmBuildMachineNightlySGE.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			System.out.println("********************\n" + Const.HYPERCUBE_ICON_PATH);
			trtmBuildMachineNightlySGE.setExpanded(true);

			TreeItem trtmDeveloperstaticexecution = new TreeItem(trtmPhaseIi, SWT.NONE);
			trtmDeveloperstaticexecution.setText("Developer (static+execution)");
			trtmDeveloperstaticexecution.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmPhaseIi.setExpanded(true);

			TreeItem trtmCriticalRulesmust = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmCriticalRulesmust.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCriticalRulesmust.setText("Critical Rules (must have)");

			TreeItem trtmRecommendedConfiguration = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmRecommendedConfiguration.setText("Recommended Configuration");
			trtmRecommendedConfiguration.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmRecommendedRulesshould = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmRecommendedRulesshould.setText("Recommended Rules (should have)");
			trtmRecommendedRulesshould.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmSupplementalRulesnice = new TreeItem(trtmParasoftsAep, SWT.NONE);
			trtmSupplementalRulesnice.setText("Supplemental Rules (nice to have)");
			trtmSupplementalRulesnice.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmParasoftsAep.setExpanded(true);

			TreeItem trtmScopeExamples = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmScopeExamples.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmScopeExamples.setText("Scope Examples");

			TreeItem trtmTestAllFiles = new TreeItem(trtmScopeExamples, SWT.NONE);
			trtmTestAllFiles.setText("Test All Files");
			trtmTestAllFiles.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmTestLocallyModified = new TreeItem(trtmScopeExamples, SWT.NONE);
			trtmTestLocallyModified.setText("Test Locally Modified");
			trtmTestLocallyModified.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmTestModifiedSince = new TreeItem(trtmScopeExamples, SWT.NONE);
			trtmTestModifiedSince.setText("Test Modified Since Installation");
			trtmTestModifiedSince.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmScopeExamples.setExpanded(true);

			TreeItem trtmSecurity = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmSecurity.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmSecurity.setText("Security");

			TreeItem trtmCwesansTop = new TreeItem(trtmSecurity, SWT.NONE);
			trtmCwesansTop.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCwesansTop.setText("CWE-SANS Top 25 Most Dangerous Programming Errors");

			TreeItem trtmCigitalJavaSecurity = new TreeItem(trtmSecurity, SWT.NONE);
			trtmCigitalJavaSecurity.setText("Cigital Java Security Rule Pack");
			trtmCigitalJavaSecurity.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmOwaspTop = new TreeItem(trtmSecurity, SWT.NONE);
			trtmOwaspTop.setText("OWASP Top 10 Security Vulnerabilities (Server Configuration)");
			trtmOwaspTop.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

			TreeItem trtmPciData = new TreeItem(trtmSecurity, SWT.NONE);
			trtmPciData.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmPciData.setText("PCI Data Security Standard (Server Configuration)");

			TreeItem trtmSecurityPriority_1 = new TreeItem(trtmSecurity, SWT.NONE);
			trtmSecurityPriority_1.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmSecurityPriority_1.setText("Security - Priority 1 - Must Have");

			TreeItem trtmSecurityPriority_2 = new TreeItem(trtmSecurity, SWT.NONE);
			trtmSecurityPriority_2.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmSecurityPriority_2.setText("Security - Priority 2 - Should Have");

			TreeItem trtmSecurityPriority_3 = new TreeItem(trtmSecurity, SWT.NONE);
			trtmSecurityPriority_3.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmSecurityPriority_3.setText("Security - Priority 3 - Nice to Have");

			TreeItem trtmSecurityAssessmentserver = new TreeItem(trtmSecurity, SWT.NONE);
			trtmSecurityAssessmentserver.setText("Security Assessment (Server Configuration)");
			trtmSecurityAssessmentserver.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmSecurity.setExpanded(true);

			TreeItem trtmStaticAnalysis = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmStaticAnalysis.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmStaticAnalysis.setText("Static Analysis");

			TreeItem trtmCodeConventionsFor = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmCodeConventionsFor.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCodeConventionsFor.setText("Code Conventions for the JavaTM Programming Language by Sun");

			TreeItem trtmCodeMellstdd = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmCodeMellstdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCodeMellstdd.setText("Code Smells (TDD)");

			TreeItem trtmCoreJeePatterns = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmCoreJeePatterns.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCoreJeePatterns.setText("Core J2EE Patterns by Alur, Cupri and Malks");

			TreeItem trtmEffectiveJavaBy = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmEffectiveJavaBy.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmEffectiveJavaBy.setText("Effective Java by Joshua Bloch");

			TreeItem trtmElementsOfJava = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmElementsOfJava.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmElementsOfJava.setText("Elements of Java Style by Scott Ambler");

			TreeItem trtmFindDuplicatedCode = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmFindDuplicatedCode.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmFindDuplicatedCode.setText("Find Duplicated Code");

			TreeItem trtmFindMemoryProblems = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmFindMemoryProblems.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmFindMemoryProblems.setText("Find Memory Problems");

			TreeItem trtmFindUnusedCode = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmFindUnusedCode.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmFindUnusedCode.setText("Find Unused Code");

			// #@# this metion jtest
			TreeItem trtmNewRulesIn84 = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmNewRulesIn84.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmNewRulesIn84.setText("New Rules in Jtest 8.4 (to be modified)");
			// #@# this metion jtest
			TreeItem trtmNewRulesIn8X = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmNewRulesIn8X.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmNewRulesIn8X.setText("New Rules in Jtest 8.x (to be modified)");
			trtmNewRulesIn8X.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			// #@# this metion jtest
			TreeItem trtmParasoftsRecommendedRules = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmParasoftsRecommendedRules.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmParasoftsRecommendedRules.setText("Parasoft's Recommended Rules (to be modified)");

			TreeItem trtmRulesForNew = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmRulesForNew.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmRulesForNew.setText("Rules for New Features in JDK 1.5 (to be modified)");

			TreeItem trtmTestHibernateCode = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmTestHibernateCode.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmTestHibernateCode.setText("Test Hibernate Code");

			TreeItem trtmThreadFeProgramming = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmThreadFeProgramming.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmThreadFeProgramming.setText("Thread Safe Programming");

			TreeItem trtmWritingRobustJava = new TreeItem(trtmStaticAnalysis, SWT.NONE);
			trtmWritingRobustJava.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmWritingRobustJava.setText("Writing Robust Java Code by AmbySoft");
			trtmStaticAnalysis.setExpanded(true);

			TreeItem trtmTestDrivenDevelopment = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmTestDrivenDevelopment.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmTestDrivenDevelopment.setText("Test Driven Development");

			TreeItem trtmCodeSmellstdd = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
			trtmCodeSmellstdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmCodeSmellstdd.setText("Code Smells (TDD Standards)");

			TreeItem trtmTdd = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
			trtmTdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmTdd.setText("TDD");

			TreeItem trtmTddWithDesign = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
			trtmTddWithDesign.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmTddWithDesign.setText("Tdd with Design by Contract");
			trtmTestDrivenDevelopment.setExpanded(true);

			TreeItem trtmUnitTesting = new TreeItem(builtinConfigTreeitem, SWT.NONE);
			trtmUnitTesting.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmUnitTesting.setText("Unit Testing");

			TreeItem trtmBugdetectivelicenseRequired = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmBugdetectivelicenseRequired.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmBugdetectivelicenseRequired.setText("BugDetective (License Required)");

			TreeItem trtmDemoConfiguration = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmDemoConfiguration.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmDemoConfiguration.setText("Demo Configuration");

			TreeItem trtmGenerateAndRun = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmGenerateAndRun.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmGenerateAndRun.setText("Generate and Run Unit Tests");

			TreeItem trtmMetrics = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmMetrics.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmMetrics.setText("Metrics");

			TreeItem trtmRunStaticAnalysis = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmRunStaticAnalysis.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmRunStaticAnalysis.setText("Run Static Analysis");

			TreeItem trtmRunStaticAnalysisAndTests = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmRunStaticAnalysisAndTests.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmRunStaticAnalysisAndTests.setText("Run Static Analysis and Unit Tests");

			TreeItem trtmRunUnitTests = new TreeItem(trtmUnitTesting, SWT.NONE);
			trtmRunUnitTests.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
			trtmRunUnitTests.setText("Run Unit Tests");

			trtmUnitTesting.setExpanded(true);
			builtinConfigTreeitem.setExpanded(true);

			TreeItem trtmTeam = new TreeItem(configTree, SWT.NONE);
			trtmTeam.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
			trtmTeam.setText("Team");

			scrolledComposite.setContent(configTree);
			scrolledComposite.setMinSize(configTree.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}

		// set right part of sash form
		StackLayout detaiLayout = new StackLayout();
		detailComposite.setLayout(detaiLayout);
		// when a configuration selected, this composite will appear in the
		// detail composite
		// otherwise the message "Select a configuration to view it." will
		// appear
		Composite oneConfigSelectedComposite = new Composite(detailComposite, SWT.NONE);
		oneConfigSelectedComposite.setLayout(new FormLayout());
		// set the composite contains name and parent components
		{
			Composite nameparentComposite = new Composite(oneConfigSelectedComposite, SWT.NONE);
			FormData fd_nameparentComposite = new FormData();
			fd_nameparentComposite.height = 65;
			fd_nameparentComposite.right = new FormAttachment(100);
			fd_nameparentComposite.left = new FormAttachment(0);
			nameparentComposite.setLayoutData(fd_nameparentComposite);
			nameparentComposite.setLayout(new FormLayout());

			Label lblName = new Label(nameparentComposite, SWT.CENTER);
			lblName.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
			FormData fd_lblName = new FormData();
			fd_lblName.width = 60;
			fd_lblName.height = 25;
			fd_lblName.top = new FormAttachment(0, 5);
			fd_lblName.left = new FormAttachment(0);
			lblName.setLayoutData(fd_lblName);
			lblName.setText("Name");

			Label lblParent = new Label(nameparentComposite, SWT.CENTER);
			lblParent.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 10, SWT.NORMAL));
			FormData fd_lblParent = new FormData();
			fd_lblParent.width = 60;
			fd_lblParent.height = 25;
			fd_lblParent.top = new FormAttachment(0, 35);
			lblParent.setLayoutData(fd_lblParent);
			lblParent.setText("Parent");

			textName = new Text(nameparentComposite, SWT.BORDER);
			FormData fd_textName = new FormData();
			fd_textName.right = new FormAttachment(100, -5);
			fd_textName.top = new FormAttachment(lblName, 0, SWT.TOP);
			fd_textName.left = new FormAttachment(lblName, 6);
			textName.setLayoutData(fd_textName);

			textParent = new Text(nameparentComposite, SWT.BORDER);
			FormData fd_textParent = new FormData();
			fd_textParent.right = new FormAttachment(100, -80);
			fd_textParent.top = new FormAttachment(textName, 6);
			fd_textParent.left = new FormAttachment(textName, 0, SWT.LEFT);
			textParent.setLayoutData(fd_textParent);

			Button btnDisconnect = new Button(nameparentComposite, SWT.NONE);
			btnDisconnect.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			FormData fd_btnDisconnect = new FormData();
			fd_btnDisconnect.bottom = new FormAttachment(100, -7);
			fd_btnDisconnect.right = new FormAttachment(100, -5);
			fd_btnDisconnect.width = 70;
			fd_btnDisconnect.height = 25;
			fd_btnDisconnect.top = new FormAttachment(0, 30);
			btnDisconnect.setLayoutData(fd_btnDisconnect);
			btnDisconnect.setText("Disconnect");

		}
		// set action composite in detail composite
		{
			Composite rightActionComposite = new Composite(oneConfigSelectedComposite, SWT.NONE);
			rightActionComposite.setLayout(new FormLayout());
			FormData fd_composite_2 = new FormData();
			fd_composite_2.right = new FormAttachment(100);
			fd_composite_2.bottom = new FormAttachment(100);
			fd_composite_2.height = 30;
			fd_composite_2.left = new FormAttachment(0, 6);
			rightActionComposite.setLayoutData(fd_composite_2);

			Button btnApply = new Button(rightActionComposite, SWT.NONE);
			FormData fd_btnApply = new FormData();
			fd_btnApply.width = 60;
			fd_btnApply.right = new FormAttachment(100, -5);
			btnApply.setLayoutData(fd_btnApply);
			btnApply.setText("Apply");

			Button btnRevert = new Button(rightActionComposite, SWT.NONE);
			FormData fd_btnRevert = new FormData();
			fd_btnRevert.right = new FormAttachment(btnApply);
			fd_btnRevert.width = 60;
			btnRevert.setLayoutData(fd_btnRevert);
			btnRevert.setText("Revert");
		}

		// set detail configuration page
		{
			TabFolder tabFolder = new TabFolder(oneConfigSelectedComposite, SWT.NONE);
			FormData fd_tabFolder = new FormData();
			fd_tabFolder.bottom = new FormAttachment(100, -35);
			fd_tabFolder.top = new FormAttachment(0, 70);
			fd_tabFolder.right = new FormAttachment(100, -5);
			fd_tabFolder.left = new FormAttachment(0, 5);
			tabFolder.setLayoutData(fd_tabFolder);

			// add scope tab into right part of sashForm
			{
				TabItem tbtmScope = new TabItem(tabFolder, SWT.NONE);
				tbtmScope.setImage(SWTResourceManager.getImage(Const.SCOPE_ICON_PATH));
				tbtmScope.setText("Scope");

				ScrolledComposite scrolledCompositeScope = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
				tbtmScope.setControl(scrolledCompositeScope);
				scrolledCompositeScope.setExpandHorizontal(true);
				scrolledCompositeScope.setExpandVertical(true);

				Composite compositeInScrolledCompositeScope = new Composite(scrolledCompositeScope, SWT.NONE);
				compositeInScrolledCompositeScope.setLayout(new FormLayout());

				TabFolder tabFolderInScope = new TabFolder(compositeInScrolledCompositeScope, SWT.NONE);
				FormData fd_tabFolderInScope = new FormData();
				fd_tabFolderInScope.right = new FormAttachment(100, -10);
				fd_tabFolderInScope.top = new FormAttachment(0, 10);
				fd_tabFolderInScope.left = new FormAttachment(0, 10);
				tabFolderInScope.setLayoutData(fd_tabFolderInScope);

				// add and set File Filter in Scope

				{
					TabItem tbtmFileFilter = new TabItem(tabFolderInScope, SWT.NONE);
					tbtmFileFilter.setText("File Filters");

					Composite fileFilterComposite = new Composite(tabFolderInScope, SWT.NONE);
					tbtmFileFilter.setControl(fileFilterComposite);
					fileFilterComposite.setLayout(new FormLayout());

					Group grpTimeOptions = new Group(fileFilterComposite, SWT.NONE);
					grpTimeOptions.setText("Time options");
					grpTimeOptions.setLayout(new FormLayout());
					FormData fd_grpTimeOptions = new FormData();
					fd_grpTimeOptions.right = new FormAttachment(100, -5);
					fd_grpTimeOptions.left = new FormAttachment(0, 5);
					fd_grpTimeOptions.top = new FormAttachment(0, 5);
					grpTimeOptions.setLayoutData(fd_grpTimeOptions);

					Button btnNoTimeFilters = new Button(grpTimeOptions, SWT.RADIO);
					FormData fd_btnNoTimeFilters = new FormData();
					fd_btnNoTimeFilters.top = new FormAttachment(0, 10);
					btnNoTimeFilters.setLayoutData(fd_btnNoTimeFilters);
					btnNoTimeFilters.setText("No time filters");

					Button btnSinceDate = new Button(grpTimeOptions, SWT.RADIO);
					FormData fd_btnSinceDate = new FormData();
					fd_btnSinceDate.top = new FormAttachment(btnNoTimeFilters, 5);
					btnSinceDate.setLayoutData(fd_btnSinceDate);
					btnSinceDate.setText("Test only files added or modified since the cutoff date");

					DateTime sinceDateTime = new DateTime(grpTimeOptions, SWT.BORDER);
					FormData fd_dateTime = new FormData();
					fd_dateTime.left = new FormAttachment(btnSinceDate, 15);
					fd_dateTime.top = new FormAttachment(btnNoTimeFilters);
					sinceDateTime.setLayoutData(fd_dateTime);

					Button btnTileDate = new Button(grpTimeOptions, SWT.CHECK);
					FormData fd_btnTileDate = new FormData();
					fd_btnTileDate.top = new FormAttachment(sinceDateTime, 5);
					fd_btnTileDate.left = new FormAttachment(0, 15);
					btnTileDate.setLayoutData(fd_btnTileDate);
					btnTileDate.setText("and added or modified before (now when disabled)");

					DateTime tileDateTime = new DateTime(grpTimeOptions, SWT.BORDER);
					FormData fd_TileDataTime = new FormData();
					fd_TileDataTime.top = new FormAttachment(btnSinceDate, 3);
					fd_TileDataTime.left = new FormAttachment(sinceDateTime, 0, SWT.LEFT);
					tileDateTime.setLayoutData(fd_TileDataTime);

					Button btnTestFilesInLast = new Button(grpTimeOptions, SWT.RADIO);
					FormData fd_btnTestFilesInLast = new FormData();
					fd_btnTestFilesInLast.top = new FormAttachment(btnTileDate, 5);
					btnTestFilesInLast.setLayoutData(fd_btnTestFilesInLast);
					btnTestFilesInLast.setText("Test only files added or modified in the last");

					text_lastDaysInLineFilter = new Text(grpTimeOptions, SWT.BORDER);
					FormData fd_text_lastDays = new FormData();
					fd_text_lastDays.top = new FormAttachment(btnTileDate, 3);
					fd_text_lastDays.left = new FormAttachment(btnTestFilesInLast, 5);
					text_lastDaysInLineFilter.setLayoutData(fd_text_lastDays);

					Label lblDays = new Label(grpTimeOptions, SWT.NONE);
					FormData fd_lblDays = new FormData();
					fd_lblDays.top = new FormAttachment(btnTileDate, 3);
					fd_lblDays.left = new FormAttachment(text_lastDaysInLineFilter, 5);
					lblDays.setLayoutData(fd_lblDays);
					lblDays.setText("days");

					Button btnTestLocalFile = new Button(grpTimeOptions, SWT.RADIO);
					FormData fd_btnTestLocalFile = new FormData();
					fd_btnTestLocalFile.bottom = new FormAttachment(100, -5);
					fd_btnTestLocalFile.top = new FormAttachment(btnTestFilesInLast, 5);
					btnTestLocalFile.setLayoutData(fd_btnTestLocalFile);
					btnTestLocalFile.setText("Test only files added or modified locally");

					Group grpAuthorOptions = new Group(fileFilterComposite, SWT.NONE);
					grpAuthorOptions.setText("Author options");
					grpAuthorOptions.setLayout(new FormLayout());
					FormData fd_grpAuthorOptions = new FormData();
					fd_grpAuthorOptions.left = new FormAttachment(0, 5);
					fd_grpAuthorOptions.right = new FormAttachment(100, -5);
					fd_grpAuthorOptions.bottom = new FormAttachment(100, -10);
					fd_grpAuthorOptions.top = new FormAttachment(grpTimeOptions, 10);
					grpAuthorOptions.setLayoutData(fd_grpAuthorOptions);

					Button btnNoAuthorFilters = new Button(grpAuthorOptions, SWT.RADIO);
					FormData fd_btnNoAuthorFilters = new FormData();
					fd_btnNoAuthorFilters.top = new FormAttachment(0, 10);
					btnNoAuthorFilters.setLayoutData(fd_btnNoAuthorFilters);
					btnNoAuthorFilters.setText("No author filters");

					Button btnFilesAuthoredByUser = new Button(grpAuthorOptions, SWT.RADIO);
					FormData fd_btnRadioButton = new FormData();
					fd_btnRadioButton.top = new FormAttachment(btnNoAuthorFilters, 5);
					fd_btnRadioButton.bottom = new FormAttachment(100, -5);
					btnFilesAuthoredByUser.setLayoutData(fd_btnRadioButton);
					btnFilesAuthoredByUser.setText("Test only files authored by user");

					text_authorNameInLineFilter = new Text(grpAuthorOptions, SWT.BORDER);
					FormData fd_text = new FormData();
					fd_text.top = new FormAttachment(btnNoAuthorFilters, 5);
					fd_text.left = new FormAttachment(btnFilesAuthoredByUser, 5);
					fd_text.bottom = new FormAttachment(100, -5);
					text_authorNameInLineFilter.setLayoutData(fd_text);
				}

				// add and set Line Filter in Scope
				{
					TabItem tbtmLineFilters = new TabItem(tabFolderInScope, SWT.NONE);
					tbtmLineFilters.setText("Line Filters");

					Composite lineFilterComposite = new Composite(tabFolderInScope, SWT.NONE);
					tbtmLineFilters.setControl(lineFilterComposite);
					lineFilterComposite.setLayout(new FormLayout());

					Group grpTimeOptionsInLineFilter = new Group(lineFilterComposite, SWT.NONE);
					grpTimeOptionsInLineFilter.setText("Time options");
					grpTimeOptionsInLineFilter.setLayout(new FormLayout());
					FormData fd_grpTimeOptions = new FormData();
					fd_grpTimeOptions.right = new FormAttachment(100, -5);
					fd_grpTimeOptions.left = new FormAttachment(0, 5);
					fd_grpTimeOptions.top = new FormAttachment(0, 5);
					grpTimeOptionsInLineFilter.setLayoutData(fd_grpTimeOptions);

					Button btnNoTimeFiltersInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnNoTimeFilters = new FormData();
					fd_btnNoTimeFilters.top = new FormAttachment(0, 10);
					btnNoTimeFiltersInLineFilter.setLayoutData(fd_btnNoTimeFilters);
					btnNoTimeFiltersInLineFilter.setText("No time filters");

					Button btnSinceDateInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnSinceDate = new FormData();
					fd_btnSinceDate.top = new FormAttachment(btnNoTimeFiltersInLineFilter, 5);
					btnSinceDateInLineFilter.setLayoutData(fd_btnSinceDate);
					btnSinceDateInLineFilter.setText("Test only lines added or modified since the cutoff date");

					DateTime sinceDateTimeInLineFilter = new DateTime(grpTimeOptionsInLineFilter, SWT.BORDER);
					FormData fd_dateTime = new FormData();
					fd_dateTime.left = new FormAttachment(btnSinceDateInLineFilter, 15);
					fd_dateTime.top = new FormAttachment(btnNoTimeFiltersInLineFilter);
					sinceDateTimeInLineFilter.setLayoutData(fd_dateTime);

					Button btnTestFilesInLastInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnTestFilesInLast = new FormData();
					fd_btnTestFilesInLast.top = new FormAttachment(btnSinceDateInLineFilter, 7);
					btnTestFilesInLastInLineFilter.setLayoutData(fd_btnTestFilesInLast);
					btnTestFilesInLastInLineFilter.setText("Test only lines added or modified in the last");

					text_lastDaysInLineFilter = new Text(grpTimeOptionsInLineFilter, SWT.BORDER);
					FormData fd_text_lastDays = new FormData();
					fd_text_lastDays.top = new FormAttachment(btnSinceDateInLineFilter, 5);
					fd_text_lastDays.left = new FormAttachment(btnTestFilesInLastInLineFilter, 5);
					text_lastDaysInLineFilter.setLayoutData(fd_text_lastDays);

					Label lblDays = new Label(grpTimeOptionsInLineFilter, SWT.NONE);
					FormData fd_lblDays = new FormData();
					fd_lblDays.top = new FormAttachment(btnSinceDateInLineFilter, 7);
					fd_lblDays.left = new FormAttachment(text_lastDaysInLineFilter, 5);
					lblDays.setLayoutData(fd_lblDays);
					lblDays.setText("days");

					Button btnTestLocalFileInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnTestLocalFile = new FormData();
					fd_btnTestLocalFile.bottom = new FormAttachment(100, -5);
					fd_btnTestLocalFile.top = new FormAttachment(btnTestFilesInLastInLineFilter, 5);
					btnTestLocalFileInLineFilter.setLayoutData(fd_btnTestLocalFile);
					btnTestLocalFileInLineFilter.setText("Test only lines added or modified locally");

					Group grpAuthorOptionsInLineFilter = new Group(lineFilterComposite, SWT.NONE);
					grpAuthorOptionsInLineFilter.setText("Author options");
					grpAuthorOptionsInLineFilter.setLayout(new FormLayout());
					FormData fd_grpAuthorOptions = new FormData();
					fd_grpAuthorOptions.top = new FormAttachment(grpTimeOptionsInLineFilter, 10);
					fd_grpAuthorOptions.left = new FormAttachment(0, 5);
					fd_grpAuthorOptions.right = new FormAttachment(100, -5);
					fd_grpAuthorOptions.bottom = new FormAttachment(100, -10);
					grpAuthorOptionsInLineFilter.setLayoutData(fd_grpAuthorOptions);

					Button btnNoAuthorFiltersInLineFilter = new Button(grpAuthorOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnNoAuthorFilters = new FormData();
					fd_btnNoAuthorFilters.top = new FormAttachment(0, 10);
					btnNoAuthorFiltersInLineFilter.setLayoutData(fd_btnNoAuthorFilters);
					btnNoAuthorFiltersInLineFilter.setText("No author filters");

					Button btnFilesAuthoredByUserInLineFilter = new Button(grpAuthorOptionsInLineFilter, SWT.RADIO);
					FormData fd_btnRadioButton = new FormData();
					fd_btnRadioButton.top = new FormAttachment(btnNoAuthorFiltersInLineFilter, 5);
					btnFilesAuthoredByUserInLineFilter.setLayoutData(fd_btnRadioButton);
					btnFilesAuthoredByUserInLineFilter.setText("Test only lines authored by user");

					text_authorNameInLineFilter = new Text(grpAuthorOptionsInLineFilter, SWT.BORDER);
					FormData fd_text = new FormData();
					fd_text.top = new FormAttachment(btnNoAuthorFiltersInLineFilter, 5);
					fd_text.left = new FormAttachment(btnFilesAuthoredByUserInLineFilter, 5);
					text_authorNameInLineFilter.setLayoutData(fd_text);
				}
				// add and set Method Filters in Scope
				{
					TabItem tbtmMethodFilters = new TabItem(tabFolderInScope, SWT.NONE);
					tbtmMethodFilters.setText("Method Filters");

					Composite methodFiltersComposite = new Composite(tabFolderInScope, SWT.NONE);
					tbtmMethodFilters.setControl(methodFiltersComposite);
					methodFiltersComposite.setLayout(new FormLayout());

					Button btnSkipMethedByPattern = new Button(methodFiltersComposite, SWT.CHECK);
					FormData fd_btnCheckButton = new FormData();
					fd_btnCheckButton.top = new FormAttachment(0, 5);
					fd_btnCheckButton.right = new FormAttachment(100, -5);
					fd_btnCheckButton.left = new FormAttachment(0, 5);
					btnSkipMethedByPattern.setLayoutData(fd_btnCheckButton);
					btnSkipMethedByPattern.setText("Skip methods with names that match one of the following regular expressions");

					tableMethodsPattern = new Table(methodFiltersComposite, SWT.BORDER | SWT.FULL_SELECTION);
					FormData fd_table = new FormData();
					fd_table.left = new FormAttachment(btnSkipMethedByPattern, 5, SWT.LEFT);
					fd_table.right = new FormAttachment(100, -70);
					fd_table.bottom = new FormAttachment(100, -5);
					fd_table.top = new FormAttachment(btnSkipMethedByPattern, 5);
					tableMethodsPattern.setLayoutData(fd_table);
					tableMethodsPattern.setHeaderVisible(true);
					tableMethodsPattern.setLinesVisible(true);

					TableColumn tblclmnMethodPattern = new TableColumn(tableMethodsPattern, SWT.NONE);
					tblclmnMethodPattern.setWidth(210);
					tblclmnMethodPattern.setText("Method name regular expression");

					Button btnAddMethodPattern = new Button(methodFiltersComposite, SWT.NONE);
					FormData fd_btnAddMethodPattern = new FormData();
					fd_btnAddMethodPattern.left = new FormAttachment(tableMethodsPattern, 5);
					fd_btnAddMethodPattern.width = 60;
					fd_btnAddMethodPattern.top = new FormAttachment(btnSkipMethedByPattern, 5);
					btnAddMethodPattern.setLayoutData(fd_btnAddMethodPattern);
					btnAddMethodPattern.setText("Add");

					Button btnRemoveMethodPattern = new Button(methodFiltersComposite, SWT.NONE);
					FormData fd_btnRemoveMethodPattern = new FormData();
					fd_btnRemoveMethodPattern.width = 60;
					fd_btnRemoveMethodPattern.left = new FormAttachment(btnAddMethodPattern, 0, SWT.LEFT);
					fd_btnRemoveMethodPattern.top = new FormAttachment(btnAddMethodPattern, 5);
					btnRemoveMethodPattern.setLayoutData(fd_btnRemoveMethodPattern);
					btnRemoveMethodPattern.setText("Remove");
				}

				// add and set bottom composite in Scope
				{
					Composite bottomCompositeInScope = new Composite(compositeInScrolledCompositeScope, SWT.NONE);
					bottomCompositeInScope.setLayout(new FormLayout());
					FormData fd_bottomCompositeInScope = new FormData();
					fd_bottomCompositeInScope.top = new FormAttachment(tabFolderInScope, 10);
					fd_bottomCompositeInScope.right = new FormAttachment(100, -10);
					fd_bottomCompositeInScope.left = new FormAttachment(0, 10);
					bottomCompositeInScope.setLayoutData(fd_bottomCompositeInScope);

					Button btnLimitSimpleMethods = new Button(bottomCompositeInScope, SWT.CHECK);
					FormData fd_btnLimitSimpleMethods = new FormData();
					fd_btnLimitSimpleMethods.top = new FormAttachment(0, 3);
					btnLimitSimpleMethods.setLayoutData(fd_btnLimitSimpleMethods);
					btnLimitSimpleMethods.setText("Do not test methods with cyclomatic complexity less than");

					Button btnLimitsDeprecatedClassMethod = new Button(bottomCompositeInScope, SWT.CHECK);
					FormData fd_btnLimitsDeprecatedClassMethod = new FormData();
					fd_btnLimitsDeprecatedClassMethod.bottom = new FormAttachment(100, -10);
					fd_btnLimitsDeprecatedClassMethod.top = new FormAttachment(btnLimitSimpleMethods, 5);
					btnLimitsDeprecatedClassMethod.setLayoutData(fd_btnLimitsDeprecatedClassMethod);
					btnLimitsDeprecatedClassMethod.setText("Do not test @deprecated classes or methods");

					text_minMethodNum = new Text(bottomCompositeInScope, SWT.BORDER);
					FormData fd_text_minMethodNum = new FormData();
					fd_text_minMethodNum.left = new FormAttachment(btnLimitSimpleMethods, 5);
					text_minMethodNum.setLayoutData(fd_text_minMethodNum);

					scrolledCompositeScope.setContent(compositeInScrolledCompositeScope);
					scrolledCompositeScope.setMinSize(compositeInScrolledCompositeScope.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}

			// to be complete
			TabItem tbtmStatic = new TabItem(tabFolder, SWT.NONE);
			tbtmStatic.setImage(SWTResourceManager.getImage(Const.STATIC_ICON_PATH));
			tbtmStatic.setText("Static");

			// add Generation tab into right part of sashForm

			TabItem tbtmGeneration = new TabItem(tabFolder, SWT.NONE);
			tbtmGeneration.setImage(SWTResourceManager.getImage(Const.GENERATION_ICON_PATH));
			tbtmGeneration.setText("Generation");

			ScrolledComposite scrolledCompositeGeneration = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
			tbtmGeneration.setControl(scrolledCompositeGeneration);
			scrolledCompositeGeneration.setExpandHorizontal(true);
			scrolledCompositeGeneration.setExpandVertical(true);

			Composite compositeInScrolledCompositeGeneration = new Composite(scrolledCompositeGeneration, SWT.NONE);
			compositeInScrolledCompositeGeneration.setLayout(new FormLayout());

			Button btnEnableUnitTestGeneration = new Button(compositeInScrolledCompositeGeneration, SWT.CHECK);
			FormData fd_btnEnableUnitTestGeneration = new FormData();
			fd_btnEnableUnitTestGeneration.top = new FormAttachment(0, 5);
			fd_btnEnableUnitTestGeneration.left = new FormAttachment(0, 5);
			btnEnableUnitTestGeneration.setLayoutData(fd_btnEnableUnitTestGeneration);
			btnEnableUnitTestGeneration.setText("Enable Unit Test Generation");

			TabFolder tabFolderInGeneration = new TabFolder(compositeInScrolledCompositeGeneration, SWT.NONE);
			FormData fd_tabFolderInGeneration = new FormData();
			fd_tabFolderInGeneration.top = new FormAttachment(btnEnableUnitTestGeneration, 10);
			fd_tabFolderInGeneration.left = new FormAttachment(btnEnableUnitTestGeneration, 0, SWT.LEFT);
			fd_tabFolderInGeneration.bottom = new FormAttachment(100, -5);
			fd_tabFolderInGeneration.right = new FormAttachment(100, -5);
			tabFolderInGeneration.setLayoutData(fd_tabFolderInGeneration);

			TabItem tbtmInputs = new TabItem(tabFolderInGeneration, SWT.NONE);
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

			// add components related with "Connecting to a Running Applications:"
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
				
				TabItem tbtmFilter = new TabItem(tabFolderInGeneration, SWT.NONE);
				tbtmFilter.setText("Filter");
				
				Composite filterInGenerationComposite = new Composite(tabFolderInGeneration, SWT.NONE);
				tbtmFilter.setControl(filterInGenerationComposite);

			scrolledCompositeGeneration.setContent(compositeInScrolledCompositeGeneration);
			scrolledCompositeGeneration.setMinSize(compositeInScrolledCompositeGeneration.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			
			TabItem tbtmExecution = new TabItem(tabFolder, SWT.NONE);
			tbtmExecution.setImage(SWTResourceManager.getImage(Const.EXECUTION_ICON_PATH));
			tbtmExecution.setText("Execution");

			TabItem tbtmCommon = new TabItem(tabFolder, SWT.NONE);
			tbtmCommon.setImage(SWTResourceManager.getImage(Const.COMMON_ICON_PATH));
			tbtmCommon.setText("Common");

			TabItem tbtmCodeReview = new TabItem(tabFolder, SWT.NONE);
			tbtmCodeReview.setImage(SWTResourceManager.getImage(Const.CODEREVIEW_ICON_PATH));
			tbtmCodeReview.setText("Code Review");

			TabItem tbtmGoals = new TabItem(tabFolder, SWT.NONE);
			tbtmGoals.setImage(SWTResourceManager.getImage(Const.GOALS_ICON_PATH));
			tbtmGoals.setText("Goals");

		}
		// this must be called when detail composite generated totally.
		detaiLayout.topControl = oneConfigSelectedComposite;

	}
}
