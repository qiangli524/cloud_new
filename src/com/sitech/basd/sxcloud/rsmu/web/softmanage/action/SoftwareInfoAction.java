package com.sitech.basd.sxcloud.rsmu.web.softmanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sitech.basd.component.service.user.UserService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.rsmu.config.SuffixConstant;
import com.sitech.basd.sxcloud.rsmu.domain.deploy.deployfileversion.DeployFileVersionObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareFileInfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.deployfileversion.DeployFileVersionService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.SoftwareFileInfoService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.SoftwareInfoService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.SoftwareInfohisService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.deploy.form.DeployFileVersionForm;
import com.sitech.basd.sxcloud.util.DownLoadUtil;
import com.sitech.basd.sxcloud.util.exception.BaseException;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeService;
import com.sitech.utils.capture.FileMd5Util;
import com.sitech.utils.capture.GetTarFromStandardUtil;
import com.sitech.utils.capture.vo.CaptureFileVO;
import com.sitech.utils.capture.vo.CaptureResultType;
import com.sitech.utils.capture.vo.CaptureTarResultVO;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
@Controller("softwareInfoAction")
@Scope("prototype")
public class SoftwareInfoAction extends BaseAction {

	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;

	@Autowired
	private SoftwareInfoService softwareInfoService;

	@Autowired
	private AppService appService;

	@Autowired
	private SoftwareInfohisService softwareInfohisService;

	@Autowired
	private UserService userService;
	@Autowired
	private DeployFileVersionService deployFileVersionService;

	@Autowired
	private TbBusiSysTreeService tbBusiSysTreeService;
	@Autowired
	private SoftwareFileInfoService softwareFileInfoService;

	private String bizid;// 业务系统树的id

	private TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj;// 软件实体类

	private List<TbBusiSoftwareInfoObj> resultList;// 结果集

	private String softid;// 页面传过来的软件id

	private List<TbSysAppObj> sysappList;// 上传时的基准应用集合

	private String sysappid;// 上传时基准应用的id

	private String softname;// 上传时软件名称

	private DeployFileVersionForm theForm;

	public DeployFileVersionForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DeployFileVersionForm theForm) {
		this.theForm = theForm;
	}

	private static Map<String, String> map = new HashMap<String, String>();

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public TbBusiSoftwareInfoObj getTbBusiSoftwareInfoObj() {
		return tbBusiSoftwareInfoObj;
	}

	public void setTbBusiSoftwareInfoObj(TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj) {
		this.tbBusiSoftwareInfoObj = tbBusiSoftwareInfoObj;
	}

	public List<TbBusiSoftwareInfoObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<TbBusiSoftwareInfoObj> resultList) {
		this.resultList = resultList;
	}

	public String getSoftid() {
		return softid;
	}

	public void setSoftid(String softid) {
		this.softid = softid;
	}

	public List<TbSysAppObj> getSysappList() {
		return sysappList;
	}

	public void setSysappList(List<TbSysAppObj> sysappList) {
		this.sysappList = sysappList;
	}

	public String getSysappid() {
		return sysappid;
	}

	public void setSysappid(String sysappid) {
		this.sysappid = sysappid;
	}

	public String getSoftname() {
		return softname;
	}

	public void setSoftname(String softname) {
		this.softname = softname;
	}

	// 列出软件信息，软件管理里面直接列出，业务系统下要获取业务系统的id
	/**
	 * @Title: listSoftwareInfo
	 * @Description: 查询出所有的软件信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午4:00:42
	 */
	public String listSoftwareInfo() {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		// 初始化分页
		tbBusiSoftwareInfoObj.setPagination(this.getPaginater().initPagination(request));

		List<TbBusiSoftwareInfoObj> returnList = new ArrayList<TbBusiSoftwareInfoObj>();

		// 查业务系统下
		if (bizid != null && !"".equals(bizid) && !"null".equals(bizid)) {
			TbBusiSysTree sysObj = new TbBusiSysTree();
			sysObj.setParentId(bizid);
			sysObj.setType(2);// 基准应用
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(sysObj);// 基准应用的集合
			// 得到基准应用id集合
			List<Integer> appidList = new ArrayList<Integer>();
			for (TbBusiSysTree tbBusiSysTree : appLst) {
				appidList.add(Integer.parseInt(tbBusiSysTree.getBusiId()));
			}

			if (appidList.size() > 0) {
				tbBusiSoftwareInfoObj.setAppidList(appidList);
				returnList = softwareInfoService.queryForListByAppidUseIn(tbBusiSoftwareInfoObj);
			}
		} else {
			// 查所有
			returnList = softwareInfoService.queryForListByObjNew(tbBusiSoftwareInfoObj);
		}

		if (resultList == null) {
			resultList = new ArrayList<TbBusiSoftwareInfoObj>();
		}

		for (TbBusiSoftwareInfoObj softObj : returnList) {
			String parth = softObj.getSOFTPARTH();
			String name = softObj.getNAME();
			name = name + parth.substring(parth.lastIndexOf("."));
			softObj.setNAME(name);
			resultList.add(softObj);
		}
		return "listSoftware";
	}

	/**
	 * @Title: delSoftwareInfo
	 * @Description: 删除列表中的软件信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午5:34:45
	 */
	public void delSoftwareInfo() {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		tbBusiSoftwareInfoObj.setID(Integer.parseInt(softid));

		List<TbBusiSoftwareInfoObj> list = softwareInfoService
				.queryForListByObjNew(tbBusiSoftwareInfoObj);

		int result = 0;

		int ret = softwareInfoService.deleteByObj(tbBusiSoftwareInfoObj);
		if (ret > 0) {
			result = 1;
			// 删除成功，则把相应的文件也删掉
			if (list != null && list.size() > 0) {
				TbBusiSoftwareInfoObj wareinfoObj = list.get(0);
				File f = null;
				String fileUrl = wareinfoObj.getSOFTPARTH();
				f = new File(fileUrl);
				if (f.exists()) {
					f.delete();
				}

				// 将删除信息插入到软件历史记录表
				TbBusiSoftwareInfoHisObj wareinfoHisObj = new TbBusiSoftwareInfoHisObj();
				try {
					BeanUtils.copyProperties(wareinfoHisObj, wareinfoObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				wareinfoHisObj.setSOFTWAREID(tbBusiSoftwareInfoObj.getID());
				wareinfoHisObj.setUPDATEUSER(session.get("name").toString());
				wareinfoHisObj.setUPDATETYPE("1");
				softwareInfohisService.insertByObj(wareinfoHisObj);
			}
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setOPERTYPE(2);
		operObj.setMESSAGE("删除软件" + tbBusiSoftwareInfoObj.getID());
		operObj.setREMARK("");
		operObj.setRESULT(result);
		operationService.insertByObj(operObj);// 写操作日志

		try {
			// PrintWriter pw = response.getWriter();
			// pw.print(ret);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: modSoftwareInfo
	 * @Description: 修改软件信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午6:17:37
	 */
	public String modSoftwareInfo() {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		tbBusiSoftwareInfoObj.setID(Integer.parseInt(softid));
		tbBusiSoftwareInfoObj = softwareInfoService.queryForListByObjNew(tbBusiSoftwareInfoObj)
				.get(0);

		TbSysAppObj appObj = new TbSysAppObj();
		appObj.setID(tbBusiSoftwareInfoObj.getAPPID());
		appObj = appService.queryByObj(appObj);
		if (appObj != null) {
			tbBusiSoftwareInfoObj.setAPPNAME(appObj.getAPPNAME());
		}
		return "modSoftwareInfo";
	}

	/**
	 * @Title: saveSoftwareInfo
	 * @Description: 保存软件信息
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午7:35:46
	 */
	public String saveSoftwareInfo() throws BaseException {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		operObj.setREMARK("");

		int ret = 0;
		int result = 0;
		if (tbBusiSoftwareInfoObj.getID() == null || "".equals(tbBusiSoftwareInfoObj.getID())) {
			ret = softwareInfoService.insertByObj(tbBusiSoftwareInfoObj);
			operObj.setOPERTYPE(1);
			operObj.setMESSAGE("新增软件" + tbBusiSoftwareInfoObj.getNAME());
		} else {
			ret = softwareInfoService.updateByObj(tbBusiSoftwareInfoObj);
			operObj.setOPERTYPE(3);
			operObj.setMESSAGE("修改软件" + tbBusiSoftwareInfoObj.getNAME() + "的信息");
			if (ret > 0) {
				// 入软件历史记录表
				TbBusiSoftwareInfoHisObj wareinfoHisObj = new TbBusiSoftwareInfoHisObj();
				try {
					BeanUtils.copyProperties(wareinfoHisObj, tbBusiSoftwareInfoObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				wareinfoHisObj.setSOFTWAREID(tbBusiSoftwareInfoObj.getID());
				wareinfoHisObj.setUPDATEUSER(session.get("name").toString());
				wareinfoHisObj.setUPDATETYPE("2");
				softwareInfohisService.insertByObj(wareinfoHisObj);
			}
		}
		if (ret != -1) {
			result = 1;
		}
		operObj.setRESULT(result);
		operationService.insertByObj(operObj);// 写操作日志

		JSONObject jo = new JSONObject();
		if (ret != -1) {
			jo.put("ret", ret);
			try {
				// PrintWriter pw = response.getWriter();
				// pw.print(jo);
				// pw.flush();
				// pw.close();
				PrintWriterOut.printWirter(response, jo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Title: uploadSoftware
	 * @Description: 进入上传页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午7:18:57
	 */
	public String uploadSoftware() {
		// 查出所有基准应用
		TbSysAppObj appObj = new TbSysAppObj();
		if (bizid != null && !"".equals(bizid) && !"null".equals(bizid)) {
			// 在业务系统下上传
			TbBusiSysTree sysObj = new TbBusiSysTree();
			sysObj.setParentId(bizid);
			sysObj.setType(2);// 基准应用树
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(sysObj);// 基准应用的集合
			// 得到基准应用id集合
			List<Integer> appidList = new ArrayList<Integer>();
			for (TbBusiSysTree tbBusiSysTree : appLst) {
				appidList.add(Integer.parseInt(tbBusiSysTree.getBusiId()));
			}

			if (appidList.size() > 0) {
				appObj.setIdlist(appidList);
				sysappList = appService.queryForListUseIn(appObj);
			} else {
				sysappList = new ArrayList<TbSysAppObj>();
			}
		} else {
			// 直接上传
			sysappList = appService.queryForListByObj(appObj);
		}
		return "upload";
	}

	/**
	 * @Title: saveSoftwareRAR
	 * @Description: 上传软件
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午7:30:51
	 */
	public String saveSoftwareRAR() throws Exception {
		// 文件开始上传，进行读写操作
		if (sysappid == null) {
			sysappid = tbBusiSoftwareInfoObj.getAPPID() + "";// 保存时通过对象获取
		}
		if (tbBusiSoftwareInfoObj.getATTACHMENTFileName() != null
				&& !"".equals(tbBusiSoftwareInfoObj.getATTACHMENTFileName())) {
			int index = tbBusiSoftwareInfoObj.getATTACHMENTFileName().lastIndexOf(".");
			softname += tbBusiSoftwareInfoObj.getATTACHMENTFileName().substring(index);
			map.clear();
			map.put("softname", softname);

			// 判断文件是否是指定的文件类型，如果不是，终止上传操作
			String suffix = tbBusiSoftwareInfoObj.getATTACHMENTFileName().substring(index + 1);
			String[] softsuffix = SuffixConstant.SOFTWARE.split(",");
			int count = 0;
			for (String string : softsuffix) {
				if (string.equals(suffix)) {
					count += 1;
					break;
				}
			}
			if (count < 1) {// 不是指定的文件类型
				return null;
			}
		}

		String b = (String) request.getParameter("b");

		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		tbSysAppObj.setID(Integer.parseInt(sysappid));
		tbSysAppObj = appService.queryByObj(tbSysAppObj);
		String appname = tbSysAppObj.getAPPNAME();
		String savePath = this.getSavePath(appname);
		int ret = 0;
		int result = 0;
		if (b == null || "".equals(b)) {
			FileOutputStream fos = null;
			try {
				File file = new File(savePath);
				if (!file.isDirectory()) {
					file.mkdirs();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				File file = new File(savePath + File.separator + softname);
				if (!file.exists()) {
					file.createNewFile();
				}
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(tbBusiSoftwareInfoObj.getATTACHMENT());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		// 读写完毕，进行添加操作，入软件信息表
		if (Boolean.valueOf(b)) {
			File tempFile = null;
			try {
				tempFile = new File(savePath + File.separator + map.get("softname"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DecimalFormat df = new DecimalFormat("0.00");
			tbBusiSoftwareInfoObj
					.setSOFTWARE_SIZE(String.valueOf(df.format(tempFile.length() / 1024.0 / 1024.0)));
			try {
				tbBusiSoftwareInfoObj.setSOFTPARTH(savePath + File.separator + map.get("softname"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			String target_tar_md5 = FileMd5Util.getFileSummaryByMD5(savePath + File.separator
					+ map.get("softname"));
			// 此处使用文件MD5,默认上传成功@huojla
			tbBusiSoftwareInfoObj.setTarget_tar_md5(target_tar_md5);
			tbBusiSoftwareInfoObj.setBase_tar_md5(target_tar_md5);

			ret = softwareInfoService.insertByObj(tbBusiSoftwareInfoObj);
			if (ret != -1) {
				TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
				operObj.setREMARK("");
				operObj.setOPERTYPE(1);
				operObj.setMESSAGE("新增软件" + tbBusiSoftwareInfoObj.getNAME());
				result = 1;
				operObj.setRESULT(result);
				operationService.insertByObj(operObj);// 写操作日志
			}
			if (bizid != null && !"".equals(bizid) && !"null".equals(bizid)) {
				map.clear();
				try {
					// PrintWriter pw = response.getWriter();
					// pw.print("success");
					// pw.flush();
					// pw.close();
					PrintWriterOut.printWirter(response, "success");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				return "saveSoftwareRAR";
			}
		}
		return null;
	}

	/**
	 * @Title: getSavePath
	 * @Description: 获取上传文件的保存路径
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午3:50:10
	 */
	private String getSavePath(String appname) {
		String savePath = "";
		TbGlobalConfigObj tbGlobalConfigObj = new TbGlobalConfigObj();
		tbGlobalConfigObj.setKEY("softwarerar");
		tbGlobalConfigObj = tbGlobalConfigService.queryByObj(tbGlobalConfigObj);
		if (tbGlobalConfigObj != null) {
			savePath = tbGlobalConfigObj.getVALUE();
			String datestr = new SimpleDateFormat("yyMMdd").format(new Date());
			savePath = savePath + File.separator + appname + File.separator + datestr;
		}
		// 创建文件目录
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return savePath;
	}

	/**
	 * @Title: catchSoftware
	 * @Description: 进入捕获页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午8:09:25
	 */
	public String catchSoftware() {
		// 获取基准应用列表
		TbBusiSysTree sysObj = new TbBusiSysTree();
		sysObj.setParentId(bizid);
		sysObj.setType(2);// 基准应用树
		List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(sysObj);// 基准应用树节点的集合
		// 得到基准应用id集合
		List<Integer> appidList = new ArrayList<Integer>();
		for (TbBusiSysTree tbBusiSysTree : appLst) {
			appidList.add(Integer.parseInt(tbBusiSysTree.getBusiId()));
		}

		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		if (appidList.size() > 0) {
			tbSysAppObj.setIdlist(appidList);
			sysappList = appService.queryForListUseIn(tbSysAppObj);
		}
		return "catchSoftware";
	}

	/**
	 * @Title: attchbasepath
	 * @Description: 获取所捕获应用的根路径
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午8:14:09
	 */
	public void attchbasepath() {
		String appid = request.getParameter("appid");
		TbSysAppObj tbSysAppObj = new TbSysAppObj();
		tbSysAppObj.setID(Integer.parseInt(appid));
		tbSysAppObj = appService.queryByObj(tbSysAppObj);
		String basepath = tbSysAppObj.getBASEPATH();
		try {
			response.setContentType("text/html;charset=utf-8");
			// PrintWriter pw = response.getWriter();
			// pw.print(basepath);
			// pw.flush();
			// pw.close();
			PrintWriterOut.printWirter(response, basepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: catchSysSoftware
	 * @Description: 捕获软件包
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng duangh修改，修改查询基准应用和主机用户信息，返回捕获结果在前台展示
	 * @version 1.0
	 * @createtime 2013-8-17 下午8:17:26
	 */
	public String catchSysSoftware() {
		String result = "error";

		String softpath = request.getParameter("softpath");
		String[] pathArray = softpath.split(",");

		String basepath = "";
		String localPath = "";
		String tarName = request.getParameter("tarName");
		tarName = tarName + DateUtil.getCurrentDateStrForTime();
		String appid = request.getParameter("appid");

		Map queryMap = new HashMap();
		queryMap.put("appID", appid);
		Map<String, String> resultMap = userService.queryAppUserList(queryMap);

		String host = (String) resultMap.get("IP");
		String username = (String) resultMap.get("USERNAME");
		String password = (String) resultMap.get("PASSWORD");
		int hostport = 22;
		Object port = resultMap.get("HOSTPORT");
		if (port != null && !"0".equals(port.toString())) {
			hostport = Integer.parseInt(port.toString());
		}

		basepath = (String) resultMap.get("BASEPATH");
		String appname = (String) resultMap.get("APPNAME");
		localPath = this.getSavePath(appname);

		Map<CaptureResultType, CaptureTarResultVO> captureResultMap = GetTarFromStandardUtil
				.getTarFromStandardByReturn(host, hostport, username, password, basepath,
						pathArray, tarName + ".tar", localPath);
		CaptureTarResultVO result_string = captureResultMap.get(CaptureResultType.RESULE_STRING);
		result = result_string.getResult();
		if (result != null && result.equalsIgnoreCase("success")) {
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
			tbBusiSoftwareInfoObj.setAPPID(Integer.parseInt(appid));
			tbBusiSoftwareInfoObj.setAPPNAME(appname);
			tbBusiSoftwareInfoObj.setNAME(tarName);
			tbBusiSoftwareInfoObj.setREMARK("捕获的软件包");
			tbBusiSoftwareInfoObj.setMANUFACTURERS("云管理平台！");
			tbBusiSoftwareInfoObj.setSYSTEM_REQUEST("同基准应用！");
			tbBusiSoftwareInfoObj.setCATCHFILES(softpath);
			/*
			 * 处理md5数据
			 */
			CaptureTarResultVO base_tar_md5 = captureResultMap.get(CaptureResultType.BASE_TAR_MD5);
			CaptureTarResultVO target_tar_md5 = captureResultMap
					.get(CaptureResultType.TARGET_TAR_MD5);
			CaptureTarResultVO file_md5 = captureResultMap.get(CaptureResultType.FILE_MD5);
			tbBusiSoftwareInfoObj.setBase_tar_md5(base_tar_md5.getResult());
			tbBusiSoftwareInfoObj.setTarget_tar_md5(target_tar_md5.getResult());

			List<CaptureFileVO> fileMd5List = file_md5.getFileMd5s();

			// TbSysUserinfoObj userinfo = (TbSysUserinfoObj)
			// request.getSession().getAttribute(Constant.USER_SESSION_KEY);
			// if (userinfo == null) {
			// tbBusiSoftwareInfoObj.setPROVIDERS("未获取到用户！");
			// } else {
			tbBusiSoftwareInfoObj.setPROVIDERS(session.get("account").toString());
			// }
			/*
			 * 移除setUPDATETIME，使用Xml直接生成
			 */
			tbBusiSoftwareInfoObj.setSOFTPARTH(localPath + File.separator + tarName + ".tar");
			File file = new File(localPath + File.separator + tarName + ".tar");
			DecimalFormat df = new DecimalFormat("0.00");
			tbBusiSoftwareInfoObj
					.setSOFTWARE_SIZE(String.valueOf(df.format(file.length() / 1024.0 / 1024.0)));
			// 入软件信息表
			String softwareId = "" + softwareInfoService.insertByObj(tbBusiSoftwareInfoObj);
			for (CaptureFileVO captureFile : fileMd5List) {

				TbBusiSoftwareFileInfoObj tbBusiSoftwareFileInfoObj = new TbBusiSoftwareFileInfoObj();
				tbBusiSoftwareFileInfoObj.setFILEMD5(captureFile.getFilemd5());
				tbBusiSoftwareFileInfoObj.setFILEPATH(captureFile.getFilepath());
				tbBusiSoftwareFileInfoObj.setSOFTWAREID(softwareId);
				tbBusiSoftwareFileInfoObj.setAPPID(appid);
				softwareFileInfoService.insertByObj(tbBusiSoftwareFileInfoObj);

			}
		}

		// 结果压入valuestack
		ActionContext.getContext().getValueStack().push(result);
		return "catchResult";
	}

	/**
	 * @Title: listSoftwareInfoForSel
	 * @Description: 版本管理添加版本时选择软件
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午1:50:23
	 */
	public String listSoftwareInfoForSel() throws BaseException {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		// 初始化分页
		tbBusiSoftwareInfoObj.setPagination(this.getPaginater().initPagination(request));

		List<TbBusiSoftwareInfoObj> returnList = new ArrayList<TbBusiSoftwareInfoObj>();

		// 查业务系统下
		if (bizid != null && !"".equals(bizid) && !"null".equals(bizid)) {// 注意busitabs里面传过来的是entityId，即busiid
			TbBusiSysTree sysObj = new TbBusiSysTree();
			sysObj.setBusiId(bizid);
			sysObj.setType(1);// 业务系统
			List<TbBusiSysTree> appLst = tbBusiSysTreeService.queryForTree(sysObj);// 业务系统的集合
			List<String> pidlist = new ArrayList<String>();
			if (appLst != null && appLst.size() > 0) {
				for (TbBusiSysTree tbBusiSysTree : appLst) {
					pidlist.add(tbBusiSysTree.getId());
				}
			}

			// 查基准应用
			TbBusiSysTree tbBusiSysTree = new TbBusiSysTree();
			tbBusiSysTree.setType(2);
			tbBusiSysTree.setPidlist(pidlist);
			List<TbBusiSysTree> appList = tbBusiSysTreeService
					.queryForListByParentIdList(tbBusiSysTree);

			// 得到基准应用id集合
			List<Integer> appidList = new ArrayList<Integer>();
			for (TbBusiSysTree sysTree : appList) {
				appidList.add(Integer.parseInt(sysTree.getBusiId()));
			}

			if (appidList.size() > 0) {
				tbBusiSoftwareInfoObj.setAppidList(appidList);
				returnList = softwareInfoService.queryForListByAppidUseIn(tbBusiSoftwareInfoObj);
			}
		} else {
			// 查所有
			returnList = softwareInfoService.queryForListByObjNew(tbBusiSoftwareInfoObj);
		}

		if (resultList == null) {
			resultList = new ArrayList<TbBusiSoftwareInfoObj>();
		}

		for (TbBusiSoftwareInfoObj softObj : returnList) {
			String parth = softObj.getSOFTPARTH();
			String name = softObj.getNAME();
			name = name + parth.substring(parth.lastIndexOf("."));
			softObj.setNAME(name);
			resultList.add(softObj);
		}
		return "listSoftwareInfoForSel";
	}

	/**
	 * @Title: downLoadSoftwareRar
	 * @Description: 软件下载
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午7:37:28
	 */
	public void downLoadSoftwareRar() {
		if (tbBusiSoftwareInfoObj == null) {
			tbBusiSoftwareInfoObj = new TbBusiSoftwareInfoObj();
		}
		tbBusiSoftwareInfoObj.setID(Integer.parseInt(softid));
		tbBusiSoftwareInfoObj = softwareInfoService.queryByObj(tbBusiSoftwareInfoObj);
		if (tbBusiSoftwareInfoObj != null) {
			String filePath = tbBusiSoftwareInfoObj.getSOFTPARTH();
			String fileName = tbBusiSoftwareInfoObj.getNAME();
			fileName = fileName + filePath.substring(filePath.lastIndexOf("."));
			DownLoadUtil.downLoadFile(filePath, fileName, response);
		} else {
			try {
				// PrintWriter pw = response.getWriter();
				// pw.write("没有相关记录");
				// pw.flush();
				// pw.close();
				PrintWriterOut.printWirter(response, "没有相关记录");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String fastCreateVersion() {
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
		double finalVersionNo = deployFileVersionService.getMaxVersionNoByAppid(freqobj);

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
		theForm.setLOCATION(path + "/" + appName + "/" + finalVersionNo + "/" + theForm.getNAME());
		// 取得系统的ID
		if (request.getParameter("bizid") != null) {
			String sysid = request.getParameter("bizid");
			theForm.setSYSID(sysid);
		}
		theForm.setFlag((short) 0);
		return "fastCreateVersion";

	}

}
