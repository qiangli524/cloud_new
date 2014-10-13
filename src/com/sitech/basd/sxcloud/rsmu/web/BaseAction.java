package com.sitech.basd.sxcloud.rsmu.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationService;
import com.sitech.basd.sxcloud.rsmu.web.util.page.Paginater;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

public abstract class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static final Logger logger = Logger.getLogger(BaseAction.class.getName());
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -1906818607776238891L;
	public final Paginater paginater = new Paginater();

	public Paginater getPaginater() {
		return paginater;
	}

	protected TbSysOperationLogObj getTbSysOperationLogObj(HttpServletRequest request) {
		TbSysOperationLogObj operObj = new TbSysOperationLogObj();
		int funcid = 0;
		Integer funcsid1 = (Integer) request.getSession().getAttribute(Constant.FUNCID);
		String funcsid = String.valueOf(funcsid1);
		if (funcsid != null && !funcsid.equals("null") && !funcsid.equals("")) {
			funcid = Integer.parseInt(funcsid);
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj)
		// request.getSession().getAttribute(
		// Constant.USER_SESSION_KEY);
		Object loginId = request.getSession().getAttribute(Constant.LOGID_SESSION_KEY);
		try {
			operObj.setLOGINID(Integer.parseInt(loginId.toString()));
			operObj.setUSERID((Integer) session.get("id"));
		} catch (Exception e) {
			operObj.setLOGINID(-1);
			operObj.setUSERID(-1);
			logger.error(e.getMessage());
		}
		operObj.setFUNCID(funcid);
		return operObj;
	}

	// public void validate(ActionMapping mapping, HttpServletRequest request,
	// HttpServletResponse response) throws MySecurityException {
	// }
	//
	// public ActionForward execute(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// ActionForward forward = mapping.findForward("default");
	// String methodName = null;
	// try {
	// String parameter = mapping.getParameter();
	// if ("*".equals(parameter)) {
	// validate(mapping, request, response);
	// } else if (parameter == null || "".equals(parameter)
	// || parameter.startsWith("#")) {
	// if (parameter != null && parameter.length() > 1) {
	// methodName = parameter.substring(1);
	// } else {
	// methodName = mapping.getPath();
	// if (methodName.length() > 1)
	// methodName = methodName.substring(methodName
	// .lastIndexOf("/") + 1);
	// }
	// if (methodName != null && methodName.length() > 0) {
	// if (methodName == null || "execute".equals(methodName)
	// || "perform".equals(methodName))
	// throw new NoSuchMethodException();
	// if (!methodName.startsWith("login")) {
	// validate(mapping, request, response);
	// }
	// if (methodName != null && methodName.startsWith("list")) {
	// TbSysOperationLogObj operObj = this
	// .getTbSysOperationLogObj(request);
	// operObj.setMESSAGE("查询");
	// operObj.setOPERTYPE(4);
	// operObj.setREMARK("");
	// operObj.setRESULT(1);
	// // operationService.insertByObj(operObj);
	// }
	// forward = dispatchMethod(mapping, form, request, response,
	// methodName);
	// }
	// } else {
	// methodName = getMethodName(mapping, form, request, response,
	// parameter);
	// if (methodName == null || "execute".equals(methodName)
	// || "perform".equals(methodName))
	// throw new NoSuchMethodException();
	// if (!methodName.startsWith("login")) {
	// validate(mapping, request, response);
	// }
	// forward = dispatchMethod(mapping, form, request, response,
	// methodName);
	//
	// }
	// if (null == request.getHeader("referer")) {
	// throw new MySecurityException("您无权限!");
	// }
	// } catch (NoSuchMethodException nsme) {
	// LogHelper.error("Struts 错误：" + getClass().getName()
	// + nsme.getMessage());
	// } catch (InvocationTargetException itex) {
	// LogHelper.error("Struts 错误：" + getClass().getName()
	// + itex.getMessage());
	// } catch (Throwable th) {
	// LogHelper.error(getClass().getName() + "Struts 错误：" + methodName
	// + ":" + th.getMessage());
	// }
	// return forward;
	// }

	protected OperationService operationService;

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public HttpServletRequest request;
	public HttpServletResponse response;
	public Map session;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		// response.setHeader("P3P","CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	// protected String getMethodName(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response,
	// String parameter) throws Exception {
	// return request.getParameter(parameter);
	// }
	/**
	 * 
	 * @Title: 弹出错误信息共用方法
	 * @Copyright:Copyright (c) Jul 12, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public void showErrorMsg(int errorFlag) {
		// PrintWriter out = null;
		HttpServletResponse response = Struts2Utils.getResponse();
		JSONObject json = new JSONObject();
		// out = response.getWriter();
		json.put("result", errorFlag);
		// out.println(json);
		// out.close();
		PrintWriterOut.printWirter(response, json);
	}

	/**
	 * @Title: filterURL
	 * @Description: 对于直接通过URL访问的进行拦截
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws MySecurityException
	 * @createtime 2013-9-26 下午4:28:02
	 */
	// protected void filterURL(HttpServletRequest request) throws
	// MySecurityException{
	// String referer = request.getHeader("referer");
	// if (referer == null) {
	// throw new MySecurityException("对不起，您无权进行此操作，请从正常的入口进入相关功能模块!");
	// }
	// }

	/**
	 * 
	 * <p>
	 * 向前台返回json数据
	 * </p>
	 * 
	 * @author xugang
	 * @param json
	 */
	public void returnJson(String json) {
		response.setContentType("text/html;charset=utf-8");
		// PrintWriter out = null;
		try {
			// out = response.getWriter();
			// out.print(json);
			// out.flush();
			PrintWriterOut.printWirter(response, json);
		} finally {
			// if (out != null) {
			// out.close();
			// }
		}
	}
}
