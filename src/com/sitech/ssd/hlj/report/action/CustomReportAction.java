/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.hlj.report.domain.CustomReportObj;
import com.sitech.ssd.hlj.report.service.CustomReportService;

/**
 *<P>
 * 黑龙江自定义报表
 *</p>
 * Jul 30, 2014,1:36:46 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Scope("prototype")
@Controller("customReportAction")
public class CustomReportAction extends BaseAction {
	private static final long serialVersionUID = -8984290670753746736L;
	@Autowired
	private CustomReportService customReportService;
	private List<UnitedTreeObj> treeList;
	private List<CustomReportObj> objList;
	private CustomReportObj obj;
	 // 下载文件名
    private String fileName;
    private String excelJson;
	/**
	 * 
	 * <p>进入自定义报表页面</p>
	 *
	 * @Createtime Jul 30, 2014,2:06:57 PM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	public String view(){		
		return "view";
	}

	/**
	 * 
	 * <p>资源添加页面</p>
	 *
	 * @Createtime Jul 30, 2014,5:07:08 PM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	public String showDetailAdd() {
		if(CustomReportService.RESOURCE_TYPE_HOST.equals(obj.getResourceType())){		
			return "showHostDetailAdd";
		}else{			
			return "showVmDetailAdd";
		}
	}

	public String vmlist(){
		if(obj==null){
			obj = new CustomReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		objList = customReportService.queryVmList(obj);
		return "vmlist";
	}
	
	public String hostlist(){
		if(obj==null){
			obj = new CustomReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		objList = customReportService.queryHostList(obj);
		return "hostlist";
	}
	/**
	 * 
	 * <p>获取集群</p>
	 *
	 * @Createtime Aug 4, 2014,9:30:43 AM
	 * @author xugang
	 * @version 1.0
	 */
	public void getClusterByDataCenter(){
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = customReportService.getCluster(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());
	}
	
	/**
	 * 
	 * <p>获取业务系统列表</p>
	 *
	 * @Createtime Aug 4, 2014,10:58:09 AM
	 * @author xugang
	 * @version 1.0
	 */
	public void getBusinessList(){
		if(obj==null){
			obj = new CustomReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List<BusiManagerTree> list = customReportService.queryBusinessList(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());
	}
	
	/**
	 * 
	 * <p>获取数据中心列表</p>
	 *
	 * @Createtime Aug 4, 2014,2:38:34 PM
	 * @author xugang
	 * @version 1.0
	 */
	public void getDataCenterList(){
		if(obj==null){
			obj = new CustomReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = customReportService.getDataCenterList(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());	
	}
	
	/**
	 * 
	 * <p>获取业子务系统列表</p>
	 *
	 * @Createtime Aug 4, 2014,10:58:09 AM
	 * @author xugang
	 * @version 1.0
	 */
	public void getSubBusinessList(){
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List<BusiManagerTree> list = customReportService.getSubBusinessList(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());
	}
	/**
	 * 
	 * <p>查询指标页面</p>
	 *
	 * @Createtime Aug 5, 2014,2:07:12 PM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	public String showKpis(){
		return "showKpis";
	}
	/**
	 * 
	 * <p>查询</p>
	 *
	 * @Createtime Aug 6, 2014,10:21:52 AM
	 * @author xugang
	 * @version 1.0
	 */
	public void query(){
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		JSONObject json = customReportService.queryReportData(obj);
		this.returnJson(json.toString());
	}

	
	/**
	 * 
	 * <p>附件下载</p>
	 * @throws UnsupportedEncodingException 
	 *
	 */
	public String exportExcel() throws UnsupportedEncodingException{	
		return "success";
	}	
	
	public InputStream getDownloadFile() throws FileNotFoundException{
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		ByteArrayOutputStream out = customReportService.getExcelOutPutStream(obj);
		fileName = "自定义报表.xls";
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	/**
	 *
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 *
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 *
	 * @return the treeList
	 */
	public List<UnitedTreeObj> getTreeList() {
		return treeList;
	}

	/**
	 *
	 * @param treeList the treeList to set
	 */
	public void setTreeList(List<UnitedTreeObj> treeList) {
		this.treeList = treeList;
	}


	/**
	 *
	 * @return the obj
	 */
	public CustomReportObj getObj() {
		return obj;
	}

	/**
	 *
	 * @param obj the obj to set
	 */
	public void setObj(CustomReportObj obj) {
		this.obj = obj;
	}

	/**
	 *
	 * @return the objList
	 */
	public List<CustomReportObj> getObjList() {
		return objList;
	}

	/**
	 *
	 * @param objList the objList to set
	 */
	public void setObjList(List<CustomReportObj> objList) {
		this.objList = objList;
	}

	/**
	 *
	 * @return the excelJson
	 */
	public String getExcelJson() {
		return excelJson;
	}

	/**
	 *
	 * @param excelJson the excelJson to set
	 */
	public void setExcelJson(String excelJson) {
		this.excelJson = excelJson;
	}	
	
	
	
}
