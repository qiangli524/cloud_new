package domain.jvm;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class HadoopJvmObj extends BaseObj{
	private String kpi_id;
	private String description;
	private String host_name;
	private String cluster_name;
	private String startTime;
	private String endTime;
	private String points;//显示多少个点
	private String node_type;// 节点类型
	private String id;// 节点ID
	private String parent_id;
	private List uuidList;

	public List getUuidList() {
		return uuidList;
	}

	public void setUuidList(List uuidList) {
		this.uuidList = uuidList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String getKpi_id() {
		return kpi_id;
	}
	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getCluster_name() {
		return cluster_name;
	}
	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
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
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	
}
