package com.sitech.basd.resource.web.united;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.cloud3.service.departproject.DepartProjectService;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.resource.service.united.ResourceStatisticsService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.showresource.ShowResourceService;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;

@Component("unitedOutlineAction")
@Scope("prototype")
@SuppressWarnings("all")
public class UnitedOutlineAction extends BaseAction {
	private List<ResourceStatisticsObj> resultList;
	private List<AlarmHostStatistics> alarmList;
	private List<AlarmHostStatistics> netList;
	private List<DepartProjectObj> projectList;
	private List userProjectList;
	private List vmList;
	private Map map = new HashMap();
	private VMHostObj vmObj = new VMHostObj();
	private RelationObj userAllObj = new RelationObj();
	private RelationObj userUsedObj = new RelationObj();
	private AlarmHostStatistics cpuAllObj = new AlarmHostStatistics();
	private AlarmHostStatistics cpuUsedObj = new AlarmHostStatistics();
	private AlarmHostStatistics woObj = new AlarmHostStatistics();

	@Autowired
	private ResourceStatisticsService resourceStatisticsService;
	@Autowired
	private ShowResourceService showResourceService;// 用于统计IP及告警
	@Autowired
	private DepartProjectService departProjectService;// 用于统计项目的资源信息
	@Autowired
	private NetService netService;// 用于统计项目的网络和IP
	@Autowired
	private UserInfoService userInfoService;// 用于查询用户的ID
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private RelationService relationService;// 查询用户项目下的虚拟机
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	/**
	 * 
	 * @Title: getResource
	 * @Description: 获取资源统计信息(适用于admin账户)
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-8-28 下午3:29:59
	 */
	public String getAllResource() {
		// 查询资源池的信息(包括资源池总体信息，以及vmware类型、xen资源池信息)以及IP资源
		ResourceStatisticsObj obj = new ResourceStatisticsObj();
		resultList = resourceStatisticsService.queryForListByObj(obj);
		if (resultList != null && resultList.size() > 0) {
			for (ResourceStatisticsObj rsObj : resultList) {
				map.put(rsObj.getKey(), rsObj.getValue());
			}
		}
		// 查询CPU总量
		cpuAllObj = showResourceService.queryAllCPU();
		// 查询已用CPU
		cpuUsedObj = showResourceService.queryUsedCPU();
		// 查询告警
		alarmList = showResourceService.alarmHostStatistics();
		for (AlarmHostStatistics ahObj : alarmList) {
			if (alarmList != null && alarmList.size() > 0) {
				map.put(ahObj.getLevel(), ahObj.getLevelcount());
			}
		}
		// 查询网络IP
		netList = showResourceService.queryIpData();
		// 查询工单
		woObj = showResourceService.queryWOCount();

		return "allResource";
	}

	/**
	 * @Title: showResourceForAhLeader
	 * @Description: 安徽移动领导视图
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-20 下午4:55:15
	 */
	public String showResourceForAhLeader() {
		// 查询资源池的信息(包括资源池总体信息，以及vmware类型、xen资源池信息)以及IP资源
		ResourceStatisticsObj obj = new ResourceStatisticsObj();
		resultList = resourceStatisticsService.queryForListByObj(obj);
		if (resultList != null && resultList.size() > 0) {
			for (ResourceStatisticsObj rsObj : resultList) {
				map.put(rsObj.getKey(), rsObj.getValue());
			}
		}
		// 查询CPU总量
		cpuAllObj = showResourceService.queryAllCPU();
		// 查询已用CPU
		cpuUsedObj = showResourceService.queryUsedCPU();
		// 查询网络IP
		netList = showResourceService.queryIpData();

		return "ahResource";
	}

	/**
	 * 
	 * @Title: getResourceByProject
	 * @Description: 通过项目来查看其对应的资源信息
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-8-30 上午9:34:29
	 */
	public String getResourceByProject() {
		DepartProjectObj obj = new DepartProjectObj();
		String account = session.get("account").toString();
		obj.setPROJECT_LEADER(account);
		List<DepartProjectObj> projectList = departProjectService
				.queryResourceByDepartment(obj);
		if (projectList != null && projectList.size() > 0) {
			obj = projectList.get(0);
		}
		// 用户登录后查看此用户下的项目
		// 进行分页处理
		DepartProjectObj obj1 = new DepartProjectObj();
		obj1.setPROJECT_LEADER(account);
		obj1.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		userProjectList = departProjectService.departProjectList(obj1);
		// 查询告警
		alarmList = showResourceService.alarmHostStatistics();
		if (alarmList != null && alarmList.size() > 0) {
			for (AlarmHostStatistics ahObj : alarmList) {
				if (alarmList != null && alarmList.size() > 0) {
					map.put(ahObj.getLevel(), ahObj.getLevelcount());
				}
			}
		}
		// 查询网络IP
		if (projectList != null && projectList.size() > 0) {
			String net_id = obj.getNETWORK_DOMAIN();
			TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
			netObj.setNET_ID(net_id);
			netList = netService.queryForListByObj(netObj);
		}

		// 查询项目对应的虚拟机资源信息 ----start
		// 查询该项目用户对应的ID
		TbSysUserinfoObj user = new TbSysUserinfoObj();
		user.setACCOUNT(account);
		user = userInfoService.queryByObj(user);
		VmAuthorityObj auth = new VmAuthorityObj();
		auth.setUSERID(user.getID());
		userAllObj = vmAuthorityService.queryAllCountResource(auth);
		userUsedObj = vmAuthorityService.queryUsedCountResource(auth);

		return "departResource";
	}

	/**
	 * 
	 * @Title: showVMAll
	 * @Description: 通过项目来查看全部虚拟机
	 * @param
	 * @return
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-7 上午9:34:29
	 */
	public String showVMAll() {
		String account = session.get("account").toString();
		userAllObj.setPROJECT_LEADER(account);
		userAllObj.setPagination(this.getPaginater().initPagination(request));
		vmList = vmAuthorityService.queryAllVMListByAccount(userAllObj);
		return "showVmAll";
	}

	/**
	 * 
	 * @Title: showVMNews
	 * @Description: 通过每个项目来查看虚拟机
	 * @param
	 * @return
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-2 上午9:34:29
	 */
	public String showVMNews() {
		vmObj.setPagination(this.getPaginater().initPagination(request));
		vmList = relationService.queryVMByProjectId(vmObj);
		return "showVm";
	}

	/**
	 * 
	 * @Title: showContraResource
	 * @Description: 获取登录账户对应的资源信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-8-30 上午9:41:59
	 */
	public String showContraResource() {
		String userid = session.get("id").toString();
		String account = session.get("account").toString();
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[]{""};
		if (global != null) {
			users = global.getVALUE().split(",");
		}

		if ("1".equals(userid)) {// 对于admin用户，不需要分配权限
			return getAllResource();
		} else {// 对于普通登录用户，需要进行权限控制
			int flag = 0;
			for (int i = 0; i < users.length; i++) {
				if (account.equals(users[i])) {
					flag = 1;
				}
			}
			if (flag == 1) {
				return getAllResource();
			} else {
				return getResourceByProject();
			}

		}

	}

	public List<ResourceStatisticsObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<ResourceStatisticsObj> resultList) {
		this.resultList = resultList;
	}

	public List<AlarmHostStatistics> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(List<AlarmHostStatistics> alarmList) {
		this.alarmList = alarmList;
	}

	public List<AlarmHostStatistics> getNetList() {
		return netList;
	}

	public void setNetList(List<AlarmHostStatistics> netList) {
		this.netList = netList;
	}

	public List<DepartProjectObj> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<DepartProjectObj> projectList) {
		this.projectList = projectList;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List getVmList() {
		return vmList;
	}

	public void setVmList(List vmList) {
		this.vmList = vmList;
	}

	public List getUserProjectList() {
		return userProjectList;
	}

	public void setUserProjectList(List userProjectList) {
		this.userProjectList = userProjectList;
	}

	public VMHostObj getVmObj() {
		return vmObj;
	}

	public void setVmObj(VMHostObj vmObj) {
		this.vmObj = vmObj;
	}

	public RelationObj getUserAllObj() {
		return userAllObj;
	}

	public void setUserAllObj(RelationObj userAllObj) {
		this.userAllObj = userAllObj;
	}

	public RelationObj getUserUsedObj() {
		return userUsedObj;
	}

	public void setUserUsedObj(RelationObj userUsedObj) {
		this.userUsedObj = userUsedObj;
	}

	public AlarmHostStatistics getCpuAllObj() {
		return cpuAllObj;
	}

	public void setCpuAllObj(AlarmHostStatistics cpuAllObj) {
		this.cpuAllObj = cpuAllObj;
	}

	public AlarmHostStatistics getCpuUsedObj() {
		return cpuUsedObj;
	}

	public void setCpuUsedObj(AlarmHostStatistics cpuUsedObj) {
		this.cpuUsedObj = cpuUsedObj;
	}

	public AlarmHostStatistics getWoObj() {
		return woObj;
	}

	public void setWoObj(AlarmHostStatistics woObj) {
		this.woObj = woObj;
	}

}
