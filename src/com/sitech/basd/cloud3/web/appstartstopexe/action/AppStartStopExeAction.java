package com.sitech.basd.cloud3.web.appstartstopexe.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.web.appstartstopexe.form.AppStartStopExeForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.data.WriterExcel;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@SuppressWarnings("serial")
@Controller("appStartStopExeAction")
public class AppStartStopExeAction extends BaseAction implements
		ServletContextAware {

	private AppStartStopExeForm theForm;

	/** excel file */
	private File file;
	/** struts2 interface */
	private ServletContext servletContext;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 
	 * @Title: 获取页面信息，执行操作
	 * @Copyright: Copyright (c) 2013-10-23
	 * @Company: si-tech
	 * @author chenjlc
	 * @version 1.0
	 */
	public String show() {
		return "show";
	}

	/**
	 * 
	 * @Title: 获取页面信息，执行操作
	 * @Copyright: Copyright (c) 2013-10-23
	 * @Company: si-tech
	 * @author chenjlc
	 * @version 1.0
	 */
	public String exe() {
		if (theForm == null) {
			theForm = new AppStartStopExeForm();
		}
		String retureMsg = "";
		Boolean result = true;
		String choose_type = theForm.getChoose_type();
		String iplist = "";
		String username = "";
		String pwd = "";
		String exetype = "";
		String exepath = "";
		if ("1".equals(choose_type)) {
			iplist = theForm.getIp();
			username = theForm.getUsername();
			pwd = theForm.getPassword();
			exetype = theForm.getExetype();
			exepath = theForm.getExepath();
			String[] ips = iplist.split(",");
			for (int i = 0; i < ips.length; i++) {
				result = startStopExe(ips[i], username, pwd, exepath, exetype);
				if (!result) {
					if (retureMsg == "") {
						retureMsg = retureMsg + ips[i];
					} else {
						retureMsg = retureMsg + "," + ips[i];
					}
				}
			}
		} else {
			Workbook rwb = null;
			Cell cell = null;
			// 获取Excel文件对象
			try {
				InputStream is = new FileInputStream(file);
				rwb = Workbook.getWorkbook(is);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取文件的指定工作表 默认的第一个
			Sheet sheet = rwb.getSheet(0);
			// 行数(表头的目录不需要，从1开始)
			for (int i = 1; i < sheet.getRows(); i++) {
				iplist = sheet.getCell(0, i).getContents();
				username = sheet.getCell(1, i).getContents();
				pwd = sheet.getCell(2, i).getContents();
				exepath = sheet.getCell(3, i).getContents();
				exetype = sheet.getCell(4, i).getContents();
				result = startStopExe(iplist, username, pwd, exepath, exetype);
				if (!result) {
					if (retureMsg == "") {
						retureMsg = retureMsg + iplist;
					} else {
						retureMsg = retureMsg + "," + iplist;
					}
				}
			}
		}
		if (retureMsg == "") {
			retureMsg = retureMsg + "执行成功！";
		} else {
			retureMsg = retureMsg + "主机执行失败！";
		}
		theForm.setUsername("");
		theForm.setPassword("");
		theForm.setExepath("");
		theForm.setIp("");
		theForm.setExetype("");
		theForm.setUpload_file(null);
		theForm.setReturnMsg(retureMsg);
		return "success";

	}

	public AppStartStopExeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppStartStopExeForm theForm) {
		this.theForm = theForm;
	}

	public Boolean startStopExe(String ip, String username, String pwd,
			String exePath, String exetype) {

		SshConnection conn = null;
		try {
			conn = SSHUtil.getSshConnection(ip, 22, username, pwd);
			if (conn == null) {
				return false;
			} else {
				if ("1".equals(exetype)) {
					exePath = "sh  " + exePath + " ";
				} 
//				else {
//					exePath = " " + exePath + " &";
//				}
				SSHUtil.sendShellToSSHChannel(conn, true, exePath);
				conn.close();
				return true;
			}
		} catch (Exception e) {
			conn = null;
			return false;
		}
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub

	}
}
