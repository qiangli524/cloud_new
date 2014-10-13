package com.sitech.basd.sxcloud.cloud.domain.workorder;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: WorkOrderObj
 * </p>
 * <p>
 * Description: bomc工单资源联合实体
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-22 下午1:11:30
 * 
 */
public class WorkOrderObj extends BasePrivilegeObj {

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

	private String PROJECT_NAME;// 项目名称
	private String NETWORK_NAME;// 网络名称
	private Integer ALLCOUNT;// 任务总数
	private Integer SUCCESS;// 成功任务个数
	private Integer FALUIRE;// 失败任务个数
	private Integer WAITDEAL;// 待处理任务个数
	private Integer DEALING;// 处理中任务个数
	private Integer UNMOUNTSTORE;// 创建成功未挂存储个数
	private String TEMPLATENAME;// 模板名称
	private String PROJECT_USER_NAME;// 项目责任人名称
	private String SHOWNAME;// 申请人显示名称
	private String BUSISYSTEMNAME;// 工单所属业务系统名称
	private String CLUSTERNAME;// 指定的主机所属集群名称
	private String CONNECT_ID;// 链接id
	private String CLUSTERID;// 指定主机所属集群id

	/** 工单资源对应的业务id */
	private String resBusiSytemId;
	/** 工单资源对应的业务名称 */
	private String resBusiSystemName;// 资源所属业务系统名称，bomc接口中申请的资源所属业务系统名称（和工单所属业务系统名称的却别）
	/** */
	private String resParentBusiSystemName;
	private String resParentBusiSystemiId;
	/** 资源应用目录 */
	private String resAppDir;
	/** 资源应用大小，单位M */
	private String resAppSize;
	/** 模板类型 vmware 1、xen 2 */
	private String TEMP_TYPE;

	private Integer proGress;//进度0-100默认为null
	
	
	/**
	 *
	 * @return the proGress
	 */
	public Integer getProGress() {
		return proGress;
	}

	/**
	 *
	 * @param proGress the proGress to set
	 */
	public void setProGress(Integer proGress) {
		this.proGress = proGress;
	}

	public String getHOST_NAME() {
		return HOST_NAME;
	}

	public void setHOST_NAME(String hOST_NAME) {
		HOST_NAME = hOST_NAME;
	}

	public String getSHOWNAME() {
		return SHOWNAME;
	}

	public void setSHOWNAME(String sHOWNAME) {
		SHOWNAME = sHOWNAME;
	}

	public String getPROJECT_USER_NAME() {
		return PROJECT_USER_NAME;
	}

	public void setPROJECT_USER_NAME(String pROJECT_USER_NAME) {
		PROJECT_USER_NAME = pROJECT_USER_NAME;
	}

	public String getTEMPLATENAME() {
		return TEMPLATENAME;
	}

	public void setTEMPLATENAME(String tEMPLATENAME) {
		TEMPLATENAME = tEMPLATENAME;
	}

	public String getWMESSAGE() {
		return WMESSAGE;
	}

	public void setWMESSAGE(String wMESSAGE) {
		WMESSAGE = wMESSAGE;
	}

	public Integer getWSTAT() {
		return WSTAT;
	}

	public void setWSTAT(Integer wSTAT) {
		WSTAT = wSTAT;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Integer getTYPE() {
		return TYPE;
	}

	public void setTYPE(Integer tYPE) {
		TYPE = tYPE;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getREQUESTDONETIME() {
		return REQUESTDONETIME;
	}

	public void setREQUESTDONETIME(String rEQUESTDONETIME) {
		REQUESTDONETIME = rEQUESTDONETIME;
	}

	public String getRECEIVETIME() {
		return RECEIVETIME;
	}

	public void setRECEIVETIME(String rECEIVETIME) {
		RECEIVETIME = rECEIVETIME;
	}

	public String getEXETIME() {
		return EXETIME;
	}

	public void setEXETIME(String eXETIME) {
		EXETIME = eXETIME;
	}

	public String getPROJECT_USER_ID() {
		return PROJECT_USER_ID;
	}

	public void setPROJECT_USER_ID(String pROJECT_USER_ID) {
		PROJECT_USER_ID = pROJECT_USER_ID;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}

	public String getBOMC_UUID() {
		return BOMC_UUID;
	}

	public void setBOMC_UUID(String bOMC_UUID) {
		BOMC_UUID = bOMC_UUID;
	}

	public Integer getSTATE() {
		return STATE;
	}

	public void setSTATE(Integer sTATE) {
		STATE = sTATE;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getCPU_NUM() {
		return CPU_NUM;
	}

	public void setCPU_NUM(Integer cPU_NUM) {
		CPU_NUM = cPU_NUM;
	}

	public Integer getMEM_SIZE() {
		return MEM_SIZE;
	}

	public void setMEM_SIZE(Integer mEM_SIZE) {
		MEM_SIZE = mEM_SIZE;
	}

	public Double getSR_SIZE() {
		return SR_SIZE;
	}

	public void setSR_SIZE(Double sR_SIZE) {
		SR_SIZE = sR_SIZE;
	}

	public String getNETWORK_ID() {
		return NETWORK_ID;
	}

	public void setNETWORK_ID(String nETWORK_ID) {
		NETWORK_ID = nETWORK_ID;
	}

	public String getTEMPLATE_ID() {
		return TEMPLATE_ID;
	}

	public void setTEMPLATE_ID(String tEMPLATE_ID) {
		TEMPLATE_ID = tEMPLATE_ID;
	}

	public String getTEMPLATE_TYPE() {
		return TEMPLATE_TYPE;
	}

	public void setTEMPLATE_TYPE(String tEMPLATE_TYPE) {
		TEMPLATE_TYPE = tEMPLATE_TYPE;
	}

	public String getWORKORDER_ID() {
		return WORKORDER_ID;
	}

	public void setWORKORDER_ID(String wORKORDER_ID) {
		WORKORDER_ID = wORKORDER_ID;
	}

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public Integer getDEAL_COUNT() {
		return DEAL_COUNT;
	}

	public void setDEAL_COUNT(Integer dEAL_COUNT) {
		DEAL_COUNT = dEAL_COUNT;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
	}

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public String getNETWORK_NAME() {
		return NETWORK_NAME;
	}

	public void setNETWORK_NAME(String nETWORK_NAME) {
		NETWORK_NAME = nETWORK_NAME;
	}

	public String getDEALTIME() {
		return DEALTIME;
	}

	public void setDEALTIME(String dEALTIME) {
		DEALTIME = dEALTIME;
	}

	public String getVM_NAME() {
		return VM_NAME;
	}

	public void setVM_NAME(String vM_NAME) {
		VM_NAME = vM_NAME;
	}

	public String getCAMEFROM() {
		return CAMEFROM;
	}

	public void setCAMEFROM(String cAMEFROM) {
		CAMEFROM = cAMEFROM;
	}

	public String getIPADDRESS() {
		return IPADDRESS;
	}

	public void setIPADDRESS(String iPADDRESS) {
		IPADDRESS = iPADDRESS;
	}

	public Integer getALLCOUNT() {
		return ALLCOUNT;
	}

	public void setALLCOUNT(Integer aLLCOUNT) {
		ALLCOUNT = aLLCOUNT;
	}

	public Integer getSUCCESS() {
		return SUCCESS;
	}

	public void setSUCCESS(Integer sUCCESS) {
		SUCCESS = sUCCESS;
	}

	public Integer getFALUIRE() {
		return FALUIRE;
	}

	public void setFALUIRE(Integer fALUIRE) {
		FALUIRE = fALUIRE;
	}

	public Integer getWAITDEAL() {
		return WAITDEAL;
	}

	public void setWAITDEAL(Integer wAITDEAL) {
		WAITDEAL = wAITDEAL;
	}

	public Integer getDEALING() {
		return DEALING;
	}

	public void setDEALING(Integer dEALING) {
		DEALING = dEALING;
	}

	public String getBUSISYSTEMID() {
		return BUSISYSTEMID;
	}

	public void setBUSISYSTEMID(String bUSISYSTEMID) {
		BUSISYSTEMID = bUSISYSTEMID;
	}

	public String getBUSISYSTEMNAME() {
		return BUSISYSTEMNAME;
	}

	public void setBUSISYSTEMNAME(String bUSISYSTEMNAME) {
		BUSISYSTEMNAME = bUSISYSTEMNAME;
	}

	public String getResBusiSystemName() {
		return resBusiSystemName;
	}

	public void setResBusiSystemName(String busiSystemName) {
		this.resBusiSystemName = busiSystemName;
	}

	public String getResAppDir() {
		return resAppDir;
	}

	public void setResAppDir(String resAppDir) {
		this.resAppDir = resAppDir;
	}

	public String getResAppSize() {
		return resAppSize;
	}

	public void setResAppSize(String resAppSize) {
		this.resAppSize = resAppSize;
	}

	public String getResBusiSytemId() {
		return resBusiSytemId;
	}

	public void setResBusiSytemId(String resBusiSytemId) {
		this.resBusiSytemId = resBusiSytemId;
	}

	public String getResParentBusiSystemName() {
		return resParentBusiSystemName;
	}

	public void setResParentBusiSystemName(String resParentBusiSystemName) {
		this.resParentBusiSystemName = resParentBusiSystemName;
	}

	public String getResParentBusiSystemiId() {
		return resParentBusiSystemiId;
	}

	public void setResParentBusiSystemiId(String resParentBusiSystemiId) {
		this.resParentBusiSystemiId = resParentBusiSystemiId;
	}

	public String getTEMP_TYPE() {
		return TEMP_TYPE;
	}

	public void setTEMP_TYPE(String tEMP_TYPE) {
		TEMP_TYPE = tEMP_TYPE;
	}

	public Integer getUNMOUNTSTORE() {
		return UNMOUNTSTORE;
	}

	public void setUNMOUNTSTORE(Integer uNMOUNTSTORE) {
		UNMOUNTSTORE = uNMOUNTSTORE;
	}

	public String getHOST_ID() {
		return HOST_ID;
	}

	public void setHOST_ID(String hOST_ID) {
		HOST_ID = hOST_ID;
	}

	public Integer getISREFERHOST() {
		return ISREFERHOST;
	}

	public void setISREFERHOST(Integer iSREFERHOST) {
		ISREFERHOST = iSREFERHOST;
	}

	public String getCLUSTERNAME() {
		return CLUSTERNAME;
	}

	public void setCLUSTERNAME(String cLUSTERNAME) {
		CLUSTERNAME = cLUSTERNAME;
	}

	public String getCONNECT_ID() {
		return CONNECT_ID;
	}

	public void setCONNECT_ID(String cONNECT_ID) {
		CONNECT_ID = cONNECT_ID;
	}

	public String getCLUSTERID() {
		return CLUSTERID;
	}

	public void setCLUSTERID(String cLUSTERID) {
		CLUSTERID = cLUSTERID;
	}

	public String getWORK_ORDER_TITLE() {
		return WORK_ORDER_TITLE;
	}

	public void setWORK_ORDER_TITLE(String wORK_ORDER_TITLE) {
		WORK_ORDER_TITLE = wORK_ORDER_TITLE;
	}

	public String getWORK_ORDER_COMM_MSG() {
		return WORK_ORDER_COMM_MSG;
	}

	public void setWORK_ORDER_COMM_MSG(String wORK_ORDER_COMM_MSG) {
		WORK_ORDER_COMM_MSG = wORK_ORDER_COMM_MSG;
	}

	
}
