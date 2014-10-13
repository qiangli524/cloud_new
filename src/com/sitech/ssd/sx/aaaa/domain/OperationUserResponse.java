package com.sitech.ssd.sx.aaaa.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ClassName:OperationUserResponse Function: 用户暂挂、恢复响应类
 * 
 * @author duangh
 * @Date 2014 四月 3 10:57:16
 */
@XmlRootElement(name = "OperationUserResponse")
@XmlType(name = "OperationUserResponse")
public class OperationUserResponse {
	private Ret ret;

	@XmlElement(name = "Ret")
	public Ret getRet() {
		return ret;
	}

	public void setRet(Ret ret) {
		this.ret = ret;
	}
}
