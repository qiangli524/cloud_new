package com.sitech.ssd.ah.paas.busiResource.service.statistics;

import java.util.List;

import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

/**
 * <p>
 * Title: PaasBusiStatisticsService
 * </p>
 * <p>
 * Description:paas资源页面监控 Tab页服务
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
 * @createtime 2014-7-28 上午11:49:14
 */
public interface PaasBusiStatisticsService {
	/**
	 * @Title: queryForSysNum
	 * @Description:查询子系统个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午2:19:19
	 */
	public int queryForSysNum(PaasBusiTreeObj obj);

	/**
	 * @Title: PaasBusiTreeObj
	 * @Description: 查询子系统集合
	 * @param
	 * @return List
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午10:35:40
	 */
	public List queryForSysList(PaasBusiTreeObj obj);

	/**
	 * @Title: countHostNum
	 * @Description: 统计boss下关联物理主机个数
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
	 * @Description: boss节点下的虚拟主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午11:07:06
	 */
	public int queryForSysVmCount(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForBusiCount
	 * @Description: 统计业务节点下主机个数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 上午10:09:54
	 */
	public int queryForBusiCount(PaasBusiTreeObj obj);

	/**
	 * @Title: hostsOfChildSystem
	 * @Description: 返回子系统下关联主机列表
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
	 * @Description: 返回子系统下关联虚拟机列表
	 * @param
	 * @return List<VmReportForm>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-31 上午8:29:53
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
	 * @createtime 2014-8-1 上午8:32:49
	 */
	public int queryForBusiVmCount(PaasBusiTreeObj obj);

	/**
	 * @Title: queryForSysBusiCount
	 * @Description: 查询boss节点下业务数
	 * @param
	 * @return int
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-1 上午11:18:59
	 */
	public int queryForSysBusiCount(PaasBusiTreeObj obj);
}
