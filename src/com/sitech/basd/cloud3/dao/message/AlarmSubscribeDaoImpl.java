package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.message.AlarmSubscribeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("alarmSubscribeDao")
public class AlarmSubscribeDaoImpl extends BaseDao implements AlarmSubscribeDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询订阅短信信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(AlarmSubscribeObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Alarm_subscribe.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Alarm_subscribe.queryForListByObj",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Alarm_subscribe.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询订阅告警信息
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public AlarmSubscribeObj queryForObject(AlarmSubscribeObj obj) {
		AlarmSubscribeObj subscribeObj = null;
		try {
			subscribeObj = (AlarmSubscribeObj) getSqlMap().queryForObject(
					"Alarm_subscribe.queryForObject", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Alarm_subscribe.queryForObject:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return subscribeObj;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(插入订阅短信信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int insertByObj(AlarmSubscribeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Alarm_subscribe.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_subscribe.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: TODO(更新订阅短信信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int updateByObj(AlarmSubscribeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Alarm_subscribe.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_subscribe.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: TODO(删除订阅短信信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int deleteByObj(AlarmSubscribeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Alarm_subscribe.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Alarm_subscribe.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
