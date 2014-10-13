/**
 * SelRoleResponse.java
 * com.sitech.ssd.sx.aaaa.domain
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 26 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:SelRoleResponse Function: 4A用户管理查询角色响应类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 11:53:00
 */
@XmlRootElement(name = "SelRoleResponse")
@XmlType(name = "SelRoleResponse")
public class SelRoleResponse {
	private List<Role> role;

	@XmlElement(name = "Role")
	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
}
