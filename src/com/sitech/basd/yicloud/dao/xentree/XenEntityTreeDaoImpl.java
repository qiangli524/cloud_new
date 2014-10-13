package com.sitech.basd.yicloud.dao.xentree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.xentree.XenEntityConObj;
import com.sitech.basd.yicloud.domain.xentree.XenEntityTreeObj;
import com.sitech.basd.yicloud.domain.xentree.XenTreeObj;
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
public class XenEntityTreeDaoImpl extends BaseDao implements XenEntityTreeDao {

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
	public List<XenEntityTreeObj> queryForTree(XenEntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("XenTree.queryForTree:" + e.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

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
	public List<XenEntityTreeObj> queryVMForTree(XenEntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryVMForTree", obj);
		} catch (Exception e) {
			LogHelper.error("XenTree.queryVMForTree:" + e.getMessage()
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
	public int insertTreeNode(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("XenTree.insertTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.insertTreeNode:" + sqlexception.getMessage()
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
	public int updateTreeNode(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.updateTreeNode:" + sqlexception.getMessage()
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
	public int updateNodeParentId(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateNodeParentId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.updateNodeParentId:"
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
	public int delTreeNode(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("XenTree.delTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.delTreeNode:" + sqlexception.getMessage()
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
	public int delSubNode(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("XenTree.delSubNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.delSubNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

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
	public XenEntityTreeObj queryTreeNode(XenEntityTreeObj obj) {
		XenEntityTreeObj tObj = new XenEntityTreeObj();
		try {
			tObj = (XenEntityTreeObj) getSqlMap().queryForObject(
					"XenTree.queryTreeNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("XenTree.queryTreeNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return tObj;
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
	public int queryHostCount(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("XenTree.queryHostCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("XenTree.queryHostCount:" + sqlexception.getMessage()
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
	public int queryVmCount(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("XenTree.queryVmCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("XenTree.queryVmCount:" + sqlexception.getMessage()
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
	public int queryClusterCount(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.queryForObject("XenTree.queryClusterCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("XenTree.queryClusterCount:"
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
	public int queryNetOrStoreCount(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject("XenTree.queryNetOrStoreCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("XenTree.queryNetOrStoreCount:"
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
	public List<XenEntityConObj> queryEntityInfo(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryEntityInfo", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryEntityInfo:" + sqlException.getMessage()
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
	public XenEntityConObj queryEntityInfoByObj(XenEntityConObj obj) {
		List lst = null;
		XenEntityConObj tempObj = new XenEntityConObj();
		lst = queryEntityInfo(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (XenEntityConObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * 
	 * @Title: queryForHostList
	 * @Description:获取同一集群下的主机列表
	 * @param
	 * @return List<XenXenXenEntityConObj>
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 26, 2012 10:40:37 AM
	 */
	public List<XenEntityConObj> queryForHostList(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryForHostList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryForHostList:"
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
	public List<XenEntityConObj> queryEntityData(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryEntityData", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryEntityData:" + sqlException.getMessage()
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
	public int insertConObj(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("XenTree.insertConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.insertConObj:" + sqlException.getMessage()
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
	public List queryForMigHostList(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryForMigHostList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryForMigHostList:"
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
			lst = getSqlMap().queryForList("XenTree.querySolutionList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.querySolutionList:"
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
			Object o = getSqlMap().insert("XenTree.insertSolutionByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("XenTree.insertSolutionByObj:"
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
			Object o = getSqlMap().queryForObject("XenTree.querySolutionIdSeq");
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("XenTree.querySolutionIdSeq:"
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
	public int deleteConObj(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("XenTree.deleteConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.deleteConObj:" + sqlexception.getMessage()
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
	public int updateEntityConInfo(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateEntityConInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.updateEntityConInfo:"
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
	public int updateConObj(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("XenTree.updateConObj:" + sqlException.getMessage()
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
	public List queryClusterList(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryClusterList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryClusterList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 查询当前节点对应的主机及集群的uuid,及虚拟机uuid
	 * @param
	 * @return XenXenXenEntityConObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public XenEntityTreeObj queryForConn(XenEntityTreeObj obj) {
		XenEntityTreeObj en = new XenEntityTreeObj();
		try {
			en = (XenEntityTreeObj) getSqlMap().queryForObject(
					"XenTree.queryForConn", obj);
		} catch (Exception e) {
			LogHelper.error("XenTree.queryForConn:" + e.getMessage()
					+ getClass().getName());
		}
		return en;
	}


	public List queryForXenHost(XenEntityConObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryForXenHost", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryForXenHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public List queryClusterList(XenEntityTreeObj obj) {
		return null;
	}

	@Override
	public List<XenEntityTreeObj> queryForHostList(XenEntityTreeObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryForMigHostList(XenEntityTreeObj obj) {
		// TODO Auto-generated method stub
		return null;
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
	@Override
	public List queryForXenHost(XenEntityTreeObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenTree.queryForXenHost", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenTree.queryForXenHost:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
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
	public XenEntityTreeObj querySpecVirtual(XenEntityTreeObj obj){
		XenEntityTreeObj en = new XenEntityTreeObj();
		try {
			en = (XenEntityTreeObj) getSqlMap().queryForObject(
					"XenTree.querySpecVirtual", obj);
		} catch (Exception e) {
			LogHelper.error("XenTree.querySpecVirtual:" + e.getMessage()
					+ getClass().getName());
		}
		return en;
	}

	

	/**
	 * 
	 * @Title: queryForHostUuidAndClusterUuid
	 * @Description: 根据主机的UUID查询主机的Uuid和集群的Uuid
	 * @param
	 * @return XenEntityTreeObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-16 下午01:35:45
	 */
	public XenEntityTreeObj queryForHostUuidAndClusterUuid(XenEntityTreeObj obj){
		XenEntityTreeObj en = new XenEntityTreeObj();
		try {
			en = (XenEntityTreeObj) getSqlMap().queryForObject(
					"XenTree.queryForHostUuidAndClusterUuidByHostEntityId", obj);
		} catch (Exception e) {
			LogHelper.error("XenTree.queryForHostUuidAndClusterUuidByHostEntityId:" + e.getMessage()
					+ getClass().getName());
		}
		return en;
	}
	
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
	public int updateTreeNodeMess(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateTreeNodeMess", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.updateTreeNodeMess:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;

	}
	
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
	public int updateTreeNodeName(XenEntityTreeObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateTreeNodeName", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("XenTree.updateTreeNodeName:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;

	}
	
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
	public int updateConRelation(XenEntityConObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenTree.updateConRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("XenTree.updateConRelation:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
}
