package com.sitech.basd.cloud3.web.departproject.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.cloud3.domain.departproject.DepartManObj;
import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.cloud3.service.departproject.DepartManService;
import com.sitech.basd.cloud3.service.departproject.DepartProjectService;
import com.sitech.basd.cloud3.service.departproject.RelationService;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.rsmu.config.SuffixConstant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.page.Pagination;
import com.sitech.basd.sxcloud.util.DownLoadUtil;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.excel.ExcelUtils;
import com.sitech.utils.servlet.PrintWriterOut;

@SuppressWarnings("all")
@Component("departProjectAction")
@Scope("prototype")
public class DepartProjectAction extends BaseAction {
	@Autowired
	private DepartProjectService departProjectService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private NetService netService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private DepartManService departManService;

	private List resultList;
	private DepartProjectObj obj = new DepartProjectObj();
	private TbSysUserinfoObj userObj = new TbSysUserinfoObj();// 用户
	private TbCloud2NetInfoObj netObj = new TbCloud2NetInfoObj();// 网络
	private RelationObj userAllObj = new RelationObj();
	private RelationObj userUsedObj = new RelationObj();
	private VMHostObj vmHostObj = new VMHostObj();// 虚拟机
	private List remaList; // 没有选中的虚拟机
	private List selectedList;// 被选中的虚拟机
	private String basisName;// 上传文件的名称
	private String proid;
	private String flag;
	private List<DepartManObj> departList;// 部门列表
	private String departId;

	/**
	 * 
	 * @Title: 查询获取项目信息列表
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: siweichao
	 * @version: 1.0
	 */
	public String listDepartProject() {
		String account = session.get("account").toString();
		int flagForAccount = 0;
		/***** 权限设置----超级管理员可绕过此权限 ******************/
		TbGlobalConfigObj gObj = new TbGlobalConfigObj();
		gObj.setKEY("user_auth");
		gObj = tbGlobalConfigService.queryByObj(gObj);
		String[] users = new String[] { "" };
		if (gObj.getVALUE() != null && !("").equals(gObj.getVALUE())) {
			users = gObj.getVALUE().split(",");
		}
		for (int i = 0; i < users.length; i++) {
			if (account.equals(users[i])) {
				flagForAccount = 1;
			}
		}
		List<DepartProjectObj> list = new ArrayList<DepartProjectObj>();
		// 设置条件及权限控制
		if ("admin".equals(account)) {// 超级管理员
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		} else if (flagForAccount == 1) {// 特殊用户
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		} else {// 普通用户
				// 找出当前登录账户相关的信息
			obj.setPROJECT_LEADER(account);
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		}
		// 查询出对应项目集合
		list = departProjectService.departProjectList(obj);

//		// 循环遍历，通过leader查找出对应的多个负责人的信息
//		String[] accounts = new String[] { "" };
//		for (int j = 0; j < list.size(); j++) {// 解析出每个负责人
//			if (list.get(j).getPROJECT_LEADER().contains("_")) {// 有多个负责人
//				accounts = list.get(j).getPROJECT_LEADER().split("_");
//			} else {// 仅有一个负责人
//				accounts[0] = list.get(j).getPROJECT_LEADER();
//			}
//			// 暂时存放负责人的相关信息
//			String name = "";
//			String phone = "";
//			String email = "";
//			for (int i = 0; i < accounts.length; i++) {// 根据解析出的账户信息，查询该账户相关信息
//				if (accounts[i] != null && !"".equals(accounts[i])) {
//					TbSysUserinfoObj user = new TbSysUserinfoObj();
//					user.setACCOUNT(accounts[i].trim());
//					user = userInfoService.queryByObj(user);
//					if (user != null) {
//						name = name + " " + user.getNAME();
//						phone = phone + " " + user.getMOBILE();
//					}
//				}
//				list.get(j).setPROJECT_LEADERNAME(name);
//				list.get(j).setCONTACT_PHONE(phone);
//				accounts[i] = "";
//			}
//		}
		resultList = list;
		return "success";
	}

	/**
	 * 
	 * @Title: 查询获取项目信息列表（仅用于展示）
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: siwiechao
	 * @version: 1.0
	 */
	public String listProject() {
		String account = session.get("account").toString();
		// 查看该用户是否存在于领导组
		int flagForAccount = 0;
		/***** 权限设置----超级管理员可绕过此权限 ******************/
		TbGlobalConfigObj gObj = new TbGlobalConfigObj();
		gObj.setKEY("user_auth");
		gObj = tbGlobalConfigService.queryByObj(gObj);
		String[] users = new String[] { "" };
		if (gObj.getVALUE() != null && !("").equals(gObj.getVALUE())) {
			users = gObj.getVALUE().split(",");
		}
		for (int i = 0; i < users.length; i++) {
			if (account.equals(users[i])) {
				flagForAccount = 1;
			}
		}
		// 设置查询条件
		List<DepartProjectObj> list = new ArrayList<DepartProjectObj>();
		if ("admin".equals(account)) {
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		} else if (flagForAccount == 1) {
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		} else {
			// 找出当前登录账户相关的信息
			obj.setPROJECT_LEADER(account);
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		}
		// 查询项目列表
		list = departProjectService.departProjectList(obj);
		// 释放obj的projectLeader字段
		obj.setPROJECT_LEADER("");

//		// 循环遍历，通过leader查找出对应的多个负责人的信息
//		String[] accounts = new String[] { "" };
//		for (int j = 0; j < list.size(); j++) {// 解析出每个负责人
//			if (list.get(j).getPROJECT_LEADER().contains("_")) {// 有多个负责人
//				accounts = list.get(j).getPROJECT_LEADER().split("_");
//			} else {// 仅有一个负责人
//				accounts[0] = list.get(j).getPROJECT_LEADER();
//			}
//			// 暂时存放负责人的相关信息
//			String name = "";
//			String phone = "";
//			String email = "";
//			for (int i = 0; i < accounts.length; i++) {// 根据解析出的账户信息，查询该账户相关信息
//				if (accounts[i] != null && !"".equals(accounts[i])) {
//					TbSysUserinfoObj user = new TbSysUserinfoObj();
//					user.setACCOUNT(accounts[i].trim());
//					List temp = userInfoService.queryLikeForListByObj(user);
//					if (temp!=null && temp.size()>0){
//						user = (TbSysUserinfoObj)temp.get(0);
//						if (user != null) {
//							name = name + " " + user.getNAME();
//							phone = phone + " " + user.getMOBILE();
//						}
//					}
//				}
//				list.get(j).setPROJECT_LEADERNAME(name);
//				list.get(j).setCONTACT_PHONE(phone);
//				accounts[i] = "";
//			}
//		}
		resultList = list;
		return "listProject";
	}

	/**
	 * 
	 * @Title: 删除项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String delDepartProject() {
		String account = session.get("account").toString();
		// 插入历史
		obj.setAccount(account);
		departProjectService.insertDepartProjectHis(obj);
		// 删除项目
		obj.setAccount("");
		departProjectService.delDepartProject(obj);
		return "del";
	}

	/**
	 * 
	 * @Title: 修改项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String modDepartProject() {
		obj = departProjectService.queryDepartProjectOne(obj);
		int MEMORY_SIZE = (int) Math.round((obj.getMEMORY_SIZE() / 1024 * 100) / 100.0);
		int STORAGE_SIZE = (int) Math.round((obj.getSTORAGE_SIZE() / 1024 * 100) / 100.0);
		obj.setSTORAGE_SIZE(STORAGE_SIZE);
		obj.setMEMORY_SIZE(MEMORY_SIZE);
		String[] accounts = new String[] { "" };
		accounts = obj.getAccount().split("_");
		String name = "";
		String phone = "";
		String email = "";
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i] != null && !"".equals(accounts[i])) {
				TbSysUserinfoObj user = new TbSysUserinfoObj();
				user.setACCOUNT(accounts[i]);
				user = userInfoService.queryByObj(user);
				if (user != null) {
					name = name + " " + user.getNAME();
					phone = phone + " " + user.getMOBILE();
					email = email + " " + user.getEMAIL();
				}
			}
		}
		obj.setCONTACT_PHONE(phone);
		obj.setPROJECT_LEADERNAME(name);
		obj.setEMAIL(email);
		/**************** 添加查询科室列表模块 ************************/
		DepartManObj dObj = new DepartManObj();
		dObj.setType("2");// 代表科室
		departList = departManService.queryForDepartListByObj(dObj);
		flag = "mod";
		return "modify";
	}

	/**
	 * 
	 * @Title: 新增添加项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String addDepartProject() {
		/**************** 添加查询部门列表模块 ************************/
		DepartManObj dObj = new DepartManObj();
		departList = departManService.queryForDepartListByObj(dObj);
		flag = "add";
		return "add";
	}

	/**
	 * 
	 * @Title: 保存项目信息
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String saveDepartProject() {
		String path = "";
		if (obj.getBasis() != null && !"".equals(obj.getBasis())) {
			path = this.getSavePath(obj.getBasis()) + File.separator + obj.getBasis();
			obj.setBasis(path);
		}
		int data = 0;
		obj.setPROJECT_LEADER(obj.getAccount());
		obj.setNETWORK_DOMAIN(obj.getNetid());
		obj.setMEMORY_SIZE(obj.getMEMORY_SIZE() * 1024);
		obj.setSTORAGE_SIZE(obj.getSTORAGE_SIZE() * 1024);
		if (obj.getID() == null || "".equals(obj.getID()) || "null".equals(obj.getID())) {
			// 新增
			departProjectService.insertDepartProject(obj);
		} else {
			// 修改
			departProjectService.updateDepartProject(obj);
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// p = response.getWriter();
		// p.print(String.valueOf(data));
		PrintWriterOut.printWirter(response, String.valueOf(data));
		// p.flush();
		// p.close();
		return null;
	}

	/**
	 * 
	 * @Title: 获取用户信息列表，用于选择项目负责人
	 * @Copyright:Copyright (c) Jul 11, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryUserInfoList() {
		String account = session.get("account").toString();
		if (!"admin".equals(account)) {
			userObj.setACCOUNT(account);
		}
		userObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = userInfoService.queryLikeForListByObj(userObj);
		return "user";
	}

	/**
	 * 
	 * @Title: 查询获取网络域信息列表，用户选择网络域
	 * @Copyright:Copyright (c) Jul 11, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String queryNetDomainList() {
		netObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = netService.queryForListByObj(netObj);
		return "domain";
	}

	/**
	 * 
	 * @Title: 查询获取项目历史信息列表
	 * @Copyright:Copyright (c) Jul 10, 2013
	 * @Company: si-tech
	 * @author: caowx
	 * @version: 1.0
	 */
	public String listDepartProjectHis() {
		String account = session.get("account").toString();
		if ("admin".equals(account)) {
			// 进行分页处理
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			// 获取结果列表
			resultList = departProjectService.departProjectHisList(obj);
		} else {
			TbSysUserinfoObj user = new TbSysUserinfoObj();
			user.setACCOUNT(account);
			user = userInfoService.queryByObj(user);
			obj.setPROJECT_LEADER(user.getNAME());
			obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
			resultList = departProjectService.departProjectHisList(obj);
		}
		return "his";
	}

	/**
	 * 
	 * @Title: 添加虚拟机列表
	 * @Copyright:2013-9-5
	 * @Company: si-tech
	 * @author: yanggl
	 * @version: 1.0
	 */
	public String addVM() {
		VMHostObj vmObj = new VMHostObj();
		// vmObj.setPROJECT_ID(vmHostObj.getPROJECT_ID());
		vmObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		remaList = relationService.queryRemainVMList(vmObj);
		return "addVM";
	}

	/**
	 * 
	 * @Title: 展示虚拟机列表
	 * @Copyright:2013-9-5
	 * @Company: si-tech
	 * @author: yanggl
	 * @version: 1.0
	 */
	public String showVM() {
		VMHostObj vmObj = new VMHostObj();
		vmObj.setPROJECT_ID(vmHostObj.getPROJECT_ID());
		vmObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		selectedList = relationService.querySelectedVMList(vmObj);
		return "showVM";
	}

	/**
	 * 
	 * @Title: exportVmExcel
	 * @Description: 项目下虚拟机资源导出报表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws IOException
	 * @createtime 2013-9-9
	 */
	public String exportVmExcel() throws IOException {
		List<VMHostObj> list = new ArrayList<VMHostObj>();
		VMHostObj vmObj = new VMHostObj();
		vmObj.setPROJECT_ID(vmHostObj.getPROJECT_ID().trim());
		vmObj.setPagination(new Pagination(1000));
		list = relationService.querySelectedVMList(vmObj);
		List<String[]> rowsList = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		for (VMHostObj obj : list) {
			rowsList.add(new String[] { obj.getVH_NAME(), obj.getVH_IP(), type(obj.getVH_TYPE()),
					obj.getVH_SYSTEM(), obj.getVH_CPU(),
					df.format(Double.parseDouble(obj.getVH_MEM()) / 1024.00),
					df.format(Double.parseDouble(obj.getVH_STORAGE()) / 1024.00) });
		}
		// 获取采集指标数据
		String[] title = new String[] { "虚拟机名称", "IP地址", "虚拟化类型", "操作系统类型", "CPU(核)", "内存(G)",
				"存储(G)" };
		OutputStream os = null;
		try {
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment;filename=VmForProjectInfo.xls");// 定义文件名
			os = response.getOutputStream();
			StringBuilder sb = new StringBuilder();
			sb.append("项目所属虚拟机列表");
			ExcelUtils.writeDataExcel("sheet", sb.toString(), title, rowsList, os);
		} catch (IOException e) {

		} finally {
			if (os != null) {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: 删除虚拟机列表
	 * @Copyright:2013-9-5
	 * @Company: si-tech
	 * @author: yanggl
	 * @version: 1.0
	 */
	public String delVM() {
		VMHostObj vmObj = new VMHostObj();
		vmObj.setPROJECT_ID(vmHostObj.getPROJECT_ID());
		vmObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		selectedList = relationService.querySelectedVMList(vmObj);
		return "delVM";
	}

	/**
	 * 
	 * @Title: 插入到项目和虚拟机关系表中
	 * @Copyright:2013-9-5
	 * @Company: si-tech
	 * @author: yanggl
	 * @version: 1.0
	 */
	public String saveRelation() {
		int data = 0;
		String vhuuid = vmHostObj.getVH_UUID();
		String conectId = vmHostObj.getConnectId();
		String[] vh = vhuuid.split(",");
		String[] co = conectId.split(",");
		RelationObj reObj = new RelationObj();
		for (int i = 0; i <= vh.length - 1; i++) {
			reObj.setVmuuId(vh[i]);
			reObj.setConnectId(co[i]);
			reObj.setProjectId(vmHostObj.getPROJECT_ID());
			relationService.insertRelation(reObj);
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// p = response.getWriter();
		// p.print(String.valueOf(data));
		PrintWriterOut.printWirter(response, String.valueOf(data));
		// p.flush();
		// p.close();
		return null;
	}

	/**
	 * 
	 * @Title: 删除项目和虚拟机关系表
	 * @Copyright:2013-9-5
	 * @Company: si-tech
	 * @author: yanggl
	 * @version: 1.0
	 */
	public String delRelation() {
		int data = 0;
		String vhuuid = vmHostObj.getVH_UUID();
		String conectId = vmHostObj.getConnectId();
		String[] vh = vhuuid.split(",");
		String[] co = conectId.split(",");
		RelationObj reObj = new RelationObj();
		for (int i = 0; i <= vh.length - 1; i++) {
			reObj.setVmuuId(vh[i]);
			reObj.setConnectId(co[i]);
			reObj.setProjectId(vmHostObj.getPROJECT_ID());
			relationService.deleteRelation(reObj);
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// p = response.getWriter();
		// p.print(String.valueOf(data));
		PrintWriterOut.printWirter(response, String.valueOf(data));
		// p.flush();
		// p.close();
		return null;
	}

	/**
	 * 
	 * @Title: queryXXVMByObj
	 * @Description: 通过虚拟机名字，IP,虚拟机类型查询虚拟机列表
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-5
	 */
	public String queryAddVMByObj() {
		vmHostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		remaList = relationService.queryRemainVMList(vmHostObj);
		return "addVM";
	}

	public String queryVMByObj() {
		vmHostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		selectedList = relationService.querySelectedVMList(vmHostObj);
		return "showVM";
	}

	public String queryDelVMByObj() {
		vmHostObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		selectedList = relationService.querySelectedVMList(vmHostObj);
		return "delVM";
	}

	/**
	 * 
	 * @Title: vmResourceRate
	 * @Description: 虚拟机资源使用率
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-9
	 */
	public String vmResourceRate() {
		DepartProjectObj departObj = new DepartProjectObj();
		departObj.setID(vmHostObj.getPROJECT_ID());
		userAllObj = departProjectService.queryAllCountResource(departObj);
		userUsedObj = departProjectService.queryUsedCountResource(departObj);
		return "vmResourceRate";
	}

	/**
	 * 
	 * @Title: projectResourceRate
	 * @Description: 项目资源分配率
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-9
	 */
	public String projectResourceRate() {
		DepartProjectObj departObj = new DepartProjectObj();
		if (departId != null && !"".equals(departId)) {
			departObj.setDEPART_ID(departId);
			// userUsedObj = departProjectService.queryUsedResource(departObj);
			// userAllObj = departProjectService.queryAllResource(departObj);
		}
		return "projectResourceRate";
	}

	/**
	 * 
	 * @Title: exportDepartExcel
	 * @Description: 科室下资源导出报表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws IOException
	 * @createtime 2013-9-9
	 */
	public String exportProjectExcel() throws IOException {
		if(obj.getPROJECT_NAME()!=null&!("").equals(obj.getPROJECT_NAME())){
			
			obj.setPROJECT_NAME(java.net.URLDecoder.decode(obj.getPROJECT_NAME(), "UTF-8")) ;
		}
		if(obj.getPROJECT_LEADERNAME()!=null&!("").equals(obj.getPROJECT_LEADERNAME())){
			
			obj.setPROJECT_LEADERNAME(java.net.URLDecoder.decode(obj.getPROJECT_LEADERNAME(),"UTF-8")) ;
		}
		List<RelationObj> all = departProjectService.queryAllResource(obj);
		List<RelationObj> used = departProjectService.queryUsedResource(obj);
		List<String[]> rowsList = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < all.size(); i++) {
			for (int j = 0; j < used.size(); j++) {
				if (used.get(j).getPROJECT_NAME() != null
						&& used.get(j).getPROJECT_NAME().equals(all.get(i).getPROJECT_NAME())) {
					rowsList.add(new String[] {
							all.get(i).getPROJECT_NO(),
							all.get(i).getPROJECT_NAME(),
							all.get(i).getDEPART_NAME(),
							all.get(i).getCPU_ALL_COUNT(),
							used.get(j).getCPU_USED_COUNT(),
							df.format(
									Integer.parseInt(used.get(j).getCPU_USED_COUNT()) * 100.00
											/ Integer.parseInt(all.get(i).getCPU_ALL_COUNT()))
									.toString(),
							df.format(Integer.parseInt(all.get(i).getMEM_ALL_MB()) / 1024.00),
							df.format(Integer.parseInt(used.get(j).getMEM_USED_MB()) / 1024.00),
							df.format(
									Integer.parseInt(used.get(j).getMEM_USED_MB()) * 100.00
											/ Integer.parseInt(all.get(i).getMEM_ALL_MB()))
									.toString(),
							df.format(Integer.parseInt(all.get(i).getSTORAGE_ALL_MB()) / 1024.00 / 1024.00),
							df.format(Integer.parseInt(used.get(j).getSTORAGE_USED_MB()) / 1024.00 / 1024.00),
							df.format(
									Integer.parseInt(used.get(j).getSTORAGE_USED_MB()) * 100.00
											/ Integer.parseInt(all.get(i).getSTORAGE_ALL_MB()))
									.toString() ,
							all.get(i).getIP_ALL_COUNT(),
							used.get(j).getIP_USED_COUNT(),
							df.format(
									Integer.parseInt(used.get(j).getIP_USED_COUNT()) * 100.00
											/ Integer.parseInt(all.get(i).getIP_ALL_COUNT()))
									.toString(),
							all.get(i).getPROJECT_LEADERNAME(),
							all.get(i).getVM_ALL_COUNT()
					});
					break;
				} else {
					if (j == used.size() - 1) {
						rowsList.add(new String[] {
								all.get(i).getPROJECT_NO(),
								all.get(i).getPROJECT_NAME(),
								all.get(i).getDEPART_NAME(),
								all.get(i).getCPU_ALL_COUNT(),
								"0",
								"0.00",
								df.format(Integer.parseInt(all.get(i).getMEM_ALL_MB()) / 1024.00),
								"0.00",
								"0.00",
								df.format(Integer.parseInt(all.get(i).getSTORAGE_ALL_MB()) / 1024.00 / 1024.00),
								"0.00", "0.00" ,
								all.get(i).getIP_ALL_COUNT(),
								"0",
								"0.00",
								all.get(i).getPROJECT_LEADERNAME(),
								all.get(i).getVM_ALL_COUNT()
						});
					} else {
						continue;
					}
				}
			}
		}
		// 获取采集指标数据
		String[] title = new String[] {"项目编号", "项目名称", "部门名称","CPU预算总量", "CPU使用量", "CPU分配率(%)", "内存预算总量(G)",
				"内存使用量(G)", "内存分配率(%)", "存储预算总量(T)", "存储使用量(T)", "存储分配率(%)","IP预算总量", "IP使用量", "IP分配率(%)","项目负责人","虚拟机个数" };
		OutputStream os = null;
		try {
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment;filename=DepartInfo.xls");// 定义文件名
			os = response.getOutputStream();
			StringBuilder sb = new StringBuilder();
			sb.append("项目预算报表");
			ExcelUtils.writeDataExcel("sheet", sb.toString(), title, rowsList, os);
		} catch (IOException e) {

		} finally {
			if (os != null) {
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
		return null;
	}

	public String saveBasis() {
		/* String savePath = this.getSavePath(obj.getBASISNAME()); */
		String basis = obj.getBasis();
		String savePath = this.getSavePath(basis);

		// ------update by lipp 校验文件的类型
		int index = basis.lastIndexOf(".");
		String suffix = basis.substring(index + 1);
		String[] departsuffix = SuffixConstant.DEPARTFILE.split(",");
		int count = 0;
		for (String string : departsuffix) {
			if (string.equals(suffix)) {
				count += 1;
				break;
			}
		}
		if (count < 1) {
			return null;
		}
		// --------

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
			File file = new File(savePath + File.separator + obj.getBasis());
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
			fis = new FileInputStream(obj.getBasisObj());
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

	/**
	 * @Title: getSavePath
	 * @Description: 获取上传文件的保存路径
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-11 下午19:50:10
	 */
	private String getSavePath(String appname) {
		String savePath = "";
		TbGlobalConfigObj tbGlobalConfigObj = new TbGlobalConfigObj();
		tbGlobalConfigObj.setKEY("basis"); // 全局配置中的关键字固定为basis
		tbGlobalConfigObj = tbGlobalConfigService.queryByObj(tbGlobalConfigObj);
		if (tbGlobalConfigObj != null) {
			savePath = tbGlobalConfigObj.getVALUE();
			/* savePath = savePath + File.separator + appname; */
			/* savePath = savePath ; */
		}
		/*
		 * // 创建文件目录 File file = new File(savePath); if(!file.exists()){
		 * file.mkdirs(); }
		 */
		return savePath;
	}

	/**
	 * @Title: downLoadBasisFile
	 * @Description: 附件下载
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午11:05:40
	 */
	public void downLoadBasisFile() {
		DepartProjectObj departProjectObj = new DepartProjectObj();
		departProjectObj.setID(proid);
		departProjectObj = departProjectService.queryDepartProjectOne(departProjectObj);
		if (departProjectObj != null) {
			String filePath = departProjectObj.getBasis();
			String fileName = "附件";
			fileName = fileName + filePath.substring(filePath.lastIndexOf("."));
			DownLoadUtil.downLoadFile(filePath, fileName, response);
		} else {
			try {
				// PrintWriter pw = response.getWriter();
				// pw.write("没有相关记录");
				PrintWriterOut.printWirter(response, "没有相关记录");
				// pw.flush();
				// pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String type(String type) {
		if ("1".equals(type)) {
			return "VMWARE";
		} else if ("3".equals(type)) {
			return "XEN";
		} else {
			return "-";
		}
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public DepartProjectObj getObj() {
		return obj;
	}

	public void setObj(DepartProjectObj obj) {
		this.obj = obj;
	}

	public VMHostObj getVmHostObj() {
		return vmHostObj;
	}

	public void setVmHostObj(VMHostObj vmHostObj) {
		this.vmHostObj = vmHostObj;
	}

	public List getRemaList() {
		return remaList;
	}

	public void setRemaList(List remaList) {
		this.remaList = remaList;
	}

	public List getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List selectedList) {
		this.selectedList = selectedList;
	}

	public TbSysUserinfoObj getUserObj() {
		return userObj;
	}

	public void setUserObj(TbSysUserinfoObj userObj) {
		this.userObj = userObj;
	}

	public TbCloud2NetInfoObj getNetObj() {
		return netObj;
	}

	public void setNetObj(TbCloud2NetInfoObj netObj) {
		this.netObj = netObj;
	}

	public RelationObj getUserAllObj() {
		return userAllObj;
	}

	public void setUserAllObj(RelationObj userAllObj) {
		this.userAllObj = userAllObj;
	}

	public RelationObj getUserUsedObj() {
		return userUsedObj;
	}

	public void setUserUsedObj(RelationObj userUsedObj) {
		this.userUsedObj = userUsedObj;
	}

	public String getBasisName() {
		return basisName;
	}

	public void setBasisName(String basisName) {
		this.basisName = basisName;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<DepartManObj> getDepartList() {
		return departList;
	}

	public void setDepartList(List<DepartManObj> departList) {
		this.departList = departList;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

}
