package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.List;

import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;

public class OsFileSystemForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;//主键ID
	private String os_host_id;//主机iD
	private String order_num;//序号
	private String filesys_name;//文件系统名
	private String filesys_type;//文件系统类型
	private String filesys_size;//文件系统大小
	private String filesys_desc;//描述信息
	private String volume_group;//卷组
	private String logical_volume;//逻辑卷
	
	private String template_id;//模版编号
	private List<OsFileSystemModel> ofsList;
	private List<OsTemplateFileSystem> otfsList;
	private List<TbSysDictObj> sdList;
	private List<OsTemplate> templList;
	private List<OsTemplateVolGroup> vgList;
	
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
	public List<OsFileSystemModel> getOfsList() {
		return ofsList;
	}
	public void setOfsList(List<OsFileSystemModel> ofsList) {
		this.ofsList = ofsList;
	}
	public List<OsTemplateFileSystem> getOtfsList() {
		return otfsList;
	}
	public void setOtfsList(List<OsTemplateFileSystem> otfsList) {
		this.otfsList = otfsList;
	}
	public List<TbSysDictObj> getSdList() {
		return sdList;
	}
	public void setSdList(List<TbSysDictObj> sdList) {
		this.sdList = sdList;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public List<OsTemplate> getTemplList() {
		return templList;
	}
	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
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
	public List<OsTemplateVolGroup> getVgList() {
		return vgList;
	}
	public void setVgList(List<OsTemplateVolGroup> vgList) {
		this.vgList = vgList;
	}
	
}
