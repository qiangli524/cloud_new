package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployStrategyObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiVideoExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployStrategyService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.VideoExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployStrategyForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class DeployStrategyAction extends CRUDBaseAction {

	/**
	 * @Title:得到部署策略配置信息列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listDeployStrategy() throws BaseException {
		TbBusiDeployStrategyObj obj = new TbBusiDeployStrategyObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		if (theForm.getSTRATEGYNAME() != null && !"".equals(theForm.getSTRATEGYNAME())) {
			obj.setSTRATEGYNAME(theForm.getSTRATEGYNAME());
		}
		if (theForm.getTYPE() != 0) {
			obj.setTYPE(theForm.getTYPE());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = this.deployStrategyService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return LIST;
	}

	/**
	 * @Title:增加部署策略配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addDeployStrategy() throws BaseException {
		return ADD;
	}

	/**
	 * @Title:保存部署策略配置信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveDeployStrategy() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		TbBusiDeployStrategyObj obj = new TbBusiDeployStrategyObj();

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
		int ret = 0;
		int result = 0;
		if (theForm.getID() == 0) {
			ret = deployStrategyService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增部署策略配置");
		} else {
			ret = deployStrategyService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改部署策略配置");
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
	 * @Title:修改部署策略配置
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modDeployStrategy() throws BaseException {
		TbBusiDeployStrategyObj obj = new TbBusiDeployStrategyObj();
		obj.setID(theForm.getID());
		TbBusiDeployStrategyObj tempObj = deployStrategyService.queryByObj(obj);
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

	/**
	 * @Title:删除部署策略配置
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delDeployStrategy() throws BaseException {
		TbBusiDeployStrategyObj obj = new TbBusiDeployStrategyObj();
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
		int ret = deployStrategyService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}

		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除应用" + obj.getID());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "del";
	}

	// 根据传入进来的id来决定 获取主机列表 或 录像列表
	@SuppressWarnings("unchecked")
	public String HostAndVideo() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String type = request.getParameter("id");
		if (type != null && !"".equals(type.trim())) {
			ArrayList<TbBusiHostObj> bloodList = null;
			if (type.equals("2")) {
				TbBusiHostObj obj = new TbBusiHostObj();
				bloodList = (ArrayList<TbBusiHostObj>) busiHostService.queryForListByObj(obj);
			} else {
				TbBusiVideoExampleObj obj = new TbBusiVideoExampleObj();
				bloodList = (ArrayList<TbBusiHostObj>) videoExampleService.queryForListByObj(obj);
			}

			// 存入json
			response.setContentType("text/html; charset=gb2312");
			JSONArray ja = new JSONArray();
			ja = JSONArray.fromObject(bloodList);
			// PrintWriter out = response.getWriter();
			// out.print(ja.toString());
			// out.close();
			PrintWriterOut.printWirter(response, ja.toString());
		}
		return null;

	}

	DeployStrategyService deployStrategyService;
	private BusiHostService busiHostService;
	private VideoExampleService videoExampleService;
	private DeployStrategyForm theForm;

	public DeployStrategyForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployStrategyForm theForm) {
		this.theForm = theForm;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setDeployStrategyService(DeployStrategyService deployStrategyService) {
		this.deployStrategyService = deployStrategyService;
	}

	public void setVideoExampleService(VideoExampleService videoExampleService) {
		this.videoExampleService = videoExampleService;
	}

}
