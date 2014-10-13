package com.sitech.basd.yicloud.web.opersystem.form;

import java.io.File;
import java.util.List;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 操作系统 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author duangh
 * @version 1.0
 */
@SuppressWarnings("serial")
public class OperSystemForm {
	private int ID; // 编号
	private String NAME; // 操作系统名称
	private String VERSION; // 操作系统版本
	private String REMARK; // 操作系统
	private String UPDATETIME; // 上传时间
	private String TYPE; // 类别；0：物理主机；1：虚拟机
	private File file; // 附件
	private String fileFileName;//附件名
	private String PARTH;// 操作系统保存路径
	private String OPERTYPE; // 操作系统类型,0是Unix,1是Linux,2是hplinux,3是aix，4是windows
	private List resultList;
	private String queryName;
	private String QUERYTYPE;
	private String QUERYOPERTYPE;
	
	public String getQUERYTYPE() {
		return QUERYTYPE;
	}

	public void setQUERYTYPE(String qUERYTYPE) {
		QUERYTYPE = qUERYTYPE;
	}

	public String getQUERYOPERTYPE() {
		return QUERYOPERTYPE;
	}

	public void setQUERYOPERTYPE(String qUERYOPERTYPE) {
		QUERYOPERTYPE = qUERYOPERTYPE;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String version) {
		VERSION = version;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
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

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String updatetime) {
		UPDATETIME = updatetime;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getPARTH() {
		return PARTH;
	}

	public void setPARTH(String parth) {
		PARTH = parth;
	}

	public String getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(String opertype) {
		OPERTYPE = opertype;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
