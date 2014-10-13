package dao.queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.queue.HadoopQueueRelationObj;

/**
 * <p>Title: HadoopQueueRelationDaoImpl</p>
 * <p>Description: 队列关系表持久层实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-2-10 上午10:07:12
 *
 */
@Repository("hadoopQueueRelationDao")
public class HadoopQueueRelationDaoImpl extends BaseDao implements HadoopQueueRelationDao {

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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopQueueRelationObj> queryForListByObj(HadoopQueueRelationObj relationObj) {
		List<HadoopQueueRelationObj> list = new ArrayList<HadoopQueueRelationObj>();
		try {
			list = getSqlMap().queryForList("HadoopQueueRelation.queryForListByObj", relationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueueRelation.queryForListByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

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
	@Override
	public int insertByObj(HadoopQueueRelationObj relationObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopQueueRelation.insertByObj", relationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueueRelation.insertByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

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
	@Override
	public int deleteByObj(HadoopQueueRelationObj relationObj) {
		int ret = 0;
		try {
			getSqlMap().delete("HadoopQueueRelation.deleteByObj", relationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueueRelation.deleteByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

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
	@Override
	public int updateByObj(HadoopQueueRelationObj relationObj) {
		int ret = 0;
		try {
			getSqlMap().update("HadoopQueueRelation.updateByObj", relationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueueRelation.updateByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

}
