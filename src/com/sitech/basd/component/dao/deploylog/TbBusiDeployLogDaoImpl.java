package com.sitech.basd.component.dao.deploylog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.component.domain.deploylog.TbBusiDeployLog;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

@Repository("tbBusiDeployLogDao")
public class TbBusiDeployLogDaoImpl extends BaseDao implements TbBusiDeployLogDao{

	public List<TbBusiDeployLog> queryDeployLogList(TbBusiDeployLog obj) {
		List<TbBusiDeployLog> list = new ArrayList<TbBusiDeployLog>();
		try {
			list = getSqlMap().queryForList("TbBusiDeployLog.queryBusiDeployLogList", obj);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			LogHelper.error("TbBusiDeployLog.queryBusiDeployLogLis:" + sqlException.getMessage()
					+ getClass().getName());
		}
		return list;
	}
}
