package action.topN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.monitor.HadoopMonitorService;
import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.monitor.HadoopMonitorObj;
import domain.tree.HadoopTreeObj;

/**
 * <p>
 * Title: HadoopKpiTopNAction
 * </p>
 * <p>
 * Description: 服务节点监控指标监控值topN
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午7:24:35
 * 
 */
@SuppressWarnings("serial")
@Controller("hadoopKpiTopAction")
@Scope("prototype")
public class HadoopKpiTopNAction extends BaseAction {

	@Autowired
	private HadoopMonitorService hadoopMonitorService;

	@Autowired
	private HadoopTreeService hadoopTreeService;

	private HadoopTreeObj obj;

	private HadoopMonitorObj hadoopMonitorObj;

	private List<HadoopMonitorObj> kpiList;

	private List<HadoopTreeObj> serviceList;// 服务类型集合

	private List<HadoopMonitorObj> resultList;

	private int topnum;// N 值

	private String kpi_id;// kpi指标

	public HadoopTreeObj getObj() {
		return obj;
	}

	public void setObj(HadoopTreeObj obj) {
		this.obj = obj;
	}

	public HadoopMonitorObj getHadoopMonitorObj() {
		return hadoopMonitorObj;
	}

	public void setHadoopMonitorObj(HadoopMonitorObj hadoopMonitorObj) {
		this.hadoopMonitorObj = hadoopMonitorObj;
	}

	public List<HadoopMonitorObj> getKpiList() {
		return kpiList;
	}

	public void setKpiList(List<HadoopMonitorObj> kpiList) {
		this.kpiList = kpiList;
	}

	public List<HadoopMonitorObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopMonitorObj> resultList) {
		this.resultList = resultList;
	}

	public int getTopnum() {
		return topnum;
	}

	public void setTopnum(int topnum) {
		this.topnum = topnum;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public List<HadoopTreeObj> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<HadoopTreeObj> serviceList) {
		this.serviceList = serviceList;
	}

	/**
	 * @Title: goTopPage
	 * @Description: 进入到topN的展示页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-6 下午9:58:01
	 */
	public String goTopPage() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}
		List<String> list = new ArrayList<String>();
		if (HadoopConstant.serviceNode.equals(obj.getNode_type())) {// 如果是服务节点
			// 查询当前节点下主机的kpi集合（topN是节点下主机的topN，需要拿主机的kpi）
			serviceList = new ArrayList<HadoopTreeObj>();
		} else {// 如果是根节点
			// 查询所有服务节点集合
			HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
			hadoopTreeObj.setNode_type(HadoopConstant.serviceNode);
			serviceList = hadoopTreeService.queryForListByObj(hadoopTreeObj);

			// 转换服务类型
			for (HadoopTreeObj treeObj : serviceList) {
				treeObj = this.revertServiceType(treeObj);
			}
			if (serviceList.size() > 0) {
				obj.setId(serviceList.get(0).getId());
			}
		}
		list.add(obj.getId());
		hadoopMonitorObj.setUuidList(list);
		kpiList = hadoopMonitorService.queryHostKpiListByServiceNode(hadoopMonitorObj);
		resultList = new ArrayList<HadoopMonitorObj>();
		return "gotoppage";
	}

	/**
	 * @Title: revertServiceType
	 * @Description: 转化服务类型名称
	 * @param
	 * @return HadoopTreeObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-8 下午5:03:52
	 */
	private HadoopTreeObj revertServiceType(HadoopTreeObj treeObj) {
		if (HadoopConstant.nameNode.equals(treeObj.getService_type())) {
			treeObj.setService_type("nameNode");
		} else if (HadoopConstant.dataNode.equals(treeObj.getService_type())) {
			treeObj.setService_type("dataNode");
		} else if (HadoopConstant.journalNode.equals(treeObj.getService_type())) {
			treeObj.setService_type("journalNode");
		} else if (HadoopConstant.nodeManager.equals(treeObj.getService_type())) {
			treeObj.setService_type("nodeManager");
		} else if (HadoopConstant.reduceManager.equals(treeObj.getService_type())) {
			treeObj.setService_type("reduceManager");
		} else if (HadoopConstant.hmaster.equals(treeObj.getService_type())) {
			treeObj.setService_type("hmaster");
		} else if (HadoopConstant.regionServer.equals(treeObj.getService_type())) {
			treeObj.setService_type("regionServer");
		} else if (HadoopConstant.hbase_thirftServer.equals(treeObj.getService_type())) {
			treeObj.setService_type("hbase_thirftServer");
		} else if (HadoopConstant.znode.equals(treeObj.getService_type())) {
			treeObj.setService_type("znode");
		} else if (HadoopConstant.hive_thirftServer.equals(treeObj.getService_type())) {
			treeObj.setService_type("hive_thirftServer");
		} else if (HadoopConstant.impalaxx.equals(treeObj.getService_type())) {
			treeObj.setService_type("impalaxx");
		}
		return treeObj;
	}

	/**
	 * @Title: showTopN
	 * @Description: 展示topN报表
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:33:07
	 */
	public void showTopN() {
		Map<String, Object> paramMap = this.initParamMap();
		resultList = hadoopMonitorService.queryForLatestKpi(paramMap);
		for (HadoopMonitorObj monitorObj : resultList) {
			this.dealValue(monitorObj);
		}
		String chartXml = hadoopMonitorService.buildTopNFusionChart(resultList, "top" + topnum
				+ "报表");
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartXml);
		jo.put("resultList", resultList);
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: dealValue
	 * @Description: 处理值，保留两位小数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-16 下午3:10:40
	 */
	private void dealValue(HadoopMonitorObj monitorObj) {
		if (monitorObj.getKpi_value() != null) {
			String[] arr = monitorObj.getKpi_value().split("\\.");
			if (arr.length == 2) {
				String second = arr[1].substring(0, 2);
				String value = arr[0].concat(".").concat(second);
				monitorObj.setKpi_value(value);
			}
		}
	}

	/**
	 * @Title: initParamMap
	 * @Description: 初始化参数map
	 * @param
	 * @return Map<String,Object>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:45:10
	 */
	private Map<String, Object> initParamMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (topnum == 0) {
			topnum = 5;// 默认top5
		}
		paramMap.put("topnum", topnum);
		paramMap.put("kpi_id", kpi_id);
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}
		paramMap.put("serviceNodeType", hadoopMonitorObj.getHost_name());
		return paramMap;
	}

	/**
	 * @Title: queryKpiList
	 * @Description: 根据服务查询kpi
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-9 上午9:56:27
	 */
	public void queryKpiList() {
		if (obj == null) {
			obj = new HadoopTreeObj();
		}
		if (hadoopMonitorObj == null) {
			hadoopMonitorObj = new HadoopMonitorObj();
		}
		List<String> list = new ArrayList<String>();
		list.add(obj.getId());
		hadoopMonitorObj.setUuidList(list);
		kpiList = hadoopMonitorService.queryHostKpiListByServiceNode(hadoopMonitorObj);
		try {
			response.setCharacterEncoding("UTF-8");
			JSONObject jo = new JSONObject();
			jo.put("kpiList", kpiList);
			// PrintWriter pw = response.getWriter();
			// pw.print(jo);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
