package com.sitech.basd.yicloud.service.entityreference;

import com.sitech.basd.yicloud.dao.entityreference.EntityRefDao;
import com.sitech.basd.yicloud.domain.entityreference.EntityReferenceObj;

public class EntityRefServiceImpl implements EntityRefService {
	private EntityRefDao entityRefDao;

	public void setEntityRefDao(EntityRefDao entityRefDao) {
		this.entityRefDao = entityRefDao;
	}

	/**
	 * 插入数据，unit_id ，code,c_id的关系
	 * @param EntityReferenceObj
	 * @return	int
	 */
	public int insertByObj(EntityReferenceObj obj) {
		return entityRefDao.insertByObj(obj);
	}

	/**
	 *  查询一条数据，unit_id,code ,c_id的关系
	 * @param EntityReferenceObj
	 * @return EntityReferenceObj
	 */
	public EntityReferenceObj queryByObj(EntityReferenceObj obj) {
		return entityRefDao.queryByObj(obj);
	}

}
