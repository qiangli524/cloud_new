package com.sitech.basd.envmanager.dao.ledger;

import java.util.List;

import com.sitech.basd.cloud3.domain.machineroom.MachineRoomObj;
import com.sitech.basd.envmanager.domain.ledger.LedgerObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class LedgerDaoImpl extends BaseDao implements  LedgerDao{
	/**
	 * @Title:查询所有设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryLedgerObj(LedgerObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Ledger.queryLedgerObjCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Ledger.queryLedgerObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Ledger.queryLedgerObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	/**
	 * @Title:查询某个设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public LedgerObj queryLedgerOne(LedgerObj obj) {
		LedgerObj lObj=null;
		try {
			lObj= (LedgerObj) getSqlMap().queryForObject("Ledger.queryLedgerOne",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Ledger.queryLedgerOne:" 
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lObj;
	}
	/**
	 * @Title:删除设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int deleteLedgerObj(LedgerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Ledger.deleteLedgerObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Ledger.deleteLedgerObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:增加设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int insertLedgerObj(LedgerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Ledger.insertLedgerObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Ledger.insertLedgerObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:修改设备信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public int updateLedgerObj(LedgerObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Ledger.updateLedgerObj", obj);
			if(o!=null){ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Ledger.updateLedgerObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

}
