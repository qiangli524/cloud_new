package com.sitech.basd.cloud3.service.departproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.cloud3.dao.departproject.DepartManDao;
import com.sitech.basd.cloud3.domain.departproject.DepartManObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Service("departManService")
public class DepartManServiceImpl implements DepartManService {
	@Autowired
	private DepartManDao departManDao;

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询部门列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:32:49
	 */
	public List queryForDepartListByObj(DepartManObj obj) {
		return departManDao.queryForDepartListByObj(obj);
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询部门列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:32:49
	 */
	public List queryForSectionListByObj(DepartManObj obj){
		return departManDao.queryForSectionListByObj(obj);
	}

	
	/**
	 * 
	 * @Title: queryByPbj
	 * @Description: 查询一条记录
	 * @param
	 * @return DepartManObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:11
	 */
	public DepartManObj queryDepartByObj(DepartManObj obj) {
		return departManDao.queryDepartByObj(obj);
	}

	/**
	 * 
	 * @Title: queryByPbj
	 * @Description: 查询一条部门记录
	 * @param
	 * @return DepartManObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午8:34:11
	 */
	public DepartManObj queryBySectionObj(DepartManObj obj){
		return departManDao.querySectionByObj(obj);
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
		return departManDao.insertByObj(obj);
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
		return departManDao.updateByObj(obj);
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
		return departManDao.deleteByObj(obj);
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
		return departManDao.queryAllResourceForDepart(obj);
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
		return departManDao.queryAllResourceForSection(obj);
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
		return departManDao.queryUsedResourceForSection(obj);
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
		return departManDao.queryUsedResourceForDepart(obj);
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
	public List<DepartManObj> queryAllResourceListForDepart(DepartManObj obj){
		
		return departManDao.queryAllResourceListForDepart(obj);
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
	public List<DepartManObj> queryAllResourceListForSection(DepartManObj obj){
		return departManDao.queryAllResourceListForSection(obj);
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
	public List<DepartManObj> queryUsedResourceListForSection(DepartManObj obj){
		return departManDao.queryUsedResourceListForSection(obj);
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
	public List<DepartManObj> queryUsedResourceListForDepart(DepartManObj obj){
		return departManDao.queryUsedResourceListForDepart(obj);
	}
}
