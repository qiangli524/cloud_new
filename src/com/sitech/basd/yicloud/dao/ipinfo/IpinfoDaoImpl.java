package com.sitech.basd.yicloud.dao.ipinfo;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.dictionary.DictionaryObj;
import com.sitech.basd.yicloud.domain.ipinfo.IpinfoObj;

public class IpinfoDaoImpl extends BaseDao implements IpinfoDao {

	/**
	 * @Title:获取ip数据结果集
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByObj(IpinfoObj obj) {
		List list = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"IpInfo.queryByObjForCount", obj)).intValue());
			}
			list = getSqlMap().queryForList("IpInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("IpInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:获取是否使用下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByUsed(DictionaryObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("IpInfo.queryForListByUsed", obj);
		} catch (Exception e) {
			LogHelper.error("IpInfo.queryForListByUsed:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:获取是否被阻塞下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByBlocked(DictionaryObj obj) {
		List list = null;
		try {
			list = getSqlMap()
					.queryForList("IpInfo.queryForListByBlocked", obj);
		} catch (Exception e) {
			LogHelper.error("IpInfo.queryForListByBlocked:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:获取IP类型下拉框的值
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-7
	 * @version 1.0
	 */
	public List queryForListByType(DictionaryObj obj) {
		List list = null;
		try {
			list = getSqlMap().queryForList("IpInfo.queryForListByType", obj);
		} catch (Exception e) {
			LogHelper.error("IpInfo.queryForListByType:" + e.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title:添加IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-8
	 * @version 1.0
	 */
	public int insertByObj(IpinfoObj obj) {
		int result = 0;
		try {
			Object object = getSqlMap().insert("IpInfo.insertByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("IpInfo.insertByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return result;
	}

	/**
	 * @Title:根据ID查询IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */

	public IpinfoObj queryByObj(IpinfoObj obj) {
		IpinfoObj ipinfoObj = null;
		List ipinfoList = null;
		ipinfoList = queryForListByObj(obj);
		if (ipinfoList != null && ipinfoList.size() != 0) {
			ipinfoObj = (IpinfoObj) ipinfoList.get(0);
		}
		return ipinfoObj;
	}

	/**
	 * @Title:修改IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int updateByObj(IpinfoObj obj) {

		int result = 0;
		try {
			Object object = getSqlMap().update("IpInfo.updateByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("IpInfo.updateByObj:" + e.getMessage()
					+ getClass().getName());
		}
		return result;

	}

	/**
	 * @Title:删除IP池信息
	 * @author：lzh
	 * @Company: si-tech
	 * @date : 2012-6-12
	 * @version 1.0
	 */
	public int deleteByObj(IpinfoObj obj) {
		int result = 0;
		Object object;
		try {
			object = getSqlMap().delete("IpInfo.deleteByObj", obj);
			if (object != null) {
				result = Integer.parseInt(object.toString());
			}
		} catch (Exception e) {
			result = -1;
			LogHelper.error("IpInfo.deleteByObj:" + e.getMessage()
					+ getClass().getName());

		}

		return result;
	}
	
	public String queryIpUnusedAndUsedCount(String net_id){
		String ret = null;
		try {
			ret = (String)getSqlMap().queryForObject("IpInfo.queryIpUnusedAndUsedCount", net_id);
		} catch (Exception e) {
			LogHelper.error("IpInfo.queryIpUnusedAndUsedCount:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
	public String queryAllIpUnusedAndUsedCount(){
		String ret = null;
		try {
			ret = (String)getSqlMap().queryForObject("IpInfo.queryAllIpUnusedAndUsedCount");
		} catch (Exception e) {
			LogHelper.error("IpInfo.queryAllIpUnusedAndUsedCount:" + e.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	@Override
	public void updateIpState() {
		try {
			getSqlMap().update("IpInfo.updateIpState");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateIpStateByIp(String ip) {
		try {
			getSqlMap().update("IpInfo.updateIpStateByIp",ip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
