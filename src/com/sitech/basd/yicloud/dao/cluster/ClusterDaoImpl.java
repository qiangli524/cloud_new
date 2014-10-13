package com.sitech.basd.yicloud.dao.cluster;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.cluster.ClusterObj;

public class ClusterDaoImpl extends BaseDao implements ClusterDao {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("cluster.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public List queryListByObj(ClusterObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("cluster.queryListByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("cluster.queryListByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:查询单个集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public ClusterObj queryByObj(ClusterObj obj) {
		ClusterObj tempObj = new ClusterObj();
		List list = queryListByObj(obj);
		if (list != null && list.size() > 0) {
			tempObj = (ClusterObj) list.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更改集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int updateByObj(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("cluster.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObjUuid
	 * @Description:通过Uuid更新数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 26, 2012 11:35:08 AM
	 */
	public int updateByObjUuid(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("cluster.updateByObjUuid", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.updateByObjUuid:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int deleteByObj(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("cluster.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return ClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public ClusterObj queryForCollection(ClusterObj obj) {
		ClusterObj c = new ClusterObj();
		try {
			c = (ClusterObj) getSqlMap().queryForObject(
					"cluster.queryForCollection", obj);
		} catch (Exception sqlException) {
			LogHelper.error("cluster.queryForCollection:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return c;
	}

	/**
	 * 
	 * @Title: queryForConn
	 * @Description: 插入集群信息（xen）
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:50:41 AM
	 */
	public int insertForConnection(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("cluster.insertForConnection", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("cluster.insertForConnection:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 获取集群ID序列
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 22, 2012 3:21:14 PM
	 */
	public int getIdSequence() {
		try {

			return (Integer) getSqlMap()
					.queryForObject("cluster.getIdSequence");
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
	}

	/**
	 * 
	 * @Title: updateForConnection
	 * @Description:更新集群的信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 23, 2012 11:34:20 AM
	 */
	public int updateForConnection(ClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("cluster.updateForConnection", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.updateForConnection:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForGroupId
	 * @Description: 生成一个DRS组编号
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:39:16 PM
	 */
	public int queryForGroupId() {
		int c = 0;
		try {
			c = (Integer) getSqlMap().queryForObject("cluster.queryForGroupId");
		} catch (Exception sqlException) {
			LogHelper.error("cluster.queryForGroupId:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return c;
	}

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroup(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("cluster.addDRSGroup", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("cluster.addDRSGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: addDRSGroup
	 * @Description: 增加一个DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 20, 2013 4:33:29 PM
	 */
	public int addDRSGroupDetail(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("cluster.addDRSGroupDetail", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("cluster.addDRSGroupDetail:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryGroupList
	 * @Description: 查询DRS列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 21, 2013 10:50:04 AM
	 */
	public List queryGroupList(ClusterObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"cluster.queryGroupCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("cluster.queryGroupList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("cluster.queryGroupList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteGroup
	 * @Description: 删除DRS组
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:28 PM
	 */
	public int deleteGroup(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("cluster.deleteGroup", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.deleteGroup:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteGroupDetail
	 * @Description: 删除DRS成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Mar 25, 2013 4:04:42 PM
	 */
	public int deleteGroupDetail(Map map) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("cluster.deleteGroupDetail", map);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("cluster.deleteGroupDetail:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteAllGroup
	 * @Description: 删除所有的组（用于同步数据）
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 2:39:27 PM
	 */
	public int deleteAllGroup() {
		int ret = 0;
		try {
			getSqlMap().startTransaction();
			getSqlMap().delete("cluster.deleteAllGroup");
			getSqlMap().delete("cluster.deleteAllGroupDetail");
			ret = 1;
			getSqlMap().commitTransaction();
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("cluster.deleteAllGroup:"
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
	 * @Title: listGroupMember
	 * @Description: 查询组成员
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 2, 2013 3:49:00 PM
	 */
	public List listGroupMember(Map map) {
		List list = null;
		try {
			list = getSqlMap().queryForList("cluster.listGroupMember", map);
		} catch (Exception sqlException) {
			LogHelper.error("cluster.listGroupMember:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}
}
