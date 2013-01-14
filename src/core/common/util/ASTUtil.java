package core.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;
import java.util.Stack;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

/**
 * 
 * @author Bin
 *
 * Some utility about AST
 */
public class ASTUtil {

	public static String getMethodNodeScope(Stack<ASTNode> path,
			MethodDeclaration node) {
		Enumeration<ASTNode> iter = path.elements();
		StringBuffer scope = new StringBuffer();
		while (iter.hasMoreElements()) {
			ASTNode item = iter.nextElement();
			switch (item.getNodeType()) {
			case ASTNode.COMPILATION_UNIT:
				CompilationUnit cu = (CompilationUnit) item;
				PackageDeclaration pack = cu.getPackage();
				if (pack != null) {
					scope.append(cu.getPackage().getName().toString() + ".");
				}
				break;
			case ASTNode.TYPE_DECLARATION:
				TypeDeclaration td = (TypeDeclaration) item;
				scope.append(td.getName().toString() + ".");
				break;
			case ASTNode.METHOD_DECLARATION:
				MethodDeclaration md = (MethodDeclaration) item;
				scope.append(md.getName().toString());
				scope.append("(");
				boolean needDelete = false;
				for (Object o : md.parameters()) {
					if (o instanceof SingleVariableDeclaration) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						scope.append(svd.getType().toString());
						scope.append(",");
						needDelete = true;
					}
				}
				if (needDelete)
					scope.deleteCharAt(scope.length() - 1);
				scope.append(").");
				break;

			}

		}

		scope.deleteCharAt(scope.length() - 1);
		return scope.toString();
	}

	public static String getVariableNodeScope(Stack<ASTNode> path,
			VariableDeclarationStatement node) {
		Enumeration<ASTNode> iter = path.elements();
		StringBuffer scope = new StringBuffer();
		while (iter.hasMoreElements()) {
			ASTNode item = iter.nextElement();
			switch (item.getNodeType()) {
			case ASTNode.TYPE_DECLARATION:
				TypeDeclaration td = (TypeDeclaration) item;
				scope.append(td.getName().toString() + ".");
				break;
			case ASTNode.INITIALIZER:
				// TODO
				break;
			case ASTNode.ANONYMOUS_CLASS_DECLARATION:
				// TODO
				break;
			case ASTNode.METHOD_DECLARATION:
				MethodDeclaration md = (MethodDeclaration) item;
				scope.append(md.getName().toString());
				scope.append("(");
				boolean needDelete = false;
				for (Object o : md.parameters()) {
					if (o instanceof SingleVariableDeclaration) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						scope.append(svd.getType().toString());
						scope.append(",");
						needDelete = true;
					}
				}
				if (needDelete)
					scope.deleteCharAt(scope.length() - 1);
				scope.append(").");
				break;
			case ASTNode.COMPILATION_UNIT:
				CompilationUnit cu = (CompilationUnit) item;
				PackageDeclaration pack = cu.getPackage();
				if (pack != null)
					scope.append(cu.getPackage().getName().toString() + ".");
				break;
			}

		}

		for (Object o : node.fragments()) {
			if (o instanceof VariableDeclarationFragment) {
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) o;
				scope.append(vdf.getName().toString());
			}
		}

		return scope.toString();
	}

	public static String getArgumentNodeScope(Stack<ASTNode> path,
			SingleVariableDeclaration node) {
		Enumeration<ASTNode> iter = path.elements();
		StringBuffer scope = new StringBuffer();
		while (iter.hasMoreElements()) {
			ASTNode item = iter.nextElement();
			switch (item.getNodeType()) {
			case ASTNode.TYPE_DECLARATION:
				TypeDeclaration td = (TypeDeclaration) item;
				scope.append(td.getName().toString() + ".");
				break;
			case ASTNode.INITIALIZER:
				// TODO
				break;
			case ASTNode.ANONYMOUS_CLASS_DECLARATION:
				// TODO
				break;
			case ASTNode.METHOD_DECLARATION:
				MethodDeclaration md = (MethodDeclaration) item;
				scope.append(md.getName().toString());
				scope.append("(");
				boolean needDelete = false;
				for (Object o : md.parameters()) {
					if (o instanceof SingleVariableDeclaration) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						scope.append(svd.getType().toString());
						scope.append(",");
						needDelete = true;
					}
				}
				if (needDelete)
					scope.deleteCharAt(scope.length() - 1);
				scope.append(").");
				break;
			case ASTNode.COMPILATION_UNIT:
				CompilationUnit cu = (CompilationUnit) item;
				PackageDeclaration pack = cu.getPackage();
				if (pack != null)
					scope.append(cu.getPackage().getName().toString() + ".");
				break;
			}

		}

		scope.append(node.getName().toString());
		return scope.toString();
	}

	public static String getFieldNodeScope(Stack<ASTNode> path,
			FieldDeclaration node) {
		Enumeration<ASTNode> iter = path.elements();
		StringBuffer scope = new StringBuffer();
		while (iter.hasMoreElements()) {
			ASTNode item = iter.nextElement();
			switch (item.getNodeType()) {
			case ASTNode.COMPILATION_UNIT:
				CompilationUnit cu = (CompilationUnit) item;
				PackageDeclaration pack = cu.getPackage();
				if (pack != null)
					scope.append(cu.getPackage().getName().toString() + ".");
				break;
			case ASTNode.TYPE_DECLARATION:
				TypeDeclaration td = (TypeDeclaration) item;
				scope.append(td.getName().toString() + ".");
				break;

			case ASTNode.METHOD_DECLARATION:
				MethodDeclaration md = (MethodDeclaration) item;
				scope.append(md.getName().toString());
				scope.append("(");
				boolean needDelete = false;
				for (Object o : md.parameters()) {
					if (o instanceof SingleVariableDeclaration) {
						SingleVariableDeclaration svd = (SingleVariableDeclaration) o;
						scope.append(svd.getType().toString());
						scope.append(",");
						needDelete = true;
					}
				}
				if (needDelete)
					scope.deleteCharAt(scope.length() - 1);
				scope.append(").");
				break;
			}

		}

		for (Object o : node.fragments()) {
			if (o instanceof VariableDeclarationFragment) {
				VariableDeclarationFragment vdf = (VariableDeclarationFragment) o;
				scope.append(vdf.getName().toString());
			}
		}

		return scope.toString();
	}

	public static ASTNode copyNode(AST ast, ASTNode node) {
		ASTNode copy = ASTNode.copySubtree(ast, node);
		ASTVisitor visitor = new ResetASTVisitor();
		copy.accept(visitor);
		return copy;
	}

	public static void getAllSrcFile(File dir, ArrayList<String> srcs,
			Set<String> srcPaths) {
		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getAbsolutePath().endsWith(".java")) {
				srcs.add(file.getAbsolutePath());
				srcPaths.add(file.getParentFile().getAbsolutePath());
			} else if (file.isDirectory()) {
				getAllSrcFile(file, srcs, srcPaths);
			}
		}
	}
}

class ResetASTVisitor extends ASTVisitor {
	
	@Override
	public void preVisit(ASTNode node){
		node.setSourceRange(0, 0);
	}

}
