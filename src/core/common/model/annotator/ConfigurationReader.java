package core.common.model.annotator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * 
 * @author Bin
 * 
 * Configuration file reader
 *
 */
public class ConfigurationReader {
	private static Configuration configuration = null;

	public static Configuration get(File dir) {
		configuration = new Configuration();
		List<String> srcFiles = getAllSrcFile(dir);
		for (String srcFile : srcFiles) {
			Src src = new Src(srcFile);
			ASTVisitor visitor = new ConfigurationReaderVisitor(configuration);
			src.getAstTree().accept(visitor);
		}
		return configuration;
	}

	public static Configuration get() {
		return configuration;
	}

	public static List<String> getAllSrcFile(File dir) {
		ArrayList<String> result = new ArrayList<String>();
		getAllSrcFile(result, dir);
		return result;
	}

	private static void getAllSrcFile(ArrayList<String> srcFileNames, File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith(".java"))
				srcFileNames.add(file.getAbsolutePath());
			else if (file.isDirectory())
				getAllSrcFile(srcFileNames, file);
		}
	}
}
