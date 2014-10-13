package com.sitech.basd.component.tree.service.rule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.component.tree.dao.rule.RuleValidateDao;
import com.sitech.basd.component.tree.domain.rule.RuleValidateObj;

@Service("ruleValidateService")
public class RuleValidateServiceImpl implements RuleValidateService {
	@Autowired
	private RuleValidateDao ruleValidateDao;

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
		return ruleValidateDao.queryForList(obj);
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
		return ruleValidateDao.insertByObj(obj);
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
		return ruleValidateDao.updateByObj(obj);
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
		return ruleValidateDao.deleteByObj(obj);
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
		return ruleValidateDao.deleteByTaskId(obj);
	}
}
