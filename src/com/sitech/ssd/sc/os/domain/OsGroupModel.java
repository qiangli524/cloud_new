package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
  * @Title: 操作系统用户组表
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午06:03:16
 */
public class OsGroupModel extends BaseObj {

	private String id;//主键ID
	private String os_host_id;//主机ID     
	private String group_id;//组ID
	private String group_name;//组名
	private String group_desc;//组描述
	private String flag;
	
	public OsGroupModel(){}
	
	public OsGroupModel(String os_host_id){
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
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_desc() {
		return group_desc;
	}

	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
