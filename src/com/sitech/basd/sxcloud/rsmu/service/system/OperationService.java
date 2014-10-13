package com.sitech.basd.sxcloud.rsmu.service.system;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;

public interface OperationService {
	/**
	 * @Title:插入操作日志信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int insertByObj(TbSysOperationLogObj obj);
}
