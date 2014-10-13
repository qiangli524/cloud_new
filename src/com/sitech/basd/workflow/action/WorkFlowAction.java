package com.sitech.basd.workflow.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.emobile.bpm.invoker.WorkflowDefInvoker;
import com.emobile.bpm.invoker.bean.ProcessModelBean;
import com.emobile.bpm.invoker.exception.BPMException;
import com.sitech.basd.cloud3.dao.departproject.DepartProjectDao;
import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.dao.net.TbNetDao;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder.WorkOrderObj;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.workflow.dao.WorkOrderResourceDao;
import com.sitech.basd.workflow.domain.NewResourceFormObj;
import com.sitech.basd.workflow.domain.VMBean;
import com.sitech.basd.yicloud.dao.busisystem.BusiSystemDao;
import com.sitech.ssd.bpm.common.BaseDao;
import com.sitech.ssd.bpm.common.Pagination;
import com.sitech.ssd.bpm.domain.WorkFlow;
import com.sitech.ssd.bpm.domain.WorkFlowConstant;
import com.sitech.ssd.bpm.service.AttachmentService;
import com.sitech.ssd.bpm.service.WorkFlowService;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("workFlowAction")
@Scope("prototype")
@SuppressWarnings({ "rawtypes", "unchecked","unused" })
public class WorkFlowAction extends BaseAction  {

	final Logger log = Logger.getLogger(WorkFlowAction.class);
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private BusiSystemDao busiSystemDao;
	@Autowired
	private TbIpDao tbIpDao;
	@Autowired
	private TbNetDao tbNetDao;
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private DepartProjectDao departProjectDao;
	@Autowired
	private WorkOrderResourceDao workOrderResourceDao; 
	@Autowired
	private WorkFlowService bpmWorkFlowService;
	@Autowired
	private com.sitech.basd.workflow.service.WorkFlowService workFlowService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private BaseDao bpmBaseDao;
	
	private NewResourceFormObj formObj;
	
	public NewResourceFormObj getFormObj() {
		return formObj;
	}

	public void setFormObj(NewResourceFormObj formObj) {
		this.formObj = formObj;
	}

	private List<Map<String, String>> bpmWorkFlowList;
	private List<String> systemList;
	public List<String> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<String> systemList) {
		this.systemList = systemList;
	}

	private List temList;
	private List busiList;
	public List getBusiList() {
		return busiList;
	}

	public void setBusiList(List busiList) {
		this.busiList = busiList;
	}

	public List getTemList() {
		return temList;
	}

	public void setTemList(List temList) {
		this.temList = temList;
	}

	public List getBpmWorkFlowList() {
		return bpmWorkFlowList;
	}

	public void setBpmWorkFlowList(List bpmWorkFlowList) {
		this.bpmWorkFlowList = bpmWorkFlowList;
	}

	public List<WorkFlow> my() {
		String username = session.get("account").toString();
		List<WorkFlow> list = new ArrayList<WorkFlow>();
		WorkFlow obj = new WorkFlow();
		obj.setAccount(username);
		obj.setQueryType(WorkFlowConstant.QUERY_TYPE_MY);
		obj.setOrderStatus(WorkFlowConstant.ORDER_STATUS_NORMAL);
		Pagination pa = new Pagination(10);
		pa.currentPageNo = 1;
		pa.pageSize = 10;
		pa.pageCount = 0;
		pa.totalCount = 0;
		obj.setPagination(pa);
		list = bpmWorkFlowService.getWorkFlowList(obj);
		return list;
	}
	
	public String getAllFlowList(){
		WorkflowDefInvoker  workflowDefInvoker  = new WorkflowDefInvoker (session.get("account").toString());
		try {
			ProcessModelBean[] beans = workflowDefInvoker.getAllDefinition();
			bpmWorkFlowList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < beans.length; i++) {
				Map<String, String> m = new HashMap<String, String>();
				m.put("desc", beans[i].getPDesc());
				m.put("name", beans[i].getPpName());
				m.put("author",beans[i].getPAuthor());
				m.put("version", beans[i].getPVersion());
				m.put("id", beans[i].getPMId()+"");
				bpmWorkFlowList.add(m);
			}
		} catch (BPMException e) {
			e.printStackTrace();
		}
		return "allFlow";
	}
	
	public String newWorkFlow(){
		String resourceName = request.getParameter("resourceName");
		if (resourceName.equals("resource"))
		{
			try {
				systemList = temManDao.queryTemSystemList();
				systemList.add(0, "请选择操作系统");
				
				busiList = getBusinessList();
				Map m = new HashMap();
				m.put("id", "-1");
				m.put("name", "请选择业务系统");
				busiList.add(0,m);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "resourceApply";
		}
		return "resourceApply";
	}
	
	/**
	 * @Title: getTemSystemList
	 * @Description: 查询模板中的系统名称
	 * @return
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:50:50
	 */
	public String getTemSystemList(){
		 try {
			List<String> list = temManDao.queryTemSystemList();
			String json = JacksonUtil.toJson(list);
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: getTemListBySystem
	 * @Description: 根据系统名称，查询模板中的软件
	 * @return
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-18 下午3:52:38
	 */
	public String getTemListBySystem(){
		try {
			String name = request.getParameter("systemName");
			List<TemManObj> list = temManDao.queryTemBySystem(name);
			String json = JacksonUtil.toJson(list);
			System.out.println(json);
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: getProjectList
	 * @Description: 查询所有项目列表
	 * @return
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-18 下午6:30:05
	 */
	public String getProjectList(){
		DepartProjectObj obj = new DepartProjectObj();
		String domain = getLoginDomain();
		if (!domain.equals("")){
			obj.setDomain(domain);
		}
		List<DepartProjectObj>  list = departProjectDao.queryForList(obj);
		String json = JacksonUtil.toJson(list);
		PrintWriterOut.printWirter(response, json);
		return null;
	}
	
	private String getLoginDomain(){
		return "";
	}
	
	public String todo(){
		return "todo";
	}
	
	public String saveWorkOrder(){
		if (formObj == null) {
			formObj = new NewResourceFormObj();
		}
		
		String uuid = RandomUUID.getUuid();
		WorkOrderObj obj = new WorkOrderObj();
		obj.setUUID(uuid);
		obj.setTYPE(0);
		obj.setDEAL_COUNT(0);
		obj.setRECEIVETIME(TimeformatCommon.getCurrentSysTimeByDefaultFormat());// 接收时间
		obj.setBOMC_UUID(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		obj.setWSTAT(0);
		obj.setSTATE(0);
		obj.setCAMEFROM("1");//加工单来源
		obj.setWORKORDER_ID(uuid);
		obj.setWORK_ORDER_TITLE(formObj.getWorkOrderTitle());
		obj.setWORK_ORDER_COMM_MSG(formObj.getRemark());
		try {
			workOrderResourceDao.insertWorkOrderTable(obj);
			
			//选择集群
			String netType = formObj.getNetType();
			String clusterId = "";
			if (netType.equals("out")){
				clusterId = "573c55c040254df6bc9a59d60ef971e4";
			}else{
				clusterId = chooseCluster();
			}
			
			List<VMBean> beans = new ArrayList<VMBean>();
			for (int i=1; i<=formObj.getCount(); i++){
				VMBean bean = new VMBean();
				bean.setCpuNums(formObj.getCpu()+"");
				bean.setHardDiskSize(formObj.getStore()+"");
				bean.setMemorySize(formObj.getMemory()+"");
				bean.setModelId(formObj.getModelId());
				bean.setName(new Date().getTime()+"");
				bean.setSubBusinessId(formObj.getBusiId());
				bean.setClusterId(clusterId);
				beans.add(bean);
			}
			
			
			insertCreateResource(beans, obj, formObj.getStoreStr());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: insertCreateResource
	 * @Description: 插入申请的任务
	 * @param
	 * @return void
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-12-11 下午1:10:11
	 */
	private void insertCreateResource(List<VMBean> beans, WorkOrderObj wobj, String storeStr) {
		int index = 0;
		if (busiList==null || busiList.size()==0)
			busiList = getBusinessList();
		List ips = new ArrayList();

		int k = 0;
		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			VMBean bean = (VMBean)iterator.next();
			wobj.setISREFERHOST(1);
			wobj.setID(RandomUUID.getUuid());
			wobj.setWORKORDER_ID(wobj.getUUID());
			wobj.setSTATUS(0);
			wobj.setDEAL_COUNT(0);
			wobj.setCPU_NUM(Integer.valueOf(bean.getCpuNums()));
			wobj.setMEM_SIZE(Integer.valueOf(bean.getMemorySize()));
//			wobj.setSR_SIZE(Double.valueOf(bean.getHardDiskSize()));
			wobj.setTEMPLATE_ID(bean.getModelId());
			wobj.setISREFERHOST(0);
			
			List hosts = chooseHost(bean.getClusterId());
			Map map = (Map)hosts.get(index);
			wobj.setHOST_ID(map.get("HOST_ID").toString());
			wobj.setISREFERHOST(1);
			
			for (Iterator iterator2 = busiList.iterator(); iterator2.hasNext();) {
				Map m = (Map) iterator2.next();
				if (m.get("id").toString().equals(bean.getSubBusinessId())){
					String tmpVlan = m.get("entity_id").toString();
					if (ips.size()==0)
						ips = chooseIP(tmpVlan);
					
					List tmp = getModelNicCount(bean.getModelId());
					Map model = (Map)tmp.get(0);
					int nicCount = Integer.valueOf(model.get("nic_count").toString());
					int tempCpu = Integer.valueOf(model.get("CPU").toString());
					if (tempCpu>wobj.getCPU_NUM())
						wobj.setCPU_NUM(tempCpu);
					int tempMem = Integer.valueOf(model.get("MEM").toString());
					if (tempMem>wobj.getMEM_SIZE())
						wobj.setMEM_SIZE(tempMem);
					Double tempStore = Double.valueOf(model.get("STORE").toString());
//					if (tempStore>wobj.getSR_SIZE())
					wobj.setSR_SIZE(tempStore);
					
					String ipStr = "";
					String vlan = "";
					int used = k;
					for (int i=used; i<used+nicCount; i++)
					{
						String tip = ips.get(i).toString();
						ipStr += tip + ",";
						TbCloud2IpInfoObj ipObj = new TbCloud2IpInfoObj();
						ipObj.setIPADDRESS(tip);
						ipObj.setISUSED("1");
						tbIpDao.updateIPByObj(ipObj);
						vlan += tmpVlan + ",";
						k++;
					}

					if (ipStr.length()>0)
						ipStr = ipStr.substring(0,ipStr.length()-1);
					
					if (vlan.length()>0)
						vlan = vlan.substring(0,vlan.length()-1);
					
					wobj.setNETWORK_ID(vlan);
					wobj.setIPADDRESS(ipStr);
					break;
				}
			}
			
			wobj.setVM_NAME(bean.getName()+index);
			wobj.setTEMPLATE_ID(bean.getModelId());
			wobj.setTEMPLATE_TYPE("VM");
			wobj.setPROJECT_ID(bean.getProjectId());
			wobj.setBUSISYSTEMID(bean.getSubBusinessId());
			index++;
			try {
				bpmBaseDao.getSqlMap().insert("WorkOrder.insertResource",wobj);
				String[] strs = storeStr.split(",");
				for (int i = 0; i < strs.length; i++) {
					Map param = new HashMap();
					param.put("resource_id", wobj.getID());
					param.put("stores", Integer.valueOf(strs[i])*1024);
					bpmBaseDao.getSqlMap().insert("WorkOrder.insertResourceStore",param);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String chooseCluster(){
		List list = new ArrayList();
		String clsterId = "";
		try {
			list = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryClusterCpu");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.size()>0){
			Map m = (Map)list.get(0);
			clsterId = m.get("kpi_value").toString();
		}
		return clsterId;
	}
	
	/**
	 * @Title: chooseHost
	 * @Description: 根据剩余存储大小，查询出符合要求的主机
	 * @param
	 * @return List
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-9-2 上午11:34:06
	 */
	private List chooseHost(String clusterId){
		List result = new ArrayList();
		try {
			result = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryForHostInfoByStore",clusterId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List chooseNet(){
		List result = new ArrayList();
		try {
			result = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryForIPInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List chooseIP(String net_id){
		List result = new ArrayList();
		try {
			result = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryForIp",net_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private List getModelNicCount(String modelId){
		List result = new ArrayList();
		try {
			result = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryForModelNicCount",modelId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List getBusinessList(){
		List result = new ArrayList();
		try {
			result = bpmBaseDao.getSqlMap().queryForList("WorkOrder.queryForBusi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
