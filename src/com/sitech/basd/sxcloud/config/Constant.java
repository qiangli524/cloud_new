/**
 * @Title:公用常量
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */
package com.sitech.basd.sxcloud.config;

public class Constant {
	public static final String USER_SESSION_KEY = "USER_SESSION_KEY";
	public static final String LOGID_SESSION_KEY = "LOGID_SESSION_KEY";

	/*
	 * 全局配置项
	 */
	public static final String GROUBLE_KEY_IP = "IpDetection"; // Ip安全检测
	public static final String GROUBLE_KEY_USR = "UserName"; // 用户名安全检测
	public static final String GROUBLE_KEY_NUM = "Numbers"; // 次数安全检测

	/*
	 * 安全鉴权类型
	 */
	public static final int SECURITY_TYPE_IP = 0; // IP安全检测类型
	public static final int SECURITY_TYPE_USR = 1; // 用户名安全检测类型
	public static final int SECURITY_TYPE_NUM = 2; // 次数安全检测类型

	/*
	 * 领导视图
	 */
	public static final String LEADER_SESSION_KEY = "LEADER_SEESION_KEY";
	/*
	 * 工作流
	 */
	public static final int FLOW_ONLORDER_PROCESS_ID = 1;// 资源申请编号常量
	public static final int FLOW_APPORDER_PROCESS_ID = 2;// 申请应用部署流程编号常量
	/*
	 * 
	 */
	public static final String FUNCID = "FUNCSID";// 功能编号

	public static final String EXCEPTION = "exception";
	/*
	 * 远程访问主机
	 */
	public static final String REQUEST_HOST = "REQUEST_HOST";
	/*
	 * 远程访问主机端口
	 */
	public static final String REQUEST_PORT = "REQUEST_PORT";
	/*
	 * 本地主机IP
	 */
	public static final String LOCAL_HOST = "LOCAL_HOST";
	/*
	 * 本地主机端口
	 */
	public static final String LOCAL_PORT = "LOCAL_PORT";
	/*
	 * 登录账号
	 */
	public static final String ACCOUNT = "account";
	/*
	 * 当前用户所在域
	 */
	public static final String USER_DOMAIN = "USER_DOMAIN";
	/*
	 * 用户组ID  key 用于session存取用户组ID
	 */
	public static final String GROUP_ID = "GROUP_ID";
	/**
	 * 全局配置表，配置CAS单点连接
	 */
	public static final String CAS_URL = "CAS_URL";
	
	/**
	 * 通过业支4A平台管理角色时使用的用户
	 */
	public static final String TEMP4AUSER = "admin";
}