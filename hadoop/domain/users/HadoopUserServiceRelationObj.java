package domain.users;

import java.util.List;

/**
 * <p>
 * Title: HadoopUserServiceRelationObj
 * </p>
 * <p>
 * Description: 用户或用户组与服务的关系
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
 * @createtime 2014-2-21 下午2:31:29
 * 
 */
public class HadoopUserServiceRelationObj {

	private String id;// 主键
	private String entity_id;// 实体id（即用户或用户组的id）
	private Integer entity_type;// 实体类型 0用户 1用户组
	private Integer service_type;// 服务类型
	private Integer status;// 任务状态 0待处理 1处理中 2处理成功 3处理失败
	private Integer deal_type;// 操作类型 0创建 1删除 2修改

	private List<String> idList;

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
	public Integer getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(Integer entity_type) {
		this.entity_type = entity_type;
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
	public Integer getDeal_type() {
		return deal_type;
	}
	public void setDeal_type(Integer deal_type) {
		this.deal_type = deal_type;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

}
