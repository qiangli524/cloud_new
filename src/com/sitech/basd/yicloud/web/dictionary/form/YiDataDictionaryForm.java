package com.sitech.basd.yicloud.web.dictionary.form;

import java.util.List;


public class YiDataDictionaryForm {
	private int ID;// 序号
	private int PARENT_ID;// 上级节点
	private String CODE;// 编码
	private String NAME;// 名称
	private String GROUP_NAME;// 分组
	private List resultList;// 结果列表
	private int flag;// 判断是插入还是更新

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getPARENT_ID() {
		return PARENT_ID;
	}

	public void setPARENT_ID(int parent_id) {
		PARENT_ID = parent_id;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String code) {
		CODE = code;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String group_name) {
		GROUP_NAME = group_name;
	}

}
