package com.sitech.ssd.ah.nas.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NasKpiObj extends BaseObj {

	private int id;
	private String kpiname;//kpi名称
	private String kpivalue;//kpi值
	private String nas_device_id;//nas设备id
	
	private String storesize;//存储设备总量
	private String allfilesize;//划分给文件系统的总量
	private String usedfilesize;//文件系统的使用总量
	private String filenum;//文件系统个数
	private String filepoolnum;//存储池个数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKpiname() {
		return kpiname;
	}
	public void setKpiname(String kpiname) {
		this.kpiname = kpiname;
	}
	public String getKpivalue() {
		return kpivalue;
	}
	public void setKpivalue(String kpivalue) {
		this.kpivalue = kpivalue;
	}
	public String getNas_device_id() {
		return nas_device_id;
	}
	public void setNas_device_id(String nas_device_id) {
		this.nas_device_id = nas_device_id;
	}
	public String getStoresize() {
		return storesize;
	}
	public void setStoresize(String storesize) {
		this.storesize = storesize;
	}
	public String getAllfilesize() {
		return allfilesize;
	}
	public void setAllfilesize(String allfilesize) {
		this.allfilesize = allfilesize;
	}
	public String getUsedfilesize() {
		return usedfilesize;
	}
	public void setUsedfilesize(String usedfilesize) {
		this.usedfilesize = usedfilesize;
	}
	public String getFilenum() {
		return filenum;
	}
	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}
	public String getFilepoolnum() {
		return filepoolnum;
	}
	public void setFilepoolnum(String filepoolnum) {
		this.filepoolnum = filepoolnum;
	}
	
}
