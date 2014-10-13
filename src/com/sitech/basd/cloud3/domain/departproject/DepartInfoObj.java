package com.sitech.basd.cloud3.domain.departproject;

/**
 * <p>Title: DepartObj</p>
 * <p>Description: 部门预算实体类  对应表tb_depart_info</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-11-11 下午6:08:57
 *
 */
public class DepartInfoObj {

	private String id;//主键
	private String name;//部门名称
	private String leader;//部门负责人
	private Integer cpu;//预分配cpu个数
	private Double mem;//预分配内存大小
	private Double store;//预分配存储大小
	private Integer ip_num;//预分配ip地址个数
	private String type;//部门类型 1部门 2科室
	private String parent_id;//上级部门id
	
	private Integer cpuUsed;//已分配cpu
	private Double memUsed;//已分配内存
	private Double storeUsed;//已分配存储
	private Integer ipUsedNum;//已分配ip数量
	
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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public Integer getCpu() {
		return cpu;
	}
	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}
	public Integer getIp_num() {
		return ip_num;
	}
	public void setIp_num(Integer ip_num) {
		this.ip_num = ip_num;
	}
	public Double getMem() {
		return mem;
	}
	public void setMem(Double mem) {
		this.mem = mem;
	}
	public Double getStore() {
		return store;
	}
	public void setStore(Double store) {
		this.store = store;
	}
	public Integer getCpuUsed() {
		return cpuUsed;
	}
	public void setCpuUsed(Integer cpuUsed) {
		this.cpuUsed = cpuUsed;
	}
	public Double getMemUsed() {
		return memUsed;
	}
	public void setMemUsed(Double memUsed) {
		this.memUsed = memUsed;
	}
	public Double getStoreUsed() {
		return storeUsed;
	}
	public void setStoreUsed(Double storeUsed) {
		this.storeUsed = storeUsed;
	}
	public Integer getIpUsedNum() {
		return ipUsedNum;
	}
	public void setIpUsedNum(Integer ipUsedNum) {
		this.ipUsedNum = ipUsedNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
}
