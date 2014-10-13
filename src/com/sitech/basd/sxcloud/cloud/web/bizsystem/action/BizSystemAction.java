package com.sitech.basd.sxcloud.cloud.web.bizsystem.action;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.bizsystem.BizSystemObj;
import com.sitech.basd.sxcloud.cloud.domain.bizsystem.DomainObj;
import com.sitech.basd.sxcloud.cloud.service.appmessage.AppMessageService;
import com.sitech.basd.sxcloud.cloud.service.appmessage.TbCloudAppInfoService;
import com.sitech.basd.sxcloud.cloud.service.bizsystem.BizSystemService;
import com.sitech.basd.sxcloud.cloud.web.bizsystem.form.BizSystemForm;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.basd.yicloud.service.busitree.BusiTreeService;
import com.sitech.basd.yicloud.util.JSONUtil;
import com.sitech.utils.common.FormDataUtil;

@SuppressWarnings("all")
public class BizSystemAction extends CRUDBaseAction {
	private BizSystemForm theForm;

	public BizSystemForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BizSystemForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有业务系统信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String listBizSystem() {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		BizSystemObj obj = new BizSystemObj();
		if (theForm.getSYS_ID() != null && !"".equals(theForm.getSYS_ID())) {
			obj.setSYS_ID(theForm.getSYS_ID().trim());
		}
		if (theForm.getSYS_NAME() != null && !"".equals(theForm.getSYS_NAME())) {
			obj.setSYS_NAME(theForm.getSYS_NAME().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List BizSystemList = bizSystemService.queryForListByObj(obj);
		theForm.setResultList(BizSystemList);
		return "listBizSystem";
	}

	/**
	 * @Title:增加业务系统信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String addBizSystem() {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String createType = request.getParameter("createType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		DomainObj doaObj = new DomainObj();
		List regionList = bizSystemService.queryForListByDomainObj(doaObj);
		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setType(0);
		List busiCenList = tbBusiSysTreeService.queryForTree(treeObj);
		if (!nodeId.equals("")) {
			theForm.setBusiCenterId(nodeId);
		}
		theForm.setBusiCenterList(busiCenList);
		theForm.setRegionList(regionList);
		theForm.setFlag(0);
		request.setAttribute("createType", createType);
		request.setAttribute("nodeId", nodeId);
		return "addBizSystem";
	}

	/**
	 * @Title:保存业务系统信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String saveBizSystem() {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String createType = request.getParameter("createType");
		String flag = request.getParameter("flag");
		BizSystemObj obj = new BizSystemObj();
		try {
			FormDataUtil.copyProperties(obj, request);
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int ret = 0;
		String result = "";
		if (createType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			if (flag.equals("0")) {
				String bizSysId = bizSystemService.insertByObj(obj);
				if (ret != -1) {
					// 向业务中心树的表中插入数据
					TbBusiSysTree treeObj = new TbBusiSysTree();
					treeObj.setName(obj.getSYS_NAME());
					treeObj.setParentId(nodeId);
					treeObj.setType(1);
					treeObj.setBusiId(bizSysId);
					String ret2 = tbBusiSysTreeService.insertTbBusiSysTree(treeObj);
					if (ret2 == null) {
						result = "{\"result\":\"" + "-1" + "\"}";
					} else {
						result = "{\"result\":\"" + "1" + "\"}";
					}
				} else {
					result = "{\"result\":\"" + "-1" + "\"}";
				}
				JSONObject jo = JSONObject.fromObject(result);
				try {
					JSONUtil.printJSON(jo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
				// operObj.setOPERTYPE(1);
				// operObj.setMESSAGE("新增应用信息");
			} else {
				ret = bizSystemService.updateByObj(obj);
				if (ret != -1) {
					// 向业务中心树的表中插入数据
					TbBusiSysTree treeObj = new TbBusiSysTree();
					treeObj.setId(nodeId);
					treeObj.setName(obj.getSYS_NAME());
					int ret2 = tbBusiSysTreeService.updateTbBusiSysTreeByObj(treeObj);
					if (ret2 == -1) {
						result = "{\"result\":\"" + "-1" + "\"}";
					} else {
						result = "{\"result\":\"" + "1" + "\"}";
					}
				} else {
					result = "{\"result\":\"" + "-1" + "\"}";
				}
				JSONObject jo = JSONObject.fromObject(result);
				try {
					JSONUtil.printJSON(jo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
				// operObj.setOPERTYPE(3);
				// operObj.setMESSAGE("修改应用信息");
			}

		} else if (createType.equals("list")) {
			String busiCenterId = "";
			try {
				busiCenterId = URLDecoder.decode(request.getParameter("busiCenterId"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag.equals("0")) {
				// 插入业务系统表
				String bizSysId = bizSystemService.insertByObj(obj);
				// 插入业务系统树表
				TbBusiSysTree treeObj = new TbBusiSysTree();
				treeObj.setParentId(busiCenterId);
				treeObj.setType(1);
				treeObj.setName(obj.getSYS_NAME());
				treeObj.setBusiId(bizSysId);
				tbBusiSysTreeService.insertTbBusiSysTree(treeObj);
				// operObj.setOPERTYPE(1);
				// operObj.setMESSAGE("新增应用信息");
			} else {
				// 更新业务系统表
				ret = bizSystemService.updateByObj(obj);
				TbBusiSysTree queryObj = new TbBusiSysTree();
				queryObj.setBusiId(obj.getSYS_ID());
				queryObj.setType(1);
				List<TbBusiSysTree> lst = tbBusiSysTreeService.queryForTree(queryObj);
				if (lst != null && lst.size() > 0) {
					queryObj = lst.get(0);
				}
				TbBusiSysTree treeObj = new TbBusiSysTree();
				treeObj.setId(queryObj.getId());
				treeObj.setParentId(busiCenterId);
				treeObj.setName(obj.getSYS_NAME());
				tbBusiSysTreeService.updateTbBusiSysTreeByObj(treeObj);
				// 更新业务系统树表
				// operObj.setOPERTYPE(3);
				// operObj.setMESSAGE("修改应用信息");
			}
		}
		// operObj.setRESULT(result);
		// int retOper = operationService.insertByObj(operObj);//写操作日志
		return "saveBizSystem";
	}

	/**
	 * @Title:删除业务系统信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */
	public String delBizSystem() throws BaseException {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String operType = request.getParameter("operType");
		BizSystemObj obj = new BizSystemObj();
		int result = 0;
		if (operType.equals("tree")) {
			String nodeId = request.getParameter("nodeId");
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
			}
			int ret = tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);
			obj.setSYS_ID(treeObj.getBusiId());
			int ret2 = bizSystemService.deleteByObj(obj);

			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setOPERTYPE(2);
			operObj.setMESSAGE("删除应用信息");
			operObj.setREMARK("");
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志

			String res = "";
			if (ret != -1) {
				res = "{\"result\":\"" + "1" + "\"}";
				result = 1;
			} else {
				res = "{\"result\":\"" + "-1" + "\"}";
			}
			JSONObject jo = JSONObject.fromObject(res);
			try {
				JSONUtil.printJSON(jo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else if (operType.equals("list")) {
			try {
				BeanUtils.copyProperties(obj, theForm);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			int ret = bizSystemService.deleteByObj(obj);

			// 删除业务中心树表
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setType(1);
			treeObj.setBusiId(obj.getSYS_ID());
			tbBusiSysTreeService.deleteTbBusiSysTreeById(treeObj);

			if (ret > 1) {
				result = 1;
			}
			TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
			operObj.setOPERTYPE(2);
			operObj.setMESSAGE("删除应用信息");
			operObj.setREMARK("");
			operObj.setRESULT(result);
			@SuppressWarnings("unused")
			int retOper = operationService.insertByObj(operObj);// 写操作日志
		}
		return "delBizSystem";
	}

	/**
	 * @Title:修改业务系统信息
	 * @Copyright: Copyright (c) 201208
	 * @Company: si-tech
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String modBizSystem() throws BaseException {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}

		HttpServletRequest request = Struts2Utils.getRequest();
		String createType = request.getParameter("createType");
		String nodeId = request.getParameter("nodeId") == null ? "" : request
				.getParameter("nodeId");
		BizSystemObj obj = new BizSystemObj();
		if (createType.equals("tree")) {
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setId(nodeId);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				obj.setSYS_ID(treeObj.getBusiId());
				theForm.setBusiCenterId(treeObj.getParentId());
			}
		} else if (createType.equals("list")) {
			String SYS_ID = request.getParameter("SYS_ID");
			obj.setSYS_ID(SYS_ID);
			TbBusiSysTree treeObj = new TbBusiSysTree();
			treeObj.setType(1);
			treeObj.setBusiId(SYS_ID);
			List lst = tbBusiSysTreeService.queryForTree(treeObj);
			if (lst != null && lst.size() > 0) {
				treeObj = (TbBusiSysTree) lst.get(0);
				theForm.setBusiCenterId(treeObj.getParentId());
			}
		}
		BizSystemObj tempObj = bizSystemService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		DomainObj doaObj = new DomainObj();
		TbBusiSysTree treeObj = new TbBusiSysTree();
		treeObj.setType(0);
		List busiCenList = tbBusiSysTreeService.queryForTree(treeObj);
		theForm.setBusiCenterList(busiCenList);
		List regionList = bizSystemService.queryForListByDomainObj(doaObj);
		theForm.setRegionList(regionList);
		theForm.setFlag(1);
		request.setAttribute("createType", createType);
		request.setAttribute("nodeId", nodeId);
		return "modBizSystem";
	}

	/**
	 * 
	 * @Title: getHostByBusi
	 * @Description: 得到业务系统所对应的主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 26, 2013 2:52:54 PM
	 */
	public String getHostByBusi() {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String SYS_ID = request.getParameter("SYS_ID");
		String isApp = request.getParameter("app");
		TbSysAppObj sysObj = new TbSysAppObj();
		sysObj.setSYS_ID(SYS_ID);
		List<TbSysAppObj> appLst = appService.queryForListByObj(sysObj);

		List<TbBusiDeployExampleObj> deployList = new ArrayList<TbBusiDeployExampleObj>();
		List<TbBusiHostObj> hostList = new ArrayList<TbBusiHostObj>();
		if (appLst != null && appLst.size() > 0) {
			for (TbSysAppObj sys : appLst) {
				TbBusiDeployExampleObj to = new TbBusiDeployExampleObj();
				to.setAPPID(sys.getID());
				List<TbBusiDeployExampleObj> exampleList = (List<TbBusiDeployExampleObj>) deployExampleService
						.queryForListByObj(to);
				deployList.addAll(exampleList);
			}
		}
		for (TbBusiDeployExampleObj tb : deployList) {
			TbBusiHostObj th = new TbBusiHostObj();
			th.setID(tb.getHOSTID());
			List<TbBusiHostObj> hList = (List<TbBusiHostObj>) busiHostService.queryForListByObj(th);
			hostList.addAll(hList);
		}
		theForm.setApp_num(appLst.size());
		theForm.setExample_num(deployList.size());
		theForm.setSYS_ID(SYS_ID);
		theForm.setAppList(appLst);
		theForm.setExampleList(deployList);
		theForm.setResultList(hostList);
		if (isApp.equals("1")) {
			return "app_list";
		} else if (isApp.equals("0")) {
			return "example_list";
		} else {
			return LIST;
		}

	}

	/**
	 * 
	 * @Title: busiSystemInfo
	 * @Description: 查询业务系统下的信息，包括基准应用，部署实例个数
	 * @return String
	 * @author duangh
	 * @version 1.0
	 * @createtime 2013/03/22
	 */
	public String busiSystemInfo() {
		if (theForm == null) {
			theForm = new BizSystemForm();
		}
		BizSystemObj obj = new BizSystemObj();
		if (theForm.getSYS_NAME() != null && !theForm.getSYS_NAME().equals("")) {
			obj.setSYS_NAME(theForm.getSYS_NAME());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List resultList = bizSystemService.busiSystemInfo(obj);
		theForm.setResultList(resultList);
		return "busiSystemInfo";
	}

	private BizSystemService bizSystemService;
	private BusiTreeService busiTreeService;
	private TbCloudAppInfoService tbCloudAppInfoService;
	private DeployExampleService deployExampleService;
	private BusiHostService busiHostService;
	private TbBusiSysTreeService tbBusiSysTreeService;
	private AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setTbBusiSysTreeService(TbBusiSysTreeService tbBusiSysTreeService) {
		this.tbBusiSysTreeService = tbBusiSysTreeService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setTbCloudAppInfoService(TbCloudAppInfoService tbCloudAppInfoService) {
		this.tbCloudAppInfoService = tbCloudAppInfoService;
	}

	public void setBusiTreeService(BusiTreeService busiTreeService) {
		this.busiTreeService = busiTreeService;
	}

	public void setBizSystemService(BizSystemService bizSystemService) {
		this.bizSystemService = bizSystemService;
	}

	private AppMessageService appMessageService;

	public void setAppMessageService(AppMessageService appMessageService) {
		this.appMessageService = appMessageService;
	}

}
