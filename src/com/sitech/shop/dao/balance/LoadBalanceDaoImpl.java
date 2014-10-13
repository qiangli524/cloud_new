package com.sitech.shop.dao.balance;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.shop.domain.balance.LoadBalanceObj;

@Repository("loadBalanceDao")
public class LoadBalanceDaoImpl extends BaseDao implements LoadBalanceDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询负载均衡器列表
	 * @param
	 * @return List<LoadBalanceObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-24 下午2:10:40
	 */
	public List<LoadBalanceObj> queryForList(LoadBalanceObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("LoadBalance.queryByObjForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("LoadBalance.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("LoadBalance.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
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
	public LoadBalanceObj queryByObj(LoadBalanceObj obj) {
		LoadBalanceObj result = null;
		List<LoadBalanceObj> list = queryForList(obj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 更新一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int updateByObj(LoadBalanceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("LoadBalance.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("LoadBalance.updateByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public int deleteByObj(LoadBalanceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("LoadBalance.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("LoadBalance.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public int insertByObj(LoadBalanceObj obj) {
		return getSqlMapClientTemplate().update("LoadBalance.insertByObj", obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see com.sitech.basd.resource.dao.loadbalance.LoadBalanceDao#queryForCount(com.sitech.basd.resource.domain.united.loadbalance.LoadBalanceObj)
	 */
	@Override
	public Integer queryForCount(LoadBalanceObj obj) {
		try {
			return (Integer) (getSqlMap().queryForObject("LoadBalance.queryByObjForCount", obj));
		} catch (SQLException e) {
			LogHelper.error("LoadBalance.insertByObj:" + e.getMessage() + getClass().getName());
			return -1;
		}
	}
}
