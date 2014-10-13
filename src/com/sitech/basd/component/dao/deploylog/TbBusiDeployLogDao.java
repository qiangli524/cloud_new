package com.sitech.basd.component.dao.deploylog;

import java.util.List;

import com.sitech.basd.component.domain.deploylog.TbBusiDeployLog;

public interface TbBusiDeployLogDao {
	public List<TbBusiDeployLog> queryDeployLogList(TbBusiDeployLog obj);

}