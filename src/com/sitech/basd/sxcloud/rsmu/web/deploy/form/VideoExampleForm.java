package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import java.util.List;

@SuppressWarnings("serial")
public class VideoExampleForm {

	/*
	 * 部署实例管理
	 */
	private int ID; // 编号
	private String VIDEONAME; // 录像名称
	private String CREATEUSER; // 创建人
	private String CREATETIME; // 创建时间
	private String REMARK; // 备注
	private String HOSTIP; // 主机IP
	private String HOSTID; // 主机ID
	private String HOSTUSERNAME; // 主机用户名
	private List hostList; // 所有主机集合
	private List resultList; // 结果集
	private List hostConfigList; // 主机配置信息集合
	private String result = "succ"; // 启动部署录像的结果
	private String resultStr; // 命令集的字符串
	private String STARTTIME; // 部署回放开始时间
	private String ENDTIME; // 部署回放结束时间
	private int VIDEOID;

	public int getVIDEOID() {
		return VIDEOID;
	}

	public void setVIDEOID(int videoid) {
		VIDEOID = videoid;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVIDEONAME() {
		return VIDEONAME;
	}

	public void setVIDEONAME(String vIDEONAME) {
		VIDEONAME = vIDEONAME;
	}

	public String getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(String cREATEUSER) {
		CREATEUSER = cREATEUSER;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getHOSTIP() {
		return HOSTIP;
	}

	public void setHOSTIP(String hOSTIP) {
		HOSTIP = hOSTIP;
	}

	public String getHOSTUSERNAME() {
		return HOSTUSERNAME;
	}

	public void setHOSTUSERNAME(String hOSTUSERNAME) {
		HOSTUSERNAME = hOSTUSERNAME;
	}

	public List getHostList() {
		return hostList;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getHostConfigList() {
		return hostConfigList;
	}

	public void setHostConfigList(List hostConfigList) {
		this.hostConfigList = hostConfigList;
	}

	public String getHOSTID() {
		return HOSTID;
	}

	public void setHOSTID(String hOSTID) {
		HOSTID = hOSTID;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getSTARTTIME() {
		return STARTTIME;
	}

	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}

	public String getENDTIME() {
		return ENDTIME;
	}

	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}

	/*
	 * 清空ActionForm
	 */

}
