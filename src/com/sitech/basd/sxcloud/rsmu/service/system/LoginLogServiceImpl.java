package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysLoginLogDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class LoginLogServiceImpl extends BaseService implements LoginLogService {
	private TbSysLoginLogDao tbSysLoginLogDao;

	public TbSysLoginLogDao getTbSysLoginLogDao() {
		return tbSysLoginLogDao;
	}

	public void setTbSysLoginLogDao(TbSysLoginLogDao tbSysLoginLogDao) {
		this.tbSysLoginLogDao = tbSysLoginLogDao;
	}

	/**
	 * @Title:获得登录日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysLoginLogObj obj) {
		return this.tbSysLoginLogDao.queryForListByObj(obj);
	}

}
