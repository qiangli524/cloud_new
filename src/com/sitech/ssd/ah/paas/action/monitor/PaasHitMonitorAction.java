package com.sitech.ssd.ah.paas.action.monitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.monitor.PaasStatisticsMonitorService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.utils.servlet.PrintWriterOut;


@Controller("paasHitMonitorAction")
@Scope("prototype")
public class PaasHitMonitorAction extends BaseAction {

	private String id;
	private String entity_id;
	private String node_type;
	private String fusionChartsString;
	private String flag;// get,delete,incr,touch..
	private Map<String, String> map;

	@Autowired
	private PaasStatisticsMonitorService paasStatisticsMonitorService;
	
	/**
	 * 
	 * @Title: goPaasHitMonitorPage
	 * @Description: 跳转到命中监控
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2014-4-1 下午3:02:59
	 */
	public String goPaasHitMonitorPage(){
		if(map==null){
			map = new HashMap<String, String>();
		}
		PaasKpiMonitorObj obj = new PaasKpiMonitorObj();
		if(entity_id!=null&&!"".equals(entity_id)){
			obj.setEntity_id(entity_id);
		}
		obj.setServer_kpi(PaasConstant.MEMCACHE);
		obj.setKpi_type(PaasConstant.MEMCACHE);
		map = paasStatisticsMonitorService.showHitsAndMissesValue(obj);
		return "go";
	}

	/**
	 * 
	 * @Title: showHits
	 * @Description: 展示命中情况
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-1 下午3:02:59
	 */
	public String showHits() {
		FusionCharts fusionCharts = new FusionCharts();
		PaasTreeObj treeObj = new PaasTreeObj();
		if (PaasConstant.EXAMPLES.equals(node_type)) {
			treeObj.setEntity_id(entity_id);
		} else if (PaasConstant.RESOURCEPOOL.equals(node_type)) {
			treeObj.setParent_id(id);
		}
		treeObj.setNode_type(node_type);
		treeObj.setFlag(flag);
		try {
			fusionCharts = paasStatisticsMonitorService.showHitsAndMisses(treeObj);
		} catch (Exception e) {
			LOG.error("查询失败", e.getMessage() + e.getClass().getName());
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
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
	 * @createtime 2013-9-16 上午10:61:00
	 */
	private synchronized void printJson(String... params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
	}

	/**
	 * 
	 * @Title: initQueryErrorCharts
	 * @Description: 当查询失败时，显示报表
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:45:04
	 */
	private FusionCharts initQueryErrorCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		return fusionCharts;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFusionChartsString() {
		return fusionChartsString;
	}

	public void setFusionChartsString(String fusionChartsString) {
		this.fusionChartsString = fusionChartsString;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	
}
