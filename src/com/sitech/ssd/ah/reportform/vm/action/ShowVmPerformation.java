package com.sitech.ssd.ah.reportform.vm.action;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.util.excel.ExcelUtils;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.ssd.ah.reportform.vm.service.ReportformVM;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.vo.util.UnitedConstant;


/**
 * 虚拟机和主机的报表
 * <p>Title: ShowVmPerformation</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author shijc
 * @version 1.0
 * @createtime 2014-8-21 下午3:57:36
 *
 */
@Controller("showVmPerformation")
@Scope(value="prototype")
public class ShowVmPerformation extends BaseAction {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private List<VmReportForm> vmReportFormList;
	private VmReportForm vf;
	
	@Autowired
	private ReportformVM reportformVM;
	
	@Autowired
	private ReportformVM reportformHost;
	
	@Autowired
	private UnitedTreeService unitedTreeService;
	
	private String isHide;//判断页面是否应该显示列表
	
	
	public String getIsHide() {
		return isHide;
	}


	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}


	public ReportformVM getReportformHost() {
		return reportformHost;
	}


	public void setReportformHost(ReportformVM reportformHost) {
		this.reportformHost = reportformHost;
	}


	/**
	 * @Title: vmPerformance
	 * @Description: 虚拟机性能统计报表
	 * @param
	 * @return String
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 上午9:59:11
	 */
	public String vmPerformance(){
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		if(StringUtils.isEmpty(vf.getStartDate())){
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd")+" 00:00:00");
		}
		if(StringUtils.isEmpty(vf.getEndDate())){
			vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		//if(StringUtils.isEmpty(vf.getCenter_uuid())){
		//	vf.setCenter_uuid(vf.getDataCenterList() != null && vf.getDataCenterList().size() > 0?vf.getDataCenterList().get(0).getUuid():"");
		//}
		vf.setPagination(this.getPaginater().initPagination(request));// 分页
		vmReportFormList = reportformVM.queryHyTimeLabelAndData(vf);
		return "vmPerformance";
	}
	
	/**
	 * 上海电信虚拟机报表管理
	 * @Title: SHVmPerformance
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-21 下午4:01:26
	 */
	public String SHVmPerformance(){
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		if(0==vf.getDiskEndUsage()){
			vf.setDiskEndUsage(10000000);
		}
		if(0==vf.getNetworkEndUsage()){
			vf.setNetworkEndUsage(10000000);
		}
		if(StringUtils.isEmpty(vf.getStartDate())){
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd")+" 00:00:00");
		}
		if(StringUtils.isEmpty(vf.getEndDate())){
			vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		return "SHVmPerformance";
	}
	
	
	/**
	 * 上海电信新增主机报表管理
	 * @Title: SHHostPerformance
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-21 下午3:58:06
	 */
	public String SHHostPerformance(){
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		if(0==vf.getDiskEndUsage()){
			vf.setDiskEndUsage(10000000);
		}
		if(0==vf.getNetworkEndUsage()){
			vf.setNetworkEndUsage(10000000);
		}
		if(StringUtils.isEmpty(vf.getStartDate())){
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd")+" 00:00:00");
		}
		if(StringUtils.isEmpty(vf.getEndDate())){
			vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		return "SHHostPerformance";
	}
	
	
	/**
	 * 上海使用虚拟机报表
	 * @Title: SHVmPerformance
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-6 下午1:52:14
	 */
	public String SHQueryHostPerformance(){
		isHide="1";
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		if(0==vf.getDiskEndUsage()){
			vf.setDiskEndUsage(100000);
		}
		if(0==vf.getNetworkEndUsage()){
			vf.setNetworkEndUsage(100000);
		}
		if(StringUtils.isEmpty(vf.getStartDate())){
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd")+" 00:00:00");
		}
		if(StringUtils.isEmpty(vf.getEndDate())){
			vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		//if(StringUtils.isEmpty(vf.getCenter_uuid())){
		//	vf.setCenter_uuid(vf.getDataCenterList() != null && vf.getDataCenterList().size() > 0?vf.getDataCenterList().get(0).getUuid():"");
		//}
		vf.setPagination(this.getPaginater().initPagination(request));// 分页
		vmReportFormList = reportformHost.queryHyTimeLabelAndData(vf);
		return "SHHostPerformance";
	}
	
	/**
	 * 上海使用虚拟机报表
	 * @Title: SHVmPerformance
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-6 下午1:52:14
	 */
	public String SHQueryVmPerformance(){
		isHide="1";
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		if(0==vf.getDiskEndUsage()){
			vf.setDiskEndUsage(100000);
		}
		if(0==vf.getNetworkEndUsage()){
			vf.setNetworkEndUsage(100000);
		}
		if(StringUtils.isEmpty(vf.getStartDate())){
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd")+" 00:00:00");
		}
		if(StringUtils.isEmpty(vf.getEndDate())){
			vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		//if(StringUtils.isEmpty(vf.getCenter_uuid())){
		//	vf.setCenter_uuid(vf.getDataCenterList() != null && vf.getDataCenterList().size() > 0?vf.getDataCenterList().get(0).getUuid():"");
		//}
		vf.setPagination(this.getPaginater().initPagination(request));// 分页
		vmReportFormList = reportformVM.queryHyTimeLabelAndData(vf);
		return "SHVmPerformance";
	}
	
	//用于虚拟机添加页面Iframe
	public String vmPerformanceForAdd(){
		if(vf == null){
			vf = new VmReportForm();
		}
		if(0==vf.getCpuEndUsage()){
			vf.setCpuEndUsage(100);
		}
		if(0==vf.getMemEndUsage()){
			vf.setMemEndUsage(100);
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		//if(StringUtils.isEmpty(vf.getCenter_uuid())){
		//	vf.setCenter_uuid(vf.getDataCenterList() != null && vf.getDataCenterList().size() > 0?vf.getDataCenterList().get(0).getUuid():"");
		//}
		vf.setPagination(this.getPaginater().initPagination(request));// 分页
		vf.setIsForAdd(1);//标志为 添加页面
		vmReportFormList = reportformVM.queryHyTimeLabelAndData(vf);
		return "vmPerformanceForAdd";
	}
	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService
					.queryForTreeList(unitedTreeObj);
			for (UnitedTreeObj unitedTreeObj2 : utreeList) {
				if (UnitedConstant.VMWARE.equals(unitedTreeObj2.getVtype())
						|| UnitedConstant.XEN.equals(unitedTreeObj2.getVtype())) {
					if (treeList == null) {
						treeList = new ArrayList<UnitedTreeObj>();
					}
					treeList.add(unitedTreeObj2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treeList;
	}
	@SuppressWarnings("unchecked")
	public void vmPerformanceExport() throws IOException{
		if(vf == null){
			vf = new VmReportForm();
		}
		List<VmReportForm> vmReportFormList = reportformVM.queryHyTimeLabelAndData1(vf);
		Map<String,Map<String,Object>> excelDataMap = new HashMap<String,Map<String,Object>>();
		String netWorkName = null;
		Map<String,Object> sheetDataMap = null;
		List<Object[]> rowDataList = null;
		//获取采集指标数据
		String[] title = new String[]{"虚拟机名称","业务子系统","承载业务","IP","虚拟化类型","CPU配置","CPU最大值(%)","内存配置(G)","内存最大值(%)","存储总量(G)","存储使用量峰值(G)","存储使用率(%)"};
		String firstRowTitle = "虚拟机资源使用率统计报表";
		String simpleNetWorkName = null;
		for(VmReportForm data : vmReportFormList){
			netWorkName = data.getNetwork();
			//截取网络域简称
			simpleNetWorkName = StringUtils.isNotEmpty(netWorkName) && netWorkName.length() > 3?netWorkName.substring(0, 3):"未归属vlan";
			if(excelDataMap.containsKey(simpleNetWorkName)){
				sheetDataMap = excelDataMap.get(simpleNetWorkName);
				rowDataList = ((List<Object[]>)sheetDataMap.get(ExcelUtils.SHEET_DATA_KEY));
			}else{
				sheetDataMap = new HashMap<String,Object>();
				excelDataMap.put(simpleNetWorkName, sheetDataMap);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_COLUMN_GROUP_KEY,new Boolean(true));
				sheetDataMap.put(ExcelUtils.SHEET_NAME_KEY, 
						simpleNetWorkName);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_ROW_TITLE_KEY, firstRowTitle);
				sheetDataMap.put(ExcelUtils.SHEET_TITLE_KEY, title);
				rowDataList = new ArrayList<Object[]>();
				sheetDataMap.put(ExcelUtils.SHEET_DATA_KEY, rowDataList);
			}
			rowDataList.add(new String[]{data.getVmName(),StringUtils.trimToEmpty(StringUtils.isEmpty(data.getBusi())?"":data.getBusiParent()),StringUtils.trimToEmpty(StringUtils.isEmpty(data.getBusi())?"":data.getBusi())
					,data.getIp(),data.getVmType()
					,data.getCpuAll(),data.getCpu_maxKpiValue()
					,data.getMemAll(),data.getMem_maxKpiValue()
					,data.getStoreAll(),data.getStoreUsed_maxKpiValue(),data.getStoreUsedPercent()});
		}
		OutputStream os = null;
		try{
			response.setContentType("application/msexcel");
			String fileName = new String((firstRowTitle+".xls").getBytes("GBK"),"ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");//定义文件名
			os = response.getOutputStream();
			ExcelUtils.writeDataMultipleSheet(excelDataMap.values(), os);
		} catch (IOException e) {
			
		}finally{
			if(os != null){
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void sh_vmPerformanceExport() throws IOException{
		if(vf == null){
			vf = new VmReportForm();
		}
		List<VmReportForm> vmReportFormList = reportformVM.queryHyTimeLabelAndData(vf);
		Map<String,Map<String,Object>> excelDataMap = new HashMap<String,Map<String,Object>>();
		String netWorkName = null;
		Map<String,Object> sheetDataMap = null;
		List<Object[]> rowDataList = null;
		//获取采集指标数据
		String[] title = new String[]{"子系统","承载业务","虚拟机名称","所属vlan","IP","虚拟化类型","CPU配置","CPU使用率Max(%)","内存配置(G)","内存使用率Max(%)","磁盘读Max(kbps)","磁盘写Max(kbps)","网络上行Max(kbps)","网络下行Max(kbps)","存储总量(G)","存储使用量Max(G)","存储使用率(%)"};
		String firstRowTitle = "虚拟机资源使用率统计报表";
		String simpleNetWorkName = "未归属vlan";
		for(VmReportForm data : vmReportFormList){
			netWorkName = data.getNetwork();
			//截取网络域简称
			/**
			 * 暂时不按照网络进行sheet页的划分，所有的虚拟机都在一个sheet内
			 * 
			 * 需要改造，虚拟机报表从缓存中获取，查询的数据放入缓存。
			 */
//			simpleNetWorkName = StringUtils.isNotEmpty(netWorkName) && netWorkName.length() > 3?netWorkName.substring(0, 3):"未归属vlan";
			if(excelDataMap.containsKey(simpleNetWorkName)){
				sheetDataMap = excelDataMap.get(simpleNetWorkName);
				rowDataList = ((List<Object[]>)sheetDataMap.get(ExcelUtils.SHEET_DATA_KEY));
			}else{
				sheetDataMap = new HashMap<String,Object>();
				excelDataMap.put(simpleNetWorkName, sheetDataMap);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_COLUMN_GROUP_KEY,new Boolean(true));
				sheetDataMap.put(ExcelUtils.SHEET_NAME_KEY, 
						simpleNetWorkName);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_ROW_TITLE_KEY, firstRowTitle);
				sheetDataMap.put(ExcelUtils.SHEET_TITLE_KEY, title);
				rowDataList = new ArrayList<Object[]>();
				sheetDataMap.put(ExcelUtils.SHEET_DATA_KEY, rowDataList);
			}
			rowDataList.add(new String[]{StringUtils.trimToEmpty(StringUtils.isEmpty(data.getBusi())?"":data.getBusiParent()),StringUtils.trimToEmpty(StringUtils.isEmpty(data.getBusi())?"":data.getBusi())
					,data.getVmName(),data.getNetwork(),data.getIp(),data.getVmType()
					,data.getCpuAll(),data.getCpu_maxKpiValue()
					,data.getMemAll(),data.getMem_maxKpiValue()
					,data.getDisk_ioReadMaxValue(),data.getDisk_ioWriteMaxValue(),data.getNetwork_ioUpMaxValue(),data.getNetwork_ioDownMaxValue()
					,data.getStoreAll(),data.getStoreUsed_maxKpiValue(),data.getStoreUsedPercent()});
		}
		OutputStream os = null;
		try{
			response.setContentType("application/msexcel");
			String fileName = new String((firstRowTitle+".xls").getBytes("GBK"),"ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");//定义文件名
			os = response.getOutputStream();
			ExcelUtils.writeDataMultipleSheet(excelDataMap.values(), os);
		} catch (IOException e) {
			
		}finally{
			if(os != null){
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}
	
	
	/**
	 * 主机报表的导出操作
	 * @Title: sh_hostPerformanceExport
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return void
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-9-11 下午2:56:04
	 */
	public void sh_hostPerformanceExport() throws IOException{
		if(vf == null){
			vf = new VmReportForm();
		}
		List<VmReportForm> vmReportFormList = reportformHost.queryHyTimeLabelAndData(vf);
		Map<String,Map<String,Object>> excelDataMap = new HashMap<String,Map<String,Object>>();
		String netWorkName = null;
		Map<String,Object> sheetDataMap = null;
		List<Object[]> rowDataList = null;
		//获取采集指标数据
		String[] title = new String[]{"主机名称","所属vlan","IP","主机类型","CPU配置","CPU使用率Max(%)","内存配置(G)","内存使用率Max(%)","磁盘读Max(kbps)","磁盘写Max(kbps)","网络上行Max(kbps)","网络下行Max(kbps)","存储总量(G)","存储使用量Max(G)","存储使用率(%)"};
		String firstRowTitle = "主机资源使用率统计报表";
		String simpleNetWorkName = "未归属vlan";
		for(VmReportForm data : vmReportFormList){
			netWorkName = data.getNetwork();
			//截取网络域简称
			/**
			 * 暂时不按照网络进行sheet页的划分，所有的虚拟机都在一个sheet内
			 * 
			 * 需要改造，虚拟机报表从缓存中获取，查询的数据放入缓存。
			 */
//			simpleNetWorkName = StringUtils.isNotEmpty(netWorkName) && netWorkName.length() > 3?netWorkName.substring(0, 3):"未归属vlan";
			if(excelDataMap.containsKey(simpleNetWorkName)){
				sheetDataMap = excelDataMap.get(simpleNetWorkName);
				rowDataList = ((List<Object[]>)sheetDataMap.get(ExcelUtils.SHEET_DATA_KEY));
			}else{
				sheetDataMap = new HashMap<String,Object>();
				excelDataMap.put(simpleNetWorkName, sheetDataMap);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_COLUMN_GROUP_KEY,new Boolean(true));
				sheetDataMap.put(ExcelUtils.SHEET_NAME_KEY, 
						simpleNetWorkName);
				sheetDataMap.put(ExcelUtils.SHEET_FIRST_ROW_TITLE_KEY, firstRowTitle);
				sheetDataMap.put(ExcelUtils.SHEET_TITLE_KEY, title);
				rowDataList = new ArrayList<Object[]>();
				sheetDataMap.put(ExcelUtils.SHEET_DATA_KEY, rowDataList);
			}
			rowDataList.add(new String[]{data.getHostName(),data.getNetwork(),data.getIp(),data.getHostType()
					,data.getCpuAll(),data.getCpu_maxKpiValue()
					,data.getMemAll(),data.getMem_maxKpiValue()
					,data.getDisk_ioReadMaxValue(),data.getDisk_ioWriteMaxValue(),data.getNetwork_ioUpMaxValue(),data.getNetwork_ioDownMaxValue()
					,data.getStoreAll(),data.getStoreUsed_maxKpiValue(),data.getStoreUsedPercent()});
		}
		OutputStream os = null;
		try{
			response.setContentType("application/msexcel");
			String fileName = new String((firstRowTitle+".xls").getBytes("GBK"),"ISO8859-1");
			response.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");//定义文件名
			os = response.getOutputStream();
			ExcelUtils.writeDataMultipleSheet(excelDataMap.values(), os);
		} catch (IOException e) {
			
		}finally{
			if(os != null){
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}

	public VmReportForm getVf() {
		return vf;
	}

	public void setVf(VmReportForm vf) {
		this.vf = vf;
	}

	public ReportformVM getReportformVM() {
		return reportformVM;
	}

	public List<VmReportForm> getVmReportFormList() {
		return vmReportFormList;
	}

	public void setVmReportFormList(List<VmReportForm> vmReportFormList) {
		this.vmReportFormList = vmReportFormList;
	}

	public void setReportformVM(ReportformVM reportformVM) {
		this.reportformVM = reportformVM;
	}
	
	
}
