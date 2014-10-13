package com.sitech.ws;

public interface Type {

	/**
	 * 实体
	 */
	public static final String TTYPE_CI = "TTYPE_CI";

	/**
	 * 关系
	 */
	public static final String TTYPE_RE = "TTYPE_RE";

	/**
	 * 数据中心
	 */
	public static final String CTYPE_VDC = "VDC";
	/**
	 * 集群
	 */
	public static final String CTYPE_CLUSTER = "CLUSTER";
	/**
	 * 宿主机
	 */
	public static final String CTYPE_HOST = "HOST";
	/**
	 * 虚拟机
	 */
	public static final String CTYPE_VM = "VM";
	/**
	 * 存储
	 */
	public static final String CTYPE_STORAGE = "STORAGE";
	/**
	 * 虚拟交换机
	 */
	public static final String CTYPE_VSS = "VSS";
	/**
	 * 分布式虚拟交换机
	 */
	public static final String CTYPE_VDS = "VDS";
	/**
	 * 物理网卡
	 */
	public static final String CTYPE_NIC = "NIC";
	/**
	 * 虚拟网卡
	 */
	public static final String CTYPE_VNIC = "VNIC";
	/**
	 * 端口组
	 */
	public static final String CTYPE_PORTGROUP = "PORTGROUP";
	/**
	 * 模板
	 */
	public static final String CTYPE_TEMPLATE = "TEMPLATE";

	/**
	 * 属于关系
	 */
	public static final String RTYPE_0 = "0";
	/**
	 * 连接
	 */
	public static final String RTYPE_1 = "1";
	/**
	 * 安装于
	 */
	public static final String RTYPE_2 = "2";
	/**
	 * 运行在
	 */
	public static final String RTYPE_3 = "3";

}
