package com.sitech.basd.component.web.log.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.domain.log.LogObj;
import com.sitech.basd.component.service.log.LogService;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.component.web.log.form.LogForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

@SuppressWarnings("all")
@Controller("logAction")
@Scope("prototype")
public class LogAction extends CRUDBaseAction {
	@Resource
	private LogService logService;
	@Resource
	private UserService userService;
	private LogForm theForm;

	public LogForm getTheForm() {
		return theForm;
	}

	public void setTheForm(LogForm theForm) {
		this.theForm = theForm;
	}

	private static final Logger Log = Logger.getLogger(LogAction.class);

	/**
	 * 
	 * @Title: listLog
	 * @Description:显示配置的日志信息列表
	 * @author duangh
	 * @date May 22, 2013 10:57:56 AM
	 * @return
	 */
	public String listLog() {
		String appid = Struts2Utils.getParameter("entityId");
		if (theForm == null) {
			theForm = new LogForm();
		}
		LogObj obj = new LogObj();
		if (theForm.getName() != null && !theForm.getName().equals("")) {
			obj.setName(theForm.getName());
		}
		if (theForm.getHostIP() != null && !theForm.getHostIP().equals("")) {
			obj.setHostIP(theForm.getHostIP().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<LogObj> resultList = logService.queryListByObj(obj);
		Struts2Utils.getRequest().setAttribute("resultList", resultList);
		return LIST;
	}

	/**
	 * 
	 * @Title: addLog
	 * @Description:进入增加配置日志信息界面
	 * @author duangh
	 * @date May 22, 2013 10:59:49 AM
	 * @return
	 */
	public String addLog() {
		return ADD;
	}

	/**
	 * 
	 * @Title: updateLog
	 * @Description:进入修改配置日志信息界面
	 * @author duangh
	 * @date May 22, 2013 11:00:37 AM
	 * @return
	 */
	public String updateLog() {
		String id = Struts2Utils.getParameter("id");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		if (theForm == null) {
			theForm = new LogForm();
		}
		if (obj != null) {
			theForm.setHostIP(obj.getHostIP());
			theForm.setHostUser(obj.getHostUser());
			theForm.setType(obj.getType());
			theForm.setId(obj.getId());
			theForm.setName(obj.getName());
			theForm.setPath(obj.getPath());
			theForm.setDescription(obj.getDescription());
			theForm.setFlag(1);
			theForm.setExtension(obj.getExtension());
		}
		return MODIFY;
	}

	/**
	 * 
	 * @Title: saveLogInfo
	 * @Description:保存日志信息
	 * @author duangh
	 * @date May 22, 2013 4:34:30 PM
	 * @return
	 */
	public String saveLogInfo() {

		String appId = Struts2Utils.getParameter("appId");
		int flag = theForm.getFlag();
		if (flag == 0) {
			LogObj obj = new LogObj();
			String hostids = Struts2Utils.getParameter("hostids");
			if (hostids != null && !"".equals(hostids)) {
				String[] ids = hostids.split(",");
				for (String id : ids) {
					obj.setId(RandomUUID.getUuid());
					obj.setUserId(id);
					// obj.setName(theForm.getName());
					obj.setType(theForm.getType());
					obj.setExtension(theForm.getExtension().trim());
					obj.setCategory("0");// 默认为通用类型
					String path = theForm.getPath().trim();
					if (path.indexOf("/") != -1) {
						obj.setName(path.substring(path.lastIndexOf("/") + 1, path.length()));
					} else {
						obj.setName(path);
					}
					obj.setPath(path);
					obj.setDescription(theForm.getDescription());
					if (appId != null && !"null".equals(appId) && !"null".equals(appId)) {
						obj.setAppId(appId);
					}
					logService.insertByObj(obj);
				}
			}
		} else if (flag == 1) {
			LogObj obj = new LogObj();
			obj.setId(theForm.getId());
			obj.setType(theForm.getType());
			obj.setExtension(theForm.getExtension().trim());
			obj.setCategory("0");// 默认为通用类型
			String path = theForm.getPath().trim();
			if (path.indexOf("/") != -1) {
				obj.setName(path.substring(path.lastIndexOf("/") + 1, path.length()));
			} else {
				obj.setName(path);
			}
			obj.setPath(path);
			obj.setDescription(theForm.getDescription());
			if (appId != null && !"null".equals(appId) && !"null".equals(appId)) {
				obj.setAppId(appId);
			}
			logService.updateByObj(obj);
		}

		return null;
	}

	/**
	 * 
	 * @Title: deleteLog
	 * @Description:删除配置日志信息
	 * @author duangh
	 * @date May 22, 2013 11:01:31 AM
	 * @return
	 */
	public String deleteLog() {
		LogObj obj = new LogObj();
		obj.setId(theForm.getId());
		logService.deleteByObj(obj);
		return DEL;
	}

	/**
	 * 
	 * @Title: getLogMemu
	 * @Description:登录主机查看日志信息,查看的为文件夹
	 * @author duangh
	 * @date May 23, 2013 5:02:11 PM
	 * @return
	 */
	public String getLogMenu() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		String hostIp = obj.getHostIP();
		String user = obj.getHostUser();
		String pwd = obj.getUserPwd();
		String path = obj.getPath();// 路径
		String extension = obj.getExtension();
		Integer port = obj.getHostPort();
		if (port == null || port == 0) {
			port = 22;
		}

		SshResourceFactory ssh = SshResourceFactory.getInstance();
		List<String> logMenu = new ArrayList<String>();
		try {
			SshConnection conn = new SshConnection(hostIp, port, user, pwd);
			// String shell = "ls -1 " + path;
			String shell = "ls -lh " + path;
			if (extension != null && !extension.equals("")) {// 过滤后缀名
				shell = "ls -lh " + path + "/*." + extension;
				List<String> list = ssh.executeCommand(conn, shell);
				if (list != null && list.size() > 0) {
					// 点击查看的为文件夹，每行显示一个文件
					// logMenu.add(path.trim());
					for (String log : list) {
						if (log != null && !"".equals(log)) {
							char c = log.charAt(0);
							if (c == '-' || c == 'd') {
								logMenu.add(c + log.substring(log.lastIndexOf("/") + 1));
							}
						}
					}
				}
			} else {// 不过滤后缀名
				List<String> list = ssh.executeCommand(conn, shell);
				if (list != null && list.size() > 0) {
					// 点击查看的为文件夹，每行显示一个文件
					// logMenu.add(path.trim());
					for (String log : list) {
						if (log != null && !"".equals(log)) {
							char c = log.charAt(0);
							if (c == '-' || c == 'd') {
								logMenu.add(c + log.substring(log.lastIndexOf(" ") + 1));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("resultList", logMenu);
		request.setAttribute("path", path);
		request.setAttribute("id", id);
		return "logMenu";
	}

	/**
	 * 
	 * @Title: getLogInfo
	 * @Description:登录主机查看日志信息，查看的是文件
	 * @author duangh
	 * @date May 24, 2013 11:36:23 AM
	 * @return
	 */

	public String getLogInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		String hostIp = obj.getHostIP();
		String user = obj.getHostUser();
		String pwd = obj.getUserPwd();
		String path = obj.getPath();// 路径
		Integer port = obj.getHostPort();
		if (port == null || port == 0) {
			port = 22;
		}

		String ajax_path = request.getParameter("ajax_path");
		if (ajax_path != null && !ajax_path.equals("")) {
			path = ajax_path;
		}
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn = new SshConnection(hostIp, port, user, pwd);
			// 点击查看的为文件，默认显示后100行
			String shell = "tail -n 100 " + path;
			List<String> list = ssh.executeCommand(conn, shell);
			StringBuffer content = new StringBuffer();
			if (list != null && list.size() > 0) {
				for (String logInfo : list) {
					System.out.println(logInfo);
					if (!"".equals(logInfo) && logInfo != null) {
						if (!logInfo.trim().startsWith("Last login")) {
							content.append(logInfo);
							content.append("\r\n");
						}
					}
				}
				request.setAttribute("log", content.toString());
			}
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("path", path);
		request.setAttribute("id", id);
		return "logInfo";
	}

	/**
	 * 
	 * @Title: getMenuLog
	 * @Description:进入配置的文件夹后查看菜单内相应的文件或者文件夹
	 * @author duangh
	 * @date May 24, 2013 1:22:01 PM
	 * @return
	 */
	public String getMenuLog() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String path = request.getParameter("path");

		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		String hostIp = obj.getHostIP();
		String user = obj.getHostUser();
		String pwd = obj.getUserPwd();
		Integer port = obj.getHostPort();
		if (port == null || port == 0) {
			port = 22;
		}

		SshResourceFactory ssh = SshResourceFactory.getInstance();
		String shell = null;
		try {
			SshConnection conn = new SshConnection(hostIp, port, user, pwd);
			// 点击查看的为文件，默认显示后100行
			shell = "ls -l " + path;
			List<String> list = ssh.executeCommand(conn, shell);
			List<String> logMenu = new ArrayList<String>();
			if (list != null && list.size() > 0) {
				StringBuffer content = new StringBuffer();
				String fileName = null;
				for (String str : list) {
					if (str != null && str.length() > 0 && str.charAt(0) == '-') {
						fileName = str;
						break;
					}
				}
				if (fileName.indexOf(path.trim()) != -1) {
					if (fileName.charAt(0) == '-') {// 查看的是文件
						shell = "tail -n 100 " + path;// 默认查询后100行的数据
						List<String> logInfoList = ssh.executeCommand(conn, shell);
						for (String logInfo : list) {
							System.out.println(logInfo);
							if (!"".equals(logInfo) && logInfo != null) {
								if (!logInfo.trim().startsWith("Last login")) {
									content.append(logInfo);
									content.append("\r\n");
								}
							}
						}
						path = path.substring(0, path.lastIndexOf("/"));// path为当前目录
					}
				} else {// 点击查看的为文件夹，每行显示一个文件
					String extension = obj.getExtension();
					shell = "ls -lh " + path;
					if (extension != null && !extension.equals("")) {// 过滤文件名
						shell = "ls -lh " + path + "/*." + extension;
						List<String> logList = ssh.executeCommand(conn, shell);
						for (String log : logList) {
							if (log != null && !"".equals(log)) {
								char c = log.charAt(0);
								if (c == '-' || c == 'd') {
									logMenu.add(c + log.substring(log.lastIndexOf("/") + 1));
								}
							}
						}
					} else {// 不过滤文件名
						List<String> logList = ssh.executeCommand(conn, shell);
						for (String log : logList) {
							if (log != null && !"".equals(log)) {
								char c = log.charAt(0);
								if (c == '-' || c == 'd') {
									logMenu.add(c + log.substring(log.lastIndexOf(" ") + 1));
								}
							}
						}
					}
				}
				JSONObject json = new JSONObject();
				json.put("logMenu", JSONArray.fromObject(logMenu));
				json.put("content", content.toString());
				json.put("logId", id);
				json.put("path", path);
				PrintWriter p = Struts2Utils.getResponse().getWriter();
				p.print(json.toString());
				p.flush();
				p.close();
			}
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getLastLine
	 * @Description:自定义读取日志的最后几行
	 * @author duangh
	 * @date May 27, 2013 1:05:03 PM
	 * @return
	 */
	public String getLastLine() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String path = request.getParameter("path");
		String line = request.getParameter("line");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		String hostIp = obj.getHostIP();
		String user = obj.getHostUser();
		String pwd = obj.getUserPwd();
		Integer port = obj.getHostPort();
		if (port == null || port == 0) {
			port = 22;
		}
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn = new SshConnection(hostIp, port, user, pwd);
			String shell = "tail -n " + line + " " + path;
			List<String> list = ssh.executeCommand(conn, shell);
			StringBuffer content = new StringBuffer();
			if (list != null && list.size() > 0) {
				for (String logInfo : list) {
					System.out.println(logInfo);
					if (!"".equals(logInfo) && logInfo != null) {
						if (!logInfo.trim().startsWith("Last login")) {
							content.append(logInfo);
							content.append("\r\n");
						}
					}
				}
				JSONObject json = new JSONObject();
				json.put("content", content.toString());
				PrintWriter p = Struts2Utils.getResponse().getWriter();
				p.print(json.toString());
				p.flush();
				p.close();
			}
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getSerchWord
	 * @Description:查看日志时搜索关键词
	 * @author duangh
	 * @date May 27, 2013 1:39:13 PM
	 * @return
	 */
	public String getSerchWord() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String path = request.getParameter("path");
		String word = request.getParameter("word");
		LogObj obj = new LogObj();
		obj.setId(id);
		obj = logService.queryForObj(obj);
		String hostIp = obj.getHostIP();
		String user = obj.getHostUser();
		String pwd = obj.getUserPwd();
		Integer port = obj.getHostPort();
		if (port == null || port == 0) {
			port = 22;
		}
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn = new SshConnection(hostIp, port, user, pwd);
			String shell = "grep -in " + word + " " + path;
			List<String> list = ssh.executeCommand(conn, shell);
			StringBuffer content = new StringBuffer();
			if (list != null && list.size() > 0) {
				for (String logInfo : list) {
					System.out.println(logInfo);
					if (!"".equals(logInfo) && logInfo != null) {
						if (!logInfo.trim().startsWith("Last login")) {
							content.append(logInfo);
							content.append("\r\n");
						}
					}
				}
				JSONObject json = new JSONObject();
				json.put("content", content.toString());
				PrintWriter p = Struts2Utils.getResponse().getWriter();
				p.print(json.toString());
				p.flush();
				p.close();
			}
		} catch (Exception e) {
			Log.error("error:get Log info error" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
