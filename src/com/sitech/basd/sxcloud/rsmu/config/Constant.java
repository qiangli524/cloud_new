/**
 * @Title:公用常量
 * @Copyright: Copyright (c) 201006
 * @Company: si-tech
 * @author zengls
 * @version 1.0
 */
package com.sitech.basd.sxcloud.rsmu.config;

public class Constant {
	public static final String USER_SESSION_KEY = "USER_SESSION_KEY";
	public static final String LOGID_SESSION_KEY = "LOGID_SESSION_KEY";

	public static final String INTERFACE_USER_NAME = "WEB_INTERFACE_USER_1234";
	/*
	 * 电信入围测试与监控管理接口暂用
	 */

	public static final String USER_SESSION_ID = "USER_SESSION_ID";
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

	/**
	 * 
	 * radware工作负载
	 * 
	 */
	public static final String Radware_Address = "";// 服务地址
	public static final String Radware_UserName = ""; // 负载服务用户名
	public static final String Radware_Password = "";// 负载服务用户密码

	public static int PROCESS_LEVEL = 0;// 虚拟机创建进度

	/*
	 * 
	 */
	public static final String FUNCID = "FUNCSID";// 功能编号
}