package com.sitech.basd.cloud3.dao.departproject;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.DepartManObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public interface DepartManDao {
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
	public List queryForDepartListByObj(DepartManObj obj);
	
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
	public List queryForSectionListByObj(DepartManObj obj);
	
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
	public DepartManObj queryDepartByObj(DepartManObj obj);

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
	public DepartManObj querySectionByObj(DepartManObj obj);
	
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
	public int insertByObj(DepartManObj obj);

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
	public int updateByObj(DepartManObj obj);

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
	public int deleteByObj(DepartManObj obj);
	
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
	public DepartManObj queryAllResourceForDepart(DepartManObj obj);
	
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
	public DepartManObj queryAllResourceForSection(DepartManObj obj);
	
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
	public DepartManObj queryUsedResourceForSection(DepartManObj obj);
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
	public DepartManObj queryUsedResourceForDepart(DepartManObj obj);
	
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
	public List<DepartManObj> queryAllResourceListForDepart(DepartManObj obj);
	
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
	public List<DepartManObj> queryAllResourceListForSection(DepartManObj obj);
	
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
	public List<DepartManObj> queryUsedResourceListForSection(DepartManObj obj);
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
	public List<DepartManObj> queryUsedResourceListForDepart(DepartManObj obj);
}
