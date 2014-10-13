package com.sitech.basd.sxcloud.cloud.domain.resource;
/**
 * <p>Title: TbCloudEntityUser</p>
 * <p>Description: 用于工单改造
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-14 下午7:20:51
 *
 */
public class TbCloudEntityUser {
	private String entity_name;
	private String type;
	private int userid;
	private String entity_type;
	private String entity_id;
	private String operauthority;
	private String connect_id;
	public String getEntity_name() {
		return entity_name;
	}
	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getOperauthority() {
		return operauthority;
	}
	public void setOperauthority(String operauthority) {
		this.operauthority = operauthority;
	}
	public String getConnect_id() {
		return connect_id;
	}
	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}
	
}
