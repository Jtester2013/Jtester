package core.common.model.annotator;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

/**
 * 
 * @author Bin
 *
 *	Record of Configuration file information
 */

public class Configuration {
	private Map<String, AnnotationTypeDeclaration> annotations;
	private Map<String, VariableDeclarationStatement> variables;
	private Map<String, MethodDeclaration> methods;
	private Map<String, FieldDeclaration> fields;
	private Map<String, SingleVariableDeclaration> arguments;
	
	Configuration(){
		this.annotations = new Hashtable<String, AnnotationTypeDeclaration>();
		this.variables = new Hashtable<String, VariableDeclarationStatement>();
		this.methods = new Hashtable<String, MethodDeclaration>();
		this.fields = new Hashtable<String, FieldDeclaration>();
		this.arguments = new Hashtable<String, SingleVariableDeclaration>();
	}

	public Map<String, AnnotationTypeDeclaration> getAnnotations() {
		return annotations;
	}

	public Map<String, VariableDeclarationStatement> getVariables() {
		return variables;
	}

	public Map<String, MethodDeclaration> getMethods() {
		return methods;
	}

	public Map<String, FieldDeclaration> getFields() {
		return fields;
	}

	public Map<String, SingleVariableDeclaration> getArguments() {
		return arguments;
	}
}
