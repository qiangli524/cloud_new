package com.sitech.basd.bol.domain.nodestatus;

public class BolNodeStatusObj {
	private String nodeCode;
	private String nodeField;			
	private float nodeValue;			
	private String time;
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getNodeField() {
		return nodeField;
	}
	public void setNodeField(String nodeField) {
		this.nodeField = nodeField;
	}
	public float getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(float nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}			

}
