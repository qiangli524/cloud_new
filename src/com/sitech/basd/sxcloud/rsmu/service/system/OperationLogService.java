package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;

public interface OperationLogService {
	/**
	 * @Title:获得操作日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysOperationLogObj obj);
	
	/**
	 * @Title:根据条件查询操作日志列表
	 * @Copyright: Copyright (c) 2012-10-24
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryOperationLogList(TbSysOperationLogObj obj);
}
