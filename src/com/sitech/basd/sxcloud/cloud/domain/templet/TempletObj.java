package com.sitech.basd.sxcloud.cloud.domain.templet;

import java.io.Serializable;

import com.sitech.basd.sxcloud.cloud.web.templet.form.TempletForm;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;
import com.sitech.basd.yicloud.web.scheduler.form.StrategyForm;

@SuppressWarnings("serial")
public class TempletObj extends BaseObj implements Serializable, Cloneable {
	private String TEM_ID; // 模板ID
	private String ID; // 模板配置表中的ID
	private String TEM_NAME; // 模板名称
	private String TYPE; // 模板类型
	private String KEY; // 模板配置表中的KEY
	private String VALUE; // 模板配置表中的VALUE
	private String TEM_DESC; // 模板描述
	private String CONFIG_NAME; // 配置名称
	private String CONFIG_VALUE; // 配置项
	private String TYPE_NAME; // 模板类型名称
	private String TYPE_DESC; // 模板类型名称描述
	private String KV1;
	private String KV2;
	private String KV3;
	private String KV4;
	private String KV5;
	private String KV6;
	private String KV7;
	private String KV8;
	private String KV9;
	private String KV10;
	private String KV11;
	private String KV12;
	private String KV13;
	private String KV14;
	private String KV15;
	private String KV16;
	private String KV17;
	private String KV18;
	private String KV19;
	private String KV20;
	private String RELEASE_FLAG; // 用户模板发布状态
	private String OPERTYPE; // 模板操作类别
	private String OPERTIME; // 操作模板时间
	private String MESSAGE; // 模板操作信息说明
	private String RESULT; // 模板操作是否成功
	private int KV_NUM; // 用来存放不为空的kv的个数

	public int getKV_NUM() {
		return KV_NUM;
	}

	public void setKV_NUM(int kv_num) {
		KV_NUM = kv_num;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String result) {
		RESULT = result;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String message) {
		MESSAGE = message;
	}

	public String getOPERTYPE() {
		return OPERTYPE;
	}

	public void setOPERTYPE(String opertype) {
		OPERTYPE = opertype;
	}

	public String getOPERTIME() {
		return OPERTIME;
	}

	public void setOPERTIME(String opertime) {
		OPERTIME = opertime;
	}

	public String getKV1() {
		return KV1;
	}

	public void setKV1(String kv1) {
		KV1 = kv1;
	}

	public String getKV2() {
		return KV2;
	}

	public void setKV2(String kv2) {
		KV2 = kv2;
	}

	public String getKV3() {
		return KV3;
	}

	public void setKV3(String kv3) {
		KV3 = kv3;
	}

	public String getKV4() {
		return KV4;
	}

	public void setKV4(String kv4) {
		KV4 = kv4;
	}

	public String getCONFIG_NAME() {
		return CONFIG_NAME;
	}

	public void setCONFIG_NAME(String config_name) {
		CONFIG_NAME = config_name;
	}

	public String getCONFIG_VALUE() {
		return CONFIG_VALUE;
	}

	public void setCONFIG_VALUE(String config_value) {
		CONFIG_VALUE = config_value;
	}

	public String getTEM_DESC() {
		return TEM_DESC;
	}

	public void setTEM_DESC(String tem_desc) {
		TEM_DESC = tem_desc;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getTEM_ID() {
		return TEM_ID;
	}

	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}

	public String getTEM_NAME() {
		return TEM_NAME;
	}

	public void setTEM_NAME(String tem_name) {
		TEM_NAME = tem_name;
	}

	public String getKV5() {
		return KV5;
	}

	public void setKV5(String kv5) {
		KV5 = kv5;
	}

	public String getKV6() {
		return KV6;
	}

	public void setKV6(String kv6) {
		KV6 = kv6;
	}

	public String getKV7() {
		return KV7;
	}

	public void setKV7(String kv7) {
		KV7 = kv7;
	}

	public String getKV8() {
		return KV8;
	}

	public void setKV8(String kv8) {
		KV8 = kv8;
	}

	public String getKV9() {
		return KV9;
	}

	public void setKV9(String kv9) {
		KV9 = kv9;
	}

	public String getKV10() {
		return KV10;
	}

	public void setKV10(String kv10) {
		KV10 = kv10;
	}

	public String getKV11() {
		return KV11;
	}

	public void setKV11(String kv11) {
		KV11 = kv11;
	}

	public String getRELEASE_FLAG() {
		return RELEASE_FLAG;
	}

	public void setRELEASE_FLAG(String release_flag) {
		RELEASE_FLAG = release_flag;
	}

	public String toString() {
		String ret = "";
		// 将数据库中的字符串连接后返回一个字符串
		ret = this.KV1 + "," + this.KV2 + "," + this.KV3 + "," + this.KV4 + ","
				+ this.KV5 + "," + this.KV6 + "," + this.KV7 + "," + this.KV8
				+ "," + this.KV9 + "," + this.KV10 + "," + this.KV11 + ","
				+ this.KV12 + "," + this.KV13 + "," + this.KV14 + ","
				+ this.KV15 + "," + this.KV16 + "," + this.KV17 + ","
				+ this.KV18 + "," + this.KV19 + "," + this.KV20 + ",";
		return ret;
	}

	public String getKEY() {
		return KEY;
	}

	public void setKEY(String key) {
		KEY = key;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String value) {
		VALUE = value;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String getTYPE_DESC() {
		return TYPE_DESC;
	}

	public void setTYPE_DESC(String type_desc) {
		TYPE_DESC = type_desc;
	}

	public String getKV12() {
		return KV12;
	}

	public void setKV12(String kv12) {
		KV12 = kv12;
	}

	public String getKV13() {
		return KV13;
	}

	public void setKV13(String kv13) {
		KV13 = kv13;
	}

	public String getKV14() {
		return KV14;
	}

	public void setKV14(String kv14) {
		KV14 = kv14;
	}

	public String getKV15() {
		return KV15;
	}

	public void setKV15(String kv15) {
		KV15 = kv15;
	}

	public String getKV16() {
		return KV16;
	}

	public void setKV16(String kv16) {
		KV16 = kv16;
	}

	public String getKV17() {
		return KV17;
	}

	public void setKV17(String kv17) {
		KV17 = kv17;
	}

	public String getKV18() {
		return KV18;
	}

	public void setKV18(String kv18) {
		KV18 = kv18;
	}

	public String getKV19() {
		return KV19;
	}

	public void setKV19(String kv19) {
		KV19 = kv19;
	}

	public String getKV20() {
		return KV20;
	}

	public void setKV20(String kv20) {
		KV20 = kv20;
	}
	
	public boolean setKV(StrategyForm strategyForm)
	{
		TempletForm theForm = strategyForm.getTempForm();
		theForm.setTEM_ID(strategyForm.getTemp_id());
		if(null == theForm || "-1".equals(theForm.getTEM_ID()))
		{
			return false;
		}
		this.setTEM_ID(theForm.getTEM_ID());
		this.setKV1(isNA(theForm.getKV1())? null:("VH_CPU:"+theForm.getKV1()));
		this.setKV2(isNA(theForm.getKV2())? null:("VH_MEM:"+theForm.getKV2()));
		this.setKV3(isNA(theForm.getKV3())? null:("VH_STORAGE:"+theForm.getKV3()));
		this.setKV4(isNA(theForm.getKV4())? null:("VH_NUM:"+theForm.getKV4()));
		this.setKV20("BASE_TEMPLATE:t100");
		return true;
	}

	/**
	 * 判断一个对象是否为空串,""及null
	 * @param obj
	 * @return
	 */
	private boolean isNA(Object obj)
	{
		if(null == obj)
		{
			return true;
		}
		if("".equals(obj.toString()))
		{
			return true;
		}
		return false;
	}
}
