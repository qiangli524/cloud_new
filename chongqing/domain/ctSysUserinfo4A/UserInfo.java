package domain.ctSysUserinfo4A;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;  
import javax.xml.bind.annotation.XmlRootElement;




/**
 * @description 
 * @author zhoucang
 * @date 上午11:37:36
 */
@XmlRootElement(name="account", namespace="http://ctSysUserinfoFor4AService.service/")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserInfo {

	/*
	 * 帐号ID
	 */
	
	
	public  String accId;

	/*
	 * 密码
	 */
	
	
	public String userPasswordMD5;
	
	/*
	 * 加密密码
	 */
	
	
	public String userPasswordSHA1;

	/*
	 * 姓名
	 */
	
	
	public String name;

	/*
	 * 邮箱
	 */
	
	
	public String email;

	/*
	 * 性别
	 */
	
	
	public String gender;

	/*
	 * 手机号码
	 */
	
	
	public String mobile;

	/*
	 * 身份证号码
	 */
	
	
	public String idCardNumber;

	

	@XmlElement(name="accId")
	public String getAccId() {
		return accId;
	}
	
	public void setAccId(String accId) {
		this.accId = accId;
	}

	@XmlElement(name="userPasswordMD5")
	public String getUserPasswordMD5() {
		return userPasswordMD5;
	}
	
	public void setUserPasswordMD5(String userPasswordMD5) {
		this.userPasswordMD5 = userPasswordMD5;
	}
	
	@XmlElement(name="userPasswordSHA1")
	public String getuserPasswordSHA1() {
		return userPasswordSHA1;
	}
	
	public void setuserPasswordSHA1(String userPasswordSHA1) {
		this.userPasswordSHA1 = userPasswordSHA1;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement(name="gender")
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@XmlElement(name="mobile")
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@XmlElement(name="idCardNumber")
	public String getIdCardNumber() {
		return idCardNumber;
	}
	
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	

	
}
