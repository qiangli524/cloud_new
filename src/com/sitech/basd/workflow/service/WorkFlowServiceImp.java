package com.sitech.basd.workflow.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.emobile.bpm.invoker.RunWorkflowInvoker;
import com.emobile.bpm.invoker.WfEntryInfoInvoker;
import com.emobile.bpm.invoker.WorkflowDefInvoker;
import com.emobile.bpm.invoker.bean.InputBean;
import com.emobile.bpm.invoker.bean.ResultBean;
import com.emobile.bpm.invoker.bean.Step;
import com.emobile.bpm.invoker.bean.WaitingAssignBean;
import com.emobile.bpm.invoker.exception.BPMException;
import com.sitech.basd.workflow.domain.TbWorkFlow;
import com.sitech.basd.workflow.domain.TbWorkFlowData;
import com.sitech.ssd.bpm.common.BaseService;
import com.sitech.ssd.bpm.domain.CloudStep;
import com.sitech.ssd.bpm.domain.CloudStepData;
import com.sitech.ssd.bpm.domain.UserOrder;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.bpm.domain.WorkFlowConfig;
import com.sitech.ssd.bpm.domain.WorkFlowConstant;
import com.sitech.ssd.bpm.util.CachedConnectionFactory;
import com.sitech.ssd.bpm.util.DateUtil;
import com.sitech.ssd.bpm.util.Env;
import com.sitech.ssd.bpm.util.RabbitMQException;
import com.sitech.ssd.bpm.util.RabbitMQUtil;

@SuppressWarnings("unchecked")
@Service("workFlowService")
public class WorkFlowServiceImp extends BaseService implements WorkFlowService{
	private static final Logger logger = Logger.getLogger(WorkFlowServiceImp.class.getName());
	private static final String userName = "admin";
	private WfEntryInfoInvoker wfEntryInfoInvoker = new WfEntryInfoInvoker(userName);
	private RunWorkflowInvoker runWorkflowInvoker = new RunWorkflowInvoker(userName);
	private WorkflowDefInvoker  workflowDefInvoker  = new WorkflowDefInvoker (userName);
	
	@Override
	public void addWorkFlow(WorkFlow obj, List<com.sitech.basd.workflow.domain.TbWorkFlowData> datas, CloudStep step) {
		try {
			String realResourceName = this.getRealResourceName(obj.getResourceName());
			if(obj.getEntryId() == null){
				//初始化实例
				obj = initEntryId(obj,realResourceName);
				//签出
				this.assignTaskToUser(obj.getEntryId());
				//派发cloudstep
				addWorkFlowStep(obj.getEntryId(),obj.getAccount(),obj.getAccount());
				//工单归属人
				if(obj.getUserId()==null){
					obj.setUserId(obj.getAccount());
				}
			}
			//证明初始化成功
			if(obj.getEntryId()>0){
				this.assignTaskToUser(obj.getEntryId());
				this.doAction(obj, step ,null);
			}else{
				logger.error("初始化initbpm失败");
			}
			//save workOrder
			if(obj.getId()==null){
				if(obj.getOrderTitle()==null){
					obj.setOrderTitle("默认名称");
				}
				this.getSqlMap().insert("tbWorkOrder.insertWorkFlow",obj);
				if (datas!=null)
				{
					for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
						TbWorkFlowData tbWorkFlowData = (TbWorkFlowData) iterator.next();
						tbWorkFlowData.setWorkFlowId(obj.getId());
						this.getSqlMap().insert("tbWorkOrder.insertWorkFlowData",tbWorkFlowData);
					}
				}
			}else{
				this.getSqlMap().update("tbWorkOrder.updateWorkFlow",obj);
				if (datas!=null)
				{
					for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
						TbWorkFlowData tbWorkFlowData = (TbWorkFlowData) iterator.next();
						tbWorkFlowData.setWorkFlowId(obj.getId());
						this.getSqlMap().update("tbWorkOrder.updateWorkFlowData", tbWorkFlowData);
					}
				}
			}
		} catch (Exception e) {
			obj = null;
			logger.error("saveWorkOrder:"+ e.getMessage());
			e.printStackTrace();
		}
	}

	public String getRealResourceName(String resourceName){
		if(resourceName==null || resourceName.length()==0){			
			logger.error("流程名称为空");
		}
		return resourceName + WorkFlowConstant.VERSION_DEFALUT;
	}
	
	/**
	 * 
	 * <p>初始化entryId，返回实例ID/p>
	 * 
	 * @param obj
	 * @param runWorkflowInvoker
	 * @param workflowDefInvoker
	 * @param entryId
	 * @param realResourceName
	 * @return
	 * @throws BPMException
	 */
	private WorkFlow initEntryId(WorkFlow obj,
			String realResourceName) throws Exception {
		Long entryId = null;
		InputBean[] input = new InputBean[]{
				new InputBean(WorkFlowConstant.BPM_NEXTUSER,"String",userName)
		};
		int[] initActionIds = runWorkflowInvoker.getInitializeActionIds(realResourceName);
		if(workflowDefInvoker.isResourceNameExisted(realResourceName)){					
			if(initActionIds.length!=0){					
				ResultBean resultBean = runWorkflowInvoker.initializeByRemote(realResourceName, initActionIds[0], input);
				entryId = resultBean.getEntryId();
				obj.setEntryId(entryId);
				//工单编号
				obj.setOrderNo(entryId.toString());
				obj.setCreator(obj.getAccount());
			}else{
				logger.error("getInitializeActionId的长度为0,流程名称为:"+realResourceName);
			}
		}else{
			logger.error("流程名不存在:"+realResourceName);
		}
		return obj;
	}
	
	/**
	 * 
	 * <p>签出</p>
	 * 
	 * @param entryId
	 * @throws Exception
	 */
	private void assignTaskToUser(Long entryId) throws Exception{
		WaitingAssignBean[] waitbeans = runWorkflowInvoker.getWaitingAssignList(entryId,userName);
		if(waitbeans.length != 0){		
			runWorkflowInvoker.assignTaskToUser(entryId, waitbeans[0].getStepId(),userName);
		}	
	}
	
	/**
	 * <p>派发</p>
	 * @throws Exception 
	 * 
	 */
	private int addWorkFlowStep(Long entryId,String Caller,String executor) throws Exception {
		Step waitbean = this.getCurrentStepByEntryId(entryId);
		int result = 0;
		if(waitbean!=null){
			CloudStep step = new CloudStep();
			step.setCaller(Caller);
			step.setStartDate(DateUtil.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
			step.setStatus(WorkFlowConstant.STEP_STAUTS_WAIT);
			step.setStepId(waitbean.getStepId());
			step.setStepName(waitbean.getStepName());
			step.setExecutor(executor);
			step.setEntryId(entryId);
			result = (Integer) this.getSqlMap().insert("WorkFlow.insertWorkFlowStep",step);
		}
		return result;
	}
	
	/**
	 * 
	 * <p>执行动作</p>
	 * 
	 * @param obj
	 * @param step
	 * @param runWorkflowInvoker
	 * @param timestamp
	 * @param entryId
	 * @throws BPMException
	 * @throws Exception
	 * @throws SQLException
	 */
	private void doAction(WorkFlow workFlow, CloudStep step,WorkFlowConfig workFlowConfig)
			throws BPMException, Exception, SQLException {	
		int[] actionIds = runWorkflowInvoker.getAvailableActionsByRemote(workFlow.getEntryId(), null);					
		if(actionIds.length!=0){
			List<InputBean> list = new ArrayList<InputBean>();
			step = this.getCurrCloudStep(workFlow.getEntryId(),workFlow.getAccount(),step);
			if(step!=null){				
				//修改当前步骤
				this.updateWorkFlowStep(workFlow.getAccount(), step,list);
				CloudStep cloudStep = new CloudStep();
				cloudStep.setEntryId(workFlow.getEntryId());
				cloudStep.setStepId(step.getStepId());
				//				List stepList  = getSqlMap().queryForList("WorkFlow.queryWorkFlowWaitStep",cloudStep);
				//				if(stepList.size()==0){					
				//保存流程数据
				InputBean[] input = this.getInputBeanByStep(list);
				//执行工作流引擎动作
				ResultBean resultBean = runWorkflowInvoker.doActionByRemote(workFlow.getEntryId(), actionIds[0], input);				
				Step waitbean = this.getCurrentStepByEntryId(workFlow.getEntryId());
				if(waitbean!=null){						
					//获取下一步执行人
					if(workFlowConfig==null){						
						workFlowConfig = this.getWorkFlowConfig(workFlow.getEntryId(),workFlow.getResourceName(),workFlow.getCreator());
					}
					//为下一步执行人派发工单
					this.sendWorkOrderForNextUser(workFlow,workFlowConfig);
					//设置下一步名称
					workFlow.setStepName(waitbean.getStepName());
					workFlow.setStepId(waitbean.getStepId());
					workFlow.setRunStatus(WorkFlowConstant.ORDER_RUNSTATUS_DEFAULT);
				}else{
					workFlow.setNextGroup(null);
					workFlow.setNextUser(null);
					workFlow.setStepId(99);
					//工单结束 修改结束状态
					workFlow.setRunStatus(WorkFlowConstant.ORDER_RUNSTATUS_END);
					this.getSqlMap().insert("WorkFlow.updateWorkFlow",workFlow);
					//发送rabbitMq消息
					this.sendMessage(workFlow);						
				}
				//				}
			}else{
				logger.info("无权执行");
			}			
		}else{
			logger.error("actionIds is null,entryId:"+workFlow.getEntryId());
		}
	}
	
	/**
	 * 
	 * <p>获取当前步骤</p>
	 * 
	 * @param entryId
	 * @return
	 * @throws BPMException
	 */
	public Step getCurrentStepByEntryId(Long entryId) throws Exception{
		Step step = null;
		//获取当前步骤
		Step[] curSteps = wfEntryInfoInvoker.getCurrentSteps( entryId );
		if(curSteps.length!=0){					
			step = curSteps[0];
		}
		return step;
	}
	
	/**
	 * 
	 * <p>获取当前cloudStep</p>
	 * 
	 * @return
	 * @throws Exception 
	 */
	public CloudStep getCurrCloudStep(Long entryId,String account, CloudStep step) throws Exception{
		Step currStep = getCurrentStepByEntryId(entryId);
		//验证是否有权限				
		CloudStep cloudStep = new CloudStep();
		cloudStep.setEntryId(entryId);
		cloudStep.setStepId(step.getStepId());
		cloudStep.setExecutor(account);
		cloudStep  = (CloudStep)getSqlMap().queryForObject("WorkFlow.queryWorkFlowWaitStep",cloudStep);
		if(cloudStep!=null){
			cloudStep.setObjData(step.getObjData());
			cloudStep.setListData(step.getListData());
		}
		return cloudStep;
	}
	
	/**
	 * 
	 * <p>保存流程数据</p>
	 * 
	 * @param workFlow
	 * @param step
	 * @param timestamp
	 * @param objDataJson
	 * @param listDataJson
	 * @throws Exception 
	 */
	private void updateWorkFlowStep(String account, CloudStep step, List<InputBean> list)
			throws Exception {		
		step.setFinishDate(DateUtil.dateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
		step.setStatus(WorkFlowConstant.STEP_STAUTS_FINISH);
		step.setExecutor(account);
		int result = this.getSqlMap().update("WorkFlow.updateWorkFlowStep",step);
		if(result==1){			
			this.addWorkFlowStepData(step.getCsid(),step,list);
		}
	}
	
	/**
	 * 
	 * <p>插入节点值</p>
	 * 
	 * @param obj
	 * @param step
	 * @return
	 * @throws SQLException 
	 */
	private InputBean[] getInputBeanByStep(List<InputBean> list) throws Exception {
		//流程的下一步执行人
		list.add(new InputBean(WorkFlowConstant.BPM_NEXTUSER,"String",userName));							
		//创建InputBean[]
		InputBean[] inputs = new InputBean[list.size()];
		for(int i=0;i<list.size();i++){
			inputs[i] = list.get(i);
		}		
		return inputs;
	}
	
	/**
	 * <p>获取审批人</p>
	 * 
	 * @param workFlow
	 * @param step
	 * @return
	 * @throws SQLException 
	 */
	private WorkFlowConfig getWorkFlowConfig(Long entryId,String resourceName,String defalutUsername) throws Exception {
		WorkFlowConfig workFlowConfig = null;
		Step waitbean = this.getCurrentStepByEntryId(entryId);
		if(waitbean!=null){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("resourceName", resourceName);
			paramMap.put("stepId", waitbean.getStepId());
			workFlowConfig = (WorkFlowConfig)getSqlMap().queryForObject("WorkFlow.queryWorkFlowConfig",paramMap);

			if(workFlowConfig==null){
				logger.info("没有配置审批人,下一步骤审批为工单创建人,resourceName="+resourceName+
						",stepId="+waitbean.getStepId());
			}
		}		
		return workFlowConfig;
	}
	
	/**
	 * 
	 * <p>为下一步执行人派单</p>
	 * 
	 * @param workFlow
	 * @param workFlowConfig
	 * @throws Exception
	 */
	public void sendWorkOrderForNextUser(WorkFlow workFlow,WorkFlowConfig workFlowConfig) throws Exception{
		if(workFlowConfig!=null){			
			if(workFlowConfig.getGroupName()!=null){						
				workFlow.setNextGroup(workFlowConfig.getGroupName());
				if(workFlowConfig.getCallType()==WorkFlowConstant.GROUP_EXE_AI){
					//智能分配 把任务分配给工单少的人
					String user = this.getLessOrderUser( workFlowConfig.getGroupName() ,workFlowConfig.getResourceName());	
					workFlow.setGroupName(workFlowConfig.getGroupName());
					this.addWorkFlowStep(workFlow.getEntryId(),workFlow.getAccount(),user); 
				}else if(workFlowConfig.getCallType()==WorkFlowConstant.GROUP_EXE_SEIZE){
					//抢占式
					workFlow.setGroupName(workFlowConfig.getGroupName());
					this.addWorkFlowStep(workFlow.getEntryId(),workFlow.getAccount(),workFlowConfig.getGroupName()); 
				}else{
					//					//TODO 发给组内所有人
					//					if(workFlowConfig.getUserName()!=null){
					//						String[] users = workFlowConfig.getUserName().split(",");
					//						for(String u : users){
					//							this.addWorkFlowStep(workFlow.getEntryId(),workFlow.getAccount(),u); 
					//						}									
					//					}else{
					//						logger.error("配置错误，组内的人员必须配置到username中，以逗号隔开");
					//					}
					logger.error("配置错误,暂时不支持calltype=2");
				}
			}else{
				workFlow.setNextUser(workFlowConfig.getUserName());
				this.addWorkFlowStep(workFlow.getEntryId(),workFlow.getAccount(), workFlowConfig.getUserName()); 
			}
		}else{
			String nextUser = null;
			if(workFlow.getId()==null){		
				nextUser = workFlow.getAccount();
			}else{
				//下一步执行人为工单归属人
				WorkFlow wf = (WorkFlow)getSqlMap().queryForObject("WorkFlow.queryWorkFlow",
						workFlow);
				nextUser = wf.getUserId();
			}
			workFlow.setNextUser(nextUser);
			this.addWorkFlowStep(workFlow.getEntryId(),workFlow.getAccount(), nextUser); 
		}					
	}
	
	/**
	 * <p>工作结束发送rabbitMQ消息</p>
	 * 
	 * @param workFlow
	 * @throws RabbitMQException 
	 */
	private void sendMessage(WorkFlow workFlow) throws RabbitMQException {
		String isopen = Env.getInstance().getProperty("rabbitmq.isopen");
		if(Boolean.parseBoolean(isopen)){			
			String host = Env.getInstance().getProperty("cloud.rabbitmq.host");
			String port = Env.getInstance().getProperty("cloud.rabbitmq.port");

			CachedConnectionFactory cachedConnectionFactory = new CachedConnectionFactory(host,Integer.parseInt(port));
			RabbitMQUtil rabbitMQUtil = new RabbitMQUtil(cachedConnectionFactory); 
			rabbitMQUtil.publishMessage("", Env.getInstance().getProperty("rabbitmq.queues.workflow.end"), workFlow);
			cachedConnectionFactory.destroy();
		}
	}
	
	/**
	 * <p>添加WorkFlowStepData</p>
	 * 
	 * @param csid
	 * @param step
	 * @throws SQLException 
	 */
	private void addWorkFlowStepData(Integer csid, CloudStep step, List<InputBean> list) throws Exception {
		//objData
		JSONObject objDataJson = this.formatObjData(step, list);
		//listData
		JSONArray listDataJson = this.formatListData(step);
		//保存步骤数据
		CloudStepData cloudStepData = new CloudStepData();		
		cloudStepData.setCsid(csid);
		cloudStepData.setObjData(objDataJson.toString());
		cloudStepData.setListData(listDataJson.toString());
		//		cloudStepData.setTimestamp(timestamp);
		this.getSqlMap().insert("tbWorkOrder.insertWorkFlowStepData",cloudStepData);
	}
	
	/**
	 * 
	 * <p>获取工单数量少的用户</p>
	 * 
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	private String getLessOrderUser(String gropuName,String resourceName) throws Exception{
		//TODO 获取组下所有人
		List<Map<String,String>> users = new ArrayList<Map<String,String>>();
		Map<String,String> map1 = new HashMap<String,String>();
		if("1084".equals(gropuName)){
			map1.put("account","517");
			Map<String,String> map2 = new HashMap<String,String>();
			map2.put("account","518");					
			users.add(map1);
			users.add(map2);
		}else if("1085".equals(gropuName)){
			map1.put("account","519");
			Map<String,String> map2 = new HashMap<String,String>();
			map2.put("account","520");			
			users.add(map1);
			users.add(map2);
		}


		Map map = new HashMap();
		map.put("users", users);
		map.put("resourceName", resourceName);
		String user = null;
		List list = getSqlMap().queryForList("WorkFlow.queryUserWrokFlowAmount",map);
		if(list.size()!=0){
			UserOrder userOrder= (UserOrder)list.get(0);
			user = userOrder.getExecutor();
		}else{
			user = users.get(0).get("account");
		}
		return user;
	}
	
	/**
	 * 
	 * <p>objdata</p>
	 * 
	 * @param step
	 * @param list
	 * @return
	 */
	private JSONObject formatObjData(CloudStep step, List<InputBean> list) {
		JSONObject objDataJson = new JSONObject();
		if(step.getObjData() != null){							
			Set<String> set = step.getObjData().keySet();
			for (Iterator it = set.iterator(); it.hasNext();) {
				String key = (String) it.next();
				String[] values = (String[])step.getObjData().get(key);
				if( values!=null && values.length!=0 ){
					String val = "";
					for(String value :values){
						val=val+value+",";
					}
					if(val.length()>0){
						val = val.substring(0,val.length()-1);
					}
					objDataJson.put(key, val);
					//以Custom_开头的都是BPM中需要的字段
					if(list != null && key.contains(WorkFlowConstant.BPM_FIELD_MARKED)){
						list.add(new InputBean(key,"String",val));
					}
				}
			}
		}
		return objDataJson;
	}

	/**
	 * 
	 * <p>listData</p>
	 * 
	 * @param step
	 * @return
	 */
	private JSONArray formatListData(CloudStep step) {
		JSONArray listDataJson = new JSONArray();
		if(step.getListData() != null){
			Set<String> set = step.getListData().keySet();
			List<String> keylist = new ArrayList<String>();
			for (Iterator it = set.iterator(); it.hasNext();) {
				String key = (String) it.next();
				keylist.add(key);
			}
			int len =  ((String[])step.getListData().get(keylist.get(0))).length;
			for(int i= 0;i<len;i++){
				JSONObject jSONObject = new JSONObject();
				for(String key : keylist){
					String[] values = (String[])step.getListData().get(key);
					jSONObject.put(key, values[i]);
				}
				listDataJson.add(jSONObject);
			}
		}
		return listDataJson;
	}

	@Override
	public List<TbWorkFlowData> getWorkFlowData(int workFlowId) {
		List<TbWorkFlowData> result = new ArrayList<TbWorkFlowData>();
		try {
			result = this.getSqlMap().queryForList("tbWorkOrder.queryForWorkFlowDataList",workFlowId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TbWorkFlowData> getVMList(String creator) {
		List<TbWorkFlowData> result = new ArrayList<TbWorkFlowData>();
		try {
			result = this.getSqlMap().queryForList("tbWorkOrder.queryForVmList", creator);
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				TbWorkFlowData tbWorkFlowData = (TbWorkFlowData) iterator.next();
				tbWorkFlowData.setMemorySize(tbWorkFlowData.getMemorySize()/1024);
				tbWorkFlowData.setStoreSize(tbWorkFlowData.getStoreSize()/1024);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getVmForValidate() {
		List result = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = this.getSqlMap().queryForList("tbWorkOrder.queryForOutDateWorkFlow", sdf.format(new Date()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List getWorkFlowStatisticInfo(String creator){
		List<WorkFlow> list = new ArrayList<WorkFlow>();
		try {
			list = getSqlMap().queryForList("WorkFlow.queryWorkFlowStatisticInfo",creator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addValidateWorkFlow(int workflowid) {
		TbWorkFlow tbWorkFlow = getTbWorkFlowObj(workflowid);
		tbWorkFlow.setId(null);
		tbWorkFlow.setOrderTitle("虚拟机到期");
		tbWorkFlow.setCreateTime(new Date());
		tbWorkFlow.setNextUser(tbWorkFlow.getCreator());
		tbWorkFlow.setCreator("system");
		tbWorkFlow.setStepId(1);
		tbWorkFlow.setStepName("虚拟机到期节点");
		tbWorkFlow.setRunStatus(0);
		
		String realResourceName = this.getRealResourceName(tbWorkFlow.getResourceName());

		InputBean[] input = new InputBean[]{
				new InputBean(WorkFlowConstant.BPM_NEXTUSER,"String",userName)
		};
		int[] initActionIds;
		try {
			initActionIds = runWorkflowInvoker.getInitializeActionIds(realResourceName);
			if(workflowDefInvoker.isResourceNameExisted(realResourceName)){					
				if(initActionIds.length!=0){					
					ResultBean resultBean = runWorkflowInvoker.initializeByRemote(realResourceName, initActionIds[0], input);
					tbWorkFlow.setEntryId(resultBean.getEntryId());
				}
			}
		} catch (BPMException e1) {
			e1.printStackTrace();
		}
		tbWorkFlow.setResourceName("resourceValidate");
		tbWorkFlow.setOrderNo(tbWorkFlow.getEntryId()+"");
		try {
			this.getSqlMap().insert("tbWorkOrder.insertTbWorkFlow",tbWorkFlow);
			
			CloudStep step = new CloudStep();
			step.setEntryId(tbWorkFlow.getEntryId());
			step.setStepId(1);
			step.setStepName(tbWorkFlow.getStepName());
			step.setCaller("system");
			step.setExecutor(tbWorkFlow.getNextUser());
			step.setStatus(0);
			this.getSqlMap().insert("tbWorkOrder.insertWorkFlowStep", step);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<TbWorkFlowData> datas = getWorkFlowData(workflowid);
		for (Iterator iterator = datas.iterator(); iterator.hasNext();) {
			TbWorkFlowData tbWorkFlowData = (TbWorkFlowData) iterator.next();
			tbWorkFlowData.setValidateTime(null);
			try {
				this.getSqlMap().update("tbWorkOrder.updateValidateWorkFlowData",tbWorkFlowData);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			tbWorkFlowData.setId(0);
			tbWorkFlowData.setWorkFlowId(tbWorkFlow.getId());
			try {
				this.getSqlMap().insert("tbWorkOrder.insertWorkFlowData",tbWorkFlowData);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public TbWorkFlow getTbWorkFlowObj(int id) {
		TbWorkFlow tbWorkFlow = null;
		try {
			tbWorkFlow = (TbWorkFlow)getSqlMap().queryForObject("tbWorkOrder.queryForTbWorkFlow", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tbWorkFlow;
	}

	@Override
	public void deleteOutDataWorkFlow(int workFlowId, long entryId) {
		try {
			getSqlMap().delete("tbWorkOrder.deleteTbWorkFlowStepByEntryId", entryId);
			getSqlMap().delete("tbWorkOrder.deleteTbWorkFlowDataByWorkFlowId", workFlowId);
			getSqlMap().delete("tbWorkOrder.deleteTbWorkFlowById", workFlowId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getEntryIdByVmName(String vmName) {
		String entryId = "";
		try {
			List list  = getSqlMap().queryForList("tbWorkOrder.queryForEntryIdByVmName", vmName);
			if (list!=null && list.size()>0)
				entryId = list.get(0).toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entryId;
	}
	
}
