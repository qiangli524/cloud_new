/**
 * OperationUserPwdRequest.java
 * com.sitech.ssd.sx.aaaa.domain
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 25 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:OperationUserPwdRequest Function: 4A用户修改密码信息请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 25 17:30:57
 */
@XmlRootElement(name = "OperationUserPwdRequest")
@XmlType(name = "OperationUserPwdRequest")
public class OperationUserPwdRequest {
	private String uid;
	private String pwd;

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@XmlElement(name = "pwd")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
