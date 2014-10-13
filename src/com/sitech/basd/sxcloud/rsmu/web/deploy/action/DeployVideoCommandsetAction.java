package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.VideoExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.VideoExampleForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.MySecurityException;
import com.sitech.basd.sxcloud.util.ssh.SshConnection;
import com.sitech.basd.sxcloud.util.ssh.SshConstants;
import com.sitech.basd.sxcloud.util.ssh.SshSession;
import com.sitech.basd.yicloud.domain.entitytree.SolutionObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.utils.servlet.PrintWriterOut;

public class DeployVideoCommandsetAction extends CRUDBaseAction {

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
		PrintWriterOut.printWirter(response, ja.toString());
		// out.close();
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
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("id");
		String user = (String) request.getParameter("HOSTUSERNAME");
		String ip = (String) request.getParameter("HOSTIP");
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
		try {
			// ssh连接开始
			this.sshConnection(request, ip, port, user, pwd);
			// 建立唯一队列
			// JmsServerPra.setQuqueSn(mt_id);
		} catch (MySecurityException sex) {
			// 写入日志
			// log.info(sex);
			// super.addMessage(request, sex.getMessage());
			theForm.setResult("error");
			return "faliure";
		}

		return "";

	}

	/**
	 * SSH连接
	 * 
	 * @param request
	 * @param host
	 * @param port
	 * @param username
	 * @param pwd
	 * @throws MySecurityException
	 */
	private SshConnection sshConnection(HttpServletRequest request, String host, String port,
			String username, String pwd) throws MySecurityException {

		// ssh连接
		String connectionInfo = SshConnection.getConnectionInfo(host, port, username);

		request.setAttribute(SshConstants.PARAMETER_CONNECTION, connectionInfo);

		SshSession session = new SshSession(request);
		// 此连接是否已经存在
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		// If the connection does not exist yet, open a new one.
		if (sshConnection == null) {
			// ssh 连接
			sshConnection = new SshConnection(host, Integer.parseInt(port), username, pwd);

			// 打开Channel
			sshConnection.openShellChannel();
		}

		// If there is a collision adding it to the session, then one
		// must have been created while we were creating ours. Close ours
		// and use the other one.
		session.addSshConnection(sshConnection);

		return sshConnection;

	}

	public String listReplayVideo() throws BaseException {
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
		return "";
	}

	/*
	 * 回放过程 yangwca
	 */
	public String reploy_process() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String videoid = request.getParameter("videoid");
		videoid = "1";
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		// obj.setFLAG(1);
		obj.setVIDEOID(Integer.parseInt(videoid));
		List lis = videoExampleService.queryIDListByObj(obj);
		if (theForm == null) {
			theForm = new VideoExampleForm();
		}
		theForm.setResultList(lis);
		return "reployprocess";
	}

	/*
	 * 定时回放
	 */
	public String reploy_process_time() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String videoid = request.getParameter("videoid");
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		obj.setVIDEOID(Integer.parseInt(videoid));
		// obj.setFLAG(1);
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

	/** 浙江竞标演示 */
	public String executeDemo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String temId = request.getParameter("TEM_ID");
		request.setAttribute("TEM_ID", temId);
		return "executeDemo";
	}

	public String reploy_process_demo() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		// String videoid = request.getParameter("videoid");
		String flag = request.getParameter("flag");
		String TEM_ID = request.getParameter("TEM_ID");// 得到保存的修复模板的Id
		SolutionObj tObj = new SolutionObj();
		tObj.setTEM_ID(TEM_ID);
		List<SolutionObj> temList = entityTreeService.querySolutionList(tObj);
		tObj = temList.get(0);
		int index = Integer.parseInt(flag);
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		obj.setVIDEOID(tObj.getVIDEOID());
		// obj.setFLAG(1);
		List<TbBusiDeployVideorecordObj> reployProcessList = (ArrayList<TbBusiDeployVideorecordObj>) videoExampleService
				.queryIDListByObj(obj);
		// List<TbBusiDeployVideorecordObj> addList = new
		// ArrayList<TbBusiDeployVideorecordObj>();
		// 存入json
		response.setContentType("text/html; charset=utf-8");
		JSONArray ja = new JSONArray();
		/*
		 * if (index <= reployProcessList.size()) { for (int i = index; i <=
		 * reployProcessList.size(); i++) { TbBusiDeployVideorecordObj tempObj1
		 * = reployProcessList.get(i); TbBusiDeployVideorecordObj tempObj2 =
		 * reployProcessList .get(i + 1); if (tempObj1.getFLAG() ==
		 * tempObj2.getFLAG() && tempObj1.getFLAG() == 2 && tempObj2.getFLAG()
		 * == 2) { addList.add(tempObj1); addList.add(tempObj2); break; } else {
		 * addList.add(tempObj1); break; } } ja = JSONArray.fromObject(addList);
		 */
		ja = JSONArray.fromObject(reployProcessList.get(index));
		// PrintWriter out = response.getWriter();
		// out.print(ja.toString());
		// out.close();
		PrintWriterOut.printWirter(response, ja.toString());
		return null;
	}

	VideoExampleService videoExampleService;
	BusiHostService busiHostService;
	BusiHostConfigService busiHostConfigService;
	private EntityTreeService entityTreeService;
	private VideoExampleForm theForm;

	public VideoExampleForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VideoExampleForm theForm) {
		this.theForm = theForm;
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

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

}
