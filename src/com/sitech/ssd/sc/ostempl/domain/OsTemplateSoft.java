package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateSoft
 * @Description: 安装模版        软件包
 * @author JamTau
 * @date 2014-9-8 下午3:48:07
 *
 */
public class OsTemplateSoft extends BaseObj {
	
	private String id;
	private String template_id;
	private String soft_type;
	private String soft_name;
	private String soft_desc;

	public OsTemplateSoft(){}
	
	public OsTemplateSoft(String id,String template_id){
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

	public String getSoft_type() {
		return soft_type;
	}

	public void setSoft_type(String soft_type) {
		this.soft_type = soft_type;
	}

	public String getSoft_name() {
		return soft_name;
	}

	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}

	public String getSoft_desc() {
		return soft_desc;
	}

	public void setSoft_desc(String soft_desc) {
		this.soft_desc = soft_desc;
	}
}

