package com.sitech.basd.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.ChannelSftp;
import com.sitech.utils.capture.vo.BaseSshConnVO;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.encrypt.DES3;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.sftp.SftpUtil;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

/**
 * 
 * <p>
 * Title: AppDeployUtil
 * </p>
 * <p>
 * Description: 应用部署工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-29 下午2:49:31
 * 
 */
public class AppDeployUtil {
	private static final Log LOG = LogFactory.getLog(AppDeployUtil.class);
	public static final String LOCAL_STORE_PATH = PropertiesUtil.getString("properties/cloud_host",
			"local_store_path");
	// 若为/时，则为部署功能
	public static final String DEPLOY_ROOT_PATH = "/";

	/**
	 * 
	 * @Title: getSshConnection
	 * @Description: 获取Ssh连接
	 * @param
	 * @return SshConnection
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-28 下午4:06:34
	 */
	public synchronized static SshConnection getSshConnection(BaseSshConnVO baseVO)
			throws SSHConnectException {
		// 主机IP
		String host = baseVO.getHost();
		// 登录用户名
		String username = baseVO.getUsername();
		// 若为信任关系，则为key路径，否则为密码
		String pwdOrKeyPath = baseVO.getPwdOrKeyPath();
		// 是否使用信任关系
		Boolean ifByPublicKey = baseVO.getIfByPublicKey();
		// 端口
		Integer port = baseVO.getPort();
		SshConnection sshConnection = null;
		try {
			sshConnection = SSHUtil.getSshConnection(ifByPublicKey, host, port, username,
					pwdOrKeyPath);
		} catch (Exception e) {
			throw new SSHConnectException("获取Ssh连接：" + username + "@" + host + ":" + port + "失败！"
					+ e.getMessage(), e);
		}
		return sshConnection;
	}

	/**
	 * 
	 * @Title: getSftpConnection
	 * @Description: 获取Sftp连接
	 * @param
	 * @return ChannelSftp
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-28 下午4:06:56
	 */
	public synchronized static ChannelSftp getSftpConnection(BaseSshConnVO baseVO)
			throws SSHConnectException {
		// 主机IP
		String host = baseVO.getHost();
		// 登录用户名
		String username = baseVO.getUsername();
		// 若为信任关系，则为key路径，否则为密码
		String pwdOrKeyPath = baseVO.getPwdOrKeyPath();
		// 是否使用信任关系
		Boolean ifByPublicKey = baseVO.getIfByPublicKey();
		// 端口
		Integer port = baseVO.getPort();
		ChannelSftp sftp = null;
		try {
			sftp = SftpUtil.getSftpConnection(ifByPublicKey, host, port, username, pwdOrKeyPath);
		} catch (Exception e) {
			throw new SSHConnectException("获取Sftp连接：" + username + "@" + host + ":" + port + "失败！"
					+ e.getMessage(), e);
		}
		return sftp;
	}

	/**
	 * 
	 * @Title: getFileFromStandard
	 * @Description: 在基准机获取File-tar
	 * @param basepath
	 *            : 基准应用路径，tarName tar名称 deployPathArray 部署文件路径
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-27 下午2:02:08
	 */
	public static void getFileFromStandard(SshConnection sshConnection, ChannelSftp sftp,
			String basepath, String tarName) throws SSHConnectException {
		try {
			// 根据部署文集路径数组打包文件
			// AppTarUtil.tar(sshConnection, basepath, deployPathArray,
			// tarName);
			String filePath = SSHUtil.replace_str(basepath);
			// 将文件tar传输到云平台
			SftpUtil.downloadRemoteFileToLocal(sftp, filePath + File.separator + tarName,
					LOCAL_STORE_PATH);
		} catch (Exception e) {
			throw new SSHConnectException("Sftp Get File (基准机传输文件到云平台服务器失败)@ " + basepath
					+ File.separator + tarName + " 失败！" + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Title: deployFileFormLocalToDeploy
	 * @Description: 从本地服务器传输tar到部署机部署
	 * @param // 部署路径(包括应用名称)
 String
	 *            deploypath // 解压tar名称 String deployTarName
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-28 下午1:54:02
	 */
	public static void deployFileFormVersionToDeploy(SshConnection sshConnection, ChannelSftp sftp,
			String deploypath, String deployTarPath) throws SSHConnectException {
		try {
			// 创建部署路径文件夹
			SSHUtil.recursionMkdirToSSHChannel(sshConnection, true, deploypath);
			// 云平台传输tar文件到部署机
			SftpUtil.uploadLocalFileToRemote(sftp, deploypath, deployTarPath);
			// 解压缩tar
			// AppTarUtil.untar(sshConnection, deploypath, deployTarName);
		} catch (Exception e) {
			throw new SSHConnectException("Sftp Local File To Deploy Host(云平台本地上传文件到部署主机)"
					+ deploypath + " " + deployTarPath + " 失败！" + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Title: deployFileFormLocalToDeploy
	 * @Description: 从本地服务器传输tar到部署机部署
	 * @param // 部署路径(包括应用名称)
 String
	 *            deploypath // 解压tar名称 String deployTarName
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-28 下午1:54:02
	 */
	public static void deployFileFormLocalToDeploy(SshConnection sshConnection, ChannelSftp sftp,
			String deploypath, String deployTarName) throws SSHConnectException {
		try {
			// 创建部署路径文件夹
			SSHUtil.recursionMkdirToSSHChannel(sshConnection, true, deploypath);
			// 云平台传输tar文件到部署机
			SftpUtil.uploadLocalFileToRemote(sftp, deploypath, LOCAL_STORE_PATH + File.separator
					+ deployTarName);
			// 解压缩tar
			// AppTarUtil.untar(sshConnection, deploypath, deployTarName);
		} catch (Exception e) {
			throw new SSHConnectException("Sftp Local File To Deploy Host(云平台本地上传文件到部署主机)"
					+ deploypath + deployTarName + " 失败！" + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Title: initLocalSshConnection
	 * @Description: 实例云平台服务器本地Ssh连接
	 * @param com
	 *            /sitech/ssd/busisys/config/cloud_host 配置文件配置本地连接信息
	 * @return SshConnection
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @throws SSHConnectException
	 * @createtime 2013-5-30 下午2:06:04
	 */
	public synchronized static SshConnection initLocalSshConnection() throws SSHConnectException {
		String localIp = PropertiesUtil.getString("properties/cloud_host", "local_host_ip");
		Integer localPort = Integer.parseInt(PropertiesUtil.getString("properties/cloud_host",
				"local_host_port"));
		String localUser = PropertiesUtil.getString("properties/cloud_host", "local_host_user");
		String localPass = PropertiesUtil.getString("properties/cloud_host", "local_host_pass");
		SshConnection connection = null;
		try {
			connection = SSHUtil.getSshConnection(false, localIp, localPort, localUser,
					DES3.decrypt(localPass));
		} catch (Exception e) {
			throw new SSHConnectException("获取Ssh连接：" + localUser + "@" + localIp + ":" + localPort
					+ "失败！" + e.getMessage(), e);
		}
		return connection;
	}

	/**
	 * 
	 * @Title: closeSshConnection
	 * @Description: 关闭Ssh连接
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-28 下午4:08:05
	 */
	public synchronized static void closeSshConnection(SshConnection connection) {
		connection.close();
	}

	/**
	 * 
	 * @Title: closeSftpConnection
	 * @Description: 关闭Sftp连接
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-28 下午4:08:19
	 */
	public synchronized static void closeSftpConnection(ChannelSftp sftp) {
		SftpUtil.quitSftpConnection(sftp);
	}

	/**
	 * 
	 * @Title: backupDeployFile
	 * @Description: 备份部署文件mv file file@yyyyMMddHHmmssSSSS
	 * @param connection
	 *            : 部署机连接 basepath：部署根路径/opt/apache-tomcat-6.0.35/webapps
	 *            deployPathArray 文件清单-cloud/index.jsp,cloud/WEB-INF/web.xml
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-15 上午11:34:12
	 */
	public static void backupDeployFile(SshConnection connection, String basepath,
			String[] deployPathArray) {
		LOG.info("登录到根目录： cd " + basepath + "@" + connection.getConnectionInfo());
		SSHUtil.sendShellToSSHChannel(connection, true, "cd " + basepath);
		LOG.info("备份部署文件开始！");
		if (deployPathArray != null && deployPathArray.length > 0) {
			for (int i = 0; i < deployPathArray.length; i++) {
				String relative_path = "";
				// 若为部署功能，则不进行备份
				if (DEPLOY_ROOT_PATH.equals(deployPathArray[i])) {
					// 若为部署功能，则不进行备份
				} else {
					relative_path = deployPathArray[i];// 文件相对路径
					LOG.info(connection.getConnectionInfo() + "mv " + relative_path + " "
							+ relative_path + "@" + DateUtil.getCurrentDateStrForWSIF());
					// 备份源文件
					SSHUtil.sendShellToSSHChannel(connection, true, "mv " + relative_path + " "
							+ relative_path + "@" + DateUtil.getCurrentDateStrForWSIF());
				}
			}
		}
	}
}
