package com.sitech.basd.sxcloud.cloud.domain.vmhost;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

@SuppressWarnings("all")
public class VMHostObj extends BasePrivilegeObj implements Serializable, Cloneable {
	private String VH_ID; // 虚拟机编号
	private String VH_NAME; // 虚拟机名称
	private String VH_MEM; // 内存
	private String VH_CPU; // CPU
	private String VH_STORAGE; // 存储
	private String VH_SYSTEM; // 操作系统
	private String VH_IP; // IP地址
	private String VH_NETWORK; // 网卡数量
	private String VH_RUNTIME; // 运行时间
	private String EQ_ID; // 物理主机编号
	private String VH_STAT = null; // 虚拟机状态
	private String VH_DESC; // 虚拟机描述
	private String P_ID; // 所属项目编号
	private String VH_MIN_CPU; // cpu最小值
	private String VH_MAX_CPU; // cpu最大值
	private String VH_MIN_MEM; // 内存最小值
	private String VH_MAX_MEM; // 内存最大值
	private String VH_STARTED; // 开始时间
	private String VH_COMPLETED; // 完成时间
	private String VH_DEPLOYETIME; // 部署时间
	private String VH_UPTIME; // 更新时间
	private String VH_LOG; // 日志
	private String VH_USER; // 用户名
	private String VH_PASS; // 密码
	private String VH_TYPE; // 虚拟机类型(KVM或者IBM)
	private String VH_CID;// CMDB接口返回id
	private String VH_STATUS; // 是否向cmdb推送数据
	private String VH_UNITID; // 监控返回的id
	private int ID;// 自增序列-虚拟机信息表唯一标示
	private String VH_UUID;// Xen虚拟机唯一标识UUId
	private String DNS;// VmwareDNS域名
	private String VH_TYPE_1;// 虚拟机类型
	private String EQ_NAME; // 物理主机名称
	private int APPNUM;// 应用个数
	private String H_ID;// 虚拟机对应的在tb_busi_host中的Id
	private String USER_ID;// 用户id
	private String BUSI_STATUS;// 虚拟机上的业务状态，1上线，0或者空为已分配
	// 集群标识 VMware-集群ID Xen-资源池pool-UUID(共有)
	private String connectId;
	// 虚拟机所在当前宿主机Code，Uuid,host-23
	private String hostCode;
	// 虚拟机虚拟网卡数量
	private String NICNUM;
	private String PROJECT_ID;// 项目id
	// 项目名称
	private String PROJECT_NAME;

	private List<String> uuidList;
	// 数据插入时间
	private Date insertTime;
	// 数据更新时间
	private Date updateTime;
	// 虚拟机连接状态
	private String connectStatus;

	private String centerid;// 中心id

	private String cluid;// 集群id

	private String temid;

	// 资源对应的与名称，子业务
	private String name;
	private String os_type;// 操作系统类型，参考OperateSystemType类

	// 虚拟机所属网络域的名称
	private String NET_NAME;

	private List<String> hostIdList;// 主机id集合

	private List<String> clusterList;// 所属集群
	private List<String> connectCodeList;// vCenter标示列表，北京电信使用
	private String public_ip;// 虚拟机的公网IP地址
	private String workOrderType;//工单类型
	
	public String getOs_type() {
		return os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getPublic_ip() {
		return public_ip;
	}

	public void setPublic_ip(String public_ip) {
		this.public_ip = public_ip;
	}

	public List<String> getConnectCodeList() {
		return connectCodeList;
	}

	public void setConnectCodeList(List<String> connectCodeList) {
		this.connectCodeList = connectCodeList;
	}

	public String getNET_NAME() {
		return NET_NAME;
	}

	public void setNET_NAME(String nET_NAME) {
		NET_NAME = nET_NAME;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemid() {
		return temid;
	}

	public String getBUSI_STATUS() {
		return BUSI_STATUS;
	}

	public void setBUSI_STATUS(String bUSI_STATUS) {
		BUSI_STATUS = bUSI_STATUS;
	}

	public void setTemid(String temid) {
		this.temid = temid;
	}

	public String getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(String connectStatus) {
		this.connectStatus = connectStatus;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public String getPROJECT_ID() {
		return PROJECT_ID;
	}

	public void setPROJECT_ID(String pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}

	public String getNICNUM() {
		return NICNUM;
	}

	public void setNICNUM(String nICNUM) {
		NICNUM = nICNUM;
	}

	public String getHostCode() {
		return hostCode;
	}

	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public int getAPPNUM() {
		return APPNUM;
	}

	public void setAPPNUM(int appnum) {
		APPNUM = appnum;
	}

	public String getEQ_NAME() {
		return EQ_NAME;
	}

	public void setEQ_NAME(String eq_name) {
		EQ_NAME = eq_name;
	}

	public String getVH_TYPE_1() {
		return VH_TYPE_1;
	}

	public void setVH_TYPE_1(String vh_type_1) {
		VH_TYPE_1 = vh_type_1;
	}

	public String getDNS() {
		return DNS;
	}

	public void setDNS(String dns) {
		DNS = dns;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getVH_UUID() {
		return VH_UUID;
	}

	public void setVH_UUID(String vh_uuid) {
		VH_UUID = vh_uuid;
	}

	public String getVH_UNITID() {
		return VH_UNITID;
	}

	public void setVH_UNITID(String vh_unitid) {
		VH_UNITID = vh_unitid;
	}

	public String getVH_STATUS() {
		return VH_STATUS;
	}

	public void setVH_STATUS(String vh_status) {
		VH_STATUS = vh_status;
	}

	public String getVH_CID() {
		return VH_CID;
	}

	public void setVH_CID(String vh_cid) {
		VH_CID = vh_cid;
	}

	public String getVH_TYPE() {
		return VH_TYPE;
	}

	public void setVH_TYPE(String vh_type) {
		VH_TYPE = vh_type;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getVH_NAME() {
		return VH_NAME;
	}

	public void setVH_NAME(String vh_name) {
		VH_NAME = vh_name;
	}

	public String getVH_MEM() {
		return VH_MEM;
	}

	public void setVH_MEM(String vh_mem) {
		VH_MEM = vh_mem;
	}

	public String getVH_CPU() {
		return VH_CPU;
	}

	public void setVH_CPU(String vh_cpu) {
		VH_CPU = vh_cpu;
	}

	public String getVH_STORAGE() {
		return VH_STORAGE;
	}

	public void setVH_STORAGE(String vh_storage) {
		VH_STORAGE = vh_storage;
	}

	public String getVH_SYSTEM() {
		return VH_SYSTEM;
	}

	public void setVH_SYSTEM(String vh_system) {
		VH_SYSTEM = vh_system;
	}

	public String getVH_IP() {
		return VH_IP;
	}

	public void setVH_IP(String vh_ip) {
		VH_IP = vh_ip;
	}

	public String getVH_NETWORK() {
		return VH_NETWORK;
	}

	public void setVH_NETWORK(String vh_network) {
		VH_NETWORK = vh_network;
	}

	public String getVH_RUNTIME() {
		return VH_RUNTIME;
	}

	public void setVH_RUNTIME(String vh_runtime) {
		VH_RUNTIME = vh_runtime;
	}

	public String getEQ_ID() {
		return EQ_ID;
	}

	public void setEQ_ID(String eq_id) {
		EQ_ID = eq_id;
	}

	public String getVH_STAT() {
		return VH_STAT;
	}

	public void setVH_STAT(String vh_stat) {
		VH_STAT = vh_stat;
	}

	public String getVH_DESC() {
		return VH_DESC;
	}

	public void setVH_DESC(String vh_desc) {
		VH_DESC = vh_desc;
	}

	public String getP_ID() {
		return P_ID;
	}

	public void setP_ID(String p_id) {
		P_ID = p_id;
	}

	public String getVH_MIN_CPU() {
		return VH_MIN_CPU;
	}

	public void setVH_MIN_CPU(String vh_min_cpu) {
		VH_MIN_CPU = vh_min_cpu;
	}

	public String getVH_MAX_CPU() {
		return VH_MAX_CPU;
	}

	public void setVH_MAX_CPU(String vh_max_cpu) {
		VH_MAX_CPU = vh_max_cpu;
	}

	public String getVH_MIN_MEM() {
		return VH_MIN_MEM;
	}

	public void setVH_MIN_MEM(String vh_min_mem) {
		VH_MIN_MEM = vh_min_mem;
	}

	public String getVH_MAX_MEM() {
		return VH_MAX_MEM;
	}

	public void setVH_MAX_MEM(String vh_max_mem) {
		VH_MAX_MEM = vh_max_mem;
	}

	public String getVH_STARTED() {
		return VH_STARTED;
	}

	public void setVH_STARTED(String vh_started) {
		VH_STARTED = vh_started;
	}

	public String getVH_COMPLETED() {
		return VH_COMPLETED;
	}

	public void setVH_COMPLETED(String vh_completed) {
		VH_COMPLETED = vh_completed;
	}

	public String getVH_DEPLOYETIME() {
		return VH_DEPLOYETIME;
	}

	public void setVH_DEPLOYETIME(String vh_deployetime) {
		VH_DEPLOYETIME = vh_deployetime;
	}

	public String getVH_UPTIME() {
		return VH_UPTIME;
	}

	public void setVH_UPTIME(String vh_uptime) {
		VH_UPTIME = vh_uptime;
	}

	public String getVH_LOG() {
		return VH_LOG;
	}

	public void setVH_LOG(String vh_log) {
		VH_LOG = vh_log;
	}

	public String getVH_USER() {
		return VH_USER;
	}

	public void setVH_USER(String vh_user) {
		VH_USER = vh_user;
	}

	public String getVH_PASS() {
		return VH_PASS;
	}

	public void setVH_PASS(String vh_pass) {
		VH_PASS = vh_pass;
	}

	public String getH_ID() {
		return H_ID;
	}

	public void setH_ID(String h_id) {
		H_ID = h_id;
	}

	public List<String> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<String> clusterList) {
		this.clusterList = clusterList;
	}

	public String getCenterid() {
		return centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getCluid() {
		return cluid;
	}

	public void setCluid(String cluid) {
		this.cluid = cluid;
	}

	public List<String> getHostIdList() {
		return hostIdList;
	}

	public void setHostIdList(List<String> hostIdList) {
		this.hostIdList = hostIdList;
	}

	/**
	 * 重写toString方法
	 */
	public String toString() {
		if (this.getDNS() == null) {
			this.setDNS("");
		}
		if (this.getVH_CPU() == null) {
			this.setVH_CPU("");
		}
		if (this.getVH_IP() == null) {
			this.setVH_IP("");
		}
		if (this.getVH_MEM() == null) {
			this.setVH_MEM("");
		}
		if (this.getVH_NAME() == null) {
			this.setVH_NAME("");
		}
		if (this.getVH_STAT() == null) {
			this.setVH_STAT("");
		}
		if (this.getVH_SYSTEM() == null) {
			this.setVH_SYSTEM("");
		}
		if (this.getVH_TYPE() == null) {
			this.setVH_TYPE("");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("DNS:" + this.getDNS() + ",");
		sb.append("VH_CPU:" + this.getVH_CPU() + ",");
		sb.append("VH_IP:" + this.getVH_IP() + ",");
		sb.append("VH_MEM:" + this.getVH_MEM() + ",");
		sb.append("VH_NAME:" + this.getVH_NAME() + ",");
		sb.append("VH_STAT:" + this.getVH_STAT() + ",");
		sb.append("VH_SYSTEM:" + this.getVH_SYSTEM() + ",");
		sb.append("VH_TYPE:" + this.getVH_TYPE());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 重写equals方法
	 */
	public boolean equals(Object obj) {
		if (obj instanceof VMHostObj) {
			VMHostObj temp = (VMHostObj) obj;
			if (this.getDNS().equals(temp.getDNS()) && this.getVH_CPU().equals(temp.getVH_CPU())
					&& this.getVH_IP().equals(temp.getVH_IP())
					&& this.getVH_MEM().equals(temp.getVH_MEM())
					&& this.getVH_NAME().equals(temp.getVH_NAME())
					&& this.getVH_STAT().equals(temp.getVH_STAT())
					&& this.getVH_SYSTEM().equals(temp.getVH_SYSTEM())
					&& this.getVH_TYPE().equals(temp.getVH_TYPE())) {
				return true;
			}
		}
		return false;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getPROJECT_NAME() {
		return PROJECT_NAME;
	}

	public void setPROJECT_NAME(String pROJECT_NAME) {
		PROJECT_NAME = pROJECT_NAME;
	}

	public String getWorkOrderType() {
		return workOrderType;
	}

	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}

}
