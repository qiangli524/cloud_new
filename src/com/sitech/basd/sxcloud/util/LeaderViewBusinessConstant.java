package com.sitech.basd.sxcloud.util;

public class LeaderViewBusinessConstant {
	
	/*
	 * 域运行状态
	 */
	public static int SYS_STATUS_NORMAL = 0;      //正常
	public static int SYS_STATUS_WARNING = 1;     //告警
	public static int SYS_STATUS_TERMINATION = 2; //停止
	
	
	/*
	 * 域类型
	 */
	public static int DOMAIN_TYPE_BOSS = 1;	//核心域BOSS
	public static int DOMAIN_TYPE_INNER = 2;//内部接入域
	public static int DOMAIN_TYPE_OUTER = 3;//互联网接入域
	
	public static String DOMAIN_REG_BOSS = "10.208.229"; //核心域BOSS 10.208.229.*
	public static String DOMAIN_REG_INNER = "10.208.230";//内部接入域   10.208.230.*
	public static String DOMAIN_REG_OUTER = "10.209.83";//互联网接入域  10.209.83.*
	
	
	
	/**
	 * 
	 * 监控
	 */
	/*
	 *  时间区间
	 */
	public static int MONITORING_GAP_CPU = 6;   //CPU 6个小时
	public static int MONITORING_GAP_MEM = 12;  //MEMORY 12小时
	public static int MONITORING_GAP_STOR = 24; //STORGY 24 小时
	public static int MONITORING_GAP_NET = 24;  //NET 24小时
	
	/*
	 * 监控指标
	 */
	public static String MONITORING_HM_KPI_CPU = "PM-H-01-010-11"; //主机CPU监控指标
	public static String MONITORING_HM_KPI_MEM = "PM-H-01-010-12"; //主机内存监控指标
	public static String MONITORING_HM_KPI_STOR = "PM-H-01-010-13";//主机存储监控指标
	public static String MONITORING_HM_KPI_NET = "PM-H-01-010-14"; //主机网络监控指标
	
	public static String MONITORING_VM_KPI_CPU = "PM-V-01-010-11"; //虚拟机CPU监控指标
	public static String MONITORING_VM_KPI_MEM = "PM-V-01-010-12"; //虚拟机内存监控指标
	public static String MONITORING_VM_KPI_STOR = "PM-V-01-010-13";//虚拟机存储监控指标
	public static String MONITORING_VM_KPI_NET = "PM-V-01-010-14"; //虚拟机网络监控指标
	
	public static String MONITORING_APP_KPI_STATUS = "PM-A-01-010-01"; //应用状态监控指标
	public static String MONITORING_APP_KPI_COUNT = "PM-A-01-010-02";  //应用数量监控指标
	public static String MONITORING_APP_KPI_CPU = "PM-A-01-010-03";    //应用所占CPU监控指标
	public static String MONITORING_APP_KPI_MEM = "PM-A-01-010-04";    //应用所占内存监控指标
	
	public static String MONITORING_ROOM_KPI_TEMP = "PM-R-01-010-01";  //机房温度监控指标
	public static String MONITORING_ROOM_KPI_HUMI = "PM-R-01-010-02";  //机房湿度监控指标
	
	//TODO 机柜监控数据指标
	
}
