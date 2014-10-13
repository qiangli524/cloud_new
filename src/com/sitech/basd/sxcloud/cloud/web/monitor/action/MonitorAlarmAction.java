package com.sitech.basd.sxcloud.cloud.web.monitor.action;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.springframework.beans.factory.annotation.Autowired;

import util.DomainUtil;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmLocationObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.service.alarm.TbCloud2MonitorAlarmService;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.monitor.MonitorService;
import com.sitech.basd.sxcloud.cloud.web.monitor.form.MonitorAlarmForm;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.servlet.PrintWriterOut;

import dao.resourceV.ResourceViewDao;
import dao.resourceV.ResourceViewObj;

public class MonitorAlarmAction extends BaseAction {

	private String cateNum; // 柱状图显示告警时判断告警级别
	private MonitorAlarmForm theForm;
	private MonitorAlarmLocationObj monitorAlarmLocationObj;
	private String eventTypeTemp;

	public MonitorAlarmForm getTheForm() {
		return theForm;
	}

	public void setTheForm(MonitorAlarmForm theForm) {
		this.theForm = theForm;
	}

	@Autowired
	private TbCloud2MonitorAlarmService alarmService;
	
	@Autowired
	private ResourceViewDao resourceViewDao; 

	/**
	 * @Title:查询监控告警管理的的信息
	 * @Copyright: Copyright (c) 20120323
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listMonitorAlarm() {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		// 临时查询条件 EVENT_TYPE add by lizqd 20140912
		eventTypeTemp = theForm.getEVENT_TYPE();
		MonitorAlarmObj obj = new MonitorAlarmObj();
		/**
		 * 根据级别过滤告警信息
		 */
		HttpServletRequest request = Struts2Utils.getRequest();
		String evenLocation = request.getParameter("alarmIp");
		if (evenLocation != null && !evenLocation.equals("")) {
			theForm.setEVENT_LOCATION(evenLocation);
		}
		TbGlobalConfigObj levelObj = new TbGlobalConfigObj();
		levelObj.setKEY("EVENT_LEVEL");
		List<TbGlobalConfigObj> levelList = tbGlobalConfigService.queryForListByObj(levelObj);
		if (levelList != null && levelList.size() == 1) {
			levelObj = levelList.get(0);
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(levelObj.getVALUE());
			buffer.append(")");
			obj.setFilterlevel(buffer.toString());
		}
		/**
		 * 根据类型过滤告警信息
		 */
		TbGlobalConfigObj typeObj = new TbGlobalConfigObj();
		typeObj.setKEY("EVENT_TYPE");
		List<TbGlobalConfigObj> typeList = tbGlobalConfigService.queryForListByObj(typeObj);
		if (typeList != null && typeList.size() == 1) {
			typeObj = typeList.get(0);
			StringBuffer typeBuffer = new StringBuffer();
			typeBuffer.append("(");
			typeBuffer.append(typeObj.getVALUE());
			typeBuffer.append(")");
			obj.setFiltertype(typeBuffer.toString());
		}
		if (theForm.getCONTENT() != null && !"".equals(theForm.getCONTENT())) {
			obj.setCONTENT(theForm.getCONTENT().trim());
		}
		if (theForm.getTITILE() != null && !"".equals(theForm.getTITILE())) {
			obj.setTITILE(theForm.getTITILE().trim());
		}
		if (theForm.getEVENT_TYPE() != null && !"".equals(theForm.getEVENT_TYPE())) {
			obj.setEVENT_TYPE(theForm.getEVENT_TYPE());
		}
		if (cateNum != null && !"".equals(cateNum)) {
			obj.setEVENT_LEVEL(cateNum);
			theForm.setEVENT_LEVEL(cateNum);
		}
		if (theForm.getEVENT_LEVEL() != null && !"".equals(theForm.getEVENT_LEVEL())) {
			obj.setEVENT_LEVEL(theForm.getEVENT_LEVEL());
		}
		if (theForm.getEVENT_STAT() != null && !"".equals(theForm.getEVENT_STAT())) {
			obj.setEVENT_STAT(theForm.getEVENT_STAT());
		}
		if (theForm.getALARM_TIME() != null && !"".equals(theForm.getALARM_TIME())) {
			obj.setALARM_TIME(theForm.getALARM_TIME());
		}
		if (theForm.getINS_DATE() != null && !"".equals(theForm.getINS_DATE())) {
			obj.setINS_DATE(theForm.getINS_DATE());
		}
		if (theForm.getEVENT_LOCATION() != null && !"".equals(theForm.getEVENT_LOCATION())) {
			obj.setEVENT_LOCATION(theForm.getEVENT_LOCATION());
		}
		//添加告警系统参数 add by lizqd 20140904
		if (theForm.getEVENT_SYS() != null && !"".equals(theForm.getEVENT_SYS())) {
			obj.setEVENT_SYS(theForm.getEVENT_SYS());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = monitorService.queryAlarmForListByObj(obj);
		theForm.setResultList(resultList);
		return "listMonitorAlarm";
	}
	
	/**
	 * 首页展示的告警列表
	 * @Title: listMonitorAlarm_index
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-6 上午9:45:02
	 */
	public String listMonitorAlarm_index() {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		MonitorAlarmObj obj = new MonitorAlarmObj();

		/**
		 * 只展示未处理的
		 */
		obj.setEVENT_STAT("0");
		String loginName = request.getSession().getAttribute("account").toString();
		String userid = session.get("id").toString();
		String domain = session.get(Constant.USER_DOMAIN).toString();
		obj.setDomain(domain);
		List resultList = monitorService.queryAlarmForListByObj_index(obj, loginName, userid);
		theForm.setResultList(resultList);
		return "listMonitorAlarm_index";
	}

	public String listMonitorAlarmChart() {
		MonitorAlarmObj obj = new MonitorAlarmObj();

		/**
		 * 查询条件
		 */
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		/**
		 * 根据级别过滤告警信息
		 */
		if(eventTypeTemp != null && !"".equals(eventTypeTemp)){
			theForm.setEVENT_TYPE(eventTypeTemp);
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String evenLocation = request.getParameter("alarmIp");
		if (evenLocation != null && !evenLocation.equals("")) {
			theForm.setEVENT_LOCATION(evenLocation);
		}
		TbGlobalConfigObj levelObj = new TbGlobalConfigObj();
		levelObj.setKEY("EVENT_LEVEL");
		List<TbGlobalConfigObj> levelList = tbGlobalConfigService.queryForListByObj(levelObj);
		if (levelList != null && levelList.size() == 1) {
			levelObj = levelList.get(0);
			StringBuffer buffer = new StringBuffer();
			buffer.append("(");
			buffer.append(levelObj.getVALUE());
			buffer.append(")");
			obj.setFilterlevel(buffer.toString());
		}
		/**
		 * 根据类型过滤告警信息
		 */
		TbGlobalConfigObj typeObj = new TbGlobalConfigObj();
		typeObj.setKEY("EVENT_TYPE");
		List<TbGlobalConfigObj> typeList = tbGlobalConfigService.queryForListByObj(typeObj);
		if (typeList != null && typeList.size() == 1) {
			typeObj = typeList.get(0);
			StringBuffer typeBuffer = new StringBuffer();
			typeBuffer.append("(");
			typeBuffer.append(typeObj.getVALUE());
			typeBuffer.append(")");
			obj.setFiltertype(typeBuffer.toString());
		}
		if (theForm.getCONTENT() != null && !"".equals(theForm.getCONTENT())) {
			obj.setCONTENT(theForm.getCONTENT().trim());
		}
		if (theForm.getTITILE() != null && !"".equals(theForm.getTITILE())) {
			obj.setTITILE(theForm.getTITILE().trim());
		}
		if (theForm.getEVENT_TYPE() != null && !"".equals(theForm.getEVENT_TYPE())) {
			obj.setEVENT_TYPE(theForm.getEVENT_TYPE());
		}
		if (cateNum != null && !"".equals(cateNum)) {
			obj.setEVENT_LEVEL(cateNum);
			theForm.setEVENT_LEVEL(cateNum);
		}
		if (theForm.getEVENT_LEVEL() != null && !"".equals(theForm.getEVENT_LEVEL())) {
			obj.setEVENT_LEVEL(theForm.getEVENT_LEVEL());
		}
		if (theForm.getEVENT_STAT() != null && !"".equals(theForm.getEVENT_STAT())) {
			obj.setEVENT_STAT(theForm.getEVENT_STAT());
		}
		if (theForm.getALARM_TIME() != null && !"".equals(theForm.getALARM_TIME())) {
			obj.setALARM_TIME(theForm.getALARM_TIME());
		}
		if (theForm.getINS_DATE() != null && !"".equals(theForm.getINS_DATE())) {
			obj.setINS_DATE(theForm.getINS_DATE());
		}
		if (theForm.getEVENT_LOCATION() != null && !"".equals(theForm.getEVENT_LOCATION())) {
			obj.setEVENT_LOCATION(theForm.getEVENT_LOCATION());
		}
		
		//添加告警系统参数 add by lizqd 20140904
		if (theForm.getEVENT_SYS() != null && !"".equals(theForm.getEVENT_SYS())) {
			obj.setEVENT_SYS(theForm.getEVENT_SYS());
		}
		
		FusionCharts fChart = alarmService.queryMonitorAlarmChart(obj);
		String fusionChartsString = JacksonUtil.toJson(fChart);
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		// out.print(fusionChartsString);
		// out.close();

		PrintWriterOut.printWirter(response, fusionChartsString);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String dealMonitorAlarm_index() throws BaseException {
		this.dealMonitorAlarm();
		return this.listMonitorAlarm_index();
	}
	/**
	 * @Title:处理监控告警信息
	 * @Copyright: Copyright (c) 20120326
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String dealMonitorAlarm() throws BaseException {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		String str = theForm.getEVENT_ID();
		String[] eventId = str.split(",");
		for (int i = 0; i < eventId.length; i++) {
			MonitorAlarmObj obj = new MonitorAlarmObj();
			obj.setEVENT_ID(eventId[i]);
			List<MonitorAlarmObj> list = monitorService.queryAlarmForListByObj(obj);
			if (list != null && list.size() == 1) {
				MonitorAlarmObj tempObj = list.get(0);
				tempObj.setEVENT_ID(eventId[i]);
				ret = monitorService.updateByObj(tempObj);
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("处理监控告警");
			}
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "deal";
	}

	@SuppressWarnings("unchecked")
	public String hisMonitorAlarm_index() throws BaseException {
		this.hisMonitorAlarm();
		return this.listMonitorAlarm_index();
	}
	/**
	 * @Title:处理监控告警信息
	 * @Copyright: Copyright (c) 20120326
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String hisMonitorAlarm() throws BaseException {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		String str = theForm.getEVENT_ID();
		String[] eventId = str.split(",");
		for (int i = 0; i < eventId.length; i++) {
			MonitorAlarmObj obj = new MonitorAlarmObj();
			obj.setEVENT_ID(eventId[i]);
			List<MonitorAlarmObj> list = monitorService.queryAlarmForListByObj(obj);
			if (list != null && list.size() == 1) {
				MonitorAlarmObj tempObj = list.get(0);
				try {
					BeanUtils.copyProperties(obj, tempObj);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				ret = monitorService.insertByObj(tempObj);
				try {
					monitorService.publishOKToMQ(tempObj);
				} catch (RabbitMQException e) {
					e.printStackTrace();
					throw new BaseException("执行清除告警消息时出错!");
				}
			}
			ret = monitorService.deleteByObj(obj);
		}
		operObj.setOPERTYPE(1);
		operObj.setMESSAGE("将告警监控放入历史");
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "his";
	}

	/**
	 * @Title:查询监控告警管理的历史信息
	 * @Copyright: Copyright (c) 20120323
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String listHisMonitorAlarm() {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		// 临时查询条件 EVENT_TYPE add by lizqd 20140912
		eventTypeTemp = theForm.getEVENT_TYPE();
		MonitorAlarmObj obj = new MonitorAlarmObj();

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getCONTENT() != null && !"".equals(theForm.getCONTENT())) {
			obj.setCONTENT(theForm.getCONTENT().trim());
		}
		if (theForm.getTITILE() != null && !"".equals(theForm.getTITILE())) {
			obj.setTITILE(theForm.getTITILE().trim());
		}
		if (theForm.getEVENT_TYPE() != null && !"".equals(theForm.getEVENT_TYPE())) {
			obj.setEVENT_TYPE(theForm.getEVENT_TYPE());
		}
		//添加告警系统参数 add by lizqd 20140904
		if (theForm.getEVENT_SYS() != null && !"".equals(theForm.getEVENT_SYS())) {
			obj.setEVENT_SYS(theForm.getEVENT_SYS());
		}
		List resultList = monitorService.queryAlarmHisForList(obj);
		theForm.setResultList(resultList);
		return "listHis";
	}

	/**
	 * @Title:数据中心 各种告警的个数超链接
	 * @Copyright: Copyright (c) 20120820
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public String forResourcePage() {
		/**
		 * 根据级别过滤告警信息
		 */
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String event_level = request.getParameter("EVENT_LEVEL");
		MonitorAlarmObj obj = new MonitorAlarmObj();
		obj.setEVENT_LEVEL(event_level);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = monitorService.queryAlarmForListByObj(obj);
		theForm.setResultList(resultList);

		/**
		 * 展示jfreechart图表
		 */
		try {
			MonitorAlarmObj obj1 = new MonitorAlarmObj();
			Map<String, Long> temp = alarmService.getAlarms(obj1);
			double[][] data = new double[][] { { temp.get("0") == null ? 0 : temp.get("0") },
					{ temp.get("1") == null ? 0 : temp.get("1") },
					{ temp.get("2") == null ? 0 : temp.get("2") },
					{ temp.get("3") == null ? 0 : temp.get("3") } };
			String[] rowKeys = { "严重告警", "主要告警", "次要告警", "不确定告警" };
			String[] columnKeys = { "" };
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys,
					data);
			// 创建主题样式
			StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
			// 设置标题字体
			standardChartTheme.setExtraLargeFont(new Font("宋体", Font.BOLD, 20)); // 设置图例的字体
			standardChartTheme.setRegularFont(new Font("宋体", Font.BOLD, 15)); // 设置轴向的字体
			standardChartTheme.setLargeFont(new Font("宋体", Font.BOLD, 15)); // 应用主题样式
			ChartFactory.setChartTheme(standardChartTheme);
			JFreeChart chart = ChartFactory.createBarChart3D("告警统计图", "", "数量", dataset,
					PlotOrientation.VERTICAL, true, false, false);
			String filename = ServletUtilities.saveChartAsPNG(chart, 450, 300, null,
					request.getSession());
			String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;
			request.setAttribute("graphURL", graphURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listMonitorAlarm";
	}

	private Long[] number;

	public Long[] getNumber() {
		return number;
	}

	public void setNumber(Long[] number) {
		this.number = number;
	}

	/**
	 * @Title:查询警告类型的数量
	 * @Copyright: Copyright (c) 20120323
	 * @Company: si-tech
	 * @author siweichao
	 * @version 1.0
	 */
	public String count() {
		number = new Long[0];
		try {
			MonitorAlarmObj obj = new MonitorAlarmObj();
			Map<String, Long> temp = alarmService.getAlarms(obj);
			number = new Long[] { temp.get("0") == null ? 0 : temp.get("0"),
					temp.get("1") == null ? 0 : temp.get("1"),
					temp.get("2") == null ? 0 : temp.get("2"),
					temp.get("3") == null ? 0 : temp.get("3") };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "count";
	}

	private MonitorService monitorService;
	private TbGlobalConfigService tbGlobalConfigService;

	public void setTbGlobalConfigService(TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public String viewMonitorAlarm() {
		if (theForm == null) {
			theForm = new MonitorAlarmForm();
		}
		String location = request.getParameter("location");
		String levelstr = request.getParameter("level");
		String level = "";
		if ("4".equals(levelstr)) {
			level = "0";
		} else if ("3".equals(levelstr)) {
			level = "1";
		} else if ("2".equals(levelstr)) {
			level = "2";
		} else if ("1".equals(levelstr)) {
			level = "3";
		}
		MonitorAlarmObj obj = new MonitorAlarmObj();
		obj.setEVENT_LOCATION(location);
		obj.setEVENT_LEVEL(level);
		obj.setPagination(this.getPaginater().initPagination(request));
		@SuppressWarnings("unchecked")
		List<MonitorAlarmObj> list = monitorService.queryAlarmForListByObj(obj);
		if (list != null && list.size() > 0) {
			theForm.setResultList(list);
		}
		return "view";
	}

	public String getTodayAlarmNum() {
		MonitorAlarmObj obj = new MonitorAlarmObj();
		obj.setALARM_TIME(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date()));
		List<MonitorAlarmObj> resultList = monitorService.queryAlarmForListByObj(obj);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date())
				+ "=============" + resultList.size());
		int ret = 0;// 所有告警记录的报警次数之和
		for (MonitorAlarmObj mao : resultList) {
			ret += Integer.parseInt(mao.getEVENT_COUNT());
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(ret);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String hasAlarmPermission() {
		String loginName = request.getSession().getAttribute("account").toString();

		TbGlobalConfigObj permissionUsers = new TbGlobalConfigObj();
		permissionUsers.setKEY("alarmPermission");
		List<TbGlobalConfigObj> levelList = tbGlobalConfigService
				.queryForListByObj(permissionUsers);
		int ret = 0;
		if (levelList != null && levelList.size() == 1) {
			permissionUsers = levelList.get(0);
			String[] values = permissionUsers.getVALUE().split(",");
			for (String value : values) {
				if (loginName.equals(value)) {
					ret = 1;
					break;
				}
			}
		}

		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(ret);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title:getLocationInfo
	 * @Description:获取定位信息
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-8-9 下午08:56:47
	 */
	public String getLocationInfo(){
		MonitorAlarmLocationObj mlo = new MonitorAlarmLocationObj();
		String entityId = request.getParameter("entityId");
		String entityType = request.getParameter("entityType");
		// FIXME chenyu
		if(entityId!=null){
			try {
				ResourceViewObj obj = resourceViewDao.queryResourceViewObj(entityId);
				String connectId = obj.getConnect_id();
				String uuid = obj.getUuid();
				mlo.setConnectId(connectId);
				mlo.setUuid(uuid);
			} catch (SQLException e) {
				throw new RuntimeException("定位资源异常");
			}
		}
		//从session 中获取domain
		String dom = String.valueOf(DomainUtil.getSessionStringDomain());
		mlo.setDomain(dom);
		
		//EVENT_TYPE 0是应用告警、1是虚拟机告警、2是物理主机告警、3是机房告警，4进程告警     -1存储告警
		if("1".equals(entityType)){
			monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			monitorAlarmLocationObj = alarmService.queryLocationForVm(mlo);
			if(monitorAlarmLocationObj==null){
				monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			}
			monitorAlarmLocationObj.setType("vm");
		}else if("2".equals(entityType)){
			monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			monitorAlarmLocationObj = alarmService.queryLocationForHost(mlo);
			if(monitorAlarmLocationObj==null){
				monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			}
			monitorAlarmLocationObj.setType("host");
		}else if("-1".equals(entityType)){
			monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			monitorAlarmLocationObj = alarmService.queryLocationForStore(mlo);
			if(monitorAlarmLocationObj==null){
				monitorAlarmLocationObj= new MonitorAlarmLocationObj();
			}
			monitorAlarmLocationObj.setType("store");
		}
		return "locationInfo";
	}

	public String getCateNum() {
		return cateNum;
	}

	public void setCateNum(String cateNum) {
		this.cateNum = cateNum;
	}

	public MonitorAlarmLocationObj getMonitorAlarmLocationObj() {
		return monitorAlarmLocationObj;
	}

	public void setMonitorAlarmLocationObj(
			MonitorAlarmLocationObj monitorAlarmLocationObj) {
		this.monitorAlarmLocationObj = monitorAlarmLocationObj;
	}

	public String getEventTypeTemp() {
		return eventTypeTemp;
	}

	public void setEventTypeTemp(String eventTypeTemp) {
		this.eventTypeTemp = eventTypeTemp;
	}
}
