package com.sitech.basd.cloud3.dao.monitor;

import java.util.List;

import com.sitech.basd.cloud3.domain.monitor.InterfaceDetailObj;
import com.sitech.basd.cloud3.domain.monitor.InterfaceInfoObj;

public interface InterDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询接口执行的详细信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 4:35:26 PM
	 */
	public List queryForListByObj(InterfaceDetailObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 显示几种接口相关信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jan 9, 2013 7:02:48 PM
	 */
	public List queryForList(InterfaceInfoObj obj);
}
