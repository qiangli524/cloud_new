package domain.tbSysUserinfo4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Title: USERMODIFYRSP
 * </p>
 * <p>
 * Description: 处理完用户操作之后返回给4A的信息对应的JAVA对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-16 下午2:41:37
 * 
 */
@XmlRootElement(name = "USERMODIFYRSP")
@XmlType(propOrder={"head","body"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserModifyRSP {

	private ResponseHead head;
	private ResponseBody body;

	/**
	 * @return the head
	 */
	@XmlElement(name = "HEAD",required=true)
	public ResponseHead getHead() {
		return head;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(ResponseHead head) {
		this.head = head;
	}

	/**
	 * @return the body
	 */
	@XmlElement(name = "BODY",required=true)
	public ResponseBody getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(ResponseBody body) {
		this.body = body;
	}

}
