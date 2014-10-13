package domain.tree;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class HadoopTreeObj {
	private String id;// 主键
	private String name;// 节点名称
	@XStreamOmitField
	private String parent_id;// 父节点标示
	private String node_type;// 节点类型
	@XStreamOmitField
	private String service_type;// 服务类型
	@XStreamOmitField
	private String service_name;// 服务的具体名称
	@XStreamOmitField
	private String uuid;// 实体唯一标示
	@XStreamOmitField
	private Boolean isParent = true;// 是否为父节点
	@XStreamOmitField
	private String icon;// 图标
	@XStreamOmitField
	private Boolean nocheck = true;
	@XStreamOmitField
	private String kpiId;// kpi
	@XStreamOmitField
	private String startTime;
	@XStreamOmitField
	private String endTime;
	@XStreamOmitField
	private List nodeList;
	@XStreamOmitField
	private String parent_nodeType;//主机节点的父节点的节点类型
	@XStreamOmitField
	private String parent_serviceType;//主机节点的父节点的服务类型
	@XStreamOmitField
	private String hostId;// 主机ID
	@XStreamOmitField
	private String cluster_id;// 标识
	@XStreamOmitField
	private String status;//节点状态
	@XStreamOmitField
	private List<String> parentIdList;//父节点集合
	@XStreamOmitField
	private String user_defined;// 主/从
	private String title;//用于树节点显示异常信息
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List getNodeList() {
		return nodeList;
	}

	public void setNodeList(List nodeList) {
		this.nodeList = nodeList;
	}

	public String getParent_serviceType() {
		return parent_serviceType;
	}

	public void setParent_serviceType(String parent_serviceType) {
		this.parent_serviceType = parent_serviceType;
	}

	public String getParent_nodeType() {
		return parent_nodeType;
	}

	public void setParent_nodeType(String parent_nodeType) {
		this.parent_nodeType = parent_nodeType;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
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

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public List<String> getParentIdList() {
		return parentIdList;
	}

	public void setParentIdList(List<String> parentIdList) {
		this.parentIdList = parentIdList;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser_defined() {
		return user_defined;
	}

	public void setUser_defined(String user_defined) {
		this.user_defined = user_defined;
	}

	
}
