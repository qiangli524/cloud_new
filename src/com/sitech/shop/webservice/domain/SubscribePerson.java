package com.sitech.shop.webservice.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * 告警订阅人信息
 * @author jily
 *
 */
public class SubscribePerson {
	private String login_id;//订阅人ID
	private String isSubSms;//是否发送短信  是：1或者否：0
	private String login_phone;//订阅人电话号码
	private String isSubEmail;//是否发送邮件 是：1或者否：0
	private String login_mail;//订阅人邮箱地址
	
	public SubscribePerson(){
		
	}
	public SubscribePerson(String login_id, String isSubSms,
			String login_phone, String isSubEmail, String login_mail) {
		this.login_id = login_id;
		this.isSubSms = isSubSms;
		this.login_phone = login_phone;
		this.isSubEmail = isSubEmail;
		this.login_mail = login_mail;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getIsSubSms() {
		return isSubSms;
	}
	public void setIsSubSms(String isSubSms) {
		this.isSubSms = isSubSms;
	}
	public String getLogin_phone() {
		return login_phone;
	}
	public void setLogin_phone(String login_phone) {
		this.login_phone = login_phone;
	}
	public String getIsSubEmail() {
		return isSubEmail;
	}
	public void setIsSubEmail(String isSubEmail) {
		this.isSubEmail = isSubEmail;
	}
	public String getLogin_mail() {
		return login_mail;
	}
	public void setLogin_mail(String login_mail) {
		this.login_mail = login_mail;
	}
	
}
