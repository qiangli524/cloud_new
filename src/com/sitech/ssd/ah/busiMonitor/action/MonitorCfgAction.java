package com.sitech.ssd.ah.busiMonitor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.ssd.ah.busiMonitor.domain.UserManageObj;
import com.sitech.ssd.ah.busiMonitor.service.MonitorCfgService;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.busiResource.service.statistics.PaasBusiStatisticsService;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;

@SuppressWarnings("unchecked")
@Controller("monitorCfgAction")
@Scope("prototype")
public class MonitorCfgAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MonitorCfgAction.class);
	
	@Autowired
	MonitorCfgService monitorCfgService;
	
	//查询物理主机及虚拟主机信息service
	@Autowired
	PaasBusiStatisticsService  paasBusiStatisticsService ;
	
	private MonitorCfgObj obj;
	private List<MonitorCfgObj> resultList;
	private String id;
	
	private List<GreenPlumHostInfoObj> hostList;//物理主机list
	private List<VmReportForm> vmHostList;//虚拟主机list
	private String flag;//查询主机类型标示:1:物理主机,2:虚拟主机
	private GreenPlumHostInfoObj gphiObj;//物理主机基础类
	private VmReportForm vrfObj;//虚拟主机基础类
	private PaasBusiTreeObj pObj;//主机查询条件类
	private List<UserManageObj> userList;//根据IP查询用户结果list
	private String hostip;//主机IP
	private Map<String,String> kpiIdMap;
	
	private List hostIpList;//根据虚拟主机id查询IP结果list
	private String vmId;//主机ID
	private String connectId;
	
	//物理主机，虚拟主机查询条件
	private String eq_name;
	private String eq_ip;
	private String vmName;
	private String vmIp;
	
	//修改操作时临时参数
	private String extEqIdTemp;  //采集主机唯一标识，物理主机：主机ip_序列号，虚拟机：vcenterid_虚拟机id
	private String hostIpTemp;  //采集主机IP
	private String userIdTemp;  //用户ID
	private int delResult;
		
	public String queryList() throws BaseException {
		if(obj == null){
			obj = new MonitorCfgObj();
		}
		if(obj.getKpiName() != null && !"".equals(obj.getKpiName())){
			if("话务量监控".equals(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-01");
			}else if("目录文件积压量".equals(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-02");
			}else if("错误日志监控".equals(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-03");
			}else if("流量查询服务监控".equals(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-04");
			}else if("端口收发监控".equals(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-05");
			}else if("提醒服务监控".contains(obj.getKpiName())){
				obj.setKpiId("PM-01-01-001-06");
			}
		}
		obj.setStatus("1");
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = monitorCfgService.queryMonitorCfgObjList(obj);
		return "list";
	}
	
	public String addInfo() throws BaseException {
		kpiIdMap = new HashMap<String,String>();
		kpiIdMap.put("PM-01-01-001-01", "话务量监控");
		kpiIdMap.put("PM-01-01-001-02", "目录文件积压量");
		kpiIdMap.put("PM-01-01-001-03", "错误日志监控");
		kpiIdMap.put("PM-01-01-001-04", "流量查询服务监控");
		kpiIdMap.put("PM-01-01-001-05", "端口收发监控");
		kpiIdMap.put("PM-01-01-001-06", "提醒服务监控");
		return "add";
	}
	
	public String updateInfo() throws BaseException {
		if(obj == null){
			obj = new MonitorCfgObj();
		}
		kpiIdMap = new HashMap<String,String>();
		kpiIdMap.put("PM-01-01-001-01", "话务量监控");
		kpiIdMap.put("PM-01-01-001-02", "目录文件积压量");
		kpiIdMap.put("PM-01-01-001-03", "错误日志监控");
		kpiIdMap.put("PM-01-01-001-04", "流量查询服务监控");
		kpiIdMap.put("PM-01-01-001-05", "端口收发监控");
		kpiIdMap.put("PM-01-01-001-06", "提醒服务监控");
		obj.setId(id);
		obj = monitorCfgService.queryMonitorCfgObj(obj);
		userList = monitorCfgService.queryUserByIpList(obj.getHostIp());
		
		extEqIdTemp = obj.getExtEqId();
		hostIpTemp = obj.getHostIp();
		userIdTemp = obj.getUserId();
		
		return "update";
	}
	
	public String delInfo() throws BaseException {
		if(obj == null){
			obj = new MonitorCfgObj();
		}
//		System.out.println("id:" + id);
		obj.setId(id);
		obj.setStatus("2");
		delResult = monitorCfgService.delMonitorCfgObj(obj);
		return "del";
	}
	
	public String saveInfo() throws BaseException {
		if (obj == null) {
			obj = new MonitorCfgObj();
		}
		obj.setStatus("1");
		monitorCfgService.saveMonitorCfgObj(obj);
		return "redirect";
	}
	//查询主机列表
	public String queryHostList() throws BaseException {
		if(pObj == null){
			pObj = new PaasBusiTreeObj();
		}
		
		HttpServletRequest request = Struts2Utils.getRequest();
		//根据类型查询主机 1:物理主机 2:虚拟机
		if("1".equals(flag)){
			pObj.setEq_name(request.getParameter("eq_name"));
			pObj.setEq_ip(request.getParameter("eq_ip"));
			pObj.setPagination(this.getPaginater().initPagination(request));
			hostList = monitorCfgService.queryHostsList(pObj);
			return "hostsList";
		}else {
			pObj.setVmName(vmName);
			pObj.setIp(vmIp);
			pObj.setPagination(this.getPaginater().initPagination(request));
			vmHostList = monitorCfgService.queryVmHostsList(pObj);
			return "vmHostsList";
		}
	}
	//根据虚拟主机id查询IP列表
	public String queryIpByVmHost(){
		hostIpList = new ArrayList();
		if(vmId != null && !"".equals(vmId)){
			hostIpList = monitorCfgService.queryIpByVmHostList(vmId,connectId);
		}
		return "hostIpList";
	}
	//根据IP地址查询主机用户列表
	public String queryUserByIp(){
		userList = new ArrayList();
		if(hostip != null && !"".equals(hostip)){
			userList = monitorCfgService.queryUserByIpList(hostip);
		}
		return "userList";
	}

	public MonitorCfgObj getObj() {
		return obj;
	}

	public void setObj(MonitorCfgObj obj) {
		this.obj = obj;
	}

	public List<MonitorCfgObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<MonitorCfgObj> resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<GreenPlumHostInfoObj> getHostList() {
		return hostList;
	}

	public void setHostList(List<GreenPlumHostInfoObj> hostList) {
		this.hostList = hostList;
	}

	public List<VmReportForm> getVmHostList() {
		return vmHostList;
	}

	public void setVmHostList(List<VmReportForm> vmHostList) {
		this.vmHostList = vmHostList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public GreenPlumHostInfoObj getGphiObj() {
		return gphiObj;
	}

	public void setGphiObj(GreenPlumHostInfoObj gphiObj) {
		this.gphiObj = gphiObj;
	}

	public VmReportForm getVrfObj() {
		return vrfObj;
	}

	public void setVrfObj(VmReportForm vrfObj) {
		this.vrfObj = vrfObj;
	}

	public PaasBusiTreeObj getpObj() {
		return pObj;
	}

	public void setpObj(PaasBusiTreeObj pObj) {
		this.pObj = pObj;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getEq_name() {
		return eq_name;
	}

	public void setEq_name(String eqName) {
		eq_name = eqName;
	}

	public String getEq_ip() {
		return eq_ip;
	}

	public void setEq_ip(String eqIp) {
		eq_ip = eqIp;
	}

	public String getVmName() {
		return vmName;
	}

	public void setVmName(String vmName) {
		this.vmName = vmName;
	}

	public String getVmIp() {
		return vmIp;
	}

	public void setVmIp(String vmIp) {
		this.vmIp = vmIp;
	}

	public List getHostIpList() {
		return hostIpList;
	}

	public void setHostIpList(List hostIpList) {
		this.hostIpList = hostIpList;
	}

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}

	public String getConnectId() {
		return connectId;
	}

	public void setConnectId(String connectId) {
		this.connectId = connectId;
	}

	public String getExtEqIdTemp() {
		return extEqIdTemp;
	}

	public void setExtEqIdTemp(String extEqIdTemp) {
		this.extEqIdTemp = extEqIdTemp;
	}

	public String getHostIpTemp() {
		return hostIpTemp;
	}

	public void setHostIpTemp(String hostIpTemp) {
		this.hostIpTemp = hostIpTemp;
	}

	public String getUserIdTemp() {
		return userIdTemp;
	}

	public void setUserIdTemp(String userIdTemp) {
		this.userIdTemp = userIdTemp;
	}

	public Map<String, String> getKpiIdMap() {
		return kpiIdMap;
	}

	public void setKpiIdMap(Map<String, String> kpiIdMap) {
		this.kpiIdMap = kpiIdMap;
	}

	public int getDelResult() {
		return delResult;
	}

	public void setDelResult(int delResult) {
		this.delResult = delResult;
	}


}
