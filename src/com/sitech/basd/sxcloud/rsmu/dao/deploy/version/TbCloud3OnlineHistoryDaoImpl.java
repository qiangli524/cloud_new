package com.sitech.basd.sxcloud.rsmu.dao.deploy.version;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

/**
 * 
 * <p>
 * Title: TbCloud3AppVersionDao
 * </p>
 * <p>
 * Description: 应用版本Dao
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-3-23 上午10:20:56
 * 
 */
public class TbCloud3OnlineHistoryDaoImpl extends BaseDao implements
		TbCloud3OnlineHistoryDao {

	/**
	 * 
	 * @Title: insertByVO
	 * @Description: 插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:45
	 */
	public String insertByVO(TbCloud3OnlineHistoryVO obj) {
		String ret = "";
		try {
			Object o = getSqlMap().insert("TbCloud3OnlineHistory.insertByVO",
					obj);
			if (o != null) {
				ret = o.toString();
			}
		} catch (SQLException e) {
			ret = "-1";
			LogHelper.error("TbCloud3OnlineHistory.insertByVO:"
					+ e.getMessage());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryListByObj
	 * @Description: 查询上线历史数据
	 * @param
	 * @return TbCloud3AppVersionDao
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-23 上午10:21:33
	 */
	public List<TbCloud3OnlineHistoryVO> queryListByObj(
			TbCloud3OnlineHistoryVO obj) {
		List<TbCloud3OnlineHistoryVO> lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"TbCloud3OnlineHistory.queryByObjForCount",
												obj)).intValue());
			}
			lst = (List<TbCloud3OnlineHistoryVO>) getSqlMap().queryForList(
					"TbCloud3OnlineHistory.queryListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("TbCloud3OnlineHistory.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

}
