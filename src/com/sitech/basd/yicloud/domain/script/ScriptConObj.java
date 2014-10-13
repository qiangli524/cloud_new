package com.sitech.basd.yicloud.domain.script;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class ScriptConObj extends BaseObj implements Serializable, Cloneable {
	private int id;
	private String scriptId;
	private String templetId;
	private String type;
	//删除时 作为参数
	private String id1;
	private String id2;
	private String id3;
	private String id4;
	private String id5;
	private String id6;
	private String id7;
	private String id8;
	private String id9;
	private String id10;
	
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public String getId3() {
		return id3;
	}
	public void setId3(String id3) {
		this.id3 = id3;
	}
	public String getId4() {
		return id4;
	}
	public void setId4(String id4) {
		this.id4 = id4;
	}
	public String getId5() {
		return id5;
	}
	public void setId5(String id5) {
		this.id5 = id5;
	}
	public String getId6() {
		return id6;
	}
	public void setId6(String id6) {
		this.id6 = id6;
	}
	public String getId7() {
		return id7;
	}
	public void setId7(String id7) {
		this.id7 = id7;
	}
	public String getId8() {
		return id8;
	}
	public void setId8(String id8) {
		this.id8 = id8;
	}
	public String getId9() {
		return id9;
	}
	public void setId9(String id9) {
		this.id9 = id9;
	}
	public String getId10() {
		return id10;
	}
	public void setId10(String id10) {
		this.id10 = id10;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScriptId() {
		return scriptId;
	}
	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}
	public String getTempletId() {
		return templetId;
	}
	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
