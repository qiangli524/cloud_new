package com.sitech.basd.subclauses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.subclauses.dao.SubClausesDao;
import com.sitech.basd.subclauses.domain.SubClausesObj;

/**
 * <p>
 * Title: SubClausesServiceImpl
 * </p>
 * <p>
 * Description: SubClausesService逻辑层实现
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 */
@Service("subClausesService")
public class SubClausesServiceImpl implements SubClausesService {

	@Autowired
	private SubClausesDao subClausesDao;
	
	/**
	 * @Title:获取所有条目
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public List<?> queryAllSubClauses(SubClausesObj subClausesObj) {
		return subClausesDao.queryAllSubClauses(subClausesObj);
	}
	
	/**
	 * @Title:获取条目详情
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public SubClausesObj querySubClausesById(String clausesId){
		SubClausesObj obj = subClausesDao.querySubClausesById(clausesId);
		return obj;
	}

	/**
	 * @Title:验证条目唯一性
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int validateSubClauses(String clausesId){
		return subClausesDao.validateSubClauses(clausesId);
	}
	
	/**
	 * @Title:添加条目
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public void AddSubClauses(SubClausesObj SubClausesObj){
		subClausesDao.addSubClauses(SubClausesObj);
	}
	
	/**
	 * @Title:更新条目
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int UpdateSubClauses(SubClausesObj SubClausesObj){
		return subClausesDao.updateSubClauses(SubClausesObj);
	}
	
	/**
	 * @Title:获取模板List
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public List<?> getResourceNameList(String resourceType){
		return subClausesDao.getResourceNameList(resourceType);
	}
	
	/**
	 * @Title:删除条目
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int deleteSubClause(String idstr){
		return subClausesDao.deleteSubClause(idstr);
	}
	
	/**
	 * @Title:条目发布
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int publishState(SubClausesObj obj){
		return subClausesDao.publistSubClause(obj);
	}
	
	@Override
	public String getResourceInfo(String resourceType,String resourceId){
		return subClausesDao.getResourceInfo(resourceType,resourceId);
	}
//	@Override
//	public int insertMytestService(MytestObj mytestObj){
//		
//		return mytestDao.insertMytest(mytestObj);
//	}
//	
//	@Override
//	public int deleteMytestService(MytestObj mytestObj){
//		
//		return mytestDao.deleteByName(mytestObj);
//	}
//	
//	@Override
//	public int updateMytestService(MytestObj mytestObj){
//		
//		return mytestDao.updateMytest(mytestObj);
//	}
}
