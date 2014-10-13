package com.sitech.basd.subclauses.service;

import java.util.List;

import com.sitech.basd.subclauses.domain.SubClausesObj;

/**
 * <p>
 * Title: SubClausesService
 * </p>
 * <p>
 * Description: 条目管理Service接口类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 */
public interface SubClausesService {

	/**
	 * @Title: queryAllSubClauses
	 * @Description: 查询所有服务条目
	 * @param
	 * @return List<?>
	 * @throws
	 */
	public List<?> queryAllSubClauses(SubClausesObj subClausesObj);
	
	public SubClausesObj querySubClausesById(String clausesId);
	
	public int validateSubClauses(String clausesId);
	
	public void AddSubClauses(SubClausesObj SubClausesObj);
	
	public int UpdateSubClauses(SubClausesObj SubClausesObj);
	
	public List<?> getResourceNameList(String resourceType);
	
	public int deleteSubClause(String idstr);
	
	public int publishState(SubClausesObj obj);
	
	public String getResourceInfo(String resourceType,String resourceId);

}
