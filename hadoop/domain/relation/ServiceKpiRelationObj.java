package domain.relation;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: ServiceKpiRelationObj
 * </p>
 * <p>
 * Description: 服务名称和KPI关系相关属性
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-7 下午7:42:14
 * 
 */
public class ServiceKpiRelationObj extends BaseObj implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String serviceId;// 服务ID
	private String description;// 描述
	private String service_name;// 服务名称
	private String nodeType;// 节点类型
	private String kpi_id;// KPI值
	private String old_kpiId;// 修改之前的kpiId
	private String new_kpiId;// 修改之后的kpiId
	private String service_type;// 服务类型

	public String getOld_kpiId() {
		return old_kpiId;
	}

	public void setOld_kpiId(String old_kpiId) {
		this.old_kpiId = old_kpiId;
	}

	public String getNew_kpiId() {
		return new_kpiId;
	}

	public void setNew_kpiId(String new_kpiId) {
		this.new_kpiId = new_kpiId;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

}
