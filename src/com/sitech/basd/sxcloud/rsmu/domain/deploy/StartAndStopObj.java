package com.sitech.basd.sxcloud.rsmu.domain.deploy;

/**
 * 
 * <p>Title: SAndSObj</p>
 * <p>Description: 一键启停对象
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author hehui
 * @version 1.0
 * @createtime 2013-10-26 下午5:36:14
 *
 */
public class StartAndStopObj {
	private String aloneDeployeIp;
	private String selectUsee;
	private String commonUseId;
	private String commonPassword;
	private String selectScript;
	private String commonScriptType;
	private String commonScriptPath;
	private String aloneUserId;
	private String alonePassword;
	private String aloneScriptType;
	private String aloneScriptPath;

	public String getSelectUsee() {
		return selectUsee;
	}

	public void setSelectUsee(String selectUsee) {
		this.selectUsee = selectUsee;
	}

	public String getCommonUseId() {
		return commonUseId;
	}

	public void setCommonUseId(String commonUseId) {
		this.commonUseId = commonUseId;
	}

	public String getCommonPassword() {
		return commonPassword;
	}

	public void setCommonPassword(String commonPassword) {
		this.commonPassword = commonPassword;
	}

	public String getSelectScript() {
		return selectScript;
	}

	public void setSelectScript(String selectScript) {
		this.selectScript = selectScript;
	}

	public String getCommonScriptType() {
		return commonScriptType;
	}

	public void setCommonScriptType(String commonScriptType) {
		this.commonScriptType = commonScriptType;
	}

	public String getCommonScriptPath() {
		return commonScriptPath;
	}

	public void setCommonScriptPath(String commonScriptPath) {
		this.commonScriptPath = commonScriptPath;
	}

	public String getAloneUserId() {
		return aloneUserId;
	}

	public void setAloneUserId(String aloneUserId) {
		this.aloneUserId = aloneUserId;
	}

	public String getAlonePassword() {
		return alonePassword;
	}

	public void setAlonePassword(String alonePassword) {
		this.alonePassword = alonePassword;
	}

	public String getAloneScriptType() {
		return aloneScriptType;
	}

	public void setAloneScriptType(String aloneScriptType) {
		this.aloneScriptType = aloneScriptType;
	}

	public String getAloneScriptPath() {
		return aloneScriptPath;
	}

	public void setAloneScriptPath(String aloneScriptPath) {
		this.aloneScriptPath = aloneScriptPath;
	}

	public String getAloneDeployeIp() {
		return aloneDeployeIp;
	}

	public void setAloneDeployeIp(String aloneDeployeIp) {
		this.aloneDeployeIp = aloneDeployeIp;
	}
}
