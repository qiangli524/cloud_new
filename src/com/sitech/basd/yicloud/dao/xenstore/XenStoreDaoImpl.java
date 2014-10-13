package com.sitech.basd.yicloud.dao.xenstore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.utils.randomid.RandomUUID;

public class XenStoreDaoImpl extends BaseDao implements XenStoreDao {
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
	public List queryForListByObj(XenStoreObj obj) {
		List<XenStoreObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("XenStore.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("XenStore.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("XenStore.queryForListByObj:" + sqlexception.getMessage()
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
	public int insertDatastore(XenStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("XenStore.insertDatastore", obj);
			if (o != null) {
				ret = (Integer) o;
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("XenStore.insertDatastore:" + sqlException.getMessage()
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
	public int updateByObj(XenStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenStore.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.updateByObj:" + sqlException.getMessage()
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
	public int deleteByObj(XenStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenStore.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 查询一条存储记录
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public XenStoreObj queryByObj(XenStoreObj obj) {
		XenStoreObj tempObj = null;
		List list = queryForListByObj(obj);
		if (list != null && list.size() > 0) {
			tempObj = (XenStoreObj) list.get(0);
		}
		return tempObj;
	}

	/**
	 * 
	 * @Title: querySRIdSequence
	 * @Description: 查询存储的id
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 14, 2013 4:06:46 PM
	 */
	public int querySRIdSequence() {
		int sequence = 0;
		try {
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj();
			globalObj.setKEY("TB_CLOUD2_STORE_SEQUENCE");
			Object obj = getSqlMap().queryForObject("TbGlobalConfig.queryForObjByObj", globalObj);
			TbGlobalConfigObj reObj = null;
			if (obj != null) {
				reObj = (TbGlobalConfigObj) obj;
				sequence = Integer.parseInt(reObj.getVALUE());
			}
			if (obj == null) {
				String id = RandomUUID.getUuid();
				globalObj.setVALUE("2");
				globalObj.setID(id);
				Object insertObj = getSqlMap().insert("TbGlobalConfig.insertByObj", globalObj);
				return 1;
			} else if (sequence >= 999999) {
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
				return 1;
			} else {
				XenStoreObj storeObj = new XenStoreObj();
				while (storeObj != null) {
					storeObj.setId(sequence);
					storeObj = queryByObj(storeObj);
					if (storeObj == null) {
						break;
					} else {
						sequence += 1;
					}
					storeObj = new XenStoreObj();
				}

				globalObj.setVALUE((sequence + 1) + "");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
			}
		} catch (Exception e) {
			LogHelper.error("TbGlobalConfig.operate:" + e.getMessage() + getClass().getName());
		}
		return sequence;
	}

	/**
	 * 
	 * @Title: updateXenStoreMess
	 * @Description: 更新xen存储的信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 11:48:28 AM
	 */
	public int updateXenStoreMess(XenStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenStore.updateXenStoreMess", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.updateXenStoreMess:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForStoreList
	 * @Description: 查询所有存储
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 4:53:41 PM
	 */
	public List queryForStoreList(XenStoreObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("XenStore.queryForStoreList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("XenStore.queryForStoreList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: deleteStoreRelation
	 * @Description: 删除存储和主机的关系
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 26, 2013 4:54:39 PM
	 */
	public int deleteStoreRelation(XenStoreObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenStore.deleteStoreRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.deleteStoreRelation:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForConnectStoreHostCountByObj
	 * @Description: 查询存储关联的主机数
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 29, 2013 5:56:01 PM
	 */
	public int queryForConnectStoreHostCountByObj(XenStoreObj obj) {
		int num = 0;
		try {
			num = (Integer) getSqlMap().queryForObject("XenStore.queryForCountByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.queryForCountByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return num;
	}

	/**
	 * @Title: queryBatchForBestHost
	 * @Description: 获取xen最佳主机
	 * @param
	 * @return Map<String,List<JSONObject>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-29 下午4:10:43
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<JSONObject>> queryBatchForBestHost(List<TbCloud2HostInfoObj> hostList,
			WorkOrderObj wobj) {
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

				XenStoreObj xenDataStoreObj = new XenStoreObj();
				xenDataStoreObj.setDependent_host_uuid(obj.getH_uuid());
				xenDataStoreObj.setConnect_id(obj.getConnectId());
				List<XenStoreObj> list = getSqlMapClient().queryForList("XenStore.queryByObj",
						xenDataStoreObj);
				if (list != null && list.size() > 0) {
					Double freesize = 0.0;
					for (XenStoreObj dataobj : list) {
						if (Double.parseDouble(dataobj.getFree_size()) > freesize) {
							freesize = Double.parseDouble(dataobj.getFree_size() == null ? "0.0"
									: dataobj.getFree_size());
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
			LogHelper.error("XenDataStorage.queryByObj: " + e.getMessage());
		} finally {
			try {
				smc.commitTransaction();
				smc.endTransaction();
			} catch (SQLException e) {
				logger.error("XenDataStorage.queryByObj: " + e.getMessage(), e);
			}
		}
		return map;
	}

	/**
	 * 
	 * @Title: queryNoRepeatListByObj
	 * @Description: 查询所有不重复的xen存储
	 * @param
	 * @return List<XenStoreObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-6 下午1:05:08
	 */
	public List<XenStoreObj> queryNoRepeatListByObj(XenStoreObj obj) {
		List<XenStoreObj> lst = null;
		try {
			lst = getSqlMap().queryForList("XenStore.queryNoRepeatListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("XenStore.queryNoRepeatListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: countResource
	 * @Description: 统计xen存储使用情况
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午2:49:01
	 */
	@Override
	public CMSObj countResource(XenStoreObj xenStoreObj) {
		CMSObj cmsObj = new CMSObj();
		try {
			Object obj = getSqlMap().queryForObject("XenStore.countResource", xenStoreObj);
			if (obj != null) {
				cmsObj = (CMSObj) obj;
			}
		} catch (Exception e) {
			LogHelper.error("XenStore.countResource: " + e.getMessage() + e.getClass().getName());
		}
		return cmsObj;
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
	public int relevanceDataStore(XenStoreObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().update("XenStore.relevanceDataStore", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("XenStore.relevanceDataStore:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

}
