package com.sitech.basd.component.dao.script;

import java.util.List;

import com.sitech.basd.component.domain.script.ScriptGroupObj;
import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.script.ScriptRelationObj;

public interface ScriptsDao {
	/**
	 * 
	 * @Title: queryForList
	 * @Description:查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:48:35 AM
	 */
	public List queryForList(ScriptObj obj);

	/**
	 * 
	 * @Title: ConfigGroupList
	 * @Description: 查询配置文件组列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:08:12 AM
	 */
	public List queryScriptGroupList(ScriptGroupObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int insertByObj(ScriptObj obj);

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int insertGroup(ScriptGroupObj obj);

	/**
	 * 
	 * @Title:
	 * @Description: 修改一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int updateByObj(ScriptObj obj);

	/**
	 * 
	 * @Title:
	 * @Description: 修改一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int updateGroup(ScriptGroupObj obj);

	/**
	 * 
	 * @Title:
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int deleteByObj(ScriptObj obj);

	/**
	 * 
	 * @Title:deleteRelation
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 29, 2013 10:49:18 AM
	 */
	public int deleteRelation(ScriptObj obj);

	/**
	 * 
	 * @Title:
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	public int deleteGroup(ScriptGroupObj obj);

	/**
	 * 
	 * @Title: querySelectedList
	 * @Description: 查询已选的配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 6:28:06 PM
	 */
	public List querySelectedList(ScriptRelationObj obj);

	/**
	 * 
	 * @Title: queryRemainList
	 * @Description: 查询备选成员列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 9:16:08 AM
	 */
	public List queryRemainList(ScriptRelationObj obj);

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 删除组的所有成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	public int deleteGroupMember(ScriptRelationObj obj);

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 增加组的成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	public int insertGroupMember(ScriptRelationObj obj);

	/**
	 * 
	 * @Title: queryForDeployList
	 * @Description: 查询部署实例对应的配置文件
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 28, 2013 4:46:38 PM
	 */
	public List queryForDeployList(ScriptObj obj);

	/**
	 * 
	 * @Title: queryForAppScript
	 * @Description: 查询基准应用下部署实例对应的所有的脚本
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 9, 2013 9:49:15 AM
	 */
	public List queryForAppScript(ScriptObj obj);

	/**
	 * 
	 * @Title: queryLessGradeScript
	 * @Description: 查询实例对应的小于当前级别的所有脚本
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 11, 2013 4:15:41 PM
	 */
	public List queryLessGradeScript(ScriptObj obj);

	/**
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return ScriptObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @param obj
	 * @createtime 2013-12-31 下午5:27:13
	 */
	public ScriptObj queryByObj(ScriptObj obj);

}
