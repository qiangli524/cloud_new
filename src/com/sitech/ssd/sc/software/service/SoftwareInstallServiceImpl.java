package com.sitech.ssd.sc.software.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.ssd.sc.software.dao.SoftwareInstallDao;
import com.sitech.ssd.sc.software.domain.SoftwareInstallObj;
import com.sitech.utils.publicShop.PublicCloudSshUtil;

@Service("softwareInstallService")
public class SoftwareInstallServiceImpl implements SoftwareInstallService {
	@Autowired
	private SoftwareInstallDao softwareInstallDao;
	@Autowired
	private PropertyUtil sshProp;

	public int insertByObj(SoftwareInstallObj obj) {
		return softwareInstallDao.insertByObj(obj);
	}

	public List queryForList(SoftwareInstallObj obj) {
		return softwareInstallDao.queryForList(obj);
	}

	public SoftwareInstallObj queryByObj(SoftwareInstallObj obj) {
		return softwareInstallDao.queryByObj(obj);
	}

	public int updateByObj(SoftwareInstallObj obj) {
		return softwareInstallDao.updateByObj(obj);
	}

	public int deleteByObj(SoftwareInstallObj obj) {
		return softwareInstallDao.deleteByObj(obj);
	}

	public int install(SoftwareInstallObj obj) {
		int ret = -1;
		try {
			Timestamp startTime = new Timestamp(System.currentTimeMillis());
			obj.setStart_time(startTime.toString());
			String type = obj.getSoftware_type();
			String result = null;
			if ("1".equals(type)) {// 安装mysql
				// 调用安装mysql脚本
				String cmd = "sh " + sshProp.getString("source_install");
				result = PublicCloudSshUtil
						.executeSshCmd(sshProp.getString("salt_ip"),
								sshProp.getString("salt_username"),
								sshProp.getString("salt_password"), cmd);
			} else {// 安装tomcat
				// 调用安装tomcat脚本
				String cmd = "sh " + sshProp.getString("rpm_install");
				result = PublicCloudSshUtil
						.executeSshCmd(sshProp.getString("salt_ip"),
								sshProp.getString("salt_username"),
								sshProp.getString("salt_password"), cmd);
			}
			// 解析安装的服务端
			String[] ips = obj.getIp().split(",");
			for (int i = 0; i < ips.length; i++) {
				SoftwareInstallObj s = new SoftwareInstallObj();
				s.setIp(ips[i]);
				s.setDescription(obj.getDescription());
				s.setStatus("1");// 安装成功
				ret = softwareInstallDao.insertByObj(s);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ret;
	}

}
