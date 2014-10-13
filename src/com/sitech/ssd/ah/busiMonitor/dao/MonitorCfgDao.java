package com.sitech.ssd.ah.busiMonitor.dao;

import java.util.List;

import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.ssd.ah.busiMonitor.domain.UserManageObj;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

public interface MonitorCfgDao {
	public List<MonitorCfgObj> queryMonitorCfgObjList(MonitorCfgObj obj);

	public int addMonitorCfgObj(MonitorCfgObj obj);
	
	public int updateMonitorCfgObj(MonitorCfgObj obj);
	
	public int delMonitorCfgObj(MonitorCfgObj obj);
	
	public MonitorCfgObj queryMonitorCfgObj(MonitorCfgObj obj);
	//查询物理主机列表
	public List<GreenPlumHostInfoObj> queryHostsList(PaasBusiTreeObj obj);
	//查询虚拟主机列表
	public List<VmReportForm> queryVmHostsList(PaasBusiTreeObj obj);
	
	public List<UserManageObj> queryUserByIpList(String ip);
	
	public List<String> queryHostIpByVmHostList(String vmId,String connectId);

}
