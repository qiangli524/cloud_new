package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;

public class OsGroupForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;//主键ID
	private String os_host_id;//主机ID     
	private String group_id;//组ID
	private String group_name;//组名
	private String group_desc;//描述
	
	private String template_id;//模版编号
	private List<OsTemplateGroup> otglist;
	private List<OsGroupModel> oglist;
	private List<OsTemplate> templList;
	
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public List<OsTemplateGroup> getOtglist() {
		return otglist;
	}
	public void setOtglist(List<OsTemplateGroup> otglist) {
		this.otglist = otglist;
	}
	public List<OsGroupModel> getOglist() {
		return oglist;
	}
	public void setOglist(List<OsGroupModel> oglist) {
		this.oglist = oglist;
	}
	public List<OsTemplate> getTemplList() {
		return templList;
	}
	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
	}
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}
	
}
