package core.common.cfg.javacfg;

import java.util.Collection;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import core.common.cfg.interfaces.IExitNode;
import core.common.cfg.interfaces.IStartNode;
import core.common.cfg.model.ControlFlowGraph;

public class JavaControlFlowGraph extends ControlFlowGraph {
	private MethodDeclaration method;
	
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
	
	@Override
	public String toString() {
		return super.toString();
	}
}
