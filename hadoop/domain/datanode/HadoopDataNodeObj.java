package domain.datanode;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class HadoopDataNodeObj extends BaseObj{

	private String id;
	private String last_contact;
	private String admin_state;
	private Double configured_capacity;
	private Double dfs_used;
	private Double non_dfs_used;
	private Double remaining;
	private Double dfs_used_percent;
	private Double remaining_percent;
	private Integer blocks;
	private Double block_pool_used;
	private Double blocks_pool_used_percent;
	private Double failed_volumes;
	private String host_id;
	private String cluster_id;
	
	private String treeClusterId;//树表中的集群uuid
	
	private List<String> hdfsUuidList;
	
	private List<String> hostUuidList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLast_contact() {
		return last_contact;
	}

	public void setLast_contact(String last_contact) {
		this.last_contact = last_contact;
	}

	public String getAdmin_state() {
		return admin_state;
	}

	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}

	public Double getConfigured_capacity() {
		return configured_capacity;
	}

	public void setConfigured_capacity(Double configured_capacity) {
		this.configured_capacity = configured_capacity;
	}

	public Double getDfs_used() {
		return dfs_used;
	}

	public void setDfs_used(Double dfs_used) {
		this.dfs_used = dfs_used;
	}

	public Double getNon_dfs_used() {
		return non_dfs_used;
	}

	public void setNon_dfs_used(Double non_dfs_used) {
		this.non_dfs_used = non_dfs_used;
	}

	public Double getRemaining() {
		return remaining;
	}

	public void setRemaining(Double remaining) {
		this.remaining = remaining;
	}

	public Double getDfs_used_percent() {
		return dfs_used_percent;
	}

	public void setDfs_used_percent(Double dfs_used_percent) {
		this.dfs_used_percent = dfs_used_percent;
	}

	public Double getRemaining_percent() {
		return remaining_percent;
	}

	public void setRemaining_percent(Double remaining_percent) {
		this.remaining_percent = remaining_percent;
	}

	public Integer getBlocks() {
		return blocks;
	}

	public void setBlocks(Integer blocks) {
		this.blocks = blocks;
	}

	public Double getBlock_pool_used() {
		return block_pool_used;
	}

	public void setBlock_pool_used(Double block_pool_used) {
		this.block_pool_used = block_pool_used;
	}

	public Double getBlocks_pool_used_percent() {
		return blocks_pool_used_percent;
	}

	public void setBlocks_pool_used_percent(Double blocks_pool_used_percent) {
		this.blocks_pool_used_percent = blocks_pool_used_percent;
	}

	public Double getFailed_volumes() {
		return failed_volumes;
	}

	public void setFailed_volumes(Double failed_volumes) {
		this.failed_volumes = failed_volumes;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public String getTreeClusterId() {
		return treeClusterId;
	}

	public void setTreeClusterId(String treeClusterId) {
		this.treeClusterId = treeClusterId;
	}

	public List<String> getHdfsUuidList() {
		return hdfsUuidList;
	}

	public void setHdfsUuidList(List<String> hdfsUuidList) {
		this.hdfsUuidList = hdfsUuidList;
	}

	public List<String> getHostUuidList() {
		return hostUuidList;
	}

	public void setHostUuidList(List<String> hostUuidList) {
		this.hostUuidList = hostUuidList;
	}

}
