package com.sitech.basd.component.tree.dao.rule;

import java.util.List;

import com.sitech.basd.component.tree.domain.rule.RuleValidateObj;

public interface RuleValidateDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 9:24:27 AM
	 */
	public List queryForList(RuleValidateObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 9:27:46 AM
	 */
	public int insertByObj(RuleValidateObj obj);

	/**
	 * 
	 * @Title: updateByObj
	 * @Description:更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 9:28:20 AM
	 */
	public int updateByObj(RuleValidateObj obj);

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 9:28:59 AM
	 */
	public int deleteByObj(RuleValidateObj obj);

	/**
	 * 
	 * @Title: deleteByTaskId
	 * @Description:通过taskId删除任务
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 1, 2013 5:21:26 PM
	 */
	public int deleteByTaskId(RuleValidateObj obj);
}
