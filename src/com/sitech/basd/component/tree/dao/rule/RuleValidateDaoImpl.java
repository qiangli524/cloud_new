package com.sitech.basd.component.tree.dao.rule;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.tree.domain.rule.RuleValidateObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("ruleValidateDao")
public class RuleValidateDaoImpl extends BaseDao implements RuleValidateDao {
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
	public List queryForList(RuleValidateObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("Rule.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Rule.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

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
	public int insertByObj(RuleValidateObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Rule.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rule.insertByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int updateByObj(RuleValidateObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Rule.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rule.updateByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteByObj(RuleValidateObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Rule.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rule.deleteByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteByTaskId(RuleValidateObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Rule.deleteByTaskId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rule.deleteByTaskId:" + e.getMessage());
		}
		return ret;
	}
}
