/**
 * HadoopUserGroup.java
 * domain.users
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 一月 8 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package domain.users;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * ClassName:HadoopUserGroup Function: TODO ADD FUNCTION
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 一月 8 15:46:42
 */
public class HadoopUserGroup extends BaseObj {
	private String id;
	private String name;
	private String description;
	private String cluster_id;// 集群id

	private String service_id;// 用户和服务关系表中的主键
	private Integer service_type;// 所属服务
	private String cluster_name;// 集群名称

	private Integer status;// 任务状态 0待处理 1处理中 2处理成功 3处理失败
	private Integer deal_type;// 类型 0创建 1删除
	private Integer entity_type;// 实例类型 0用户 1用户组

	private String serviceArr;// 多个service_type组成的字符串，以","分割
	private List<Integer> typeList;
	private String serviceTypeStr;// 服务名组成的字符串
	private Integer state;
	private String userIds;// 多个用户id组成的字符串
	private String queue_id;// 队列ID
	private String entity_id;// 用户服务关系表的主键
	private String queue_entity_type;// 用户类型

	private String ip;// 主节点ip
	private String systemUserName;// 系统用户名
	private String password;// 系统用户密码
	private Integer purpose;// 是否hadoop用户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public Integer getService_type() {
		return service_type;
	}

	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeal_type() {
		return deal_type;
	}

	public void setDeal_type(Integer deal_type) {
		this.deal_type = deal_type;
	}

	public String getServiceArr() {
		return serviceArr;
	}

	public void setServiceArr(String serviceArr) {
		this.serviceArr = serviceArr;
	}

	public List<Integer> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Integer> typeList) {
		this.typeList = typeList;
	}

	public String getServiceTypeStr() {
		return serviceTypeStr;
	}

	public void setServiceTypeStr(String serviceTypeStr) {
		this.serviceTypeStr = serviceTypeStr;
	}

	public Integer getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(Integer entity_type) {
		this.entity_type = entity_type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getQueue_entity_type() {
		return queue_entity_type;
	}

	public void setQueue_entity_type(String queue_entity_type) {
		this.queue_entity_type = queue_entity_type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSystemUserName() {
		return systemUserName;
	}

	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

}
