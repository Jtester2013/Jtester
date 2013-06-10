package plugin.ui.window.configuration.confighandlers.Static;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.booleanLiteral_return;

public class GeneralHandler {
	boolean ifSkipJSP;
	int lineLimit;
	boolean checkErroeCompiledFile;
	boolean ignoreGlobalAnalysisRules;
	boolean allowCompilationError;
	boolean includeUserDefinedTestClasses;
	boolean ignoreCodeDuplicationRules;
	public boolean isIfSkipJSP() {
		return ifSkipJSP;
	}
	public void setIfSkipJSP(boolean ifSkipJSP) {
		this.ifSkipJSP = ifSkipJSP;
	}
	public int getLineLimit() {
		return lineLimit;
	}
	public void setLineLimit(int lineLimit) {
		this.lineLimit = lineLimit;
	}
	public boolean isCheckErroeCompiledFile() {
		return checkErroeCompiledFile;
	}
	public void setCheckErroeCompiledFile(boolean checkErroeCompiledFile) {
		this.checkErroeCompiledFile = checkErroeCompiledFile;
	}
	public boolean isIgnoreGlobalAnalysisRules() {
		return ignoreGlobalAnalysisRules;
	}
	public void setIgnoreGlobalAnalysisRules(boolean ignoreGlobalAnalysisRules) {
		this.ignoreGlobalAnalysisRules = ignoreGlobalAnalysisRules;
	}
	public boolean isAllowCompilationError() {
		return allowCompilationError;
	}
	public void setAllowCompilationError(boolean allowCompilationError) {
		this.allowCompilationError = allowCompilationError;
	}
	public boolean isIncludeUserDefinedTestClasses() {
		return includeUserDefinedTestClasses;
	}
	public void setIncludeUserDefinedTestClasses(
			boolean includeUserDefinedTestClasses) {
		this.includeUserDefinedTestClasses = includeUserDefinedTestClasses;
	}
	public boolean isIgnoreCodeDuplicationRules() {
		return ignoreCodeDuplicationRules;
	}
	public void setIgnoreCodeDuplicationRules(boolean ignoreCodeDuplicationRules) {
		this.ignoreCodeDuplicationRules = ignoreCodeDuplicationRules;
	}
}
