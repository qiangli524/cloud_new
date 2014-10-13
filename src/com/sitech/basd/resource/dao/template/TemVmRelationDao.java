package com.sitech.basd.resource.dao.template;

import java.util.List;

import com.sitech.basd.resource.domain.template.TemVmRelationObj;

public interface TemVmRelationDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询关系列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public List queryForList(TemVmRelationObj obj);

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 删除一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int deleteByObj(TemVmRelationObj obj);

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
}
