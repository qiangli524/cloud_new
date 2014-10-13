package com.sitech.basd.yicloud.web.middleware.form;

import java.io.File;
import java.util.List;

/**
 * 
 * Title: TbYicloudBusinessAppObj Description: 中间件 Copyright: Copyright (c)2012
 * Company: SI-TECH
 * 
 * @author duangh
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MiddlewareForm {
	private int ID; // 编号
	private String NAME; // 中间件名称
	private String VERSION; // 中间件版本
	private String REMARK; // 备注
	private String UPDATETIME; // 上传时间
	private File file; // 附件
	private String fileFileName;//附件名
	private String PARTH; // 保存中间件的路径
	private List resultList;

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

	public String getPARTH() {
		return PARTH;
	}

	public void setPARTH(String parth) {
		PARTH = parth;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}
