package com.sitech.basd.sxcloud.cloud.service.resource;

import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

public interface HostInfoService {

	/**
	 * 
	 * @Title: 获得主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryForListByObj(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 添加主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 修改主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2HostInfoObj obj);
	public int updateHostStatusByObj(TbCloud2HostInfoObj obj);

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
	public int updateInterByObj(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 删除主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 对于修改、删除添加历史数据
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int insertHostHis(TbCloud2HostHisObj obj);

	/**
	 * 
	 * @Title: 查询主机历史信息
	 * @Copyright: Copyright (c) 2011-12-15
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostHisObj> queryHisForListByObj(TbCloud2HostHisObj obj);

	/**
	 * 
	 * @Title: 通过机柜Id查询相应的主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryHostListById(String CQ_ID);

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List<TbCloud2HostConfigObj> queryForConfigListByObj(
			TbCloud2HostConfigObj obj);

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByConfigObj(TbCloud2HostConfigObj obj);

	/**
	 * @Title:更新主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByConfigObj(TbCloud2HostConfigObj obj);

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbCloud2HostConfigObj queryByConfigObj(TbCloud2HostConfigObj obj);

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByConfigObj(TbCloud2HostConfigObj obj);

	/**
	 * 
	 * @Title: 获得已经监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryMonitorHost(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 获得未监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryNotMonitorHost(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 根据类型查询主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryForListByType(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title:通过deviceId 删除主机
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int deleteByDeviceId(TbCloud2HostInfoObj obj);

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
	public TbCloud2HostInfoObj queryByObj(TbCloud2HostInfoObj obj);

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
	public int getIdSequence();

	/**
	 * @Title: countvmNum
	 * @Description: 查询每个主机下虚拟机的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public int countvmNum(Map map);

	/**
	 * @Title: queryAllHost
	 * @Description: 查询所有主机列表（安徽移动竞标）
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryAllHost(TbCloud2HostInfoObj obj);

	/**
	 * @Title: countStorageNum
	 * @Description: 查询每个主机下存储的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public int countStorageNum(Map map);

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
	public TbCloud2HostInfoObj queryForIdByIp(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 查询不在主机池中的主机
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List hostNotInPool(TbCloud2HostInfoObj obj);

	/**
	 * 
	 * @Title: 更改主机的主机池ID *
	 * @param map
	 *            ,key为主机池id，value为要更改的主机数组
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updatePoolId(TbCloud2HostInfoObj obj);

	/**
	 * @Title: queryHostListUseIn
	 * @Description: 使用in语句查询出所有符合条件的主机集合
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-2 上午9:37:41
	 */
	public List<TbCloud2HostInfoObj> queryHostListUseIn(
			TbCloud2HostInfoObj hostObj);

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
	public CMSObj countResource(TbCloud2HostInfoObj hostObj);

	/**
	 * 
	 * @Title: 修改时查询IP和和名字是否重复
	 * @Copyright: 2013-09-14
	 * @Company: si-tech
	 * @author yanggl
	 * @version 1.0
	 */
	public int validateNameAndIP(TbCloud2HostInfoObj obj);

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
	public CMSObj countResourceByEqid(TbCloud2HostInfoObj hostObj);

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
	public List<TbCloud2HostInfoObj> queryHostListUseInByEqid(
			TbCloud2HostInfoObj hostInfoObj);

	/**
	 * @Title: queryHostListByAllocatedSerious
	 * @Description: 查询已分配异常主机列表
	 * @param
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-17 下午4:55:08
	 */
	public List<TbCloud2HostInfoObj> queryHostListByAllocatedSerious(
			TbCloud2HostInfoObj hostInfoObj);

	public List<TbCloud2HostInfoObj> querySeriousHostListUseIn(
			TbCloud2HostInfoObj hostInfoObj);

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
	public int updateUnitedDataByObj(TbCloud2HostInfoObj hostInfoObj);

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
	public List<TbCloud2HostInfoObj> queryHostListByUnAllocatedSerious(
			TbCloud2HostInfoObj hostInfoObj);

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
	public List<TbCloud2HostInfoObj> querySeriousHostListUseInUnAllo(
			TbCloud2HostInfoObj thObj);

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
	public List<TbCloud2HostInfoObj> queryForStoreDeviceList(
			TbCloud2HostInfoObj infoObj);

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
	public Map<String, Double> queryForDistribute(
			TbCloud2HostInfoObj tbCloud2HostInfoObj);

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
	public List<TbCloud2HostInfoObj> queryForListForCluster(
			TbCloud2HostInfoObj hostInfoObj);

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
	public List<TbCloud2HostInfoObj> queryHostListThroughCluster(
			TbCloud2HostInfoObj hostInfoObj);
	
	/**
	 * @Title: queryAllHostTYPE
	 * @Description: 查询可分配主机类型及配置
	 * @return int
	 * @author chenjlc
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryAllHostType(TbCloud2HostInfoObj obj);
	
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
	public List<TbCloud2HostInfoObj> queryHostListForBusiManager(TbCloud2HostInfoObj host);

}
