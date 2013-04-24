package core.common.svd.path;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ParseJavaSource {

	public ParseJavaSource() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public static CompilationUnit parseJavaSource(String soursePath){
		CompilationUnit unit = null;
		ASTParser parsert = ASTParser.newParser(AST.JLS3);
		String content;
		try {
			content = read(soursePath);
			parsert.setSource(content.toCharArray());
			unit = (CompilationUnit) parsert.createAST(null);
			return unit;
		} catch (IOException e) {
			System.out.println("the sourse file path: "+ soursePath + " can't be found");
			e.printStackTrace();
		}
		return null;
	}
	
	private static String read(String filePath) throws IOException {
		File file = new File(filePath);
		byte[] b = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(b);
		return new String(b);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
