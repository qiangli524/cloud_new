package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
  * @Title: 用户组与用户关系表
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午06:07:53
 */
public class OsGroupUserModel extends BaseDao {
	
	private String id;//主键ID
	private String os_host_id;//主机ID
	private String group_id;//组ID
	private String user_id;//用户名
	private String mask;
	private String flag;
	
	public OsGroupUserModel(){}
	
	public OsGroupUserModel(String os_host_id){
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
	
	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
