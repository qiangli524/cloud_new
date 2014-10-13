package domain.login4A;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>Title: Request4ABody</p>
 * <p>Description: 4Abody</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-17 上午11:16:21
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Request4ABody {
	
	/*
	 * 成功标识0成功 非0失败
	 */
	private String rsp;
	
	/*
	 * 当前登录主帐号。应用侧获取当前主帐号，应用记录审计日志时需要把当前主帐号发送到4A
	 */
	private String mainAccid;
	
	
	/*
	 * 当前从帐号登录名，当RSP为非0时，不返回此标签。与请求参数中的APPACCTID意义不同
	 */
	private String appAcctid;

	/**
	 * @return the rsp
	 */
	@XmlElement(name="RSP")
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
	 * @return the mainAccid
	 */
	@XmlElement(name="MAINACCID")
	public String getMainAccid() {
		return mainAccid;
	}

	/**
	 * @param mainAccid the mainAccid to set
	 */
	public void setMainAccid(String mainAccid) {
		this.mainAccid = mainAccid;
	}

	/**
	 * @return the appAcctid
	 */
	@XmlElement(name="APPACCTID")
	public String getAppAcctid() {
		return appAcctid;
	}

	/**
	 * @param appAccid the appAcctid to set
	 */
	public void setAppAcctid(String appAcctid) {
		this.appAcctid = appAcctid;
	}
	
}
