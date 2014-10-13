package com.sitech.basd.resource.web.united.vm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sitech.basd.resource.domain.united.vm.VmDatastoreRelationObj;
import com.sitech.basd.resource.service.united.vm.VmDatastoreRelationService;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

@Component("vmDatastoreRelationAction")
public class VmDatastoreRelationAction extends BaseAction {

	@Autowired
	private VmDatastoreRelationService vmDatastoreRelationService;

	private VmDatastoreRelationObj vmDatastoreRelationObj;

	// 虚拟机列表
	private List<VMHostObj> vmList;
	// 存储块列表
	private List<DataStoreObj> dataStoreList;

	public List<DataStoreObj> getDataStoreList() {
		return dataStoreList;
	}

	public void setDataStoreList(List<DataStoreObj> dataStoreList) {
		this.dataStoreList = dataStoreList;
	}

	public List<VMHostObj> getVmList() {
		return vmList;
	}

	public void setVmList(List<VMHostObj> vmList) {
		this.vmList = vmList;
	}

	public VmDatastoreRelationObj getVmDatastoreRelationObj() {
		return vmDatastoreRelationObj;
	}

	public void setVmDatastoreRelationObj(VmDatastoreRelationObj vmDatastoreRelationObj) {
		this.vmDatastoreRelationObj = vmDatastoreRelationObj;
	}

	/**
	 * 
	 * @Title: vmListForDatastore
	 * @Description: 获得存储的虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-17 上午11:15:29
	 */
	public String vmListForDatastore() {
		List<VmDatastoreRelationObj> relationList = null;

		VmDatastoreRelationObj obj = new VmDatastoreRelationObj();
		// 通过存储uuid查询虚拟机与存储相关信息
		relationList = vmDatastoreRelationService
				.queryRelationListByDatastoreuuid(vmDatastoreRelationObj);
		// 找到一个正确的数据
		if (relationList.size() != 0) {
			for (int i = 0; i < relationList.size(); i++) {
				if (relationList.get(i).getConnectid() != null
						&& relationList.get(i).getVtype() != null) {
					obj = relationList.get(i);
					break;
				}

			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (obj != null) {
			vmList = vmDatastoreRelationService.queryForList(obj);

		}

		return "vmListForDatastore";
	}

	/**
	 * 
	 * @Title: datastoreListForVm
	 * @Description: 查询虚拟机的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-17 下午2:28:49
	 */
	public String datastoreListForVm() {
		List<VmDatastoreRelationObj> relationList = null;
		VmDatastoreRelationObj obj = new VmDatastoreRelationObj();
		// 通过存储uuid查询虚拟机与存储相关信息
		relationList = vmDatastoreRelationService.queryRelationListByVmuuid(vmDatastoreRelationObj);
		// 找到一个正确的数据
		if (relationList.size() != 0) {
			for (int i = 0; i < relationList.size(); i++) {
				if (relationList.get(i).getConnectid() != null
						&& relationList.get(i).getVtype() != null) {
					obj = relationList.get(i);
					break;
				}

			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (obj != null) {
			dataStoreList = vmDatastoreRelationService.queryDatastoreForList(obj);

		}
		return "datastoreListForVm";
	}
}
