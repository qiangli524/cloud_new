/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.hlj.report.service;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.util.DateUtil;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.ssd.hlj.report.domain.CustomReportObj;
import com.sitech.utils.date.TimeformatCommon;
/**
 *<P>
 * 自定义报表实现类
 *</p>
 * Jul 30, 2014,1:49:44 PM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Service("customReportService")
public class CustomReportServiceImpl extends BaseService implements CustomReportService {

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#queryVmList()
	 */
	@Override
	public List<CustomReportObj> queryVmList(CustomReportObj  obj) {
		List<CustomReportObj> lst = new ArrayList<CustomReportObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("CustomReport.queryVhCount", obj))
								.intValue());
			}
				lst = getSqlMap().queryForList(
						"CustomReport.queryVhList", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return lst;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#getCluster(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public List getCluster(CustomReportObj obj) {
		List list  = new ArrayList();
		try {
			list = this.getSqlMap().queryForList("CustomReport.queryCluster",obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#queryBusinessList()
	 */
	@Override
	public List<BusiManagerTree> queryBusinessList(CustomReportObj obj) {
		List list  = new ArrayList();
		try {
			list = this.getSqlMap().queryForList("CustomReport.queryBusinessList",obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#getSubBusinessList(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public List<BusiManagerTree> getSubBusinessList(CustomReportObj obj) {
		List list  = new ArrayList();
		try {
			list = this.getSqlMap().queryForList("CustomReport.querySubBusinessList",obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#queryHostList(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public List<CustomReportObj> queryHostList(CustomReportObj obj) {
		List<CustomReportObj> lst = new ArrayList<CustomReportObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("CustomReport.queryHostCount", obj))
								.intValue());
			}
				lst = getSqlMap().queryForList(
						"CustomReport.queryHostList", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return lst;
	}

	private FusionCharts getFusionChartsLine(String caption,String unit){
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setYaxisname("单位："+unit);
		chart.setXaxisname("时间");
		chart.setCaption(caption);
		chart.setShowvalues("0");
		chart.setDecimals("2");
		chart.setPlotGradientColor(""); // 渐变色
		chart.setThousandseparator(",");// 千分位的分隔符
		chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
		chart.setShowborder("0");
		chart.setShowNames("0");//是否显示横坐标值
		chart.setAnchorRadius("1");//折线节点的半径大小
		chart.setLinethickness("1.5");//折线的厚度
		// chart.setShowvalues("0");//是否显示值，1显示，0不显示，默认显示
		chart.setCanvasbordercolor("E6E6E6");
		
		/////////////////
		//chart.setDivintervalhints("0, 10, 20, 30,40,50,60,80,100");
		chart.setPalette("3");
		chart.setBtnResetChartTitle("恢复");
		chart.setBtnResetChartTooltext("恢复");
		chart.setBtnSwitchToPinModeTitle("Pin模式");
		chart.setBtnSwitchToPinModeTooltext("Pin模式");
		chart.setBtnSwitchtoZoomModeTitle("缩放模式");
		chart.setBtnSwitchToZoomModeTooltext("缩放模式");
		chart.setBtnZoomOutTitle("上一步");
		chart.setBtnZoomOutTooltext("上一步");
		//导出设置
		chart.setExportenabled("1");
		chart.setExportAtClient("1");
		chart.setExportDialogMessage("正在生成,请稍候...");
		chart.setExportFormats("JPG=生成JPG图片|PDF=生成PDF文件");
		chart.setExportHandler("fcExporter1");
		//chart.setShowAboutMenuItem("0");
		fusionCharts.setChart(chart);
		return fusionCharts;
	}
	
	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#queryReportData(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public JSONObject queryReportData(CustomReportObj obj) {
		JSONObject json = new JSONObject();
		//资源类型为主机
		if(CustomReportService.RESOURCE_TYPE_HOST.equals(obj.getResourceType())){
			json = queryReportDataHost(obj);
		}else if(CustomReportService.RESOURCE_TYPE_VM.equals(obj.getResourceType())){
			json = queryReportDataHy(obj);			
		} 
		
		return json;
	}

	/**
	 * <p>虚拟机性能报表查询</p>
	 *
	 * @Createtime Aug 6, 2014,1:24:27 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	private JSONObject queryReportDataHy(CustomReportObj obj) {
		JSONObject json = new JSONObject();
		try {
			List<Data>  allDataList = new ArrayList<Data>();
			// 折线图
			FusionCharts fusionCharts = this.getFusionChartsLine(obj.getKpiName()+"走势图",obj.getKpiunit());
			// 横坐标，即时间按点数取limit范围
			List<Categories> categoriesList = new ArrayList<Categories>();
			List<Dataset> dataset = new ArrayList<Dataset>();
			List<String> resourceIds = new ArrayList<String>();
			//根据开始时间和结束时间判断需要查询几张表
			List<String> tableList = this.getQueryTables("tb_cloud2_hy_coll",obj.getStartDate(),obj.getEndDate(),obj.getDateType());	
			if(tableList.size()!=0){
				//如果等于-1,表示查询所有,则根据数据中心,集群过滤出top5
				if(ALL.equals(obj.getResourceIds())){
					obj.setTableName(tableList.get(tableList.size()-1));
					resourceIds = getSqlMap().queryForList("CustomReport.queryHyTop5", obj);				
				}else{
					for(String resourceId:obj.getResourceIds().split(",")){
						resourceIds.add(resourceId);
					}
				}
				obj.setResIdsList(resourceIds);
				// 查询所有的时间点
				List<String> timeList = new ArrayList<String>();
				for(String table : tableList){
					obj.setTableName(table);
					List<String>  clist = getSqlMap().queryForList("CustomReport.queryHyCategorys", obj);
					timeList.addAll(clist);
				}
				Categories categories = new Categories();
				List<Category> cList = new ArrayList<Category>();
				for (String time : timeList) {
					Category category = new Category();
					category.setLabel(time);
					cList.add(category);
				}
				categories.setCategory(cList);
				categoriesList.add(categories);
				
				//查出时间和名字对应的值
				for(String resid :resourceIds){
					String resourceName = this.getResourceName(resid,CustomReportService.RESOURCE_TYPE_VM);
					Dataset dataset1 = new Dataset();
					dataset1.setSeriesname(resourceName);
					List<Data> date1 = new ArrayList<Data>();
					List<Data> dataList = new ArrayList<Data>();
					for(String table : tableList){
						obj.setTableName(table);
						obj.setResourceIds(resid);
						List  clist = getSqlMap().queryForList("CustomReport.queryHyValue", obj);
						dataList.addAll(clist);
					}
					allDataList.addAll(dataList);
					//放入map集合
					Map<String, String> colls = new HashMap<String, String>();
					for (Data d : dataList) {
						d.setLabel2(resourceName);
						d.setLabel3(obj.getKpiName());
						colls.put(d.getLabel(), d.getValue());// 放入时间和内存值
					}
					// 迭代时间集合
					String previousValue="";
					for (Category category : cList) {
						Data dat1 = new Data();
						String time = category.getLabel();
						// 横坐标（时间）是否在colls集合里
						if (colls.containsKey(time)) {
							// 在
							previousValue=colls.get(time);
							dat1.setValue(previousValue);
							date1.add(dat1);
						} else {
							// 不在集合里，则该时间点对应值设为0
							dat1.setValue(previousValue);
							date1.add(dat1);
						}
					}
					dataset1.setData(date1);
					dataset.add(dataset1);
					
				}
				fusionCharts.setCategories(categoriesList);
				fusionCharts.setDataset(dataset);
			}else{
				Log.info("报表需要查询的表不存在");
			}					
			//封装返回结果
			json.put("chartXml", fusionCharts);
			json.put("resultList", allDataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * <p>主机性能报表查询</p>
	 *
	 * @Createtime Aug 6, 2014,1:24:27 PM
	 * @author xugang
	 * @version 1.0
	 * @param obj
	 * @return
	 */
	private JSONObject queryReportDataHost(CustomReportObj obj) {
		JSONObject json = new JSONObject();
		try {
			List<Data>  allDataList = new ArrayList<Data>();
			// 折线图
			FusionCharts fusionCharts = this.getFusionChartsLine(obj.getKpiName()+"走势图",obj.getKpiunit());
			// 横坐标，即时间按点数取limit范围
			List<Categories> categoriesList = new ArrayList<Categories>();
			List<Dataset> dataset = new ArrayList<Dataset>();
			List<String> resourceIds = new ArrayList<String>();
			//根据开始时间和结束时间判断需要查询几张表
			List<String> tableList = this.getQueryTables("tb_cloud2_host_coll",obj.getStartDate(),obj.getEndDate(),obj.getDateType());	
			if(tableList.size()!=0){
				//如果等于-1,表示查询所有,则根据数据中心,集群过滤出top5
				if(ALL.equals(obj.getResourceIds())){
					obj.setTableName(tableList.get(tableList.size()-1));
					resourceIds = getSqlMap().queryForList("CustomReport.queryHostTop5", obj);				
				}else{
					for(String resourceId:obj.getResourceIds().split(",")){
						resourceIds.add(resourceId);
					}
				}
				obj.setResIdsList(resourceIds);
				// 查询所有的时间点
				List<String> timeList = new ArrayList<String>();
				for(String table : tableList){
					obj.setTableName(table);
					List<String>  clist = getSqlMap().queryForList("CustomReport.queryHostCategorys", obj);
					timeList.addAll(clist);
				}
				Categories categories = new Categories();
				List<Category> cList = new ArrayList<Category>();
				
				for (int i=0;i<timeList.size();i++) {
					String time = timeList.get(i);
					Category category = new Category();
					category.setLabel(time);
					cList.add(category);
				}
				categories.setCategory(cList);
				categoriesList.add(categories);
				
				//查出时间和名字对应的值
				for(String resid :resourceIds){
					String resourceName = this.getResourceName(resid,CustomReportService.RESOURCE_TYPE_HOST);
					Dataset dataset1 = new Dataset();
					dataset1.setSeriesname(resourceName);
					List<Data> date1 = new ArrayList<Data>();
					List<Data> dataList = new ArrayList<Data>();
					for(String table : tableList){
						obj.setTableName(table);
						obj.setResourceIds(resid);
						List  clist = getSqlMap().queryForList("CustomReport.queryHostValue", obj);
						dataList.addAll(clist);
					}
					allDataList.addAll(dataList);
					//放入map集合
					Map<String, String> colls = new HashMap<String, String>();
					for (Data d : dataList) {
						d.setLabel2(resourceName);
						d.setLabel3(obj.getKpiName());
						colls.put(d.getLabel(), d.getValue());// 放入时间和内存值
					}
					// 迭代时间集合
					String previousValue="";
					for (Category category : cList) {
						Data dat1 = new Data();
						String time = category.getLabel();
						// 横坐标（时间）是否在colls集合里
						if (colls.containsKey(time)) {
							// 在
							previousValue=colls.get(time);
							dat1.setValue(previousValue);
							date1.add(dat1);
						} else {
							// 不在集合里，则该时间点对应值设为0
							dat1.setValue(previousValue);
							date1.add(dat1);
						}
					}
					dataset1.setData(date1);
					dataset.add(dataset1);
					
				}
				fusionCharts.setCategories(categoriesList);
				fusionCharts.setDataset(dataset);
			}else{
				Log.info("报表需要查询的表不存在");
			}					
			//封装返回结果
			json.put("chartXml", fusionCharts);
			json.put("resultList", allDataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * <p>获取主机名称</p>
	 *
	 * @Createtime Aug 7, 2014,10:05:29 AM
	 * @author xugang
	 * @version 1.0
	 * @param resid
	 * @return
	 */
	private String getResourceName(String resid,String type) {
		String name = "";
		try {
			String[] resids = resid.split("_");
			CustomReportObj obj = new CustomReportObj();
			obj.setConnectId(resids[0]);
			obj.setUuid(resids[1]);
			List<String>  clist = new ArrayList<String>();
			if(CustomReportService.RESOURCE_TYPE_HOST.equals(type)){				
				clist = getSqlMap().queryForList("CustomReport.queryHostName", obj);
			}else{				
				clist = getSqlMap().queryForList("CustomReport.queryHyName", obj);
			}
			if(clist.size()!=0){
				name=clist.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * <p>根据开始时间和结束时间判断需要查询几张表</p>
	 *
	 * @Createtime Aug 6, 2014,2:19:48 PM
	 * @author xugang
	 * @version 1.0
	 * @param string
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List getQueryTables(String prefix, String startDate, String endDate,String DateType) {
		List<String> tables = new ArrayList<String>();
			try {
				Date date1 = TimeformatCommon.TimeStringToDate(startDate,"yyyy-MM-dd hh:mm:ss");
				Date date2 = TimeformatCommon.TimeStringToDate(endDate,"yyyy-MM-dd hh:mm:ss");
				String currentDate = DateUtil.getFormatTime(new Date(), "yyyyMMdd");
				if ((date2.getTime() - date1.getTime()) > 0) {// 结束时间大于开始时间，可以执行查询					
					if(DATETYPE_NOW.equals(DateType)){//实时的查询天表
						int calDay = DateUtil.daysBetween(date1, date2);
						for(int i=0;i<=calDay;i++){
							String day = DateUtil.getFormatNDate(date1, "yyyyMMdd", i);
							if(!currentDate.equals(day)){
								tables.add(prefix+"_"+day);		
							}else{
								tables.add(prefix);	
							}
						}
					}else{//小时和天粒度的查询月表
						//相差月份
						int calMonth = DateUtil.monthsBetween(date1, date2);
						for(int i=0;i<=calMonth;i++){
							String month = DateUtil.getFormatNMonth(date1, "yyyyMM", i); 
							tables.add(prefix+"_"+month);		
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		//验证表是否存在
		tables = this.isExistTable(tables);	
		return tables;
	}
	
	/**
	 * <p>判断表是否存在</p>
	 *
	 * @Createtime Aug 6, 2014,2:47:57 PM
	 * @author xugang
	 * @version 1.0
	 * @param tables
	 */
	private List<String> isExistTable(List<String> tables) {
		List<String> newTables = new ArrayList<String>(); 
		for(String table:tables){
			try {
				int isExist = ((Integer) getSqlMap().queryForObject("CustomReport.isExistTable", table)).intValue();
				if(isExist!=0){
					newTables.add(table);
				}else{
					Log.info("表不存在:"+table);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newTables;
	}
	
	private void setCell(WritableSheet sheet, JSONObject map,int cellIndex,WritableCellFormat wcfFC,String[] keys) throws Exception {
		Iterator iter = map.entrySet().iterator(); 
		for(int i=0;i<keys.length;i++){
			String key = keys[i];
			String val = "";
			if(map.get(key)!=null){
				val = String.valueOf(map.get(key));
			}
			Label label = new jxl.write.Label(i, cellIndex, val,wcfFC);  
			sheet.setColumnView(cellIndex,500); 
			sheet.addCell(label);    
		}
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#getExcelOutPutStream(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public ByteArrayOutputStream getExcelOutPutStream(CustomReportObj obj) {
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
			JSONObject json = this.queryReportData(obj);
			JSONArray list = json.getJSONArray("resultList");

			JSONObject map = new JSONObject();
			map.put("label2", "资源名称");
			map.put("label3", "指标名称");
			map.put("value", "指标值("+obj.getKpiunit()+")");
			map.put("label", "采集时间");
			String[] keys = new String[]{"label2","label3","value","label"};
			// data head
			if(true){				
				Row row = st.createRow(0);
				row.setHeightInPoints(30);
				for(int i=0;i<keys.length;i++){
					String key = keys[i];
					String val = "";
					if(map.get(key)!=null){
						val = String.valueOf(map.get(key));
					}
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
				JSONObject datamap = (JSONObject)list.get(i);
				for(int j=0;j<keys.length;j++){
					String key = keys[j];
					String val = "";
					if(datamap.get(key)!=null){
						val = String.valueOf(datamap.get(key));
					}
					Cell cell = row.createCell(j);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleData);
					cell.setCellValue(val);
				}
			}
			st.autoSizeColumn((short)0); //调整第一列宽度
			st.autoSizeColumn((short)1); //调整第二列宽度
			st.autoSizeColumn((short)2); //调整第三列宽度
			st.autoSizeColumn((short)3); //调整第四列宽度
			workbook.write(os);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}		
		return os;
	}

	/**
	 *
	 * @see com.sitech.ssd.hlj.report.service.CustomReportService#getDataCenterList(com.sitech.ssd.hlj.report.domain.CustomReportObj)
	 */
	@Override
	public List getDataCenterList(CustomReportObj obj) {
		List list  = new ArrayList();
		try {
			list = this.getSqlMap().queryForList("CustomReport.queryDataCenter",obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
		
}
