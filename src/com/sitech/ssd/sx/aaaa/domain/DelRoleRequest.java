/**
 * DelRoleRequest.java
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
 * ClassName:DelRoleRequest Function: 4A用户管理删除角色请求类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 11:46:59
 */
@XmlRootElement(name = "DelRoleRequest")
@XmlType(name = "DelRoleRequest")
public class DelRoleRequest {
	private String roleid;

	@XmlElement(name = "roleid")
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
