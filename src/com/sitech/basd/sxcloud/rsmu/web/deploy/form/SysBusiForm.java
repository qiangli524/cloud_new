package com.sitech.basd.sxcloud.rsmu.web.deploy.form;

import org.apache.struts2.components.File;

public class SysBusiForm  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file = null;// ����
	
	private String action = null;
	
	private String connection = null;

	private String filename = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}




}