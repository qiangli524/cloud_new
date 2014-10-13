/**
 * OperationUserRequest.java
 * com.sitech.ssd.sx.aaaa.domain
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 四月 3 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:OperationUserRequest Function: 用户暂挂、恢复请求类
 * 
 * @author duangh
 */
@XmlRootElement(name = "OperationUserRequest")
@XmlType(name = "OperationUserRequest")
public class OperationUserRequest {
	private String uid;
	private String enable;

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@XmlElement(name = "enable")
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
