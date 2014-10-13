package com.sitech.basd.cloud3.domain.departproject;

/**
 * <p>Title: DepartHisInfoObj</p>
 * <p>Description: 部门资源历史记录实体类   对应表tb_depart_his_info</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-11 下午6:24:22
 *
 */
public class DepartHisInfoObj {

	private Integer id;//主键 自增长
	private String depart_id;//部门id
	private String resource_size;//资源大小
	private String resource_used_size;//资源已使用大小
	private Integer resource_type;//资源类型
	private String coll_time;//采集时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(String depart_id) {
		this.depart_id = depart_id;
	}
	public String getColl_time() {
		return coll_time;
	}
	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}
	public String getResource_size() {
		return resource_size;
	}
	public void setResource_size(String resource_size) {
		this.resource_size = resource_size;
	}
	public Integer getResource_type() {
		return resource_type;
	}
	public void setResource_type(Integer resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_used_size() {
		return resource_used_size;
	}
	public void setResource_used_size(String resource_used_size) {
		this.resource_used_size = resource_used_size;
	}
	
}
