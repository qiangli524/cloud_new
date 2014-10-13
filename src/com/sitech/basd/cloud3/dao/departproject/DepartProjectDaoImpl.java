package com.sitech.basd.cloud3.dao.departproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;
@Repository("departProjectDao")
public class DepartProjectDaoImpl extends BaseDao implements DepartProjectDao {

	@Override
	public int deleteDepartProjectObj(DepartProjectObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("DepartProject.deleteDepartProjectObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartProject.deleteDepartProjectObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertDepartProjectObj(DepartProjectObj obj) {
		int ret = 0;
		String id = RandomUUID.getUuid();
		obj.setID(id);
		try {
			Object o = getSqlMap().insert("DepartProject.insertDepartProjectObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartProject.insertDepartProjectObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryDepartProjectList(DepartProjectObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DepartProject.queryDepartProjectCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("DepartProject.queryDepartProjectList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartProject.queryDepartProjectList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public DepartProjectObj queryDepartProjectOne(DepartProjectObj obj) {
		DepartProjectObj pObj = null;
		try {
			pObj = (DepartProjectObj) getSqlMap().queryForObject(
					"DepartProject.queryDepartProjectOne", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartProject.queryDepartProjectOne:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return pObj;
	}

	@Override
	public int updateDepartProjectObj(DepartProjectObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DepartProject.updateDepartProjectObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartProject.updateDepartProjectObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public List queryDepartProjectHisList(DepartProjectObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DepartProject.queryDepartProjectHisCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("DepartProject.queryDepartProjectHisList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartProject.queryDepartProjectHisList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	@Override
	public int insertDepartProjectHisObj(DepartProjectObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("DepartProject.insertDepartProjectHisObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartProject.insertDepartProjectHisObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryResourceByDepartment
	 * @Description: 查询该项目的资源
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-8-30 上午11:04:36
	 */
	@Override
	public List queryResourceByDepartment(DepartProjectObj obj) {
		List lst = null;
		try {

			lst = getSqlMap().queryForList("DepartProject.queryResourceByProject", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartProject.queryResourceByProject:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title: queryForList
	 * @Description: 查询项目集合
	 * @param
	 * @return List<DepartProjectObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-3 下午9:48:27
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DepartProjectObj> queryForList(DepartProjectObj departProjectObj) {
		List<DepartProjectObj> list = new ArrayList<DepartProjectObj>();
		try {
			if (departProjectObj.getPagination() != null) {
				departProjectObj.setFIRSTROWNUM(departProjectObj.getPagination().getFirstRownum());
				departProjectObj.setPAGESIZE(departProjectObj.getPagination().getPageSize());
				departProjectObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"DepartProject.countForList", departProjectObj)).intValue());
			}
			list = getSqlMap().queryForList("DepartProject.queryForList", departProjectObj);
		} catch (Exception e) {
			LogHelper.error("DepartProject.queryForList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询所有资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryAllCountResource(DepartProjectObj obj) {
		RelationObj userObj = null;
		try {
			userObj = (RelationObj) getSqlMap().queryForObject("DepartProject.queryAllCountResource", obj);
		} catch (Exception e) {
			LogHelper.error("DepartProject.queryAllCountResource" + e.getMessage() + getClass().getName());
		}
		return userObj;
	}
	/**
	 * 
	 * @Title: queryUsedCountResource
	 * @Description: 查询已用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-06
	 */
	public RelationObj queryUsedCountResource(DepartProjectObj obj) {
		RelationObj userObj = null;
		try {
			userObj = (RelationObj) getSqlMap().queryForObject("DepartProject.queryUsedCountResource", obj);
		} catch (Exception e) {
			LogHelper.error("DepartProject.queryUsedCountResource" + e.getMessage() + getClass().getName());
		}
		return userObj;
	}
	
	/**
	 * 
	 * @Title: queryUsedResource
	 * @Description: 查询已分配资源
	 * @param
	 * @return RelationObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-31
	 */
	public List<RelationObj> queryUsedResource(DepartProjectObj obj){
		List<RelationObj> list = new ArrayList<RelationObj>();
		RelationObj userObj = null;
		try {
			list =  getSqlMap().queryForList("DepartProject.queryUsedResource", obj);
		} catch (Exception e) {
			LogHelper.error("DepartProject.queryUsedResource" + e.getMessage() + getClass().getName());
		}
		return list;
		
	}
	
	/**
	 * 
	 * @Title: queryAllResource
	 * @Description: 查询所有资源
	 * @param
	 * @return RelationObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-31
	 */
	public List<RelationObj> queryAllResource(DepartProjectObj obj){
		List<RelationObj> list = new ArrayList<RelationObj>();
		try {
			list =  getSqlMap().queryForList("DepartProject.queryAllResource", obj);
		} catch (Exception e) {
			LogHelper.error("DepartProject.queryAllResource" + e.getMessage() + getClass().getName());
		}
		return list;
	}
	
}
