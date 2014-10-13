package com.sitech.basd.sxcloud.cloud.service.virtual;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.virtual.TbCloud2VirtualConfigDao;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualConfigObj;

public class TbCloud2VirtualConfigServiceImpl implements
		TbCloud2VirtualConfigService {
	private TbCloud2VirtualConfigDao tbCloud2VirtualConfigDao;

	public void setTbCloud2VirtualConfigDao(
			TbCloud2VirtualConfigDao tbCloud2VirtualConfigDao) {
		this.tbCloud2VirtualConfigDao = tbCloud2VirtualConfigDao;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:24 PM
	 */
	public int deleteByObj(TbCloud2VirtualConfigObj obj) {
		return tbCloud2VirtualConfigDao.deleteByObj(obj);
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:31 PM
	 */
	public int insertByObj(TbCloud2VirtualConfigObj obj) {
		return tbCloud2VirtualConfigDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询出具体虚拟机配置信息
	 * @param
	 * @return TbCloud2VirtualConfigObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:08 PM
	 */
	public TbCloud2VirtualConfigObj queryByObj(TbCloud2VirtualConfigObj obj) {
		return tbCloud2VirtualConfigDao.queryByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 根据虚拟机配置部分信息查询匹配的所有虚拟机配置信息
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:32:51 PM
	 */
	public List queryForListByObj(TbCloud2VirtualConfigObj obj) {
		return tbCloud2VirtualConfigDao.queryForListByObj(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新虚拟机配置信息
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Mar 27, 2012 5:33:15 PM
	 */
	public int updateByObj(TbCloud2VirtualConfigObj obj) {
		return tbCloud2VirtualConfigDao.updateByObj(obj);
	}

}
