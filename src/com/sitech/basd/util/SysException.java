package com.sitech.basd.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

public class SysException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7295338779346910001L;

	/**
	 * SysException的跟踪调试信息，一般包括类名和方法名。
	 */
	private String trace = "";

	public SysException(String errId, String errOwnMsg, Exception oriEx) {
	
		super(oriEx);
		this.errId = errId;
		this.errMsg = errOwnMsg;
		this.errOri = oriEx;
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(os);
		this.errOri.printStackTrace(p);
		this.errMsgOri = os.toString().trim();
		this.writeSysException();
	}

	public SysException(String errId, Exception oriEx) {
		super(oriEx);
		
		this.errId = errId;
		this.errOri = oriEx;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(os);
		this.errOri.printStackTrace(p);
		this.errMsgOri = os.toString().trim();
		this.writeSysException();
	}

	/**
	 * 添加跟踪信息，只有捕捉到SysException的时候才调用该函数。
	 * 
	 * @param msg
	 *            String 跟踪信息
	 */
	public void appendMsg(String msg) {
		trace += msg;
	}

	/**
	 * 返回跟踪信息
	 * 
	 * @return String
	 */
	public String getTrace() {
		return trace;
	}

	public String getMessage() {
		String message = "";
		message = errMsg + "[" + errMsgOri + "]";
		return message;
	}

	/**
	 * 打印原发错误信息
	 */
	public void printDebug() {
		this.errOri.printStackTrace();
	}

	private void writeSysException() {
		Logger log = Logger.getLogger(SysException.class);
		
		log.error("id:" + this.errId);
		log.error("msg:" + this.errMsg);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(os);
		this.errOri.printStackTrace(p);
		log.error(os.toString());

	}
	
}
