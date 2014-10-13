package action.login4A;
import java.io.IOException;

/**
 * <p>Title: LoginFor4AAction</p>
 * <p>Description: CT域4A单点登录action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author zhoucang
 * @version 1.0
 * @createtime 2014-8-4 下午4:24:18
 *
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.ultrapower.casp.client.LoginUtil;
import com.ultrapower.casp.common.code.ResultCode;
import com.ultrapower.casp.common.code.ResultCodeMess;
import com.ultrapower.casp.common.datatran.data.ticket.TransferTicket;
import com.ultrapower.casp.common.datatran.data.user.UserInfo;



import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import service.login4A.Login4AService;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysLoginLogObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUsergroupObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.LoginService;
import com.sitech.basd.sxcloud.rsmu.service.system.UsergroupService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.system.form.UsergroupForm;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.session.UserSession;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;


@Component("loginCT4AAction")
@Scope("prototype")
public class LoginForCT4AAction extends BaseAction{
	
	  private static final long serialVersionUID = 1L;
	  
	  @Autowired
		private LoginService loginService;
		
		@Autowired
		private Login4AService login4aService;

		@Autowired
		private UsergroupService usergroupService;
		
	
	


	  public String login() throws BaseException {
		  
		  String login_result = "fail4a";
		  String returnMsg = "LoginUtil.getInstance().checkTicket(request) 失败 ";
		  request.setAttribute("fail", returnMsg);
		  
		  String path = request.getSession().getServletContext().getRealPath("/chongqing/login4A/casp_client_config.properties");
		   /*系统启动时要初始化配置*/ 
		  System.out.println("它的路径path:"+path);
		  LoginUtil.getInstance().init(path);
		 
		  // HttpServletRequest request = Struts2Utils.getRequest();
	        if (LoginUtil.getInstance().isEnable()) {
	            if (LoginUtil.getInstance().checkTicket(request)) {
	                String strTic = LoginUtil.getInstance().getTicket(request);
	                System.out.println("票据是："+strTic);
	                TransferTicket ticket = LoginUtil.getInstance().analysTicket(strTic);
	                System.out.println("getRetCode："+ticket.getRetCode()+"getServerId："+ticket.getServerId()+ "acc" +ticket.getSlaveAcc());
	          
	                if (ticket != null && ticket.getRetCode() != null && ResultCode.RESULT_OK.equals(ticket.getRetCode())) {
	                    UserInfo userInfo = LoginUtil.getInstance().qryUserByTicket(ticket);
	                    if (!ResultCode.RESULT_OK.equals(userInfo.getRetCode())) {
	                        // 跳转到错误页面，显示错误码；
	                    	returnMsg = LoginUtil.getInstance().getCodeMess(userInfo.getRetCode(),
	                                request.getLocale().getLanguage());
	                    
	                    	request.setAttribute("fail", returnMsg);
	                        login_result = "fail4a";
	                    } else {

	                    	// 应用资源根据帐号信息做登录后业务处理；
	                    	Object account = request.getSession().getAttribute("account");
	
	        				if (account == null) {
	        					pushSession(userInfo.getAccountID());
	        				}
	        				login_result = "success";
	        				returnMsg=null;
	                    	
	                    }
	                } else {
	                    // 跳转到错误页面，显示错误码；
	                	returnMsg="票据验证ticket != null && ticket.getRetCode() != null && ResultCode.RESULT_OK.equals(ticket.getRetCode())失败";
	                	request.setAttribute("fail", returnMsg);
	                	login_result = "fail4a";
	                }
	                
	                //return
	               
	            }
	           
	        } else {
	            // 使用应用资源本地认证；
	        	login_result = "login";
				returnMsg=null;
	        	
	        }
	        
	        return login_result;
	    }
	  
		private void initSession(HttpServletRequest request, int loginId, TbSysUserinfoObj tempObj) {
			HttpSession session = request.getSession();
			session.setAttribute(Constant.LOGID_SESSION_KEY, loginId);
			session.setAttribute("id", tempObj.getID());
			session.setAttribute("account", tempObj.getACCOUNT());
			session.setAttribute("name", tempObj.getNAME());
			session.setAttribute("datau", tempObj.getDATAAUTHORITY());
			session.setAttribute(Constant.REQUEST_HOST, request.getRemoteAddr());
			session.setAttribute(Constant.REQUEST_PORT, request.getRemotePort());
			session.setAttribute(Constant.LOCAL_HOST, request.getLocalAddr());
			session.setAttribute(Constant.LOCAL_PORT, request.getLocalPort());
			UserSession.setHttpSession(session);
		}
		
		
		
		private void pushSession(String account){
			
			if(null==account||"".equals(account.trim())){
				throw new RuntimeException("接收的报文信息不全，缺少appAccid");
			}
			String result = "1";
			
			TbSysUserinfoObj obj = new TbSysUserinfoObj();
			obj.setACCOUNT(account);
			TbSysUserinfoObj tempObj = loginService.queryByObj(obj);
			TbSysLoginLogObj loginLogObj = new TbSysLoginLogObj();
			loginLogObj.setACCOUNT(tempObj.getACCOUNT());
			try {
				loginLogObj.setHOSTNAME(java.net.Inet4Address.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				LogHelper
						.info("IP:" + request.getRemoteAddr() + " 获取HOSTNAME失败" + getClass().getName());
			}
			loginLogObj.setIP(request.getRemoteAddr());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			loginLogObj.setLOGINTIME(format.format(new Date()));
			loginLogObj.setREMARK("");
			loginLogObj.setRESULT(result);
			loginLogObj.setUSERID(tempObj.getID());
			int loginId = loginService.insertByObj(loginLogObj);
			
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					Cookie cookie = request.getCookies()[0];// 获取cookie
					cookie.setMaxAge(0);// 让cookie过期
				}
			}
			request.getSession(true);
			
			initSession(request, loginId, tempObj);
			
			// 对用户权限进行判断
			String leader = loginService.queryLeaderOrNotByUid(tempObj.getID());
			if (null != leader && "1".equals(leader)) {
				request.getSession().setAttribute(Constant.LEADER_SESSION_KEY, true);
			}
		}
		
		
}
