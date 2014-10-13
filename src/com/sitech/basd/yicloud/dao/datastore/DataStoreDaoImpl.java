package com.sitech.basd.yicloud.dao.datastore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import util.DomainUtil;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfoObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

@Repository("dataStoreDao")
public class DataStoreDaoImpl extends BaseDao implements DataStoreDao {
	@Autowired
	private ReourceInDomainUtil reourceInDomainUtil;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询数据存储列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:46:15 AM
	 */
	public List queryForListByObj(DataStoreObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("DataStore.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DataStore.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForListByDateStoreInfoObj
	 * @Description: 查询数据存储列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public List queryForListByDateStoreInfoObj(DataStoreInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DataStore.queryByDataStoreInfoObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("DataStore.queryForListByDataStoreInfoObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryForListByDataStoreInfoObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertDatastore
	 * @Description: 向TB_YICLOUD_DATASTORE表插入信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int insertDatastore(DataStoreObj obj) {
		int ret = 0;
		if (obj.getDomain() == null || "".equals(obj.getDomain())) {
			/*
			 * 用于自动同步数据使用
			 */
			obj.setDomain(reourceInDomainUtil.initResourceDomainBySessionAndConfig(obj
					.getConnectId()));
		}
		try {
			Object o = getSqlMap().insert("DataStore.insertDatastore", obj);
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.insertDatastore:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新TB_YICLOUD_DATASTORE表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int updateByObj(DataStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DataStore.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.updateByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除TB_YICLOUD_DATASTORE表信息
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public int deleteByObj(DataStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DataStore.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryClusterds
	 * @Description: 查询集群下的存储
	 * @param
	 * @return List
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public List queryClusterds(DataStoreObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("DataStore.queryClusterds", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryClusterds:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryAllDataStore
	 * @Description: 查询所有不重复的存储
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 1, 2013 9:37:26 AM
	 */
	public List queryAllDataStore(DataStoreObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DataStore.queryAllDateStoreForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("DataStore.queryAllDateStore", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryAllDateStore:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForDateStoreList
	 * @Description: 查询存储
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 1:31:25 PM
	 */
	public List queryForDateStoreList(DataStoreObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("DataStore.queryForDateStoreList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryForDateStoreList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForCountByObj
	 * @Description: 查询存储关联的主机数
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 4:33:25 PM
	 */
	public int queryForConnectStoreHostCountByObj(DataStoreObj obj) {
		int num = 0;
		try {
			num = (Integer) getSqlMap().queryForObject("DataStore.queryForCountByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.queryForCountByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return num;
	}

	/**
	 * 
	 * @Title: queryForHostIdsList
	 * @Description: 根据存储的标示查询存储
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 6, 2013 4:09:11 PM
	 */
	public List queryForHostIdsList(DataStoreObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("DataStore.queryForHostIdsList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryForHostIdsList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryVmwareDataStoreAllAndFree
	 * @Description: 统计存储总量和余量信息
	 * @param
	 * @return Map<String,Double> key:CAPACITY FREESPACE
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-7 下午8:33:30
	 */
	public Map<String, Double> queryVmwareDataStoreAllAndFree(Map<String, Object> map) {
		Map<String, Double> result = null;
		try {
			DomainUtil.setDomainToMap(map);
			result = (Map<String, Double>) getSqlMap().queryForObject(
					"DataStore.queryVmwareDataStoreAllAndFree", map);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryVmwareDataStoreAllAndFree:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	@Override
	public Map<String, Double> queryXenDataStoreAllAndFree(Map<String, Object> map) {
		Map<String, Double> result = null;
		try {
			DomainUtil.setDomainToMap(map);
			result = (Map<String, Double>) getSqlMap().queryForObject(
					"DataStore.queryXenDSAllAndFree", map);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryXenDSAllAndFree:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	/**
	 * 
	 * @Title: getAllDatastoreEntity
	 * @Description: 查询数据存储列表，一般用于比对
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 上午9:27:00
	 */
	public List<String> getAllDatastoreEntity(DataStoreObj obj) {
		List<String> list = null;
		try {
			list = getSqlMap().queryForList("DataStore.getAllDatastoreEntity", obj);
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.getAllDatastoreEntity:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: updateDatastoreByObj
	 * @Description: 更新TB_YICLOUD_DATASTORE表信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 上午11:21:48
	 */
	public int updateDatastoreByObj(DataStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DataStore.updateDatastoreByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.updateDatastoreByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByUuid
	 * @Description: 根据连接Code(vCenterCode,Xen UUID)及存储Code删除数据
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-31 上午11:33:34
	 */
	public int deleteByUuid(DataStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DataStore.deleteByUuid", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.deleteByUuid:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryBatchForBestHost
	 * @Description: 构造获取最优主机的参数
	 * @param
	 * @return Map<String,List<JSONObject>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-8-26 上午8:51:19
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<JSONObject>> queryBatchForBestHost(List<TbCloud2HostInfoObj> hostList,
			WorkOrderObj wobj) throws Exception {
		SqlMapClient smc = this.getSqlMapClient();
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		try {
			smc.startTransaction();
			smc.startBatch();
			List<JSONObject> paramList = new ArrayList<JSONObject>();
			for (TbCloud2HostInfoObj obj : hostList) {
				JSONObject json = new JSONObject();
				json.put("hostUuid", obj.getH_uuid());

				int allVCPU = obj.getMaxVcpu();
				int usedVCPU = obj.getUsedVcpu();
				json.put("leftVCPU", allVCPU - usedVCPU);

				int allMem = Integer.parseInt(obj.getMem() == null ? "0" : obj.getMem());
				int usedMem = obj.getUsedMemMb() == null ? 0 : obj.getUsedMemMb();
				json.put("leftMem", allMem - usedMem);

				DataStoreObj tbYicloudDatastore = new DataStoreObj();
				tbYicloudDatastore.setHOST_ID(obj.getH_uuid());
				tbYicloudDatastore.setConnectId(obj.getConnectId());
				List<DataStoreObj> list = getSqlMapClient().queryForList("DataStore.queryByObj",
						tbYicloudDatastore);
				if (list != null && list.size() > 0) {
					Double freesize = 0.0;
					for (DataStoreObj dataobj : list) {
						if (Double.parseDouble(dataobj.getFREE_SPACE()) > freesize) {
							freesize = Double.parseDouble(dataobj.getFREE_SPACE() == null ? "0.0"
									: dataobj.getFREE_SPACE());
							json.put("storeUuid", dataobj.getStore_uuid());
							json.put("leftSR_Size", freesize.intValue());
						}
					}
					paramList.add(json);
				}
			}
			map.put(wobj.getUUID(), paramList);
			smc.executeBatch();
		} catch (Exception e) {
			logger.error("DataStore.queryByObj: " + e.getMessage(), e);
			throw new Exception(e);
		} finally {
			try {
				smc.commitTransaction();
				smc.endTransaction();
			} catch (SQLException e) {
				logger.error("DataStore.queryByObj: " + e.getMessage(), e);
			}
		}
		return map;
	}

	/**
	 * @Title: countResource
	 * @Description: 统计cpu、内存、存储等信息
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午12:01:09
	 */
	@Override
	public CMSObj countResource(DataStoreObj dataStoreObj) {
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("DataStore.countResource", dataStoreObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("DataStore.countResource: " + e.getMessage() + e.getClass().getName());
		}
		return cmsObj;
	}

	/**
	 * 
	 * @Title: queryNoRelevanceStoreDevice
	 * @Description: 查询未关联的存储块
	 * @param
	 * @return List<DataStoreObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 上午11:54:00
	 */
	public List<DataStoreObj> queryNoRelevanceStoreDevice(DataStoreObj dataStoreObj) {
		List<DataStoreObj> lst = null;
		try {
			if (dataStoreObj.getPagination() != null) {
				dataStoreObj.setFIRSTROWNUM(dataStoreObj.getPagination().getFirstRownum());
				dataStoreObj.setPAGESIZE(dataStoreObj.getPagination().getPageSize());
				dataStoreObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DataStore.queryNoRelevanceStoreDeviceForCount", dataStoreObj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DataStore.queryNoRelevanceStoreDevice", dataStoreObj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryNoRelevanceStoreDevice:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: relevanceDataStore
	 * @Description: 关联存储块
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 下午12:51:43
	 */
	public int relevanceDataStore(DataStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DataStore.relevanceDataStore", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("DataStore.relevanceDataStore:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryRelevanceStoreDevice
	 * @Description: 查询已关联的存储
	 * @param
	 * @return List<DataStoreObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 下午1:10:18
	 */
	public List<DataStoreObj> queryRelevanceStoreDevice(DataStoreObj dataStoreObj) {
		List<DataStoreObj> lst = null;
		try {
			if (dataStoreObj.getPagination() != null) {
				dataStoreObj.setFIRSTROWNUM(dataStoreObj.getPagination().getFirstRownum());
				dataStoreObj.setPAGESIZE(dataStoreObj.getPagination().getPageSize());
				dataStoreObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DataStore.queryRelevanceStoreDeviceForCount", dataStoreObj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DataStore.queryRelevanceStoreDevice", dataStoreObj);
		} catch (Exception sqlexception) {
			LogHelper.error("DataStore.queryRelevanceStoreDevice:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: queryForListByDeviceId
	 * @Description: 根据存储设备类型查询存储列表
	 * @param
	 * @return List<DataStoreObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午1:06:26
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DataStoreObj> queryForListByDeviceId(DataStoreObj dataStoreObj) {
		List<DataStoreObj> list = new ArrayList<DataStoreObj>();
		try {
			if (dataStoreObj.getPagination() != null) {
				dataStoreObj.setFIRSTROWNUM(dataStoreObj.getPagination().getFirstRownum());
				dataStoreObj.setPAGESIZE(dataStoreObj.getPagination().getPageSize());
				dataStoreObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("DataStore.countByDeviceId",
								dataStoreObj)).intValue());
			}
			list = getSqlMap().queryForList("DataStore.queryForListByDeviceId", dataStoreObj);
		} catch (Exception e) {
			LogHelper.error("DataStore.queryForListByDeviceId: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @see com.sitech.basd.yicloud.dao.datastore.DataStoreDao#queryStoreHostList(com.sitech.basd.yicloud.domain.datastore.DataStoreObj)
	 */
	@Override
	public List<TbCloud2HostInfoObj> queryStoreHostList(DataStoreObj obj) {
		List<TbCloud2HostInfoObj> list = new ArrayList<TbCloud2HostInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap().queryForObject(
										"DataStore.queryStoreHostCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("DataStore.queryStoreHostList", obj);
		} catch (Exception e) {
			LogHelper.error("DataStore.queryForListByDeviceId: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

}
