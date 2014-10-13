package com.sitech.basd.sxcloud.cloud.web.workorder_sc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WorkOrderAuditForm implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private List resultList;
	
	// 工单表
	private String UUID;// 工单编号
	private Integer TYPE;// 工单类型：0申请，1调整，2回收
	private String USERNAME;// 工单申请人：
	private String REQUESTDONETIME;// 工单要求完成时间
	private String RECEIVETIME;// 工单接收时间
	private String EXETIME;// 工单处理时间
	private String PROJECT_USER_ID;// 项目责任人id
	private String PROJECT_ID;// 项目id
	private String BOMC_UUID;// bomc传递过来的id
	private Integer STATE;// 工单状态 1不正常 0正常
	private String CAMEFROM;// 工单来源
	private Integer WSTAT;// 工单状态 0未执行 1已执行
	private String WMESSAGE;// 工单信息
	private String BUSISYSTEMID;// 业务系统的id
	private String WORK_ORDER_TITLE; //工单标题
	private String WORK_ORDER_COMM_MSG;//工单备注
	private String RESOURCE_TYPE;//申请资源类型
	
	private String EQ_TYPE;//申请资源类型
	private String EQ_TYPE_ID;//申请资源类型
	private String RESOURCE_NUM;//申请资源数量
	private String maxCounts;//最大申请资源数量
	private String AUDIT_TYPE;//审批状态
	private String busi_pool_id;//资源池ID
	private String busi_pool_name;//资源池名字
	
	// 工单任务表
	private String ID;// 主键
	private Integer CPU_NUM;// cpu数量
	private Integer MEM_SIZE;// 内存大小
	private Double SR_SIZE;// 存储大小
	private String NETWORK_ID;// 网络域id
	private String TEMPLATE_ID;// connect_id + "_" +　templatecode
	private String TEMPLATE_TYPE;// 模板类型 HOST/VM
	private String WORKORDER_ID;// 工单表uuid
	private Integer STATUS;// 状态 0 待处理 1处理中 2处理成功 3处理失败
	private Integer DEAL_COUNT;// 处理次数
	private String ENTITY_ID;// 实体id，(虚拟机或主机id)
	private String DEALTIME;// 处理时间
	private String VM_NAME;// bomc虚拟机名称
	private String IPADDRESS;// 手动执行时，任务制定的ip
	private String MESSAGE;// 信息
	private String HOST_NAME;// 主机名
	private String HOST_ID;// 指定的主机
	private Integer ISREFERHOST;// 是否指定主机
	
	//审批列表
	private Integer id;
	private String workorderid;
	private String audit_type;
	private String audit_date;
	private String audit_info;
	private String audit_user;
	
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public Integer getCPU_NUM() {
		return CPU_NUM;
	}
	public void setCPU_NUM(Integer cpu_num) {
		CPU_NUM = cpu_num;
	}
	public Integer getMEM_SIZE() {
		return MEM_SIZE;
	}
	public void setMEM_SIZE(Integer mem_size) {
		MEM_SIZE = mem_size;
	}
	public Double getSR_SIZE() {
		return SR_SIZE;
	}
	public void setSR_SIZE(Double sr_size) {
		SR_SIZE = sr_size;
	}
	public String getNETWORK_ID() {
		return NETWORK_ID;
	}
	public void setNETWORK_ID(String network_id) {
		NETWORK_ID = network_id;
	}
	public String getTEMPLATE_ID() {
		return TEMPLATE_ID;
	}
	public void setTEMPLATE_ID(String template_id) {
		TEMPLATE_ID = template_id;
	}
	public String getTEMPLATE_TYPE() {
		return TEMPLATE_TYPE;
	}
	public void setTEMPLATE_TYPE(String template_type) {
		TEMPLATE_TYPE = template_type;
	}
	public String getWORKORDER_ID() {
		return WORKORDER_ID;
	}
	public void setWORKORDER_ID(String workorder_id) {
		WORKORDER_ID = workorder_id;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer status) {
		STATUS = status;
	}
	public Integer getDEAL_COUNT() {
		return DEAL_COUNT;
	}
	public void setDEAL_COUNT(Integer deal_count) {
		DEAL_COUNT = deal_count;
	}
	public String getENTITY_ID() {
		return ENTITY_ID;
	}
	public void setENTITY_ID(String entity_id) {
		ENTITY_ID = entity_id;
	}
	public String getDEALTIME() {
		return DEALTIME;
	}
	public void setDEALTIME(String dealtime) {
		DEALTIME = dealtime;
	}
	public String getVM_NAME() {
		return VM_NAME;
	}
	public void setVM_NAME(String vm_name) {
		VM_NAME = vm_name;
	}
	public String getIPADDRESS() {
		return IPADDRESS;
	}
	public void setIPADDRESS(String ipaddress) {
		IPADDRESS = ipaddress;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String message) {
		MESSAGE = message;
	}
	public String getHOST_NAME() {
		return HOST_NAME;
	}
	public void setHOST_NAME(String host_name) {
		HOST_NAME = host_name;
	}
	public String getHOST_ID() {
		return HOST_ID;
	}
	public void setHOST_ID(String host_id) {
		HOST_ID = host_id;
	}
	public Integer getISREFERHOST() {
		return ISREFERHOST;
	}
	public void setISREFERHOST(Integer isreferhost) {
		ISREFERHOST = isreferhost;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uuid) {
		UUID = uuid;
	}
	public Integer getTYPE() {
		return TYPE;
	}
	public void setTYPE(Integer type) {
		TYPE = type;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String username) {
		USERNAME = username;
	}
	public String getREQUESTDONETIME() {
		return REQUESTDONETIME;
	}
	public void setREQUESTDONETIME(String requestdonetime) {
		REQUESTDONETIME = requestdonetime;
	}
	public String getRECEIVETIME() {
		return RECEIVETIME;
	}
	public void setRECEIVETIME(String receivetime) {
		RECEIVETIME = receivetime;
	}
	public String getEXETIME() {
		return EXETIME;
	}
	public void setEXETIME(String exetime) {
		EXETIME = exetime;
	}
	public String getPROJECT_USER_ID() {
		return PROJECT_USER_ID;
	}
	public void setPROJECT_USER_ID(String project_user_id) {
		PROJECT_USER_ID = project_user_id;
	}
	public String getPROJECT_ID() {
		return PROJECT_ID;
	}
	public void setPROJECT_ID(String project_id) {
		PROJECT_ID = project_id;
	}
	public String getBOMC_UUID() {
		return BOMC_UUID;
	}
	public void setBOMC_UUID(String bomc_uuid) {
		BOMC_UUID = bomc_uuid;
	}
	public Integer getSTATE() {
		return STATE;
	}
	public void setSTATE(Integer state) {
		STATE = state;
	}
	public String getCAMEFROM() {
		return CAMEFROM;
	}
	public void setCAMEFROM(String camefrom) {
		CAMEFROM = camefrom;
	}
	public Integer getWSTAT() {
		return WSTAT;
	}
	public void setWSTAT(Integer wstat) {
		WSTAT = wstat;
	}
	public String getWMESSAGE() {
		return WMESSAGE;
	}
	public void setWMESSAGE(String wmessage) {
		WMESSAGE = wmessage;
	}
	public String getBUSISYSTEMID() {
		return BUSISYSTEMID;
	}
	public void setBUSISYSTEMID(String busisystemid) {
		BUSISYSTEMID = busisystemid;
	}
	public String getWORK_ORDER_TITLE() {
		return WORK_ORDER_TITLE;
	}
	public void setWORK_ORDER_TITLE(String work_order_title) {
		WORK_ORDER_TITLE = work_order_title;
	}
	public String getWORK_ORDER_COMM_MSG() {
		return WORK_ORDER_COMM_MSG;
	}
	public void setWORK_ORDER_COMM_MSG(String work_order_comm_msg) {
		WORK_ORDER_COMM_MSG = work_order_comm_msg;
	}
	public String getRESOURCE_TYPE() {
		return RESOURCE_TYPE;
	}
	public void setRESOURCE_TYPE(String resource_type) {
		RESOURCE_TYPE = resource_type;
	}
	public String getEQ_TYPE() {
		return EQ_TYPE;
	}
	public void setEQ_TYPE(String eq_type) {
		EQ_TYPE = eq_type;
	}
	public String getEQ_TYPE_ID() {
		return EQ_TYPE_ID;
	}
	public void setEQ_TYPE_ID(String eq_type_id) {
		EQ_TYPE_ID = eq_type_id;
	}
	public String getRESOURCE_NUM() {
		return RESOURCE_NUM;
	}
	public void setRESOURCE_NUM(String resource_num) {
		RESOURCE_NUM = resource_num;
	}
	public String getMaxCounts() {
		return maxCounts;
	}
	public void setMaxCounts(String maxCounts) {
		this.maxCounts = maxCounts;
	}
	public String getAUDIT_TYPE() {
		return AUDIT_TYPE;
	}
	public void setAUDIT_TYPE(String audit_type) {
		AUDIT_TYPE = audit_type;
	}
	public String getBusi_pool_id() {
		return busi_pool_id;
	}
	public void setBusi_pool_id(String busi_pool_id) {
		this.busi_pool_id = busi_pool_id;
	}
	public String getBusi_pool_name() {
		return busi_pool_name;
	}
	public void setBusi_pool_name(String busi_pool_name) {
		this.busi_pool_name = busi_pool_name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkorderid() {
		return workorderid;
	}
	public void setWorkorderid(String workorderid) {
		this.workorderid = workorderid;
	}
	public String getAudit_type() {
		return audit_type;
	}
	public void setAudit_type(String audit_type) {
		this.audit_type = audit_type;
	}
	public String getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}
	public String getAudit_info() {
		return audit_info;
	}
	public void setAudit_info(String audit_info) {
		this.audit_info = audit_info;
	}
	public String getAudit_user() {
		return audit_user;
	}
	public void setAudit_user(String audit_user) {
		this.audit_user = audit_user;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	
}
