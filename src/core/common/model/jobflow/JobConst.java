package core.common.model.jobflow;

public class JobConst {
	
	public final static String JOB_FLOW_TYPE = "JOBFlowType";
	public final static String PREFIX = "shootsoft_testing_";
	public final static String ENTER= "\r\n";
	public final static String CONTROL_FLOW_GRAPH = "ControlFlowGraph";
	public final static String INT = "int";
	
	//visitor
	public final static String TARGET_SOURCE = "TargetSource";
	public final static String TARGET_CLASS = "TargetClass";
	public final static String TARGET_METHOD = "TargetMethod";
	public final static String FILENAME = "FileName";
	
	public final static String MONITOR = "Monitor";
	public final static String AST = "AST";
	public final static String AST_NOT_FOUND = "AST Not Found";
	public final static String ASTVIEW = "ASTView";
	public final static String ASTXML = "ASTXml";
	public final static String MOCKMETHOD = "NeedMock";
	public final static String SEMANTICS = "Semantics";

	//Compiler
	public final static String CLASS_PATH = "ClassPath";
	public final static String COMPILED_REPORT = "CompiledReport";
	
	//Compiler
	public final static String TARGET_PATH_SRC = "TargetSrc";
	public final static String TARGET_PATH_BIN = "TargetBin";	
	
	//public final static String TARGET_PATH = "TargetPath";
	//public final static String ACTUAL_PATH = "ActualPath";
	
	public final static String CONDITION_TREES = "ConditionTrees";
	public final static String CONDITION_TYPE = "ConditionType";
	
	//Tester
	public final static String RESULT_CONVERAGE = "ResultConverage";
	public final static String RESULT_CONVERAGE_PATH = "ResultConveragePath";
	/**
	 * 生成的实际测试用例
	 * */
	public final static String RESULT_TESTCASE = "ResultTestCase";
	/**
	 * 目标路径
	 * */
	public final static String RESULT_TARGET_PATH = "ResultTargetPath";
	public final static String RESULT_ACTUAL_PATH = "ResultActualPath";
	
	public final static String RESULT_RETURN_VALUES = "ResultReturnValues";
	public final static String RESULT_XML = "ResultXML";

	
	//Tester
	public final static String TEST_CASE_EXECUTOR = "TestCaseExecutor";
	
	public final static String MONITOR_TYPE = "MonitorType";
	
	public final static String GENE_MAX_EVOLUTIONS = "GeneMaxEvolutions";
	public final static String GENE_POPULATION_SIZE = "GenePopulationSize";
	
	public final static String XMLROOT = "XmlReportRoot";
	
	//Data Flow Analysis
	public final static String FUNCTIONSINFO = "FunctionsInfo";
	public final static String AVAILABLE_EXPRESSION_INFO = "AEInfo";
	public final static String AVAILABLE_EXPRESSION_SOLUTION = "AESolution";
	
	public final static String EQUATIONS = "Equations";
	public final static String PARAMS = "Params";
	public final static String VAR = "Variable";
	public final static double narrow = 312;
	
	// semantics
	// File Close Checker
	public final static String STREAM_TYPE_1 = "InputStream";
	public final static String STREAM_TYPE_2 = "OutputStream";
	public final static String STREAM_CLOSE = "close";
	// Iterator Remove Checker
	public final static String REMOVE_TYPE_1  = "remove";
	public final static String ITERATOR_TYPE = "Iterator";
	public final static String ITERATOR_METHOD = "iterator";
	public final static String ITERATOR_NEXT = "next";
	
	// Method Override Checker
	public final static String METHOD_EQUALS = "equals";
	public final static String METHOD_HASHCODE = "hashCode";
	
	// File Content Injection Checker
	public final static String INJECTION_WRITE = "write";
	public final static String INJECTION_STREAM_TYPE1 = "FileOutputStream";
	public final static String INJECTION_REQUEST_TYPE1 = "HttpServletRequest";
	
	// Sensitive Data Exposure Checker
	public final static String EXPOSURE_WRITE = "write";
	public final static String EXPOSURE_RESPONSE_TYPE1 = "HttpServletResponse";
	public final static String EXPOSURE_TYPE1 = "Exception";
	public final static String EXPOSURE_TYPE2 = "IOException";
	
	// IProblem
	public final static String COMPILER_PROBLEM = "Compiler Problems";
	
	// Ontology
	public final static String ONTOLOGY_STREAM_NOT_CLOSED ="STREAM_NOT_CLOSED";
	public final static String ONTOLOGY_OVERRIDE_METHOD_EQUALS_BUT_HASHCODE ="OVERRIDE_METHOD_EQUALS_BUT_HASHCODE";
	public final static String ONTOLOGY_REMOVE_IN_ITERATION ="REMOVE_IN_ITERATION";
	public final static String ONTOLOGY_UNUSED_VARIABLE ="UNUSED_VARIABLE";
	public final static String ONTOLOGY_USE_NULL_POINTER ="USE_NULL_POINTER";
	public final static String ONTOLOGY_CONDITION_ALWAYS_SAME_VALUE = "CONDITION_ALWAYS_SAME_VALUE";
	public final static String ONTOLOGY_OUT_OF_BOUNDARY = "OUT_OF_BOUNDARY";
	public final static String ONTOLOGY_DIVIDED_BY_ZERO = "DIVIDED_BY_ZERO";
	public final static String ONTOLOGY_FILE_CONTENT_INJECTION = "FILE_CONTENT_INJECTION";
	public final static String ONTOLOGY_SENSITIVE_DATA_EXPOSURE = "SENSITIVE_DATA_EXPOSURE";
	
	// Ontology
	public final static String ONTOLOGY_DESCRIPTION_CN = "描述";
	public final static String ONTOLOGY_LEVEL_CN = "级别";
	public final static String ONTOLOGY_EXAMPLE_CN = "示例";
}
