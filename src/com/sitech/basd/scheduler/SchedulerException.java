package com.sitech.basd.scheduler;

/**
 * 
 * <p>
 * Title: SchedulerException
 * </p>
 * <p>
 * Description: Job调度异常类
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
public class SchedulerException extends Exception {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7472985327997264035L;

	public SchedulerException() {

	}

	public SchedulerException(String message) {
		super(message);
	}

	public SchedulerException(String message, Throwable th) {
		super(message, th);
	}
}
