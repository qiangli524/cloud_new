package dao.queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.queue.HadoopQueueObj;

/**
 * <p>Title: HadoopQueueDaoImpl</p>
 * <p>Description: 队列持久层实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-21 下午7:04:22
 *
 */
@Repository("hadoopQueueDao")
public class HadoopQueueDaoImpl extends BaseDao implements HadoopQueueDao {

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
	@SuppressWarnings("unchecked")
	public List<HadoopQueueObj> queryForListByObj(HadoopQueueObj hadoopQueueObj){
		List<HadoopQueueObj> list = new ArrayList<HadoopQueueObj>();
		try {
			if (hadoopQueueObj.getPagination() != null) {
				hadoopQueueObj.setFIRSTROWNUM(hadoopQueueObj.getPagination().getFirstRownum());
				hadoopQueueObj.setPAGESIZE(hadoopQueueObj.getPagination().getPageSize());
				hadoopQueueObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HadoopQueue.queryForListCount", hadoopQueueObj)).intValue()
				);
			}
			list = getSqlMap().queryForList("HadoopQueue.queryForListByObj", hadoopQueueObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("HadoopQueue.queryForListByObj: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录，如果传入id，则删除一条记录，如果传入idList，则批量删除；失败则返回-1
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午6:58:33
	 */
	public int deleteByObj(HadoopQueueObj hadoopQueueObj){
		int ret = 0;
		try {
			getSqlMap().delete("HadoopQueue.deleteByObj", hadoopQueueObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueue.deleteByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录，失败则返回-1
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:01:55
	 */
	public int insertByObj(HadoopQueueObj hadoopQueueObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopQueue.insertByObj",hadoopQueueObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueue.insertByObj:  " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}
	
	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录，失败则返回-1
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:03:08
	 */
	public int updateByObj(HadoopQueueObj hadoopQueueObj){
		int ret = 0;
		try {
			getSqlMap().update("HadoopQueue.updateByObj", hadoopQueueObj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueue.updateByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopQueueObj> queryForParentList(HadoopQueueObj hadoopQueueObj) {
		List<HadoopQueueObj> list = new ArrayList<HadoopQueueObj>();
		try {
			if (hadoopQueueObj.getPagination() != null) {
				hadoopQueueObj.setFIRSTROWNUM(hadoopQueueObj.getPagination().getFirstRownum());
				hadoopQueueObj.setPAGESIZE(hadoopQueueObj.getPagination().getPageSize());
				hadoopQueueObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HadoopQueue.queryForListCount", hadoopQueueObj)).intValue()
				);
			}
			list = getSqlMap().queryForList("HadoopQueue.queryForParentList", hadoopQueueObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("HadoopQueue.queryForParentList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
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
	@SuppressWarnings("unchecked")
	@Override
	public List<HadoopQueueObj> queryForSubQueueList(HadoopQueueObj queueObj) {
		List<HadoopQueueObj> list = new ArrayList<HadoopQueueObj>();
		try {
			list = getSqlMap().queryForList("HadoopQueue.queryForSubQueueList", queueObj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("HadoopQueue.queryForSubQueueList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
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
	@Override
	public HadoopQueueObj queryConfigInfo(HadoopQueueObj obj) {
		HadoopQueueObj queueObj = new HadoopQueueObj();
		try {
			queueObj = (HadoopQueueObj) getSqlMap().queryForObject("HadoopQueue.queryConfigInfo", obj);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("HadoopQueue.queryConfigInfo: " + e.getMessage() + e.getClass().getName());
		}
		return queueObj;
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
		List<HadoopQueueObj> queueList = new ArrayList<HadoopQueueObj>();
		try {
			queueList = getSqlMap().queryForList("HadoopQueue.validateQueueName",obj);
		} catch (Exception e) {
			LogHelper.error("HadoopQueue.validateQueueName:" + e.getMessage() + e.getClass().getName());
		}
		return queueList;
	}
}
