package com.sitech.shop.domain.disk;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: VirtualDiskObj
 * </p>
 * <p>
 * Description: 虚拟磁盘实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-4-17 下午4:28:43
 * 
 */
public class VirtualDiskObj extends BaseObj {
	// 表字段
	private String id;
	private String name;// 虚拟磁盘名称
	private String connectCode;// 资源池标识
	private String datacenterCode;// 数据中心标识
	private String datastoreCode;// 数据存储标识
	private Double capacityInMB;// 虚拟机磁盘容量，单位MB
	private String user_id;// 用户id
	private String path;// 虚拟磁盘所在路径
	private String label;// vcenter返回的名称
	private String create_time;// 创建时间
	private String disk_type;// 1.系统盘 2.数据盘
	private String end_time;// 到期时间
	// 关联表属性
	private String vm_uuid;// 虚拟机uuid
	private String vm_name;// 关联的虚拟机名称
	private String state;// 磁盘挂载状态0.是未挂载 1.已挂载
	// 自定义属性
	private Integer vmNum;// 虚拟机数量
	private String oper;// 操作
	private String service_status;// 服务状态
	private List<String> connectCodeList;//

	public String getService_status() {
		return service_status;
	}

	public void setService_status(String service_status) {
		this.service_status = service_status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getDisk_type() {
		return disk_type;
	}

	public void setDisk_type(String disk_type) {
		this.disk_type = disk_type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

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

	public String getConnectCode() {
		return connectCode;
	}

	public void setConnectCode(String connectCode) {
		this.connectCode = connectCode;
	}

	public Double getCapacityInMB() {
		return capacityInMB;
	}

	public void setCapacityInMB(Double capacityInMB) {
		this.capacityInMB = capacityInMB;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getVmNum() {
		return vmNum;
	}

	public void setVmNum(Integer vmNum) {
		this.vmNum = vmNum;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getVm_uuid() {
		return vm_uuid;
	}

	public void setVm_uuid(String vm_uuid) {
		this.vm_uuid = vm_uuid;
	}

	public String getDatacenterCode() {
		return datacenterCode;
	}

	public void setDatacenterCode(String datacenterCode) {
		this.datacenterCode = datacenterCode;
	}

	public String getDatastoreCode() {
		return datastoreCode;
	}

	public void setDatastoreCode(String datastoreCode) {
		this.datastoreCode = datastoreCode;
	}

	public List<String> getConnectCodeList() {
		return connectCodeList;
	}

	public void setConnectCodeList(List<String> connectCodeList) {
		this.connectCodeList = connectCodeList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getVm_name() {
		return vm_name;
	}

	public void setVm_name(String vm_name) {
		this.vm_name = vm_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}
