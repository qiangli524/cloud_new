package com.sitech.basd.yicloud.dao.switches;

import java.util.List;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.switches.SwitchObj;

public class SwitchesDaoImpl extends BaseDao implements SwitchesDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description:查询列表（包括路由器和交换机）
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:36:50 AM
	 */
	public List queryForListByObj(SwitchObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("SwitchInfo.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("SwitchInfo.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 添加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int insertByObj(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("SwitchInfo.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SwitchInfo.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 修改一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int updateByObj(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("SwitchInfo.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SwitchInfo.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:20:10 AM
	 */
	public int deleteByObj(SwitchObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("SwitchInfo.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("SwitchInfo.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return SwitchObj
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 28, 2012 11:22:30 AM
	 */
	public SwitchObj queryByObj(SwitchObj obj) {
		List lst = null;
		SwitchObj tObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tObj = (SwitchObj) lst.get(0);
		}
		return tObj;
	}

	/**
	 * 
	 * @Title: getIdSequence
	 * @Description: 查询路由器（交换机）的ID
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 18, 2012 9:44:10 AM
	 */
	public String getIdSequence() {
		String ret="0";
		try {
			Object o =getSqlMap().queryForObject(
					"SwitchInfo.getIdSequence");
			if(o!=null){
				ret = o.toString();
			}
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
		return ret;
	}
}
