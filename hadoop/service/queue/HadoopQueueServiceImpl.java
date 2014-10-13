package service.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.queue.HadoopQueueDao;
import domain.queue.HadoopQueueObj;

@Service("hadoopQueueService")
public class HadoopQueueServiceImpl implements HadoopQueueService {

	@Autowired
	private HadoopQueueDao hadoopQueueDao;
	
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
	@Override
	public List<HadoopQueueObj> queryForListByObj(HadoopQueueObj hadoopQueueObj) {
		return hadoopQueueDao.queryForListByObj(hadoopQueueObj);
	}
	
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
	@Override
	public int deleteByObj(HadoopQueueObj hadoopQueueObj) {
		return hadoopQueueDao.deleteByObj(hadoopQueueObj);
	}
	
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
	@Override
	public int insertByObj(HadoopQueueObj hadoopQueueObj) {
		return hadoopQueueDao.insertByObj(hadoopQueueObj);
	}

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
	@Override
	public int updateByObj(HadoopQueueObj hadoopQueueObj) {
		return hadoopQueueDao.updateByObj(hadoopQueueObj);
	}

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
	@Override
	public List<HadoopQueueObj> queryForParentList(HadoopQueueObj hadoopQueueObj) {
		return hadoopQueueDao.queryForParentList(hadoopQueueObj);
	}

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
	@Override
	public List<HadoopQueueObj> queryForSubQueueList(HadoopQueueObj queueObj) {
		return hadoopQueueDao.queryForSubQueueList(queueObj);
	}
	
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
	public HadoopQueueObj queryConfigInfo(HadoopQueueObj obj){
		return hadoopQueueDao.queryConfigInfo(obj);
	}
	
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
	public List<HadoopQueueObj> validateQueueName(HadoopQueueObj obj){
		return hadoopQueueDao.validateQueueName(obj);
	}
}
