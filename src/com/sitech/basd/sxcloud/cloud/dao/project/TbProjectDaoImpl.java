package com.sitech.basd.sxcloud.cloud.dao.project;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbProjectDaoImpl extends BaseDao implements TbProjectDao {
	/**
	 * @Title:查询已有项目列表
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TbCloud2ProjectInfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbProject.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbProject.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbProject.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int insertByObj(TbCloud2ProjectInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbProject.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbProject.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有项目
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int deleteByObj(TbCloud2ProjectInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbProject.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbProject.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个项目对象
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TbCloud2ProjectInfoObj queryByObj(TbCloud2ProjectInfoObj obj) {
		List lst = null;
		TbCloud2ProjectInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2ProjectInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新项目信息
	 * @Copyright: Copyright (c) 2011-12-2
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public int updateByObj(TbCloud2ProjectInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbProject.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbProject.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:根据项目名称查询项目管理表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TbCloud2ProjectInfoObj queryByName(TbCloud2ProjectInfoObj obj) {
		List lst = null;
		TbCloud2ProjectInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbCloud2ProjectInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:为下拉列表提供的接口
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByProjectObj(TbCloud2ProjectInfoObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList(
					"TbProject.queryForListByProjectObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbProject.queryForListByProjectObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:创建项目中的用户
	 * @Copyright: Copyright (c) 2012-02-14
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertVuserByObj(TbCloud2ProjectInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbProject.insertVuserByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbProject.insertVuserByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除项目中已有用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int deleteUserByObj(TbCloud2ProjectInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbProject.deleteUserByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbProject.deleteUserByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询项目中的用户
	 * @Copyright: Copyright (c) 2012-02-17
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryUserListByObj(VuserManageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbProject.queryUserByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TbProject.queryUserListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbProject.queryUserListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
}
