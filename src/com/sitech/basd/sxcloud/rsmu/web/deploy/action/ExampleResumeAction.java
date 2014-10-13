package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.ExampleResumeObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.ExampleResumeService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.ExampleResumeForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("serial")
public class ExampleResumeAction extends CRUDBaseAction {

	/**
	 * @Title:查询出所有升级恢复信息
	 * @Copyright: Copyright (c) 201009
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String ExampleResumeList() {
		if (theForm == null) {
			theForm = new ExampleResumeForm();
		}
		ExampleResumeObj obj = new ExampleResumeObj();

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

		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// TbSysUserinfoObj userInfoObj =
		// (TbSysUserinfoObj)ServletActionContext.getRequest().getSession().getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String) session.get("datau"));
		List list = exampleResumeService.queryForListByObj(obj);
		theForm.setResultList(list);
		theForm.setAppList(appList); // 应用集合
		return LIST;
	}

	// 定时来获取升级的状态
	@SuppressWarnings("unchecked")
	public String Example_RESUME_Flag() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		ExampleResumeObj obj = new ExampleResumeObj();
		obj.setLONGID(id);
		ArrayList<ExampleResumeObj> reployProcessList = (ArrayList<ExampleResumeObj>) exampleResumeService
				.queryForListByObj(obj);
		for (int i = 0; i < reployProcessList.size(); i++) {
			// String ids[]=id.split(",");
			ExampleResumeObj upgradeExample = (ExampleResumeObj) reployProcessList.get(i);
			String flag = new Integer(upgradeExample.getRESUME_FLAG()).toString();

			String str = "";
			if (flag != null && !"".equals(flag)) {
				if (flag.equals("0")) {
					upgradeExample.setRESUME_FLAG_NAME("未恢复");
					str += "<input type=\"button\" class=\"thickbox btn-style02\" value=\"恢复\" ";
					str += "onclick=\"resumeRequest(" + upgradeExample.getID()
							+ ");return false;\" />&nbsp;";
				} else if (flag.equals("1")) {
					upgradeExample.setRESUME_FLAG_NAME("<img src=\"" + request.getContextPath()
							+ "/sxcloud/images/ajax-loader.gif\" width='15' height='18'/>"
							+ upgradeExample.getRESUME_PERCENT());
					// str+="<input type=\"button\" class=\"thickbox btn-style02\" value=\"升级\" disabled=\"disabled\" ";
					// str+="onclick=\"upgradeRequest("+upgradeExample.getID()+");return false;\" />&nbsp;";
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
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=gb2312");
		JSONArray ja = new JSONArray();
		ja = JSONArray.fromObject(reployProcessList);
		// PrintWriter out=response.getWriter();
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
	public String delExampleResume() {

		if (theForm == null) {
			theForm = new ExampleResumeForm();
		}
		ExampleResumeObj obj = new ExampleResumeObj();
		obj.setID(theForm.getID());
		exampleResumeService.deleteByObj(obj);
		return "";
	}

	/**
	 * @Title:恢复操作
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String resumeRequest() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		ExampleResumeObj obj = new ExampleResumeObj();
		obj.setID(Integer.parseInt(id));
		int count = exampleResumeService.resumeByObj(obj);
		HttpServletResponse response = Struts2Utils.getResponse();
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
	public String ResumeFlagStatus() {

		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("ID");
		ExampleResumeObj obj = new ExampleResumeObj();
		obj.setID(Integer.parseInt(id));
		ExampleResumeObj tempObj = (ExampleResumeObj) exampleResumeService.queryForListByObj(obj)
				.get(0);
		if (tempObj != null) {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=gb2312");
			// out = response.getWriter();
			// out.print(tempObj.getRESUME_FLAG());
			// out.close();
			PrintWriterOut.printWirter(response, tempObj.getRESUME_FLAG());
		}
		return null;
	}

	private ExampleResumeForm theForm;

	public ExampleResumeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ExampleResumeForm theForm) {
		this.theForm = theForm;
	}

	ExampleResumeService exampleResumeService;
	AppService appService;

	public void setExampleResumeService(ExampleResumeService exampleResumeService) {
		this.exampleResumeService = exampleResumeService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

}
