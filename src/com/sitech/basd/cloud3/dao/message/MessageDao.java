package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import com.sitech.basd.cloud3.domain.message.MessageObj;

public interface MessageDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询下发短信记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(MessageObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(插入下发短信记录)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午9:41:46
	 */
	public int insertByObj(MessageObj obj);

}
