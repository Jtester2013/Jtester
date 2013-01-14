package core.common.model.annotator;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import core.common.model.om.ConstraintVariable;
import core.common.model.om.Location;
import core.common.model.om.OMShared;

/**
 * 
 * @author Bin
 * 
 * Construct the relationship between Constraint Variables.
 *
 */
public class ConstraintVisitor extends ASTVisitor {
	private Map<String, ConstraintVariable> table;
	private Src src;
	private Stack<ASTNode> stack;

	public ConstraintVisitor(Map<String, ConstraintVariable> table, Src src) {
		this.table = table;
		this.src = src;
		this.stack = new Stack<ASTNode>();
	}

	@Override
	public void preVisit(ASTNode node) {
		this.stack.push(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		this.stack.pop();
	}

	public boolean visit(VariableDeclarationFragment node) {
		Expression exp = node.getInitializer();
		if (exp != null) {
			IVariableBinding binding = node.resolveBinding();
			String key = binding.getKey();
			ConstraintVariable cv = this.table.get(key);
			if (cv == null) {
				System.err.printf("%s does not contained in the constarint table", key);
			}
			ConstraintVariable cv1 = handleExpression(exp);
			Location loc = new Location(src.getFileAbsolutePath(),
					src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			cv.addConstraint(cv1, loc);

		}
		return super.visit(node);
	}

	private ConstraintVariable handleExpression(Expression exp) {
		ConstraintVariable cv = null;
		switch (exp.getNodeType()) {
		case ASTNode.CLASS_INSTANCE_CREATION:
			cv = handleClassInstanceCreation((ClassInstanceCreation) exp);
			break;
		case ASTNode.METHOD_INVOCATION:
			cv = handleMethodInvocation((MethodInvocation) exp);
			break;
		case ASTNode.SIMPLE_NAME:
			cv = handleSimpleName((SimpleName) exp);
			break;
		case ASTNode.QUALIFIED_NAME:
			cv = handleQulifiedName((QualifiedName) exp);
			break;
		case ASTNode.THIS_EXPRESSION:
			cv = handleThisExp((ThisExpression) exp);
			break;
		case ASTNode.CAST_EXPRESSION:
			cv = handleCastExp((CastExpression) exp);
		}
		return cv;
	}

	private ConstraintVariable handleCastExp(CastExpression castExp) {
		Expression exp = castExp.getExpression();
		return handleExpression(exp);
	}

	private ConstraintVariable handleThisExp(ThisExpression exp) {
		String key = findKeyOfMethodDeclartion();
		List<ConstraintVariable> paras = OMShared.getMethodParametersByKey(key);
		return paras.get(0);
	}

	private ConstraintVariable handleQulifiedName(QualifiedName exp) {
		ConstraintVariable cv = null;
		SimpleName name = exp.getName();
		String key = getKeyByName(exp);
		cv = OMShared.getConstraintByKey(key);
		if (cv == null) {
			Location loc = new Location(this.src.getFileAbsolutePath(),
					src.getLineNumber(exp));
			loc.setStartPosition(exp.getStartPosition());
			loc.setLength(exp.getLength());
			cv = new ConstraintVariable(key, loc);
			IBinding binding = name.resolveBinding();
			if (binding != null) {
				key = binding.getKey();
				ConstraintVariable cv1 = OMShared.getConstraintByKey(key);
				if (cv1 != null)
					cv.addConstraint(cv1, loc);
			}
		}
		return cv;
	}

	private String getKeyByName(QualifiedName node) {
		Name qual = node.getQualifier();
		SimpleName name = node.getName();
		String qualKey;
		if (qual.getNodeType() == ASTNode.QUALIFIED_NAME)
			qualKey = getKeyByName((QualifiedName) qual);
		else {
			qualKey = qual.resolveBinding().getKey();
		}
		return String.format("%s.%s", qualKey, name.toString());
	}

	private ConstraintVariable handleSimpleName(SimpleName exp) {
		IBinding binding = exp.resolveBinding();
		if (binding != null) {
			String key = binding.getKey();
			ConstraintVariable cv = OMShared.getConstraintByKey(key);
			if (cv == null) {
				System.err.printf("Cannot find constraing by key : %s", key);
			}
			return cv;
		}
		return null;
	}

	private ConstraintVariable handleMethodInvocation(MethodInvocation exp) {
		IBinding binding = exp.resolveMethodBinding();
		if (binding != null) {
			String key = binding.getKey();
			ConstraintVariable cv = OMShared.getConstraintByKey(key);
			if (cv == null) {
				System.err.printf("Cannot find constraing by key : %s", key);
			}
			return cv;
		}
		return null;
	}

	private ConstraintVariable handleClassInstanceCreation(
			ClassInstanceCreation exp) {
		IBinding binding = exp.resolveConstructorBinding();
		if (binding != null) {
			String key = binding.getKey();
			ConstraintVariable cv = OMShared.getConstraintByKey(key);
			if (cv == null) {
				System.err.printf("Cannot find constraint by key : %s", key);
			}
			return cv;
		}
		return null;
	}

	public boolean visit(ExpressionStatement node) {
		return super.visit(node);
	}

	public boolean visit(QualifiedName node) {
		return super.visit(node);

	}

	public boolean visit(SimpleName node) {
		return super.visit(node);
	}

	@SuppressWarnings("unchecked")
	public boolean visit(MethodInvocation node) {
		IMethodBinding binding = node.resolveMethodBinding();
		List<Expression> arguments = node.arguments();
		boolean isStatic = (binding.getModifiers() & Modifier.STATIC) != 0;
		boolean isConstructor = binding.isConstructor();
		if (binding != null) {
			String key = binding.getKey();
			if (OMShared.getConstraintByKey(key) != null) {
				List<ConstraintVariable> parameters = OMShared
						.getMethodParametersByKey(key);
				Location loc = new Location(src.getFileAbsolutePath(),
						src.getLineNumber(node));
				loc.setStartPosition(node.getStartPosition());
				loc.setLength(node.getLength());
				if (isStatic || isConstructor) {
					for (int i = 0; i < arguments.size(); ++i) {
						Expression argu = arguments.get(i);
						ConstraintVariable cv = handleExpression(argu);
						parameters.get(i).addConstraint(cv, loc);
					}
				} else {
					ConstraintVariable cv = handleMethodInvocationExp(node
							.getExpression());
					parameters.get(0).addConstraint(cv, loc);
					for (int i = 0; i < arguments.size(); ++i) {
						Expression argu = arguments.get(i);
						cv = handleExpression(argu);
						parameters.get(i + 1).addConstraint(cv, loc);
					}
				}
			}
		}
		return super.visit(node);
	}

	private ConstraintVariable handleMethodInvocationExp(Expression exp) {
		if (exp == null) {
			return handleThisExp(null);
		} else {
			return handleExpression(exp);
		}
	}

	private String findKeyOfMethodDeclartion() {
		for (ASTNode node : this.stack) {
			if (node.getNodeType() == ASTNode.METHOD_DECLARATION) {
				MethodDeclaration md = (MethodDeclaration) node;
				IMethodBinding binding = md.resolveBinding();
				if (binding != null)
					return binding.getKey();
			}
		}
		return null;
	}

	public boolean visit(InfixExpression node) {
		return super.visit(node);
	}

	public boolean visit(PrefixExpression node) {
		return super.visit(node);
	}

	public boolean visit(PostfixExpression node) {
		return super.visit(node);
	}

	public boolean visit(SuperFieldAccess node) {
		return super.visit(node);
	}

	public boolean visit(ThisExpression node) {
		return super.visit(node);
	}

	public boolean visit(SuperMethodInvocation node) {
		return super.visit(node);
	}

	public boolean visit(FieldAccess node) {
		return super.visit(node);
	}

	public boolean visit(ConditionalExpression node) {
		return super.visit(node);
	}

	public boolean visit(ClassInstanceCreation node) {
		return super.visit(node);
	}

	public boolean visit(Assignment node) {

		return super.visit(node);
	}

	public boolean visit(ArrayInitializer node) {
		return super.visit(node);
	}

	public boolean visit(ArrayAccess node) {
		return super.visit(node);
	}

	public boolean visit(ReturnStatement node) {
		ConstraintVariable returncv = handleExpression(node.getExpression());
		if (returncv != null) {
			Location loc = new Location(this.src.getFileAbsolutePath(), this.src.getLineNumber(node));
			loc.setStartPosition(node.getStartPosition());
			loc.setLength(node.getLength());
			String methodKey = this.findKeyOfMethodDeclartion();
			ConstraintVariable methodcv = OMShared
					.getConstraintByKey(methodKey);
			methodcv.addConstraint(returncv, loc);
		}
		return super.visit(node);
	}
}
