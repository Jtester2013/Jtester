package plugin.ui.window.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class ConfigTree {
	// configTreeFilePath point to the xml file which describe the configTree's
	// constructor
	public static String configTreeFilePath = System.getProperty("user.dir") + "\\src\\plugin\\ui\\window\\configuration\\configuration_list.xml";
	
	public Tree tree;
	public static TreeItem trtmUser;
	public static TreeItem trtmBuiltin;
	public static TreeItem trtmTeam;
	
	public static TreeItem selectedItem = null;

	private Action duplicateAction, newAction, deleteAction, exportAction, setAsDefaultAction;// 作用于树节点上的actions

	// todo: duplicate, New child, Delete, export, set as default
	private static String defaultConfigName = "N";

	public ConfigTree(Composite parent, int style) {
		// TODO Auto-generated constructor stub
		tree = new Tree(parent, style);
		trtmUser = new TreeItem(tree, SWT.NONE);
		trtmUser.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmUser.setText("User-defined");
		trtmUser.setExpanded(true);

		trtmBuiltin = new TreeItem(tree, SWT.NONE);
		trtmBuiltin.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmBuiltin.setText("Builtin");
		trtmBuiltin.setExpanded(true);

		
	}

	protected void contentCreate(){
		trtmTeam = new TreeItem(tree, SWT.NONE);
		trtmTeam.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmTeam.setText("Team");
		trtmTeam.setExpanded(true);
//
		TreeItem trtmCodeReview = new TreeItem(trtmBuiltin, SWT.NONE);
		trtmCodeReview.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmCodeReview.setText("Code Review");

		TreeItem trtmPostcommit = new TreeItem(trtmCodeReview, SWT.NONE);
		trtmPostcommit.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmPostcommit.setText("Post-Commit");

		TreeItem trtmPrecommit = new TreeItem(trtmCodeReview, SWT.NONE);
		trtmPrecommit.setText("Pre-Commit");
		trtmPrecommit.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));

		trtmCodeReview.setExpanded(true);

		TreeItem trtmParasoftsAep = new TreeItem(trtmBuiltin, SWT.NONE);
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

		TreeItem trtmScopeExamples = new TreeItem(trtmBuiltin, SWT.NONE);
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

		TreeItem trtmSecurity = new TreeItem(trtmBuiltin, SWT.NONE);
		trtmSecurity.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmSecurity.setText("Security");

		TreeItem trtmCwesansTop = new TreeItem(trtmSecurity, SWT.NONE);
		trtmCwesansTop.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmCwesansTop.setText("CWE-SANS Top 25 Most Dangerous Programming Errors");

		TreeItem trtmCigitalJavaSecurity = new TreeItem(trtmSecurity, SWT.NONE);
		trtmCigitalJavaSecurity.setText("Cigital Java Security Rule Pack");
		trtmCigitalJavaSecurity.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//
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

		TreeItem trtmStaticAnalysis = new TreeItem(trtmBuiltin, SWT.NONE);
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
		
		TreeItem trtmThreadFeProgramming = new TreeItem(trtmBuiltin, SWT.NONE);
		trtmThreadFeProgramming.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmThreadFeProgramming.setText("Thread Safe Programming");
		TreeItem trtmCodeSmellstdd = new TreeItem(trtmBuiltin, SWT.NONE);
		trtmCodeSmellstdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmCodeSmellstdd.setText("Code Smells (TDD Standards)");
		
		TreeItem trtmDemoConfiguration = new TreeItem(trtmUser, SWT.NONE);
		trtmDemoConfiguration.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmDemoConfiguration.setText("Demo Configuration");
		
		TreeItem trtmRunStaticAnalysisAndTests = new TreeItem(trtmBuiltin, SWT.NONE);
		trtmRunStaticAnalysisAndTests.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmRunStaticAnalysisAndTests.setText("Run Static Analysis and Unit Tests");

//		TreeItem trtmThreadFeProgramming = new TreeItem(trtmStaticAnalysis, SWT.NONE);
//		trtmThreadFeProgramming.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmThreadFeProgramming.setText("Thread Safe Programming");
//
//		TreeItem trtmWritingRobustJava = new TreeItem(trtmStaticAnalysis, SWT.NONE);
//		trtmWritingRobustJava.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmWritingRobustJava.setText("Writing Robust Java Code by AmbySoft");
//		trtmStaticAnalysis.setExpanded(true);
//
//		TreeItem trtmTestDrivenDevelopment = new TreeItem(trtmBuiltin, SWT.NONE);
//		trtmTestDrivenDevelopment.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
//		trtmTestDrivenDevelopment.setText("Test Driven Development");
//
//		TreeItem trtmCodeSmellstdd = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
//		trtmCodeSmellstdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmCodeSmellstdd.setText("Code Smells (TDD Standards)");
//
//		TreeItem trtmTdd = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
//		trtmTdd.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmTdd.setText("TDD");
//
//		TreeItem trtmTddWithDesign = new TreeItem(trtmTestDrivenDevelopment, SWT.NONE);
//		trtmTddWithDesign.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmTddWithDesign.setText("Tdd with Design by Contract");
//		trtmTestDrivenDevelopment.setExpanded(true);
//
//		TreeItem trtmUnitTesting = new TreeItem(trtmBuiltin, SWT.NONE);
//		trtmUnitTesting.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
//		trtmUnitTesting.setText("Unit Testing");
//
//		TreeItem trtmBugdetectivelicenseRequired = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmBugdetectivelicenseRequired.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmBugdetectivelicenseRequired.setText("BugDetective (License Required)");
//
//		TreeItem trtmDemoConfiguration = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmDemoConfiguration.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmDemoConfiguration.setText("Demo Configuration");
//
//		TreeItem trtmGenerateAndRun = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmGenerateAndRun.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmGenerateAndRun.setText("Generate and Run Unit Tests");
//
//		TreeItem trtmMetrics = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmMetrics.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmMetrics.setText("Metrics");
//
//		TreeItem trtmRunStaticAnalysis = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmRunStaticAnalysis.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmRunStaticAnalysis.setText("Run Static Analysis");
//
//		TreeItem trtmRunStaticAnalysisAndTests = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmRunStaticAnalysisAndTests.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmRunStaticAnalysisAndTests.setText("Run Static Analysis and Unit Tests");
//
//		TreeItem trtmRunUnitTests = new TreeItem(trtmUnitTesting, SWT.NONE);
//		trtmRunUnitTests.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
//		trtmRunUnitTests.setText("Run Unit Tests");
//
//		trtmUnitTesting.setExpanded(true);

		// add event listener to tree
		tree.addMouseListener(new MouseAdapter() {
//			public void mouseDoubleClick(MouseEvent evt) {
//				treeMouseDoubleClick(evt);
//			}

			public void mouseDown(MouseEvent evt) {
				boolean isTreeitem = evt.getSource() instanceof Tree;
				selectedItem = ((Tree)(evt.getSource())).getSelection()[0];
				System.out.println(selectedItem.getText());
				if (isTreeitem) {
					initMenu(evt);
				}
			}

		});
		
		
	/*	// 懒加载
	   tree.addListener(SWT.Expand, new Listener () {  
            public void handleEvent (final Event event) {  
                final TreeItem root = (TreeItem) event.item;  
                TreeItem[] items = root.getItems ();  
                for(int i= 0; i<items.length; i++) {  
                    // if have added children for the item, just return.  
                    if(items[i].getData() != null)  
                        return;  
                    items[i].dispose();  
                }  
                  
                TreeItem item = (TreeItem) root.get
                TreeItem[] childrenItems = item.getItems();
                  
                // disc return  
                if(childrenItems == null) return;  
                for(int i= 0; i<childrenItems.length; i++) {  
                    TreeItem item = new TreeItem(root, 0);  
                    item.setText(files[i].getName());  
                    item.setData(files[i]);  
                    if(files[i].isDirectory()) {  
                        // display '+' only for directory.  
                        new TreeItem(item, 0);  
                    }  
                }  
            }  
        });  */
	}
	public  void initMenu(MouseEvent event) {
		initializeMenu();
	}

	private void initializeMenu()  {
		// test eclipse path
		/*File file = new File("D:\\testetet.txt");
		PrintStream writer;
		try {
			writer = new PrintStream(file);
			System.setOut(writer);
			System.out.print(Platform.getInstallLocation());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		createActions();
		MenuManager mgr = new MenuManager();
		
		mgr.add(newAction);
		mgr.add(duplicateAction);
		mgr.add(deleteAction);
		mgr.add(exportAction);
		mgr.add(setAsDefaultAction);
		
		Menu menu = mgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	/*
	 * 事件处理方法
	 */
	private void createActions() {
		newAction = new Action("新建") {
			public void run() {
				// .........
			}
		};
		duplicateAction = new Action("复制") {
			public void run() {
				// .........
				TreeItem item = new TreeItem(trtmUser, SWT.None);
				item.setImage(selectedItem.getImage());
				item.setText(selectedItem.getText());
			}
		};
		deleteAction = new Action("删除") {
			public void run() {
				// .........
			}
		};

		exportAction = new Action("导出") {
			public void run() {
				// .........
			}
		};
		setAsDefaultAction = new Action("设为默认") {
			public void run() {
				// .........
			}
		};

	}

	/**
	 * buid tree from a existing configFile
	 * 
	 * @param filePath
	 */
	private void constructTreeFromConfigFile(String filePath) {
		// 逐条读取config，并实例化。添加相应的图标，设置相应的菜单，注册事件。

	}

	/**
	 * add one tree item to a specified tree item
	 * 
	 * @param parent
	 *            the tree-item that new item attached
	 * @param name
	 *            new item's name
	 * @param expandable
	 *            tree:the new item can be attached by another item
	 */
	public void addTreeItem(TreeItem parent, String name, boolean expandable) {
		TreeItem newItem = new TreeItem(parent, SWT.NONE);
		String imagePath;
		if (expandable) {
			imagePath = "";
		} else {
			imagePath = "";
		}
		newItem.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		newItem.setText(name);
	}

	private boolean removeConfig(String configName) {
		// 删除对应的配置文件

		// 删除config tree的对应项

		return true;
	}

	private boolean createConfig(String configName) {
		// 创建对应的配置文件

		// 创建config tree的对应项

		return true;
	}

	private void addNode(Document configList, String configFileRootPath, String configName) {

	}

}