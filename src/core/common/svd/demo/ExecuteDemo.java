package core.common.svd.demo;

import java.io.IOException;

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
		String filePath = System.getProperty("user.dir")+"\\src\\core\\common\\svd\\test\\SVDTest.java";
		System.out.println(filePath);
		JavaControlFlowGraph[] cfgsArray = PathGenerator.read(filePath);
		JavaControlFlowGraph cfgInstance;
		for (int i = 0; i < cfgsArray.length; i++) {
			System.out.println("Begin to analyse CFG: "+cfgsArray[i].getSignature());
			// 抽取程序路径
			cfgInstance = cfgsArray[i];
			Path[] paths = PathGenerator.abstractPath(cfgInstance);
			System.out.println(paths.length);
			// 对第一条路径进行符号执行
			if (paths.length>0) {
				Path exePath = paths[0];
				SymbolExecutor executor = new SymbolExecutor();
				executor.execute(exePath);
			}else {
				System.out.println("path is not detected");
			}
		}
	}

}
