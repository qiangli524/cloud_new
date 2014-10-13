package com.sitech.ssd.ah.nas.domain;

import java.util.List;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class NasFileSystemObj extends BasePrivilegeObj{

	private int ID; //自增长主键
	private String FS_STORAGE_POOL; //当前文件系统所属存储池名称
	private String FS_NAME; //文件系统名称
	private String FS_STATUS; //文件系统状态：online等
	private String FS_SHARED_TYPE; //文件系统共享类型：NFS、CIFS
	private Double FS_SIZE; //文件系统大小，单位为：M
	private Double FS_USED_SIZE; //文件系统已经使用大小，单位:M
	private String FS_NASIP; //nas文件系统接入端的nasIP地址
	private String NAS_DEVICE_ID;//每个设备的唯一标示
	private List<NasFileSystemObj> ipList;//ip列表
	
	private String SHARENUM;//共享改文件系统的虚拟机或者主机个数
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFS_STORAGE_POOL() {
		return FS_STORAGE_POOL;
	}
	public void setFS_STORAGE_POOL(String fS_STORAGE_POOL) {
		FS_STORAGE_POOL = fS_STORAGE_POOL;
	}
	public String getFS_NAME() {
		return FS_NAME;
	}
	public void setFS_NAME(String fS_NAME) {
		FS_NAME = fS_NAME;
	}
	public String getFS_STATUS() {
		return FS_STATUS;
	}
	public void setFS_STATUS(String fS_STATUS) {
		FS_STATUS = fS_STATUS;
	} 
	public String getFS_SHARED_TYPE() {
		return FS_SHARED_TYPE;
	}
	public void setFS_SHARED_TYPE(String fS_SHARED_TYPE) {
		FS_SHARED_TYPE = fS_SHARED_TYPE;
	}
	public Double getFS_SIZE() {
		return FS_SIZE;
	}
	public void setFS_SIZE(Double fS_SIZE) {
		FS_SIZE = fS_SIZE;
	}
	public Double getFS_USED_SIZE() {
		return FS_USED_SIZE;
	}
	public void setFS_USED_SIZE(Double fS_USED_SIZE) {
		FS_USED_SIZE = fS_USED_SIZE;
	}
	public String getFS_NASIP() {
		return FS_NASIP;
	}
	public void setFS_NASIP(String fS_NASIP) {
		FS_NASIP = fS_NASIP;
	}
	public String getNAS_DEVICE_ID() {
		return NAS_DEVICE_ID;
	}
	public void setNAS_DEVICE_ID(String nAS_DEVICE_ID) {
		NAS_DEVICE_ID = nAS_DEVICE_ID;
	}
	public List<NasFileSystemObj> getIpList() {
		return ipList;
	}
	public void setIpList(List<NasFileSystemObj> ipList) {
		this.ipList = ipList;
	}
	public String getSHARENUM() {
		return SHARENUM;
	}
	public void setSHARENUM(String sHARENUM) {
		SHARENUM = sHARENUM;
	}
}
