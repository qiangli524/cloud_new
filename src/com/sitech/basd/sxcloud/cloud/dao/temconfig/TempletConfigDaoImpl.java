package com.sitech.basd.sxcloud.cloud.dao.temconfig;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.temconfig.TempletTypeConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;


public class TempletConfigDaoImpl extends BaseDao implements TempletConfigDao {
	/**
	 * @Title:查询已有服务列表
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletTypeConfigObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"temconfig.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("temconfig.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("temconfig.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryForListByConObj(TempletConfigObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"temconfig.queryByConObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("temconfig.queryForListByConObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("temconfig.queryForListByConObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询并返回一个服务对象
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@Override
	public TempletTypeConfigObj queryByObj(TempletTypeConfigObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		TempletTypeConfigObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TempletTypeConfigObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:创建服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@Override
	public int insertByObj(TempletTypeConfigObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().insert("temconfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * @Title:更新服务信息
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@Override
	public int updateByObj(TempletTypeConfigObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("temconfig.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有服务
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@Override
	public int deleteByObj(TempletTypeConfigObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("temconfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * @Title:插入新服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByConObj(TempletConfigObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().update("temconfig.insertByConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.insertByConObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:修改服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByConObj(TempletConfigObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().update("temconfig.updateByConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.updateByConObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:删除服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteConObj(TempletConfigObj obj){
		int ret = 0;
		try {
			Object o = getSqlMap().update("temconfig.deleteByConObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("temconfig.deleteByConObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	/**
	 * @Title:查询服务项
	 * @Copyright: Copyright (c) 2012-08-29
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public TempletConfigObj queryByConObj(TempletConfigObj obj){
		List lst = null;
		TempletConfigObj tempObj = null;
		lst = queryForListByConObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TempletConfigObj) lst.get(0);
		}
		return tempObj;
	}

}
