/**
 * DelUserRequest.java
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
 * ClassName:DelUserRequest Function: 4A用户删除用户信息请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 25 17:40:48
 */
@XmlRootElement(name = "DelUserRequest")
@XmlType(name = "DelUserRequest")
public class DelUserRequest {
	private String uid;

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
