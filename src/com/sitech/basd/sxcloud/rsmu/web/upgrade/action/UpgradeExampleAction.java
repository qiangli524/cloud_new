package com.sitech.basd.sxcloud.rsmu.web.upgrade.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleObj;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.upgrade.UpgradeExampleService;
import com.sitech.basd.sxcloud.rsmu.service.upgrade.UpgradeStrategyService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.upgrade.form.UpgradeExampleForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class UpgradeExampleAction extends CRUDBaseAction {

	/**
	 * @Title:查询出所有升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String upgradeExampleList() throws BaseException {
		if (theForm == null) {
			theForm = new UpgradeExampleForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		UpgradeExampleObj obj = new UpgradeExampleObj();

		if (theForm.getAPPID() != 0) {
			obj.setAPPID(theForm.getAPPID());
		}
		if (theForm.getHOSTNAME() != null && !"".equals(theForm.getHOSTNAME())) {
			obj.setHOSTNAME(theForm.getHOSTNAME());
		}
		if (theForm.getSTART_STOP_FLAG() != 0) {
			obj.setSTART_STOP_FLAG(theForm.getSTART_STOP_FLAG());
		}

		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List list = upgradeExampleService.queryForListByObj(obj);
		theForm.setResultList(list);
		theForm.setAppList(appList); // 应用集合
		return LIST;
	}

	// 定时来获取升级的状态
	@SuppressWarnings("unchecked")
	public String Example_UPGRADE_Flag() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setLONGID(id);
		ArrayList<UpgradeExampleObj> reployProcessList = (ArrayList<UpgradeExampleObj>) upgradeExampleService
				.queryListIDByObj(obj);
		for (int i = 0; i < reployProcessList.size(); i++) {
			// String ids[]=id.split(",");
			UpgradeExampleObj upgradeExample = (UpgradeExampleObj) reployProcessList.get(i);
			String flag = new Integer(upgradeExample.getUPGRADE_FLAG()).toString();
			String start_stop_flag = new Integer(upgradeExample.getSTART_STOP_FLAG()).toString();

			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("0")) {
					upgradeExample.setUPGRADE_FLAG_NAME("未升级");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"升级\" ";
					str += "onclick=\"upgradeRequest(" + upgradeExample.getID()
							+ ");return false;\" />&nbsp;";
				} else if (flag.equals("1")) {
					upgradeExample
							.setUPGRADE_FLAG_NAME("<img src='./images/ajax-loader.gif' width='15' height='18'/>正在升级"
									+ upgradeExample.getUPGRADE_PERCENT() + "");
					// str+="<input type=\"button\" class=\"thickbox
					// btn-style02\" value=\"升级\" disabled=\"disabled\" ";
					// str+="onclick=\"upgradeRequest("+upgradeExample.getID()+");return
					// false;\" />&nbsp;";
				} else if (flag.equals("2")) {
					upgradeExample.setUPGRADE_FLAG_NAME("已升级");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"升级\" ";
					str += "onclick=\"upgradeRequest(" + upgradeExample.getID()
							+ ");return false;\" />&nbsp;";
				}
			}
			if (start_stop_flag != null && !"".equals(start_stop_flag)) {
				if (start_stop_flag.equals("0")) {
					upgradeExample
							.setSTART_STOP_FLAG_NAME("<img src='./images/ajax-loader.gif' width='15' height='18'/>正在停止");
				} else if (start_stop_flag.equals("1")) {
					upgradeExample.setSTART_STOP_FLAG_NAME("已停止");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"启动\" ";
					str += "onclick=\"startRequest(" + upgradeExample.getID()
							+ ");return false;\" />";
				} else if (start_stop_flag.equals("2")) {
					upgradeExample
							.setSTART_STOP_FLAG_NAME("<img src='./images/ajax-loader.gif' width='15' height='18'/>正在启动");
				} else if (start_stop_flag.equals("3")) {
					upgradeExample.setSTART_STOP_FLAG_NAME("已启动");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"停止\" ";
					str += "onclick=\"stopRequest(" + upgradeExample.getID()
							+ ");return false;\" />";
				}
			}
			upgradeExample.setUPGRADE_FLAG_AN(str);
			reployProcessList.set(i, upgradeExample);
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

	/**
	 * @Title:删除升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String delUpgradeExample() throws BaseException {

		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(theForm.getID());
		/*
		 * UpgradeExampleObj objTemp = upgradeExampleService.queryByObj(obj); if
		 * (objTemp.getUPGRADE_FLAG() == 1) {
		 * theForm.setError_msg("对不起，该条数据为升级或者已升级状态，不能删除!!!"); return
		 * upgradeExampleList(mapping, form, request, response); }
		 */
		upgradeExampleService.deleteByObj(obj);
		return "del";
	}

	/**
	 * @Title:新增升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String addUpgradeExample() throws BaseException {

		TbBusiHostObj obj = new TbBusiHostObj();
		List hostList = busiHostService.queryForListByObj(obj);
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		theForm.setHostList(hostList);
		theForm.setAppList(appList);
		return ADD;
	}

	/**
	 * @Title:保存新增升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureAddUpgradeExample() throws BaseException {

		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setHOSTID(theForm.getHOSTID());
		obj.setAPPID(theForm.getAPPID());
		obj.setAPPPORT(theForm.getAPPPORT());
		obj.setKEYNAME(theForm.getKEYNAME());
		// obj.setUPGRADE_FLAG(0);
		// obj.setSTART_STOP_FLAG(1);
		upgradeExampleService.insertByObj(obj);
		return "save_add";
	}

	/**
	 * @Title:修改升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String modUpgradeExample() throws BaseException {

		UpgradeExampleObj obj = new UpgradeExampleObj();
		TbSysAppObj appObj = new TbSysAppObj();
		TbBusiHostObj obj1 = new TbBusiHostObj();
		obj.setID(theForm.getID());
		UpgradeExampleObj objTemp = upgradeExampleService.queryByObj(obj);
		/*
		 * if (objTemp.getUPGRADE_FLAG() == 1) {
		 * theForm.setError_msg("对不起，该条数据为升级或者已升级状态，不能修改!!!"); return
		 * upgradeExampleList(mapping, form, request, response); }
		 */
		List hostList = busiHostService.queryForListByObj(obj1);
		List appList = appService.queryForListByObj(appObj);
		theForm.setID(objTemp.getID());
		theForm.setHOSTID(objTemp.getHOSTID());
		theForm.setAPPID(objTemp.getAPPID());
		theForm.setHostList(hostList);
		theForm.setAppList(appList);
		theForm.setKEYNAME(objTemp.getKEYNAME());
		theForm.setAPPPORT(objTemp.getAPPPORT());
		return MODIFY;
	}

	/**
	 * @Title:保存修改升级信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureModUpgradeExample() throws BaseException {

		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(theForm.getID());
		obj.setHOSTID(theForm.getHOSTID());
		obj.setAPPID(theForm.getAPPID());
		obj.setAPPPORT(theForm.getAPPPORT());
		obj.setKEYNAME(theForm.getKEYNAME());
		upgradeExampleService.updateByObj(obj);
		return "save_mod";
	}

	/**
	 * @Title:升级操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String upgradeRequest() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(Integer.parseInt(id));
		int count = upgradeExampleService.upgradeByObj(obj);
		response.setContentType("text/html; charset=gb2312");
		// out = response.getWriter();
		// out.print(count);
		// out.flush();
		// out.close();
		PrintWriterOut.printWirter(response, count);
		return null;
	}

	/**
	 * @Title:启动应用操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String startRequest() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(Integer.parseInt(id));
		int count = upgradeExampleService.startByObj(obj);
		response.setContentType("text/html; charset=gb2312");
		// out = response.getWriter();
		// out.print(count);
		// out.flush();
		// out.close();
		PrintWriterOut.printWirter(response, count);
		return null;
	}

	/**
	 * @Title:停止应用操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String stopRequest() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(Integer.parseInt(id));
		int count = upgradeExampleService.stopByObj(obj);
		response.setContentType("text/html; charset=gb2312");
		// out = response.getWriter();
		// out.print(count);
		// out.flush();
		// out.close();
		PrintWriterOut.printWirter(response, count);
		return null;
	}

	/*
	 * 通过jquary获取升级状态信息
	 */
	public String UpgradeFlagStatus() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleObj obj = new UpgradeExampleObj();
		obj.setID(Integer.parseInt(id));
		UpgradeExampleObj tempObj = upgradeExampleService.queryByObj(obj);
		if (tempObj != null) {
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(tempObj.getUPGRADE_FLAG());
			// out.close();
			PrintWriterOut.printWirter(response, tempObj.getUPGRADE_FLAG());
		}
		return null;
	}

	UpgradeExampleService upgradeExampleService;

	public void setUpgradeExampleService(UpgradeExampleService upgradeExampleService) {
		this.upgradeExampleService = upgradeExampleService;
	}

	BusiHostService busiHostService;

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	UpgradeStrategyService upgradeStrategyService;

	public void setUpgradeStrategyService(UpgradeStrategyService upgradeStrategyService) {
		this.upgradeStrategyService = upgradeStrategyService;
	}

	AppService appService;

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	private UpgradeExampleForm theForm;

	public UpgradeExampleForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UpgradeExampleForm theForm) {
		this.theForm = theForm;
	}

}
