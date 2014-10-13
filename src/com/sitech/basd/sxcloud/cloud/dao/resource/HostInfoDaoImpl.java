package com.sitech.basd.sxcloud.cloud.dao.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;

public class HostInfoDaoImpl extends BaseDao implements HostInfoDao {
	@Autowired
	private ReourceInDomainUtil reourceInDomainUtil;

	/**
	 * 
	 * @Title: 获得主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryForListByObj(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = new ArrayList<TbCloud2HostInfoObj>();
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList(
					"HostInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 查询主机是否已经被分配
	 * 
	 * @Title: queryForListByObjByAllocated
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-1-23 下午8:17:14
	 */
	public List<TbCloud2HostInfoObj> queryForListByObjByAllocated(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = new ArrayList<TbCloud2HostInfoObj>();
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList(
					"HostInfo.queryForListByObjByAllocated", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryForListByObjByAllocated:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 删除主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("HostInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 添加主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			if (obj.getDomain() == null || "".equals(obj.getDomain())) {
				/*
				 * 用于自动同步数据使用
				 */
				obj.setDomain(reourceInDomainUtil.initResourceDomainBySessionAndConfig(obj
						.getConnectId()));
			}
			Object o = getSqlMap().insert("HostInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 修改主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateInterByObj
	 * @Description: 数据对比时，若不同，则更新数据库
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 12, 2012 1:35:17 PM
	 */
	public int updateInterByObj(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateByInterObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateByInterObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 对于修改、删除添加历史数据
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int insertHostHis(TbCloud2HostHisObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("HostInfo.insertHostHis", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.insertHostHis:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 查询主机历史信息
	 * @Copyright: Copyright (c) 2011-12-15
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostHisObj> queryHisForListByObj(TbCloud2HostHisObj obj) {
		List<TbCloud2HostHisObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.queryHisForListByObjForCount", obj)).intValue());
			}
			lst = (List<TbCloud2HostHisObj>) getSqlMap().queryForList(
					"HostInfo.queryHisForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryHisForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 通过机柜Id查询相应的主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryHostListById(String CQ_ID) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList(
					"HostInfo.queryHostListById", CQ_ID);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryHostListById:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostConfigObj> queryForConfigListByObj(TbCloud2HostConfigObj obj) {
		List<TbCloud2HostConfigObj> lst = new ArrayList<TbCloud2HostConfigObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("HostInfo.queryByConfigObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("HostInfo.queryForConfigListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryForConfigListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByConfigObj(TbCloud2HostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("HostInfo.insertByConfigObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.insertByConfigObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByConfigObj(TbCloud2HostConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateByConfigObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateByConfigObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbCloud2HostConfigObj queryByConfigObj(TbCloud2HostConfigObj obj) {
		List lst = null;
		TbCloud2HostConfigObj tempObj = null;
		lst = queryForConfigListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2HostConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByConfigObj(TbCloud2HostConfigObj obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().delete("HostInfo.deleteByConfigObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.deleteByConfigObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 获得已经监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryMonitorHost(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList("HostInfo.queryMonitorHost",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryMonitorHost:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 获得未监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryNotMonitorHost(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList(
					"HostInfo.queryNotMonitorHost", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryNotMonitorHost:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 根据类型查询主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryForListByType(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = null;
		try {
			lst = (List<TbCloud2HostInfoObj>) getSqlMap().queryForList(
					"HostInfo.queryForListByType", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryForListByType:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title:通过deviceId 删除主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteByDeviceId(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("HostInfo.deleteByDeviceId", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.deleteByDeviceId:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 6, 2012 10:05:14 AM
	 */
	public TbCloud2HostInfoObj queryByObj(TbCloud2HostInfoObj obj) {
		List lst = null;
		TbCloud2HostInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2HostInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询主机序列ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Sep 11, 2012 9:59:53 AM
	 */
	public int getIdSequence() {
		int sequence = 0;
		try {
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj();
			globalObj.setKEY("TB_CLOUD2_HOST_INFO_SEQUENCE");
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
				TbCloud2HostInfoObj hostobj = new TbCloud2HostInfoObj();
				while (hostobj != null) {
					hostobj.setId(sequence);
					hostobj = queryByObj(hostobj);
					if (hostobj == null) {
						break;
					} else {
						sequence += 1;
					}
					hostobj = new TbCloud2HostInfoObj();
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
	 * @Title: countvmNum
	 * @Description: 查询每个主机下虚拟机的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public int countvmNum(Map map) {
		int ret = 0;
		try {
			List list = getSqlMap().queryForList("HostInfo.countvmNum", map);
			if (list != null && list.size() > 0) {
				Map tempmap = (Map) list.get(0);
				Object o = tempmap.get("NUM");
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HostInfo.countvmNum:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryAllHost
	 * @Description: 查询所有主机列表（安徽移动竞标）
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2HostInfoObj> queryAllHost(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"HostInfo.queryAllHostForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("HostInfo.queryAllHost", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryAllHost:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: countStorageNum
	 * @Description: 查询每个主机下存储的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public int countStorageNum(Map map) {
		int ret = 0;
		try {
			List list = getSqlMap().queryForList("HostInfo.countStorageNum", map);
			if (list != null && list.size() > 0) {
				Map countMap = (Map) list.get(0);
				Object o = countMap.get("NUM");
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.error("HostInfo.countStorageNum:" + e.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForIdByIp
	 * @Description: 根据主机Ip查询主机Id
	 * @param
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 2, 2013 2:53:20 PM
	 */
	public TbCloud2HostInfoObj queryForIdByIp(TbCloud2HostInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("HostInfo.queryForIdByIp", obj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryForIdByIp:" + e.getMessage() + getClass().getName());
		}
		TbCloud2HostInfoObj hostInfoObj = null;
		if (lst != null && lst.size() > 0) {
			hostInfoObj = (TbCloud2HostInfoObj) lst.get(0);
		}
		return hostInfoObj;
	}

	/**
	 * 
	 * @Title: 查询不在主机池中的主机
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List hostNotInPool(TbCloud2HostInfoObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap()
								.queryForObject("HostInfo.hostNotInPoolForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("HostInfo.hostNotInPool", obj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.hostNotInPool:" + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: 
	 *         更改主机的主机池ID，先删除主机的池id,再添加；要删除的主机池ID为map参数中的'page'属性，对应的value为数组，不传则
	 *         更 改 所 有
	 * @param map
	 *            ,key为主机池id，value为要更改的主机数组；map.put(pool_id,String[] changid);
	 *            map.put("page", String[]ids);
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updatePoolId(TbCloud2HostInfoObj obj) {
		int ret = 0;
		SqlMapClient smc = this.getSqlMap();
		String flag = obj.getFlag();

		try {
			smc.startTransaction();
			if (flag.equals("1")) {
				// 删除主机池和主机的关联关系
				smc.delete("HostInfo.delPoolRelation", obj);
			} else {
				smc.startBatch();
				// 再行添加新选中的主机和主机池的关系
				String hostids = obj.getHOST_IDS();
				String[] hostid = hostids.split(",");
				for (String host_id : hostid) {
					obj.setId(Integer.parseInt(host_id));
					getSqlMap().insert("HostInfo.insertPoolRelation", obj);
				}
				smc.executeBatch();
			}
			ret = 1;
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("HostInfo.insertPoolRelation:" + e.getMessage() + getClass().getName());
		} finally {
			try {
				smc.commitTransaction();
				smc.endTransaction();
			} catch (SQLException e) {
				LogHelper.error("HostInfo.insertPoolRelation:" + e.getMessage()
						+ getClass().getName());
			}
		}

		return ret;
	}

	/*
	 * <p>Title: queryHostListUseIn</p> <p>Description: 使用in语句查询出符合条件的主机集合
	 * 
	 * @param hostObj
	 * 
	 * @return
	 * 
	 * @see
	 * com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao#queryHostListUseIn
	 * (com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryHostListUseIn(TbCloud2HostInfoObj hostObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("HostInfo.queryHostListUseIn", hostObj);
		} catch (Exception e) {
			LogHelper
					.error("HostInfo.queryHostListUseIn: " + e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByObjNot
	 * @Description: 查询主键不在此集合中的记录
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午2:42:55
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryForListByObjNot(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMapClient().queryForList("HostInfo.queryForListByObjNot", hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryForListByObjNot: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: updateUnitedDataByObj
	 * @Description: 更新统一树数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-4 下午3:22:54
	 */
	public int updateUnitedDataByObj(TbCloud2HostInfoObj hostInfoObj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateUnitedDataByObj", hostInfoObj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateUnitedDataByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: countResource
	 * @Description: 统计cpu、内存、存储等信息
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-9 下午11:11:36
	 */
	@Override
	public CMSObj countResource(TbCloud2HostInfoObj hostObj) {
		hostObj.setModulus(this.getModulus());
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("HostInfo.countResource", hostObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostInfo.countResource : " + e.getMessage() + e.getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * 
	 * @Title: 修改时查询IP和和名字是否重复
	 * @Copyright: 2013-09-14
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public int validateNameAndIP(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().queryForObject("HostInfo.validateNameAndIP", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper
					.error("HostInfo.validateNameAndIP:" + e.getMessage() + e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: countResourceByEqid
	 * @Description: 通过eq_id查询
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 下午9:54:45
	 */
	@Override
	public CMSObj countResourceByEqid(TbCloud2HostInfoObj hostObj) {
		hostObj.setModulus(this.getModulus());
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("HostInfo.countResourceByEqid", hostObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostInfo.countResourceByEqid: " + e.getMessage()
					+ e.getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * @Title: queryHostListUseInByEqid
	 * @Description: 通过eq_id查询
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 下午10:02:03
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryHostListUseInByEqid(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("HostInfo.queryHostListUseInByEqid", hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryHostListUseInByEqid: " + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryHostListByAllocatedSerious
	 * @Description: 根据分配类型查询异常主机列表
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午4:55:08
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryHostListByAllocatedSerious(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (hostInfoObj.getPagination() != null) {
				hostInfoObj.setFIRSTROWNUM(hostInfoObj.getPagination().getFirstRownum());
				hostInfoObj.setPAGESIZE(hostInfoObj.getPagination().getPageSize());
				hostInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.countHostListByAllocatedSerious", hostInfoObj))
								.intValue());
			}
			list = getSqlMap()
					.queryForList("HostInfo.queryHostListByAllocatedSerious", hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryHostListByAllocatedSerious: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> querySeriousHostListUseIn(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("HostInfo.querySeriousHostListUseIn", hostInfoObj);
		} catch (Exception e) {
			logger.error("HostInfo.querySeriousHostListUseIn: " + e.getMessage() + e.getClass());
		}
		return list;
	}

	/**
	 * @Title: queryHostListByUnAllocatedSerious
	 * @Description: 查询未分配异常主机列表
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午4:55:08
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryHostListByUnAllocatedSerious(
			TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (hostInfoObj.getPagination() != null) {
				hostInfoObj.setFIRSTROWNUM(hostInfoObj.getPagination().getFirstRownum());
				hostInfoObj.setPAGESIZE(hostInfoObj.getPagination().getPageSize());
				hostInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.countHostListByUnAllocatedSerious", hostInfoObj))
								.intValue());
			}
			list = getSqlMap().queryForList("HostInfo.queryHostListByUnAllocatedSerious",
					hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryHostListByUnAllocatedSerious: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: querySeriousHostListUseInUnAllo
	 * @Description: 根据主键集合查询未分配的异常主机列表
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午4:55:08
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> querySeriousHostListUseInUnAllo(TbCloud2HostInfoObj thObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("HostInfo.querySeriousHostListUseInUnAllo", thObj);
		} catch (Exception e) {
			logger.error("HostInfo.querySeriousHostListUseInUnAllo: " + e.getMessage()
					+ e.getClass());
		}
		return list;
	}

	/**
	 * 
	 * @Title: insertIbmPowerMachine
	 * @Description: 插入IBMpower主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午6:47:47
	 */
	public int insertIbmPowerMachine(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("HostInfo.insertIbmPowerMachine", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.insertIbmPowerMachine:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateIbmPowerMachine
	 * @Description: 更新IBMpower主机信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午6:47:47
	 */
	public int updateIbmPowerMachine(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateIbmPowerMachine", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateIbmPowerMachine:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	private int getModulus() {
		int modulus = 1;
		String modulusStr = PropertiesUtil.getString("properties/modulus", "modulus");
		if (modulusStr != null) {
			modulus = Integer.parseInt(modulusStr);
		}
		return modulus;
	}

	/**
	 * @Title: queryForStoreDeviceList
	 * @Description: 查询主机所属存储设备
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-20 下午1:13:20
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryForStoreDeviceList(TbCloud2HostInfoObj infoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = sqlMapClient.queryForList("HostInfo.queryForStoreDeviceList", infoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryForStoreDeviceList: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: countStoreForUnAllo
	 * @Description: 查询为分配主机的存储
	 * @param
	 * @return double
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-25 下午2:18:30
	 */
	@Override
	public double countStoreForUnAllo(TbCloud2HostInfoObj hostInfoObj) {
		double ret = 0.0;
		try {
			Object obj = sqlMapClient.queryForObject("HostInfo.countStoreForUnAllo", hostInfoObj);
			if (obj != null) {
				ret = (Double) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostInfo.countStoreForUnAllo: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForDistribute
	 * @Description: 查询分布式主机的存储
	 * @param
	 * @return Map<String,Double>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-28 上午10:13:07
	 */
	@Override
	public TbCloud2HostInfoObj queryForDistribute(TbCloud2HostInfoObj tbCloud2HostInfoObj) {
		TbCloud2HostInfoObj cloud2HostInfoObj = new TbCloud2HostInfoObj();
		try {
			Object obj = sqlMapClient.queryForObject("HostInfo.queryForDistribute",
					tbCloud2HostInfoObj);
			if (obj != null) {
				cloud2HostInfoObj = (TbCloud2HostInfoObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryForDistribute: " + e.getMessage()
					+ e.getClass().getName());
		}
		return cloud2HostInfoObj;
	}

	/**
	 * @Title: queryForListForCluster
	 * @Description: 查询集群下的主机
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-19 下午1:09:15
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbCloud2HostInfoObj> queryForListForCluster(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			list = getSqlMap().queryForList("HostInfo.queryForListForCluster", hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryForListForCluster: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryHostListThroughCluster
	 * @Description: 根据集群查主机
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @Date 2014-8-2 下午7:24:46
	 * @author lipp
	 * @param hostInfoObj
	 * @return
	 */
	@Override
	public List<TbCloud2HostInfoObj> queryHostListThroughCluster(TbCloud2HostInfoObj hostInfoObj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (hostInfoObj.getPagination() != null) {
				hostInfoObj.setFIRSTROWNUM(hostInfoObj.getPagination().getFirstRownum());
				hostInfoObj.setPAGESIZE(hostInfoObj.getPagination().getPageSize());
				hostInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.countHostListThroughCluster", hostInfoObj)).intValue());
			}
			list = getSqlMap().queryForList("HostInfo.queryHostListThroughCluster", hostInfoObj);
		} catch (Exception e) {
			LogHelper.error("HostInfo.queryHostListThroughCluster: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	public int updateHostStatusByObj(TbCloud2HostInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("HostInfo.updateHostStatusByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("HostInfo.updateHostStatusByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryAllHostTYPE
	 * @Description: 查询可分配主机类型及配置
	 * @return int
	 * @author chenjlc
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryAllHostType(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.queryAllHostTypeForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("HostInfo.queryAllHostType", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryAllHostType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * @Title: queryHostListForBusiManager
	 * @Description: 业务中心（视图），业务节点上添加物理主机查询
	 * 		选择界面，排除已选择过的物理主机。
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @date 2014年8月19日
	 */
	public List<TbCloud2HostInfoObj> queryHostListForBusiManager(TbCloud2HostInfoObj host){
		List<TbCloud2HostInfoObj> lst = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (host.getPagination() != null) {
				host.setFIRSTROWNUM(host.getPagination().getFirstRownum());
				host.setPAGESIZE(host.getPagination().getPageSize());
				host.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"HostInfo.queryHostListForBusiManagerCount", host))
								.intValue());
			}
			lst = getSqlMap().queryForList("HostInfo.queryHostListForBusiManager", host);
		} catch (Exception sqlexception) {
			LogHelper.error("HostInfo.queryHostListForBusiManager:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
