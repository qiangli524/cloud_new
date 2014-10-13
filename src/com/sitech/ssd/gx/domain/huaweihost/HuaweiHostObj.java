package com.sitech.ssd.gx.domain.huaweihost;

import com.sitech.vo.huawei.HostVO;

public class HuaweiHostObj {
	private String id;// 主机--ID主键
	private String code;//UUID
	private String uri; //访问主机的uri地址
	private String name; //主机名称
	private String description;	//描述
	private String ip; //主机ip
	private String cluster_code;//集群ID
	private String status;//主机状态
	private String vendor;//服务器厂商
	private String model;//服务器型号
	private int memInMB; //主机内存总大小(单位 M)
	private int cpuNum; //CPU数量 （单位 个）
	private int nicNum;//网卡数量（单位 个）
	private int cpuMHz;//主机的CPU主频大小，单位是MHz
	
	private HostVO hostVO;
	
	public HuaweiHostObj() {
		super();
		hostVO = new HostVO();
	}
	
	public HuaweiHostObj(HostVO hostVO) {
		super();
		this.hostVO = hostVO;
	}

	public HostVO getHostVO() {
		return hostVO;
	}
	public void setHostVO(HostVO hostVO) {
		this.hostVO = hostVO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		if(hostVO==null){
			return code;
		}else{
			return hostVO.getUrn();
		}
		
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUri() {
		if(hostVO==null){
			return uri;
		}else{
			return hostVO.getUri();
		}
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		if(hostVO==null){
			return name;
		}else{
			return hostVO.getName();
		}
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		if(hostVO==null){
			return description;
		}else{
			return hostVO.getDescription();
		}
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIp() {
		if(hostVO==null){
			return ip;
		}else{
			return hostVO.getIp();
		}
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCluster_code() {
		if(hostVO==null){
			return cluster_code;
		}else{
			String[] clusterUrns = hostVO.getClusterUrn().split(":");
			return clusterUrns[clusterUrns.length-1];
		}
	}
	public void setCluster_code(String cluster_code) {
		this.cluster_code = cluster_code;
	}
	public String getStatus() {
		if(hostVO==null){
			return status;
		}else{
			return hostVO.getStatus();
		}
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendor() {
		if(hostVO==null){
			return vendor;
		}else{
			return hostVO.getVendor();
		}
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getModel() {
		if(hostVO==null){
			return model;
		}else{
			return hostVO.getModel();
		}
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMemInMB() {
		if(hostVO==null){
			return memInMB;
		}else{
			return hostVO.getMemQuantityMB();
		}
	}
	public void setMemInMB(int memInMB) {
		this.memInMB = memInMB;
	}
	public int getCpuNum() {
		if(hostVO==null){
			return cpuNum;
		}else{
			return hostVO.getCpuQuantity();
		}
	}
	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}
	public int getNicNum() {
		if(hostVO==null){
			return nicNum;
		}else{
			return hostVO.getNicQuantity();
		}
	}
	public void setNicNum(int nicNum) {
		this.nicNum = nicNum;
	}
	public int getCpuMHz() {
		if(hostVO==null){
			return cpuMHz;
		}else{
			return hostVO.getCpuMHz();
		}
	}
	public void setCpuMHz(int cpuMHz) {
		this.cpuMHz = cpuMHz;
	}

}
