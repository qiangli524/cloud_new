package com.sitech.taokeeper.action;

import static common.toolkit.java.constant.EmptyObjectConstant.EMPTY_STRING;
import static common.toolkit.java.constant.SymbolConstant.COLON;
import static common.toolkit.java.constant.SymbolConstant.COMMA;
import static common.toolkit.java.constant.SymbolConstant.SQUARE_BRACKETS_LEFT;
import static common.toolkit.java.constant.SymbolConstant.SQUARE_BRACKETS_RIGHT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.taokeeper.dao.AlarmSettingsDAO;
import com.sitech.taokeeper.dao.ReportDAO;
import com.sitech.taokeeper.dao.SettingsDAO;
import com.sitech.taokeeper.dao.ZooKeeperClusterDAO;
import com.sitech.taokeeper.dao.ZookeeperNodesInfoDAO;
import com.sitech.taokeeper.model.AlarmSettings;
import com.sitech.taokeeper.model.TaoKeeperStat;
import com.sitech.taokeeper.model.TaokeeperNodesInfo;
import com.sitech.taokeeper.model.ZooKeeperCluster;
import common.toolkit.java.constant.EmptyObjectConstant;
import common.toolkit.java.entity.DateFormat;
import common.toolkit.java.exception.DaoException;
import common.toolkit.java.util.DateUtil;
import common.toolkit.java.util.ObjectUtil;
import common.toolkit.java.util.StringUtil;
import common.toolkit.java.util.collection.ListUtil;
import common.toolkit.java.util.io.ServletUtil;

/**
 * <p>
 * Title: BossProcessMonitorAction
 * </p>
 * <p>
 * Description: zookeeper监控
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author liudan_bj1
 * @version 1.0
 * @createtime 2014-8-4 下午4:41:14
 * 
 */
@Controller("taoKeeperMonitorAction")
@Scope("prototype")
public class TaoKeeperMonitorAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	protected ZooKeeperClusterDAO zooKeeperClusterDAO;
	@Autowired
	protected AlarmSettingsDAO alarmSettingsDAO;
	@Autowired
	protected SettingsDAO taoKeeperSettingsDAO;
	@Autowired
	protected ZookeeperNodesInfoDAO zookeeperNodesInfoDAO;
	@Autowired
	protected ReportDAO reportDAO;

	private String clusterName;
	private String serverListString;
	private String description;
	private String clusterId;
	private String handleMessage;
	private String maxDelayOfCheck;
	private String maxCpuUsage;
	private String maxMemoryUsage;
	private String maxLoad;
	private String wangwangList;
	private String phoneList;
	private String emailList;
	private String maxConnectionPerIp;
	private String maxWatchPerIp;
	private String dataDir;
	private String dataLogDir;
	private String maxDiskUsage;
	private String nodePathCheckRule;
	private String server;
	private String statDate;
	private String result;
	private Map<String, Object> zooKeeperSettingsPAGEResult;
	private ZooKeeperCluster obj;
	private AlarmSettings aobj;
	private List<ZooKeeperCluster> zkClusterList;
	private String oper;

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public AlarmSettings getAobj() {
		return aobj;
	}

	public void setAobj(AlarmSettings aobj) {
		this.aobj = aobj;
	}

	public List<ZooKeeperCluster> getZkClusterList() {
		return zkClusterList;
	}

	public void setZkClusterList(List<ZooKeeperCluster> zkClusterList) {
		this.zkClusterList = zkClusterList;
	}

	public ZooKeeperCluster getObj() {
		return obj;
	}

	public void setObj(ZooKeeperCluster obj) {
		this.obj = obj;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getServerListString() {
		return serverListString;
	}

	public void setServerListString(String serverListString) {
		this.serverListString = serverListString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getHandleMessage() {
		return handleMessage;
	}

	public void setHandleMessage(String handleMessage) {
		this.handleMessage = handleMessage;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	/**
	 * @Title: listZookeeperCluster
	 * @Description: 查询zk集群列表
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-13 下午3:12:03
	 */
	public String listZookeeperCluster() {
		if (obj == null) {
			obj = new ZooKeeperCluster();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		zkClusterList = zooKeeperClusterDAO.queryClusterList(obj);
		return "listZookeeperCluster";
	}

	/**
	 * @Title: zooKeeperRegisterPAGE
	 * @Description: 至注册页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-13 下午2:34:54
	 */
	public String zooKeeperRegisterPAGE() {
		return "zooKeeperRegisterPAGE";
	}

	/**
	 * @Title: zooKeeperSettingsPAGE
	 * @Description: 至修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-13 下午4:51:01
	 */
	public String zooKeeperSettingsPAGE() {
		clusterId = StringUtil.defaultIfBlank(clusterId, 1 + EMPTY_STRING);
		try {
			// 查询集群信息
			obj = zooKeeperClusterDAO.getZooKeeperClusterByCulsterId(Integer.parseInt(clusterId));
			if (obj == null) {
				ServletUtil
						.writeToResponse(
								response,
								"目前还没有这样的ZK集群<a href='taoKeeperMonitor_zooKeeperRegisterPAGE.do'><font color='red'> 加入监控</font></a>");
				return null;
			}
			// 由于serverList格式问题，因为这里要特殊处理
			// String zooKeeperClusterServerList =
			// CollectionUtil.toString(obj.getServerList());
			/*
			 * zooKeeperSettingsPAGEResult = new HashMap<String, Object>();
			 * zooKeeperSettingsPAGEResult.put("zooKeeperCluster",
			 * zooKeeperCluster); zooKeeperSettingsPAGEResult.put("clusterId",
			 * clusterId);
			 * zooKeeperSettingsPAGEResult.put("zooKeeperClusterServerList",
			 * zooKeeperClusterServerList);
			 * zooKeeperSettingsPAGEResult.put("handleMessage", handleMessage);
			 */
			// 查询该集群的告警配置信息
			aobj = alarmSettingsDAO.getAlarmSettingsByCulsterId(Integer.parseInt(clusterId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zooKeeperRegisterPAGE";
	}

	/**
	 * 注意，这里更新完数据库后，还要更新缓存。
	 */
	public String updateZooKeeperSettingsHandle() {
		try {
			if (StringUtil.isBlank(clusterId))
				throw new Exception("clusterId 不能为空");
			obj.setClusterId(Integer.parseInt(clusterId));
			// 密码加密
			obj.setSsh_passwd(EncryptUtil.encode(obj.getSsh_passwd().trim()));
			if (!StringUtil.isBlank(obj.getServerListStr())) {
				obj.setServerList(ListUtil.parseList(
						obj.getServerListStr().replace(SQUARE_BRACKETS_LEFT, EMPTY_STRING)
								.replace(SQUARE_BRACKETS_RIGHT, EMPTY_STRING), COMMA));
			}
			// 进行ZK集群信息修改
			String handleMessage = null;
			if (zooKeeperClusterDAO.updateZooKeeperSettingsByClusterId(obj)) {
				handleMessage = "[Update Success], and update cache success.";
			} else {
				handleMessage = "Update Fail";
				LOG.warn("对zooKeeper集群信息更新失败-" + obj);
				result = "-1";
			}
			// 进行告警配置信息修改
			aobj.setClusterId(Integer.parseInt(clusterId));
			alarmSettingsDAO.updateAlarmSettingsByClusterId(aobj);
			result = "1";
			// response.sendRedirect("taoKeeperMonitor_zooKeeperSettingsPAGE.do?clusterId="
			// + clusterId + "&handleMessage=" + handleMessage);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 注册ZooKeeper集群, 并且自动完成缓存更新，插入一个默认报警设置
	 */
	public String registerZooKeeperHandle() {
		try {
			ZooKeeperCluster zooKeeperCluster = new ZooKeeperCluster();
			zooKeeperCluster.setClusterName(obj.getClusterName());
			zooKeeperCluster.setDescription(obj.getDescription());
			if (!StringUtil.isBlank(obj.getServerListStr())) {
				zooKeeperCluster.setServerList(ListUtil.parseList(
						obj.getServerListStr().replace(SQUARE_BRACKETS_LEFT, EMPTY_STRING)
								.replace(SQUARE_BRACKETS_RIGHT, EMPTY_STRING), COMMA));
			}
			zooKeeperCluster.setSsh_port(obj.getSsh_port());
			zooKeeperCluster.setSsh_passwd(EncryptUtil.encode(obj.getSsh_passwd().trim()));
			zooKeeperCluster.setSsh_username(obj.getSsh_username());
			// 进行集群注册,成功返回正数,失败返回负数
			// String handleMessage = null;
			int clusterId = zooKeeperClusterDAO.addZooKeeper(zooKeeperCluster);
			if (0 < clusterId) {
				LOG.warn("完成zooKeeper集群添加：" + zooKeeperCluster);
				// 同时加入报警
				aobj.setClusterId(clusterId);
				alarmSettingsDAO.addAlarmSettings(aobj);
				handleMessage = "Register Success, and add a default alarm settings for you.";
				result = "1";
			} else {
				handleMessage = "Register Fail";
				clusterId = -1;
				result = "-1";
			}
			// response.sendRedirect("zooKeeperMonitorAction_zooKeeperSettingsPAGE.action?clusterId="
			// + clusterId + "&handleMessage=" + handleMessage);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * @Title: deleteZKClusterAndAlarm
	 * @Description: 删除集群及告警配置信息
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-9-15 上午10:28:48
	 */
	public String deleteZKClusterAndAlarm() {
		if (obj == null || aobj == null) {
			obj = new ZooKeeperCluster();
			aobj = new AlarmSettings();
		}
		obj.setClusterId(Integer.parseInt(clusterId));
		aobj.setClusterId(Integer.parseInt(clusterId));
		try {
			zooKeeperClusterDAO.deleteZooKeeperCluster(obj);
			alarmSettingsDAO.deleteAlarmSettings(aobj);
			result = "1";
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		return "results";
	}

	public String showZooKeeperStatusPAGE() throws IOException {
		try {
			// 获取第一个集群
			List<ZooKeeperCluster> allZookeeperCluster = zooKeeperClusterDAO
					.getAllZooKeeperClusterIdAndName();
			if (StringUtil.isBlank(clusterId) && allZookeeperCluster != null
					&& allZookeeperCluster.size() > 0) {
				clusterId = allZookeeperCluster.get(0).getClusterId().intValue() + "";
			}
			ZooKeeperCluster zooKeeperCluster = null;
			List<TaokeeperNodesInfo> taokeeperNodesInfoList = new ArrayList<TaokeeperNodesInfo>(0);
			if (!StringUtil.isBlank(clusterId)) {
				zooKeeperCluster = zooKeeperClusterDAO.getZooKeeperClusterByCulsterId(Integer
						.parseInt(clusterId));
				taokeeperNodesInfoList = zookeeperNodesInfoDAO
						.getZookeeperNodesInfosByCulsterId(Integer.parseInt(clusterId));
			}
			request.setAttribute("taokeeperNodesInfoList", taokeeperNodesInfoList);
			request.setAttribute("zooKeeperCluster", zooKeeperCluster);
			request.setAttribute("allZookeeperCluster", allZookeeperCluster);
			return "zooKeeperStatusPAGE";

		} catch (NumberFormatException e) {
			LOG.error("不合法的clusterId：" + clusterId);
			ServletUtil.writeToResponse(response, "不合法的clusterId：" + clusterId);
			e.printStackTrace();
		} catch (DaoException e) {
			LOG.error("Error when handle db: " + e.getMessage());
			ServletUtil.writeToResponse(response, "Error when handle db: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error("Server error : " + e.getMessage());
			ServletUtil.writeToResponse(response, "Server error: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String showZooKeeperAlarmSettingsPAGE() {
		clusterId = StringUtil.defaultIfBlank(clusterId, 1 + EMPTY_STRING);
		// try {
		// Map<Integer, ZooKeeperCluster > zooKeeperClusterMap =
		// GlobalInstance.getAllZooKeeperCluster();
		// Map<Integer, AlarmSettings > alarmSettingsMap =
		// GlobalInstance.getAllAlarmSettings();
		// AlarmSettings alarmSettings =
		// GlobalInstance.getAlarmSettingsByClusterId( Integer.parseInt(
		// clusterId ) );
		// if( null == alarmSettings ){
		// alarmSettings = alarmSettingsDAO.getAlarmSettingsByCulsterId(
		// Integer.parseInt( clusterId) );
		// }
		// if( null == alarmSettings ){
		// ServletUtil.writeToResponse( response,
		// "目前还没有这样的ZK集群<a href='"+request.getContextPath()+"/taoKeeperMonitor_zooKeeperRegisterPAGE.do'><font color='red'> 加入监控</font></a>"
		// );
		// return null;
		// }
		// request.setAttribute( "alarmSettings", alarmSettings );
		// request.setAttribute( "alarmSettingsMap", alarmSettingsMap );
		// request.setAttribute("clusterId", clusterId );
		// request.setAttribute( "zooKeeperClusterMap", zooKeeperClusterMap );
		// request.setAttribute( "handleMessage", StringUtil.trimToEmpty(
		// handleMessage ) );
		// return "showZooKeeperAlarmSettingsPAGE";
		// } catch ( NumberFormatException e ) {
		// e.printStackTrace();
		// } catch ( DaoException e ) {
		// e.printStackTrace();
		// } catch ( Exception e ) {
		// e.printStackTrace();
		// }
		return null;
	}

	public void updateAlarmSettingsHandle() {
		try {
			if (StringUtil.isBlank(clusterId))
				throw new Exception("clusterId 不能为空");
			AlarmSettings alarmSettings = new AlarmSettings();
			alarmSettings.setClusterId(Integer.parseInt(clusterId));
			alarmSettings.setMaxDelayOfCheck(StringUtil.trimToEmpty(maxDelayOfCheck));
			alarmSettings.setMaxCpuUsage(StringUtil.trimToEmpty(maxCpuUsage));
			alarmSettings.setMaxMemoryUsage(StringUtil.trimToEmpty(maxMemoryUsage));
			alarmSettings.setMaxLoad(StringUtil.trimToEmpty(maxLoad));
			alarmSettings.setWangwangList(StringUtil.trimToEmpty(wangwangList));
			alarmSettings.setPhoneList(StringUtil.trimToEmpty(phoneList));
			alarmSettings.setEmailList(StringUtil.trimToEmpty(emailList));
			alarmSettings.setMaxConnectionPerIp(StringUtil.trimToEmpty(maxConnectionPerIp));
			alarmSettings.setMaxWatchPerIp(StringUtil.trimToEmpty(maxWatchPerIp));
			alarmSettings.setDataDir(StringUtil.trimToEmpty(dataDir));
			alarmSettings.setDataLogDir(StringUtil.trimToEmpty(dataLogDir));
			alarmSettings.setMaxDiskUsage(StringUtil.trimToEmpty(maxDiskUsage));
			alarmSettings.setNodePathCheckRule(StringUtil.trimToEmpty(nodePathCheckRule));
			// 进行Update
			String handleMessage = null;
			// if( alarmSettingsDAO.updateAlarmSettingsByClusterId(
			// alarmSettings ) ){
			// handleMessage = "Update Success";
			// }else{
			// handleMessage = "Update Fail";
			// }
			response.sendRedirect(request.getContextPath() + "/"
					+ "taoKeeperMonitor_alarmSettingsPAGE&clusterId=" + clusterId
					+ "&handleMessage=" + handleMessage);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reportPageData() {
		try {
			clusterId = StringUtil.defaultIfBlank(clusterId, 1 + EMPTY_STRING);
			statDate = StringUtil.defaultIfBlank(statDate, DateUtil.getNowTime(DateFormat.Date));

			String contentOfReport = getReportContentOfServerConnectionByClusterIdAndServerAndStatDate(
					Integer.parseInt(clusterId), server, statDate);

			ServletUtil.writeToResponse(response, contentOfReport);

		} catch (NumberFormatException e) {
			LOG.error("不合法的clusterId：" + clusterId);
			ServletUtil.writeToResponse(response, "不合法的clusterId：" + clusterId);
			e.printStackTrace();
		} catch (DaoException e) {
			LOG.error("Error when handle db: " + e.getMessage());
			ServletUtil.writeToResponse(response, "Error when handle db: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error("Server error : " + e.getMessage());
			ServletUtil.writeToResponse(response, "Server error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String reportPAGE() {
		try {
			clusterId = StringUtil.defaultIfBlank(clusterId, 1 + EMPTY_STRING);
			statDate = StringUtil.defaultIfBlank(statDate, DateUtil.getNowTime(DateFormat.Date));
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("clusterId", clusterId);
			model.put("server", server);
			model.put("statDate", statDate);
			request.setAttribute("result", model);
			return "reportPAGE";
		} catch (NumberFormatException e) {
			LOG.error("不合法的clusterId：" + clusterId);
			ServletUtil.writeToResponse(response, "不合法的clusterId：" + clusterId);
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error("Server error : " + e.getMessage());
			ServletUtil.writeToResponse(response, "Server error: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	private String getReportContentOfServerConnectionByClusterIdAndServerAndStatDate(int clusterId,
			String server, String statDate) throws Exception {
		if (StringUtil.isBlank(server))
			return EmptyObjectConstant.EMPTY_STRING;
		List<TaoKeeperStat> taoKeeperStatList;
		try {
			taoKeeperStatList = reportDAO.queryTaoKeeperStatByClusterIdAndServerAndStatDate(
					clusterId, server, statDate);
		} catch (DaoException e) {
			throw new Exception("Error when get stat," + e.getMessage(), e.getCause());
		}
		// ////////////////////////////////////////////////////////////////////////
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setYaxisname("单位：个");
		chart.setXaxisname("时间");
		chart.setCaption("zookeeper节点监控");
		chart.setShowvalues("0");// 是否显示值，1显示，0不显示，默认显示
		chart.setDecimals("2");
		chart.setPlotGradientColor(""); // 渐变色
		chart.setThousandseparator(",");// 千分位的分隔符
		chart.setBgcolor("FFFFFF,FFFFFF");// 面板背景色
		chart.setShowborder("0");
		chart.setShowNames("0");// 是否显示x横坐标值
		chart.setCanvasbordercolor("E6E6E6");// 画布四边的边框颜色
		chart.setAnchorRadius("2");// 折线节点的半径大小
		chart.setLinethickness("2");// 折线的厚度
		// chart.setLabelDisplay("none");
		chart.setDivintervalhints("0, 10, 20, 30,40,50,60,80,100");
		chart.setPalette("3");
		chart.setBtnResetChartTitle("恢复");
		chart.setBtnResetChartTooltext("恢复");
		chart.setBtnSwitchToPinModeTitle("Pin模式");
		chart.setBtnSwitchToPinModeTooltext("Pin模式");
		chart.setBtnSwitchtoZoomModeTitle("缩放模式");
		chart.setBtnSwitchToZoomModeTooltext("缩放模式");
		chart.setBtnZoomOutTitle("上一步");
		chart.setBtnZoomOutTooltext("上一步");
		List<Dataset> dataset = new ArrayList<Dataset>();
		List<Categories> categoriesList = new ArrayList<Categories>();
		List<Category> cList = new ArrayList<Category>();// 查询所有的时间点，取limit值
		Categories categories = new Categories();
		categories.setCategory(cList);
		categoriesList.add(categories);
		Dataset watchDataset = new Dataset();
		List<Data> watchDataList = new ArrayList<Data>();
		watchDataset.setSeriesname("订阅者数量");
		watchDataset.setData(watchDataList);
		dataset.add(watchDataset);
		Dataset connsDataset = new Dataset();
		List<Data> connsDataList = new ArrayList<Data>();
		connsDataset.setSeriesname("客户端连接数");
		connsDataset.setData(connsDataList);
		dataset.add(connsDataset);

		Dataset znodesDataset = new Dataset();
		List<Data> znodesDataList = new ArrayList<Data>();
		znodesDataset.setSeriesname("ZNode数量");
		znodesDataset.setData(znodesDataList);
		dataset.add(znodesDataset);

		// ////////////////////////////////////////////////////////////////////////
		for (TaoKeeperStat taoKeeperStat : taoKeeperStatList) {
			try {
				if (ObjectUtil.isBlank(taoKeeperStat)
						|| ObjectUtil.isBlank(taoKeeperStat.getWatches(),
								taoKeeperStat.getConnections()))
					continue;
				String statDateTime = StringUtil.trimToEmpty(taoKeeperStat.getStatDateTime());
				String xValue = StringUtil.replaceAll(
						statDateTime.substring(0, statDateTime.indexOf(".")),
						EmptyObjectConstant.EMPTY_STRING, COLON);
				int watchers = taoKeeperStat.getWatches();
				int conns = taoKeeperStat.getConnections();
				long nodeConut = taoKeeperStat.getNodeCount();
				if (StringUtil.trimToEmpty(xValue).startsWith("0"))
					xValue = xValue.replaceFirst("0", EmptyObjectConstant.EMPTY_STRING);
				Category category = new Category();
				category.setLabel(xValue);
				cList.add(category);
				// 订阅者数量
				Data watchersDt = new Data();
				watchersDt.setValue(watchers + "");
				watchDataList.add(watchersDt);
				// 客户端连接数
				Data connsDt = new Data();
				connsDt.setValue(conns + "");
				connsDataList.add(connsDt);
				// ZNode数量
				Data znodesDt = new Data();
				znodesDt.setValue(nodeConut + "");
				znodesDataList.add(znodesDt);
			} catch (Throwable e) {
				LOG.error("Error when parse:" + taoKeeperStat);
				e.printStackTrace();
			}

		}
		fusionCharts.setChart(chart);
		fusionCharts.setCategories(categoriesList);
		fusionCharts.setDataset(dataset);
		return JacksonUtil.toJson(fusionCharts);// 以Json字符串返回
	}

}
