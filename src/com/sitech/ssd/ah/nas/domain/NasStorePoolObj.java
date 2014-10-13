package com.sitech.ssd.ah.nas.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NasStorePoolObj extends BaseObj {

	private String id ;
	private String naspoolname; //存储池名称
	private Double freespace; //存储池的剩余量（以M为单位）
	private Double totalspace; //存储池的总量（以M为单位）
	private String nas_device_id; //nas设备标识
	private String flag; //存储池的唯一标示

	private String fileNum;//存储池中文件系统的个数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaspoolname() {
		return naspoolname;
	}
	public void setNaspoolname(String naspoolname) {
		this.naspoolname = naspoolname;
	}
	public Double getFreespace() {
		return freespace;
	}
	public void setFreespace(Double freespace) {
		this.freespace = freespace;
	}
	public Double getTotalspace() {
		return totalspace;
	}
	public void setTotalspace(Double totalspace) {
		this.totalspace = totalspace;
	}
	public String getNas_device_id() {
		return nas_device_id;
	}
	public void setNas_device_id(String nas_device_id) {
		this.nas_device_id = nas_device_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFileNum() {
		return fileNum;
	}
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	
}
