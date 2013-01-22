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
package core.common.cfg.interfaces;

/**
 * Node that represent empty operator with label, such as case branch or label
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IBranchNode extends IBasicBlock, ISingleIncoming, ISingleOutgoing {
	/**
	 * Then branch of "if" statement
	 */
	public static String IF_THEN = "if then";
	/**
	 * Else branch of "if" statement
	 */
	public static String IF_ELSE = "if else";
	
	/**
	 * Then branch of "if" statement
	 */
	public static String WHILE_THEN = "while then";
	/**
	 * Else branch of "if" statement
	 */
	public static String WHILE_ELSE = "while else";
	
	/**
	 * Default branch of "switch" statement
	 */
	public static String DEFAULT = "default";

	/**
	 * @return label of a branch
	 */
	String getLabel();
}
