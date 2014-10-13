package com.sitech.basd.sxcloud.cloud.web.workorder_sc;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.service.departproject.DepartProjectService;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.template.TemManService;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.workorder_sc.WorkOrderAudit;
import com.sitech.basd.sxcloud.cloud.domain.workorder_sc.WorkOrderObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.IpService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.service.workorder_sc.WorkOrderService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.yicloud.domain.busisystem.BusiSystemObj;
import com.sitech.basd.yicloud.service.busisystem.BusiSystemService;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.vmname.RandomNameForVM;
import com.sitech.vo.util.UnitedConstant;

@SuppressWarnings("all")
@Controller("workOrderAuditAction")
@Scope("prototype")
public class WorkOrderAuditAction extends BaseAction {

	@Resource
	private WorkOrderService workOrderServiceSc;

	@Autowired
	private RabbitMQUtil rabbitMQUtil;

	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	private WorkOrderObj workOrderObj;

	private WorkOrderAuditForm theForm;

	private List<WorkOrderObj> resultList;

	private List<DepartProjectObj> projectList;

	private String oper;// 操作

	private String operFrom;//

	private Integer type;// 工单类型

	private Integer auditype;// 审批状态

	private String resource_type;// 工单类型

	private String uuid;

	private String wrid;

	private String projectid;

	/**
	 * @Title: updateWorkOrderAudit
	 * @Description: 更改审批状态
	 * @param
	 * @return void
	 * @throws
	 * @author threej
	 * @version 1.0
	 * @createtime 2014年7月5日09:29:11
	 */
	public String updateWorkOrderAudit() {
		String UUID = request.getParameter("wuuid");
		WorkOrderObj workOrderObj = new WorkOrderObj();
		if (UUID != null && !"".equals(UUID)) {
			workOrderObj.setUUID(UUID);
			workOrderObj = workOrderServiceSc.queruForAuditInfo(workOrderObj);
		}
		try {
			if (theForm == null)
				theForm = new WorkOrderAuditForm();
			BeanUtils.copyProperties(theForm, workOrderObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		request.setAttribute("UUID", UUID);
		return "updateWorkOrderAudit";
	}

	/**
	 * 保存审批操作
	 */
	public void saveWorkOrderAudit() {
		// workOrderService.updateWorkOrderTable(workOrderObj);
		WorkOrderObj workOrderObj = new WorkOrderObj();
		WorkOrderAudit workOrderAudit = new WorkOrderAudit();
		try {
			BeanUtils.copyProperties(workOrderObj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// workOrderService.savaAudit(workOrderObj);

		String audit_date = "";
		audit_date = DateUtil.getCurrentDateStr();
		workOrderAudit.setAudit_date(audit_date);
		workOrderAudit.setAudit_type(workOrderObj.getAUDIT_TYPE());
		workOrderAudit.setWorkorderid(workOrderObj.getUUID());
		workOrderAudit.setAudit_info(theForm.getAudit_info());
		String account = session.get("account").toString();
		workOrderAudit.setAudit_user(account);
		// workOrderService.insertAudit(workOrderAudit);
		try {
			rabbitMQUtil.publishMessage("", "app.workOrder.audit.queue",
					workOrderAudit);
		} catch (RabbitMQException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title: listWorkOrderAudit
	 * @Description: 展示审批详情
	 * @param
	 * @return String
	 * @throws
	 * @author treej
	 * @version 1.0
	 * @createtime 2014年7月7日14:15:08
	 */
	public String listWorkOrderAudit() {

		String UUID = request.getParameter("uuid");
		String workorderid = request.getParameter("workorderid");
		WorkOrderObj workOrderObj = new WorkOrderObj();
		WorkOrderAudit workOrderAudit = new WorkOrderAudit();

		if (UUID != null && !"".equals(UUID)) {
			workOrderObj.setUUID(UUID);
			workOrderObj = workOrderServiceSc
					.queruForWorkOrderInfo(workOrderObj);
		}
		try {
			if (theForm == null) {
				theForm = new WorkOrderAuditForm();
			}
			BeanUtils.copyProperties(theForm, workOrderObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (UUID != null && !"".equals(UUID)) {
			workOrderObj.setUUID(UUID);
			workOrderAudit.setWorkorderid(workOrderObj.getUUID());
			List result = workOrderServiceSc.listAudit(workOrderAudit);
			theForm.setResultList(result);
		}
		workOrderObj.setWORKORDER_ID(UUID);
		resultList = workOrderServiceSc.queryResourceList(workOrderObj);
		return "listWorkOrderAudit";

	}

	/**
	 * @Title: listWorkOrderForTabs
	 * @Description: 展示工单信息用于业务资源
	 * @param
	 * @return String
	 * @throws
	 * @author yangl
	 * @version 1.0
	 * @createtime 2013-9-19
	 */
	public String listWorkOrderForTabs() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		if (workOrderObj.getCAMEFROM() != null
				&& "-1".equals(workOrderObj.getCAMEFROM())) {
			workOrderObj.setCAMEFROM(null);
		}
		if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT()) {
			workOrderObj.setWSTAT(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			workOrderObj.setPROJECT_USER_ID(account);
		}
		resultList = workOrderServiceSc.queryByObj(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();
		return "listWorkOrderForTabs";
	}

	/**
	 * @Title: workOrderAudit
	 * @Description: 审批工单
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-8 下午12:59:19
	 */
	public String workOrderAudit() {
		if (workOrderObj == null) {
			workOrderObj = new WorkOrderObj();
		}
		if (workOrderObj.getTYPE() != null && -1 == workOrderObj.getTYPE()) {
			workOrderObj.setTYPE(null);
		}
		if (workOrderObj.getPROJECT_ID() != null
				&& "-1".equals(workOrderObj.getPROJECT_ID())) {
			workOrderObj.setPROJECT_ID(null);
		}
		if (workOrderObj.getCAMEFROM() != null
				&& "-1".equals(workOrderObj.getCAMEFROM())) {
			workOrderObj.setCAMEFROM(null);
		}
		if (workOrderObj.getWSTAT() != null && -1 == workOrderObj.getWSTAT()) {
			workOrderObj.setWSTAT(null);
		}
		workOrderObj.setPagination(this.getPaginater().initPagination(request));

		String account = session.get("account").toString();
		String userId = session.get("id").toString();
		// 全局配置里边配置了几个用户的权限
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		String[] users = new String[] { "" };
		if (global != null) {
			users = global.getVALUE().split(",");
		}

		if (!"1".equals(userId)) {// 对于admin用户，不需要分配权限
			// 对于普通登录用户，需要进行权限控制
			int flag = 0;
			for (int i = 0; i < users.length; i++) {
				if (account.equals(users[i])) {
					flag = 1;
				}
			}
			if (flag != 1) {
				workOrderObj.setPROJECT_USER_ID(userId);
			}

		}
		workOrderObj.setAUDIT_TYPE("1");
		resultList = workOrderServiceSc.queryByObjByAudit(workOrderObj);
		projectList = workOrderServiceSc.queryProjectList();
		return "workOrderAudit";
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getOperFrom() {
		return operFrom;
	}

	public void setOperFrom(String operFrom) {
		this.operFrom = operFrom;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getWrid() {
		return wrid;
	}

	public void setWrid(String wrid) {
		this.wrid = wrid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<DepartProjectObj> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<DepartProjectObj> projectList) {
		this.projectList = projectList;
	}

	public WorkOrderObj getWorkOrderObj() {
		return workOrderObj;
	}

	public void setWorkOrderObj(WorkOrderObj workOrderObj) {
		this.workOrderObj = workOrderObj;
	}

	public List<WorkOrderObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<WorkOrderObj> resultList) {
		this.resultList = resultList;
	}

	public WorkOrderAuditForm getTheForm() {
		return theForm;
	}

	public void setTheForm(WorkOrderAuditForm theForm) {
		this.theForm = theForm;
	}
}
