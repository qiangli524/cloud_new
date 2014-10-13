package com.sitech.basd.resource.web.united;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.global.HostGlobalObj;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.service.global.HostGlobalService;
import com.sitech.basd.resource.service.global.VmGlobalService;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.web.xen.form.XenManForm;

@Controller("tabAbstractAction")
@Scope("prototype")
public class TabAbstractAction extends BaseAction {

	private String uuid; // 当前结点的id比如：集群、主机
	private String connect_id;// 当前模块所在连接池id
	private Integer countHost;// 当前集群结点下主机的个数
	private Integer countVm; // 当前集群下虚拟机的个数
	private TbCloud2HostInfoObj hostInfo; // 主机信息
	private VMHostObj vmObj; // 虚拟机信息
	private List<DataStoreObj> dataStoreList;// 主机存储信息列表

	@Autowired
	private HostGlobalService hostGlobalService;
	@Autowired
	private VmGlobalService vmGlobalService;
	@Autowired
	private HostInfoService hostInfoService;
	@Autowired
	private VMHostService vmHostService;

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
				Double storageM = Double.parseDouble(vmObj.getVH_STORAGE());
				Double storageG = storageM / 1024.00;
				vmObj.setVH_STORAGE(df.format(storageG).toString());
			}
		}
		return "vmInfo";
	}

	/**
	 * 
	 * @Title: hostStorage
	 * @Description: 查询主机的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime aug 1, 2013 11:50 PM
	 */
	public String hostStorage() {
		DataStoreObj obj = new DataStoreObj();
		if (uuid != null && !"".equals(uuid)) {
			obj.setHOST_ID(uuid);
		}
		if (connect_id != null && !"".equals(connect_id)) {
			obj.setConnectId(connect_id);
		}
		dataStoreList = hostGlobalService.queryForListByObj(obj);
		return "hostStorage";
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

}
