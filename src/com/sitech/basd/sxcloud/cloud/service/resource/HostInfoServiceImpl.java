package com.sitech.basd.sxcloud.cloud.service.resource;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.resource.domain.united.CMSObj;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostHisObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;

public class HostInfoServiceImpl implements HostInfoService {
	private HostInfoDao hostInfoDao;

	public HostInfoDao getHostInfoDao() {
		return hostInfoDao;
	}

	public void setHostInfoDao(HostInfoDao hostInfoDao) {
		this.hostInfoDao = hostInfoDao;
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
		return hostInfoDao.deleteByObj(obj);
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
		return hostInfoDao.insertByObj(obj);
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
		return hostInfoDao.insertHostHis(obj);
	}

	/**
	 * 
	 * @Title: 获得主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryForListByObj(TbCloud2HostInfoObj obj) {
		List<TbCloud2HostInfoObj> lst = hostInfoDao.queryForListByObj(obj);
		NumberFormat format = NumberFormat.getIntegerInstance();
		for (TbCloud2HostInfoObj tbCloud2HostInfoObj : lst) {
			tbCloud2HostInfoObj.setMem(format.format(Double
					.parseDouble(tbCloud2HostInfoObj.getMem() == null
							? "0"
							: tbCloud2HostInfoObj.getMem()) / 1024));
			tbCloud2HostInfoObj.setStore(format.format(Double
					.parseDouble(tbCloud2HostInfoObj.getStore() == null
							? "0"
							: tbCloud2HostInfoObj.getStore()) / 1024));
		}
		return lst;
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
		return hostInfoDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: 查询主机历史信息
	 * @Copyright: Copyright (c) 2011-12-15
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostHisObj> queryHisForListByObj(TbCloud2HostHisObj obj) {
		return hostInfoDao.queryHisForListByObj(obj);
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
		return hostInfoDao.queryHostListById(CQ_ID);
	}

	/**
	 * @Title:根据主机配置部分信息查询匹配的所有主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List<TbCloud2HostConfigObj> queryForConfigListByObj(
			TbCloud2HostConfigObj obj) {
		return hostInfoDao.queryForConfigListByObj(obj);
	}

	/**
	 * @Title:插入主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByConfigObj(TbCloud2HostConfigObj obj) {
		return hostInfoDao.insertByConfigObj(obj);
	}

	/**
	 * @Title:更新主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByConfigObj(TbCloud2HostConfigObj obj) {
		return hostInfoDao.updateByConfigObj(obj);
	}

	/**
	 * @Title:查询出具体主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbCloud2HostConfigObj queryByConfigObj(TbCloud2HostConfigObj obj) {
		return hostInfoDao.queryByConfigObj(obj);
	}

	/**
	 * @Title:删除主机配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByConfigObj(TbCloud2HostConfigObj obj) {
		return hostInfoDao.deleteByConfigObj(obj);
	}

	/**
	 * 
	 * @Title: 获得已经监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryMonitorHost(TbCloud2HostInfoObj obj) {
		return hostInfoDao.queryMonitorHost(obj);
	}

	/**
	 * 
	 * @Title: 获得未监控的主机列表
	 * @Copyright: Copyright (c) 2012-04-06
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryNotMonitorHost(TbCloud2HostInfoObj obj) {
		return hostInfoDao.queryNotMonitorHost(obj);
	}

	/**
	 * 
	 * @Title: 根据类型查询主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryForListByType(TbCloud2HostInfoObj obj) {
		return hostInfoDao.queryForListByType(obj);
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
		return hostInfoDao.deleteByDeviceId(obj);
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
		return hostInfoDao.queryByObj(obj);
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
	@Override
	public int getIdSequence() {
		return hostInfoDao.getIdSequence();
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
		return hostInfoDao.updateInterByObj(obj);
	}

	/**
	 * @Title: countvmNum
	 * @Description: 查询每个主机下虚拟机的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public int countvmNum(Map map) {
		return hostInfoDao.countvmNum(map);
	}

	/**
	 * @Title: queryAllHost
	 * @Description: 查询所有主机列表（安徽移动竞标）
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryAllHost(TbCloud2HostInfoObj obj) {
		return hostInfoDao.queryAllHost(obj);
	}

	/**
	 * @Title: countStorageNum
	 * @Description: 查询每个主机下存储的个数
	 * @return int
	 * @author duangh
	 * @version 1.0
	 */
	public int countStorageNum(Map map) {
		return hostInfoDao.countStorageNum(map);
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
		return hostInfoDao.queryForIdByIp(obj);
	}

	/**
	 * 
	 * @Title: 查询不在主机池中的主机
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List hostNotInPool(TbCloud2HostInfoObj obj) {
		return hostInfoDao.hostNotInPool(obj);
	}

	/**
	 * 
	 * @Title: 更改主机的主机池ID,
	 * @param map
	 *            ,key为主机池id，value为要更改的主机数组
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updatePoolId(TbCloud2HostInfoObj obj) {
		return hostInfoDao.updatePoolId(obj);
	}

	/*
	 * <p>Title: queryHostListUseIn</p> <p>Description: 使用in语句查询出符合条件的主机集合
	 * 
	 * @param hostObj
	 * 
	 * @return
	 * 
	 * @see com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService#
	 * queryHostListUseIn
	 * (com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj)
	 */
	@Override
	public List<TbCloud2HostInfoObj> queryHostListUseIn(
			TbCloud2HostInfoObj hostObj) {
		return hostInfoDao.queryHostListUseIn(hostObj);
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
		// TODO Auto-generated method stub
		return hostInfoDao.countResource(hostObj);
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
		return hostInfoDao.validateNameAndIP(obj);
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
		return hostInfoDao.countResourceByEqid(hostObj);
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
	@Override
	public List<TbCloud2HostInfoObj> queryHostListUseInByEqid(
			TbCloud2HostInfoObj hostInfoObj) {
		// TODO Auto-generated method stub
		return hostInfoDao.queryHostListUseInByEqid(hostInfoObj);
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
	@Override
	public List<TbCloud2HostInfoObj> queryHostListByAllocatedSerious(
			TbCloud2HostInfoObj hostInfoObj) {
		// TODO Auto-generated method stub
		return hostInfoDao.queryHostListByAllocatedSerious(hostInfoObj);
	}

	@Override
	public List<TbCloud2HostInfoObj> querySeriousHostListUseIn(
			TbCloud2HostInfoObj hostInfoObj) {
		// TODO Auto-generated method stub
		return hostInfoDao.querySeriousHostListUseIn(hostInfoObj);
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
		return hostInfoDao.updateUnitedDataByObj(hostInfoObj);
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
	@Override
	public List<TbCloud2HostInfoObj> queryHostListByUnAllocatedSerious(
			TbCloud2HostInfoObj hostInfoObj) {

		return hostInfoDao.queryHostListByUnAllocatedSerious(hostInfoObj);
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
	@Override
	public List<TbCloud2HostInfoObj> querySeriousHostListUseInUnAllo(
			TbCloud2HostInfoObj thObj) {
		return hostInfoDao.querySeriousHostListUseInUnAllo(thObj);
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
	@Override
	public List<TbCloud2HostInfoObj> queryForStoreDeviceList(
			TbCloud2HostInfoObj infoObj) {
		return hostInfoDao.queryForStoreDeviceList(infoObj);
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
	public Map<String, Double> queryForDistribute(
			TbCloud2HostInfoObj tbCloud2HostInfoObj) {
		TbCloud2HostInfoObj cloud2HostInfoObj = hostInfoDao
				.queryForDistribute(tbCloud2HostInfoObj);
		Map<String, Double> map = new HashMap<String, Double>();
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);

		double alloSpace = Double
				.parseDouble(cloud2HostInfoObj.getStore() == null
						? "0.0"
						: cloud2HostInfoObj.getStore());
		double usedSpace = cloud2HostInfoObj.getUsedStore() == null
				? 0
				: cloud2HostInfoObj.getUsedStore();
		map.put("alloSpace", Double.parseDouble(nf.format(alloSpace / 1024)));
		map.put("usedSpace", Double.parseDouble(nf.format(usedSpace / 1024)));
		map.put("unusedSpace",
				Double.parseDouble(nf.format(alloSpace - usedSpace)));
		return map;
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
	@Override
	public List<TbCloud2HostInfoObj> queryForListForCluster(
			TbCloud2HostInfoObj hostInfoObj) {
		return hostInfoDao.queryForListForCluster(hostInfoObj);
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
	public List<TbCloud2HostInfoObj> queryHostListThroughCluster(
			TbCloud2HostInfoObj hostInfoObj) {
		// TODO Auto-generated method stub
		return hostInfoDao.queryHostListThroughCluster(hostInfoObj);
	}

	@Override
	public int updateHostStatusByObj(TbCloud2HostInfoObj obj) {
		return hostInfoDao.updateHostStatusByObj(obj);
	}

	/**
	 * @Title: queryAllHostTYPE
	 * @Description: 查询可分配主机类型及配置
	 * @return int
	 * @author chenjlc
	 * @version 1.0
	 */
	public List<TbCloud2HostInfoObj> queryAllHostType(
			TbCloud2HostInfoObj hostInfoObj) {
		// TODO Auto-generated method stub
		return hostInfoDao.queryAllHostType(hostInfoObj);
	}	

	/**
	 * @Title: queryHostListForBusiManager
	 * @Description: 业务中心（视图），业务节点上添加物理主机查询
	 * 		选择界面，排除已选择过的物理主机。
	 * @return List<TbCloud2HostInfoObj>
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @date 2014年8月19日
	 */
	public List<TbCloud2HostInfoObj> queryHostListForBusiManager(TbCloud2HostInfoObj host){
		return hostInfoDao.queryHostListForBusiManager(host);
	}
	
}
