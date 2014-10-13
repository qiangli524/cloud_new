/**
 * SelUserRequest.java
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
 * ClassName:SelUserRequest Function: 4A用户管理查询单个用户请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 25 14:44:35
 */
@XmlType(name = "SelUserRequest")
@XmlRootElement(name = "SelUserRequest")
public class SelUserRequest {
	private String uid;

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
