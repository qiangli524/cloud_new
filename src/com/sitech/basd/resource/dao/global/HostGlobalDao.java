package com.sitech.basd.resource.dao.global;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.united.CMSObj;

public interface HostGlobalDao {
	/**
	 * 
	 * @Title: queryCpuTop
	 * @Description: 查询主机cpu使用率 topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:15:38 PM
	 */
	public List queryCpuTop(Map map);

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询cpu topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryCpuXData(Map map);

	/**
	 * 
	 * @Title: queryMemTop
	 * @Description: 查询主机内存使用率topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:17:11 PM
	 */
	public List queryMemTop(Map map);

	/**
	 * 
	 * @Title: queryCpuXData
	 * @Description: 查询内存 topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryMemXData(Map map);

	/**
	 * 
	 * @Title: queryStoreTop
	 * @Description: 查询主机存储使用率topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:18:50 PM
	 */
	public List queryStoreTop(Map map);

	/**
	 * 
	 * @Title: queryStoreXData
	 * @Description: 查询store topN对应的x值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 8:42:32 AM
	 */
	public List queryStoreXData(Map map);

	/**
	 * 
	 * @Title: queryCPUList
	 * @Description: 查询按照CPU降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryCPUList(Map map);

	/**
	 * 
	 * @Title: queryMemList
	 * @Description: 查询按照内存降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryMemList(Map map);

	/**
	 * 
	 * @Title: queryStoreList
	 * @Description: 查询按照存储降序的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 9:23:23 AM
	 */
	public List queryStoreList(Map map);

	/**
	 * 
	 * @Title: countHost
	 * @Description: 统计当前集群下主机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public Integer countHost(HostGlobalObj obj);

	/**
	 * @Title: queryCpuTopClu
	 * @Description: 查集群下
	 * @param
	 * @return List
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午5:10:33
	 */
	public List queryCpuTopClu(Map map);

	/**
	 * @Title: queryCpuTopClu
	 * @Description: 查集群下
	 * @param
	 * @return List
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午5:10:33
	 */
	public List queryMemTopClu(Map map);

	/**
	 * @Title: queryCpuTopClu
	 * @Description: 查集群下
	 * @param
	 * @return List
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-10 上午5:10:33
	 */
	public List queryStoreTopClu(Map map);

	public List queryCpuXDataClu(Map map);

	public List queryMemXDataClu(Map map);

	public List queryStoreXDataClu(Map map);

	public List queryCPUListClu(Map map);

	public List queryMemListClu(Map map);

	public List queryStoreListClu(Map map);

	/**
	 * 
	 * @Title: queryCpuTopN
	 * @Description: 查询宿主机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryHostCpuTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterHostCpuTopN
	 * @Description: 查询集群宿主机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostCpuTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostMemTopN
	 * @Description: 查询宿主机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryHostMemTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterHostMemTopN
	 * @Description: 查询集群宿主机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostMemTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostStorageTopN
	 * @Description: 查询宿主机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryHostStorageTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterHostStorageTopN
	 * @Description: 查询集群宿主机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterHostStorageTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostByMap
	 * @Description: 查询主机通过集群uuid和connect_id
	 * @param
	 * @return List
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-17
	 */
	public List queryHostByMap(Map<String, String> map);

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据集群集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	public List<HostGlobalObj> queryForListUseClusterIn(HostGlobalObj hostObj);

	/**
	 * @Title: queryForListUseClusterIn
	 * @Description: 根据数据中心集合查询主机集合
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午9:51:47
	 */
	public List<HostGlobalObj> queryForListUseCenterIn(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForHost(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForCluster(HostGlobalObj hostObj);

	/**
	 * @Title: countResource
	 * @Description: 查询cpu、内存等
	 * @param
	 * @return CMSObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午10:00:46
	 */
	public CMSObj countResourceForCenter(HostGlobalObj hostObj);

	/**
	 * @Title: queryForClusterListByCenter
	 * @Description: 根据数据中心查询集群
	 * @param
	 * @return List<HostGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-2 上午11:51:58
	 */
	public List<HostGlobalObj> queryForClusterListByCenter(HostGlobalObj hostGlobalObj);

	/**
	 * 
	 * @Title: queryHostStoreIKbpsTopN
	 * @Description: 查询宿主机存储写TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:20:38
	 */
	public List<Data> queryHostStoreIKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostStoreOKbpsTopN
	 * @Description:查询宿主机存储读TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:20:53
	 */
	public List<Data> queryHostStoreOKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostNetworkOKbpsTopN
	 * @Description: 查询宿主机网络读TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:21:01
	 */
	public List<Data> queryHostNetworkOKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryHostNetworkIKbpsTopN
	 * @Description: T 查询宿主机网络写TopN
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午2:21:17
	 */
	public List<Data> queryHostNetworkIKbpsTopN(Map<String, Object> map) throws Exception;

}
