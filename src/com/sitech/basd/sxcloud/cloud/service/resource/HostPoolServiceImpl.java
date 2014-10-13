package com.sitech.basd.sxcloud.cloud.service.resource;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.resource.HostPoolDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostPoolObj;

public class HostPoolServiceImpl implements HostPoolService {

	private HostPoolDao hostPoolDao;

	/**
	 * 
	 * @Title: 删除主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int deleteHostForPool(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.deleteHostForPool(obj);
	}

	/**
	 * 
	 * @Title: 增加主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int insertHostForPool(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.insertHostForPool(obj);
	}

	/**
	 * 
	 * @Title: 查询主机池里的主机信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */

	@Override
	public List queryHostName(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.queryHostName(obj);
	}

	/**
	 * 
	 * @Title: 查询主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public List queryHostForPool(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.queryHostForPool(obj);
	}

	/**
	 * 
	 * @Title: 查询一条主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public TbCloud2HostPoolObj queryHostObj(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.queryHostObj(obj);
	}

	/**
	 * 
	 * @Title: 修改主机池信息
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int updateHostForPool(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.updateHostForPool(obj);
	}

	/**
	 * 
	 * @Title: 相同主机名称的个数统计
	 * @Copyright: Copyright (c) 2013-02-27
	 * @Company: si-tech
	 * @author xmq
	 * @version 1.0
	 */
	@Override
	public int getHostPoolCount(TbCloud2HostPoolObj obj) {
		// TODO Auto-generated method stub
		return hostPoolDao.getHostPoolCount(obj);
	}

	public HostPoolDao getHostPoolDao() {
		return hostPoolDao;
	}

	public void setHostPoolDao(HostPoolDao hostPoolDao) {
		this.hostPoolDao = hostPoolDao;
	}

	@Override
	public int deleteHostRelation(TbCloud2HostPoolObj obj) {
		return hostPoolDao.deleteHostRelation(obj);
	}

}
