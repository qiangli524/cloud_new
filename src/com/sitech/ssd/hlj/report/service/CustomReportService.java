/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import net.sf.json.JSONObject;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.ssd.hlj.report.domain.CustomReportObj;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Jul 30, 2014,1:48:50 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public interface CustomReportService {
	//资源类型  1主机2虚拟机
	public final static String RESOURCE_TYPE_HOST = "host";
	public final static String RESOURCE_TYPE_VM = "vm";
	public final static String RESOURCE_TYPE_STORE = "store";
	public final static String RESOURCE_TYPE_IP = "ip";
	public final static String ALL = "-1";
	//时间维度 1实时 2小时 3天
	public final static String DATETYPE_NOW = "1";
	public final static String DATETYPE_HOUR = "2";
	public final static String DATETYPE_DAY = "3";
	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Jul 30, 2014,5:18:49 PM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	List<CustomReportObj> queryVmList(CustomReportObj  obj);

	/**
	 * <p>获取集群</p>
	 *
	 * @Createtime Aug 4, 2014,9:34:52 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	List getCluster(CustomReportObj obj);

	/**
	 * <p>获取业务系统列表</p>
	 *
	 * @Createtime Aug 4, 2014,10:58:20 AM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	List<BusiManagerTree> queryBusinessList(CustomReportObj obj);

	/**
	 * <p>获取子业务系统列表</p>
	 *
	 * @Createtime Aug 4, 2014,11:17:50 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	List<BusiManagerTree> getSubBusinessList(CustomReportObj obj);

	/**
	 * <p>主机列表查询</p>
	 *
	 * @Createtime Aug 5, 2014,10:14:44 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	List<CustomReportObj> queryHostList(CustomReportObj obj);

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Aug 6, 2014,10:36:20 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	JSONObject queryReportData(CustomReportObj obj);

	/**
	 * <p>excel导出</p>
	 *
	 * @Createtime Aug 7, 2014,5:44:43 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	ByteArrayOutputStream getExcelOutPutStream(CustomReportObj obj);

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Aug 15, 2014,10:04:22 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	List getDataCenterList(CustomReportObj obj);

}
