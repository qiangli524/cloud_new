package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.message.AlarmUserObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("alarmUserDao")
public class AlarmUserDaoImpl extends BaseDao implements AlarmUserDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询短信告警联系人信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(AlarmUserObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Alarm_user.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Alarm_user.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Alarm_user.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(插入短信告警联系人信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int insertByObj(AlarmUserObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Alarm_user.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_user.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: TODO(更新短信告警联系人信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int updateByObj(AlarmUserObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Alarm_user.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_user.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: TODO(删除短信告警联系人信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int deleteByObj(AlarmUserObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Alarm_user.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_user.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
