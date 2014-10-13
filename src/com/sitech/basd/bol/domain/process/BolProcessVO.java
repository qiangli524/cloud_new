package com.sitech.basd.bol.domain.process;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BolProcessVO extends BaseObj{
	
	private int id;//进程标识
	private String name;//进程名
	private String descrip;//进程描述
	private int nodeId;//节点标识
	private int programId;//程序标识
	private int status = -1;//进程状态 0 异常 1 正常
	private String lastupdate;//状态日期  20140303112300
	private String nodeName;//节点名
	private String programName;//程序名
	
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
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
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
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
