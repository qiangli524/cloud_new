package com.sitech.basd.sxcloud.cloud.service.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.virtual.TbVirtualPoolDao;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class VirtualPoolServiceImpl extends BaseService implements
		VirtualPoolService {

	/**
	 * @Title:删除已监控虚拟机
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2VirtualPoolObj obj) {

		return tbVirtualPoolDao.deleteByObj(obj);

	}

	/**
	 * @Title:插入监控的虚拟机
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2VirtualPoolObj obj) {

		return tbVirtualPoolDao.insertByObj(obj);
	}

	/**
	 * @Title:查询并返回一个虚拟机对象
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TbCloud2VirtualPoolObj queryByObj(TbCloud2VirtualPoolObj obj) {

		return tbVirtualPoolDao.queryByObj(obj);
	}

	/**
	 * @Title:查询已监控虚拟机列表
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2VirtualPoolObj obj) {
		return tbVirtualPoolDao.queryForListByObj(obj);
	}

	/**
	 * @Title:更新监控虚拟机信息
	 * @Copyright: Copyright (c) 2012-03-31
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2VirtualPoolObj obj) {

		return tbVirtualPoolDao.updateByObj(obj);

	}

	/**
	 * @Title: 同步虚拟机数据到虚拟机资源池
	 * @Copyright: Copyright (c) 2012-04-11
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObjForSyn(TbCloud2VirtualPoolObj obj) {

		return tbVirtualPoolDao.insertByObjForSyn(obj);

	}

	private TbVirtualPoolDao tbVirtualPoolDao;

	public void setTbVirtualPoolDao(TbVirtualPoolDao tbVirtualPoolDao) {
		this.tbVirtualPoolDao = tbVirtualPoolDao;
	}

}
