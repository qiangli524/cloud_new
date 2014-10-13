package com.sitech.basd.yicloud.dao.xentree;

import java.util.List;

import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
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
public interface XenEntityTreeDao {

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
	public List<XenEntityTreeObj> queryForTree(XenEntityTreeObj obj);

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 */
	public List<XenEntityTreeObj> queryVMForTree(XenEntityTreeObj obj);

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
	public int insertTreeNode(XenEntityTreeObj obj);

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
	public int updateTreeNode(XenEntityTreeObj obj);

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
	public int delTreeNode(XenEntityTreeObj obj);

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
	public int delSubNode(XenEntityTreeObj obj);

	/**
	 * 
	 * @Title: queryTreeNode
	 * @Description: 查询树节点的信息
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public XenEntityTreeObj queryTreeNode(XenEntityTreeObj obj);

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
	public int queryHostCount(XenEntityConObj obj);

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
	public int queryVmCount(XenEntityConObj obj);

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
	public int queryClusterCount(XenEntityTreeObj obj);

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
	public int queryNetOrStoreCount(XenEntityConObj obj);

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
	public List<XenEntityConObj> queryEntityInfo(XenEntityConObj obj);

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
	public XenEntityConObj queryEntityInfoByObj(XenEntityConObj obj);

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description:获取同一集群下的主机列表
	 * @param
	 * @return List<XenEntityTreeObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:40:37 AM
	 */
	public List<XenEntityTreeObj> queryForHostList(XenEntityTreeObj obj);

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
	public List<XenEntityConObj> queryEntityData(XenEntityConObj obj);

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
	public int insertConObj(XenEntityConObj obj);

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
	public List queryForMigHostList(XenEntityTreeObj obj);

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
	public int deleteConObj(XenEntityConObj obj);

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
	public int updateEntityConInfo(XenEntityConObj obj);

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
	public int updateConObj(XenEntityConObj obj);

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
	public List queryClusterList(XenEntityTreeObj obj);

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 查询当前节点对应的主机及集群的uuid,及虚拟机uuid
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public XenEntityTreeObj queryForConn(XenEntityTreeObj obj);

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
	public List queryForXenHost(XenEntityTreeObj obj);

	

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
	public int updateNodeParentId(XenEntityTreeObj obj);
	/**
	 * 
	 * @Title: 
	 * @Description: 查询挂在集群下的虚拟机的信息(uuid,及集群的uuid) 
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public XenEntityTreeObj querySpecVirtual(XenEntityTreeObj obj);
	

	/**
	 * 
	 * @Title: queryForHostUuidAndClusterUuid
	 * @Description: 根据主机的entityId查询主机的Uuid和集群的Uuid
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-16 下午01:35:45
	 */
	public XenEntityTreeObj queryForHostUuidAndClusterUuid(XenEntityTreeObj obj);
	
	/**
	 * 
	 * @Title: updateTreeNodeMess
	 * @Description: 更新数节点的所有信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 11:29:23 AM
	 */
	public int updateTreeNodeMess(XenEntityTreeObj obj);
	
	/**
	 * 
	 * @Title: updateTreeNodeName
	 * @Description: 更新树节点的名称
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Apr 9, 2013 5:59:30 PM
	 */
	public int updateTreeNodeName(XenEntityTreeObj obj);
	
	/**
	 * 
	 * @Title: updateConRelation
	 * @Description: 更新con表中关系
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 16, 2013 12:55:57 PM
	 */
	public int updateConRelation(XenEntityConObj obj);
}
