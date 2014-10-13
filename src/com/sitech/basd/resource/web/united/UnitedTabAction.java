package com.sitech.basd.resource.web.united;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.global.HostGlobalService;
import com.sitech.basd.resource.service.global.VmGlobalService;
import com.sitech.basd.resource.service.united.UnitedHostService;
import com.sitech.basd.resource.service.united.UnitedTaskAndEventService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.united.NetworkUnitedVO;
import com.sitech.vo.united.PortGroupUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
@Controller("unitedTabAction")
@Scope("prototype")
public class UnitedTabAction extends BaseAction {

	private String uuid; // 当前结点的id比如：集群、主机
	private String connect_id;// 当前模块所在连接池id
	private Integer countHost;// 当前集群结点下主机的个数
	private Integer countVm; // 当前集群下虚拟机的个数
	private TbCloud2HostInfoObj hostInfo; // 主机信息
	private VMHostObj vmObj; // 虚拟机信息s
	private List<DataStoreObj> dataStoreList;// 主机存储信息列表
	private List resultList;
	private String type;
	private String vtype;
	private String name;
	private String dataStoreName; // 存储信息名称

	private Integer pageSize;// 页面大小

	@Autowired
	private HostGlobalService hostGlobalService;
	@Autowired
	private VmGlobalService vmGlobalService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private UnitedTaskAndEventService unitedTaskAndEventService;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private UnitedVMService unitedVMService;
	@Autowired
	private UnitedHostService unitedHostService;
	@Autowired
	private BusiManagerTreeService busiManagerTreeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DataStoreService dataStoreService;

	/**
	 * 
	 * @Title: countHost
	 * @Description: 统计当前集群下主机和虚拟机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public String clusterAbstract() {
		HostGlobalObj hostObj = new HostGlobalObj();
		VmGlobalObj vmObj = new VmGlobalObj();
		if (uuid != null && !"".equals(uuid)) {
			hostObj.setCluster_uuid(uuid);
			vmObj.setCluster_uuid(uuid);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			hostObj.setConnect_uuid(connect_id);
			vmObj.setConnect_uuid(connect_id);
		}
		countHost = hostGlobalService.countHost(hostObj);
		countVm = vmGlobalService.countVm(vmObj);
		return "clusterAbstract";
	}

	/**
	 * 
	 * @Title: countVm
	 * @Description: 统计当前主机下虚拟机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public String countVm() {
		return null;
	}

	/**
	 * 
	 * @Title: hostInfo
	 * @Description: 查询主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 20:55 PM
	 */
	@MethodLog(remark = "UnitedTabAction-hostInfo", type = 4, message = "查询主机信息")
	public String hostInfo() {
		if (hostInfo == null) {
			hostInfo = new TbCloud2HostInfoObj();
		}
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		if (uuid != null && !"".equals(uuid)) {
			obj.setH_uuid(uuid);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnectId(connect_id);
		}
		hostInfo = hostInfoService.queryByObj(obj);
		return "hostInfo";
	}

	/**
	 * 
	 * @Title: hostInfo
	 * @Description: 查询虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 1, 2013 11:50 PM
	 */
	@MethodLog(remark = "UnitedTabAction-vmInfo", type = 4, message = "查询虚拟机信息")
	public String vmInfo() {
		if (vmObj == null) {
			vmObj = new VMHostObj();
		}
		VMHostObj obj = new VMHostObj();
		if (uuid != null && !"".equals(uuid)) {
			obj.setVH_UUID(uuid);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnectId(connect_id);
		}
		vmObj = vmHostService.queryByObj(obj);
		// 本地存储转化为G
		DecimalFormat df = new DecimalFormat("#.00");
		if (vmObj != null) {
			if (vmObj.getVH_STORAGE() != null && !"".equals(vmObj.getVH_STORAGE())) {
				// Double storageM = Double.parseDouble(vmObj.getVH_STORAGE());
				// Double storageG = storageM / 1024.00;
				// vmObj.setVH_STORAGE(df.format(storageG).toString());
			}
			// 查询虚拟机上所部属的子业务系统
			BusiManagerTree busi = new BusiManagerTree();
			busi.setEntity_id(vmObj.getVH_UUID());
			busi.setConnect_id(vmObj.getConnectId());
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(busi);
			if (list != null && list.size() > 0) {
				busi = list.get(0);
				BusiManagerTree parent = new BusiManagerTree();
				parent.setId(busi.getParent_id());
				List<BusiManagerTree> parentList = busiManagerTreeService.queryForTree(parent);
				if (parentList != null && parentList.size() > 0) {
					parent = parentList.get(0);
					if (2 == (parent.getType())) {
						vmObj.setCenterid(parent.getName());// 展示虚拟机上部署的子业务系统的名称
					} else if (4 == (parent.getType())) {// 当存在承载业务时，需要展示上一级节点
						BusiManagerTree parent1 = new BusiManagerTree();
						parent1.setId(parent.getParent_id());
						parentList = busiManagerTreeService.queryForTree(parent1);
						if (parentList != null && parentList.size() > 0) {
							parent = parentList.get(0);
							vmObj.setCenterid(parent.getName());
						}
					}
					TbSysUserinfoObj user = new TbSysUserinfoObj();
					if (parent.getUser_id() != null && !"".equals(parent.getUser_id())) {
						user.setID(Integer.parseInt(parent.getUser_id()));
						user = userInfoService.queryByObj(user);
						if (user != null) {
							vmObj.setUSER_ID(user.getNAME());// 展示子业务系统对应的负责人名称
						}

					}

				}

			}

		}
		return "vmInfo";
	}

	/**
	 * 
	 * @Title: hostStorage
	 * @Description: 查询主机的存储列表
	 * @param
	 * @return String1
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 1, 2013 11:50 PM
	 */
	@MethodLog(remark = "UnitedTabAction-hostStorage", type = 4, message = "查询主机的存储列表")
	public String hostStorage() {
		DataStoreObj obj = new DataStoreObj();
		if (uuid != null && !"".equals(uuid)) {
			obj.setHOST_ID(uuid);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnectId(connect_id);
		}
		if (UnitedConstant.VMWARE.equals(vtype)) {// vmware主机存储列表
			dataStoreList = hostGlobalService.queryForListByObj(obj);
		} else if (UnitedConstant.XEN.equals(vtype)) {// xen存储列表
			try {
				dataStoreList = unitedHostService.getHostDataStoreForXen(obj);
			} catch (Exception e) {
				LOG.error("xen主机获取存储列表出错" + e.getMessage());
			}
		}
		return "hostStorage";
	}

	/**
	 * 
	 * @Title: deleHostStorage
	 * @Description: 删除主机的存储
	 * @param
	 * @return String
	 * @throws
	 * @author liwq
	 * @version 1.0
	 * @createtime 2013-6-9 11:15 PM
	 */
	@MethodLog(remark = "UnitedTabAction-deleHostStorage", type = 4, message = "删除主机的存储")
	public void deleHostStorage() {
		int ret = -1;
		DataStoreObj obj = new DataStoreObj();
		if (null != dataStoreName && !"".equals(dataStoreName)) {
			obj.setNAME(dataStoreName);
		}
		if (null != connect_id && !"".equals(connect_id)) {
			obj.setConnectId(connect_id);
		}
		if (null != uuid && !"".equals(uuid)) {
			obj.setHOST_ID(uuid);
			ret = dataStoreService.deleteByObj(obj);
		}
		PrintWriterOut.printWirter(response, ret);
	}

	/**
	 * @Title: attchTask
	 * @Description: 获取任务列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-2 下午4:24:50
	 */
	@MethodLog(remark = "UnitedTabAction-attchTask", type = 4, message = "获取任务列表")
	public String attchTask() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setType(type);

		try {
			List<UnitedTreeObj> list = unitedTreeService.queryForTreeList(obj);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
				obj.setName(name);
				resultList = unitedTaskAndEventService.attchTask(obj, pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listTask";
	}

	/**
	 * @Title: attchEvent
	 * @Description: 获取事件列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-2 下午4:26:48
	 */
	@MethodLog(remark = "UnitedTabAction-attchEvent", type = 4, message = "获取事件列表")
	public String attchEvent() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setType(type);
		try {
			List<UnitedTreeObj> list = unitedTreeService.queryForTreeList(obj);
			if (list != null && list.size() > 0) {
				obj = list.get(0);
				obj.setName(name);
				resultList = unitedTaskAndEventService.attchEvent(obj, pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listEvent";
	}

	/**
	 * @Title: attchLogs
	 * @Description: 获取日志列表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-8-2 下午4:26:48
	 */
	@MethodLog(remark = "UnitedTabAction-attchLogs", type = 4, message = "获取日志列表")
	public String attchLogs() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setType(type);
		obj.setVtype(vtype);
		resultList = unitedTaskAndEventService.attchLogs(obj);
		return "listLogs";
	}

	/**
	 * 
	 * @Title: getVMNetWork
	 * @Description: 查看虚拟机的网络信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 9, 2013 11:51:51 AM
	 */
	@MethodLog(remark = "UnitedTabAction-getNetWork", type = 4, message = "查看虚拟机的网络信息")
	public String getNetWork() {
		VirtualMachineUnitedVO obj = new VirtualMachineUnitedVO();
		if (vtype != null && !"".equals(vtype)) {
			obj.setVmType(vtype);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnectCode(connect_id);
		}
		if (uuid != null && !"".equals(uuid)) {
			obj.setVmCode(uuid);
		}
		try {
			NetworkUnitedVO netWork = unitedVMService.getVMNetWork(obj);
			if (netWork != null) {
				resultList = netWork.getList();
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return "netWork";
	}

	/**
	 * 
	 * @Title: getHostPortGroup
	 * @Description: 获取主机端口组
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-08-21
	 */
	@MethodLog(remark = "UnitedTabAction-getHostPortGroup", type = 4, message = "获取主机端口组")
	public String getHostPortGroup() {
		UnitedTreeObj obj = new UnitedTreeObj();
		if (vtype != null && !"".equals(vtype)) {
			obj.setVtype(vtype);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnect_id(connect_id);
		}
		if (uuid != null && !"".equals(uuid)) {
			obj.setUuid(uuid);
		}
		try {
			PortGroupUnitedVO netWork = unitedHostService.getHostPortGroup(obj);
			if (netWork != null) {
				resultList = netWork.getList();
			}
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return "portgroup";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountHost() {
		return countHost;
	}

	public void setCountHost(Integer countHost) {
		this.countHost = countHost;
	}

	public Integer getCountVm() {
		return countVm;
	}

	public void setCountVm(Integer countVm) {
		this.countVm = countVm;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public TbCloud2HostInfoObj getHostInfo() {
		return hostInfo;
	}

	public void setHostInfo(TbCloud2HostInfoObj hostInfo) {
		this.hostInfo = hostInfo;
	}

	public VMHostObj getVmObj() {
		return vmObj;
	}

	public void setVmObj(VMHostObj vmObj) {
		this.vmObj = vmObj;
	}

	public List<DataStoreObj> getDataStoreList() {
		return dataStoreList;
	}

	public void setDataStoreList(List<DataStoreObj> dataStoreList) {
		this.dataStoreList = dataStoreList;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDataStoreName() {
		return dataStoreName;
	}

	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}
}
