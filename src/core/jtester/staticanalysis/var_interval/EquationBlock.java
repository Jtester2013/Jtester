package core.jtester.staticanalysis.var_interval;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.model.functionblock.AnalysisBlock;

public class EquationBlock extends AnalysisBlock {
	public EquationBlock(IBasicBlock bb, int lable) {
		super(bb, lable);
	}
}
