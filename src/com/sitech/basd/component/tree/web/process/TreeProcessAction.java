package com.sitech.basd.component.tree.web.process;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.tree.domain.relation.ExampleRelationObj;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

import domain.tree.HadoopTreeObj;
import enumtype.Types;
@Scope("prototype")
@Controller("treeProcessAction")
public class TreeProcessAction extends BaseAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	private List<UserObj> hostList = new ArrayList<UserObj>();

	private List<ProcessObj> resultList = new ArrayList<ProcessObj>();

	private ProcessObj processObj;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String oper;

	private String example_id;// 节点业务id

	private String hostIP;

	private String type;
	
	private String server_type;

	private String userNames;
	
	private String passWords;
	
	private String processIds;//需要验证身份的进程id
	
	private String ips;//需要验证身份的ip
	@Autowired
	private ExampleRelationService exampleRelationService;

	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProcessService processService;
	@Autowired
	private HadoopTreeService hadoopTreeService;
	@Autowired
	private PaasTreeService paasTreeService;

	private List<IP> ipList = new ArrayList<IP>();

	public List<IP> getIpList() {
		return ipList;
	}

	public void setIpList(List<IP> ipList) {
		this.ipList = ipList;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProcessObj getProcessObj() {
		return processObj;
	}

	public void setProcessObj(ProcessObj processObj) {
		this.processObj = processObj;
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

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public void setResultList(List<ProcessObj> resultList) {
		this.resultList = resultList;
	}
	

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getPassWords() {
		return passWords;
	}

	public void setPassWords(String passWords) {
		this.passWords = passWords;
	}
	
	public String getProcessIds() {
		return processIds;
	}

	public void setProcessIds(String processIds) {
		this.processIds = processIds;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}
	
	public String getServer_type() {
		return server_type;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	/**
	 * 列出多进程的信息列表
	 * 
	 * @return
	 */
	public String listAllProcess() {
		ProcessObj obj = new ProcessObj();
		if ("2".equals(type)) {
			TbBusiSysTree tree = new TbBusiSysTree();
			tree.setBusiId(example_id);
			List<TbBusiSysTree> treeList = tbBusiSysTreeService.queryForTree(tree);
			if (treeList != null && treeList.size() > 0) {
				tree = treeList.get(0);
			}
			obj.setEXAMPLE_ID(tree.getId());
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			resultList = processService.queryForAppProcess(obj);
		} else if ("3".equals(type)) {
			String example_change = DeployIdFormat.generateAppMapKey(Integer.parseInt(type),
					Integer.parseInt(example_id));
			obj.setEXAMPLE_ID(example_change);
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			resultList = processService.queryForDeployList(obj);
		} else {
			logger.debug("type类型错误，请检查;");
		}
		return "listprocess";
	}

	/**
	 * 添加新进程的操作方法
	 * 
	 * @return
	 */
	public String addNewProcess() {
		// String oper = request.getParameter("oper");
		// String example_id = request.getParameter("example_id");
		// String type = request.getParameter("type");
		// String hostIP = request.getParameter("hostIP");
		ProcessObj obj = new ProcessObj();
		if ("add".equals(oper)) {
			processObj = obj;// 添加的时候需要清空表单所以赋值为null
		} else if ("edit".equals(oper)) {
			String processId = request.getParameter("processid");
			obj.setID(processId);
			List<ProcessObj> objs = processService.queryProcessInfoList(obj);
			if (objs != null && objs.size() == 1) {
				processObj = objs.get(0);
			}
		}
		// request.setAttribute("example_id", example_id);
		// request.setAttribute("type", type);
		// request.setAttribute("hostIP", hostIP);
		// request.setAttribute("oper", oper);
		return "addprocess";
	}
	
	public String saveProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String oper = request.getParameter("oper");
		String userids = request.getParameter("selectUsers");// 所选的用户
		String example_ids = request.getParameter("example_ids");
		String type = request.getParameter("type1");
		try {
			if ("edit".equals(oper)) {
				processService.updateProcessByObj(processObj);
				// out = response.getWriter();
				// out.println(new JSONObject());
				// out.close();
				PrintWriterOut.printWirter(response, new JSONObject());
				return null;
			} else if ("add".equals(oper)) {
				ExampleRelationObj relation = new ExampleRelationObj();
				// 对于部署实例及基准应用的序列id（example_id）进行转化，因为二者可能会出现冲突
				relation.setType(type);
				if (userids != null && !userids.equals("") && userids.length() > 1
						&& example_ids != null && !example_ids.equals("")
						&& example_ids.length() > 1) {
					String[] ipIds1 = userids.split(",");
					String[] examples = example_ids.split(",");
					for (int i = 0; i < ipIds1.length; i++) {
						String example_id = DeployIdFormat.generateAppMapKey(3,
								Integer.parseInt(examples[i]));// 3代表部署实例
						relation.setExample_id(example_id);
						String processUuid = RandomUUID.getUuid();
						processObj.setID(processUuid);
						processObj.setISRUNNING(3);//0 停止 1启动 2部分启动
						processObj.setTAST_TYPE(2);//0 待处理 1处理中 2已处理
						processObj.setOPERATION(2);//0 停止 1 启动 2 不做操作
						processObj.setPROCESS_COUNT_ACTUAL(-1);
						processObj.setPROCESS_STATE(0);
						relation.setEntity_id(processUuid);
						exampleRelationService.inserByObj(relation);
						processObj.setUSER_ID(ipIds1[i]);
						processObj.setPARENT_ID("0");
						UserObj userObj = new UserObj();
						userObj.setId(ipIds1[i]);
						userObj = userService.queryUserObjById(userObj);
						processObj.setIP(userObj.getIp());
						processObj.setUSERNAME(userObj.getUsername());
						processService.insertByObj(processObj);
					}
				}

				// out = response.getWriter();
				// out.println(new JSONObject());
				// out.close();
				PrintWriterOut.printWirter(response, new JSONObject());
			} else {
				logger.debug("出错了.");
				return null;
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
				if ("start".equals(oper)
						&& (isRunning == 1 && operation == 2 && taskType == 2)) {// 运行中不继续启动
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("start".equals(oper) && operation == 1
						&& (taskType == 0 || taskType == 1)) {// 启动中不能继续启动
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 3);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("stop".equals(oper)
						&& (isRunning == 0 && operation == 2 && taskType == 2)) {// 已经停止不用继续停止
					JSONObject json = new JSONObject();
					// PrintWriter out = response.getWriter();
					json.put("result", 2);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				if ("stop".equals(oper)
						&& (operation == 0 && (taskType == 0 || taskType == 1))) {// 停止中不能继续停止
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
			 * 启动或停止后，修改进程状态为 处理中，代码移至后台处理OperProcessCustomerService
			 * 
			 */
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

	public String delProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String processId = request.getParameter("processId");
		try {
			ProcessObj obj = new ProcessObj();
			obj.setID(processId);
			processService.deleteRelation(obj);// 删除进程和实体的关系 @yanggl 2014.1.9
			int ret = processService.delByObj(obj);
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

	/**
	 * 
	 * @Title: listHadoopProcess
	 * @Description: 查询Hadoop各个节点的进程列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-1-8 下午5:40:01
	 */
	public String listHadoopProcess() throws Exception {
		if (processObj == null) {
			processObj = new ProcessObj();
		}
		ProcessObj obj = new ProcessObj();
		if (processObj.getPROCESS() != null && !"".equals(processObj.getPROCESS())) {
			obj.setPROCESS(processObj.getPROCESS());
		}
		if (processObj.getPROCESS_KEY() != null && !"".equals(processObj.getPROCESS_KEY())) {
			obj.setPROCESS_KEY(processObj.getPROCESS_KEY());
		}
		obj.setEXAMPLE_ID(example_id);
		if (HadoopConstant.hostNode.equals(type)) {// 主机节点
			resultList = processService.queryHadoopHostNodeProcess(obj);
		}else{
			obj.setPagination(this.getPaginater().initPagination(request));// 分页
			HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
			// hadoopTreeObj.setParent_id(example_id);
			List nodeIdList = new ArrayList();
			hadoopTreeObj.setId(example_id);
			List<HadoopTreeObj> treeList = acquireChildNode(
					HadoopConstant.hostNode, hadoopTreeObj,
					new ArrayList<HadoopTreeObj>());
			for (HadoopTreeObj treeObj : treeList) {
				nodeIdList.add(treeObj.getId());
			}
			obj.setNodeIdList(nodeIdList);
			resultList = processService.queryHadoopOtherNodeProcess(obj);
		}
		return "listHadoopProcess";
	}

	/**
	 * 
	 * @Title: addNewHadoopProcess
	 * @Description: Hadoop中进程显示添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午7:32:45
	 */
	public String addNewHadoopProcess() {
		ProcessObj obj = new ProcessObj();
		if ("add".equals(oper)) {
			processObj = obj;// 添加的时候需要清空表单所以赋值为null
		} else if ("edit".equals(oper)) {
			String processId = request.getParameter("processid");
			obj.setID(processId);
			List<ProcessObj> objs = processService.queryProcessInfoList(obj);
			if (objs != null && objs.size() == 1) {
				processObj = objs.get(0);
			}
		}
		return "addHadoopProcess";
	}

	/**
	 * 
	 * @Title: saveHadoppProcess
	 * @Description: 保存Hadoop节点中添加的进程
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午7:34:38
	 */
	public String saveHadoppProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String oper = request.getParameter("oper");
		String userids = request.getParameter("selectUsers");// 所选的用户
		String userips = request.getParameter("userips");// IP地址
		String example_id = request.getParameter("example_id");// 节点ID
		String type = request.getParameter("type1");
		try {
			if ("edit".equals(oper)) {
				processService.updateProcessByObj(processObj);
				// out = response.getWriter();
				// out.println(new JSONObject());
				// out.close();
				PrintWriterOut.printWirter(response, new JSONObject());
				return null;
			} else if ("add".equals(oper)) {
				ExampleRelationObj relation = new ExampleRelationObj();
				relation.setType(type);
				if (userids != null && !userids.equals("") && userids.length() > 1
						&& example_id != null && !example_id.equals("")) {
					String[] ipIds1 = userips.split(",");
					String[] userIds1 = userids.split(",");
					for (int i = 0; i < ipIds1.length; i++) {
						relation.setExample_id(example_id);
						String processUuid = RandomUUID.getUuid();
						relation.setEntity_id(processUuid);
						exampleRelationService.inserByObj(relation);
						processObj.setID(processUuid);
						processObj.setISRUNNING(3);//0 停止 1启动 2部分启动 3检测中
						processObj.setTAST_TYPE(2);//0 待处理 1处理中 2已处理 
						processObj.setOPERATION(2);//0 停止 1 启动 2 无状态
						processObj.setPROCESS_COUNT_ACTUAL(-1);
						processObj.setPROCESS_STATE(0);
						processObj.setUSER_ID(userIds1[i]);
						processObj.setPARENT_ID("0");
						processObj.setIP(ipIds1[i]);
						processObj.setUSERNAME(userIds1[i]);
						processService.insertByObj(processObj);
					}
				}
				// out = response.getWriter();
				// out.println(new JSONObject());
				// out.close();
				PrintWriterOut.printWirter(response, new JSONObject());
			} else {
				logger.debug("出错了.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: acquireChildNode
	 * @Description: 递归计算子级节点个数，不单单是子节点
	 * @param
	 * @return List<HadoopTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-16 上午10:46:49
	 */
	private List<HadoopTreeObj> acquireChildNode(String childNodeType,
			HadoopTreeObj hadoopTreeObj,
			List<HadoopTreeObj> retList) throws Exception {
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService
					.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
						retList.add(htObj);
				} else {
					acquireChildNode(childNodeType, htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}
	/**
	 * @Title: checkOperPage
	 * @Description: 跳转到验证页面
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-2 上午10:52:07
	 */
	public String checkOperPage(){
		acquireIpList(ips);
		return "checkOperPage";
	}
	/**
	 * @Title: checkOperDo
	 * @Description: 用于验证用户名、密码是否正确
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-2 上午9:45:00
	 */
	@SuppressWarnings("unchecked")
	public void checkOperDo(){
		String result="";
		acquireIpList(ips);
		String[] userNameArr = userNames.split(",");
		String[] passWordArr = passWords.split(",");
		for(int i=0;i<ipList.size();i++){
			IP ipObj = ipList.get(i);
			hostIP = ipObj.getIp();
			UserObj userObj = new UserObj();
			userObj.setIp(hostIP.trim());
			userObj.setPagination(this.getPaginater().initPagination(request));// 分页
			List<UserObj> list = userService.listForProcess(userObj);
			if(list != null && list.size()>=1){
				try {
					String actual_userName = list.get(0).getUsername();
					String actual_passWord =list.get(0).getPassword();
					if(userNameArr[i].equals(actual_userName) && passWordArr[i].equals(actual_passWord)){
						result = "";
					}else{
						result +="主机"+ipObj.getIp()+"信息填写有误;"+"\n";
					}
				} catch (Exception e) {
					logger.info("验证过程出错！",e);
				}
			}else{
				result += "无此主机"+ipObj.getIp()+";"+"\n";
			}
		}		
		JSONObject jo = new JSONObject();
		jo.put("result", result);
		PrintWriterOut.printWirter(response, jo);
	}
	/**
	 * @Title: acquireIpList
	 * @Description: 将ip字符串转换成集合，并去重
	 * @param
	 * @return List<IP>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-10 下午1:04:58
	 */
	public List<IP> acquireIpList(String ipStr){
		String[] ipArr = ipStr.split(",");
		for(String str : ipArr){
			String[] ip = str.split("/");
			IP ipObj =new IP();
			ipObj.setIp(ip[0]);
			if(ipList.size()<1){			
				ipList.add(ipObj);
			}else{
				boolean flag = true;
				for(IP ipOb : ipList){
					if(ipOb.getIp().equals(ip[0])){
						flag = false;
						break;
					}
				}
				if(flag){				
					ipList.add(ipObj);
				}
			}
		}
		return ipList;
	}
	//私有的内部类
	private class IP{
		private String ip;

		public String getIp() {
			return ip;
		}
	
		public void setIp(String ip) {
			this.ip = ip;
		}
	}

	/**
	 * 
	 * @Title: listPaasProcess
	 * @Description: 查询paas各节点进程列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-3 上午11:40:08
	 */
	public String listPaasProcess() throws Exception {
		if (processObj == null) {
			processObj = new ProcessObj();
		}
		ProcessObj obj = new ProcessObj();
		if (processObj.getPROCESS() != null && !"".equals(processObj.getPROCESS())) {
			obj.setPROCESS(processObj.getPROCESS());
		}
		if (processObj.getPROCESS_KEY() != null && !"".equals(processObj.getPROCESS_KEY())) {
			obj.setPROCESS_KEY(processObj.getPROCESS_KEY());
		}
		String node_type = null;
		String serverType = null;
		if(PaasConstant.ORACLE.equals(server_type)){
			node_type = PaasConstant.DAAS_DB;
			serverType = PaasConstant.ORACLE_SERVER;
		}else if(PaasConstant.WEBLOGIC.equals(server_type)){
			node_type = PaasConstant.MAAS_DOMAIN;
			serverType = PaasConstant.WEBLOGIC_SERVER;
		}
		obj.setEXAMPLE_ID(example_id);
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		if (PaasConstant.DAAS_DB.equals(type) || PaasConstant.MAAS_DOMAIN.equals(type)) {
			resultList = processService.queryHadoopHostNodeProcess(obj);
		} else {
			PaasTreeObj paasTreeObj = new PaasTreeObj();
			List nodeIdList = new ArrayList();
			paasTreeObj.setId(example_id);
			List<PaasTreeObj> treeList = acquirePaasChildNode(node_type,serverType, paasTreeObj,
					new ArrayList<PaasTreeObj>());
			for (PaasTreeObj treeObj : treeList) {
				nodeIdList.add(treeObj.getId());
			}
			obj.setNodeIdList(nodeIdList);
			resultList = processService.queryHadoopOtherNodeProcess(obj);
		}
		return "listPaasProcess";
	}

	/**
	 * 
	 * @Title: addPaasProcess
	 * @Description: 添加paas进程
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-3 下午2:38:52
	 */
	public String addPaasProcess() {
		ProcessObj obj = new ProcessObj();
		if ("add".equals(oper)) {
			processObj = obj;// 添加的时候需要清空表单所以赋值为null
		} else if ("edit".equals(oper)) {
			String processId = request.getParameter("processid");
			obj.setID(processId);
			List<ProcessObj> objs = processService.queryProcessInfoList(obj);
			if (objs != null && objs.size() == 1) {
				processObj = objs.get(0);
			}
		}
		return "addPaasProcess";
	}

	/**
	 * 
	 * @Title: saveHadoppProcess
	 * @Description: 保存Hadoop节点中添加的进程
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-9 下午7:34:38
	 */
	public String savePaasProcess() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String oper = request.getParameter("oper");
		String userids = request.getParameter("selectUsers");// 所选的用户
		String userips = request.getParameter("userips");// IP地址
		String example_id = request.getParameter("example_id");// 节点ID
		String type = request.getParameter("type1");
		try {
			if ("edit".equals(oper)) {
				processService.updateProcessByObj(processObj);
				PrintWriterOut.printWirter(response, new JSONObject());
				return null;
			} else if ("add".equals(oper)) {
				ExampleRelationObj relation = new ExampleRelationObj();
				relation.setType(type);
				if (userids != null && !userids.equals("") && userids.length() > 1
						&& example_id != null && !example_id.equals("")) {
					String[] ipIds1 = userips.split(",");
					String[] userIds1 = userids.split(",");
					for (int i = 0; i < ipIds1.length; i++) {
						relation.setExample_id(example_id);
						String processUuid = RandomUUID.getUuid();
						relation.setEntity_id(processUuid);
						exampleRelationService.inserByObj(relation);
						processObj.setID(processUuid);
						processObj.setISRUNNING(3);// 0 停止 1启动 2部分启动 3检测中
						processObj.setTAST_TYPE(2);// 0 待处理 1处理中 2已处理
						processObj.setOPERATION(2);// 0 停止 1 启动 2 无状态
						processObj.setPROCESS_COUNT_ACTUAL(-1);
						processObj.setPROCESS_STATE(0);
						processObj.setUSER_ID(userIds1[i]);
						processObj.setPARENT_ID("0");
						processObj.setIP(ipIds1[i]);
						processObj.setUSERNAME(userIds1[i]);
						processService.insertByObj(processObj);
					}
				}
				PrintWriterOut.printWirter(response, new JSONObject());
			} else {
				logger.debug("出错了.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: acquirePaasChildNode
	 * @Description: 递归计算子级节点个数，不单单是子节点
	 * @param
	 * @return List<PaasTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-9-3 下午3:15:27
	 */
	private List<PaasTreeObj> acquirePaasChildNode(String childNodeType, String clildServerType, 
			PaasTreeObj paasTreeObj, List<PaasTreeObj> retList) throws Exception {
		try {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setParent_id(paasTreeObj.getId());
			List<PaasTreeObj> treeList = paasTreeService.queryTreeNodeByObj(treeObj);
			for (PaasTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type()) 
						&& clildServerType.equals(htObj.getServer_type())) {
					retList.add(htObj);
				} else {
					acquirePaasChildNode(childNodeType, clildServerType, htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： ", e);
		}
	}
}
