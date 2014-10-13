package com.sitech.basd.sxcloud.rsmu.domain.softmanage;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiSoftwareInfoObj extends BaseObj implements Serializable, Cloneable {
	private Integer ID;// 软件编号
	private String SOFTWARE_SIZE;// 软件大小
	private String MANUFACTURERS; // 软件制作厂家
	private String SYSTEM_REQUEST; // 软件平台要求
	private String PROVIDERS; // 软件提供者
	private String INTRODUCE; // 软件功能介绍
	private String REMARK; // 备注
	private String NAME; // 软件名称
	private String VERSION; // 软件版本
	private Integer APPID; // 应用编号
	private String UPDATETIME; // 更新时间
	private String SOFTPARTH; // 软件上传路径

	private String APPNAME; // 应用名称
	private String SYSID; // 所属业务系统ID
	private String APPIDS;

	// lipp
	private List<Integer> appidList;// 所属应用的应用id集合
	private File ATTACHMENT; // 附件文件名
	private String ATTACHMENTFileName; // 附件名
	private String ATTACHMENTContentType;
	private String TARNAME;
	private String CATCHFILES;
	// 基准应用打包后生成md5,用于跟传输到云平台后tar生成md5比对
	private String base_tar_md5;
	// 传输到云管理平台后生成md5,用于与基准应用tar比较
	private String target_tar_md5;
	// base_tar_md5 target_tar_md5 比对值，用户判断tarmd5 是否一致
	private String comparevalue;

	public String getComparevalue() {
		return comparevalue;
	}

	public void setComparevalue(String comparevalue) {
		this.comparevalue = comparevalue;
	}

	public String getBase_tar_md5() {
		return base_tar_md5;
	}

	public void setBase_tar_md5(String base_tar_md5) {
		this.base_tar_md5 = base_tar_md5;
	}

	public String getTarget_tar_md5() {
		return target_tar_md5;
	}

	public void setTarget_tar_md5(String target_tar_md5) {
		this.target_tar_md5 = target_tar_md5;
	}

	public String getCATCHFILES() {
		return CATCHFILES;
	}

	public void setCATCHFILES(String catchfiles) {
		CATCHFILES = catchfiles;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getSOFTWARE_SIZE() {
		return SOFTWARE_SIZE;
	}

	public void setSOFTWARE_SIZE(String sOFTWARE_SIZE) {
		SOFTWARE_SIZE = sOFTWARE_SIZE;
	}

	public String getMANUFACTURERS() {
		return MANUFACTURERS;
	}

	public void setMANUFACTURERS(String mANUFACTURERS) {
		MANUFACTURERS = mANUFACTURERS;
	}

	public String getSYSTEM_REQUEST() {
		return SYSTEM_REQUEST;
	}

	public void setSYSTEM_REQUEST(String sYSTEM_REQUEST) {
		SYSTEM_REQUEST = sYSTEM_REQUEST;
	}

	public String getPROVIDERS() {
		return PROVIDERS;
	}

	public void setPROVIDERS(String pROVIDERS) {
		PROVIDERS = pROVIDERS;
	}

	public String getINTRODUCE() {
		return INTRODUCE;
	}

	public void setINTRODUCE(String iNTRODUCE) {
		INTRODUCE = iNTRODUCE;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public Integer getAPPID() {
		return APPID;
	}

	public void setAPPID(Integer aPPID) {
		APPID = aPPID;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getSOFTPARTH() {
		return SOFTPARTH;
	}

	public void setSOFTPARTH(String sOFTPARTH) {
		SOFTPARTH = sOFTPARTH;
	}

	public String getAPPNAME() {
		return APPNAME;
	}

	public void setAPPNAME(String aPPNAME) {
		APPNAME = aPPNAME;
	}

	public String getSYSID() {
		return SYSID;
	}

	public void setSYSID(String sYSID) {
		SYSID = sYSID;
	}

	public String getAPPIDS() {
		return APPIDS;
	}

	public void setAPPIDS(String aPPIDS) {
		APPIDS = aPPIDS;
	}

	public List<Integer> getAppidList() {
		return appidList;
	}

	public void setAppidList(List<Integer> appidList) {
		this.appidList = appidList;
	}

	public File getATTACHMENT() {
		return ATTACHMENT;
	}

	public void setATTACHMENT(File aTTACHMENT) {
		ATTACHMENT = aTTACHMENT;
	}

	public String getATTACHMENTFileName() {
		return ATTACHMENTFileName;
	}

	public void setATTACHMENTFileName(String aTTACHMENTFileName) {
		ATTACHMENTFileName = aTTACHMENTFileName;
	}

	public String getATTACHMENTContentType() {
		return ATTACHMENTContentType;
	}

	public void setATTACHMENTContentType(String aTTACHMENTContentType) {
		ATTACHMENTContentType = aTTACHMENTContentType;
	}

	public String getTARNAME() {
		return TARNAME;
	}

	public void setTARNAME(String tARNAME) {
		TARNAME = tARNAME;
	}

}
