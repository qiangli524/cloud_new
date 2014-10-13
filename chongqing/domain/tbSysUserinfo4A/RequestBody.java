package domain.tbSysUserinfo4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @description
 * @author chenyu
 * @date 上午11:39:53
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RequestBody {

	/*
	 * 管理员从账号
	 */
	private String operator;
	/*
	 * 管理员密码
	 */
	private String operatorPwd;
	/*
	 * 管理员ip
	 */
	private String operatorIp;
	/*
	 * 操作类型add、delete、change、chgstatus、resetpwd
	 */
	private String modifyMode;
	
	/*
	 * 变更的用户信息
	 */
	private UserInfo userInfo;

	/**
	 * @return the operator
	 */
	@XmlElement(name="OPERATORID")
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the operatorPwd
	 */
	@XmlElement(name="OPERATORPWD")
	public String getOperatorPwd() {
		return operatorPwd;
	}

	/**
	 * @param operatorPwd
	 */
	public void setOperatorPwd(String operatorPwd) {
		this.operatorPwd = operatorPwd;
	}

	/**
	 * @return the operatorIp
	 */
	@XmlElement(name="OPERATORIP")
	public String getOperatorIp() {
		return operatorIp;
	}

	/**
	 * @param operatorIp
	 */
	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	/**
	 * @return the modifyMode
	 */
	@XmlElement(name="MODIFYMODE")
	public String getModifyMode() {
		return modifyMode;
	}

	/**
	 * @param modifyMode
	 */
	public void setModifyMode(String modifyMode) {
		this.modifyMode = modifyMode;
	}

	/**
	 * @return the userInfo
	 */
	@XmlElement(name="USERINFO")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
