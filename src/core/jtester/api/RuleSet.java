package core.jtester.api;

public class RuleSet {
	// Result
	public final static String RESULT = "Result";
	public final static String ERROR = "Error"; 
	public final static String WARNING = "Warning";

	// static analysis
	// data flow
	public static final String FUNCTION_INFO_VISITOR = "core.common.model.functionblock.FunctionsInfoVisitor";
	public static final String AVAILABLE_EXP = "core.jtester.staticanalysis.available_exp.AvbExpCalculator";
	public static final String VERY_BUSY_EXP = "core.jtester.staticanalysis.verybusy_exp.VeyBsyExpCalculator";
	public static final String LIVE_VAR = "core.jtester.staticanalysis.live_variable.LivVarCalculator";
	public static final String REACHING_DEF = "core.jtester.staticanalysis.reaching_def.RchDefCalculator";
	public static final String CONST_PROPAGATION = "core.jtester.staticanalysis.const_propagation.ConProCalculator";
	public static final String SHAPE_ANALYSIS = "core.jtester.staticanalysis.shape_analysis.ShapeCalculator";
	// type inference
	public static final String CONST_PROBLEM = "core.jtester.staticanalysis.const_problem.ConstProblemAnalyzer";
	public static final String DATA_DETAINED = "core.jtester.staticanalysis.data_detained.DataDetainedAnalyzer";

	// var interval
	public static final String VAR_INTERVAL = "core.jtester.staticanalysis.var_interval.EquationCalculator";
	
	
	// semantics extraction
	public static final String SEMANTICS_EXTRACTION = "core.jtester.staticanalysis.semantics_extraction.SemanticsExtractor";
	public static final String SEMANTICS_ADAPTER = "core.jtester.ontology.adapter.SemanticsAdapter";
	
	// ontology reasoning
	public static final String ONTOLOGY_REASONER = "core.jtester.ontology.reasoner.JtesterReasoner";
}
