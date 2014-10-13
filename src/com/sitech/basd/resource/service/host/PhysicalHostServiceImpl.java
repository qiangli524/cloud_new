package com.sitech.basd.resource.service.host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.host.IPMIInfoDao;
import com.sitech.basd.resource.domain.host.IPMIInfo;
import com.sitech.basd.sxcloud.util.EncryptUtil;
import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@Service("physicalHostService")
public class PhysicalHostServiceImpl implements PhysicalHostService {
	@Resource
	private IPMIInfoDao ipmiInfoDao;

	@Override
	public String bootFromPXE(String hosts) {
		IPMIInfo ipmi = new IPMIInfo();
		String response = null;
		if (hosts != null && hosts.indexOf(',') != -1) {
			String[] hostids = hosts.split(",");
			for (String id : hostids) {
				ipmi.setId(id);
				ipmi = ipmiInfoDao.getipmiInfo(ipmi);
				/** IMPI的java lib包对hp的ILO2不好使，先改为调用IPMItool */
				// response = ipmiCommand(ipmi, IPMIConstant.POWERUP);
				String command = "ipmitool -I lanplus -H " + ipmi.getIp() + " -U " + ipmi.getUser() + " -P "
						+ EncryptUtil.decode(ipmi.getPwd()) + " chassis bootdev pxe";
				String result = "Set Boot Device to pxe";// 用来判断成功失败的结果
				response = exeCommand(command, result);
			}
		}
		return response;
	}

	@Override
	public String rebootHost(String hosts) {
		IPMIInfo ipmi = new IPMIInfo();
		String response = null;
		if (hosts != null && hosts.indexOf(',') != -1) {
			String[] hostids = hosts.split(",");
			for (String id : hostids) {
				ipmi.setId(id);
				ipmi = ipmiInfoDao.getipmiInfo(ipmi);
				// response = ipmiCommand(ipmi, IPMIConstant.POWERUP);
				String command = "ipmitool -I lanplus -H " + ipmi.getIp() + " -U " + ipmi.getUser() + " -P "
						+ EncryptUtil.decode(ipmi.getPwd()) + " power reset";
				String result = "";// 用来判断成功失败的结果
				response = exeCommand(command, result);
			}
		}
		return response;
	}

	private String exeCommand(String command, String result) {
		String response = "失败!";
		try {
			Runtime rt = Runtime.getRuntime();
			Process pro = rt.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String s = null;
			List<String> list = new ArrayList<String>();
			while ((s = br.readLine()) != null) {
				list.add(s);
			}
			if (s == null) {
				BufferedReader error = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
				while ((s = error.readLine()) != null) {
					list.add(s);
				}
			}
			for (String str : list) {
				System.out.println(str);
				if (str.contains(result)) {
					response = "成功!";
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return response;
	}

	@Override
	public String shutDownHost(String id) {
		IPMIInfo ipmi = new IPMIInfo();
		ipmi.setId(id);
		ipmi = ipmiInfoDao.getipmiInfo(ipmi);
		// String response = ipmiCommand(ipmi, IPMIConstant.POWERDOWN);
		String command = "ipmitool -I lanplus -H " + ipmi.getIp() + " -U " + ipmi.getUser() + " -P "
				+ EncryptUtil.decode(ipmi.getPwd()) + " power off";
		String result = "Chassis Power Control: Down/Off";// 用来判断成功失败的结果
		String response = exeCommand(command, result);
		return response;
	}

	@Override
	public String startUpHost(String hosts) {
		IPMIInfo ipmi = new IPMIInfo();
		String response = null;
		ipmi.setId(hosts);
		ipmi = ipmiInfoDao.getipmiInfo(ipmi);
		// response = ipmiCommand(ipmi, IPMIConstant.POWERUP);
		String command = "ipmitool -I lanplus -H " + ipmi.getIp() + " -U " + ipmi.getUser() + " -P "
				+ EncryptUtil.decode(ipmi.getPwd()) + " power on";
		String result = "Chassis Power Control: Up/On";// 用来判断成功失败的结果
		response = exeCommand(command, result);
		return response;
	}

	/** IMPI的java lib包对hp的ILO2不好使，先改为调用IPMItool */
	// private String ipmiCommand(IPMIInfo ipmi, String powerCommand) {
	// String response = null;
	// try {
	// IPMIUtil.initConnect();
	// String host = ipmi.getIp();
	// String user = ipmi.getUser();
	// String pwd = ipmi.getPwd();
	// pwd = EncryptUtil.decode(pwd);
	// String version = ipmi.getVersion();
	// Assert.notNull(host);
	// Assert.notNull(user);
	// Assert.notNull(pwd);
	//
	// ConnectionHandle handle = null;
	// try {
	// handle = IPMIUtil.createConnection(host, user, pwd);
	// response = IPMIUtil.sendPowerCommand(handle, version, powerCommand);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// IPMIUtil.closeSession(handle);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// IPMIUtil.distroyConnect();
	// }
	// return response;
	// }
	@Override
	public String installOS(String os) {
		// 获取安装操作系统服务器信息
		String result = "执行成功！";
		String installHost = CfgUtil.getString("install_host");
		String installUser = CfgUtil.getString("install_user");
		String installPwd = CfgUtil.getString("install_pwd");
		String script = CfgUtil.getString("install_script");
		installPwd = EncryptUtil.decode(installPwd);
		String cmd = script + " " + os;
		SshConnection connection = SSHUtil.getSshConnection(installHost, 22, installUser, installPwd);
		SSHUtil.sendShellToSSHChannel(connection, true, cmd);
		connection.close();
		return result;
	}
}
