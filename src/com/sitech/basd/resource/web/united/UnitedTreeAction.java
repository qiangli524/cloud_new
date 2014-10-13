package com.sitech.basd.resource.web.united;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.global.HostGlobalService;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.top.TopTargetService;
import com.sitech.basd.resource.service.united.UnitedClusterService;
import com.sitech.basd.resource.service.united.UnitedDataCenterService;
import com.sitech.basd.resource.service.united.UnitedHostService;
import com.sitech.basd.resource.service.united.UnitedTaskAndEventService;
import com.sitech.basd.resource.service.united.UnitedTaskThread;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.resource.service.united.XenDataCompareService;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.util.systemlog.MethodLog;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.number.NumberFormatUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.xml.XmlProperties;
import com.sitech.vo.united.ClusterUnitedVO;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.SnapshotUnitedVO;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualMachineConsole;
import com.sitech.vo.united.VirtualMachinePowerStatus;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
@Controller("unitedTreeAction")
@Scope("prototype")
public class UnitedTreeAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(UnitedTreeAction.class);
	@Autowired
	private XmlProperties vmwareConnectionXml;
	private List resultList;
	private List storageList;
	private List netList;
	private String id;
	private String parent_id;
	private String name;
	private String type;
	private String vtype;
	private String uuid;
	private String state;// 虚拟机的状态
	private String connect_id;
	private String monitor_id;
	private String result;
	private String parent_uuid;
	private Boolean migrateState;
	private Boolean adjusteState;
	private String vh_stat;// 0,1(用于判断虚拟机是关机或开机)
	private String oper;
	private String datacenterCode;// 数据中心code
	private String dataStoreCode;// 存储code
	// 虚拟机网卡数量
	private String vm_nic_count;
	private String vm_num;

	private SnapshotUnitedVO snapshotVO;
	private ClusterUnitedVO clusterUnitedVO;
	private DatastoreUnitedVO datastoreUnitedVO;
	private HostUnitedVO hostUnitedVO;
	private DataStoreObj dataStoreObj;
	private UnitedTreeObj unitedTreeObj;
	private VirtualMachineUnitedVO virtualMachineUnitedVO;
	private TbCloud2HostInfoObj host;
	private HostManageForm hostForm;
	@Autowired
	private UnitedTreeService unitedTreeService;
	@Autowired
	private UnitedDataCenterService unitedDataCenterService;
	@Autowired
	private UnitedClusterService unitedClusterService;
	@Autowired
	private UnitedHostService unitedHostService;
	@Autowired
	private UnitedVMService unitedVMService;
	@Autowired
	private TemManService temManService;
	@Autowired
	private DataStoreService dataStoreService;
	@Autowired
	private NetService netService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private UnitedTaskAndEventService unitedTaskAndEventService;
	@Autowired
	private XenStoreService xenStoreService;
	@Autowired
	private IpService ipService;
	@Resource
	private UnitedTaskThread unitedTaskThread;
	@Autowired
	private TopTargetService targetService;
	@Autowired
	private HostGlobalService hostGlobalService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private BusiManagerTreeService busiManagerTreeService;
	@Autowired
	private XenDataCompareService xenDataCompareService;

	public String getVm_num() {
		return vm_num;
	}

	public void setVm_num(String vm_num) {
		this.vm_num = vm_num;
	}

	public String getVm_nic_count() {
		return vm_nic_count;
	}

	public void setVm_nic_count(String vm_nic_count) {
		this.vm_nic_count = vm_nic_count;
	}

	public SnapshotUnitedVO getSnapshotVO() {
		return snapshotVO;
	}

	public String getMonitor_id() {
		return monitor_id;
	}

	public void setMonitor_id(String monitor_id) {
		this.monitor_id = monitor_id;
	}

	public void setSnapshotVO(SnapshotUnitedVO snapshotVO) {
		this.snapshotVO = snapshotVO;
	}

	/**
	 * 
	 * @Title: listUnitedTree
	 * @Description: 跳转至统一树界面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 17, 2013 4:31:10 PM
	 */
	public String listUnitedTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 异步加载统一树
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime Jul 17, 2013 4:39:44 PM
	 */
	public String asyncForTree() throws HttpClientException {
		String userid = session.get("id").toString();
		String account = session.get("account").toString();
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}

		try {

			if ("1".equals(userid)) {// 对于admin用户，不需要分配权限
				resultList = unitedTreeService.queryForUnitedTree(request);
			} else {// 对于普通登录用户，需要进行权限控制
				// resultList = unitedTreeService.queryForUnitedTree(request);
				int flag = 0;
				for (int i = 0; i < users.length; i++) {
					if (account.equals(users[i])) {
						flag = 1;
					}
				}
				if (flag == 1) {
					resultList = unitedTreeService.queryForUnitedTree(request);
				} else {
					resultList = unitedTreeService.queryForAuthTree(request,
							Integer.parseInt(userid));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "tree";
	}

	/**
	 * 
	 * @Title: tabs
	 * @Description: 进入tabs页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 22, 2013 10:44:31 AM
	 */
	public String tabs() {
		return "tabs";
	}

	/**
	 * 
	 * @Title: addArea
	 * @Description:进入添加地域页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-12 上午9:35:04
	 */
	public String addArea() {
		return "addArea";
	}

	/**
	 * 
	 * @Title: saveArea
	 * @Description: 保存地域信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-12 上午9:47:58
	 */
	public String saveArea() {
		UnitedTreeObj obj = new UnitedTreeObj();
		try {
			obj.setName(URLDecoder.decode(name, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		obj.setParent_id(parent_id);
		obj.setType(UnitedConstant.AREA);
		try {
			result = unitedTreeService.insertByObj(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addDataCenter
	 * @Description: 添加数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 22, 2013 12:00:11 PM
	 */
	public String addDataCenter() {
		resultList = vmwareConnectionXml.getList();
		return "dataCenter";
	}

	/**
	 * 
	 * @Title: saveDataCenter
	 * @Description: 保存数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 22, 2013 5:41:16 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-saveDataCenter", type = 1, message = "保存数据中心")
	public String saveDataCenter() throws UnsupportedEncodingException, SQLException {
		UnitedTreeObj u = new UnitedTreeObj();
		u.setName(URLDecoder.decode(name, "utf-8"));
		u.setParent_id(parent_id);
		u.setVtype(vtype);
		u.setConnect_id(connect_id);
		try {
			result = unitedDataCenterService.createDataCenter(u);
		} catch (HttpClientException e) {
			e.printStackTrace();
			result = "Create DataCenter Failed:" + e.getMessage();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: delDataCenter
	 * @Description:删除数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 22, 2013 5:41:50 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-delDataCenter", type = 2, message = "删除数据中心")
	public String delDataCenter() throws SQLException {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setId(id);
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		try {
			result = unitedDataCenterService.delDataCenter(obj);
		} catch (HttpClientException e) {
			result = "Delete DataCenter Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addCluster
	 * @Description: 进入添加集群页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 22, 2013 5:58:51 PM
	 */
	public String addCluster() {
		return "cluster";
	}

	/**
	 * 
	 * @Title: saveCluster
	 * @Description: 保存集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 * @createtime Jul 23, 2013 9:15:14 AM
	 */
	@MethodLog(remark = "UnitedTreeAction-saveCluster", type = 1, message = "保存集群")
	public String saveCluster() throws Exception {
		if (clusterUnitedVO == null) {
			clusterUnitedVO = new ClusterUnitedVO();
		}
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setName(URLDecoder.decode(name, "utf-8"));
		obj.setParent_id(parent_id);
		obj.setVtype(vtype);
		obj.setType(type);
		obj.setConnect_id(connect_id);
		obj.setParent_uuid(parent_uuid);
		try {
			result = unitedClusterService.createCluster(obj, clusterUnitedVO);
		} catch (HttpClientException e) {
			result = "Create Cluster Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 控制台
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws SQLException
	 */
	@MethodLog(remark = "UnitedTreeAction-vmConsole", type = 3, message = "控制台")
	public String vmConsole() {
		HttpServletRequest request = Struts2Utils.getRequest();
		VirtualMachineUnitedVO virtualMachineUnitedVO = new VirtualMachineUnitedVO();
		virtualMachineUnitedVO.setConnectCode(connect_id);
		virtualMachineUnitedVO.setVmCode(uuid);
		try {
			VirtualMachineConsole virtualMachineConsole = unitedVMService.vmConsole(
					virtualMachineUnitedVO, UnitedConstant.XEN);
			String[] paths = virtualMachineConsole.getHOST().split("&");
			request.setAttribute("path1", paths[0]);
			request.setAttribute("path2", paths[1]);
			request.setAttribute("host", virtualMachineConsole.getPROXYHOST1());
			request.setAttribute("state", state);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return "xenvmconsole";
	}

	/**
	 * 
	 * @Title: delCluster
	 * @Description: 删除集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 23, 2013 9:47:03 AM
	 */
	@MethodLog(remark = "UnitedTreeAction-delCluster", type = 2, message = "删除集群")
	public String delCluster() throws Exception {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setVtype(vtype);
		obj.setConnect_id(connect_id);
		obj.setParent_uuid(parent_uuid);
		obj.setUuid(uuid);
		obj.setId(id);
		try {
			result = unitedClusterService.deleteCluster(obj);
		} catch (HttpClientException e) {
			result = "Delete Cluster Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addHost
	 * @Description: 添加主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 23, 2013 10:43:54 AM
	 */
	public String addHost() {
		return "host";
	}

	/**
	 * 
	 * @Title: selectUnAllocatedHost
	 * @Description: 选择未分配的主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-24 上午11:53:47
	 */
	public String selectUnAllocatedHost() {
		if (host == null) {
			host = new TbCloud2HostInfoObj();
		}

		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				host.setHasvertual("4");
			} else if (UnitedConstant.XEN.equals(vtype)) {
				host.setHasvertual("3");
			}

			host.setAllocated(0);
			resultList = hostInfoService.queryForListByObj(host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "unAllocatedHost";
	}

	/*########################  非虚拟化主机SC Begin  #############################*/
	/**
	 * 
	 * @Title: selectUnAllocatedHostSC
	 * @Description: 从空闲池中选择主机
	 * @param
	 * @return String
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014-08-04 16:37:07
	 */
	public String unAllocatedHostSC() {
		
		if(hostForm==null){
			hostForm = new HostManageForm();
		}
		TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
		if (hostForm.getHostUuids() != null && !hostForm.getHostUuids().equals("")) {
			host.setHostUuids(hostForm.getHostUuids());
		}

		if (hostForm.getEq_name() != null && !hostForm.getEq_name().equals("")) {
			host.setEq_name(hostForm.getEq_name().trim());
		}
		if (hostForm.getEq_ip() != null && !hostForm.getEq_ip().equals("")) {
			host.setEq_ip(hostForm.getEq_ip().trim());
		}
		if (hostForm.getEq_type() != null && !hostForm.getEq_type().equals("-1")) {
			host.setEq_type(hostForm.getEq_type());
		}
		if (hostForm.getSTATUS() != null && !"".equals(hostForm.getSTATUS())) {
			host.setSTATUS(hostForm.getSTATUS());
		}
		host.setAllocated(0);
		host.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		
		hostForm.setResultList(hostInfoService.queryForListByObj(host));
		//hostForm.setResultList(hostInfoService.queryAllHost(host));
		
		return "unAllocatedHostSC";
	}

	/**
	 * 
	 * @Title: saveAllocatedHostSC
	 * @Description: 从空闲池中选择主机
	 * @param
	 * @return String
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014-08-04 16:37:07
	 */
	public String saveAllocatedHostSC() {
		
		//System.out.println("JamTau 资源树图ID:" + id + ",connect_id:" + connect_id + ",parnetId:" + parent_id);
		JSONObject json = new JSONObject();
		
		String connectIds[] = connect_id.split(",");
		for(String s:connectIds){
			if(s != null && !"".equals(s)){
				UnitedTreeObj obj = new UnitedTreeObj();
				//obj.setId(id);
				obj.setConnect_id(s);
				try {
					obj = unitedTreeService.queryByObj(obj);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(obj==null){					
					json.put("result", 3);
					json.put("parent_id", parent_id);
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				
				obj.setParent_id(parent_id);
				obj.setVtype("8");
				try {
					unitedTreeService.updateByObj(obj);
					TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
					host.setEq_id(obj.getConnect_id());
					host.setAllocated(1);
					hostInfoService.updateHostStatusByObj(host);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				json.put("result", 2);
				json.put("parent_id", parent_id);
				PrintWriterOut.printWirter(response, json);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: recycleHostNotVir
	 * @Description: 将主机释放回空闲池
	 * @param
	 * @return String
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014-08-04 16:37:07
	 */
	public String recycleHostNotVir() {
		HttpServletResponse response = Struts2Utils.getResponse();
		
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setVtype("7");
		obj.setType("1");
		List<UnitedTreeObj> list = unitedTreeService.queryForListByObj(obj);
		if(list.size()>0){
			obj = list.get(0);
		}else{
			//没有空闲池存在，释放失败
		}
		String _id = obj.getId();
		
		obj = new UnitedTreeObj();
		//obj.setConnect_id(connect_id);
		obj.setVtype(vtype);
		obj.setUuid(uuid);
		obj.setId(id);
		try {
			obj = unitedTreeService.queryByObj(obj);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		obj.setParent_id(_id);
		obj.setVtype("7");
		
		try {
			unitedTreeService.updateByObj(obj);
			TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
			host.setEq_id(obj.getConnect_id());
			host.setAllocated(0);
			hostInfoService.updateHostStatusByObj(host);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("result", 2);
		json.put("parent_id", parent_id);
		PrintWriterOut.printWirter(response, json);
		return null;
		
	}
	/*########################  非虚拟化主机 SC End  #############################*/	
	
	/**
	 * 
	 * @Title: saveHost
	 * @Description: 保存主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 27, 2013 10:37:06 AM
	 */
	@MethodLog(remark = "UnitedTreeAction-saveHost", type = 1, message = "保存主机")
	public String saveHost() throws SQLException {
		UnitedTreeObj obj = new UnitedTreeObj();
		String userId = session.get("id").toString();
		obj.setConnect_id(connect_id);
		obj.setName(name);
		obj.setParent_id(parent_id);
		obj.setParent_uuid(parent_uuid);
		obj.setVtype(vtype);
		obj.setUser_id(Integer.parseInt(userId));
		try {
			result = unitedHostService.createHost(obj, hostUnitedVO);
		} catch (HttpClientException e) {
			result = "Create Host Failed:" + e.getMessage();
			e.printStackTrace();
		}
		result = "添加主机" + result;
		return "results";
	}

	/**
	 * 
	 * @Title: deleteHost
	 * @Description: 移除资源池中的主机（已分配）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-25 下午4:31:43
	 */
	@MethodLog(remark = "UnitedTreeAction-saveHost", type = 1, message = "移除主机")
	public String deleteHost() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setVtype(vtype);
		obj.setUuid(uuid);
		obj.setId(id);
		try {
			result = unitedHostService.deleteHost(obj);
		} catch (HttpClientException e) {
			result = "Remove Host Failed:" + e.getMessage();
			e.printStackTrace();
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		result = "移除主机" + result;
		return "results";
	}

	/**
	 * 
	 * @Title: putVMPowerState
	 * @Description: 修改虚拟机电源状态
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 27, 2013 5:18:13 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-putVMPowerState", type = 3, message = "修改虚拟机电源状态")
	public String putVMPowerState() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setParent_uuid(parent_uuid);
		obj.setVtype(vtype);
		obj.setUuid(uuid);
		obj.setOper(oper);
		try {
			result = unitedVMService.putVMPowerState(obj);
		} catch (HttpClientException e) {
			result = "Update Host Power Status Failed :" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: putVMPowerStateOnList
	 * @Description: 修改虚拟机电源状态(从列表操作)
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-4 下午4:39:19
	 */
	public String putVMPowerStateOnList() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(request.getParameter("pool_uuid"));
		obj.setParent_uuid(request.getParameter("host_uuid") == null ? "" : request
				.getParameter("host_uuid"));
		obj.setVtype(request.getParameter("vtype"));
		obj.setUuid(request.getParameter("vh_uuid"));
		obj.setOper(request.getParameter("state"));
		try {
			result = unitedVMService.putVMPowerState(obj);
		} catch (HttpClientException e) {
			result = "Put VM Power State Failed :" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: putVMPowerStateOnList_BD
	 * @Description: 修改虚拟机状态-北京电信演示
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-15 下午2:39:34
	 */
	public String putVMPowerStateOnList_BD() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(request.getParameter("pool_uuid"));
		obj.setParent_uuid(request.getParameter("host_uuid") == null ? "" : request
				.getParameter("host_uuid"));
		obj.setVtype(request.getParameter("vtype"));
		obj.setUuid(request.getParameter("vh_uuid"));
		try {
			result = unitedVMService.putVMPowerState_BD(obj, request);
		} catch (HttpClientException e) {
			result = "Put VM Power State Failed :" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: destoryVM
	 * @Description: 从磁盘移除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 29, 2013 2:36:42 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-destoryVM", type = 2, message = "从磁盘移除虚拟机")
	public String destoryVM() throws SQLException {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setVtype(vtype);
		obj.setUuid(uuid);
		obj.setId(id);
		try {
			result = unitedVMService.deleteVM(obj);
		} catch (HttpClientException e) {
			result = "Destory VM Failed :" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: addVM
	 * @Description: 进入创建虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 30, 2013 9:57:43 AM
	 */
	public String addVM() {
		// 获取模板列表
		// TemManObj obj = new TemManObj();
		// obj.setType(vtype);
		// resultList = temManService.queryForList(obj);
		// 获取存储列表
		if (UnitedConstant.VMWARE.equals(vtype)) {
			DataStoreObj dsObj = new DataStoreObj();
			dsObj.setHOST_ID(uuid);
			dsObj.setConnectId(connect_id);
			storageList = dataStoreService.queryForListByObj(dsObj);
		} else {
			XenStoreObj storeObj = new XenStoreObj();
			storeObj.setDependent_host_uuid(uuid);
			storageList = xenStoreService.queryNewVmStore(storeObj);
		}
		/*
		 * 去除查询网络子域方法，采用ajax--getSubDomainList获取
		 */
		return "addVM";
	}

	/**
	 * 
	 * @Title: getSubDomainList
	 * @Description: 获取网络子域列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-19 下午9:15:30
	 */
	public String getSubDomainList() {
		// 获取网络子域列表
		UnitedTreeObj subdomain = new UnitedTreeObj();
		subdomain.setType(UnitedConstant.SUBDOMIAN);
		try {
			resultList = unitedTreeService.queryForTreeList(subdomain);
		} catch (SQLException e) {
			LOG.error("查询网络子域异常！" + e.getMessage());
			e.printStackTrace();
		}
		return "subdomain";
	}

	/**
	 * 
	 * @Title: selectTemMan
	 * @Description: 选择虚拟机模板
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 11, 2013 11:34:43 AM
	 */
	public String chooseTemMan() {
		TemManObj temManObj = new TemManObj();
		if (vtype != null && !("").equals(vtype)) {
			temManObj.setType(vtype);
		}
		if (connect_id != null && !("").equals(connect_id)) {
			temManObj.setConnectId(connect_id);
		}
		resultList = temManService.queryForList(temManObj);
		return "chooseTemMan";
	}

	/**
	 * 
	 * @Title: getStorageDetail
	 * @Description: 获取存储的使用信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 8, 2013 12:41:25 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-getStorageDetail", type = 4, message = "获取存储的使用信息")
	public String getStorageDetail() {
		DecimalFormat nf = new DecimalFormat("0.00");
		if (UnitedConstant.VMWARE.equals(vtype)) {
			dataStoreObj = new DataStoreObj();
			// /dsObj.setHOST_ID(uuid);
			dataStoreObj.setConnectId(connect_id);
			dataStoreObj.setStore_uuid(request.getParameter("store_uuid"));
			storageList = dataStoreService.queryForListByObj(dataStoreObj);
			if (storageList != null && storageList.size() > 0) {
				dataStoreObj = (DataStoreObj) storageList.get(0);
				dataStoreObj
						.setCAPACITY(nf.format(Double.parseDouble(dataStoreObj.getCAPACITY()) / 1024));
				dataStoreObj.setFREE_SPACE(nf.format(Double.parseDouble(dataStoreObj
						.getFREE_SPACE()) / 1024));
				storageList.add(dataStoreObj);
			}
		} else {
			XenStoreObj storeObj = new XenStoreObj();
			storeObj.setConnect_id(connect_id);
			storeObj.setStore_uuid(request.getParameter("store_uuid"));
			storeObj = xenStoreService.queryByObj(storeObj);
			dataStoreObj = new DataStoreObj();
			dataStoreObj.setCAPACITY(nf.format(Double.parseDouble(storeObj.getSr_size()) / 1024));
			dataStoreObj
					.setFREE_SPACE(nf.format(Double.parseDouble(storeObj.getFree_size()) / 1024));
			storageList = new ArrayList();
			storageList.add(dataStoreObj);
		}
		return "storage";

	}

	/**
	 * 
	 * @Title: createVMByTem
	 * @Description: 通过模板创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 30, 2013 6:56:32 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-createVMByTem", type = 1, message = "通过模板创建虚拟机")
	public String createVMByTem() throws SQLException {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setParent_id(parent_id);
		obj.setVtype(vtype);
		obj.setParent_uuid(parent_uuid);
		String userid = session.get("id").toString();
		obj.setUser_id(Integer.parseInt(userid));
		// 获取用户ID
		final String taskUUID = RandomUUID.getUuid();
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		final String createrName = user[1];
		GlobalTaskObj taskObj = new GlobalTaskObj();
		taskObj.setId(taskUUID);

		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalTaskObj taskObj = new GlobalTaskObj();
				taskObj.setName("创建虚拟机");
				taskObj.setCreaterName(createrName);
				taskObj.setId(taskUUID);
				taskObj.setContent("正在创建虚拟机" + virtualMachineUnitedVO.getNewVmName());
				taskObj.setType(enumtype.Types.GlobalTaskType.CLONEVM.toString());
				unitedTaskThread.updateTaskProgress(taskObj);
			}
		});
		taskThread.start();// 更新任务线程

		try {
			Thread.sleep(2 * 1000);// 保证任务信息执行
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			result = unitedVMService.createVMByTem(obj, virtualMachineUnitedVO, request);
			taskThread.interrupt();
			taskObj.setContent("创建虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "完成!" + result);
			unitedTaskThread.endTask(taskObj);
		} catch (Exception e) {
			taskThread.interrupt();
			taskObj.setContent("创建虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
					+ e.getMessage());
			unitedTaskThread.endTask(taskObj);
			result = "创建虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "Failed 失败!原因："
					+ e.getMessage() + e;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: createVMByVM
	 * @Description: 进入通过虚拟机创建虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 30, 2013 9:07:26 PM
	 */
	public String addVMByVMPage() throws SQLException {
		virtualMachineUnitedVO = new VirtualMachineUnitedVO();
		// 获取主机列表
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setType(UnitedConstant.HOST);
		obj.setVtype(vtype);
		resultList = unitedTreeService.queryForTreeList(obj);
		// 获取网络列表
		// 获取网络子域列表
		UnitedTreeObj subdomain = new UnitedTreeObj();
		subdomain.setType(UnitedConstant.SUBDOMIAN);
		try {
			netList = unitedTreeService.queryForTreeList(subdomain);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (UnitedConstant.XEN.equals(vtype)) {
			XenStoreObj xenObj = new XenStoreObj();
			xenObj.setConnect_id(connect_id);
			Map<String, Object> stoMap = new HashMap<String, Object>();
			storageList = xenStoreService.queryForStoreList(xenObj);
			for (Object storeObj : storageList) {
				XenStoreObj xenStoObj = (XenStoreObj) storeObj;
				if (xenStoObj.getType().equals("LVM") || xenStoObj.getType().equals("NFS")) {
					stoMap.put(xenStoObj.getStore_uuid(), xenStoObj);
				}
			}
			Set<Entry<String, Object>> stoSet = stoMap.entrySet();
			storageList = new ArrayList();
			for (Entry<String, Object> entry : stoSet) {
				XenStoreObj xenStoreOnHostObj = (XenStoreObj) entry.getValue();
				if (xenStoreOnHostObj.getType().equals("LVM")) {
					TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
					hostObj.setH_uuid(xenStoreOnHostObj.getDependent_host_uuid());
					hostObj = hostInfoService.queryByObj(hostObj);
					xenStoreOnHostObj.setName(xenStoreOnHostObj.getName() + " on "
							+ hostObj.getEq_name());
				}
				storageList.add(xenStoreOnHostObj);
			}
		}
		// 获取被克隆虚拟机的详细信息
		VMHostObj vm = new VMHostObj();
		vm.setVH_UUID(uuid);
		vm.setConnectId(connect_id);
		vm = vmHostService.queryByObj(vm);
		virtualMachineUnitedVO = RevertEntity.formVMHostObjToVirtualMachineUnitedVO(vm);
		VmIpObj vmIpObj = new VmIpObj();
		vmIpObj.setVm_uuid(uuid);
		vmIpObj.setConnectid(connect_id);
		List<VmIpObj> vmIpList = unitedVMService.queryVmIpRelation(vmIpObj);
		vm_nic_count = vmIpList.size() + "";
		return "cloneVM";
	}

	/**
	 * 
	 * @Title: createVMByVM
	 * @Description: 通过虚拟机克隆虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 31, 2013 4:38:39 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-createVMByVM", type = 1, message = "通过虚拟机克隆虚拟机")
	public String createVMByVM() throws SQLException {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setParent_id(parent_id);
		obj.setVtype(vtype);
		obj.setParent_uuid(parent_uuid);
		// 用户ID
		String userid = session.get("id").toString();
		obj.setUser_id(Integer.parseInt(userid));

		final String taskUUID = RandomUUID.getUuid();
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		final String createrName = user[1];
		GlobalTaskObj taskObj = new GlobalTaskObj();
		taskObj.setId(taskUUID);

		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalTaskObj taskObj = new GlobalTaskObj();
				taskObj.setName("克隆虚拟机");
				taskObj.setCreaterName(createrName);
				taskObj.setId(taskUUID);
				taskObj.setContent("正在克隆新虚拟机" + virtualMachineUnitedVO.getNewVmName());
				taskObj.setType(enumtype.Types.GlobalTaskType.CLONEVM.toString());
				unitedTaskThread.updateTaskProgress(taskObj);
			}
		});
		taskThread.start();// 更新任务线程
		try {
			Thread.sleep(2 * 1000);// 保证任务信息执行
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			result = unitedVMService.createVMByVM(obj, virtualMachineUnitedVO, request);
			taskThread.interrupt();
			taskObj.setContent("克隆虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "完成!" + result);
			unitedTaskThread.endTask(taskObj);
		} catch (Exception e) {
			taskThread.interrupt();
			taskObj.setContent("创建虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
					+ e.getMessage() + e);
			unitedTaskThread.endTask(taskObj);
			result = "创建虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "Failed 失败!原因："
					+ e.getMessage() + e;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: markAsTemplate
	 * @Description: 将虚拟机转化为模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-24 下午5:18:56
	 */
	public String markAsTemplate() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		obj.setConnect_id(connect_id);
		obj.setId(id);
		try {
			result = unitedVMService.markAsTemplate(obj);
		} catch (HttpClientException e) {
			result = "虚拟机标记为模板失败！Failed :" + e.getMessage();
			e.printStackTrace();
		} catch (SQLException e) {
			result = "虚拟机标记为模板失败！Failed :" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: goCreateSnapshotVM
	 * @Description: 进入创建快照页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 23, 2013 5:52:19 PM
	 */
	public String goCreateSnapshotVM() {
		return "goCreateSnapshotVM";
	}

	/**
	 * 
	 * @Title: addSnapshot
	 * @Description: 虚拟机执行快照
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 27, 2013 3:09:37 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-addSnapshot", type = 1, message = "虚拟机执行快照")
	public String addSnapshot() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		obj.setConnect_id(connect_id);
		try {
			result = unitedVMService.createSnapShot(obj, snapshotVO);
		} catch (HttpClientException e) {
			result = "创建快照 Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * 
	 * @Title: snapShotManage
	 * @Description: 获取虚拟机快照列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 2:58:48 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-snapShotManage", type = 4, message = "获取虚拟机快照列表")
	public String snapShotManage() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		obj.setConnect_id(connect_id);
		SnapshotUnitedVO vo = new SnapshotUnitedVO();
		try {
			vo = unitedVMService.getSnapShotList(obj);
			if (vo.getIsSuccess()) {
				resultList = vo.getSnapshots();
			}
		} catch (HttpClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "snapshotList";
	}

	/**
	 * 
	 * @Title: operSnapShot
	 * @Description: 对快照进行操作
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 7:16:44 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-operSnapShot", type = 3, message = "对快照进行操作")
	public String operSnapShot() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		try {
			result = unitedVMService.operSnapShot(obj, request);
		} catch (HttpClientException e) {
			result = "操作快照 Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";

	}

	/**
	 * 
	 * @Title: console
	 * @Description: 打开虚拟机控制台
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 3:56:57 PM
	 */
	public String console() {
		VMHostObj obj = new VMHostObj();
		obj.setVH_UUID(uuid);
		VMHostObj vh = vmHostService.queryByObj(obj);		
		String ip = PropertiesUtil.getString("weburl", connect_id);
		request.setAttribute("ip", ip);
		connect_id = vh.getConnectId();
		vtype = UnitedConstant.VMWARE;
		uuid = vh.getVH_UUID();
		return "console";
	}

	/**
	 * 
	 * @Title: getticket
	 * @Description: 获取控制台链接票据
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Aug 1, 2013 4:27:25 PM
	 */
	@MethodLog(remark = "UnitedTreeAction-getticket", type = 4, message = "获取控制台链接票据")
	public String getticket() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		try {
			result = unitedVMService.console(obj);
		} catch (HttpClientException e) {
			result = "获取虚拟机控制台票据失败！Failed";
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * @Title: goEditVMPage
	 * @Description: 进入编辑虚拟机的界面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午2:32:18
	 */
	public String goEditVMPage() {
		// 获取被修改虚拟机的详细信息
		VMHostObj vmobj = new VMHostObj();
		vmobj.setVH_UUID(uuid);
		vmobj = vmHostService.queryByObj(vmobj);

		if (vmobj != null) {
			virtualMachineUnitedVO = RevertEntity.formVMHostObjToVirtualMachineUnitedVO(vmobj);
		}
		// 查询虚拟机对应的磁盘列表
		if (UnitedConstant.VMWARE.equals(vtype)) {
			UnitedTreeObj obj = new UnitedTreeObj();
			obj.setVtype(vtype);
			obj.setConnect_id(connect_id);
			obj.setUuid(uuid);
			resultList = unitedVMService.getVMDiskList(obj);
		}
		return "goeditpage";
	}

	/**
	 * @Title: saveVMInfo
	 * @Description:保存修改的虚拟机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午2:36:00
	 */
	@MethodLog(remark = "UnitedTreeAction-saveVMInfo", type = 3, message = "保存修改的虚拟机信息")
	public String saveVMInfo() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setParent_id(parent_id);
		obj.setVtype(vtype);
		obj.setParent_uuid(parent_uuid);
		obj.setUuid(uuid);
		// 组装编辑vmware虚拟机磁盘所需要的参数
		if (UnitedConstant.VMWARE.equals(vtype)) {
			List<VirtualDiskUnitedVO> vmdkList = new ArrayList<VirtualDiskUnitedVO>();
			String labels = request.getParameter("labels");
			String capacitys = request.getParameter("capacitys");
			try {
				labels = URLDecoder.decode(labels, "UTF-8");
				capacitys = URLDecoder.decode(capacitys, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] label = labels.split(",");
			String[] capacity = capacitys.split(",");
			for (int i = 0; i < label.length; i++) {
				VirtualDiskUnitedVO v = new VirtualDiskUnitedVO();
				v.setVmdkLabel(label[i]);
				v.setCapacityInMB(Long.parseLong(capacity[i]) * 1024);
				vmdkList.add(v);
			}
			virtualMachineUnitedVO.setVmdkList(vmdkList);
		}
		// 判断虚拟机的状态
		if (UnitedConstant.XEN.equals(vtype)) {
			if ("1".equals(state)) {
				// 关机状态
				virtualMachineUnitedVO.setPowerState(VirtualMachinePowerStatus.powerOff);
			} else if ("2".equals(state)) {
				// 运行状态
				virtualMachineUnitedVO.setPowerState(VirtualMachinePowerStatus.powerOn);
			} else if ("3".equals(state)) {
				// 挂起状态
				virtualMachineUnitedVO.setPowerState(VirtualMachinePowerStatus.suspended);
			}
		}
		try {
			result = unitedVMService.saveVMInfo(obj, virtualMachineUnitedVO);
		} catch (HttpClientException e) {
			result = "Failed:" + e.getMessage();
			e.printStackTrace();
		} catch (SQLException e) {
			result = "Failed:" + e.getMessage();
			e.printStackTrace();
		}
		return "results";
	}

	/**
	 * @Title: gomigrateVMPage
	 * @Description: 进入迁移虚拟机的界面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午2:36:26
	 */
	@SuppressWarnings("unchecked")
	public String gomigrateVMPage() {
		// 父节点
		UnitedTreeObj parentobj = new UnitedTreeObj();
		parentobj.setUuid(parent_uuid);
		parentobj.setConnect_id(connect_id);
		try {
			if (vtype.equals(UnitedConstant.VMWARE)) {
				List<UnitedTreeObj> list = unitedTreeService.queryForTreeList(parentobj);
				if (list != null && list.size() > 0) {
					parentobj = list.get(0);
					// 获取到爷爷节点的id
					String yyid = parentobj.getParent_id();
					UnitedTreeObj obj = new UnitedTreeObj();
					obj.setParent_id(yyid);
					// 获取所有父级节点的同级节点
					List<UnitedTreeObj> plist = unitedTreeService.queryForTreeList(obj);
					if (plist != null && plist.size() > 0) {
						UnitedTreeObj robj = null;
						for (UnitedTreeObj unitedTreeObj : plist) {
							if (unitedTreeObj.getId() != null
									&& unitedTreeObj.getId().equals(parentobj.getId())) {
								robj = unitedTreeObj;
							}
						}
						if (robj != null) {
							plist.remove(robj);
						}

						if (plist.size() > 0) {
							List<String> hostidlist = new ArrayList<String>();
							for (UnitedTreeObj unitedTreeObj : plist) {
								hostidlist.add(unitedTreeObj.getUuid());
							}
							// 获取宿主机同级主机的集合，在一个集群下
							TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
							hostObj.setUuidList(hostidlist);
							List<TbCloud2HostInfoObj> hostlist = hostInfoService
									.queryHostListUseIn(hostObj);
							resultList = hostlist;
							DataStoreObj data = new DataStoreObj();
							data.setHOST_ID(parent_uuid);
							data.setConnectId(connect_id);
							storageList = dataStoreService.queryForListByObj(data);
						}
					}
				}
			} else if (vtype.equals(UnitedConstant.XEN)) {
				HostUnitedVO hostVo = unitedVMService.getXenMigrateHost(connect_id, uuid);
				List reList = new ArrayList();
				migrateState = false;
				if (hostVo.isSuccess) {
					List<HostUnitedVO> HostLst = hostVo.getHostList();
					if (HostLst.size() == 0) {
						resultList = reList;
						return "gomigrateVMPage";
					}
					for (HostUnitedVO vo : HostLst) {
						TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
						hostInfoObj.setH_uuid(vo.getHostCode());
						hostInfoObj.setEq_name(vo.getHostname());
						reList.add(hostInfoObj);
					}
					migrateState = true;
					resultList = reList;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return "gomigrateVMPage";
	}

	/**
	 * @Title: gomigrateVMPage
	 * @Description: 进入虚拟机迁移页面，支持跨集群迁移
	 * @return String
	 * @throws
	 * @Date 2014-6-1 上午10:34:30
	 * @author lipp
	 * @return
	 */
	public String gorelocateVMPage() {
		/* 可以使用主机虚拟机全量表，比较简单，暂时未使用 */

		// 找出与宿主机在同一个数据中心下的所有主机
		UnitedTreeObj parentObj = new UnitedTreeObj();
		parentObj.setId(parent_id);
		List<UnitedTreeObj> list = unitedTreeService.queryForListByObj(parentObj);
		// 主机
		parentObj = list.get(0);
		vtype = parentObj.getVtype();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			String clusterId = parentObj.getParent_id();
			UnitedTreeObj paramObj = new UnitedTreeObj();
			paramObj.setId(clusterId);
			// 集群
			paramObj = unitedTreeService.queryForListByObj(paramObj).get(0);

			UnitedTreeObj centerObj = new UnitedTreeObj();
			centerObj.setId(paramObj.getParent_id());
			centerObj = unitedTreeService.queryForListByObj(centerObj).get(0);
			datacenterCode = centerObj.getUuid();

			UnitedTreeObj clusterObj = new UnitedTreeObj();
			clusterObj.setParent_id(paramObj.getParent_id());
			List<UnitedTreeObj> clusterList = unitedTreeService.queryForListByObj(clusterObj);
			List<String> pidList = new ArrayList<String>();
			for (UnitedTreeObj unitedTreeObj : clusterList) {
				pidList.add(unitedTreeObj.getId());
			}

			UnitedTreeObj treeObj = new UnitedTreeObj();
			treeObj.setPidlist(pidList);
			resultList = unitedTreeService.queryForListByObj(treeObj);
		} else if (UnitedConstant.XEN.equals(vtype)) {
			try {
				HostUnitedVO hostVo = unitedVMService.getXenMigrateHost(connect_id, uuid);
				List reList = new ArrayList();
				migrateState = false;
				if (hostVo.isSuccess) {
					List<HostUnitedVO> HostLst = hostVo.getHostList();
					if (HostLst.size() == 0) {
						resultList = reList;
						return "gomigrateVMPage";
					}
					for (HostUnitedVO vo : HostLst) {
						TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
						hostInfoObj.setH_uuid(vo.getHostCode());
						hostInfoObj.setEq_name(vo.getHostname());
						reList.add(hostInfoObj);
					}
					migrateState = true;
					resultList = reList;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "gorelocateVMPage";
	}

	/**
	 * @Title: selectDataStoreForMigrate
	 * @Description: 根据主机选择存储
	 * @return void
	 * @throws
	 * @Date 2014-6-1 下午1:55:53
	 * @author lipp
	 */
	public void selectDataStoreForMigrate() {
		DataStoreObj storeObj = new DataStoreObj();
		storeObj.setHOST_ID(uuid);
		storeObj.setConnectId(connect_id);
		storeObj.setDATACENTER_ID(datacenterCode);
		storageList = dataStoreService.queryForHostIdsList(storeObj);
		JSONArray jsonArray = JSONArray.fromObject(storageList);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriterOut.printWirter(response, jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: selectHostFormigrate
	 * @Description:查询迁移虚拟机所需要的主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-17 下午2:35:43
	 */
	public String selectHostFormigrate() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setUuid(uuid);
		obj.setConnect_id(connect_id);
		try {
			obj = unitedTreeService.queryByObj(obj);
			// 查询主机节点信息
			UnitedTreeObj parent = new UnitedTreeObj();
			parent.setId(obj.getParent_id());
			parent = unitedTreeService.queryByObj(parent);
			// 查询集群节点信息
			UnitedTreeObj cluster = new UnitedTreeObj();
			cluster.setId(parent.getParent_id());
			cluster = unitedTreeService.queryByObj(cluster);
			Map map = new HashMap();
			map.put("eq_name", name);
			map.put("cluster_uuid", cluster.getUuid());
			map.put("connect_id", cluster.getConnect_id());
			resultList = hostGlobalService.queryHostByMap(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "selectHost";
	}

	/**
	 * @Title: executeMigrateVM
	 * @Description: 执行虚拟机迁移操作
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-1 下午5:28:36
	 */
	@MethodLog(remark = "UnitedTreeAction-executeMigrateVM", type = 3, message = "执行虚拟机迁移操作")
	public String executeMigrateVM() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setParent_uuid(parent_uuid);
		obj.setUuid(uuid);
		obj.setVtype(vtype);

		final String taskUUID = RandomUUID.getUuid();
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		final String createrName = user[1];
		GlobalTaskObj taskObj = new GlobalTaskObj();
		taskObj.setId(taskUUID);
		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalTaskObj taskObj = new GlobalTaskObj();
				taskObj.setName("迁移虚拟机");
				taskObj.setCreaterName(createrName);
				taskObj.setId(taskUUID);
				taskObj.setType(enumtype.Types.GlobalTaskType.DEPLOY.toString());
				unitedTaskThread.updateTaskProgress(taskObj);
			}
		});
		taskThread.start();// 更新任务线程

		try {
			// 迁移时查询目标主机的id，确定新的父子关系
			UnitedTreeObj parentObj = new UnitedTreeObj();
			parentObj.setUuid(parent_uuid);
			parentObj.setConnect_id(connect_id);
			parentObj = unitedTreeService.queryByObj(parentObj);
			if (parentObj != null) {
				obj.setParent_id(parentObj.getId());
			}
			result = unitedVMService.migrateVM(obj, virtualMachineUnitedVO);
			taskThread.interrupt();
			taskObj.setContent("虚拟机迁移完成!" + result);
			unitedTaskThread.endTask(taskObj);
		} catch (Exception e) {
			taskThread.interrupt();
			taskObj.setContent("迁移虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
					+ e.getMessage() + e);
			unitedTaskThread.endTask(taskObj);
			result = "迁移虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败Failed!原因："
					+ e.getMessage() + e;
		}
		return "results";
	}

	/**
	 * 
	 * @Title: executeRelocateVM
	 * @Description: 执行虚拟机重定位
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-6-24 下午3:20:32
	 */
	public String executeRelocateVM() {
		final String taskUUID = RandomUUID.getUuid();
		String[] user = new String[] { session.get("id").toString(),
				session.get("account").toString(), session.get("name").toString() };
		final String createrName = user[1];
		GlobalTaskObj taskObj = new GlobalTaskObj();
		taskObj.setId(taskUUID);
		Thread taskThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GlobalTaskObj taskObj = new GlobalTaskObj();
				taskObj.setName("迁移虚拟机");
				taskObj.setCreaterName(createrName);
				taskObj.setId(taskUUID);
				taskObj.setType(enumtype.Types.GlobalTaskType.DEPLOY.toString());
				unitedTaskThread.updateTaskProgress(taskObj);
			}
		});
		taskThread.start();// 更新任务线程

		try {
			if (virtualMachineUnitedVO == null) {
				virtualMachineUnitedVO = new VirtualMachineUnitedVO();
			}
			virtualMachineUnitedVO.setConnectCode(connect_id);
			virtualMachineUnitedVO.setVmCode(uuid);
			virtualMachineUnitedVO.setHostCode(parent_uuid);
			virtualMachineUnitedVO.setDatastoreCode(dataStoreCode);
			virtualMachineUnitedVO.setVType(vtype);
			result = unitedVMService.relocateVM(virtualMachineUnitedVO);

			taskThread.interrupt();
			taskObj.setContent("虚拟机迁移完成!" + result);
			unitedTaskThread.endTask(taskObj);
		} catch (Exception e) {
			taskThread.interrupt();
			taskObj.setContent("迁移虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
					+ e.getMessage() + e);
			unitedTaskThread.endTask(taskObj);
			result = "迁移虚拟机 " + virtualMachineUnitedVO.getNewVmName() + "失败Failed!原因："
					+ e.getMessage() + e;
		}
		return "results";
	}

	/**
	 * @Title: queryDataStoreInfo
	 * @Description: 查询存储
	 * @return void
	 * @throws
	 * @Date 2014-6-1 下午3:25:46
	 * @author lipp
	 */
	public void queryDataStoreInfo() {
		DataStoreObj storeObj = new DataStoreObj();
		storeObj.setHOST_ID(uuid);
		storeObj.setConnectId(connect_id);
		storeObj.setDATACENTER_ID(datacenterCode);
		storeObj.setStore_uuid(dataStoreCode);
		storeObj = (DataStoreObj) dataStoreService.queryForHostIdsList(storeObj).get(0);
		storeObj.setCAPACITY(NumberFormatUtil.numFormat(
				Double.parseDouble(storeObj.getCAPACITY()) / 1024, "#.##"));
		storeObj.setPurchaseSpace(NumberFormatUtil.numFormat(
				Double.parseDouble(storeObj.getPurchaseSpace()) / 1024, "#.##"));
		storeObj.setFREE_SPACE(NumberFormatUtil.numFormat(
				Double.parseDouble(storeObj.getFREE_SPACE()) / 1024, "#.##"));
		// String info = "该存储总容量：" + storeObj.getCAPACITY() + "MB，置备容量： "
		// + storeObj.getPurchaseSpace() + "MB，剩余容量： " +
		// storeObj.getFREE_SPACE() + "MB";
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriterOut.printWirter(response, JacksonUtil.toJson(storeObj));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Title: queryForAbstract
	 * @Description: 统一树首页的摘要
	 * @param
	 * @return List<IndexAbstract>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime 2013-8-2 下午11:13:58
	 */
	@MethodLog(remark = "UnitedTreeAction-indexAbstract", type = 4, message = "统一树首页的摘要")
	public String indexAbstract() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list = unitedTreeService.queryForAbstract();

		return "indexAbstract";
	}

	/**
	 * 
	 * @Title: getAdjustState
	 * @Description: 判断能否调整虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Aug 10, 2013 3:34:50 PM
	 */
	public String getAdjustState() {
		if (vtype.equals("1")) {// vmware虚拟机
			adjusteState = true;
		} else {// xen虚拟机
			if (state.equals("1")) {// 关机
				adjusteState = true;
			} else {
				adjusteState = false;
			}
		}
		return "adjustState";
	}

	/**
	 * 
	 * @Title: validateName
	 * @Description: 验证表单关键字是否唯一
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 20130809
	 */
	public String validateName() throws SQLException {
		int count = 0;
		if (name != null && !"".equals(name)) {
			UnitedTreeObj u = new UnitedTreeObj();
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u.setName(name);
			u.setVtype(vtype);
			count = unitedTreeService.queryName(u);

		}
		JSONObject js = new JSONObject();
		js.put("count", count);
		response.setContentType("type/html;charset=UTF-8");
		// out = response.getWriter();
		// out.println(js);
		// out.close();
		PrintWriterOut.printWirter(response, js);
		return null;
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 查询
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-12 下午7:09:42
	 */
	public String queryTreeNodeByName() {
		if (name != null && !"".equals(name)) {
			unitedTreeObj = new UnitedTreeObj();
			try {
				name = URLDecoder.decode(name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Pattern p = Pattern.compile("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
			Matcher m = p.matcher(name);
			if (m.find()) {// 若查询时传入的为IP地址
				unitedTreeObj.setIp(name);
			} else {
				unitedTreeObj.setName(name);
			}
			unitedTreeObj.setType(type);
			try {
				if (UnitedConstant.VM_AND_HOST.equals(type)) {// 查询宿主机及虚拟机
					// 宿主机列表
					unitedTreeObj.setType(UnitedConstant.HOST);
					List<UnitedTreeObj> hostList = unitedTreeService
							.queryTreeNodeByName(unitedTreeObj);
					// 虚拟机列表
					unitedTreeObj.setType(UnitedConstant.VM);
					List<UnitedTreeObj> vmList = unitedTreeService
							.queryTreeNodeByName(unitedTreeObj);
					boolean resultAdd = hostList.addAll(vmList);
					// 业务系统下虚拟机列表
					BusiManagerTree busi = new BusiManagerTree();
					busi.setType(2);
					busi.setName(name);
					List busiVMlist = busiManagerTreeService.queryVmBySubSystem(busi);
					hostList.addAll(busiVMlist);
					resultList = hostList;
				} else if ("2".equals(type)) {// 搜索业务系统列表
					BusiManagerTree busi = new BusiManagerTree();
					busi.setType(Integer.parseInt(type));
					busi.setName(name);
					resultList = busiManagerTreeService.queryVmBySubSystem(busi);
				} else {// 搜索主机或虚拟机
					resultList = unitedTreeService.queryTreeNodeByName(unitedTreeObj);
				}

			} catch (SQLException e) {
				LOG.error("查询树节点数据异常" + e.getMessage() + e, e);
			}
		}
		return "querynode";
	}

	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public String getExpandNodes() {
		try {
			name = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		UnitedTreeObj u = new UnitedTreeObj();
		if (name != null && !"".equals(name)) {
			u.setName(name);
		}
		if (type != null && !"".equals(type)) {
			u.setType(type);
		}
		try {
			if (UnitedConstant.VM_AND_HOST.equals(type)) {// 查询全部节点
				u.setType(UnitedConstant.HOST);
				List listHost = new ArrayList();
				try {
					listHost = unitedTreeService.getExpandNodesForHost(u);
				} catch (Exception e) {
					LOG.error("查询宿主机展开节点异常！");
				}
				List listVM = new ArrayList();
				u.setType(UnitedConstant.VM);
				try {
					listVM = unitedTreeService.getExpandNodesForVM(u);
				} catch (Exception e) {
					LOG.error("查询宿主机展开节点异常！");
				}
				boolean resultAdd = listHost.addAll(listVM);
				resultList = listHost;
			} else if (UnitedConstant.HOST.equals(type)) {// 查询展开主机所需节点信息
				resultList = unitedTreeService.getExpandNodesForHost(u);
			} else {// 查询展开虚拟机对应节点信息(包括通过业务系统查询虚拟机)
				u.setType(UnitedConstant.VM);
				resultList = unitedTreeService.getExpandNodesForVM(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "expandNode";

	}

	/**
	 * @Title: selectVlan
	 * @Description: 根据传过来的网络域id选择该网络域下面的vlan
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-16 上午9:08:03
	 */
	public void selectVlan() {
		List<TbCloud2NetInfoObj> netList = netService.queryForListByDomainId("");
		JSONArray json = new JSONArray();
		JSONObject jo = new JSONObject();
		for (TbCloud2NetInfoObj tbCloud2NetInfoObj : netList) {
			jo.put("net_id", tbCloud2NetInfoObj.getNET_ID());
			jo.put("net_name", tbCloud2NetInfoObj.getNAME());
			json.add(jo);
		}
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(json.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: selectIp
	 * @Description: 根据vlan查询其对应的IP列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-9-8 下午1:27:07
	 */
	public String selectIpByVlan() {
		TbCloud2IpInfoObj ip = new TbCloud2IpInfoObj();
		ip.setISUSED("0");
		ip.setNET_ID(uuid);
		// ip.setPagination(this.getPaginater().initPagination(request));
		resultList = ipService.queryForListByObj(ip);
		return "selectIpByVlan";
	}

	/**
	 * 
	 * @Title: syncCluster
	 * @Description: xen同步集群
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws Exception
	 * @throws SQLException
	 * @createtime 2013-9-18 上午11:17:33
	 */
	public String syncCluster() {
		result = "操作成功!";
		// String parent_id = request.getParameter("id");
		xenDataCompareService.xenDataCompare();// xen数据同步
		// xenDataCompareService.compareCluster();
		return "syncCluster";
	}

	/**
	 * 
	 * @Title: selectDataStore
	 * @Description: 通过主机uuid和connect_id查询存储
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-27
	 */
	public void selectDataStore() {
		DataStoreObj data = new DataStoreObj();
		data.setHOST_ID(parent_uuid);
		data.setConnectId(connect_id);
		storageList = dataStoreService.queryForListByObj(data);
		JSONArray json = JSONArray.fromObject(storageList);
		try {
			response.setCharacterEncoding("UTF-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(json.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: renameVM
	 * @Description: 虚拟机重命名
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-18 下午3:15:35
	 */
	public String renameVM() throws UnsupportedEncodingException, SQLException {
		UnitedTreeObj u = new UnitedTreeObj();
		u.setName(URLDecoder.decode(name, "utf-8"));
		u.setVtype(vtype);
		u.setUuid(uuid);
		u.setConnect_id(connect_id);
		u.setType(type);
		try {
			String js = unitedVMService.renameVM(u);
			PrintWriterOut.printWirter(response, js);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ------------------------------------------------------------
	/**
	 * 
	 * @Title: enterMaintenanceMode
	 * @Description: 主机进入维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:41:38
	 */
	public String enterMaintenanceMode() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		String vtype = request.getParameter("vtype");
		result = unitedTreeService.enterMaintenanceMode(vtype, hostUnitedVO);
		return "results";
	}

	/**
	 * 
	 * @Title: exitMaintenanceMode
	 * @Description: 主机退出维护模式
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-2-24 下午1:43:38
	 */
	public String exitMaintenanceMode() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		String vtype = request.getParameter("vtype");
		result = unitedTreeService.exitMaintenanceMode(vtype, hostUnitedVO);
		return "results";
	}

	// ===============================================================
	/**
	 * 
	 * @Title: editCluster
	 * @Description:进入编辑设置集群页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-1 下午1:56:32
	 */
	public String editClusterPage() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		try {
			clusterUnitedVO = unitedClusterService.getClusterInfo(obj);
			// liwq 集群所属主机集合
			TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
			hostInfoObj.setCluid(id);// 借用属性，此处表示集群id
			hostInfoObj.setConnectId(connect_id);// 属性借用，表示connectid
			hostInfoObj.setAllocated(1);
			resultList = hostInfoService.queryForListForCluster(hostInfoObj);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		return "edit_cluster";
	}

	/**
	 * 
	 * @Title: editCluster
	 * @Description: 编辑集群
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-1 下午2:34:06
	 */
	public String editCluster() {
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		obj.setVtype(vtype);
		try {
			result = unitedClusterService.editCluster(obj, clusterUnitedVO);
			PrintWriterOut.printWirter(response, result);
		} catch (HttpClientException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @Title: disConnectHost
	 * @Description: 断开主机连接
	 * @return void
	 * @throws
	 * @Date 2014-4-28 下午3:08:02
	 * @author lipp
	 */
	public void disConnectHost() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		result = unitedHostService.disConnectHost(vtype, hostUnitedVO);
		PrintWriterOut.printWirter(response, result);
	}

	/**
	 * @Title: changeHostPowerState
	 * @Description: 修改主机电源状态
	 * @return void
	 * @throws
	 * @Date 2014-4-28 下午3:23:39
	 * @author lipp
	 */
	public void changeHostPowerState() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		result = unitedHostService.changeHostPowerState(vtype, hostUnitedVO);
		PrintWriterOut.printWirter(response, result);
	}

	/**
	 * @Title: enterAwaitModeHost
	 * @Description: 主机进入待机模式
	 * @return void
	 * @throws
	 * @Date 2014-4-28 下午3:50:34
	 * @author lipp
	 */
	public void enterAwaitModeHost() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		result = unitedHostService.enterAwaitModeHost(vtype, hostUnitedVO);
		PrintWriterOut.printWirter(response, result);
	}

	/**
	 * @Title: exitAwaitModeHost
	 * @Description: 主机退出待机模式
	 * @return void
	 * @throws
	 * @Date 2014-4-28 下午3:51:51
	 * @author lipp
	 */
	public void exitAwaitModeHost() {
		if (hostUnitedVO == null) {
			hostUnitedVO = new HostUnitedVO();
		}
		result = unitedHostService.exitAwaitModeHost(vtype, hostUnitedVO);
		PrintWriterOut.printWirter(response, result);
	}

	/**
	 * 
	 * @Title: importOvfPage
	 * @Description: TODO(导入Ovf页面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String importOvfPage() {

		/*
		 * Url参数： parent_id='+treeNode.id +"&vtype="+treeNode.vtype
		 * +"&type="+treeNode.type +"&parent_uuid="+treeNode.uuid
		 * +"&uuid="+treeNode.uuid +"&connect_id="+treeNode.connect_id
		 */
		// 设置主机树节点
		unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setId(id);

		// 获取被修改虚拟机的详细信息
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setConnectId(connect_id);
		hostObj.setH_uuid(uuid);
		hostObj = hostInfoService.queryByObj(hostObj);

		// 使用 VirtualMachineUnitedVO 代替 OvfUnitedVO 传输对象
		virtualMachineUnitedVO = new VirtualMachineUnitedVO();
		virtualMachineUnitedVO.setIp(hostObj.getEq_ip());
		virtualMachineUnitedVO.setConnectCode(hostObj.getConnectId());
		virtualMachineUnitedVO.setHostCode(hostObj.getH_uuid());

		return "importOvf";
	}

	/**
	 * 
	 * @Title: importOvf
	 * @Description: TODO(保存Ovf)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String importOvf() {
		unitedTreeObj.getId();
		try {
			unitedTreeObj = unitedTreeService.queryByObj(unitedTreeObj);
		} catch (SQLException e) {
			LOG.error("查询树节点异常" + e.getMessage() + e, e);
		}
		unitedVMService.importOvf(unitedTreeObj, virtualMachineUnitedVO);
		return null;
	}

	/**
	 * 
	 * @Title: addDataStore
	 * @Description: 进入主机添加存储页面
	 * @param
	 * @return String
	 * @throws
	 * @author liwq
	 * @version 1.0
	 * @createtime 2014-6-1 上午10:50:34
	 */
	public String addDataStore() {
		return "dataStore";
	}

	/**
	 * 
	 * @Title: addDataStore
	 * @Description: 进入主机添加存储页面
	 * @param
	 * @return String
	 * @throws
	 * @author liwq
	 * @version 1.0
	 * @createtime 2014-6-1 上午10:50:34
	 */
	public void saveDataStore() {
		String saveResult = "";
		if (datastoreUnitedVO == null) {
			datastoreUnitedVO = new DatastoreUnitedVO();
		}
		datastoreUnitedVO.setConnectCode(connect_id);
		datastoreUnitedVO.setHostCode(uuid);
		saveResult = unitedHostService.saveHostDataStore(vtype, datastoreUnitedVO);
		PrintWriterOut.printWirter(response, saveResult);
	}
	
	
	/**
	 * 在非虚拟化资源池中添加主机
	 * 主机从空闲资源池中选择
	 * @return
	 */
	public String addHostSC() {
		return "host_sc";
	}

	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public ClusterUnitedVO getClusterUnitedVO() {
		return clusterUnitedVO;
	}

	public void setClusterUnitedVO(ClusterUnitedVO clusterUnitedVO) {
		this.clusterUnitedVO = clusterUnitedVO;
	}

	public DatastoreUnitedVO getDatastoreUnitedVO() {
		return datastoreUnitedVO;
	}

	public void setDatastoreUnitedVO(DatastoreUnitedVO datastoreUnitedVO) {
		this.datastoreUnitedVO = datastoreUnitedVO;
	}

	public HostUnitedVO getHostUnitedVO() {
		return hostUnitedVO;
	}

	public void setHostUnitedVO(HostUnitedVO hostUnitedVO) {
		this.hostUnitedVO = hostUnitedVO;
	}

	public VirtualMachineUnitedVO getVirtualMachineUnitedVO() {
		return virtualMachineUnitedVO;
	}

	public void setVirtualMachineUnitedVO(VirtualMachineUnitedVO virtualMachineUnitedVO) {
		this.virtualMachineUnitedVO = virtualMachineUnitedVO;
	}

	public DataStoreObj getDataStoreObj() {
		return dataStoreObj;
	}

	public void setDataStoreObj(DataStoreObj dataStoreObj) {
		this.dataStoreObj = dataStoreObj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List getStorageList() {
		return storageList;
	}

	public void setStorageList(List storageList) {
		this.storageList = storageList;
	}

	public List getNetList() {
		return netList;
	}

	public void setNetList(List netList) {
		this.netList = netList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getMigrateState() {
		return migrateState;
	}

	public void setMigrateState(Boolean migrateState) {
		this.migrateState = migrateState;
	}

	public Boolean getAdjusteState() {
		return adjusteState;
	}

	public TbCloud2HostInfoObj getHost() {
		return host;
	}

	public void setHost(TbCloud2HostInfoObj host) {
		this.host = host;
	}

	public void setAdjusteState(Boolean adjusteState) {
		this.adjusteState = adjusteState;
	}

	public UnitedTreeObj getUnitedTreeObj() {
		return unitedTreeObj;
	}

	public void setUnitedTreeObj(UnitedTreeObj unitedTreeObj) {
		this.unitedTreeObj = unitedTreeObj;
	}

	public String getVh_stat() {
		return vh_stat;
	}

	public void setVh_stat(String vh_stat) {
		this.vh_stat = vh_stat;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getDatacenterCode() {
		return datacenterCode;
	}

	public void setDatacenterCode(String datacenterCode) {
		this.datacenterCode = datacenterCode;
	}

	public String getDataStoreCode() {
		return dataStoreCode;
	}

	public void setDataStoreCode(String dataStoreCode) {
		this.dataStoreCode = dataStoreCode;
	}

	public HostManageForm getHostForm() {
		return hostForm;
	}

	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

}
