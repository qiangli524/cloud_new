package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class TbBusiDoTaskObj extends BaseObj implements Serializable, Cloneable {

	private int ID;             //编号
	private int EXA_ID;             //升级编号或部署编号     ps:暂时用作appid
	private int EXA_TYPE;           //1：升级2：部署3：卸载4：启动应用5：停止应用
	private int DO_FLAG;            //状态(0：待处理1：处理2：已处理)
	private String updateTime;         //处理时间
	private int EXECUTE_FLAG;       //0:未创建后台任务，1：已创建后台任务，与表TB_BUSI_BUSI_TASK记录有关
	private List resultList;

	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getEXA_ID() {
		return EXA_ID;
	}
	public void setEXA_ID(int exa_id) {
		EXA_ID = exa_id;
	}
	public int getEXA_TYPE() {
		return EXA_TYPE;
	}
	public void setEXA_TYPE(int exa_type) {
		EXA_TYPE = exa_type;
	}
	public int getDO_FLAG() {
		return DO_FLAG;
	}
	public void setDO_FLAG(int do_flag) {
		DO_FLAG = do_flag;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}
	public void setEXECUTE_FLAG(int execute_flag) {
		EXECUTE_FLAG = execute_flag;
	}
	
}
