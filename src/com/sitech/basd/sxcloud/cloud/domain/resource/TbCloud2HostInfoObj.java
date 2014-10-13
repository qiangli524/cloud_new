package com.sitech.basd.sxcloud.cloud.domain.resource;

import java.util.Date;
import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;

public class TbCloud2HostInfoObj extends BasePrivilegeObj {

	private String eq_id = null;
	private String c_addr;
	private String eq_name = null;
	private String eq_type = null;
	private String eq_ip = null;
	private String eq_hostname = null;
	private String eq_temperature = null;
	private String hasvertual = null;
	private String PROTOCOL; // 远程连接主机协议(Telnet、Ssh等)
	private int monitorCount; // 已监控的主机个数
	private int allCount; // 可以监控的主机总个数
	private int notMonitorCount;// 未监控主机的个数
	private int device_id;// 设备ID
	private int control = 0; // 云平台能否管控 0 不管控、1 管控
	private String cq_id = null;
	private int id;// 主机--ID主键
	private String h_uuid;// xen UUID
	private String ins_date = null;
	private String cpu_fq;// cpu主频
	private String cpu_cl;// cpu芯数
	private String mem;// 内存
	private String store;// 存储
	private String MODEL;// 主机型号
	private int NIC_NUM;// 主机网卡数量
	private String CPU_DESC;// CPU描述
	private String HOST_PROC;// 主机制造商
	private String STATUS;// 主机状态：0，未采集到 1，正常启动 2，停止 3，异常
	private String memUsage;

	private int vm_num;// 主机下虚拟机的个数
	private Integer storage_num;// 主机下存储块数

	private String HOST_POOL_ID;// 主机池ID
	private String hostUuids;
	private String HOST_IDS;// 主机IDS
	private String POOL_TYPE;// 主机池类型
	private String flag;// 当前页的主机IDS

	private String MAC;
	// 集群标识 VMware-集群ID Xen-资源池pool-UUID(共有)
	private String connectId;
	// 宿主机当前支持最大VCPU
	private Integer maxVcpu;
	// 宿主机已使用VCPU核数
	private Integer usedVcpu;
	// 宿主机CPU最大容量-MHZ
	private Integer maxCpu;
	// 宿主机已使用内存MB
	private Integer usedMemMb;
	// 已使用CpuMhz
	private Integer usedCpu;
	// 是否分配
	private Integer allocated;
	// 数据更新时间
	private Date updateDate;
	// 宿主机机连接状态
	private String connectStatus;
	private String USER_ID;// 拥有主机权限的用户ID

	private List<String> uuidList;// 主机h_uuid集合
	private List cubinetList = null; // 机柜列表
	private String cluid;// 集群id
	private String centerid;// 数据中心id

	private int modulus;

	private String deviceName;// 所属存储设备名称
	private String deviceId;// 所属存储设备id

	private Integer is_distributed;// 是否分布式
	private Double usedStore;// 已使用存储
	//add by qism
	private String hmc_sn;//操作系统（暂用）
	
	private String COUNT;// 可分配资源个数
	private int eq_counts;// 申请设备个数

	private String sn; // 序列号
	private String power_supply;//电源模块状态
	
	private String parent_id;//业务视图查询需要
	
	public String getHmc_sn() {
		return hmc_sn;
	}

	public void setHmc_sn(String hmc_sn) {
		this.hmc_sn = hmc_sn;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(String connectStatus) {
		this.connectStatus = connectStatus;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Integer getAllocated() {
		return allocated;
	}

	public void setAllocated(Integer allocated) {
		this.allocated = allocated;
	}

	public Integer getUsedCpu() {
		return usedCpu;
	}

	public void setUsedCpu(Integer usedCpu) {
		this.usedCpu = usedCpu;
	}

	public Integer getUsedMemMb() {
		return usedMemMb;
	}

	public void setUsedMemMb(Integer usedMemMb) {
		this.usedMemMb = usedMemMb;
	}

	public Integer getMaxVcpu() {
		return maxVcpu;
	}

	public void setMaxVcpu(Integer maxVcpu) {
		this.maxVcpu = maxVcpu;
	}

	public Integer getUsedVcpu() {
		return usedVcpu;
	}

	public void setUsedVcpu(Integer usedVcpu) {
		this.usedVcpu = usedVcpu;
	}

	public Integer getMaxCpu() {
		return maxCpu;
	}

	public void setMaxCpu(Integer maxCpu) {
		this.maxCpu = maxCpu;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mac) {
		MAC = mac;
	}

	public String getHostUuids() {
		return hostUuids;
	}

	public void setHostUuids(String hostUuids) {
		this.hostUuids = hostUuids;
	}

	public String getHOST_POOL_ID() {
		return HOST_POOL_ID;
	}

	public void setHOST_POOL_ID(String host_pool_id) {
		HOST_POOL_ID = host_pool_id;
	}

	public Integer getStorage_num() {
		return storage_num;
	}

	public void setStorage_num(Integer storage_num) {
		this.storage_num = storage_num;
	}

	public int getVm_num() {
		return vm_num;
	}

	public void setVm_num(int vm_num) {
		this.vm_num = vm_num;
	}

	public String getMemUsage() {
		return memUsage;
	}

	public void setMemUsage(String memUsage) {
		this.memUsage = memUsage;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getHOST_PROC() {
		return HOST_PROC;
	}

	public void setHOST_PROC(String host_proc) {
		HOST_PROC = host_proc;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String model) {
		MODEL = model;
	}

	public void setNIC_NUM(int nic_num) {
		NIC_NUM = nic_num;
	}

	public String getCPU_DESC() {
		return CPU_DESC;
	}

	public void setCPU_DESC(String cpu_desc) {
		CPU_DESC = cpu_desc;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

	public String getCpu_fq() {
		return cpu_fq;
	}

	public void setCpu_fq(String cpu_fq) {
		this.cpu_fq = cpu_fq;
	}

	public String getCpu_cl() {
		return cpu_cl;
	}

	public void setCpu_cl(String cpu_cl) {
		this.cpu_cl = cpu_cl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getH_uuid() {
		return h_uuid;
	}

	public void setH_uuid(String h_uuid) {
		this.h_uuid = h_uuid;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
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

	public String getPROTOCOL() {
		return PROTOCOL;
	}

	public void setPROTOCOL(String protocol) {
		PROTOCOL = protocol;
	}

	public int getControl() {
		return control;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public int getMonitorCount() {
		return monitorCount;
	}

	public void setMonitorCount(int monitorCount) {
		this.monitorCount = monitorCount;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getNotMonitorCount() {
		return notMonitorCount;
	}

	public void setNotMonitorCount(int notMonitorCount) {
		this.notMonitorCount = notMonitorCount;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public int getNIC_NUM() {
		return NIC_NUM;
	}

	/**
	 * 重写toString方法
	 */
	public String toString() {
		if (this.getCPU_DESC() == null) {
			this.setCPU_DESC("");
		}
		if (this.getEq_name() == null) {
			this.setEq_name("");
		}
		if (this.getCpu_fq() == null) {
			this.setCpu_fq("");
		}
		if (this.getCpu_cl() == null) {
			this.setCpu_cl("");
		}
		if (this.getMem() == null) {
			this.setMem("");
		}
		if (this.getMODEL() == null) {
			this.setMODEL("");
		}
		if (this.getHOST_PROC() == null) {
			this.setHOST_PROC("");
		}
		if (this.getNIC_NUM() == 0) {
			this.setNIC_NUM(0);
		}
		if (this.getSTATUS() == null) {
			this.setSTATUS("");
		}
		if (this.getEq_ip() == null) {
			this.setEq_ip("");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("CPU_DESC:" + this.getCPU_DESC() + ",");
		sb.append("eq_name:" + this.getEq_name() + ",");
		sb.append("cpu_fq:" + this.getCpu_fq() + ",");
		sb.append("cpu_cl:" + this.getCpu_cl() + ",");
		sb.append("mem:" + this.getMem() + ",");
		sb.append("MODEL:" + this.getMODEL() + ",");
		sb.append("HOST_PROC:" + this.getHOST_PROC() + ",");
		sb.append("STATUS:" + this.getSTATUS() + ",");
		sb.append("eq_ip:" + this.getEq_ip() + ",");
		sb.append("NIC_NUM:" + this.getNIC_NUM());
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 重写equals方法
	 */
	public boolean equals(Object obj) {
		if (obj instanceof TbCloud2HostInfoObj) {
			TbCloud2HostInfoObj temp = (TbCloud2HostInfoObj) obj;
			if (this.getCPU_DESC().equals(temp.getCPU_DESC())
					&& this.getEq_name().equals(temp.getEq_name())
					&& this.getCpu_fq().equals(temp.getCpu_fq())
					&& this.getCpu_cl().equals(temp.getCpu_cl())
					&& this.getMem().equals(temp.getMem())
					&& this.getMODEL().equals(temp.getMODEL())
					&& this.getHOST_PROC().equals(temp.getHOST_PROC())
					&& (this.getNIC_NUM() == temp.getNIC_NUM())
					&& this.getSTATUS().equals(temp.getSTATUS())
					&& this.getEq_ip().equals(temp.getEq_ip())) {
				return true;
			}
		}
		return false;
	}

	public String getHOST_IDS() {
		return HOST_IDS;
	}

	public void setHOST_IDS(String host_ids) {
		HOST_IDS = host_ids;
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

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public List getCubinetList() {
		return cubinetList;
	}

	public void setCubinetList(List cubinetList) {
		this.cubinetList = cubinetList;
	}

	public String getCluid() {
		return cluid;
	}

	public void setCluid(String cluid) {
		this.cluid = cluid;
	}

	public int getModulus() {
		return modulus;
	}

	public void setModulus(int modulus) {
		this.modulus = modulus;
	}

	public Integer getIs_distributed() {
		return is_distributed;
	}

	public void setIs_distributed(Integer is_distributed) {
		this.is_distributed = is_distributed;
	}

	public Double getUsedStore() {
		return usedStore;
	}

	public void setUsedStore(Double usedStore) {
		this.usedStore = usedStore;
	}

	public String getCenterid() {
		return centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	public String getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}

	public int getEq_counts() {
		return eq_counts;
	}

	public void setEq_counts(int eq_counts) {
		this.eq_counts = eq_counts;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPower_supply() {
		return power_supply;
	}

	public void setPower_supply(String power_supply) {
		this.power_supply = power_supply;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
