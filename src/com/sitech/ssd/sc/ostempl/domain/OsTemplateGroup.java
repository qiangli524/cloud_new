package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateGroup
 * @Description: OS安装模版 -- 用户组
 * @author JamTau
 * @date 2014-8-20 下午4:49:10
 *
 */
public class OsTemplateGroup extends BaseObj {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	private String group_id;//组ID
	private String group_name;//组标识
	private String group_desc;//组描述
	
	public OsTemplateGroup(){}
	
	public OsTemplateGroup(String id,String template_id){
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
	
}
