package com.sitech.shop.dao.broadband;

import java.util.List;

import com.sitech.shop.domain.broadband.RuleNumberObj;

/**
 * 购买宽带所需要的规则号ID
 * @author duangh
 *
 */
public interface RuleNumberDao {
	/**
	 * 查询规则号信息列表
	 * @param 要过滤的规则号
	 * @return 规则号列表
	 */
	public List<RuleNumberObj> queryRuleNumList(RuleNumberObj obj);
	
	/**
	 * 插入规则号信息
	 * @param 要插入的规则号信息
	 * @return 插入的规则号ID
	 */
	public int insertRuleNum(RuleNumberObj obj);
	
	/**
	 * 修改规则号信息
	 * @param 要修改的规则号信息
	 * @return 修改的规则号ID
	 */
	public int updataRuleNum(RuleNumberObj obj);
	
	/**
	 * 删除规则号信息
	 * @param 要删除的规则号信息
	 * @return 删除的规则号ID
	 */
	public int deleteRuleNum(RuleNumberObj obj);
	
	/**
	 * 释放占用的rule number
	 * @param obj
	 * @return
	 */
	public int releaseRuleNum(RuleNumberObj obj);
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
	public RuleNumberObj queryByObj(RuleNumberObj obj);
	
}
