package com.sitech.ws.web;


/**
 * 
 * <p>
 * Title: NoticeUtil
 * </p>
 * <p>
 * Description: 客户端通知工具类
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
 * @createtime Oct 10, 2012 11:19:51 AM
 * 
 */
public class NoticeUtil {
	private static NoticeUtil util = new NoticeUtil();

	private NoticeUtil() {

	}

	public static synchronized NoticeUtil getInstance() {
		if (util == null) {
			util = new NoticeUtil();
		}
		return util;
	}

	/**
	 * 
	 * @Title: addVM
	 * @Description: 添加虚拟机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addVM(String vmName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vmName, vmName, Type.CTYPE_VM, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delVM
	 * @Description: 删除虚拟机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delVM(String vmName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vmName, vmName, Type.CTYPE_VM, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delVM
	 * @Description: 删除模板
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delTem(String temName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// temName, temName, Type.CTYPE_TEMPLATE, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addTem
	 * @Description: 增加模板
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addTem(String temName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// temName, temName, Type.CTYPE_TEMPLATE, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateVM
	 * @Description: 更新虚拟机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateVM(String vmName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vmName, vmName, Type.CTYPE_VM, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addHost
	 * @Description: 添加宿主机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addHost(String hostName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// hostName, hostName, Type.CTYPE_HOST, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delHost
	 * @Description: 删除宿主机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delHost(String hostName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// hostName, hostName, Type.CTYPE_HOST, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateHost
	 * @Description: 更新宿主机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateHost(String hostName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// hostName, hostName, Type.CTYPE_HOST, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addDatacenter
	 * @Description: 添加数据中心通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addDatacenter(String dcName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// dcName, dcName, Type.CTYPE_VDC, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delDatacenter
	 * @Description: 删除数据中心通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delDatacenter(String dcName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// dcName, dcName, Type.CTYPE_VDC, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateDatacenter
	 * @Description: 更新数据中心通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateDatacenter(String dcName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// dcName, dcName, Type.CTYPE_VDC, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addCluster
	 * @Description: 添加集群通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addCluster(String clName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// clName, clName, Type.CTYPE_CLUSTER, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delCluster
	 * @Description: 删除集群通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delCluster(String clName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// clName, clName, Type.CTYPE_CLUSTER, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCluster
	 * @Description: 更新集群通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCluster(String clName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// clName, clName, Type.CTYPE_CLUSTER, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addStorage
	 * @Description: 添加存储通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addStorage(String strName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// strName, strName, Type.CTYPE_STORAGE, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delStorage
	 * @Description: 删除存储通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delStorage(String strName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// strName, strName, Type.CTYPE_STORAGE, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateStorage
	 * @Description: 更新存储通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateStorage(String strName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// strName, strName, Type.CTYPE_STORAGE, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addVss
	 * @Description: 添加虚拟交换机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addVss(String vssName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vssName, vssName, Type.CTYPE_VSS, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delVss
	 * @Description: 删除虚拟交换机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delVss(String vssName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vssName, vssName, Type.CTYPE_VSS, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateVss
	 * @Description: 更新虚拟交换机通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateVss(String vssName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vssName, vssName, Type.CTYPE_VSS, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addVnic
	 * @Description: 添加虚拟网卡通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addVnic(String vnicName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vnicName, vnicName, Type.CTYPE_VNIC, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delVnic
	 * @Description: 删除虚拟网卡通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delVnic(String vnicName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vnicName, vnicName, Type.CTYPE_VNIC, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateVnic
	 * @Description: 更新虚拟网卡通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateVnic(String vnicName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vnicName, vnicName, Type.CTYPE_VNIC, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addPortgroup
	 * @Description: 添加虚拟端口组通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addPortgroup(String groupName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// groupName, groupName, Type.CTYPE_PORTGROUP, Operation.OPER_ADD);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delPortgroup
	 * @Description: 删除虚拟端口组通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delPortgroup(String groupName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// groupName, groupName, Type.CTYPE_PORTGROUP, Operation.OPER_DEL);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updatePortgroup
	 * @Description: 更新虚拟端口组通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updatePortgroup(String groupName) {
		// String result = Client.getInstance()
		// .noticeOthers(Type.TTYPE_CI, groupName, groupName,
		// Type.CTYPE_PORTGROUP, Operation.OPER_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateHostRelation
	 * @Description: 主机关系变化
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateHostRelation(String hostName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// hostName, hostName, Type.CTYPE_HOST, Operation.OPER_REL_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateVMRelation
	 * @Description: 虚拟机迁移，关系变化
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateVMRelation(String vmName) {
		// String result = Client.getInstance().noticeOthers(Type.TTYPE_CI,
		// vmName, vmName, Type.CTYPE_VM, Operation.OPER_REL_MODI);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: addCMDBDC
	 * @Description: 通知cmdb添加数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String addCMDBDC(String dcName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// dcName,
		// Type.CTYPE_VDC, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delCMDBDC
	 * @Description: 通知cmdb删除数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delCMDBDC(String dcName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// dcName,
		// Type.CTYPE_VDC, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCMDBDC
	 * @Description: 通知cmdb数据中心修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCMDBDC(String dcName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// dcName,
		// Type.CTYPE_VDC, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCMDBCluster
	 * @Description: 通知cmdb集群修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCMDBCluster(String clName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// clName,
		// Type.CTYPE_CLUSTER, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delCMDBCluster
	 * @Description: 通知cmdb集群修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delCMDBCluster(String clName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// clName,
		// Type.CTYPE_CLUSTER, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCMDBHost
	 * @Description: 通知cmdb主机修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCMDBHost(String hostName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// hostName, Type.CTYPE_HOST, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCMDBDataStorage
	 * @Description: 通知cmdb修改存储
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCMDBDataStorage(String datastorage, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// datastorage, Type.CTYPE_STORAGE, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: updateCMDBVM
	 * @Description: 通知cmdb虚拟机修改
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String updateCMDBVM(String vmName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// vmName,
		// Type.CTYPE_VM, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delCMDBVM
	 * @Description: 通知cmdb虚拟机删除
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delCMDBVM(String vmName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// vmName,
		// Type.CTYPE_VM, op);
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: delCMDBTem
	 * @Description: 通知cmdb模板删除
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String delCMDBTem(String vmName, String op) {
		// String result = Client.getInstance().noticeCMDB(Type.TTYPE_CI,
		// vmName,
		// Type.CTYPE_TEMPLATE, op);]
		String result = "";
		return result;
	}

	/**
	 * 
	 * @Title: globalSynchNotice
	 * @Description: 全局接口通知
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 10, 2012 11:25:56 AM
	 */
	public String globalSynchNotice(String... param) {
		// String result = Client.getInstance().globalSynchNotice(param);
		String result = "";
		return result;
	}

	public static void main(String[] args) {
		String result = NoticeUtil.getInstance().addVM("12345");
	}
}
