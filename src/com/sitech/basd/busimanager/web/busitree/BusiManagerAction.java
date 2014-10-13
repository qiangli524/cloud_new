package com.sitech.basd.busimanager.web.busitree;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService;
import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.service.system.UserInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.web.showVm.form.ShowVmForm;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: TbBusiSysTreeAction
 * </p>
 * <p>
 * Description: 业务系统树页面Action类
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
 * @createtime 2013-5-20 下午1:53:45
 * 
 */
// Struts配置busisys_*
@Controller("busiManagerAction")
@Scope("prototype")
public class BusiManagerAction extends CRUDBaseAction {

	@Autowired
	private BusiManagerTreeService busiManagerTreeService;

	@Autowired
	private VMHostService vmHostService;

	@Autowired
	private HostInfoService hostInfoService;

	@Autowired
	private UserInfoService userInfoService;

	private static final long serialVersionUID = 7394314502309143247L;

	private BusiManagerTree theForm;

	private ShowVmForm vmForm;
	private HostManageForm hostForm;

	private String id;

	private String type;

	private String name;

	private String parent_id;

	private String connect_id;

	private String entity_id;
	
	private String eq_id;

	private String fusionChartsString;

	private String top_num;

	private List resultList;

	private String flag;

	 // 下载文件名
    private String fileName;
    
	private TbSysUserinfoObj userObj = new TbSysUserinfoObj();

	/************ 业务中心对应操作 ************************/

	public String addBusiSysCenter() {
		return "add_sys_busi_center";
	}

	/**
	 * 
	 * @Title: updateBusiSysCenter
	 * @Description: 更新业务中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String updateBusiSysCenter() {
		BusiManagerTree obj = new BusiManagerTree();
		if (theForm.getId() != null && !"".equals(theForm.getId())) {
			obj.setId(theForm.getId());
		}
		List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
		if (list != null) {
			if (list.size() > 0) {
				theForm = list.get(0);
			}
		}
		return "updateBusiSysCenter";
	}

	/**
	 * 
	 * @Title: saveBusiSysCenter
	 * @Description: 保存业务中心信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveBusiSysCenter() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			BusiManagerTree obj = new BusiManagerTree();
			BeanUtils.copyProperties(obj, theForm);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				busiManagerTreeService.updateBusiManagerTreeByObj(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			} else {
				BusiManagerTree objTemp = new BusiManagerTree();
				objTemp.setType(0);
				objTemp.setName(obj.getName());
				List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();
					return null;
				}
				obj.setId(RandomUUID.getUuid());// 定义id
				obj.setType(0);// 定义类型:0表示0层后面的依次类推
				obj.setParent_id("0");// 定义其父节点为0(根节点固定的)
				busiManagerTreeService.insertBusiManagerTree(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delBusiSysCenter
	 * @Description: 删除业务中心
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String delBusiSysCenter() {
		BusiManagerTree obj = new BusiManagerTree();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			} else {
				obj.setParent_id("");
				obj.setId(id);
				busiManagerTreeService.deleteBusiManagerTreeById(obj);
				json.put("result", 2); // 1不能删除
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();

				return null;
			}
		}
		return null;
	}

	/********** 业务系统 **********************************/
	public String addBizSys() {
		if (theForm == null) {
			theForm = new BusiManagerTree();
		}
		theForm.setParent_id(parent_id);
		return "addBusiSys";
	}

	/**
	 * 
	 * @Title: updateBizSys
	 * @Description: 更新业务系统信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String updateBizSys() {
		BusiManagerTree obj = new BusiManagerTree();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			if (list.size() > 0) {
				theForm = list.get(0);
			}
		}
		return "updateBizSys";
	}

	/**
	 * 
	 * @Title: saveBizSys
	 * @Description: 保存业务系统信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveBizSys() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			BusiManagerTree obj = new BusiManagerTree();
			BeanUtils.copyProperties(obj, theForm);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				busiManagerTreeService.updateBusiManagerTreeByObj(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				// out.close();
				return null;
			} else {
				obj.setId(RandomUUID.getUuid());// 定义id
				obj.setType(1);// 定义类型:1表示业务系统层
				BusiManagerTree objTemp = new BusiManagerTree();
				objTemp.setType(1);
				objTemp.setName(obj.getName());
				List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();
					return null;
				}
				busiManagerTreeService.insertBusiManagerTree(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delBizSys
	 * @Description: 删除业务系统
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String delBizSys() {
		BusiManagerTree obj = new BusiManagerTree();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			try {
				JSONObject json = new JSONObject();
				// PrintWriter out = response.getWriter();

				if (list.size() > 0) {// 存在子节点不能删除
					json.put("result", 1); // 1不能删除
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();
					return null;
				} else {
					obj.setParent_id("");
					obj.setId(id);
					busiManagerTreeService.deleteBusiManagerTreeById(obj);
					json.put("result", 2); //
					json.put("parent_id", parent_id);
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/******************* 子系统 *****************************/
	public String addSysApp() {
		if (theForm == null) {
			theForm = new BusiManagerTree();
		}
		theForm.setParent_id(parent_id);
		flag = "add";
		return "addSysApp";
	}

	/**
	 * 
	 * @Title: updateSysApp
	 * @Description: 更新基准应用信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String modifySysApp() {
		BusiManagerTree obj = new BusiManagerTree();
		TbSysUserinfoObj tObj = new TbSysUserinfoObj();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			if (list.size() > 0) {
				theForm = list.get(0);
			}
			tObj.setID(Integer.valueOf(theForm.getUser_id()));
			List<TbSysUserinfoObj> userList = userInfoService.queryLikeForListByObj(tObj);
			if (userList.size() == 1) {
				theForm.setUserName(userList.get(0).getNAME());
			}
			flag = "update";
		}
		return "modifySysApp";
	}

	/**
	 * 
	 * @Title: saveSysApp
	 * @Description: 子系统
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveSysApp() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			BusiManagerTree obj = new BusiManagerTree();
			BeanUtils.copyProperties(obj, theForm);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				busiManagerTreeService.updateBusiManagerTreeByObj(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			} else {
				// 判断是否存在重名
				BusiManagerTree objTemp = new BusiManagerTree();
				objTemp.setType(2);
				objTemp.setName(obj.getName());
				List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();

					return null;
				}
				// 插入
				obj.setType(2);// 定义类型:2表示业务子系统
				obj.setId(RandomUUID.getUuid());// 定义id
				busiManagerTreeService.insertBusiManagerTree(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delSysApp
	 * @Description: 删除基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String delSysApp() {
		BusiManagerTree obj = new BusiManagerTree();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			} else {
				obj.setParent_id("");
				obj.setId(id);
				busiManagerTreeService.deleteBusiManagerTreeById(obj);
				json.put("result", 2); // 1不能删除
				json.put("parent_id", parent_id);
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		}
		return null;
	}

	/********************* (虚拟机) **********************/
	/**
	 * 
	 * @Title: addVm
	 * @Description: 添加虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String addVm() {
		if (vmForm == null) {
			vmForm = new ShowVmForm();
		}
		VMHostObj obj = new VMHostObj();
		if (vmForm.getEQ_ID() != null && !vmForm.getEQ_ID().equals("")) {
			obj.setEQ_ID(vmForm.getEQ_ID());
		}
		if (vmForm.getQueryName() != null && !vmForm.getQueryName().equals("")) {
			obj.setVH_NAME(vmForm.getQueryName());
		}
		if (vmForm.getQueryVHIP() != null && !vmForm.getQueryVHIP().equals("")) {
			obj.setVH_IP(vmForm.getQueryVHIP());
		}
		if (vmForm.getQueryType() != null && !vmForm.getQueryType().equals("")) {
			String type = vmForm.getQueryType();
			if (!"0".equals(type)) {
				obj.setVH_TYPE(type);
			} else {
				obj.setVH_TYPE("");
			}
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		BusiManagerTree obj1 = new BusiManagerTree();
		obj1.setType(3);
		List<BusiManagerTree> treeVmList = busiManagerTreeService.queryForTree(obj1);
		List<VMHostObj> vmList = new ArrayList<VMHostObj>();
		if (treeVmList.size() == 0) {
			vmList = vmHostService.queryVMListForBusi(obj);
		} else {
			vmList = busiManagerTreeService.queryForVmList(obj);
		}
		vmForm.setResultList(vmList);
		return "addVm";
	}

	/**
	 * 
	 * @Title: saveVm
	 * @Description: 保存虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveVm() {
		HttpServletResponse response = Struts2Utils.getResponse();
		JSONObject json = new JSONObject();
		String str[] = new String[] {};
		String str1[] = new String[] {};
		try {
			// PrintWriter out = response.getWriter();
			// 查询虚拟机信息
			VMHostObj vmObj = new VMHostObj();
			if (entity_id != null && !"".equals(entity_id)) { // entity_id传过来的是拼接的参数
				str = entity_id.split(",");
				for (String s : str) {
					if (s != null && !"".equals(s)) {
						str1 = s.split("_");
						entity_id = str1[0];
						connect_id = str1[1];
						vmObj.setVH_UUID(entity_id);
						vmObj.setConnectId(connect_id);
						List<VMHostObj> list = vmHostService.queryForListByObj(vmObj);
						// 插入到业务树表中
						BusiManagerTree obj = new BusiManagerTree();
						obj.setType(3);// 定义类型:3表示虚拟机
						if (list.size() == 1) {
							obj.setName(list.get(0).getVH_NAME());
							obj.setDesc(list.get(0).getVH_DESC());
							obj.setParent_id(parent_id);
							obj.setConnect_id(connect_id);
							obj.setEntity_id(entity_id);
							obj.setId(RandomUUID.getUuid());// 定义id
							busiManagerTreeService.insertBusiManagerTree(obj);
							json.put("result", 2);
							json.put("parent_id", parent_id);
							// out.println(json);
							PrintWriterOut.printWirter(response, json);
							// out.close();
						} else {
							json.put("result", 1); // 保存失败，没有找到对应的虚拟机信息
							json.put("parent_id", parent_id);
							// out.println(json);
							PrintWriterOut.printWirter(response, json);
							// out.close();
							return null;
						}
					}
				}
			} else {
				json.put("result", 3); // 保存失败，没有找到对应的虚拟机信息
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delVm
	 * @Description: 删除虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String delVm() {
		BusiManagerTree obj = new BusiManagerTree();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			busiManagerTreeService.deleteBusiManagerTreeById(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			json.put("result", 2);
			json.put("parent_id", parent_id);
			// out.println(json);
			PrintWriterOut.printWirter(response, json);
			// out.close();
			return null;
		}
		return null;
	}

	/************** 承载业务 *******************/

	public String addBusiness() {
		if (theForm == null) {
			theForm = new BusiManagerTree();
		}
		theForm.setParent_id(parent_id);
		flag = "add";
		return "addBusiness";
	}

	/**
	 * 
	 * @Title: modifybusiness
	 * @Description: 更新承载业务
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String modBusiness() {
		BusiManagerTree obj = new BusiManagerTree();
		// TbSysUserinfoObj tObj = new TbSysUserinfoObj();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			if (list.size() > 0) {
				theForm = list.get(0);
			}
			// tObj.setID(Integer.valueOf(theForm.getUser_id()));
			/*
			 * List<TbSysUserinfoObj>
			 * userList=userInfoService.queryLikeForListByObj(tObj);
			 * if(userList.size()==1){
			 * theForm.setUserName(userList.get(0).getNAME()); }
			 */
			flag = "update";
		}
		return "modifybusiness";
	}

	/**
	 * 
	 * @Title: saveBusiness
	 * @Description: 保存承载业务
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveBusiness() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			BusiManagerTree obj = new BusiManagerTree();
			BeanUtils.copyProperties(obj, theForm);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				busiManagerTreeService.updateBusiManagerTreeByObj(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();

				return null;
			} else {
				// 判断是否存在重名
				BusiManagerTree objTemp = new BusiManagerTree();
				objTemp.setType(2);
				objTemp.setName(obj.getName());
				List<BusiManagerTree> objs = busiManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					PrintWriterOut.printWirter(response, json);
					// out.close();

					return null;
				}
				// 插入
				obj.setType(4);// 定义类型:4表示承载业务
				obj.setId(RandomUUID.getUuid());// 定义id
				busiManagerTreeService.insertBusiManagerTree(obj);
				json.put("result", 2);
				json.put("parent_id", obj.getParent_id());
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: delSysApp
	 * @Description: 删除基准应用
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String delBusiness() {
		BusiManagerTree obj = new BusiManagerTree();
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<BusiManagerTree> list = busiManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				obj.setParent_id("");
				obj.setId(id);
				busiManagerTreeService.deleteBusiManagerTreeById(obj);
				json.put("result", 2); // 1不能删除
				json.put("parent_id", parent_id);
				// out.println(json);
				PrintWriterOut.printWirter(response, json);
				// out.close();
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: vmTopN
	 * @Description: 跳转到topN
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String vmTopN() {
		return "vmTopN";
	}

	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机TopN报表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String queryVmTopN() {
		FusionCharts fusionCharts = new FusionCharts();
		Map<String, Object> map = new HashMap<String, Object>();
		// 实例参数
		if (top_num != null && !"".equals(top_num)) {
			map.put("top_num", Integer.valueOf(top_num));
		} else {
			top_num = String.valueOf(6);// 没有N的时候默认6
			map.put("top_num", Integer.valueOf(top_num));
		}
		if (type != null && !"".equals(type)) {
			map.put("type", type);// 复用一下树中的type,但实际表示的是top所展示的种类如Cpu等
		} else {
			type = "cpu";
			map.put("type", type);
		}
		map.put("parent_id", id);
		try {
			fusionCharts = busiManagerTreeService.queryVmTopN(map);
			if (fusionCharts == null) {
				fusionCharts = initQueryErrorCharts();
			}
		} catch (Exception e) {
			LOG.error("查询虚拟机TopN失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryVmTopNList
	 * @Description: 查询虚拟机TopN列表报表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String queryVmTopNList() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 实例参数
		if (top_num != null && !"".equals(top_num)) {
			map.put("top_num", Integer.valueOf(top_num));
		} else {
			top_num = String.valueOf(6);// 没有N的时候默认6
			map.put("top_num", Integer.valueOf(top_num));
		}
		if (type != null && !"".equals(type)) {
			map.put("type", type);// 复用一下树中的type,但实际表示的是top所展示的种类如Cpu等
		} else {
			type = "cpu";
			map.put("type", type);
		}
		map.put("parent_id", id);
		try {
			resultList = busiManagerTreeService.queryTopList(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultListJson = JacksonUtil.toJson(resultList);
		// 传输json数据到界面
		printJson(resultListJson);
		return null;
	}

	/*********************** 业务系统下 ******************************/
	/**
	 * 
	 * @Title: vmTopN
	 * @Description: 跳转到topN
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String sysVmTopN() {
		return "sysVmTopN";
	}

	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询业务系统级别的虚拟机TopN报表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String querySysVmTopN() {
		FusionCharts fusionCharts = new FusionCharts();
		Map<String, Object> map = new HashMap<String, Object>();
		// 实例参数
		if (top_num != null && !"".equals(top_num)) {
			map.put("top_num", Integer.valueOf(top_num));
		} else {
			top_num = String.valueOf(6);// 没有N的时候默认6
			map.put("top_num", Integer.valueOf(top_num));
		}
		if (type != null && !"".equals(type)) {
			map.put("type", type);// 复用一下树中的type,但实际表示的是top所展示的种类如Cpu等
		} else {
			type = "cpu";
			map.put("type", type);
		}
		map.put("parent_id", id);
		try {
			fusionCharts = busiManagerTreeService.querySysVmTopN(map);
			if (fusionCharts == null) {
				fusionCharts = initQueryErrorCharts();
			}
		} catch (Exception e) {
			LOG.error("查询虚拟机TopN失败！" + e.getMessage() + e, e);
			fusionCharts = initQueryErrorCharts();
		}
		fusionChartsString = JacksonUtil.toJson(fusionCharts);
		// 传输json数据到界面
		printJson(fusionChartsString);
		return null;
	}

	/**
	 * 
	 * @Title: queryVmTopNList
	 * @Description: 查询虚拟机TopN列表报表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:36:37
	 */
	public String querySysVmTopNList() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 实例参数
		if (top_num != null && !"".equals(top_num)) {
			map.put("top_num", Integer.valueOf(top_num));
		} else {
			top_num = String.valueOf(6);// 没有N的时候默认6
			map.put("top_num", Integer.valueOf(top_num));
		}
		if (type != null && !"".equals(type)) {
			map.put("type", type);// 复用一下树中的type,但实际表示的是top所展示的种类如Cpu等
		} else {
			type = "cpu";
			map.put("type", type);
		}
		map.put("parent_id", id);
		try {
			resultList = busiManagerTreeService.querySysTopList(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultListJson = JacksonUtil.toJson(resultList);
		// 传输json数据到界面
		printJson(resultListJson);
		return null;
	}

	/**
	 * 
	 * @Title: initQueryErrorCharts
	 * @Description: 当查询失败时，显示报表
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午9:45:04
	 */
	private FusionCharts initQueryErrorCharts() {
		FusionCharts fusionCharts = new FusionCharts();
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: printJson
	 * @Description: 传输Json数据到界面
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-14 上午10:41:00
	 */
	private synchronized void printJson(String... params) {
		// PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			// out = response.getWriter();

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// out.println(params[i]);
					PrintWriterOut.printWirter(response, params[i]);
				}
			}
			// out.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: 获取用户信息列表，用于选择项目负责人
	 * @Copyright:Copyright (c) Jul 11, 2013
	 * @Company: si-tech
	 * @author: siweichao
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
	 * <p>附件下载</p>
	 * @throws UnsupportedEncodingException 
	 *
	 */
	public String exportExcel() throws UnsupportedEncodingException{	
		return "success";
	}	
	
	public InputStream getDownloadFile() throws FileNotFoundException{
		List<Map<String,Object>>  list = busiManagerTreeService.getVmhostListByBusiId(id,type);
		ByteArrayOutputStream out = new ByteArrayOutputStream();  
		busiManagerTreeService.getOutPutStreamByVmList(list,out);
		BusiManagerTree bm = new BusiManagerTree();
		bm.setId(id);
		List<BusiManagerTree> treelist = busiManagerTreeService.queryForTree(bm);
		if(treelist.size()!=0){			
			fileName=treelist.get(0).getName()+"-虚拟机列表.xls";
		}else{
			fileName="虚拟机列表.xls";
		}
		try {
			fileName = new String(fileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/*****************************物理机 操作 begin*****************************************/
	
	/**
	 * 
	 * @Title: addHostToBusi
	 * @Description: 添加物理机
	 * @param
	 * @return String
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014年8月3日
	 */
	public String addHostToBusi(){
		
		if(hostForm==null){
			hostForm = new HostManageForm();
		}
		TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
		if (hostForm.getHostUuids() != null && !hostForm.getHostUuids().equals("")) {
			host.setHostUuids(hostForm.getHostUuids());
		}
		if(hostForm.getEq_id() != null && !hostForm.getEq_id().equals("")){
			host.setEq_id(hostForm.getEq_id());
		}
		if(hostForm.getSn() != null && !hostForm.getSn().equals("")){
			host.setSn(hostForm.getSn());
		}
		if (hostForm.getEq_name() != null && !hostForm.getEq_name().equals("")) {
			host.setEq_name(hostForm.getEq_name().trim());
		}
		if (hostForm.getEq_ip() != null && !hostForm.getEq_ip().equals("")) {
			host.setEq_ip(hostForm.getEq_ip().trim());
		}
		if (hostForm.getEq_type() != null && !hostForm.getEq_type().equals("-1")) {
			host.setEq_type(hostForm.getEq_type());
		}
		if (hostForm.getAllocated() != null && !hostForm.getAllocated().equals("")) {
			host.setAllocated(hostForm.getAllocated());
		}
		if (hostForm.getSTATUS() != null && !"".equals(hostForm.getSTATUS())) {
			host.setSTATUS(hostForm.getSTATUS());
		}
		host.setParent_id(parent_id);
		host.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));
		hostForm.setResultList(hostInfoService.queryHostListForBusiManager(host));
		
		return "addHostToBusi";
	}
	
	/**
	 * @Title: saveHostToBusi
	 * @Description: 在业务视图中，添加物理主机（SC）
	 * @param 
	 * @return String 返回类型
	 * @throws
	 * @author JamTau
	 * @date 2014年8月18日
	 */
	public String saveHostToBusi() {
		HttpServletResponse response = Struts2Utils.getResponse();
		JSONObject json = new JSONObject();
		String eqIds[] = new String[] {};
		try {
			// 查询虚拟机信息
			TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
			if (eq_id != null && !"".equals(eq_id)) { // eqId传过来的是拼接的参数
				eqIds = eq_id.split(",");
				for (String s : eqIds) {
					if (s != null && !"".equals(s)) {
						host.setEq_id(s);
						host = hostInfoService.queryByObj(host);		
						// 插入到业务树表中
						BusiManagerTree obj = new BusiManagerTree();
						obj.setType(5);// 定义类型:5标识物理机
						if (host != null) {
							//业务视图-->节点名称：如果EQ_IP有值，只用EQ_IP；否则使用序列号
						    if(host.getEq_ip() == null || "".equals(host.getEq_ip())){		
						    	obj.setName(host.getSn());
						    }else{
						    	obj.setName(host.getEq_ip());
						    }
							obj.setDesc(host.getEq_hostname());
							obj.setParent_id(parent_id);
							obj.setConnect_id(host.getEq_id());
							obj.setEntity_id(host.getEq_ip());
							id = RandomUUID.getUuid();
							obj.setId(id);// 定义id
							busiManagerTreeService.insertBusiManagerTree(obj);
							json.put("result", 2);
							json.put("parent_id", parent_id);
							PrintWriterOut.printWirter(response, json);
						} else {
							json.put("result", 1); // 保存失败，没有找到对应的虚拟机信息
							json.put("parent_id", parent_id);
							PrintWriterOut.printWirter(response, json);
							return null;
						}
					}
				}
			} else {
				json.put("result", 3); // 保存失败，没有找到对应的虚拟机信息
				PrintWriterOut.printWirter(response, json);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: delVm
	 * @Description: 删除物理机
	 * @param
	 * @return String
	 * @throws
	 * @author JamTau
	 * @version 1.0
	 * @createtime 2014年8月3日
	 */
	public String deleteHostToBusi() {
		HttpServletResponse response = Struts2Utils.getResponse();
		BusiManagerTree obj = new BusiManagerTree();
		if (id != null && !"".equals(id)) {
			obj.setId(id);
			busiManagerTreeService.deleteBusiManagerTreeById(obj);
			JSONObject json = new JSONObject();
			json.put("result", 2);
			json.put("parent_id", parent_id);
			PrintWriterOut.printWirter(response, json);
			return null;
		}
		return null;
	}
	
	/*****************************物理机 操作 end*****************************************/
	
	/**
	 *
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 *
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public BusiManagerTreeService getBusiManagerTreeService() {
		return busiManagerTreeService;
	}

	public void setBusiManagerTreeService(BusiManagerTreeService busiManagerTreeService) {
		this.busiManagerTreeService = busiManagerTreeService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusiManagerTree getTheForm() {
		return theForm;
	}

	public void setTheForm(BusiManagerTree theForm) {
		this.theForm = theForm;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public ShowVmForm getVmForm() {
		return vmForm;
	}

	public void setVmForm(ShowVmForm vmForm) {
		this.vmForm = vmForm;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getTop_num() {
		return top_num;
	}

	public void setTop_num(String top_num) {
		this.top_num = top_num;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public HostManageForm getHostForm() {
		return hostForm;
	}

	public void setHostForm(HostManageForm hostForm) {
		this.hostForm = hostForm;
	}

	public String getEq_id() {
		return eq_id;
	}

	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}

}
