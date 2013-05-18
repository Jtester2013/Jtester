/*******************************************************************************
 * Copyright (c) 2009, 2010 Alena Laskavaia 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alena Laskavaia  - initial API and implementation
 *******************************************************************************/
package core.common.cfg.model;

import org.eclipse.jdt.core.dom.Expression;

import core.common.cfg.interfaces.IBranchNode;

/**
 * Branch node is a node with on incoming arc, one outgoing arc and a "string"
 * label. Can be used to represent branches of if, switch and labelled
 * statements.
 */
public class BranchNode extends PlainNode implements IBranchNode {
	protected String label;
	boolean detected = false;
	int visitTime = 0;

	public boolean isDetected() {
		return detected;
	}

	public void setDetected(boolean visited) {
		this.detected = visited;
	}
	
	public boolean revertDetected(){
		boolean revert = !detected;
		detected = revert;
		return detected;
	}

	protected BranchNode(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public int getVisitTime() {
		return visitTime;
	}
	
	public void setVisitTime(int n) {
		visitTime=n;
	}

	public void increaseVisitTime() {
		visitTime = visitTime+1;
	}
	
	// TODO 返回此分支对应的条件表达式
	public Object getCondition(){
//		Expression
		return this.getData();
	}

	@Override
	public String toStringData() {
		return label + " " + getData().toString();
	}
}
