package com.sitech.basd.sxcloud.rsmu.web.deploy.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppFileVersionVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppOnlineFilepathVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackFileVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppRollbackInfoVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3OnlineHistoryVO;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.AppVersionService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.FileVersionService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.OnlineFilepathService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.OnlineHistoryService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.RollbackFileService;
import com.sitech.basd.sxcloud.rsmu.service.deploy.version.RollbackInfoService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.VersionResumeForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;

@SuppressWarnings("serial")
public class VersionResumeAction extends CRUDBaseAction {

	private AppService appService;
	// private VersionResumeService versionResumeService;
	private FileVersionService fileVersionService;
	private DeployExampleService deployExampleService;
	private RollbackFileService rollbackFileService;
	private RollbackInfoService rollbackInfoService;
	private OnlineFilepathService onlineFilepathService;
	private AppVersionService appVersionService;
	private OnlineHistoryService onlineHistoryService;

	public void setOnlineHistoryService(
			OnlineHistoryService onlineHistoryService) {
		this.onlineHistoryService = onlineHistoryService;
	}

	public void setOnlineFilepathService(
			OnlineFilepathService onlineFilepathService) {
		this.onlineFilepathService = onlineFilepathService;
	}

	public void setRollbackFileService(RollbackFileService rollbackFileService) {
		this.rollbackFileService = rollbackFileService;
	}

	public void setRollbackInfoService(RollbackInfoService rollbackInfoService) {
		this.rollbackInfoService = rollbackInfoService;
	}

	// public void setVersionResumeService(
	// VersionResumeService versionResumeService) {
	// this.versionResumeService = versionResumeService;
	// }

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setFileVersionService(FileVersionService fileVersionService) {
		this.fileVersionService = fileVersionService;
	}

	public void setDeployExampleService(
			DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setAppVersionService(AppVersionService appVersionService) {
		this.appVersionService = appVersionService;
	}

	/**
	 * 
	 * @Title: listVersionResume
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-23 下午3:52:21
	 */
	public String listVersionResume() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbSysAppObj obj = new TbSysAppObj();
		if (theForm.getAPPID() > 0) {
			obj.setID(theForm.getAPPID());
		}
		if (theForm.getCATCH_STATUS() != null
				&& !"".equals(theForm.getCATCH_STATUS())) {
			obj.setCATCH_STATUS(theForm.getCATCH_STATUS().trim());
		}
		/* obj.setPagination(this.getPaginater().initPagination(request)) ;//分页 */
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String)session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		TbSysAppObj obj1 = new TbSysAppObj();
		List appList1 = appService.queryForListByObj(obj1);
		theForm.setResultList(appList);
		theForm.setAppList(appList1);
		return LIST;
	}

	/**
	 * 
	 * @Title: historyVersion
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-23 下午5:14:18
	 */
	public String historyVersion() {

		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbSysAppObj obj = new TbSysAppObj();
		if (theForm.getAPPID() > 0) {
			obj.setID(theForm.getAPPID());
		}
		if (theForm.getCATCH_STATUS() != null
				&& !"".equals(theForm.getCATCH_STATUS())) {
			obj.setCATCH_STATUS(theForm.getCATCH_STATUS().trim());
		}
		/* obj.setPagination(this.getPaginater().initPagination(request)) ;//分页 */
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String)session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		TbSysAppObj obj1 = new TbSysAppObj();
		List appList1 = appService.queryForListByObj(obj1);
		theForm.setResultList(appList);
		theForm.setAppList(appList1);
		return LIST;
	}

	private VersionResumeForm theForm;

	public VersionResumeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VersionResumeForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * 
	 * @Title: getFirstApp
	 * @Description: TODO(获取应用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 上午10:19:05
	 */
	public String getFirstApp() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		TbSysAppObj obj = new TbSysAppObj();
		if (theForm.getAPPID() > 0) {
			obj.setID(theForm.getAPPID());
		}
		if (theForm.getCATCH_STATUS() != null
				&& !"".equals(theForm.getCATCH_STATUS())) {
			obj.setCATCH_STATUS(theForm.getCATCH_STATUS().trim());
		}
		/* obj.setPagination(this.getPaginater().initPagination(request)) ;//分页 */
//		TbSysUserinfoObj userInfoObj = (TbSysUserinfoObj) request.getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setDATAAUTHORITY((String)session.get("datau"));
		List appList = appService.queryForListByObj(obj);
		TbSysAppObj obj1 = new TbSysAppObj();
		List appList1 = appService.queryForListByObj(obj1);
		theForm.setResultList(appList);
		theForm.setAppList(appList1);

		return "first";
	}

	/**
	 * 
	 * @Title: getSecondVersion
	 * @Description: TODO(获取版本)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 上午10:18:51
	 */
	public String getSecondVersion() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		TbCloud3AppOnlineFilepathVO obj = new TbCloud3AppOnlineFilepathVO();
		if (theForm.getAPPID() > 0) {
			obj.setAppid(theForm.getAPPID());
		}

		List<TbCloud3AppOnlineFilepathVO> respathList = onlineFilepathService
				.queryListByAppId(obj);
		theForm.setVersionList(respathList);
		/**
		 * List lst = versionResumeService.queryForListByObj(obj);
		 * theForm.setVersionList(lst);
		 * 
		 * TbSysAppObj vo = new TbSysAppObj(); if (theForm.getAPPID() > 0) {
		 * vo.setID(theForm.getAPPID()); } TbSysAppObj rstvo =
		 * appService.queryByObj(vo); theForm.setAPPNAME(rstvo.getAPPNAME());
		 */
		TbSysAppObj vo = new TbSysAppObj();
		if (theForm.getAPPID() > 0) {
			vo.setID(theForm.getAPPID());
		}
		TbSysAppObj rstvo = appService.queryByObj(vo);
		theForm.setAPPNAME(rstvo.getAPPNAME());
		return "second";
	}

	/**
	 * 
	 * @Title: getThirdPath
	 * @Description: TODO(获取path)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 上午10:18:29
	 */
	public String getThirdPath() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		// 集合处理逻辑
		if (theForm.getResume_type() == 0) {
			TbCloud3AppOnlineFilepathVO obj = new TbCloud3AppOnlineFilepathVO();

			if (theForm.getAPPID() > 0) {
				obj.setAppid(theForm.getAPPID());
			}
			if (!"".equals(theForm.getDay_version())
					&& theForm.getDay_version() != null) {
				obj.setDay_version(theForm.getDay_version());
			}
			TbCloud3AppOnlineFilepathVO OnlineVo = onlineFilepathService
					.queryVOByObj(obj);
			String filePath = OnlineVo.getFilePath();
			String[] filepaths = filePath.split(",");
			List<TbCloud3AppFileVersionVO> fileList = new ArrayList<TbCloud3AppFileVersionVO>();
			for (String relativePath : filepaths) {
				TbCloud3AppFileVersionVO fileVo = new TbCloud3AppFileVersionVO();
				fileVo.setFilePath(relativePath);
				fileList.add(fileVo);
			}
			theForm.setFileList(fileList);
			return "third";
		} else {
			// 找路径集合
			Map<String, Object> pathmap = new HashMap<String, Object>();
			/**
			 * TbCloud3AppVersionVO appV = new TbCloud3AppVersionVO(); if
			 * (!"".equals(theForm.getDay_version()) && theForm.getDay_version()
			 * != null) { appV.setDay_version(theForm.getDay_version()); }
			 * List<TbCloud3AppVersionVO> appversions = appVersionService
			 * .queryCollListByObj(appV); for (TbCloud3AppVersionVO
			 * tbCloud3AppVersionVO : appversions) { TbCloud3AppFileVersionVO
			 * fileVO = new TbCloud3AppFileVersionVO();
			 * fileVO.setAppVersion(tbCloud3AppVersionVO.getDay_version());
			 * 
			 * @SuppressWarnings("unchecked") List<TbCloud3AppFileVersionVO>
			 *                                files =
			 *                                (List<TbCloud3AppFileVersionVO>)
			 *                                fileVersionService
			 *                                .queryForListByObj(fileVO); for
			 *                                (TbCloud3AppFileVersionVO
			 *                                tbCloud3AppFileVersionVO : files)
			 *                                {
			 *                                pathmap.put(tbCloud3AppFileVersionVO
			 *                                .getFilePath(),
			 *                                tbCloud3AppFileVersionVO.getId());
			 *                                } }
			 **/
			TbCloud3AppOnlineFilepathVO filepth = new TbCloud3AppOnlineFilepathVO();
			if (theForm.getAPPID() > 0) {
				filepth.setAppid(theForm.getAPPID());
			}
			if (!"".equals(theForm.getDay_version())
					&& theForm.getDay_version() != null) {
				filepth.setDay_version(theForm.getDay_version());
			}
			List<TbCloud3AppOnlineFilepathVO> respathList = onlineFilepathService
					.queryListByObj(filepth);
			for (TbCloud3AppOnlineFilepathVO onFilevo : respathList) {
				String respsth = onFilevo.getFilePath();
				String[] respsths = respsth.split(",");
				for (String string : respsths) {
					pathmap.put(string, onFilevo.getAppid());
				}
			}

			// 找出路径集合中路径对应的id
			Map<String, Object> tobkgfilepaths = new HashMap<String, Object>();
			// List<String > tobkgfileids = new ArrayList<String>();
			TbCloud3AppVersionVO vvo = new TbCloud3AppVersionVO();
			if (theForm.getAPPID() > 0) {
				vvo.setApp_id(theForm.getAPPID());
			}
			if (!"".equals(theForm.getDay_version())
					&& theForm.getDay_version() != null) {
				vvo.setDay_version(theForm.getDay_version());
			}
			TbCloud3AppVersionVO reVo = appVersionService.queryByObj(vvo);
			String inserttime = reVo.getInsertTime();
			TbCloud3AppFileVersionVO tcvo = new TbCloud3AppFileVersionVO();
			tcvo.setInsertDate(inserttime);
			Set<Map.Entry<String, Object>> set = pathmap.entrySet();
			for (Map.Entry<String, Object> en : set) {
				tcvo.setFilePath(en.getKey());
				TbCloud3AppFileVersionVO fullpathVO = fileVersionService
						.queryFileVersionForResumeByObj(tcvo);
				if (fullpathVO == null) {
					tobkgfilepaths.put(en.getKey(), null);
				} else {
					tobkgfilepaths.put(en.getKey(),
							fullpathVO.getFileFullPath());
				}
			}
			theForm.setTobkgfilepaths(JSONObject.fromObject(tobkgfilepaths)
					.toString());
			theForm.setTobkgfilepathsmap(tobkgfilepaths);
			// if(theForm.getTobkgfilepaths() != null){
			// theForm.setTobkgfilepathsset(theForm.getTobkgfilepaths().entrySet());
			// }

			// 与应用相关的主机列表
			TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
			if (theForm.getAPPID() > 0) {
				obj.setAPPID(theForm.getAPPID());
			}
			// String fileid = "";
			// if (!"".equals(theForm.getFileids()) && theForm.getFileids() !=
			// null) {
			// fileid = theForm.getFileids();
			// }
			// String[] fileids = fileid.split(",");
			List hostList = deployExampleService.queryForListByObj(obj);
			theForm.setHostList(hostList);
			return "fourth";
		}
	}

	/**
	 * 
	 * @Title: getFouthHosts
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-26 上午11:09:22
	 */
	public String getFourthHosts() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}

		// 按文件恢复
		if (theForm.getResume_type() == 0) {
			// 回退的文件列表,fileid目前存的是filepath。
			String fileid = "";
			Map<String, Object> tobkgfilepaths = new HashMap<String, Object>();
			if (theForm.getFileids() != null
					&& !"".equals(theForm.getFileids())) {
				fileid = theForm.getFileids();
			}
			String[] fileids = fileid.split(",");
			TbCloud3AppFileVersionVO vo = new TbCloud3AppFileVersionVO();
			if (theForm.getDay_version() != null
					&& !"".equals(theForm.getDay_version())) {
				vo.setAppVersion(theForm.getDay_version());
			}
			for (String id : fileids) {
				vo.setFilePath(id);
				TbCloud3AppFileVersionVO conditionVo = fileVersionService
						.queryVOByObj(vo);
				TbCloud3AppFileVersionVO rstvo = conditionVo == null ? new TbCloud3AppFileVersionVO()
						: conditionVo;
				if (rstvo.getId() == null) {
					rstvo.setFilePath(id);
					rstvo.setFileFullPath(null);
				}
				tobkgfilepaths
						.put(rstvo.getFilePath(), rstvo.getFileFullPath());
			}
			theForm.setTobkgfilepaths(JSONObject.fromObject(tobkgfilepaths)
					.toString());
			theForm.setTobkgfilepathsmap(tobkgfilepaths);
		}
		//
		// if(theForm.getTobkgfilepaths() != null){
		// theForm.setTobkgfilepathsset(theForm.getTobkgfilepaths().entrySet());
		// }

		// 与应用相关的主机列表
		TbBusiDeployExampleObj obj = new TbBusiDeployExampleObj();
		if (theForm.getAPPID() > 0) {
			obj.setAPPID(theForm.getAPPID());
		}
		// String fileid = "";
		// if (!"".equals(theForm.getFileids()) && theForm.getFileids() != null)
		// {
		// fileid = theForm.getFileids();
		// }
		// String[] fileids = fileid.split(",");
		List hostList = deployExampleService.queryForListByObj(obj);
		theForm.setHostList(hostList);
		return "fourth";
	}

	/**
	 * 
	 * @Title: finish
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-26 下午3:25:48
	 */
	public String finish() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		TbCloud3AppRollbackInfoVO obj = new TbCloud3AppRollbackInfoVO();
		if (theForm.getAPPID() > 0) {
			obj.setAppId(theForm.getAPPID());
		}

		if (!"".equals(theForm.getExampleids())
				&& theForm.getExampleids() != null) {
			obj.setDeployIds(theForm.getExampleids());
			String[] deployids = theForm.getExampleids().split(",");
			List<String> hostlst = new ArrayList<String>();
			for (String deployid : deployids) {
				TbBusiDeployExampleObj o = new TbBusiDeployExampleObj();
				o.setID(Integer.valueOf(deployid));
				TbBusiDeployExampleObj rsto = (TbBusiDeployExampleObj) deployExampleService
						.queryForListByObj(o).get(0);
				hostlst.add(rsto.getIP());
			}
			theForm.setHostLst(hostlst);
		}
		if (theForm.getTobkgfilepaths() != null
				&& !"".equals(theForm.getTobkgfilepaths())) {
			theForm.setTobkgfilepathsmap((Map<String, Object>) JSONObject
					.fromObject(theForm.getTobkgfilepaths()));
		}
		return "finish";
	}

	public String toDBForBkg() {
		if (theForm == null) {
			theForm = new VersionResumeForm();
		}
		TbCloud3AppRollbackInfoVO obj = new TbCloud3AppRollbackInfoVO();
		if (theForm.getAPPID() > 0) {
			obj.setAppId(theForm.getAPPID());
		}
		if (theForm.getResume_type() > -1) {
			obj.setResume_type(theForm.getResume_type());
		}

		if (!"".equals(theForm.getExampleids())
				&& theForm.getExampleids() != null) {
			obj.setDeployIds(theForm.getExampleids());
		}
		if (theForm.getTobkgfilepaths() != null
				&& !"".equals(theForm.getTobkgfilepaths())) {
			theForm.setTobkgfilepathsmap((Map<String, Object>) JSONObject
					.toBean(JSONObject.fromObject(theForm.getTobkgfilepaths()),
							HashMap.class));
		}
		if (theForm.getDay_version() != null
				&& !"".equals(theForm.getDay_version())) {
			obj.setDay_version(theForm.getDay_version());
		}

		obj.setExecuteFlag(0); // 扫描状态0：未扫描1：已扫描
		obj.setStatus(0);// 回滚状态-0:未回滚，1:正在回滚2:已回滚3：回滚失败
		obj.setExecutePercent("未开始回滚！");// 执行百分比信息
		String rtn = rollbackInfoService.insertByObj(obj);
		StringBuilder deploypath = new StringBuilder();
		if (rtn != null && !"".equals(rtn)) {
			// String fileid = "";
			Set<Map.Entry<String, Object>> set = null;
			if (theForm.getTobkgfilepathsmap() != null) {
				set = theForm.getTobkgfilepathsmap().entrySet();
			}
			// 更新上线路径
			TbSysAppObj appVo = new TbSysAppObj();
			if (theForm.getAPPID() > 0) {
				appVo.setID(theForm.getAPPID());
			}
			TbSysAppObj rstvo = appService.queryByObj(appVo);
			String basePath = rstvo.getBASEPATH();
			for (Map.Entry<String, Object> en : set) {
				TbCloud3AppRollbackFileVO vo = new TbCloud3AppRollbackFileVO();
				vo.setRollbackFilePath(en.getKey());
				deploypath = deploypath.append(basePath).append("/")
						.append(en.getKey()).append(",");
				if (en.getValue().toString() != "null") {
					vo.setRollbackFileFullPath((String) en.getValue());
				} else {
					vo.setRollbackFileFullPath(null);
				}
				vo.setRollbackId(rtn);
				rollbackFileService.insertByObj(vo);
			}

		}
		// 更改部署实例状态为正在回滚
		if (!"".equals(theForm.getExampleids())
				&& theForm.getExampleids() != null) {
			String exampleid = theForm.getExampleids();
			String[] exampleids = exampleid.split(",");
			for (String string : exampleids) {
				TbBusiDeployExampleObj eObj = new TbBusiDeployExampleObj();
				eObj.setID(Integer.valueOf(string));
				eObj.setDEPLOY_FLAG("8");
				eObj.setDEPLOY_PERCENT("等待回滚");
				deployExampleService.deployRequest(eObj);

				// 更新上线路径
				TbSysAppObj condiAppObj = new TbSysAppObj();
				condiAppObj.setOnlinePath(deploypath.toString());
				if (theForm.getAPPID() > 0) {
					condiAppObj.setID(theForm.getAPPID());
				}
				if (theForm.getVersionDesc() != null
						&& !"".equals(theForm.getVersionDesc())) {
					condiAppObj.setVersionDesc(theForm.getVersionDesc());
				}
				appService.updateOnlinePath(condiAppObj);

				TbCloud3OnlineHistoryVO hisVo = new TbCloud3OnlineHistoryVO();
				hisVo.setExampleId(Integer.valueOf(string));
				hisVo.setIsRollback("1");
				hisVo.setIsSuccess("0");
				hisVo.setOnlinePath(deploypath.toString());
				onlineHistoryService.insertByObj(hisVo);
			}
		}

		return "tobkg";
	}
}
