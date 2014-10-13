package service.queue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

import dao.queue.HadoopQueueRelationDao;
import domain.queue.HadoopQueueRelationObj;

/**
 * <p>Title: HadoopQueueRelationServiceImpl</p>
 * <p>Description: 队列关系表逻辑层实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-2-10 上午10:56:52
 *
 */
@Service("hadoopQueueRelationService")
public class HadoopQueueRelationServiceImpl extends BaseDao implements HadoopQueueRelationService {

	@Autowired
	private HadoopQueueRelationDao hadoopQueueRelationDao;
	
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
	@Override
	public List<HadoopQueueRelationObj> queryForListByObj(HadoopQueueRelationObj relationObj) {
		return hadoopQueueRelationDao.queryForListByObj(relationObj);
	}

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
	@Override
	public int insertByObj(HadoopQueueRelationObj relationObj) {
		return hadoopQueueRelationDao.insertByObj(relationObj);
	}

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
	@Override
	public int deleteByObj(HadoopQueueRelationObj relationObj) {
		return hadoopQueueRelationDao.deleteByObj(relationObj);
	}

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
	@Override
	public int updateByObj(HadoopQueueRelationObj relationObj) {
		return hadoopQueueRelationDao.updateByObj(relationObj);
	}

}
