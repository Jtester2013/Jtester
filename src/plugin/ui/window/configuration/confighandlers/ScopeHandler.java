package plugin.ui.window.configuration.confighandlers;

import plugin.ui.window.configuration.confighandlers.scope.FileFiltersHandler;
import plugin.ui.window.configuration.confighandlers.scope.LineFiltersHandler;
import plugin.ui.window.configuration.confighandlers.scope.MethodFilterHandler;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.booleanLiteral_return;


public class ScopeHandler {
	int fileTimeOption;
	int dayLimit;
	String authorNameFilter;
	boolean onlyModifiedFiel;
	int cyclomaticLimit;
	boolean ifTestDeprecatedElements;
	
	FileFiltersHandler fileFiltersHandler;
	LineFiltersHandler lineFiltersHandler;
	MethodFilterHandler methodFilterHandler;
	public ScopeHandler(FileFiltersHandler f, LineFiltersHandler l, MethodFilterHandler m) {
		// TODO Auto-generated constructor stub
		this.fileFiltersHandler = f;
		this.methodFilterHandler = m;
		this.lineFiltersHandler = l;
	}
	public int getFileTimeOption() {
		return fileTimeOption;
	}
	public void setFileTimeOption(int fileTimeOption) {
		this.fileTimeOption = fileTimeOption;
	}
	public int getDayLimit() {
		return dayLimit;
	}
	public void setDayLimit(int dayLimit) {
		this.dayLimit = dayLimit;
	}
	public String getAuthorNameFilter() {
		return authorNameFilter;
	}
	public void setAuthorNameFilter(String authorNameFilter) {
		this.authorNameFilter = authorNameFilter;
	}
	public int getCyclomaticLimit() {
		return cyclomaticLimit;
	}
	public void setCyclomaticLimit(int cyclomaticLimit) {
		this.cyclomaticLimit = cyclomaticLimit;
	}
	public boolean isIfTestDeprecatedElements() {
		return ifTestDeprecatedElements;
	}
	public void setIfTestDeprecatedElements(boolean ifTestDeprecatedElements) {
		this.ifTestDeprecatedElements = ifTestDeprecatedElements;
	}
	public FileFiltersHandler getFileFiltersHandler() {
		return fileFiltersHandler;
	}
	public void setFileFiltersHandler(FileFiltersHandler fileFiltersHandler) {
		this.fileFiltersHandler = fileFiltersHandler;
	}
	
	
}
