package com.sitech.basd.yicloud.web.busicenter.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.component.domain.process.ProcessObj;
import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.process.ProcessService;
import com.sitech.basd.component.service.script.ScriptsService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.tree.service.relation.ExampleRelationService;
import com.sitech.basd.sxcloud.cloud.domain.appmessage.TbCloudAppInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.service.appmessage.TbCloudAppInfoService;
import com.sitech.basd.sxcloud.cloud.service.bizsystem.BizSystemService;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployAlarmObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployAlarmService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.SoftwareInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busitree.BusiTreeObj;
import com.sitech.basd.yicloud.domain.database.AppDatabaseObj;
import com.sitech.basd.yicloud.domain.entitytree.TreeObj;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.basd.yicloud.service.busitree.BusiTreeService;
import com.sitech.basd.yicloud.service.database.AppDatabaseService;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.basd.yicloud.web.busicenter.form.BusiTreeForm;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SshPingIp;

/**
 * 
 * <p>
 * Title: BusiTreeAction
 * </p>
 * <p>
 * Description: 业务中心管理Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author duangh
 * @version 1.0
 * @createtime Apr 18, 2012 11:05:32 AM
 * 
 */
public class BusiTreeAction extends CRUDBaseAction {
	private BusiTreeService busiTreeService;
	private SoftwareInfoService softwareInfoService;
	private LeaderViewBusinessService leaderViewBusinessService;
	private TbCloudAppInfoService tbCloudAppInfoService;
	private BusiHostService busiHostService;
	private DeployExampleService deployExampleService;
	private BusiTreeForm theForm;
	private AppService appService;
	private TbBusiSysTreeService tbBusiSysTreeService;
	private BizSystemService bizSystemService;
	private AppDatabaseService appDatabaseService;
	private BusiHostConfigService busiHostConfigService;
	private ProcessService processService;
	private ScriptsService scriptsService;
	private ExampleRelationService exampleRelationService;
	private DeployAlarmService deployAlarmService;
	private UserService userService;

	private Boolean nameEnabled;

	public Boolean getNameEnabled() {
		return nameEnabled;
	}

	public void setNameEnabled(Boolean nameEnabled) {
		this.nameEnabled = nameEnabled;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDeployAlarmService(DeployAlarmService deployAlarmService) {
		this.deployAlarmService = deployAlarmService;
	}

	public void setBusiHostConfigService(BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	public void setScriptsService(ScriptsService scriptsService) {
		this.scriptsService = scriptsService;
	}

	public void setExampleRelationService(ExampleRelationService exampleRelationService) {
		this.exampleRelationService = exampleRelationService;
	}

	public void setAppDatabaseService(AppDatabaseService appDatabaseService) {
		this.appDatabaseService = appDatabaseService;
	}

	public void setBizSystemService(BizSystemService bizSystemService) {
		this.bizSystemService = bizSystemService;
	}

	public void setTbBusiSysTreeService(TbBusiSysTreeService tbBusiSysTreeService) {
		this.tbBusiSysTreeService = tbBusiSysTreeService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setTbCloudAppInfoService(TbCloudAppInfoService tbCloudAppInfoService) {
		this.tbCloudAppInfoService = tbCloudAppInfoService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setLeaderViewBusinessService(LeaderViewBusinessService leaderViewBusinessService) {
		this.leaderViewBusinessService = leaderViewBusinessService;
	}

	public void setBusiTreeService(BusiTreeService busiTreeService) {
		this.busiTreeService = busiTreeService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public BusiTreeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiTreeForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: listVMService
	 * @Description: 获取虚拟机主机树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String listBusiTree() throws Exception {
		return LIST;
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String asyncForTree() throws Exception {
		BusiTreeObj treeObj = new BusiTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		if (str == null || "".equals(str)) {
			treeObj.setType("1");
		} else {
			treeObj.setParentId(Integer.valueOf(str));
		}
		List<BusiTreeObj> resultList = busiTreeService.queryForTree(treeObj);
		List<TreeObj> list = new ArrayList<TreeObj>();
		if (list != null) {
			BusiTreeObj tempObj = new BusiTreeObj();
			for (BusiTreeObj obj : resultList) {
				TreeObj tObj = new TreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(obj.getType());
				tObj.setEntityId(obj.getBusiId());
				// 判断是不是父节点
				tempObj.setParentId(obj.getId());
				List<BusiTreeObj> lst = busiTreeService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);

				}
				// 设置图标
				if (obj.getType().equals("1")) {// 业务中心
					tObj.setIcon("sxcloud/images/virtual/Datacenter.png");
					tObj.setNocheck(true);
				}
				if (obj.getType().equals("2")) { // 业务系统
					tObj.setIcon("sxcloud/images/virtual/cluster.png");
					tObj.setNocheck(true);
				}
				if (obj.getType().equals("3")) { // 应用
					tObj.setIcon("sxcloud/images/virtual/Host.png");
					tObj.setNocheck(true);
				}
				if (obj.getType().equals("4")) { // 应用部署的主机
					tObj.setIcon("sxcloud/images/virtual/VirtualMachine.png");
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: busitabs
	 * @Description: 进入tab页
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String busitabs() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		request.setAttribute("type", type);
		request.setAttribute("id", id);
		return "busitabs";
	}

	/**
	 * 
	 * @Title: appInfo
	 * @Description: 点击应用查看摘要信息
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */

	public String appInfo() {
		String appid = Struts2Utils.getParameter("id");// 得到应用Id
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		tbSysAppObj.setID(Integer.valueOf(appid));
		try {
			cloneToForm(theForm, tbSysAppObj);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "appInfo";
	}

	/**
	 * 
	 * @Title: cloneToForm
	 * @Description: 把信息复制到Form中
	 * @param
	 * @return DeployExampleForm
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Feb 21, 2013 4:35:03 PM
	 */
	public BusiTreeForm cloneToForm(BusiTreeForm theForm, TbSysAppObj obj)
			throws SecurityException, IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, NoSuchFieldException {

		TbSysAppObj tbSysAppObj = appService.queryByObj(obj);

		TbBusiHostObj tbBusiHostObj = new TbBusiHostObj();
		if (tbSysAppObj.getSTRATEGY() > 0) {
			tbBusiHostObj.setID(tbSysAppObj.getSTRATEGY());
		}
		theForm.setStandardHostIP(busiHostService.queryByObj(tbBusiHostObj).getIP());
		theForm.setStandardPath(tbSysAppObj.getBASEPATH());
		theForm.setDeployPath(tbSysAppObj.getDEPLOYPATH().replace(",", ",\r\n"));
		theForm.setStartsh(tbSysAppObj.getSTARTSHELL());
		theForm.setStopsh(tbSysAppObj.getSTOPSHELL());
		theForm.setName(tbSysAppObj.getAPPNAME());
		return theForm;
	}

	/**
	 * 
	 * @Title: softwareInfo
	 * @Description: 点击应用时的软件包信息
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String softwareInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 得到业务系统的Id
		request.setAttribute("id", id);
		List<TbBusiSysTree> appLst = null;
		if (id != null && !"".equals(id)) {
			TbBusiSysTree sysObj = new TbBusiSysTree();
			sysObj.setParentId(id);
			sysObj.setType(2);
			appLst = tbBusiSysTreeService.queryForTree(sysObj);
		}
		String appIds = "";
		if (appLst != null && appLst.size() > 0) {
			for (int i = 0; i < appLst.size(); i++) {
				if (i != appLst.size() - 1) {
					appIds += appLst.get(i).getBusiId() + ",";
				} else {
					appIds += appLst.get(i).getBusiId();
				}
			}
		}
		TbBusiSoftwareInfoObj obj = new TbBusiSoftwareInfoObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setAPPIDS(appIds);
		List resultList = softwareInfoService.queryForListByObj(obj);
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		theForm.setResultList(resultList);
		return "softwareInfo";
	}

	/**
	 * 
	 * @Title: bizsysInfo
	 * @Description: 点击业务系统时的摘要信息
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String bizsysInfo() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		String id = Struts2Utils.getParameter("id");// 得到业务系统Id
		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setId(id);
		List lst = tbBusiSysTreeService.queryForTree(treeObj);
		BizSystemObj bizObj = new BizSystemObj();
		if (lst != null & lst.size() > 0) {
			String sysId = ((TbBusiSysTree) lst.get(0)).getBusiId();
			bizObj.setSYS_ID(sysId);
			bizObj = bizSystemService.queryByObj(bizObj);
		}
		if (bizObj != null) {
			theForm.setName(bizObj.getSYS_NAME());
			theForm.setStatus(String.valueOf(bizObj.getSTATU()));
			theForm.setDesc(bizObj.getSYS_DESC());
		}
		return "bizsysInfo";
	}

	/**
	 * 
	 * @Title: bizsysView
	 * @Description: 点击业务系统时查看应用信息
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String bizsysView() throws Exception {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = null;
		if (theForm.getId() == null) {
			id = request.getParameter("id");// 得到业务系统Id
		} else {
			id = theForm.getId();// 得到业务系统Id
		}
		theForm.setId(id);
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// ServletActionContext.getRequest()
		// .getSession().getAttribute(Constant.USER_SESSION_KEY);
		String dataAuthorityByUser = (String) session.get("datau");
		String appId = "";
		if (dataAuthorityByUser == null) {
			TbBusiSysTree queryObj = new TbBusiSysTree();
			queryObj.setType(2);
			queryObj.setParentId(id);
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(queryObj);
			if (appLst != null && appLst.size() > 0) {
				for (int i = 0; i < appLst.size(); i++) {
					if (i != appLst.size() - 1) {
						appId += appLst.get(i).getBusiId() + ",";
					} else {
						appId += appLst.get(i).getBusiId();
					}
				}
			}
		} else {
			String[] appIds = dataAuthorityByUser.split(",");
			TbBusiSysTree queryObj = new TbBusiSysTree();
			queryObj.setType(2);
			queryObj.setParentId(id);
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(queryObj);
			Map<String, String> map = new HashMap<String, String>();
			if (appLst != null && appLst.size() > 0) {
				for (TbBusiSysTree appObj : appLst) {
					map.put(appObj.getBusiId(), appObj.getBusiId());
				}
			}
			for (int i = 0; i < appIds.length; i++) {
				if (map.get(appIds[i]) != null) {
					if (i != appIds.length - 1) {
						appId += appIds[i] + ",";
					} else {
						appId += appIds[i];
					}
				}
			}
		}
		TbBusiSysTree sysObj = new TbBusiSysTree();
		sysObj.setType(1);
		sysObj.setId(id);
		List<TbBusiSysTree> sysLst = tbBusiSysTreeService.queryForTree(sysObj);
		if (sysLst != null && sysLst.size() > 0) {
			sysObj = sysLst.get(0);
			obj.setSYS_ID(sysObj.getBusiId());
		} else {
			obj.setSYS_ID("-1");
		}
		obj.setDATAAUTHORITY(appId);
		List<TbBusiDeployExampleObj> resultList = this.deployExampleService
				.queryForBizListByObj(obj);
		for (TbBusiDeployExampleObj tbBusiDeployExampleObj : resultList) {
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
		theForm.setResultList(resultList);

		return "bizsysView";
	}

	/**
	 * 
	 * @Title: addBizsysApp
	 * @Description: 添加应用
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String addBizsysApp() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		try {
			name = URLDecoder.decode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		return "addBizsysApp";
	}

	/**
	 * 
	 * @Title: addDeployApp
	 * @Description: 添加部署应用
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String addDeployApp() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 得到应用Id
		// 根据应用Id，查询基准应用Id
		TbCloudAppInfoObj appObj = new TbCloudAppInfoObj();
		appObj.setId(Integer.parseInt(id));
		List baseList = tbCloudAppInfoService.queryAppListBase(appObj);
		if (baseList != null && baseList.size() > 0) {
			appObj = (TbCloudAppInfoObj) baseList.get(0);
		}
		String baseId = appObj.getBase_id();// 得到基准应用Id
		String name = request.getParameter("name");// 得到应用名称
		// 查询可以部署的主机或虚拟机
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		TbBusiHostObj obj = new TbBusiHostObj();
		List hostList = busiHostService.queryForListByObj(obj);
		theForm.setResultList(hostList);
		request.setAttribute("id", baseId);
		request.setAttribute("name", name);
		return "addDeployApp";
	}

	/**
	 * 
	 * @Title: deployApp
	 * @Description: 部署应用
	 * @param
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Apr 18, 2012 11:42:00 AM
	 */
	public String deployApp() {
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		obj.setHOSTID(Integer.parseInt(theForm.getId()));// 主机Id
		obj.setAPPID(Integer.parseInt(theForm.getAppId()));// 基准引用Id
		int ret = deployExampleService.insertByObj(obj);// 插入数据,返回插入后的Id
		if (ret != -1) {
			obj.setID(ret);
			obj.setDEPLOY_FLAG("1");// 部署状态改为正在部署，后台处理
			int result = deployExampleService.deployRequest(obj);
		}
		return "deployApp";
	}

	/**
	 * 
	 * @Title: bizsysAppInfo
	 * @Description: 展示基准应用的信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 7, 2013 5:31:22 PM
	 */
	public String bizsysAppInfo() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		String id = Struts2Utils.getParameter("id");// 得到业务系统Id
		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setId(id);
		List lst = tbBusiSysTreeService.queryForTree(treeObj);
		TbSysAppObj appObj = new TbSysAppObj();
		if (lst != null & lst.size() > 0) {
			String sysId = ((TbBusiSysTree) lst.get(0)).getBusiId();
			appObj.setID(Integer.parseInt(sysId));
			appObj = appService.queryByObj(appObj);
		}
		if (appObj != null) {
			theForm.setName(appObj.getAPPNAME());
			TbBusiHostObj hostObj = new TbBusiHostObj();
			hostObj.setID(appObj.getSTRATEGY());
			hostObj = busiHostService.queryByObj(hostObj);
			if (hostObj != null) {
				theForm.setStandardHostIP(hostObj.getIP());
			} else {
				theForm.setStandardHostIP("");
			}
			theForm.setStandardPath(appObj.getBASEPATH());
			theForm.setDesc(appObj.getREMARK());
			theForm.setStatus(appObj.getSTATUS());
		}
		return "bizsysAppInfo";
	}

	/**
	 * 
	 * @Title: busiCenterInfo
	 * @Description: 业务中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 20, 2013 10:30:55 AM
	 */
	public String busiCenterInfo() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		TbBusiSysTree busiCenObj = new TbBusiSysTree();
		busiCenObj.setParentId(id);
		busiCenObj.setType(1);
		List<TbBusiSysTree> sysLst = tbBusiSysTreeService.queryForTree(busiCenObj);
		int busiAppNum = 0;
		int busiDepNum = 0;
		if (sysLst != null && sysLst.size() > 0) {
			theForm.setBusiSysNum(sysLst.size());
			for (TbBusiSysTree tbBusiSysObj : sysLst) {
				TbBusiSysTree busiSysObj = new TbBusiSysTree();
				busiSysObj.setParentId(tbBusiSysObj.getId());
				busiSysObj.setType(2);
				List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(busiSysObj);
				if (appLst != null && appLst.size() > 0) {
					busiAppNum += appLst.size();
					for (TbBusiSysTree tbBusiAppObj : appLst) {
						TbBusiSysTree busiAppObj = new TbBusiSysTree();
						busiAppObj.setParentId(tbBusiAppObj.getId());
						busiAppObj.setType(3);
						List<TbBusiSysTree> depLst = tbBusiSysTreeService.queryForTree(busiAppObj);
						if (depLst != null && depLst.size() > 0) {
							busiDepNum += depLst.size();
						}
					}
				}
			}
		} else {
			theForm.setBusiSysNum(0);
		}
		theForm.setBusiAppNum(busiAppNum);
		theForm.setBusiDepNum(busiDepNum);
		return "busiCenterInfo";
	}

	/**
	 * 
	 * @Title: listDataBase
	 * @Description: 获取连接数据库信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 9:39:45 AM
	 */
	public String listDataBase() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		AppDatabaseObj obj = new AppDatabaseObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List appDaLst = appDatabaseService.queryForListByObj(obj);
		if (appDaLst != null && appDaLst.size() > 0) {
			theForm.setResultList(appDaLst);
			request.setAttribute("size", appDaLst.size());
		} else {
			request.setAttribute("size", 0);
		}
		return "listDataBase";
	}

	/**
	 * 
	 * @Title: addDataBase
	 * @Description: 添加数据库
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 25, 2013 3:51:41 PM
	 */
	public String addDataBase() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		theForm.setFlag("add");
		return "addDataBase";
	}

	/**
	 * 
	 * @Title: modDataBase
	 * @Description: 修改数据库
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 25, 2013 4:20:58 PM
	 */
	public String modDataBase() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		String id = theForm.getId();
		AppDatabaseObj obj = new AppDatabaseObj();
		obj.setId(id);
		obj = appDatabaseService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		theForm.setFlag("mod");
		return "modDataBase";
	}

	/**
	 * 
	 * @Title: saveDataBase
	 * @Description: 保存数据
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 25, 2013 4:13:36 PM
	 */
	public String saveDataBase() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		String flag = theForm.getFlag();
		AppDatabaseObj obj = new AppDatabaseObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (flag.equals("add")) {
			String ret = appDatabaseService.insertByObj(obj);
		} else {
			int ret = appDatabaseService.updateByObj(obj);
		}
		return "saveDataBase";
	}

	public String delDataBase() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		String id = theForm.getId();
		AppDatabaseObj obj = new AppDatabaseObj();
		obj.setId(id);
		int ret = appDatabaseService.deleteByObj(obj);
		return "delDataBase";
	}

	/**
	 * 
	 * @Title: deployExampleInfo
	 * @Description: 展示部署实例的基本信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 21, 2013 4:14:00 PM
	 */
	public String deployExampleInfo() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id"); // 部署实例的id
		TbBusiSysTree depTreeObj = new TbBusiSysTree();
		depTreeObj.setId(id);
		depTreeObj.setType(3);
		List<TbBusiSysTree> depLst = tbBusiSysTreeService.queryForTree(depTreeObj);
		if (depLst != null && depLst.size() > 0) {
			depTreeObj = depLst.get(0);
		}
		TbBusiDeployExampleObj depExampleObj = new TbBusiDeployExampleObj();
		depExampleObj.setID(Integer.parseInt(depTreeObj.getBusiId()));
		depExampleObj = deployExampleService.queryByObj(depExampleObj);

		TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
		hostConfigObj.setHOSTCONFIGID(depExampleObj.getHOST_CONFIG_ID());
		hostConfigObj = busiHostConfigService.queryByObj(hostConfigObj);
		String scriptsName = "";
		String processName = "";
		// 查询进程
		ProcessObj processObj = new ProcessObj();
		String example_id = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
				depExampleObj.getID());
		processObj.setEXAMPLE_ID(example_id);
		List<ProcessObj> proList = processService.queryForDeployList(processObj);
		if (proList != null && proList.size() > 0) {
			for (int i = 0; i < proList.size(); i++) {
				if (i != proList.size() - 1) {
					processName += proList.get(i).getPROCESS() + ";";
				} else {
					processName += proList.get(i).getPROCESS();
				}
			}

			// 服务状态：0：停止，1：运行，2：异常
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
					theForm.setServeState("1");
				} else {
					theForm.setServeState("2");
				}
			} else {
				isStart = false;
				theForm.setServeState("0");
			}
		} else {
			theForm.setServeState("0");
		}

		// 查询脚本
		String example_change = DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
				depExampleObj.getID());
		ScriptObj obj = new ScriptObj();
		obj.setExample_id(example_change);
		List<ScriptObj> scriptLst = scriptsService.queryForDeployList(obj);
		for (int i = 0; i < scriptLst.size(); i++) {
			if (i != scriptLst.size() - 1) {
				scriptsName += scriptLst.get(i).getName() + ";";
			} else {
				scriptsName += scriptLst.get(i).getName();
			}
		}

		if (hostConfigObj != null) {
			UserObj userObj = new UserObj();
			userObj.setId(hostConfigObj.getUSER_MANAGE_ID());
			userObj = userService.queryUserObjById(userObj);
			if (userObj != null) {
				theForm.setHostUserName(userObj.getUsername());
				theForm.setDeployHostIp(userObj.getIp());
			} else {
				theForm.setHostUserName("");
				theForm.setDeployHostIp("");
			}
			theForm.setDeployPath(hostConfigObj.getBase_path());
		}
		if (depExampleObj != null) {
			theForm.setDeploy_flag(depExampleObj.getDEPLOY_FLAG() == null ? "" : depExampleObj
					.getDEPLOY_FLAG());
			theForm.setDeployeEndtime(depExampleObj.getDEPLOYEENDTIME() == null ? ""
					: depExampleObj.getDEPLOYEENDTIME());
			theForm.setStart_stop_flag(depExampleObj.getSTART_STOP_FLAG() == null ? ""
					: depExampleObj.getSTART_STOP_FLAG());
		} else {
			theForm.setDeploy_flag("");
			theForm.setDeployeEndtime("");
			theForm.setStart_stop_flag("");
			theForm.setDeployPath("");
		}
		theForm.setDeployExampleType(depExampleObj.getDEPLOYEXAMPLE_TYPE());
		theForm.setProcess(processName);
		theForm.setScript(scriptsName);
		// 设置部署实例名称
		theForm.setExampleName(depExampleObj.getExampleName());
		// 应用访问地址
		theForm.setAppAccessPath(hostConfigObj.getAppPath());
		theForm.setDeployPercent(depExampleObj.getDEPLOY_PERCENT());

		return "deployExampleInfo";
	}

	/**
	 * 
	 * @Title: gotoDeployAlarm
	 * @Description: 需要分页和提交时的中间封装页
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 24, 2013 11:07:38 AM
	 */
	public String gotoSkipPage() {
		return "gotoSkipPage";
	}

	/**
	 * 
	 * @Title: listDeployAlarm
	 * @Description: 展示部署异常
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 5:28:14 PM
	 */
	public String listDeployAlarm() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			String id = request.getParameter("id");
			if (id != null) {
				id = id.trim();
			}
			TbBusiSysTree sysTreeObj = new TbBusiSysTree();
			sysTreeObj.setParentId(id);
			sysTreeObj.setType(2);
			String appIds = "";
			List<TbBusiSysTree> lst = tbBusiSysTreeService.queryForTree(sysTreeObj);
			if (lst != null && lst.size() > 0) {
				for (int i = 0; i < lst.size(); i++) {
					if (i != lst.size() - 1) {
						appIds += lst.get(i).getBusiId() + ",";
					} else {
						appIds += lst.get(i).getBusiId();
					}
				}
			}
			TbBusiDeployAlarmObj obj = new TbBusiDeployAlarmObj();
			if (appIds != null && !appIds.equals("")) {
				obj.setAPPIDS(appIds);
			} else {
				obj.setAPPIDS("-1");
			}
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			List resultList = deployAlarmService.queryForListByObj(obj);
			if (resultList != null) {
				theForm.setResultList(resultList);
				request.setAttribute("size", resultList.size());
			} else {
				request.setAttribute("size", 0);
			}
			request.setAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		return "listDeployAlarm";
	}

	/**
	 * 
	 * @Title: listSysDeployHost
	 * @Description: 展示业务系统下部署主机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 24, 2013 3:28:54 PM
	 */
	public String listSysDeployHost() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			String id = null;
			if (theForm.getId() == null) {
				id = request.getParameter("id");// 得到业务系统Id
			} else {
				id = theForm.getId();// 得到业务系统Id
			}
			theForm.setId(id);
			TbBusiSysTree sysTreeObj = new TbBusiSysTree();
			sysTreeObj.setType(2);
			sysTreeObj.setParentId(id);
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(sysTreeObj);
			String hostIds = "";
			if (appLst != null && appLst.size() > 0) {
				for (int i = 0; i < appLst.size(); i++) {
					TbSysAppObj appObj = new TbSysAppObj();
					appObj.setID(Integer.parseInt(appLst.get(i).getBusiId()));
					appObj = appService.queryByObj(appObj);
					if (appObj != null) {
						hostIds += appObj.getSTRATEGY() + ",";
					}
					TbBusiSysTree appTreeObj = new TbBusiSysTree();
					appTreeObj.setType(3);
					appTreeObj.setParentId(appLst.get(i).getId());
					List<TbBusiSysTree> depLst = tbBusiSysTreeService.queryForTree(appTreeObj);
					if (depLst != null && depLst.size() > 0) {
						for (int j = 0; j < depLst.size(); j++) {
							TbBusiDeployExampleObj depObj = new TbBusiDeployExampleObj();
							depObj.setID(Integer.parseInt(depLst.get(j).getBusiId()));
							depObj = deployExampleService.queryByObj(depObj);
							if (depObj != null) {
								hostIds += depObj.getHOSTID() + ",";
							}
						}
					}
				}
			}
			TbBusiHostObj hostObj = new TbBusiHostObj();
			if (hostIds != null && hostIds.length() > 0) {
				hostIds = hostIds.substring(0, hostIds.length() - 1);
			}
			hostObj.setHOSTIDS(hostIds);
			hostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			List hostLst = busiHostService.queryForListByObj(hostObj);
			if (hostLst != null) {
				theForm.setResultList(hostLst);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return "listSysDeployHost";
	}

	/**
	 * 
	 * @Title: checkStatus
	 * @Description: 检测主机的运行状态
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 25, 2013 10:49:27 AM
	 */
	public String checkStatus() {
		try {
			String IP = request.getParameter("IP");
			String hostId = request.getParameter("hostId");
			Boolean status = SshPingIp.pingOtherServer(IP);
			TbBusiHostObj obj = new TbBusiHostObj();
			obj.setID(Integer.parseInt(hostId));
			JSONObject jo = new JSONObject();
			if (status) {
				jo.put("result", 1);
				obj.setWORKSTATUS(1);// 正在运行

			} else {
				jo.put("result", -1);
				obj.setWORKSTATUS(2);// 已关机
			}
			busiHostService.updateHostState(obj);
			JSONUtil.printJSON(jo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return null;
	}

	/**
	 * 
	 * @Title: listAppDeployHost
	 * @Description: 展示业务中心下部署主机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 25, 2013 2:24:45 PM
	 */
	public String listAppDeployHost() {
		if (theForm == null) {
			theForm = new BusiTreeForm();
		}
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			String id = request.getParameter("id"); // 应用ID

			TbBusiSysTree appTreeObj = new TbBusiSysTree();
			appTreeObj.setType(2);
			if (id != null && !"".equals(id)) {
				theForm.setId(id);
				appTreeObj.setId(id);
			}
			if (id == null || "".equals(id)) {
				appTreeObj.setId(theForm.getId());
				id = theForm.getId();
			}
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(appTreeObj);
			String hostIds = "";
			if (appLst != null && appLst.size() > 0) {
				appTreeObj = appLst.get(0);
				TbSysAppObj appObj = new TbSysAppObj();
				appObj.setID(Integer.parseInt(appTreeObj.getBusiId()));
				appObj = appService.queryByObj(appObj);
				if (appObj != null) {
					hostIds += appObj.getSTRATEGY() + ",";
				}
				TbBusiSysTree depTreeObj = new TbBusiSysTree();
				depTreeObj.setType(3);
				depTreeObj.setParentId(id);
				List<TbBusiSysTree> depLst = tbBusiSysTreeService.queryForTree(depTreeObj);
				if (depLst != null && depLst.size() > 0) {
					for (int j = 0; j < depLst.size(); j++) {
						TbBusiDeployExampleObj depObj = new TbBusiDeployExampleObj();
						depObj.setID(Integer.parseInt(depLst.get(j).getBusiId()));
						depObj = deployExampleService.queryByObj(depObj);
						if (depObj != null) {
							hostIds += depObj.getHOSTID() + ",";
						}
					}
				}
			}
			TbBusiHostObj hostObj = new TbBusiHostObj();
			if (hostIds != null && hostIds.length() > 0) {
				hostIds = hostIds.substring(0, hostIds.length() - 1);
			}
			hostObj.setHOSTIDS(hostIds);
			hostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			List hostLst = busiHostService.queryForListByObj(hostObj);
			if (hostLst != null) {
				theForm.setResultList(hostLst);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.EXCEPTION;
		}
		return "listSysDeployHost";
	}

	/**
	 * 
	 * @Title: validateName
	 * @Description: 验证名称是否唯一
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 18, 2013 7:53:57 PM
	 */
	public String validateName() {
		String id = request.getParameter("id");
		String name = "";
		try {
			name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setName(name);
		List<TbBusiSysTree> treeLst = tbBusiSysTreeService.queryForTree(treeObj);
		if (treeLst != null && treeLst.size() > 0) {
			Boolean match = false;
			for (TbBusiSysTree tbBusiSysTree : treeLst) {
				if (tbBusiSysTree.getBusiId() != null && tbBusiSysTree.getBusiId().equals(id)) {
					nameEnabled = true;
					match = true;
				}
			}
			if (!match) {
				nameEnabled = false;
			}
		} else {
			nameEnabled = true;
		}
		return "validateName";
	}
}
