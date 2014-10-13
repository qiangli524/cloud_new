package com.sitech.basd.sxcloud.workflow.web.appworkflow.form;

import java.util.List;


public class AppDeployWorkFlowForm{
	private String NEED_NUMBERS; // 需求编号
	private String NEED_SPONSOR; // 需求发起人
	private String NEED_CONT; // 需求内容
	private String START_DATE; // 需求发起时间
	private String END_DATE; // 需求结束时间
	private int NEED_STATUS; // 需求状态:0 等待处理；1 正在处理；2处理结束
	private String DISPOSE_MAN; // 任务处理人唯一标识,终止节点为NULL值

	private String APP_SIZE; // 应用部署标识
	private String APP_IP; // 应用部署IP标识
	private int APP_IPMODEL = 1; // 应用部署IP模式

	private String COMMAND; // 执行操作
	private String FLOW_TYPE; // 工单的类型
	private String taskId; // 任务编号
	private int processNode = 0; // 节点编号,如发起环节、审批一环节、审批环节二编号
	private String REMARK = null; // 备注
	private String INPUT_REMARK = null; // 输入备注
	private String NODE_NAME = null; // 节点名称
	private int NODE_TYPE = 0; // 节点类型
	private String IMPLEMENT_PERSON = null; // 实施人员ID
	private String NODE_REMARK = null; // 节点备注
	private String NEED_REMARK; // 任务审批意见
	private String TASK_ID;
	private String RECEIVE_TIME;
	private String DISPOSE_TIME;
	private List TASK_OPERATE; // 任务操作，(1:普通操作,2:挂起,3:工单回撤【只能回撤下一节点全然处理的工单】,4:打回)
	private List taskInfoResultList; // 任务详情
	private List resultList; // 申请工单信息
	private List infoResultList; // 申请工单内的详细信息
	private List flowTaskList; // 审批的详细信息
	private int ID; // 应用ID
	private String APPID; // 应用ID
	private String APPNAME; // 应用名称
	private List appList;// 应用部署下拉列表

	private String TEM_ID; // 模板编号
	private String SELECT; // 选择模板
	private String TEM_NAME; // 模板名称
	private String APP_ID; // 应用编号标识
	private String APP_IDVALUE; // 应用编号的值
	private String APP_PORT; // 应用端口标识
	private String APP_PORTVALUE;// 应用端口的值
	private String APP_NAME; // 应用名称标识
	private String APP_NAMEVALUE; // 应用名称的值
	private List templetList; // 模板列表
	private String TYPE; // 模板类型
	private String COUNTIP;
	private String VLANIP; // 服务IP
	private String COUNTVLAN; // 父窗口接受子窗口的vlanip

	private int DEFEND_FLAG; // 是否添加防篡改
	private String DEFEND_DIR; // 添加防篡改的受控目录
	private String IP; // IP地址

	/*
	 * 工作流需要的参数
	 */
	private String TASKID = null; // 当前任务编号

	private int flag;

	private String[] checkboxid;

	private String chks;
	private String vlanchks;

	public String getVlanchks() {
		return vlanchks;
	}

	public void setVlanchks(String vlanchks) {
		this.vlanchks = vlanchks;
	}

	public String getChks() {
		return chks;
	}

	public void setChks(String chks) {
		this.chks = chks;
	}

	public String[] getCheckboxid() {
		return checkboxid;
	}

	public void setCheckboxid(String[] checkboxid) {
		this.checkboxid = checkboxid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String need_numbers) {
		NEED_NUMBERS = need_numbers;
	}

	public String getNEED_SPONSOR() {
		return NEED_SPONSOR;
	}

	public void setNEED_SPONSOR(String need_sponsor) {
		NEED_SPONSOR = need_sponsor;
	}

	public String getNEED_CONT() {
		return NEED_CONT;
	}

	public void setNEED_CONT(String need_cont) {
		NEED_CONT = need_cont;
	}

	public String getSTART_DATE() {
		return START_DATE;
	}

	public void setSTART_DATE(String start_date) {
		START_DATE = start_date;
	}

	public String getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(String end_date) {
		END_DATE = end_date;
	}

	public int getNEED_STATUS() {
		return NEED_STATUS;
	}

	public void setNEED_STATUS(int need_status) {
		NEED_STATUS = need_status;
	}

	public String getAPP_SIZE() {
		return APP_SIZE;
	}

	public void setAPP_SIZE(String app_size) {
		APP_SIZE = app_size;
	}

	public String getAPP_IP() {
		return APP_IP;
	}

	public void setAPP_IP(String app_ip) {
		APP_IP = app_ip;
	}

	public int getAPP_IPMODEL() {
		return APP_IPMODEL;
	}

	public void setAPP_IPMODEL(int app_ipmodel) {
		APP_IPMODEL = app_ipmodel;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String command) {
		COMMAND = command;
	}

	public String getTASKID() {
		return TASKID;
	}

	public void setTASKID(String taskid) {
		TASKID = taskid;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getFLOW_TYPE() {
		return FLOW_TYPE;
	}

	public void setFLOW_TYPE(String flow_type) {
		FLOW_TYPE = flow_type;
	}

	public String getDISPOSE_MAN() {
		return DISPOSE_MAN;
	}

	public void setDISPOSE_MAN(String dispose_man) {
		DISPOSE_MAN = dispose_man;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getProcessNode() {
		return processNode;
	}

	public void setProcessNode(int processNode) {
		this.processNode = processNode;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getINPUT_REMARK() {
		return INPUT_REMARK;
	}

	public void setINPUT_REMARK(String input_remark) {
		INPUT_REMARK = input_remark;
	}

	public String getNODE_NAME() {
		return NODE_NAME;
	}

	public void setNODE_NAME(String node_name) {
		NODE_NAME = node_name;
	}

	public int getNODE_TYPE() {
		return NODE_TYPE;
	}

	public void setNODE_TYPE(int node_type) {
		NODE_TYPE = node_type;
	}

	public String getIMPLEMENT_PERSON() {
		return IMPLEMENT_PERSON;
	}

	public void setIMPLEMENT_PERSON(String implement_person) {
		IMPLEMENT_PERSON = implement_person;
	}

	public String getNODE_REMARK() {
		return NODE_REMARK;
	}

	public void setNODE_REMARK(String node_remark) {
		NODE_REMARK = node_remark;
	}

	public String getNEED_REMARK() {
		return NEED_REMARK;
	}

	public void setNEED_REMARK(String need_remark) {
		NEED_REMARK = need_remark;
	}

	public List getTASK_OPERATE() {
		return TASK_OPERATE;
	}

	public void setTASK_OPERATE(List task_operate) {
		TASK_OPERATE = task_operate;
	}

	public List getTaskInfoResultList() {
		return taskInfoResultList;
	}

	public void setTaskInfoResultList(List taskInfoResultList) {
		this.taskInfoResultList = taskInfoResultList;
	}

	public List getInfoResultList() {
		return infoResultList;
	}

	public void setInfoResultList(List infoResultList) {
		this.infoResultList = infoResultList;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String task_id) {
		TASK_ID = task_id;
	}

	public String getRECEIVE_TIME() {
		return RECEIVE_TIME;
	}

	public void setRECEIVE_TIME(String receive_time) {
		RECEIVE_TIME = receive_time;
	}

	public String getDISPOSE_TIME() {
		return DISPOSE_TIME;
	}

	public void setDISPOSE_TIME(String dispose_time) {
		DISPOSE_TIME = dispose_time;
	}

	public String getSELECT() {
		return SELECT;
	}

	public void setSELECT(String select) {
		SELECT = select;
	}

	public String getTEM_NAME() {
		return TEM_NAME;
	}

	public void setTEM_NAME(String tem_name) {
		TEM_NAME = tem_name;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}

	public List getFlowTaskList() {
		return flowTaskList;
	}

	public void setFlowTaskList(List flowTaskList) {
		this.flowTaskList = flowTaskList;
	}

	public int getDEFEND_FLAG() {
		return DEFEND_FLAG;
	}

	public void setDEFEND_FLAG(int defend_flag) {
		DEFEND_FLAG = defend_flag;
	}

	public String getDEFEND_DIR() {
		return DEFEND_DIR;
	}

	public void setDEFEND_DIR(String defend_dir) {
		DEFEND_DIR = defend_dir;
	}

	public List getTempletList() {
		return templetList;
	}

	public void setTempletList(List templetList) {
		this.templetList = templetList;
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String app_id) {
		APP_ID = app_id;
	}

	public String getAPP_PORT() {
		return APP_PORT;
	}

	public void setAPP_PORT(String app_port) {
		APP_PORT = app_port;
	}

	public String getAPP_NAME() {
		return APP_NAME;
	}

	public void setAPP_NAME(String app_name) {
		APP_NAME = app_name;
	}

	public String getAPP_IDVALUE() {
		return APP_IDVALUE;
	}

	public void setAPP_IDVALUE(String app_idvalue) {
		APP_IDVALUE = app_idvalue;
	}

	public String getAPP_PORTVALUE() {
		return APP_PORTVALUE;
	}

	public void setAPP_PORTVALUE(String app_portvalue) {
		APP_PORTVALUE = app_portvalue;
	}

	public String getAPP_NAMEVALUE() {
		return APP_NAMEVALUE;
	}

	public void setAPP_NAMEVALUE(String app_namevalue) {
		APP_NAMEVALUE = app_namevalue;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getAPPID() {
		return APPID;
	}

	public void setAPPID(String appid) {
		APPID = appid;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String appname) {
		APPNAME = appname;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getCOUNTIP() {
		return COUNTIP;
	}

	public void setCOUNTIP(String countip) {
		COUNTIP = countip;
	}

	public String getVLANIP() {
		return VLANIP;
	}

	public void setVLANIP(String vlanip) {
		VLANIP = vlanip;
	}

	public String getCOUNTVLAN() {
		return COUNTVLAN;
	}

	public void setCOUNTVLAN(String countvlan) {
		COUNTVLAN = countvlan;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

}
