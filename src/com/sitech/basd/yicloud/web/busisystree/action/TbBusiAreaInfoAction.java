package com.sitech.basd.yicloud.web.busisystree.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.service.order.OrderService;
import com.sitech.basd.component.tree.service.task.TaskService;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * <p>
 * Title: TbBusiAreaInfoAction
 * </p>
 * <p>
 * Description: 应用部署首页统计
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-17 下午2:20:01
 * 
 */
@SuppressWarnings("all")
@Controller("tbBusiAreaInfoAction")
@Scope("prototype")
public class TbBusiAreaInfoAction extends CRUDBaseAction {

	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;

	@Autowired
	private BusiHostService busiHostService;

	@Autowired
	private ProcessService processService;

	@Autowired
	private AppService appService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TaskService taskServiceDeploy;

	private TbBusiHostObj busiHostObj;// 部署主机

	private ProcessObj processObj;

	private OrderObj orderObj;

	private String workstatus;// 部署主机运行状态

	private String isnor;

	private String status;

	private List resultList;

	private List appList;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsnor() {
		return isnor;
	}

	public void setIsnor(String isnor) {
		this.isnor = isnor;
	}

	public OrderObj getOrderObj() {
		return orderObj;
	}

	public void setOrderObj(OrderObj orderObj) {
		this.orderObj = orderObj;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public ProcessObj getProcessObj() {
		return processObj;
	}

	public void setProcessObj(ProcessObj processObj) {
		this.processObj = processObj;
	}

	public TbBusiHostObj getBusiHostObj() {
		return busiHostObj;
	}

	public void setBusiHostObj(TbBusiHostObj busiHostObj) {
		this.busiHostObj = busiHostObj;
	}

	public String getWorkstatus() {
		return workstatus;
	}

	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * @Title: showBusiAreaInfoBiz
	 * @Description: 统计业务系统个数、应用个数、实例个数
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:28:55
	 */
	public void showBusiAreaInfoBiz() {
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		TbBusiSysTree tbBusiSysTree = new TbBusiSysTree();
		// 统计业务系统个数
		tbBusiSysTree.setType(1);
		int bizcount = tbBusiSysTreeService.countByObj(tbBusiSysTree);
		jo.put("bizcount", bizcount);

		// 统计基准应用个数
		tbBusiSysTree.setType(2);
		int appcount = tbBusiSysTreeService.countByObj(tbBusiSysTree);
		jo.put("appcount", appcount);

		// 统计部署实例个数
		tbBusiSysTree.setType(3);
		int examplecount = tbBusiSysTreeService.countByObj(tbBusiSysTree);
		jo.put("examplecount", examplecount);

		ja.add(jo);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ja.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: showBusiAreaInfoBusiHost
	 * @Description: 统计部署主机相关信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:58:54
	 */
	public void showBusiAreaInfoBusiHost() {
		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		int countBusiHost = busiHostService.countAll(tbBusiHostObj);
		// 查询正在运行的部署机
		tbBusiHostObj.setWORKSTATUS(1);
		int countWorkBusiHost = busiHostService.countBusiHost(tbBusiHostObj);
		// 查询已停止的部署机
		tbBusiHostObj.setWORKSTATUS(0);
		int countUnworkBusiHost = busiHostService.countBusiHost(tbBusiHostObj);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("countBusiHost", countBusiHost);
		jsonObject.put("countWorkBusiHost", countWorkBusiHost);
		jsonObject.put("countUnworkBusiHost", countUnworkBusiHost);
		jsonArray.add(jsonObject);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(jsonArray.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: showBusiAreaInfoProcess
	 * @Description: 统计进程相关信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午6:00:40
	 */
	public void showBusiAreaInfoProcess() {
		ProcessObj processObj = new ProcessObj();
		int processNum = processService.countProcess(processObj);
		// 异常进程数
		int urprocessNum = processService.countProcessUnNor();
		// 正常进程数
		processObj.setPROCESS_STATE(2);
		int rprocessNum = processService.countProcess(processObj);
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("processNum", processNum);
		jo.put("rprocessNum", rprocessNum);
		jo.put("urprocessNum", urprocessNum);
		ja.add(jo);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ja.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: showBusiAreaInfoTask
	 * @Description: 统计订单相关信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午6:00:59
	 */
	public void showBusiAreaInfoTask() {
		OrderObj orderObj = new OrderObj();
		TaskObj taskObj = new TaskObj();
		int orderNum = orderService.countOrderNum(orderObj);
		int taskNum = taskServiceDeploy.countTaskNum(taskObj);
		// 正在执行的个数
		taskObj.setSTATUS(1);
		int exeNum = taskServiceDeploy.countTaskNum(taskObj);
		// 成功个数
		int successNum = 0;
		// 失败个数
		int failureNum = 0;
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("orderNum", orderNum);
		jo.put("taskNum", taskNum);
		jo.put("exeNum", exeNum);
		jo.put("successNum", successNum);
		jo.put("failureNum", failureNum);
		ja.add(jo);
		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ja.toString());
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: listBusiHost
	 * @Description: 展示部署主机信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午6:02:44
	 */
	public String listBusiHost() {
		if (busiHostObj == null) {
			busiHostObj = new TbBusiHostObj();
		}
		if (workstatus != null && !"".equals(workstatus) && !"null".equals(workstatus)) {
			busiHostObj.setWORKSTATUS(Integer.parseInt(workstatus));
		}
		busiHostObj.setPagination(this.getPaginater().initPagination(request));
		resultList = busiHostService.queryAllMappingsByObj(busiHostObj);
		return "listbusihost";
	}

	public String listBusiArea() {
		return "show";
	}

	/**
	 * @Title: listProcess
	 * @Description: 展示进程
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午3:12:25
	 */
	public String listProcess() {
		if (processObj == null) {
			processObj = new ProcessObj();
		}
		// TbSysAppObj appObj = new TbSysAppObj();
		// appList = appService.queryForListByObj(appObj);
		processObj.setPARENT_ID("0");
		processObj.setPagination(this.getPaginater().initPagination(request));
		List<ProcessObj> temp = new ArrayList<ProcessObj>();
		if ("normal".equals(isnor)) {
			processObj.setPROCESS_STATE(2);
			temp = processService.queryProcessInfoList(processObj);
		} else if ("unnormal".equals(isnor)) {
			temp = processService.queryUnNormalProcess(processObj);
		} else {
			temp = processService.queryProcessInfoList(processObj);
		}
		resultList = new ArrayList<ProcessObj>();
		for (int i = 0; i < temp.size(); i++) {
			resultList.add(temp.get(i));
			ProcessObj obj1 = new ProcessObj();
			obj1.setPARENT_ID(temp.get(i).getID());
			resultList.addAll(processService.queryProcessInfoList(obj1));
		}
		return "listProcess";

	}

	/**
	 * @Title: listOrder
	 * @Description: 展示订单
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午3:52:49
	 */
	public String listOrder() {
		if (orderObj == null) {
			orderObj = new OrderObj();
		}
		orderObj.setPagination(this.getPaginater().initPagination(request));
		resultList = orderService.queryOrderInfoList(orderObj);
		return "listOrder";
	}

	/**
	 * @Title: listTask
	 * @Description: 展示任务
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-26 下午4:03:45
	 */
	public String listTask() {
		try {
			TaskObj obj = new TaskObj();
			if (status != null && !"".equals(status)) {
				obj.setSTATUS(Integer.parseInt(status));
			}
			obj.setPagination(this.getPaginater().initPagination(request));
			resultList = taskServiceDeploy.queryTaskRealtiontOrderList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listTask";
	}

}
