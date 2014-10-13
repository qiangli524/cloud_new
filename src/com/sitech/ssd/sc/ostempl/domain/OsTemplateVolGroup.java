package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateVolGroup
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-16 下午6:29:49
 *
 */
public class OsTemplateVolGroup extends BaseObj {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	private String vg_name;//VG名称
	private String vg_pvno;//PV编号
	private String vg_pesize;//大小
	private String vg_desc;//描述
	
	public OsTemplateVolGroup(){
		
	}
	
	public OsTemplateVolGroup(String id,String template_id){
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
	public String getVg_name() {
		return vg_name;
	}
	public void setVg_name(String vg_name) {
		this.vg_name = vg_name;
	}
	public String getVg_pvno() {
		return vg_pvno;
	}
	public void setVg_pvno(String vg_pvno) {
		this.vg_pvno = vg_pvno;
	}
	public String getVg_pesize() {
		return vg_pesize;
	}
	public void setVg_pesize(String vg_pesize) {
		this.vg_pesize = vg_pesize;
	}
	public String getVg_desc() {
		return vg_desc;
	}
	public void setVg_desc(String vg_desc) {
		this.vg_desc = vg_desc;
	}
}
