package core.common.model.annotator;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import core.common.model.om.ConstraintVariable;
import core.common.model.om.Location;
import core.common.model.om.OMShared;

/**
 * 
 * @author Bin
 * 
 * Construct Constraint Table with initial annotation types.
 *
 */
public class DeclarationVisitor extends ASTVisitor {
	private Map<String, ConstraintVariable> table;
	private Src src;
	private String methodKey;

	public DeclarationVisitor(Map<String, ConstraintVariable> table, Src src) {
		this.table = table;
		this.src = src;
	}

	@SuppressWarnings("unchecked")
	public boolean visit(FieldDeclaration node) {
		List<VariableDeclarationFragment> list = node.fragments();
		if (list != null) {
			int lineNum = this.src.getLineNumber(node);
			Location loc = new Location(src.getFileAbsolutePath(), lineNum);
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			for (VariableDeclarationFragment vdf : list) {
				IVariableBinding ivb = vdf.resolveBinding();
				String key = ivb.getKey();
				if (this.table.containsKey(key)) {
					ConstraintVariable cv = this.table.get(key);
					cv.setLocation(loc);
					continue;
				} else {
					ConstraintVariable cv = new ConstraintVariable(key, loc);
					this.table.put(key, cv);
				}
			}
		}
		return super.visit(node);
	}

	public boolean visit(MethodDeclaration node) {
		int lineNum = this.src.getLineNumber(node);
		Location loc = new Location(this.src.getFileAbsolutePath(), lineNum);
		loc.setStartPosition(node.getStartPosition());
		loc.setLength(node.getLength());
		IMethodBinding binding = node.resolveBinding();
		if (binding != null) {
			String key = binding.getKey();
			this.methodKey = key;
			if (!this.table.containsKey(key)) {
				ConstraintVariable cv = new ConstraintVariable(key, loc);
				this.table.put(key, cv);

				//Not constructor && Not static
				if (!binding.isConstructor()
						&& (binding.getModifiers() & Modifier.STATIC) == 0) {
					ConstraintVariable paracv = new ConstraintVariable(
							String.format("%s#this", key), loc);
					OMShared.putMethodParameter(key, paracv);
				}
			}
		}
		return super.visit(node);
	}

	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationStatement node) {
		List<VariableDeclarationFragment> list = node.fragments();
		if (list != null) {
			int lineNum = this.src.getLineNumber(node);
			Location loc = new Location(src.getFileAbsolutePath(), lineNum);
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			for (VariableDeclarationFragment vdf : list) {
				IVariableBinding ivb = vdf.resolveBinding();
				String key = ivb.getKey();
				if (this.table.containsKey(key)) {
					ConstraintVariable cv = this.table.get(key);
					cv.setLocation(loc);
					continue;
				} else {
					ConstraintVariable cv = new ConstraintVariable(key, loc);
					this.table.put(key, cv);
				}
			}
		}
		return super.visit(node);
	}
	
	@SuppressWarnings("unchecked")
	public boolean visit(VariableDeclarationExpression node){
		List<VariableDeclarationFragment> list = node.fragments();
		if (list != null) {
			int lineNum = this.src.getLineNumber(node);
			Location loc = new Location(src.getFileAbsolutePath(), lineNum);
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			for (VariableDeclarationFragment vdf : list) {
				IVariableBinding ivb = vdf.resolveBinding();
				String key = ivb.getKey();
				if (this.table.containsKey(key)) {
					ConstraintVariable cv = this.table.get(key);
					cv.setLocation(loc);
					continue;
				} else {
					ConstraintVariable cv = new ConstraintVariable(key, loc);
					this.table.put(key, cv);
				}
			}
		}
		return super.visit(node);
	}

	public boolean visit(SingleVariableDeclaration node) {
		int lineNum = this.src.getLineNumber(node);
		Location loc = new Location(this.src.getFileAbsolutePath(), lineNum);
		loc.setStartPosition(node.getStartPosition());
		loc.setLength(node.getLength());
		IVariableBinding binding = node.resolveBinding();
		if (binding != null) {
			String key = binding.getKey();
			ConstraintVariable cv = null;
			if (!this.table.containsKey(key)) {
				cv = new ConstraintVariable(key, loc);
				this.table.put(key, cv);
				if (this.methodKey != null) {
					OMShared.putMethodParameter(this.methodKey, cv);
				}
			}

		}
		return super.visit(node);
	}

}
