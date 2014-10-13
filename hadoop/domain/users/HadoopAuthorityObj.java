package domain.users;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>
 * Title: HadoopAuthorityObj
 * </p>
 * <p>
 * Description: hadoop权限
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
 * @createtime 2014-3-5 下午4:11:47
 * 
 */
public class HadoopAuthorityObj extends BaseObj {

	private String id;// 主键
	private String entity_id;// 实体id
	private String path;// 路径
	private Integer service_type;// 服务类型
	private String authority;// 权限 形如777 542 等

	private List<String> entityIdList;// 实体id集合

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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getService_type() {
		return service_type;
	}
	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public List<String> getEntityIdList() {
		return entityIdList;
	}
	public void setEntityIdList(List<String> entityIdList) {
		this.entityIdList = entityIdList;
	}

}
