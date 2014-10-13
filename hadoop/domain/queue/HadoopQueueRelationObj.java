package domain.queue;
/**
 * <p>Title: HadoopQueueObj</p>
 * <p>Description: 队列、用户、配置文件关系实体类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author siweichao
 * @version 1.0
 * @createtime 2014-1-21 下午5:53:25
 *
 */

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * <p>Title: QueueRelationObj</p>
 * <p>Description: 队列与用户或者配置文件的关系表</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-23 上午10:56:20
 *
 */
public class HadoopQueueRelationObj extends BaseObj{
	
	private String queue_id;//队列id
	private String entity_id;//用户id或者配置文件id
	private String entity_type;//实体类型，1 用户  2配置文件
	
	private List<String> queueIdList;//队列id
	public String getQueue_id() {
		return queue_id;
	}
	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getEntity_type() {
		return entity_type;
	}
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	public List<String> getQueueIdList() {
		return queueIdList;
	}
	public void setQueueIdList(List<String> queueIdList) {
		this.queueIdList = queueIdList;
	}

}
