package com.sitech.basd.sxcloud.rsmu.web.softmanage.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.cloud3.domain.sysappconfig.SysAppConfigObj;
import com.sitech.basd.cloud3.service.sysappconfig.SysAppConfigService;
import com.sitech.basd.component.domain.user.UserObj;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.service.bizsystem.BizSystemService;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDoTaskObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.TaskService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppHisService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppPortService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.softmanage.form.AppForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.utils.common.FormDataUtil;
import com.sitech.utils.exception.SSHException;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@SuppressWarnings("serial")
public class AppAction extends CRUDBaseAction {
	private AppForm theForm;
	private AppService appService;
	private AppPortService appPortService;
	private TaskService taskService;
	private AppHisService appHisService;
	private BizSystemService bizSystemService;
	private BusiHostService busiHostService;
	private BusiHostConfigService busiHostConfigService;
	private TbBusiSysTreeService tbBusiSysTreeService;
	private UserService userService;
	private SysAppConfigService sysAppConfigService;
	private DeployExampleService deployExampleService;

	private String basepathEnabled;
	private int depNum;
	private int appId;

	public String sysId;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getDepNum() {
		return depNum;
	}

	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getBasepathEnabled() {
		return basepathEnabled;
	}

	public void setBasepathEnabled(String basepathEnabled) {
		this.basepathEnabled = basepathEnabled;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setSysAppConfigService(SysAppConfigService sysAppConfigService) {
		this.sysAppConfigService = sysAppConfigService;
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

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public AppForm getTheForm() {
		return theForm;
	}

	public void setTheForm(AppForm theForm) {
		this.theForm = theForm;
	}

	public void setBizSystemService(BizSystemService bizSystemService) {
		this.bizSystemService = bizSystemService;
	}

	public void setAppHisService(AppHisService appHisService) {
		this.appHisService = appHisService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public void setAppPortService(AppPortService appPortService) {
		this.appPortService = appPortService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	/**
	 * @Title:查询出所有应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listApp() {

		if (theForm == null) {
			theForm = new AppForm();
		}
		theForm.setID(0);
		TbSysAppObj obj = new TbSysAppObj();
		String sys_id = Struts2Utils.getParameter("sys_id");
		if (sys_id != null && !"".equals(sys_id)) {
			obj.setSYS_ID(sys_id);
			theForm.setSYS_ID(sys_id);
		} else if (theForm.getSYS_ID() != null && !"".equals(theForm.getSYS_ID())) {
			obj.setSYS_ID(theForm.getSYS_ID());
		}
		if (theForm.getAPPNAME() != null && !"".equals(theForm.getAPPNAME())) {
			obj.setAPPNAME(theForm.getAPPNAME().trim());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS().trim());
		}
		/* obj.setPagination(this.getPaginater().initPagination(request)) ;//分页 */
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		for (Object object : appList) {
			TbSysAppObj appObj = (TbSysAppObj) object;
			String hostConfigId = appObj.getHOST_CONFIG_ID();
			TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
			configObj.setHOSTCONFIGID(hostConfigId);
			configObj = busiHostConfigService.queryByObj(configObj);
			if (configObj != null) {
				UserObj userObj = new UserObj();
				userObj.setId(configObj.getUSER_MANAGE_ID());
				userObj = userService.queryUserObjById(userObj);
				if (userObj != null) {
					appObj.setHOSEUSERNAME(userObj.getUsername());
				} else {
					appObj.setHOSEUSERNAME("");
				}
			} else {
				appObj.setHOSEUSERNAME("");
			}
		}
		theForm.setResultList(appList);
		return LIST;

	}

	public String goback() {
		theForm = new AppForm();
		theForm.setID(0);
		TbSysAppObj obj = new TbSysAppObj();
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		theForm.setResultList(appList);
		return LIST;
	}

	/**
	 * @Title:捕获应用镜像列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public String listAppImage() {
		if (theForm == null) {
			theForm = new AppForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbSysAppObj obj = new TbSysAppObj();
		if (theForm.getAPPID() > 0) {
			obj.setID(theForm.getAPPID());
		}
		if (theForm.getCATCH_STATUS() != null && !"".equals(theForm.getCATCH_STATUS())) {
			obj.setCATCH_STATUS(theForm.getCATCH_STATUS().trim());
		}
		/* obj.setPagination(this.getPaginater().initPagination(request)) ;//分页 */
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		TbSysAppObj obj1 = new TbSysAppObj();
		List appList1 = appService.queryForListByObj(obj1);
		theForm.setResultList(appList);
		theForm.setAppList(appList1);
		return "listAppImage";

	}

	/**
	 * @Title:捕获镜像填写备注信息界面
	 * @author duangh
	 */
	public String remarkPage() {
		return "remark";
	}

	/**
	 * @Title:捕获应用镜像功能
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public String CatchImage() throws BaseException {
		if (theForm == null) {
			theForm = new AppForm();
		}
		theForm.reset();// 清空ActionForm
		HttpServletRequest request = Struts2Utils.getRequest();
		String remark = request.getParameter("remark");
		try {
			remark = URLDecoder.decode(remark, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String appid = request.getParameter("APPID");
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		tbSysAppObj.setID(Integer.valueOf(appid));
		/** 以前是先查询是否捕获，如果未捕获再进行捕获，现进行修改，支持捕获多个版本--duangh */
		// tbSysAppObj.setCATCH_STATUS("1");
		// TbSysAppObj appobj = appService.queryByObj(tbSysAppObj);
		// int ret = 0;
		// if (appobj == null) {
		// ret = appService.catchRequestByObj(tbSysAppObj);
		// }
		// if (ret == 1) {
		// TbBusiDoTaskObj taskObj = new TbBusiDoTaskObj();
		// taskObj.setEXA_ID(Integer.valueOf(appid));
		// taskObj.setEXA_TYPE(9); // 9代表捕获镜像
		// taskObj.setDO_FLAG(0);
		// TbBusiDoTaskObj obj = taskService.getTaskObj(taskObj);
		// if (obj == null) {
		// taskService.insertCatchImageTask(taskObj);
		// }
		// }
		// 更新捕获状态信息，为正在捕获
		tbSysAppObj.setCATCH_STATUS("1");
		int ret = appService.catchRequestByObj(tbSysAppObj);
		if (ret == 1) {
			TbBusiDoTaskObj taskObj = new TbBusiDoTaskObj();
			taskObj.setEXA_ID(Integer.valueOf(appid));
			taskObj.setEXA_TYPE(9); // 9代表捕获镜像
			taskObj.setDO_FLAG(0);
			taskService.insertCatchImageTask(taskObj);// 插入任务表
			TbSysAppHisObj hisObj = new TbSysAppHisObj();
			hisObj.setApp_id(Integer.valueOf(appid));
			hisObj.setRemark(remark);
			appHisService.insertByObj(hisObj);// 插入应用镜像历史版本信息表
		}

		return "CatchImage";
	}

	/**
	 * @Title：查看捕获的应用镜像的历史版本
	 * @author duangh
	 */
	public String historyVersion() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String app_id = request.getParameter("appId");
		TbSysAppHisObj obj = new TbSysAppHisObj();
		obj.setApp_id(Integer.parseInt(app_id));
		List resultList = appHisService.historyVersion(obj);
		request.setAttribute("resultList", resultList);
		return "version";
	}

	/**
	 * 
	 * @Title: addApp
	 * @Description: 跳转创建基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-13 下午1:06:44
	 */
	public String addApp() {
		if (theForm == null) {
			theForm = new AppForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		String dialogName = request.getParameter("dialogName") == null ? "" : request
				.getParameter("dialogName");
		BizSystemObj bizObj = new BizSystemObj();
		List bizList = bizSystemService.queryForListByObj(bizObj);
		theForm.setBizList(bizList);// 业务系统集合
		theForm.setHOSEUSERLIST(new ArrayList());
		TbBusiHostObj obj = new TbBusiHostObj();
		List<TbBusiHostObj> busiHostList = (ArrayList<TbBusiHostObj>) busiHostService
				.queryForListByObj(obj);
		theForm.setBusiHostList(busiHostList);
		if (operType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null & lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				theForm.setSYS_ID(treeObj.getBusiId());
			}
		} else {
			theForm.setOldSysId(theForm.getSYS_ID());
		}
		request.setAttribute("operType", operType);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("dialogName", dialogName);
		return ADD;
	}

	/**
	 * @Title:保存应用请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveApp() {
		if (theForm == null) {
			theForm = new AppForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		TbSysAppObj obj = new TbSysAppObj();

		TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
		if (operType.equals("list")) {
			// 保存原业务系统ID
			sysId = theForm.getOldSysId();
			try {
				BeanUtils.copyProperties(obj, theForm);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			if (obj.getID() == 0) {
				hostConfigObj.setUSER_MANAGE_ID(obj.getHOSEUSERID());
				hostConfigObj.setHOSTID(obj.getSTRATEGY());
			} else {
				TbSysAppObj queryObj = new TbSysAppObj();
				queryObj = appService.queryByObj(obj);
				if (queryObj != null) {
					TbBusiHostConfigObj queryHostConfig = new TbBusiHostConfigObj();
					queryHostConfig.setHOSTCONFIGID(queryHostConfig.getHOSTCONFIGID());
					queryHostConfig = busiHostConfigService.queryByObj(queryHostConfig);
					if (queryHostConfig != null) {
						hostConfigObj.setUSER_MANAGE_ID(queryHostConfig.getUSER_MANAGE_ID());
						hostConfigObj.setHOSTID(queryHostConfig.getHOSTID());
					}
				}
			}
			hostConfigObj.setBase_path(theForm.getBASEPATH());
		} else if (operType.equals("tree")) {
			try {
				FormDataUtil.copyProperties(obj, request);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (obj.getID() == 0) {
				hostConfigObj.setUSER_MANAGE_ID(obj.getHOSEUSERID());
				hostConfigObj.setHOSTID(obj.getSTRATEGY());
			} else {
				TbSysAppObj queryObj = new TbSysAppObj();
				queryObj = appService.queryByObj(obj);
				if (queryObj != null) {
					TbBusiHostConfigObj queryHostConfig = new TbBusiHostConfigObj();
					queryHostConfig.setHOSTCONFIGID(queryHostConfig.getHOSTCONFIGID());
					queryHostConfig = busiHostConfigService.queryByObj(queryHostConfig);
					if (queryHostConfig != null) {
						hostConfigObj.setUSER_MANAGE_ID(queryHostConfig.getUSER_MANAGE_ID());
						hostConfigObj.setHOSTID(queryHostConfig.getHOSTID());
					}
				}
			}
			hostConfigObj.setBase_path(obj.getBASEPATH());
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");
		// 更新基准主机用户关联应用

		int ret = 0;
		int result = 0;
		if (operType.equals("list")) {
			// 从列表进行操作
			if (theForm.getID() == 0) {
				String hostConfigId = RandomUUID.getUuid();
				obj.setHOST_CONFIG_ID(hostConfigId);
				ret = appService.insertByObj(obj);
				if (ret > 0) {
					// 向树中插入数据
					TbBusiSysTree queryObj = new TbBusiSysTree();
					queryObj.setType(1);
					queryObj.setBusiId(obj.getSYS_ID());
					List sysLst = tbBusiSysTreeService.queryForTree(queryObj);
					if (sysLst != null && sysLst.size() > 0) {
						queryObj = (TbBusiSysTree) sysLst.get(0);
					}
					TbBusiSysTree treeObj = new TbBusiSysTree();
					treeObj.setBusiId(ret + "");
					treeObj.setName(obj.getAPPNAME());
					treeObj.setParentId(queryObj.getId());
					treeObj.setType(2);
					TbBusiHostObj tbHostObj = new TbBusiHostObj();
					tbHostObj.setID(theForm.getSTRATEGY());
					tbHostObj = busiHostService.queryByObj(tbHostObj);
					if (tbHostObj != null) {
						treeObj.setHostIP(tbHostObj.getIP());
					}
					String ret2 = tbBusiSysTreeService.insertTbBusiSysTree(treeObj);

					// TbBusiDoTaskObj taskObj = new TbBusiDoTaskObj();
					// taskObj.setEXA_ID(ret);
					// taskObj.setEXA_TYPE(10); // 10代表初始化基准机全部文件版本到数据库
					// taskObj.setDO_FLAG(0);
					// 暂不执行抓取基准应用任务
					// taskService.insertCatchImageTask(taskObj);// 插入任务表
					// 更新基准主机用户关联应用

					hostConfigObj.setAPPID(String.valueOf(ret));
					hostConfigObj.setHOSTCONFIGID(hostConfigId);
					ret = busiHostConfigService.insertByObj(hostConfigObj);

					if (operType.equals("tree")) {
					}
					operObj.setOPERTYPE(1);
					operObj.setMESSAGE("新增应用" + obj.getAPPNAME());
				}

			} else {
				TbSysAppObj queryAppObj = new TbSysAppObj();
				queryAppObj.setID(obj.getID());
				queryAppObj = appService.queryByObj(queryAppObj);
				String sysId = queryAppObj.getSYS_ID();
				obj.setSYS_ID(sysId);
				ret = appService.updateByObj(obj);
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改应用" + obj.getAPPNAME());
				// 更新基准主机用户关联应用
				hostConfigObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.updateHostConfigByAppId(hostConfigObj);
				// 更新树中数据
				TbBusiSysTree sysObj = new TbBusiSysTree();
				sysObj.setType(1);
				sysObj.setBusiId(sysId);
				List sysLst = tbBusiSysTreeService.queryForTree(sysObj);
				if (sysLst != null && sysLst.size() > 0) {
					sysObj = (TbBusiSysTree) sysLst.get(0);
				}
				TbBusiSysTree appObj = new TbBusiSysTree();
				appObj.setType(2);
				appObj.setBusiId(obj.getID() + "");
				List appLst = tbBusiSysTreeService.queryForTree(appObj);
				if (appLst != null && appLst.size() > 0) {
					appObj = (TbBusiSysTree) appLst.get(0);
				}
				TbBusiSysTree treeObj = new TbBusiSysTree();
				treeObj.setId(appObj.getId());
				treeObj.setName(obj.getAPPNAME());
				treeObj.setParentId(sysObj.getId());
				int ret2 = tbBusiSysTreeService.updateTbBusiSysTreeByObj(treeObj);
			}

		} else if (operType.equals("tree")) {
			// 从树上进行操作
			int id = obj.getID();
			if (id == 0) {
				TbBusiSysTree parentObj = new TbBusiSysTree();
				parentObj.setType(1);
				parentObj.setId(nodeId);
				List lst = tbBusiSysTreeService.queryForTree(parentObj);
				if (lst != null && lst.size() > 0) {
					parentObj = (TbBusiSysTree) lst.get(0);
				}
				obj.setSYS_ID(parentObj.getBusiId());
				String hostConfigId = RandomUUID.getUuid();
				obj.setHOST_CONFIG_ID(hostConfigId);
				ret = appService.insertByObj(obj);
				if (ret > 0) {
					TbBusiDoTaskObj taskObj = new TbBusiDoTaskObj();
					taskObj.setEXA_ID(ret);
					taskObj.setEXA_TYPE(10); // 10代表初始化基准机全部文件版本到数据库
					taskObj.setDO_FLAG(0);
					// 暂不执行抓取基准应用任务
					// taskService.insertCatchImageTask(taskObj);// 插入任务表
					// 更新基准主机用户关联应用
					hostConfigObj.setAPPID(String.valueOf(ret));
					hostConfigObj.setHOSTCONFIGID(hostConfigId);
					busiHostConfigService.insertByObj(hostConfigObj);
					TbBusiSysTree treeObj = new TbBusiSysTree();
					treeObj.setBusiId(ret + "");
					treeObj.setName(obj.getAPPNAME());
					treeObj.setParentId(nodeId);
					treeObj.setType(2);
					TbBusiHostObj tbHostObj = new TbBusiHostObj();
					tbHostObj.setID(obj.getSTRATEGY());
					tbHostObj = busiHostService.queryByObj(tbHostObj);
					if (tbHostObj != null) {
						treeObj.setHostIP(tbHostObj.getIP());
					}
					String ret2 = tbBusiSysTreeService.insertTbBusiSysTree(treeObj);

					if (ret > 0) {
						result = 1;
					}
					operObj.setOPERTYPE(1);
					operObj.setMESSAGE("新增应用" + obj.getAPPNAME());
					operObj.setRESULT(result);
					@SuppressWarnings("unused")
					int retOper = operationService.insertByObj(operObj);// 写操作日志

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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}

			} else {
				operObj.setOPERTYPE(3);
				operObj.setMESSAGE("修改应用" + obj.getAPPNAME());
				// 更新基准主机用户关联应用
				hostConfigObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.updateHostConfigByAppId(hostConfigObj);

				TbBusiSysTree queryTreeObj = new TbBusiSysTree();
				queryTreeObj.setType(2);
				queryTreeObj.setId(nodeId);
				List lst = tbBusiSysTreeService.queryForTree(queryTreeObj);
				if (lst != null && lst.size() > 0) {
					queryTreeObj = (TbBusiSysTree) lst.get(0);
				}
				int ret2 = 0;
				if (queryTreeObj != null) {
					queryTreeObj.setName(obj.getAPPNAME());
					ret2 = tbBusiSysTreeService.updateTbBusiSysTreeByObj(queryTreeObj);
				}

				TbBusiSysTree parentObj = new TbBusiSysTree();
				parentObj.setId(queryTreeObj.getParentId());
				List pLst = tbBusiSysTreeService.queryForTree(parentObj);
				if (pLst != null && pLst.size() > 0) {
					parentObj = (TbBusiSysTree) pLst.get(0);
				}
				obj.setSYS_ID(parentObj.getBusiId());
				ret = appService.updateByObj(obj);

				String res = "";
				if (ret2 == -1) {
					res = "{\"result\":\"" + "-1" + "\"}";
				} else {
					res = "{\"result\":\"" + "1" + "\"}";
				}

				if (ret > 0) {
					result = 1;
				}
				operObj.setRESULT(result);
				@SuppressWarnings("unused")
				int retOper = operationService.insertByObj(operObj);// 写操作日志

				JSONObject jo = JSONObject.fromObject(res);
				try {
					JSONUtil.printJSON(jo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		}

		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return REDIRECT;
	}

	/**
	 * @Title:修改应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String modApp() {
		if (theForm == null) {
			theForm = new AppForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		String dialogName = request.getParameter("dialogName") == null ? "" : request
				.getParameter("dialogName");
		BizSystemObj bizObj = new BizSystemObj();
		List bizList = bizSystemService.queryForListByObj(bizObj);
		theForm.setBizList(bizList);// 业务系统集合
		TbBusiHostObj busiHostObj = new TbBusiHostObj();
		List<TbBusiHostObj> busiHostList = (ArrayList<TbBusiHostObj>) busiHostService
				.queryForListByObj(busiHostObj);
		theForm.setBusiHostList(busiHostList);
		TbSysAppObj obj = new TbSysAppObj();
		if (operType.equals("list")) {
			obj.setID(theForm.getID());
			// 保存原业务系统ID
			theForm.setOldSysId(theForm.getSYS_ID());
		} else if (operType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				obj.setID(Integer.parseInt(treeObj.getBusiId()));
				theForm.setID(obj.getID());
			}
		}
		obj = appService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, obj);
			theForm.setAPP_IDENTIFY_BF(obj.getAPP_IDENTIFY());
			TbBusiHostObj hostObj = new TbBusiHostObj();
			hostObj.setID(theForm.getSTRATEGY());
			hostObj = busiHostService.queryByObj(hostObj);
			if (hostObj != null) {
				UserObj userObj = new UserObj();
				userObj.setIp(hostObj.getIP());
				List hoseuserlist = userService.queryForExampleUserListByObj(userObj);
				theForm.setHOSEUSERLIST(hoseuserlist);
			} else {
				theForm.setHOSEUSERLIST(new ArrayList());
			}
			TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
			hostConfigObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
			hostConfigObj = busiHostConfigService.queryByObj(hostConfigObj);
			theForm.setHOSEUSERID(hostConfigObj.getUSER_MANAGE_ID());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("operType", operType);
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("dialogName", dialogName);
		return MODIFY;
	}

	/**
	 * @Title:删除应用请求
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delApp() {

		if (theForm == null) {
			theForm = new AppForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		TbSysAppObj obj = new TbSysAppObj();
		int result = 0;
		if (operType.equals("list")) {
			try {
				BeanUtils.copyProperties(obj, theForm);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			obj = appService.queryByObj(obj);
			// 删除hostConfig表中数据
			if (obj != null && obj.getHOST_CONFIG_ID() != null
					&& !"".equals(obj.getHOST_CONFIG_ID())) {
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.deleteByObj(configObj);
			}
			// 删除列表数据
			int ret = appService.deleteByObj(obj);

			// 删除业务中心树表
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setType(2);
			treeObj.setBusiId(obj.getID() + "");
			tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);
			// 删除appConfig表中数据
			SysAppConfigObj appConfigObj = new SysAppConfigObj();
			appConfigObj.setCONFIG_ID(obj.getID());
			sysAppConfigService.deleteSysAppConfigObj(appConfigObj);

			if (ret > 0) {
				result = 1;
			}

			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setOPERTYPE(2);
			operObj.setMESSAGE("删除应用" + obj.getID());
			operObj.setREMARK("");
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		} else if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
			}
			obj.setID(Integer.parseInt(treeObj.getBusiId()));
			obj = appService.queryByObj(obj);
			String res = "";
			// 删除hostConfig表中数据
			if (obj != null && obj.getHOST_CONFIG_ID() != null
					&& !"".equals(obj.getHOST_CONFIG_ID())) {
				TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
				configObj.setHOSTCONFIGID(obj.getHOST_CONFIG_ID());
				busiHostConfigService.deleteByObj(configObj);

				// 删除appConfig表中数据
				SysAppConfigObj appConfigObj = new SysAppConfigObj();
				appConfigObj.setCONFIG_ID(obj.getID());
				sysAppConfigService.deleteSysAppConfigObj(appConfigObj);
				// 删除列表数据
				int ret = appService.deleteByObj(obj);
				// 删除树上数据
				int ret2 = tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);
				if (ret > 0 && ret2 > 0) {
					result = 1;
					res = "{\"result\":\"" + "1" + "\"}";
				} else {
					res = "{\"result\":\"" + "-1" + "\"}";
				}
			} else {
				res = "{\"result\":\"" + "-1" + "\"}";
			}

			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setOPERTYPE(2);
			operObj.setMESSAGE("删除应用" + obj.getID());
			operObj.setREMARK("");
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志

			JSONObject jo = JSONObject.fromObject(res);
			try {
				JSONUtil.printJSON(jo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		if (theForm.getSYS_ID() != null && !"".equals(theForm.getSYS_ID())) {
			sysId = theForm.getSYS_ID();
		} else {
			sysId = "";
		}
		return REDIRECT;
	}

	/**
	 * @Title:定时来获取部署的状态
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String appStatus() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		TbSysAppObj obj = new TbSysAppObj();
		obj.setLONGID(id);
		ArrayList<TbSysAppObj> reployProcessList = (ArrayList<TbSysAppObj>) appService
				.queryListIDByObj(obj);
		for (int i = 0; i < reployProcessList.size(); i++) {
			TbSysAppObj deployExample = (TbSysAppObj) reployProcessList.get(i);
			String flag = deployExample.getSTATUS();
			String start_stop_flag = deployExample.getSTART_STOP_FLAG();
			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("1")) {
					deployExample.setDEPLOY_FLAG_NAME("已注册");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"部署\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'2');return false;\" />&nbsp;";
				} else if (flag.equals("2")) {
					deployExample.setDEPLOY_FLAG_NAME("部署");
				} else if (flag.equals("3")) {
					deployExample.setDEPLOY_FLAG_NAME("已部署");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"注销\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'4');return false;\" />&nbsp;";
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"升级\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'6');return false;\" />&nbsp;";
				} else if (flag.equals("4")) {
					deployExample.setDEPLOY_FLAG_NAME("注销");
				} else if (flag.equals("5")) {
					deployExample.setDEPLOY_FLAG_NAME("已注销");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"部署\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'2');return false;\" />&nbsp;";
				} else if (flag.equals("6")) {
					deployExample.setDEPLOY_FLAG_NAME("升级");
				} else if (flag.equals("7")) {
					deployExample.setDEPLOY_FLAG_NAME("已升级");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"注销\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'4');return false;\" />&nbsp;";
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"升级\" ";
					str += "onclick=\"deployRequest(" + deployExample.getID()
							+ ",'6');return false;\" />&nbsp;";
				}
			}
			if (start_stop_flag != null && !"".equals(start_stop_flag)) {
				if (start_stop_flag.equals("0")) {
					deployExample.setSTART_STOP_FLAG_NAME("正在停止");
				} else if (start_stop_flag.equals("1")) {
					deployExample.setSTART_STOP_FLAG_NAME("已停止");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"启动\" ";
					str += "onclick=\"StartAndStopDeployExample(" + deployExample.getID()
							+ ",'2');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				} else if (start_stop_flag.equals("2")) {
					deployExample.setSTART_STOP_FLAG_NAME("正在启动");
				} else if (start_stop_flag.equals("3")) {
					deployExample.setSTART_STOP_FLAG_NAME("已启动");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"停止\" ";
					str += "onclick=\"StartAndStopDeployExample(" + deployExample.getID()
							+ ",'0');return false;\" />";
					// obj.setSTART_STOP_FLAG_AN(str);
				}
			}
			deployExample.setDEPLOY_FLAG_AN(str);
			reployProcessList.set(i, deployExample);
		}

		// 存入json
		response.setContentType("text/html; charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(reployProcessList);
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;
	}

	/*
	 * 通过jquary获取应用状态信息
	 */
	public String AppFlagStatus() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		TbSysAppObj tempObj = null;
		if (operType.equals("list")) {
			String id = request.getParameter("ID");
			TbSysAppObj obj = new TbSysAppObj();
			obj.setID(Integer.parseInt(id));
			tempObj = appService.queryByObj(obj);
		} else if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
			}
			TbSysAppObj obj = new TbSysAppObj();
			obj.setID(Integer.parseInt(treeObj.getBusiId()));
			tempObj = appService.queryByObj(obj);
		}
		if (tempObj != null) {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(tempObj.getSTATUS());
			// out.close();
			PrintWriterOut.printWirter(response, tempObj.getSTATUS());
		}
		return null;
	}

	/**
	 * @Title:检测应用标识是否已经存在
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String queryAjx_app_identify() throws BaseException {

		TbSysAppObj obj = new TbSysAppObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String appIdentify = request.getParameter("appIdentify");
		obj.setAPP_IDENTIFY(appIdentify);
		List<TbSysAppObj> list = appService.queryForListByObj(obj);
		String id = request.getParameter("id");
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out = response.getWriter();
			Boolean match = false;
			JSONObject jo = new JSONObject();
			if (list != null && list.size() > 0) {
				for (TbSysAppObj tbSysAppObj : list) {
					if (id.equals(tbSysAppObj.getID() + "")) {
						jo.put("enabled", "true");
						match = true;
					}
				}
				if (!match) {
					jo.put("enabled", "false");
				}
			} else {
				jo.put("enabled", "true");
			}
			// out.print(jo);
			PrintWriterOut.printWirter(response, jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: validateBasepath
	 * @Description: 验证基准主机应用路径是否可用
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jul 18, 2013 9:04:38 PM
	 */
	public String validateBasepath() {
		if (theForm == null) {
			theForm = new AppForm();
		}

		UserObj userObj = new UserObj();
		userObj.setId(theForm.getHOSEUSERID());
		userObj = userService.queryUserObjById(userObj);
		if (userObj != null) {
			SSHUtil sshUtil = new SSHUtil();
			String hostIP = userObj.getIp();
			Integer port = userObj.getHostport();
			if(port==null || port==0){
				port = 22;
			}
			String username = userObj.getUsername();
			String pwd = userObj.getPassword();
			SshConnection sshConnection = null;
			try {
				sshConnection = sshUtil.getSshConnectionThrowException(hostIP, port, username, pwd);
			} catch (SSHException e) {
				String mess = e.getMessage();
				if (mess.endsWith("All parameters must be at least one character.")) {
					basepathEnabled = "paraException";
				} else if (mess.endsWith("Invalid username or password.")) {
					basepathEnabled = "invalidNameOrPassException";
				} else if (mess.endsWith("Unknown host.")) {
					basepathEnabled = "unkonwnHostException";
				} else if (mess.endsWith("Unable to connect to host.")) {
					basepathEnabled = "unableConnectException";
				} else {
					basepathEnabled = "sshException";
				}
				return "validateBasepath";
			} catch (Exception e) {
				basepathEnabled = "sshException";
				return "validateBasepath";
			}
			String basePath = theForm.getBASEPATH();
			int index = basePath.lastIndexOf("/");
			if (index < 0) {
				basepathEnabled = "checkPath";
				return "validateBasepath";
			}
			String path1 = basePath.substring(0, index);
			String path2 = basePath.substring(index + 1);
			String shellCmd = "ls -l " + path1 + "|grep " + path2 + "|wc -l";
			List<String> lst = new ArrayList<String>();
			try {
				lst = SSHUtil.sendShellToSSHChannelReMess(sshConnection, true, shellCmd);
			} catch (Exception e) {
				basepathEnabled = "sshException";
				return "validateBasepath";
			}
			String re = "";
			if (lst.size() > 0) {
				re = lst.get(lst.size() - 2).split("\n")[1];
				int result = 0;
				// 解析查询目录返回结果
				try {
					result = Integer.parseInt(re);
				} catch (Exception e) {

				}
				if (result > 0) {
					basepathEnabled = "existTrue";
					basepathEnabled = vailidataPathRepeat();
				} else {
					basepathEnabled = "existFalse";
				}
			} else {
				basepathEnabled = "existFalse";
			}
		} else {
			basepathEnabled = "existFalse";
		}
		return "validateBasepath";
	}

	/**
	 * 
	 * @Title: vailidataPathRepeat
	 * @Description: 验证是否路径是否重复
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-29 下午4:48:01
	 */
	private String vailidataPathRepeat() {
		TbBusiHostConfigObj configObj = new TbBusiHostConfigObj();
		configObj.setHOSTID(theForm.getSTRATEGY());
		configObj.setUSER_MANAGE_ID(theForm.getHOSEUSERID());
		List<TbBusiHostConfigObj> configLst = busiHostConfigService.queryForListByObj(configObj);
		TbSysAppObj sysObj = new TbSysAppObj();
		int id = theForm.getID();
		if (id != 0) {
			sysObj.setID(id);
			sysObj = appService.queryByObj(sysObj);
		}
		if (configLst != null && configLst.size() > 0) {
			Boolean match = false;
			for (TbBusiHostConfigObj tbBusiHostConfigObj : configLst) {
				if (tbBusiHostConfigObj.getBase_path().equals(theForm.getBASEPATH())) {
					if (id != 0
							&& sysObj.getHOST_CONFIG_ID().equals(
									tbBusiHostConfigObj.getHOSTCONFIGID())) {
						basepathEnabled = "repeatFalse";
						match = true;
						break;
					} else {
						basepathEnabled = "repeatTrue";
						match = true;
						break;
					}
				}
			}
			if (!match) {
				basepathEnabled = "repeatFalse";
			}
		} else {
			basepathEnabled = "repeatFalse";
		}
		return basepathEnabled;
	}

	/**
	 * 
	 * @Title: queryAppSonNum
	 * @Description: 查询基准应用下部署实例的个数
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-27 下午7:32:54
	 */
	public String queryAppSonNum() {
		TbBusiDeployExampleObj depObj = new TbBusiDeployExampleObj();
		depObj.setAPPID(appId);
		depNum = deployExampleService.queryAppSonNum(depObj);
		return "result";
	}
}
