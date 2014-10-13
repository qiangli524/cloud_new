package com.sitech.basd.resource.dao.united;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;

public interface UnitedTreeDao {
	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询统一树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTreeObj> queryForUnitedTree(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询单个节点
	 * @param
	 * @return UnitedTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 2, 2013 4:40:43 PM
	 */
	public UnitedTreeObj queryByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:15 PM
	 */
	public String insertByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:26:47 PM
	 */
	public int updateByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 2:27:11 PM
	 */
	public int deleteByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: getAllUniqueEntity
	 * @Description:获取所有唯一实体（用于数据比对）
	 * @param
	 * @return List<String>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 25, 2013 8:55:12 PM
	 */
	public List<String> getAllUniqueEntity(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: deleteByUuidAndConnectId
	 * @Description: 根据实体ID及连接ID删除对象
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-7-26 下午8:13:58
	 */
	public int deleteByUuidAndConnectId(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryForAbstract
	 * @Description: 统一树首页的摘要
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-7-26 下午8:13:58
	 */
	public List<Map<String, String>> queryForAbstract();

	/**
	 * @Title: queryForTreeListUseIn
	 * @Description: 查询出满足条件的实体集合
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 上午9:48:43
	 */
	public List<UnitedTreeObj> queryForTreeListUseIn(UnitedTreeObj unitedTreeObj);

	/**
	 * @Title: queryByName
	 * @Description: 是否存在相同名字的虚拟机
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-8-15
	 */
	public int queryName(UnitedTreeObj unitedTreeObj) throws SQLException;

	/**
	 * 
	 * @Title: queryForUnitedTree
	 * @Description: 查询权限树
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 17, 2013 11:51:37 AM
	 */
	public List<UnitedTreeObj> queryForAuthTree(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（虚拟机所在的主机在集群下,此种情况是正常情况）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForVMInHostAndCluster(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（虚拟机所在的主机在集群下同时存在地域结点,此种情况是正常情况）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-17 下午7:01:12
	 */
	public List getExpandNodesForVmHasLoaction(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVMInCluster
	 * @Description: 查询展开虚拟机所需节点集合（此情况适用于主机在集群下，不在主机下）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:25:38
	 */
	public List getExpandNodesForVMInCluster(UnitedTreeObj obj);

	/**
	 * 
	 * @Title: getExpandNodesForVMInHostAndDc
	 * @Description: 查询展开虚拟机所需节点集合（此情况适用于虚拟机所在的主机在数据中心下，不在集群下）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:25:38
	 */
	public List getExpandNodesForVMInHostAndDc(UnitedTreeObj obj);

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（主机在集群下）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHostInCluster(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVM
	 * @Description: 查询展开虚拟机所需节点集合（主机在集群下）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHosthasLocation(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: getExpandNodesForVMInCluster
	 * @Description: 查询展开主机所需节点集合(主机在数据中心下)
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午7:01:12
	 */
	public List getExpandNodesForHostInDc(UnitedTreeObj obj)
			throws SQLException;

	/**
	 * 
	 * @Title: updateOperateByObj
	 * @Description: 更新一条数据
	 * @param
	 * @return int
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-13
	 */
	public int updateOperateByObj(UnitedTreeObj obj) throws SQLException;

	/**
	 * 
	 * @Title: queryNetDomain
	 * @Description: 查询网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-14
	 */
	public List queryNetDomain();

	/**
	 * 
	 * @Title: querySubNet
	 * @Description: 查询子网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-14
	 */
	public List querySubNet(UnitedTreeObj obj);

	/**
	 * 
	 * @Title: updateObjById
	 * @Description: 通过id更新obj
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-26 下午4:39:27
	 */
	public int updateObjById(UnitedTreeObj obj);

	/**
	 * @Title: queryForListForWorkOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午3:49:40
	 */
	public List<UnitedTreeObj> queryForListForWorkOrder(
			UnitedTreeObj unitedTreeObj);

	/**
	 * @Title: queryForCenterTreeList
	 * @Description: 统计数据中心
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-3 下午6:52:09
	 */
	public List<UnitedTreeObj> queryForCenterTreeList(UnitedTreeObj unitedTreeObj);

	/**
	 * @Title: queryForListUnionParent
	 * @Description:查询出父节点信息
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 下午7:12:14
	 */
	public List<UnitedTreeObj> queryForListUnionParent(UnitedTreeObj unitedTreeObj);

	 /**  
	  * @Title: queryForListByObj  
	  * @Description: 查询列表
	  * @return List<UnitedTreeObj>   
	  * @throws  
	  * @Date 2014-4-23 下午12:36:20
	  * @author lipp
	  * @param treeObj
	  * @return
	  */
	public List<UnitedTreeObj> queryForListByObj(UnitedTreeObj treeObj);

}
