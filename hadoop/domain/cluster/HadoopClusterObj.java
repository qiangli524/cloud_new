package domain.cluster;

/**
 * 
 * <p>
 * Title: HadoopClusterObj
 * </p>
 * <p>
 * Description: 集群表相关属性
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
 * @createtime 2014-1-13 下午4:31:03
 * 
 */
public class HadoopClusterObj {
	private String id;
	private String cluster_name;
	private String cluster_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCluster_name() {
		return cluster_name;
	}

	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

}
