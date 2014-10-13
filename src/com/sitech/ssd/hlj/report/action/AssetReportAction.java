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

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.hlj.report.domain.AssetReportObj;
import com.sitech.ssd.hlj.report.domain.CustomReportObj;
import com.sitech.ssd.hlj.report.service.AssetReportService;

/**
 *<P>
 * 资产报表
 *</p>
 * Jul 30, 2014,1:36:46 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Scope("prototype")
@Controller("assetReportAction")
public class AssetReportAction extends BaseAction {
	private static final long serialVersionUID = -8984290670753746736L;
	@Autowired
	private AssetReportService assetReportService;
	private AssetReportObj obj;
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
	 * <p>查询资产数据</p>
	 *
	 * @Createtime Sep 5, 2014,4:40:23 PM
	 * @author xugang
	 * @version 1.0
	 */
	public void queryAssetData(){
		if(obj==null){
			obj = new AssetReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = assetReportService.queryAssetData(obj);
		JSONArray json = JSONArray.fromObject(list);
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
		if(obj==null){
			obj = new AssetReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = assetReportService.queryAssetData(obj);
		ByteArrayOutputStream out = assetReportService.getExcelOutPutStream(JSONObject.fromObject(obj.getExportjson()),list);
		fileName = "资产报表.xls";
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/**
	 * 
	 * <p>vlan列表</p>
	 *
	 * @Createtime Sep 9, 2014,3:56:30 PM
	 * @author xugang
	 * @version 1.0
	 */
	public void getVlanList(){
		if(obj==null){
			obj = new AssetReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = assetReportService.getVlanList(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());	
	}
	
	/**
	 * 
	 * <p>机房列表</p>
	 *
	 * @Createtime Sep 17, 2014,2:47:54 PM
	 * @author xugang
	 * @version 1.0
	 */
	public void getRoomList(){		
		List list = assetReportService.getRoomist(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());	
	}
	
	/**
	 * 
	 * <p>子网络域列表</p>
	 *
	 * @Createtime Sep 9, 2014,3:56:30 PM
	 * @author xugang
	 * @version 1.0
	 */
	public void getSubNetList(){
		if(obj==null){
			obj = new AssetReportObj();
		}
		obj.setDomain((String) request.getSession().getAttribute("USER_DOMAIN"));
		List list = assetReportService.getSubNetList(obj);
		JSONArray json = JSONArray.fromObject(list);
		this.returnJson(json.toString());	
	}
	
	/**
	 *
	 * @return the obj
	 */
	public AssetReportObj getObj() {
		return obj;
	}
	/**
	 *
	 * @param obj the obj to set
	 */
	public void setObj(AssetReportObj obj) {
		this.obj = obj;
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
