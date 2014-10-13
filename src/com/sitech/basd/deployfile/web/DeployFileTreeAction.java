package com.sitech.basd.deployfile.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import rabbitmq.QueueDefind;

import com.sitech.basd.deployfile.domain.DeployFileTreeObj;
import com.sitech.basd.deployfile.service.DeployFileTreeService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.servlet.PrintWriterOut;

import deploy.RollbackObj;
import enumtype.Types;

@Controller("deployFileTreeAction")
@Scope("prototype")
public class DeployFileTreeAction extends BaseAction {
	private final static Logger logger = LoggerFactory.getLogger(DeployFileTreeAction.class
			.getName());
	@Autowired
	private DeployFileTreeService deployFileTreeService;
	@Autowired
	private RabbitMQUtil rabbitMQUtil;
	private String taskId;// 任务ID
	private String orderId;// 订单Id
	private String file_url;// 所有文件清单
	private String rollBackType;// 回滚类型(0 增量; 1 全量)
	private DeployFileTreeObj obj;
	private List<DeployFileTreeObj> resultList;

	public String getRollBackType() {
		return rollBackType;
	}

	public void setRollBackType(String rollBackType) {
		this.rollBackType = rollBackType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public DeployFileTreeObj getObj() {
		return obj;
	}

	public void setObj(DeployFileTreeObj obj) {
		this.obj = obj;
	}

	public List<DeployFileTreeObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<DeployFileTreeObj> resultList) {
		this.resultList = resultList;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	/**
	 * 
	 * @Title: listDeployFileTree
	 * @Description: 初始化页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午1:35:45
	 */
	public String listDeployFileTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 异步加载树
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午1:29:36
	 */
	public String asyncForTree() {
		List<DeployFileTreeObj> list = queryTreeNode(request);
		List<DeployFileTreeObj> resultList = deployFileTreeService.initTreelist(list);
		JSONArray json = JSONArray.fromObject(resultList);
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = null;
		// out = response.getWriter();
		// out.print(json.toString());
		// out.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<DeployFileTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-16 下午1:58:26
	 */
	public List<DeployFileTreeObj> queryTreeNode(HttpServletRequest request) {
		List<DeployFileTreeObj> list = new ArrayList<DeployFileTreeObj>();
		DeployFileTreeObj obj = new DeployFileTreeObj();
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			obj.setType("0");
		} else {
			obj.setParent_id(id);
		}
		obj.setOrder_id(orderId);
		list = deployFileTreeService.queryForTree(obj);
		return list;
	}

	/**
	 * 
	 * @Title: saveSelectedNodes
	 * @Description: 选择节点
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-12-17 下午1:43:01
	 */
	public void sendSelectedNodes() {
		RollbackObj rollbackObj = new RollbackObj();
		rollbackObj.setTaskId(taskId);
		rollbackObj.setOrderId(orderId);
		if ("1".equals(rollBackType)) {
			rollbackObj.setRollbackType(Types.DeployRollbackType.ALL);
		} else {
			rollbackObj.setUrl(file_url);
			rollbackObj.setRollbackType(Types.DeployRollbackType.INCREMENTAL);
		}
		sendDataRabbit(rollbackObj);
	}

	private void sendDataRabbit(RollbackObj obj) {
		try {
			rabbitMQUtil.publishMessage("", QueueDefind.FILE_ROlLBACK_QUEUE, obj);
		} catch (RabbitMQException e) {
			logger.error("send rabbitmq queue : deploy.file.rollback.queue error.");
		}
	}
}
