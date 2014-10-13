package com.sitech.basd.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;

/**
 * bomc dan dian deng lu
 * 
 * @author Administrator
 * 
 */
@Service("bomcInterceptor")
public class BomcInterceptor extends AbstractInterceptor {

	@Autowired
	private LoginService LoginService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation aInvocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userLogin = (String) request.getSession().getAttribute(
				"edu.yale.its.tp.cas.client.filter.user");
		logger.info("BOMC获取的登陆用户为：" + userLogin
				+ "________________________________________________");

//		String admins = PropertiesUtil.getValue("properties/admin_authority.properties", "admin");

		String name = null;
		TbSysUserinfoObj obj = new TbSysUserinfoObj();
//		if (admins != null && admins.contains(userLogin.trim())) {
//			obj.setACCOUNT(userLogin.trim());
//			logger.info("登陆用户为超级管理员用户：" + userLogin
//					+ "________________________________________________");
//			TbSysUserinfoObj temObj = LoginService.queryByObj(obj);
//			name = temObj.getNAME();
//			userLogin = "admin";
//		}

		obj.setACCOUNT(userLogin.trim());
		TbSysUserinfoObj tempObj = LoginService.queryByObj(obj);
		logger.info("登陆用户为：" + userLogin + "________________________________________________");
		HttpSession session = request.getSession();
		if (tempObj != null) {
			session.setAttribute(Constant.LOGID_SESSION_KEY, 1);
			session.setAttribute("id", tempObj.getID());
			session.setAttribute("name", name == null ? tempObj.getNAME() : name);
			session.setAttribute("account", tempObj.getACCOUNT());
			session.setAttribute("datau", tempObj.getDATAAUTHORITY());

			session.setAttribute(Constant.REQUEST_HOST, request.getRemoteAddr());
			session.setAttribute(Constant.REQUEST_PORT, request.getRemotePort());
			session.setAttribute(Constant.LOCAL_HOST, request.getLocalAddr());
			session.setAttribute(Constant.LOCAL_PORT, request.getLocalPort());
		} else {
			logger.info("登陆失败,无此用户;" + userLogin);
		}
		return aInvocation.invoke();
	}

}
