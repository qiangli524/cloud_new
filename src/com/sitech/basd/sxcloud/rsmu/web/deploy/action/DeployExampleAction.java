package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.component.domain.log.TbOperationLogObj;
import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.service.log.LogService;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.service.bizsystem.BizSystemService;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.StartAndStopObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDoTaskObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.configfile.DeployConfigFile;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleHisService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployStrategyService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.TaskService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.configfile.DeployConfigFileServer;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.AppVersionService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.OnlineHistoryService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployExampleForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.utils.common.FormDataUtil;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.exception.SSHException;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

@SuppressWarnings("all")
public class DeployExampleAction extends CRUDBaseAction {
	private Logger Log = Logger.getLogger(DeployExampleAction.class);
	private DeployExampleForm theForm;
	private DeployExampleService deployExampleService;
	private BusiHostService busiHostService;
	private AppService appService;
	private DeployExampleHisService deployExampleHisService;
	private DeployStrategyService deployStrategyService;
	private BizSystemService bizSystemService;
	private DeployConfigFileServer deployConfigFileServerImpl;
	private AppVersionService appVersionService;
	private OnlineHistoryService onlineHistoryService;
	private BusiHostConfigService busiHostConfigService;
	private TbBusiSysTreeService tbBusiSysTreeService;
	private UserService userService;
	private ProcessService processService;
	@Autowired
	private LogService logService;
	private String sysId;
	private List resultList;
	private StartAndStopObj startAndStopObj;

	public StartAndStopObj getStartAndStopObj() {
		return startAndStopObj;
	}

	public void setStartAndStopObj(StartAndStopObj startAndStopObj) {
		this.startAndStopObj = startAndStopObj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTbBusiSysTreeService(TbBusiSysTreeService tbBusiSysTreeService) {
		this.tbBusiSysTreeService = tbBusiSysTreeService;
	}

	public void setBusiHostConfigService(BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

	public void setOnlineHistoryService(OnlineHistoryService onlineHistoryService) {
		this.onlineHistoryService = onlineHistoryService;
	}

	public DeployExampleForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployExampleForm theForm) {
		this.theForm = theForm;
	}

	public void setAppVersionService(AppVersionService appVersionService) {
		this.appVersionService = appVersionService;
	}

	private TaskService taskService;

	public void setDeployConfigFileServerImpl(DeployConfigFileServer deployConfigFileServerImpl) {
		this.deployConfigFileServerImpl = deployConfigFileServerImpl;
	}

	public void setBizSystemService(BizSystemService bizSystemService) {
		this.bizSystemService = bizSystemService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setDeployStrategyService(DeployStrategyService deployStrategyService) {
		this.deployStrategyService = deployStrategyService;
	}

	public void setDeployExampleHisService(DeployExampleHisService deployExampleHisService) {
		this.deployExampleHisService = deployExampleHisService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	/**
	 * @Title:得到部署实例管理信息列表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		if (request.getParameter("HOST_ID") != null && !request.getParameter("HOST_ID").equals("")) {
			obj.setHOSTID(Integer.parseInt(request.getParameter("HOST_ID")));
			theForm.setHOSTID(Integer.parseInt(request.getParameter("HOST_ID")));
		}
		String sys_id = request.getParameter("sys_id");
		if (sys_id != null && !"".equals(sys_id)) {
			obj.setSYS_ID(sys_id);
			theForm.setSYS_ID(sys_id);
		} else if (theForm.getSYS_ID() != null && !"".equals(theForm.getSYS_ID())) {
			obj.setSYS_ID(theForm.getSYS_ID());
		}
		if (theForm.getAPPID() != 0) {
			obj.setAPPID(theForm.getAPPID());
		}
		if (theForm.getDEPLOY_FLAG() != null && !"".equals(theForm.getDEPLOY_FLAG())) {
			obj.setDEPLOY_FLAG(theForm.getDEPLOY_FLAG());
		}
		if (theForm.getSTART_STOP_FLAG() != null && !"".equals(theForm.getSTART_STOP_FLAG())) {
			obj.setSTART_STOP_FLAG(theForm.getSTART_STOP_FLAG());
		}
		if (theForm.getHOSTID() != 0) {
			obj.setHOSTID(theForm.getHOSTID());
		}
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		BizSystemObj bizObj = new BizSystemObj();
		List bizList = bizSystemService.queryForListByObj(bizObj);

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// ServletActionContext.getRequest()
		// .getSession().getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) (String) session.get("datau"));
		List resultList = this.deployExampleService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		theForm.setAppList(appList); // 应用集合
		theForm.setBizList(bizList);// 业务系统集合
		return LIST;
	}

	/**
	 * 
	 * @Title: batchDeployExample
	 * @Description: 跳转到批量部署页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 19, 2013 4:32:08 PM
	 */
	public String batchDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String nodeId = request.getParameter("nodeId");

		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// ServletActionContext.getRequest()
		// .getSession().getAttribute(Constant.USER_SESSION_KEY);
		String dataAuthority = (String) session.get("datau");
		String appId = "-1";

		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setId(nodeId);
		List<TbBusiSysTree> lst = tbBusiSysTreeService.queryForTree(treeObj);
		if (lst != null && lst.size() > 0) {
			treeObj = lst.get(0);
		}
		if (dataAuthority == null) {
			appId = treeObj.getBusiId();
		} else {
			String[] appIds = dataAuthority.split(",");
			for (int i = 0; i < appIds.length; i++) {
				if (appIds[i] == treeObj.getBusiId()) {
					appId = treeObj.getBusiId();
					break;
				}
			}
		}
		TbBusiDeployExampleObj depExObj = new TbBusiDeployExampleObj();
		depExObj.setDATAAUTHORITY(appId);
		// depExObj.setPagination(this.getPaginater().initPagination(
		// Struts2Utils.getRequest()));// 分页
		List resultList = deployExampleService.queryForListByObj(depExObj);
		theForm.setResultList(resultList);
		request.setAttribute("nodeId", nodeId);
		return "batch";
	}

	/**
	 * 
	 * @Title: listBizDeployExample
	 * @Description: 展示业务视图
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 6, 2013 1:04:10 PM
	 */
	public String listBizDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (theForm.getAPPID() != 0) {
			obj.setAPPID(theForm.getAPPID());
		}
		if (theForm.getDEPLOY_FLAG() != null && !"".equals(theForm.getDEPLOY_FLAG())) {
			obj.setDEPLOY_FLAG(theForm.getDEPLOY_FLAG());
		}
		if (theForm.getSTART_STOP_FLAG() != null && !"".equals(theForm.getSTART_STOP_FLAG())) {
			obj.setSTART_STOP_FLAG(theForm.getSTART_STOP_FLAG());
		}
		if (theForm.getBIZID() != null && !theForm.getBIZID().equals("")) {
			obj.setSYS_ID(theForm.getBIZID());
		}
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		BizSystemObj bizObj = new BizSystemObj();
		List bizList = bizSystemService.queryForListByObj(bizObj);

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// ServletActionContext.getRequest()
		// .getSession().getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List resultList = this.deployExampleService.queryForBizListByObj(obj);
		theForm.setResultList(resultList);
		theForm.setAppList(appList); // 应用集合
		theForm.setBizList(bizList);// 业务系统集合
		return "bizview";
	}

	// 定时来获取部署的状态
	@SuppressWarnings("unchecked")
	public String Example_Deploy_Flag() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		obj.setLONGID(id);
		ArrayList<TbBusiDeployExampleObj> reployProcessList = (ArrayList<TbBusiDeployExampleObj>) deployExampleService
				.queryListIDByObj(obj);
		for (int i = 0; i < reployProcessList.size(); i++) {
			TbBusiDeployExampleObj deployExample = (TbBusiDeployExampleObj) reployProcessList
					.get(i);
			String flag = deployExample.getDEPLOY_FLAG();
			String start_stop_flag = deployExample.getSTART_STOP_FLAG();
			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("0")) {
					deployExample.setDEPLOY_FLAG_NAME("未部署");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"部署\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'1');return false;\" />&nbsp;";
				} else if (flag.equals("1")) {
					deployExample.setDEPLOY_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>"
							+ deployExample.getDEPLOY_PERCENT() + "");
				} else if (flag.equals("2")) {
					deployExample.setDEPLOY_FLAG_NAME("已部署");
					// str += "<input type=\"button\" class=\"thickbox
					// btn-style02\" value=\"卸载\" ";
					// str += "onclick=\"deployRequest(" + deployExample.getID()
					// + ",'3');return false;\" />&nbsp;";
				} else if (flag.equals("3")) {
					// deployExample
					// .setDEPLOY_FLAG_NAME("<img
					// src=\"<%=request.getContextPath()
					// %>/sxcloud/images/ajax-loader.gif\" width=\"15\"
					// height=\"18\"/>正在卸载");
				} else if (flag.equals("4")) {
					// deployExample.setDEPLOY_FLAG_NAME("已卸载");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"部署\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'1');return false;\" />&nbsp;";
				} else if (flag.equals("5") || flag.equals("8") || flag.equals("9")) {// 等待上线-等待回滚-正在回滚
					deployExample.setDEPLOY_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>"
							+ deployExample.getDEPLOY_PERCENT() + "");
				} else if (flag.equals("7") || flag.equals("10")) {// 上线完成
					deployExample.setDEPLOY_FLAG_NAME("上线完成！");
				}
			}
			if (start_stop_flag != null && !"".equals(start_stop_flag)) {
				if (start_stop_flag.equals("0")) {
					deployExample.setSTART_STOP_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>正在停止");
				} else if (start_stop_flag.equals("1")) {
					deployExample.setSTART_STOP_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/downed.png\" width=\"16\" height=\"16\"/>已停止");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"启动\" ";
					str += "onclick=\"StartAndStopDeployExample(" + deployExample.getID()
							+ ",'2');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				} else if (start_stop_flag.equals("2")) {
					deployExample.setSTART_STOP_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif\" width=\"15\" height=\"18\"/>正在启动");
				} else if (start_stop_flag.equals("3")) {
					deployExample.setSTART_STOP_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/uped.png\" width=\"16\" height=\"16\"/>已启动");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"停止\" ";
					str += "onclick=\"StartAndStopDeployExample(" + deployExample.getID()
							+ ",'0');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				}
			}
			String starttime = deployExample.getDEPLOYESTARTTIME();
			String endtime = deployExample.getDEPLOYEENDTIME();
			if (starttime == null && endtime == null) {
				deployExample.setDEPLOYEENDTIME("--");
			}
			if (starttime != null && endtime == null) {
				deployExample.setDEPLOYEENDTIME(deployExample.getDEPLOYESTARTTIME() + "-- 未结束");
			}
			if (starttime == null && endtime != null) {
				deployExample.setDEPLOYEENDTIME("--");
			}
			if (starttime != null && endtime != null) {
				if (starttime.compareTo(endtime) > 0) {
					deployExample.setDEPLOYEENDTIME(deployExample.getDEPLOYESTARTTIME() + "-- 未结束");
				} else {
					deployExample.setDEPLOYEENDTIME(deployExample.getDEPLOYESTARTTIME() + "--"
							+ deployExample.getDEPLOYEENDTIME());
				}
			}
			deployExample.setDEPLOY_FLAG_AN(str);
			reployProcessList.set(i, deployExample);
		}

		// 存入json
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(reployProcessList);
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		PrintWriterOut.printWirter(response, ja.toString());
		// out.close();
		return null;
	}

	/**
	 * @Title:删除部署实例管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (operType.equals("list")) {
			// 保存原业务系统的ID
			sysId = theForm.getSYS_ID();
			int result = 0;
			try {
				BeanUtils.copyProperties(obj, theForm);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obj = deployExampleService.queryByObj(obj);
			// 删除hostConfig表中数据
			if (obj != null && obj.getHOST_CONFIG_ID() != null
					&& !"".equals(obj.getHOST_CONFIG_ID())) {
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.deleteByObj(configObj);
			}
			// 删除deployExample表中数据
			int ret = deployExampleService.deleteByObj(obj);
			if (ret > 0) {
				result = 1;
			}
			insertOperationLog(result);
			// 删除业务中心树表
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setType(3);
			treeObj.setBusiId(obj.getID() + "");
			tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);
		} else if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			int ret2 = 0;
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				obj.setID(Integer.parseInt(treeObj.getBusiId()));
				ret2 = tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);
			}
			int result = 0;
			// 删除hostConfig表中数据
			obj = deployExampleService.queryByObj(obj);
			// 删除hostConfig表中数据
			if (obj != null && obj.getHOST_CONFIG_ID() != null
					&& !"".equals(obj.getHOST_CONFIG_ID())) {
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.deleteByObj(configObj);
			}
			// 删除deployExample表中数据
			int ret = deployExampleService.deleteByObj(obj);
			// 删除节点时，删除操作日志中数据
			TbOperationLogObj tbLogObj = new TbOperationLogObj();
			tbLogObj.setExampleId(treeObj.getBusiId());
			logService.deleteOperationLog(tbLogObj);
			String res = "";
			if (ret != -1 && ret2 != -1) {
				result = 1;
				res = "{\"result\":\"" + "1" + "\"}";
			} else {
				res = "{\"result\":\"" + "-1" + "\"}";
			}
			insertOperationLog(result);
			JSONObject jo = JSONObject.fromObject(res);
			try {
				JSONUtil.printJSON(jo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return REDIRECT;
	}

	/**
	 * 
	 * @Title: insertOperationLog
	 * @Description: 插入操作日志
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 31, 2013 7:42:32 PM
	 */
	private void insertOperationLog(int result) {
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除部署实例管理信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
	}

	/**
	 * @Title:添加部署实例管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		String dialogName = request.getParameter("dialogName") == null ? "" : request
				.getParameter("dialogName");
		theForm.reset();// 清空ActionForm
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		TbBusiHostObj hostObj = new TbBusiHostObj();
		List hostLst = busiHostService.queryForListByObj(hostObj);
		theForm.setHostList(hostLst);
		theForm.setAppList(appList);
		theForm.setHOSEUSERLIST(new ArrayList());
		if (operType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				appObj.setID(Integer.parseInt(treeObj.getBusiId()));
				appObj = appService.queryByObj(appObj);
				theForm.setAPPID(appObj.getID());
			}
		}
		request.setAttribute("operType", operType);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("dialogName", dialogName);

		return ADD;
	}

	/**
	 * @Title:添加部署实例管理信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");

		if (operType.equals("tree")) {
			try {
				FormDataUtil.copyProperties(theForm, request);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
		hostConfigObj.setDeploy_path(theForm.getDEPLOYFILEPATH());
		hostConfigObj.setBase_path(theForm.getDeployPath());
		// 设置应用访问路径
		hostConfigObj.setAppPath(theForm.getAppVisitPath());
		if (theForm.getID() == 0) {
			hostConfigObj.setUSER_MANAGE_ID(theForm.getHOSEUSERID());
			hostConfigObj.setHOSTID(theForm.getHOSTID());
		}

		int ret = 0;
		int result = 0;
		// 从列表操作
		if (operType.equals("list")) {
			// 保存业务系统Id
			sysId = theForm.getSYS_ID();
			// 添加部署实例
			if (theForm.getID() == 0) {
				int hostId = theForm.getHOSTID();
				String hostConfigId = RandomUUID.getUuid();

				TbSysAppObj tbSysAppObj = new TbSysAppObj();
				tbSysAppObj.setID(theForm.getAPPID());
				TbSysAppObj sysAppObj = appService.queryByObj(tbSysAppObj);

				TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
				obj.setHOSTID(hostId);
				obj.setAPPID(theForm.getAPPID());
				obj.setSYS_ID(sysAppObj.getSYS_ID());
				obj.setHOST_CONFIG_ID(hostConfigId);
				obj.setDEPLOY_FLAG("0");
				obj.setDEPLOY_PERCENT("请等待");
				obj.setExampleName(theForm.getExampleName());
				obj.setTRUST_FLAG(theForm.getTRUST_FLAG());
				obj.setDEPLOYEXAMPLE_TYPE(theForm.getDEPLOYEXAMPLE_TYPE());
				// TbCloud3AppVersionVO vo = new TbCloud3AppVersionVO();
				// vo.setApp_id(theForm.getAPPID());
				// String minDayVersion =
				// appVersionService.queryMinAppVersion(vo);
				// obj.setDay_version(minDayVersion);

				ret = deployExampleService.insertByObj(obj);

				hostConfigObj.setAPPID(String.valueOf(ret));
				hostConfigObj.setHOSTCONFIGID(hostConfigId);
				int configRet = busiHostConfigService.insertByObj(hostConfigObj);

				// 向树上添加部署实例
				TbBusiSysTree parentObj = new TbBusiSysTree();
				parentObj.setBusiId(theForm.getAPPID() + "");
				parentObj.setType(2);
				List appLst = tbBusiSysTreeService.queryForTree(parentObj);
				if (appLst != null && appLst.size() > 0) {
					parentObj = (TbBusiSysTree) appLst.get(0);
				}
				TbBusiHostObj hostObj = new TbBusiHostObj();
				hostObj.setID(theForm.getHOSTID());
				hostObj = busiHostService.queryByObj(hostObj);
				TbBusiSysTree treeObj = new TbBusiSysTree();
				treeObj.setBusiId(ret + "");
				treeObj.setHostIP(hostObj.getIP());
				// 节点名称为部署实例名称By huojla
				treeObj.setName(theForm.getExampleName());
				treeObj.setType(3);
				treeObj.setParentId(parentObj.getId());
				String treeNum = tbBusiSysTreeService.insertTbBusiSysTree(treeObj);

				operObj.setOPERTYPE(1);
				operObj.setMESSAGE("新增部署实例");
				if (ret > 0) {
					result = 1;
					operObj.setRESULT(result);
				}
			} else {
				TbBusiDeployExampleObj quObj = new TbBusiDeployExampleObj();
				quObj.setID(theForm.getID());
				quObj = deployExampleService.queryByObj(quObj);

				TbBusiDeployExampleObj depObj = new TbBusiDeployExampleObj();
				depObj.setID(theForm.getID());
				depObj.setHOSTID(quObj.getHOSTID());
				depObj.setAPPID(quObj.getAPPID());
				depObj.setDEPLOY_FLAG(theForm.getDEPLOY_FLAG());
				// 增加部署实例名称
				depObj.setExampleName(theForm.getExampleName());
				depObj.setTRUST_FLAG(theForm.getTRUST_FLAG());
				depObj.setDEPLOYEXAMPLE_TYPE(theForm.getDEPLOYEXAMPLE_TYPE());
				ret = deployExampleService.updateByObj(depObj);

				TbBusiSysTree queryTree = new TbBusiSysTree();
				queryTree.setType(3);
				queryTree.setBusiId(theForm.getID() + "");
				List lst = tbBusiSysTreeService.queryForTree(queryTree);
				if (lst != null && lst.size() > 0) {
					queryTree = (TbBusiSysTree) lst.get(0);
				}
				TbBusiSysTree tree = new TbBusiSysTree();
				tree.setName(theForm.getExampleName());
				tree.setId(queryTree.getId());
				tbBusiSysTreeService.updateTbBusiSysTreeByObj(tree);

				TbBusiHostConfigObj queryHostObj = new TbBusiHostConfigObj();
				queryHostObj.setHOSTCONFIGID(quObj.getHOST_CONFIG_ID());
				queryHostObj = busiHostConfigService.queryByObj(queryHostObj);
				hostConfigObj.setUSER_MANAGE_ID(queryHostObj.getUSER_MANAGE_ID());
				hostConfigObj.setHOSTID(queryHostObj.getHOSTID());
				hostConfigObj.setHOSTCONFIGID(quObj.getHOST_CONFIG_ID());
				busiHostConfigService.updateHostConfigByAppId(hostConfigObj);

				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改部署实例");
			}
		} else if (operType.equals("tree")) {
			// 从业务中心树上操作
			String ret2 = "";
			if (theForm.getID() == 0) {
				String hostConfigId = RandomUUID.getUuid();

				TbBusiSysTree parentObj = new TbBusiSysTree();
				parentObj.setType(2);
				parentObj.setId(nodeId);
				List lst = tbBusiSysTreeService.queryForTree(parentObj);
				if (lst != null && lst.size() > 0) {
					parentObj = (TbBusiSysTree) lst.get(0);
				}
				int hostId = theForm.getHOSTID();
				TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();

				TbSysAppObj tbSysAppObj = new TbSysAppObj();
				tbSysAppObj.setID(Integer.parseInt(parentObj.getBusiId()));
				TbSysAppObj sysAppObj = appService.queryByObj(tbSysAppObj);

				obj.setHOSTID(hostId);
				obj.setAPPID(sysAppObj.getID());
				obj.setExampleName(theForm.getExampleName());
				obj.setSYS_ID(sysAppObj.getSYS_ID());
				obj.setHOST_CONFIG_ID(hostConfigId);
				obj.setDEPLOY_FLAG("0");
				obj.setDEPLOY_PERCENT("请等待");
				obj.setTRUST_FLAG(theForm.getTRUST_FLAG());
				obj.setDEPLOYEXAMPLE_TYPE(theForm.getDEPLOYEXAMPLE_TYPE());
				// TbCloud3AppVersionVO vo = new TbCloud3AppVersionVO();
				// vo.setApp_id(Integer.parseInt(parentObj.getBusiId()));
				// String minDayVersion =
				// appVersionService.queryMinAppVersion(vo);
				// obj.setDay_version(minDayVersion);

				ret = deployExampleService.insertByObj(obj);

				hostConfigObj.setAPPID(String.valueOf(ret));
				hostConfigObj.setHOSTCONFIGID(hostConfigId);
				int configRet = busiHostConfigService.insertByObj(hostConfigObj);

				operObj.setOPERTYPE(1);
				operObj.setMESSAGE("新增部署实例");

				if (ret > 0) {
					result = 1;
					operObj.setRESULT(result);
					@SuppressWarnings("unused")
					int retOper = operationService.insertByObj(operObj);// 写操作日志

					TbBusiSysTree treeObj = new TbBusiSysTree();
					TbBusiHostObj hostObj = new TbBusiHostObj();
					hostObj.setID(hostId);
					hostObj = busiHostService.queryByObj(hostObj);

					if (hostObj != null) {
						treeObj.setName(theForm.getExampleName());
						treeObj.setHostIP(hostObj.getIP());
						treeObj.setBusiId(ret + "");
						treeObj.setParentId(nodeId);
						treeObj.setType(3);
						ret2 = tbBusiSysTreeService.insertTbBusiSysTree(treeObj);
					}
				}
				String res = "";
				if (ret2.equals("-1")) {
					res = "{\"result\":\"" + "-1" + "\"}";
				} else {
					res = "{\"result\":\"" + "1" + "\"}";
				}
				JSONObject jo = JSONObject.fromObject(res);
				try {
					JSONUtil.printJSON(jo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			} else {
				TbBusiDeployExampleObj queryObj = new TbBusiDeployExampleObj();
				queryObj.setID(theForm.getID());
				queryObj = deployExampleService.queryByObj(queryObj);

				queryObj.setDEPLOY_FLAG(theForm.getDEPLOY_FLAG());
				queryObj.setExampleName(theForm.getExampleName());
				queryObj.setTRUST_FLAG(theForm.getTRUST_FLAG());
				queryObj.setDEPLOYEXAMPLE_TYPE(theForm.getDEPLOYEXAMPLE_TYPE());
				ret = deployExampleService.updateByObj(queryObj);

				TbBusiSysTree queryTree = new TbBusiSysTree();
				queryTree.setType(3);
				queryTree.setBusiId(theForm.getID() + "");
				List lst = tbBusiSysTreeService.queryForTree(queryTree);
				if (lst != null && lst.size() > 0) {
					queryTree = (TbBusiSysTree) lst.get(0);
				}
				TbBusiSysTree tree = new TbBusiSysTree();
				tree.setName(theForm.getExampleName());
				tree.setId(queryTree.getId());
				tbBusiSysTreeService.updateTbBusiSysTreeByObj(tree);

				TbBusiHostConfigObj queryHostObj = new TbBusiHostConfigObj();
				queryHostObj.setHOSTCONFIGID(queryObj.getHOST_CONFIG_ID());
				queryHostObj = busiHostConfigService.queryByObj(queryHostObj);
				hostConfigObj.setUSER_MANAGE_ID(queryHostObj.getUSER_MANAGE_ID());
				hostConfigObj.setHOSTID(queryHostObj.getHOSTID());

				hostConfigObj.setHOSTCONFIGID(queryObj.getHOST_CONFIG_ID());
				busiHostConfigService.updateHostConfigByAppId(hostConfigObj);

				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改部署实例");

				String res = "";
				if (ret > 0) {
					result = 1;
					res = "{\"result\":\"" + "1" + "\"}";
				} else {
					res = "{\"result\":\"" + "-1" + "\"}";
				}
				operObj.setRESULT(result);
				@SuppressWarnings("unused")
				int retOper = operationService.insertByObj(operObj);// 写操作日志
				JSONObject jo = JSONObject.fromObject(res);
				try {
					JSONUtil.printJSON(jo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		}

		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		return REDIRECT;
	}

	/**
	 * 
	 * @Title: modDeployExample
	 * @Description: update 实例连接显示bug
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-18 上午11:00:56
	 */
	public String modDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();

		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		String dialogName = request.getParameter("dialogName") == null ? "" : request
				.getParameter("dialogName");

		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (operType.equals("list")) {
			obj.setID(theForm.getID());
			sysId = theForm.getSYS_ID();
		} else if (operType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				obj.setID(Integer.parseInt(treeObj.getBusiId()));
				theForm.setID(Integer.parseInt(treeObj.getBusiId()));
			}
		}

		TbBusiDeployExampleObj tempObj = deployExampleService.queryByObj(obj);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TbSysAppObj appObj = new TbSysAppObj();
		appObj.setID(theForm.getAPPID());

		TbBusiHostObj busiHostObj = new TbBusiHostObj();
		List<TbBusiHostObj> busiHostList = busiHostService.queryForListByObj(busiHostObj);
		theForm.setHostList(busiHostList);

		TbBusiHostObj hostObj = new TbBusiHostObj();
		hostObj.setID(theForm.getHOSTID());
		hostObj = busiHostService.queryByObj(hostObj);
		if (hostObj != null) {
			Map map = new HashMap();
			map.put("ip", hostObj.getIP());
			List hoseuserlist = userService.queryDeployUserList(map);
			theForm.setHOSEUSERLIST(hoseuserlist);
		} else {
			theForm.setHOSEUSERLIST(new ArrayList());
		}
		TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
		hostConfigObj.setHOSTCONFIGID(tempObj.getHOST_CONFIG_ID());
		hostConfigObj = busiHostConfigService.queryByObj(hostConfigObj);
		if (hostConfigObj != null) {
			theForm.setHOSEUSERID(hostConfigObj.getUSER_MANAGE_ID());
			theForm.setDeployPath(hostConfigObj.getBase_path());
			theForm.setDEPLOYFILEPATH(hostConfigObj.getDeploy_path());
		}

		request.setAttribute("operType", operType);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("dialogName", dialogName);

		TbSysAppObj apobj = new TbSysAppObj();
		List appList = appService.queryForListByObj(apobj);
		theForm.setAppList(appList);
		// 设置实例访问路径
		theForm.setAppVisitPath(hostConfigObj.getAppPath());
		if (operType.equals("list")) {
			theForm.setSYS_ID(sysId);
		}
		return MODIFY;
	}

	/**
	 * @Title:启停服务操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String StartAndStopDeployExample() {
		// DeployExampleForm theForm = (DeployExampleForm) form;
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		String param = request.getParameter("paran");

		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		obj.setID(Integer.parseInt(id));
		obj.setSTART_STOP_FLAG(param);
		int count = deployExampleService.StartAndStopByObj(obj);
		if (count != 0) {
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(count);
			// out.close();
			PrintWriterOut.printWirter(response, count);
		}
		return null;
		// theForm.reset(mapping, request);
		// return mapping.findForward("default");
	}

	/**
	 * @Title:更改部署状态
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String deployRequest() {
		// DeployExampleForm theForm = (DeployExampleForm) form;
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = "";
		String param = request.getParameter("paran");
		String operType = request.getParameter("operType");
		if (operType.equals("list")) {
			id = request.getParameter("ID");
		} else if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				id = treeObj.getBusiId();
			}
		}

		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		obj.setID(Integer.parseInt(id));
		TbBusiDeployExampleObj objtmp = new TbBusiDeployExampleObj();
		objtmp = deployExampleService.queryByObj(obj);
		TbBusiDeployExampleHisObj objhis = new TbBusiDeployExampleHisObj();
		try {
			BeanUtils.copyProperties(objhis, objtmp);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("1".equals(param) && objtmp.getDEPLOYESTARTTIME() != null
				&& "2".equals(objtmp.getDEPLOY_FLAG())) {
			deployExampleHisService.insertByObj(objhis);// 添加到ExampleHis中
		}
		obj.setDEPLOY_FLAG(param);
		int ishave = deployExampleService.queryForListByObj(obj).size();
		int count = 0;
		if (ishave < 1) {
			count = deployExampleService.deployRequest(obj);
		}

		TbBusiDeployExampleObj depObj = new TbBusiDeployExampleObj();
		depObj.setID(Integer.parseInt(id));
		depObj = deployExampleService.queryByObj(depObj);
		JSONObject jo = new JSONObject();
		if (count > 0) {
			jo.put("result", 1);
		} else {
			jo.put("result", -1);
		}
		jo.put("name", depObj.getIP());
		response.setContentType("text/html; charset=UTF-8");
		// out = response.getWriter();
		// out.print(jo);
		// out.close();
		PrintWriterOut.printWirter(response, jo);

		// theForm.reset(mapping, request);
		// return mapping.findForward("default");
		return null;
	}

	/*
	 * 通过jquary获取部署状态信息
	 */
	public String DeployFlagStatus() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String operType = request.getParameter("operType");
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (operType.equals("list")) {
			String id = request.getParameter("ID");
			obj.setID(Integer.parseInt(id));
		} else if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				obj.setID(Integer.parseInt(treeObj.getBusiId()));
			}
		}
		TbBusiDeployExampleObj tempObj = deployExampleService.queryByObj(obj);
		if (tempObj != null) {
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(tempObj.getDEPLOY_FLAG().toString());
			// out.close();
			PrintWriterOut.printWirter(response, tempObj.getDEPLOY_FLAG().toString());
		}
		return null;
	}

	/**
	 * @Title:根据不同的应用加载应用信息
	 * @Copyright: Copyright (c) 2012-03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public String selectAppRequest() {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		String appid = request.getParameter("APPID");
		tbSysAppObj.setID(Integer.valueOf(appid));

		tbSysAppObj = appService.queryByObj(tbSysAppObj);

		if (tbSysAppObj.getID() > 0) {
			theForm.setAPPID(tbSysAppObj.getID());
		}
		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		if (tbSysAppObj.getSTRATEGY() > 0) {
			tbBusiHostObj.setID(tbSysAppObj.getSTRATEGY());
		}
		JSONObject jo = new JSONObject();
		jo.put("result", "1");
		jo.put("standardHostIP", busiHostService.queryByObj(tbBusiHostObj).getIP());
		jo.put("standardPath", tbSysAppObj.getBASEPATH());
		jo.put("deployPath", tbSysAppObj.getDEPLOYPATH().replace(",", ",\r\n"));
		jo.put("startsh", tbSysAppObj.getSTARTSHELL());
		jo.put("stopsh", tbSysAppObj.getSTOPSHELL());

		try {
			JSONUtil.printJSON(jo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * @Title:查询可用的IP地址
	 * @Copyright: Copyright (c) 2012-03-16
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String queryAppIp() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		int APPID = Integer.parseInt(request.getParameter("APPID"));
		String standardHostIP = request.getParameter("standardHostIP");
		// String operType = request.getParameter("operType");
		// String ID = request.getParameter("ID");

		// TbSysAppObj appObj = new TbSysAppObj();
		// List appList = appService.queryForListByObj(appObj);
		// theForm.setAppList(appList);

		// deployObj.setPagination(this.getPaginater().initPagination(request));//
		// 分页
		TbBusiHostObj obj = new TbBusiHostObj();
		if (APPID > 0) {
			obj.setAPPID(APPID);
		}
		List hostList = busiHostService.queryHostMinusExampleByObjAPPID(obj);
		JSONArray ja = new JSONArray();
		for (Object object : hostList) {
			TbBusiHostObj tbBusiHostObj = (TbBusiHostObj) object;
			if (!tbBusiHostObj.getVLANIP().equals(standardHostIP)) {
				JSONObject jo = new JSONObject();
				jo.put("ID", tbBusiHostObj.getID());
				jo.put("IP", tbBusiHostObj.getIP());
				jo.put("VLANIP", tbBusiHostObj.getVLANIP());
				jo.put("HOSTUSERNAME", tbBusiHostObj.getHOSTUSERNAME());
				ja.add(jo);
			}
		}
		// theForm.setHostList(hostList);
		// request.setAttribute("operType", operType);
		// request.setAttribute("ID", ID);
		// return "queryAppIp";
		try {
			JSONUtil.printJSON(ja);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:查看所选应用对应的应用信息
	 * @Copyright: Copyright (c) 201207
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 * @throws NoSuchFieldException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public DeployExampleForm AppInfo(DeployExampleForm theForm, TbSysAppObj obj)
			throws SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, NoSuchFieldException {

		TbSysAppObj tbSysAppObj = appService.queryByObj(obj);

		if (tbSysAppObj.getID() > 0) {
			theForm.setAPPID(tbSysAppObj.getID());
		}
		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		if (tbSysAppObj.getSTRATEGY() > 0) {
			tbBusiHostObj.setID(tbSysAppObj.getSTRATEGY());
		}
		theForm.setStandardHostIP(busiHostService.queryByObj(tbBusiHostObj).getIP());
		theForm.setStandardPath(tbSysAppObj.getBASEPATH());
		theForm.setDeployPath(tbSysAppObj.getDEPLOYPATH().replace(",", ",\r\n"));
		theForm.setStartsh(tbSysAppObj.getSTARTSHELL());
		theForm.setStopsh(tbSysAppObj.getSTOPSHELL());
		// theForm.setTYPE_NAME(tempObj.getTYPE_NAME());
		// theForm.setTEM_DESC(tempObj.getTEM_DESC());
		// theForm = getForm(
		// "com.sitech.basd.rsmu.web.deploy.form.DeployExampleForm",
		// theForm, map, kvmap);

		return theForm;
	}

	/**
	 * 
	 * @Title:得到启动日志信息
	 * @Copyright: Copyright (c) Jan 16, 2013
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String getLogsInfo() {

		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("ip");// 主机IP
		String name = request.getParameter("name");// 主机用户
		String path = null;
		// String auto = request.getParameter("auto");
		// String line = request.getParameter("line");

		// String keyword = request.getParameter("keyword");
		// if (keyword != null && keyword.equals("")) {
		// keyword = null;// 如果前台传入keyword 为空字符串，keyword置为null
		// }
		// 查询用户名密码
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String hostId = request.getParameter("hostId");
		String appId = request.getParameter("appId");
		obj.setHOSTID(Integer.parseInt(hostId));
		obj.setAPPID(appId);
		obj = busiHostConfigService.queryByObj(obj);
		String pwd = obj.getSshPwd();
		Integer port  = obj.getHOSTPORT();
		if(port==null || port==0){
			port=22;
		}
		
		String ajax_path = request.getParameter("ajax_path");
		if (ajax_path != null && !"".equals(ajax_path)) {
			path = ajax_path;// 路径
		}
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn = new SshConnection(ip, port, name, pwd);
			String shell = null;
			// if (keyword != null && !"".equals(keyword)) {
			// if (keyword.indexOf("\'") != -1) {
			// shell = "grep " + keyword.trim() + " " + path;
			// } else {
			// shell = "grep -in \"" + keyword + "\" " + path;
			// }
			// } else {
			//
			// String tailLine = null;
			// if (line == null || line.trim().equals("")) {
			// tailLine = "500";// 默认读取最后500行数据
			// } else {
			// tailLine = line;
			// }
			// shell = "tail -n " + tailLine + " " + path;
			// }
			shell = "ls -l " + path;
			List<String> list = ssh.executeCommand(conn, shell);
			StringBuffer content = new StringBuffer();
			List<String> logMenu = new ArrayList<String>();
			if (list != null && list.size() > 0) {
				String fileName = list.get(0);
				if (fileName.indexOf(path.trim()) != -1) {
					if (fileName.charAt(0) == '-') {// 查看的是文件
						shell = "tail -n 500 " + path;// 默认查询后500行的数据
						List<String> logInfoList = ssh.executeCommand(conn, shell);
						for (String logInfo : logInfoList) {
							content.append(logInfo);
							content.append("\r\n");
						}
					}
				} else {// 点击查看的为文件夹，每行显示一个文件
					shell = "ls -1 " + path;
					List<String> logList = ssh.executeCommand(conn, shell);
					logMenu.add(path.trim());
					for (String log : logList) {
						logMenu.add(log.trim());
					}
				}
			}
			JSONObject json = new JSONObject();
			json.put("logMenu", JSONArray.fromObject(logMenu));
			json.put("content", content.toString());
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json.toString());
			p.close();
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		// request.setAttribute("auto", auto);
		// if (line != null && line.equals("")) {
		// line = null; // 如果前台传入空字符串，将line的置为null
		// }
		// request.setAttribute("line", line);
		// request.setAttribute("keyword", keyword);
		// request.setAttribute("name", name);
		// request.setAttribute("hostId", hostId);
		// request.setAttribute("appId", appId);
		return null;
	}

	/**
	 * @Title:进入查看日志界面
	 * @author duangh
	 */
	public String getStartLogInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 查询配置的日志路径
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String hostId = request.getParameter("hostId");
		String appId = request.getParameter("appId");
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		obj.setHOSTID(Integer.parseInt(hostId));
		obj.setAPPID(appId);
		obj = busiHostConfigService.queryByObj(obj);
		String log_path = obj.getLogPath();
		List<String> resultList = new ArrayList<String>();
		if (log_path != null && log_path.indexOf(",") != -1) {
			String[] paths = log_path.split(",");
			for (String logpath : paths) {
				resultList.add(logpath);
			}
		} else {
			resultList.add(log_path);
		}
		request.setAttribute("resultList", resultList);
		request.setAttribute("hostId", hostId);
		request.setAttribute("appId", appId);
		request.setAttribute("ip", ip);
		request.setAttribute("name", name);
		return "logInfo";
	}

	/**
	 * @Title:返回上一级目录
	 * @author duangh
	 */
	public String goBackPath() {
		HttpServletRequest request = Struts2Utils.getRequest();
		// 查询配置的日志路径
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String hostId = request.getParameter("hostId");
		String appId = request.getParameter("appId");
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		obj.setHOSTID(Integer.parseInt(hostId));
		obj.setAPPID(appId);
		obj = busiHostConfigService.queryByObj(obj);
		String log_path = obj.getLogPath();
		String pwd = obj.getSshPwd();
		Integer port = obj.getHOSTPORT();
		if(port==null || port==0){
			port = 22;
		}
		List<String> resultList = new ArrayList<String>();
		if (log_path != null && log_path.indexOf(",") != -1) {
			String[] paths = log_path.split(",");
			for (String logpath : paths) {
				resultList.add(logpath.trim());
			}
		} else {
			resultList.add(log_path);
		}
		String path = request.getParameter("path");// 此为当前目录
		String parent = path.substring(0, path.lastIndexOf("/"));
		String rootDirectory = "0";// 0代表不是配置的根目录，1为配置的根目录
		try {
			List<String> list = null;
			if (resultList.contains(path)) {// 配置的路径包含上级目录，即已经回到配置的主目录
				list = resultList;
				rootDirectory = "1";
			} else {
				SshResourceFactory ssh = SshResourceFactory.getInstance();
				SshConnection conn = new SshConnection(ip, port, name, pwd);
				String shell = "ls -1 " + parent;
				list = ssh.executeCommand(conn, shell);
				list.add(0, parent);
			}
			JSONObject json = new JSONObject();
			json.put("logMenu", JSONArray.fromObject(list));
			json.put("dir", rootDirectory);
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json.toString());
			p.close();
		} catch (Exception e) {
			Log.error("error:goback parent directory error:" + e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title:得到配置文件的信息
	 * @Copyright: Copyright (c) Jan 16, 2013
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String getConfigFileInfo() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		request.setAttribute("ip", ip);
		request.setAttribute("name", name);
		request.setAttribute("pwd", pwd);
		/**
		 * 把配置记录数据库删除
		 */
		DeployConfigFile obj = new DeployConfigFile();
		obj.setIP(ip);
		List lists = deployConfigFileServerImpl.queryForListByObj(obj);
		theForm.setResultList(lists);
		return "getConfigFileInfo";
	}

	/**
	 * 打开配置文件
	 * 
	 * @Title:
	 * @Copyright: Copyright (c) 2013-1-24
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String operConfigFile() {
		int flag = 1; // 执行成功，没有抛出异常
		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String path = request.getParameter("path");
		String port = request.getParameter("port");
		if(port==null || "0".equals(port)){
			port = "22";
		}
		// 处理密码
		pwd = this.dealPwd(pwd);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		String exception = "";
		String content = "";
		try {
			SshConnection conn = new SshConnection(ip, Integer.parseInt(port), name, pwd);
			ssh.downFileFromRemoteToStream(conn, path, bos);
			content = bos.toString();
		} catch (Exception e2) {
			flag = 0;
			e2.printStackTrace();
			exception = e2.getMessage();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (content != null && !"".equals(content)) {
			Pattern p = Pattern.compile("\n");
			Matcher m = p.matcher(content);
			content = m.replaceAll("\r");
		} else {
			content = exception;
		}
		try {
			JSONObject json = new JSONObject();
			json.put("content", content);
			json.put("flag", flag);
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json.toString());
			p.flush();
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: dealPwd
	 * @Description: 密码处理类，将空格换成“+”后解密
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-12-27 下午4:26:22
	 */
	private String dealPwd(String pwd) {
		try {
			pwd = DoubleEncryptUtils.decrypt(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}

	/**
	 * 
	 * @Title:
	 * @Copyright: Copyright (c) 2013-1-25
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public String saveConfigFile() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("ip");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String path = request.getParameter("path");
		String content = request.getParameter("Content");
		String port = request.getParameter("port");
		if(port==null || "0".equals(port)){
			port = "22";
		}
		pwd = this.dealPwd(pwd);
		String result = "1";
		if (content != null) {
			Pattern p = Pattern.compile("\r");
			Matcher m = p.matcher(content);
			content = m.replaceAll("\n");
		}
		byte[] con = content.getBytes();
		ByteArrayInputStream bos = new ByteArrayInputStream(con);
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn = new SshConnection(ip, Integer.parseInt(port), name, pwd);
			ssh.uploadFileToRemoteToStream(conn, path, bos);
			/**
			 * 把配置记录写入数据库
			 */
			DeployConfigFile obj = new DeployConfigFile();
			obj.setIP(ip);
			obj.setPATH(path);
			List list = deployConfigFileServerImpl.queryForListByObj(obj);
			if (list == null || list.size() == 0) {
				deployConfigFileServerImpl.insertByObj(obj);
				result = "2";
			}
		} catch (Exception e2) {
			result = "-1";
			e2.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			String json = "{\"result\":\"" + result + "\"}";
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除配置文件修改的历史记录
	 * 
	 * @return
	 */
	public String deleteConfigFileHis() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("ip");
		String path = request.getParameter("path");
		String result = "-1";
		try {
			/**
			 * 把配置记录数据库删除
			 */
			DeployConfigFile obj = new DeployConfigFile();
			obj.setIP(ip);
			obj.setPATH(path);
			int ret = deployConfigFileServerImpl.deleteByObj(obj);
			if (ret == -1) {
				result = "-1";
			} else {
				result = "1";
			}
		} catch (Exception e2) {
			result = "-1";
			e2.printStackTrace();
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			String json = "{\"result\":\"" + result + "\"}";
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title 进入部署升级页面
	 * @author duangh
	 * @return String
	 */
	public String upgradeDeployExample() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		String dialogName = request.getParameter("dialogName") == null ? "" : request
				.getParameter("dialogName");
		String idStr = "";
		if (operType.equals("list")) {
			idStr = Struts2Utils.getParameter("ids");
		} else if (operType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				idStr = treeObj.getBusiId();
			}
		}
		String[] ids = idStr.split(",");
		List<TbBusiHostObj> hostList = new ArrayList<TbBusiHostObj>();
		int app_id = 0;
		for (String id : ids) {
			TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
			obj.setID(Integer.parseInt(id));
			TbBusiDeployExampleObj tempObj = deployExampleService.queryByObj(obj);
			app_id = tempObj.getAPPID();
			TbBusiHostObj hostobj = new TbBusiHostObj();
			hostobj.setID(tempObj.getHOSTID());
			hostobj = busiHostService.queryByObj(hostobj);
			if (null != hostobj && !"".equals(hostobj.getVLAN())) {
				tempObj.setVLAN(hostobj.getVLAN());
			}
			hostobj.setAPPID(Integer.parseInt(id));// 要升级的应用的id
			hostList.add(hostobj);
		}
		TbSysAppObj appObj = new TbSysAppObj();
		appObj.setID(app_id);
		TbSysAppObj tbSysAppObj = appService.queryByObj(appObj);

		theForm.setHostList(hostList);
		theForm.setAPPID(tbSysAppObj.getID());
		theForm.setStandardPath(tbSysAppObj.getBASEPATH());
		// theForm.setDeployPath(tbSysAppObj.getDEPLOYPATH().replace(",",
		// ",\r\n"));
		String onlinePath = "";
		if (tbSysAppObj.getOnlinePath() != null) {
			onlinePath = tbSysAppObj.getOnlinePath();
		}
		theForm.setOnlinePath(onlinePath.replace(",", ",\r\n"));
		theForm.setStartsh(tbSysAppObj.getSTARTSHELL());
		theForm.setStopsh(tbSysAppObj.getSTOPSHELL());
		theForm.setAPPNAME(tbSysAppObj.getAPPNAME());
		theForm.setVersionDesc(tbSysAppObj.getVersionDesc());

		request.setAttribute("operType", operType);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("dialogName", dialogName);

		TbSysAppObj apobj = new TbSysAppObj();
		List appList = appService.queryForListByObj(apobj);
		theForm.setAppList(appList);
		return "upgrade";
	}

	/**
	 * 
	 * @Title: startUpgrade
	 * @Description: 修改升级实例状态
	 * @param
	 * @return String
	 * @throws
	 * @author duangh update by huojla
	 * @version 1.0
	 * @createtime 2013-3-30 下午12:24:04
	 */
	public String startUpgrade() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		if (operType.equals("tree")) {
			theForm.setAPPID(Integer.parseInt(request.getParameter("APPID")));
			theForm.setIsbackup(Integer.parseInt(request.getParameter("isbackup")));
			theForm.setIsrestart(Integer.parseInt(request.getParameter("isrestart")));
			theForm.setOnlinePath(request.getParameter("onlinePath"));
			theForm.setVersionDesc(request.getParameter("versionDesc"));
		}
		int app_id = theForm.getAPPID();// 基准应用id
		// 是否重启
		int isrestart = theForm.getIsrestart();
		// 是否全量备份
		int isbackup = theForm.getIsbackup();
		// 保存更改的部署路径
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		tbSysAppObj.setID(app_id);
		TbSysAppObj sysAppObj = appService.queryByObj(tbSysAppObj);
		// sysAppObj.setDEPLOYPATH(theForm.getDeployPath().replace("\r\n", ""));
		sysAppObj.setOnlinePath(theForm.getOnlinePath().replace("\r\n", ""));
		sysAppObj.setVersionDesc(theForm.getVersionDesc());
		appService.updateOnlinePath(sysAppObj);

		// 先捕获最新镜像
		TbBusiDoTaskObj taskObj = new TbBusiDoTaskObj();
		taskObj.setEXA_ID(app_id);
		taskObj.setEXA_TYPE(9); // 9代表捕获镜像
		taskObj.setDO_FLAG(0);
		// 暂时未添加该功能
		// taskService.insertCatchImageTask(taskObj);// 插入任务表

		// 捕获镜像后将部署状态改为正在部署
		String ids = request.getParameter("ids");
		if (ids != null) {
			String[] id = ids.split(",");
			for (String deploy_id : id) {
				TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
				obj.setID(Integer.parseInt(deploy_id));
				obj.setDEPLOY_PERCENT("等待上线！");
				obj.setDEPLOY_FLAG("5");// 将部署状态置为正在升级
				obj.setEXECUTE_FLAG("0");
				deployExampleService.deployRequest(obj);
				obj.setIsrestart(isrestart);
				deployExampleService.updateExampleIsRestart(obj);
				obj.setIsbackup(isbackup);
				deployExampleService.updateExampleIsBackup(obj);

				TbCloud3OnlineHistoryVO hisVo = new TbCloud3OnlineHistoryVO();
				hisVo.setExampleId(Integer.valueOf(deploy_id));
				hisVo.setIsRollback("0");
				hisVo.setIsSuccess("0");
				hisVo.setOnlinePath(theForm.getOnlinePath().replace("\r\n", ""));
				onlineHistoryService.insertByObj(hisVo);
			}
		}

		if (operType.equals("tree")) {
			JSONObject jo = new JSONObject();
			jo.put("result", 1);
			try {
				JSONUtil.printJSON(jo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		return REDIRECT;
	}

	/**
	 * @Tittle 判断选择的应用是否为同一基准应用部署
	 * @return null
	 */
	public String sameBaseApp() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String param = request.getParameter("ids");
		int result = 1;
		if (param != null) {
			String[] params = param.split(",");
			int app_id;
			TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
			obj.setID(Integer.parseInt(params[0]));
			obj = deployExampleService.queryByObj(obj);
			app_id = obj.getAPPID();
			for (int i = 1; i < params.length; i++) {
				TbBusiDeployExampleObj tempObj = new TbBusiDeployExampleObj();
				tempObj.setID(Integer.parseInt(params[1]));
				tempObj = deployExampleService.queryByObj(tempObj);
				int temp_id = tempObj.getAPPID();
				if (temp_id != app_id) {
					result = -1;
					break;
				}
			}
		}
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			String json = "{\"result\":\"" + result + "\"}";
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到部署路径
	 */
	public String getDeployPath() {

		try {
			TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
			HttpServletRequest request = Struts2Utils.getRequest();
			String id = request.getParameter("id");
			obj.setID(Integer.parseInt(id));
			obj = this.deployExampleService.queryByObj(obj);
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print("{\"path\":\"" + obj.getDEPLOYPATH() + "\"}");
			p.close();
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @Title: listExamplesInTree
	 * @Description: 在应用部署树中展示部署实例列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 14, 2013 10:22:50 AM
	 */
	public String listExamplesInTree() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("app_id");
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (app_id == null || "".equals(app_id)) {
			app_id = String.valueOf(theForm.getAPPID());
		}
		obj.setAPPID(Integer.parseInt(app_id));
		// 用于根据实际需要展示不同的页面
		String userType = request.getParameter("userType");
		request.setAttribute("userType", userType);

		// 分页
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		List<TbBusiDeployExampleObj> list = deployExampleService.queryForListByObj(obj);
		for (TbBusiDeployExampleObj tbBusiDeployExampleObj : list) {
			String deployTime = "";
			if (tbBusiDeployExampleObj.getDEPLOYESTARTTIME() == null
					|| tbBusiDeployExampleObj.getDEPLOYESTARTTIME().equals("")) {
				deployTime += "未部署";
			} else {
				deployTime += tbBusiDeployExampleObj.getDEPLOYESTARTTIME();
				if (tbBusiDeployExampleObj.getDEPLOYEENDTIME() == null
						|| tbBusiDeployExampleObj.getDEPLOYEENDTIME().equals("")) {
					deployTime += "-- 未结束";
				} else {
					deployTime += "-- " + tbBusiDeployExampleObj.getDEPLOYEENDTIME();
				}
			}
			tbBusiDeployExampleObj.setDeployTime(deployTime);

			// 查询进程

			ProcessObj processObj = new ProcessObj();
			String example_id = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
					tbBusiDeployExampleObj.getID());
			processObj.setEXAMPLE_ID(example_id);
			List<ProcessObj> proList = processService.queryForDeployList(processObj);
			// 服务状态：0：停止，1：运行，2：异常
			if (proList != null && proList.size() > 0) {
				boolean isStop = true;
				boolean isStart = true;
				for (int i = 0; i < proList.size(); i++) {
					if (proList.get(i).getISRUNNING() != 0) {
						isStop = false;
						break;
					}
				}
				if (!isStop) {
					for (ProcessObj processObj2 : proList) {
						if (processObj2.getPROCESS_STATE() != 2) {
							isStart = false;
							break;
						}
					}
					if (isStart) {
						tbBusiDeployExampleObj.setServeState("1");
					} else {
						tbBusiDeployExampleObj.setServeState("2");
					}
				} else {
					tbBusiDeployExampleObj.setServeState("0");
				}

			} else {
				tbBusiDeployExampleObj.setServeState("0");
			}
		}
		theForm.setAPPID(Integer.parseInt(app_id));
		theForm.setResultList(list);
		return "treeList";
	}

	public String listExamplesInTreeByTaskId() {
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("app_id");
		String task_id = request.getParameter("task_id");
		// 用于根据实际需要展示不同的页面
		String userType = request.getParameter("userType");
		request.setAttribute("userType", userType);
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (app_id == null || "".equals(app_id)) {
			app_id = String.valueOf(theForm.getAPPID());
		}
		if (task_id == null || "".equals(task_id)) {
			task_id = theForm.getTASK_ID();
		}
		obj.setAPPID(Integer.parseInt(app_id));
		obj.setTASK_ID(task_id);

		// 分页
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		List<TbBusiDeployExampleObj> list = deployExampleService.queryByObjJoinTaskList(obj);
		theForm.setAPPID(Integer.parseInt(app_id));
		theForm.setTASK_ID(task_id);
		theForm.setResultList(list);
		return "treeList";
	}

	public String validateAppVisitPath() {
		JSONObject jo = new JSONObject();
		String appVisitPath = request.getParameter("appVisitPath");
		String id = request.getParameter("id");
		TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
		configObj.setAppPath(appVisitPath);
		List<TbBusiHostConfigObj> configLst = busiHostConfigService.queryForListByObj(configObj);
		if (configLst != null && configLst.size() > 0) {
			TbBusiDeployExampleObj exampleObj = new TbBusiDeployExampleObj();
			exampleObj.setID(Integer.parseInt(id));
			exampleObj = deployExampleService.queryByObj(exampleObj);
			boolean match = false;
			for (TbBusiHostConfigObj tbBusiHostConfigObj : configLst) {
				if ((exampleObj.getHOST_CONFIG_ID()).equals(tbBusiHostConfigObj.getHOSTCONFIGID())) {
					jo.put("appVisitPathEnabled", true);
					match = true;
					break;
				}
			}
			if (!match) {
				jo.put("appVisitPathEnabled", false);
			}
		} else {
			jo.put("appVisitPathEnabled", true);
		}
		try {
			JSONUtil.printJSON(jo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String validateDeployPath() {
		JSONObject jo = new JSONObject();
		if (theForm == null) {
			theForm = new DeployExampleForm();
		}
		TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
		configObj.setHOSTID(theForm.getHOSTID());
		configObj.setUSER_MANAGE_ID(theForm.getHOSEUSERID());
		List<TbBusiHostConfigObj> configLst = busiHostConfigService.queryForListByObj(configObj);
		TbBusiDeployExampleObj exampleObj = new TbBusiDeployExampleObj();
		int id = theForm.getID();
		if (id != 0) {
			exampleObj.setID(id);
			exampleObj = deployExampleService.queryByObj(exampleObj);
		}
		if (configLst != null && configLst.size() > 0) {
			Boolean match = false;
			for (TbBusiHostConfigObj tbBusiHostConfigObj : configLst) {
				if (tbBusiHostConfigObj.getBase_path().equals(theForm.getDeployPath())) {
					if (id != 0
							&& exampleObj.getHOST_CONFIG_ID().equals(
									tbBusiHostConfigObj.getHOSTCONFIGID())) {
						jo.put("examplePathEnabled", true);
						match = true;
						break;
					} else {
						jo.put("examplePathEnabled", false);
						match = true;
						break;
					}
				}
			}
			if (!match) {
				jo.put("examplePathEnabled", true);
			}
		} else {
			jo.put("examplePathEnabled", true);
		}
		try {
			JSONUtil.printJSON(jo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: akeyStartAndStop
	 * @Description: 一键启停
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-25 上午10:14:22
	 */
	public String akeyStartAndStop() {
		// String appId = request.getParameter("entityId");
		// TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		// obj.setAPPID(Integer.parseInt(appId));
		// obj.setDATAAUTHORITY((String) session.get("datau"));
		// resultList = deployExampleService.queryForListByObj(obj);
		if (startAndStopObj != null) {
			startAndStopObj = new StartAndStopObj();
		}
		return "akeyStartAndStop";
	}

	/**
	 * 
	 * @Title: startAndStopApp
	 * @Description: 启停应用
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-10-26 下午4:18:56
	 */
	public String startAndStopApp() {
		if (startAndStopObj == null) {
			startAndStopObj = new StartAndStopObj();
		}
		int port = 22;
		String[] scriptPathArr = startAndStopObj.getAloneScriptPath().split(", ");
		String[] userIdArr = startAndStopObj.getAloneUserId().split(", ");
		String[] pwdArr = startAndStopObj.getAlonePassword().split(", ");
		String[] deployIpArr = startAndStopObj.getAloneDeployeIp().split(", ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < deployIpArr.length; i++) {
			String username = null;
			String pwd = null;
			if (startAndStopObj.getSelectUsee().equals("1")) {
				username = startAndStopObj.getCommonUseId();
				pwd = startAndStopObj.getCommonPassword();
			} else {
				username = userIdArr[i];
				pwd = pwdArr[i];
			}
			com.sitech.utils.ssh.SshConnection sshConnection = null;
			try {
				sshConnection = SSHUtil.getSshConnectionThrowException(deployIpArr[i], port,
						username, pwd);
			} catch (SSHException e) {
				String mess = e.getMessage();
				if (mess.endsWith("All parameters must be at least one character.")) {
					sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：参数异常；\n");
					continue;
				} else if (mess.endsWith("Invalid username or password.")) {
					sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：用户名、密码错误；\n");
					continue;
				} else if (mess.endsWith("Unknown host.")) {
					sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：无法连接主机；\n");
					continue;
				} else if (mess.endsWith("Unable to connect to host.")) {
					sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：无法连接主机；\n");
					continue;
				} else {
					sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：连接异常；\n");
					continue;
				}
			} catch (Exception e) {
				sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：连接异常；\n");
				continue;
			}
			String shellCmd = null;
			if (startAndStopObj.getSelectScript().equals("1")) {
				shellCmd = "sh " + startAndStopObj.getCommonScriptPath();
			} else {
				shellCmd = "sh " + scriptPathArr[i];
			}
			List<String> lst = new ArrayList<String>();
			try {
				lst = SSHUtil.sendShellToSSHChannelReMess(sshConnection, true, shellCmd);
			} catch (Exception e) {
				sb.append("主机：" + deployIpArr[i] + "操作失败，可能原因：执行异常；\n");
				continue;
			}
			sb.append("主机：" + deployIpArr[i] + "操作成功；\n");
		}
		JSONObject jo = new JSONObject();
		jo.put("result", sb.toString());
		printJson(jo.toString());
		return null;
	}

	private synchronized void printJson(String param) {
		response.setCharacterEncoding("UTF-8");
		// out = response.getWriter();
		if (param != null) {
			// out.println(param);
			PrintWriterOut.printWirter(response, param);
		}
		// out.close();
	}
}
