package plugin.ui.window.configuration;

import java.io.FileInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class ConfigTree {
	// configTreeFilePath point to the xml file which describe the configTree's
	// constructor
	String configTreeFilePath = "";
	public Tree tree;
	public TreeItem trtmUser;
	public TreeItem trtmBuiltin;
	public TreeItem trtmTeam;

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
		

		trtmTeam = new TreeItem(tree, SWT.NONE);
		trtmTeam.setImage(SWTResourceManager.getImage(Const.FOLDER_ICON_PATH));
		trtmTeam.setText("Team");
		trtmTeam.setExpanded(true);
		
		
		
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

		TreeItem trtmThreadFeProgramming = new TreeItem(trtmStaticAnalysis, SWT.NONE);
		trtmThreadFeProgramming.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmThreadFeProgramming.setText("Thread Safe Programming");

		TreeItem trtmWritingRobustJava = new TreeItem(trtmStaticAnalysis, SWT.NONE);
		trtmWritingRobustJava.setImage(SWTResourceManager.getImage(Const.HYPERCUBE_ICON_PATH));
		trtmWritingRobustJava.setText("Writing Robust Java Code by AmbySoft");
		trtmStaticAnalysis.setExpanded(true);

		TreeItem trtmTestDrivenDevelopment = new TreeItem(trtmBuiltin, SWT.NONE);
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

		TreeItem trtmUnitTesting = new TreeItem(trtmBuiltin, SWT.NONE);
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

	}

	/**
	 * buid tree from a existing configFile
	 * 
	 * @param filePath
	 */
	private void constructTreeFromConfigFile(String filePath) {

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

	}

	public static void main(String[] args) {
		String configListPath = Const.rootPath + "\\src\\configuration_list.xml";
		Document doc;
		DocumentBuilderFactory factory;
		DocumentBuilder docBuilder;
		Element root;
		String elementName;
		FileInputStream in;
		try {
			// get the xml file
			in = new FileInputStream(configListPath);
			// 解析XML文件,生成document对象
			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			docBuilder = factory.newDocumentBuilder();
			doc = docBuilder.parse(in);
			// 解析成功
			System.out.println("parse successfull");
			// 获取XML文档的根节点
			root = doc.getDocumentElement();
			elementName = root.getNodeName();
			// 打印根节点的属性
			printAttributes(root);
			// 打印该文档全部节点
			System.out.println("打印全部节点");
			printElement(root, 0);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	// 打印某个节点的全部属性
	public static void printAttributes(Element elem) {
		NamedNodeMap attributes;
		int i, max;
		String name, value;
		Node curNode;

		attributes = elem.getAttributes();
		max = attributes.getLength();

		for (i = 0; i < max; i++) {
			curNode = attributes.item(i);
			name = curNode.getNodeName();
			value = curNode.getNodeValue();
			System.out.println("\t" + name + " = " + value);
		}
	}

	// 打印所有的节点的名称和值
	// 改方法采用递归方式打印文档的全部节点
	public static void printElement(Element elem, int depth) {
		String elementName;
		NodeList children;
		int i, max;
		Node curChild;
		Element curElement;
		String nodeName, nodeValue;

		// elementName = elem.getNodeName();
		// 获取输入节点的全部子节点
		children = elem.getChildNodes();

		// 按一定格式打印输入节点
		for (int j = 0; j < depth; j++) {
			System.out.print(" ");
		}
		printAttributes(elem);

		// 采用递归方式打印全部子节点
		max = children.getLength();
		for (i = 0; i < max; i++) {

			curChild = children.item(i);

			// 递归退出条件
			if (curChild instanceof Element) {
				curElement = (Element) curChild;
				printElement(curElement, depth + 1);
			} else {
				nodeName = curChild.getNodeName();
				nodeValue = curChild.getNodeValue();

				for (int j = 0; j < depth; j++)
					System.out.print(" ");
				System.out.println(nodeName + " = " + nodeValue);
			}
		}

	}

}
