package com.sitech.basd.yicloud.service.healthy;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.reporthost.TbHostDao;
import com.sitech.basd.sxcloud.cloud.dao.reportvirtual.TbHyDao;
import com.sitech.basd.sxcloud.cloud.domain.reporthost.TbHostObj;
import com.sitech.basd.sxcloud.cloud.domain.reportvirtual.TbHyObj;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;

/**
 * 
 * <p>
 * Title: VMManagerServiceImpl
 * </p>
 * <p>
 * Description: 虚拟机管理实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author taoxue
 * @version 1.0
 * @createtime Apr 18, 2012 11:08:57 AM
 * 
 */
public class HealthyManagerServiceImpl implements HealthyManagerService { 
	private EntityTreeDao entityTreeDao; 
	TbHyDao tbHyDao;
	TbHostDao tbHostDao;

	public void setTbHostDao(TbHostDao tbHostDao) {
		this.tbHostDao = tbHostDao;
	}

	public void setTbHyDao(TbHyDao tbHyDao) {
		this.tbHyDao = tbHyDao;
	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询数据中心，集群，主机，虚拟机等生成树
	 * @param
	 * @return EntityTreeObj
	 * @throws
	 * @author duangh
	 * @version 1.0
	 */
	public List<EntityTreeObj> queryForTree(EntityTreeObj obj) {
		return entityTreeDao.queryForTree(obj);
	}
	
	/**
	 * @Title:查询当天的虚拟服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHyForToday(TbHyObj obj) {
		return tbHyDao.queryTbHyForToday(obj);
	}
	
	/**
	 * @Title:查询当天的物理服务器采集信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public List queryTbHostForToday(TbHostObj obj) {
		return tbHostDao.queryTbHostForToday(obj);
	}
	
	
}
