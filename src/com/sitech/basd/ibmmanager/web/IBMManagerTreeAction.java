package com.sitech.basd.ibmmanager.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.ibmmanager.domain.IBMManagerTreeObj;
import com.sitech.basd.ibmmanager.service.IBMManagerTreeService;
import com.sitech.basd.ibmmanager.util.IBMConstant;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: IBMManagerTreeAction
 * </p>
 * <p>
 * Description: IBM小型机管理相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2013-11-4 上午11:33:19
 * 
 */
@Controller("ibmManagerTreeAction")
@Scope("prototype")
public class IBMManagerTreeAction extends BaseAction {
	@Autowired
	private IBMManagerTreeService ibmManagerTreeService;
	private IBMManagerTreeObj obj = new IBMManagerTreeObj();
	private String id;
	private String name;
	private String uuid;
	private String type;
	private String vtype;
	private String parent_id;
	private List resultList;

	/**
	 * 
	 * @Title: listIBMManagerTree
	 * @Description: 初始化页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 上午11:35:58
	 */
	public String listIBMManagerTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 异步加载统一树
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-1 下午9:20:40
	 */
	public String asyncForTree() throws HttpClientException {
		List<IBMManagerTreeObj> list = queryTree(request);
		List<IBMManagerTreeObj> resultList = ibmManagerTreeService.initTreelist(list);
		JSONArray json = JSONArray.fromObject(resultList);
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = null;
		// out = response.getWriter();
		// out.print(json.toString());
		// out.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: queryTree
	 * @Description: 查询树节点
	 * @param
	 * @return List<IBMManagerTreeObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:13:44
	 */
	public List<IBMManagerTreeObj> queryTree(HttpServletRequest request) {
		List<IBMManagerTreeObj> list = new ArrayList<IBMManagerTreeObj>();
		IBMManagerTreeObj obj = new IBMManagerTreeObj();
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			obj.setType("0");
		} else {
			obj.setParent_id(id);
		}
		list = ibmManagerTreeService.queryForTree(obj);
		return list;

	}

	/********** IBM小型机 ***************************************************************************************/
	/**
	 * 
	 * @Title: addIBMManager
	 * @Description: 添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午2:32:50
	 */
	public String addIBMManager() {
		return "addIBM";
	}

	/**
	 * 
	 * @Title: saveIBMManager
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 11, 2013 2:49:55 PM
	 */
	public String saveIBMManager() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				ibmManagerTreeService.updateTreeByObj(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				IBMManagerTreeObj objTemp = new IBMManagerTreeObj();
				objTemp.setType("1");
				objTemp.setName(obj.getName());
				List<IBMManagerTreeObj> objs = ibmManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				obj.setId(RandomUUID.getUuid());// 定义id
				obj.setUuid(RandomUUID.getUuid());
				obj.setType("1");
				obj.setParent_id("1");// 定义其父节点为1(根节点固定的)
				obj.setVtype("0");
				ibmManagerTreeService.insertTree(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
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
	 * @Title: updateIBMManager
	 * @Description: 修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:14:01
	 */
	public String updateIBMManager() {
		List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
		if (list != null) {
			if (list.size() > 0) {
				obj = list.get(0);
			}
		}
		return "updateIBM";
	}

	/**
	 * 
	 * @Title: deleteIBMManager
	 * @Description: 删除操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:14:09
	 */
	public String deleteIBMManager() {
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				obj.setId(id);
				obj.setParent_id("");
				ibmManagerTreeService.deleteTreeById(obj);
				json.put("result", 2); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			}
		}
		return null;
	}

	/********** IBM整机 ******************************************************************************/
	/**
	 * 
	 * @Title: addPower
	 * @Description: 添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:21:18
	 */
	public String addPower() {
		return "addPower";
	}

	/**
	 * 
	 * @Title: savePower
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:21:30
	 */
	public String savePower() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				ibmManagerTreeService.updateTreeByObj(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				IBMManagerTreeObj objTemp = new IBMManagerTreeObj();
				objTemp.setType("2");
				objTemp.setName(obj.getName());
				List<IBMManagerTreeObj> objs = ibmManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				obj.setId(RandomUUID.getUuid());// 定义id
				obj.setUuid(RandomUUID.getUuid());
				obj.setType("2");
				obj.setVtype("0");
				ibmManagerTreeService.insertTree(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
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
	 * @Title: updatePower
	 * @Description: 修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:25:24
	 */
	public String updatePower() {
		List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
		if (list != null) {
			if (list.size() > 0) {
				obj = list.get(0);
			}
		}
		return "updPower";
	}

	/**
	 * 
	 * @Title: delPower
	 * @Description: 删除操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午4:26:03
	 */
	public String delPower() {
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				obj.setId(id);
				obj.setParent_id("");
				ibmManagerTreeService.deleteTreeById(obj);
				json.put("result", 2); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			}
		}
		return null;
	}

	/********** 逻辑分区 ******************************************************************************/
	/**
	 * 
	 * @Title: addLogicPartition
	 * @Description: 添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午4:34:56
	 */
	public String addLogicPartition() {
		return "addLogicPartition";
	}

	/**
	 * 
	 * @Title: savePower
	 * @Description: 保存
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午3:21:30
	 */
	public String saveLogicPartition() {
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (obj.getId() != null && !"".equals(obj.getId())) {
				ibmManagerTreeService.updateTreeByObj(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				IBMManagerTreeObj objTemp = new IBMManagerTreeObj();
				objTemp.setType("3");
				objTemp.setName(obj.getName());
				List<IBMManagerTreeObj> objs = ibmManagerTreeService.queryForTree(objTemp);
				if (objs != null && objs.size() >= 1) {
					json.put("result", 1);
					// out.println(json);
					// out.close();
					PrintWriterOut.printWirter(response, json);
					return null;
				}
				obj.setId(RandomUUID.getUuid());// 定义id
				obj.setUuid(RandomUUID.getUuid());
				obj.setType("3");
				obj.setVtype("0");
				ibmManagerTreeService.insertTree(obj);
				json.put("result", 2);
				// out.println(json);
				// out.close();
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
	 * @Title: updLogicPartition
	 * @Description: 修改页面
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午4:46:27
	 */
	public String updateLogicPartition() {
		List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
		if (list != null) {
			if (list.size() > 0) {
				obj = list.get(0);
			}
		}
		return "updateLogicPartition";
	}

	/**
	 * 
	 * @Title: delLogicPartition
	 * @Description: 删除操作
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:05:24
	 */
	public String delLogicPartition() {
		HttpServletResponse response = Struts2Utils.getResponse();
		if (id != null && !"".equals(id)) {
			obj.setParent_id(id);
			List<IBMManagerTreeObj> list = ibmManagerTreeService.queryForTree(obj);
			JSONObject json = new JSONObject();
			// PrintWriter out = response.getWriter();
			if (list.size() > 0) {// 存在子节点不能删除
				json.put("result", 1); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			} else {
				obj.setId(id);
				obj.setParent_id("");
				ibmManagerTreeService.deleteTreeById(obj);
				json.put("result", 2); // 1不能删除
				// out.println(json);
				// out.close();
				PrintWriterOut.printWirter(response, json);
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-5 上午9:10:18
	 */
	public String getExpandNodes() {
		try {
			name = URLDecoder.decode(name, "UTF-8");
			IBMManagerTreeObj tree = new IBMManagerTreeObj();
			if (name != null && !"".equals(name)) {
				tree.setName(name);
			}
			if (type != null && !"".equals(type)) {
				tree.setType(type);
			}
			if (IBMConstant.HOST.equals(type)) {
				resultList = ibmManagerTreeService.queryExpandNodesForPower(tree);
			} else if (IBMConstant.VM.equals(type)) {
				resultList = ibmManagerTreeService.queryExpandNodesForLP(tree);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "expandNode";
	}

	/**
	 * 
	 * @Title: queryTreeNodeByName
	 * @Description: 通过名字查询左侧树节点
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-11-4 下午5:59:22
	 */
	public String queryTreeNodeByName() {
		try {
			if (name != null && !"".equals(name)) {
				IBMManagerTreeObj obj = new IBMManagerTreeObj();
				name = URLDecoder.decode(name, "UTF-8");
				obj.setName(name);
				obj.setType(type);
				if (IBMConstant.HOST.equals(type)) {
					resultList = ibmManagerTreeService.queryForHostTreeByName(obj);
				} else if (IBMConstant.VM.equals(type)) {
					resultList = ibmManagerTreeService.queryForVMTreeByName(obj);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "querynode";
	}

	// ----------------------get()和set()-------------------------
	public List getResultList() {
		return resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public IBMManagerTreeObj getObj() {
		return obj;
	}

	public void setObj(IBMManagerTreeObj obj) {
		this.obj = obj;
	}

}
