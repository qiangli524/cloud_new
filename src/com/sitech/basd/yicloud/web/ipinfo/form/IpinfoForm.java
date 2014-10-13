package com.sitech.basd.yicloud.web.ipinfo.form;

import java.util.List;


public class IpinfoForm  {

	private String IP_ID; // 编号
	private String ISUSED; // 是否使用ID
	private String ISBLOCKED; // 是否被阻塞ID，0否，1是
	private String IPADDRESS; // IP地址
	private String INS_DATE; // 时间
	private String NET_ID;
	private String ISUSEDNAME; // 是否使用名称
	private String ISBLOCKEDNAME; // 是否被阻塞名称，0否，1是
	private String IP_TYPE; // IP类型
	private String IP_TYPENAME; //IP类型名称

	private List resultList = null; // 结果列表
	private List usedList = null; // 是否使用下拉列表显示
	private List blockList = null; // 是否被堵塞列表作为下拉列表显示
	private List typeList = null; // IP类型列表作为下拉列表显示
	
	private int flag;// 判断是插入还是更新

	private static final long serialVersionUID = 5103976394939900606L;

	public String getIP_ID() {
		return IP_ID;
	}

	public void setIP_ID(String ip_id) {
		IP_ID = ip_id;
	}

	public String getISUSED() {
		return ISUSED;
	}

	public void setISUSED(String isused) {
		ISUSED = isused;
	}

	public String getISBLOCKED() {
		return ISBLOCKED;
	}

	public void setISBLOCKED(String isblocked) {
		ISBLOCKED = isblocked;
	}

	public String getIPADDRESS() {
		return IPADDRESS;
	}

	public void setIPADDRESS(String ipaddress) {
		IPADDRESS = ipaddress;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getNET_ID() {
		return NET_ID;
	}

	public void setNET_ID(String net_id) {
		NET_ID = net_id;
	}

	public String getISUSEDNAME() {
		return ISUSEDNAME;
	}

	public void setISUSEDNAME(String isusedname) {
		ISUSEDNAME = isusedname;
	}

	public String getISBLOCKEDNAME() {
		return ISBLOCKEDNAME;
	}

	public void setISBLOCKEDNAME(String isblockedname) {
		ISBLOCKEDNAME = isblockedname;
	}

	public String getIP_TYPE() {
		return IP_TYPE;
	}

	public void setIP_TYPE(String ip_type) {
		IP_TYPE = ip_type;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public List getUsedList() {
		return usedList;
	}

	public void setUsedList(List usedList) {
		this.usedList = usedList;
	}

	public List getBlockList() {
		return blockList;
	}

	public void setBlockList(List blockList) {
		this.blockList = blockList;
	}

	public List getTypeList() {
		return typeList;
	}

	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getIP_TYPENAME() {
		return IP_TYPENAME;
	}

	public void setIP_TYPENAME(String ip_typename) {
		IP_TYPENAME = ip_typename;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
