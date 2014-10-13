package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import com.sitech.basd.cloud3.domain.message.AlarmUserObj;

public interface AlarmUserDao {
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
	public List queryForListByObj(AlarmUserObj obj);

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
	public int insertByObj(AlarmUserObj obj);

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
	public int updateByObj(AlarmUserObj obj);

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
	public int deleteByObj(AlarmUserObj obj);

}
