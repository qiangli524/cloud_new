package com.sitech.shop.webservice.domain;

import java.util.Date;

public class TbSysUserinfo {

    private Integer id;


    private String account;


    private String password;


    private String name;


    private String mobile;


    private String email;


    private Long status;


    private Date createtime;


    private Long createuser;


    private String dataauthority;


    private String domain;


    private String userlevel;

    private String userType;

    private String paymentType;

    private String shopUserId;
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getStatus() {
        return status;
    }


    public void setStatus(Long status) {
        this.status = status;
    }


    public Date getCreatetime() {
        return createtime;
    }


    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public Long getCreateuser() {
        return createuser;
    }


    public void setCreateuser(Long createuser) {
        this.createuser = createuser;
    }


    public String getDataauthority() {
        return dataauthority;
    }


    public void setDataauthority(String dataauthority) {
        this.dataauthority = dataauthority == null ? null : dataauthority.trim();
    }


    public String getDomain() {
        return domain;
    }


    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }


    public String getUserlevel() {
        return userlevel;
    }


    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel == null ? null : userlevel.trim();
    }


    public String getUserType() {
        return userType;
    }


    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }


    public String getPaymentType() {
        return paymentType;
    }


    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }
    public String getShopUserId() {
        return shopUserId;
    }


    public void setShopUserId(String shopUserId) {
        this.shopUserId = shopUserId == null ? null : shopUserId.trim();
    }
}