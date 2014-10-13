package com.sitech.basd.component.domain.process;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ProcessObj extends BaseObj {
	private String ID; // 进程的ID
	private String PROCESS;// 进程名称
	private String PROCESS_KEY;// 进程的唯一标识
	private Integer ISRUNNING;// 进程当前运行的状态
	private Integer OPERATION;// 进程的操作
	private Integer TAST_TYPE;// 任务类型
	private Integer PROCESS_STATE;// 进程状态
	private Integer PROCESS_LEVEL;// 进程级别
	private String USER_ID;// 用户表ID
	private String START_SCRIPT;// 启动脚本
	private String STOP_SCRIPT;// 停止脚本
	private String ADD_TIME;// 添加时间
	private String UPDATE_TIME;// 更新时间
	private String START_TIME;// 启动时间
	private String STOP_TIME;// 停止时间
	private String PROCESS_DESC;// 进程描述
	private String ADD_USER;// 添加者
	private String UPDATE_USER;// 更新者
	private Integer TYPE;// 0，通用，1部署管理
	private String IP;// 主机的ip地址
	private String USERNAME;// 主机的用户名
	private String PROCESS_GROUP_ID;// 进程组的id
	private String EXAMPLE_ID;// 部署实例或应用的ID
	private String TASK_ID;// 任务id，订单关联的任务id
	private Integer APPID;// 基准应用的id
	private String PARENT_ID;// 父节点的id
	private Integer LEVEL;// 进程级别
	private Integer PROCESS_COUNT;// 同名进程个数
	private String ENTITY_ID;// 实例的id
	private Integer PROCESS_COUNT_ACTUAL;
	// 部署实例ID转码后拼接，已,分割，用于查询业务中心树中基准应用上脚本列表
	private String encodeExampleStr;
	// 部署实例名称
	private String example_name;
	// 节点ID List
	private List nodeIdList;
	private int allcount;//总个数
	private int normalcount;//正常个数
	//add by qism 
	private Integer IS_CHECK;//该进程是否添加验证
	
	public Integer getIS_CHECK() {
		return IS_CHECK;
	}

	public void setIS_CHECK(Integer iS_CHECK) {
		IS_CHECK = iS_CHECK;
	}


	public List getNodeIdList() {
		return nodeIdList;
	}

	public void setNodeIdList(List nodeIdList) {
		this.nodeIdList = nodeIdList;
	}

	public String getExample_name() {
		return example_name;
	}

	public void setExample_name(String example_name) {
		this.example_name = example_name;
	}

	public Integer getPROCESS_COUNT_ACTUAL() {
		return PROCESS_COUNT_ACTUAL;
	}

	public void setPROCESS_COUNT_ACTUAL(Integer pROCESS_COUNT_ACTUAL) {
		PROCESS_COUNT_ACTUAL = pROCESS_COUNT_ACTUAL;
	}

	public String getEncodeExampleStr() {
		return encodeExampleStr;
	}

	public void setEncodeExampleStr(String encodeExampleStr) {
		this.encodeExampleStr = encodeExampleStr;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
	}

	public Integer getPROCESS_COUNT() {
		return PROCESS_COUNT;
	}

	public void setPROCESS_COUNT(Integer pROCESS_COUNT) {
		PROCESS_COUNT = pROCESS_COUNT;
	}

	public Integer getLEVEL() {
		return LEVEL;
	}

	public void setLEVEL(Integer lEVEL) {
		LEVEL = lEVEL;
	}

	public String getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}

	public Integer getAPPID() {
		return APPID;
	}

	public void setAPPID(Integer aPPID) {
		APPID = aPPID;
	}

	public String getPROCESS_KEY() {
		return PROCESS_KEY;
	}

	public void setPROCESS_KEY(String pROCESS_KEY) {
		PROCESS_KEY = pROCESS_KEY;
	}

	public String getPROCESS_DESC() {
		return PROCESS_DESC;
	}

	public void setPROCESS_DESC(String pROCESSDESC) {
		PROCESS_DESC = pROCESSDESC;
	}

	public String getADD_USER() {
		return ADD_USER;
	}

	public void setADD_USER(String aDDUSER) {
		ADD_USER = aDDUSER;
	}

	public String getUPDATE_USER() {
		return UPDATE_USER;
	}

	public void setUPDATE_USER(String uPDATEUSER) {
		UPDATE_USER = uPDATEUSER;
	}

	public String getEXAMPLE_ID() {
		return EXAMPLE_ID;
	}

	public void setEXAMPLE_ID(String eXAMPLEID) {
		EXAMPLE_ID = eXAMPLEID;
	}

	public String getPROCESS_GROUP_ID() {
		return PROCESS_GROUP_ID;
	}

	public void setPROCESS_GROUP_ID(String pROCESSGROUPID) {
		PROCESS_GROUP_ID = pROCESSGROUPID;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public ProcessObj() {
		super();
	}

	public Integer getTYPE() {
		return TYPE;
	}

	public void setTYPE(Integer tYPE) {
		TYPE = tYPE;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPROCESS() {
		return PROCESS;
	}

	public void setPROCESS(String pROCESS) {
		PROCESS = pROCESS;
	}

	public Integer getISRUNNING() {
		return ISRUNNING;
	}

	public void setISRUNNING(Integer iSRUNNING) {
		ISRUNNING = iSRUNNING;
	}

	public Integer getOPERATION() {
		return OPERATION;
	}

	public void setOPERATION(Integer oPERATION) {
		OPERATION = oPERATION;
	}

	public Integer getTAST_TYPE() {
		return TAST_TYPE;
	}

	public void setTAST_TYPE(Integer tAST_TYPE) {
		TAST_TYPE = tAST_TYPE;
	}

	public Integer getPROCESS_STATE() {
		return PROCESS_STATE;
	}

	public void setPROCESS_STATE(Integer pROCESS_STATE) {
		PROCESS_STATE = pROCESS_STATE;
	}

	public Integer getPROCESS_LEVEL() {
		return PROCESS_LEVEL;
	}

	public void setPROCESS_LEVEL(Integer pROCESS_LEVEL) {
		PROCESS_LEVEL = pROCESS_LEVEL;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getSTART_SCRIPT() {
		return START_SCRIPT;
	}

	public void setSTART_SCRIPT(String sTART_SCRIPT) {
		START_SCRIPT = sTART_SCRIPT;
	}

	public String getSTOP_SCRIPT() {
		return STOP_SCRIPT;
	}

	public void setSTOP_SCRIPT(String sTOP_SCRIPT) {
		STOP_SCRIPT = sTOP_SCRIPT;
	}

	public String getADD_TIME() {
		return ADD_TIME;
	}

	public void setADD_TIME(String aDDTIME) {
		ADD_TIME = aDDTIME;
	}

	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}

	public void setUPDATE_TIME(String uPDATETIME) {
		UPDATE_TIME = uPDATETIME;
	}

	public String getSTART_TIME() {
		return START_TIME;
	}

	public void setSTART_TIME(String sTARTTIME) {
		START_TIME = sTARTTIME;
	}

	public String getSTOP_TIME() {
		return STOP_TIME;
	}

	public void setSTOP_TIME(String sTOPTIME) {
		STOP_TIME = sTOPTIME;
	}

	public int getAllcount() {
		return allcount;
	}

	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}

	public int getNormalcount() {
		return normalcount;
	}

	public void setNormalcount(int normalcount) {
		this.normalcount = normalcount;
	}
	
}