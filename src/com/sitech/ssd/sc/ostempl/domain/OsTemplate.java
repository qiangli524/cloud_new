package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @ClassName: OsTemplate
 * @Description: OS安装模版
 * @author JamTau
 * @date 2014-8-20 下午4:45:24
 */
public class OsTemplate extends BaseObj{
	
	private String id;//安装模版ID,
	private String templ_name;//模版名称
	private String templ_desc;//模版描述
	private String os_version;//OS版本
	
	public OsTemplate(){}
	
	public OsTemplate(String id){
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTempl_name() {
		return templ_name;
	}
	public void setTempl_name(String templ_name) {
		this.templ_name = templ_name;
	}
	public String getTempl_desc() {
		return templ_desc;
	}
	public void setTempl_desc(String templ_desc) {
		this.templ_desc = templ_desc;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

}
