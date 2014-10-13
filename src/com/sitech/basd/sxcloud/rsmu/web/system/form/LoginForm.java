package com.sitech.basd.sxcloud.rsmu.web.system.form;

public class LoginForm {
	private String ID;
	private String ACCOUNT;
	private String PASSWORD;
	private String NAME;
	private String MOBILE;
	private String EMAIL;
	private String STATUS;
	private String CREATETIME;
	private String CREATEUSER;
	
	private String xssEncode(String s) {
		if (s == null || "".equals(s)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('＞');// 全角大于号
				break;
			case '<':
				sb.append('＜');// 全角小于号
				break;
			case '\'':
				sb.append('‘');// 全角单引号
				break;
			case '\"':
				sb.append('“');// 全角双引号
				break;
			case '&':
				sb.append('＆');// 全角
				break;
			case '\\':
				sb.append('＼');// 全角斜线
				break;
			case '#':
				sb.append('＃');// 全角井号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public String getACCOUNT() {
		return xssEncode(ACCOUNT);
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String createtime) {
		CREATETIME = createtime;
	}

	public String getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(String createuser) {
		CREATEUSER = createuser;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mobile) {
		MOBILE = mobile;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

}
