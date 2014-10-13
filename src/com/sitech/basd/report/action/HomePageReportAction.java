package com.sitech.basd.report.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.report.domain.VmHostBusiSysVO;
import com.sitech.basd.report.domain.VmHostDetailInfoVO;
import com.sitech.basd.report.service.HomePageReportService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: HomePageReportAction
 * </p>
 * <p>
 * Description: 主页报表Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-6-17 下午2:22:27
 * 
 */
@Controller("homePageReportAction")
@Scope("prototype")
public class HomePageReportAction extends CRUDBaseAction {
	@Autowired
	private HomePageReportService homePageReportService;
	private VmHostDetailInfoVO vmHostDetailInfoVO; 
	private String hyId;
	private String type;

	/**
	 * 
	 * @Title: resourceStatistics
	 * @Description: 跳转到资源统计界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:53:11
	 */
	public String resourceStatistics() {
		return "resourceStatistics";
	}

	/**
	 * 
	 * @Title: resourceStatisticsData
	 * @Description: 资源统计数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:56:42
	 */
	public String resourceStatisticsData() {
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.initResourceStatisticsData(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}

	/**
	 * 
	 * @Title: resourceStatisticsHis
	 * @Description: 跳转到资源历史趋势界面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:53:11
	 */
	public String resourceHisTrend() {
		return "resourceHisTrend";
	}

	/**
	 * 
	 * @Title: resourceStatisticsHisData
	 * @Description: 资源统计数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:56:42
	 */
	public String resourceHisTrendData() {
		String type = request.getParameter("type");
		String data = homePageReportService.initResourceStatisticsTrendData(type);
		PrintWriterOut.printWirter(response, data);
		return null;
	}

	/**
	 * 
	 * @Title: historyAlarmTrend
	 * @Description: 跳转到历史告警趋势图
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:53:11
	 */
	public String historyAlarmTrend() {
		return "historyAlarmTrend";
	}

	/**
	 * 
	 * @Title: historyAlarmTrendData
	 * @Description: 历史告警趋势数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-17 下午4:56:42
	 */
	public String historyAlarmTrendData() {
		String data = homePageReportService.initHistoryAlarmData();
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title:vmHostPfmcSummary
	 * @Description:跳转到虚拟主机性能情况页面的方法
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 4, 2014 10:06:10 AM
	 */
	public String vmHostPfmcSummary(){
		return "vmHostPfmcSummary";
	}
	
	/**
	 * @Title:vmHostPfmcSummaryData
	 * @Description:虚拟主机性能情况数据查询方法
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 4, 2014 10:38:17 AM
	 */
	public String vmHostPfmcSummaryData() {
		String type = request.getParameter("type");
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.initVmHostPfmcSummaryData(type,domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title:vmHostDetailInfo
	 * @Description:虚拟机详细信息查询方法
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 9:46:54 AM
	 */
	@SuppressWarnings("unchecked")
	public String vmHostDetailInfo() {
		String hyId = request.getParameter("hyId");
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("HY_ID", hyId);
		hashMap.put("domain", domain);
		List list = homePageReportService.queryVmHostDetailInfo(hashMap);
		List<VmHostBusiSysVO> busiSysList = new ArrayList<VmHostBusiSysVO>();
		VmHostDetailInfoVO vmHostDetailInfoVO = new VmHostDetailInfoVO();
		if (list != null && list.size() > 0) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				HashMap<String, String> vmHostDetailInfoHashMap = (HashMap<String, String>) iterator.next();
				vmHostDetailInfoVO.setBlgCenterName(vmHostDetailInfoHashMap.get("BLG_CENTER_NAME"));
				vmHostDetailInfoVO.setBlgHostName(vmHostDetailInfoHashMap.get("BLG_HOST_NAME"));
				vmHostDetailInfoVO.setVmHostName(vmHostDetailInfoHashMap.get("VM_HOST_NAME"));
				vmHostDetailInfoVO.setVmHostIp(vmHostDetailInfoHashMap.get("VM_HOST_IP"));
				VmHostBusiSysVO vmHostBusiSysVO = new VmHostBusiSysVO();
				vmHostBusiSysVO.setBusiSysName1(vmHostDetailInfoHashMap.get("busiSysName1"));
				vmHostBusiSysVO.setBusiSysName2(vmHostDetailInfoHashMap.get("busiSysName2"));
				vmHostBusiSysVO.setBusiSysName3(vmHostDetailInfoHashMap.get("busiSysName3"));
				vmHostBusiSysVO.setBusiSysName4(vmHostDetailInfoHashMap.get("busiSysName4"));
				vmHostBusiSysVO.setBusiSysName5(vmHostDetailInfoHashMap.get("busiSysName5"));
				busiSysList.add(vmHostBusiSysVO);
			}
			vmHostDetailInfoVO.setBusiSysList(busiSysList);
			this.vmHostDetailInfoVO = vmHostDetailInfoVO;
		}
		return "vmHostDetailInfo";
	}
	
	/**
	 * @Title: LANPortRateReport
	 * @Description: LAN（半双工）端口利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:02:42
	 */
	public String LANPortRateReport(){
		return "LANPortRateReport";
	}
	
	/**
	 * @Title: LANPortRateReportData
	 * @Description: LAN（半双工）端口利用率报表  ajax
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:02:44
	 */
	public String LANPortRateReportData() {
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryLANPortRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: WANPortInRateReport
	 * @Description: WAN（全双工）端口流入利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:04:34
	 */
	public String WANPortInRateReport(){
		return "WANPortInRateReport";
	}
	
	/**
	 * @Title: WANPortInRateReportData
	 * @Description: WAN（全双工）端口流入利用率报表 ajax
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:04:36
	 */
	public String WANPortInRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryWANPortInRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: WANPortOutRateReport
	 * @Description: WAN（全双工）端口流出利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:10:01
	 */
	public String WANPortOutRateReport(){
		return "WANPortOutRateReport";
	}
	
	/**
	 * @Title: WANPortOutRateReportData
	 * @Description: WAN（全双工）端口流出利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:10:03
	 */
	public String WANPortOutRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryWANPortOutRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: PortErrorRateReport
	 * @Description: 端口误码率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:16:32
	 */
	public String PortErrorRateReport(){
		return "PortErrorRateReport";
	}
	
	/**
	 * @Title: PortErrorRateReportData
	 * @Description: 端口误码率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:16:33
	 */
	public String PortErrorRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryPortErrorRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: PortLostRateReport
	 * @Description: 端口丢包率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:21:15
	 */
	public String PortLostRateReport(){
		return "PortLostRateReport";
	}
	
	/**
	 * @Title: PortLostRateReportData
	 * @Description: 端口丢包率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:21:16
	 */
	public String PortLostRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryPortLostRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: CPUUsedRateReport
	 * @Description: 设备CPU利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:26:06
	 */
	public String CPUUsedRateReport(){
		return "CPUUsedRateReport";
	}
	
	/**
	 * @Title: CPUUsedRateReportData
	 * @Description: 设备CPU利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:26:04
	 */
	public String CPUUsedRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryCPUUsedRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	/**
	 * @Title: MemUsedRateReport
	 * @Description: 设备内存利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:30:06
	 */
	public String MemUsedRateReport(){
		return "MemUsedRateReport";
	}
	
	/**
	 * @Title: MemUsedRateReportData
	 * @Description: 设备内存利用率报表
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-15 上午10:30:09
	 */
	public String MemUsedRateReportData(){
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.queryMemUsedRateReport(domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @Title:vmHostDetailInfoData
	 * @Description:虚拟机详细信息页面中进程占用TOP显示数据查询方法
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Aug 7, 2014 9:49:20 AM
	 */
	public String vmHostDetailInfoData() {
		String domain = session.get(Constant.USER_DOMAIN)==null ? "" : session.get(Constant.USER_DOMAIN).toString();
		String data = homePageReportService.initVmHostDetailInfoData(hyId,type,domain);
		PrintWriterOut.printWirter(response, data);
		return null;
	}

	public String getHyId() {
		return hyId;
	}

	public void setHyId(String hyId) {
		this.hyId = hyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VmHostDetailInfoVO getVmHostDetailInfoVO() {
		return vmHostDetailInfoVO;
	}

	public void setVmHostDetailInfoVO(VmHostDetailInfoVO vmHostDetailInfoVO) {
		this.vmHostDetailInfoVO = vmHostDetailInfoVO;
	}
	
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:新资源总体报表
	 *  @作者 
	 *	@时间 2014-9-16 上午9:44:34
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String resourceMainReport() {
		return "resourceMainReport";
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:新资源总体报表数据
	 *  @作者 
	 *	@时间 2014-9-16 上午9:44:50
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	
	public String resourceMainReportData() throws SQLException {
		String data = homePageReportService.resourceMainReport(request);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:进入数据中心使用率报表
	 *  @作者 
	 *	@时间 2014-9-16 上午9:45:05
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String resourceDCReport() {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("type", request.getParameter("type"));
		return "resourceDCReport";
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:数据中心使用率数据
	 *  @作者 
	 *	@时间 2014-9-16 上午9:45:19
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public String resourceDCReportData() throws SQLException {
		String data = homePageReportService.resourceDCReport(request);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:进入集群使用率报表
	 *  @作者 
	 *	@时间 2014-9-16 上午9:45:32
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String resourceGroupReport() {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("type", request.getParameter("type"));
		return "resourceGroupReport";
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:集群使用率数据
	 *  @作者 
	 *	@时间 2014-9-16 上午9:45:55
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public String resourceGroupReportData() throws SQLException {
		String data = homePageReportService.resourceGroupReport(request);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:进入主机使用率报表
	 *  @作者 
	 *	@时间 2014-9-16 上午9:46:05
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String resourceHostReport() {
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("parent_id", request.getParameter("parent_id"));
		return "resourceHostReport";
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:主机使用率数据
	 *  @作者 
	 *	@时间 2014-9-16 上午9:46:16
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public String resourceHostReportData() throws SQLException {
		String data = homePageReportService.resourceHostReport(request);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:IP使用率下钻，网络域->子网络域->VLAN
	 *  @作者 
	 *	@时间 2014-9-16 上午9:46:40
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 */
	public String resourceNextIpReport() {
		request.setAttribute("level", request.getParameter("level"));
		request.setAttribute("id", request.getParameter("id"));
		request.setAttribute("parent_id", request.getParameter("parent_id"));
		return "resourceNextIpReport";
	}
	/**
	 * 
	 *  @方法名称:
	 *  @方法描述:IP使用率数据
	 *  @作者 
	 *	@时间 2014-9-16 上午9:47:16
	 *  @版权 si-tech 2014 All right reserved.
	 *  @return
	 *  @throws SQLException
	 */
	public String resourceNextIpReportData() throws SQLException {
		String data = homePageReportService.getIPData(request);
		PrintWriterOut.printWirter(response, data);
		return null;
	}
	
	public String colonyReport() {
		request.setAttribute("type", request.getParameter("type"));
		return "colonyReport";
	}
}
