package com.sitech.basd.envmanager.dao.updatepwd;

import java.util.List;

import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.envmanager.domain.updatepwd.UpdatepwdObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class UpdatepwdDaoImpl  extends BaseDao implements UpdatepwdDao{

	@Override
	public int deletePwdObj(UpdatepwdObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Updatepwd.deletePwdObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Updatepwd.deletePwdObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public int insertPwdObj(UpdatepwdObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Updatepwd.insertPwdObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Updatepwd.insertPwdObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public UpdatepwdObj queryPwdOne(UpdatepwdObj obj) {
		UpdatepwdObj uObj=null;
		try {
			uObj= (UpdatepwdObj) getSqlMap().queryForObject("Updatepwd.queryPwdOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Updatepwd.queryPwdOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return uObj;
	}

	@Override
	public int updatePwdObj(UpdatepwdObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Updatepwd.updatePwdObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Updatepwd.updatePwdObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:查询相应设备下的所有用户名密码信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryUpdatepwdObj(UpdatepwdObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Updatepwd.queryPwdObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Updatepwd.queryUpdatepwdObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Updatepwd.queryUpdatepwdObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
