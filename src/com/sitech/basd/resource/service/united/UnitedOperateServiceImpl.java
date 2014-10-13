package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.resource.util.VirtualMachineType;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.dao.switches.VirtualSwitchDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.PortGroupUnitedVO;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.united.VirtualDiskModeVO;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualEthernetCardUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.UnitedRestURI;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedOperateService")
public class UnitedOperateServiceImpl implements UnitedOperateService {
	@Resource
	private VirtualSwitchDao virtualSwitchDao;
	@Resource
	private UnitedTreeDao unitedTreeDao;
	@Resource
	private VmAuthorityService vmAuthorityService;
	@Resource
	private VMHostDao vmHostDao;
	@Resource
	private VMwareDataCompareService vmwareDataCompareService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private DataStoreDao dataStoreDao;

	/**
	 * 
	 * @Title: createOperate
	 * @Description:创建裸机
	 * @author duangh
	 * @date 2013 八月 30 18:20:08
	 * @return
	 */
	@Override
	public String createOperate(UnitedTreeObj obj, VirtualMachineUnitedVO vo)
			throws HttpClientException {
		String result = UnitedConstant.FAIL;
		vo.setConnectCode(obj.getConnect_id());
		vo.setHostCode(obj.getUuid());
		VirtualMachineUnitedVO resultVO = createNakedVM(vo);// 调用接口创建裸机
		if (resultVO.getIsSuccess()) {
			result = UnitedConstant.SUCCESS;
			vo.setVmCode(resultVO.getVmCode());// 虚拟机创建成功后返回唯一标识
			insertVMinfo(obj, vo);// 创建成功后插入数据库等操作
		} else {
			result = resultVO.getLog();
		}
		return result;
	}

	/**
	 * 
	 * @Title: createNakedVM
	 * @Description:调用接口创建裸机
	 * @author duangh
	 * @date 2013 九月 3 17:50:28
	 * @param vo
	 * @return
	 * @throws HttpClientException
	 */
	public VirtualMachineUnitedVO createNakedVM(VirtualMachineUnitedVO vo)
			throws HttpClientException {
		vo = VirtualClient.post(UnitedRestURI.VMWARE_NAKEDVM, vo,
				new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
				});
		return vo;
	}

	/**
	 * 
	 * @Title: insertVMinfo
	 * @Description:虚拟机信息插入数据库
	 * @author duangh
	 * @date 2013 九月 3 17:59:21
	 * @param obj
	 * @param vo
	 */
	public void insertVMinfo(UnitedTreeObj obj, VirtualMachineUnitedVO vo) {
		// 插入虚拟机信息表
		try {
			insertVMTable(obj, vo);
		} catch (SQLException e) {
			// ibatis throws SQLException,I can do nothing.
		}
		// 插入tree表
		insertTreeTable(obj, vo);
		if (!validateAdmin(obj)) {// 验证是否为admin用户，如果不是插入entityuser
			// insertAuthorTable(obj);
		}
	}

	/**
	 * 
	 * @Title: insertVMTable
	 * @Description:将虚拟机信息插入虚拟机表
	 * @author duangh
	 * @date 2013 九月 4 15:45:35
	 * @param obj
	 * @param vo
	 * @throws SQLException
	 */
	public void insertVMTable(UnitedTreeObj obj, VirtualMachineUnitedVO vo) throws SQLException {
		VMHostObj vmhost = new VMHostObj();
		int vm_id = vmHostDao.queryVhostIdSequence();
		vmhost.setID(vm_id);
		vmhost.setVH_ID(vm_id + "");
		String eq_id = vmwareDataCompareService.getTbCloud2HostId(vo);
		vmhost.setEQ_ID(eq_id);
		vmhost.setVH_NAME(vo.getNewVmName());
		vmhost.setVH_UUID(vo.getVmCode());
		vmhost.setVH_CPU(vo.getNumCPUs() + "");
		vmhost.setVH_MEM(vo.getMemoryMB() + "");
		Double storageSizeMb = vo.getStorageSizeInMb();
		DecimalFormat dcFormat = new DecimalFormat("0.0");
		String fmStorageSizeMb = dcFormat.format(storageSizeMb);
		vmhost.setVH_STORAGE(fmStorageSizeMb);
		vmhost.setVH_SYSTEM(vo.getOperationSystemName());
		vmhost.setVH_IP(vo.getIp());
		vmhost.setConnectId(obj.getConnect_id());
		vmhost.setVH_STAT(UnitedConstant.POWEROFF);
		vmhost.setVH_TYPE(VirtualMachineType.VMWARE);
		vmhost.setVH_NETWORK(vo.getVifNum() + "");
		vmhost.setHostCode(obj.getUuid());
		vmHostDao.insertByVMhostObj(vmhost);
	}

	/**
	 * 
	 * @Title: insertTreeTable
	 * @Description:创建虚拟机完成后将信息插入tree表
	 * @author duangh
	 * @date 2013 九月 4 15:38:32
	 * @param obj
	 * @param vo
	 */
	public void insertTreeTable(UnitedTreeObj obj, VirtualMachineUnitedVO vo) {
		UnitedTreeObj insertObj = new UnitedTreeObj();
		insertObj.setConnect_id(vo.getConnectCode());
		insertObj.setName(vo.getNewVmName());
		insertObj.setParent_id(obj.getId());
		insertObj.setType(UnitedConstant.VM);
		insertObj.setVtype(obj.getVtype());
		insertObj.setUuid(vo.getVmCode());
		try {
			unitedTreeDao.insertByObj(insertObj);
		} catch (SQLException e) {
			// ibatis throws SQLException,I can do nothing.
		}
	}

	/**
	 * 
	 * @Title: insertAuthorTable
	 * @Description:将信息插入虚拟机权限表
	 * @author duangh
	 * @date 2013 九月 4 15:42:20
	 * @param obj
	 */
	public void insertAuthorTable(UnitedTreeObj obj) {
		VmAuthorityObj authorObj = new VmAuthorityObj();
		authorObj.setUSERID(obj.getUser_id());
		authorObj.setENTITY_NAME(obj.getName());
		authorObj.setENTITY_TYPE(UnitedConstant.VM);
		authorObj.setENTITY_ID(obj.getUuid());
		authorObj.setOPERAUTHORITY("0");// 给与创建虚拟机人所有的操作权限
		authorObj.setTYPE(obj.getVtype());
		authorObj.setCONNECT_ID(obj.getConnect_id());
		vmAuthorityService.insertByObj(authorObj);
	}

	/**
	 * 
	 * @Title: validateAdmin
	 * @Description:验证是否为admin用户,admin用户返回true，非admin用户返回false
	 * @author duangh
	 * @date 2013 九月 4 17:55:24
	 * @return
	 */
	public boolean validateAdmin(UnitedTreeObj obj) {
		String userAccount = "admin";
		boolean b = true;
		int userid = obj.getUser_id();
		TbSysUserinfoObj user = new TbSysUserinfoObj();
		user.setID(userid);
		user = userInfoService.queryByObj(user);
		if (user != null && userAccount.equals(user.getACCOUNT())) {
			b = false;
		}
		return b;
	}

	@Override
	public List<VirtualSwitch> hostVswitch(VirtualSwitch vsObj) {
		List<VirtualSwitch> result = virtualSwitchDao.listVirtualSwitch(vsObj);
		return result;
	}

	@Override
	public List<PortGroupUnitedVO> hostPortGroup(UnitedTreeObj obj) {
		// 先从接口查询，以后修改，从库中查询
		PortGroupUnitedVO portGroupUnitedVO = new PortGroupUnitedVO();
		String vtype = VirtualConstant.VT_VMWARE;
		String url = "network/" + vtype + "/portgroup/get/" + obj.getConnect_id() + "/"
				+ obj.getUuid();
		try {
			portGroupUnitedVO = VirtualClient.get(url,
					new JacksonUtil.TypeReference<PortGroupUnitedVO>() {
					});
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
		List<PortGroupUnitedVO> resultList = portGroupUnitedVO.getList();
		for (Object portgroup : resultList) {// 不显示VMKernal类型的端口组
			if (portgroup instanceof Map) {
				Map portgroupMap = (Map) portgroup;
				if ((Boolean) portgroupMap.get("isVMKernal")) {
					resultList.remove(portgroup);
				}
			}
		}
		return resultList;
	}

	@Override
	public String mountISO(VirtualMachineUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		vo.setVType(dealvType(vo.getVType()));
		try {
			ResultSet set = VirtualClient.put(UnitedRestURI.VMURI + vo.getVmCode()
					+ UnitedRestURI.ISOMOUNT, vo, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	// 前台页面传过来的vType为1,2,3,4,,要转化为对应的vmware,xen,kvm,ibm--------duangh
	private String dealvType(String webVtype) {
		String vType = null;
		if (UnitedConstant.VMWARE.equals(webVtype)) {
			vType = VirtualConstant.VT_VMWARE;
		} else if (UnitedConstant.XEN.equals(webVtype)) {
			vType = VirtualConstant.VT_XEN;
		}
		return vType;
	}

	@Override
	public String rebootToBIOS(VirtualMachineUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		vo.setVType(dealvType(vo.getVType()));
		try {
			ResultSet set = VirtualClient.put(UnitedRestURI.VMURI + vo.getVmCode()
					+ UnitedRestURI.BIOS, vo, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	/**
	 * 
	 * @Title: dealResult
	 * @Description:处理调用接口返回结果
	 * @author duangh
	 * @date 2013 九月 5 20:48:01
	 * @param resultSet
	 * @return
	 */
	public String dealResult(ResultSet resultSet) {
		String result = null;
		if (resultSet.getIsSuccess()) {
			result = UnitedConstant.SUCCESS;
		} else {
			result = resultSet.getLog();
		}
		return result;
	}

	@Override
	public List<String> isoPathList() {
		String key = "iso";// 关键字
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		obj.setKEY(key);
		obj = tbGlobalConfigService.queryByObj(obj);
		String[] values = obj.getVALUE().split(",");
		List<String> list = Arrays.asList(values);
		return list;
	}

	/**
	 * 
	 * @Title: getVnicInfo
	 * @Description:调用接口查询虚拟机的网卡信息
	 * @author duangh
	 * @date 2013 九月 6 10:58:08
	 * @param vo
	 * @return
	 */
	public List<VirtualEthernetCardUnitedVO> getVnicInfo(VirtualEthernetCardUnitedVO vo) {
		List<VirtualEthernetCardUnitedVO> list = null;
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		String param = UnitedRestURI.paramvType + vType + UnitedRestURI.paramconnectCode
				+ vo.getConnectCode();
		try {
			list = VirtualClient.get(UnitedRestURI.VMHARDWAREURI + vo.getVmCode()
					+ UnitedRestURI.VNIC + param,
					new JacksonUtil.TypeReference<List<VirtualEthernetCardUnitedVO>>() {
					});
		} catch (HttpClientException e) {

		}
		return list;
	}

	@Override
	public String addVnic(VirtualEthernetCardUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		vo.setVType(dealvType(vo.getVType()));
		try {
			ResultSet set = VirtualClient.post(UnitedRestURI.VMHARDWAREURI + vo.getVmCode()
					+ UnitedRestURI.VNIC, vo, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	@Override
	public String deleteVnic(VirtualEthernetCardUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		String param = UnitedRestURI.paramvType + vType + UnitedRestURI.paramconnectCode
				+ vo.getConnectCode();
		try {
			String url = UnitedRestURI.VMHARDWAREURI + vo.getVmCode() + UnitedRestURI.VNIC
					+ UnitedRestURI.SEPERATER + vo.getEthernetCardName() + param;
			ResultSet set = VirtualClient.delete(url, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	@Override
	public String reconfigVnic(VirtualEthernetCardUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		try {
			String url = UnitedRestURI.VMHARDWAREURI + vo.getVmCode() + UnitedRestURI.VNIC
					+ UnitedRestURI.SEPERATER + vo.getEthernetCardName();
			ResultSet set = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	@Override
	public String createDisk(VirtualDiskUnitedVO vo) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		vo.setCapacityInMB(vo.getCapacityInMB());// jsp中输入的单位是MB
		if (vo.getDiskMode() == null || vo.getDiskMode().equals("")) {
			vo.setDiskMode(VirtualDiskModeVO.PERSISTENT);
		}
		if ("-1".equals(vo.getDatastoreCode())) {
			vo.setVmdkPath("");
		} else {
			VMHostObj vmHostObj = getVmHostObj(vo.getConnectCode(), vo.getVmCode());
			String vmName = vmHostObj.getVH_NAME();
			// VMDK名称暂时使用时间生成
			String directory = "[" + vo.getDatastoreCode() + "] " + vmName + "/"
					+ DateUtil.getCurrentDateStrForWSIF() + ".vmdk";
			vo.setVmdkPath(directory);
		}

		try {
			String url = UnitedRestURI.VMHARDWAREURI + vo.getVmCode() + UnitedRestURI.VDISK;
			ResultSet set = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	@Override
	public List<VirtualDiskUnitedVO> getDiskInfo(VirtualDiskUnitedVO vo) {
		List<VirtualDiskUnitedVO> list = null;
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		String param = UnitedRestURI.paramvType + vType + UnitedRestURI.paramconnectCode
				+ vo.getConnectCode();
		try {
			list = VirtualClient.get(UnitedRestURI.VMHARDWAREURI + vo.getVmCode()
					+ UnitedRestURI.VDISK + param,
					new JacksonUtil.TypeReference<List<VirtualDiskUnitedVO>>() {
					});
		} catch (HttpClientException e) {

		}
		return list;
	}

	@Override
	public String deleteDisk(VirtualDiskUnitedVO vo, String destroy) {
		String result = UnitedConstant.FAIL;
		// 前台页面传过来的vType为1,2,3,4要转化为对应的vmware,xen,kvm,ibm
		String vType = dealvType(vo.getVType());
		vo.setVType(vType);
		String param = UnitedRestURI.paramvType + vType + UnitedRestURI.paramconnectCode
				+ vo.getConnectCode() + "&destroy=" + destroy;
		try {
			String url = UnitedRestURI.VMHARDWAREURI + vo.getVmCode() + UnitedRestURI.VDISK
					+ UnitedRestURI.SEPERATER + vo.getVmdkLabel() + param;
			ResultSet set = VirtualClient.delete(url, new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = dealResult(set);
		} catch (HttpClientException e) {
			result = "调用接口错误！原因:" + e.getMessage();
		}
		return result;
	}

	/**
	 * 
	 * @Title: getDataStoreList
	 * @Description: 获取存储列表
	 * @param
	 * @return List<DataStoreObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-21 下午2:04:43
	 */
	public List<DataStoreObj> getDataStoreList(String hostCode, String connectCode,
			String datacenterCode) {
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setConnectId(connectCode);
		dataStoreObj.setDATACENTER_ID(datacenterCode);
		dataStoreObj.setHOST_ID(hostCode);
		List<DataStoreObj> storeList = dataStoreDao.queryForListByObj(dataStoreObj);
		return storeList;
	}

	/**
	 * 
	 * @Title: getVmHostObj
	 * @Description: 查询虚拟机名称
	 * @param
	 * @return VMHostObj
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-21 下午2:35:49
	 */
	private VMHostObj getVmHostObj(String connectCode, String vmCode) {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setConnectId(connectCode);
		vmHostObj.setVH_UUID(vmCode);
		List<VMHostObj> vhList = vmHostDao.queryForListByObj(vmHostObj);
		vmHostObj = vhList.get(0);
		return vmHostObj;
	}
}
