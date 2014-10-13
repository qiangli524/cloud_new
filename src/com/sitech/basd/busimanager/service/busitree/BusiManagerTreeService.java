package com.sitech.basd.busimanager.service.busitree;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;

/**
 * 
 * <p>
 * Title: BusiManagerTreeDao
 * </p>
 * <p>
 * Description: 业务系统树业务处理类
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
public interface BusiManagerTreeService {
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
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForLimitTree(BusiManagerTree obj);

	public void insertBusiManagerTree(BusiManagerTree obj) throws Exception;

	/**
	 * 
	 * @Title: initBusiSysTreelist
	 * @Description: 实例树业务系统树权限数据List
	 * @param
	 * @return List<BusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:24:41
	 */
	public List<BusiManagerTree> initBusiSysTreelist(String id, String username,
			List<BusiManagerTree> resultList);

	/**
	 * 
	 * @Title: deleteBusiManagerTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
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
	 * @author hehui
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
	 * @createtime 2013-8-14 下午5:41:08
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
	public List<BusiManagerTree> queryForListByParentIdList(BusiManagerTree BusiManagerTree);

	/**
	 * 
	 * @Title: validateDelete
	 * @Description: 验证能否被删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-23 下午7:30:51
	 */
	public Boolean validateDelete(String id, String type);

	/**
	 * 获取不同类型type的统计个数
	 * 
	 * @Title: getTypeCounts
	 * @param
	 * @return Map<String,Long>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-13 下午2:30:02
	 */
	public Map<Long, Long> getTypeCounts(BusiManagerTree obj);

	/**
	 * 获取某一个节点下的所有子节点的统计数量
	 * 
	 * @Title: getTypeCountsByPoint
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return Map<Long,Long>
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:47:32
	 */
	public Map<String, Long> getTypeCountsByPoint(BusiManagerTree obj);

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
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts queryVmTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	public List queryTopList(Map<String, Object> map) throws Exception;

	/******************* 业务系统下 ********************************/

	/**
	 * 
	 * @Title: querySysVmTopN
	 * @Description: 查询虚拟机TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts querySysVmTopN(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: querySysTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	public List querySysTopList(Map<String, Object> map) throws Exception;

	/**
	 * 
	 * @Title: queryVmBySubSystem
	 * @Description: 通过业务子系统查询其对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-11 下午3:34:06
	 */
	public List queryVmBySubSystem(BusiManagerTree busi);
	
	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合(不带承载业务)
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public List getExpandNodes(BusiManagerTree obj);
	
	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合(带承载业务)
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public List getExpandNodes1(BusiManagerTree obj);
	
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
	public List queryExpandNodesForBusi(BusiManagerTree busi);
	
	public FusionCharts queryChartDayData(List<BusiManagerTree> objs,String caption);

	/**
	 * <p>导出excel</p>
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	public List getVmhostListByBusiId(String id, String type);

	/**
	 * <p>根据虚拟机列表生成excel输出流</p>
	 * 
	 * @param list
	 * @param out
	 */
	public void getOutPutStreamByVmList(List<Map<String,Object>> list, ByteArrayOutputStream out);
	
	/**
	 * @Title:queryForList
	 * @Description: 查询业务列表：业务中心，业务系统，业务子系统，承载子业务
	 * @param BusiManagerTree obj
	 * @return List<BusiManagerTree>
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-9-2 下午4:47:52
	 */
	public List<BusiManagerTree> queryForList(BusiManagerTree obj);
	
}
