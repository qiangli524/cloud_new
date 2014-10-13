package com.sitech.basd.sxcloud.cloud.web.cubinetmanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.cloud.domain.cubinetmanage.TbCubinetObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.cubinetmanage.CubinetService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.web.cubinetmanage.form.CubinetManageForm;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.formater.Formater;
import com.sitech.utils.servlet.PrintWriterOut;

public class CubinetManageAction extends CRUDBaseAction {

	/**
	 * @Title:获取机柜列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String getCubinetHostList() throws BaseException {
		if (theForm == null) {
			theForm = new CubinetManageForm();
		}
		TbCubinetObj obj = new TbCubinetObj();
		if (theForm.getC_type() != null && !"".equals(theForm.getC_type())) {
			obj.setC_type(Integer.valueOf(theForm.getC_type()));
		}
		if (theForm.getC_name() != null && !"".equals(theForm.getC_name())) {
			obj.setC_name(theForm.getC_name());
		}
		if (theForm.getR_id() != null && !"".equals(theForm.getR_id())) {
			obj.setR_id(theForm.getR_id());
		}
		/*
		 * 查询不用插操作日志表 HttpServletRequest request = Struts2Utils.getRequest();
		 * TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		 * operObj.setMESSAGE("查询"); operObj.setOPERTYPE(4);
		 * operObj.setREMARK(""); operObj.setRESULT(1);
		 * operationService.insertByObj(operObj);
		 */
		List userInfoList = cubinetService.queryForListByObj(obj); // 机柜列表

		// IBM Power
		List IBM_PowerList = new ArrayList();
		// IBM小刀
		List IBM_KnifeList = new ArrayList();
		// HP小刀
		List HP_KnifeList = new ArrayList();
		// 磁带库
		List Tape_LibraryList = new ArrayList();
		// 网络设备
		List Network_EquList = new ArrayList();
		// 其他设备
		List Others_EquList = new ArrayList();

		if (userInfoList != null && userInfoList.size() > 0) {
			for (int i = 0; i < userInfoList.size(); i++) {
				TbCubinetObj tempObj = (TbCubinetObj) userInfoList.get(i);
				if (tempObj.getC_type() != null && !tempObj.getC_type().equals("")) {
					// tempObj.setCanDelete(this.cubinetService.queryTbHostCountForCanDelete(tempObj));
					if (tempObj != null && tempObj.getC_name() != null && tempObj.getC_type() == 1) {
						IBM_PowerList.add(tempObj);
					} else if (tempObj != null && tempObj.getC_name() != null
							&& tempObj.getC_type() == 2) {
						IBM_KnifeList.add(tempObj);
					} else if (tempObj != null && tempObj.getC_name() != null
							&& tempObj.getC_type() == 3) {
						HP_KnifeList.add(tempObj);
					} else if (tempObj != null && tempObj.getC_name() != null
							&& tempObj.getC_type() == 4) {
						Tape_LibraryList.add(tempObj);
					} else if (tempObj != null && tempObj.getC_name() != null
							&& tempObj.getC_type() == 5) {
						Network_EquList.add(tempObj);
					} else {
						Others_EquList.add(tempObj);
					}
				}
			}
		}
		theForm.setRoomOptionList(cubinetService.queryTbRoomForCubSelect());
		theForm.setIbm_powerlist(IBM_PowerList);
		theForm.setIbm_knifelist(IBM_KnifeList);
		theForm.setHp_knifelist(HP_KnifeList);
		theForm.setTape_librarylist(Tape_LibraryList);
		theForm.setNetwork_equlist(Network_EquList);
		theForm.setOthers_equlist(Others_EquList);
		return LIST;
	}

	/**
	 * 
	 * @Title: 通过机柜Id查询相应的主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public String checkHostList() throws BaseException {
		if (hostForm == null) {
			hostForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String c_id = request.getParameter("c_id");
		// TbCubinetObj tempObj = new TbCubinetObj();
		// tempObj.setC_id(c_id);
		// tempObj.setC_type(Integer.parseInt(theForm.getC_type()));
		// TbCubinetObj obj = cubinetService.queryByObj(tempObj);
		// List<TbCloud2HostInfoObj> resultList =
		// hostInfoService.queryHostListById(c_id);
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setCq_id(c_id);
		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryAllHost(obj);
		hostForm.setResultList(resultList);
		// if (list != null && list.size() > 0) {
		// theForm.setList(list);
		// theForm.setC_name(obj.getC_name());
		// theForm.setC_addr(obj.getC_addr());
		// theForm.setC_type(String.valueOf(obj.getC_type()));
		// }
		return "host";
	}

	/**
	 * 
	 * @Title: 通过机柜Id查询是否存在相应的主机信息
	 * @Copyright: Copyright (c) 2011-12-14
	 * @Company: si-tech
	 * @author siweichao
	 * @version 1.0
	 */
	public String adjustCubinet() {
		int flag = 0; // 没有主机关联
		if (hostForm == null) {
			hostForm = new HostManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String c_id = request.getParameter("c_id");
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setCq_id(c_id);
		List<TbCloud2HostInfoObj> resultList = hostInfoService.queryAllHost(obj);
		HttpServletResponse httpResponse = Struts2Utils.getResponse();
		if (resultList.size() > 0 && resultList != null) {
			flag = 1;
		}
		JSONObject jo = new JSONObject();
		try {
			PrintWriter pw = httpResponse.getWriter();
			jo.put("flag", flag);
			pw.print(jo);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title:增加机柜请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public String addCubinet() throws BaseException {
		if (theForm == null) {
			theForm = new CubinetManageForm();
		}
		theForm.setR_id("");// 清空ActionForm
		theForm.setC_name("");
		theForm.setC_type("");
		theForm.setRoomOptionList(cubinetService.queryTbRoomForCubSelect());
		return ADD;
	}

	/**
	 * @Title:修改机柜请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public String modCubinet() throws BaseException {
		TbCubinetObj obj = new TbCubinetObj();
		if (theForm == null) {
			theForm = new CubinetManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String c_id = request.getParameter("c_id");
		obj.setC_id(c_id);
		TbCubinetObj tempObj = cubinetService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		theForm.setRoomOptionList(cubinetService.queryTbRoomForCubSelect());
		return MODIFY;
	}

	/**
	 * @Title:删除机柜请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 */
	public String delCubinet() throws BaseException {
		TbCubinetObj obj = new TbCubinetObj();
		if (theForm == null) {
			theForm = new CubinetManageForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String c_id = request.getParameter("c_id");
		obj.setC_id(c_id);
		@SuppressWarnings("unused")
		TbCubinetObj busihostObj = cubinetService.queryByObj(obj);
		cubinetService.deleteByObj(obj);
		return "del";
	}

	/**
	 * @Title:保存机柜请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author wangqxa
	 * @version 1.0
	 * @throws Exception
	 */
	public String saveCubinet() throws Exception {
		TbCubinetObj obj = new TbCubinetObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
			if ("".equals(theForm.getC_id()) || theForm.getC_id() == null) {
				obj.setC_id(Formater.getFixLengthStr(cubinetService.getSequence(), 4));
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(theForm.getC_id()) || theForm.getC_id() == null) { // flag是来自JS中的隐藏变量，判断是新增操作或修改操作
			cubinetService.insertByObj(obj);
		} else {
			cubinetService.updateByObj(obj);
		}

		return "save";
	}

	/**
	 * 
	 * @Title: checkHost
	 * @Description: 验证机柜是否存在关联主机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Nov 26, 2012 4:57:46 PM
	 */
	public String checkHost() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String c_id = request.getParameter("c_id");
		List<TbCloud2HostInfoObj> list = hostInfoService.queryHostListById(c_id);
		if (list != null && list.size() != 0) {
			// PrintWriter p = response.getWriter();
			// p.print("{'result':'yes'}");
			// p.close();
			PrintWriterOut.printWirter(response, "{'result':'yes'}");
		}
		return null;
	}

	private CubinetService cubinetService;

	private HostInfoService hostInfoService;
	private CubinetManageForm theForm;
	private HostManageForm hostForm;

	public CubinetManageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(CubinetManageForm theForm) {
		this.theForm = theForm;
	}

	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public CubinetService getCubinetService() {
		return cubinetService;
	}

	public void setCubinetService(CubinetService cubinetService) {
		this.cubinetService = cubinetService;
	}

	public HostManageForm getHostForm() {
		return hostForm;
	}

	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

}
