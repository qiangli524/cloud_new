package domain.kpi;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

import domain.tab.ChartType;

public class HadoopKpiObj extends BaseObj{
	private String id;
	private String description;
	private String kpi_id;
	private String unit;
	private String shape;//1.线图2.面图
	private String isEffect;//1有效2无效
	private String level;//0高1中2低
	private String serviceId;
	private ChartType type;
	private String lastData;//最新的监控数据此指标
	private String serverNodeId;
	private String hostName;
	private String clusterName;
	private String threshold;//阈值
	private String threshold_type;//百分比,数值
	private String service_name;// 服务名称
	
	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getThreshold_type() {
		return threshold_type;
	}

	public void setThreshold_type(String threshold_type) {
		this.threshold_type = threshold_type;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getServerNodeId() {
		return serverNodeId;
	}

	public void setServerNodeId(String serverNodeId) {
		this.serverNodeId = serverNodeId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public HadoopKpiObj() {
		super();
	}

	public HadoopKpiObj(String kpi_id,String description, ChartType type) {
		super();
		this.description = description;
		this.kpi_id = kpi_id;
		this.type = type;
	}
	
	public String getServiceId() {
		return serviceId;
	}

	public String getLastData() {
		return lastData;
	}

	public void setLastData(String lastData) {
		this.lastData = lastData;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

	public String getLevel() {
		return level;
	}

	public ChartType getType() {
		return type;
	}

	public void setType(ChartType type) {
		this.type = type;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
