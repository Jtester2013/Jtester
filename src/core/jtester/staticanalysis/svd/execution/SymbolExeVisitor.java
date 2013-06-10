package core.jtester.staticanalysis.svd.execution;

import java.awt.List;
import java.util.Iterator;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.Assignment.Operator;

/**import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.WildcardType;*/


public class SymbolExeVisitor extends ASTVisitor {
	ProgramEnv env;
	
	public SymbolExeVisitor(ProgramEnv env){
		this.env = env;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * do with the infix expression
	 * @param exp
	 */
	public ExpressionNode InfixExpression2ExpressionNode(InfixExpression exp){
		
		Expression leftPart = exp.getLeftOperand(),
				rightPart = exp.getRightOperand();
		org.eclipse.jdt.core.dom.InfixExpression.Operator operator = exp.getOperator();
		
		ExpressionNode resultNode =  new ExpressionNode(ExpressionType.expression, ""),
//		ExpressionNode resultNode =  new ExpressionNode(ExpressionType.not_defined, ""),
				leftNode = new ExpressionNode(ExpressionType.not_defined, ""),
				rightNode = new ExpressionNode(ExpressionType.not_defined, "");
		
		// 构造左操作数
		if (leftPart instanceof NumberLiteral) {
			leftNode = new ExpressionNode(ExpressionType.single_int, ((NumberLiteral)leftPart).getToken());
		} else if (leftPart instanceof SimpleName) {
			String sourceVarNameLeft = ((SimpleName)leftPart).getIdentifier();
			leftNode = env.getMap().get(sourceVarNameLeft).clone();
		} else if (leftPart instanceof InfixExpression) {
			leftNode = InfixExpression2ExpressionNode((InfixExpression)leftPart);
		}
		// 构造右操作数
		if (rightPart instanceof NumberLiteral) {
			rightNode = new ExpressionNode(ExpressionType.single_int, ((NumberLiteral)rightPart).getToken());
		} else if (rightPart instanceof SimpleName) {
			String sourceVarNameRight = ((SimpleName)rightPart).getIdentifier();
			rightNode = env.getMap().get(sourceVarNameRight).clone();
		} else if (rightPart instanceof InfixExpression) {
			rightNode = InfixExpression2ExpressionNode((InfixExpression)rightPart);
		}
		
		if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.TIMES)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.multi, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.PLUS)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.plus, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.MINUS)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.minus, leftNode, rightNode);
		}else if (operator.equals(org.eclipse.jdt.core.dom.InfixExpression.Operator.DIVIDE)) {
			resultNode = new ExpressionNode(ExpressionType.expression, "", ExpressionOperator.div, leftNode, rightNode);
		}else {
			System.out.println("not supported operator : "+ operator);
			return null;
		}
		return resultNode;
	}
	
	public ExpressionNode generateExpressionNode(SimpleName varName){
		return null;
	}
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(Assignment node) {
//		System.out.println("Assignment left node type: "+node.getLeftHandSide().getNodeType());
		Expression leftPart = node.getLeftHandSide(),
				rightPart = node.getRightHandSide();
		Operator operator = node.getOperator();
		String varName;
		if (leftPart instanceof SimpleName) {// 变量
			
			SimpleName var = (SimpleName)leftPart;
			varName = var.getIdentifier();
			if (env.existVar(varName)) { // 如果存在该变量,则用右侧的Expression赋值给左边变量，添加到env中
				ExpressionNode valueNode = null;
				// 处理右侧的表达式
				if (rightPart instanceof SimpleName) { // 如果右侧
					valueNode = env.getMap().get(((SimpleName)rightPart).getIdentifier()).clone();
				}else if (rightPart instanceof NumberLiteral) {
					String number = ((NumberLiteral)rightPart).getToken();
					valueNode = new ExpressionNode(ExpressionType.single_int, number);
					env.getMap().put(varName, valueNode);
				}else if (rightPart instanceof InfixExpression) { // calculate the value of a infix expression
					valueNode = InfixExpression2ExpressionNode((InfixExpression)rightPart);
				}else if (rightPart instanceof PrefixExpression) { // TODO add support for "-1", "--i"
					PrefixExpression rightPrefixExpression = (PrefixExpression)rightPart;
					PrefixExpression.Operator preOperator = rightPrefixExpression.getOperator();
					Expression preOperand = rightPrefixExpression.getOperand();
					if (preOperator.equals(PrefixExpression.Operator.MINUS) && preOperand instanceof NumberLiteral) {
						valueNode = new ExpressionNode(ExpressionType.single_int, "-"+((NumberLiteral)preOperand).getToken());
					}else if (preOperator.equals(PrefixExpression.Operator.INCREMENT)) {
						
					}else if (preOperand.equals(PrefixExpression.Operator.DECREMENT)) {
						
					}
				}else if (rightPart instanceof PostfixExpression) { // TODO add support for "i--"
					PostfixExpression rightPostfixExpression = (PostfixExpression)rightPart;
					
				}else {
					System.out.println("not supported right part");
				}
				env.getMap().put(varName, valueNode);
				return true;
			}else {
				System.out.println("The variable named \"" + varName +"\" not exist");
				return false;
			}
		}else if (leftPart instanceof FieldAccess) { // 成员变量
			
			return false;
		}else  {
			System.out.println("not supported Assignment left type");
			return false;
		}
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(InfixExpression node) {
		node.getLeftOperand();
		node.getRightOperand();
		node.getOperator();
		return true;
	}
	
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(VariableDeclarationExpression node) {
		System.out.println("VariableDeclarationExpression is : "+ node);
		if (node.getType().toString().equals("int")) {
			node.fragments();// 获取声明语句中的list<VariableDeclarationFragment>.
			VariableDeclarationFragment fragment;
			for (Iterator iterator = node.fragments().iterator(); iterator.hasNext();) {
				fragment = (VariableDeclarationFragment) iterator.next(); // 初始化+赋值单元
				String varNameString = fragment.getName().getIdentifier();// 变量部分
				ExpressionNode valueNode;
				Expression expression = fragment.getInitializer(); // 赋值部分
				if (expression == null) {// 如果只是声明，没有初始化，则用符号变量初始化
					valueNode = new ExpressionNode(ExpressionType.single_variable, UUIDGenerator.getUUID().toString(), null, null, null);
					env.getMap().put(varNameString, valueNode);
				}else if (expression instanceof NumberLiteral) { // 以数字初始化
					NumberLiteral numberLiteral = (NumberLiteral)expression;
					valueNode = new ExpressionNode(ExpressionType.single_int, numberLiteral.getToken()); // 用expression初始化tempNode
					env.getMap().put(varNameString, valueNode);
				}else if (expression instanceof SimpleName) {
					String varName = ((SimpleName)expression).getIdentifier();// 赋值右边的变量
					try {
						valueNode = env.getMap().get(varName).clone();
						env.getMap().put(varNameString, valueNode);
					} catch (NullPointerException e) {
						System.out.println("there is no var named as "+varNameString+" in the environment.");
						e.printStackTrace();
					}
				}else if (expression instanceof InfixExpression) {
					valueNode = InfixExpression2ExpressionNode((InfixExpression)expression);
					env.getMap().put(varNameString, valueNode);
				}
			}
			return true;
		}else {
			System.err.println("not supported type assignment: "+ node.getType());
			return false;
		}
	}
	
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ExpressionStatement node) {
		// 针对node中的expression调用visit方法
		node.getExpression().accept(this);
		return true;
	}
	
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SingleVariableDeclaration node) {
		System.out.println(node.getClass().getName());
		if (node.getType().toString().equals("int")) {
			String varNameString = node.getName().getIdentifier();
			ExpressionNode valueNode = new ExpressionNode(ExpressionType.single_variable, UUIDGenerator.getUUID().toString());
			env.getMap().put(varNameString, valueNode);
			/*
			 * if (expression == null) {// 如果只是声明，没有初始化，则用符号变量初始化 valueNode =
			 * new ExpressionNode(ExpressionType.single_variable,
			 * UUIDGenerator.getUUID().toString(), null, null, null);
			 * env.getMap().put(varNameString, valueNode); }else if (expression
			 * instanceof NumberLiteral) { // 以数字初始化 NumberLiteral numberLiteral
			 * = (NumberLiteral)expression;
			 * 
			 * valueNode = new ExpressionNode(ExpressionType.single_int,
			 * numberLiteral.getToken()); // 用expression初始化tempNode
			 * env.getMap().put(varNameString, valueNode); }else if (expression
			 * instanceof SimpleName) { String varName =
			 * ((SimpleName)expression).getIdentifier();// 赋值右边的变量 try {
			 * valueNode = env.getMap().get(varName).clone();
			 * env.getMap().put(varNameString, valueNode); } catch
			 * (NullPointerException e) {
			 * System.out.println("there is no var named as "
			 * +varNameString+" in the environment."); e.printStackTrace(); } }
			 */
			return true;
		} else {
			return false;
		}
			
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(PostfixExpression node) {
		return true;
	}
	
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(AnnotationTypeDeclaration node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(AnnotationTypeMemberDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(AnonymousClassDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ArrayAccess node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ArrayCreation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ArrayInitializer node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ArrayType node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(AssertStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(Block node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 * <p>Note: {@link LineComment} and {@link BlockComment} nodes are
	 * not considered part of main structure of the AST. This method will
	 * only be called if a client goes out of their way to visit this
	 * kind of node explicitly.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(BlockComment node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(BooleanLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(BreakStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(CastExpression node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(CatchClause node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(CharacterLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ClassInstanceCreation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(CompilationUnit node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ConditionalExpression node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ConstructorInvocation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ContinueStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(DoStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(EmptyStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(EnhancedForStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(EnumConstantDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(EnumDeclaration node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(FieldAccess node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(FieldDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ForStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(IfStatement node) {
		
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ImportDeclaration node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(InstanceofExpression node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(Initializer node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(LabeledStatement node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 * <p>Note: {@link LineComment} and {@link BlockComment} nodes are
	 * not considered part of main structure of the AST. This method will
	 * only be called if a client goes out of their way to visit this
	 * kind of node explicitly.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(LineComment node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(MarkerAnnotation node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(MemberRef node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(MemberValuePair node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(MethodRef node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(MethodRefParameter node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(MethodDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(MethodInvocation node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(Modifier node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(NormalAnnotation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(NullLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(NumberLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(PackageDeclaration node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(ParameterizedType node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ParenthesizedExpression node) {
		return true;
	}



	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(PrefixExpression node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(PrimitiveType node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(QualifiedName node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(QualifiedType node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ReturnStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SimpleName node) {
		String name = node.getIdentifier();
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SimpleType node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(SingleMemberAnnotation node) {
		return true;
	}



	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(StringLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SuperConstructorInvocation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SuperFieldAccess node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SuperMethodInvocation node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SwitchCase node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SwitchStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(SynchronizedStatement node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(TagElement node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.0
	 */
	public boolean visit(TextElement node) {
		return true;
	}


	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ThisExpression node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(ThrowStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(TryStatement node) {
		return true;
	}
	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(TypeDeclaration node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(TypeDeclarationStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(TypeLiteral node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(TypeParameter node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.7.1
	 */
	public boolean visit(UnionType node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(VariableDeclarationStatement node) {
//		System.out.println("VariableDeclarationStatement is : "+ node);// 2，这是什么？
		if (node.getType().toString().equals("int")) {
			node.fragments();// 获取声明语句中的list<VariableDeclarationFragment>.
			VariableDeclarationFragment fragment;
			for (Iterator iterator = node.fragments().iterator(); iterator.hasNext();) {
				fragment = (VariableDeclarationFragment) iterator.next(); // 初始化+赋值单元
				String varNameString = fragment.getName().getIdentifier();// 变量部分
				ExpressionNode valueNode;
				Expression expression = fragment.getInitializer(); // 赋值部分
				if (expression == null) {// 如果只是声明，没有初始化，则用符号变量初始化
					valueNode = new ExpressionNode(ExpressionType.single_variable, UUIDGenerator.getUUID().toString(), null, null, null);
					env.getMap().put(varNameString, valueNode);
				}else if (expression instanceof NumberLiteral) { // 以数字初始化
					NumberLiteral numberLiteral = (NumberLiteral)expression;
					valueNode = new ExpressionNode(ExpressionType.single_int, numberLiteral.getToken()); // 用expression初始化tempNode
					env.getMap().put(varNameString, valueNode);
				}else if (expression instanceof SimpleName) {
					String varName = ((SimpleName)expression).getIdentifier();// 赋值右边的变量
					try {
						valueNode = env.getMap().get(varName).clone();
						env.getMap().put(varNameString, valueNode);
					} catch (NullPointerException e) {
						System.out.println("there is no var named as "+varNameString+" in the environment.");
						e.printStackTrace();
					}
				}else if (expression instanceof InfixExpression) {
					valueNode = InfixExpression2ExpressionNode((InfixExpression)expression);
					env.getMap().put(varNameString, valueNode);
				}
			}
			return true;
		}else {
			System.err.println("not supported type assignment: "+ node.getType());
			return false;
		}
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(VariableDeclarationFragment node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 */
	public boolean visit(WhileStatement node) {
		return true;
	}

	/**
	 * Visits the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing and return true.
	 * Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if the children of this node should be
	 * visited, and <code>false</code> if the children of this node should
	 * be skipped
	 * @since 3.1
	 */
	public boolean visit(WildcardType node) {
		return true;
	}
/*
	
	*//**
	 * Visits the given AST node prior to the type-specific visit
	 * (before <code>visit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *
	 * @see #preVisit2(ASTNode)
	 *//*
	public void preVisit(ASTNode node) {
		// default implementation: do nothing
	}

	*//**
	 * Visits the given AST node prior to the type-specific visit (before <code>visit</code>).
	 * <p>
	 * The default implementation calls {@link #preVisit(ASTNode)} and then
	 * returns true. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @return <code>true</code> if <code>visit(node)</code> should be called,
	 * and <code>false</code> otherwise.
	 * @see #preVisit(ASTNode)
	 * @since 3.5
	 *//*
	public boolean preVisit2(ASTNode node) {
		preVisit(node);
		return true;
	}

	*//**
	 * Visits the given AST node following the type-specific visit
	 * (after <code>endVisit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void postVisit(ASTNode node) {
		// default implementation: do nothing
	}



	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(AnnotationTypeDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(AnnotationTypeMemberDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(AnonymousClassDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ArrayAccess node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ArrayCreation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ArrayInitializer node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ArrayType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(AssertStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(Assignment node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(Block node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * <p>Note: {@link LineComment} and {@link BlockComment} nodes are
	 * not considered part of main structure of the AST. This method will
	 * only be called if a client goes out of their way to visit this
	 * kind of node explicitly.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(BlockComment node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(BooleanLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(BreakStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(CastExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(CatchClause node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(CharacterLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ClassInstanceCreation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(CompilationUnit node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ConditionalExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ConstructorInvocation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ContinueStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(DoStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(EmptyStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(EnhancedForStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(EnumConstantDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(EnumDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ExpressionStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(FieldAccess node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(FieldDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ForStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(IfStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ImportDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(InfixExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(InstanceofExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(Initializer node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(Javadoc node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(LabeledStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * <p>Note: {@link LineComment} and {@link BlockComment} nodes are
	 * not considered part of main structure of the AST. This method will
	 * only be called if a client goes out of their way to visit this
	 * kind of node explicitly.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(LineComment node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(MarkerAnnotation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(MemberRef node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(MemberValuePair node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(MethodRef node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(MethodRefParameter node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(MethodDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(MethodInvocation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(Modifier node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(NormalAnnotation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(NullLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(NumberLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(PackageDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(ParameterizedType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ParenthesizedExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(PostfixExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(PrefixExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(PrimitiveType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(QualifiedName node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(QualifiedType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ReturnStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SimpleName node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SimpleType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(SingleMemberAnnotation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SingleVariableDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(StringLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SuperConstructorInvocation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SuperFieldAccess node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SuperMethodInvocation node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SwitchCase node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SwitchStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(SynchronizedStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(TagElement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.0
	 *//*
	public void endVisit(TextElement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ThisExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(ThrowStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(TryStatement node) {
		// default implementation: do nothing
	}
	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(TypeDeclaration node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(TypeDeclarationStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(TypeLiteral node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(TypeParameter node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.7.1
	 *//*
	public void endVisit(UnionType node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(VariableDeclarationExpression node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(VariableDeclarationStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(VariableDeclarationFragment node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 *//*
	public void endVisit(WhileStatement node) {
		// default implementation: do nothing
	}

	*//**
	 * End of visit the given type-specific AST node.
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 *
	 * @param node the node to visit
	 * @since 3.1
	 *//*
	public void endVisit(WildcardType node) {
		// default implementation: do nothing
	}
	*/

	public ProgramEnv getEnv() {
		return env;
	}

	public void setEnv(ProgramEnv env) {
		this.env = env;
	}

}
