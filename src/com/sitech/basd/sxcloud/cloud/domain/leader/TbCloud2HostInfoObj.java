package com.sitech.basd.sxcloud.cloud.domain.leader;

/**
 * 机柜信息
 * 
 * @author zhangwj
 * @date 2011.12.1
 */
public class TbCloud2HostInfoObj {

	private String eq_id = null; // 服务器编号

	private String eq_name = null; // 服务器名称
	private String eq_type = null; // 服务器类型
	private String eq_ip = null; // 服务器IP地址
	private String eq_hostname = null; // 服务器主机名称
	private String eq_temperature = null;// 服务器温度

	private String cq_id = null; // 服务器所在机柜编号

	private String position = null; // 主机所在机柜的位置

	private String c_addr = null; // 主机所在的机柜机柜位置

	private String sn = null; // 主机sn 只有POWER才有

	private int status;
	private String pool_id;
	private String pool_name;
	private String pool_desc;
	private String pool_icon;
	private int temperature;

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getPool_name() {
		return pool_name;
	}

	public void setPool_name(String pool_name) {
		this.pool_name = pool_name;
	}

	public String getPool_desc() {
		return pool_desc;
	}

	public void setPool_desc(String pool_desc) {
		this.pool_desc = pool_desc;
	}

	public String getPool_icon() {
		return pool_icon;
	}

	public void setPool_icon(String pool_icon) {
		this.pool_icon = pool_icon;
	}

	public String getPool_id() {
		return pool_id;
	}

	public void setPool_id(String pool_id) {
		this.pool_id = pool_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getC_addr() {
		return c_addr;
	}

	public void setC_addr(String c_addr) {
		this.c_addr = c_addr;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCq_id() {
		return cq_id;
	}

	public void setCq_id(String cq_id) {
		this.cq_id = cq_id;
	}

	public String getEq_hostname() {
		return eq_hostname;
	}

	public void setEq_hostname(String eq_hostname) {
		this.eq_hostname = eq_hostname;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eq_ip) {
		this.eq_ip = eq_ip;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eq_name) {
		this.eq_name = eq_name;
	}

	public String getEq_temperature() {
		return eq_temperature;
	}

	public void setEq_temperature(String eq_temperature) {
		this.eq_temperature = eq_temperature;
	}

	public String getEq_type() {
		return eq_type;
	}

	public void setEq_type(String eq_type) {
		this.eq_type = eq_type;
	}

}
