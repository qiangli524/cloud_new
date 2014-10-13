package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;

public interface LoginLogService {
	/**
	 * @Title:获得登录日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysLoginLogObj obj);
}
