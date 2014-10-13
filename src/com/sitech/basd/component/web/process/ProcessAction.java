package com.sitech.basd.component.web.process;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.exception.CloudRuntimeException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import enumtype.Types;

@Controller("processAction")
public class ProcessAction extends BaseAction {
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List appList = new ArrayList();

	private List<UserObj> hostList = new ArrayList<UserObj>();

	private List<ProcessObj> resultList;

	private List<ProcessObj> resultParentList;

	private ProcessObj processObj;

	private String parentProcessIds;

	private String parentProcessUserIds;

	private Integer APPID;

	private String HOST_IP;

	private String PROCESS;

	private String PROCESS_KEY;

	private Integer operation;

	private String oper;

	private String userType;// 标识当前页面在哪个里面

	public String getHOST_IP() {
		return HOST_IP;
	}

	public void setHOST_IP(String hOST_IP) {
		HOST_IP = hOST_IP;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPROCESS_KEY() {
		return PROCESS_KEY;
	}

	public void setPROCESS_KEY(String pROCESS_KEY) {
		PROCESS_KEY = pROCESS_KEY;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getParentProcessIds() {
		return parentProcessIds;
	}

	public void setParentProcessIds(String parentProcessIds) {
		this.parentProcessIds = parentProcessIds;
	}

	public String getParentProcessUserIds() {
		return parentProcessUserIds;
	}

	public void setParentProcessUserIds(String parentProcessUserIds) {
		this.parentProcessUserIds = parentProcessUserIds;
	}

	@Autowired
	private AppService appService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProcessService processService;

	public ProcessObj getProcessObj() {
		return processObj;
	}

	public void setProcessObj(ProcessObj processObj) {
		this.processObj = processObj;
	}

	public List<ProcessObj> getResultParentList() {
		return resultParentList;
	}

	public void setResultParentList(List<ProcessObj> resultParentList) {
		this.resultParentList = resultParentList;
	}

	public List getAppList() {
		return appList;
	}

	public void setAppList(List appList) {
		this.appList = appList;
	}

	public List<UserObj> getHostList() {
		return hostList;
	}

	public void setHostList(List<UserObj> hostList) {
		this.hostList = hostList;
	}

	public List<ProcessObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<ProcessObj> resultList) {
		this.resultList = resultList;
	}

	public Integer getAPPID() {
		return APPID;
	}

	public void setAPPID(Integer aPPID) {
		APPID = aPPID;
	}

	public String getPROCESS() {
		return PROCESS;
	}

	public void setPROCESS(String pROCESS) {
		PROCESS = pROCESS;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	/**
	 * 列出多进程的信息列表
	 * 
	 * @return
	 */
	public String listAllProcess() {
		ProcessObj obj = new ProcessObj();
		/**
		 * 所有应用列表
		 */
		TbSysAppObj appObj = new TbSysAppObj();
		appList = appService.queryForListByObj(appObj);

		if (HOST_IP != null) {
			obj.setIP(HOST_IP);
		}
		if (APPID != null && APPID != 0) {
			obj.setAPPID(APPID);
		}
		if (PROCESS != null && !PROCESS.trim().equals("")) {
			obj.setPROCESS(PROCESS);
		}
		if (PROCESS_KEY != null && !PROCESS_KEY.trim().equals("")) {
			obj.setPROCESS_KEY(PROCESS_KEY);
		}
		if (operation != null) {
			if (0 == operation) {// 运行中
				obj.setOPERATION(2);
				obj.setTAST_TYPE(2);
				obj.setISRUNNING(1);
			} else if (1 == operation) {// 已停止
				obj.setOPERATION(2);
				obj.setTAST_TYPE(2);
				obj.setISRUNNING(0);
			} else if (2 == operation) {// 正在启动
				obj.setOPERATION(1);
				obj.setTAST_TYPE(0);
				obj.setISRUNNING(0);
			} else if (3 == operation) {// 正在停止
				obj.setOPERATION(0);
				obj.setTAST_TYPE(0);
				obj.setISRUNNING(1);
			} else if (4 == operation) {// 检测中
				obj.setOPERATION(2);
				obj.setISRUNNING(3);
				obj.setTAST_TYPE(2);
			} else if (5 == operation) {// 部分启动
				obj.setOPERATION(2);
				obj.setISRUNNING(2);
				obj.setTAST_TYPE(2);
			} else {
				logger.debug("hehe");
			}
		}
		obj.setPARENT_ID("0");
		/**
		 * 暂时只查询通用的，过滤其他所有---------------------------------带开发
		 */
		obj.setTYPE(0);
		if ("processParent".equals(userType)) {
			resultList = processService.queryProcessInfoList(obj);
			String parentProcessIds = request.getParameter("parentProcessIds");
			request.setAttribute("parentProcessIds", parentProcessIds);
		} else {
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			List<ProcessObj> temp = processService.queryProcessInfoList(obj);
			resultList = new ArrayList<ProcessObj>();
			for (int i = 0; i < temp.size(); i++) {
				resultList.add(temp.get(i));
				ProcessObj obj1 = new ProcessObj();
				obj1.setPARENT_ID(temp.get(i).getID());
				resultList.addAll(processService.queryProcessInfoList(obj1));
			}
		}
		return "listprocess";
	}

	/**
	 * 添加新进程的操作方法
	 * 
	 * @return
	 */
	public String addNewProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		oper = request.getParameter("oper");
		ProcessObj obj = new ProcessObj();
		if ("add".equals(oper)) {
			processObj = obj;
		} else if ("edit".equals(oper)) {
			try {
				String processId = request.getParameter("processid");
				obj.setID(processId);
				List<ProcessObj> objs = processService.queryProcessInfoList(obj);
				if (objs != null && objs.size() == 1) {
					processObj = objs.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "addprocess";
	}

	public String saveProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String userids = request.getParameter("userids");
		String oper = request.getParameter("oper");
		try {
			if ("edit".equals(oper)) {
				processService.updateProcessByObj(processObj);
			} else {
				if (userids != null && userids.length() > 1) {
					String[] ipIds1 = userids.split(",");
					for (int i = 0; i < ipIds1.length; i++) {
						processObj.setID(RandomUUID.getUuid());
						processObj.setUSER_ID(ipIds1[i]);
						processObj.setPARENT_ID("0");
						processObj.setISRUNNING(3);
						processObj.setTAST_TYPE(2);
						processObj.setOPERATION(2);
						processObj.setPROCESS_STATE(0);
						processObj.setPROCESS_COUNT_ACTUAL(-1);
						processService.insertByObj(processObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 操作线程
	 * 
	 * @Title: operProcess
	 * @param
	 * @return String
	 * @throws
	 * @author shijc
	 * @version 1.0
	 * @createtime 2013-9-4 上午10:21:05
	 */
	public synchronized String operProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String processIds = request.getParameter("processIds");
		String oper = request.getParameter("oper");
		List<process.ProcessObjAgent> processObjs = new ArrayList<process.ProcessObjAgent>();
		try {
			String[] processIdArrays = processIds.split(",");
			for (int i = 0; i < processIdArrays.length; i++) {
				String processId = processIdArrays[i];
				/**
				 * 检测进程当前的状态
				 */
				ProcessObj obj = new ProcessObj();
				obj.setID(processId);
				obj = processService.queryProcessInfoList(obj).get(0);
				int isRunning = obj.getISRUNNING();
				int operation = obj.getOPERATION();
				int taskType = obj.getTAST_TYPE();
				if ("start".equals(oper) && (isRunning == 1 && operation == 2 && taskType == 2)) {// 运行中不继续启动
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("start".equals(oper) && operation == 1 && (taskType == 0 || taskType == 1)) {// 启动中不能继续启动
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 3);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("stop".equals(oper) && (isRunning == 0 && operation == 2 && taskType == 2)) {// 已经停止不用继续停止
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 2);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("stop".equals(oper) && (operation == 0 && (taskType == 0 || taskType == 1))) {// 停止中不能继续停止
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 4);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				process.ProcessObjAgent processObj = new process.ProcessObjAgent();
				processObj.setID(processId);
				if ("start".equals(oper)) {
					processObj.setStatus(Types.processOperStatus.START);
				} else if ("stop".equals(oper)) {
					processObj.setStatus(Types.processOperStatus.STOP);
				}
				processObjs.add(processObj);
			}
			if ("start".equals(oper)) {
				rabbitmqUtil.publishMessage("process.oper.queue.exchange", "", processObjs);
			} else if ("stop".equals(oper)) {
				rabbitmqUtil.publishMessage("process.oper.queue.exchange", "", processObjs);
			} else {
				logger.debug("oper传递参数错误，无此参数,内置参数为[start,stop]");
			}

			/**
			 * 启动或停止后，修改进程状态为 处理中
			 * 
			 */
			for (int i = 0; i < processIdArrays.length; i++) {
				String processId = processIdArrays[i];
				ProcessObj processObj = new ProcessObj();
				processObj.setID(processId);
				processObj.setOPERATION("start".equals(oper) ? 1 : 0);// 0停止，1启动，2吴状态
				processObj.setTAST_TYPE(0);// 处理状态 0 待处理 1 处理中 2 已处理
				processService.updateProcessByObj(processObj);
			}
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			json.put("result", 0);
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存进程的父子关系
	 * 
	 * @return
	 */
	public String saveParentProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		try {
			if ("edit".equals(oper)) {
				processService.updateProcessByObj(processObj);
			} else {
				if (parentProcessIds != null && parentProcessIds.length() > 1) {
					String[] tParentProcessIds = parentProcessIds.split(",");
					String[] tUserIds = parentProcessUserIds.split(",");
					for (int i = 0; i < tParentProcessIds.length; i++) {
						String parentProcessId = tParentProcessIds[i];
						ProcessObj temp = new ProcessObj();
						temp.setID(parentProcessId);
						List<ProcessObj> temps = processService.queryProcessInfoList(temp);
						if (temps != null && temps.size() == 1) {
							ProcessObj parentObj = temps.get(0);
							processObj.setID(RandomUUID.getUuid());
							processObj.setPARENT_ID(parentProcessId);
							processObj.setUSER_ID(tUserIds[i]);
							processObj.setTYPE(parentObj.getTYPE());
							processObj.setISRUNNING(3);
							processObj.setTAST_TYPE(2);
							processObj.setOPERATION(2);
							processObj.setPROCESS_STATE(0);
							processObj.setPROCESS_COUNT_ACTUAL(-1);
							processService.insertByObj(processObj);
						} else {
							throw new CloudRuntimeException("无法查询到父进程的数据.");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String delProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String processId = request.getParameter("processId");
		try {
			ProcessObj obj = new ProcessObj();
			obj.setID(processId);
			ProcessObj obj0 = processService.queryProcessInfoList(obj).get(0);
			int ret = processService.delByObj(obj);
			// 如果删除父进程的时候把父进程下面所有的子进程都变为父进程
			if (obj0.getPARENT_ID().equals("0")) {
				ProcessObj obj1 = new ProcessObj();
				obj1.setPARENT_ID(processId);
				List<ProcessObj> objs = processService.queryProcessInfoList(obj1);
				for (int i = 0; i < objs.size(); i++) {
					ProcessObj obj2 = objs.get(i);
					obj2.setPARENT_ID("0");
					processService.updateProcessByObj(obj2);
				}
			}
			JSONObject json = new JSONObject();
			json.put("result", ret);
			// PrintWriter out = response.getWriter();
			// out.println(json);
			// out.close();
			PrintWriterOut.printWirter(response, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
