package com.sitech.basd.bol.domain.boltask;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: BolTaskObj</p>
 * <p>Description: 资源任务实体类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-7 下午5:21:12
 *
 */
public class BolTaskObj extends BaseObj{
	private Integer ID;//任务编号
	private String HOST;//主机
	private String TIME;//时间戳
	private String STATUS;//任务状态 0 提交   1 操作失败  2 操作成功  3 失效'
	private String RESOURCE_ID;//资源标识（bol_resource表的ID）
	private String RESOURCE_NAME;//资源名称
	private String RESOURCE_TYPE;//资源类型
	private String OP;//操作 0开启 1停止 2暂停 3激活  99 监听'
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getRESOURCE_ID() {
		return RESOURCE_ID;
	}
	public void setRESOURCE_ID(String rESOURCE_ID) {
		RESOURCE_ID = rESOURCE_ID;
	}
	public String getRESOURCE_NAME() {
		return RESOURCE_NAME;
	}
	public void setRESOURCE_NAME(String rESOURCE_NAME) {
		RESOURCE_NAME = rESOURCE_NAME;
	}
	public String getRESOURCE_TYPE() {
		return RESOURCE_TYPE;
	}
	public void setRESOURCE_TYPE(String rESOURCE_TYPE) {
		RESOURCE_TYPE = rESOURCE_TYPE;
	}
	public String getOP() {
		return OP;
	}
	public void setOP(String oP) {
		OP = oP;
	}
	
}
