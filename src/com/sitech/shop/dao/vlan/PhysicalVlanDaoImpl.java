/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: PhysicalVlanDaoImpl.java 
 * @Package com.sitech.basd.resource.dao.united 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 上午11:17:00 
 * @version V1.0   
 */
package com.sitech.shop.dao.vlan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;

/**
 * @ClassName: PhysicalVlanDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 上午11:17:00
 * @version 1.0
 */
@Repository("physicalVlanDao")
public class PhysicalVlanDaoImpl extends BaseDao implements PhysicalVlanDao {

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForListByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#queryForListByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PhysicalVlanObj> queryForListByObj(PhysicalVlanObj obj) throws Exception {
		List<PhysicalVlanObj> list = new ArrayList<PhysicalVlanObj>();
		if (obj.getPagination() != null) {
			obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
			obj.setPAGESIZE(obj.getPagination().getPageSize());
			obj.getPagination().setTotalCount(
					((Integer) getSqlMapClientTemplate().queryForObject(
							"PhysicalVlan.queryByObjForCount", obj)).intValue()); // 分页查询的基本信息
																					// }
		}
		list = getSqlMapClientTemplate().queryForList("PhysicalVlan.queryForPhysicalVlanList", obj);
		return list;
	}

	/**
	 * @throws Exception
	 * 
	 * @Title: getPhysicalVlanList
	 * @Description: 获取一条记录
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlanObj(PhysicalVlanObj obj) throws Exception {
		PhysicalVlanObj result = null;
		List<PhysicalVlanObj> list = queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: insertByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#insertByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public int insertByObj(PhysicalVlanObj obj) throws Exception {
		return (Integer) getSqlMapClientTemplate().update("PhysicalVlan.insertByObj", obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deleteByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#deleteByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public int deleteByObj(PhysicalVlanObj obj) throws Exception {
		return getSqlMapClientTemplate().delete("PhysicalVlan.deleteByObj", obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#updateByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public int updateByObj(PhysicalVlanObj obj) throws Exception {
		return getSqlMapClientTemplate().update("PhysicalVlan.updateByObj", obj);
	}

	/**
	 * 
	 * @Title: updateForBatch
	 * @Description: 批量更新
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-21 上午11:36:39
	 */
	public void updateForBatch(final List<PhysicalVlanObj> list) throws Exception {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<PhysicalVlanObj>() {

			@Override
			public PhysicalVlanObj doInSqlMapClient(SqlMapExecutor arg0) throws SQLException {
				arg0.startBatch();
				for (PhysicalVlanObj disk : list) {
					arg0.update("PhysicalVlan.updateByObj", disk);
				}
				arg0.executeBatch();
				return null;
			}
		});
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: setUserNull
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#setUserNull(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public int setUserNull(PhysicalVlanObj obj) throws Exception {
		return getSqlMapClientTemplate().update("PhysicalVlan.setUserNull", obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getPhysicalVlanByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#getPhysicalVlanByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public PhysicalVlanObj getPhysicalVlanByObj(PhysicalVlanObj obj) throws Exception {
		obj = (PhysicalVlanObj) getSqlMapClientTemplate().queryForObject(
				"PhysicalVlan.queryForPhysicalVlanObj", obj);
		return obj;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryNoAssignForListByObj
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @throws Exception
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#queryNoAssignForListByObj(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PhysicalVlanObj> queryNoAssignForListByObj(PhysicalVlanObj obj) throws Exception {
		List<PhysicalVlanObj> list = new ArrayList<PhysicalVlanObj>();
		list = getSqlMapClientTemplate().queryForList("PhysicalVlan.getUnAssignedVlanList", obj);
		return list;
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
	 * @throws SQLException
	 * @see com.sitech.basd.resource.dao.united.vlan.PhysicalVlanDao#queryForCount(com.sitech.basd.resource.domain.united.vlan.PhysicalVlanObj)
	 */
	@Override
	public Integer queryForCount(PhysicalVlanObj obj) throws SQLException {
		return (Integer) (getSqlMapClientTemplate().queryForObject(
				"PhysicalVlan.queryByObjForCount", obj));
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getANoAssignByObj
	 * </p>
	 * <p>
	 * Description: 获得一个随机没有被分配的物理Vlan
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see dao.vlan.PhysicalVlanDao#getANoAssignByObj(domain.vlan.PhysicalVlanObj)
	 */
	@Override
	public PhysicalVlanObj getANoAssignByObj(PhysicalVlanObj obj) throws SQLException {
		return (PhysicalVlanObj) getSqlMapClientTemplate().queryForObject(
				"PhysicalVlan.getANoAssignByObj", obj);
	}

}
