package core.common.svd.demo;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.common.cfg.model.BranchNode;
import core.common.svd.execution.SymbolExecutor;
import core.common.svd.path.Path;
import core.common.svd.path.PathGenerator;

public class ExecuteDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filePath = System.getProperty("user.dir")+"\\src\\core\\common\\svd\\test\\ASTViewTest.java";
//		String filePath = System.getProperty("user.dir")+"\\tested_files\\ontology\\People.java";
		System.out.println(filePath);
		JavaControlFlowGraph[] cfgsArray = PathGenerator.read(filePath);
		JavaControlFlowGraph cfgInstance;
		
		LinkedList<Path> executablePaths = new LinkedList<>();
		String methodNameString;
		for (int cfgIndex = 0; cfgIndex < cfgsArray.length; cfgIndex++) {
			methodNameString = cfgsArray[cfgIndex].getSignature();
			System.out.println("Begin to analyse CFG: "+methodNameString);
			// 抽取程序路径
			cfgInstance = cfgsArray[cfgIndex];
			Path[] paths = PathGenerator.abstractPath(cfgInstance);
			System.out.println("There are "+paths.length+" generated paths"+" for the method "+ methodNameString);
			// 打印pathCollection的所有path的条件分支情况
			for (int i = 0; i < paths.length; i++) {
				Path tempPath = paths[i];
				System.out.println("For path "+i);
				for (Iterator iterator = tempPath.iterator(); iterator
						.hasNext();) {
					Object block = iterator.next();
					if (block instanceof BranchNode) {
						BranchNode branchNode = (BranchNode)block;
						System.out.println("Expression:"+branchNode.getData()+"\t Label:"+branchNode.getLabel());
					}
				}
			}
			// 对所有路径进行符号执行
			Path exePath;
			for (int pathIndex = 0; pathIndex < paths.length; pathIndex++) {
				System.out.println("Begin to symbolic execute path " + pathIndex);
				exePath = paths[pathIndex];
				SymbolExecutor executor = new SymbolExecutor(exePath);
				boolean executable = executor.execute(exePath);
				if (executable) {// TODO:为其生成测试数据。 
					executablePaths.add(exePath);  //将返回值为true的路径添加到可执行路径中。
				}
				exePath.getEnv().printValue();
			}
			System.out.println("For the method: " + methodNameString
					+ ", there are " + executablePaths.size()
					+ " executablePath");
		}
	}
}
