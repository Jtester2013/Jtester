package core.common.cfg.javacfg;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import core.common.cfg.interfaces.IExitNode;
import core.common.cfg.interfaces.IStartNode;
import core.common.cfg.model.ControlFlowGraph;
import core.common.model.functionblock.ConditionExpression;

public class JavaControlFlowGraph extends ControlFlowGraph {
	private MethodDeclaration method;
	private List<ConditionExpression> conditions;
	
	public JavaControlFlowGraph(IStartNode start, Collection<IExitNode> exitNodes) {
		super(start, exitNodes);
	}

	public static JavaControlFlowGraph build(MethodDeclaration def, boolean cut) {
		return new ControlFlowGraphBuilder().build(def, cut);
	}
	
	public static JavaControlFlowGraph build(MethodDeclaration def) {
		return new ControlFlowGraphBuilder().build(def);
	}
	
	public void setMethod(MethodDeclaration method){
		this.method = method;
	}
	
	public MethodDeclaration getMethod(){
		return method;
	}
	
	public void setConditions(List<ConditionExpression> conditions) {
		this.conditions = conditions;
	}

	public List<ConditionExpression> getConditions() {
		return conditions;
	}
	
	// get the method name of this cfg
	public String getSignature(){
		return method.getName().toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
