package com.sitech.basd.exception;

/**
 * 
 * <p>
 * Title:CloudChartsException
 * </p>
 * <p>
 * Description: 云平台报表异常定义
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
public class CloudChartsException extends RuntimeException {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -4152316589483411035L;

	public CloudChartsException() {

	}

	public CloudChartsException(String message) {
		super(message);
	}

	public CloudChartsException(String message, Throwable th) {
		super(message, th);
	}
}
