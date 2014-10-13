package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class TbBusiDeployVideorecordDaoImpl extends BaseDao implements
		TbBusiDeployVideorecordDao {

	/**
	 * @Title:录像过程记录列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public List queryForListByObj(TbBusiDeployVideorecordObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiDeployVideorecord.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbBusiDeployVideorecord.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployVideorecord.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	* @Title:录像过程记录
	* @Copyright: Copyright (c) 201006
	* @Company: si-tech
	* @author yangwenchao
	* @version 1.0
	*/
	public int insertByObj(TbBusiDeployVideorecordObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert(
					"TbBusiDeployVideorecord.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployVideorecord.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public List queryIDListByObj(TbBusiDeployVideorecordObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TbBusiDeployVideorecord.queryByObjForCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"TbBusiDeployVideorecord.queryIDListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("TbBusiDeployVideorecord.queryIDListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:获得执行编号
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	*/
	public int queryForID_FREQ_SEQUENCES() {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"TbBusiDeployVideorecord.queryForID_FREQ_SEQUENCES");
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper
					.error("TbBusiDeployVideorecord.queryForID_FREQ_SEQUENCES:"
							+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询序列作为videoid
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	*/
	public int queryForVideoId() {
		int ret = 0;
		try {
			Object o = getSqlMap().queryForObject(
					"TbBusiDeployVideorecord.queryForVideoId");
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TbBusiDeployVideorecord.queryForVideoId:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
}
