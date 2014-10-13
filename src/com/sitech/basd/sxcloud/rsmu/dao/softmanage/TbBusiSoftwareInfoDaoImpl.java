package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiSoftwareInfoDaoImpl extends BaseDao implements TbBusiSoftwareInfoDao {

	/**
	 * @Title:删除软件信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbBusiSoftwareInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TbBusiSoftwareInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiSoftwareInfo.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:插入软件信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiSoftwareInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TbBusiSoftwareInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiSoftwareInfo.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询出具体软件信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbBusiSoftwareInfoObj queryByObj(TbBusiSoftwareInfoObj obj) {
		List lst = new ArrayList();
		TbBusiSoftwareInfoObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TbBusiSoftwareInfoObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:根据软件部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiSoftwareInfoObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiSoftwareInfo.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("TbBusiSoftwareInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiSoftwareInfo.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:更新软件信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbBusiSoftwareInfoObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("TbBusiSoftwareInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiSoftwareInfo.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title: queryForListByObjNew
	 * @Description:根据对象查询所有
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午4:37:30
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbBusiSoftwareInfoObj> queryForListByObjNew(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj) {
		List<TbBusiSoftwareInfoObj> list = new ArrayList<TbBusiSoftwareInfoObj>();
		try {
			if (tbBusiSoftwareInfoObj.getPagination() != null) {
				tbBusiSoftwareInfoObj.setFIRSTROWNUM(tbBusiSoftwareInfoObj.getPagination()
						.getFirstRownum());
				tbBusiSoftwareInfoObj.setPAGESIZE(tbBusiSoftwareInfoObj.getPagination()
						.getPageSize());
				tbBusiSoftwareInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("TbBusiSoftwareInfo.countByObj",
								tbBusiSoftwareInfoObj)).intValue());
			}
			list = getSqlMap().queryForList("TbBusiSoftwareInfo.queryForListByObjNew",
					tbBusiSoftwareInfoObj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSoftwareInfo.queryForListByObjNew: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: queryForListByAppidUseIn
	 * @Description: 使用in语句根据应用id集合查询符合条件的软件信息
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午5:18:49
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TbBusiSoftwareInfoObj> queryForListByAppidUseIn(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj) {
		List<TbBusiSoftwareInfoObj> list = new ArrayList<TbBusiSoftwareInfoObj>();
		try {
			if (tbBusiSoftwareInfoObj.getPagination() != null) {
				tbBusiSoftwareInfoObj.setFIRSTROWNUM(tbBusiSoftwareInfoObj.getPagination()
						.getFirstRownum());
				tbBusiSoftwareInfoObj.setPAGESIZE(tbBusiSoftwareInfoObj.getPagination()
						.getPageSize());
				tbBusiSoftwareInfoObj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiSoftwareInfo.countByAppidUseIn", tbBusiSoftwareInfoObj))
								.intValue());
			}
			list = getSqlMap().queryForList("TbBusiSoftwareInfo.queryForListByAppidUseIn",
					tbBusiSoftwareInfoObj);
		} catch (Exception e) {
			LogHelper.error("TbBusiSoftwareInfo.queryForListByAppidUseIn: " + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}

}
