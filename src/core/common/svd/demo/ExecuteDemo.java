package core.common.svd.demo;

import java.io.IOException;
import java.util.LinkedList;

import core.common.cfg.javacfg.JavaControlFlowGraph;
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
		// 针对每一个method的cfg生成程序路径并进行符号执行
		for (int cfgIndex = 0; cfgIndex < cfgsArray.length; cfgIndex++) {
			methodNameString = cfgsArray[cfgIndex].getSignature();
			System.out.println("Begin to analyse CFG: "+methodNameString);
			// 抽取程序路径
			cfgInstance = cfgsArray[cfgIndex];
			Path[] paths = PathGenerator.abstractPath(cfgInstance);
			System.out.println("There are "+paths.length+" generated paths"+" for the method "+ methodNameString);
			// 对所有路径进行符号执行
			Path exePath;
			for (int pathIndex = 0; pathIndex < paths.length; pathIndex++) {
				System.out.println("Begin to symbolic execute path " + pathIndex);
				exePath = paths[cfgIndex];
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
