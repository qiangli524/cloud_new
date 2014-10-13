package com.sitech.ssd.ah.reportform.vm.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.ssd.ah.reportform.vm.service.ReportformVM;
import com.sitech.ssd.ah.reportform.vm.service.VmForChartService;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

@Controller("showVmChartAction")
@Scope(value = "prototype")
public class ShowVmChartAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private VmReportForm vf;
	private List<VmReportForm> resultList;
	@Autowired
	private ReportformVM reportformVM;
	@Autowired
	private VmForChartService vmForChartService;
	@Autowired
	private UnitedTreeService unitedTreeService;

	public VmForChartService getVmForChartService() {
		return vmForChartService;
	}

	public void setVmForChartService(VmForChartService vmForChartService) {
		this.vmForChartService = vmForChartService;
	}

	public VmReportForm getVf() {
		return vf;
	}

	public void setVf(VmReportForm vf) {
		this.vf = vf;
	}

	/**
	 * @Title: printToScreen
	 * @Description: 将内容打印回屏幕
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-14 上午8:51:22
	 */
	private void printToScreen(Object value) {
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(value);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: showVmDetail
	 * @Description: 至虚拟机报表页面
	 * @author qism
	 * @return String
	 * @createtime 2014-4-14 上午9:21:10
	 */
	public String showVmDetail() {
		if (vf == null) {
			vf = new VmReportForm();
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		return "showVmDetail";
	}

	/**
	 * @Title: showVmDetailAdd
	 * @Description: 至虚拟机添加页面
	 * @author qism
	 * @return String
	 * @createtime 2014-4-21 上午9:21:10
	 */
	public String showVmDetailAdd() {
		return "showVmDetailAdd";
	}

	/**
	 * @Title: showVmDetailInfo
	 * @Description:虚拟机报表界面展示信息
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-4-14 上午9:37:12
	 */
	public void showVmDetailInfo() {
		if (vf == null) {
			System.out.println("vf为空，创建对象！");
			vf = new VmReportForm();
		}
		if (StringUtils.isEmpty(vf.getStartDate())) {
			vf.setStartDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 00:00:00");
		}
		// if(StringUtils.isEmpty(vf.getEndDate())){
		// vf.setEndDate(vf.getStartDate().substring(0, 10)+" 23:59:59");
		// }
		// 如果结束时间为空，则为今天
		if (StringUtils.isEmpty(vf.getEndDate())) {
			vf.setEndDate(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 23:59:59");
		}
		// 如果为空提交上来的会是0
		if (vf.getTop_num() == 0) {
			vf.setTop_num(10);
		}
		String chartJson;
		if ("cpu".equals(vf.getCpu_mem())) {
			if (vf.getCpuStartUsage() == 0) {
				vf.setCpuStartUsage(0);
			}
			if (vf.getCpuEndUsage() == 0) {
				vf.setCpuEndUsage(100);
			}
			try {
				// vf.setVmName(URLDecoder.decode(vf.getVmName(), "UTF-8"));
				if (vf.getBusi() != null) {
					vf.setBusi(URLDecoder.decode(vf.getBusi(), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("开始获取cpuJson");
			chartJson = vmForChartService.buildBusiTopNChartJSON_cpu(vf);
		} else {
			if (vf.getMemStartUsage() == 0) {
				vf.setMemStartUsage(0);
			}
			if (vf.getMemEndUsage() == 0) {
				vf.setMemEndUsage(100);
			}
			try {
				System.out.println("开始获取memJson");
				// vf.setVmName(URLDecoder.decode(vf.getVmName(), "UTF-8"));
				if (vf.getBusi() != null) {
					vf.setBusi(URLDecoder.decode(vf.getBusi(), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			chartJson = vmForChartService.buildBusiTopNChartJSON_mem(vf);
		}
		JSONObject jo = new JSONObject();
		resultList = reportformVM.queryHyTimeLabelAndDataForChartList(vf);
		jo.put("resultList", resultList);
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	@SuppressWarnings("unused")
	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService.queryForTreeList(unitedTreeObj);
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
}