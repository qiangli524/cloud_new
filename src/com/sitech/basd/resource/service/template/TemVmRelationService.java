package com.sitech.basd.resource.service.template;

import com.sitech.basd.resource.domain.template.TemVmRelationObj;

public interface TemVmRelationService {

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 插入一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int insertByObj(TemVmRelationObj obj);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午8:29:44
	 */
	public void deleteByObj(TemVmRelationObj relation);
}
