/**
 * AddUserResponse.java
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
 * ClassName:AddUserResponse Function: 4A增加用户响应类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:32:40
 */
@XmlRootElement(name = "AddUserResponse")
@XmlType(name = "AddUserResponse")
public class AddUserResponse {
	private Ret ret;

	@XmlElement(name = "Ret")
	public Ret getRet() {
		return ret;
	}

	public void setRet(Ret ret) {
		this.ret = ret;
	}
}
