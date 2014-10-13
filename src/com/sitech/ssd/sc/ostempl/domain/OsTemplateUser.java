package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateUser
 * @Description: OS安装模版 --用户
 * @author JamTau
 * @date 2014-8-20 下午4:48:27
 *
 */
public class OsTemplateUser extends BaseObj {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	private String user_name;//用户标识
	private String pass_word;//用户口令
	private String primary_group;//主组
	private String home_dir;//家目录（输入）
	private String user_id;//用户ID号
	private String user_desc;//描述信息
	
	public OsTemplateUser(){}
	
	public OsTemplateUser(String id,String template_id){
		this.id = id;
		this.template_id = template_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
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
}
