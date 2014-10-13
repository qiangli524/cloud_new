package com.sitech.basd.cloud3.service.departproject;

import java.util.List;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;

public interface DepartProjectService {

	/**
	 * 
	 * @Title: 查询获取项目信息列表
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public List departProjectList(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 删除项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int delDepartProject(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 新增项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertDepartProject(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 修改项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int updateDepartProject(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 查询一条项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public DepartProjectObj queryDepartProjectOne(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 查询获取项目历史信息列表
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public List departProjectHisList(DepartProjectObj obj);

	/**
	 * 
	 * @Title: 插入历史信息做记录
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public int insertDepartProjectHis(DepartProjectObj obj);

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
	 * @createtime 2013-9-3 下午9:47:34
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
	 * @createtime 2013-09-06
	 */
	public List<RelationObj>  queryUsedResource(DepartProjectObj obj);
	
	
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
	public List<RelationObj>  queryAllResource(DepartProjectObj obj);
}
