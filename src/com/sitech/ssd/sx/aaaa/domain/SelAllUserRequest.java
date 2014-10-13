/**
 * SelAllUserRequest.java
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
 * ClassName:SelAllUserRequest Function: 4A用户管理查询所有用户请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 25 14:47:28
 */
@XmlRootElement(name = "SelAllUserRequest")
@XmlType(name = "SelAllUserRequest")
public class SelAllUserRequest {
	private String uid = "all";// 此处约定填写固定值“all”

	@XmlElement(name = "uid")
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
