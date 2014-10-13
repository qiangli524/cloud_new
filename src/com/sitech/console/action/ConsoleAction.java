package com.sitech.console.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.exception.ConsoleException;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.console.service.ConsoleService;

/**
 * 
 * <p>
 * Title: ConsoleAction
 * </p>
 * <p>
 * Description: 虚拟化资源池控制台
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-7-30 下午1:30:21
 * 
 */
@Controller("consoleAction")
@Scope("prototype")
public class ConsoleAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(ConsoleAction.class);
	@Autowired
	private ConsoleService consoleService;
	// 虚拟机标示，对应noVnc配置文件标示，vm1(虚拟机标识): 192.168.1.1(一般为宿主机IP):5901(虚拟机在宿主机中对应的)
	private String vmCode;
	// websockify部署主机
	private String websockify_host;
	// websockify启动端口
	private String websockify_port;
	// 错误信息
	private String error_msg = "";

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getWebsockify_host() {
		return websockify_host;
	}

	public void setWebsockify_host(String websockify_host) {
		this.websockify_host = websockify_host;
	}

	public String getWebsockify_port() {
		return websockify_port;
	}

	public void setWebsockify_port(String websockify_port) {
		this.websockify_port = websockify_port;
	}

	public String getVmCode() {
		return vmCode;
	}

	public void setVmCode(String vmCode) {
		this.vmCode = vmCode;
	}

	/**
	 * 
	 * @Title: vnc_auto
	 * @Description: 访问虚拟机控制台，自动登录
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-30 下午1:33:13
	 */
	public String vnc_auto() {
		try {
			Map<String, String> websokifyMap = consoleService.initWebsockifyParam();
			this.websockify_host = websokifyMap.get("websockify_host");
			this.websockify_port = websokifyMap.get("websockify_port");
			if (this.websockify_host == null || "".equals(this.websockify_host)) {
				throw new ConsoleException("websockify_host 参数配置错误！请重新到“全局配置”中配置！");
			}
			if (this.websockify_port == null || "".equals(this.websockify_port)) {
				throw new ConsoleException("websockify_port 参数配置错误！请重新到“全局配置”中配置！");
			}
			if (this.vmCode == null || "".equals(this.vmCode)) {
				throw new ConsoleException("虚拟机参数未传递，正确格式为：cloud/console_vnc_auto.do?vmCode=vm1");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			this.error_msg = e.getMessage();
		}
		return SUCCESS;
	}
}
