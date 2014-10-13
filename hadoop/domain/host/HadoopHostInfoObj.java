package domain.host;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: HadoopHostInfoObj</p>
 * <p>Description: hadoop主机表实体类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午10:21:16
 *
 */
public class HadoopHostInfoObj extends BaseObj{
	private String id;//物理主键
	private String host_name;// 主机名称用于监控
	private String host_type;//主机类型 0物理机 2虚拟机
	private String service_type;//服务器类型 0x86服务器 1机架服务器 2vmware虚拟机 3xen虚拟机 4kvm虚拟机 5其他
	private String os;//操作系统
	private String service;//服务类型
	private Double cpu_ghz;//CPUGHZ
	private Double mem_size;//内存大小
	private Double local_disk_size;//本地存储总大小
	private Double swap_size;//交换区大小
	private String ip;//IP地址
	private Integer cpu_num;//cpu个数
	private String cluster_id;//集群id
	private String cluster_name;//集群名称
	private String label_name;// 主机名字
	private String serviceId;//服务节点Id
	private Integer status;//主机状态
	private String node_id;// 节点ID
	private String node_type;// 节点类型
	private String parent_id;//父节点ID
	private String kpi_id;
	private List nodeIdList;// 节点ID List
	private List hostNameList;// 主机名字集合
	
	private Integer allcount;//进程总数
	private Integer normalcount;//正常进程数
	private int state;//节点状态
	
	private String kpi_value;//
	
	public List getHostNameList() {
		return hostNameList;
	}

	public void setHostNameList(List hostNameList) {
		this.hostNameList = hostNameList;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getHost_type() {
		return host_type;
	}
	public void setHost_type(String host_type) {
		this.host_type = host_type;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Double getCpu_ghz() {
		return cpu_ghz;
	}
	public void setCpu_ghz(Double cpu_ghz) {
		this.cpu_ghz = cpu_ghz;
	}
	public Double getMem_size() {
		return mem_size;
	}
	public void setMem_size(Double mem_size) {
		this.mem_size = mem_size;
	}
	public Double getLocal_disk_size() {
		return local_disk_size;
	}
	public void setLocal_disk_size(Double local_disk_size) {
		this.local_disk_size = local_disk_size;
	}
	public Double getSwap_size() {
		return swap_size;
	}
	public void setSwap_size(Double swap_size) {
		this.swap_size = swap_size;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getCpu_num() {
		return cpu_num;
	}
	public void setCpu_num(Integer cpu_num) {
		this.cpu_num = cpu_num;
	}
	public String getCluster_id() {
		return cluster_id;
	}
	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}
	public String getCluster_name() {
		return cluster_name;
	}
	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public List getNodeIdList() {
		return nodeIdList;
	}

	public void setNodeIdList(List nodeIdList) {
		this.nodeIdList = nodeIdList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	public Integer getNormalcount() {
		return normalcount;
	}

	public void setNormalcount(Integer normalcount) {
		this.normalcount = normalcount;
	}

	public String getKpi_value() {
		return kpi_value;
	}

	public void setKpi_value(String kpi_value) {
		this.kpi_value = kpi_value;
	}
	
}
