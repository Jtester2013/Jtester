package core.jtester.staticanalysis.svd.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import core.common.cfg.javacfg.ControlFlowGraphBuilder;
import core.common.cfg.javacfg.JavaControlFlowGraph;
import core.jtester.staticanalysis.svd.execution.Path;
import core.jtester.staticanalysis.svd.execution.PathGenerator;

public class PathGenerateDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\core\\jtester\\staticanalysis\\svd\\test\\SVDTest.java";
		System.out.println(filePath);
		JavaControlFlowGraph[] cfgsArray = PathGenerator.read(filePath);
		JavaControlFlowGraph cfgInstance;
		for (int i = 0; i < cfgsArray.length; i++) {
			System.out.println("Begin to analyse CFG: "+cfgsArray[i].getSignature());
			// 抽取程序路径
			cfgInstance = cfgsArray[i];
			Path[] paths = PathGenerator.abstractPath(cfgInstance);
			System.out.println(paths.length);
			// 打印path
			for (int j = 0; j < paths.length; j++) {
				System.out.println("Print path："+ j);
				PathGenerator.printPath(paths[j].getPathNodes());
			}
		}
	}
}
