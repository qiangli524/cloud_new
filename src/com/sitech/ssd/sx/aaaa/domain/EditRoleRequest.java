/**
 * EditRoleRequest.java
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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:EditRoleRequest Function: 4A用户管理编辑角色请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 11:33:13
 */
@XmlRootElement(name = "EditRoleRequest")
@XmlType(name = "EditRoleRequest")
public class EditRoleRequest {
	private Role role;

	@XmlElement(name = "Role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
