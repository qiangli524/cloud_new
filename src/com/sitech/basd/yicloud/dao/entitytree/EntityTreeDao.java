package com.sitech.basd.yicloud.dao.entitytree;

import java.util.List;
import java.util.Map;

import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;

/**
 * 
 * <p>
 * Title: EntityTreeDao
 * </p>
 * <p>
 * Description: ztree管理接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Apr 18, 2012 11:14:17 AM
 * 
 */
public interface EntityTreeDao {

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryVMForTree(EntityTreeObj obj);

	/**
	 * 
	 * @Title: insertTreeNode
	 * @Description: 向树中插入节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:07:09 AM
	 */
	public int insertTreeNode(EntityTreeObj obj);

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 修改树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int updateTreeNode(EntityTreeObj obj);

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 删除树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int delTreeNode(EntityTreeObj obj);

	/**
	 * 
	 * @Title: delSubNode
	 * @Description: 删除某一节点对应的子节点
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:28:29 AM
	 */
	public int delSubNode(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public EntityTreeObj queryTreeNode(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryHostCount
	 * @Description: 查询主机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryHostCount(EntityConObj obj);

	/**
	 * 
	 * @Title: queryVmCount
	 * @Description: 查询虚拟机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryVmCount(EntityConObj obj);

	/**
	 * 
	 * @Title: queryClusterCount
	 * @Description: 查询集群的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryClusterCount(EntityConObj obj);

	/**
	 * 
	 * @Title: queryNetOrStoreCount
	 * @Description: 查询网络或存储的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryNetOrStoreCount(EntityConObj obj);

	/**
	 * 
	 * @Title: queryEntityInfo
	 * @Description: 查询主机，虚拟机等实体信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public List<EntityConObj> queryEntityInfo(EntityConObj obj);

	/**
	 * 
	 * @Title: queryEntityInfoByObj
	 * @Description: 查询一条主机，虚拟机等实体信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public EntityConObj queryEntityInfoByObj(EntityConObj obj);

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description:获取同一集群下的主机列表
	 * @param
	 * @return List<EntityTreeObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:40:37 AM
	 */
	public List<EntityTreeObj> queryForHostList(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryEntityData
	 * @Description: 查询主机在哪个数据中心下
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public List<EntityConObj> queryEntityData(EntityConObj obj);

	/**
	 * 
	 * @Title: insertConObj
	 * @Description: 向TB_CLOUD_ENTITY_CON表中插入数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:07:09 AM
	 */
	public int insertConObj(EntityConObj obj);

	/**
	 * 
	 * @Title: queryForMigHostList
	 * @Description: 获取迁移主机
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 31, 2012 11:21:56 AM
	 */
	public List queryForMigHostList(EntityTreeObj obj);

	/**
	 * 
	 * @Title: querySolutionList
	 * @Description: 查询所有修复模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public List querySolutionList(SolutionObj obj);

	/**
	 * 
	 * @Title: insertSolutionByObj
	 * @Description: 向修复模板列表插入数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int insertSolutionByObj(SolutionObj obj);

	/**
	 * 
	 * @Title: deleteConObj
	 * @Description: 删除TB_CLOUD_ENTITY_CON表数据
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int deleteConObj(EntityConObj obj);

	/**
	 * 
	 * @Title: updateEntityConInfo
	 * @Description: 更新TB_CLOUD_ENTITY_CON数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Sep 18, 2012 1:46:00 PM
	 */
	public int updateEntityConInfo(EntityConObj obj);

	/**
	 * 
	 * @Title: deleteConObj
	 * @Description: 删除TB_CLOUD_ENTITY_CON表数据
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int updateConObj(EntityConObj obj);

	/**
	 * 
	 * @Title: queryClusterList
	 * @Description: 查询集群列表
	 * @param
	 * @return list
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 11:07:09 AM
	 */
	public List queryClusterList(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 查询当前节点对应的主机及集群的uuid,及虚拟机uuid
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public EntityTreeObj queryForConn(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryForXenHost
	 * @Description: 查询要迁移的主机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 11:45:48 AM
	 */
	public List queryForXenHost(EntityTreeObj obj);

	/**
	 * 
	 * @Title: delTreeNodeByEntity
	 * @Description: 通过实体ID删除节点
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Jul 18, 2012 10:28:29 AM
	 */
	public int delTreeNodeByEntity(EntityTreeObj obj);

	/**
	 * 
	 * @Title: updateTreeNodeByUuid
	 * @Description: 通过实体id更新树节点数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 5:45:21 PM
	 */
	public int updateTreeNodeByUuid(EntityTreeObj obj);

	/**
	 * 
	 * @Title: deleteAllData
	 * @Description:删除库中原有数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	public int deleteAllData();

	/**
	 * 
	 * @Title: queryDcClHostVMRelat
	 * @Description: 查询虚拟机关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostVMRelat(
			Map<String, Object> map);

	/**
	 * 
	 * @Title: queryDcClHostRelat
	 * @Description: 查询主机关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostRelat(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryDcClRelat
	 * @Description: 查询集群关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClRelat(Map<String, Object> map);

	/**
	 * 
	 * @Title: queryHostClDcId
	 * @Description:查询主机的节点Id和所在的集群和数据中心的id
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Otc 27, 2012 11:34:58 AM
	 */
	public List<Map<String, Integer>> queryHostClDcId(EntityTreeObj obj);

	/**
	 * 
	 * @Title: updateNodeParentId
	 * @Description: 只更新树的父节点id
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public int updateNodeParentId(EntityTreeObj obj);

	/**
	 * 
	 * @Title: queryVMForAuth
	 * @Description: 查询树(供设置虚拟机权限使用)
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public List queryVMForAuth(EntityTreeObj obj);

	/**
	 * 
	 * @Title: searchNodes
	 * @Description: 搜索名称或ip地址匹配的主机或虚拟机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public List searchNodes(Map map);

	public List getClusterFailHost(EntityTreeObj obj);
}
