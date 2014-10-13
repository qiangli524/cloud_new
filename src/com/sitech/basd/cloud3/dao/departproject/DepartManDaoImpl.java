package com.sitech.basd.cloud3.dao.departproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.cloud3.domain.departproject.DepartManObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@Repository("departManDao")
public class DepartManDaoImpl extends BaseDao implements DepartManDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询科室列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:32:49
	 */
	public List queryForDepartListByObj(DepartManObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("DepartMan.queryDepartCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DepartMan.queryForDepartListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryForDepartListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询部门列表
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-19 上午8:32:49
	 */
	public List queryForSectionListByObj(DepartManObj obj){
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("DepartMan.querySectionCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("DepartMan.queryForSectionListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryForSectionListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: queryByPbj
	 * @Description: 查询一条科室记录
	 * @param
	 * @return DepartManObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:11
	 */
	public DepartManObj queryDepartByObj(DepartManObj obj) {
		DepartManObj reObj = null;
		List<DepartManObj> treeLst = queryForDepartListByObj(obj);
		if (treeLst != null && treeLst.size() > 0) {
			reObj = treeLst.get(0);
		}
		return reObj;
	}
	
	/**
	 * 
	 * @Title: querySectionByObj
	 * @Description: 查询一条部门记录
	 * @param
	 * @return DepartManObj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:11
	 */
	public DepartManObj querySectionByObj(DepartManObj obj) {
		DepartManObj reObj = null;
		List<DepartManObj> treeLst = queryForSectionListByObj(obj);
		if (treeLst != null && treeLst.size() > 0) {
			reObj = treeLst.get(0);
		}
		return reObj;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:56
	 */
	public int insertByObj(DepartManObj obj) {
		int ret = 0;
		String id = RandomUUID.getUuid();
		obj.setId(id);
		try {
			Object o = getSqlMap().insert("DepartMan.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartMan.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:56
	 */
	public int updateByObj(DepartManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("DepartMan.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartMan.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:35:45
	 */
	public int deleteByObj(DepartManObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("DepartMan.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DepartMan.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询科室下所有预分配资源
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public DepartManObj queryAllResourceForDepart(DepartManObj obj){
		DepartManObj dObj = new DepartManObj();
		try {
			dObj = (DepartManObj) getSqlMap().queryForObject("DepartMan.queryAllResourceForDepart", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryAllResourceForDepart:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询部门下所有预分配资源
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public DepartManObj queryAllResourceForSection(DepartManObj obj){
		DepartManObj dObj = new DepartManObj();
		try {
			dObj = (DepartManObj) getSqlMap().queryForObject("DepartMan.queryAllResourceForSection", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryAllResourceForSection:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询部门下已使用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public DepartManObj queryUsedResourceForSection(DepartManObj obj){
		DepartManObj dObj = new DepartManObj();
		try {
			dObj = (DepartManObj) getSqlMap().queryForObject("DepartMan.queryUsedResourceForSection", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryUsedResourceForSection:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	/**
	 * 
	 * @Title: queryAllCountResource
	 * @Description: 查询科室下已使用资源
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public DepartManObj queryUsedResourceForDepart(DepartManObj obj){
		DepartManObj dObj = new DepartManObj();
		try {
			dObj = (DepartManObj) getSqlMap().queryForObject("DepartMan.queryUsedResourceForDepart", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryUsedResourceForDepart:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	
	/**
	 * 
	 * @Title: queryAllResourceListForDepart
	 * @Description: 查询科室下所有预分配资源列表
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public List<DepartManObj> queryAllResourceListForDepart(DepartManObj obj){
		List<DepartManObj> dObj = new ArrayList<DepartManObj>();
		try {
			dObj =  getSqlMap().queryForList("DepartMan.queryAllResourceForDepart", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryAllResourceForDepart:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	
	/**
	 * 
	 * @Title: queryAllResourceListForSection
	 * @Description: 查询部门下所有预分配资源列表
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public List<DepartManObj> queryAllResourceListForSection(DepartManObj obj){
		List<DepartManObj> dObj = new ArrayList<DepartManObj>();
		try {
			dObj =  getSqlMap().queryForList("DepartMan.queryAllResourceForSection", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryAllResourceForSection:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	
	/**
	 * 
	 * @Title: queryUsedResourceListForSection
	 * @Description: 查询部门下已使用资源列表
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public List<DepartManObj> queryUsedResourceListForSection(DepartManObj obj){
		List<DepartManObj> dObj = new ArrayList<DepartManObj>();
		try {
			dObj =  getSqlMap().queryForList("DepartMan.queryUsedResourceForSection", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryUsedResourceForSection:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
	/**
	 * 
	 * @Title: queryUsedResourceListForDepart
	 * @Description: 查询科室下已使用资源列表
	 * @param
	 * @return obj
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-16
	 */
	public List<DepartManObj> queryUsedResourceListForDepart(DepartManObj obj){
		List<DepartManObj> dObj = new ArrayList<DepartManObj>();
		try {
			dObj =  getSqlMap().queryForList("DepartMan.queryUsedResourceForDepart");
		} catch (Exception sqlexception) {
			LogHelper.error("DepartMan.queryUsedResourceForDepart:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return dObj;
	}
}
