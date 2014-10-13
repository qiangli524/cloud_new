package com.sitech.basd.resource.web.united;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedOperateService;
import com.sitech.basd.resource.service.united.UnitedTaskThread;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualEthernetCardUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: UnitedOperateAction
 * </p>
 * <p>
 * Description:联通测试用例中新增加功能
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date 2013 八月 30
 */
@SuppressWarnings("serial")
@Controller("unitedOperateAction")
@Scope("prototype")
public class UnitedOperateAction extends CRUDBaseAction {
	private String uuid;
	private String vtype;
	private String connect_id;
	private String parent_uuid;
	private List queryList;
	private List queryList2;
	private List<DataStoreObj> datastoreList;
	private VirtualMachineUnitedVO virtualMachineUnitedVO;
	private VirtualEthernetCardUnitedVO virtualEthernetCardUnitedVO;// 网卡
	private VirtualDiskUnitedVO virtualDiskUnitedVO;// 磁盘
	@Resource
	private DataStoreService dataStoreService;
	@Resource
	private UnitedOperateService unitedOperateService;
	@Resource
	private UnitedTaskThread unitedTaskThread;
	@Resource
	private VMHostService vmHostService;
	@Resource
	private UnitedVMService unitedVMService;

	public List<DataStoreObj> getDatastoreList() {
		return datastoreList;
	}

	public void setDatastoreList(List<DataStoreObj> datastoreList) {
		this.datastoreList = datastoreList;
	}

	public VirtualDiskUnitedVO getVirtualDiskUnitedVO() {
		return virtualDiskUnitedVO;
	}

	public void setVirtualDiskUnitedVO(VirtualDiskUnitedVO virtualDiskUnitedVO) {
		this.virtualDiskUnitedVO = virtualDiskUnitedVO;
	}

	public VirtualEthernetCardUnitedVO getVirtualEthernetCardUnitedVO() {
		return virtualEthernetCardUnitedVO;
	}

	public void setVirtualEthernetCardUnitedVO(
			VirtualEthernetCardUnitedVO virtualEthernetCardUnitedVO) {
		this.virtualEthernetCardUnitedVO = virtualEthernetCardUnitedVO;
	}

	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public List getQueryList2() {
		return queryList2;
	}

	public void setQueryList2(List queryList2) {
		this.queryList2 = queryList2;
	}

	public VirtualMachineUnitedVO getVirtualMachineUnitedVO() {
		return virtualMachineUnitedVO;
	}

	public void setVirtualMachineUnitedVO(VirtualMachineUnitedVO virtualMachineUnitedVO) {
		this.virtualMachineUnitedVO = virtualMachineUnitedVO;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List getQueryList() {
		return queryList;
	}

	public void setQueryList(List queryList) {
		this.queryList = queryList;
	}

	/**
	 * 
	 * @Title: addnakedVM
	 * @Description:创建裸机页面
	 * @author duangh
	 * @date 2013 八月 30 14:03:35
	 * @return
	 */
	public String addnakedVM() {
		// 获取存储列表
		DataStoreObj dsObj = new DataStoreObj();
		dsObj.setHOST_ID(uuid);
		queryList = dataStoreService.queryForListByObj(dsObj);
		// 查选端口组信息
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		queryList2 = unitedOperateService.hostPortGroup(obj);
		return ADD;
	}

	/**
	 * 
	 * @Title: createOperate
	 * @Description:创建虚拟机裸机操作
	 * @author duangh
	 * @date 2013 八月 30 17:59:06
	 * @return
	 * @throws IOException
	 */
	public String createOperate() throws Exception {

		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(uuid);
		// obj.setParent_id(parent_id);
		obj.setVtype(vtype);
		obj.setId(parent_uuid);// 虚拟机父节点ID,即主机这个节点的ID
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
			String result = unitedOperateService.createOperate(obj, virtualMachineUnitedVO);
			taskThread.interrupt();
			taskObj.setContent("创建虚拟机" + virtualMachineUnitedVO.getNewVmName() + "完成!" + result);
			unitedTaskThread.endTask(taskObj);
		} catch (HttpClientException e) {
			taskThread.interrupt();
			taskObj.setContent("创建虚拟机" + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
					+ e.getMessage());
			unitedTaskThread.endTask(taskObj);
		}
		return null;
	}

	/**
	 * 
	 * @Title: editVMProperties
	 * @Description:进入编辑虚拟机属性界面
	 * @author duangh
	 * @date 2013 八月 30 22:50:16
	 * @return
	 */
	public String editVMProperties() {
		// 获取被修改虚拟机的详细信息
		VMHostObj vmobj = new VMHostObj();
		vmobj.setVH_UUID(uuid);
		vmobj = vmHostService.queryByObj(vmobj);

		// 得到虚拟机的网卡信息，暂时从接口去取
		if (virtualEthernetCardUnitedVO == null) {
			virtualEthernetCardUnitedVO = new VirtualEthernetCardUnitedVO();
		}
		virtualEthernetCardUnitedVO.setConnectCode(connect_id);
		virtualEthernetCardUnitedVO.setVmCode(uuid);
		virtualEthernetCardUnitedVO.setVType(vtype);
		queryList = unitedOperateService.getVnicInfo(virtualEthernetCardUnitedVO);

		// 得到虚拟机磁盘信息，从接口取
		if (virtualDiskUnitedVO == null) {
			virtualDiskUnitedVO = new VirtualDiskUnitedVO();
		}
		virtualDiskUnitedVO.setConnectCode(connect_id);
		virtualDiskUnitedVO.setVmCode(uuid);
		virtualDiskUnitedVO.setVType(vtype);
		List<VirtualDiskUnitedVO> diskList = unitedOperateService.getDiskInfo(virtualDiskUnitedVO);
		ActionContext.getContext().put("diskList", diskList);// 放到Context对象中

		// 查选端口组信息
		UnitedTreeObj obj = new UnitedTreeObj();
		obj.setConnect_id(connect_id);
		obj.setUuid(parent_uuid);// 主机的标识
		queryList2 = unitedOperateService.hostPortGroup(obj);

		if (vmobj != null) {
			virtualMachineUnitedVO = RevertEntity.formVMHostObjToVirtualMachineUnitedVO(vmobj);
		}
		return MODIFY;
	}

	/**
	 * 
	 * @Title: hostVswitch
	 * @Description:主机虚拟机交换机管理，联通测试用例时添加
	 * @author duangh
	 * @date 2013 九月 2 09:39:14
	 * @return
	 */
	public String hostVswitch() {
		VirtualSwitch vsObj = new VirtualSwitch();
		vsObj.setHostCode(uuid);
		vsObj.setConnect_id(connect_id);
		vsObj.setPagination(this.getPaginater().initPagination(request));// 分页
		queryList = unitedOperateService.hostVswitch(vsObj);
		return "vswitch";
	}

	/**
	 * '
	 * 
	 * @Title: addVnic
	 * @Description:调用接口添加虚拟机网卡
	 * @author duangh
	 * @date 2013 九月 6 09:34:49
	 * @return
	 * @throws IOException
	 */
	public String addVnic() {
		String result = unitedOperateService.addVnic(virtualEthernetCardUnitedVO);
		String function = "addCallback";
		callBack(function, result);
		return null;
	}

	/**
	 * 
	 * @Title: deleteVnic
	 * @Description:删除虚拟机网卡
	 * @author duangh
	 * @date 2013 九月 2 15:29:49
	 * @return
	 * @throws IOException
	 */
	public String deleteVnic() {
		String result = unitedOperateService.deleteVnic(virtualEthernetCardUnitedVO);
		String function = "callBack";
		callBack(function, result);
		return null;
	}

	/**
	 * 
	 * @Title: reconfigVnic
	 * @Description:重新配置虚拟机网卡
	 * @author duangh
	 * @date 2013 九月 6 15:31:25
	 * @return
	 */
	public String reconfigVnic() {
		String result = unitedOperateService.reconfigVnic(virtualEthernetCardUnitedVO);
		String function = "callBack";
		callBack(function, result);
		return null;
	}

	/**
	 * 
	 * @Title: adjustStorage
	 * @Description:进入虚拟机调整存储页面
	 * @author duangh
	 * @date 2013 九月 2 15:32:13
	 * @return
	 */
	public String adjustStorage() {
		return "storage";
	}

	/**
	 * 
	 * @Title: operateSystem
	 * @Description:虚拟机安装操作系统或者重新安装
	 * @author duangh
	 * @date 2013 九月 2 15:33:25
	 * @return
	 */
	public String operateSystem() {
		// 获取ISO路径
		queryList = unitedOperateService.isoPathList();
		return "operateSystem";
	}

	/**
	 * 
	 * @Title: addHardware
	 * @Description:进入增加硬件页面，目前只支持网卡,磁盘
	 * @author duangh
	 * @date 2013 九月 2 15:37:02
	 * @return
	 */
	public String addHardware() {
		// 查选端口组信息
		UnitedTreeObj obj = new UnitedTreeObj();
		String connectCode = request.getParameter("connect_id");
		String hostCode = request.getParameter("parent_uuid");
		obj.setConnect_id(connectCode);
		obj.setUuid(hostCode);// 主机的UUID
		queryList = unitedOperateService.hostPortGroup(obj);
		String datacenterCode = unitedVMService.getDataCenterCode(hostCode, connectCode);
		List<DataStoreObj> datastoreList = unitedOperateService.getDataStoreList(hostCode,
				connectCode, datacenterCode);
		if (datastoreList == null) {
			datastoreList = new ArrayList<DataStoreObj>();
		}
		this.datastoreList = datastoreList;
		return "addHardware";
	}

	/**
	 * 
	 * @Title: mountISO
	 * @Description:调用接口挂载ISO
	 * @author duangh
	 * @date 2013 九月 3 10:32:52
	 * @return
	 * @throws IOException
	 */
	public void mountISO() {
		String result = UnitedConstant.FAIL;
		result = unitedOperateService.mountISO(virtualMachineUnitedVO);
		String function = "callBack";
		callBack(function, result);
	}

	/**
	 * 
	 * @Title: rebootToBIOS
	 * @Description:重启进入BIOS
	 * @author duangh
	 * @throws IOException
	 * @date 2013 九月 3 11:39:48
	 */
	public void rebootToBIOS() {
		String result = unitedOperateService.rebootToBIOS(virtualMachineUnitedVO);
		String function = "toBIOSCallBack";
		callBack(function, result);
	}

	/**
	 * 
	 * @Title: createDisk
	 * @Description:虚拟机添加磁盘
	 * @author duangh
	 * @date 2013 九月 6 23:17:14
	 * @return
	 */
	public String createDisk() {
		String result = unitedOperateService.createDisk(virtualDiskUnitedVO);
		String function = "addCallback";
		callBack(function, result);
		return null;
	}

	public String deleteDisk() {
		String destroy = request.getParameter("destroy");
		String result = unitedOperateService.deleteDisk(virtualDiskUnitedVO, destroy);
		String function = "callBack";
		callBack(function, result);
		return null;
	}

	/**
	 * 
	 * @Title: listVmVdis
	 * @Description: 进入虚拟机的存储页面(xen)
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Nov 27, 2012 4:43:37 PM
	 */
	public String listVmVdis() {
		VirtualDiskUnitedVO vo = new VirtualDiskUnitedVO();
		vo.setConnectCode(connect_id);
		vo.setVmCode(uuid);
		vo.setVType(vtype);
		queryList = unitedOperateService.getDiskInfo(vo);
		return "listVmVdis";
	}

	// 返回结果到前台页面，执行函数
	public void callBack(String function, String result) {
		try {
			String callback = "<script>parent." + function + "('" + result + "')</script>";
			response.setContentType("text/html; charset=UTF-8");
			// PrintWriter p = response.getWriter();
			// p.print(callback);
			// p.flush();
			// p.close();
			PrintWriterOut.printWirter(response, callback);
		} catch (Exception e) {

		}
	}
}
