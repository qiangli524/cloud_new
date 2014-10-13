package com.sitech.basd.bol.service.bolresource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.bolresource.BolResourceDao;
import com.sitech.basd.bol.domain.bolresource.BolResourceObj;
import com.sitech.basd.bol.domain.bolresource.BolResourcesRegisterObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * <p>Title: RolResourceServiceImpl</p>
 * <p>Description: 资源视图逻辑层实现类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:46:15
 *
 */
@Service("bolResourceService")
public class BolResourceServiceImpl implements BolResourceService{

	@Autowired
	private BolResourceDao bolResourceDao;
	
	/**
	 * @Title: countForResource
	 * @Description: 统计资源数量
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:05:58
	 */
	@Override
	public int countForResource(BolResourceObj bolResourceObj) {
		return bolResourceDao.countForResource(bolResourceObj);
	}

	/**
	 * @Title: countHostNum
	 * @Description: 统计主机数量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:33:26
	 */
	@Override
	public Integer countHostNum() {
		return bolResourceDao.countHostNum();
	}

	/**
	 * @Title: queryForSum
	 * @Description: 查询各种汇总
	 * @param
	 * @return Double
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午10:44:24
	 */
	@Override
	public Double queryForSum(BolResourceObj bolResourceObj) {
		return bolResourceDao.queryForSum(bolResourceObj);
	}

	/**
	 * @Title: queryForUsed
	 * @Description: 查询资源使用量
	 * @param
	 * @return Integer
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 上午11:11:37
	 */
	@Override
	public Double queryForUsed(BolResourceObj bolResourceObj) {
		return bolResourceDao.queryForUsed(bolResourceObj);
	}

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询资源列表
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-6 下午3:52:28
	 */
	@Override
	public List<BolResourceObj> queryForListByObj(BolResourceObj bolResourceObj) {
		return bolResourceDao.queryForListByObj(bolResourceObj);
	}

	/**
	 * @Title: queryResourceForProcess
	 * @Description: 展示某种资源关联的资源
	 * @param
	 * @return List<BolResourceObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-7 下午2:02:52
	 */
	@Override
	public List<BolResourceObj> queryResourceForProcess(BolResourceObj bolResourceObj) {
		return bolResourceDao.queryResourceForProcess(bolResourceObj);
	}
	
	/**
	 * 
	 * @Title: queryResourcesRegister
	 * @Description: 查询资源登记情况，即 每个进程的资源占用情况
	 * @param
	 * @return List<BolResourcesRegisterObj>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 16, 2014 1:01:32 PM
	 */
	public List<BolResourcesRegisterObj> queryResourcesRegister(BolResourcesRegisterObj obj){
		return bolResourceDao.queryResourcesRegister(obj);
	}

}
