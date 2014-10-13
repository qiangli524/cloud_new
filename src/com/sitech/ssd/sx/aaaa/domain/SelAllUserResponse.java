/**
 * SelAllUserResponse.java
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

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:SelAllUserResponse Function: 4A用户管理查询所有用户相应类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:48:52
 */
@XmlRootElement(name = "SelAllUserResponse")
@XmlType(name = "SelAllUserResponse")
public class SelAllUserResponse {
	private List<User> user;

	@XmlElement(name = "User")
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
