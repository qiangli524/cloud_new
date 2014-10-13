package com.sitech.basd.yicloud.web.graph.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTaskThread;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.resource.service.united.UnitedVMService;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.dao.entitytree.EntityTreeDao;
import com.sitech.basd.yicloud.domain.entitytree.EntityConObj;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;
import com.sitech.basd.yicloud.util.TypeConstant;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.VirtualMachineUnitedVO;

/**
 * 
 * @author duangh <duangh@si-tech.com.cn>
 */
public class HandlerAction extends BaseAction {
	private EntityTreeDao entityTreeDao;
	private VMHostDao vmHostDao;
	private VirtualMachineUnitedVO virtualMachineUnitedVO;
	@Resource
	private UnitedTaskThread unitedTaskThread;
	@Resource
	private UnitedVMService unitedVMService;
	@Resource
	private UnitedTreeService unitedTreeService;

	public void setVmHostDao(VMHostDao vmHostDao) {
		this.vmHostDao = vmHostDao;
	}

	public void setEntityTreeDao(EntityTreeDao entityTreeDao) {
		this.entityTreeDao = entityTreeDao;
	}

	/** 改为统一树后修改 */
	@Deprecated
	public String createVMDeprecated() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmname = request.getParameter("vmname");
		String vmcpu = request.getParameter("vmcpu");
		String vmmem = request.getParameter("vmmem");
		String storage = request.getParameter("storage");
		if (storage != null && !storage.trim().equals("")) {
			storage = String.valueOf(Integer.parseInt(storage) * 1024 * 1024);
		}
		String cpuParam = null;
		String memParam = null;
		String storageParam = null;

		String template = "vm-818";// 模板code，暂时写死，名为duangh的虚拟机
		String host_name = "host-42";// 虚拟机创建的主机，暂时写死，默认创建在172.21.3.34上
		String dsName = "datastore1";// 虚拟机创建的存储,默认创建在172.21.3.34的datastore1上
		String invokeResult = null;
		// 调用接口创建虚拟机
		try {
			String url = "/vmware/domain/clone/" + "[vmName:" + template + "].[cloneName:" + vmname
					+ "].[hostName:" + host_name + "].[dsName:" + dsName + "]/";
			if (vmcpu != null && !vmcpu.trim().equals("")) {
				url = url.replace("]/", "].");
				cpuParam = "[cpu:" + vmcpu + "]/";
				url += cpuParam;
			}
			if (vmmem != null && !vmmem.trim().equals("")) {
				url = url.replace("]/", "].");
				memParam = "[memoryMB:" + vmmem + "]/";
				url += memParam;
			}
			if (storage != null && !storage.trim().equals("")) {
				url = url.replace("]/", "].");
				storageParam = "[diskSize:" + storage + "].[op:Edit]/";
				url += storageParam;
			}

			String result = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(result);
			String cloneResult = (String) ps.get(ResponseCode.RESPONSE_CODE);
			if (cloneResult.equals(ResponseCode.SUCCESS)) {// 创建虚拟机成功后向数据库中插入信息
				String code = (String) ps.get(ResponseCode.CODE);

				// 查询主机在哪个数据中心和集群下
				EntityTreeObj etbj = new EntityTreeObj();
				etbj.setEntityId(host_name);
				List<Map<String, Integer>> temp = entityTreeDao.queryHostClDcId(etbj);
				Map<String, Integer> map = null;
				if (temp != null && temp.size() > 0) {
					map = temp.get(0);
				}
				// 向实体树表中插入数据
				EntityTreeObj en = new EntityTreeObj();
				en.setName(vmname);
				en.setType(TypeConstant.VMWARE_VM);
				en.setEntityId(code);
				en.setParentId(temp.get(0).get("hostId"));
				int ret1 = entityTreeDao.insertTreeNode(en);
				// 向tb_cloud_entity_con表中插入数据
				EntityConObj ec = new EntityConObj();
				try {
					ec.setDataCenterId(temp.get(0).get("dcId"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				ec.setClusterId(temp.get(0).get("clusterId"));
				ec.setName(vmname);
				ec.setType(0);
				ec.setEntityId(code);
				int ret3 = entityTreeDao.insertConObj(ec);

				// 向VMHOST_INFO表中插入数据
				int vmId = vmHostDao.queryVhostIdSequence();// 查询vmhost_info表序列作为虚拟机唯一标识；
				VMHostObj vmObj = new VMHostObj();
				vmObj.setVH_NAME(vmname);
				vmObj.setEQ_ID("1_01_001_0029_01");
				String VH_ID = createNewVhostId2(vmObj.getEQ_ID());
				vmObj.setVH_ID(VH_ID);
				vmObj.setID(vmId);
				vmObj.setVH_UUID(code);
				vmObj.setVH_TYPE("1");// vm_host_info表中的vmware的类型为1
				// 查询原虚拟机的数据
				VMHostObj tempObj = new VMHostObj();
				tempObj.setVH_UUID(template);
				tempObj = vmHostDao.queryByObj(tempObj);
				if (tempObj != null) {
					if (vmcpu != null && !vmcpu.trim().equals("")) {
						vmObj.setVH_CPU(vmcpu);
					} else {
						vmObj.setVH_CPU(tempObj.getVH_CPU());
					}
					if (vmmem != null && !vmmem.trim().equals("")) {
						vmObj.setVH_MEM(vmmem);
					} else {
						vmObj.setVH_MEM(tempObj.getVH_MEM());
					}
					if (storage != null && !storage.trim().equals("")) {
						vmObj.setVH_STORAGE(storage);
					} else {
						vmObj.setVH_STORAGE(tempObj.getVH_STORAGE());
					}
					vmObj.setVH_SYSTEM(tempObj.getVH_SYSTEM());
					vmObj.setVH_IP(tempObj.getVH_IP());
					vmObj.setVH_STAT(tempObj.getVH_STAT());
				}
				int ret4 = vmHostDao.insertByVMhostObj(vmObj);
				invokeResult = "1";
			}
		} catch (Exception e) {
			invokeResult = "-1";
		}
		try {
			String json = "{\"result\":\"" + invokeResult + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: createVM
	 * @Description: 基于安徽统一接口创建虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author duangh update by huojla@20131116
	 * @version 1.0
	 * @createtime 2013-11-16 上午10:09:28
	 */
	public String createVM() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vmname = request.getParameter("vmname");
		String vmcpu = request.getParameter("vmcpu");
		String vmmem = request.getParameter("vmmem");
		String storage = request.getParameter("storage");

		request.setAttribute("gateway", "");
		request.setAttribute("dns", "");
		request.setAttribute("ip", "");
		request.setAttribute("subnet", "");
		request.setAttribute("netWay", "1");
		String invokeResult = null;
		// 调用接口创建虚拟机
		UnitedTreeObj obj = new UnitedTreeObj();
		String connect_id = "VCENTER.m0";
		String parent_uuid = "host-10"; // 虚拟机创建的主机，暂时写死，默认创建在172.21.3.34上
		UnitedTreeObj treeObj = new UnitedTreeObj();
		treeObj.setUuid(parent_uuid);
		List<UnitedTreeObj> list = null;
		try {
			list = unitedTreeService.queryForTreeList(treeObj);
			treeObj = list.get(0);
			String vtype = "1";
			obj.setConnect_id(connect_id);
			obj.setParent_id(treeObj.getId());
			obj.setVtype(vtype);
			obj.setParent_uuid(parent_uuid);
			virtualMachineUnitedVO = new VirtualMachineUnitedVO();
			virtualMachineUnitedVO.setDatastoreCode("datastore-11");// 虚拟机创建的存储,默认创建在172.21.3.34的datastore1上
			virtualMachineUnitedVO.setNewVmName(vmname);
			virtualMachineUnitedVO.setVmCode("vm-254");// 模板code,暂时写死
			virtualMachineUnitedVO.setNumCPUs(Integer.parseInt(vmcpu));
			virtualMachineUnitedVO.setStorageSizeInMb(Double.parseDouble(storage));// 创建时，会将GB转换为MB
			virtualMachineUnitedVO.setMemoryMB(Integer.parseInt(vmmem));

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
				String result = unitedVMService.createVMByTem(obj, virtualMachineUnitedVO, request);
				taskThread.interrupt();
				taskObj.setContent("克隆虚拟机" + virtualMachineUnitedVO.getNewVmName() + "完成!" + result);
				unitedTaskThread.endTask(taskObj);
				invokeResult = "1";
			} catch (Exception e) {
				taskThread.interrupt();
				taskObj.setContent("克隆虚拟机" + virtualMachineUnitedVO.getNewVmName() + "失败!原因："
						+ e.getMessage());
				unitedTaskThread.endTask(taskObj);
				invokeResult = "-1";
			}
			String json = "{\"result\":\"" + invokeResult + "\"}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: createNewVhostId2
	 * @Description: 创建虚拟机ID
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Apr 28, 2012 3:24:59 PM
	 */
	public String createNewVhostId2(String eqId) {
		String vhostId = null;
		if (null != eqId && !"".equals(eqId)) {
			int seq = vmHostDao.queryVhostIdSequence();
			NumberFormat formatter = NumberFormat.getNumberInstance(); // 设置数据格式
			formatter.setMinimumIntegerDigits(3); // 设置最小长度
			formatter.setMaximumIntegerDigits(3); // 设置最大长度
			vhostId = eqId + "_" + formatter.format(seq);
		}
		return vhostId;
	}
}
