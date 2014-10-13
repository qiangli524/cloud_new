/*
 * @(#) BaseException.java Sep 1, 2005
 *
 * Copyright 2005 Trust in Belief, Inc. All rights reserved.
 * Trust in Belief PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.sitech.basd.sxcloud.rsmu.web.util.exception;

/**
 * @author wangtao
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseException extends Exception {

	static final long serialVersionUID = 0xe9c95fac76557723L;

	public BaseException() {
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
