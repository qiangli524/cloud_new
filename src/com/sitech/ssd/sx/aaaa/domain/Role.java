/**
 * Role.java
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
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:Role Function: 4A用户角色类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 10:48:34
 */
@XmlType(name = "Role")
public class Role {
	private String roleId;
	private String roleName;
	private String permissionId;
	private String roleDes;

	@XmlElement(name = "roleid")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@XmlElement(name = "rolename")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@XmlElement(name = "permissionid")
	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@XmlElement(name = "roledes")
	public String getRoleDes() {
		return roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

}
