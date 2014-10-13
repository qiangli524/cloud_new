package com.sitech.ssd.ah.paas.busiResource.dao.statistics;

import java.util.List;

import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

/**
 * <p>
 * Title: PaasBusiStatisticsDao
 * </p>
 * <p>
 * Description:监控页面数据查询
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-7-28 上午11:17:07
 * 
 */
public interface PaasBusiStatisticsDao {
	/**
	 * @Title: queryForSysNum
	 * @Description: 查询子系统个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午2:13:35
	 */
	public int queryForSysNum(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForSysList
	 * @Description: 查询子系统的集合
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午10:34:16
	 */
	public List queryForSysList(PaasBusiTreeObj obj);

	/**
	 * @Title: countHostNum
	 * @Description: 查询boss节点下物理主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午10:59:36
	 */
	public int queryForSysCount(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForSysVmCount
	 * @Description: 查询boss节点下虚拟主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午11:09:47
	 */
	public int queryForSysVmCount(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForBusiCount
	 * @Description: 查询业务节点下的主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 上午10:07:55
	 */
	public int queryForBusiCount(PaasBusiTreeObj obj);

	/**
	 * @Title: hostsOfChildSystem
	 * @Description: 返回服务下物理主机列表
	 * @param
	 * @return List<PaasBusiTreeObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午11:02:26
	 */
	public List<GreenPlumHostInfoObj> queryHostsForService(PaasBusiTreeObj obj);

	/**
	 * @Title: queryVmHostsForService
	 * @Description: 返回服务下虚拟主机列表
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午5:44:44
	 */
	public List<VmReportForm> queryVmHostsForService(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForSysVmCount
	 * @Description: 统计业务下虚拟机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午8:34:09
	 */
	public int queryForBusiVmCount(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForSysBusiCount
	 * @Description: 查询boss下业务数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午11:20:08
	 */
	public int queryForSysBusiCount(PaasBusiTreeObj obj);
}
