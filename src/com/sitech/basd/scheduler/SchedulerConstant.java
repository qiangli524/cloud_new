package com.sitech.basd.scheduler;


/**
 * 
 * <p>
 * Title: SchedulerConstant
 * </p>
 * <p>
 * Description: 定时调度变量
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author limng_bj
 * @version 1.0
 * @createtime 2013-11-08 10:32:13
 * 
 */
public class SchedulerConstant {
	/**
	 * 资源调度group
	 */
	public static final String RESOURCE_GROUP = "resource_group";

	/**
	 * Job处理类中属性
	 */
	public static final String PARAMETER = "parameter";
	/**
	 * 资源创建
	 */
	public static final int SCHEDULER_TYPE_CREATE = 0;
	/**
	 * 资源回收
	 */
	public static final int SCHEDULER_TYPE_RECYCLE = 2;
	/**
	 * 开机
	 */
	public static final int SCHEDULER_TYPE_POWER_ON = 3;
	/**
	 * 关机
	 */
	public static final int SCHEDULER_TYPE_POWER_OFF = 4;

}
