package com.sitech.shop.domain.firewall;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/** 
* @ClassName: FirewallObj 
* @Description: TODO(防火墙实体类 对应表：tb_firewall_info) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-4-25 下午1:42:47 
* @version 1.0 
*/
public class FirewallObj extends BaseObj{
	private String id;
	private String name;
	private String description;
	private String area_id;
	private String user_id;
	private String create_time;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p>  
	*/
	public FirewallObj() {
		super();
	}

	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param string 
	*/
	public FirewallObj(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
}
