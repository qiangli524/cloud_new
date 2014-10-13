package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateGroupUser
 * @Description: OS安装模版 -- 用户与组
 * @author JamTau
 * @date 2014-8-20 下午4:50:19
 *
 */
public class OsTemplateGroupUser extends BaseObj {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	private String group_id;//组标识
	private String user_id;//用户标识
	private String flag;//标识用户与组关系类型如主组、附加组
	private String mask;
	
	public OsTemplateGroupUser(){}
	
	public OsTemplateGroupUser(String id,String template_id){
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
	
}
