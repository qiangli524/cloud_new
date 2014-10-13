package com.sitech.basd.sxcloud.rsmu.web.softmanage.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiAppPortObj;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppPortService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class AppPortAction extends CRUDBaseAction {
	/**
	 * @Title:查询应用端口信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modAppPort() {

		TbBusiAppPortObj obj = new TbBusiAppPortObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String port = request.getParameter("port");
		obj.setPORT(port);
		TbBusiAppPortObj tempObj = appPortService.queryByObj(obj);
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			request.setCharacterEncoding("gb2312");// 字符串转换
			// response.setCharacterEncoding("utf-8");//字符串转换
			// PrintWriter out=response.getWriter();
			if (tempObj == null) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("no");
				PrintWriterOut.printWirter(response, "no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @Title:删除应用端口请求
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delAppPort() {
		TbBusiAppPortObj obj = new TbBusiAppPortObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String port = request.getParameter("port");
		obj.setPORT(port);
		int ret = appPortService.deleteByObj(obj);
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			// PrintWriter out = response.getWriter();
			if (ret > 0) {
				// out.print("ok");
				PrintWriterOut.printWirter(response, "ok");
			} else {
				// out.print("err");
				PrintWriterOut.printWirter(response, "err");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据传入进来的应用id来决定 获取对应的端口
	@SuppressWarnings("unchecked")
	public String app_port() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String appid = request.getParameter("id");
		if (appid != null && !"".equals(appid.trim())) {
			ArrayList<TbBusiHostObj> bloodList = null;
			TbBusiAppPortObj obj = new TbBusiAppPortObj();
			obj.setAPPID(Integer.parseInt(appid));
			bloodList = (ArrayList<TbBusiHostObj>) appPortService.queryForListByObj(obj);
			// 存入json
			HttpServletResponse response = Struts2Utils.getResponse();
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

	private AppPortService appPortService;

	public void setAppPortService(AppPortService appPortService) {
		this.appPortService = appPortService;
	}

}
