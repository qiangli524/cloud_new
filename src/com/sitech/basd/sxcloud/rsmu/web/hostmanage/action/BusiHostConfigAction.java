package com.sitech.basd.sxcloud.rsmu.web.hostmanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigHisService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.hostmanage.form.BusiHostConfigForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.utils.servlet.PrintWriterOut;

public class BusiHostConfigAction extends CRUDBaseAction {

	/**
	 * @Title:获取主机配置列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listBusiHostConfig() throws Exception {
		if (theForm == null) {
			theForm = new BusiHostConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setID(0);
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String hostId = request.getParameter("hostId");
		if (hostId != null && !"".equals(hostId)) {
			theForm.setHOSTID(Integer.parseInt(hostId));
		}
		String id = request.getParameter("id");
		if (id != null) {
			theForm.setHOSTID(Integer.parseInt(id));
		}
		String IP = request.getParameter("hostIp");
		if (IP != null && !"".equals(IP)) {
			theForm.setIP(IP);
		}
		obj.setHOSTID(theForm.getHOSTID());
		obj.setHOSTNAME(theForm.getHOSTNAME());
		obj.setIP(theForm.getIP());
		if (theForm.getHOSTUSERNAME() != null && !"".equals(theForm.getHOSTUSERNAME())) {
			obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		obj.setSTATUS(1);
		List<TbBusiHostConfigObj> userInfoList = busiHostConfigService
				.queryForHostConfigAndDepListByObj(obj);
		theForm.setResultList(userInfoList);
		return LIST;
	}

	/**
	 * @Title:增加主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addBusiHostConfig() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm == null) {
			theForm = new BusiHostConfigForm();
		}
		theForm.reset();// 清空ActionForm
		TbBusiHostObj busiHostobj = new TbBusiHostObj();
		String HOSTID = request.getParameter("HOSTID");
		busiHostobj.setID(Integer.parseInt(HOSTID));
		@SuppressWarnings("unused")
		List<TbBusiHostConfigObj> configList = new ArrayList<TbBusiHostConfigObj>();
		List busiHostList = busiHostService.queryForListByObj(busiHostobj);
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		theForm.setHOSTNAME(((TbBusiHostObj) busiHostList.get(0)).getHOSTNAME());
		theForm.setHOSTID(((TbBusiHostObj) busiHostList.get(0)).getID());
		theForm.setAppList(appList);
		theForm.setIP(((TbBusiHostObj) busiHostList.get(0)).getIP());
		return ADD;
	}

	/**
	 * @Title:保存主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String saveBusiHostConfig() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		// String password = DES3.encrypt(theForm.getHOSRPWD()); // 密码加密
		int HOSTID = theForm.getHOSTID();
		obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME_BF());
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// obj.setHOSRPWD(password);
		int ret = 0;
		int result = 0;

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		if (theForm.getID() == 0) {
			obj.setHOSTID(HOSTID);
			if (obj.getTYPE() == 1) {
				obj.setSTATUS(1);
			}
			obj.setSTATUS(1);
			ret = busiHostConfigService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增主机配置信息");
			// 如果选择生成部署实例
			if (theForm.getIfexample().equals("0")) {
				TbSysAppObj tbSysAppObj = new TbSysAppObj();
				tbSysAppObj.setID(theForm.getAPPID());
				TbSysAppObj sysAppObj = appService.queryByObj(tbSysAppObj);
				// sysAppObj.setDEPLOYPATH(theForm.getDeploy_path().replace(
				// "\r\n", ""));
				// appService.updateByObj(sysAppObj);

				TbBusiDeployExampleObj tdeObj = new TbBusiDeployExampleObj();
				tdeObj.setAPPID(theForm.getAPPID());
				tdeObj.setDEPLOYPATH(theForm.getDeploy_path());
				if (theForm.getIsCredituser() == 0) {
					tdeObj.setHostUsername(theForm.getHOSTUSERNAME());
				} else {
					tdeObj.setHostUsername(theForm.getCREDITUSER());
				}
				tdeObj.setIsbackup(0);
				tdeObj.setIsrestart(0);
				tdeObj.setHOSTID(HOSTID);
				tdeObj.setSYS_ID(sysAppObj.getSYS_ID());
				deployExampleService.insertByObj(tdeObj);
			}
		} else {
			obj.setSTATUS(1);
			ret = busiHostConfigService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改主机配置信息");
			if (ret > 0) {
				// 修改主机配置信息成功的同时将删除信息插入到主机配置历史记录表
				TbBusiHostConfigHisObj busihostConfigHisObj = new TbBusiHostConfigHisObj();
				try {
					BeanUtils.copyProperties(busihostConfigHisObj, theForm);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request
				// .getSession().getAttribute(Constant.USER_SESSION_KEY);
				busihostConfigHisObj.setUPDATEUSER(session.get("name").toString());
				busihostConfigHisObj.setUPDATETYPE("1");
				busiHostConfigHisService.insertByObj(busihostConfigHisObj);
			}
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		// try {
		// response.sendRedirect("/hostconfig_listBusiHostConfig.do?hostId=" +
		// HOSTID);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return "save";

	}

	/**
	 * @Title:修改主机配置请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modBusiHostConfig() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostConfigForm();
		}
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		obj.setID(theForm.getID());
		TbBusiHostConfigObj tempObj = busiHostConfigService.queryByObj(obj);
		// String password = DES3.decrypt(tempObj.getHOSRPWD());
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			theForm.setHOSTUSERNAME_BF(tempObj.getHOSTUSERNAME());
			theForm.setTYPE_BF(tempObj.getTYPE());
			// theForm.setHOSRPWD(password);
			// theForm.setReHOSRPWD(password);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		theForm.setAppList(appList);
		return MODIFY;
	}

	/**
	 * @Title:删除主机配置信息请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delBusiHostConfig() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostConfigForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
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
		@SuppressWarnings("unused")
		TbBusiHostConfigObj busihostConfigObj = busiHostConfigService.queryByObj(obj);
		String hostId = Integer.toString(obj.getHOSTID());
		TbBusiHostConfigObj tempHostObj = busiHostConfigService.queryByObj(busihostConfigObj);
		// int ret = busiHostConfigService.deleteByObj(obj);

		tempHostObj.setSTATUS(3);
		tempHostObj.setEXECUTE_FLAG(0);
		int ret1 = busiHostConfigService.updateByObj(tempHostObj);
		if (ret1 > 0) {
			// 删除主机配置信息成功的同时将删除信息插入到主机配置历史记录表
			TbBusiHostConfigHisObj busihostConfigHisObj = new TbBusiHostConfigHisObj();
			try {
				BeanUtils.copyProperties(busihostConfigHisObj, busihostConfigObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
			// request.getSession()
			// .getAttribute(Constant.USER_SESSION_KEY);

			busihostConfigHisObj.setUPDATEUSER(session.get("name").toString());
			busihostConfigHisObj.setUPDATETYPE("2");
			busiHostConfigHisService.insertByObj(busihostConfigHisObj);
			result = 1;
		}
		// 插入操作日志表
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除主机配置信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		// try {
		// response.sendRedirect("/listBusiHostConfig.do?hostId=" + hostId);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return DEL;
		// return mapping.findForward("default");
	}

	/**
	 * @Title:验证主机用户是否已经存在
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String queryAjx_username() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String username = request.getParameter("hostusername");
		String hostid = request.getParameter("hostid");
		obj.setHOSTUSERNAME(username);
		obj.setHOSTID(Integer.parseInt(hostid));
		TbBusiHostConfigObj objt = busiHostConfigService.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out = response.getWriter();
			if (objt == null) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("no");
				PrintWriterOut.printWirter(response, "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:验证超级管理员是否唯一
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String queryAjx_usertype() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String usertype = request.getParameter("usertype");
		String hostid = request.getParameter("hostid");
		obj.setTYPE(Integer.parseInt(usertype));
		obj.setHOSTID(Integer.parseInt(hostid));
		TbBusiHostConfigObj objt = busiHostConfigService.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out = response.getWriter();
			if (objt == null) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("no");
				PrintWriterOut.printWirter(response, "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:删除用户时验证用户是否为超级管理员用户，如果是则不能删除
	 * @Copyright: Copyright (c) 201011
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String queryAjx_type() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		String userid = request.getParameter("userid");
		obj.setID(Integer.parseInt(userid));
		TbBusiHostConfigObj objt = busiHostConfigService.queryByObj(obj);
		try {
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out = response.getWriter();
			if (objt.getTYPE() == 1) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("no");
				PrintWriterOut.printWirter(response, "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:得到ssh，端口，用户名，密码等信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String getUserInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String hostId = request.getParameter("hostId");
		String appId = request.getParameter("appId");
		String userName = null;
		String userPort = null;
		String userPwd = null;
		String apppath = null;
		String logpath = null;
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		obj.setHOSTID(Integer.parseInt(hostId));
		obj.setAPPID(appId);
		obj = busiHostConfigService.queryByObj(obj);
		if (obj != null) {
			userName = obj.getHOSTUSERNAME();
			userPort = obj.getSshPort();
			userPwd = obj.getSshPwd();
			apppath = obj.getAppPath();
			logpath = obj.getLogPath();
		}
		try {
			String json = "{'name':'" + userName + "','port':'" + userPort + "','pwd':'" + userPwd
					+ "','apppath':'" + apppath + "','logpath':'" + logpath + "'}";
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(JSONObject.fromObject(json).toString());
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getDepExampleAppPath
	 * @Description: 获取部署实例的访问路径
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-4 下午5:28:22
	 */
	public String getDepExampleAppPath() {
		String appId = request.getParameter("appId");
		TbBusiDeployExampleObj exObj = new TbBusiDeployExampleObj();
		exObj.setAPPID(Integer.parseInt(appId));
		exObj = deployExampleService.queryByObj(exObj);
		JSONObject jo = new JSONObject();
		if (exObj != null) {
			TbBusiHostConfigObj hostConfigObj = new TbBusiHostConfigObj();
			hostConfigObj.setHOSTCONFIGID(exObj.getHOST_CONFIG_ID());
			hostConfigObj = busiHostConfigService.queryByObj(hostConfigObj);
			if (hostConfigObj != null && hostConfigObj.getAppPath() != null) {
				jo.put("apppath", hostConfigObj.getAppPath());
			} else {
				jo.put("apppath", null);
			}
		} else {
			jo.put("apppath", null);
		}
		try {
			JSONUtil.printJSON(jo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: queryForListByHostId
	 * @Description: TODO(通过主机编号查找用户信息)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @throws IOException
	 * @createtime 2013-4-12 下午7:31:05
	 */
	@SuppressWarnings("unchecked")
	public String getUserInfoByHostId() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String hostId = request.getParameter("hostId");
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		obj.setHOSTID(Integer.parseInt(hostId));
		List configList = busiHostConfigService.queryForListByHostId(obj);

		if (hostId != null && !"".equals(hostId.trim())) {
			ArrayList<TbBusiHostConfigObj> userList = null;
			obj.setHOSTID(Integer.parseInt(hostId));
			userList = (ArrayList<TbBusiHostConfigObj>) busiHostConfigService
					.queryForListByHostId(obj);
			// 存入json
			response.setContentType("text/html; charset=UTF-8");
			JSONArray ja = new JSONArray();
			ja = JSONArray.fromObject(userList);
			// PrintWriter out = response.getWriter();
			// out.print(ja.toString());
			// out.close();
			PrintWriterOut.printWirter(response, ja.toString());
		}
		return null;
	}

	/**
	 * @Title:进入连接主机输入用户名，密码的界面
	 * @Copyright: Copyright (c) 201303
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String ssh2Host() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String ip = request.getParameter("hostip");
		request.setAttribute("ip", ip);
		return "ssh2Host";
	}

	private BusiHostConfigService busiHostConfigService;
	private BusiHostService busiHostService;
	private BusiHostConfigHisService busiHostConfigHisService;
	private BusiHostConfigForm theForm;
	private AppService appService;
	private DeployExampleService deployExampleService;

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public BusiHostConfigForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiHostConfigForm theForm) {
		this.theForm = theForm;
	}

	public void setBusiHostConfigHisService(BusiHostConfigHisService busiHostConfigHisService) {
		this.busiHostConfigHisService = busiHostConfigHisService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setBusiHostConfigService(BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

}
