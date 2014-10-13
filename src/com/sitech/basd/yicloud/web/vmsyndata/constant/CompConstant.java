package com.sitech.basd.yicloud.web.vmsyndata.constant;

/**
 * j
 * <p>
 * Title: Constant
 * </p>
 * <p>
 * Description: 静态参数
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Oct 27, 2012 10:56:38 AM
 * 
 */
public class CompConstant {
	public static final String VDC = "VDC";// 虚拟数据中心[VDC]
	public static final String VDC_CODE = "VDC_CODE";// 虚拟数据中心[VDC]-唯一标识
	public static final String CLUSTER = "CLUSTER";// 集群[CLUSTER]
	public static final String CLUSTER_HA = "CLUSTER_HA";// 集群[CLUSTER_HA]
	public static final String CLUSTER_DRS = "CLUSTER_DRS";// 集群[CLUSTER_DRS]
	public static final String CLUSTER_CODE = "CLUSTER_CODE";// 集群[CLUSTER]-唯一标识
	public static final String CL_CODE = "CL_CODE";// 集群[CLUSTER]-唯一标识
	public static final String HOST_CODE = "HOST_CODE";// 主机[HOST]-唯一标识
	public static final String HOST = "HOST";// 主机[HOST]
	public static final String VM = "VM";// 虚拟机[VM]
	public static final String VM_CODE = "VM_CODE";// 虚拟机[VM]-唯一标识
	/** vmware数据实例标识 */
	public static volatile boolean VMWARE_IS_INIT = false;
}
