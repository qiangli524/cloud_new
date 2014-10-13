package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployTimeConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployTimeConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployTimeConfigForm;

@Controller("deployTimeConfigAction")
public class DeployTimeConfigAction extends CRUDBaseAction {
	private DeployTimeConfigForm theForm;
	@Autowired
	private DeployTimeConfigService deployTimeConfigService;

	/**
	 * @Title:得到部署时间配置信息列表
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listDeployTimeConfig() throws Exception {
		if (theForm == null) {
			theForm = new DeployTimeConfigForm();
		}
		TbBusiDeployTimeConfigObj obj = new TbBusiDeployTimeConfigObj();
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List resultList = deployTimeConfigService.queryForListByObj(obj);
		theForm.setResultList(resultList);
		return "";
	}

	/**
	 * @Title:保存部署时间配置信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String saveDeployTimeConfig() throws Exception {
		if (theForm == null) {
			theForm = new DeployTimeConfigForm();
		}
		TbBusiDeployTimeConfigObj obj = new TbBusiDeployTimeConfigObj();
		String type = request.getParameter("type");
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
		if ("0".equals(type)) {

		} else if ("1".equals(type)) {
			ret = deployTimeConfigService.updateByObj(obj);
		} else if ("2".equals(type)) {
			ret = deployTimeConfigService.updateByObj2(obj);
		} else if ("3".equals(type)) {
			ret = deployTimeConfigService.updateByObj3(obj);
		}
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("修改部署时间信息");
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "";
	}

	/**
	 * @Title:修改部署时间配置
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String modDeployTimeConfig() throws Exception {
		if (theForm == null) {
			theForm = new DeployTimeConfigForm();
		}
		TbBusiDeployTimeConfigObj obj = new TbBusiDeployTimeConfigObj();
		obj.setID(theForm.getID());
		TbBusiDeployTimeConfigObj tempObj = (TbBusiDeployTimeConfigObj) deployTimeConfigService
				.queryForListByObj(obj).get(0);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theForm.setEXPRESSION_BF(tempObj.getEXPRESSION());
		theForm.setSTATUS_BF(tempObj.getSTATUS());
		return "";
	}
}
