package domain.login4A;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import domain.tbSysUserinfo4A.RequestHead;

/**
 * <p>Title: UserRsp</p>
 * <p>Description: 去4A校验token后返回的java对象</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-17 上午11:14:18
 *
 */
@XmlRootElement(name="USERRSP")
public class UserRsp {
	
	private RequestHead head;
	
	private Request4ABody body;

	/**
	 * @return the head
	 */
	@XmlElement(name="HEAD")
	public RequestHead getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(RequestHead head) {
		this.head = head;
	}

	/**
	 * @return the body
	 */
	@XmlElement(name="BODY")
	public Request4ABody getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Request4ABody body) {
		this.body = body;
	}
	
	

}
