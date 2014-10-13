package com.sitech.basd.resource.dao.global;

import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;

public interface VmGlobalDao {
	/**
	 * 
	 * @Title: queryCpuTop
	 * @Description: 查询虚拟机cpu使用率 topN
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
	 * @Title: queryMemTop
	 * @Description: 查询虚拟机内存使用率topN
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
	 * @Title: queryStoreTop
	 * @Description: 查询虚拟机存储使用率topN
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
	 * @Title: queryStoreTop
	 * @Description: 查询主机存储使用率topN
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:18:50 PM
	 */
	public List queryCpuXData(Map map);

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
	public Integer countVm(VmGlobalObj obj);

	public List queryCpuTopClu(Map map);

	public List queryMemTopClu(Map map);

	public List queryStoreTopClu(Map map);

	public List queryCpuXDataClu(Map map);

	public List queryMemXDataClu(Map map);

	public List queryStoreXDataClu(Map map);

	public List queryCPUListClu(Map map);

	public List queryMemListClu(Map map);

	public List queryStoreListClu(Map map);

	/**
	 * 
	 * @Title: queryVmCpuTopN
	 * @Description: 查询虚拟机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmCpuTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterVmCpuTopN
	 * @Description: 查询集群虚拟机CpuTopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmCpuTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVmMemTopN
	 * @Description: 查询虚拟机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmMemTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterVmMemTopN
	 * @Description: 查询集群虚拟机内存TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmMemTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVmStorageTopN
	 * @Description: 查询虚拟机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryVmStorageTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryClusterVmStorageTopN
	 * @Description: 查询集群虚拟机存储TopN
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:22:10
	 */
	public List<Data> queryClusterVmStorageTopN(Map<String, Object> map) throws Exception;

	public List<Data> queryHostVmCpuTopN(Map<String, Object> map) throws Exception;

	public List<Data> queryHostVmMemTopN(Map<String, Object> map) throws Exception;

	public List<Data> queryHostVmStorageTopN(Map<String, Object> map) throws Exception;

	public List queryCPUListHost(Map<String, Object> map);

	public List queryMemListHost(Map<String, Object> map);

	public List queryStoreListHost(Map<String, Object> map);

	/**
	 * @Title: queryForListByHost
	 * @Description: 根据主机查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByHost(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryForListByCluster
	 * @Description: 根据集群查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCluster(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryForListByCenter
	 * @Description: 根据数据中心查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCenter(VmGlobalObj vmGlobalObj);

	/**
	 * @Title: queryCPUListVM
	 * @Description: cpuTOP 关联业务
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午4:41:03
	 */
	public List<TopTargetObj> queryCPUListVM(Map<String, Object> paramMap);

	/**
	 * @Title: queryMemListVM
	 * @Description: memTOP 关联业务
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午4:41:07
	 */
	public List<TopTargetObj> queryMemListVM(Map<String, Object> paramMap);

	/**
	 * 
	 * @Title: queryVMStoreIKbpsTopN
	 * @Description: 查询虚拟机存储写速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:15:36
	 */
	public List<Data> queryVMStoreIKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVMStoreOKbpsTopN
	 * @Description: 查询虚拟机存储读速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:02
	 */
	public List<Data> queryVMStoreOKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVMNetworkOKbpsTopN
	 * @Description: 查询虚拟机网卡读速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:16
	 */
	public List<Data> queryVMNetworkOKbpsTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVMNetworkIKbpsTopN
	 * @Description: 查询虚拟机网卡写速率Topn
	 * @param
	 * @return List<Data>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-29 下午4:16:27
	 */
	public List<Data> queryVMNetworkIKbpsTopN(Map<String, Object> map) throws Exception;

}
