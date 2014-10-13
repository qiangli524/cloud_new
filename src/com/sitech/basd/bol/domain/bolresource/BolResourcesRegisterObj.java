package com.sitech.basd.bol.domain.bolresource;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class BolResourcesRegisterObj extends BaseObj{
	private String id;
	private String name;
	private String host;
	private Integer status;
	private float M;//内存(M)
	private float C;//CPU(C) 
	private float S;//存储(S) 
	private float DH;//文件句柄(DH) 
	private float PH;//进程文件(PH) 
	private float Q;//消息队列(Q) 
	private float SN;//信号量(SN) 
	private float N;//网络连接(N) 
	private float P;//进程(P) 
	private float DB;//数据库连接(DB)  
	private float CH;//目录文件(CH)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public float getM() {
		return M;
	}
	public void setM(float m) {
		M = m;
	}
	public float getC() {
		return C;
	}
	public void setC(float c) {
		C = c;
	}
	public float getS() {
		return S;
	}
	public void setS(float s) {
		S = s;
	}
	public float getDH() {
		return DH;
	}
	public void setDH(float dH) {
		DH = dH;
	}
	public float getPH() {
		return PH;
	}
	public void setPH(float pH) {
		PH = pH;
	}
	public float getQ() {
		return Q;
	}
	public void setQ(float q) {
		Q = q;
	}
	public float getSN() {
		return SN;
	}
	public void setSN(float sN) {
		SN = sN;
	}
	public float getN() {
		return N;
	}
	public void setN(float n) {
		N = n;
	}
	public float getP() {
		return P;
	}
	public void setP(float p) {
		P = p;
	}
	public float getDB() {
		return DB;
	}
	public void setDB(float dB) {
		DB = dB;
	}
	public float getCH() {
		return CH;
	}
	public void setCH(float cH) {
		CH = cH;
	}
	
}
