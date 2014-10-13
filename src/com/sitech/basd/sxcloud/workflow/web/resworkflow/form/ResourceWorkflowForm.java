package com.sitech.basd.sxcloud.workflow.web.resworkflow.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.workflow.domain.engine.FlowTaskObj;
import com.sitech.basd.sxcloud.workflow.domain.resourceorder.ResourceOrderInfoObj;

public class ResourceWorkflowForm {
	private String NEED_NUMBERS; // 需求编号
	private String NEED_SPONSOR = null; // 需求发起人
	private String NEED_STATUS = null; // 需求状态:0 等待处理；1 正在处理；2处理结束
	private String start_time = null; // 开始时间
	private String end_time = null; // 终止时间
	private String COMMAND = null; // 执行操作
	private String TASKID = null; // 当前任务编号
	private String TEM_NAME;// 模板名称
	private String TEM_DESC;// 模板描述
	private String TEM_ID;// 模板ID
	private String SELECT;// 所选择的对象
	private String TYPE; // 模板类型
	private String[] CONFIG_NAME; // 配置项
	private String[] CONFIG_VALUE; // 配置值
	private int flag = 0;
	private String VH_CPUVALUE; // cpu的值
	private String VH_CPU = "0"; // cpu
	private String VH_MEM = "0"; // 内存
	private String VH_MEMVALUE; // 内存的值
	private String RELEASE_FLAG; // 模板发布状态
	private String VH_MAX_CPU = "0"; // CPU最大值
	private String VH_MAX_CPUVALUE;
	private String IMAGE_ID = "0"; // 虚拟映像ID
	private String IMAGE_IDVALUE;
	private String VH_MIN_CPU = "0"; // CPU最小值
	private String VH_MIN_CPUVALUE;
	private String VH_MAX_MEM = "0"; // 内存最大值
	private String VH_MAX_MEMVALUE;
	private String VH_MIN_MEM = "0"; // 内存最小值
	private String VH_MIN_MEMVALUE;
	private List typeList; // 资源类别下拉列表
	private String OPERTYPE; // 模板操作类别
	/** huojl */
	private String FLOW_TYPE;
	private List needResultList;
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
	private List<Integer> TASK_OPERATE;
	private List TaskInfoResultList;
	private List infoResultList;
	private String content;// 具体内容
	private String TYPE_ID;// 资源类别ID
	private String TYPE_NAME;// 资源类别名称
	private List templetList;// 选取的资源类别所对应模板列表
	private String VH_PROCESS_UNIT; // CPU处理单元(0.1单位)
	private String VH_PROCESS_UNITVALUE; // 存储CPU处理单元
	private String VH_MAX_PROCESS_UNIT;// CPU处理单元最大值(0.1单位)
	private String VH_MAX_PROCESS_UNITVALUE; // 用来存储CPU处理单元最大值(0.1单位)
	private String VH_MIN_PROCESS_UNIT; // CPU处理单元最小值
	private String VH_MIN_PROCESS_UNITVALUE; // 用来存储CPU处理单元最小值
	private String VH_STORAGE; // 存储(MB)
	private String VH_STORAGEVALUE; // 用来保存输入的存储的值
	private List virtualList;// 虚拟机列表
	private String VH_ID_IBM;
	private String VH_NAME;// 虚拟机名称
	private String VH_DESC;// 虚拟机描述
	private String VH_STAT;// 虚拟机状态
	private String APPLY_Type;//申请类别
	
	public String getAPPLY_Type() {
		return APPLY_Type;
	}

	public void setAPPLY_Type(String aPPLYType) {
		APPLY_Type = aPPLYType;
	}

	private List<TbCloud2VirtualInfoObj> tbVirtualList;// 获取所申请的虚拟机列表

	public List<TbCloud2VirtualInfoObj> getTbVirtualList() {
		return tbVirtualList;
	}

	public void setTbVirtualList(List<TbCloud2VirtualInfoObj> tbVirtualList) {
		this.tbVirtualList = tbVirtualList;
	}

	public String getVH_ID_IBM() {
		return VH_ID_IBM;
	}

	public void setVH_ID_IBM(String vh_id_ibm) {
		VH_ID_IBM = vh_id_ibm;
	}

	public List getVirtualList() {
		return virtualList;
	}

	public void setVirtualList(List virtualList) {
		this.virtualList = virtualList;
	}

	public String getVH_PROCESS_UNIT() {
		return VH_PROCESS_UNIT;
	}

	public void setVH_PROCESS_UNIT(String vh_process_unit) {
		VH_PROCESS_UNIT = vh_process_unit;
	}

	public String getVH_PROCESS_UNITVALUE() {
		return VH_PROCESS_UNITVALUE;
	}

	public void setVH_PROCESS_UNITVALUE(String vh_process_unitvalue) {
		VH_PROCESS_UNITVALUE = vh_process_unitvalue;
	}

	public String getVH_MAX_PROCESS_UNIT() {
		return VH_MAX_PROCESS_UNIT;
	}

	public void setVH_MAX_PROCESS_UNIT(String vh_max_process_unit) {
		VH_MAX_PROCESS_UNIT = vh_max_process_unit;
	}

	public String getVH_MAX_PROCESS_UNITVALUE() {
		return VH_MAX_PROCESS_UNITVALUE;
	}

	public void setVH_MAX_PROCESS_UNITVALUE(String vh_max_process_unitvalue) {
		VH_MAX_PROCESS_UNITVALUE = vh_max_process_unitvalue;
	}

	public String getVH_MIN_PROCESS_UNIT() {
		return VH_MIN_PROCESS_UNIT;
	}

	public void setVH_MIN_PROCESS_UNIT(String vh_min_process_unit) {
		VH_MIN_PROCESS_UNIT = vh_min_process_unit;
	}

	public String getVH_MIN_PROCESS_UNITVALUE() {
		return VH_MIN_PROCESS_UNITVALUE;
	}

	public void setVH_MIN_PROCESS_UNITVALUE(String vh_min_process_unitvalue) {
		VH_MIN_PROCESS_UNITVALUE = vh_min_process_unitvalue;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getVH_STORAGEVALUE() {
		return VH_STORAGEVALUE;
	}

	public void setVH_STORAGEVALUE(String vh_storagevalue) {
		VH_STORAGEVALUE = vh_storagevalue;
	}

	public List getTempletList() {
		return templetList;
	}

	public void setTempletList(List templetList) {
		this.templetList = templetList;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List getInfoResultList() {
		return infoResultList;
	}

	public void setInfoResultList(List infoResultList) {
		this.infoResultList = infoResultList;
	}

	public List getTaskInfoResultList() {
		return TaskInfoResultList;
	}

	public void setTaskInfoResultList(List taskInfoResultList) {
		TaskInfoResultList = taskInfoResultList;
	}

	public List<Integer> getTASK_OPERATE() {
		return TASK_OPERATE;
	}

	public void setTASK_OPERATE(List<Integer> task_operate) {
		TASK_OPERATE = task_operate;
	}

	public String getFLOW_TYPE() {
		return FLOW_TYPE;
	}

	public void setFLOW_TYPE(String flow_type) {
		FLOW_TYPE = flow_type;
	}

	public String getSELECT() {
		return SELECT;
	}

	public void setSELECT(String select) {
		this.SELECT = select;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String[] getCONFIG_NAME() {
		return CONFIG_NAME;
	}

	public void setCONFIG_NAME(String[] cONFIGNAME) {
		CONFIG_NAME = cONFIGNAME;
	}

	public String[] getCONFIG_VALUE() {
		return CONFIG_VALUE;
	}

	public void setCONFIG_VALUE(String[] cONFIGVALUE) {
		CONFIG_VALUE = cONFIGVALUE;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getVH_CPUVALUE() {
		return VH_CPUVALUE;
	}

	public void setVH_CPUVALUE(String vh_cpuvalue) {
		VH_CPUVALUE = vh_cpuvalue;
	}

	public String getVH_MEMVALUE() {
		return VH_MEMVALUE;
	}

	public void setVH_MEMVALUE(String vh_memvalue) {
		VH_MEMVALUE = vh_memvalue;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String rELEASEFLAG) {
		RELEASE_FLAG = rELEASEFLAG;
	}

	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}

	public void setVH_MAX_CPU(String vHMAXCPU) {
		VH_MAX_CPU = vHMAXCPU;
	}

	public String getVH_MAX_CPUVALUE() {
		return VH_MAX_CPUVALUE;
	}

	public void setVH_MAX_CPUVALUE(String vHMAXCPUVALUE) {
		VH_MAX_CPUVALUE = vHMAXCPUVALUE;
	}

	public String getIMAGE_ID() {
		return IMAGE_ID;
	}

	public void setIMAGE_ID(String iMAGEID) {
		IMAGE_ID = iMAGEID;
	}

	public String getIMAGE_IDVALUE() {
		return IMAGE_IDVALUE;
	}

	public void setIMAGE_IDVALUE(String iMAGEIDVALUE) {
		IMAGE_IDVALUE = iMAGEIDVALUE;
	}

	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}

	public void setVH_MIN_CPU(String vHMINCPU) {
		VH_MIN_CPU = vHMINCPU;
	}

	public String getVH_MIN_CPUVALUE() {
		return VH_MIN_CPUVALUE;
	}

	public void setVH_MIN_CPUVALUE(String vHMINCPUVALUE) {
		VH_MIN_CPUVALUE = vHMINCPUVALUE;
	}

	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}

	public void setVH_MAX_MEM(String vHMAXMEM) {
		VH_MAX_MEM = vHMAXMEM;
	}

	public String getVH_MAX_MEMVALUE() {
		return VH_MAX_MEMVALUE;
	}

	public void setVH_MAX_MEMVALUE(String vHMAXMEMVALUE) {
		VH_MAX_MEMVALUE = vHMAXMEMVALUE;
	}

	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}

	public void setVH_MIN_MEM(String vHMINMEM) {
		VH_MIN_MEM = vHMINMEM;
	}

	public String getVH_MIN_MEMVALUE() {
		return VH_MIN_MEMVALUE;
	}

	public void setVH_MIN_MEMVALUE(String vHMINMEMVALUE) {
		VH_MIN_MEMVALUE = vHMINMEMVALUE;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public String getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(String oPERTYPE) {
		OPERTYPE = oPERTYPE;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String tYPENAME) {
		TYPE_NAME = tYPENAME;
	}

	public String getTEM_NAME() {
		return TEM_NAME;
	}

	public void setTEM_NAME(String tEMNAME) {
		TEM_NAME = tEMNAME;
	}

	public String getTEM_DESC() {
		return TEM_DESC;
	}

	public void setTEM_DESC(String tEMDESC) {
		TEM_DESC = tEMDESC;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tEMID) {
		TEM_ID = tEMID;
	}

	private List<ResourceOrderInfoObj> resultList;
	private String[] chk;
	private List<FlowTaskObj> flowTaskList = null;// 当前工单的任务审批流程列表

	public List<FlowTaskObj> getFlowTaskList() {
		return flowTaskList;
	}

	public void setFlowTaskList(List<FlowTaskObj> flowTaskList) {
		this.flowTaskList = flowTaskList;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public List<ResourceOrderInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResourceOrderInfoObj> resultList) {
		this.resultList = resultList;
	}

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String nEEDNUMBERS) {
		NEED_NUMBERS = nEEDNUMBERS;
	}

	public String getNEED_SPONSOR() {
		return NEED_SPONSOR;
	}

	public void setNEED_SPONSOR(String nEEDSPONSOR) {
		NEED_SPONSOR = nEEDSPONSOR;
	}

	public String getNEED_STATUS() {
		return NEED_STATUS;
	}

	public void setNEED_STATUS(String nEEDSTATUS) {
		NEED_STATUS = nEEDSTATUS;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String cOMMAND) {
		COMMAND = cOMMAND;
	}

	public String getTASKID() {
		return TASKID;
	}

	public void setTASKID(String tASKID) {
		TASKID = tASKID;
	}

	public List getNeedResultList() {
		return needResultList;
	}

	public void setNeedResultList(List needResultList) {
		this.needResultList = needResultList;
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

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getVH_DESC() {
		return VH_DESC;
	}

	public void setVH_DESC(String vh_desc) {
		VH_DESC = vh_desc;
	}

	public String getVH_STAT() {
		return VH_STAT;
	}

	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}

}
