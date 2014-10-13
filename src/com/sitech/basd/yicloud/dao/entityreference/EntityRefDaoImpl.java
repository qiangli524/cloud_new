package com.sitech.basd.yicloud.dao.entityreference;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.entityreference.EntityReferenceObj;

public class EntityRefDaoImpl extends BaseDao implements EntityRefDao {
	/**
	 * 插入数据，unit_id ，code,c_id的关系
	 * @param EntityReferenceObj
	 * @return	int
	 */
	public int insertByObj(EntityReferenceObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("EntityReference.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			LogHelper.error("EntityReference.insertByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return ret;
	}

	public List<EntityReferenceObj> queryForList(EntityReferenceObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("EntityReference.queryByObj", obj);
		} catch (Exception sqlException) {
			LogHelper.error("EntityReference.queryByObj:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 *  查询一条数据，unit_id,code ,c_id的关系
	 * @param EntityReferenceObj
	 * @return EntityReferenceObj
	 */
	public EntityReferenceObj queryByObj(EntityReferenceObj obj) {
		EntityReferenceObj tempObj = null;
		List<EntityReferenceObj> list = queryForList(obj);
		if (list != null && list.size() > 0) {
			tempObj = list.get(0);
		}
		return tempObj;
	}
}
