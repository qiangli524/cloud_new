package com.sitech.basd.busimanager.dao.busitree;

import java.util.List;
import java.util.Map;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

/**
 * 
 * <p>
 * Title: BusiManagerTreeDao
 * </p>
 * <p>
 * Description: 业务系统树数据库Dao
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:44:34
 * 
 */
public interface BusiManagerTreeDao {
	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForTree(BusiManagerTree obj);

	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForLimitTree(BusiManagerTree obj);

	public void insertBusiManagerTree(BusiManagerTree obj) throws Exception ;

	/**
	 * 
	 * @Title: deleteBusiManagerTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	public int deleteBusiManagerTreeById(BusiManagerTree obj);
	
	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime May 30, 2013 6:54:45 PM
	 */
	public int updateBusiManagerTreeByObj(BusiManagerTree obj);
	
	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return BusiManagerTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	public BusiManagerTree queryBusiCenterSonNodesNum(BusiManagerTree obj);

	/**
	 * @Title: countByObj
	 * @Description: 统计业务系统下各子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:42:53
	 */
	public int countByObj(BusiManagerTree BusiManagerTree);

	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	public List<BusiManagerTree> queryForListByParentIdList(
			BusiManagerTree BusiManagerTree);
	
	/**
	 * 获取业务的统计个数
	 * @Title: getTypeCounts
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return Map<String,Long>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-13 下午2:25:16
	 */
	public Map<Long,Long> getTypeCounts(BusiManagerTree obj);
	
	/**
	 * @Title: queryForVmList
	 * @Description: 获取对应的虚拟机列表（去掉已存在的）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午2:25:16
	 */
	public List<VMHostObj> queryForVmList(VMHostObj obj);
	
	/**
	 * 获取所有的统计个数
	 * @Title: queryAllStatisticsCount
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:44:52
	 */
	public List<BusiManagerTree> queryAllStatisticsCount(BusiManagerTree BusiManagerTree);
	
	/*************子业务系统下的topN*************************************/
	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机cpuTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机cpuTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN1(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询子系统下的虚拟机CpuTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmCpuTopN2(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemTopN
	 * @Description: 查询虚拟机menTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemTopN
	 * @Description: 查询虚拟机menTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN1(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemTopN2
	 * @Description: 查询业务子系统下虚拟机menTopN（带承载业务）
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmMemTopN2(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmStoreTopN
	 * @Description: 查询虚拟机StoreTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> queryVmStoreTopN(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmCpuList
	 * @Description: 查询虚拟机cpuList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemList
	 * @Description: 查询虚拟机MemList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmCpuList2
	 * @Description: 查询业务子系统下虚拟机cpuList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList2(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmCpuList
	 * @Description: 查询虚拟机cpuList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmCpuList1(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemList
	 * @Description: 查询虚拟机MemList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList1(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: queryVmMemList1
	 * @Description: 业务子系统下查询虚拟机MemList（带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> queryVmMemList2(Map<String, Object> map) throws Exception;

	/*************业务系统下的topN*************************************/
	
	/**
	 * 
	 * @Title: querySysVmCpuTopN
	 * @Description: 业务系统下查询虚拟机cpuTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> querySysVmCpuTopN(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: querySysVmMemTopN
	 * @Description: 业务系统下查询虚拟机menTopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<Data> querySysVmMemTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: querySysVmMemList
	 * @Description: 业务系统下查询虚拟机cpuList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> querySysVmCpuList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: querySysVmMemList
	 * @Description: 业务系统下查询虚拟机MemList
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List<TopTargetObj> querySysVmMemList(Map<String, Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: querySysVmMemList
	 * @Description: 查询展开树所需结点（不带承载业务）
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodes(BusiManagerTree busi) throws Exception;
	
	/**
	 * 
	 * @Title: querySysVmMemList（带承载业务）
	 * @Description: 查询展开树所需结点
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodes1(BusiManagerTree busi) throws Exception;

	/**
	 * 
	 * @Title: queryExpandNodesForBusi
	 * @Description: 查询展开树所需结点
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodesForBusi(BusiManagerTree busi) throws Exception;

	/**
	 * <p>描述该方法的主要功能</p>
	 * 
	 * @param id
	 * @return
	 */
	public List<BusiManagerTree> getBusiTreeList(String id,String type);

	/**
	 * <p>根据子业务ID,查询子业务下的虚拟机</p>
	 * 
	 * @param id
	 * @param list
	 * @return
	 */
	public void getVmhostListByBusiId(BusiManagerTree bt, List list);
	
	/**
	 * @Title: queryForList
	 * @Description: 查询业务中心，业务系统，业务子系统，承载子业务
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-9-2 下午4:41:30
	 */
	public List<BusiManagerTree> queryForList(BusiManagerTree obj);
}
