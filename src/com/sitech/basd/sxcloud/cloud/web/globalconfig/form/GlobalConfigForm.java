package com.sitech.basd.sxcloud.cloud.web.globalconfig.form;

import java.util.List;

@SuppressWarnings("serial")
public class GlobalConfigForm {

	private String KEY = null;
	private String VALUE = null;
	private String CFG_DESC = null;

	private String ID = null;
	private List resultList = null;
	private String flag = null;

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

}
