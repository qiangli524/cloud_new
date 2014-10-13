package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.List;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;

public class OsGroupUserForm implements Serializable {

	private static final long serialVersionUID = -9077191979555808976L;

	private String id;//主键ID
	private String os_host_id;//主机ID
	private String group_id;//组ID
	private String user_id;//用户名
	private String flag;//主组和附加组标志位
	private String mask;
	
	private String template_id;
	private List<OsGroupUserModel> oguList;
	private List<OsTemplateGroupUser> otguList;
	private List<OsGroupModel> ogList;
	private List<OsUserModel> ouList;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public List<OsGroupUserModel> getOguList() {
		return oguList;
	}
	public void setOguList(List<OsGroupUserModel> oguList) {
		this.oguList = oguList;
	}
	public List<OsTemplateGroupUser> getOtguList() {
		return otguList;
	}
	public void setOtguList(List<OsTemplateGroupUser> otguList) {
		this.otguList = otguList;
	}
	public List<OsGroupModel> getOgList() {
		return ogList;
	}
	public void setOgList(List<OsGroupModel> ogList) {
		this.ogList = ogList;
	}
	public List<OsUserModel> getOuList() {
		return ouList;
	}
	public void setOuList(List<OsUserModel> ouList) {
		this.ouList = ouList;
	}
	public List<OsTemplate> getTemplList() {
		return templList;
	}
	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
	}
	
}
