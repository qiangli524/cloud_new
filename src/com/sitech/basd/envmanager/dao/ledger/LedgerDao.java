package com.sitech.basd.envmanager.dao.ledger;

import java.util.List;

import com.sitech.basd.envmanager.domain.ledger.LedgerObj;

public interface LedgerDao {
	/**
	 * @Title:查询所有设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public List queryLedgerObj(LedgerObj obj);
	/**
	 * @Title:查询某个设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public LedgerObj queryLedgerOne(LedgerObj obj);
	/**
	 * @Title:添加设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int insertLedgerObj(LedgerObj obj);
	/**
	 * @Title:修改设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateLedgerObj(LedgerObj obj);
	/**
	 * @Title:删除设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int deleteLedgerObj(LedgerObj obj);


}
