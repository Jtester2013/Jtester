package core.common.svd.path;

import core.common.cfg.model.*;
import core.common.cfg.interfaces.*;
import core.common.cfg.javacfg.*;

public class PathNode {
	AbstractBasicBlock data;
	
	public AbstractBasicBlock getData() {
		return data;
	}
	public void setData(AbstractBasicBlock data) {
		this.data = data;
	}
}
