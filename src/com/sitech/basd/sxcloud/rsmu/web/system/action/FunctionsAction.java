package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.cloud3.service.newui.NewUIFuncService;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.system.FunctionsService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.FunctionsForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public class FunctionsAction extends CRUDBaseAction {
	private FunctionsForm theForm;

	public FunctionsForm getTheForm() {
		return theForm;
	}

	public void setTheForm(FunctionsForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listFunctions() throws BaseException {
		if (theForm == null) {
			theForm = new FunctionsForm();
		}
		theForm.setID(0);
		TbSysFunctionsObj obj = new TbSysFunctionsObj();
		if (theForm.getFUNNAME() != null && !"".equals(theForm.getFUNNAME())) {
			obj.setFUNNAME(theForm.getFUNNAME().trim());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		if (theForm.getFUNCID() != null && !"".equals(theForm.getFUNCID())) {
			obj.setFUNCID(theForm.getFUNCID());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List functionsList = functionsService.queryForListByObj(obj);
		theForm.setResultList(functionsList);
		return LIST;

	}

	/**
	 * @Title:增加功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String addFunctions() throws BaseException {
		theForm = new FunctionsForm();
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("oper", "0");
		return ADD;
	}

	/**
	 * @Title:保存功能管理模块请求
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveFunctions() throws BaseException {
		if (theForm == null) {
			theForm = new FunctionsForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();

		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		TbSysFunctionsObj obj = new TbSysFunctionsObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		String pic_path = request.getParameter("ICON");
		Map<String, String> map = new HashMap<String, String>();
		map.put("LOGINID", session.get("account").toString());
		map.put("FUNCID", obj.getFUNCID());
		Map picMap = new HashMap();
		picMap.put("FUNCID", obj.getFUNCID());
		if (pic_path == null || "".equals(pic_path)) {
			picMap.put("PIC_PATH", "/newUI/newUI/images/icon_08.png");
		} else {
			picMap.put("PIC_PATH", pic_path);
		}
		if (theForm.getID() == 0) {
			ret = functionsService.insertByObj(obj);
			newUIFuncService.insertNewUIUserFunc(map);
			newUIFuncService.insertNewUIPicFunc(picMap);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增功能信息");
		} else {
			TbSysFunctionsObj mid = new TbSysFunctionsObj();
			mid.setID(obj.getID());
			mid = functionsService.queryByObj(mid);
			map.put("OLD_MODULE", mid.getFUNCID());
			picMap.put("OLD_MODULE", mid.getFUNCID());
			ret = functionsService.updateByObj(obj);
			// 若为二级节点则更新三级节点
			updateThreeNodeState(obj);
			newUIFuncService.updateNewUIPicFunc(picMap);
			newUIFuncService.updateNewUIUserFunc(map);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改功能信息");
		}
		if (ret > 0) {
			result = 1;
		}
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirect";
	}

	/**
	 * 
	 * @Title: updateThreeNodeState
	 * @Description: 如果是二级节点设置为无效，则三级节点同样设置为无效
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 上午10:09:00
	 */
	private void updateThreeNodeState(TbSysFunctionsObj tbSysFunctionsObj) {
		String funcid = tbSysFunctionsObj.getFUNCID();
		String funStatus = tbSysFunctionsObj.getSTATUS();
		String twoRegex = "^[0-9]{3}[1-9]000000";
		String threeRegex = "^[0-9]{5}[1-9]0000";
		if (funcid.matches(twoRegex)) {// 若当前为二级菜单
			String subFuncid = funcid.substring(0, 4);
			List<TbSysFunctionsObj> threeNodeList = functionsService.queryThreeFuncNode(subFuncid);
			if (threeNodeList != null && threeNodeList.size() > 0) {
				for (TbSysFunctionsObj threeNode : threeNodeList) {
					if (!funStatus.equals(threeNode.getSTATUS())) {
						threeNode.setSTATUS(funStatus);
						functionsService.updateByObj(threeNode);
					}
				}
			}
		}
	}

	/**
	 * @Title:修改功能管理模块信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String modFunctions() throws BaseException {
		if (theForm == null) {
			theForm = new FunctionsForm();
		}

		TbSysFunctionsObj obj = new TbSysFunctionsObj();
		obj.setID(theForm.getID());
		TbSysFunctionsObj tempObj = functionsService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("oper", "1");
		return ADD;
	}

	/**
	 * @Title:删除功能管理模块请求
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delFunctions() throws BaseException {
		if (theForm == null) {
			theForm = new FunctionsForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		// TbSysUserinfoObj user = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		TbSysFunctionsObj obj = new TbSysFunctionsObj();
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
		obj = functionsService.queryByObj(obj);
		int ret = functionsService.deleteByObj(obj);

		if (ret > 0) {
			result = 1;
			Map map = new HashMap();
			map.put("FUNCID", obj.getFUNCID());
			map.put("LOGINID", session.get("account").toString());
			newUIFuncService.deleteNewUIUserFunc(map);
			newUIFuncService.deleteNewUIPicFunc(map);
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(Struts2Utils.getRequest());
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除功能信息");
		operObj.setREMARK("");
		operObj.setRESULT(result);
		@SuppressWarnings("unused")
		int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "redirect";
	}

	/**
	 * 验证功能编号的唯一性
	 * 
	 * @return
	 */
	public String validateFunc() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String FUNCID = request.getParameter("FUNCID");

		String id = request.getParameter("id");
		String result = "";
		if (id.equals("0")) {// 新增
			TbSysFunctionsObj obj = new TbSysFunctionsObj();
			obj.setFUNCID(FUNCID);
			obj = functionsService.queryByObj(obj);
			if (obj != null) {
				result = "{'result':'-1'}";
			} else {
				result = "{'result':'1'}";
			}
		} else {
			TbSysFunctionsObj t = new TbSysFunctionsObj();
			t.setID(Integer.parseInt(id));
			t = functionsService.queryByObj(t);
			if (FUNCID.equals(t.getFUNCID())) {// 序号未修改
				result = "{'result':'1'}";
			} else {
				TbSysFunctionsObj tb = new TbSysFunctionsObj();
				tb.setFUNCID(FUNCID);
				tb = functionsService.queryByObj(tb);
				if (tb != null) {
					result = "{'result':'-1'}";
				} else {
					result = "{'result':'1'}";
				}
			}
		}

		/** 将返回值装到JSON传到页面 */
		response.setContentType("type/html;charset=UTF-8");
		JSONObject jo = JSONObject.fromObject(result);
		// out = response.getWriter();
		// out.println(jo.toString());
		// out.close();
		PrintWriterOut.printWirter(response, jo.toString());
		return null;
	}

	/**
	 * 
	 * @Title: listIcon
	 * @Description: 进入展示图标页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Apr 18, 2013 4:15:27 PM
	 */
	public String listIcon() {
		return "icon";
	}

	private FunctionsService functionsService;
	private NewUIFuncService newUIFuncService;

	public void setNewUIFuncService(NewUIFuncService newUIFuncService) {
		this.newUIFuncService = newUIFuncService;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

}
