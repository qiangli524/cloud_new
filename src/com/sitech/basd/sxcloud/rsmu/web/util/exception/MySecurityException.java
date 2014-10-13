package com.sitech.basd.sxcloud.rsmu.web.util.exception;

public class MySecurityException extends BaseException {

	static final long serialVersionUID = 0xc875f450fb287d79L;
	static String msg = "您没有足够的访问权限，请以其他身份重新登录或通知管理员！";

	public MySecurityException() {
		super(msg);
	}

	public MySecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySecurityException(String message) {
		super(message);
	}

	public MySecurityException(Throwable cause) {
		super(msg, cause);
	}

}
