package service.queue;

import java.util.List;

import domain.queue.HadoopQueueRelationObj;

/**
 * <p>Title: HadoopQueueRelationService</p>
 * <p>Description: 队列关系表逻辑层接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-2-10 上午10:49:17
 *
 */
public interface HadoopQueueRelationService {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询结果集
	 * @param
	 * @return List<HadoopQueueRelationObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-10 上午10:50:16
	 */
	public List<HadoopQueueRelationObj> queryForListByObj(HadoopQueueRelationObj relationObj);
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-10 上午10:50:58
	 */
	public int insertByObj(HadoopQueueRelationObj relationObj);
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-10 上午10:52:43
	 */
	public int deleteByObj(HadoopQueueRelationObj relationObj);
	
	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-10 上午10:53:27
	 */
	public int updateByObj(HadoopQueueRelationObj relationObj);
}
