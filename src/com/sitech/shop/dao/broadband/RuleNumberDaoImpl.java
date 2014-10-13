package com.sitech.shop.dao.broadband;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.domain.broadband.RuleNumberObj;

@Repository("ruleNumberDao")
public class RuleNumberDaoImpl extends BaseDao implements RuleNumberDao{

	@Override
	public List<RuleNumberObj> queryRuleNumList(RuleNumberObj obj) {
		List<RuleNumberObj> resultList = getSqlMapClientTemplate().queryForList("RuleNumber.queryForList", obj);
		return resultList;
	}

	@Override
	public int insertRuleNum(RuleNumberObj obj) {
		int row = (Integer) getSqlMapClientTemplate().insert("RuleNumber.insertByObj", obj);
		return row;
	}

	@Override
	public int updataRuleNum(RuleNumberObj obj) {
		int row = getSqlMapClientTemplate().update("RuleNumber.updateByObj", obj);
		return row;
	}

	@Override
	public int deleteRuleNum(RuleNumberObj obj) {
		int row = getSqlMapClientTemplate().delete("RuleNumber.deleteByObj", obj);
		return row;
	}

	@Override
	public int releaseRuleNum(RuleNumberObj obj) {
		int row = getSqlMapClientTemplate().update("RuleNumber.releaseRuleNum", obj);
		return row;
	}
	/**
	 * 
	 * @Title: queryOneTemManObj
	 * @Description: 查询单个实体信息
	 * @param
	 * @return LoadBalanceObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-7 上午11:51:21
	 */
	public RuleNumberObj queryByObj(RuleNumberObj obj) {
		RuleNumberObj result = null;
		List<RuleNumberObj> list = queryRuleNumList(obj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

}
