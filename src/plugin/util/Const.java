package plugin.util;

public class Const {
	// Common
	public static final String JTESTER = "Jtester";

	public static final String EMPTY_LINE = "                      ";

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

	// added by John
	// ���ָ�·��
	public static final StringBuffer rootPath = new StringBuffer(System.getProperty("user.dir"));
	public static final String iconPath = rootPath.append("\\icons").toString();

	// �ȽϾ����Ԫ�ص�·��
	public static final String FOLDER_ICON_PATH = iconPath
			+ "\\folder_icon.gif";
	public static final String HYPERCUBE_ICON_PATH = iconPath
			+ "\\hypercube-16.gif";
	public static final String USER_ICON_PATH = iconPath + "\\user.gif";
	public static final String TEAM_ICON_PATH = iconPath + "\\ovr16\\team.gif";
	public static final String CONFIGURATION_ICON_PATH = iconPath
			+ "\\configuration.gif";

	// icons used in detail configuration
	public static final String SCOPE_ICON_PATH = iconPath
			+ "\\diy\\scope_DIY.gif";
	public static final String STATIC_ICON_PATH = iconPath
			+ "\\diy\\static_DIY.gif";
	public static final String GENERATION_ICON_PATH = iconPath
			+ "\\diy\\generation_DIY.gif";
	public static final String EXECUTION_ICON_PATH = iconPath
			+ "\\diy\\execution_DIY.gif";
	public static final String COMMON_ICON_PATH = iconPath
			+ "\\diy\\common_DIY.gif";
	public static final String CODEREVIEW_ICON_PATH = iconPath
			+ "\\diy\\codereview_DIY.gif";
	public static final String GOALS_ICON_PATH = iconPath
			+ "\\diy\\goals_DIY.gif";
	
	
}
