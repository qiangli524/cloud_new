package com.sitech.basd.sxcloud.rsmu.web.softmanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;

/**
 * 
 * <p>
 * Title: DownloaFileAction
 * </p>
 * <p>
 * Description: 文件下载Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-9-18 下午3:58:10
 * 
 */
@Controller("downloaFileAction")
@Scope("prototype")
public class DownloaFileAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(DownloaFileAction.class);
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -4816459480831824466L;
	private String fileName;

	public String getFileName() throws UnsupportedEncodingException {
		// 防止中文乱码
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		fileName = fileName.substring(fileName.lastIndexOf(File.separator));
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		// return
		// ServletActionContext.getServletContext().getResourceAsStream("/" +
		// fileName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException " + fileName + "不存在！", e);
		}
		return fis;
	}

	public String execute() {
		return "success";
	}
}
