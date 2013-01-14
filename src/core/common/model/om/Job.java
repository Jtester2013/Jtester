package core.common.model.om;

import java.util.Set;

public interface Job {
	public Set<? extends Job> getConstraint();
}
