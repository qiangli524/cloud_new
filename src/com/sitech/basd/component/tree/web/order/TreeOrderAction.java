package com.sitech.basd.component.tree.web.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import rabbitmq.QueueDefind;

import com.sitech.basd.component.tree.domain.order.OrderObj;
import com.sitech.basd.component.tree.domain.order.OrderRelationTaskObj;
import com.sitech.basd.component.tree.domain.tactics.TacticsObj;
import com.sitech.basd.component.tree.domain.task.TaskObj;
import com.sitech.basd.component.tree.domain.task.TaskRelationObj;
import com.sitech.basd.component.tree.service.order.OrderService;
import com.sitech.basd.component.tree.service.tactics.TacticsService;
import com.sitech.basd.component.tree.service.task.TaskService;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.deployfileversion.DeployFileVersionService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.OrderNumber;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import deploy.OrderTaskObj;
import enumtype.Types;

/**
 * 智能部署订单管理类
 * 没有加@Scope("prototype") 这是一个bug，以后再修复，如果加上这个参数会导致很多东西无法使用，
 * @author GongHaijian
 *
 */
@SuppressWarnings("serial")
@Controller("treeOrderAction")
public class TreeOrderAction extends BaseAction {

	private List<OrderObj> resultList;

	private OrderObj orderObj;
	
	private String taskTactiStrs;//任务和策略的合集

	@Autowired
	private TaskService taskServiceDeploy;
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private TacticsService tacticsService;

	@Autowired
	private DeployFileVersionService deployFileVersionService;

	private Integer entityId;// 节点对应的实体id//树节点对应的实体

	private String id;// 树的节点id

	private String backupType;// 订单关联部署实例备份策略

	public Integer getEntityId() {
		return entityId;
	}

	public String getTaskTactiStrs() {
		return taskTactiStrs;
	}


	public TacticsService getTacticsService() {
		return tacticsService;
	}

	public void setTacticsService(TacticsService tacticsService) {
		this.tacticsService = tacticsService;
	}

	public void setTaskTactiStrs(String taskTactiStrs) {
		this.taskTactiStrs = taskTactiStrs;
	}


	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBackupType() {
		return backupType;
	}

	public void setBackupType(String backupType) {
		this.backupType = backupType;
	}

	public List<OrderObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<OrderObj> resultList) {
		this.resultList = resultList;
	}

	public OrderObj getOrderObj() {
		return orderObj;
	}

	public void setOrderObj(OrderObj orderObj) {
		this.orderObj = orderObj;
	}

	public String listOrder() {
		try {
			String entityIdT = request.getParameter("entityId");
			if (entityIdT != null) {
				entityId = Integer.parseInt(entityIdT);
			}
			id = request.getParameter("id");
			OrderObj obj = new OrderObj();
			obj.setAPP_ID(entityId);
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			resultList = orderService.queryOrderInfoList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listorder";
	}


	/**
	 * 添加订单
	 * @Title: addNewOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-26 下午5:00:56
	 */
	public String addNewOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		String orderid = request.getParameter("orderid");
		// String appid = request.getParameter("appid");
		if ("add".equals(oper)) {
			orderObj = new OrderObj();
		} else {
			OrderObj obj = new OrderObj();
			obj.setID(orderid);
			orderObj = orderService.queryOrderInfoList(obj).get(0);
			if (orderObj != null) {
				String versionid = orderObj.getVERSIONID();
				if (versionid != null && !"".equals(versionid)) {
					DeployFileVersionObj deployFileVersionObj = new DeployFileVersionObj();
					deployFileVersionObj.setID(versionid);
					deployFileVersionObj = deployFileVersionService
							.queryFileVersionOne(deployFileVersionObj);
					if (deployFileVersionObj != null) {
						orderObj.setVersionPath(deployFileVersionObj.getLOCATION());
					} else {
						orderObj.setVersionPath("版本不存在");
					}
				} else {
					orderObj.setVersionPath("未关联版本");
				}
			}
		}
		request.setAttribute("oper", oper);
		request.setAttribute("appid", entityId);
		return "addorder";
	}

	
	
	/**
	 * 订单的快速添加，包含任务和实例主机的添加
	 * @Title: quickAddNewOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2014-8-26 下午5:01:54
	 */
	public String quickAddNewOrder() {
		
		/**
		 * 待开发
		 */
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		String orderid = request.getParameter("orderid");
		// String appid = request.getParameter("appid");
		if ("add".equals(oper)) {
			orderObj = new OrderObj();
		} else {
			OrderObj obj = new OrderObj();
			obj.setID(orderid);
			orderObj = orderService.queryOrderInfoList(obj).get(0);
			if (orderObj != null) {
				String versionid = orderObj.getVERSIONID();
				if (versionid != null && !"".equals(versionid)) {
					DeployFileVersionObj deployFileVersionObj = new DeployFileVersionObj();
					deployFileVersionObj.setID(versionid);
					deployFileVersionObj = deployFileVersionService
							.queryFileVersionOne(deployFileVersionObj);
					if (deployFileVersionObj != null) {
						orderObj.setVersionPath(deployFileVersionObj.getLOCATION());
					} else {
						orderObj.setVersionPath("版本不存在");
					}
				} else {
					orderObj.setVersionPath("未关联版本");
				}
			}
		}
		request.setAttribute("oper", oper);
		request.setAttribute("appid", entityId);
		return "quickaddorder";
	}
	
	
	/**
	 * 添加订单和任务的关系
	 * 
	 * @return
	 */
	public String addOrderRelationTask() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String taskids = request.getParameter("taskids");
		String orderid = request.getParameter("orderid");
		String[] ids = taskids.split(",");
		for (String taskid : ids) {
			OrderRelationTaskObj orto = new OrderRelationTaskObj();
			orto.setORDER_ID(orderid);
			orto.setTASK_ID(taskid);
			orderService.insertRelationTaskByObj(orto);
		}

		OrderObj obj1 = new OrderObj();
		obj1.setID(orderid);
		List<OrderObj> orders = orderService.queryRelationForTaskList(obj1);
		try {
			// PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("result", 1);
			json.put("taskcount", orders.size());
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getTaskCountByOrderId() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String orderid = request.getParameter("orderid");
		OrderRelationTaskObj obj = new OrderRelationTaskObj();
		obj.setORDER_ID(orderid);
		List<OrderRelationTaskObj> orders = orderService.queryOrderRelationTaskList(obj);
		try {
			// PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("result", 1);
			json.put("taskcount", orders.size());
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		JSONObject json = new JSONObject();
		String oper = request.getParameter("oper");
		int ent = -1;
		if ("add".equals(oper)) {
			orderObj.setID(RandomUUID.getUuid());
			orderObj.setORDER_ID(OrderNumber.getOrderNumber("D"));
			orderObj.setIS_SUBMIT(0);
			orderObj.setAPP_ID(entityId);
			ent = orderService.insertByObj(orderObj);
		} else {
			ent = orderService.updateOrderByObj(orderObj);
		}
		try {
			// PrintWriter out = response.getWriter();
			if (ent >= 0) {
				json.put("obj", orderObj);
			}
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 快速创建工单和任务的处理action
	 * @return
	 */
	public String quickSaveOrder() {
		HttpServletResponse response = Struts2Utils.getResponse();
		JSONObject json = new JSONObject();
		int ent = -1;
		String orderId=RandomUUID.getUuid();
		orderObj.setID(orderId);
		orderObj.setORDER_ID(OrderNumber.getOrderNumber("D"));
		/**
		 * 许可证目前是随机产生的，前台不提供输入
		 */
		
		/**
		 * 文件清单使用软件包的文件清单
		 */
		String fileList="";
		List<OrderObj> orderObj1=orderService.queryFileListFromSoftware(orderObj);
		
		if(orderObj1!=null&&orderObj1.size()==1){
			fileList=orderObj1.get(0).getFILE_LIST();
		}
		
		orderObj.setAUTHORIZATION(RandomUUID.getUuid());//
		orderObj.setIS_SUBMIT(0);
		orderObj.setAPP_ID(entityId);
		orderObj.setFILE_LIST(fileList);
		ent = orderService.insertByObj(orderObj);
		
		/**
		 * 如果工单入库成功，现在入库任务 taskTactiStrs
		 */
		String[] taskTactis=taskTactiStrs.split("_");//解析出每条任务
		for(int i=0;i<taskTactis.length;i++){
			String[] str=taskTactis[i].split("~");
			String[] examples=str[0].split(",");//实例id
			String[] executelevels=str[1].split(",");//实例级别
//			String taskType=str[2];//任务类型
			
			/**
			 * 任务入库
			 */
			TaskObj taskObj=new TaskObj();
			String taskId = RandomUUID.getUuid();
			taskObj.setID(taskId);
//			taskObj.setTASK_TYPE(Integer.parseInt(taskType));//任务类型   //暂时使用默认值，如果需要用户选择，请 把前台jsp的注释打开
			taskObj.setTASK_TYPE(0);//任务类型
			taskObj.setAPP_ID(entityId);
			taskObj.setTASK_ID(OrderNumber.getOrderNumber("T"));
			taskObj.setSTATUS(0);// 新建任务设置状态为草稿
			OrderRelationTaskObj orderTaskObj = new OrderRelationTaskObj();
			orderTaskObj.setTASK_ID(taskId);
			orderTaskObj.setORDER_ID(orderId);
			orderService.insertRelationTaskByObj(orderTaskObj);
			ent = taskServiceDeploy.insertByObj(taskObj);
			TaskRelationObj taskRO = new TaskRelationObj();
			taskRO.setTASK_ID(taskId);
			taskRO.setDEPLOY_STATUS(0);
			for (int j = 0; j < examples.length; j++) {
				taskRO.setEXAMPLE_ID(examples[j]);
				taskRO.setEXECUTE_LEVEL(Integer.parseInt(executelevels[j]));
				taskServiceDeploy.insertRelationByObj(taskRO);
			}
			
			/**
			 * 策略入库
			 */
//			if(str.length==5){
//				String tackits=str[3];//任务策略，任务策略可以不添加，所以需要判断是否为null
//				String taskTactisExecutetime=str[4];
//				if(tackits!=null&&!tackits.equals("")){
//					String[] tackitObjs=tackits.split("＆");
//					TacticsObj tacticsObj=new TacticsObj();
//					tacticsObj.setEXECUTETIME(taskTactisExecutetime);
//					for(int ii=0;ii<tackitObjs.length;ii++){
//						String temp=tackitObjs[ii];
//						String[] temps=temp.split("=");
//						if(temp.contains("tacticsObj.isAddTactics")){
//							tacticsObj.setIsAddTactics(temps[1]);
//						}else if(temp.contains("tacticsObj.EXECUTEMETHOD")){
//							tacticsObj.setEXECUTEMETHOD(Short.parseShort(temps[1]));
//						}else if(temp.contains("tacticsObj.TIMEMODE")){
//							tacticsObj.setTIMEMODE(Short.parseShort(temps[1]));
//						}else if(temp.contains("tacticsObj.ISCOPY")){
//							tacticsObj.setISCOPY(Short.parseShort(temps[1]));
//						}else if(temp.contains("tacticsObj.ISNEEDCHECK")){
//							tacticsObj.setISNEEDCHECK(Short.parseShort(temps[1]));
//						}else if(temp.contains("tacticsObj.ISRESTART")){
//							tacticsObj.setISRESTART(Short.parseShort(temps[1]));
//						}
//					}
//					tacticsObj.setTASKID(taskId);
//					tacticsObj.setID(RandomUUID.getUuid());
//					tacticsService.insertTacticsObj(tacticsObj);
//				}
//			}
		}
		/**
		 * 返回值已经返回错误类型等
		 */
		try {
			// PrintWriter out = response.getWriter();
			if (ent >= 0) {
				json.put("obj", orderObj);
			}
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String delOrder() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String orderid = request.getParameter("orderid");
		try {
			OrderRelationTaskObj objR = new OrderRelationTaskObj();
			/**
			 * 判断订单下面是否有任务，如果有任务不允许删除
			 */
			objR.setORDER_ID(orderid);
			List<OrderRelationTaskObj> objRs = orderService.queryOrderRelationTaskList(objR);
			if (objRs != null && objRs.size() > 0) {// 订单下面含有任务，不能删除
				JSONObject json = new JSONObject();
				json.put("result", 2);
				// PrintWriter out = response.getWriter();
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
			} else {
				OrderObj obj = new OrderObj();
				obj.setID(orderid);
				orderService.delByObj(obj);
				// OrderRelationTaskObj obj1=new OrderRelationTaskObj();
				// obj1.setORDER_ID(orderid);
				// orderService.delRelationTaskByObj(obj1);
				JSONObject json = new JSONObject();
				json.put("result", 1);
				// PrintWriter out = response.getWriter();
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String queryFileList() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String orderid = request.getParameter("orderid");
		OrderObj obj = new OrderObj();
		obj.setID(orderid);
		orderObj = orderService.queryOrderInfoList(obj).get(0);
		return "filelist";
	}

	/**
	 * 订单部署，提交，发送订单id到消息队列中
	 * 
	 * @return
	 */
	public String submitOrderToDeploy() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// TbSysUserinfoObj userInfo = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		String loginUserName = session.get("account").toString();// 获取当前登陆用户
		try {
			String orderids = request.getParameter("orderids");
			if (orderids != null && !orderids.equals("")) {
				String[] arrayOrderIds = orderids.split(",");
				List<OrderTaskObj> otos = new ArrayList<OrderTaskObj>();
				for (int i = 0; i < arrayOrderIds.length; i++) {
					String orderId = arrayOrderIds[i];
					OrderObj obj = new OrderObj();
					obj.setID(orderId);
					obj = orderService.queryOrderInfoList(obj).get(0);
					// int taskCount = obj.getTASK_COUNT();
					// int taskCompleteCount = obj.getTASK_COMPLETE_COUNT();
					// int taskDealCount = obj.getTASK_DEAL_COUNT();
					int isSubmit = obj.getIS_SUBMIT();
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					if (1 == isSubmit) {// 表示订单在处理中
						json.put("result", 2);// 订单正在处理或者已经处理完成无法提交
						// out.println(json);
						// out.close();
						PrintWriterOut.printWirter(response, json);
						return null;
					}
					// } else if (taskCount == taskCompleteCount ||
					// taskDealCount != 0) {
					// json.put("result", 2);// 订单正在处理或者已经处理完成无法提交
					// out.println(json);
					// out.close();
					// return null;
					// }
					/**
					 * 发送订单id到消息队列
					 */
					OrderTaskObj oto = new OrderTaskObj();
					if ("0".equals(obj.getBackupType())) {// 增量备份
						oto.setBackupType(Types.DeployBackupType.INCREMENTAL);
					} else if ("1".equals(obj.getBackupType())) {// 全部备份
						oto.setBackupType(Types.DeployBackupType.ALL);
					} else if ("2".equals(obj.getBackupType())) {// 无备份
						oto.setBackupType(Types.DeployBackupType.NON);
					} else {// 默认增量
						oto.setBackupType(Types.DeployBackupType.INCREMENTAL);
					}
					oto.setORDER_ID(orderId);
					oto.setFILE_LIST(obj.getFILE_LIST());
					oto.setType(Types.entityType.Order);
					oto.setLoginUserName(loginUserName);
					oto.setDeployVersionId(obj.getVERSIONID());
					otos.add(oto);
				}
				rabbitmqUtil.publishMessage("", QueueDefind.DEPLOY_QUEUE, otos);
				/**
				 * 消息发送成功后修改订单和任务的状态
				 */
				for (int i = 0; i < arrayOrderIds.length; i++) {
					// 修改订单状态为上线中
					String orderId = arrayOrderIds[i];
					OrderObj objT = new OrderObj();
					objT.setIS_SUBMIT(1);
					objT.setID(orderId);
					orderService.updateOrderByObj(objT);
					// 修改任务状态为处理中
					TaskObj taskObj = new TaskObj();
					taskObj.setORDER_ID(orderId);
					taskObj.setAPP_ID(entityId);
					List<TaskObj> taskObjs = taskServiceDeploy.queryTaskRealtiontOrderList(taskObj);
					for (int j = 0; j < taskObjs.size(); j++) {
						taskObj = taskObjs.get(j);
						Integer status = taskObj.getSTATUS();
						if (status == 0) {
							taskObj.setSTATUS(1);
							taskServiceDeploy.updateTaskByObj(taskObj);
						}
					}
				}
			}
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

	/**
	 * 强制重置订单状态
	 * 
	 * @return
	 */
	public String qzCancelOrderStatus() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			String orderids = request.getParameter("orderids");
			if (orderids != null && !orderids.equals("")) {
				String[] arrayOrderIds = orderids.split(",");
				JSONObject json = new JSONObject();
				// PrintWriter out = response.getWriter();
				for (int i = 0; i < arrayOrderIds.length;) {
					String orderId = arrayOrderIds[i];
					OrderObj obj = new OrderObj();
					obj.setID(orderId);
					obj.setIS_SUBMIT(0);
					int ent = orderService.updateOrderByObj(obj);
					if (ent >= 0) {
						json.put("result", 1);
						// out.println(json);
						// out.close();
						PrintWriterOut.printWirter(response, json);
						return null;
					} else {
						json.put("result", 2);
						// out.println(json);
						// out.close();
						PrintWriterOut.printWirter(response, json);
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
