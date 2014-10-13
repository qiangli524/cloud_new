package com.sitech.ssd.gx.domain.huaweivm;

import java.util.List;

import com.sitech.vo.huawei.Disk;
import com.sitech.vo.huawei.Nic;
import com.sitech.vo.huawei.VMDetailVO;

public class HuaweiVmObj {
	private String id;// 自增序列-虚拟机信息表唯一标示
	private String code;// 虚拟机唯一标识UUId
	private String name; // 虚拟机名称
	private int memInMb; // 内存
	private int cpuNum; // CPU
	private int storageInMb; // 存储
	private String osType;//虚拟机操作系统类型
	private int osVersion;//操作系统版本号
	private String ip; // IP地址
	private String network; // 网卡数量
	private String state; // 虚拟机状态
	private String desc; // 虚拟机描述
	private int nicNum;// 虚拟机虚拟网卡数量
	private String insertTime;// 数据插入时间
	private String dcCode;//中心id
	private String clusterCode;//集群id
	private String hostCode;// 虚拟机所在当前宿主机Code
	private String uri; //访问主机的uri地址
	private String template;//true:实体为为模板；false实体为虚拟机
	
	private VMDetailVO vmDetailVO;
	
	public HuaweiVmObj() {
		super();
	}
	
	public HuaweiVmObj(VMDetailVO vmDetailVO) {
		super();
		this.vmDetailVO = vmDetailVO;
	}

	public VMDetailVO getVmDetailVO() {
		return vmDetailVO;
	}

	public void setVmDetailVO(VMDetailVO vmDetailVO) {
		this.vmDetailVO = vmDetailVO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		if(vmDetailVO==null){
			return code;
		}else{
			return vmDetailVO.getUrn();
		}
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		if(vmDetailVO==null){
			return name;
		}else{
			return vmDetailVO.getName();
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMemInMb() {
		if(vmDetailVO==null){
			return memInMb;
		}else{
			return vmDetailVO.getVmConfig().getMemory().getQuantityMB();
		}
	}
	public void setMemInMb(int memInMb) {
		this.memInMb = memInMb;
	}
	public int getCpuNum() {
		if(vmDetailVO==null){
			return cpuNum;
		}else{
			return vmDetailVO.getVmConfig().getCpu().getQuantity();
		}
	}
	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}
	public int getStorageInMb() {
		if(vmDetailVO==null){
			return storageInMb;
		}else{
			List<Disk> disks =  vmDetailVO.getVmConfig().getDisks();
			int diskSiza = 0;
			for (Disk disk : disks) {
				diskSiza += disk.getQuantityGB()*1024;
			}
			return diskSiza;
		}
	}
	public void setStorageInMb(int storageInMb) {
		this.storageInMb = storageInMb;
	}
	public int getNicNum() {
		if(vmDetailVO==null){
			return nicNum;
		}else{
			return vmDetailVO.getVmConfig().getNics().size();
		}
	}
	public void setNicNum(int nicNum) {
		this.nicNum = nicNum;
	}
	
	public String getOsType() {
		if(vmDetailVO==null){
			return osType;
		}else{
			return vmDetailVO.getOsOptions().getOsType();
		}
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public int getOsVersion() {
		if(vmDetailVO==null){
			return osVersion;
		}else{
			return vmDetailVO.getOsOptions().getOsVersion();
		}
	}

	public void setOsVersion(int osVersion) {
		this.osVersion = osVersion;
	}

	public String getIp() {
		if(vmDetailVO==null){
			return ip;
		}else{
			String ips = "";
			List<Nic> nisc = vmDetailVO.getVmConfig().getNics();
			for (Nic nic : nisc) {
				ips += nic.getIp()+";";
			}
			ips = ips.substring(0,ips.length()-1);
			return ips;
		}
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getState() {
		if(vmDetailVO==null){
			return state;
		}else{
			return vmDetailVO.getStatus();
		}
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDesc() {
		if(vmDetailVO==null){
			return desc;
		}else{
			return vmDetailVO.getDescription();
		}
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getInsertTime() {
		if(vmDetailVO==null){
			return insertTime;
		}else{
			return vmDetailVO.getCreateTime();
		}
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getDcCode() {
		String[] vmUrns = vmDetailVO.getUrn().split(":");
		String dateCenterCode = vmUrns[2];
		if(vmDetailVO==null){
			return dcCode;
		}else{
			return dateCenterCode;
		}
	}
	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}
	public String getClusterCode() {
		String[] cluUrns = vmDetailVO.getClusterUrn().split(":");
		String cluCode = cluUrns[cluUrns.length-1];
		if(vmDetailVO==null){
			return clusterCode;
		}else{
			return cluCode;
		}
	}
	public void setClusterCode(String clusterCode) {
		this.clusterCode = clusterCode;
	}
	public String getHostCode() {
		String[] hostUrns = vmDetailVO.getHostUrn().split(":");
		String hostUrn = hostUrns[hostUrns.length-1];
		if(vmDetailVO==null){
			return hostCode;
		}else{
			return hostUrn;
		}
	}
	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}
	public String getUri() {
		if(vmDetailVO==null){
			return uri;
		}else{
			return vmDetailVO.getUri();
		}
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTemplate() {
		if(vmDetailVO==null){
			return template;
		}else{
			return String.valueOf(vmDetailVO.getIsTemplate());
		}
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
