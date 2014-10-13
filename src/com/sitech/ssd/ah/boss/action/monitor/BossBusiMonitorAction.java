package com.sitech.ssd.ah.boss.action.monitor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.boss.domain.common.CommonObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossBusiMonitorObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossTreeObj;
import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;
import com.sitech.ssd.ah.boss.service.monitor.BossBusiMonitorService;
import com.sitech.ssd.ah.boss.service.monitor.BossProcessMonitorService;
import com.sitech.ssd.ah.boss.service.procedure.BossProcedureService;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("unchecked")
@Controller("bossBusiMonitorAction")
@Scope("prototype")
public class BossBusiMonitorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(BossBusiMonitorAction.class);
	@Autowired
	private BossBusiMonitorService bossBusiMonitorService;
	@Autowired
	private BossProcessMonitorService bossProcessMonitorService;
	@Autowired
	private BossProcedureService bossProcedureService;
	BossBusiMonitorObj obj;
	MonitorCfgObj cfgObj;
	List<BossBusiMonitorObj> busiObjList;
	List<MonitorCfgObj> cfgObjList;
	private List BossTreeObjList;
	private BossTreeObj treeObj;
	private String uid;
	private String kpi;
	private String fusionChartsString;
	private Map<String, String> nctRatio;

	public String getFusionChartsString() {
		return fusionChartsString;
	}

	public void setFusionChartsString(String fusionChartsString) {
		this.fusionChartsString = fusionChartsString;
	}

	public List<MonitorCfgObj> getCfgObjList() {
		return cfgObjList;
	}

	public void setCfgObjList(List<MonitorCfgObj> cfgObjList) {
		this.cfgObjList = cfgObjList;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public BossBusiMonitorObj getObj() {
		return obj;
	}

	public void setObj(BossBusiMonitorObj obj) {
		this.obj = obj;
	}

	public List<BossBusiMonitorObj> getBusiObjList() {
		return busiObjList;
	}

	public void setBusiObjList(List<BossBusiMonitorObj> busiObjList) {
		this.busiObjList = busiObjList;
	}

	public MonitorCfgObj getCfgObj() {
		return cfgObj;
	}

	public void setCfgObj(MonitorCfgObj cfgObj) {
		this.cfgObj = cfgObj;
	}

	public Map<String, String> getNctRatio() {
		return nctRatio;
	}

	public void setNctRatio(Map<String, String> nctRatio) {
		this.nctRatio = nctRatio;
	}

	/**
	 * @Title: tabs
	 * @Description: 转到tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午1:07:05
	 */
	public String tabs() {
		return "portTabs";
	}

	/**
	 * @Title: queryMonitorDataList
	 * @Description:当前数据: 查询监控数据，精确到池
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 上午11:52:33
	 */
	public String queryMonitorDataListOfPort() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		CommonObj comObj = new CommonObj();
		comObj.setType("2");
		List<CommonObj> clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
		if ("PM-01-01-001-01".equals(kpi)) {// 话务量
			comObj.setType("4");
		} else if ("PM-01-01-001-03".equals(kpi)) {// 错误日志
			comObj.setType("6");
		} else if ("PM-01-01-001-04".equals(kpi)) {// 流量查询
			comObj.setType("7");
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口收发
			comObj.setType("5");
		} else if ("PM-01-01-001-06".equals(kpi)) {// 提醒服务
			comObj.setType("8");
		} else {
			comObj.setType("3");
		}
		List<CommonObj> poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
		obj.setClusterList(clusterList);
		// 默认显示列表第一个
		if (obj.getCLUSTER_NAME() == null && obj.getPOOL_NAME() == null) {
			obj.setCLUSTER_NAME(clusterList.get(0).getNodeId());
			obj.setPOOL_NAME(poolList.get(0).getNodeId());
		}
		if (!"NCT".equals(obj.getPOOL_NAME())) {
			obj.setONLINE_FLAG("");
		}
		obj.setPoolList(poolList);
		obj.setBUSI_KPI(kpi);
		// 默认：当天
		obj.setSTART_TIME(TimeformatCommon.GetCurrentSysTime("yyyyMMdd") + "00");
		obj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyyMMdd") + "23");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		busiObjList = bossBusiMonitorService.queryListForPort(obj);
		// 新添需求：按天统计在线、离线量
		// obj.setTIME_SEQ(TimeformatCommon.GetCurrentSysTime("yyyyMMdd"));
		// nctRatio = bossBusiMonitorService.queryNctRatio(obj);

		if ("PM-01-01-001-01".equals(kpi)) {// 话务监控
			return "hwList";
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口监控
			return "portList";
		} else {
			return "portList";// 流量监控、提醒服务监控
		}
	}

	/**
	 * @Title: showNctRatio
	 * @Description: 获取话务量在线与离线的比例
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 上午11:02:30
	 */
	public void showNctRatio() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		FusionCharts fusionCharts = new FusionCharts();
		try {
			obj.setTIME_SEQ(TimeformatCommon.GetCurrentSysTime("yyyyMMdd"));
			fusionCharts = bossBusiMonitorService.queryNctRatioChartJSONF(obj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		printJson(fusionChartsString);
	}

	/**
	 * @Title: showHosiNctRatio
	 * @Description: 获取历史话务量在线与离线的比例
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-20 下午1:10:57
	 */
	public void showHosiNctRatio() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		FusionCharts fusionCharts = new FusionCharts();
		try {
			// obj.setTIME_SEQ(TimeformatCommon.GetCurrentSysTime("yyyyMMdd"));
			obj.setSTART_TIME(obj.getSTART_TIME().replace("-", ""));
			obj.setEND_TIME(obj.getEND_TIME().replace("-", ""));
			fusionCharts = bossBusiMonitorService.queryHosiNctRatioChartJSONF(obj);
		} catch (Exception e) {
			LOG.error("查询失败！" + e.getMessage() + e, e);
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		printJson(fusionChartsString);
	}

	private synchronized void printJson(String... params) {
		response.setCharacterEncoding("UTF-8");
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				PrintWriterOut.printWirter(response, params[i]);
			}
		}
	}

	/**
	 * @Title: queryChartJSONOfPort
	 * @Description: 当前池子数据的图像展示
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-4 下午2:47:23
	 */
	public void queryChartJSONOfPort() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if ("".equals(obj.getNum()) || obj.getNum() == null) {
			obj.setNum(100);
		}
		if (uid != null && !"".equals(uid)) {
			ProcedureObj pobj = new ProcedureObj();
			pobj.setUid(uid);
			pobj = bossProcedureService.queryBossProcedureByUid(pobj);
			obj.setPROGRESS_NODE(pobj.getProgram_name());
		}
		if (!"NCT".equals(obj.getPOOL_NAME())) {
			obj.setONLINE_FLAG("");
		}
		// 当天
		obj.setSTART_TIME(TimeformatCommon.GetCurrentSysTime("yyyyMMdd") + "00");
		obj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyyMMdd") + "23");
		logger.info("开始获取池子历史Json字符串");
		String chartJson = bossBusiMonitorService.queryChartJSONOfPort(obj);
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	/**
	 * @Title: queryProcessDataDesc
	 * @Description: 当前数据：池子下进程端口明细
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午2:18:44
	 */
	public String queryProcessDataDescOfPort() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		obj.setBUSI_KPI(kpi);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		busiObjList = bossBusiMonitorService.queryListForPortProcess(obj);
		if ("PM-01-01-001-01".equals(kpi)) {// 话务监控
			return "hwDesc";
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口监控
			return "portDesc";
		} else {
			return "portDesc";
		}
	}

	/**
	 * @Title: queryHosiDataList
	 * @Description: 历史：查询集群下池子或池子下的进程的端口、话务、流量、提醒数据
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午7:42:17
	 */
	public String queryHosiPortDataList() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if (!"NCT".equals(obj.getPOOL_NAME())) {
			obj.setONLINE_FLAG("");
		}
		// 时间处理
		this.timeJudge(obj);
		CommonObj comObj = new CommonObj();
		comObj.setType("2");
		List<CommonObj> clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
		if ("PM-01-01-001-01".equals(kpi)) {// 话务量
			comObj.setType("4");
		} else if ("PM-01-01-001-03".equals(kpi)) {// 错误日志
			comObj.setType("6");
		} else if ("PM-01-01-001-04".equals(kpi)) {// 流量查询
			comObj.setType("7");
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口收发
			comObj.setType("5");
		} else if ("PM-01-01-001-06".equals(kpi)) {// 提醒服务
			comObj.setType("8");
		} else {
			comObj.setType("3");
		}
		List<CommonObj> poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
		obj.setClusterList(clusterList);
		obj.setPoolList(poolList);
		obj.setBUSI_KPI(kpi);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		// 默认显示列表第一个
		if (obj.getCLUSTER_NAME() == null && obj.getPOOL_NAME() == null) {
			obj.setCLUSTER_NAME(clusterList.get(0).getNodeId());
			obj.setPOOL_NAME(poolList.get(0).getNodeId());
		}
		if (uid != null && !"".equals(uid)) {
			ProcedureObj pobj = new ProcedureObj();
			pobj.setUid(uid);
			pobj = bossProcedureService.queryBossProcedureByUid(pobj);
			obj.setPROGRESS_NODE(pobj.getProgram_name());
			// 查询到进程
			// busiObjList =
			// bossBusiMonitorService.queryProcessMonitorListFromHosi(obj);
		} else {
			// 查询到池子
			busiObjList = bossBusiMonitorService.queryHosiListForPort(obj);
		}
		this.timeDispose();
		if ("PM-01-01-001-01".equals(kpi)) {// 话务监控
			return "hosiHWList";
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口监控
			return "hosiPortList";
		} else {
			return "hosiPortList";// 流量监控、提醒服务监控
		}
	}

	/**
	 * @Title: queryHosiListForPortDesc
	 * @Description: 历史： 池子的每一天内的详细
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 上午11:25:12
	 */
	public String queryHosiListForPortDesc() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		obj.setBUSI_KPI(kpi);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		busiObjList = bossBusiMonitorService.queryHosiListForPortDesc(obj);
		if ("PM-01-01-001-01".equals(kpi)) {// 话务监控
			return "hosiHWDescList";
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口监控
			return "hosiPortDescList";
		} else {
			return "hosiPortDescList";// 流量监控、提醒服务监控
		}
	}

	/**
	 * @Title: queryHosiChartJSONForPool
	 * @Description: 历史： 池子下端口数据收发折线图
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-2 下午9:50:26
	 */
	public void queryHosiChartJSONForPool() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if (!"NCT".equals(obj.getPOOL_NAME())) {
			obj.setONLINE_FLAG("");
		}
		this.timeJudge(obj);
		if ("".equals(obj.getNum()) || obj.getNum() == null) {
			obj.setNum(100);
		}
		logger.info("开始获取池子历史Json字符串");
		String chartJson = bossBusiMonitorService.queryHosiChartJSONForPool(obj);
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	/**
	 * @Title: hwTabs
	 * @Description: 跳转到话单量tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-3 下午6:53:45
	 */
	public String hwTabs() {
		return "hwTabs";
	}

	/**
	 * @Title: fluxTabs
	 * @Description: 跳转到流量查询tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 下午5:38:24
	 */
	public String fluxTabs() {
		return "fluxTabs";
	}

	/**
	 * @Title: remindTabs
	 * @Description: 跳转到提醒服务tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 上午10:09:13
	 */
	public String remindTabs() {
		return "remindTabs";
	}

	/**
	 * @Title: listErrorLog
	 * @Description: 错误日志列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:36:18
	 */
	public String queryListForErrorLog() {
		kpi = "PM-01-01-001-03";
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if (!"NCT".equals(obj.getPOOL_NAME())) {
			obj.setONLINE_FLAG("");
		}
		// 时间处理
		this.timeJudge(obj);
		CommonObj comObj = new CommonObj();
		comObj.setType("2");
		List<CommonObj> clusterList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询集群
		if ("PM-01-01-001-01".equals(kpi)) {// 话务量
			comObj.setType("4");
		} else if ("PM-01-01-001-03".equals(kpi)) {// 错误日志
			comObj.setType("6");
		} else if ("PM-01-01-001-04".equals(kpi)) {// 流量查询
			comObj.setType("7");
		} else if ("PM-01-01-001-05".equals(kpi)) {// 端口收发
			comObj.setType("5");
		} else if ("PM-01-01-001-06".equals(kpi)) {// 提醒服务
			comObj.setType("8");
		} else {
			comObj.setType("3");
		}
		List<CommonObj> poolList = bossProcessMonitorService.queryCommonObjList(comObj);// 查询程训池
		obj.setClusterList(clusterList);
		obj.setPoolList(poolList);
		obj.setBUSI_KPI(kpi);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		// 默认显示列表第一个
		if (obj.getCLUSTER_NAME() == null && obj.getPOOL_NAME() == null) {
			obj.setCLUSTER_NAME(clusterList.get(0).getNodeId());
			obj.setPOOL_NAME(poolList.get(0).getNodeId());
		}
		// 查询到池子
		busiObjList = bossBusiMonitorService.queryListForErrorLog(obj);
		this.timeDispose();
		return "listErrorLog";
	}

	/**
	 * @Title: queryHosiChartJSONForLog
	 * @Description: 返回错误日志折线图数据
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午2:38:23
	 */
	public void queryHosiChartJSONForLog() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		this.timeJudge(obj);
		if ("".equals(obj.getNum()) || obj.getNum() == null) {
			obj.setNum(100);
		}
		logger.info("开始获取错误日志Json字符串");
		String chartJson = bossBusiMonitorService.queryHosiChartJSONForLog(obj);
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	/**
	 * @Title: queryListForErrorLogDesc
	 * @Description: 池子下的进程错误日志详细
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午3:52:33
	 */
	public String queryListForErrorLogDesc() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		obj.setBUSI_KPI(kpi);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		busiObjList = bossBusiMonitorService.queryForErrorLogDesc(obj);
		return "errorLogDesc";
	}

	/**
	 * @Title: queryListForDir
	 * @Description: 查询待监控的目录和文件,并匹配最新的监控数据
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-9 下午7:14:49
	 */
	public String queryListForMonitorDir() {
		if (cfgObj == null) {
			cfgObj = new MonitorCfgObj();
		}
		if (StringUtils.isEmpty(cfgObj.getSTART_TIME())) {
			cfgObj.setSTART_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 00:00:00");
		}
		if (StringUtils.isEmpty(cfgObj.getEND_TIME())) {
			cfgObj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 23:59:59");
		}
		cfgObj.setKpiId("PM-01-01-001-02");
		cfgObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		cfgObjList = bossBusiMonitorService.queryListForMonitorDir(cfgObj);
		return "listMonitorDir";
	}

	/**
	 * @Title: queryListForDirDesc
	 * @Description: 匹配监控表，得到该目录的监控数据列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 上午10:02:18
	 */
	public String queryListForDirDesc() {
		boolean fs = false;
		boolean fe = false;
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if (StringUtils.isEmpty(obj.getSTART_TIME())) {
			obj.setSTART_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 00:00:00");
			fs = true;
		}
		if (StringUtils.isEmpty(obj.getEND_TIME())) {
			obj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 23:59:59");
			fe = true;
		}
		obj.setEQ_ID(cfgObj.getExtEqId() + "_" + cfgObj.getKpiCfgValue());
		obj.setBUSI_KPI("PM-01-01-001-02");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		busiObjList = bossBusiMonitorService.queryListForMonitorDirDesc(obj);
		if (fs) {
			obj.setSTART_TIME("");
		}
		if (fe) {
			obj.setEND_TIME("");
		}
		return "listDirDesc";
	}

	/**
	 * @Title: queryChartJSONForDirDesc
	 * @Description: 返回目录历史数据走势图数据
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 下午12:36:54
	 */
	public void queryChartJSONForDirDesc() {
		if (obj == null) {
			obj = new BossBusiMonitorObj();
		}
		if ("".equals(obj.getNum()) || obj.getNum() == null) {
			obj.setNum(100);
		}
		if (StringUtils.isEmpty(obj.getSTART_TIME())) {
			obj.setSTART_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 00:00:00");
		}
		if (StringUtils.isEmpty(obj.getEND_TIME())) {
			obj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyy-MM-dd") + " 23:59:59");
		}
		// obj.setHOST_IP(cfgObj.getHostIp());
		obj.setEQ_ID(cfgObj.getExtEqId() + "_" + cfgObj.getKpiCfgValue());
		obj.setBUSI_KPI("PM-01-01-001-02");
		String chartJson = bossBusiMonitorService.queryChartJSONForDirDesc(obj);
		JSONObject jo = new JSONObject();
		jo.put("chartXml", chartJson);
		this.printToScreen(jo);
	}

	/**
	 * @Title: processMonitorTree
	 * @Description: 得到进程监控树
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 下午6:35:35
	 */
	public String processMonitorTree() {
		return "list";
	}

	/**
	 * @Title: asyncForTree
	 * @Description: 展开树节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 下午6:37:24
	 */
	public String asyncForTree() {

		return "tree";
	}

	/**
	 * @Title: processTabs
	 * @Description: 跳转到进程监控的tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-10 下午6:17:25
	 */
	public String processTabs() {
		return "processTabs";
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

	/**
	 * @Title: timeJudge
	 * @Description: 处理时间
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 上午11:48:28
	 */
	private void timeJudge(BossBusiMonitorObj oo) {
		if (("".equals(obj.getSTART_TIME()) && "".equals(obj.getEND_TIME()))
				|| (obj.getSTART_TIME() == null && obj.getEND_TIME() == null)) {
			// 默认一个月内的
			java.util.Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			java.util.Date date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStart = sdf.format(date);// 上个月的今天
			java.util.Calendar c2 = Calendar.getInstance();
			java.util.Date date2 = c2.getTime();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String dateEnd = sdf2.format(date2);// 当前日期
			obj.setSTART_TIME(dateStart);
			obj.setEND_TIME(dateEnd);
		} else if ("".equals(obj.getSTART_TIME()) || obj.getSTART_TIME() == null) {
			java.util.Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			java.util.Date date = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dateStart = sdf.format(date);
			obj.setSTART_TIME(dateStart);
			String end_time = "";
			String[] arr2 = obj.getEND_TIME().split("-");
			for (String s : arr2) {
				end_time += s;
			}
			obj.setEND_TIME(end_time);
		} else if ("".equals(obj.getEND_TIME()) || obj.getEND_TIME() == null) {
			String start_time = "";
			String[] arr = obj.getSTART_TIME().split("-");
			for (String s : arr) {
				start_time += s;
			}
			obj.setSTART_TIME(start_time);
			obj.setEND_TIME(TimeformatCommon.GetCurrentSysTime("yyyyMMdd"));
		} else {
			// 去-
			String start_time = "";
			String end_time = "";
			String[] arr = obj.getSTART_TIME().split("-");
			for (String s : arr) {
				start_time += s;
			}
			obj.setSTART_TIME(start_time);
			String[] arr2 = obj.getEND_TIME().split("-");
			for (String s : arr2) {
				end_time += s;
			}
			obj.setEND_TIME(end_time);
		}
	}

	/**
	 * @Title: timeDispose
	 * @Description: 时间二次处理
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-5 下午1:37:51
	 */
	private void timeDispose() {
		// 加-
		String s = obj.getSTART_TIME();
		String start = s.substring(0, 4) + "-" + s.substring(4, 6) + "-" + s.substring(6, 8);
		obj.setSTART_TIME(start);
		String e = obj.getEND_TIME();
		String end = e.substring(0, 4) + "-" + e.substring(4, 6) + "-" + e.substring(6, 8);
		obj.setEND_TIME(end);
	}
}
