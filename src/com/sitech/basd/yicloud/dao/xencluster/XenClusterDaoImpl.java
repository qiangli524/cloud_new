package com.sitech.basd.yicloud.dao.xencluster;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.xentree.XenClusterObj;
import com.sitech.utils.randomid.RandomUUID;

public class XenClusterDaoImpl extends BaseDao implements XenClusterDao {
	/**
	 * @Title:保存集群信息
	 * @author：duangh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:29:27 PM
	 * @version 1.0
	 */
	public int insertByObj(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("xenCluster.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("xenCluster.insertByObj:" + sqlexception.getMessage()
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
	public List queryListByObj(XenClusterObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("xenCluster.queryListByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("xenCluster.queryListByObj:" + sqlException.getMessage()
					+ getClass().getName());
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
	public XenClusterObj queryByObj(XenClusterObj obj) {
		XenClusterObj tempObj = null;
		List list = queryListByObj(obj);
		if (list != null && list.size() > 0) {
			tempObj = (XenClusterObj) list.get(0);
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
	public int updateByObj(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("xenCluster.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("xenCluster.updateByObj:" + sqlexception.getMessage()
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
	public int updateByObjUuid(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("xenCluster.updateByObjUuid", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("xenCluster.updateByObjUuid:" + sqlexception.getMessage()
					+ getClass().getName());
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
	public int deleteByObj(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("xenCluster.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("xenCluster.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForCollection
	 * @Description: 查询集群的uuid等信息
	 * @param
	 * @return XenClusterObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Oct 19, 2012 9:10:06 AM
	 */
	public XenClusterObj queryForCollection(XenClusterObj obj) {
		XenClusterObj c = new XenClusterObj();
		try {
			c = (XenClusterObj) getSqlMap().queryForObject("xenCluster.queryForCollection", obj);
		} catch (Exception sqlException) {
			LogHelper.error("xenCluster.queryForCollection:" + sqlException.getMessage()
					+ getClass().getName());
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
	public int insertForConnection(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("xenCluster.insertForConnection", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("xenCluster.insertForConnection:" + e.getMessage()
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
		// try {
		//
		// return (Integer) getSqlMap()
		// .queryForObject("xenCluster.getIdSequence");
		// } catch (Exception sqlexception) {
		// throw new DaoException("Error finding getTaskidSequence. Cause: "
		// + sqlexception);
		// }
		int sequence = 0;
		try {
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj();
			globalObj.setKEY("TB_CLOUD2_CLUSTER_SEQUENCE");
			Object obj = getSqlMap().queryForObject("TbGlobalConfig.queryForObjByObj", globalObj);
			TbGlobalConfigObj reObj = null;
			if (obj != null) {
				reObj = (TbGlobalConfigObj) obj;
				sequence = Integer.parseInt(reObj.getVALUE());
			}
			if (obj == null) {
				String id = RandomUUID.getUuid();
				globalObj.setID(id);
				globalObj.setVALUE("2");
				getSqlMap().insert("TbGlobalConfig.insertByObj", globalObj);
				sequence = 1;
			} else if (sequence >= 999999) {
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				getSqlMap().update("TbGlobalConfig.updateByObj", globalObj);
				sequence = 1;
			} else {
				XenClusterObj clusterObj = new XenClusterObj();
				while (clusterObj != null) {
					clusterObj.setId(sequence);
					clusterObj = queryByObj(clusterObj);
					if (clusterObj == null) {
						break;
					} else {
						sequence += 1;
					}
					clusterObj = new XenClusterObj();
				}

				globalObj.setVALUE((sequence + 1) + "");
				globalObj.setID(reObj.getID());
				getSqlMap().update("TbGlobalConfig.updateByObj", globalObj);
			}
		} catch (Exception e) {
			LogHelper.error("TbGlobalConfig.operate:" + e.getMessage() + getClass().getName());
		}
		return sequence;
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
	public int updateForConnection(XenClusterObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("xenCluster.updateForConnection", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("xenCluster.updateForConnection:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
