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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.common.cfg.interfaces.IBasicBlock;
import core.common.cfg.interfaces.IBranchNode;
import core.common.cfg.interfaces.IConnectorNode;
import core.common.cfg.interfaces.IDecisionNode;

/**
 * @see {@link IDecisionNode}
 */
public class DecisionNode extends AbstractSingleIncomingNode implements
		IDecisionNode {
	private List<IBasicBlock> next = new ArrayList<IBasicBlock>(2);// branches of this decision
	private IConnectorNode conn;
	private DecisionType type = DecisionType.unleagal_type;
	private IConnectorNode continueNode = null;
	
	IConnectorNode continueConn = null;
	boolean detected = false;

	public IConnectorNode getContinueConn() {
		return continueConn;
	}

	public void setContinueConn(IConnectorNode continueConn) {
		this.continueConn = continueConn;
	}

	public boolean isDetected() {
		boolean isDetected = true;
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			BranchNode tempBranch = (BranchNode) iterator.next();
			if (!tempBranch.isDetected()) {
				isDetected = false;
				break;
			}
		}
		return isDetected;
	}

	/*public void setDetected(boolean detected) {
		this.detected = detected;
	}*/

	public boolean revertDetected() {
		boolean revert = !detected;
		detected = revert;
		return detected;
	}

	public BranchNode getUndetectedBranch() {
		BranchNode resultBranchNode = null;
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			BranchNode tempBranch = (BranchNode) iterator.next();
			if (!tempBranch.isDetected()) {
				resultBranchNode= tempBranch;
			}
		}
		return resultBranchNode;
	}

	public void cleanBranch(){
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			BranchNode branch = (BranchNode) iterator.next();
			branch.setDetected(false);
			if (branch.getLabel().equals(IBranchNode.WHILE_THEN)) {
				branch.setVisitTime(0);
			}
		}
	}
	
	
	public void cleanWhileBranch(){
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			BranchNode branch = (BranchNode) iterator.next();
			branch.setDetected(false);
		}
		
	}
	/**
	 * @param prev
	 */
	protected DecisionNode() {
		super();
	}

	@Override
	public void addOutgoing(IBasicBlock node) {
		IBranchNode cnode = (IBranchNode) node; // cast to throw CCE
		next.add(cnode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.cdt.codan.provisional.core.model.cfg.IBasicBlock#
	 * getOutgoingIterator()
	 */
	public IBasicBlock[] getOutgoingNodes() {
		return next.toArray(new IBasicBlock[next.size()]);
	}
	
	/**
	 * 对于循环体的Decision来说，取得其循环体分支的branch
	 * @return
	 */
	public BranchNode getLoopBranch(){
		BranchNode tempBranch = null;
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			tempBranch= (BranchNode) iterator.next();
			if (tempBranch.label.equals(IBranchNode.WHILE_THEN)) {
				break;
			}
		}
		return tempBranch;
	}
	/**
	 * 对于循环体的Decision来说，取得退出循环的branch
	 * @return
	 */
	public BranchNode getBreakBranch(){
		BranchNode tempBranch = null;
		for (Iterator iterator = next.iterator(); iterator.hasNext();) {
			tempBranch= (BranchNode) iterator.next();
			if (tempBranch.label.equals(IBranchNode.WHILE_ELSE)) {
				break;
			}
		}
		return tempBranch;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see analysis.cfg.interfaces.IBasicBlock#getOutgoingSize ()
	 */
	public int getOutgoingSize() {
		return next.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.cdt.codan.provisional.core.model.cfg.IDecisionNode#
	 * getConnectionNode()
	 */
	public IConnectorNode getMergeNode() {
		return conn;
	}

	public void setMergeNode(IConnectorNode conn) {
		this.conn = conn;
	}
	
	/**
	 * do with type of DecisionNode
	 * @return
	 */
	public DecisionType getType() {
		return type;
	}
	public void setType(DecisionType type) {
		this.type = type;
	}
	
	
	/**
	 * set and get continueNode of a DecisionNode in a loop element such as while/do-while/for/
	 * @return
	 */
	public IConnectorNode getContinueNode() {
		return continueNode;
	}

	public void setContinueNode(IConnectorNode continueNode) {
		this.continueNode = continueNode;
	}
}
