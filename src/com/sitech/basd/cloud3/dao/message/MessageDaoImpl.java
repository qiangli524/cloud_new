package com.sitech.basd.cloud3.dao.message;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.message.MessageObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDao implements MessageDao {
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
	public List queryForListByObj(MessageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Push_5_0000.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("Push_5_0000.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Push_5_0000.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

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
	public int insertByObj(MessageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Push_5_0000.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Push_5_0000.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
