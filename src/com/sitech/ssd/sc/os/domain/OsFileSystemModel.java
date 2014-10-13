package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
  * @Title: 操作系统文件系统
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午06:06:02
 */
public class OsFileSystemModel extends BaseObj {
	
	private String id;//主键ID
	private String os_host_id;//主机iD
	private String order_num;//序号
	private String filesys_name;//文件系统名
	private String filesys_type;//文件系统类型
	private String filesys_size;//文件系统大小
	private String filesys_desc;//描述信息
	private String volume_group;//卷组
	private String logical_volume;//逻辑卷
	private String flag;
	
	public OsFileSystemModel(){
		
	}
	
	public OsFileSystemModel(String os_host_id){
		this.os_host_id = os_host_id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOs_host_id() {
		return os_host_id;
	}
	public void setOs_host_id(String os_host_id) {
		this.os_host_id = os_host_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public String getFilesys_name() {
		return filesys_name;
	}
	public void setFilesys_name(String filesys_name) {
		this.filesys_name = filesys_name;
	}
	public String getFilesys_type() {
		return filesys_type;
	}
	public void setFilesys_type(String filesys_type) {
		this.filesys_type = filesys_type;
	}
	public String getFilesys_size() {
		return filesys_size;
	}
	public void setFilesys_size(String filesys_size) {
		this.filesys_size = filesys_size;
	}

	public String getFilesys_desc() {
		return filesys_desc;
	}

	public void setFilesys_desc(String filesys_desc) {
		this.filesys_desc = filesys_desc;
	}

	public String getVolume_group() {
		return volume_group;
	}

	public void setVolume_group(String volume_group) {
		this.volume_group = volume_group;
	}

	public String getLogical_volume() {
		return logical_volume;
	}

	public void setLogical_volume(String logical_volume) {
		this.logical_volume = logical_volume;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
