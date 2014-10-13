package com.sitech.basd.sxcloud.rsmu.dao.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbCloud2SecurityConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysGroupfuncObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbCloud2SecurityConfigDaoImpl extends BaseDao implements
		TbCloud2SecurityConfigDao {

	/**
	 * 
	 * @Title: 查询配置列表
	 * @Copyright: Copyright (c) 2011-12-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List<TbCloud2SecurityConfigObj> queryForListByObj(
			TbCloud2SecurityConfigObj obj) {
		List<TbCloud2SecurityConfigObj> lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbCloud2SecurityConfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCloud2SecurityConfig.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: 查询全局配置表
	 * @Copyright: Copyright (c) 2011-12-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String queryForObjByObj(String obj) {
		String ret = null;
		try {
			ret = (String) getSqlMap().queryForObject(
					"TbCloud2SecurityConfig.queryForObjByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud2SecurityConfig.queryForObjByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: 更新安全鉴权配置表
	 * @Copyright: Copyright (c) 2011-12-21
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public int updateObjByObj(TbCloud2SecurityConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update(
					"TbCloud2SecurityConfig.updateObjByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2SecurityConfig.updateObjByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * @Title:插入用户组功能关联信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2SecurityConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbCloud2SecurityConfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2SecurityConfig.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除安全配置
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 下午4:47:09
	 */
	public int deleteByObj(TbCloud2SecurityConfigObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbCloud2SecurityConfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbCloud2SecurityConfig.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * 
	 * @Title: queryForAllList
	 * @Description: 查询安全配置
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-5 下午12:30:29
	 */
	public List<TbCloud2SecurityConfigObj> queryForAllList(TbCloud2SecurityConfigObj obj) {
		List<TbCloud2SecurityConfigObj> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbCloud2SecurityConfig.queryForAllListForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbCloud2SecurityConfig.queryForAllList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbCloud2SecurityConfig.queryForAllList:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

}
