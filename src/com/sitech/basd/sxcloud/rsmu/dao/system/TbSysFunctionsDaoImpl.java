package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbSysFunctionsDaoImpl extends BaseDao implements TbSysFunctionsDao {
	/**
	 * @Title:根据模块ID模糊查询功能列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryTbSysFunctionsObjByFUNCID(FuncRoleObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysFunctions.queryTbSysFunctionsObjByFUNCID", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserinfo.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:根据模块ID集查询所有补充信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List getNaviParam(List getNaviParam) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TbSysFunctions.getNaviParam", getNaviParam);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysUserinfo.getNaviParam:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:删除功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysFunctionsObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbSysFunctions.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysFunctions.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:添加功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysFunctionsObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbSysFunctions.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysFunctions.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体功能模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysFunctionsObj queryByObj(TbSysFunctionsObj obj) {
		List lst = null;
		TbSysFunctionsObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbSysFunctionsObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据功能部分信息查询匹配的所有功能信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysFunctionsObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbSysFunctions.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbSysFunctions.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysFunctions.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysFunctionsObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbSysFunctions.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbSysFunctions.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	public String FunctionModule(TbSysFunctionsObj obj) {
		String lst = null;
		try {
			lst = ((String) getSqlMap().queryForObject("TbSysFunctions.queryFuncName", obj))
					.toString();
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysFunctions.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: queryThreeFuncNode
	 * @Description: 查询所有三级节点
	 * @param
	 * @return List<TbSysFunctionsObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 上午10:48:25
	 */
	public List<TbSysFunctionsObj> queryThreeFuncNode(String funcid) {
		List<TbSysFunctionsObj> lst = null;
		try {
			lst = (List<TbSysFunctionsObj>) getSqlMap().queryForList(
					"TbSysFunctions.queryThreeFuncNode", funcid);
		} catch (Exception sqlexception) {
			LogHelper.error("TbSysFunctions.queryThreeFuncNode:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

}
