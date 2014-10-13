package com.sitech.basd.sxcloud.cloud.domain.sxworkorder;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: WorkOrderObj
 * </p>
 * <p>
 * Description: 工单任务实体
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-22 下午1:11:30
 * 
 */
public class OrderTaskObj extends BaseObj {

	// 工单任务表
	private String uuid;// 主键
	private String templateId;// 模板id
	private String cpuCount;// cpu数量
	private String memory;// 内存大小
	private String storage;// 存储大小
	private String expandStorage;// 存储大小
	private String net1;// 网络ip池1
	private String net2;// 网络ip池2
	private String name;// 虚拟机名字
	private String clusterId;// 集群id
	private String clusterName;// 集群名字
	private String hostname;// 实体id，(虚拟机或主机id)
	private String cpushared;// 处理时间
	private String vmNum;// 主机名
	private String workOrderId;// 工单id
	private String type;// 任务类型。0：vmw 1：sce
	private String status;// 状态0待处理，1处理中，2处理成功，3处理失败
	private String freeDate;// 资源使用截止日期
	private int perfConf;// 性能配置0：低端配置1：中端配置2：高端配置
	private String success;// 成功个数
	private String waiting;// 待处理个数
	private String failNum;// 失败个数

	private String net1name;// net1name
	private String remark;// net1name

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFailNum() {
		return failNum;
	}

	public void setFailNum(String failNum) {
		this.failNum = failNum;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getWaiting() {
		return waiting;
	}

	public void setWaiting(String waiting) {
		this.waiting = waiting;
	}

	public int getPerfConf() {
		return perfConf;
	}

	public void setPerfConf(int perfConf) {
		this.perfConf = perfConf;
	}

	public String getFreeDate() {
		return freeDate;
	}

	public void setFreeDate(String freeDate) {
		this.freeDate = freeDate;
	}

	private String net2name;// net2name
	private String templateName;// templateName

	private List templeteList;// 模板列表
	private List netList;// 网络池列表
	private List clusterList;// 集群列表

	public String getNet1name() {
		return net1name;
	}

	public void setNet1name(String net1name) {
		this.net1name = net1name;
	}

	public String getNet2name() {
		return net2name;
	}

	public void setNet2name(String net2name) {
		this.net2name = net2name;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List getTempleteList() {
		return templeteList;
	}

	public void setTempleteList(List templeteList) {
		this.templeteList = templeteList;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public List getClusterList() {
		return clusterList;
	}

	public void setClusterList(List clusterList) {
		this.clusterList = clusterList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(String cpuCount) {
		this.cpuCount = cpuCount;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getExpandStorage() {
		return expandStorage;
	}

	public void setExpandStorage(String expandStorage) {
		this.expandStorage = expandStorage;
	}

	public String getNet1() {
		return net1;
	}

	public void setNet1(String net1) {
		this.net1 = net1;
	}

	public String getNet2() {
		return net2;
	}

	public void setNet2(String net2) {
		this.net2 = net2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getCpushared() {
		return cpushared;
	}

	public void setCpushared(String cpushared) {
		this.cpushared = cpushared;
	}

	public String getVmNum() {
		return vmNum;
	}

	public void setVmNum(String vmNum) {
		this.vmNum = vmNum;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// private String PROJECT_NAME;// 项目名称
	// private String NETWORK_NAME;// 网络名称
	// private Integer ALLCOUNT;// 任务总数
	// private Integer SUCCESS;// 成功任务个数
	// private Integer FALUIRE;// 失败任务个数
	// private Integer WAITDEAL;// 待处理任务个数
	// private Integer DEALING;// 处理中任务个数
	// private String TEMPLATENAME;// 模板名称
	// private String PROJECT_USER_NAME;// 项目责任人名称
	// private String SHOWNAME;// 申请人显示名称
	// private String BUSISYSTEMNAME;// 工单所属业务系统名称

}
