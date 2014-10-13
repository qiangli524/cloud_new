package com.sitech.basd.component.dao.script;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.script.ScriptGroupObj;
import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.script.ScriptRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.encrypt.DoubleEncryptUtils;

@Repository("scriptsDao")
public class ScriptsDaoImpl extends BaseDao implements ScriptsDao {

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
	public List queryForList(ScriptObj obj) {
		List list = null;

		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Script.queryForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Script.queryForList", obj);
			for (Object object : list) {
				decryptPwd((ScriptObj) object);
			}
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryForList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

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
	public List queryScriptGroupList(ScriptGroupObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Script.queryForGroupCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Script.queryForGroupList", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryForGroupList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

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
	public int insertByObj(ScriptObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Script.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.insertByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int insertGroup(ScriptGroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Script.insertGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.insertGroup:" + e.getMessage());
		}
		return ret;
	}

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
	public int updateByObj(ScriptObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Script.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.updateByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int updateGroup(ScriptGroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Script.updateGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.updateGroup:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteByObj(ScriptObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Script.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.deleteByObj:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteRelation(ScriptObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Script.deleteRelation", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.deleteRelation:" + e.getMessage());
		}
		return ret;
	}

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
	public int deleteGroup(ScriptGroupObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Script.deleteGroup", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.deleteGroup:" + e.getMessage());
		}
		return ret;
	}

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
	public List querySelectedList(ScriptRelationObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("Script.querySelectedList", obj);
			for (Object object : list) {
				decryptPwd((ScriptObj) object);
			}
		} catch (Exception sqlException) {
			LogHelper.error("Script.querySelectedList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

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
	public List queryRemainList(ScriptRelationObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("Script.queryRemainList", obj);
			for (Object object : list) {
				decryptPwd((ScriptObj) object);
			}
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryRemainList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

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
	public int deleteGroupMember(ScriptRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Script.deleteGroupMember", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.deleteGroupMember:" + e.getMessage());
		}
		return ret;
	}

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
	public int insertGroupMember(ScriptRelationObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Script.insertGroupMember", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Script.insertGroupMember:" + e.getMessage());
		}
		return ret;
	}

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
	public List queryForDeployList(ScriptObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Script.queryForDeployCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("Script.queryForDeployList", obj);
			// for (Object object : list) {
			// decryptPwd((ScriptObj)object);
			// }
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryForDeployList:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

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
	public List queryForAppScript(ScriptObj obj) {
		List list = null;
		try {

			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Script.queryForAppCount", obj)).intValue());
			}

			list = getSqlMap().queryForList("Script.queryForAppScript", obj);
			// for (Object object : list) {
			// decryptPwd((ScriptObj)object);
			// }
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryForAppScript:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

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
	public List queryLessGradeScript(ScriptObj obj) {
		List list = null;
		try {

			list = getSqlMap().queryForList("Script.queryLessGradeScript", obj);
		} catch (Exception sqlException) {
			LogHelper.error("Script.queryLessGradeScript:"
					+ sqlException.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * 
	 * @Title: decryptPwd
	 * @Description: 对用户密码进行解密
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-10-18 下午7:07:03
	 */
	public void decryptPwd(ScriptObj obj) throws Exception {
		if (obj.getPassword() != null && !"".equals(obj.getPassword())) {
			obj.setPassword(DoubleEncryptUtils.decrypt(obj.getPassword()));
		}
	}

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
	@Override
	public ScriptObj queryByObj(ScriptObj obj) {
		ScriptObj scriptObj = new ScriptObj();
		try {
			Object o = getSqlMap().queryForObject("Script.queryByObj", obj);
			if (o != null) {
				scriptObj = (ScriptObj) o;
			}
		} catch (Exception e) {
			LogHelper.error("Script.queryByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return scriptObj;
	}

}
