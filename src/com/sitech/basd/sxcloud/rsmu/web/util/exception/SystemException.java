package com.sitech.basd.sxcloud.rsmu.web.util.exception;

public class SystemException extends BaseException {

	static final long serialVersionUID = 0x67ae6f2e11a0a2e1L;
	static String msg = "(系统级异常)";

	public SystemException() {
		super(msg);
	}

	public SystemException(String message, Throwable cause) {
		super(msg + message, cause);
	}

	public SystemException(String message) {
		super(msg + message);
	}

	public SystemException(Throwable cause) {
		super(msg, cause);
	}

}
