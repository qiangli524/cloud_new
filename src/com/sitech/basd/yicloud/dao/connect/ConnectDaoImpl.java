package com.sitech.basd.yicloud.dao.connect;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.connect.ConnectObj;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;

public class ConnectDaoImpl extends BaseDao implements ConnectDao {

	/**
	 * @Title:查询接口连通数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 11:50:58 AM
	 * @version 1.0
	 */
	public List queryForListByObj(ConnectObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"ConnectInfo.queryByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap()
					.queryForList("ConnectInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("ConnectInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:获取A，Z端外围接口下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 2:08:53 PM
	 * @version 1.0
	 */
	public List queryForListByInterface(ConnectObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"ConnectInfo.queryForListByInterface", obj);
		} catch (Exception e) {
			LogHelper.error("ConnectInfo.queryForListByInterface:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:获取连通状态下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:22:29 PM
	 * @version 1.0
	 */
	public List queryForListByConStatus(DictionaryObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList(
					"ConnectInfo.queryForListByConStatus", obj);
		} catch (Exception e) {
			LogHelper.error("ConnectInfo.queryForListByConStatus:"
					+ e.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:添加接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:22:50 PM
	 * @version 1.0
	 */
	public int insertByObj(ConnectObj obj) {
		int result = 0;

		try {
			Object object = getSqlMap().insert("ConnectInfo.insertByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("ConnectInfo.insertByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	/**
	 * @Title:修改接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 4:48:11 PM
	 * @version 1.0
	 */
	public int updateByObj(ConnectObj obj) {
		int result = 0;
		try {
			Object object = getSqlMap().update("ConnectInfo.updateByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("ConnectInfo.updateByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	/**
	 * @Title:根据ID查询接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 18, 2012 5:10:43 PM
	 * @version 1.0
	 */
	public ConnectObj queryByObj(ConnectObj obj) {
		ConnectObj connectObj = null;
		List connList = null;
		connList = queryForListByObj(obj);
		if (connList != null && connList.size() != 0) {
			connectObj = (ConnectObj) connList.get(0);
		}
		return connectObj;
	}

	/**
	 * @Title:根据ID删除接口连通信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : Apr 19, 2012 11:19:52 AM
	 * @version 1.0
	 */
	public int deleteByObj(ConnectObj obj) {
		int result = 0;
		Object object;
		try {
			object = getSqlMap().delete("ConnectInfo.deleteByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("ConnectInfo.deleteByObj:" + e.getMessage()
					+ getClass().getName());

		}

		return result;
	}

}
