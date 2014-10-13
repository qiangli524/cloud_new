package com.sitech.basd.component.service.deploylog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sitech.basd.component.dao.deploylog.TbBusiDeployLogDao;
import com.sitech.basd.component.domain.deploylog.TbBusiDeployLog;

@Repository("TbBusiDeployLogService")
public class TbBusiDeployLogServiceImpl implements TbBusiDeployLogService{
	
	@Autowired
	private TbBusiDeployLogDao tbBusiDeployLogDao;

	@Override
	public List<TbBusiDeployLog> queryDeployLogList(TbBusiDeployLog obj) {
		return tbBusiDeployLogDao.queryDeployLogList(obj);
	}
}
