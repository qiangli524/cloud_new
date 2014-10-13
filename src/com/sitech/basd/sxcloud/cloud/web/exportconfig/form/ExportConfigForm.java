package com.sitech.basd.sxcloud.cloud.web.exportconfig.form;

import java.util.List;

@SuppressWarnings("serial")
public class ExportConfigForm {

	private String KEY = null;
	private String VALUE = null;
	private String CFG_DESC = null;
	private int TYPE; // 语句类型
	private String ID = null;
	private String flag = null; // 增、改标识
	private List resultList = null;

	public String getKEY() {
		return KEY;
	}

	public void setKEY(String key) {
		KEY = key;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String value) {
		VALUE = value;
	}

	public String getCFG_DESC() {
		return CFG_DESC;
	}

	public void setCFG_DESC(String cfg_desc) {
		CFG_DESC = cfg_desc;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}

}
