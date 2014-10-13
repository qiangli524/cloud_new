/**
 * Ret.java
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
 * ClassName:Ret Function: 4A用户管理返回类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:30:18
 */
@XmlRootElement(name = "Ret")
@XmlType(name = "Ret")
public class Ret {
	private String code;
	private String message;

	@XmlElement(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
