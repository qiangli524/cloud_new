package com.sitech.basd.resource.web.host;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.resource.domain.host.IPMIInfo;
import com.sitech.basd.resource.service.host.IPMIInfoService;
import com.sitech.basd.resource.service.host.PhysicalHostService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;

/**
 * 
 * <p>
 * Title: PhysicalHostAction
 * </p>
 * <p>
 * Description:物理机管理，联通测试用例新增
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date 2013 九月 10
 */
@SuppressWarnings("serial")
@Controller("physicalHostAction")
@Scope("prototype")
public class PhysicalHostAction extends CRUDBaseAction {
	private String os;
	private String host;
	@Resource
	private PhysicalHostService physicalHostService;
	@Resource
	private IPMIInfoService ipmiInfoService;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * 
	 * @Title: hostManagement
	 * @Description:进入主机管理界面
	 * @author duangh
	 * @date 2013 九月 10 14:54:06
	 * @return
	 */
	public String hostManagement() {
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: shutDownHost
	 * @Description:远程主机下电
	 * @author duangh
	 * @date 2013 九月 10 14:25:33
	 * @return
	 */
	public String shutDownHost() {
		String id = request.getParameter("id");
		String result = physicalHostService.shutDownHost(id);
		ActionContext.getContext().getValueStack().push(result);
		return "json";
	}

	/**
	 * 
	 * @Title: startUpHost
	 * @Description:远程主机上电
	 * @author duangh
	 * @date 2013 九月 10 14:26:10
	 * @return
	 */
	public String startUpHost() {
		String id = request.getParameter("id");
		String result = physicalHostService.startUpHost(id);
		ActionContext.getContext().getValueStack().push(result);
		return "json";
	}

	/**
	 * 
	 * @Title: bootFromPXE
	 * @Description:主机设置从PXE启动
	 * @author duangh
	 * @date 2013 九月 10 14:24:49
	 * @return
	 */
	public String bootFromPXE() {
		String result = physicalHostService.bootFromPXE(host);
		String function = "callBack";
		callBack(function, result);
		return null;
	}

	public String rebootHost() {
		String result = physicalHostService.rebootHost(host);
		String function = "callBack";
		callBack(function, result);
		return null;
	}

	// 返回结果到前台页面，执行函数
	public void callBack(String function, String result) {
		try {
			String callback = "<script>parent." + function + "('" + result + "')</script>";
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter p = response.getWriter();
			p.print(callback);
			p.flush();
			p.close();
		} catch (Exception e) {

		}
	}

	/**
	 * 
	 * @Title: chooseHosts
	 * @Description:选择主机
	 * @author duangh
	 * @date 2013 九月 11 13:19:01
	 * @return
	 */
	public String chooseHosts() {
		IPMIInfo ipmi = new IPMIInfo();
		List<IPMIInfo> resultList = ipmiInfoService.queryForList(ipmi);
		ActionContext.getContext().getValueStack().push(resultList);
		return "hosts";
	}

	/**
	 * 
	 * @Title: installOS
	 * @Description:执行安装操作系统脚本
	 * @author duangh
	 * @date 2013 九月 10 22:46:48
	 * @return
	 */
	public String installOS() {
		String result = physicalHostService.installOS(os);
		String function = "callBackClose";
		callBack(function, result);
		return null;
	}
}
