package com.sitech.basd.yicloud.dao.script;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;

public class ScriptDaoImpl extends BaseDao implements ScriptDao {
	/**
	 * 
	 * @Title: listScript
	 * @Description: 查找脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws  
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public List listScript(ScriptObj obj){
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"Scipt.queryByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"Scipt.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Scipt.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入脚本
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int insertByObj(ScriptObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Scipt.insertByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改脚本
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int updateByObj(ScriptObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().update("Scipt.updateByObj",
					obj);
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 修改脚本
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public int deleteByObj(ScriptObj obj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("Scipt.deleteByObj",
					obj);
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryScriptListByTempletObj
	 * @Description: 查询和模板关联的脚本id
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws  
	 * @createtime Oct 10, 2012 11:42:00 AM
	 */
	public List queryScriptListByTempletObj(ScriptConObj conObj){
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"Scipt.queryScriptListByTempletObj", conObj);
		} catch (Exception sqlexception) {
			LogHelper.error("Scipt.queryScriptListByTempletObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	
	/**
	 * 
	 * @Title: deleteRelation
	 * @Description: 删除关联
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 12, 2012 11:42:00 AM
	 */
	public int deleteRelation(ScriptConObj conObj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("Scipt.deleteRelation",
					conObj);
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.deleteRelation:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: addRelation
	 * @Description: 建立关联
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 12, 2012 11:42:00 AM
	 */
	public int addRelation(ScriptConObj conObj){
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Scipt.addRelation",
					conObj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.addRelation:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteScriptRelation
	 * @Description: 删除脚本时，删除脚本关联表里的记录
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 12, 2012 11:42:00 AM
	 */
	public int deleteScriptRelation(ScriptConObj conObj){
		int ret = 0;
		try {
			ret = getSqlMap().delete("Scipt.deleteScriptRelation",
					conObj);
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Scipt.deleteScriptRelation:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
