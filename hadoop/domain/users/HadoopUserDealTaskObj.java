package domain.users;

import java.util.List;

/**
 * <p>
 * Title: HadoopUserDealTaskObj
 * </p>
 * <p>
 * Description: hadoop用户或用户组操作任务类
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
 * @createtime 2014-2-21 下午2:37:18
 * 
 */
public class HadoopUserDealTaskObj {

	private String id;// 主键
	private String entity_id;// 实体id
	private Integer service_type;// 服务类型
	private Integer status;// 任务状态 0待处理 1处理中 2处理成功 3处理失败
	private Integer type;// 任务类型 0创建 1删除 2修改
	private Integer deal_count;// 处理次数

	private List<String> uuidList;//

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public Integer getService_type() {
		return service_type;
	}
	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDeal_count() {
		return deal_count;
	}
	public void setDeal_count(Integer deal_count) {
		this.deal_count = deal_count;
	}
	public List<String> getUuidList() {
		return uuidList;
	}
	public void setUuidList(List<String> uuidList) {
		this.uuidList = uuidList;
	}

}
