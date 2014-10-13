package com.sitech.basd.yicloud.dao.vmman;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.vmman.EntityConObj;
import com.sitech.basd.yicloud.domain.vmman.EntityTreeObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudDeviceHealthObj;
import com.sitech.basd.yicloud.domain.vmman.TbYicloudOsTypeObj;
import com.sitech.basd.yicloud.domain.vmman.VMManagerObj;

/**
 * 
 * <p>
 * Title: VMManagerDaoImpl
 * </p>
 * <p>
 * Description: 虚拟机管理接口实现类
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
public class VMManagerDaoImpl extends BaseDao implements VMManagerDao {

	/**
	 * @Title:查询设备主机数据结果集
	 * @Copyright: Copyright (c) 20120413
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public List queryForListByObj(VMManagerObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("VMManager.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:添加设备主机
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int insertByObj(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VMManager.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询一条信息
	 * @Copyright: Copyright (c) 20120416
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public VMManagerObj queryByObj(VMManagerObj obj) {
		List lst = null;
		VMManagerObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (VMManagerObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * @Title:修改设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int updateByObj(VMManagerObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().update("VMManager.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;

	}

	/**
	 * @Title:根据ID删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByObj(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VMManager.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据英文名称删除设备主机信息
	 * @Copyright: Copyright (c) 20120417
	 * @Company: si-tech
	 * @author lzh
	 * @version 1.0
	 */
	public int deleteByName_En(VMManagerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VMManager.deleteByName_En", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.deleteByName_En:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryHealthStateList
	 * @Description: 查询主机或虚拟机健康状态信息
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 21, 2012 10:34:05 AM
	 */
	public List queryHealthStateList(TbYicloudDeviceHealthObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("VMManager.queryHealthStateList",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryHealthStateList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryDefaultConfig
	 * @Description: 查询各个操作系统的默认配置值
	 * @param
	 * @return TbYicloudOsTypeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 10, 2012 1:14:31 PM
	 */
	public TbYicloudOsTypeObj queryDefaultConfig(TbYicloudOsTypeObj obj) {
		TbYicloudOsTypeObj tObj = new TbYicloudOsTypeObj();
		try {
			obj = (TbYicloudOsTypeObj) getSqlMap().queryForObject(
					"VMManager.queryDefaultConfig", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryDefaultConfig:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

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
			lst = getSqlMap().queryForList("VMManager.queryForTree", obj);
		} catch (Exception e) {
			LogHelper.error("VMManager.queryForTree:" + e.getMessage()
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
			Object o = getSqlMap().insert("VMManager.insertTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.insertTreeNode:"
					+ sqlexception.getMessage() + getClass().getName());
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
			Object o = getSqlMap().update("VMManager.updateTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.updateTreeNode:"
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
			Object o = getSqlMap().delete("VMManager.delTreeNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.delTreeNode:"
					+ sqlexception.getMessage() + getClass().getName());
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
			Object o = getSqlMap().delete("VMManager.delSubNode", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VMManager.delSubNode:" + sqlexception.getMessage()
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
					"VMManager.queryTreeNode", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryTreeNode:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

	/**
	* 
	* @Title: getIdSequence
	* @Description: 查询device表的ID
	* @param
	* @return String
	* @throws
	* @author taoxue
	* @version 1.0
	* @createtime Jul 18, 2012 9:44:10 AM
	*/
	public String getIdSequence() {
		try {

			return (String) getSqlMap().queryForObject(
					"VMManager.getIdSequence");

		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
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
			Object o = getSqlMap().queryForObject("VMManager.queryHostCount",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryHostCount:"
					+ sqlexception.getMessage() + getClass().getName());
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
			Object o = getSqlMap()
					.queryForObject("VMManager.queryVmCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryVmCount:"
					+ sqlexception.getMessage() + getClass().getName());
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
			Object o = getSqlMap().queryForObject(
					"VMManager.queryClusterCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryClusterCount:"
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
			Object o = getSqlMap().queryForObject(
					"VMManager.queryNetOrStoreCount", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("VMManager.queryNetOrStoreCount:"
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
			lst = getSqlMap().queryForList("VMManager.queryEntityInfo", obj);
		} catch (Exception sqlException) {
			LogHelper.error("VMManager.queryEntityInfo:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}
}
