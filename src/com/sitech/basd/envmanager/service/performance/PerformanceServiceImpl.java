package com.sitech.basd.envmanager.service.performance;

import java.util.List;

import com.sitech.basd.envmanager.dao.performance.PerformanceDao;
import com.sitech.basd.envmanager.domain.performance.AddressObj;
import com.sitech.basd.envmanager.domain.performance.PerformanceObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class PerformanceServiceImpl   extends BaseService implements PerformanceService{

	PerformanceDao performanceDao;
	
	
	
	public PerformanceDao getPerformanceDao() {
		return performanceDao;
	}

	public void setPerformanceDao(PerformanceDao performanceDao) {
		this.performanceDao = performanceDao;
	}

	
	/**
	 * @Title:查询所有资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryPerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return performanceDao.queryPerformanceObj(obj);
	}
	/**
	 * @Title:查询所有地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryAddressInfo(AddressObj obj) {
		// TODO Auto-generated method stub
		return performanceDao.queryAddressInfo(obj);
	}
	/**
	 * @Title:增加地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertAddressInfo(AddressObj obj) {
		// TODO Auto-generated method stub
		return performanceDao.insertAddressInfo(obj);
	}
	/**
	 * @Title:修改地址变动信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateAddressInfo(AddressObj obj) {
		// TODO Auto-generated method stub
		return performanceDao.updateAddressInfo(obj);
	}

	
	@Override
	public int deletePerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PerformanceObj queryPerformanceOne(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePerformanceObj(PerformanceObj obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
