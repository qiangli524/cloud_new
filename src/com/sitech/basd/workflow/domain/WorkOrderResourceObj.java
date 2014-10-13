package com.sitech.basd.workflow.domain;

public class WorkOrderResourceObj {

	// 工单任务表
	private String ID;// 主键
	private Integer CPU_NUM;// cpu数量
	private Integer MEM_SIZE;// 内存大小
	private Double SR_SIZE;// 存储大小
	private String TEMPLATE_ID;// vcentercode+"_"+templatecode
	private String TEMPLATE_TYPE;// 模板类型 "HOST" "VM"
	private Integer STATUS;// 任务状态 0 待处理 1处理中 2处理成功 3处理失败
	private String WORKORDER_ID;// 工单表id
	private Integer DEAL_COUNT;// 处理次数
	private String ENTITY_ID; // 虚拟机id
	private String DEALTIME;// 任务处理时间
	private String MESSAGE;// 信息
	private String IPADDRESS;// ip地址
	private String HOST_ID;// 虚拟机创建所在的主机
	private String PROJECT_USER_ID;// 责任人id
	private String PROJECT_ID;// 项目id
	private Integer ISREFERHOST;// 是否指定主机 0 no 1 yes

	// 虚拟机表
	private String VM_NAME; // 虚拟机名称
	private String VM_IP;// 虚拟机ip
	private String VM_SYSTEM;// 虚拟机系统

	// 网络表
	private String NETWORK_ID;// 网络域id
	private String NETWORK_NAME;// 网络域名称

	// 工单表
	private String UUID;// 工单编号
	private String BOMC_UUID;// bomc传递过来的工单id
	private Integer TYPE;// 工单类型：0申请，1调整，2回收
	private String REQUESTDONETIME;// 工单要求完成时间
	private String RECEIVETIME;// 工单接收时间
	private String EXETIME;// 工单处理时间
	private Integer STATE;// 工单是否正常 0正常 1异常
	private Integer WSTAT;// 工单状态，0 未处理 1已处理
	private String CAMEFROM;// 工单来源
	private String WMESSAGE;// 工单处理信息
	private String WORK_ORDER_TITLE; //工单标题
	private String WORK_ORDER_COMM_MSG;//工单备注

	/** 资源所属的业务系统，注意和BUSISYSTEMID（工单表）字段的区别 */
	private String BUSI_ID;
	/** 资源挂在目录 */
	private String APP_Dir;
	/** 资源挂载的大小 */
	private String APP_SIZE;

	public String getWMESSAGE() {
		return WMESSAGE;
	}

	public void setWMESSAGE(String wMESSAGE) {
		WMESSAGE = wMESSAGE;
	}

	public String getIPADDRESS() {
		return IPADDRESS;
	}

	public void setIPADDRESS(String iPADDRESS) {
		IPADDRESS = iPADDRESS;
	}

	public Integer getWSTAT() {
		return WSTAT;
	}

	public void setWSTAT(Integer wSTAT) {
		WSTAT = wSTAT;
	}

	public String getCAMEFROM() {
		return CAMEFROM;
	}

	public void setCAMEFROM(String cAMEFROM) {
		CAMEFROM = cAMEFROM;
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

	public Integer getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}

	public String getWORKORDER_ID() {
		return WORKORDER_ID;
	}

	public void setWORKORDER_ID(String wORKORDER_ID) {
		WORKORDER_ID = wORKORDER_ID;
	}

	public Integer getDEAL_COUNT() {
		return DEAL_COUNT;
	}

	public void setDEAL_COUNT(Integer dEAL_COUNT) {
		DEAL_COUNT = dEAL_COUNT;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getBOMC_UUID() {
		return BOMC_UUID;
	}

	public void setBOMC_UUID(String bOMC_UUID) {
		BOMC_UUID = bOMC_UUID;
	}

	public Integer getTYPE() {
		return TYPE;
	}

	public void setTYPE(Integer tYPE) {
		TYPE = tYPE;
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

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public Integer getSTATE() {
		return STATE;
	}

	public void setSTATE(Integer sTATE) {
		STATE = sTATE;
	}

	public String getENTITY_ID() {
		return ENTITY_ID;
	}

	public void setENTITY_ID(String eNTITY_ID) {
		ENTITY_ID = eNTITY_ID;
	}

	public String getVM_NAME() {
		return VM_NAME;
	}

	public void setVM_NAME(String vM_NAME) {
		VM_NAME = vM_NAME;
	}

	public String getVM_IP() {
		return VM_IP;
	}

	public void setVM_IP(String vM_IP) {
		VM_IP = vM_IP;
	}

	public String getVM_SYSTEM() {
		return VM_SYSTEM;
	}

	public void setVM_SYSTEM(String vM_SYSTEM) {
		VM_SYSTEM = vM_SYSTEM;
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

	public String getBUSI_ID() {
		return BUSI_ID;
	}

	public void setBUSI_ID(String bUSI_ID) {
		BUSI_ID = bUSI_ID;
	}

	public String getAPP_Dir() {
		return APP_Dir;
	}

	public void setAPP_Dir(String aPP_Dir) {
		APP_Dir = aPP_Dir;
	}

	public String getAPP_SIZE() {
		return APP_SIZE;
	}

	public void setAPP_SIZE(String aPP_SIZE) {
		APP_SIZE = aPP_SIZE;
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
