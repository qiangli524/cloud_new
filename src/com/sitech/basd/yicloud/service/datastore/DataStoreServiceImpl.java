package com.sitech.basd.yicloud.service.datastore;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfoObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.DrsVO;
import com.sitech.vo.united.HaVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;
import com.sitech.ws.Operation;
import com.sitech.ws.web.NoticeUtil;

public class DataStoreServiceImpl implements DataStoreService {
	private DataStoreDao dataStoreDao;
	private EntityTreeDao entityTreeDao;

	public void setDataStoreDao(DataStoreDao dataStoreDao) {
		this.dataStoreDao = dataStoreDao;
	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

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
		return dataStoreDao.queryForListByObj(obj);
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
		return dataStoreDao.queryForListByDateStoreInfoObj(obj);
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
		return dataStoreDao.insertDatastore(obj);
	}

	/**
	 * 
	 * @Title: saveNFS
	 * @Description: 保存添加的nfs存储
	 * @param
	 * @return int
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Sep 25, 2012 10:46:15 AM
	 */
	public String saveNFS(DataStoreObj obj) {
		String result = "1";
		String reason = "";
		try {
			String hostId = obj.getHOST_ID();
			// EntityConObj conObj = new EntityConObj();
			// conObj.setEntityId(hostId);
			// conObj.setType(Integer.parseInt(TypeConstant.VMWARE_HOST));
			// List list = entityTreeDao.queryEntityInfo(conObj);
			// if (list != null && list.size() > 0) {
			// conObj = (EntityConObj) list.get(0);
			// }

			EntityTreeObj etbj = new EntityTreeObj();
			etbj.setEntityId(hostId);
			List<Map<String, Integer>> templist = entityTreeDao.queryHostClDcId(etbj);
			Map<String, Integer> cldcIdMap = null;
			if (templist != null && templist.size() > 0) {
				cldcIdMap = templist.get(0);
			}

			// int datacenter_id = conObj.getDataCenterId();
			int datacenter_id = cldcIdMap.get("dcId");
			// int cluster_id = conObj.getClusterId();
			int cluster_id = cldcIdMap.get("clusterId");
			// String hostname = conObj.getName();
			String remoteHost = obj.getREMOTE_HOST();
			String remotePath = obj.getREMOTE_PATH();
			// String mode = "readWrite";//or readOnly
			String mode = obj.getReadOnly();
			String localPath = obj.getNAME();
			String url = "/vmware/storage/addDatastore/[hostname:" + hostId + "].[remoteHost:"
					+ remoteHost + "].[remotePath:" + URLEncoder.encode(remotePath, "utf-8")
					+ "].[mode:" + mode + "].[localPath:" + localPath + "]/";
			String addResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(addResult);
			String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (code.equals(ResponseCode.SUCCESS)) {
				try {
					NoticeUtil.getInstance().updateCMDBHost(hostId, Operation.OPER_REL_MODI);
				} catch (Exception e) {

				}
				String free_space = (String) ps.get("FreeSpace");
				String capacity = (String) ps.get("capacity");
				String storageUrl = (String) ps.get("url");
				String storage_code = (String) ps.get(ResponseCode.CODE);
				obj.setStore_uuid(storage_code);
				obj.setSTORAGE_URL(storageUrl);
				obj.setFREE_SPACE(free_space);
				obj.setCLUSTER_ID(cluster_id + "");
				obj.setDATACENTER_ID(datacenter_id + "");
				obj.setTYPE("NFS");
				obj.setCAPACITY(capacity);
				try {
					NoticeUtil.getInstance().addStorage(storage_code);
				} catch (Exception e) {

				}
				int ret = dataStoreDao.insertDatastore(obj);
			} else if (code.equals(ResponseCode.FAIL)) {
				result = "-1";
				reason = (String) ps.get(ResponseCode.RESPONSEREMARK);
			}
		} catch (Exception e) {
			result = "-1";
			reason = e.getMessage();
		}
		String json = "{\"result\":\"" + result + "\",\"reason\":\"" + reason + "\"}";
		return json;
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
		return dataStoreDao.updateByObj(obj);
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
	public int deleteByObj(DataStoreObj obj){
		int result =0;
		try {
			String url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/deleteStore";
			HostUnitedVO vo = new HostUnitedVO();
			vo.setHostCode(obj.getHOST_ID());
			vo.setConnectCode(obj.getConnectId());
			DatastoreUnitedVO davo = new DatastoreUnitedVO();
			davo.setDatastoreName(obj.getNAME());
			vo.setDatastore(davo);
			vo = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<HostUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = dataStoreDao.deleteByObj(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
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
		return dataStoreDao.queryClusterds(obj);
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
		return dataStoreDao.queryAllDataStore(obj);
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
		return dataStoreDao.queryForDateStoreList(obj);
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
		return dataStoreDao.queryForConnectStoreHostCountByObj(obj);
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
		return dataStoreDao.queryForHostIdsList(obj);
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
		return dataStoreDao.queryVmwareDataStoreAllAndFree(map);
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
	@Override
	public Map<String, List<JSONObject>> queryBatchForBestHost(List<TbCloud2HostInfoObj> hostList,
			WorkOrderObj wobj) throws Exception {
		return dataStoreDao.queryBatchForBestHost(hostList, wobj);
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
		// TODO Auto-generated method stub
		return dataStoreDao.countResource(dataStoreObj);
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
	public List<DataStoreObj> queryNoRelevanceStoreDevice(DataStoreObj dataStoreObj){
		return dataStoreDao.queryNoRelevanceStoreDevice(dataStoreObj);
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
	public int relevanceDataStore(DataStoreObj obj){
		return dataStoreDao.relevanceDataStore(obj);
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
	public List<DataStoreObj> queryRelevanceStoreDevice(DataStoreObj dataStoreObj){
		return dataStoreDao.queryRelevanceStoreDevice(dataStoreObj);
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
	@Override
	public List<DataStoreObj> queryForListByDeviceId(DataStoreObj dataStoreObj) {
		return dataStoreDao.queryForListByDeviceId(dataStoreObj);
	}

	/**
	 *
	 * @see com.sitech.basd.yicloud.service.datastore.DataStoreService#queryStoreHostList(java.lang.String)
	 */
	@Override
	public List<TbCloud2HostInfoObj> queryStoreHostList(DataStoreObj obj) {
		return dataStoreDao.queryStoreHostList(obj);
	}
	
	
}
