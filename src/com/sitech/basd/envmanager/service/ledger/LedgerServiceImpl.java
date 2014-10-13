package com.sitech.basd.envmanager.service.ledger;

import java.util.List;

import com.sitech.basd.envmanager.dao.ledger.LedgerDao;
import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;

public class LedgerServiceImpl  extends BaseService implements LedgerService{
	
	
	LedgerDao ledgerDao;

	public LedgerDao getLedgerDao() {
		return ledgerDao;
	}

	public void setLedgerDao(LedgerDao ledgerDao) {
		this.ledgerDao = ledgerDao;
	}
	/**
	 * @Title:删除资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deleteLedgerObj(LedgerObj obj) {
		return ledgerDao.deleteLedgerObj(obj);
	}
	/**
	 * @Title:增加资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertLedgerObj(LedgerObj obj) {
		return ledgerDao.insertLedgerObj(obj);
	}
	/**
	 * @Title:查询所有设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryLedgerObj(LedgerObj obj) {
		return ledgerDao.queryLedgerObj(obj);
	}
	/**
	 * @Title:查询某个资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public LedgerObj queryLedgerOne(LedgerObj obj) {
		return ledgerDao.queryLedgerOne(obj);
	}
	/**
	 * @Title:修改资源使用情况
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateLedgerObj(LedgerObj obj) {
		return ledgerDao.updateLedgerObj(obj);
	}
	
	

}
