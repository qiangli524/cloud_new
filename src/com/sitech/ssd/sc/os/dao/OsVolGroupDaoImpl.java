package com.sitech.ssd.sc.os.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.sc.os.domain.OsVolGroupModel;

@Repository("osVolGroupDao")
public class OsVolGroupDaoImpl extends BaseDao implements OsVolGroupDao {

	@Override
	public int copyOsTemplateVolGroup(OsVolGroupModel vg) {
		int _ret = 0;
		try {
			getSqlMap().insert("OsVolGroup.copyOsTemplateVolGroup", vg);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsVolGroup.copyOsTemplateVolGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@Override
	public int deleteOsVolGroup(OsVolGroupModel vg) {
		int _ret = 0;
		try {
			_ret = getSqlMap().delete("OsVolGroup.deleteOsVolGroup", vg);
		} catch (SQLException e) {
			_ret = -1;
			LogHelper.error("OsVolGroup.deleteOsVolGroup:" + e.getMessage()
					+ getClass().getName());
		}
		return _ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsVolGroupModel> selectOsVolGroupList(OsVolGroupModel vg) {
		List<OsVolGroupModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsVolGroup.selectOsVolGroupList", vg);
		} catch (SQLException e) {
			LogHelper.error("OsVolGroup.selectOsVolGroupList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsVolGroupModel> unionOsVolGroupList(OsVolGroupModel vg) {
		List<OsVolGroupModel> _list = null;
		try {
			_list = getSqlMap().queryForList("OsVolGroup.unionOsVolGroupList", vg);
		} catch (SQLException e) {
			LogHelper.error("OsVolGroup.unionOsVolGroupList:" + e.getMessage()
					+ getClass().getName());
		}
		return _list;
	}
}
