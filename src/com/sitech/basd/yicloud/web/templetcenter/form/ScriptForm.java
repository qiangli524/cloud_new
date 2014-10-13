package com.sitech.basd.yicloud.web.templetcenter.form;

import java.util.List;

public class ScriptForm {
	private int id;
	private String name;
	private String content;
	private String des;
	private String flag; //标示修改或增加
	private List resultList; //返回列表
	private String chks;
	
	public String getChks() {
		return chks;
	}
	public void setChks(String chks) {
		this.chks = chks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
}
