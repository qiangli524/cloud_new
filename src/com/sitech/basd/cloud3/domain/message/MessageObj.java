package com.sitech.basd.cloud3.domain.message;

import java.sql.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class MessageObj extends BaseObj {
	private String seq;
	private String templateid;
	private String parameter;
	private String service_no;
	private String phone_no;
	private Date insert_time;
	private String send_flag;
	private String op_code;
	private String login_no;
	private String serv_no;
	private String serv_name;
	private String sub_phone_seq;
	private Date send_time;
	private Date real_send_time;
	private String hold1;
	private String hold2;
	private String hold3;
	private String hold4;
	private String hold5;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getService_no() {
		return service_no;
	}

	public void setService_no(String service_no) {
		this.service_no = service_no;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public Date getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Date insert_time) {
		this.insert_time = insert_time;
	}

	public String getSend_flag() {
		return send_flag;
	}

	public void setSend_flag(String send_flag) {
		this.send_flag = send_flag;
	}

	public String getOp_code() {
		return op_code;
	}

	public void setOp_code(String op_code) {
		this.op_code = op_code;
	}

	public String getLogin_no() {
		return login_no;
	}

	public void setLogin_no(String login_no) {
		this.login_no = login_no;
	}

	public String getServ_no() {
		return serv_no;
	}

	public void setServ_no(String serv_no) {
		this.serv_no = serv_no;
	}

	public String getServ_name() {
		return serv_name;
	}

	public void setServ_name(String serv_name) {
		this.serv_name = serv_name;
	}

	public String getSub_phone_seq() {
		return sub_phone_seq;
	}

	public void setSub_phone_seq(String sub_phone_seq) {
		this.sub_phone_seq = sub_phone_seq;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public Date getReal_send_time() {
		return real_send_time;
	}

	public void setReal_send_time(Date real_send_time) {
		this.real_send_time = real_send_time;
	}

	public String getHold1() {
		return hold1;
	}

	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}

	public String getHold2() {
		return hold2;
	}

	public void setHold2(String hold2) {
		this.hold2 = hold2;
	}

	public String getHold3() {
		return hold3;
	}

	public void setHold3(String hold3) {
		this.hold3 = hold3;
	}

	public String getHold4() {
		return hold4;
	}

	public void setHold4(String hold4) {
		this.hold4 = hold4;
	}

	public String getHold5() {
		return hold5;
	}

	public void setHold5(String hold5) {
		this.hold5 = hold5;
	}

}
