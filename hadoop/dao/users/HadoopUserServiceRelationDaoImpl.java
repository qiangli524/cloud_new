package dao.users;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.users.HadoopUserServiceRelationObj;

/**
 * <p>Title: HadoopUserServiceRelationDaoImpl</p>
 * <p>Description: 用户或用户组与服务关系</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipp
 * @version 1.0
 * @createtime 2014-2-21 下午2:43:23
 *
 */
@Repository("hadoopUserServiceRelationDao")
public class HadoopUserServiceRelationDaoImpl extends BaseDao implements
		HadoopUserServiceRelationDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:41:52
	 */
	@Override
	public int insertByObj(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopUserServiceRelation.insertByObj", hadoopUserServiceRelationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUserServiceRelation.insertByObj: "  + e.getMessage() + e.getClass().getName());
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
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-21 下午2:42:24
	 */
	@Override
	public int deleteByObj(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		int ret = 0;
		try {
			getSqlMap().delete("HadoopUserServiceRelation.deleteByObj", hadoopUserServiceRelationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUserServiceRelation.deleteByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: updateStatus
	 * @Description: 更新状态
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 下午2:28:55
	 */
	@Override
	public int updateStatus(
			HadoopUserServiceRelationObj hadoopUserServiceRelationObj) {
		int ret = 0;
		try {
			getSqlMap().update("HadoopUserServiceRelation.updateStatus", hadoopUserServiceRelationObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUserServiceRelation.updateStatus: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

}
