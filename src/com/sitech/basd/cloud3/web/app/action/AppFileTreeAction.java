package com.sitech.basd.cloud3.web.app.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.cloud3.domain.app.TbCloud3AppFileTreeVO;
import com.sitech.basd.cloud3.service.app.TbCloud3AppFileTreeService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: AppFileTreeAction
 * </p>
 * <p>
 * Description: 应用部署文件列表树
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-3-17 上午11:14:13
 * 
 */
public class AppFileTreeAction extends CRUDBaseAction {
	private TbCloud3AppFileTreeService tbCloud3AppFileTreeService;

	public void setTbCloud3AppFileTreeService(TbCloud3AppFileTreeService tbCloud3AppFileTreeService) {
		this.tbCloud3AppFileTreeService = tbCloud3AppFileTreeService;
	}

	/**
	 * 
	 * @Title: appFileTree
	 * @Description: 进入到树页面
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-17 下午12:07:38
	 */
	public String appFileTree() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String appId = request.getParameter("appId");
		request.setAttribute("appId", appId);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: asyncFileTree
	 * @Description: 异步获取树数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-3-17 下午12:09:32
	 */
	public String asyncFileTree() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String appId = request.getParameter("appId");
		TbCloud3AppFileTreeVO vo = new TbCloud3AppFileTreeVO();
		vo.setAppId(appId);
		if (id != null) {
			vo.setParent_id(id);
		} else {
			vo.setParent_id("0");
		}
		List<TbCloud3AppFileTreeVO> resultVoList = tbCloud3AppFileTreeService
				.queryVOListByParentID(vo);
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(resultVoList);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		PrintWriterOut.printWirter(response, json.toString());
		// p.close();
		return null;
	}
}
