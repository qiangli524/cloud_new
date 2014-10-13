package com.sitech.basd.resource.dao.united.snapshot;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.united.SnapShotObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@Repository("snapShotDao")
public class SnapShotDaoImpl extends BaseDao implements SnapShotDao {
	/**
	 * 
	 * @Title: queryForSnapShotList
	 * @Description: 查询虚拟机快照列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午6:06:33
	 */
	public List<SnapShotObj> queryForSnapShotList(SnapShotObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("SnapShot.queryByObjForCount", obj))
								.intValue());
			}
			list = getSqlMap().queryForList("SnapShot.queryForSnapShotList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("SnapShot.queryForSnapShotList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条快照信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:31:34
	 */
	public String insertByObj(SnapShotObj obj) {
		String id = RandomUUID.getUuid();
		try {
			obj.setId(id);
			Object o = getSqlMap().insert("SnapShot.insertByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("SnapShot.insertByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return id;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:33:23
	 */
	public int deleteByObj(SnapShotObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("SnapShot.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("SnapShot.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: getSnapshotCountByVm</p> 
	* <p>Description: 根据虚拟机ID或者其快照个数</p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param vm_uuid
	* @return 
	* @see com.sitech.basd.resource.dao.united.snapshot.SnapShotDao#getSnapshotCountByVm(java.lang.String) 
	*/
	@Override
	public Integer getSnapshotCountByVm(SnapShotObj obj) {
		try {
			return (Integer) getSqlMap().queryForObject("SnapShot.queryByObjForCount", obj);
		} catch (Exception sqlException) {
			LogHelper.error("SnapShot.queryForObject:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return null;
	}

	/** (非 Javadoc) 
	* <p>Title: queryForCount</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	 * @throws SQLException 
	* @see com.sitech.basd.resource.dao.united.snapshot.SnapShotDao#queryForCount(com.sitech.basd.resource.domain.united.SnapShotObj) 
	*/
	@Override
	public Integer queryForCount(SnapShotObj obj) throws SQLException {
		return (Integer) (getSqlMap().queryForObject("SnapShot.queryByObjForCount", obj));
	}
}
