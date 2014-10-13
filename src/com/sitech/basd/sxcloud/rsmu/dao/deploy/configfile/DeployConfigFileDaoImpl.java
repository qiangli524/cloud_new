package com.sitech.basd.sxcloud.rsmu.dao.deploy.configfile;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.configfile.DeployConfigFile;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class DeployConfigFileDaoImpl extends BaseDao implements DeployConfigFileDao {

	public int deleteByObj(DeployConfigFile obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().delete("DeployConfig.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DeployConfig.deleteByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	
	public int insertByObj(DeployConfigFile obj) {

		int ret = 0;
		try {
			Object o = getSqlMap().insert("DeployConfig.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("DeployConfig.insertByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List queryForListByObj(DeployConfigFile obj) {
		List lst = null;
		try {
//			if (obj.getPagination() != null) {
//				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
//				obj.setPAGESIZE(obj.getPagination().getPageSize());
//				obj.getPagination().setTotalCount(((Integer) getSqlMap().queryForObject("ExampleResume.queryByObjForCount", obj)).intValue());
//			}
			lst = getSqlMap().queryForList("DeployConfig.queryForListByObj",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("DeployConfig.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

}
