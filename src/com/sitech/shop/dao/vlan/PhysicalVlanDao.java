/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: PhysicalVlanDao.java 
 * @Package com.sitech.basd.resource.dao.united 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 上午11:14:49 
 * @version V1.0   
 */
package com.sitech.shop.dao.vlan;

import java.sql.SQLException;
import java.util.List;

import com.sitech.shop.domain.vlan.PhysicalVlanObj;

/**
 * @ClassName: PhysicalVlanDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 上午11:14:49
 * @version 1.0
 */
public interface PhysicalVlanDao {

	/**
	 * @throws Exception
	 * 
	 * @Title: queryForListByObj
	 * @Description: TODO(查询)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public List<PhysicalVlanObj> queryForListByObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * 
	 * @Title: getPhysicalVlanList
	 * @Description: 获取一条记录
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlanObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: queryNoAssignForListByObj
	 * @Description: TODO(查找地域下，没有被分配的Vlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public List<PhysicalVlanObj> queryNoAssignForListByObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: insertByObj
	 * @Description: TODO(新增)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int insertByObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: deleteByObj
	 * @Description: TODO(删除)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int deleteByObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: updateByObj
	 * @Description: TODO(更新)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int updateByObj(PhysicalVlanObj obj) throws Exception;

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
	public void updateForBatch(List<PhysicalVlanObj> list) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: setUserNull
	 * @Description: TODO(设置user_id = null)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public int setUserNull(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws Exception
	 * 
	 * @Title: getPhysicalVlanByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return PhysicalVlanObj 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlanByObj(PhysicalVlanObj obj) throws Exception;

	/**
	 * @throws SQLException
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(PhysicalVlanObj obj) throws SQLException;

	/**
	 * @throws SQLException
	 * 
	 * @Title: getANoAssignByObj
	 * @Description: TODO(获得一个随机没有被分配的物理Vlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return PhysicalVlanObj 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getANoAssignByObj(PhysicalVlanObj obj) throws SQLException;

}
