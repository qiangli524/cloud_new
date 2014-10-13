package com.sitech.basd.yicloud.dao.entityreference;

import com.sitech.basd.yicloud.domain.entityreference.EntityReferenceObj;

public interface EntityRefDao {
	/**
	 * 插入数据，unit_id ，code,c_id的关系
	 * @param EntityReferenceObj
	 * @return	int
	 */
	public int insertByObj(EntityReferenceObj obj);

	/**
	 *  查询一条数据，unit_id,code ,c_id的关系
	 * @param EntityReferenceObj
	 * @return EntityReferenceObj
	 */
	public EntityReferenceObj queryByObj(EntityReferenceObj obj);
}
