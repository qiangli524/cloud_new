package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;
import com.enterprisedt.net.ftp.ssh.SSHFTPClient;
import com.sitech.basd.sxcloud.rsmu.service.deploy.ShellService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.ssh.FileChannel;
import com.sitech.basd.sxcloud.util.ssh.ShellChannel;
import com.sitech.basd.sxcloud.util.ssh.SshConnection;
import com.sitech.basd.sxcloud.util.ssh.SshConstants;
import com.sitech.basd.sxcloud.util.ssh.SshSession;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.domain.kvm.VMManagerObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.service.kvm.KvmManService;

public class DeployVideoAction extends CRUDBaseAction {
	/*
	 * static { License.setLicenseDetails("mimsDemon-si-tech",
	 * "326-6841-5475-7500"); }
	 */

	/**
	 * @Title:shell命令发送
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String sendCmd() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String sequence = request.getParameter("sequence");
		int seq;
		if (sequence == null || sequence.equals("")) {
			seq = shellService.queryForID_FREQ_SEQUENCES();
		} else {
			seq = Integer.parseInt(sequence.toString());
		}
		String shellCmd = request.getParameter("shellCmd");
		String videoid = request.getParameter("videoid");
		shellService.writeToChannel(request, response, true, shellCmd, videoid,
				seq);
		return null;
	}

	/**
	 * @Title:登出指定服务器
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String loginoutHost() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		SshSession session = new SshSession(request);
		String connectionInfo = request
				.getParameter(SshConstants.PARAMETER_CONNECTION);
		// 此连接是否已经存在
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		sshConnection.close();
		return null;
	}

	/**
	 * @Title:登录服务器
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String makeDeployVideo() throws BaseException {
		// 初始化
		ShellChannel.endFlag = "";
		HttpServletRequest request = Struts2Utils.getRequest();
		// DynaActionForm theForm = (DynaActionForm) form;
		// 服务器ID
		String ipstr = String.valueOf(ip);
		if (ipstr == null || "".equals(ipstr)) {
			ipstr = request.getParameter("ip");
		}
		request.getSession().setAttribute("ip", ipstr);
		// 端口
		String portstr = String.valueOf(port);
		if (portstr == null || "".equals(portstr)) {
			portstr = request.getParameter("port");
		}
		// 用户名
		String userstr = String.valueOf(user);
		if (userstr == null || "".equals(userstr)) {
			userstr = request.getParameter("user");
		}
		// 密码
		String pwdstr = String.valueOf(pwd);
		if (pwdstr == null || "".equals(pwdstr)) {
			pwdstr = request.getParameter("pwd");
		}

		// 保存密码到SESSION
		request.getSession().setAttribute("ftpuser", userstr);
		request.getSession().setAttribute("ftppass", pwdstr);
		request.getSession().setAttribute("ftpport", portstr);

		if ("".equals(ip)) {
			return "faliure";
		}
		SshConnection sshConnection = shellService.getSshConnection(request,
				ip, port, user, pwd);
		int sequence = 0;
		try {
			sequence = sshConnection.getLogin_sequence();
		} catch (Exception e) {
			return "failure";
		}

		request.setAttribute("sequence", sequence);
		if (sshConnection == null) {
			return "faliure";
		}

		//生成videoId；
		int videoid = shellService.queryForVideoId();
		request.setAttribute("videoid", videoid);
		return "makeDeployVideo";

	}

	/**
	 * SFTP
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String sshFile() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// Get the Channel
		SshSession session = new SshSession(request);

		String connectionInfo = request
				.getParameter(SshConstants.PARAMETER_CONNECTION);

		String channelId = SshConstants.FILE_CHANNEL;

		// 设置返回值
		request.setAttribute(SshConstants.PARAMETER_CONNECTION, connectionInfo);

		String action = request.getParameter(SshConstants.PARAMETER_ACTION);
		SshConnection sshConnection = session.getSshConnection(connectionInfo);

		/**
		 * Struts2不再支持这样的令牌控制
		 */
		// 令牌控制
		// if (!this.isTokenValid(request, true)) {
		// // 表单重复提交
		// this.saveToken(request);
		// } else {
		// 重新获得新令牌
		// this.resetToken(request);
		// this.saveToken(request);
		if (SshConstants.ACTION_CHANGE_DIRECTORY.equals(action)) {
			// 改变目录
			// 目录
			String directory = request
					.getParameter(SshConstants.PARAMETER_DIRECTORY);

			if (sshConnection != null) {
				FileChannel fileChannel = sshConnection
						.getFileChannel(channelId);
				if (fileChannel != null) {
					try {
						// 目录改变
						fileChannel.changeDirectory(directory);

					} catch (Exception e) {
						session.setErrorMessage(e.getMessage());
						return "sshFile";
					}

				}

			} else {
				// 返回到首页
				return "error";
			}

		}
		// }

		if (SshConstants.ACTION_UPLOAD.equals(action)) {

			// 文件上传

			return "uploadFile";
		}

		return "sshFile";

	}

	/**
	 * 文件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String fileDown() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		String username = String.valueOf(request.getSession().getAttribute(
				"ftpuser"));

		String ftpport = String.valueOf(request.getSession().getAttribute(
				"ftpport"));
		String host = String.valueOf(request.getSession().getAttribute("ip"));
		String password = String.valueOf(request.getSession().getAttribute(
				"ftppass"));
		String folderPath = String.valueOf(request.getSession().getAttribute(
				"path"));

		// Get the Channel
		SshSession session = new SshSession(request);

		String connectionInfo = username + "@" + host + ":" + ftpport;

		SshConnection sshConnection = session.getSshConnection(connectionInfo);

		// 文件名
		String filename = "";
		String filename1 = "";
		try {
			filename1 = request.getParameter(SshConstants.PARAMETER_FILENAME);

			filename = new String(filename1.getBytes("ISO-8859-1"), "UTF-8");

			// filename =
			// java.net.URLDecoder.decode(request.getParameter(SshConstants.PARAMETER_FILENAME),
			// "gb2312");

			// 下载
			FileChannel fileChannel = null;
			if (sshConnection != null) {

				fileChannel = sshConnection
						.getFileChannel(SshConstants.FILE_CHANNEL);

				if (fileChannel != null) {
					// response.setContentType("application/x-download");
					response.setContentType("application/octet-stream;");
					response.setHeader("Content-Disposition",
							"attachment; filename="
									+ java.net.URLEncoder.encode(filename,
											"UTF-8"));

					// Start writing the output.
					ServletOutputStream outputStream = response
							.getOutputStream();

					// fileChannel.downloadFile(filename1, outputStream);

					/** ******** */

					SSHFTPClient ftp = new SSHFTPClient();
					// ftp.disableAllAlgorithms(SSHFTPAlgorithm.CIPHER);
					// ftp.setAlgorithmEnabled(SSHFTPAlgorithm.CIPHER_3DES_CBC,
					// true);
					ftp.setRemoteHost(host);
					ftp.setAuthentication(username, password);
					ftp.getValidator().setHostValidationEnabled(false);
					ftp.connect();
					ftp.setType(FTPTransferType.BINARY);
					ftp.chdir(folderPath);

					ftp.get(outputStream, filename);
					// ftp.get(filename , filename);
					// File file = new File(filename);
					ftp.quit();

					return null;
					/** ************** */

				} else {
					// 返回到首页
					return "error";
				}

			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 初期展示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String showSshFile() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		// Get the Channel
		String connectionInfo = request
				.getParameter(SshConstants.PARAMETER_CONNECTION);
		// 转到用户根目录
		// Get the Channel
		SshSession session = new SshSession(request);
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		FileChannel fileChannel = sshConnection
				.getFileChannel(SshConstants.FILE_CHANNEL);
		// 打开文件通道
		if (fileChannel == null) {
			try {
				fileChannel = sshConnection.openFileChannel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (fileChannel != null) {
			try {
				// 目录改变
				fileChannel.changeDirectory("");
			} catch (Exception e) {
				session.setErrorMessage(e.getMessage());
				return "showSshFile";
			}

		}

		// 设置返回值
		request.setAttribute(SshConstants.PARAMETER_CONNECTION, connectionInfo);

		/*
		 * Struts2不再支持这样的令牌控制方式
		 */
		// 生成Token
		// this.saveToken(request);
		return "showSshFile";
	}

	/**
	 * 退出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String loginout() throws BaseException {
		HttpServletRequest request = Struts2Utils.getRequest();
		shellService.closeConnection(request);
		return "faliure";

	}

	/**
	 * @Title:登录指定服务器
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String loginHost() throws BaseException {
		// DynaActionForm theForm = (DynaActionForm) form;

		HttpServletRequest request = Struts2Utils.getRequest();
		String oper = request.getParameter("oper");
		if (oper != null) {
			String vID = request.getParameter("vID");

			VMManagerObj obj = new VMManagerObj();
			EntityTreeObj en = new EntityTreeObj();
			en.setId(Integer.parseInt(vID));
			en = entityTreeService.queryTreeNode(en);
			obj.setID(Integer.parseInt(en.getEntityId()));
			obj = kvmManService.queryByObj(obj);
			/*
			 * theForm.set("ip", obj.getIP()); theForm.set("port", "22");
			 * theForm.set("user", "root"); theForm.set("pwd", "111111");
			 */
			ip = obj.getIP();
			port = "22";
			user = "root";
			pwd = "111111";
			request.setAttribute("YiCloud", "Yi");
		}
		return "loginHost";
	}

	private ShellService shellService;
	private KvmManService kvmManService;
	private String ip;
	private String port;
	private String user;
	private String pwd;
	private EntityTreeService entityTreeService;

	public void setKvmManService(KvmManService kvmManService) {
		this.kvmManService = kvmManService;
	}

	public void setEntityTreeService(EntityTreeService entityTreeService) {
		this.entityTreeService = entityTreeService;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setShellService(ShellService shellService) {
		this.shellService = shellService;
	}

}
