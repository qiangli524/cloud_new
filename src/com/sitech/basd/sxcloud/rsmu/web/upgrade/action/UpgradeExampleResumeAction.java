package com.sitech.basd.sxcloud.rsmu.web.upgrade.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.upgrade.UpgradeExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.upgrade.UpgradeExampleResumeService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.upgrade.form.UpgradeExampleResumeForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class UpgradeExampleResumeAction extends CRUDBaseAction {

	/**
	 * @Title:查询出所有升级恢复信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String upgradeExampleResumeList() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		if (theForm == null) {
			theForm = new UpgradeExampleResumeForm();
		}
		if (theForm.getAPPID() != 0) {
			obj.setAPPID(theForm.getAPPID());
		}
		if (theForm.getIP() != null && !"".equals(theForm.getIP())) {
			obj.setIP(theForm.getIP());
		}
		if (theForm.getRESUME_FLAG() != 0) {
			obj.setRESUME_FLAG(theForm.getRESUME_FLAG());
		}

		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);

		obj.setPagination(this.getPaginater().initPagination(request));// 分页
		// TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj)
		// request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List list = upgradeExampleResumeService.queryForListByObj(obj);
		theForm.setResultList(list);
		theForm.setAppList(appList); // 应用集合
		return LIST;
	}

	// 定时来获取升级的状态
	@SuppressWarnings("unchecked")
	public String Example_RESUME_Flag() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		obj.setLONGID(id);
		ArrayList<UpgradeExampleResumeObj> reployProcessList = (ArrayList<UpgradeExampleResumeObj>) upgradeExampleResumeService
				.queryForListByObj(obj);
		for (int i = 0; i < reployProcessList.size(); i++) {
			// String ids[]=id.split(",");
			UpgradeExampleResumeObj upgradeExample = (UpgradeExampleResumeObj) reployProcessList
					.get(i);
			String flag = new Integer(upgradeExample.getRESUME_FLAG()).toString();

			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("0")) {
					upgradeExample.setRESUME_FLAG_NAME("未恢复");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"恢复\" ";
					str += "onclick=\"resumeRequest(" + upgradeExample.getID()
							+ ");return false;\" />&nbsp;";
				} else if (flag.equals("1")) {
					upgradeExample.setRESUME_FLAG_NAME("<img src='" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif' width='15' height='18'/>"
							+ upgradeExample.getRESUME_PERCENT());
					// str+="<input type=\"button\" class=\"thickbox
					// btn-style02\" value=\"升级\" disabled=\"disabled\" ";
					// str+="onclick=\"upgradeRequest("+upgradeExample.getID()+");return
					// false;\" />&nbsp;";
				} else if (flag.equals("2")) {
					upgradeExample.setRESUME_FLAG_NAME("已恢复");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"恢复\" ";
					str += "onclick=\"resumeRequest(" + upgradeExample.getID()
							+ ");return false;\" />&nbsp;";
				}
			}
			upgradeExample.setRESUME_FLAG_AN(str);
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
	 * @Title:删除升级恢复信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public String delUpgradeExampleResume() throws BaseException {

		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		obj.setID(theForm.getID());
		/*
		 * UpgradeExampleObj objTemp = upgradeExampleService.queryByObj(obj); if
		 * (objTemp.getUPGRADE_FLAG() == 1) {
		 * theForm.setError_msg("对不起，该条数据为升级或者已升级状态，不能删除!!!"); return
		 * upgradeExampleList(mapping, form, request, response); }
		 */
		upgradeExampleResumeService.deleteByObj(obj);
		return "del";
	}

	/**
	 * @Title:恢复操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String resumeRequest() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		obj.setID(Integer.parseInt(id));
		int count = upgradeExampleResumeService.resumeByObj(obj);
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
	public String ResumeFlagStatus() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String id = request.getParameter("ID");
		UpgradeExampleResumeObj obj = new UpgradeExampleResumeObj();
		obj.setID(Integer.parseInt(id));
		UpgradeExampleResumeObj tempObj = (UpgradeExampleResumeObj) upgradeExampleResumeService
				.queryForListByObj(obj).get(0);
		if (tempObj != null) {
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(tempObj.getRESUME_FLAG());
			// out.close();
			PrintWriterOut.printWirter(response, tempObj.getRESUME_FLAG());
		}
		return null;
	}

	UpgradeExampleResumeService upgradeExampleResumeService;
	AppService appService;
	private UpgradeExampleResumeForm theForm;

	public UpgradeExampleResumeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UpgradeExampleResumeForm theForm) {
		this.theForm = theForm;
	}

	public void setUpgradeExampleResumeService(
			UpgradeExampleResumeService upgradeExampleResumeService) {
		this.upgradeExampleResumeService = upgradeExampleResumeService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

}
