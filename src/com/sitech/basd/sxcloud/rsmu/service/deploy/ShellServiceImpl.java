package com.sitech.basd.sxcloud.rsmu.service.deploy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployVideoCommandsetDao;
import com.sitech.basd.sxcloud.rsmu.dao.deploy.TbBusiDeployVideorecordDao;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployVideorecordObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.sxcloud.util.ssh.ShellChannel;
import com.sitech.basd.sxcloud.util.ssh.SshConnection;
import com.sitech.basd.sxcloud.util.ssh.SshConstants;
import com.sitech.basd.sxcloud.util.ssh.SshSession;
import com.sitech.utils.servlet.PrintWriterOut;

public class ShellServiceImpl extends BaseService implements ShellService {
	/**
	 * @Title:获取SSH通道
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public SshConnection getSshConnection(HttpServletRequest request, String host, String port,
			String username, String pwd) {

		// ssh连接
		String connectionInfo = SshConnection.getConnectionInfo(host, port, username);
		request.setAttribute(SshConstants.PARAMETER_CONNECTION, connectionInfo);
		SshSession session = new SshSession(request);
		// 此连接是否已经存在
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		// If the connection does not exist yet, open a new one.
		if (sshConnection == null) {
			// ssh 连接
			try {
				sshConnection = new SshConnection(host, Integer.parseInt(port.trim()), username,
						pwd);
				// 打开Channel
				sshConnection.openShellChannel();
				session.addSshConnection(sshConnection);
			} catch (NumberFormatException e) {
				LogHelper.error("端口号不是数字" + e.getMessage());
				sshConnection = null;
				return sshConnection;
			} catch (Exception e) {
				LogHelper.error(e.getMessage());
				sshConnection = null;
				return sshConnection;
			}
		}
		// 创建服务器访问编号
		int seq = tbBusiDeployVideorecordDao.queryForID_FREQ_SEQUENCES();
		sshConnection.setLogin_sequence(seq);
		return sshConnection;
	}

	/**
	 * @Title:关闭SSH通道
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public void closeConnection(HttpServletRequest request) {
		// 从SESSION中清空访问服务器编号
		SshSession session = new SshSession(request);
		String connectionInfo = request.getParameter(SshConstants.PARAMETER_CONNECTION);
		// 此连接是否已经存在
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		if (sshConnection != null) {
			sshConnection.close();
		}
	}

	/**
	 * @Title:往SSH通道写数据
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public void writeToChannel(HttpServletRequest request, HttpServletResponse response,
			boolean sendNewLine, String shellCmd, String videoid, int sequence) {
		LogHelper.debug("Write request received.");
		TbBusiDeployVideorecordObj obj = new TbBusiDeployVideorecordObj();
		if (videoid == null || videoid.trim().equals("")) {
			videoid = "1";
		}
		// TbSysUserinfoObj tempObj = (TbSysUserinfoObj) request.getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		obj.setVIDEOID(Integer.parseInt(videoid));
		obj.setEXECUTE_FLAG(1);
		obj.setEXECUTE_USER(request.getSession().getAttribute("name").toString());
		obj.setFREQ_SEQUENCES(sequence);
		// out = response.getWriter();
		SshSession session = new SshSession(request);
		String connectionInfo = request.getParameter(SshConstants.PARAMETER_CONNECTION);
		String channelId = SshConstants.PARAMETER_CHANNEL;
		// Get the Channel and write to it.
		SshConnection sshConnection = session.getSshConnection(connectionInfo);
		ShellChannel shellChannel = null;
		if (sshConnection != null) {
			shellChannel = sshConnection.getShellChannel(channelId);
			if (shellChannel != null) {
				if (shellCmd != null && !"".equals(shellCmd.trim())) {
					obj.setCONTENT(shellCmd);
					obj.setFLAG(1);
					shellChannel.write(obj, sendNewLine, tbBusiDeployVideorecordDao,
							tbBusiDeployVideoCommandsetDao);
				}
				// 取得返回信息
				shellChannel.read(obj, tbBusiDeployVideorecordDao);
				String[] lines = shellChannel.getScreen();
				String row = "";
				for (int index = 0; index <= lines.length - 1; index++) {
					row = lines[index];
					if (row != null && (row.trim().endsWith("$") || row.trim().endsWith("#"))) {
						continue;
					}
					// out.print(row + "<br/>");
					PrintWriterOut.printWirter(response, "<br/>");
				}
			} else {
				// out.print("Invalid Connection or Channel.");
				PrintWriterOut.printWirter(response, "Invalid Connection or Channel.");
			}
		} else {
			// out.print("Connection Closed.");
			PrintWriterOut.printWirter(response, "Connection Closed.");
		}
		// out.flush();
	}

	/**
	 * @Title:录像过程记录列表
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public List queryForListByObj(TbBusiDeployVideorecordObj obj) {
		return tbBusiDeployVideorecordDao.queryForListByObj(obj);
	}

	/**
	 * @Title:获得执行编号
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public int queryForID_FREQ_SEQUENCES() {
		return tbBusiDeployVideorecordDao.queryForID_FREQ_SEQUENCES();
	}

	/**
	 * @Title:查询序列作为videoid
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int queryForVideoId() {
		return tbBusiDeployVideorecordDao.queryForVideoId();
	}

	/**
	 * @Title:录像过程记录
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbBusiDeployVideorecordObj obj) {
		return tbBusiDeployVideorecordDao.insertByObj(obj);
	}

	private TbBusiDeployVideorecordDao tbBusiDeployVideorecordDao;
	private TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao;

	public void setTbBusiDeployVideorecordDao(TbBusiDeployVideorecordDao tbBusiDeployVideorecordDao) {
		this.tbBusiDeployVideorecordDao = tbBusiDeployVideorecordDao;
	}

	public void setTbBusiDeployVideoCommandsetDao(
			TbBusiDeployVideoCommandsetDao tbBusiDeployVideoCommandsetDao) {
		this.tbBusiDeployVideoCommandsetDao = tbBusiDeployVideoCommandsetDao;
	}
}
