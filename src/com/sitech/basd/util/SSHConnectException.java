package com.sitech.basd.util;

/**
 * 
 * <p>
 * Title: SSHConnectException
 * </p>
 * <p>
 * Description: SSH连接异常
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-29 下午2:40:59
 * 
 */
@SuppressWarnings("serial")
public class SSHConnectException extends Exception {
	public SSHConnectException() {

	}

	public SSHConnectException(String message) {
		super(message);
	}

	public SSHConnectException(String message, Throwable th) {
		super(message, th);
	}
}
