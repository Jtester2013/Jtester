package core.common.cfg.javacfg;

import org.eclipse.jdt.core.dom.ASTNode;
import core.common.cfg.model.JumpNode;



public class JavaJumpNode extends JumpNode {
	private ASTNode labelData;
	JavaJumpNode(ASTNode label) {
		super();
		this.labelData = label;
	}

}