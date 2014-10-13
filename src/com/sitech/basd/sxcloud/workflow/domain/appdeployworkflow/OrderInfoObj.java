package com.sitech.basd.sxcloud.workflow.domain.appdeployworkflow;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

@SuppressWarnings("serial")
public class OrderInfoObj extends BaseObj implements Serializable, Cloneable {
	private int ID;		//		序号，自增
	private String NEED_NUMBERS;//需求编号
	private String TYPE; //模板的类型
	private String TEM_ID;
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
	private int kvnum;		//用来存放存了几个KV值
	
	public int getKvnum() {
		return kvnum;
	}
	public void setKvnum(int kvnum) {
		this.kvnum = kvnum;
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
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNEED_NUMBERS() {
		return NEED_NUMBERS;
	}
	public void setNEED_NUMBERS(String need_numbers) {
		NEED_NUMBERS = need_numbers;
	}
	
	public String toString(){
		String ret="";
		//将数据库中的字符串连接后返回一个字符串
		ret=this.KV1 + "," + this.KV2 + "," + this.KV3+","+this.KV4+","+this.KV5+","+this.KV6+","+this.KV7+","+this.KV8+","+this.KV9+","+this.KV10+","+this.KV11+",";
		return ret;
	}
	public String getTEM_ID() {
		return TEM_ID;
	}
	public void setTEM_ID(String tem_id) {
		TEM_ID = tem_id;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	
}
