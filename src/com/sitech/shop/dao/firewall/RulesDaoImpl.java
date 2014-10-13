package com.sitech.shop.dao.firewall;

/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
* @Title: RulesDaoImpl.java 
* @Package com.sitech.basd.resource.dao.united 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-4-23 上午11:17:00 
* @version V1.0   
*/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.shop.domain.firewall.RulesObj;

/** 
 * @ClassName: RulesDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 上午11:17:00 
 * @version 1.0 
 */
@Repository("rulesDao")
public class RulesDaoImpl extends BaseDao implements RulesDao {

	/** (非 Javadoc) 
	* <p>Title: queryForListByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.RulesDao#queryForListByObj(com.sitech.basd.resource.domain.united.RulesObj) 
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<RulesObj> queryForListByObj(RulesObj obj) {
		List<RulesObj> list = new ArrayList<RulesObj>();
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("Rules.queryByObjForCount", obj))
								.intValue()); // 分页查询的基本信息 }
			}
			list = getSqlMap().queryForList("Rules.queryForRulesList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Rules.queryForRulesList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/** (非 Javadoc) 
	* <p>Title: insertByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.RulesDao#insertByObj(com.sitech.basd.resource.domain.united.RulesObj) 
	*/
	@Override
	public int insertByObj(RulesObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Rules.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rules.insertByObj:" + e.getMessage());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: deleteByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.RulesDao#deleteByObj(com.sitech.basd.resource.domain.united.RulesObj) 
	*/
	@Override
	public int deleteByObj(RulesObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Rules.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rules.deleteByObj:" + e.getMessage());
		}
		return ret;
	}

	/** (非 Javadoc) 
	* <p>Title: updateByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.RulesDao#updateByObj(com.sitech.basd.resource.domain.united.RulesObj) 
	*/
	@Override
	public int updateByObj(RulesObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Rules.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Rules.updateByObj:" + e.getMessage());
		}
		return ret;
	}

	

	/** (非 Javadoc) 
	* <p>Title: getRulesByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.RulesDao#getRulesByObj(com.sitech.basd.resource.domain.united.RulesObj) 
	*/
	@Override
	public RulesObj getRulesByObj(RulesObj obj) {
		try {
			obj = (RulesObj)getSqlMap().queryForObject("Rules.queryForRulesObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Rules.queryForRulesObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return obj;
	}

	/** (非 Javadoc) 
	* <p>Title: queryForCount</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	 * @throws SQLException 
	* @see dao.rules.RulesDao#queryForCount(domain.rules.RulesObj) 
	*/
	@Override
	public Integer queryForCount(RulesObj obj) throws SQLException {
		return (Integer) (getSqlMap().queryForObject("Rules.queryByObjForCount", obj));
	}

}
