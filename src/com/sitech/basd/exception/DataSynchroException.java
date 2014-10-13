package com.sitech.basd.exception;

/**
 * 
 * <p>
 * Title: DataSynchroException
 * </p>
 * <p>
 * Description: 云平台数据同步异常
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
public class DataSynchroException extends Exception {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -4152316589483411035L;

	public DataSynchroException() {

	}

	public DataSynchroException(String message) {
		super(message);
	}

	public DataSynchroException(String message, Throwable th) {
		super(message, th);
	}
}
