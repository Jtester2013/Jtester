package core.common.cfg.javacfg;

import core.common.cfg.model.ConnectorNode;

public class JavaConnectorNode extends ConnectorNode {
	private String label;
	public JavaConnectorNode(String label) {
		super();
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
	@Override
	public String toStringData() {
		return label + " " + getData().toString();
	}
}