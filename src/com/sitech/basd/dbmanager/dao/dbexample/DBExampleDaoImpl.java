package com.sitech.basd.dbmanager.dao.dbexample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.dbmanager.domain.dbexample.DBExampleObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("dBExampleDao")
public class DBExampleDaoImpl extends BaseDao implements DBExampleDao {

	@Override
	public List<DBExampleObj> queryExampleList(DBExampleObj obj) {
		List<DBExampleObj> list = new ArrayList<DBExampleObj>();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("example.queryForCount", obj))
								.intValue()); // 分页查询的基本信息
			}
			list = getSqlMap().queryForList("example.queryExampleList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("example.queryExampleList:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	@Override
	public int insertExample(DBExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("example.insertExample", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("example.insertExample:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public int updateExample(DBExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("example.updateExample", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("example.updateExample:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public int deleteExample(DBExampleObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("example.deleteExample", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
			LogHelper.error("example.deleteExample:" + e.getMessage());
		}
		return ret;
	}

	@Override
	public List<DBExampleObj> queryExampleAndUserManagerBy(DBExampleObj obj) {
		List<DBExampleObj> list = new ArrayList<DBExampleObj>();
		try {
			list = getSqlMap().queryForList("example.queryExampleAndUserManagerBy", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("example.queryExampleAndUserManagerBy:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}

}
