package com.sitech.basd.sxcloud.workflow.dao.templet;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.workflow.domain.templet.TempletInfoObj;

public class TempletInfoDaoImpl extends BaseDao implements TempletInfoDao {

	/**
	 * 
	 * @Title: 保存修改后的模板信息
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public int saveTempletInfo(TempletInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TempletInfo.saveTempletInfo", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			LogHelper.debug("templetInfoService.saveTempletInfo:"
					+ e.getMessage() + getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title:删除模板信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public int deleteTempletInfo(String requestNo) {

		int ret = 0;
		try {
			Object o = getSqlMap().delete("TempletInfo.deleteTempletInfo",
					requestNo);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TempletInfo.deleteTempletInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询模板列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public List<TempletInfoObj> queryTempletList(TempletInfoObj obj) {
		List<TempletInfoObj> list = new ArrayList<TempletInfoObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TempletInfo.queryTempletForCount", obj))
								.intValue());
			}
			list = getSqlMap()
					.queryForList("TempletInfo.queryTempletList", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TempletInfo.queryTempletList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public TempletInfoObj queryByObj(TempletInfoObj obj) {
		List lst = null;
		TempletInfoObj tempObj = null;
		lst = queryTempletList(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TempletInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据工单编号查询资源信息
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public TempletInfoObj queryResourceListInfo(String num) {
		TempletInfoObj obj = new TempletInfoObj();
		try {
			obj = (TempletInfoObj) getSqlMap().queryForObject(
					"TempletInfo.queryResourceListInfo", num);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TempletInfo.queryResourceListInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return obj;
	}

}
