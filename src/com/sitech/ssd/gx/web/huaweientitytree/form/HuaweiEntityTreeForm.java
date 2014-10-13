package com.sitech.ssd.gx.web.huaweientitytree.form;

public class HuaweiEntityTreeForm {
	//数据中心相关数据
	private int dcClusterSize;
	private int dcHostSize;
	private int dcVmSize;
	//集群相关数据
	private int cluHostSize;
	private int cluVmSize;
	private String cluName;
	private String cluDesc;
	private boolean issEnableHa;
	private boolean issMemOvercommit;
	private boolean issEnableDrs;
	private int drsLevel;//drs自动化级别
	private int drsLimen; //drs迁移阈值
	//主机相关数据
	private int hostVmSize;
	private String hostName; //主机名称
	private String hostDesc; //主机描述
	private String hostStatus; //主机状态,rebooting:重启中,normal:正常,fault:故障,initial:初始化,unknown:未知
						   //poweroff:离线,booting:上电中,shutdowning:下电中
	private String hostClusterName; //集群名称
	private String hostIp; //主机ip
	private String hostBmcIp; //bmc的IP地址
	private String hostAttachedISOVM; //主机中已经挂载本地磁盘的虚拟机标识,若无虚拟机使用主机光驱则此字段为“NULL”
	private boolean hostIsMaintaining; //主机是否是维护状态
	//虚拟机相关数据
	private String vmname; //虚拟机名称
	private String vmcode; //虚拟机唯一标识
	private String vmdesc; //虚拟机描述
	private String vmlocationName;//虚拟机所属集群或主机的名称
	private String vmcreateTime; //创建时间(UTC时间字符串)
	private String runningHostName; //运行主机
	private String vmhostUrn; //虚拟机运行所在的主机标识
	private String vmstatus; //虚拟机状态：
						   //running: 运行中,stopped：已停止,unknown：不明确,hibernated：已休眠
						   //creating:创建中或模板正在部署虚拟机或正在导入模板
						   //shutting-down:删除中,migrating:迁移,fault-resuming:故障恢复中
						   //starting:启动中,stopping:停止中,hibernating：休眠中,
	
	private String vmpvDriverStatus;//starting:启动中,notRunning:未运行,running:正运行
	private boolean vmisLinkClone; //是否为链接克隆虚拟机
	private boolean vmisBindHost; //是否绑定主机
	
	public int getDcClusterSize() {
		return dcClusterSize;
	}
	public void setDcClusterSize(int dcClusterSize) {
		this.dcClusterSize = dcClusterSize;
	}
	public int getDcHostSize() {
		return dcHostSize;
	}
	public void setDcHostSize(int dcHostSize) {
		this.dcHostSize = dcHostSize;
	}
	public int getDcVmSize() {
		return dcVmSize;
	}
	public void setDcVmSize(int dcVmSize) {
		this.dcVmSize = dcVmSize;
	}
	public int getCluHostSize() {
		return cluHostSize;
	}
	public void setCluHostSize(int cluHostSize) {
		this.cluHostSize = cluHostSize;
	}
	public int getCluVmSize() {
		return cluVmSize;
	}
	public void setCluVmSize(int cluVmSize) {
		this.cluVmSize = cluVmSize;
	}
	public String getCluName() {
		return cluName;
	}
	public void setCluName(String cluName) {
		this.cluName = cluName;
	}
	public String getCluDesc() {
		return cluDesc;
	}
	public void setCluDesc(String cluDesc) {
		this.cluDesc = cluDesc;
	}
	public boolean getIssEnableHa() {
		return issEnableHa;
	}
	public void setIssEnableHa(boolean isEnableHa) {
		this.issEnableHa = isEnableHa;
	}
	public boolean getIssMemOvercommit() {
		return issMemOvercommit;
	}
	public void setIssMemOvercommit(boolean isMemOvercommit) {
		this.issMemOvercommit = isMemOvercommit;
	}
	public boolean getIssEnableDrs() {
		return issEnableDrs;
	}
	public void setIssEnableDrs(boolean isEnableDrs) {
		this.issEnableDrs = isEnableDrs;
	}
	public int getDrsLevel() {
		return drsLevel;
	}
	public void setDrsLevel(int drsLevel) {
		this.drsLevel = drsLevel;
	}
	public int getDrsLimen() {
		return drsLimen;
	}
	public void setDrsLimen(int drsLimen) {
		this.drsLimen = drsLimen;
	}
	public int getHostVmSize() {
		return hostVmSize;
	}
	public void setHostVmSize(int hostVmSize) {
		this.hostVmSize = hostVmSize;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostDesc() {
		return hostDesc;
	}
	public void setHostDesc(String hostDesc) {
		this.hostDesc = hostDesc;
	}
	public String getHostStatus() {
		return hostStatus;
	}
	public void setHostStatus(String hostStatus) {
		this.hostStatus = hostStatus;
	}
	public String getHostClusterName() {
		return hostClusterName;
	}
	public void setHostClusterName(String hostClusterName) {
		this.hostClusterName = hostClusterName;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getHostBmcIp() {
		return hostBmcIp;
	}
	public void setHostBmcIp(String hostBmcIp) {
		this.hostBmcIp = hostBmcIp;
	}
	public String getHostAttachedISOVM() {
		return hostAttachedISOVM;
	}
	public void setHostAttachedISOVM(String hostAttachedISOVM) {
		this.hostAttachedISOVM = hostAttachedISOVM;
	}
	public boolean isHostIsMaintaining() {
		return hostIsMaintaining;
	}
	public void setHostIsMaintaining(boolean hostIsMaintaining) {
		this.hostIsMaintaining = hostIsMaintaining;
	}
	public String getVmname() {
		return vmname;
	}
	public void setVmname(String vmname) {
		this.vmname = vmname;
	}
	public String getVmcode() {
		return vmcode;
	}
	public void setVmcode(String vmcode) {
		this.vmcode = vmcode;
	}
	public String getVmdesc() {
		return vmdesc;
	}
	public void setVmdesc(String vmdesc) {
		this.vmdesc = vmdesc;
	}
	public String getVmlocationName() {
		return vmlocationName;
	}
	public void setVmlocationName(String vmlocationName) {
		this.vmlocationName = vmlocationName;
	}
	public String getVmcreateTime() {
		return vmcreateTime;
	}
	public void setVmcreateTime(String vmcreateTime) {
		this.vmcreateTime = vmcreateTime;
	}
	public String getRunningHostName() {
		return runningHostName;
	}
	public void setRunningHostName(String runningHostName) {
		this.runningHostName = runningHostName;
	}
	public String getVmhostUrn() {
		return vmhostUrn;
	}
	public void setVmhostUrn(String vmhostUrn) {
		this.vmhostUrn = vmhostUrn;
	}
	public String getVmstatus() {
		return vmstatus;
	}
	public void setVmstatus(String vmstatus) {
		this.vmstatus = vmstatus;
	}
	public String getVmpvDriverStatus() {
		return vmpvDriverStatus;
	}
	public void setVmpvDriverStatus(String vmpvDriverStatus) {
		this.vmpvDriverStatus = vmpvDriverStatus;
	}
	public boolean isVmisLinkClone() {
		return vmisLinkClone;
	}
	public void setVmisLinkClone(boolean vmisLinkClone) {
		this.vmisLinkClone = vmisLinkClone;
	}
	public boolean isVmisBindHost() {
		return vmisBindHost;
	}
	public void setVmisBindHost(boolean vmisBindHost) {
		this.vmisBindHost = vmisBindHost;
	}
}
