package com.sitech.basd.sxcloud.cloud.web.showview.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.service.showview.ShowViewService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

public class ShowViewAction extends BaseAction {

	@Autowired
	private ShowViewService showViewService;
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String showview() {
		resultList = showViewService.queryAllCollHost();
		return "show";
	}

	/**
	 * 
	 * @Title: queryChartDayData
	 * @Description: 查询天表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-29 下午8:16:01
	 */
	public String queryChartDayData() {
		String machineType = request.getParameter("machineType");
		String collType = request.getParameter("collType");
		String collDataType = request.getParameter("collDataType");
		String machineId = request.getParameter("machineId");
		FusionCharts fChart = showViewService.queryChartDayData(machineType, collType,
				collDataType, machineId);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryChartWeekData
	 * @Description: 查询周表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午10:04:58
	 */
	public String queryChartWeekData() {
		String machineType = request.getParameter("machineType");
		String collType = request.getParameter("collType");
		String collDataType = request.getParameter("collDataType");
		String machineId = request.getParameter("machineId");
		FusionCharts fChart = showViewService.queryChartWeekData(machineType, collType,
				collDataType, machineId);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryChartMonthData
	 * @Description: 查询月表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午11:15:11
	 */
	public String queryChartMonthData() {
		String machineType = request.getParameter("machineType");
		String collType = request.getParameter("collType");
		String collDataType = request.getParameter("collDataType");
		String machineId = request.getParameter("machineId");
		FusionCharts fChart = showViewService.queryChartMonthData(machineType, collType,
				collDataType, machineId);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryChartYearData
	 * @Description: 查询年表数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-30 上午11:15:11
	 */
	public String queryChartYearData() {
		String machineType = request.getParameter("machineType");
		String collType = request.getParameter("collType");
		String collDataType = request.getParameter("collDataType");
		String machineId = request.getParameter("machineId");
		FusionCharts fChart = showViewService.queryChartYearData(machineType, collType,
				collDataType, machineId);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				// out.println(params[i]);
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
		// out.close();
	}

	public String queryAllMachine() {
		String machineType = request.getParameter("machineType");
		List lst = null;
		if (machineType.equals("1")) {
			lst = showViewService.queryAllCollHost();
		} else {
			lst = showViewService.queryAllCollVm();
		}
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		for (Object object : lst) {
			Map<String, String> map = (Map) object;
			jo.put("id", map.get("ID"));
			ja.add(jo);
		}
		printJson(ja.toString());
		return null;
	}
}
