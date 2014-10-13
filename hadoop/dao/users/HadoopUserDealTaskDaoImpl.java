package dao.users;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

import domain.users.HadoopUserDealTaskObj;

@Repository("hadoopUserDealTaskDao")
public class HadoopUserDealTaskDaoImpl extends BaseDao
		implements
			HadoopUserDealTaskDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-24 上午10:46:30
	 */
	@Override
	public int insertByObj(HadoopUserDealTaskObj taskObj) {
		int ret = 0;
		try {
			getSqlMap().insert("HadoopUserDealTask.insertByObj", taskObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUserDealTask.insertByObj: " + e.getMessage()
					+ e.getClass().getName());
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
	 * @createtime 2014-3-4 下午2:39:39
	 */
	@Override
	public int deleteByObj(HadoopUserDealTaskObj taskObj) {
		int ret = 0;
		try {
			getSqlMap().delete("HadoopUserDealTask.deleteByObj", taskObj);
		} catch (Exception e) {
			LogHelper.error("HadoopUserDealTask.deleteByObj: " + e.getMessage()
					+ e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

}
