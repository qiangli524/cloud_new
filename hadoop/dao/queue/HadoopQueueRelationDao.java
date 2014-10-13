package dao.queue;

import java.util.List;

import domain.queue.HadoopQueueRelationObj;

/**
 * <p>Title: HadoopQueueRelationDao</p>
 * <p>Description: 队列关系表持久层接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-2-10 上午10:02:25
 *
 */
public interface HadoopQueueRelationDao {

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询结果集
	 * @param
	 * @return List<HadoopQueueRelationObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-10 上午10:03:42
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
	 * @createtime 2014-2-10 上午10:04:43
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
	 * @createtime 2014-2-10 上午10:05:25
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
	 * @createtime 2014-2-10 上午10:06:11
	 */
	public int updateByObj(HadoopQueueRelationObj relationObj);
}
