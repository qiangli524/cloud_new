package com.sitech.basd.resource.dao.template;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.resource.domain.template.TemVmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("temVmRelationDao")
public class TemVmRelationDaoImpl extends BaseDao implements TemVmRelationDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询关系列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public List queryForList(TemVmRelationObj obj) {
		List list = null;
		try {

			list = getSqlMap().queryForList("TemVmRelation.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("TemVmRelation.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 删除一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int deleteByObj(TemVmRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TemVmRelation.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemVmRelation.deleteByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 插入一条记录
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public int insertByObj(TemVmRelationObj obj) {
		int ret = 0;
		try {
			// 设置UUID
			Object o = getSqlMap().insert("TemVmRelation.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlException) {
			ret = -1;
			LogHelper.error("TemVmRelation.insertByObj:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
}
