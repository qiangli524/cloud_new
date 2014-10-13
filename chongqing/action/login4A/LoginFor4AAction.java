package action.login4A;

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

import domain.login4A.Request4ABody;
import domain.login4A.UserRsp;

/**
 * <p>Title: LoginFor4AAction</p>
 * <p>Description: 4A单点登录action</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-5-16 下午4:24:18
 *
 */
@Component("login4AAction")
@Scope("prototype")
public class LoginFor4AAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : 序列化
	 */
	private static final long serialVersionUID = -4535626545905437360L;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private Login4AService login4aService;

	@Autowired
	private UsergroupService usergroupService;
	
	private UsergroupForm theForm;
	
	
	
	@SuppressWarnings("finally")
	public String login() throws BaseException {
		
		// 去4A校验token
		HttpServletRequest request = Struts2Utils.getRequest();
		String token = request.getParameter("token");
		String appAcctId = request.getParameter("appAcctId");
		String flag = request.getParameter("flag");
		String[] receiveInfo = {token,appAcctId,flag};
		String returnMsg = "";
		String login_result = "failure";
		try {
			String msg = login4aService.validateTokenBy4A(receiveInfo);

			UserRsp userRsp = JAXBUnmarshaller.xml2java(UserRsp.class, msg);
			if(null==userRsp){
				throw new RuntimeException("接收的报文信息不全，缺少userrsp");
			}
			Request4ABody body = userRsp.getBody();
			if(null==body){
				throw new RuntimeException("接收的报文信息不全，缺少body");
			}
			String rsp = body.getRsp();
			if(null==rsp){
				throw new RuntimeException("接收的报文信息不全，缺少rsp");
			}
			boolean isSuccess = false;
			if("0".equals(rsp.trim())){
				isSuccess = true;
			} else {
				isSuccess = false;
			}
			
			
			if (!isSuccess) {
				throw new RuntimeException("鉴权失败");
			} else {
				//判断登录信息
				Object account = request.getSession().getAttribute("account");
				if (account == null) {
					pushSession(body);
				}
				login_result = SUCCESS;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			returnMsg = e.getMessage();
			login_result = LOGIN;
		} catch (ServiceException e) {
			e.printStackTrace();
			returnMsg = e.getMessage();
			login_result = LOGIN;
		} catch (RuntimeException e) {
			e.printStackTrace();
			returnMsg = e.getMessage();
			login_result = LOGIN;
		} finally {
			request.setAttribute("returnMsg", returnMsg); 
			return login_result;
		}
		
	}
	
	/**
	 * 
	 * @Title: groupEdit
	 * @Description: 用户组操作token校验
	 * @param
	 * @return String
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-16 上午9:45:50
	 */
	@SuppressWarnings("finally")
	public String userGroupEdit(){
		// 去4A校验token
		HttpServletRequest request = Struts2Utils.getRequest();
		String token = request.getParameter("token");
		String appAcctId = request.getParameter("appAcctId");
		String flag = request.getParameter("flag");
		String[] receiveInfo = {token,appAcctId,flag};
		String returnMsg = "";
		String login_result = "failure";

		try {
			String msg = login4aService.validateTokenBy4A(receiveInfo);
			UserRsp userRsp = JAXBUnmarshaller.xml2java(UserRsp.class, msg);
			if(null==userRsp){
				throw new RuntimeException("接收的报文信息不全，缺少userrsp");
			}
			Request4ABody body = userRsp.getBody();
			if(null==body){
				throw new RuntimeException("接收的报文信息不全，缺少body");
			}
			String rsp = body.getRsp();
			if(null==rsp){
				throw new RuntimeException("接收的报文信息不全，缺少rsp");
			}
			boolean isSuccess = false;
			if("0".equals(rsp.trim())){
				isSuccess = true;
			} else {
				isSuccess = false;
			}
//			Request4ABody body = new Request4ABody();
//			boolean isSuccess = true;
			Object account = request.getSession().getAttribute("account");
			if(Constant.TEMP4AUSER.equals(account)){
				isSuccess = true;
			}
			body.setAppAcctid(Constant.TEMP4AUSER);
			if (!isSuccess) {
				throw new RuntimeException("鉴权失败");
			} else {
				//判断登录信息
				if (account == null) {
					pushSession(body);
				}
				if (theForm == null) {
					theForm = new UsergroupForm();
				}
				TbSysUsergroupObj obj = new TbSysUsergroupObj();
				if (theForm.getGROUPNAME() != null && !theForm.getGROUPNAME().equals("")) {
					obj.setGROUPNAME(theForm.getGROUPNAME());
				}
				if (theForm.getSTATUS() != -1) {
					obj.setSTATUS(theForm.getSTATUS());
				}
				obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
				List<TbSysUsergroupObj> userInfoList = usergroupService.queryForListByObj(obj);
				theForm.setResultList(userInfoList);
				login_result = "ugoupsuccess";
			}
		} catch (RemoteException e) {
			returnMsg = e.getMessage();
			e.printStackTrace();
			login_result = LOGIN;
		} catch (ServiceException e) {
			returnMsg = e.getMessage();
			e.printStackTrace();
			login_result = LOGIN;
		} catch (RuntimeException e) {
			e.printStackTrace();
			returnMsg = e.getMessage();
			login_result = LOGIN;
		} finally{
			System.out.println(login_result);
			return login_result;
		}
	}
	
	
	/**
	 * 
	 * @Title: pushSession
	 * @Description: 记录登录日志,将登录信息写入session
	 * @param body
	 * @return void
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-26 下午7:42:31
	 */
	private void pushSession(Request4ABody body){
		String account = body.getAppAcctid();
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
	

	/**
	 * 
	 * @Title: initSession
	 * @Description: 实例当前Session
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-29 上午8:32:35
	 */
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
	

	public UsergroupForm getTheForm() {
		return theForm;
	}

	public void setTheForm(UsergroupForm theForm) {
		this.theForm = theForm;
	}
	
}
