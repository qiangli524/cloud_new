package com.sitech.ssd.sc.os.web;

import java.io.Serializable;
import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;

public class OsUserForm implements Serializable {

	private static final long serialVersionUID = 3685530410827606715L;

	private String id;//主键ID
	private String os_host_id;//主机ID
	private String user_name;//用户名(ID)
	private String pass_word;//用户口令
	private String primary_group;//主组    
	private String home_dir;//家目录
	private String user_id;
	private String user_desc;
	
	private String template_id;
	private List<OsUserModel> ouList;
	private List<OsTemplateUser> otuList;
	private List<OsGroupModel> ogList;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	public String getPrimary_group() {
		return primary_group;
	}
	public void setPrimary_group(String primary_group) {
		this.primary_group = primary_group;
	}
	public String getHome_dir() {
		return home_dir;
	}
	public void setHome_dir(String home_dir) {
		this.home_dir = home_dir;
	}
	public List<OsUserModel> getOuList() {
		return ouList;
	}
	public void setOuList(List<OsUserModel> ouList) {
		this.ouList = ouList;
	}
	public List<OsTemplateUser> getOtuList() {
		return otuList;
	}
	public void setOtuList(List<OsTemplateUser> otuList) {
		this.otuList = otuList;
	}
	public List<OsGroupModel> getOgList() {
		return ogList;
	}
	public void setOgList(List<OsGroupModel> ogList) {
		this.ogList = ogList;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
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
	
}
