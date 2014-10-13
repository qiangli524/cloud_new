package com.sitech.basd.yicloud.service.script;

import java.util.List;

import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;

public interface ScriptService {
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
	public List listScript(ScriptObj obj);
	
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
	public int insertByObj(ScriptObj obj);
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
	public int updateByObj(ScriptObj obj);
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
	public int deleteByObj(ScriptObj obj);
	
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
	public List queryScriptListByTempletObj(ScriptConObj conObj);
	
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
	public int deleteRelation(ScriptConObj conObj);
	
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
	public int addRelation(ScriptConObj conObj);
	
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
	public int deleteScriptRelation(ScriptConObj conObj);
}
