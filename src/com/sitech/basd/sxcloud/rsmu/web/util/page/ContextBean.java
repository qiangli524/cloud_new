// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi
// Source File Name:   ContextBean.java

package com.sitech.basd.sxcloud.rsmu.web.util.page;

import java.io.File;
import java.io.Serializable;

public class ContextBean implements Serializable {

	static final long serialVersionUID = 0xace215e3ba22745eL;
	private String page;
	private String attachPath;
	private String license;
	private String name;
	private String dbSchema;

	public String getAttachPath() {
		return attachPath;
	}

	public ContextBean() {
		page = "10";
		name = "";
		attachPath = File.separator + "Tmp";
		license = "";
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:自定义分页 duangh
	 * </p>
	 * 
	 * @param pageSize
	 */
	public ContextBean(String pageSize) {
		page = pageSize;
		name = "";
		attachPath = File.separator + "Tmp";
		license = "";
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDbSchema() {
		return dbSchema != null ? dbSchema + "." : "";
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
}
