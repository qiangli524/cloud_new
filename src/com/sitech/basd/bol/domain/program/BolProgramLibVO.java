package com.sitech.basd.bol.domain.program;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BolProgramLibVO extends BaseObj{
	private int id;//能力库标识
	private String name;//能力库名
	private String descrip;//能力库描述
	private int status = -1;//能力库状态 0 异常 1 正常
	private String lastupdate;//状态日期  20140303112300
	
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
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	
}
