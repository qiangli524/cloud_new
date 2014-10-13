/**
 * AddRoleResponse.java
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
 * ClassName:AddRoleResponse Function: 4A增加角色响应类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 10:46:45
 */
@XmlRootElement(name = "AddRoleResponse")
@XmlType(name = "AddRoleResponse")
public class AddRoleResponse {
	private Ret ret;

	@XmlElement(name = "Ret")
	public Ret getRet() {
		return ret;
	}

	public void setRet(Ret ret) {
		this.ret = ret;
	}
}
