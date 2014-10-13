package com.sitech.basd.syslog;

import java.util.HashMap;

/**
 * <p>
 * Title: SysLogConstant
 * </p>
 * <p>
 * Description: 用户日志常量类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author liming_bj
 * @version 1.0
 * @createtime 2014-05-14 19:49:15
 * 
 */
public class SysLogConstant {

	// 操作类型**************************************************************************************
	/**
	 * 开机
	 */
	public static final String POWER_ON = "开机";
	
	/**
	 * 重命名
	 */
	public static final String RENAME = "重命名";

	/**
	 * 关机
	 */
	public static final String POWER_OFF = "关机";

	/**
	 * 重启
	 */
	public static final String RESTART = "重启";

	/**
	 * 重置
	 */
	public static final String RESET = "重置";

	/**
	 * 配置变更
	 */
	public static final String CONFIGURATION_CHANGES = "配置变更";

	/**
	 * 备份
	 */
	public static final String THE_BACKUP = "备份";

	/**
	 * 新建磁盘
	 */
	public static final String NEW_DISK = "新建磁盘";

	/**
	 * 磁盘挂载
	 */
	public static final String DISK_MOUNT = "磁盘挂载";

	/**
	 * 磁盘卸载
	 */
	public static final String DISK_OFFLOAD = "磁盘卸载";

	/**
	 * 加入网络
	 */
	public static final String JOIN_NETWORK = "加入网络";
	/**
	 * 创建快照
	 */
	public static final String CREATE_SNAPSHOT = "创建快照";

	/**
	 * 恢复快照
	 */
	public static final String RECOVERY_SNAPSHOT = "恢复快照";
	/**
	 * 删除快照
	 */
	public static final String DELETE_SNAPSHOT = "删除快照";

	/**
	 * 创建VLAN
	 */
	public static final String CREATE_VLAN = "创建VLAN";

	/**
	 * 修改VLAN
	 */
	public static final String MODIFY_VLAN = "修改VLAN";
	/**
	 * 删除VLAN
	 */
	public static final String DELETE_VLAN = "删除VLAN";
	/**
	 * 申请VLAN
	 */
	public static final String APPLY_VLAN = "申请VLAN";
	/**
	 * 申请公网IP
	 */
	public static final String APPLY_FOR_PUBLIC_IP = "申请公网IP";

	/**
	 * 公网IP绑定
	 */
	public static final String PUBLIC_IP_BINDING = "绑定公网IP";

	/**
	 * 公网IP解绑
	 */
	public static final String PUBLIC_IP_UNBUNDLING = "公网IP解绑";

	/**
	 * 创建负载均衡
	 */
	public static final String CREATE_BALANCING = "新建负载均衡";

	/**
	 * 创建负载均衡
	 */
	public static final String RENAME_BALANCE = "重命名负载均衡";
	
	/**
	 * 负载均衡配置
	 */
	public static final String BALANCING_CONFIGURATION = "负载均衡配置";
	/**
	 * 负载均衡刪除
	 */
	public static final String DELETE_BALANCING = "刪除负载均衡";
	/**
	 * 新建负载均衡监听器
	 */
	public static final String CREATE_LBLISTENER = "新建负载均衡监听器";
	/**
	 * 修改负载均衡监听器
	 */
	public static final String MODIFY_LBLISTENER = "修改负载均衡监听器";
	/**
	 * 删除负载均衡监听器
	 */
	public static final String DELETE_LBLISTENER = "删除负载均衡监听器";
	/**
	 * 创建负载实例
	 */
	public static final String CREATE_SERVER = "新建负载实例";
	/**
	 * 删除负载实例
	 */
	public static final String DELETE_SERVER = "删除负载实例";

	/**
	 * 新建防火墙
	 */
	public static final String CREATE_FIREWALL = "新建防火墙";

	/**
	 * 修改防火墙
	 */
	public static final String MODIFY_FIREWALL = "修改防火墙";
	/**
	 * 删除防火墙
	 */
	public static final String DELETE_FIREWALL = "删除防火墙";
	/**
	 * 创建防火墙规则
	 */
	public static final String CREATE_RULES = "创建防火墙规则";
	/**
	 * 修改防火墙规则
	 */
	public static final String MODIFY_RULES = "修改防火墙规则";
	/**
	 * 删除防火墙规则
	 */
	public static final String DELETE_RULES = "删除防火墙规则";

	/**
	 * 配置防火墙
	 */
	public static final String CONFIGURE_FIREWALL = "配置防火墙";

	/**
	 * 创建镜像
	 */
	public static final String CREATE_IMAGE = "创建镜像";

	/**
	 * 修改镜像
	 */
	public static final String MODIFY_IMAGE = "修改镜像";
	/**
	 * 删除镜像
	 */
	public static final String DELETE_IMAGE = "删除镜像";
	/**
	 * 上传镜像
	 */
	public static final String UPLOAD_IMAGE = "上传镜像";
	/*
	 * 告警停止
	 */
	public static final String SHUTDOWN_ALARM = "停止告警";
	/*
	 * 告警启动
	 */
	public static final String STARTUP_ALARM = "启动告警";
	/*
	 * 告警添加
	 */
	public static final String CREATE_ALARM = "新建告警";
	/*
	 * 修改添加
	 */
	public static final String MODIFY_ALARM = "修改告警";
	/*
	 * 告警删除
	 */
	public static final String DELETE_ALARM = "删除告警";
	/*
	 * 告警添加组
	 */
	public static final String CREATE_SUBSCRIBERGRP = "新建告警组";
	/*
	 * 修改添加组
	 */
	public static final String MODIFY_SUBSCRIBERGRP = "修改告警组";
	/*
	 * 告警删除组
	 */
	public static final String DELETE_SUBSCRIBERGRP = "删除告警组";
	/*
	 * 告警通知人添加
	 */
	public static final String CREATE_SUBSCRIBER = "新建告警通知人";
	/*
	 * 修改通知人添加
	 */
	public static final String MODIFY_SUBSCRIBER = "修改告警通知人";
	/*
	 * 告警通知人删除
	 */
	public static final String DELETE_SUBSCRIBER = "删除告警通知人";
	
	
	
	
	
	
	
	
	// 操作结果**************************************************************************************

	/**
	 * 操作成功
	 */
	public static final Integer OPERATION_SUCCEED = 1;

	/**
	 * 操作失败
	 */
	public static final Integer OPERATION_ERROR = 0;

	public static HashMap<String, String> getOperationTypeMap() {
		java.util.HashMap<String, String> map = new java.util.LinkedHashMap<String, String>();

		map.put("", "所有");
		map.put("开机", "开机");
		map.put("关机", "关机");
		map.put("重启", "重启");
		map.put("重置", "重置");
		map.put("备份", "备份");
		map.put("配置变更", "配置变更");
		map.put("新建磁盘", "新建磁盘");
		map.put("磁盘挂载", "磁盘挂载");
		map.put("磁盘卸载", "磁盘卸载");
		map.put("创建快照", "创建快照");
		map.put("恢复快照", "恢复快照");
		map.put("创建VLAN", "创建VLAN");
		map.put("修改VLAN", "修改VLAN");
		map.put("申请公网IP", "申请公网IP");
		map.put("公网IP绑定", "公网IP绑定");
		map.put("公网IP解绑", "公网IP解绑");
		map.put("新建负载均衡", "新建负载均衡");
		map.put("负载均衡配置", "负载均衡配置");
		map.put("配置防火墙", "配置防火墙");
		map.put("创建镜像", "创建镜像");
		map.put("修改镜像", "修改镜像");
		map.put("删除镜像", "删除镜像");
		map.put("新建防火墙", "新建防火墙");
		map.put("修改防火墙", "修改防火墙");
		map.put("删除防火墙", "删除防火墙");
		map.put("创建防火墙规则", "创建防火墙规则");
		map.put("修改防火墙规则", "修改防火墙规则");
		map.put("删除防火墙规则", "删除防火墙规则");
		map.put("加入网络", "加入网络");
		map.put("申请VLAN", "申请VLAN");
		map.put("上传镜像", "上传镜像");
		map.put("刪除负载均衡", "刪除负载均衡");
		map.put("新建负载实例", "新建负载实例");
		map.put("删除负载实例", "删除负载实例");

		return map;
	}

	public static HashMap<String, String> getOperationResultMap() {
		java.util.HashMap<String, String> map = new java.util.LinkedHashMap<String, String>();
		map.put("", "");
		map.put("1", "成功");
		map.put("0", "失败");
		return map;
	}

	// 操作内容**************************************************************************************

}
