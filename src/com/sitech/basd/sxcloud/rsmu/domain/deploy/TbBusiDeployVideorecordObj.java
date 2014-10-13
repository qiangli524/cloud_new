package com.sitech.basd.sxcloud.rsmu.domain.deploy;

import java.io.Serializable;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
@SuppressWarnings("serial")
public class TbBusiDeployVideorecordObj extends BaseObj implements Serializable,Cloneable{

	/*
	 * 部署回放
	 */
	private int ID;                  // '编号',
	private int VIDEOID;             // '录像编号',
	private String CONTENT;          // '记录',
	private int FLAG;                // '1:输入命令 2：输出结果',
	private String INSERTTIME;    // '执行时间',
	private int FREQ_SEQUENCES ;
	private int EXECUTE_FLAG ;
	private String EXECUTE_USER ;
	private String STARTTIME;			//回放开始时间
	private String    ENDTIME;		//回放结束时间
	
	public int getEXECUTE_FLAG() {
		return EXECUTE_FLAG;
	}
	public void setEXECUTE_FLAG(int execute_flag) {
		EXECUTE_FLAG = execute_flag;
	}
	public String getEXECUTE_USER() {
		return EXECUTE_USER;
	}
	public void setEXECUTE_USER(String execute_user) {
		EXECUTE_USER = execute_user;
	}
	public int getFREQ_SEQUENCES() {
		return FREQ_SEQUENCES;
	}
	public void setFREQ_SEQUENCES(int freq_sequences) {
		FREQ_SEQUENCES = freq_sequences;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String content) {
		CONTENT = content;
	}
	public int getFLAG() {
		return FLAG;
	}
	public void setFLAG(int flag) {
		FLAG = flag;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}

	public String getINSERTTIME() {
		return INSERTTIME;
	}
	public void setINSERTTIME(String inserttime) {
		INSERTTIME = inserttime;
	}
	public int getVIDEOID() {
		return VIDEOID;
	}
	public void setVIDEOID(int videoid) {
		VIDEOID = videoid;
	}
	public String getSTARTTIME() {
		return STARTTIME;
	}
	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}
	public String getENDTIME() {
		return ENDTIME;
	}
	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}
	
	 
	 
}
