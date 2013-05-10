package core.common.cfg.javacfg;

import org.eclipse.jdt.core.dom.ASTNode;

import core.common.cfg.interfaces.IBranchNode;
import core.common.cfg.interfaces.ICfgData;
import core.common.cfg.interfaces.IConnectorNode;
import core.common.cfg.interfaces.IDecisionNode;
import core.common.cfg.interfaces.IExitNode;
import core.common.cfg.interfaces.INodeFactory;
import core.common.cfg.interfaces.IPlainNode;
import core.common.cfg.model.DecisionType;
import core.common.cfg.model.NodeFactory;

public class JavaNodeFactory extends NodeFactory implements INodeFactory {
	public JavaNodeFactory() {
		super();
	}

	public IPlainNode createPlainNode() {
		return new JavaPlainNode();
	}

	public IDecisionNode createDecisionNode() {
		return new JavaDecisionNode();
	}

	public IExitNode createExitNode() {
		return new JavaExitNode();
	}

	public JavaPlainNode createPlainNode(ASTNode ast) {
		IPlainNode node = createPlainNode();
		((ICfgData) node).setData(ast);
		return (JavaPlainNode) node;
	}

	public JavaDecisionNode createDecisionNode(ASTNode ast) {
		IDecisionNode node = createDecisionNode();
		((ICfgData) node).setData(ast);
		return (JavaDecisionNode) node;
	}
	
	/**
	 * added by zzj
	 * create a decision node with a type sign
	 * @param ast
	 * @param type core.common.svd.path.DecisionType
	 * @return
	 */
	public JavaDecisionNode createDecisionNode(ASTNode ast, DecisionType type){
		JavaDecisionNode node = createDecisionNode(ast);
		node.setType(type);
		return node;
	}

	public JavaExitNode createExitNode(ASTNode ast) {
		IExitNode node = createExitNode();
		((ICfgData) node).setData(ast);
		return (JavaExitNode) node;
	}

	public IBranchNode createBranchNode(ASTNode caseSt) {
		IBranchNode node = createBranchNode("Java Branch");
		((ICfgData) node).setData(caseSt);
		return node;
	}
	
	public IBranchNode createBranchNode(String label, ASTNode expression) {
		IBranchNode node = createBranchNode(label);
		((ICfgData) node).setData(expression);
		return node;
	}
}
