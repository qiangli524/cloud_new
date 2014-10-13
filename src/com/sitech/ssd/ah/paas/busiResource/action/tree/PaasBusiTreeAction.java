package com.sitech.ssd.ah.paas.busiResource.action.tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.busiResource.service.statistics.PaasBusiStatisticsService;
import com.sitech.ssd.ah.paas.busiResource.service.tree.PaasBusiTreeService;
import com.sitech.ssd.ah.paas.busiResource.util.paasBusiConstant;
import com.sitech.ssd.ah.paas.domain.host.GreenPlumHostInfoObj;
import com.sitech.ssd.ah.paas.service.host.PaasHostInfoService;
import com.sitech.ssd.ah.reportform.vm.domain.VmReportForm;
import com.sitech.ssd.ah.reportform.vm.service.ReportformVM;
import com.sitech.vo.util.UnitedConstant;

@Controller("paasBusiTreeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class PaasBusiTreeAction extends BaseAction {
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(PaasBusiTreeAction.class);
	@Autowired
	PaasBusiTreeService paasBusiTreeService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	PaasBusiStatisticsService paasBusiStatisticsService;
	@Autowired
	PaasHostInfoService paasHostInfoService;
	@Autowired
	ReportformVM reportformVM;
	@Autowired
	private UnitedTreeService unitedTreeService;
	private VmReportForm vf;
	List<PaasBusiTreeObj> resultList;// 查询树节点返回集合
	List<VmReportForm> vmReportFormList;// 虚拟机集合
	List<GreenPlumHostInfoObj> hostInforesultList = new ArrayList();// 平台物理主机集合
	PaasBusiTreeObj paasBusiTreeObj;
	GreenPlumHostInfoObj greenPlumHostInfoObj;
	TbCloud2HostInfoObj obj;
	private String uuid;// 树节点唯一标识
	private String name;// 节点名字
	private String entity_id;// 实体ID
	private String parent_id;// 父节点
	private String node_type;// 节点类型
	private String server_type;// 服务类型
	private String result;// 操作结果
	private String eq_ids;// 系统需要关联的主机id
	private String h_names;// 系统需要关联的主机name
	private String vm_ids;// 虚拟机connct_id与vh_uuid拼接
	private String vm_names;// 虚拟机名称

	public VmReportForm getVf() {
		return vf;
	}

	public void setVf(VmReportForm vf) {
		this.vf = vf;
	}

	public String getEq_ids() {
		return eq_ids;
	}

	public String getVm_ids() {
		return vm_ids;
	}

	public void setVm_ids(String vm_ids) {
		this.vm_ids = vm_ids;
	}

	public String getVm_names() {
		return vm_names;
	}

	public void setVm_names(String vm_names) {
		this.vm_names = vm_names;
	}

	public void setEq_ids(String eq_ids) {
		this.eq_ids = eq_ids;
	}

	public String getH_names() {
		return h_names;
	}

	public void setH_names(String h_names) {
		this.h_names = h_names;
	}

	public TbCloud2HostInfoObj getObj() {
		return obj;
	}

	public void setObj(TbCloud2HostInfoObj obj) {
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<PaasBusiTreeObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<PaasBusiTreeObj> resultList) {
		this.resultList = resultList;
	}

	public PaasBusiTreeObj getPaasBusiTreeObj() {
		return paasBusiTreeObj;
	}

	public void setPaasBusiTreeObj(PaasBusiTreeObj paasBusiTreeObj) {
		this.paasBusiTreeObj = paasBusiTreeObj;
	}

	public List<GreenPlumHostInfoObj> getHostInforesultList() {
		return hostInforesultList;
	}

	public void setHostInforesultList(List<GreenPlumHostInfoObj> hostInforesultList) {
		this.hostInforesultList = hostInforesultList;
	}

	public GreenPlumHostInfoObj getGreenPlumHostInfoObj() {
		return greenPlumHostInfoObj;
	}

	public void setGreenPlumHostInfoObj(GreenPlumHostInfoObj greenPlumHostInfoObj) {
		this.greenPlumHostInfoObj = greenPlumHostInfoObj;
	}

	public List<VmReportForm> getVmReportFormList() {
		return vmReportFormList;
	}

	public void setVmReportFormList(List<VmReportForm> vmReportFormList) {
		this.vmReportFormList = vmReportFormList;
	}

	/**
	 * @Title: listPaasBusiTree
	 * @Description: 跳转至paas业务资源树页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午5:09:19
	 */
	public String listPaasBusiTree() {
		return "list";
	}

	/**
	 * @Title: tabs
	 * @Description: 跳转到tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 上午10:26:59
	 */
	public String tabs() {
		return "tabs";
	}

	/**
	 * @Title: asyncForTree
	 * @Description: 异步获取paas业务树节点数据
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午5:11:52
	 */
	public String asyncForTree() {
		resultList = paasBusiTreeService.queryForPaasBusiTree(request);
		return "tree";
	}

	/**
	 * @Title: addChildSys
	 * @Description: 跳转到添加子系统页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 上午11:15:05
	 */
	public String addChildSys() {
		return "addChildSys";
	}

	/**
	 * @Title: saveChildSys
	 * @Description: 保存子系统节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 下午12:49:26
	 */
	public String saveChildSys() {
		if (paasBusiTreeObj == null) {
			paasBusiTreeObj = new PaasBusiTreeObj();
		}
		paasBusiTreeObj.setNode_type(paasBusiConstant.CHILD_SYSTEM);
		paasBusiTreeObj.setParent_id(uuid);
		try {
			result = paasBusiTreeService.insertByObj(paasBusiTreeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * @Title: addApp
	 * @Description: 跳转到添加应用页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午9:26:24
	 */
	public String addApp() {
		return "addApp";
	}

	/**
	 * @Title: saveApp
	 * @Description: 保存应用节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午9:36:28
	 */
	public String saveApp() {
		if (paasBusiTreeObj == null) {
			paasBusiTreeObj = new PaasBusiTreeObj();
		}
		paasBusiTreeObj.setNode_type(paasBusiConstant.SERVICE);
		paasBusiTreeObj.setParent_id(uuid);
		try {
			result = paasBusiTreeService.insertByObj(paasBusiTreeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * @Title: addHost
	 * @Description: 跳转到添加主机页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 下午3:33:29
	 */
	public String addHosts() {
		if (greenPlumHostInfoObj == null) {
			greenPlumHostInfoObj = new GreenPlumHostInfoObj();
		}
		try {
			greenPlumHostInfoObj.setId(uuid);
			greenPlumHostInfoObj.setTableName("tb_paas_busi_tree");
			greenPlumHostInfoObj.setPagination(this.getPaginater().initPagination(request));// 分页
			hostInforesultList = paasHostInfoService.queryPhysicsHostList(greenPlumHostInfoObj);
		} catch (Exception e) {
			logger.error("查询出错", e);
			e.printStackTrace();
		}
		return "addHosts";
	}

	/**
	 * @Title: saveHosts
	 * @Description: 保存关联主机
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午8:55:27
	 */
	public String saveHosts() {
		result = paasBusiTreeService.toSaveHosts(parent_id, eq_ids, h_names, server_type);
		return "results";
	}

	/**
	 * @Title: addBusi
	 * @Description: 跳转到添加业务页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 下午6:03:37
	 */
	public String addBusi() {
		return "addBusi";
	}

	/**
	 * @Title: saveBusi
	 * @Description: 保存业务
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 下午6:04:39
	 */
	public String saveBusi() {
		if (paasBusiTreeObj == null) {
			paasBusiTreeObj = new PaasBusiTreeObj();
		}
		paasBusiTreeObj.setNode_type(paasBusiConstant.BUSINESS);
		paasBusiTreeObj.setParent_id(uuid);
		try {
			result = paasBusiTreeService.insertByObj(paasBusiTreeObj);
		} catch (Exception e) {
			result = "失败：" + e.getMessage() + e.getClass().getName();
		}
		return "results";
	}

	/**
	 * @Title: delChildSys
	 * @Description: 删除节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-28 上午9:01:49
	 */
	public String delNode() {
		result = paasBusiTreeService.deleteByObj(paasBusiTreeObj);
		return "results";
	}

	/**
	 * @Title: addVmHosts
	 * @Description: 跳转到添加虚拟机页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午3:16:48
	 */
	public String addVmHosts() {
		if (vf == null) {
			vf = new VmReportForm();
		}
		// 数据中心列表
		vf.setDataCenterList(initTreeList());
		vf.setUuid(uuid);
		vf.setPagination(this.getPaginater().initPagination(request));// 分页
		vmReportFormList = reportformVM.queryVmhostList(vf);
		return "addVmHosts";
	}

	/**
	 * @Title: saveVmHosts
	 * @Description: 保存虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-31 上午8:27:31
	 */
	public String saveVmHosts() {
		result = paasBusiTreeService.toSaveVmHosts(parent_id, vm_ids, vm_names, server_type);
		return "results";
	}

	/**
	 * @Title: initTreeList
	 * @Description: 得到数据中心列表
	 * @param
	 * @return List<UnitedTreeObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午3:32:23
	 */
	private List<UnitedTreeObj> initTreeList() {
		List<UnitedTreeObj> treeList = null;
		UnitedTreeObj unitedTreeObj = new UnitedTreeObj();
		unitedTreeObj.setType(UnitedConstant.DATACENTER);
		try {
			List<UnitedTreeObj> utreeList = unitedTreeService.queryForTreeList(unitedTreeObj);
			for (UnitedTreeObj unitedTreeObj2 : utreeList) {
				if (UnitedConstant.VMWARE.equals(unitedTreeObj2.getVtype())
						|| UnitedConstant.XEN.equals(unitedTreeObj2.getVtype())) {
					if (treeList == null) {
						treeList = new ArrayList<UnitedTreeObj>();
					}
					treeList.add(unitedTreeObj2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treeList;
	}
}
