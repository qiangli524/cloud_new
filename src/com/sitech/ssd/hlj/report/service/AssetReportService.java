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

import com.sitech.ssd.hlj.report.domain.AssetReportObj;

/**
 *<P>
 * 资产报表
 *</p>
 * Jul 30, 2014,1:48:50 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public interface AssetReportService {
	//资源类型  1主机2虚拟机
	public final static String RESOURCE_TYPE_HOST = "host";
	public final static String RESOURCE_TYPE_VM = "vm";
	public final static String ALL = "-1";
	
	public List queryAssetData(AssetReportObj obj);

	/**
	 * <p>excel导出</p>
	 *
	 * @Createtime Sep 5, 2014,4:48:37 PM
	 * @author xugang
	 * @version 1.0
	 * @param excelJson
	 * @param list 
	 * @return
	 */
	public ByteArrayOutputStream getExcelOutPutStream(JSONObject excelJson, List list);

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Sep 9, 2014,3:56:53 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	public List getVlanList(AssetReportObj obj);

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Sep 9, 2014,3:58:16 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	public List getSubNetList(AssetReportObj obj);

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Sep 17, 2014,2:48:22 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	public List getRoomist(AssetReportObj obj);
}
