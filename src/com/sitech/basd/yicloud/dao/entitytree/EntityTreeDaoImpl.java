package com.sitech.basd.yicloud.dao.entitytree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;

/**
 * 
 * <p>
 * Title: EntityTreeDaoImpl
 * </p>
 * <p>
 * Description: ztree管理接口实现类
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
 * @createtime Apr 18, 2012 11:14:01 AM
 * 
 */
public class EntityTreeDaoImpl extends BaseDao implements EntityTreeDao {

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
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("Tree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public List<EntityTreeObj> queryVMForTree(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryVMForTree", obj);
		} catch (Exception e) {
			LogHelper.error("Tree.queryVMForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int insertTreeNode(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Tree.insertTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.insertTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public int updateTreeNode(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Tree.updateTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.updateTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;

	}

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
	public int updateNodeParentId(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Tree.updateNodeParentId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.updateNodeParentId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;

	}

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
	public int delTreeNode(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Tree.delTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.delTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public int delSubNode(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Tree.delSubNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.delSubNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public EntityTreeObj queryTreeNode(EntityTreeObj obj) {
		EntityTreeObj tObj = new EntityTreeObj();
		try {
			obj = (EntityTreeObj) getSqlMap().queryForObject(
					"Tree.queryTreeNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Tree.queryTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return obj;
	}

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
	public int queryHostCount(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("Tree.queryHostCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Tree.queryHostCount:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryHostCount
	 * @Description: 查询虚拟机的个数
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 17, 2012 4:31:28 PM
	 */
	public int queryVmCount(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("Tree.queryVmCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Tree.queryVmCount:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public int queryClusterCount(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.queryForObject("Tree.queryClusterCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Tree.queryClusterCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public int queryNetOrStoreCount(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("Tree.queryNetOrStoreCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("Tree.queryNetOrStoreCount:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public List<EntityConObj> queryEntityInfo(EntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryEntityInfo", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryEntityInfo:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public EntityConObj queryEntityInfoByObj(EntityConObj obj) {
		List lst = null;
		EntityConObj tempObj = new EntityConObj();
		lst = queryEntityInfo(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (EntityConObj) lst.get(0);
		}
		return tempObj;
	}

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
	public List<EntityTreeObj> queryForHostList(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryForHostList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryForHostList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public List<EntityConObj> queryEntityData(EntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryEntityData", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryEntityData:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int insertConObj(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Tree.insertConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("Tree.insertConObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public List queryForMigHostList(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryForMigHostList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryForMigHostList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public List querySolutionList(SolutionObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.querySolutionList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.querySolutionList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public int insertSolutionByObj(SolutionObj obj) {
		int ret = 0;
		try {
			int id = querySolutionIdSeq();
			// obj.setTEM_ID(String.valueOf(id));
			obj.setTEM_NAME(obj.getTEM_NAME() + String.valueOf(id));
			obj.setTEM_ID(String.valueOf(id));
			Object o = getSqlMap().insert("Tree.insertSolutionByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("Tree.insertSolutionByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: querySolutionIdSeq
	 * @Description: 向修复模板列表插入数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 9, 2012 6:52:59 PM
	 */
	public int querySolutionIdSeq() {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("Tree.querySolutionIdSeq");
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("Tree.querySolutionIdSeq:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public int deleteConObj(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Tree.deleteConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.deleteConObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	@Override
	public int updateEntityConInfo(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Tree.updateEntityConInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.updateEntityConInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public int updateConObj(EntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Tree.updateConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("Tree.updateConObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public List queryClusterList(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryClusterList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryClusterList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public EntityTreeObj queryForConn(EntityTreeObj obj) {
		EntityTreeObj en = new EntityTreeObj();
		try {
			en = (EntityTreeObj) getSqlMap().queryForObject(
					"Tree.queryForConn", obj);
		} catch (Exception e) {
			LogHelper.error("Tree.queryClusterList:" + e.getMessage()
					+ getClass().getName());
		}
		return en;
	}

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
	public List queryForXenHost(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryForXenHost", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryForXenHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int delTreeNodeByEntity(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Tree.delTreeNodeByEntity", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Tree.delTreeNodeByEntity:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

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
	public int updateTreeNodeByUuid(EntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Tree.updateTreeNodeByUuid", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("Tree.updateTreeNodeByUuid:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryDcClHostVMRelat
	 * @Description: 查询VM关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostVMRelat(
			Map<String, Object> map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryDcClHostVMRelat", map);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryDcClHostVMRelat:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryDcClHostVMRelat
	 * @Description: 查询主机关联关系
	 * @param
	 * @return List<Map<String,Object>>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 27, 2012 10:22:54 AM
	 */
	public List<Map<String, Object>> queryDcClHostRelat(Map<String, Object> map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryDcClHostRelat", map);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryDcClHostRelat:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

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
	public List<Map<String, Object>> queryDcClRelat(Map<String, Object> map) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryDcClRelat", map);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryDcClRelat:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public int deleteAllData() {
		int ret = 0;
		try {
			getSqlMap().startTransaction();
			getSqlMap().delete("Tree.deleteTbCloudEntityTree");
			getSqlMap().delete("Tree.deleteTbCloudEntityCon");
			getSqlMap().delete("Tree.deleteTbCloud2VmhostInfo");
			getSqlMap().delete("Tree.deleteTbYicloudDatastore");
			getSqlMap().delete("Tree.deleteTbCloud2HostInfo");
			getSqlMap().delete("Tree.deleteTbCloudClusterInfo");
			getSqlMap().delete("Tree.deleteTbCloudVSwitch");
			getSqlMap().delete("Tree.deleteTbCloudPortgroup");
			getSqlMap().delete("Tree.deleteTbCloudTempletTree");
			getSqlMap().delete("Tree.deleteTbCloudNicRelation");
			getSqlMap().delete("Tree.deleteTbCloudNic");
			getSqlMap().delete("Tree.deleteTbCloudVirtualnic");
			ret = 1;
			getSqlMap().commitTransaction();
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("Tree.updateTreeNodeByUuid:"
					+ sqlException.getMessage() + getClass().getName());
		} finally {
			try {
				getSqlMap().endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

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
	public List<Map<String, Integer>> queryHostClDcId(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryHostClDcId", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryHostClDcId:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryVMForAuth
	 * @Description: 查询树(供分配虚拟机权限使用)
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2012 11:21:24 AM
	 */
	public List queryVMForAuth(EntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Tree.queryVMForAuth", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Tree.queryVMForAuth:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: searchNodes
	 * @Description: 搜索名称或ip地址匹配的主机或虚拟机
	 * @return String
	 * @author duangh
	 * @version 1.0
	 */
	public List searchNodes(Map map) {
		List list = null;
		try {
			list = getSqlMap().queryForList("Tree.searchNodes", map);
		} catch (Exception e) {
			LogHelper.error("Tree.searchNodes:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	public List getClusterFailHost(EntityTreeObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("Tree.getClusterFailHost", obj);
		} catch (Exception e) {
			LogHelper.error("Tree.getClusterFailHost:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
