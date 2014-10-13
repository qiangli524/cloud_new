package service.queue;

import java.util.List;

import domain.queue.HadoopQueueObj;

/**
 * <p>Title: HadoopQueueService</p>
 * <p>Description: 队列逻辑层接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-21 下午7:18:55
 *
 */
public interface HadoopQueueService {
	/**
	 * @Title: queryForList
	 * @Description: 查询列表
	 * @param
	 * @return List<HadoopQueueObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午6:57:51
	 */
	public List<HadoopQueueObj> queryForListByObj(HadoopQueueObj hadoopQueueObj);
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录，如果传入id，则删除一条记录，如果传入idList，则批量删除
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午6:58:33
	 */
	public int deleteByObj(HadoopQueueObj hadoopQueueObj);
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:01:55
	 */
	public int insertByObj(HadoopQueueObj hadoopQueueObj);
	
	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:03:08
	 */
	public int updateByObj(HadoopQueueObj hadoopQueueObj);

	/**
	 * @Title: queryForParentList
	 * @Description: 查询父队列
	 * @param
	 * @return List<HadoopQueueObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-13 下午5:25:51
	 */
	public List<HadoopQueueObj> queryForParentList(HadoopQueueObj hadoopQueueObj);

	/**
	 * @Title: queryForSubQueueList
	 * @Description: 查询子队列集合
	 * @param
	 * @return List<HadoopQueueObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-13 下午5:45:28
	 */
	public List<HadoopQueueObj> queryForSubQueueList(HadoopQueueObj queueObj);
	
	/**
	 * 
	 * @Title: queryConfigInfo
	 * @Description: 获取配置文件路径,用户名,密码,ip
	 * @param
	 * @return HadoopQueueObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-6 下午3:14:28
	 */
	public HadoopQueueObj queryConfigInfo(HadoopQueueObj obj);
	
	/**
	 * 
	 * @Title: validateQueueName
	 * @Description: 校验名字是否存在
	 * @param
	 * @return List<HadoopQueueObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-10 下午3:29:50
	 */
	public List<HadoopQueueObj> validateQueueName(HadoopQueueObj obj);
}
