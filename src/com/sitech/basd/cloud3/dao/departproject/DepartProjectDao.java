package com.sitech.basd.cloud3.dao.departproject;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;

public interface DepartProjectDao {
	/**
	 * 
	 * @Title:查询所有的项目信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryDepartProjectList(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 删除项目
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int deleteDepartProjectObj(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 更新项目信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateDepartProjectObj(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 新增项目信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertDepartProjectObj(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 查询一条项目信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public DepartProjectObj queryDepartProjectOne(DepartProjectObj obj);

	/**
	 * 
	 * @Title:查询所有的项目历史信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryDepartProjectHisList(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 删除时插入历史信息表
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertDepartProjectHisObj(DepartProjectObj obj);

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
	public List queryResourceByDepartment(DepartProjectObj obj);

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
	public List<DepartProjectObj> queryForList(DepartProjectObj departProjectObj);
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
	public RelationObj queryAllCountResource(DepartProjectObj obj);
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
	public RelationObj queryUsedCountResource(DepartProjectObj obj);
	
	
	
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
	public List<RelationObj> queryUsedResource(DepartProjectObj obj);
	
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
	public List<RelationObj> queryAllResource(DepartProjectObj obj);
}
