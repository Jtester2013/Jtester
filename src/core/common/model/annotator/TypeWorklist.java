package core.common.model.annotator;

import core.common.model.om.ConstraintVariable;
import core.common.worklist.FIFOWorklist;

/**
 * 
 * @author Bin
 *
 *	Type Inference Specific Worklist
 */
public class TypeWorklist extends FIFOWorklist<ConstraintVariable> {

	public TypeWorklist(ConstraintVariable[] constraintVariables) {
		super(constraintVariables);
	}

	@Override
	public boolean eval(ConstraintVariable variable) {
		return variable.updateSelf();
	}
	
}
