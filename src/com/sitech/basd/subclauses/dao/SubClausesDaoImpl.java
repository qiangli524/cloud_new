package com.sitech.basd.subclauses.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapException;
import com.sitech.basd.subclauses.domain.SubClausesObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Service("subClausesDao")
public class SubClausesDaoImpl extends BaseDao  implements SubClausesDao {

	/**
	 * @Title:查询出所有条目
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubClausesObj> queryAllSubClauses(SubClausesObj obj) {
		List<SubClausesObj> list = new ArrayList<SubClausesObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("subclauses.querySubClausesListsForCount",
								obj)).intValue());
			}
			list = getSqlMap().queryForList("subclauses.querySubClausesLists", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("subclauses.querySubClausesLists:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}
	
	/**
	 * @Title:根据ID查询条目详情
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public SubClausesObj querySubClausesById(String id){
		try {
			SubClausesObj obj = (SubClausesObj) getSqlMap().queryForObject("subclauses.selectByPrimaryKey", id);
			return obj;
		} catch (Exception sqlexception) {
			LogHelper.error("subclauses.selectByPrimaryKey:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
	
	/**
	 * @Title:验证ID唯一性
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int validateSubClauses(String clausesId){
		int i = 0;
		try{
				i = (Integer)getSqlMap().queryForObject("subclauses.selectCountForAdd",clausesId);
				return i;
		}catch (Exception sqlexception){
			LogHelper.error("subclauses.selectCountForAdd:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
	
	/**
	 * @Title:insert
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public void addSubClauses(SubClausesObj obj){
		try {
			getSqlMap().insert("subclauses.insertSubClauses", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("subclauses.insertSubClauses:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
	
	/**
	 * @Title:update
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int updateSubClauses(SubClausesObj obj){
		try {
			//UpdateByExampleParms parms = new UpdateByExampleParms(obj, obje);
			int count = getSqlMap().update("subclauses.updateSubClausesById", obj);
			return count;
		} catch (Exception sqlexception) {
			LogHelper.error("subclauses.updateSubClausesById:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
	
	/**
	 * @Title:获取模板列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public List<?> getResourceNameList(String resourceType){
		List relist = new ArrayList();
		try {
			if("1".equals(resourceType)){
				relist = getSqlMap().queryForList("subclauses.getResourceName1",resourceType);
			}
			return relist;
		} catch (SQLException sqlexception) {
			LogHelper.error("subclauses.getResourceName1:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
		
	}
	
	/**
	 * @Title:delete
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int deleteSubClause(String idstr){
		int result = 0;
		Object object;
		try {
			object = getSqlMap().delete("subclauses.deleteById", idstr);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("subclauses.deleteById:" + e.getMessage()
					+ getClass().getName());

		}
		return result;
	}
	
	/**
	 * @Title:upate publish_state
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zhuxla
	 * @version 1.0
	 */
	@Override
	public int publistSubClause(SubClausesObj obj){
		try {
			int count = getSqlMap().update("subclauses.publishSubClausesById", obj);
			return count;
		} catch (Exception sqlexception) {
			LogHelper.error("subclauses.publishSubClausesById:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
	
	@Override
	public String getResourceInfo(String resourceType,String resourceId){
		String retStr = "";
		try {
			if("1".equals(resourceType)){
				retStr = (String) getSqlMap().queryForObject("subclauses.getResourceInfo1",resourceId);
			}
			if("2".equals(resourceType)){
				//weit
			}
			return retStr;
		} catch (SQLException sqlexception) {
			LogHelper.error("subclauses.getResourceInfo1:" + sqlexception.getMessage() + getClass().getName());
			throw new SqlMapException();
		}
	}
}
