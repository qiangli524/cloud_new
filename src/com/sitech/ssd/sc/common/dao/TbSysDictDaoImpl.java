package com.sitech.ssd.sc.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.common.domain.TbSysDictObj;

/**
 * 
 * @ClassName: TbSysDictDaoImpl
 * @Description: 鏁版嵁瀛楀吀DAO瀹炵幇绫�
 * @author JamTau
 * @date 2014-8-27 涓嬪崍2:57:57
 * 
 */
@Repository("tbSysDictDao")
public class TbSysDictDaoImpl extends BaseDao implements TbSysDictDao {

	@Override
	public int insertTbSysDict(TbSysDictObj obj) {
		int _ret = 0;
		try {
			getSqlMap().insert("TbSysDict.insertTbSysDict", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("TbSysDict.insertTbSysDict:" + e.getMessage() + getClass().getName());
		}
		return _ret;
	}

	@Override
	public int deleteTbSysDict(TbSysDictObj obj) {
		int _ret = 0;
		try {
			getSqlMap().delete("TbSysDict.deleteTbSysDict", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("TbSysDict.deleteTbSysDict:" + e.getMessage() + getClass().getName());
		}
		return _ret;
	}

	@Override
	public int updateTbSysDict(TbSysDictObj obj) {
		int _ret = 0;
		try {
			getSqlMap().update("TbSysDict.updateTbSysDict", obj);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("TbSysDict.updateTbSysDict:" + e.getMessage() + getClass().getName());
		}
		return _ret;
	}

	@Override
	public TbSysDictObj selectTbSysDict(TbSysDictObj obj) {
		Object ret = null;
		try {
			ret = getSqlMap().queryForObject("TbSysDict.selectTbSysDict", obj);
		} catch (SQLException e) {
			LogHelper.error("TbSysDict.selectTbSysDict:" + e.getMessage() + getClass().getName());
		}
		return ret == null ? new TbSysDictObj() : (TbSysDictObj) ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TbSysDictObj> selectTbSysDictList(TbSysDictObj obj) {
		List<TbSysDictObj> _list = null;
		try {
			_list = getSqlMap().queryForList("TbSysDict.selectTbSysDictList", obj);
		} catch (SQLException e) {
			LogHelper.error("TbSysDict.selectTbSysDictList:" + e.getMessage()
					+ getClass().getName());
		}

		return _list;
	}

}
