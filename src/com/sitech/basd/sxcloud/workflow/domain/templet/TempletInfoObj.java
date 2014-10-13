package com.sitech.basd.sxcloud.workflow.domain.templet;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class TempletInfoObj extends BaseObj implements Serializable, Cloneable {
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
	private String RELEASE_FLAG; // 用户模板发布状态
	private String OPERTYPE; // 模板操作类别
	private String OPERTIME; // 操作模板时间
	private String MESSAGE; // 模板操作信息说明
	private String RESULT; // 模板操作是否成功
	private String NEED_NUMBERS;

	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}

	public void setNEED_NUMBERS(String nEEDNUMBERS) {
		NEED_NUMBERS = nEEDNUMBERS;
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
				+ "," + this.KV9 + "," + this.KV10 + "," + this.KV11 + ",";
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

}
