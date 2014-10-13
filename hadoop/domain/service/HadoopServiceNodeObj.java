package domain.service;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class HadoopServiceNodeObj extends BaseObj{
	private String id;//物理主键
	private String service_node_id;//服务节点标识
	private String service_node_name;//服务节点名称
	private String service_type;//服务节点类型
	private String host_id;//所属主机标识
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getService_node_id() {
		return service_node_id;
	}
	public void setService_node_id(String service_node_id) {
		this.service_node_id = service_node_id;
	}
	public String getService_node_name() {
		return service_node_name;
	}
	public void setService_node_name(String service_node_name) {
		this.service_node_name = service_node_name;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getHost_id() {
		return host_id;
	}
	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}
}
