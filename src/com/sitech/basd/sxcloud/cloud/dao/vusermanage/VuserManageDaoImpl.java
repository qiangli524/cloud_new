package com.sitech.basd.sxcloud.cloud.dao.vusermanage;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.vusermanage.VuserManageObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class VuserManageDaoImpl extends BaseDao implements VuserManageDao {
	/**
	 * @Title:查询用户列表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByUserObj(TbSysUserinfoObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"VuserManage.queryForListByUserObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList("VuserManage.queryForListByUserObj",
					obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("VuserManage.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询出具体V用户信息列表
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public VuserManageObj queryByObj(VuserManageObj obj) {
		List lst = null;
		VuserManageObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (VuserManageObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:查询V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(VuserManageObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"VuserManage.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("VuserManage.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VuserManage.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:添加V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int insertByObj(VuserManageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("VuserManage.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VuserManage.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:更新V用户信息
	 * @Copyright: Copyright (c) 201112
	 * @Company: si-tech
	 * @author wangjian
	 * @version 1.0
	 */
	public int updateByObj(VuserManageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("VuserManage.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VuserManage.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除V用户信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(VuserManageObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("VuserManage.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("VuserManage.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unused")
	private VuserManageDao vuserManageDao;

	public void setVuserManageDao(VuserManageDao vuserManageDao) {
		this.vuserManageDao = vuserManageDao;
	}
}
