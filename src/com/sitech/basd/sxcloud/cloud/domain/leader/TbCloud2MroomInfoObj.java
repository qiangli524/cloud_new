package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 机房信息 
 * @author zhangwj
 * @date 2011.12.1
 */
public class TbCloud2MroomInfoObj {
	
	private String m_id = null;   //机房编号
	
	private String m_name = null; //机房名称
	private String m_addr = null; //机房地址
	private String m_man = null;  //机房负责人
	private String m_tel = null;  //机房联系人电话

	private String m_email = null;//联系邮箱
	
	private String dist_id = null;//所在地区编号

	public String getDist_id() {
		return dist_id;
	}

	public void setDist_id(String dist_id) {
		this.dist_id = dist_id;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_man() {
		return m_man;
	}

	public void setM_man(String m_man) {
		this.m_man = m_man;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_tel() {
		return m_tel;
	}

	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	

}
