package dao.resourceV;

/**
 * <p>Title: ResourceViewObj</p>
 * <p>Description: 资源视图
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-8-28 下午4:08:20
 *
 */
public class ResourceViewObj {
	
	// 所属资源池
	private String connect_id;
	// 资源标识
	private String uuid;
	// 资源类型
	private String type;
	// 例:VENTER_m0_host_105
	private String entity_id;
	// 所属域
	private String domain;

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
