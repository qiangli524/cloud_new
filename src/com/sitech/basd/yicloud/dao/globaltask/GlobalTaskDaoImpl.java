package com.sitech.basd.yicloud.dao.globaltask;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;

@Repository("globalTaskDao")
public class GlobalTaskDaoImpl extends BaseDao implements GlobalTaskDao {
	/**
	 * 
	 * @Title: queryListByObj
	 * @Description:查询全局任务列表
	 * @author duangh
	 * @date Jun 22, 2013 4:34:25 PM
	 * @return
	 */
	@Override
	public List<GlobalTaskObj> queryForListByObj(GlobalTaskObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("GlobalTaskObj.queryByObjForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("GlobalTaskObj.queryForListByObj", obj);
		} catch (Exception e) {
			LogHelper.error("GlobalTaskObj.queryForListByObj:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryListProccessing
	 * @Description:任务栏显示，显示未完成和最近一分钟内的任务
	 * @author duangh
	 * @date Jul 4, 2013 5:46:27 PM
	 * @param obj
	 * @return
	 */
	@Override
	public List<GlobalTaskObj> queryListProccessing(GlobalTaskObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("GlobalTaskObj.queryListProccessing", obj);
		} catch (Exception e) {
			LogHelper.error("GlobalTaskObj.queryListProccessing:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public int insertByObj(GlobalTaskObj obj) {
		int result = 0;
		Object o = getSqlMapClientTemplate().insert("GlobalTaskObj.insertByObj", obj);
		if (o != null) {
			result = Integer.parseInt(o.toString());
		}
		return result;
	}

	@Override
	public int updateByObj(GlobalTaskObj obj) {
		int result = 0;
		Object o = getSqlMapClientTemplate().update("GlobalTaskObj.updateByObj", obj);
		if (o != null) {
			result = Integer.parseInt(o.toString());
		}
		return result;
	}
}
