package domain.queue;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: HadoopQueueObj</p>
 * <p>Description: 队列实体类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-21 下午5:53:25
 *
 */
public class HadoopQueueObj extends BaseObj{
	private String id;//主键
	private String queue_name;//队列名
	private String tactics;//调度策略 1fifo 2container 3fair
	private Double cpu_max;//cpu最大值
	private Double cpu_min;//cpu最小值
	private Double mem_max;//内存最大值
	private Double mem_min;//内存最小值
	private String parent_id;//付队列的id
	private Integer type;//队列类型 0 父队列 1 子队列
	
	private int subQueueCount;//子队列的个数
	private List<String> idList;//id集合
	private String userId;//单个或多个用户id
	private String configId;//单个或多个配置文件id
	private String service_id;//用户和服务关系表中的主键
	private String config_name;//配置文件名称
	private Integer entity_type;
	
	private String config_path;//配置文件路径
	private String ip;//配置文件所在机器的ip地址
	private String user_name;//配置文件所在机器的用户名
	private String pass_word;//配置文件所在机器的密码
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQueue_name() {
		return queue_name;
	}
	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}
	public String getTactics() {
		return tactics;
	}
	public void setTactics(String tactics) {
		this.tactics = tactics;
	}
	public Double getCpu_max() {
		return cpu_max;
	}
	public void setCpu_max(Double cpu_max) {
		this.cpu_max = cpu_max;
	}
	public Double getCpu_min() {
		return cpu_min;
	}
	public void setCpu_min(Double cpu_min) {
		this.cpu_min = cpu_min;
	}
	public Double getMem_max() {
		return mem_max;
	}
	public void setMem_max(Double mem_max) {
		this.mem_max = mem_max;
	}
	public Double getMem_min() {
		return mem_min;
	}
	public void setMem_min(Double mem_min) {
		this.mem_min = mem_min;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public int getSubQueueCount() {
		return subQueueCount;
	}
	public void setSubQueueCount(int subQueueCount) {
		this.subQueueCount = subQueueCount;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getConfig_name() {
		return config_name;
	}
	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}
	public Integer getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(Integer entity_type) {
		this.entity_type = entity_type;
	}
	public String getConfig_path() {
		return config_path;
	}
	public void setConfig_path(String config_path) {
		this.config_path = config_path;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	
}
