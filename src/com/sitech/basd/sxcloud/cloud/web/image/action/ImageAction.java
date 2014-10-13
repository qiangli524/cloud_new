package com.sitech.basd.sxcloud.cloud.web.image.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.rest.workloads.domain.WorkloadInfo;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.project.TbCloud2ProjectInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.project.TbProjectService;
import com.sitech.basd.sxcloud.cloud.service.virtual.VirtualService;
import com.sitech.basd.sxcloud.cloud.web.image.form.ImageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;

public class ImageAction extends CRUDBaseAction {
	private ImageForm theForm;

	public ImageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ImageForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有映像管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String listImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
		if (theForm.getIM_NAME() != null && !"".equals(theForm.getIM_NAME())) {
			obj.setIM_NAME(theForm.getIM_NAME().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		List functionsList = imageService.queryForListByObj(obj);
		theForm.setResultList(functionsList);
		return SUCCESS;

	}

	/**
	 * @Title:增加映像管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String addImageInfo() throws BaseException {
		theForm = new ImageForm();
		HttpServletRequest request = Struts2Utils.getRequest();
		return SUCCESS;
	}

	/**
	 * @Title:保存映像管理模块请求
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String saveImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
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
		if (theForm.getIM_ID().equals("")) {
			ret = imageService.insertByObj(obj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			ret = imageService.updateByObj(obj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return SUCCESS;
	}

	/**
	 * @Title:修改映像管理模块信息
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String modImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
		TbCloud2ImageInfoObj tempObj = imageService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @Title:删除映像管理模块请求
	 * @Copyright: Copyright (c) 20111220
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String delImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
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
		int ret = imageService.deleteByObj(obj);
		if (ret > 0) {
			result = 1;
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除功能信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return SUCCESS;
	}

	/**
	 * @Title:虚拟映像配置
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String virtualImagesConfig() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		List netList = netService.queryByNetObjForList(obj); // 调用netService的接口显示网络的下拉列表
		theForm.setNetList(netList);
		TbCloud2ImageInfoObj tbiObj = new TbCloud2ImageInfoObj();
		tbiObj.setIM_ID(theForm.getIM_ID());
		List<TbCloud2ImageTargetObj> tctList = imageService
				.getImageIdTargets(tbiObj);
		theForm.setTARGET(tctList);
		TbCloud2ImageInfoObj tbObj = imageService.queryByObj(tbiObj);
		theForm.setIM_CPU(tbObj.getIM_CPU());
		theForm.setIM_MAX_CPU(tbObj.getIM_MAX_CPU());
		theForm.setIM_MAX_MEM(tbObj.getIM_MAX_MEM());
		theForm.setIM_MAX_PROCESS_UNIT(tbObj.getIM_MAX_PROCESS_UNIT());
		theForm.setIM_MEM(tbObj.getIM_MEM());
		theForm.setIM_MIN_CPU(tbObj.getIM_MIN_CPU());
		theForm.setIM_MIN_MEM(tbObj.getIM_MIN_MEM());
		theForm.setIM_MIN_PROCESS_UNIT(tbObj.getIM_MIN_PROCESS_UNIT());
		theForm.setIM_DESC(tbObj.getIM_DESC());
		theForm.setIM_NAME(tbObj.getIM_NAME());
		theForm.setIM_PROCESS_UNIT(tbObj.getIM_PROCESS_UNIT());
		theForm.setIM_PROCESS_UNIT_MODE(tbObj.getIM_PROCESS_UNIT_MODE());
		theForm.setIM_CPU_MODE(tbObj.getIM_CPU_MODE());
		theForm.setIM_STATE(tbObj.getIM_STATE());
		theForm.setIM_LOG(tbObj.getIM_LOG());
		theForm.setIM_ROOT_PASS(tbObj.getIM_ROOT_PASS());
		theForm.setIM_CPU_MODE(tbObj.getIM_CPU_MODE());
		theForm.setIM_PROCESS_UNIT_MODE(tbObj.getIM_PROCESS_UNIT_MODE());
		theForm.setIM_NETWORK(tbObj.getIM_NETWORK());
		theForm
				.setIM_PROCESS_UNIT_Priority(tbObj
						.getIM_PROCESS_UNIT_Priority());
		return "config";
	}

	/**
	 * @Title:虚拟基本部署
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public String basicDeploy() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2ImageInfoObj tciObj = new TbCloud2ImageInfoObj();
		tciObj.setIM_ID(theForm.getIM_ID());
		WorkloadInfo info = imageService.deployImage(tciObj);
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		TbCloud2VirtualInfoObj tObj = new TbCloud2VirtualInfoObj();
		List tpList = tbProjectService.queryForListByProjectObj(obj);
		theForm.setProjectList(tpList);
		theForm.setWorkloadInfoId(info.getId());
		tObj.setVH_ID_IBM(info.getId());
		tObj.setVH_NULL(info.getId());
		TbCloud2VirtualInfoObj tbObj = virtualService.queryByObj(tObj);
		theForm.setVH_CPU(tbObj.getVH_CPU());
		theForm.setVH_MEM(tbObj.getVH_MEM());
		return "basicDeploy";
	}

	/**
	 * @Title:虚拟高级部署
	 * @Copyright: Copyright (c) 20120105
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public String advancedDeploy() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2ImageInfoObj tciObj = new TbCloud2ImageInfoObj();
		tciObj.setIM_ID(theForm.getIM_ID());
		WorkloadInfo info = imageService.deployImage(tciObj);
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		TbCloud2NetInfoObj obj1 = new TbCloud2NetInfoObj();
		theForm.setWorkloadInfoId(info.getId());
		TbCloud2ProjectInfoObj tpObj = new TbCloud2ProjectInfoObj();
		List tpList = tbProjectService.queryForListByProjectObj(tpObj);
		theForm.setProjectList(tpList);
		TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();
		List netList = netService.queryByNetObjForList(netObj);
		theForm.setNetList(netList);
		TbCloud2VirtualInfoObj tObj = new TbCloud2VirtualInfoObj();
		tObj.setVH_ID_IBM(info.getId());
		tObj.setVH_NULL(info.getId());
		TbCloud2VirtualInfoObj tbObj = virtualService.queryByObj(tObj);
		theForm.setVH_MAX_CPU(tbObj.getVH_MAX_CPU());
		theForm.setVH_CPU(tbObj.getVH_CPU());
		theForm.setVH_MIN_CPU(tbObj.getVH_MIN_CPU());
		theForm.setVH_MIN_PROCESS_UNIT(tbObj.getVH_MIN_PROCESS_UNIT());
		theForm.setVH_PROCESS_UNIT(tbObj.getVH_PROCESS_UNIT());
		theForm.setVH_MAX_PROCESS_UNIT(tbObj.getVH_MAX_PROCESS_UNIT());
		theForm.setVH_MIN_MEM(tbObj.getVH_MIN_MEM());
		theForm.setVH_MEM(tbObj.getVH_MEM());
		theForm.setVH_MAX_MEM(tbObj.getVH_MAX_MEM());
		return "advancedDeploy";
	}

	/**
	 * 
	 * @Title: deployImage
	 * @Description: 部署功能(修改虚拟机状态-由新建状态转到运行等状态)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 17, 2012 5:44:20 PM
	 */

	public String deployImage() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getWorkloadInfoId());
		obj.setVH_NAME(theForm.getIM_NAME());
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setVH_CPU(theForm.getVH_CPU());
		obj.setVH_MEM(theForm.getVH_MEM());
		obj.setVH_DESC(theForm.getIM_DESC());
		obj.setVH_STAT("EXECUTING");
		virtualService.updateByObj(obj);
		int result = imageService.deployVirtualStatToRunStat(obj);
		if (result == -1) {
			return "error";
		}
		return "deploysuccess";
	}

	/**
	 * 
	 * @Title: advancedDeployImage
	 * @Description: 高级部署功能(修改虚拟机状态-由新建状态转到运行等状态)
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 18, 2012 6:01:49 PM
	 */
	public String advancedDeployImage() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2VirtualInfoObj obj = new TbCloud2VirtualInfoObj();
		obj.setVH_ID_IBM(theForm.getWorkloadInfoId());
		obj.setVH_NAME(theForm.getIM_NAME());
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setVH_CPU(theForm.getVH_CPU());
		obj.setVH_MEM(theForm.getVH_MEM());
		obj.setVH_DESC(theForm.getIM_DESC());
		obj.setVH_NETWORK(theForm.getNET_ID());
		obj.setVH_NETWORK_DOUBLE(theForm.getNET_ID_DOUBLE());
		obj.setVH_MAX_CPU(theForm.getVH_MAX_CPU());
		obj.setVH_MIN_CPU(theForm.getVH_MIN_CPU());
		obj.setVH_MAX_MEM(theForm.getVH_MAX_MEM());
		obj.setVH_MIN_MEM(theForm.getVH_MIN_MEM());
		obj.setVH_PROCESS_UNIT(theForm.getVH_PROCESS_UNIT());
		obj.setVH_MAX_PROCESS_UNIT(theForm.getVH_MAX_PROCESS_UNIT());
		obj.setVH_MIN_PROCESS_UNIT(theForm.getVH_MIN_PROCESS_UNIT());
		obj.setVH_CPU_MODE(theForm.getSETTINGS());
		obj.setVH_PROCESS_UNIT_MODE(theForm.getMODEL());
		obj.setVH_PROCESS_UNIT_Priority(theForm.getPRIORITY());
		obj.setVH_STAT("EXECUTING");
		virtualService.updateByObj(obj);
		int result = imageService.advanceddeployVirtualStatToRunStat(obj);
		if (result == -1) {
			return "error";
		}
		return "deploysuccess";
	}

	/**
	 * @Title:将虚拟映像移至项目
	 * @Copyright: Copyright (c) 20120214
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public String moveImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		TbCloud2ProjectInfoObj obj = new TbCloud2ProjectInfoObj();
		List thProjectList = tbProjectService.queryForListByProjectObj(obj); // 调用
		// tbProjectService的接口显示项目的下拉列表
		theForm.setProjectList(thProjectList);
		return "moveImageInfo";
	}

	/**
	 * @Title:保存虚拟映像移至项目
	 * @Copyright: Copyright (c) 20120214
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public String saveMvImageInfo() throws BaseException {
		if (theForm == null) {
			theForm = new ImageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbCloud2ImageInfoObj obj = new TbCloud2ImageInfoObj();
		obj.setPROJECT_ID(theForm.getPROJECT_ID());
		obj.setIM_ID(theForm.getIM_ID());
		obj.setIM_NAME(theForm.getIM_NAME());
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");
		int ret = 0;
		int result = 0;
		int ret1 = imageService.updateByProjectIdObj(obj);
		operObj.setOPERTYPE(3);
		operObj.setMESSAGE("修改功能信息");

		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "saveMvImageInfo";
	}

	private ImageService imageService;
	private TbProjectService tbProjectService;
	private VirtualService virtualService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	private NetService netService;

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

	public void setTbProjectService(TbProjectService tbProjectService) {
		this.tbProjectService = tbProjectService;
	}

	public void setVirtualService(VirtualService virtualService) {
		this.virtualService = virtualService;
	}

}
