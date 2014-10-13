package com.sitech.ssd.ah.busiMonitor.service;

import java.util.List;

import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.ssd.ah.busiMonitor.domain.UserManageObj;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

public interface MonitorCfgService {
	public List<MonitorCfgObj> queryMonitorCfgObjList(MonitorCfgObj obj);
	public int saveMonitorCfgObj(MonitorCfgObj obj);
	public int delMonitorCfgObj(MonitorCfgObj obj);
	public MonitorCfgObj queryMonitorCfgObj(MonitorCfgObj obj);
	//查询物理主机list
	public List<GreenPlumHostInfoObj> queryHostsList(PaasBusiTreeObj obj);
	//查询虚拟主机list
	public List<VmReportForm> queryVmHostsList(PaasBusiTreeObj obj); 
	
	public List<UserManageObj> queryUserByIpList(String ip);//根据IP查询用户list
	public List<String> queryIpByVmHostList(String vmId,String connectId);//根据主机ID查询IP list
}
