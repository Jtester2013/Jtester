package core.jtester.staticanalysis.semantics_extraction;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.model.AbstractBasicBlock;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.semantics.DeclarationSemantics;
import core.common.model.semantics.InferenceSemantics;
import core.common.model.semantics.MethodSemantics;
import core.common.model.semantics.SemanticsStore;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;
import core.common.util.ASTUtil;

public class SemanticsExtractor implements IJob{
	protected String name = this.getClass().getSimpleName();
	CompilationUnit root;
	SemanticsStore store;
	
	@Override
	public boolean run(TestData data) {
		TestFile file = data.getCurrentTestFile();
		root = (CompilationUnit) file.get(JobConst.AST);
		store = new SemanticsStore();
		extractSemantics(file);
		file.put(JobConst.SEMANTICS, store);
		return true;
	}

	private void extractSemantics(TestFile file) {
		List<JavaControlFlowGraph> cfgs = (List<JavaControlFlowGraph>) file.get(JobConst.CONTROL_FLOW_GRAPH);
		for(JavaControlFlowGraph cfg: cfgs){
			handleMethod(cfg.getMethod());
			Iterator<IBasicBlock> nodes  = cfg.getNodes().iterator();
			while(nodes.hasNext()){
				AbstractBasicBlock node = (AbstractBasicBlock) nodes.next();
				ASTNode treeNode= (ASTNode) node.getData();
				handleSemantics(treeNode);
			}
		}
	}
	
	private void handleMethod(MethodDeclaration method){
		MethodSemantics ms = new MethodSemantics();
		ms.setLine(getLineNumber(method));
		ms.setName(method.getName());
		ms.setParametors(method.parameters());
		store.putMethodStore(ms);
		
		List<ASTNode> parametors = method.parameters();
		for(ASTNode node: parametors){
			handleSemantics(node);
		}
	}

	private void handleSemantics(ASTNode node){
		if(node == null){
			return;
		}
		switch(node.getNodeType()){
		case ASTNode.VARIABLE_DECLARATION_STATEMENT:
			VariableDeclarationStatement vds = (VariableDeclarationStatement)node;
			List<VariableDeclarationFragment> fragments = vds.fragments();
			for(VariableDeclarationFragment vdf : fragments){
				// deal with variable declaration
				DeclarationSemantics semantics = new DeclarationSemantics();
				semantics.setLine(getLineNumber(vds));
				semantics.setType(vds.getType());
				semantics.setName(vdf.getName());
				semantics.setValue(vdf.getInitializer());
				store.putDeclarationStore(semantics);
				
				// deal with variable inference
				handleExpression(vdf.getInitializer());
			}
			break;
		case ASTNode.EXPRESSION_STATEMENT:
			Expression exp = ((ExpressionStatement)node).getExpression();
			handleExpression(exp);
			//System.out.println(exp.structuralPropertiesForType());
			break;
		case ASTNode.INFIX_EXPRESSION:
			InfixExpression ife = (InfixExpression)node;
			handleExpression(ife);
			break;
		case ASTNode.TRY_STATEMENT:
		case ASTNode.SINGLE_VARIABLE_DECLARATION:
			break;
		case ASTNode.RETURN_STATEMENT:
			ReturnStatement rs = (ReturnStatement)node;
			handleExpression(rs.getExpression());
			break;
		case ASTNode.INSTANCEOF_EXPRESSION:	
			break;
		default:
			System.err.println("unhandled node type: " + node.getNodeType() + "  " + node);
			break;	
		}
	}
	
	private void handleExpression(Expression exp){
		if(exp == null){
			return;
		}

		switch(exp.getNodeType()){
		case ASTNode.NUMBER_LITERAL:
		case ASTNode.BOOLEAN_LITERAL:
		case ASTNode.NULL_LITERAL:
		case ASTNode.FIELD_ACCESS:
			break;
		case ASTNode.CAST_EXPRESSION:
			break;
		case ASTNode.THIS_EXPRESSION:
			break;
		case ASTNode.ARRAY_CREATION:
			break;
		case ASTNode.QUALIFIED_NAME:
			QualifiedName qn = (QualifiedName)exp;
			handleExpression(qn.getQualifier());
			break;
		case ASTNode.CLASS_INSTANCE_CREATION:
			ClassInstanceCreation cic = (ClassInstanceCreation)exp;
			List<Expression> arguments = cic.arguments();
			for(Expression argu: arguments){
				handleExpression(argu);
			}
			break;
		case ASTNode.SIMPLE_NAME:
			SimpleName name = (SimpleName) exp;
			InferenceSemantics snSemantics = new InferenceSemantics();
			snSemantics.setLine(getLineNumber(name));
			snSemantics.setName(name);
			snSemantics.setIndex(0);
			store.putInferenceStore(snSemantics);
			break;
		case ASTNode.INFIX_EXPRESSION:
			InfixExpression ife = (InfixExpression)exp;
			handleExpression(ife.getLeftOperand());
			handleExpression(ife.getRightOperand());
			break;
		case ASTNode.ARRAY_ACCESS:
			ArrayAccess aa = (ArrayAccess)exp;
			InferenceSemantics aaSemantics = new InferenceSemantics();
			aaSemantics.setLine(getLineNumber(aa));
			aaSemantics.setName((Name) aa.getArray());
			aaSemantics.setIndex(Integer.parseInt(((NumberLiteral)aa.getIndex()).toString()));
			store.putInferenceStore(aaSemantics);
			break;
		case ASTNode.ASSIGNMENT:
			Assignment assign = (Assignment) exp;
			Expression left = assign.getLeftHandSide();
			Expression right = assign.getRightHandSide();
			handleExpression(left);
			handleExpression(right);
			updateVariable(left, right);
			break;
		case ASTNode.METHOD_INVOCATION:
			MethodInvocation mi = (MethodInvocation)exp;
			InferenceSemantics miSemantics = new InferenceSemantics();
			miSemantics.setLine(getLineNumber(mi));
			miSemantics.setName((Name)mi.getExpression());
			miSemantics.setMethod(mi.getName());
			store.putInferenceStore(miSemantics);
			break;
		case ASTNode.PARENTHESIZED_EXPRESSION:
			ParenthesizedExpression pte = (ParenthesizedExpression)exp;
			handleExpression(pte.getExpression());
			break;
		case ASTNode.PREFIX_EXPRESSION:
			break;
		default:
			System.err.println("unhandled expression type:" + exp.getNodeType() + "  " + exp);
			break;
		}
	}
	
	/**
	 * update variable values for assignments
	 * 
	 * NOTE: Now this method doesn't fulfill all the conditions.
	 * 
	 * @param semantics
	 * @param value
	 */
	private void updateVariable(Expression left, Expression right){
		if(left == null || !(left instanceof Name)){
			return;
		}
		
		Iterator<DeclarationSemantics> ir = store.iterator1();
		while(ir.hasNext()){
			DeclarationSemantics ds = ir.next();
			if(ds.getName().toString().equals(left.toString())){
				ds.setValue(right);
				break;
			}
		}
	}
	
	private int getLineNumber(ASTNode node){
		if(root != null && node != null){
			return ASTUtil.getLineNumber(root, node);
		}
		return -1;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
