package com.sitech.basd.sxcloud.rsmu.dao.upgrade;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class UpgradeExampleResumeDaoImpl extends BaseDao implements
		UpgradeExampleResumeDao {

	public int deleteByObj(UpgradeExampleResumeObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("UpgradeExampleResume.deleteByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExampleResume.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(UpgradeExampleResumeObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"UpgradeExampleResume.queryByObjForCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"UpgradeExampleResume.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			LogHelper.error("UpgradeExampleResume.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int resumeByObj(UpgradeExampleResumeObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("UpgradeExampleResume.resumeByObj",
					obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("UpgradeExampleResume.resumeByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public void test(UpgradeExampleResumeObj obj) throws SQLException {
		getSqlMap().queryForObject("UpgradeExampleResume.queryByObjForCount",
				obj);
	}

	public static void main(String[] args) throws SQLException {
		UpgradeExampleResumeDaoImpl dao = new UpgradeExampleResumeDaoImpl();
		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		// List list=dao.queryForListByObj(obj);
		dao.test(obj);
	}
}
