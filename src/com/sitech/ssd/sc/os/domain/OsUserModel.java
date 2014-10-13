package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @ClassName:操作系统用户表
 * @Desc:
 * @Author:JamTau
 * @date:May 21, 2014 6:33:06 PM
 */
public class OsUserModel extends BaseObj { 
	
	private String id;//主键ID
	private String os_host_id;//主机ID
	private String user_name;//用户名(ID)
	private String pass_word;//用户口令
	private String primary_group;//主组    
	private String home_dir;//家目录
	private String user_id;
	private String user_desc;
	private String flag;
	
	public OsUserModel(){}
	
	public OsUserModel(String os_host_id){
		this.os_host_id = os_host_id;
	}
	
	public OsUserModel(String os_host_id,String user_name,String pass_word){
		this.os_host_id = os_host_id;
		this.user_name = user_name;
		this.pass_word = pass_word;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
