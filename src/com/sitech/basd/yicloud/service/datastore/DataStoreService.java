package com.sitech.basd.yicloud.service.datastore;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreInfoObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

public interface DataStoreService {
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
	public List queryForListByObj(DataStoreObj obj);

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
	public List queryForListByDateStoreInfoObj(DataStoreInfoObj obj);

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
	public int insertDatastore(DataStoreObj obj);

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
	public String saveNFS(DataStoreObj obj);

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
	public int updateByObj(DataStoreObj obj);

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
	public int deleteByObj(DataStoreObj obj);

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
	public List queryClusterds(DataStoreObj obj);

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
	public List queryAllDataStore(DataStoreObj obj);

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
	public List queryForDateStoreList(DataStoreObj obj);

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
	public int queryForConnectStoreHostCountByObj(DataStoreObj obj);

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
	public List queryForHostIdsList(DataStoreObj obj);

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
	public Map<String, Double> queryVmwareDataStoreAllAndFree(Map<String, Object> map);

	/**
	 * @Title: queryBatchForBestHost
	 * @Description: 构造获取最优主机的参数
	 * @param
	 * @return Map<String,List<JSONObject>>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 上午8:51:19
	 */
	public Map<String, List<JSONObject>> queryBatchForBestHost(List<TbCloud2HostInfoObj> hostList,
			WorkOrderObj obj) throws Exception;

	/**
	 * @Title: countResource
	 * @Description: 统计cpu、内存、存储等信息
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param dataStoreObj
	 * @createtime 2013-9-10 上午12:01:09
	 */
	public CMSObj countResource(DataStoreObj dataStoreObj);
	
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
	public List<DataStoreObj> queryNoRelevanceStoreDevice(DataStoreObj dataStoreObj);
	
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
	public int relevanceDataStore(DataStoreObj obj);
	
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
	public List<DataStoreObj> queryRelevanceStoreDevice(DataStoreObj dataStoreObj);

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
	public List<DataStoreObj> queryForListByDeviceId(DataStoreObj dataStoreObj);

	/**
	 * <p>查询存储块被哪些主机使用</p>
	 *
	 * @Createtime Jul 3, 2014,11:02:59 AM
	 * @author xugang
	 * @version 1.0
	 * @param storeUuid
	 * @return
	 */
	public List<TbCloud2HostInfoObj> queryStoreHostList(DataStoreObj obj);
	
}
