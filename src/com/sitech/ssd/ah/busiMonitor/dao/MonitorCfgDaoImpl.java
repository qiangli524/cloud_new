package com.sitech.ssd.ah.busiMonitor.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.ssd.ah.busiMonitor.domain.UserManageObj;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Repository("monitorCfgDao")
public class MonitorCfgDaoImpl extends BaseDao implements MonitorCfgDao {
	
	@Override
	public List<MonitorCfgObj> queryMonitorCfgObjList(MonitorCfgObj obj) {
		List list = null;
		try {
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"BusiMonitorCfg.queryByObjForCount",obj)).intValue());
				list = getSqlMap().queryForList("BusiMonitorCfg.queryForListByObj",obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("BusiMonitorCfg.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}
	
	@Override
	public MonitorCfgObj queryMonitorCfgObj(MonitorCfgObj obj){
		MonitorCfgObj ob = new MonitorCfgObj();
		try {
			ob = (MonitorCfgObj) getSqlMap().queryForObject("BusiMonitorCfg.queryByObj", obj);
		} catch (Exception e) {
			LogHelper.error("BusiMonitorCfg.queryByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ob;
	}
	
	@Override
	public int addMonitorCfgObj(MonitorCfgObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		String id = RandomUUID.getUuid();
		try {
			obj.setId(id);
			getSqlMap().insert("BusiMonitorCfg.addByObj", obj);
		} catch (SQLException e) {
			ret = -1;
			logger.error("BusiMonitorCfg.addByObj: " + e.getMessage() + e.getClass().getName());
		}
		return ret;
		
	}
	
	@Override
	public int updateMonitorCfgObj(MonitorCfgObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("BusiMonitorCfg.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BusiMonitorCfg.updateByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
		
	}

	@Override
	public int delMonitorCfgObj(MonitorCfgObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("BusiMonitorCfg.delByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("BusiMonitorCfg.delByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
	}

	@Override
	public List<GreenPlumHostInfoObj> queryHostsList(PaasBusiTreeObj obj) {
		List list = null;
		try {
			System.out.println(obj.getPagination());
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"BusiMonitorCfg.queryHostsListCount",obj)).intValue());
				list = getSqlMap().queryForList("BusiMonitorCfg.queryHostsList",obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("BusiMonitorCfg.queryHostsList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public List<VmReportForm> queryVmHostsList(PaasBusiTreeObj obj) {
		List list = null;
		try {
			if(obj.getPagination() != null){
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer)getSqlMap().queryForObject(
								"BusiMonitorCfg.queryVmHostsListCount",obj)).intValue());
				list = getSqlMap().queryForList("BusiMonitorCfg.queryVmHostsList",obj);
			}
		} catch (Exception sqlexception) {
			LogHelper.error("BusiMonitorCfg.queryVmHostsList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return list;
	}

	@Override
	public List<UserManageObj> queryUserByIpList(String ip) {
		List<UserManageObj> list= new ArrayList<UserManageObj>();
		VmReportForm obj = new VmReportForm();
		obj.setIp(ip);
		try {
			list =  getSqlMap().queryForList("BusiMonitorCfg.queryUserByIp", obj);
		} catch (Exception e) {
			LogHelper.error("BusiMonitorCfg.queryUserByIp: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}

	@Override
	public List<String> queryHostIpByVmHostList(String vmId,String connectId) {
		List<String> list= new ArrayList<String>();
		VmReportForm obj = new VmReportForm();
		obj.setVmId(vmId);
		obj.setVmName(connectId);
		try {
			list =  getSqlMap().queryForList("BusiMonitorCfg.queryHostIpByVmId", obj);
		} catch (Exception e) {
			LogHelper.error("BusiMonitorCfg.queryHostIpByVmId: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
	
}
