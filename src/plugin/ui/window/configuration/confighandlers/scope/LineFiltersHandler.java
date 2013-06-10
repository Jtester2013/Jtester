package plugin.ui.window.configuration.confighandlers.scope;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.booleanLiteral_return;

public class LineFiltersHandler {
	int getTimeOption(){
		return 1;
	}
	int setTimeOption(){
		return 1;
	}
	
	boolean ifAuthorFilter(){
		return false;
	}
	
	String authorFilter(String authorNameString){
		return null;
	}
}
