package com.sitech.ssd.ah.paas.busiResource.util;
/**
 * <p>Title: paasBusiConstant</p>
 * <p>Description:paas业务资源树节点类型</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:26:40
 *
 */
public class paasBusiConstant {
	/** node_type类型 */
	public static final String ROOT = "0";//根节点
	public static final String CHILD_SYSTEM="1";//子系统
	public static final String BUSINESS = "2";//承载业务
	public static final String SERVICE = "3";//服务节点
	public static final String COST_HOST = "4";//主机节点
	public static final String VM_HOST = "5";//虚拟机节点
	public static final String DATABASE_INSTANCE = "6";//数据库实例
	/** 操作结果 */
	public static final String SUCCESS="操作成功！";
	public static final String FAIL="操作失败！";
}
