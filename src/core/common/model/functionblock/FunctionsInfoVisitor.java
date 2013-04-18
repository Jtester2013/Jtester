package core.common.model.functionblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.model.jobflow.IJob;
import core.common.model.jobflow.JobConst;
import core.common.model.test.TestResultItem;
import core.common.model.test.TestData;
import core.common.model.test.TestFile;

public class FunctionsInfoVisitor extends ASTVisitor implements IJob {
	private String name = this.getClass().getSimpleName();
	
	private HashMap<String, FunctionInfo> functionsInfo;
	private int funCount = 0;
	private boolean cutUnaccessibleBranch = false;
	
	public FunctionsInfoVisitor() {
		this(true, true);
	}
	
	public FunctionsInfoVisitor(boolean cut){
		this(true, cut);
	}

	public FunctionsInfoVisitor(boolean visitNodes, boolean cut) {
		super(visitNodes);
		cutUnaccessibleBranch = cut;
	}

	@Override
	public boolean run(TestData data) {
		if (data == null) {
			return false;
		}
		
		functionsInfo = new HashMap<String, FunctionInfo>();
		funCount = 0;
		
		TestFile file = data.getCurrentTestFile();
		Object astObj = file.get(JobConst.AST);
		if (astObj == null) {
			TestResultItem item = new TestResultItem(file.getPath(), getName(), JobConst.AST_NOT_FOUND);
			data.getTestResult().add(file.getPath(), item);
			return false;
		}

		ASTNode n = (ASTNode) astObj;
		n.accept(this);

		Iterator<FunctionInfo> ir = functionsInfo.values().iterator();
		List<JavaControlFlowGraph> cfgs = new ArrayList<JavaControlFlowGraph>();
		while(ir.hasNext()){
			FunctionInfo fi = ir.next();
			cfgs.add(fi.getJavaControlFlowGraph());
		}
		
		file.put(JobConst.CONTROL_FLOW_GRAPH, cfgs);
		file.put(JobConst.FUNCTIONSINFO, functionsInfo);
		return true;
	}

	public boolean visit(MethodDeclaration n) {
		FunctionInfo functionInfo = new FunctionInfo();
		String functionName = ((MethodDeclaration) n).getName().getFullyQualifiedName();
		JavaControlFlowGraph javacfg = JavaControlFlowGraph.build((MethodDeclaration) n, cutUnaccessibleBranch);
		javacfg.setMethod(n);
		functionInfo.setFuncName(functionName);
		functionInfo.setJavaControlFlowGraph(javacfg);
		functionInfo.setContextInfo(new ContextInfo(null, null, n.parameters(), 0));
		functionsInfo.put("f" + funCount, functionInfo);
		this.funCount++;

		return true;
	}

	// visit methods
	public boolean visit(CompilationUnit n) {
		return true;
	}

	// leave methods
	public boolean leave(CompilationUnit tu) {
		return true;
	}

	public boolean visit(ASTNode astAmbiguousNode) {
		return true;
	}

	@Override
	public String getName() {
		return name;
	}
}
