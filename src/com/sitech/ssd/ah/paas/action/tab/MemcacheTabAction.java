package com.sitech.ssd.ah.paas.action.tab;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.host.PaasHostInfoObj;
import com.sitech.ssd.ah.paas.domain.statistics.PaasKpiMonitorObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.host.PaasHostInfoService;
import com.sitech.ssd.ah.paas.service.statistics.PaasStatisticsService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: MemcacheTabAction
 * </p>
 * <p>
 * Description: memcache资源树摘要相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-3-28 上午9:17:06
 * 
 */
@Controller("memcacheTabAction")
@Scope("prototype")
public class MemcacheTabAction extends BaseAction {

	@Autowired
	private PaasStatisticsService paasAbstractService;
	@Autowired
	private PaasTreeService paasTreeService;
	@Autowired
	private PaasHostInfoService paasHostInfoService;

	private PaasHostInfoObj hostObj;

	private PaasTreeObj paasTreeObj;

	private List resultList;

	private List memcacheList;

	private String id;

	private String entity_id;// 实例Id

	private String parent_id;// 父节点Id

	private String node_type;// 节点类型

	private String flag;// get,delete,incr,touch..

	private String fusionChartsString;

	public PaasHostInfoObj getHostObj() {
		return hostObj;
	}

	public void setHostObj(PaasHostInfoObj hostObj) {
		this.hostObj = hostObj;
	}

	public PaasTreeObj getPaasTreeObj() {
		return paasTreeObj;
	}

	public void setPaasTreeObj(PaasTreeObj paasTreeObj) {
		this.paasTreeObj = paasTreeObj;
	}

	/**
	 * 
	 * @Title: queryNodeName
	 * @Description: 查询节点名字
	 * @param
	 * @return PaasTreeObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-8-1 上午10:26:50
	 */
	private PaasTreeObj queryNodeName(String id) {
		PaasTreeObj paTreeObj = new PaasTreeObj();
		paTreeObj.setId(id);
		List<PaasTreeObj> nodeList = paasTreeService.queryTreeNodeByObj(paTreeObj);
		if (nodeList != null && nodeList.size() > 0) {
			paTreeObj = nodeList.get(0);
		}
		return paTreeObj;
	}

	/**
	 * 
	 * @Title: caasResourcePool
	 * @Description: caas资源池摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-14 下午5:13:34
	 */
	public String caasResourcePool() {
		PaasTreeObj treeObj = new PaasTreeObj();
		PaasTreeObj treeObj1 = new PaasTreeObj();
		treeObj1.setParent_id(id);
		List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj1);
		if (treeList != null && treeList.size() > 0) {
			treeObj1 = treeList.get(0);
			PaasTreeObj treeObj2 = new PaasTreeObj();
			treeObj2.setParent_id(treeObj1.getId());
			List<PaasTreeObj> treeList1 = paasTreeService.queryTreeNodeByObj(treeObj2);
			if (treeList1 != null && treeList1.size() > 0) {
				treeObj2 = treeList1.get(0);
				treeObj.setParent_id(treeObj2.getId());
			}
		}
		memcacheList = paasAbstractService.queryKpiNameAndValueForPool(treeObj);
		resultList = paasAbstractService.queryMemcacheExamples(treeObj);
		return "caasStatistics";
	}

	/**
	 * 
	 * @Title: memcacheExamples
	 * @Description: memcache实例摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-31 上午9:41:48
	 */
	public String memcacheExamples() {
		paasTreeObj = this.queryNodeName(id);
		PaasKpiMonitorObj kpiObj = new PaasKpiMonitorObj();
		kpiObj.setEntity_id(entity_id);
		kpiObj.setServer_kpi(PaasConstant.MEMCACHE);
		kpiObj.setKpi_type(PaasConstant.MEMCACHE);
		resultList = paasAbstractService.queryKpiNameAndValue(kpiObj);
		return "memcacheExamples";
	}

	/**
	 * 
	 * @Title: memcacheResourcePool
	 * @Description: memcache资源池摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-1 下午7:54:34
	 */
	public String memcacheResourcePool() {
		paasTreeObj = this.queryNodeName(id);
		PaasTreeObj treeObj = new PaasTreeObj();
		if (PaasConstant.CAAS_TYPE.equals(node_type)) {
			PaasTreeObj treeObj1 = new PaasTreeObj();
			treeObj1.setParent_id(id);
			List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj1);
			if (treeList != null && treeList.size() > 0) {
				treeObj1 = treeList.get(0);
				treeObj.setParent_id(treeObj1.getId());
			}
		} else if (PaasConstant.CAAS_BUSI.equals(node_type)) {
			treeObj.setParent_id(id);
		}
		memcacheList = paasAbstractService.queryKpiNameAndValueForPool(treeObj);
		resultList = paasAbstractService.queryMemcacheExamples(treeObj);
		return "memcacheResourcePool";
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
		if (PaasConstant.CAAS_EXAMPLE.equals(node_type)) {
			treeObj.setEntity_id(entity_id);
		} else if (PaasConstant.CAAS_BUSI.equals(node_type)) {
			treeObj.setParent_id(id);
		} else if (PaasConstant.CAAS_TYPE.equals(node_type)) {
			PaasTreeObj treeObj1 = new PaasTreeObj();
			treeObj1.setParent_id(id);
			List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj1);
			if (treeList != null && treeList.size() > 0) {
				treeObj1 = treeList.get(0);
				treeObj.setParent_id(treeObj1.getId());
			}
		} else if (PaasConstant.CAAS.equals(node_type)) {
			PaasTreeObj treeObj1 = new PaasTreeObj();
			treeObj1.setParent_id(id);
			List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj1);
			if (treeList != null && treeList.size() > 0) {
				treeObj1 = treeList.get(0);
				PaasTreeObj treeObj2 = new PaasTreeObj();
				treeObj2.setParent_id(treeObj1.getId());
				List<PaasTreeObj> treeList1 = paasTreeService.queryTreeNodeByObj(treeObj2);
				if (treeList1 != null && treeList1.size() > 0) {
					treeObj2 = treeList1.get(0);
					treeObj.setParent_id(treeObj2.getId());
				}
			}
		}
		treeObj.setNode_type(node_type);
		treeObj.setFlag(flag);
		try {
			fusionCharts = paasAbstractService.showHitsAndMisses(treeObj);
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
	 * @Title: showhostInfo
	 * @Description: 主机摘要
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-3 下午7:42:12
	 */
	public String showhostInfo() {
		if (hostObj == null) {
			hostObj = new PaasHostInfoObj();
		}
		hostObj.setId(entity_id);
		hostObj = paasHostInfoService.queryByObj(hostObj);
		return "showHost";
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

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
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

	public List getMemcacheList() {
		return memcacheList;
	}

	public void setMemcacheList(List memcacheList) {
		this.memcacheList = memcacheList;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

}
