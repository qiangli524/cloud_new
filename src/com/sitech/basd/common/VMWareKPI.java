package com.sitech.basd.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class VMWareKPI implements Serializable {
	// 主机名称 CM-00-01-001-01
	// 主机类型 CM-00-01-001-03
	// 主机操作系统版本 CM-00-01-001-08
	// 主机启动时间 CM-00-01-001-22
	// 主机连接状态 FM-00-01-001-06
	// VirtualCenter地址 CM-00-01-001-23
	// CPU使用情况 PM-00-01-001-05
	// 内存使用情况 PM-00-01-002-01
	/**
	 * 主机名称 CM-00-01-001-01
	 */
	public static final String Host_name = "CM-00-01-001-01";
	/**
	 * 主机类型 CM-00-01-001-03
	 */
	public static final String Host_type = "CM-00-01-001-03";
	/**
	 * 主机操作系统版本 CM-00-01-001-08
	 */
	public static final String Host_OS_version = "CM-00-01-001-08";
	/**
	 * 主机启动时间 CM-00-01-001-22
	 */
	public static final String Host_startup_time = "CM-00-01-001-22";
	/**
	 * 主机连接状态 FM-00-01-001-06
	 */
	public static final String Host_connection_status = "FM-00-01-001-06";
	/**
	 * VirtualCenter地址 CM-00-01-001-23
	 */
	public static final String VirtualCenter_address = "CM-00-01-001-23";
	/**
	 * CPU使用情况 PM-00-01-001-05
	 */
	public static final String CPU_usage_situation = "PM-00-01-001-05";
	/**
	 * 内存使用情况 PM-00-01-002-01
	 */
	public static final String Memory_usage_situation1 = "PM-00-01-002-01";

	// 虚拟机版本 CM-00-02-001-03
	// 启动模式 CM-00-01-001-24
	// 客户机操作系统 CM-00-01-001-25
	// 客户机内存 CM-00-01-001-26
	// 虚拟CPU个数 CM-00-01-001-27
	// 虚拟网卡个数 CM-00-01-001-28
	// 虚拟磁盘个数 CM-00-01-001-29
	// 电源状态 FM-00-05-002-12

	/**
	 * 虚拟机版本 CM-00-02-001-03
	 */
	public static final String Virtual_Machine_version = "CM-00-02-001-03";
	/**
	 * 启动模式 CM-00-01-001-24
	 */
	public static final String Startup_mode = "CM-00-01-001-24";
	/**
	 * 客户机操作系统 CM-00-01-001-25
	 */
	public static final String Client_OS = "CM-00-01-001-25";
	/**
	 * 客户机内存 CM-00-01-001-26
	 */
	public static final String Client_memory = "CM-00-01-001-26";
	/**
	 * 虚拟CPU个数 CM-00-01-001-27
	 */
	public static final String Virtual_CPU_number = "CM-00-01-001-27";
	/**
	 * 虚拟网卡个数 CM-00-01-001-28
	 */
	public static final String Virtual_network_adapter_number = "CM-00-01-001-28";
	/**
	 * 虚拟磁盘个数 CM-00-01-001-29
	 */
	public static final String Virtual_Disk_number = "CM-00-01-001-29";
	/**
	 * 电源状态 FM-00-05-002-12
	 */
	public static final String Power_state = "FM-00-05-002-12";

	// 数据存储名称 CM-00-02-999-01
	// 数据存储路径 CM-00-02-999-02
	// 数据存储容量 CM-00-02-999-03
	// 可用空间 CM-00-02-999-04
	// 系统卷类型 CM-00-02-999-05
	// 额外的存储空间 CM-00-02-999-06
	// VMware Tools状态 CM-00-01-001-30
	// 虚拟机健康状态 CM-00-01-001-31

	/**
	 * 数据存储名称 CM-00-02-999-01
	 */
	public static final String Data_store_name = "CM-00-02-999-01";
	/**
	 * 数据存储路径 CM-00-02-999-02
	 */
	public static final String Data_store_path = "CM-00-02-999-02";
	/**
	 * 数据存储容量 CM-00-02-999-03
	 */
	public static final String Data_store_capacity = "CM-00-02-999-03";
	/**
	 * 可用空间 CM-00-02-999-04
	 */
	public static final String Free_space = "CM-00-02-999-04";
	/**
	 * 系统卷类型 CM-00-02-999-05
	 */
	public static final String System_volume_type = "CM-00-02-999-05";
	/**
	 * 额外的存储空间 CM-00-02-999-06
	 */
	public static final String Additional_storage_space = "CM-00-02-999-06";
	/**
	 * VMware Tools状态 CM-00-01-001-30
	 */
	public static final String VMware_tools_Status = "CM-00-01-001-30";
	/**
	 * 虚拟机健康状态 CM-00-01-001-31
	 */
	public static final String Virtual_machine_health_status = "CM-00-01-001-31";

	// 已使用的存储 CM-00-01-001-32
	// 未共享的存储 CM-00-01-001-33
	// 额外的存储 CM-00-01-001-34
	// 置备的存储（未共享的存储 + 额外的存储） CM-00-01-001-35
	// 活动客户机内存 CM-00-01-001-36
	// 已消耗的主机内存 CM-00-01-001-37
	// 已消耗的主机CPU CM-00-01-001-38
	// 客户机专用内存 CM-00-01-001-39

	/**
	 * 已使用的存储 CM-00-01-001-32
	 */
	public static final String Used_storage = "CM-00-01-001-32";
	/**
	 * 未共享的存储 CM-00-01-001-33
	 */
	public static final String Non_shared_storage = "CM-00-01-001-33";
	/**
	 * 额外的存储 CM-00-01-001-34
	 */
	public static final String Additional_storage = "CM-00-01-001-34";
	/**
	 * 置备的存储（未共享的存储 + 额外的存储） CM-00-01-001-35
	 */
	public static final String Provisioned_storage = "CM-00-01-001-35";
	/**
	 * 活动客户机内存 CM-00-01-001-36
	 */
	public static final String Active_client_memory = "CM-00-01-001-36";
	/**
	 * 已消耗的主机内存 CM-00-01-001-37
	 */
	public static final String Consumed_host_memory = "CM-00-01-001-37";
	/**
	 * 已消耗的主机CPU CM-00-01-001-38
	 */
	public static final String Consumed_host_CPU = "CM-00-01-001-38";
	/**
	 * 客户机专用内存 CM-00-01-001-39
	 */
	public static final String Client_specific_memory = "CM-00-01-001-39";

	// 客户机已共享内存 CM-00-01-001-40
	// 内存开销 CM-00-01-001-41
	// CPU使用率 PM-00-01-001-07
	// CPU等待 PM-00-01-001-08
	// 已用的CPU PM-00-01-001-09
	// 已消耗的内存 PM-00-01-001-10
	// 已分配的内存 PM-00-01-001-11
	// 内存使用情况 PM-00-01-001-12

	/**
	 * 客户机已共享内存 CM-00-01-001-40
	 */
	public static final String Client_shared_memory = "CM-00-01-001-40";
	/**
	 * 内存开销 CM-00-01-001-41
	 */
	public static final String Memory_overhead = "CM-00-01-001-41";
	/**
	 * CPU使用率 PM-00-01-001-07
	 */
	public static final String CPU_usage = "PM-00-01-001-07";
	/**
	 * CPU等待 PM-00-01-001-08
	 */
	public static final String CPU_wait = "PM-00-01-001-08";
	/**
	 * 已用的CPU PM-00-01-001-09
	 */
	public static final String Used_CPU = "PM-00-01-001-09";
	/**
	 * 已消耗的内存 PM-00-01-001-10
	 */
	public static final String Used_Memory = "PM-00-01-001-10";
	/**
	 * 已分配的内存 PM-00-01-001-11
	 */
	public static final String Allocated_memory = "PM-00-01-001-11";
	/**
	 * 内存使用情况 PM-00-01-001-12
	 */
	public static final String Memory_usage_situation2 = "PM-00-01-001-12";

	// 网络使用情况 PM-00-01-001-13
	// 网络数据传输速度 PM-00-01-001-14
	// 磁盘使用情况 PM-00-01-001-15
	// 已发出的磁盘命令 PM-00-01-001-16

	/**
	 * 网络使用情况 PM-00-01-001-13
	 */
	public static final String Network_usage_situation = "PM-00-01-001-13";
	/**
	 * 网络数据传输速度 PM-00-01-001-14
	 */
	public static final String Network_data_transfer_speed = "PM-00-01-001-14";
	/**
	 * 磁盘使用情况 PM-00-01-001-15
	 */
	public static final String Disk_usage_situation = "PM-00-01-001-15";
	/**
	 * 已发出的磁盘命令 PM-00-01-001-16
	 */
	public static final String Sented_disk_command = "PM-00-01-001-16";

}
