package com.sitech.basd.sxcloud.rsmu.service.system;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysOperationLogDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class OperationServiceImpl extends BaseService implements
		OperationService {
	public int insertByObj(TbSysOperationLogObj obj) {
		return tbSysOperationLogDao.insertByObj(obj);
	}

	private TbSysOperationLogDao tbSysOperationLogDao;

	public void setTbSysOperationLogDao(
			TbSysOperationLogDao tbSysOperationLogDao) {
		this.tbSysOperationLogDao = tbSysOperationLogDao;
	}
}
