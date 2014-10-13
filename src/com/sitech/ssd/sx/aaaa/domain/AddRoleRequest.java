/**
 * AddRoleRequest.java
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
 * ClassName:AddRoleRequest Function: 4A用户管理增加角色请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 11:31:45
 */
@XmlRootElement(name = "AddRoleRequest")
@XmlType(name = "AddRoleRequest")
public class AddRoleRequest {
	private Role role;

	@XmlElement(name = "Role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
