package com.sitech.basd.sxcloud.rsmu.web.system.form;

import java.util.List;

import domain.TbDirectoryCodeVO;

public class UserInfoForm {
	private int ID = 0;
	private int IDS[];
	private String ACCOUNT = null;
	private String PASSWORD = null;
	private String NAME = null;
	private String MOBILE = null;
	private String EMAIL = null;
	private int STATUS = 0;
	private String CREATETIME = null;
	private int CREATEUSER = 0;
	private String flag = null; // 增改标识
	private List resultList = null;
	private String DATAAUTHORITY;// 在安徽移动暂时用于存储用户所属部门（为了避免影响系统功能）
	// 用户所属域
	private String domain;
	// 域列表，配置数据
	private List<TbDirectoryCodeVO> domainList;

	public List<TbDirectoryCodeVO> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<TbDirectoryCodeVO> domainList) {
		this.domainList = domainList;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDATAAUTHORITY() {
		return DATAAUTHORITY;
	}

	public void setDATAAUTHORITY(String dATAAUTHORITY) {
		DATAAUTHORITY = dATAAUTHORITY;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getACCOUNT() {
		return ACCOUNT;
	}

	public void setACCOUNT(String account) {
		ACCOUNT = account;
	}

	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String createtime) {
		CREATETIME = createtime;
	}

	public int getCREATEUSER() {
		return CREATEUSER;
	}

	public void setCREATEUSER(int createuser) {
		CREATEUSER = createuser;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String email) {
		EMAIL = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mobile) {
		MOBILE = mobile;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String password) {
		PASSWORD = password;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int status) {
		STATUS = status;
	}

	/*
	 * 清空ActionForm
	 */
	// public void reset(ActionMapping mapping,HttpServletRequest request)
	// {
	// super.reset(mapping, request);
	// this.ID = 0 ;
	// this.ACCOUNT = null ;
	// this.PASSWORD = null;
	// this.NAME = null ;
	// this.MOBILE = null ;
	// this.EMAIL = null ;
	// this.STATUS = 0 ;
	// this.CREATETIME = null;
	// this.CREATEUSER = 0;
	// this.resultList = null ;
	// }
	public int[] getIDS() {
		return IDS;
	}

	public void setIDS(int[] ids) {
		IDS = ids;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
