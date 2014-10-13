package com.sitech.shop.webservice.domain;

import java.io.Serializable;
import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TbOrderRelationInstance extends BaseObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uuid;

	private String orderId;

	private String productInstanceId;

	private String customerId;

	private String ifPrimary;

	private String productId;

	private String status;

	private String acctId;

	private String servId;

	private String createStatus;

	private String vmId;

	private String vmName;

	private String vmLoginName;

	private String vmLoginPassword;

	//公网IP 
	private String publicIpAddress;
    //负载均衡  和带宽   的唯一标示
	private String ipAddress;

	private String isSendsms;

	private String isSendshop;

	private String vCenterCode;

	private Date createDate;

	private Date returnShopDate;

	private Date lastUpdateDate;

	private String changeStatus;

	private Date serviceBeginTime;

	private Date serviceEndTime;

	private String boattr1;

	private String boattr2;

	private String boattr3;

	private String boattr4;

	//是否第一次购买
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

	public String getProductInstanceId() {
		return productInstanceId;
	}

	public void setProductInstanceId(String productInstanceId) {
		this.productInstanceId = productInstanceId == null ? null : productInstanceId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getIfPrimary() {
		return ifPrimary;
	}

	public void setIfPrimary(String ifPrimary) {
		this.ifPrimary = ifPrimary == null ? null : ifPrimary.trim();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId == null ? null : acctId.trim();
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId == null ? null : servId.trim();
	}

	public String getCreateStatus() {
		return createStatus;
	}

	public void setCreateStatus(String createStatus) {
		this.createStatus = createStatus == null ? null : createStatus.trim();
	}

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId == null ? null : vmId.trim();
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName == null ? null : vmName.trim();
	}

	public String getVmLoginName() {
		return vmLoginName;
	}

	public void setVmLoginName(String vmLoginName) {
		this.vmLoginName = vmLoginName == null ? null : vmLoginName.trim();
	}

	public String getVmLoginPassword() {
		return vmLoginPassword;
	}

	public void setVmLoginPassword(String vmLoginPassword) {
		this.vmLoginPassword = vmLoginPassword == null ? null : vmLoginPassword.trim();
	}

	public String getPublicIpAddress() {
		return publicIpAddress;
	}

	public void setPublicIpAddress(String publicIpAddress) {
		this.publicIpAddress = publicIpAddress == null ? null : publicIpAddress.trim();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public String getIsSendsms() {
		return isSendsms;
	}

	public void setIsSendsms(String isSendsms) {
		this.isSendsms = isSendsms == null ? null : isSendsms.trim();
	}

	public String getIsSendshop() {
		return isSendshop;
	}

	public void setIsSendshop(String isSendshop) {
		this.isSendshop = isSendshop == null ? null : isSendshop.trim();
	}

	public String getvCenterCode() {
		return vCenterCode;
	}

	public void setvCenterCode(String vCenterCode) {
		this.vCenterCode = vCenterCode == null ? null : vCenterCode.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getReturnShopDate() {
		return returnShopDate;
	}

	public void setReturnShopDate(Date returnShopDate) {
		this.returnShopDate = returnShopDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus == null ? null : changeStatus.trim();
	}

	public Date getServiceBeginTime() {
		return serviceBeginTime;
	}

	public void setServiceBeginTime(Date serviceBeginTime) {
		this.serviceBeginTime = serviceBeginTime;
	}

	public Date getServiceEndTime() {
		return serviceEndTime;
	}

	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
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