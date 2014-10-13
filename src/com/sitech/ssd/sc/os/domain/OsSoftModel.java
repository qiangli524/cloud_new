package com.sitech.ssd.sc.os.domain;

/**
 * 
 * @ClassName: OsSoftModel
 * @Description: 软件包
 * @author JamTau
 * @date 2014-9-10 上午10:07:17
 *
 */
public class OsSoftModel {
	
	private String id;
	private String os_host_id;
	private String soft_type;
	private String soft_name;
	private String soft_desc;
	private String flag;
	
	public OsSoftModel(){}
	
	public OsSoftModel(String os_host_id){
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
