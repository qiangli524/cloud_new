package domain.tbSysUserinfo4A;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @description 
 * @author chenyu
 * @date 上午11:37:36
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserInfo {

	/*
	 * 用户标识
	 */
	private String userId;

	/*
	 * 从账户登录名 
	 */
	private String loginNo;

	/*
	 * 人员姓名
	 */
	private String userName;

	/*
	 * 组织机构标识
	 */
	private String orgId;

	/*
	 * email
	 */
	private String eMail;

	/*
	 * 手机号码
	 */
	private String mobile;

	/*
	 * 登录密码
	 */
	private String passWord;

	/*
	 * 账号状态
	 */
	private String status;

	/*
	 * 生效时间
	 */
	private Date effectDate;

	/*
	 * 失效时间
	 */
	private Date expireDate;

	/*
	 * 账号描述
	 */
	private String remark;
	
	

	/*
	 * 职位
	 */
	private String duty;

	/*
	 * 操作员类别
	 */
	private String operType;
	
	/*
	 * 操作员级别
	 */
	private String operLevel;

	/*
	 * 业务级别
	 */
	private String dutyLevel;

	/**
	 * @return the userId
	 */
	@XmlElement(name = "USERID",required=true)
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the loginNo
	 */
	@XmlElement(name = "LOGINNO",required=true)
	public String getLoginNo() {
		return loginNo;
	}

	/**
	 * @param loginNo
	 */
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	/**
	 * @return the userName
	 */
	@XmlElement(name = "USERNAME",required=true)
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the orgId
	 */
	@XmlElement(name = "ORGID",required=true)
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the eMail
	 */
	@XmlElement(name = "EMAIL",required=true)
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the mobile
	 */
	@XmlElement(name = "MOBILE",required=true)
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the passWord
	 */
	@XmlElement(name = "PASSWORD",required=true)
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the status
	 */
	@XmlElement(name = "STATUS",required=true)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the effectDate
	 */
	@XmlElement(name = "EFFECTDATE",required=true)
	public Date getEffectDate() {
		return effectDate;
	}

	/**
	 * @param effectDate
	 */
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * @return the expireDate
	 */
	@XmlElement(name = "EXPIREDATE",required=true)
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return the remark
	 */
	@XmlElement(name = "REMARK",required=true)
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the duty
	 */
	@XmlElement(name = "DUTY",required=true)
	public String getDuty() {
		return duty;
	}

	/**
	 * @param duty
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
	 * @return the operType
	 */
	@XmlElement(name = "OPERTYPE",required=true)
	public String getOperType() {
		return operType;
	}

	/**
	 * @param operType
	 */
	public void setOperType(String operType) {
		this.operType = operType;
	}

	/**
	 * @return the operLevel
	 */
	@XmlElement(name = "OPERLEVEL",required=true)
	public String getOperLevel() {
		return operLevel;
	}

	/**
	 * @param operLevel
	 */
	public void setOperLevel(String operLevel) {
		this.operLevel = operLevel;
	}

	/**
	 * @return the dutyLevel
	 */
	@XmlElement(name = "DUTYLEVEL",required=true)
	public String getDutyLevel() {
		return dutyLevel;
	}

	/**
	 * @param dutyLevel
	 */
	public void setDutyLevel(String dutyLevel) {
		this.dutyLevel = dutyLevel;
	}
	
}
