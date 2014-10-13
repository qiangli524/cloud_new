package com.sitech.ssd.ah.busiMonitor.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.service.monitor.BossProcessMonitorService;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorReportObj;
import com.sitech.ssd.ah.busiMonitor.service.MonitorReportService;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("unchecked")
@Controller("monitorReportAction")
@Scope("prototype")
public class MonitorReportAction  extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MonitorReportAction.class);
	
	@Autowired
	private MonitorReportService monitorReportService;
	@Autowired
	BossProcessMonitorService bossProcessMonitorService;

	private MonitorReportObj obj;
	
	private List clusterList;
	private List poolList;
	
	public String reportMain(){
		if(obj == null){
			obj = new MonitorReportObj();
		}
		CommonObj cObj = new CommonObj();
		cObj.setType("2");
		clusterList = bossProcessMonitorService.queryCommonObjList(cObj);
		cObj.setType("3");
		poolList = bossProcessMonitorService.queryCommonObjList(cObj);
		return "reportMain";
	}
	public void queryChartJSONForTraffic() throws ParseException{
		if (obj == null) {
			obj = new MonitorReportObj();
		}
		if ("".equals(obj.getNum()) || obj.getNum() == null) {
			obj.setNum(24);
		}
		//如果不是查询话务量监控数据的，则不判断在线/离线
		if(!"PM-01-01-001-01".equals(obj.getBusiKpi())){
			obj.setOnlineFlag("");
		}
		
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMddHH");
		Date endDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(endDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		Date beginDate = dft.parse(dft.format(date.getTime()));
		
		obj.setStartTime(dft.format(beginDate));
		obj.setEndTime(dft.format(endDate));
		
		logger.info("开始获取Json");
		String chartJson = monitorReportService.queryChartJSONForTraffic(obj);
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}
	
	/**
	 * @Title: printToScreen
	 * @Description: 将内容打印回屏幕
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午8:57:57
	 */
	private void printToScreen(Object value) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriterOut.printWirter(response, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MonitorReportObj getObj() {
		return obj;
	}
	public void setObj(MonitorReportObj obj) {
		this.obj = obj;
	}
	public List getClusterList() {
		return clusterList;
	}
	public void setClusterList(List clusterList) {
		this.clusterList = clusterList;
	}
	public List getPoolList() {
		return poolList;
	}
	public void setPoolList(List poolList) {
		this.poolList = poolList;
	}
	
}
