package com.sitech.basd.sxcloud.rsmu.web.system.form;

import java.sql.Timestamp;
import java.util.List;

public class UsergroupForm {
	private int ID = 0;
	private String GROUPNAME;
	private int STATUS = -1;
	private String REMARK;
	private String CREATETIME;
	private int CREATEUSER;
	private String NAME;
	private int LEADER = -1;
	private List resultList = null;



	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public int getCREATEUSER() {
		return CREATEUSER;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String name) {
		NAME = name;
	}

	public void setCREATEUSER(int createuser) {
		CREATEUSER = createuser;
	}

	public String getGROUPNAME() {
		return GROUPNAME;
	}

	public void setGROUPNAME(String groupname) {
		GROUPNAME = groupname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
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
	// this.GROUPNAME = null ;
	// this.STATUS = -1 ;
	// this.REMARK = null ;
	// this.CREATETIME = null;
	// this.CREATEUSER = 0;
	// this.resultList = null ;
	// this.NAME = null ;
	// }

	public int getLEADER() {
		return LEADER;
	}

	public void setLEADER(int leader) {
		LEADER = leader;
	}

}
