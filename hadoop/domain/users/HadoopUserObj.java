/**
 * HadoopUsers.java
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

import java.util.Date;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: HadoopUserObj
 * </p>
 * <p>
 * Description: hadoop用户
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-2-24 下午3:28:04
 * 
 */
public class HadoopUserObj extends BaseObj {
	private String id;// 主键
	private String ip;// 所属主机ip
	private String username;// 用户名
	private String password;// 密码
	private String mac;// mac地址
	private Date insertTime;// 插入时间
	private Date updateTime;// 更新时间
	private String type;// 用户类型
	private String cluster_id;// 集群id

	private String service_id;// 用户和服务关系表中的主键
	private Integer service_type;// 服务类型
	private Integer status;// 任务状态 0待处理 1处理中 2处理成功 3处理失败
	private Integer deal_type;// 类型 0创建 1回收
	private Integer entity_type;// 实例类型

	private String cluster_name;// 集群名称
	private String associatedFlag;// 是否已经关联过
	private String serviceArr;// 多个service_type组成的字符串，以","分割
	private List<Integer> typeList;
	private String serviceTypeStr;// 服务名组成的字符串
	private Integer state;
	private String groupId;// 所属用户组的id
	private String memberId;// 用户与用户组关系表id
	private Integer memberStatus;// 关联状态
	private Integer memberType;// 处理状态
	private String queue_id;// 队列ID
	private String entity_id;// 用户服务关系表的主键
	private Integer purpose;// 是否hadoop用户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssociatedFlag() {
		return associatedFlag;
	}

	public void setAssociatedFlag(String associatedFlag) {
		this.associatedFlag = associatedFlag;
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

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
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

	public Integer getPurpose() {
		return purpose;
	}

	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}

}
