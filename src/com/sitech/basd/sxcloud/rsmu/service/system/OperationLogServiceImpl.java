package com.sitech.basd.sxcloud.rsmu.service.system;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysOperationLogDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class OperationLogServiceImpl extends BaseService implements
		OperationLogService {
	private TbSysOperationLogDao tbSysOperationLogDao;

	public TbSysOperationLogDao getTbSysOperationLogDao() {
		return tbSysOperationLogDao;
	}

	public void setTbSysOperationLogDao(
			TbSysOperationLogDao tbSysOperationLogDao) {
		this.tbSysOperationLogDao = tbSysOperationLogDao;
	}

	/**
	 * @Title:获得操作日志列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author hubo
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysOperationLogObj obj) {
		return this.tbSysOperationLogDao.queryForListByObj(obj);
	}
	
	/**
	 * @Title:根据条件查询操作日志列表
	 * @Copyright: Copyright (c) 2012-10-24
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryOperationLogList(TbSysOperationLogObj obj){
		return tbSysOperationLogDao.queryOperationLogList(obj);
	}

}
