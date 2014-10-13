package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.util.DES3;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.deployfileversion.DeployFileVersionService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployFileVersionForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.ssh.SSHUtil;
import com.sitech.utils.ssh.SshConnection;

@SuppressWarnings("all")
public class DeployFileVersionAction extends BaseAction {
	private static final Logger LOG = LoggerFactory
			.getLogger(DeployFileVersionAction.class);
	private DeployFileVersionService deployFileVersionService;
	private TbGlobalConfigService tbGlobalConfigService;
	private AppService appService;
	
	private String userType;//用来标示子页面是从哪个父页面传递过来的，用于子页面的回调方法

	private DeployFileVersionForm theForm;

	public DeployFileVersionForm getTheForm() {
		return theForm;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setTheForm(DeployFileVersionForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: 查询版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryFileVersionList() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		DeployFileVersionObj obj = new DeployFileVersionObj();
		// 取得系统的ID
		if (request.getParameter("sysid") != null) {
			String sysid = request.getParameter("sysid");
			if (sysid.length() > 0 && sysid != null) {
				obj.setSYSID(sysid);
			}
			theForm.setSYSID(sysid);
		}
		if (theForm.getDESCRIBTION() != null
				&& !"".equals(theForm.getDESCRIBTION())) {
			obj.setDESCRIBTION(theForm.getDESCRIBTION().trim());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS().trim());
		}
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME().trim());
		}
		if (theForm.getSYSID() != null && !"".equals(theForm.getSYSID())) {
			obj.setSYSID(theForm.getSYSID());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = deployFileVersionService.queryFileVersionList(obj);
		theForm.setFileVersionList(resultList);
		return "success";
	}

	/**
	 * 
	 * @Title: 新增版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String addFileVersion() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		// 取得系统的ID
		if (request.getParameter("sysid") != null) {
			String sysid = request.getParameter("sysid");
			theForm.setSYSID(sysid);
		}
		theForm.setFlag((short) 0);
		return "add";

	}

	/**
	 * 
	 * @Title: 修改版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String modFileVersion() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		DeployFileVersionObj mobj = new DeployFileVersionObj();
		try {
			BeanUtils.copyProperties(mobj, theForm);
			mobj.setID(theForm.getID());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		DeployFileVersionObj obj = deployFileVersionService
				.queryFileVersionOne(mobj);

		// 获取APPNAME
		TbSysAppObj sreqobj = new TbSysAppObj();
		sreqobj.setID(obj.getAPPID());
		TbSysAppObj sresobj = appService.queryByObj(sreqobj);
		// APPNAME
		if (sresobj != null) {
			theForm.setAPPNAME(sresobj.getAPPNAME());
		}

		theForm.setID(obj.getID());
		theForm.setNAME(obj.getNAME());
		theForm.setAPPID(obj.getAPPID());
		theForm.setNO(obj.getNO());
		theForm.setCREATED_TIME(obj.getCREATED_TIME());
		theForm.setCREATED_USER(obj.getCREATED_USER());
		theForm.setSTATUS(obj.getSTATUS());
		theForm.setLOCATION(obj.getLOCATION());
		theForm.setDESCRIBTION(obj.getDESCRIBTION());
		theForm.setUSED_TIME(obj.getUSED_TIME());
		theForm.setFlag(1);
		return "modify";
	}

	/**
	 * 
	 * @Title: 删除版本信息
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String delFileVersion() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		// 获取要删除的版本IDS
		DeployFileVersionObj obj = new DeployFileVersionObj();
		obj.setSTRIDS(theForm.getSTRIDS());
		// 获取登录工号
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		String userObj = session.get("account").toString();
		if (userObj != null) {
			obj.setDELETE_BY(userObj);
		}
		// 获取是否删除文件版本包的标识
		String fileDelFlag = request.getParameter("fileDelFlag");
		String locations = request.getParameter("locations");

		if (fileDelFlag.equals("0")) {
			// 删除版本包文件
			String locationsFinal = locations.replaceAll(";", ";rm -f");
			// shell命令
			String shellCmd = "rm -f " + locationsFinal;

			// 获取主机IP、端口、用户名密码
			// 全局配置表-tb_cloud2_global_config
			TbGlobalConfigObj greqobj = new TbGlobalConfigObj();
			// 在配置文件获取全局配置表key
			String key = PropertiesUtil
					.getString("properties/tb_cloud2_global_config",
							"deploy_version_host");
			if (key == null) {
				LOG.error("获取部署文件版本配置文件错误！");
			}
			greqobj.setKEY(key);
			TbGlobalConfigObj gresobj = tbGlobalConfigService
					.queryByObj(greqobj);
			String hostInfoStr = gresobj.getVALUE();

			String[] hostInfo = hostInfoStr.split(":");
			if (hostInfo.length != 4) {
				LOG.error("获取部署文件版本包主机连接信息错误！");
			}
			// 判断主机的配置串是否正确
			if (hostInfo.length != 4) {
				showErrorMsg(1);
				return null;
			}

			// 解析获取主机IP等
			String host = hostInfo[0];
			int port = Integer.parseInt(hostInfo[1]);
			String username = hostInfo[2];
			String pwd = DES3.decrypt(hostInfo[3]);
			// 创建和主机的连接
			SshConnection sc = SSHUtil.getSshConnection(false, host, port,
					username, pwd);

			// 执行删除命令
			SSHUtil.sendShellToSSHChannel(sc, true, shellCmd);
			sc.close();
		}
		int ret = 0;
		// 插入历史表做记录
		ret = deployFileVersionService.insertFileVersionHis(obj);
		if (ret == -1) {
			showErrorMsg(2);
			return null;
		}
		// 删除版本信息
		ret = deployFileVersionService.deleteFileVersionObj(obj);
		if (ret == -1) {
			showErrorMsg(3);
			return null;
		}
		showErrorMsg(0);
		return null;
	}

	/**
	 * 
	 * @Title: 保存和修改版本
	 * @Copyright:Copyright (c) Jul 3, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String saveFileVersion() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}

		// 新增修改标识 0新增 1修改
		int flag = theForm.getFlag();

		// 获取标识ID
		DeployFileVersionObj obj = new DeployFileVersionObj();
		UUIDGenerator uuid = new UUIDGenerator();
		if (flag == 1) {
			obj.setID(theForm.getID());
		} else {
			obj.setID(uuid.getUUID());
		}

		// 获取登录工号名称
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// session.get(Constant.USER_SESSION_KEY);
		String userObj = session.get("account").toString();
		if (userObj != null) {
			obj.setCREATED_USER(userObj);
		}
		String des = "";
		String location = "";
		String sourcePath="";
		try {
			// 版本描述转码
			des = new String(theForm.getDESCRIBTION().getBytes("iso-8859-1"),
					"utf-8");
			location = new String(theForm.getLOCATION().getBytes("iso-8859-1"),
					"utf-8");
			sourcePath = new String(theForm.getSOFRTPARTH().getBytes("iso-8859-1"),
					"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 赋值
		obj.setAPPID(theForm.getAPPID());
		obj.setNO(theForm.getNO());
		obj.setNAME(theForm.getNAME());
		obj.setSTATUS(theForm.getSTATUS());
		obj.setLOCATION(location);
		obj.setCREATED_TIME(theForm.getCREATED_TIME());
		obj.setDESCRIBTION(des.toString());
		obj.setUSED_TIME(theForm.getUSED_TIME());
		obj.setSOFTID(theForm.getSOFTID());

		int ret = 0;
		if (flag == 1) {
			// 更新
			ret = deployFileVersionService.updateFileVersionObj(obj);
			if (ret == -1) {
				// 更新发生错误
				showErrorMsg(1);
				return null;
			}
		} else {
			// 将版本包cp到LOCATION下，如果路径不存在，则创建路径
			// 校验源文件存在不存在
			File myFilePath = new File(sourcePath);
			if (!myFilePath.exists()) {
				// 文件不存在
				showErrorMsg(4);
				return null;
			}
			// 获取目标路径
			String fileLocation = theForm.getLOCATION();
			// 获取版本包的所属路径
			String finalPath = SSHUtil.replace_str(fileLocation);

			// shell命令
			String shellCmd = "\\cp " + sourcePath + " " + finalPath;

			// 获取主机IP、端口、用户名密码
			// 全局配置表-tb_cloud2_global_config
			TbGlobalConfigObj greqobj = new TbGlobalConfigObj();
			// 在配置文件获取全局配置表key
			String key = PropertiesUtil
					.getString("properties/tb_cloud2_global_config",
							"deploy_version_host");
			if (key == null) {
				LOG.error("获取部署文件版本配置文件错误！");
			}
			greqobj.setKEY(key);
			TbGlobalConfigObj gresobj = tbGlobalConfigService
					.queryByObj(greqobj);
			String hostInfoStr = gresobj.getVALUE();

			String[] hostInfo = hostInfoStr.split(":");
			if (hostInfo.length != 4) {
				LOG.error("获取部署文件版本包主机连接信息错误！");
			}

			if (hostInfo.length != 4) {
				showErrorMsg(2);
				return null;
			}
			// 解析获取主机IP等
			String host = hostInfo[0];
			int port = Integer.parseInt(hostInfo[1]);
			String username = hostInfo[2];
			String pwd = DES3.decrypt(hostInfo[3]);
			// 创建和主机的连接
			SshConnection sc = SSHUtil.getSshConnection(false, host, port,
					username, pwd);
			// 创建目标路径
			SSHUtil.recursionMkdirToSSHChannel(sc, true, finalPath);
			// 执行shell脚本
			SSHUtil.sendShellToSSHChannel(sc, true, shellCmd);
			sc.close();
			// add by chenjlc 20131224 start
			// 获取tar包MD5校验串，并记录抓捕软件中的文件信息，大小，更改时间，入表，部署完成后对比校验是否部署或升级成功
			// 判断软件包软件清单，如是"/"则为部署，不用保存文件信息
			String catchFiles = deployFileVersionService
					.querySoftCatchFiles(obj);
			if (!"/".equals(catchFiles) && null != catchFiles
					&& !"".equals(catchFiles)) {
				String fileInfolist = SSHUtil
						.getShellReturn(
								host,
								username,
								pwd,port,
								"tar -tvf "
										+ fileLocation
										+ " | awk -F \" \" '{if($1!~/d/) print $0}' | awk '{print $3\",\"$4\",\"$5\",\"$6}'");
				obj.setFileInfoList(fileInfolist);
			}
			// 获取MD5校验串
			String md5Str = SSHUtil.getShellReturn(host, username, pwd,port,
					"md5sum " + fileLocation + " | awk '{print $1}'");
			obj.setMD5CHECKSTR(md5Str);
			// add by chenjlc 20131224 end
			// 执行新增版本信息
			ret = deployFileVersionService.insertFileVersionObj(obj);
			if (ret == -1) {
				// 插入发生错误
				showErrorMsg(3);
				return null;
			}
		}
		showErrorMsg(0);
		return null;
	}

	/**
	 * 
	 * @Title: 根据APPID获取版本号和版本存放路径
	 * @Copyright:Copyright (c) Jul 8, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String getAppInfo() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		// appid
		int appid = theForm.getAPPID();
		// 获取APPNAME
		TbSysAppObj sreqobj = new TbSysAppObj();
		sreqobj.setID(appid);
		TbSysAppObj sresobj = appService.queryByObj(sreqobj);
		// APPNAME
		String appName = "";
		if (sresobj != null) {
			appName = sresobj.getAPPNAME();
		}

		// 获取版本号
		DeployFileVersionObj freqobj = new DeployFileVersionObj();
		freqobj.setAPPID(appid);
		double finalVersionNo = deployFileVersionService
				.getMaxVersionNoByAppid(freqobj);

		// 获取版本基准路径
		TbGlobalConfigObj greqobj = new TbGlobalConfigObj();
		greqobj.setKEY("softwarerar");
		TbGlobalConfigObj gresobj = tbGlobalConfigService.queryByObj(greqobj);
		// 基准路径
		String path = "";
		if (gresobj != null) {
			path = gresobj.getVALUE();
		}

		theForm.setNO(finalVersionNo);
		theForm.setAPPNAME(appName);
		theForm.setLOCATION(path + "/" + appName + "/" + finalVersionNo + "/"
				+ theForm.getNAME());

		return "add";
	}

	public String queryFileVersionByAppid() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		String appidstr = request.getParameter("appid");
		int appid = Integer.parseInt(appidstr);
		DeployFileVersionObj deployFileVersionObj = new DeployFileVersionObj();
		deployFileVersionObj.setAPPID(appid);
		List<DeployFileVersionObj> list = deployFileVersionService
				.queryFileVersionList(deployFileVersionObj);
		if (list != null && list.size() > 0) {
			theForm.setFileVersionList(list);
		}
		return "mappings";
	}

	/**
	 * 
	 * @Title:检验版本包是否存在
	 * @Copyright:Copyright (c) Jul 15, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String checkFileVersion() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}

		// 获取版本包存放路径
		String locations = request.getParameter("locations");
		File myFilePath = new File(locations);
		if (!myFilePath.exists()) {
			// 文件不存在
			showErrorMsg(1);
		} else {
			// 文件存在
			showErrorMsg(0);
		}

		return null;
	}

	/**
	 * 
	 * @Title: 版本信息历史查询
	 * @Copyright:Copyright (c) Jul 15, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryFileVersionHisList() {
		if (theForm == null) {
			theForm = new DeployFileVersionForm();
		}
		DeployFileVersionObj obj = new DeployFileVersionObj();
		if (theForm.getDESCRIBTION() != null
				&& !"".equals(theForm.getDESCRIBTION())) {
			obj.setDESCRIBTION(theForm.getDESCRIBTION().trim());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS().trim());
		}
		if (theForm.getNAME() != null && !"".equals(theForm.getNAME())) {
			obj.setNAME(theForm.getNAME().trim());
		}
		String sysid = request.getParameter("sysid");
		if (sysid != null && !"".equals(sysid)) {
			obj.setSYSID(sysid);
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List resultList = deployFileVersionService.queryFileVersionHisList(obj);
		theForm.setFileVersionList(resultList);
		return "his";
	}

	public void setDeployFileVersionService(
			DeployFileVersionService deployFileVersionService) {
		this.deployFileVersionService = deployFileVersionService;
	}

	public void setTbGlobalConfigService(
			TbGlobalConfigService tbGlobalConfigService) {
		this.tbGlobalConfigService = tbGlobalConfigService;
	}

	public DeployFileVersionService getDeployFileVersionService() {
		return deployFileVersionService;
	}

	public TbGlobalConfigService getTbGlobalConfigService() {
		return tbGlobalConfigService;
	}

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

}
