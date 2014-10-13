/**  
 * @Title: SendSmsObj.java
 * @Package com.sitech.ssd.sc.sendSms.domain
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenjl
 * @date 2014-7-29 下午3:19:09
 * @version
 */
package com.sitech.ssd.sc.sendSms.domain;

/**
 * @ClassName: SendSmsObj
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenjl
 * @date 2014-7-29 下午3:19:09
 * 
 */
public class SendSmsObj {

	private String USER_PHONE;
	private String USER_ID;
	private String SEND_CONTENT;
	private String BUSI_TYPE;
	private String INSERT_TIME;
	private String OTHER_DESC;
	private String LOGIN_ID;
	private String DO_FLAG;

	public String getUSER_PHONE() {
		return USER_PHONE;
	}

	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getSEND_CONTENT() {
		return SEND_CONTENT;
	}

	public void setSEND_CONTENT(String sEND_CONTENT) {
		SEND_CONTENT = sEND_CONTENT;
	}

	public String getBUSI_TYPE() {
		return BUSI_TYPE;
	}

	public void setBUSI_TYPE(String bUSI_TYPE) {
		BUSI_TYPE = bUSI_TYPE;
	}

	public String getINSERT_TIME() {
		return INSERT_TIME;
	}

	public void setINSERT_TIME(String iNSERT_TIME) {
		INSERT_TIME = iNSERT_TIME;
	}

	public String getOTHER_DESC() {
		return OTHER_DESC;
	}

	public void setOTHER_DESC(String oTHER_DESC) {
		OTHER_DESC = oTHER_DESC;
	}

	public String getLOGIN_ID() {
		return LOGIN_ID;
	}

	public void setLOGIN_ID(String lOGIN_ID) {
		LOGIN_ID = lOGIN_ID;
	}

	public String getDO_FLAG() {
		return DO_FLAG;
	}

	public void setDO_FLAG(String dO_FLAG) {
		DO_FLAG = dO_FLAG;
	}

}
