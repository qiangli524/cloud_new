package com.sitech.basd.resource.util;

/**
 * 
 * <p>
 * Title: VirtualMachineType
 * </p>
 * <p>
 * Description: TB_CLOUD2_VMHOST_info表虚拟化类型
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
 * @createtime 2013-7-31 下午5:32:53
 * 
 */
public class VirtualMachineType {
	// VMware虚拟化虚拟机
	public static final String VMWARE = "1";
	// Xen虚拟化虚拟机
	public static final String XEN = "3";
	// KVM虚拟化虚拟机
	public static final String KVM = "0";
	// IBM虚拟化虚拟机
	public static final String IBM = "2";
	// IBM虚拟化逻辑分区
	public static final String IBM_LOGICAL_PARTITION = "4";
}
