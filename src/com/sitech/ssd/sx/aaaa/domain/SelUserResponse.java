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
 * ClassName:SelUserRequest Function: 查询4A单个用户响应类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:37:00
 */
@XmlRootElement(name = "SelUserResponse")
@XmlType(name = "SelUserResponse")
public class SelUserResponse {
	private User user;

	@XmlElement(name = "User")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
