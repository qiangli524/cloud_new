package com.sitech.basd.sxcloud.cloud.web.showview.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;

@SuppressWarnings("all")
public class HostManageForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;// 编号
	private String eq_id = null;

	private String eq_name = null;
	private String eq_type = null;
	private String eq_ip = null;
	private String eq_hostname = null;
	private String eq_temperature = null;
	private String hasvertual = null;
	private String hostUserName;// 用户名
	private String password;// 密码
	private String cq_id;
	private String STATUS;// 状态
	private String SPACE;// 磁盘空间
	private int TYPE;// 用户类型
	private String updateTime;// 更新时间
	private int operation = 0; // 0:删除，1修改
	private String c_addr;// 机柜位置
	private List resultList = null; // 结果集列表

	private int control; // 云平台能否管控 0 不管控、1 管控
	private List hostList1 = null;
	private List hostList2 = null;
	private List hostList3 = null;
	private List hostList4 = null;
	private List hostList5 = null;
	private List<TbCloud2HostConfigObj> userInfoList;// 用户信息列表
	private List cubinetList = null; // 机柜列表
	private int TYPE_BF; // 用户类型备份
	private String HOSTUSERNAME_BF; // 主机用户名备份字段 外加
	private String start_date = null;
	private String end_date = null;
	private String PROTOCOL; // 远程连接主机协议(Telnet、Ssh等)
	private String H_UUID;// xen UUID
	private String hostUuids;

	private String HOST_POOL_ID;// 主机池ID
	private String POOL_TYPE;// 主机池类型
	private String flag;
	private String hostflag;// 是主机注册，还是主机管理界面操作的标识
	private Integer allocated;//是否分配
	private String cpu_cl;// cpu芯数
	private String mem;// 内存
	private String store;//存储
	
	public String getCpu_cl() {
		return cpu_cl;
	}

	public void setCpu_cl(String cpu_cl) {
		this.cpu_cl = cpu_cl;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	private String MAC;

	public Integer getAllocated() {
		return allocated;
	}

	public void setAllocated(Integer allocated) {
		this.allocated = allocated;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	public String getHOST_POOL_ID() {
		return HOST_POOL_ID;
	}

	public void setHOST_POOL_ID(String host_pool_id) {
		HOST_POOL_ID = host_pool_id;
	}

	public String getH_UUID() {
		return H_UUID;
	}

	public void setH_UUID(String h_uuid) {
		H_UUID = h_uuid;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

	public String getSPACE() {
		return SPACE;
	}

	public void setSPACE(String space) {
		SPACE = space;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public int getTYPE_BF() {
		return TYPE_BF;
	}

	public void setTYPE_BF(int type_bf) {
		TYPE_BF = type_bf;
	}

	public String getHOSTUSERNAME_BF() {
		return HOSTUSERNAME_BF;
	}

	public void setHOSTUSERNAME_BF(String hostusername_bf) {
		HOSTUSERNAME_BF = hostusername_bf;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public List<TbCloud2HostConfigObj> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<TbCloud2HostConfigObj> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public List getCubinetList() {
		return cubinetList;
	}

	public void setCubinetList(List cubinetList) {
		this.cubinetList = cubinetList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getCq_id() {
		return cq_id;
	}

	public void setCq_id(String cq_id) {
		this.cq_id = cq_id;
	}

	public String getEq_hostname() {
		return eq_hostname;
	}

	public void setEq_hostname(String eq_hostname) {
		this.eq_hostname = eq_hostname;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_temperature() {
		return eq_temperature;
	}

	public void setEq_temperature(String eq_temperature) {
		this.eq_temperature = eq_temperature;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

	public String getHasvertual() {
		return hasvertual;
	}

	public void setHasvertual(String hasvertual) {
		this.hasvertual = hasvertual;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public List getHostList1() {
		return hostList1;
	}

	public void setHostList1(List hostList1) {
		this.hostList1 = hostList1;
	}

	public List getHostList2() {
		return hostList2;
	}

	public void setHostList2(List hostList2) {
		this.hostList2 = hostList2;
	}

	public List getHostList3() {
		return hostList3;
	}

	public void setHostList3(List hostList3) {
		this.hostList3 = hostList3;
	}

	public List getHostList4() {
		return hostList4;
	}

	public void setHostList4(List hostList4) {
		this.hostList4 = hostList4;
	}

	/*
	 * public void reset(ActionMapping mapping, HttpServletRequest request) {
	 * super.reset(mapping, request); this.ID = 0; this.eq_id = null; this.TYPE =
	 * 0; this.eq_hostname = null; this.password = null; this.hostUserName =
	 * null; this.HOSTUSERNAME_BF = null; this.TYPE_BF = 0; this.updateTime =
	 * null; }
	 */

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public String getHostUuids() {
		return hostUuids;
	}

	public void setHostUuids(String hostUuids) {
		this.hostUuids = hostUuids;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPOOL_TYPE() {
		return POOL_TYPE;
	}

	public void setPOOL_TYPE(String pool_type) {
		POOL_TYPE = pool_type;
	}

	public String getHostflag() {
		return hostflag;
	}

	public void setHostflag(String hostflag) {
		this.hostflag = hostflag;
	}

	public List getHostList5() {
		return hostList5;
	}

	public void setHostList5(List hostList5) {
		this.hostList5 = hostList5;
	}
	
}
