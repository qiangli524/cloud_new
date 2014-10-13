package com.sitech.basd.cloud3.service.message;

import java.util.List;

import com.sitech.basd.cloud3.domain.message.MessageObj;

public interface MessageService {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询短信下发记录
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
	 * @Description: TODO(插入短信下发记录)
	 * @param
	 * @return int
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-5-22 上午10:06:56
	 */
	public int insertByObj(MessageObj obj);
}
