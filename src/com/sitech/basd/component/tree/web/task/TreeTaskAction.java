package com.sitech.basd.component.tree.web.task;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import rabbitmq.QueueDefind;

import com.sitech.basd.component.service.deploylog.TbBusiDeployLogService;
import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;
import com.sitech.basd.component.tree.domain.tactics.TacticsObj;
import com.sitech.basd.component.tree.domain.task.DeployExampleObj;
import com.sitech.basd.component.tree.domain.task.TaskLogObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.domain.task.TaskRelationObj;
import com.sitech.basd.component.tree.service.order.OrderService;
import com.sitech.basd.component.tree.service.tactics.TacticsService;
import com.sitech.basd.component.tree.service.task.TaskService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.date.TimeformatCommon;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.OrderNumber;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import deploy.OrderTaskObj;
import enumtype.Types;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("all")
@Controller("treeTaskAction")
public class TreeTaskAction extends BaseAction {
	private String GB2312_TEMPLATE_ENCODING = "GB2312";
	private String UTF_8_TEMPLATE_ENCODING = "UTF-8";

	private List<TaskObj> resultList;

	private TaskObj taskObj;

	private List<DeployExampleObj> deployExampleList;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	@Autowired
	private TbBusiDeployLogService tbBusiDeployLogService;

	private String appId;

	private String id;// 部署树表的id

	private String exampleid;

	private String orderid;

	private String executelevel;// 任务的执行级别
	
	private String userType;//标示回调函数传递的页面，
	
	private String num;//标示订单中的第几个任务关联的实例

	@Autowired
	private DeployExampleService deployExampleService;

	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TaskService taskServiceDeploy;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TacticsService tacticsService;

	private List<TaskLogObj> logList;

	private TaskLogObj taskLogObj = new TaskLogObj();

	private List<TaskLogObj> resultList1 = new ArrayList<TaskLogObj>();

	private List<TaskLogObj> resultList2 = new ArrayList<TaskLogObj>();

	private String TASK_ID;// 任务ID

	private String ORDER_ID;// 订单ID
	
	private String otherExampleid;//我会告诉你这是用来传递实例id的吗？

	public List<TaskLogObj> getResultList1() {
		return resultList1;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setResultList1(List<TaskLogObj> resultList1) {
		this.resultList1 = resultList1;
	}

	public List<TaskLogObj> getResultList2() {
		return resultList2;
	}

	public String getOtherExampleid() {
		return otherExampleid;
	}

	public void setOtherExampleid(String otherExampleid) {
		this.otherExampleid = otherExampleid;
	}

	public void setResultList2(List<TaskLogObj> resultList2) {
		this.resultList2 = resultList2;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setTASK_ID(String tASK_ID) {
		TASK_ID = tASK_ID;
	}

	public String getORDER_ID() {
		return ORDER_ID;
	}

	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}

	public TaskLogObj getTaskLogObj() {
		return taskLogObj;
	}

	public void setTaskLogObj(TaskLogObj taskLogObj) {
		this.taskLogObj = taskLogObj;
	}

	public void setTaskObj(TaskObj taskObj) {
		this.taskObj = taskObj;
	}

	public List<TaskLogObj> getLogList() {
		return logList;
	}

	public void setLogList(List<TaskLogObj> logList) {
		this.logList = logList;
	}

	public List<DeployExampleObj> getDeployExampleList() {
		return deployExampleList;
	}

	public void setDeployExampleList(List<DeployExampleObj> deployExampleList) {
		this.deployExampleList = deployExampleList;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getExecutelevel() {
		return executelevel;
	}

	public void setExecutelevel(String executelevel) {
		this.executelevel = executelevel;
	}

	public TbBusiSysTreeService getTbBusiSysTreeService() {
		return tbBusiSysTreeService;
	}

	public void setTbBusiSysTreeService(TbBusiSysTreeService tbBusiSysTreeService) {
		this.tbBusiSysTreeService = tbBusiSysTreeService;
	}

	public String getExampleid() {
		return exampleid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setExampleid(String exampleid) {
		this.exampleid = exampleid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public List<TaskObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<TaskObj> resultList) {
		this.resultList = resultList;
	}

	public TaskObj getTaskObj() {
		return taskObj;
	}

	public void settaskObj(TaskObj taskObj) {
		this.taskObj = taskObj;
	}

	public String listTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		appId = request.getParameter("entityId");
		// String size = request.getParameter("size");
		String userType = request.getParameter("userType");
		id = request.getParameter("id");
		TaskObj obj = new TaskObj();
		List<TaskObj> objs = new ArrayList<TaskObj>();
		// if (size != null && size.equals("alltask")) {
		// String status = request.getParameter("status");
		// if (status != null && !"".equals(status)) {
		// obj.setSTATUS(Integer.parseInt(status));
		// }
		// objs = taskServiceDeploy.queryTaskInfoList(obj);
		// request.setAttribute("size", size);
		// } else {
		obj.setAPP_ID(Integer.parseInt(appId));
		objs = taskServiceDeploy.queryTaskInfoList(obj);
		// }
		/**
		 * 遍历对任务的状态进行处理,处理所有状态status为处理中的任务，即1
		 */
		resultList = new ArrayList<TaskObj>();
		for (TaskObj temp : objs) {
			int status = temp.getSTATUS();
			String taskId = temp.getID();
			if (1 == status) {// 表示任务为处理中
				TaskObj temp1 = taskServiceDeploy.queryTaskStatus(temp);
				if (temp1 != null && temp1.getISCOMPLETE() != null) {
					if (0 == temp1.getISCOMPLETE()) {
						temp.setSTATUS(1);// 0 待处理 1处理中 2处理完成
						taskServiceDeploy.updateTaskByObj(temp);
					} else if (1 == temp1.getISCOMPLETE()) {
						temp.setSTATUS(2);// 0 待处理 1处理中 2处理完成
						taskServiceDeploy.updateTaskByObj(temp);
					}
				}
			}
			resultList.add(temp);
		}
		request.setAttribute("userType", userType);
		return "listtask";
	}

	public String listNotInOrderRelationTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		appId = request.getParameter("entityId");
		String userType = request.getParameter("userType");
		id = request.getParameter("id");
		TaskObj obj = new TaskObj();
		obj.setAPP_ID(Integer.parseInt(appId));
		resultList = taskServiceDeploy.queryForListBaseNotInOrderRelation(obj);
		request.setAttribute("userType", userType);
		return "listtask";
	}

	/**
	 * 获取订单包含的任务
	 * 
	 * @return
	 */
	public String getTaskRelationOrder() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			orderid = request.getParameter("orderid") != null ? request.getParameter("orderid") : orderid;
			id = request.getParameter("id") != null ? request.getParameter("id") : id;
			String userType = request.getParameter("userType");
			appId = request.getParameter("entityId") != null ? request.getParameter("entityId") : appId;
			TaskObj obj = new TaskObj();
			obj.setORDER_ID(orderid);
			if (appId != null && !"".equals(appId) && !"null".equals(appId)) {
				obj.setAPP_ID(Integer.parseInt(appId));
			}
			String backupType = null;
			if (ORDER_ID != null && !"".equals(ORDER_ID)) {
				TaskObj taskObj = new TaskObj();
				taskObj.setORDER_ID(ORDER_ID);
				taskObj.setAPP_ID(Integer.parseInt(appId));
				backupType = taskService.queryBackupType(taskObj);// 查询回滚类型
			}
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			resultList = taskServiceDeploy.queryTaskRealtiontOrderList(obj);
			request.setAttribute("userType", userType);
			request.setAttribute("backupType", backupType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listtask";
	}

	/**
	 * 添加新进程的操作方法
	 * 
	 * @return
	 */
	public String addNewTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		String taskId = request.getParameter("taskid");
		exampleid = "";
		executelevel = "";
		if ("add".equals(oper)) {
			taskObj = new TaskObj();
		} else {
			TaskObj obj = new TaskObj();
			obj.setID(taskId);
			taskObj = taskServiceDeploy.queryTaskInfoList(obj).get(0);
			TaskRelationObj obj1 = new TaskRelationObj();
			obj1.setTASK_ID(taskId);
			List<TaskRelationObj> obj1s = taskServiceDeploy.queryRelationTaskInfoList(obj1);
			for (int i = 0; i < obj1s.size(); i++) {
				exampleid += obj1s.get(i).getEXAMPLE_ID() + ",";
				executelevel += obj1s.get(i).getEXECUTE_LEVEL() + ",";
			}
		}
		request.setAttribute("oper", oper);
		return "addtask";
	}

	public String saveTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			int ent = -1;
			JSONObject json = new JSONObject();
			String oper = request.getParameter("oper");
			// PrintWriter out = response.getWriter();
			String[] examples = exampleid.split(",");
			String[] executelevels = executelevel.split(",");
			taskObj.setEXAMPLE_COUNT(examples.length);// 设置实例的个数
			if ("add".equals(oper)) {
				/**
				 * 如果订单添加的时间大于订单的有效期则，不允许添加任务
				 */
				OrderObj orderObj = new OrderObj();
				orderObj.setID(orderid);
				OrderObj orderObjTemp = orderService.queryOrderInfoList(orderObj).get(0);
				Date date = new Date();
				String effectTime = orderObjTemp.getEFFECT_TIME();
				Date date1 = TimeformatCommon.TimeStringToDate(effectTime, "yyyy-MM-dd HH:mm:ss");
				long tempLong = date1.getTime() - date.getTime();
				if (tempLong < 0) {
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				String taskId = RandomUUID.getUuid();
				taskObj.setID(taskId);
				taskObj.setAPP_ID(Integer.parseInt(appId));
				taskObj.setTASK_ID(OrderNumber.getOrderNumber("T"));
				taskObj.setSTATUS(0);// 新建任务设置状态为草稿
				OrderRelationTaskObj orderTaskObj = new OrderRelationTaskObj();
				orderTaskObj.setTASK_ID(taskId);
				orderTaskObj.setORDER_ID(orderid);
				orderService.insertRelationTaskByObj(orderTaskObj);
				ent = taskServiceDeploy.insertByObj(taskObj);
				TaskRelationObj taskRO = new TaskRelationObj();
				taskRO.setTASK_ID(taskId);
				taskRO.setDEPLOY_STATUS(0);
				for (int i = 0; i < examples.length; i++) {
					taskRO.setEXAMPLE_ID(examples[i]);
					taskRO.setEXECUTE_LEVEL(Integer.parseInt(executelevels[i]));
					taskServiceDeploy.insertRelationByObj(taskRO);
				}
			} else {
				ent = taskServiceDeploy.updateTaskByObj(taskObj);// 更新task表
				/**
				 * 更新task与实例的关系表
				 */
				TaskRelationObj taskRO = new TaskRelationObj();
				taskRO.setTASK_ID(taskObj.getID());
				List<TaskRelationObj> taskROs = taskServiceDeploy.queryRelationTaskInfoList(taskRO);// 查找这个task包含几个实例
				for (int i = 0; i < examples.length; i++) {
					String tempExample = examples[i];
					for (int j = 0; j < taskROs.size(); j++) {
						if (tempExample.equals(taskROs.get(j).getEXAMPLE_ID())) {// 如果包含这个关系，则更新此关系中的更新字段
							taskRO.setEXAMPLE_ID(tempExample);
							taskRO.setEXECUTE_LEVEL(Integer.parseInt(executelevels[i]));
							taskServiceDeploy.updateRelationByObj(taskRO);
							// 重新赋值两个列表，未被标记为temp的未需要插入的，内层对象的temp属性不为temp的为需要删除字段
							examples[i] = "temp";
							taskROs.get(j).setTemp("temp");
						}
					}
				}

				/**
				 * 插入新增的实例
				 */
				for (int i = 0; i < examples.length; i++) {
					String tempExample = examples[i];
					if (!"temp".equals(tempExample)) {
						taskRO.setEXAMPLE_ID(tempExample);
						taskRO.setEXECUTE_LEVEL(Integer.parseInt(executelevels[i]));
						taskServiceDeploy.insertRelationByObj(taskRO);
					}
				}

				/**
				 * 删除，删除的实例
				 */
				for (int j = 0; j < taskROs.size(); j++) {
					if (!"temp".equals(taskROs.get(j).getTemp())) {
						taskServiceDeploy.delRelationByObj(taskRO);
					}
				}
			}
			if (ent >= 0) {
				json.put("obj", taskObj);
			}
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检验当前加入的和已经存在的订单中的任务是否是同总类型的任务
	 * 可以优化为前台检测-----------------------------------------------待优化
	 * 
	 * @return
	 */
	public String changeTaskTypeCanJionOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			String taskType = request.getParameter("tasktype");
			OrderObj obj = new OrderObj();
			obj.setORDER_ID(orderid);
			List<OrderObj> objs = orderService.queryRelationForTaskList(obj);
			OrderObj orderObj = new OrderObj();
			int compare = -1;
			if (objs.size() > 0) {
				orderObj = objs.get(0);
				TaskObj taskObj1 = new TaskObj();
				taskObj1.setID(orderObj.getTASK_ID());
				TaskObj taskObj2 = taskService.queryTaskInfoList(taskObj1).get(0);
				compare = taskObj2.getTASK_TYPE();
			}
			if (compare != -1 && compare != Integer.parseInt(taskType)) {// 类型不同的不能加入同一个订单
				try {
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 2);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				JSONObject json = new JSONObject();
				// PrintWriter out = response.getWriter();
				json.put("result", 1);
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String delTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String taskid = request.getParameter("taskid");
		try {
			JSONObject json = new JSONObject();
			TaskObj obj = new TaskObj();
			obj.setID(taskid);
			obj = taskServiceDeploy.queryTaskInfoList(obj).get(0);
			if (obj.getSTATUS() == 1) {// 任务处理中不允许删除
				// PrintWriter out = response.getWriter();
				json.put("result", 2);
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			}
			int ent = taskServiceDeploy.delByObj(obj);// 删除所有的task
			if (ent >= 0) {
				TaskRelationObj objRO = new TaskRelationObj();// 删除所有与task关联的实例
				objRO.setTASK_ID(taskid);
				ent = taskServiceDeploy.delRelationByObj(objRO);
				OrderRelationTaskObj orderTaskObj = new OrderRelationTaskObj();
				orderTaskObj.setTASK_ID(taskid);
				ent = orderService.delRelationTaskByObj(orderTaskObj);
				TacticsObj tacticsObj = new TacticsObj();
				tacticsObj.setTASKID(taskid);
				tacticsService.deleteTacticsByTaskId(tacticsObj);
				if (ent >= 0) {
					json.put("result", 1);
				} else {
					json.put("result", -1);
				}
			} else {
				json.put("result", -1);
			}
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String executeTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String taskids = request.getParameter("taskids");
		String[] taskidsArray = taskids.split(",");
		try {
			List<OrderTaskObj> otos = new ArrayList<OrderTaskObj>();
			for (int i = 0; i < taskidsArray.length; i++) {
				String taskId = taskidsArray[i];
				TaskObj tt = new TaskObj();
				tt.setID(taskId);
				List<TaskObj> tts = taskService.queryTaskInfoList(tt);
				if (tts.size() > 0) {
					Integer status = tts.get(0).getSTATUS();
					if (0 != status) {
						/**
						 * 有任务的状态时非无任务，表示任务正在处理中，不能再次执行
						 */
						JSONObject json = new JSONObject();
						json.put("result", 2);
						// PrintWriter out = response.getWriter();
						// out.println(json);
						// out.close();
						PrintWriterOut.printWirter(response, json);
						return null;
					}
				}
				OrderTaskObj oto = new OrderTaskObj();
				oto.setTASK_ID(taskId);
				oto.setType(Types.entityType.Task);
				otos.add(oto);
				/**
				 * 发送成功后修改任务的状态为，待处理
				 */
				TaskObj taskObj = new TaskObj();
				taskObj.setSTATUS(1);
				taskObj.setID(taskId);
				taskService.updateTaskByObj(taskObj);
			}
			/**
			 * 未处理的任务重新部署
			 */
			rabbitmqUtil.publishMessage("", QueueDefind.DEPLOY_QUEUE, JacksonUtil.toJson(otos));
			JSONObject json = new JSONObject();
			json.put("result", 1);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// public String delTaskRelationOrder() {
	// HttpServletRequest request = Struts2Utils.getRequest();
	// HttpServletResponse response = Struts2Utils.getResponse();
	// String taskid = request.getParameter("taskid");
	// try {
	// JSONObject json = new JSONObject();
	// TaskObj obj = new TaskObj();
	// obj.setID(taskid);
	// int ent = taskServiceDeploy.deleteTaskRelationOrderByObj(obj);//删除所有的task
	// if (ent >= 0) {
	// json.put("result", 1);
	// } else {
	// json.put("result", -1);
	// }
	// PrintWriter out = response.getWriter();
	// out.println(json);
	// out.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	public String queryFileList() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String Taskid = request.getParameter("Taskid");
		TaskObj obj = new TaskObj();
		obj.setID(Taskid);
		taskObj = taskServiceDeploy.queryTaskInfoList(obj).get(0);
		return "filelist";
	}

	/**
	 * 列出任务下所有的部署实例的
	 * 
	 * @Title: listDeployExample
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:52:26
	 */
	public String listDeployExample() {
		HttpServletRequest request = Struts2Utils.getRequest();
		exampleid = request.getParameter("exampleid");
		executelevel = request.getParameter("executelevel");
		DeployExampleObj obj = new DeployExampleObj();
		obj.setPARENT_ID(id);
		deployExampleList = new ArrayList<DeployExampleObj>();
		List<DeployExampleObj> temp1 = taskService.queryExampleListNotInTask(obj);
		List<DeployExampleObj> temp2 = taskService.queryExampleListInTaskAndComplete(obj);
		deployExampleList.addAll(temp1);
		deployExampleList.addAll(temp2);
		request.setAttribute("exampleid", exampleid);
		request.setAttribute("executelevel", executelevel);
		return "listdeployexample";
	}

	/**
	 * 
	 * @Title: selectTask
	 * @Description: 进入选择任务页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 7, 2013 2:58:39 PM
	 */
	public String selectTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String task_ids = request.getParameter("task_ids");
		request.setAttribute("task_ids", task_ids);
		TaskObj obj = new TaskObj();
		resultList = taskServiceDeploy.queryTaskInfoList(obj);
		return "select";
	}

	/**
	 * 
	 * @Title: queryLog
	 * @Description: 显示任务的log列表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jun 17, 2013 2:58:39 PM
	 */
	public String taskLogList() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String order_id = request.getParameter("orderid");
		String task_id = request.getParameter("task_id");
		TaskLogObj obj = new TaskLogObj();
		obj.setTask_id(task_id);
		obj.setOrder_id(order_id);
		logList = taskService.queryLog(obj);
		return "log";
	}

	/**
	 * 
	 * @Title: taskReportList
	 * @Description: 显示任务的log列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-22 下午8:30:30
	 */
	public String taskReportList() {
		taskLogObj.setTask_id(TASK_ID);
		taskLogObj.setOrder_id(ORDER_ID);
		taskLogObj.setPagination(this.getPaginater().initPagination(request));
		logList = taskService.queryTaskReportList(taskLogObj);
		return "taskReport";
	}

	/**
	 * 
	 * @Title: exportExcel
	 * @Description: 导出Excel
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-25 上午9:35:17
	 */
	public void exportExcel() {
		taskLogObj.setTask_id(TASK_ID);
		taskLogObj.setOrder_id(ORDER_ID);
		boolean flag = taskService.exportExcel(response, taskLogObj);
	}

	/**
	 * 
	 * @Title: showHtml
	 * @Description: 生成html模板
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-25 上午9:36:40
	 */
	public String showHtml() throws IOException, TemplateException {
		HttpServletRequest reqeust = Struts2Utils.getRequest();
		String url = reqeust.getRealPath("/template");
		TaskObj result = new TaskObj();
		TaskObj params = new TaskObj();
		params.setTASK_ID(TASK_ID);
		params.setORDER_ID(ORDER_ID);
		taskLogObj.setTask_id(TASK_ID);
		taskLogObj.setOrder_id(ORDER_ID);
		result = taskService.queryTaskRealtiontOrderList(params).get(0);
		resultList1 = taskService.querySuccessTaskLog(taskLogObj);// 成功列表
		resultList2 = taskService.queryFailTaskLog(taskLogObj);// 失败列表
		Configuration cfg = new Configuration();// 指定一个加载模版的数据源
		cfg.setDirectoryForTemplateLoading(new File(url));// 这里我设置模版的根目录
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding(UTF_8_TEMPLATE_ENCODING);
		Template temp = cfg.getTemplate("taskReport.ftl", UTF_8_TEMPLATE_ENCODING);
		temp.setEncoding(UTF_8_TEMPLATE_ENCODING);
		// 创建一个hash类型作为数据模型的root
		Map root = new HashMap();// 把字符串放置到root中
		root.put("task_id", result.getTASK_ID());
		root.put("successCount", result.getSUCCESS_COUNT());
		root.put("failCount", result.getFAILURE_COUNT());
		root.put("resultList1", resultList1);
		root.put("resultList2", resultList2);
		// 定义解释完模板后的输出
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(url + "/" + result.getTASK_ID()
				+ ".html"), UTF_8_TEMPLATE_ENCODING));
		temp.process(root, out);
		out.flush();
		// 关闭输出流，可删除文件
		out.close();
		return null;
	}

	/**
	 * 
	 * @Title: executeCommandUDownload
	 * @Description: 下载以生成的html
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-25 上午9:39:38
	 */
	public String executeCommandUDownload() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		HttpServletRequest reqeust = Struts2Utils.getRequest();
		response.setCharacterEncoding(UTF_8_TEMPLATE_ENCODING);
		taskLogObj.setTask_id(TASK_ID);
		taskLogObj.setOrder_id(ORDER_ID);
		TaskLogObj obj1 = taskService.querySuccessCount(taskLogObj);// 成功个数
		String filePath = reqeust.getRealPath("/template") + "/" + obj1.getTask_id() + ".html";
		File file = new File(filePath);
		BufferedInputStream buffer = null;
		OutputStream out = null;
		try {
			// 检查该文件是否存在
			if (!file.exists()) {
				response.sendError(404, "File not found!");
				return "File not found!";
			}
			buffer = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int length = 0;
			response.reset(); // 非常重要
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(file.getName(), UTF_8_TEMPLATE_ENCODING));
			out = response.getOutputStream();
			while ((length = buffer.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
				out.close();
				// 删除生成文件
				System.out.println(file.delete() + "!!!!!!!!!!!!");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
