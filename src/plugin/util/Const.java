package plugin.util;

public class Const {
	// Common
	public static final String JTESTER = "Jtester";
	public static final String EMPTY_LINE = "                      ";

	// Plug-in run
	public static final String JTESTER_ALL = "plugin.run.all";
	public static final String JTESTER_CONFIGURATION = "plugin.run.configuration";
	public static final String JTESTER_CONST_PROBLEM = "plugin.run.const_problem";
	public static final String JTESTER_DATA_DETAINED = "plugin.run.data_detained";
	public static final String JTESTER_ONTOLOGY = "plugin.run.ontology";
	
	// Rule Configuration
	public static final String DEMO = "JTester Demo Configuration";
	public static final String DATA_FLOW = "JTester Data Flow Configuration";
	
	// Progress Dialog
	public static final String STATIC_FILES_CHEKCED = "Files Checked (Coding Standards): ";

	public static final String STATIC_FILES_SKIPPED = "Files Skipped (Coding Standards): ";

	public static final String STATIC_FAILED_RUNS = "Failed Runs: ";

	public static final String STATIC_VIOLATIONS_FOUND = "Violations Found: ";

	public static final String STATIC_VIOLATIONS_SUPPRESSED = "Violations Suprressed: ";

	public static final String STATIC_NUM_OF_RULES_VIOLATED = "Numbers of Rules Violated: ";

	public static final String FILE_UNDER_CHECK = "Check: ";
	
	// Type Inference Analysis Config Path
	public final static String CONST_PROBLEM_PATH = "/tested_files/type_inference/config/const_problem";
	public final static String DATA_DETAINED_PATH = "/tested_files/type_inference/config/data_detained/";

	// OWL
	public final static String OWL_PATH = "./tested_files/ontology/java-spec.owl";
	public final static String FILE_Type = "file:";
	public final static String OntologyID = "http://www.pku.edu.cn/ontologies/2013/3/java6-spec.owl";
	public final static String OWL_WARNING = "#Warning"; 
	public final static String OWL_REASONING = "Ontology Reasoning";
	
	// OWL Warning
	public final static String EMPTY_WHILE = "[Empty While Statement]\n";
	public final static String RETURN_FROM_FINALLY = "[Return From Finally]\n";
	public final static String SQL_INJECTION = "[SQL Injection]\n";
	public final static String IF_ALWAYS_TRUE = "[If Always True]\n";
	public final static String IF_ALWAYS_FALSE = "[If Always False]\n";
	public final static String ARRAY_OUT_OF_BOUNDARY = "[Array Out Of Boundary]\n";
	
	// added by John
	// 各种根路径
	public static final String rootPath = System.getProperty("user.dir");
	public static final String iconPath = rootPath + "\\icons";

	// 比较具体的元素的路径
	public static final String FOLDER_ICON_PATH = iconPath + "\\folder_icon.gif";
	public static final String HYPERCUBE_ICON_PATH = iconPath + "\\hypercube-16.gif";
	public static final String USER_ICON_PATH = iconPath + "\\user.gif";
	public static final String TEAM_ICON_PATH = iconPath + "\\ovr16\\team.gif";
	public static final String CONFIGURATION_ICON_PATH = iconPath + "\\configuration.gif";

	// icons used in detail configuration
	public static final String SCOPE_ICON_PATH = iconPath + "\\diy\\scope_DIY.gif";
	public static final String STATIC_ICON_PATH = iconPath + "\\diy\\static_DIY.gif";
	public static final String GENERATION_ICON_PATH = iconPath + "\\diy\\generation_DIY.gif";
	public static final String EXECUTION_ICON_PATH = iconPath + "\\diy\\execution_DIY.gif";
	public static final String COMMON_ICON_PATH = iconPath + "\\diy\\common_DIY.gif";
	public static final String CODEREVIEW_ICON_PATH = iconPath + "\\diy\\codereview_DIY.gif";
	public static final String GOALS_ICON_PATH = iconPath + "\\diy\\goals_DIY.gif";
	
}
