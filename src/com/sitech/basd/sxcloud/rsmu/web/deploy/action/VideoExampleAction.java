package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideoCommandsetObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployVideoCommandsetService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.ShellService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.VideoExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.VideoExampleForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.ssh.SshConnection;
import com.sitech.utils.servlet.PrintWriterOut;

public class VideoExampleAction extends CRUDBaseAction {
	/**
	 * @Title:得到部署录像信息列表
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangzechao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listVideoExample() throws BaseException {
		theForm.setID(0);
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		if (theForm.getHOSTIP() != null && !"".equals(theForm.getHOSTIP())) {
			obj.setHOSTIP(theForm.getHOSTIP());
		}
		if (theForm.getHOSTUSERNAME() != null && !"".equals(theForm.getHOSTUSERNAME())) {
			obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = this.videoExampleService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return LIST;
	}

	/**
	 * @Title:删除部署录像信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangzechao
	 * @version 1.0
	 */
	public String delVideoExample() throws BaseException {
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		HttpServletRequest request = Struts2Utils.getRequest();
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
		int ret = videoExampleService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除部署录像信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	/**
	 * @Title:添加部署录像信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangzechao
	 * @version 1.0
	 */
	public String addVideoExample() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// String CREATEUSER = ((TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY)).getNAME();
		theForm.setCREATEUSER(session.get("name").toString());
		return ADD;
	}

	/**
	 * @Title:添加部署录像信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveVideoExample() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		TbBusiHostObj hostObj = new TbBusiHostObj();

		hostObj.setID(Integer.parseInt(theForm.getHOSTID()));
		TbBusiHostObj hostObj2 = busiHostService.queryByObj(hostObj);
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		obj.setHOSTIP(hostObj2.getIP());
		theForm.setHOSTIP(hostObj2.getIP());
		int ret = 0;
		int result = 0;
		if (theForm.getID() == 0) {
			// String CREATEUSER = ((TbSysUserinfoObj) request.getSession()
			// .getAttribute(Constant.USER_SESSION_KEY)).getNAME();
			obj.setCREATEUSER(session.get("name").toString());
			ret = videoExampleService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增部署录像");

		} else {
			ret = videoExampleService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改部署录像");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";
	}

	/**
	 * @Title:修改部署录像
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modVideoExample() throws BaseException {
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		obj.setID(theForm.getID());
		TbBusiVideoExampleObj tempObj = videoExampleService.queryByObj(obj);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MODIFY;
	}

	@SuppressWarnings("unchecked")
	public String findAllHost() throws Exception {
		HttpServletResponse response = Struts2Utils.getResponse();
		TbBusiHostObj obj = new TbBusiHostObj();
		ArrayList<TbBusiHostObj> hostList = (ArrayList<TbBusiHostObj>) busiHostService
				.queryForListByObj(obj);
		// 存入json
		response.setContentType("text/html; charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(hostList);
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;

	}

	@SuppressWarnings("unchecked")
	public String findHostConfigListByHOSTID() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String HOSTID = request.getParameter("HOSTID");
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		obj.setHOSTID(Integer.parseInt(HOSTID));

		ArrayList<TbBusiHostObj> hostList = (ArrayList<TbBusiHostObj>) busiHostConfigService
				.queryForListByObj(obj);
		// 存入json
		response.setContentType("text/html; charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(hostList);
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;

	}

	public String makeVideoExample() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String user = (String) request.getParameter("HOSTUSERNAME");
		String ip = (String) request.getParameter("HOSTIP");

		String videoid = request.getParameter("videoid");
		TbBusiDeployVideoCommandsetObj commandSetobj = new TbBusiDeployVideoCommandsetObj();
		commandSetobj.setVIDEOID(Integer.parseInt(videoid));
		deployVideoCommandsetService.deleteByObj(commandSetobj);

		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		obj.setHOSTUSERNAME(user);
		TbBusiHostConfigObj obj2 = busiHostConfigService.queryByObj(obj);
		String pwd = obj2.getHOSRPWD();
		String port = "22";

		// 保存密码到SESSION
		request.getSession().setAttribute("ftpuser", user);
		request.getSession().setAttribute("ftppass", pwd);
		request.getSession().setAttribute("ftpport", port);

		if ("".equals(ip)) {

			return "faliure";
		}

		SshConnection sshConnection = shellService.getSshConnection(request, ip, port, user, pwd);
		if (sshConnection == null) {
			return "faliure";
		}
		int sequence = sshConnection.getLogin_sequence();
		request.setAttribute("sequence", sequence);
		return "";

	}

	public String listReplayVideo() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		theForm.setID(0);
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		if (theForm.getHOSTIP() != null && !"".equals(theForm.getHOSTIP())) {
			obj.setHOSTIP(theForm.getHOSTIP());
		}
		if (theForm.getHOSTUSERNAME() != null && !"".equals(theForm.getHOSTUSERNAME())) {
			obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = this.videoExampleService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return "";
	}

	/*
	 * 回放过程 yangwca
	 */
	public String reploy_process() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String videoid = request.getParameter("videoid");

		theForm.setVIDEOID(Integer.parseInt(videoid));
		return "reployprocess";
	}

	public String reploy_processByObj() throws BaseException {
		String start_time = theForm.getSTARTTIME();
		String end_time = theForm.getENDTIME();
		int videoid = theForm.getVIDEOID();
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		if (start_time != null) {
			obj.setSTARTTIME(start_time);
		}
		if (start_time != null) {
			obj.setENDTIME(end_time);
		}
		obj.setVIDEOID(videoid);
		ArrayList<TbBusiDeployVideorecordObj> reployProcessList = (ArrayList<TbBusiDeployVideorecordObj>) videoExampleService
				.queryIDListByObj(obj);
		theForm.setResultList(reployProcessList);
		return "";
	}

	/*
	 * 定时回放
	 */
	public String reploy_process_time() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String videoid = request.getParameter("videoid");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		if (start_time != null) {
			obj.setSTARTTIME(start_time);
		}
		if (start_time != null) {
			obj.setENDTIME(end_time);
		}
		obj.setVIDEOID(Integer.parseInt(videoid));
		ArrayList<TbBusiDeployVideorecordObj> reployProcessList = (ArrayList<TbBusiDeployVideorecordObj>) videoExampleService
				.queryIDListByObj(obj);
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

	/**
	 * @Title:复制所选择的部署录像的部署录像命令
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String copyVideoExample() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiDeployVideoCommandsetObj obj = new TbBusiDeployVideoCommandsetObj();
		obj.setVIDEOID(theForm.getID());
		List resultList = videoExampleService.queryForCommandListByVideoid(obj);
		String resultStr = "";
		for (int i = 0; i < resultList.size(); i++) {
			TbBusiDeployVideoCommandsetObj tempObj = (TbBusiDeployVideoCommandsetObj) resultList
					.get(i);
			resultStr = resultStr + tempObj.getCOMMAND() + "\n";
		}
		theForm.setResultStr(resultStr);
		// String CREATEUSER = ((TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY)).getNAME();
		theForm.setCREATEUSER(session.get("name").toString());
		return "";
	}

	/**
	 * @Title:修改所选择的部署录像的部署录像命令
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */

	public String catVideoExample() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		obj.setID(theForm.getID());
		TbBusiVideoExampleObj tempObj = videoExampleService.queryByObj(obj);

		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbBusiDeployVideoCommandsetObj obj2 = new TbBusiDeployVideoCommandsetObj();
		obj2.setVIDEOID(theForm.getID());
		List resultList = videoExampleService.queryForCommandListByVideoid(obj2);
		String resultStr = "";
		for (int i = 0; i < resultList.size(); i++) {
			TbBusiDeployVideoCommandsetObj tempObj2 = (TbBusiDeployVideoCommandsetObj) resultList
					.get(i);
			resultStr = resultStr + tempObj2.getCOMMAND() + "\n";
		}
		theForm.setResultStr(resultStr);
		// String CREATEUSER = ((TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY)).getNAME();
		theForm.setCREATEUSER(session.get("name").toString());
		return "";
	}

	/**
	 * @Title:保存新建的部署录像，并保存该部署录像的部署录像命令
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String saveDeployVideoCommandset() throws BaseException {
		@SuppressWarnings("unused")
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
		obj.setID(theForm.getID());
		obj.setCREATEUSER(theForm.getCREATEUSER());
		obj.setVIDEONAME(theForm.getVIDEONAME());
		obj.setREMARK(theForm.getREMARK());
		obj.setHOSTUSERNAME(theForm.getHOSTUSERNAME());
		obj.setHOSTIP(theForm.getHOSTIP());

		TbBusiHostObj hostObj = new TbBusiHostObj();

		hostObj.setID(Integer.parseInt(theForm.getHOSTID()));
		TbBusiHostObj hostObj2 = busiHostService.queryByObj(hostObj);

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		obj.setHOSTIP(hostObj2.getIP());
		theForm.setHOSTIP(hostObj2.getIP());
		int ret = 0;
		int result = 0;

		if (theForm.getID() == 0) {
			// String CREATEUSER = ((TbSysUserinfoObj) request.getSession()
			// .getAttribute(Constant.USER_SESSION_KEY)).getNAME();
			obj.setCREATEUSER(session.get("name").toString());
			ret = videoExampleService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增部署录像");

		} else {
			ret = videoExampleService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改部署录像");
		}

		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志

		int ret2 = 0;
		int result2 = 0;

		String resultStr = theForm.getResultStr();
		if (resultStr.endsWith("\n")) {
			resultStr = resultStr.substring(0, resultStr.length() - 2);
		}
		String[] strList = resultStr.split("\n");

		TbSysOperationLogObj operObj2 = this.getTbSysOperationLogObj(request);
		if (theForm.getID() == 0) {
			operObj2.setMESSAGE("新增一串部署录像命令");
			operObj.setOPERTYPE(1);
		} else {
			TbBusiDeployVideoCommandsetObj obj2 = new TbBusiDeployVideoCommandsetObj();
			obj2.setVIDEOID(theForm.getID());
			deployVideoCommandsetService.deleteByObj(obj2);
			operObj2.setMESSAGE("修改一串部署录像命令");
			operObj.setOPERTYPE(3);
		}

		for (int i = 0; i < strList.length; i++) {
			TbBusiDeployVideoCommandsetObj tempObj = new TbBusiDeployVideoCommandsetObj();
			tempObj.setVIDEOID(theForm.getID());
			tempObj.setCOMMAND(strList[i]);
			ret2 = deployVideoCommandsetService.insertByObj(tempObj);
		}

		if (ret2 > 0) {
			result2 = 1;
		}

		operObj.setRESULT(result2);
		operationService.insertByObj(operObj);// 写操作日志
		return "";
	}

	VideoExampleService videoExampleService;
	BusiHostService busiHostService;
	BusiHostConfigService busiHostConfigService;
	private ShellService shellService;
	DeployVideoCommandsetService deployVideoCommandsetService;
	private VideoExampleForm theForm;

	public VideoExampleForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VideoExampleForm theForm) {
		this.theForm = theForm;
	}

	public void setShellService(ShellService shellService) {
		this.shellService = shellService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setBusiHostConfigService(BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

	public void setVideoExampleService(VideoExampleService videoExampleService) {
		this.videoExampleService = videoExampleService;
	}

	public void setDeployVideoCommandsetService(
			DeployVideoCommandsetService deployVideoCommandsetService) {
		this.deployVideoCommandsetService = deployVideoCommandsetService;
	}

}
