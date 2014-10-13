package com.sitech.shop.webservice.domain;

import java.io.Serializable;
import java.util.Date;

public class TbOrderRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uuid;

	private String orderId;

	private String orderRelationInsid;

	private String masterRelationId;

	private String childRelationId;

	private String relationType;

	// 创建时间-接收订购关系的时间
	private Date createDate;

	// 最后更新时间
	private Date lastUpdateDate;

    //DEL 订购关系的关系 变更失败 删除关系失败
	private String boattr1;

	private String boattr2;

	private String boattr3;

	private String boattr4;

	private String boattr5;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid == null ? null : uuid.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getOrderRelationInsid() {
		return orderRelationInsid;
	}

	public void setOrderRelationInsid(String orderRelationInsid) {
		this.orderRelationInsid = orderRelationInsid == null ? null : orderRelationInsid.trim();
	}

	public String getMasterRelationId() {
		return masterRelationId;
	}

	public void setMasterRelationId(String masterRelationId) {
		this.masterRelationId = masterRelationId == null ? null : masterRelationId.trim();
	}

	public String getChildRelationId() {
		return childRelationId;
	}

	public void setChildRelationId(String childRelationId) {
		this.childRelationId = childRelationId == null ? null : childRelationId.trim();
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType == null ? null : relationType.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getBoattr1() {
		return boattr1;
	}

	public void setBoattr1(String boattr1) {
		this.boattr1 = boattr1 == null ? null : boattr1.trim();
	}

	public String getBoattr2() {
		return boattr2;
	}

	public void setBoattr2(String boattr2) {
		this.boattr2 = boattr2 == null ? null : boattr2.trim();
	}

	public String getBoattr3() {
		return boattr3;
	}

	public void setBoattr3(String boattr3) {
		this.boattr3 = boattr3 == null ? null : boattr3.trim();
	}

	public String getBoattr4() {
		return boattr4;
	}

	public void setBoattr4(String boattr4) {
		this.boattr4 = boattr4 == null ? null : boattr4.trim();
	}

	public String getBoattr5() {
		return boattr5;
	}

	public void setBoattr5(String boattr5) {
		this.boattr5 = boattr5 == null ? null : boattr5.trim();
	}
}