package core.common.model.annotator;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import core.common.model.om.ConstraintVariable;
import core.common.model.om.Location;
import core.common.model.om.OMShared;
import core.common.util.ASTUtil;

/**
 * 
 * @author Bin
 *
 *	Match every constraint variable with configuration files 
 */
public class AnnotationASTVisitor extends ASTVisitor {

	private Configuration conf;
	private Stack<ASTNode> path;
	private Src src;
	private String methodKey;

	public AnnotationASTVisitor(Configuration conf, Src src) {
		if (conf == null)
			throw new NullPointerException("Configuration is null");
		this.conf = conf;
		this.path = new Stack<ASTNode>();
		this.src = src;
	}

	@Override
	public void preVisit(ASTNode node) {
		this.path.push(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		this.path.pop();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(VariableDeclarationStatement node) {
		String scope = ASTUtil.getVariableNodeScope(path, node);
		VariableDeclarationStatement matcher = conf.getVariables().get(scope);
		if (matcher != null) {
			Location loc = new Location(src.getFileAbsolutePath(), src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			Map<Integer, Integer> values = new Hashtable<Integer, Integer>();
			for (Object o : matcher.modifiers()) {
				if (o instanceof NormalAnnotation) {
					NormalAnnotation annot = (NormalAnnotation) o;
					ASTNode copy = ASTUtil.copyNode(node.getAST(), annot);
					node.modifiers().add(0, copy);
					String typeName = annot.getTypeName().toString();
					int typeId = OMShared.getTypeIDByName(typeName);
					MemberValuePair mvp = (MemberValuePair) annot.values().get(0);
					String value = mvp.getValue().toString();
					value = value.substring(value.lastIndexOf(".") + 1);
					int valueId = OMShared.getValueIDByName(typeName, value);
					values.put(typeId, valueId);
				}
			}
			List<VariableDeclarationFragment> list = node.fragments();
			for (VariableDeclarationFragment vdf : list) {
				IVariableBinding binding = vdf.resolveBinding();
				String key = binding.getKey();
				ConstraintVariable cv = new ConstraintVariable(key, loc);
				for (int typeId : values.keySet()) {
					cv.addAnnotation(typeId, values.get(typeId), loc);
				}
				OMShared.putConstraintVariable(key, cv);
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(SingleVariableDeclaration node) {
		String scope = ASTUtil.getArgumentNodeScope(path, node);
		SingleVariableDeclaration matcher = conf.getArguments().get(scope);
		if (matcher != null) {
			Location loc = new Location(src.getFileAbsolutePath(),
					src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			IBinding binding = node.resolveBinding();
			String key = binding.getKey();
			ConstraintVariable cv = new ConstraintVariable(key, loc);
			for (Object o : matcher.modifiers()) {
				if (o instanceof NormalAnnotation) {
					NormalAnnotation annot = (NormalAnnotation) o;
					ASTNode copy = ASTUtil.copyNode(node.getAST(), annot);
					node.modifiers().add(0, copy);
					String typeName = annot.getTypeName().toString();
					int typeId = OMShared.getTypeIDByName(typeName);
					MemberValuePair mvp = (MemberValuePair) annot.values().get(
							0);
					String value = mvp.getValue().toString();
					value = value.substring(value.lastIndexOf(".") + 1);
					int valueId = OMShared.getValueIDByName(typeName, value);
					cv.addAnnotation(typeId, valueId, loc);
				}
			}
			OMShared.putConstraintVariable(key, cv);
			if (this.methodKey != null) {
				OMShared.putMethodParameter(this.methodKey, cv);
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(MethodDeclaration node) {
		String scope = ASTUtil.getMethodNodeScope(path, node);
		MethodDeclaration matcher = conf.getMethods().get(scope);
//		System.out.println("method "+conf.getMethods().size());	
		Iterator<String> ir = conf.getMethods().keySet().iterator();
//		while(ir.hasNext()){
//			System.out.println("method: "+ir.next());
//		}
//		System.out.println("scope "+scope);	
		if (matcher != null) {
			Location loc = new Location(src.getFileAbsolutePath(),
					src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			IMethodBinding binding = node.resolveBinding();
			String key = binding.getKey();
			ConstraintVariable methodcv = new ConstraintVariable(key, loc);
			ConstraintVariable thiscv = null;

			// Not constructor && Not static
			if (!binding.isConstructor()
					&& (binding.getModifiers() & Modifier.STATIC) == 0) {
				thiscv = new ConstraintVariable(String.format("%s#this", key),
						loc);
				OMShared.putConstraintVariable(String.format("%s#this", key), thiscv);
				OMShared.putMethodParameter(key, thiscv);
			}

			for (Object o : matcher.modifiers()) {
				if (o instanceof NormalAnnotation) {
					NormalAnnotation annot = (NormalAnnotation) o;
					ASTNode copy = ASTUtil.copyNode(node.getAST(), annot);
					node.modifiers().add(0, copy);
					String typeName = annot.getTypeName().toString();
					int typeId = OMShared.getTypeIDByName(typeName);
					int valueId = -1;
					List<MemberValuePair> list = annot.values();
					boolean isMethodCV = true;
					for (MemberValuePair mvp : list) {
						String value = mvp.getValue().toString();
						value = value.substring(value.lastIndexOf(".") + 1);
						if (value.equals("This")) {
							isMethodCV = false;
							continue;
						}
						valueId = OMShared.getValueIDByName(typeName, value);
					}
					if (valueId == -1)
						throw new RuntimeException(
								"annotation value is invalid");
					if (isMethodCV)
						methodcv.addAnnotation(typeId, valueId, loc);
					else
						thiscv.addAnnotation(typeId, valueId, loc);
				}
			}
			OMShared.putConstraintVariable(key, methodcv);
			this.methodKey = key;

		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean visit(FieldDeclaration node) {
		String scope = ASTUtil.getFieldNodeScope(path, node);
		FieldDeclaration matcher = conf.getFields().get(scope);
		if (matcher != null) {
			Location loc = new Location(src.getFileAbsolutePath(),
					src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			Map<Integer, Integer> values = new Hashtable<Integer, Integer>();
			for (Object o : matcher.modifiers()) {
				if (o instanceof NormalAnnotation) {
					NormalAnnotation annot = (NormalAnnotation) o;
					ASTNode copy = ASTUtil.copyNode(node.getAST(), annot);
					node.modifiers().add(0, copy);
					String typeName = annot.getTypeName().toString();
					int typeId = OMShared.getTypeIDByName(typeName);
					MemberValuePair mvp = (MemberValuePair) annot.values().get(
							0);
					String value = mvp.getValue().toString();
					value = value.substring(value.lastIndexOf(".") + 1);
					int valueId = OMShared.getValueIDByName(typeName, value);
					values.put(typeId, valueId);
				}
			}
			List<VariableDeclarationFragment> list = node.fragments();
			for (VariableDeclarationFragment vdf : list) {
				IVariableBinding binding = vdf.resolveBinding();
				String key = binding.getKey();
				ConstraintVariable cv = new ConstraintVariable(key, loc);
				for (int typeId : values.keySet()) {
					cv.addAnnotation(typeId, values.get(typeId), loc);
				}
				OMShared.putConstraintVariable(key, cv);
			}
		}
		return true;
	}
}
