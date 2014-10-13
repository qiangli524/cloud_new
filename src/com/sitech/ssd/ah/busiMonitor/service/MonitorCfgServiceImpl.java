package com.sitech.ssd.ah.busiMonitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.busiMonitor.dao.MonitorCfgDao;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.ssd.ah.busiMonitor.domain.UserManageObj;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

@Service("monitorCfgService")
public class MonitorCfgServiceImpl implements MonitorCfgService {

	@Autowired
	MonitorCfgDao monitorCfgDao;
	
	@Override
	public List<MonitorCfgObj> queryMonitorCfgObjList(MonitorCfgObj obj) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryMonitorCfgObjList(obj);
	}

	@Override
	public int delMonitorCfgObj(MonitorCfgObj obj) {
		
		return monitorCfgDao.delMonitorCfgObj(obj);
	}

	@Override
	public int saveMonitorCfgObj(MonitorCfgObj obj) {
		// TODO Auto-generated method stub
		if(obj.getId() != null && !obj.getId().equals("")){
			return monitorCfgDao.updateMonitorCfgObj(obj);
		}else{
			return monitorCfgDao.addMonitorCfgObj(obj);
		}
	}

	@Override
	public MonitorCfgObj queryMonitorCfgObj(MonitorCfgObj obj) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryMonitorCfgObj(obj);
	}

	@Override
	public List<GreenPlumHostInfoObj> queryHostsList(PaasBusiTreeObj obj) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryHostsList(obj);
	}

	@Override
	public List<VmReportForm> queryVmHostsList(PaasBusiTreeObj obj) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryVmHostsList(obj);
	}

	@Override
	public List<UserManageObj> queryUserByIpList(String ip) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryUserByIpList(ip);
	}

	@Override
	public List<String> queryIpByVmHostList(String vmId,String connectId) {
		// TODO Auto-generated method stub
		return monitorCfgDao.queryHostIpByVmHostList(vmId,connectId);
	}

}
