/**
 * HadoopGroupMember.java
 * domain.users
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 一月 9 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package domain.users;

import java.util.List;

/**
 * ClassName:HadoopGroupMember Hadoop主机用户组和用户关系
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 一月 9 16:31:45
 */
public class HadoopGroupMember {
	private String id;// 主键
	private String group_id;// 用户组id
	private String user_id;// 用户id
	private Integer service_type;// 服务类型
	private Integer status;// 状态 0处理中 1处理成功 2处理失败

	private List<String> idList;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getService_type() {
		return service_type;
	}
	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

}
