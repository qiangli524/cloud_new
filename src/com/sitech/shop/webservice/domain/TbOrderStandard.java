package com.sitech.shop.webservice.domain;

import java.io.Serializable;
import java.util.Date;

public class TbOrderStandard implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    private String orderId;

    private String prdctSpecId;

    private String productInstanceId;

    private String specId;

    private String specValue;

    private String specValueDesc;

    private String userId;

    private String bookId;

    private Date createDate;

    private Date lastUpdateDate;

    private String durationUnit;
    
    //放入购买类型 1:后付费 2：预付费
    private String payDuration;

    private Date expiringDate;

    //磁盘的唯一标示
    private String boattr1;
    //产品ID
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

    public String getPrdctSpecId() {
        return prdctSpecId;
    }

    public void setPrdctSpecId(String prdctSpecId) {
        this.prdctSpecId = prdctSpecId == null ? null : prdctSpecId.trim();
    }

    public String getProductInstanceId() {
        return productInstanceId;
    }

    public void setProductInstanceId(String productInstanceId) {
        this.productInstanceId = productInstanceId == null ? null : productInstanceId.trim();
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId == null ? null : specId.trim();
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue == null ? null : specValue.trim();
    }

    public String getSpecValueDesc() {
        return specValueDesc;
    }

    public void setSpecValueDesc(String specValueDesc) {
        this.specValueDesc = specValueDesc == null ? null : specValueDesc.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
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

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit == null ? null : durationUnit.trim();
    }

    public String getPayDuration() {
        return payDuration;
    }

    public void setPayDuration(String payDuration) {
        this.payDuration = payDuration == null ? null : payDuration.trim();
    }

    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
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