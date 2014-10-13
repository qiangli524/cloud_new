package domain.tbSysUserinfo4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @description 返回4A的xml对象
 * @author chenyu
 * @date 上午11:27:53
 */
@XmlRootElement(name="USERMODIFYREQ")
@XmlType(propOrder={"head","body"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UserModifyReq {

	private RequestHead head;

	private RequestBody body;

	/**
	 * @return the head
	 */
	@XmlElement(name = "HEAD",required=true)
	public RequestHead getHead() {
		return head;
	}

	/**
	 * @param head
	 */
	public void setHead(RequestHead head) {
		this.head = head;
	}

	/**
	 * @return the body
	 */
	@XmlElement(name = "BODY")
	public RequestBody getBody() {
		return body;
	}

	/**
	 * @param body
	 */
	public void setBody(RequestBody body) {
		this.body = body;
	}

}
