package domain.tbSysUserinfo4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @description 
 * @author chenyu
 * @date 上午11:42:57
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseHead {
	
	private String code="";
	
	private String sid="";
	
	private String timestamp="";
	
	private String serviceid="";

	/**
	 * @return the code
	 */
	@XmlElement(name="CODE",required=true)
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the sid
	 */
	@XmlElement(name="SID",required=true)
	public String getSid() {
		return sid;
	}

	/**
	 * @param sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * @return the timestamp
	 */
	@XmlElement(name="TIMESTAMP",required=true)
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the serviceid
	 */
	@XmlElement(name="SERVICEID",required=true)
	public String getServiceid() {
		return serviceid;
	}

	/**
	 * @param serviceid
	 */
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	
	

}
