/**
 * OperationUserPwdResponse.java
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
 * ClassName:OperationUserPwdResponse Function: 4A用户密码修改响应类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 25 17:33:33
 */
@XmlRootElement(name = "OperationUserPwdResponse")
@XmlType(name = "OperationUserPwdResponse")
public class OperationUserPwdResponse {
	private Ret ret;

	@XmlElement(name = "Ret")
	public Ret getRet() {
		return ret;
	}

	public void setRet(Ret ret) {
		this.ret = ret;
	}

}
