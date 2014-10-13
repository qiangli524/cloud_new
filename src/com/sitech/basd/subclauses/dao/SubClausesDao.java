package com.sitech.basd.subclauses.dao;

import java.util.List;

import com.sitech.basd.subclauses.domain.SubClausesObj;

public interface SubClausesDao {
	
	public List<SubClausesObj> queryAllSubClauses(SubClausesObj obj);
	
    public SubClausesObj querySubClausesById(String id);
	
    public int validateSubClauses(String clausesId);
    
    public void addSubClauses(SubClausesObj obj);
    
    public int updateSubClauses(SubClausesObj obj);
    
    public List<?> getResourceNameList(String resourceType);

	public int deleteSubClause(String idstr);
	
	public int publistSubClause(SubClausesObj obj);
	
	public String getResourceInfo(String resourceType,String resourceId);
	
}
