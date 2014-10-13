package com.sitech.basd.bol.domain.program;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BolProgramCompositeVO extends BaseObj{
	private int id;//程序组成标识
	private String programId;//程序标识
	private String libId;//能力库标识
	private int status = -1;//程序组成状态 0 异常 1 正常
	private String lastupdate;//状态日期  20140303112300
	private String programName;//应用程序名称
    private String libName;//能力库名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getLibId() {
		return libId;
	}
	public void setLibId(String libId) {
		this.libId = libId;
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
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	
}
