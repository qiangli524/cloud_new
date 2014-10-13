package com.sitech.basd.sxcloud.rsmu.dao.deploy;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.ExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class ExampleResumeDaoImpl extends BaseDao implements ExampleResumeDao {

	public int deleteByObj(ExampleResumeObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().delete("ExampleResume.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ExampleResume.deleteByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(ExampleResumeObj obj) {
		// TODO Auto-generated method stub
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject("ExampleResume.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("ExampleResume.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("ExampleResume.queryForListByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public int resumeByObj(ExampleResumeObj obj) {

		// TODO Auto-generated method stub
		int ret = 0;
		try {
			Object o = getSqlMap().update("ExampleResume.resumeByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("ExampleResume.resumeByObj:" + sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	public void test(ExampleResumeObj obj) throws Exception {
		getSqlMap().queryForObject("ExampleResume.queryByObjForCount", obj);
	}
}
