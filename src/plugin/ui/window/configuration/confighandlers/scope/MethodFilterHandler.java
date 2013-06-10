package plugin.ui.window.configuration.confighandlers.scope;

import java.util.LinkedList;

import com.clarkparsia.sparqlowl.parser.antlr.SparqlOwlParser.booleanLiteral_return;

public class MethodFilterHandler {
	LinkedList<String> getMethodNameRegularExpressions(){
		return null;
	}
	
	boolean addMethodNameRegularExpressions(String regular){
		return true;
	}
	
	boolean removeMethodNameRegularExpressions(String regular){
		return true;
	}
	
	int ignoreMethodLessComplexity(){
		return -1;
	}
	boolean ignoreDeprecatedMethod(){
		return true;
	}
}
