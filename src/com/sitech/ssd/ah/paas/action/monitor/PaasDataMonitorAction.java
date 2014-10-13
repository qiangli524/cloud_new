package com.sitech.ssd.ah.paas.action.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.enterprisedt.net.ftp.script.PwdCommand;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.service.monitor.PaasDataMonitorService;
import com.sitech.ssd.ah.paas.service.statistics.PaasStatisticsService;
import com.sitech.utils.servlet.PrintWriterOut;


@Controller("paasDataMonitorAction")
@Scope("prototype")
public class PaasDataMonitorAction extends BaseAction {

	private String entity_id;
	private String node_type;
	private List<PaasKpiMonitorObj> kpiList;
	private List<PaasResourceMonitorObj> list;
	private PaasResourceMonitorObj dataMonitor;
	
	
	@Autowired
	private PaasDataMonitorService paasDataMonitorService;
	@Autowired
	private PaasStatisticsService paasStatisticsService;

	/**
	 * 
	 * @Title: goPaasDataMonitorPage
	 * @Description: 进入数据监控界面
	 * @param
	 * @return List<PaasResourceMonitorObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月22日19:42:47
	 */
	public String goPaasDataMonitorPage(){
		PaasResourceMonitorObj obj = new PaasResourceMonitorObj();
		if(entity_id != null && !"".equals(entity_id)){
			obj.setKpiValue(entity_id);
		}
		list = paasDataMonitorService.getSlabLists(obj);
		if(list.size()>0){
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(list.get(0).getKpiValue());
			kpiList = paasStatisticsService.queryForLastStatisticsList(monitorObj);
		}
		PaasKpiMonitorObj kpiObj = new PaasKpiMonitorObj();
		if(entity_id != null && !"".equals(entity_id)){
			kpiObj.setEntity_id(entity_id);
		}
		dataMonitor = paasStatisticsService.queryForGlobalValue(kpiObj);
		return "go";
	}

	/**
	 * 
	 * @Title: getSlabsInfo
	 * @Description: 获取不同slabs的信息
	 * @param
	 * @return List<PaasResourceMonitorObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014年4月22日19:42:47
	 */
	public String getSlabsInfo(){
		if(entity_id!=null&&!"".equals(entity_id)){
			PaasKpiMonitorObj monitorObj = new PaasKpiMonitorObj();
			monitorObj.setEntity_id(entity_id);
			kpiList = paasStatisticsService.queryForLastStatisticsList(monitorObj);
		}
		JSONObject json = new JSONObject();
		json.put("slabkpi", kpiList);
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}
	
	
	
	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public List<PaasKpiMonitorObj> getKpiList() {
		return kpiList;
	}

	public void setKpiList(List<PaasKpiMonitorObj> kpiList) {
		this.kpiList = kpiList;
	}

	public List<PaasResourceMonitorObj> getList() {
		return list;
	}
	
	public void setList(List<PaasResourceMonitorObj> list) {
		this.list = list;
	}

	public PaasResourceMonitorObj getDataMonitor() {
		return dataMonitor;
	}

	public void setDataMonitor(PaasResourceMonitorObj dataMonitor) {
		this.dataMonitor = dataMonitor;
	}
	
	
}
