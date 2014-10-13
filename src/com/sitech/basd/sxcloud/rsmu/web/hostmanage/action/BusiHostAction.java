package com.sitech.basd.sxcloud.rsmu.web.hostmanage.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.TbBusiDeployExampleObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostConfigObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostHisObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusiHostObj;
import com.sitech.basd.sxcloud.rsmu.domain.hostmanage.TbBusibusiSwitchPortObj;
import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;
import com.sitech.basd.sxcloud.rsmu.service.deploy.DeployExampleService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostConfigService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostHisService;
import com.sitech.basd.sxcloud.rsmu.service.hostmanage.BusiHostService;
import com.sitech.basd.sxcloud.rsmu.service.softmanage.AppService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.hostmanage.form.BusiHostForm;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.data.DataExportService;
import com.sitech.basd.util.data.DataExportUtil;
import com.sitech.basd.util.data.WriterExcel;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
public class BusiHostAction extends CRUDBaseAction {
	private Logger logger = Logger.getLogger(BusiHostAction.class);

	/**
	 * @Title:获取主机列表请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String listBusiHost() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostForm();
		}
		theForm.setID(0);
		TbBusiHostObj obj = new TbBusiHostObj();
		if (theForm.getVLAN() != null && !"".equals(theForm.getVLAN())) {
			obj.setVLAN(theForm.getVLAN());
		}
		if (theForm.getIP() != null && !"".equals(theForm.getIP())) {
			obj.setIP(theForm.getIP());
		}
		if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
			obj.setSTATUS(theForm.getSTATUS());
		}
		List eployExampleList = null; // 页面应用 已经部署了主机编号
		TbSysAppObj appObj = new TbSysAppObj();
		List appList = appService.queryForListByObj(appObj);
		List userInfoList = busiHostService.queryForListByObj(obj); // 主机列表
		if (theForm.getAppid() != null && !theForm.getAppid().equals("")) {
			eployExampleList = new ArrayList();
			TbBusiDeployExampleObj dEObj = new TbBusiDeployExampleObj();
			dEObj.setAPPID(Integer.parseInt(theForm.getAppid()));
			List dEList = deployExampleService.queryForListByObj(dEObj);
			if (dEList != null && dEList.size() > 0) {
				for (int i = 0; i < dEList.size(); i++) {
					TbBusiDeployExampleObj bdEObj = (TbBusiDeployExampleObj) dEList.get(i);
					eployExampleList.add(bdEObj.getHOSTID());
				}
			}
		}

		// X86刀箱一
		List X86_1List = new ArrayList();
		// X86刀箱二
		List X86_2List = new ArrayList();
		// 小型机刀箱
		List XXJ_List = new ArrayList();
		// X86小型机混插刀箱
		List X86_XXJList = new ArrayList();
		String appNames;
		if (userInfoList != null && userInfoList.size() > 0) {
			for (int i = 0; i < userInfoList.size(); i++) {
				TbBusiHostObj tempObj = (TbBusiHostObj) userInfoList.get(i);
				// 根据主机IP查找到该主机的KBP_ClASS和DEVICE_ID
				/*
				 * TbBusiHostObj kbpClassObj = busiHostService
				 * .queryKbpClassAndDeviceId(tempObj); if (kbpClassObj != null)
				 * { // 根据主机KBP_ClASS和DEVICE_ID查找到该主机的CPU利用率 TbBusiHostObj
				 * cpuObj = busiHostService .queryCpu_Kpi(kbpClassObj); //
				 * 根据主机KBP_ClASS和DEVICE_ID查找到该主机的内存利用率 TbBusiHostObj memoryObj =
				 * busiHostService .queryMemory_Kpi(kbpClassObj);
				 * tempObj.setCPU_KPI(cpuObj.getCPU_KPI());
				 * tempObj.setMEMORY_KPI(memoryObj.getMEMORY_KPI()); }
				 */
				TbBusiDeployExampleObj tempdEObj = new TbBusiDeployExampleObj();
				tempdEObj.setHOSTID(tempObj.getID());
				appNames = "";
				List tempList = deployExampleService.queryForListByObj(tempdEObj);
				if (tempList != null && tempList.size() > 0) {
					for (int j = 0; j < tempList.size(); j++) {
						if (appNames != null && !appNames.equals(""))
							appNames = appNames + ",";
						appNames = appNames
								+ ((TbBusiDeployExampleObj) tempList.get(j)).getAPPNAME();
					}
				}
				tempObj.setAPPNAMES(appNames);
				if (tempObj != null && tempObj.getHOSTNAME() != null
						&& tempObj.getHOSTNAME().trim().startsWith("X86-1-")) {
					if (eployExampleList == null
							|| (eployExampleList != null && eployExampleList.contains(tempObj
									.getID())))
						X86_1List.add(tempObj);
				} else if (tempObj != null && tempObj.getHOSTNAME() != null
						&& tempObj.getHOSTNAME().trim().startsWith("X86-2-")) {
					if (eployExampleList == null
							|| (eployExampleList != null && eployExampleList.contains(tempObj
									.getID())))
						X86_2List.add(tempObj);
				} else if (tempObj != null && tempObj.getHOSTNAME() != null
						&& tempObj.getHOSTNAME().trim().startsWith("XXJ-")) {
					if (eployExampleList == null
							|| (eployExampleList != null && eployExampleList.contains(tempObj
									.getID())))
						XXJ_List.add(tempObj);
				} else {
					if (eployExampleList == null
							|| (eployExampleList != null && eployExampleList.contains(tempObj
									.getID())))
						X86_XXJList.add(tempObj);
				}
			}
		}
		/*
		 * TbBusiHostConfigObj tbconfig=new TbBusiHostConfigObj(); List
		 * userconfigList=busiHostConfigService.queryForListByObj(tbconfig);
		 * //主机用户列表 for(int i=0;i<userInfoList.size();i++){ String str="";
		 * TbBusiHostObj hostobj=(TbBusiHostObj)userInfoList.get(i); for(int
		 * j=0;j<userconfigList.size();j++){ TbBusiHostConfigObj
		 * configobj=(TbBusiHostConfigObj)userconfigList.get(j);
		 * if(configobj.getHOSTID()==hostobj.getID()){ str+="用户名：
		 * "+configobj.getHOSTUSERNAME()+"密码："+configobj.getHOSRPWD() +"磁盘空间:
		 * "+configobj.getSPACE()+"<br><br/>"; } } hostobj.setStrs(str);
		 * 
		 * userInfoList.set(i, hostobj); }
		 */

		theForm.setResultList(appList);
		theForm.setResultList1(X86_1List);
		theForm.setResultList2(X86_2List);
		theForm.setResultList3(XXJ_List);
		theForm.setResultList4(X86_XXJList);
		return LIST;
	}

	/**
	 * @Title:增加主机请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author zengls
	 * @version 1.0
	 */
	public String addBusiHost() throws BaseException {
		return ADD;
	}

	/**
	 * @Title:修改主机请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String modBusiHost() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostForm();
		}
		TbBusiHostObj obj = new TbBusiHostObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		obj.setID(Integer.parseInt(ID));
		TbBusiHostObj tempObj = busiHostService.queryByObj(obj);
		try {
			BeanUtils.copyProperties(theForm, tempObj);
			theForm.setVLAN_BF(tempObj.getVLAN());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MODIFY;
	}

	/**
	 * @Title:检查主机是否能够删除
	 * @Copyright: Copyright (c) 201204
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String checkBusiHostDel() throws BaseException, IOException {
		TbBusiHostConfigObj obj = new TbBusiHostConfigObj();
		HttpServletResponse response = Struts2Utils.getResponse();
		obj.setHOSTID(theForm.getID());
		List<?> list = busiHostConfigService.queryForListByObj(obj);
		List<String> jsonArr = new ArrayList<String>();
		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			jsonArr.add("NO");
		} else {
			jsonArr.add("YES");
		}
		json = JSONArray.fromObject(jsonArr);
		PrintWriterOut.printWirter(response, json.toString());

		return null;
	}

	/**
	 * @Title:删除主机请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String delBusiHost() throws BaseException {
		if (theForm == null) {
			theForm = new BusiHostForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = request.getParameter("ID");
		TbBusiHostObj obj = new TbBusiHostObj();
		obj.setID(Integer.parseInt(ID));
		int result = 0;
		/*
		 * try { BeanUtils.copyProperties(obj, theForm); } catch
		 * (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// @SuppressWarnings("unused")
		TbBusiHostObj busihostObj = busiHostService.queryByObj(obj);
		int ret = busiHostService.deleteByObj(obj);
		if (ret > 0) {
			// 删除主机信息成功的同时将删除信息插入到主机历史记录表
			TbBusiHostHisObj busihostHisObj = new TbBusiHostHisObj();
			try {
				BeanUtils.copyProperties(busihostHisObj, busihostObj);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 执行删除操作
			String username = session.get("account").toString();
			busihostHisObj.setHOSTID(obj.getID());
			busihostHisObj.setUPDATEUSER(username);
			busihostHisObj.setUPDATETYPE("2");
			result = busiHostHisService.insertByObj(busihostHisObj);

		}
		/*
		 * 插入操作日志表 TbSysOperationLogObj operObj =
		 * this.getTbSysOperationLogObj(request); operObj.setOPERTYPE(2);
		 * operObj.setMESSAGE("删除主机"); operObj.setREMARK("");
		 * operObj.setRESULT(result); @SuppressWarnings("unused") int retOper =
		 * operationService.insertByObj(operObj);// 写操作日志
		 */
		return "del";
	}

	/**
	 * @Title:保存主机请求
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String saveBusiHost() throws BaseException {
		TbBusiHostObj obj = new TbBusiHostObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String SQL_TYPE = request.getParameter("SQL_TYPE");
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ret = 0;
		int result = 0;

		// TbSysOperationLogObj operObj = this.getTbSysOperationLogObj(request);
		// operObj.setREMARK("");
		obj.setVLANIP(theForm.getIP());
		if (theForm.getID() == 0) {
			ret = busiHostService.insertByObj(obj);
			// 插入主机成功后，插入历史信息记录
			if (ret > 0) {
				// 修改主机信息成功的同时将修改信息插入到主机历史记录
				TbBusiHostHisObj busihostHisObj = new TbBusiHostHisObj();
				try {
					BeanUtils.copyProperties(busihostHisObj, theForm);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String username = session.get("account").toString();
				busihostHisObj.setHOSTID(obj.getID());
				busihostHisObj.setUPDATEUSER(username);
				busihostHisObj.setUPDATETYPE("0");
				busiHostHisService.insertByObj(busihostHisObj);

			}
		} else {
			if ("0".equals(SQL_TYPE)) {
				ret = busiHostService.updateByObj(obj);
			} else {
				ret = busiHostService.updateVlanByObj(obj);
			}
			// operObj.setOPERTYPE(3);
			// operObj.setMESSAGE("修改主机" + obj.getHOSTNAME());
			if (ret > 0) {
				// 修改主机信息成功的同时将修改信息插入到主机历史记录
				TbBusiHostHisObj busihostHisObj = new TbBusiHostHisObj();
				try {
					BeanUtils.copyProperties(busihostHisObj, theForm);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String username = session.get("account").toString();
				busihostHisObj.setHOSTID(obj.getID());
				busihostHisObj.setUPDATEUSER(username);
				busihostHisObj.setUPDATETYPE("1");
				busiHostHisService.insertByObj(busihostHisObj);

			}
		}
		if (ret > 0) {
			result = 1;
		}
		// operObj.setRESULT(result);
		// @SuppressWarnings("unused")
		// int retOper = operationService.insertByObj(operObj);// 写操作日志
		return "save";
	}

	/**
	 * @Title:验证主机IP是否已经存在
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String queryAjx_ip() throws BaseException {

		TbBusiHostObj obj = new TbBusiHostObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		// 获取入参
		String flag = request.getParameter("flag");
		// 判断主机名称是否重复
		if (flag.equals("name")) {
			String name = request.getParameter("name");
			obj.setHOSTNAME(name);
		} else {
			// 判断主机IP是否重复
			String ip = request.getParameter("ip");
			obj.setIP(ip);
		}

		String id = request.getParameter("id");

		// 查询是否存在该IP的主机
		TbBusiHostObj objt = busiHostService.queryByObj(obj);
		// 有查询结果的情况下
		if (objt != null) {
			int reseid = objt.getID();
			// 新增情况下，提示已存在该IP主机
			if ("".equals(id)) {
				showErrorMsg(1);
			} else {
				// 修改情况下，若为同一主机，则IP不重复
				if (reseid == Integer.parseInt(id)) {
					showErrorMsg(0);
				} else {
					// 否则IP重复，予以提示
					showErrorMsg(1);
				}
			}
		} else {
			// 没有同一IP的主机
			showErrorMsg(0);
		}
		return null;
	}

	// 根据传入进来的应用id来决定 获取对应的端口
	@SuppressWarnings("unchecked")
	public String Switch_port() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String vlan = request.getParameter("id");
		HttpServletResponse response = Struts2Utils.getResponse();
		if (vlan != null && !"".equals(vlan.trim())) {
			ArrayList<TbBusiHostObj> bloodList = null;
			TbBusibusiSwitchPortObj obj = new TbBusibusiSwitchPortObj();
			obj.setVLAN(Integer.parseInt(vlan));
			bloodList = (ArrayList<TbBusiHostObj>) busiHostService.queryForList_Switch_port(obj);
			// 存入json
			response.setContentType("text/html; charset=gb2312");
			JSONArray ja = new JSONArray();
			ja = JSONArray.fromObject(bloodList);
			// PrintWriter out = response.getWriter();
			// out.print(ja.toString());
			// out.close();
			PrintWriterOut.printWirter(response, ja.toString());
		}
		return null;

	}

	// 根据传入进来的id来决定 获取主机列表 或 录像列表
	@SuppressWarnings("unchecked")
	public String HostAndVideo() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String type = request.getParameter("id");
		HttpServletResponse response = Struts2Utils.getResponse();
		if (type != null && !"".equals(type.trim())) {
			ArrayList<TbBusiHostObj> bloodList = null;
			if (type.equals("2")) {
				TbBusiHostObj obj = new TbBusiHostObj();
				bloodList = (ArrayList<TbBusiHostObj>) busiHostService.queryForListByObj(obj);
			}
			// 存入json
			response.setContentType("text/html; charset=gb2312");
			JSONArray ja = new JSONArray();
			ja = JSONArray.fromObject(bloodList);
			// PrintWriter out = response.getWriter();
			// out.print(ja.toString());
			// out.close();
			PrintWriterOut.printWirter(response, ja.toString());
		}
		return null;

	}

	/**
	 * @title 主机列表导出到excel
	 * @author duangh
	 */
	public String exportxls() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");

		String filedisplay = "主机及用户列表.xls";
		try {
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
			String sql = "select IP,HOSTNAME,CASE STATUS WHEN 1 THEN '空闲' WHEN 2 THEN '非空闲' END STATUS,FREQUENCY,MEMORY,CASE OPERATE_SYSTEM WHEN '1' THEN 'LINUX' WHEN '2' THEN 'HP-UX' END OPERATE_SYSTEM,"
					+ "MANUFACTURERS,VLAN,VLANIP,MAC,CASE EQ_TYPE WHEN '1' THEN 'IBM POWER' WHEN '2' THEN 'BLADE' WHEN '3' THEN 'HP' END EQ_TYPE from TB_BUSI_HOST";
			List list = dataExportService.readTableData(sql);
			Map<String, String> map = new HashMap<String, String>();
			map.put("IP", "IP地址");
			map.put("HOSTNAME", "主机名称");
			map.put("STATUS", "主机状态");
			map.put("FREQUENCY", "主频");
			map.put("MEMORY", "内存");
			map.put("OPERATE_SYSTEM", "操作系统");
			map.put("MANUFACTURERS", "主机厂商");
			map.put("VLAN", "VLAN");
			map.put("VLANIP", "VLAPIP");
			map.put("MAC", "MAC地址");
			map.put("EQ_TYPE", "主机类型");
			list.add(0, map);
			String usersql = "select a.CREDITUSER,a.SPECIALPATH,CASE a.TYPE WHEN 1 THEN '超级管理员' WHEN 2 THEN '普通用户' END TYPE,a.HOSTUSERNAME,a.HOSRPWD,a.SPACE,"
					+ "a.SSHPORT,a.SSHPWD,a.LOGPATH,a.APPPATH,a.DEPLOYPATH,a.BASEPATH,a.STARTSHELL,a.SHUTDOWNSHELL,CASE a.IFEXAMPLE WHEN '0' THEN '生成' WHEN '1' THEN '不生成' END IFEXAMPLE,b.IP,c.APPNAME "
					+ " from tb_busi_host_config a left join TB_SYS_APP c on a.appid = c.id, tb_busi_host b where a.hostid = b.id order by a.ID desc";
			List configList = dataExportService.readTableData(usersql);
			Map<String, String> configmap = new HashMap<String, String>();
			configmap.put("CREDITUSER", "信任关系用户");
			configmap.put("SPECIALPATH", "例外文件路径");
			configmap.put("TYPE", "用户类型");
			configmap.put("HOSTUSERNAME", "主机用户名");
			configmap.put("HOSRPWD", "主机密码");
			configmap.put("SPACE", "磁盘空间");
			configmap.put("SSHPORT", "ssh端口");
			configmap.put("SSHPWD", "ssh密码");
			configmap.put("LOGPATH", "日志文件路径");
			configmap.put("APPPATH", "访问应用地址");
			configmap.put("DEPLOYPATH", "应用部署路径");
			configmap.put("BASEPATH", "应用根路径");
			configmap.put("STARTSHELL", "启动脚本路径");
			configmap.put("SHUTDOWNSHELL", "停止脚本路径");
			configmap.put("IFEXAMPLE", "是否生成实例");
			configmap.put("IP", "主机IP");
			configmap.put("APPNAME", "应用名称");
			configList.add(0, configmap);
			OutputStream out = response.getOutputStream();
			WriterExcel excel = new WriterExcel();
			HSSFWorkbook hw = excel.writeExcel("主机列表", list);
			hw = excel.writeExcel("用户列表", configList);
			hw.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("error:export host list & user list error " + e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @Title: downloadxlsTemplate
	 * @Description:下载导入主机的模板
	 * @author duangh
	 * @date Jun 15, 2013 5:11:32 PM
	 * @return
	 * @throws Exception
	 */
	public String downloadxlsTemplate() throws Exception {
		// 查询基准应用信息
		List<Map<String, String>> list = appService.queryForSheet();
		Map<String, String> map = new HashMap<String, String>();
		map.put("ID", "基准应用ID");
		map.put("APPNAME", "应用名称");
		map.put("DEPLOYPATH", "路径");
		list.add(0, map);

		// 要写入excel文件sheet页中的数据
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		String filedisplay = "主机及用户列表.xls";
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
		// 得到excel模板的文件流
		InputStream is = ServletActionContext.getServletContext().getResourceAsStream(
				"/sxcloud/hostmanage/template.xls");
		WriterExcel we = new WriterExcel(is);
		HSSFWorkbook hw = we.writeExcel("基准应用", list);
		OutputStream out = response.getOutputStream();
		hw.write(out);
		out.flush();
		out.close();
		return null;
	}

	private BusiHostService busiHostService;
	private BusiHostHisService busiHostHisService;
	private AppService appService;
	private DeployExampleService deployExampleService;
	private BusiHostConfigService busiHostConfigService;
	private DataExportService dataExportService;
	private BusiHostForm theForm;
	

	public BusiHostForm getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiHostForm theForm) {
		this.theForm = theForm;
	}

	public void setBusiHostConfigService(BusiHostConfigService busiHostConfigService) {
		this.busiHostConfigService = busiHostConfigService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public void setDeployExampleService(DeployExampleService deployExampleService) {
		this.deployExampleService = deployExampleService;
	}

	public void setBusiHostService(BusiHostService busiHostService) {
		this.busiHostService = busiHostService;
	}

	public void setBusiHostHisService(BusiHostHisService busiHostHisService) {
		this.busiHostHisService = busiHostHisService;
	}

	public void setDataExportService(DataExportService dataExportService) {
		this.dataExportService = dataExportService;
	}

	
}
