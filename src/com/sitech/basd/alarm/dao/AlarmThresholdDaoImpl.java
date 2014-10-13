package com.sitech.basd.alarm.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("alarmThresholdDao")
public class AlarmThresholdDaoImpl extends BaseDao implements AlarmThresholdDao {
	/**
	 * @Title:查询出全部信息
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public List queryForListByObj(AlarmThreshold obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("AlarmThreshold.queryByObjForCount",
								obj)).intValue());
				list = getSqlMap().queryForList("AlarmThreshold.queryForListByObj", obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("AlarmThreshold.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:插入信息
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public int addAlarmThresholdByObj(AlarmThreshold obj) {
		String id = RandomUUID.getUuid();
		obj.setId(id);
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("AlarmThreshold.insertByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("AlarmThreshold.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:修改:通过ID
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public List<AlarmThreshold> queryListById(AlarmThreshold obj) {
		List<AlarmThreshold> alarmObj = null;
		try {
			alarmObj = getSqlMap().queryForList("AlarmThreshold.queryById", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("AlarmThreshold.queryById:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return alarmObj;
	}

	/**
	 * @Title:修改
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public void updateAlarmThresholdById(AlarmThreshold obj) {
		try {
			Object ob = getSqlMap().update("AlarmThreshold.update", obj);
		} catch (SQLException e) {
			LogHelper.error("AlarmThreshold.update:" + e.getMessage());
		}
	}

	/**
	 * @Title:删除
	 * @Copyright: Copyright (c) 2014-6-20
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 */
	public void deleteAlarmThresholdById(AlarmThreshold obj) {
		int ret = 0;
		try {
			getSqlMap().delete("AlarmThreshold.delete", obj);
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("AlarmThreshold.delete:" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: validateNameByEdit
	 * @Description: 验证名字是否重复
	 * @param
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-20
	 */
	public int validateNameByEdit(AlarmThreshold obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().queryForObject("AlarmThreshold.validateNameByEdit", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("AlarmThreshold.validateNameByEdit:" + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}
}
