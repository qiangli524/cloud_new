package com.sitech.basd.ibmmanager.util;

/**
 * 
 * <p>
 * Title: IBMConstant
 * </p>
 * <p>
 * Description: IBM中相应常量
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-14 下午3:12:40
 * 
 */
public class IBMConstant {

	public static final String IBM = "0";
	public static final String X86 = "1";
	// 节点类型
	public static final String ROOT = "0";
	public static final String IBMNODE = "10";
	public static final String X86NODE = "20";
	public static final String HMC = "1";
	public static final String POWER = "2";
	public static final String LPAR = "3";
	public static final String CLUSTER = "4";
	public static final String HOST = "5";
	public static final String VM = "6";

	// 节点类型字符串标示
	// IBM
	public static final String IBM_STRING = "IBM";
	// X86
	public static final String X86_STRING = "X86";
	// HMC
	public static final String HMC_STRING = "HMC";
	// 整机
	public static final String POWER_STRING = "Power";
	// 逻辑分区
	public static final String LPAR_STRING = "Lpar";
	// 集群
	public static final String CLUSTER_STRING = "Cluster";
	// 宿主机
	public static final String HOST_STRING = "Host";
	// 虚拟机
	public static final String VM_STRING = "VM";

	// 失败、成功标示
	public static final String FAIL = "操作失败";
	public static final String SUCCESS = "操作成功";

	// Power光纤卡
	public static final String POWER_FCSPEED = "PowerFCSpeed";
	// Power网络性能
	public static final String POWER_SEASPEED = "PowerSEASpeed";
	// Power网卡流量监控
	public static final String POWER_NETCARD_PERFORMANCE = "PowerNetCardPerformance";
	// Lpar光纤卡流量信息
	public static final String LPAR_FCSPEED = "LparFCSpeed";
	// Lpar磁盘的读写速度
	public static final String LPAR_DISKSPEED = "LparDiskSpeed";
	// Lpar网卡流量监控
	public static final String LPAR_NETCARD_PERFORMANCE = "LparNetCardPerformance";

	public static final String poweredOff = "poweredOff";// 关机
	public static final String poweredOn = "poweredOn";// 开机
	public static final String standBy = "standBy";// 挂起
	public static final String unknown = "unknown";// 未知

}
