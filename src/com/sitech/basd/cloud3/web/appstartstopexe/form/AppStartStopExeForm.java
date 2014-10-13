package com.sitech.basd.cloud3.web.appstartstopexe.form;

import java.io.File;

public class AppStartStopExeForm {

	private String username;   //用户名
	private String password;   //密码
	private String exepath;    //执行路径(全路径)
	private String exetype;    //执行类型(1：脚本;2：命令)
	private String ip;         //主机ip
	private String returnMsg;   //执行结果反馈
	
	private File upload_file;
	private String choose_type;
	
	public String getChoose_type() {
		return choose_type;
	}

	public File getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(File upload_file) {
		this.upload_file = upload_file;
	}

	public void setChoose_type(String choose_type) {
		this.choose_type = choose_type;
	}

	public String getUsername() {
		return username;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExepath() {
		return exepath;
	}

	public void setExepath(String exepath) {
		this.exepath = exepath;
	}

	public String getExetype() {
		return exetype;
	}

	public void setExetype(String exetype) {
		this.exetype = exetype;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
