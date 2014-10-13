package com.sitech.basd.yicloud.web.scheduler.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.basd.yicloud.domain.scheduler.DefinitionObj;
import com.sitech.basd.yicloud.domain.scheduler.StrategyObj;
import com.sitech.basd.yicloud.domain.scheduler.TriggerRelationObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.scheduler.StrategyService;
import com.sitech.basd.yicloud.service.scheduler.TriggerDefService;
import com.sitech.basd.yicloud.web.scheduler.form.StrategyForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class StrategyAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private StrategyForm theForm;
	private StrategyService strategyService;
	private TriggerDefService triggerDefService;
	private EntityTreeService entityTreeService;
	private UnitedTreeService unitedTreeService;
	private HostInfoService hostInfoService;
	private TemManService temManService;
	private NetService netService;
	private DataStoreService dataStoreService;
	private String result;
	private String strategyId;
	private String entityId;
	private List resultList;
	private String type;
	private String netWorkId;
	private String flag; // 判断是添加还是删除 0添加 1修改
	private String connectId;
	private String hostId;// 主机ID(迁移时用到)
	@Autowired
	private VMHostService vmHostService;
	
	private VMHostObj vmHostObj;
	
	public VMHostObj getVmHostObj() {
		return vmHostObj;
	}

	public void setVmHostObj(VMHostObj vmHostObj) {
		this.vmHostObj = vmHostObj;
	}

	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public NetService getNetService() {
		return netService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public EntityTreeService getEntityTreeService() {
		return entityTreeService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public StrategyForm getTheForm() {
		return theForm;
	}

	public void setTheForm(StrategyForm theForm) {
		this.theForm = theForm;
	}

	public void setTriggerDefService(TriggerDefService triggerDefService) {
		this.triggerDefService = triggerDefService;
	}

	public void setStrategyService(StrategyService strategyService) {
		this.strategyService = strategyService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNetWorkId() {
		return netWorkId;
	}

	public void setNetWorkId(String netWorkId) {
		this.netWorkId = netWorkId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public DataStoreService getDataStoreService() {
		return dataStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	/**
	 * 
	 * @Title: listStrategy
	 * @Description: 查询调度策略列表
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String listStrategy() {
		if (theForm == null) {
			theForm = new StrategyForm();
		}
		StrategyObj obj = new StrategyObj();
		if (theForm.getName() != null && !theForm.getName().equals("")) {
			obj.setStrategy_name(theForm.getName());
		}
		if (theForm.getEnable() != null && !theForm.getEnable().equals("")) {
			if (!theForm.getEnable().equals("-1")) {
				obj.setEnable(theForm.getEnable());
			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<StrategyObj> resultList = strategyService.listStrategy(obj);

		theForm.setResultList(resultList);
		return "listStrategy";
	}

	/**
	 * 
	 * @Title: listAlarmStrategy
	 * @Description: 查询告警策略列表
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String listAlarmStrategy() {
		if (theForm == null) {
			theForm = new StrategyForm();
		}
		StrategyObj obj = new StrategyObj();
		List<StrategyObj> resultList = strategyService.listStrategy(obj);
		theForm.setResultList(resultList);
		return "listAlarmStrategy";
	}

	/**
	 * 
	 * @Title: addStrategy
	 * @Description: 增加调度策略
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws SQLException
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String addStrategy() throws SQLException {
		if (theForm == null) {
			theForm = new StrategyForm();
		}
		flag = "0";// 表示添加

		/*
		 * // 可迁移主机列表查询 UnitedTreeObj treeObj = new UnitedTreeObj();
		 * treeObj.setType(UnitedConstant.HOST); // 主机类型 List hostList =
		 * unitedTreeService.queryForTreeList(treeObj);
		 * theForm.setHostList(hostList);
		 */

		// 虚拟机模板列表赋值
		TemManObj temManObj = new TemManObj();
		List templateList = temManService.queryForList(temManObj);
		theForm.setTemplateList(templateList);

		return "addStrategy";
	}

	/**
	 * 
	 * @Title: updateStrategy
	 * @Description: 修改调度策略
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws SQLException
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String updateStrategy() throws SQLException {
		HttpServletRequest request = Struts2Utils.getRequest();
		flag = "1"; // 表示更新
		// 策略对象查询
		StrategyObj obj = new StrategyObj();
		obj.setStrategy_id(theForm.getStrategy_id());
		List tempList = strategyService.listStrategy(obj);
		if (tempList != null && tempList.size() > 0) {
			obj = (StrategyObj) tempList.get(0);
		}

		// 策略对象表单赋值
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 实体对象列表查询赋值
		entityId = obj.getEntity_id();
		String strateay_level = obj.getStrateay_level();
		List resultList = null;
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setType(strateay_level);
		resultList = unitedTreeService.queryForTreeList(treeObj);
		theForm.setResultList(resultList);
		// 更改迁移时需要的主机id
		theForm.setHost_id(obj.getHost_id());
		// 表单隐形变量赋值
		theForm.setFlag(1);
		request.setAttribute("flag", theForm.getFlag());
		request.setAttribute("type", theForm.getType());
		request.setAttribute("strateay_level", theForm.getStrateay_level());
		request.setAttribute("entityId", theForm.getEntity_id());
		request.setAttribute("content", obj.getContent());
		request.setAttribute("execute", obj.getExcute());
		request.setAttribute("migtype", obj.getMigtype());
		request.setAttribute("entityName", obj.getEntity_name());

		/*
		 * // 可迁移主机列表查询赋值 UnitedTreeObj unitedObj = new UnitedTreeObj();
		 * unitedObj.setType("3"); // 主机类型 List hostList =
		 * unitedTreeService.queryForTreeList(unitedObj);
		 * theForm.setHostList(hostList);
		 */
		// 虚拟机模板列表赋值
		// 虚拟机模板ID
		//String tempId = obj.getTemp_id();
		TemManObj temobj = new TemManObj();
		//temobj.setTemplateCode(tempId);
		//temobj.setConnectId(obj.getConnect_id());
		List<TemManObj> templateList = temManService.queryTemListById(temobj);
		theForm.setTemplateList(templateList);

		return "addStrategy";
	}

	/**
	 * 
	 * @Title: addAlarmStrategy
	 * @Description: 增加告警策略
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String addAlarmStrategy() {
		return "addAlarmStrategy";
	}

	/**
	 * 
	 * @Title: saveStrategy
	 * @Description: 保存调度策略
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String saveStrategy() {
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String excute = theForm.getExcute();
		// 目标主机
		String hostid = theForm.getHost_id();

		String trigger_ids = request.getParameter("trigger_id");
		// 模板信息
		String vh_cpu = request.getParameter("vh_cpu");
		String vh_mem = request.getParameter("vh_mem");
		String vh_num = request.getParameter("vh_num");
		String vh_storage = request.getParameter("vh_storage");
		String entity_name = request.getParameter("entity_name");
		String name = request.getParameter("name");
		if (name != null && !name.equals("")) {
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (entity_name != null && !entity_name.equals("")) {
			try {
				entity_name = URLDecoder.decode(entity_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		int ret = 0;
		// 新增调度策略
		if (theForm.getFlag() == 0) {
			// 插入对象初始化
			StrategyObj sObj = new StrategyObj();
			// 获取UUID
			String uuid = UUIDGenerator.getUUID();
			sObj.setStrategy_id(uuid);
			sObj.setStrategy_name(name); // 待修改
			sObj.setStrategy_desc(theForm.getStrategy_desc());

			sObj.setType(theForm.getType());
			sObj.setEntity_name(entity_name);
			// 动作为创建虚拟机时
			if (excute.equals("0")) {
				sObj.setStrateay_level(theForm.getStrateay_level());
			} else {
				// 实体级别为虚拟机
				sObj.setStrateay_level("4");
			}
			if (sObj.getStrateay_level().equals("3")) {
				// 实体级别为主机时，目标主机为其本身
				hostid = theForm.getEntity_id();
			}
			//TODO
			sObj.setMonitor_id(theForm.getMonitor_id());
			sObj.setConnect_id(theForm.getConnect_id());
			sObj.setEntity_id(theForm.getEntity_id());
			sObj.setEnable(theForm.getEnable());
			sObj.setEffect_date(theForm.getEffect_date());
			sObj.setExcute(theForm.getExcute());
			sObj.setExcute_type(theForm.getExcute_type());
			// 根据id查询对应的模板
			String tempCode = null;
			TemManObj tObj = new TemManObj();
			tObj.setId(theForm.getTemp_id());
			if (!"-1".equals(theForm.getTemp_id().trim())) {
				List<TemManObj> tList = new ArrayList<TemManObj>();
				tList = temManService.queryForList(tObj);
				if (tList != null) {
					tempCode = tList.get(0).getTemplateCode();
				}
			}
			sObj.setTemp_id(tempCode);
			sObj.setMigtype(theForm.getMigtype());
			sObj.setNetWork_id(theForm.getNetWork_id());
			// 资源池ID
			sObj.setConnect_id(theForm.getConnect_id());

			sObj.setCreater(session.get("account").toString());
			ret = strategyService.insertByObj(sObj);
			if (ret != -1) {
				/* 策略和触发条件关联表关系插入 */
				if (trigger_ids != null && !trigger_ids.equals("")) {
					TriggerRelationObj trObj = new TriggerRelationObj();
					trObj.setScheduler_id(uuid);
					trObj.setEntity_id(theForm.getEntity_id());
					String[] trigger_id = trigger_ids.split(",");
					for (String str : trigger_id) {
						DefinitionObj dObj = new DefinitionObj();
						trObj.setTrigger_id(str);
						// 查询对应的kpi
						dObj.setTrigger_id(str);
						List tempList = triggerDefService.listTriggerDefinition(dObj);
						if (tempList != null && tempList.size() > 0) {
							dObj = (DefinitionObj) tempList.get(0);
						}
						trObj.setKpi_id(dObj.getKpi_id());
						trObj.setKpi_n(dObj.getTigger_n());
						strategyService.insertTriggerRelation(trObj);
					}
				}
			}
			// 进行策略扩展表插入
			StrategyObj expandObj = new StrategyObj();
			expandObj.setCpu(Integer.parseInt(vh_cpu));
			expandObj.setMem(Integer.parseInt(vh_mem));
			expandObj.setStrategy_id(uuid);
			expandObj.setVh_num(Integer.parseInt(vh_num));
			expandObj.setStorage(Double.parseDouble(vh_storage));
			expandObj.setHost_id(hostid);
			ret = strategyService.insertStrategyExpand(expandObj);
		} else {
			// 修改调度策略
			StrategyObj sObj = new StrategyObj();
			sObj.setStrategy_id(theForm.getStrategy_id());
			sObj.setStrategy_name(name);
			sObj.setStrategy_desc(theForm.getStrategy_desc());
			sObj.setType(theForm.getType());
			sObj.setNetWork_id(theForm.getNetWork_id());
			// 动作为创建虚拟机时
			if (excute.equals("0")) {
				sObj.setStrateay_level(theForm.getStrateay_level());
			} else {
				// 实体级别为虚拟机
				sObj.setStrateay_level("4");
			}
			if (sObj.getStrateay_level().equals("3")) {
				// 实体级别为主机时，目标主机为其本身
				hostid = theForm.getEntity_id();
			}
			sObj.setMonitor_id(theForm.getMonitor_id());
			sObj.setEntity_id(theForm.getEntity_id());
			sObj.setEntity_name(entity_name);
			sObj.setEnable(theForm.getEnable());
			sObj.setEffect_date(theForm.getEffect_date());
			sObj.setExcute(theForm.getExcute());
			sObj.setExcute_type(theForm.getExcute_type());
			// 根据id查询对应的模板
			String tempCode = null;
			TemManObj tObj = new TemManObj();
			tObj.setId(theForm.getTemp_id());
			if (!"-1".equals(theForm.getTemp_id())) {
				List<TemManObj> tList = new ArrayList<TemManObj>();
				tList = temManService.queryForList(tObj);
				if (tList != null) {
					tempCode = tList.get(0).getTemplateCode();
				}
			}
			sObj.setTemp_id(tempCode);
			sObj.setCreater(session.get("account").toString());
			sObj.setMigtype(theForm.getMigtype());
			// 资源池ID
			sObj.setConnect_id(theForm.getConnect_id());
			ret = strategyService.updateByObj(sObj);
			if (ret != -1) {
				/* 策略和触发条件关联表更新 */
				if (trigger_ids != null && !trigger_ids.equals("")) {
					TriggerRelationObj trObj = new TriggerRelationObj();
					trObj.setScheduler_id(theForm.getStrategy_id());
					trObj.setEntity_id(theForm.getEntity_id());
					String[] trigger_id = trigger_ids.split(",");
					// 先行删除原先的关联关系
					strategyService.deleteRelation(trObj);
					// 再行插入新的关联关系
					for (String str : trigger_id) {
						DefinitionObj dObj = new DefinitionObj();
						trObj.setTrigger_id(str);
						// 查询对应的kpi
						dObj.setTrigger_id(str);
						List tempList = triggerDefService.listTriggerDefinition(dObj);
						if (tempList != null && tempList.size() > 0) {
							dObj = (DefinitionObj) tempList.get(0);
						}
						trObj.setKpi_id(dObj.getKpi_id());
						trObj.setKpi_n(dObj.getTigger_n());
						strategyService.insertTriggerRelation(trObj);
					}
				}
			}
			// 进行策略扩展表更新
			StrategyObj expandObj = new StrategyObj();
			expandObj.setCpu(Integer.parseInt(vh_cpu));
			expandObj.setMem(Integer.parseInt(vh_mem));
			expandObj.setStrategy_id(theForm.getStrategy_id());
			expandObj.setStorage(Double.parseDouble(vh_storage));
			expandObj.setHost_id(hostid);
			expandObj.setVh_num(Integer.parseInt(vh_num));
			ret = strategyService.updateStrategyExpand(expandObj);
		}
		result = ret + "";
		return "results";
	}

	/**
	 * 
	 * @Title: deleteStrategy
	 * @Description: 删除调度策略
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String deleteStrategy() {
		StrategyObj obj = new StrategyObj();
		obj.setStrategy_id(theForm.getStrategy_id());
		// 进行策略表删除
		strategyService.deleteByObj(obj);
		// 进行策略扩展表删除
		strategyService.updateStrategyExpand(obj);
		// 进行策略和触发器关联表删除
		TriggerRelationObj tObj = new TriggerRelationObj();
		tObj.setScheduler_id(theForm.getStrategy_id());
		strategyService.deleteRelation(tObj);

		return "deleteStrategy";
	}

	/**
	 * 
	 * @Title: queryCondition
	 * @Description: 查询触发条件
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String queryCondition() {
		HttpServletRequest request = Struts2Utils.getRequest();
		strategyId = "";
		strategyId = request.getParameter("sid");
		DefinitionObj obj = new DefinitionObj();
		List<DefinitionObj> resultList = triggerDefService.listTriggerDefinition(obj);
		for (DefinitionObj tempObj : resultList) {
			int interval = Integer.parseInt(tempObj.getInterval());
			int tigger_n = Integer.parseInt(tempObj.getTigger_n());
			String operator = tempObj.getOperator();
			if (!operator.equals("in")) {
				tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
						+ "次,触发条件：" + tempObj.getKpi_name() + tempObj.getOperator()
						+ tempObj.getThreshold() + "达到" + tempObj.getTrigger_m() + "次");
			} else {
				tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
						+ "次,触发条件：" + tempObj.getKpi_name() + "大于等于" + tempObj.getMin() + "且小于等于"
						+ tempObj.getMax() + "达到" + tempObj.getTrigger_m() + "次");
			}
		}
		Struts2Utils.getRequest().setAttribute("resultList", resultList);
		return "queryCondition";
	}

	/**
	 * 
	 * @Title: queryAllEntity
	 * @Description: 根据不同的类型查询所有实体
	 * @param
	 * @return null
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws SQLException
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String queryAllEntity() throws SQLException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取到资源类型：0：vcenter,1:数据中心，2：集群，3：主机,4:虚拟机
		String type = request.getParameter("type");
		// 具体操作 0：创建，1：调整 ， 2：删除，3、迁移
		String eType = request.getParameter("etype");
		if (type != null && !"".equals(type)) {
			this.type = type;
		}
		resultList = null;
		// 查询获取实体类型下的结果
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setType(type);
		if (theForm != null) {
			if (theForm.getEntity_name() != null && !"".equals(theForm.getEntity_name())) {
				obj.setName(theForm.getEntity_name());
			}
		}
		resultList = unitedTreeService.queryForTreeList(obj);
		/**************** 判断该主机存储是否为共享存储 ****************************/
		/*
		 * List<UnitedTreeObj> list = unitedTreeService.queryForTreeList(obj);
		 * List<DataStoreObj> dList = new ArrayList<DataStoreObj>();
		 * if("3".equals(eType)||"0".equals(eType)&&"3".equals(type)){
		 * if(list!=null){ for(int i=0;i<list.size();i++){
		 * //*********判断该主机存储是否为共享的***************
		 *//*
			 * DataStoreObj dObj = new DataStoreObj();
			 * dObj.setHOST_ID(list.get(i).getUuid());
			 * dObj.setConnectId(list.get(i).getConnect_id()); dList =
			 * dataStoreService.queryForListByObj(dObj); if(dList.size() != 0){
			 * if(dList.get(i).getNAME().indexOf("data") == -1){ list.remove(i);
			 * i--; } }else{ list.remove(i); //没有存储的主机直接删除 i--; } } } }
			 * resultList = list;
			 */
		return "queryAllEntity";
	}

	/**
	 * 
	 * @Title:根据所选实体查询虚拟机的模板列表
	 * @Copyright:Copyright (c) Aug 28, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryTemplate() throws SQLException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取到资源实体ID
		String eid = request.getParameter("eid");

		// 查询获取实体类型下的结果
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(eid);
		UnitedTreeObj resobj = unitedTreeService.queryByObj(obj);
		// 虚拟化类型
		String type = resobj.getType();
		if (!type.equals("4")) {
			// 虚拟化类型
			String vtype = resobj.getVtype();

			// 根据虚拟化类型或许模板列表信息
			TemManObj temobj = new TemManObj();
			temobj.setType(vtype);
			List templetList = temManService.queryForList(temobj);
			JSONArray json = JSONArray.fromObject(templetList);
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			// PrintWriter p = response.getWriter();
			// p.print(json.toString());
			// p.close();
			PrintWriterOut.printWirter(response, json.toString());
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryNetWork
	 * @Description: 查询网络域
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws
	 * @createtime aug 7, 2012 11:42:00 AM
	 */
	public String queryNetWork() {
		List<TbCloud2NetInfoObj> list = new ArrayList<TbCloud2NetInfoObj>();
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		/****** 去掉没有可用ip的网络域 *********/
		list = netService.queryNetListByObj(obj);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFREECOUNT() != null && !("").equals(list.get(i).getFREECOUNT())) {
				if (Integer.valueOf(list.get(i).getFREECOUNT()) <= 0) {
					list.remove(i);
					i--;
				}
			}
		}
		resultList = list;
		return "queryNetWork";
	}

	/**
	 * 
	 * @Title: choosedTrigger
	 * @Description: 查询已经选择的触发器条件
	 * @param
	 * @return null
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String choosedTrigger() {
		String trigger_ids = Struts2Utils.getParameter("trigger_ids");
		List<DefinitionObj> list = new ArrayList();
		if (trigger_ids != null || !trigger_ids.equals("")) {
			String[] trigger_id = trigger_ids.split(",");
			DefinitionObj obj = new DefinitionObj();
			for (String str : trigger_id) {
				obj.setTrigger_id(str);
				List<DefinitionObj> resultList = triggerDefService.listTriggerDefinition(obj);
				for (DefinitionObj tempObj : resultList) {
					int interval = Integer.parseInt(tempObj.getInterval());
					int tigger_n = Integer.parseInt(tempObj.getTigger_n());
					String operator = tempObj.getOperator();
					if (!operator.equals("in")) {
						tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
								+ "次,触发条件：" + tempObj.getKpi_name() + tempObj.getOperator()
								+ tempObj.getThreshold() + "达到" + tempObj.getTrigger_m() + "次");
					} else {
						tempObj.setContent(interval * tigger_n + "分钟内采集" + tempObj.getTigger_n()
								+ "次,触发条件：" + tempObj.getKpi_name() + "大于等于" + tempObj.getMin()
								+ "且小于等于" + tempObj.getMax() + "达到" + tempObj.getTrigger_m() + "次");
					}
				}
				list.addAll(resultList);
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=utf-8");
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: getTemplateInfo
	 * @Description: 根据模板ID查询模板的详细信息
	 * @author duangh
	 * @version 1.0
	 */
	public String getTemplateInfo() {
		TemManObj obj = null;
		String temp_id = Struts2Utils.getParameter("temp_id");
		TemManObj temobj = new TemManObj();
		temobj.setId(temp_id);
		// 查询获取模板信息对象
		List templetList = temManService.queryForList(temobj);
		if (templetList != null) {
			obj = (TemManObj) templetList.get(0);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("cpu", Integer.toString(obj.getCpu()));
		map.put("mem", Integer.toString(obj.getMem()));
		map.put("store", Double.toString(obj.getStore()));
		map.put("vmnum", "0");
		// if (templetList != null && templetList.size() > 0) {
		// TemManObj obj = (TemManObj) templetList.get(0);
		// for (int i = 1; i <= obj.getKV_NUM(); i++) {
		// String getter = "getKV" + i;
		// String value = "";
		// try {
		// Method method = obj.getClass().getMethod(getter, new Class[] {});
		// Object kv = method.invoke(obj, new Object[] {});
		// value = String.valueOf(kv);// 得到其中的kv值
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// if (value != null && !value.equals("")) {
		// String[] kv_value = value.split(":");
		// if (kv_value != null) {
		// map.put(kv_value[0], kv_value[1]);
		// }
		// }
		// }
		// }
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			JSONArray json = JSONArray.fromObject(map);
			p.print(json.toString());
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getHost
	 * @Description: 根据虚拟机获取可以迁移的主机
	 * @author siweichao
	 * @version 1.0
	 */
	public String queryHost() {
		String parent = "";
		String myself = ""; // 当前传过来的变量的所在主机直接排除
		List<UnitedTreeObj> list = new ArrayList<UnitedTreeObj>();
		UnitedTreeObj obj = new UnitedTreeObj();
		UnitedTreeObj obj1 = new UnitedTreeObj(); // 用以接收当前虚拟机信息
		if (connectId != null && !"".equals(connectId)) {
			obj.setConnect_id(connectId);
		}
		if (entityId != null && !"".equals(entityId)) {
			obj.setUuid(entityId);
		}
		try {
			obj1 = unitedTreeService.queryByObj(obj);
			if (obj1 != null) {// 有这条虚拟机的记录
				myself = obj1.getParent_id();// 获取虚拟机自己的parentid即所在主机的ID
				obj.setUuid(null); // 清空uuid
				if (myself != null && !"".equals(myself)) {
					obj.setId(myself);
					parent = unitedTreeService.queryByObj(obj).getParent_id();// 主机parentid
				}
				if (parent != null && !"".equals(parent)) {
					obj.setId(null);
					obj.setParent_id(parent);
					obj.setType("3");// 保证是主机
					list = unitedTreeService.queryForTreeList(obj);
				}
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId().equals(myself)) {
						// 还需要从主机表中确定主机状态可用才行，待开发
						list.remove(i);
					}
					/********* 选择状态可用状态主机 *****************/
					TbCloud2HostInfoObj hObj = new TbCloud2HostInfoObj();
					hObj.setH_uuid(list.get(i).getUuid());
					hObj.setConnectId(list.get(i).getConnect_id());
					TbCloud2HostInfoObj hObj1 = hostInfoService.queryByObj(hObj);
					if (hObj1 != null) {
						if ("0".equals(hObj1.getSTATUS())) {
							list.remove(i);
						}
					}
					/********* 仅有存在共享存储的主机才可以迁移虚拟机--待开发 *************/
					/*
					 * DataStoreObj dObj = new DataStoreObj();
					 * dObj.setHOST_ID(list.get(i).getUuid());
					 * dObj.setConnectId(list.get(i).getConnect_id());
					 * List<DataStoreObj> dList =
					 * dataStoreService.queryForListByObj(dObj);
					 * if(dList!=null){
					 * if(dList.get(i).getNAME().indexOf("datastore") == -1){
					 * list.remove(i); i--; } }
					 */
				}
				resultList = null;
				resultList = list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "getHost";
	}

	/**
	 * 
	 * @Title: getHost
	 * @Description: 根据虚拟机获取可以迁移的主机
	 * @author siweichao
	 * @version 1.0
	 */
	public String adjustStrategy() {
		if (theForm == null) {
			theForm = new StrategyForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String name = "";
		name = request.getParameter("name");
		StrategyObj obj = new StrategyObj();
		if (name != null && !name.equals("")) {
			obj.setStrategy_name(name);
		}
		List<StrategyObj> resultList = strategyService.listStrategy(obj);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("utf-8");
		int flag = 1;// 不存在相同的
		if (resultList.size() > 0) {
			flag = 0;
		}
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		// PrintWriter pw = null;
		try {
			// pw = response.getWriter();
			// pw.write(json.toString());
			PrintWriterOut.printWirter(response, json.toString());
		} finally {
			// pw.close();
		}
		return null;
	}
	/**
	 * 
	 * @Title: queryVmHost
	 * @Description: 查询虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-6-5 下午11:47:48
	 */
	public String queryVmHost(){
		if(vmHostObj == null){
			vmHostObj = new VMHostObj();
		}
		vmHostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		resultList = vmHostService.queryForVmList(vmHostObj);
		return "listVm";
	}
	public void setUnitedTreeService(UnitedTreeService unitedTreeService) {
		this.unitedTreeService = unitedTreeService;
	}

	public void setTemManService(TemManService temManService) {
		this.temManService = temManService;
	}

}
