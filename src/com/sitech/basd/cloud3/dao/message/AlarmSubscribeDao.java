package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import com.sitech.basd.cloud3.domain.message.AlarmSubscribeObj;

public interface AlarmSubscribeDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询订阅告警信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(AlarmSubscribeObj obj);

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
	public AlarmSubscribeObj queryForObject(AlarmSubscribeObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(插入订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int insertByObj(AlarmSubscribeObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: TODO(更新订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int updateByObj(AlarmSubscribeObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: TODO(删除订阅告警信息)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int deleteByObj(AlarmSubscribeObj obj);

}
