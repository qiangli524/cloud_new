package domain.monitor;

import java.util.List;

public class HadoopMonitorObj {
	private String host_name;// 主机名称
	private String cluster_name;// 集群名称
	private String kpi_id;// 指标ID
	private String kpi_value;// 指标值
	private String coll_time;// 时间
	private String description;// 指标描述
	private String unit;// 指标单位
	private String ipaddress;// ip地址
	private String startTime;
	private String endTime;
	private List<String> uuidList;
	private String serviceId;// 服务Id
	private String tableName;// 表名
	private String labelname;// 主机名（树节点显示名称）
	private String service_name;// 服务名称

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getKpi_value() {
		return kpi_value;
	}

	public void setKpi_value(String kpi_value) {
		this.kpi_value = kpi_value;
	}

	public String getColl_time() {
		return coll_time;
	}

	public void setColl_time(String coll_time) {
		this.coll_time = coll_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public List<String> getUuidList() {
		return uuidList;
	}

	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}
}
