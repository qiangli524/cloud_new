/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.hlj.report.domain.AssetReportObj;

/**
 *<P>
 * 资产报表
 *</p>
 * Sep 5, 2014,3:39:15 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Service("assetReportService")
public class AssetReportServiceImpl extends BaseService implements AssetReportService {

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.AssetReportService#queryAssetHost()
	 */
	@Override
	public List queryAssetData(AssetReportObj obj) {
		List lst = new ArrayList();			
		//资产类型为主机
		if(CustomReportService.RESOURCE_TYPE_HOST.equals(obj.getResourceType())){
			lst = queryAssetHost(obj);
		}else if(CustomReportService.RESOURCE_TYPE_VM.equals(obj.getResourceType())){
			lst = queryAssetVm(obj);
		}else if(CustomReportService.RESOURCE_TYPE_STORE.equals(obj.getResourceType())){
			
		}else if(CustomReportService.RESOURCE_TYPE_IP.equals(obj.getResourceType())){
			lst = queryAssetIp(obj);
		}   
		return lst;
	}
	
	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Sep 9, 2014,3:16:31 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	private List queryAssetIp(Object obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.queryIpAssetReport", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 * <p>描述该方法的主要功能</p>
	 *
	 * @Createtime Sep 9, 2014,11:24:30 AM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	private List queryAssetVm(AssetReportObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.queryVmAssetReport", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public List queryAssetHost(AssetReportObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.queryHostAssetReport", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.AssetReportService#getExcelOutPutStream(net.sf.json.JSONArray, java.util.List)
	 */
	@Override
	public ByteArrayOutputStream getExcelOutPutStream(JSONObject excelJson, List list) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		/* Create workbook */
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			/* Create Title Font */
			Font fontTitle = workbook.createFont();
			fontTitle.setFontHeightInPoints((short) 10);
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);

			/* Create Data Font */
			Font fontData = workbook.createFont();
			fontData.setFontHeightInPoints((short) 9);
			fontData.setBoldweight(Font.BOLDWEIGHT_NORMAL);

			/* Create Title Style */
			CellStyle cellStyleTitle = workbook.createCellStyle();
			cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyleTitle.setWrapText(true);
			cellStyleTitle.setFont(fontTitle);

			/* Create Data Style */
			CellStyle cellStyleData = workbook.createCellStyle();
			cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
			cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyleData.setAlignment(CellStyle.ALIGN_RIGHT);
			cellStyleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyleData.setFont(fontData);

			Sheet st = workbook.createSheet("Sheet1");
			JSONArray heads = (JSONArray) excelJson.get("heads");
			JSONArray keys = (JSONArray) excelJson.get("keys");
			// data head
			if(true){				
				Row row = st.createRow(0);
				row.setHeightInPoints(30);
				for(int i=0;i<heads.size();i++){
					String val = (String) heads.get(i);
					Cell cell = row.createCell(i);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleTitle);
					cell.setCellValue(val);
				}
			}
			// data left & data
			for(int i=0;i<list.size();i++){
				Row row = st.createRow(i+1);
				row.setHeightInPoints(15);
				Map data = (Map) list.get(i);
				for(int j=0;j<keys.size();j++){
					String key = (String) keys.get(j);
					String val = "";
					if(data.get(key)!=null){
						val = String.valueOf(data.get(key));
					}
					Cell cell = row.createCell(j);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleData);
					cell.setCellValue(val);
				}
			}
			//调整列宽度
			for(int i=0;i<heads.size();i++){				
				st.autoSizeColumn((short)i); 
			}
			workbook.write(os);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}		
		return os;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.AssetReportService#getVlanList(com.sitech.ssd.hlj.report.domain.AssetReportObj)
	 */
	@Override
	public List getVlanList(AssetReportObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.queryVlan", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.AssetReportService#getSubNetList(com.sitech.ssd.hlj.report.domain.AssetReportObj)
	 */
	@Override
	public List getSubNetList(AssetReportObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.querySubNet", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.AssetReportService#getRoomist(com.sitech.ssd.hlj.report.domain.AssetReportObj)
	 */
	@Override
	public List getRoomist(AssetReportObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("AssetReport.queryRoom", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

}
