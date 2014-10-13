package com.sitech.basd.component.tree.dao.relation;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("exampleRelationDao")
public class ExampleRelationDaoImpl extends BaseDao implements
		ExampleRelationDao {
	/**
	 * 
	 * @Title: inserByObj
	 * @Description: 增加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 30, 2013 9:06:24 AM
	 */
	public int inserByObj(ExampleRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("ExampleRelation.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("ExampleRelation.insertByObj:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除关系表中的记录
	 * @author duangh
	 * @date May 30, 2013 4:52:11 PM
	 * @param obj
	 * @return
	 */
	public int deleteByObj(ExampleRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("ExampleRelation.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("ExampleRelation.deleteByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询符合关系的列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 3:59:05 PM
	 */
	public List queryListByObj(ExampleRelationObj obj) {
		List list = null;
		try {
			list = getSqlMap()
					.queryForList("ExampleRelation.queryForList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("ExampleRelation.queryForList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询关系
	 * @param
	 * @return ExampleRelationObj
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 3:59:42 PM
	 */
	public ExampleRelationObj queryByObj(ExampleRelationObj obj) {
		ExampleRelationObj exReObj = new ExampleRelationObj();
		List list = queryListByObj(obj);
		if (list != null && list.size() > 0) {
			exReObj = (ExampleRelationObj) list.get(0);
		}
		return exReObj;
	}

	/**
	 * 
	 * @Title: filterGrade
	 * @Description: 过滤同一类型的脚本级别
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 22, 2013 10:48:48 AM
	 */
	public List filterGrade(Map map) {
		List list = null;
		try {
			list = getSqlMap().queryForList("ExampleRelation.filterGrade", map);
		} catch (Exception sqlException) {
			LogHelper.error("ExampleRelation.filterGrade:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

}
