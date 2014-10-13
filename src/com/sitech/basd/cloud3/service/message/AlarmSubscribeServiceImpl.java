package com.sitech.basd.cloud3.service.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.message.AlarmSubscribeDao;
import com.sitech.basd.cloud3.domain.message.AlarmSubscribeObj;

@Service("alarmSubscribeService")
public class AlarmSubscribeServiceImpl implements AlarmSubscribeService {
	@Autowired
	private AlarmSubscribeDao alarmSubscribeDao;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询订阅告警信息
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(AlarmSubscribeObj obj) {
		return alarmSubscribeDao.queryForListByObj(obj);
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
		return alarmSubscribeDao.queryForObject(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(插入订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午10:06:56
	 */
	public int insertByObj(AlarmSubscribeObj obj) {
		return alarmSubscribeDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: TODO(更新订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午10:06:56
	 */
	public int updateByObj(AlarmSubscribeObj obj) {
		return alarmSubscribeDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: TODO(删除订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午10:06:56
	 */
	public int deleteByObj(AlarmSubscribeObj obj) {
		return alarmSubscribeDao.deleteByObj(obj);
	}
}
