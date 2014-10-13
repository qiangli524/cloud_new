package domain.tbSysUserinfo4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * <p>Title: ResponseBody</p>
 * <p>Description: 返回给4A的body元素的java对象</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-16 下午2:47:52
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseBody {

	// 校验不通过的信息
	private String key;
	private String errCode;
	
	
	// 执行结果的信息
	private String modifyMode;
	private String userId;
	private String loginNo;
	private String rsp;
	

	// 公用
	private String errDesc;


	/**
	 * @return the key
	 */
	@XmlElement(name = "KEY",required=true)
	public String getKey() {
		return key;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * @return the errCode
	 */
	@XmlElement(name = "ERRCODE",required=true)
	public String getErrCode() {
		return errCode;
	}


	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	/**
	 * @return the modifyMode
	 */
	@XmlElement(name = "MODIFYMODE",required=true)
	public String getModifyMode() {
		return modifyMode;
	}


	/**
	 * @param modifyMode the modifyMode to set
	 */
	public void setModifyMode(String modifyMode) {
		this.modifyMode = modifyMode;
	}


	/**
	 * @return the userId
	 */
	@XmlElement(name = "USERID",required=true)
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the loginNo
	 */
	@XmlElement(name = "LOGINNO",required=true)
	public String getLoginNo() {
		return loginNo;
	}


	/**
	 * @param loginNo the loginNo to set
	 */
	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}


	/**
	 * @return the rsp
	 */
	@XmlElement(name = "RSP",required=true)
	public String getRsp() {
		return rsp;
	}


	/**
	 * @param rsp the rsp to set
	 */
	public void setRsp(String rsp) {
		this.rsp = rsp;
	}


	/**
	 * @return the errDesc
	 */
	@XmlElement(name = "ERRDESC",required=true)
	public String getErrDesc() {
		return errDesc;
	}


	/**
	 * @param errDesc the errDesc to set
	 */
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
	
	
	
}
