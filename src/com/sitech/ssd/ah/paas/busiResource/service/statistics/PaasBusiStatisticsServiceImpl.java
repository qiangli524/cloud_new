package com.sitech.ssd.ah.paas.busiResource.service.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.paas.busiResource.dao.statistics.PaasBusiStatisticsDao;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.busiResource.util.paasBusiConstant;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

/**
 * <p>
 * Title: PaasBusiStatisticsServiceImpl
 * </p>
 * <p>
 * Description: paas资源树监控页面数据服务实现类
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
 * @createtime 2014-7-28 上午11:48:30
 * 
 */
@Service("paasBusiStatisticsService")
public class PaasBusiStatisticsServiceImpl implements PaasBusiStatisticsService {
	@Autowired
	PaasBusiStatisticsDao paasBusiStatisticsDao;

	@Override
	public int queryForSysCount(PaasBusiTreeObj obj) {
		return paasBusiStatisticsDao.queryForSysCount(obj);
	}

	@Override
	public List<GreenPlumHostInfoObj> queryHostsForService(PaasBusiTreeObj obj) {
		List<GreenPlumHostInfoObj> list = paasBusiStatisticsDao.queryHostsForService(obj);
		return list;
	}

	@Override
	public int queryForBusiCount(PaasBusiTreeObj obj) {
		return paasBusiStatisticsDao.queryForBusiCount(obj);
	}

	@Override
	public int queryForSysNum(PaasBusiTreeObj obj) {
		obj.setNode_type(paasBusiConstant.ROOT);
		return paasBusiStatisticsDao.queryForSysNum(obj);
	}

	@Override
	public List<VmReportForm> queryVmHostsForService(PaasBusiTreeObj obj) {
		List<VmReportForm> list = paasBusiStatisticsDao.queryVmHostsForService(obj);
		return list;
	}

	@Override
	public int queryForBusiVmCount(PaasBusiTreeObj obj) {
		return paasBusiStatisticsDao.queryForBusiVmCount(obj);
	}

	@Override
	public List queryForSysList(PaasBusiTreeObj obj) {
		return paasBusiStatisticsDao.queryForSysList(obj);
	}

	@Override
	public int queryForSysVmCount(PaasBusiTreeObj obj) {
		// TODO Auto-generated method stub
		return paasBusiStatisticsDao.queryForSysVmCount(obj);
	}

	@Override
	public int queryForSysBusiCount(PaasBusiTreeObj obj) {
		// TODO Auto-generated method stub
		return paasBusiStatisticsDao.queryForSysBusiCount(obj);
	}

}
