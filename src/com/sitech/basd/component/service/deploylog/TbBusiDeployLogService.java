package com.sitech.basd.component.service.deploylog;

import java.util.List;

import com.sitech.basd.component.domain.deploylog.TbBusiDeployLog;

public interface TbBusiDeployLogService {
	public List<TbBusiDeployLog> queryDeployLogList(TbBusiDeployLog obj);

}
