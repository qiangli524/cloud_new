package com.sitech.basd.yicloud.dao.vmman;

import java.sql.SQLException;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;

public class VMHostDaoImpl extends BaseDao implements VMHostDao {

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.dao.vmman.VMHostDao#insertByVMhostObj(com.sitech.basd.yicloud.domain.vmman.VMHostObj)   
	*/
	public int insertByVMhostObj(VMHostObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap()
					.insert("VMHostInfoYi.insertByVMhostObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.dao.vmman.VMHostDao#queryVhostIdSequence()   
	*/
	public int queryVhostIdSequence() {
		int ret = 0;
		try {
			Object obj = getSqlMap().queryForObject(
					"VMHostInfoYi.queryVhostIdSequence");
			if (null != obj) {
				ret = Integer.valueOf(obj.toString());
			}
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfoYi.queryVhostIdSequence:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.dao.vmman.VMHostDao#queryVMHostInfo(com.sitech.basd.yicloud.domain.vmman.VMHostObj)   
	*/
	public List<VMHostObj> queryVMHostInfo(VMHostObj obj) {
		List<VMHostObj> objList = null;
		try {
			objList = getSqlMap().queryForList("VMHostInfoYi.queryVMHostInfo",
					obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfoYi.queryVMHostInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return objList;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.dao.vmman.VMHostDao#updateVMHostStatusInfo(com.sitech.basd.yicloud.domain.vmman.VMHostObj)   
	*/
	public int updateVMHostStatusInfo(VMHostObj obj) {
		int ret = 0;
		try {
			ret = getSqlMap()
					.update("VMHostInfoYi.updateVMHostStatusInfo", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfoYi.updateVMHostStatusInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	/* (non-Javadoc)   
	* @see com.sitech.basd.yicloud.dao.vmman.VMHostDao#queryHostListByObj()   
	*/
	public List queryHostListByObj() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("VMHostInfoYi.queryHostListByObj");
		} catch (Exception sqlexception) {
			LogHelper.error("VMHostInfoYi.queryHostListByObj:"
					+ sqlexception.getMessage());
		}
		return lst;
	}

}
