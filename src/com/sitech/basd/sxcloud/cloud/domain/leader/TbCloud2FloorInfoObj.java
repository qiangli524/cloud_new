package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 楼层信息
 * @author zhangwj
 * @date 2011.12.1
 */
public class TbCloud2FloorInfoObj {
	
	private String F_ID = null;  //楼层编号
	
	private String F_NAME = null;//楼层名称
	
	private String M_ID = null;  //所属机房编号
	
	public String getF_ID() {
		return F_ID;
	}
	public void setF_ID(String f_id) {
		F_ID = f_id;
	}
	public String getF_NAME() {
		return F_NAME;
	}
	public void setF_NAME(String f_name) {
		F_NAME = f_name;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_id) {
		M_ID = m_id;
	}
	
	
}
