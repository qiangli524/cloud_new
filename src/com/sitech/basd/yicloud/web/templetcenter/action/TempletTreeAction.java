package com.sitech.basd.yicloud.web.templetcenter.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.image.ImageService;
import com.sitech.basd.sxcloud.cloud.web.image.form.ImageForm;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.entitytree.TreeObj;
import com.sitech.basd.yicloud.domain.script.ScriptConObj;
import com.sitech.basd.yicloud.domain.script.ScriptObj;
import com.sitech.basd.yicloud.domain.templettree.TempletTreeObj;
import com.sitech.basd.yicloud.service.script.ScriptService;
import com.sitech.basd.yicloud.service.templettree.TempletTreeService;
import com.sitech.basd.yicloud.web.templetcenter.form.ScriptForm;
import com.sitech.basd.yicloud.web.templetcenter.form.TempletTreeForm;
import com.sitech.utils.servlet.PrintWriterOut;

public class TempletTreeAction extends CRUDBaseAction {
	private TempletTreeService templetTreeService;
	private TempletTreeForm theForm;
	private ScriptService scriptService;
	private ScriptForm theScriptForm;
	private String templet_id;
	private String templet_name;
	private ImageForm imageForm;
	private ImageService imageService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public ImageForm getImageForm() {
		return imageForm;
	}

	public void setImageForm(ImageForm imageForm) {
		this.imageForm = imageForm;
	}

	public String getTemplet_name() {
		return templet_name;
	}

	public void setTemplet_name(String templet_name) {
		this.templet_name = templet_name;
	}

	public String getTemplet_id() {
		return templet_id;
	}

	public void setTemplet_id(String templet_id) {
		this.templet_id = templet_id;
	}

	public ScriptForm getTheScriptForm() {
		return theScriptForm;
	}

	public void setTheScriptForm(ScriptForm theScriptForm) {
		this.theScriptForm = theScriptForm;
	}

	public ScriptService getScriptService() {
		return scriptService;
	}

	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	public TempletTreeForm getTheForm() {
		return theForm;
	}

	public void setTheForm(TempletTreeForm theForm) {
		this.theForm = theForm;
	}

	public TempletTreeService getTempletTreeService() {
		return templetTreeService;
	}

	public void setTempletTreeService(TempletTreeService templetTreeService) {
		this.templetTreeService = templetTreeService;
	}

	/**
	 * 
	 * @Title: listTempletTree
	 * @Description: 获取模板树
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public String listTempletTree() {
		return "list";
	}

	/**
	 * 
	 * @Title: asyncForTree
	 * @Description: 采用异步的方式生成树
	 * @param
	 * @return ActionForward
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public String asyncForTree() throws Exception {
		TempletTreeObj treeObj = new TempletTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String str = request.getParameter("id");
		if (str == null || "".equals(str)) {
			treeObj.setParent_id(-1);
		} else {
			treeObj.setParent_id(Integer.valueOf(str));
		}
		List<TempletTreeObj> resultList = templetTreeService.queryForTree(treeObj);
		List<TreeObj> list = new ArrayList<TreeObj>();
		if (list != null) {
			TempletTreeObj tempObj = new TempletTreeObj();
			for (TempletTreeObj obj : resultList) {
				TreeObj tObj = new TreeObj();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setType(String.valueOf(obj.getType()));
				tObj.setEntityId(obj.getTemplet_id());
				// 判断是不是父节点
				tempObj.setParent_id(obj.getId());
				List<TempletTreeObj> lst = templetTreeService.queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);

				}
				// 设置图标
				if (obj.getType() == 1) {// 目录
					tObj.setIcon("sxcloud/images/folder.gif");
				}
				if (obj.getType() == 2) { // 模板
					tObj.setIcon("sxcloud/images/page.gif");
				}
				list.add(tObj);
			}
		}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray json = JSONArray.fromObject(list);
		// PrintWriter p = response.getWriter();
		// p.print(json.toString());
		// p.close();
		PrintWriterOut.printWirter(response, json.toString());
		return null;
	}

	/**
	 * 
	 * @Title: creatMenu
	 * @Description: 创建子菜单
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public String creatMenu() {
		if (theForm == null) {
			theForm = new TempletTreeForm();
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		int parent_id = Integer.parseInt(request.getParameter("id"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		request.setAttribute("parent_id", parent_id);
		request.setAttribute("flag", flag);
		return "creatMenu";
	}

	/**
	 * 
	 * @Title: saveMenu
	 * @Description: 保存子菜单
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws IOException
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public String saveMenu() throws IOException {
		if (theForm == null) {
			theForm = new TempletTreeForm();
		}
		TempletTreeObj obj = new TempletTreeObj();
		HttpServletRequest request = Struts2Utils.getRequest();
		String flag = request.getParameter("flag");
		String name = request.getParameter("name");
		if (flag.equals("0")) {
			String parent_id = request.getParameter("parent_id");
			obj.setParent_id(Integer.parseInt(parent_id));
			obj.setName(name);
			obj.setType(1);
		} else {
			obj.setParent_id(-1);
			obj.setName(name);
			obj.setType(1);
		}
		int ret = templetTreeService.creatMenu(obj);
		String json = null;
		if (ret != -1) {
			json = "{\"result\":\"" + 1 + "\"}";
		} else {
			json = "{\"result\":\"" + -1 + "\"}";
		}
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除子菜单
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws IOException
	 * @createtime Oct 9, 2012 11:42:00 AM
	 */
	public String deleteMenu() throws IOException {
		int id = Integer.parseInt(Struts2Utils.getParameter("id"));
		TempletTreeObj obj = new TempletTreeObj();
		obj.setParent_id(id);
		List<TempletTreeObj> lst = templetTreeService.queryForTree(obj);
		String json = null;
		if (lst.size() == 0) {
			TempletTreeObj temObj = new TempletTreeObj();
			temObj.setId(id);
			int ret = templetTreeService.deleteMenu(temObj);
			if (ret != -1) {
				json = "{\"result\":\"" + 1 + "\"}";
			}
		} else {
			json = "{\"result\":\"" + -1 + "\",\"reason\":\"" + "目录不为空不能删除" + "\"}";
		}
		Struts2Utils.getResponse().setContentType("application/json;charset=utf-8");
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: moveintoMenu
	 * @Description: 移动到指定目录
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws IOException
	 * @createtime Oct 10, 2012 11:42:00 AM
	 */
	public String moveintoMenu() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");
		String targetId = request.getParameter("targetId");
		TempletTreeObj obj = new TempletTreeObj();
		obj.setId(Integer.parseInt(id));
		obj.setParent_id(Integer.parseInt(targetId));
		int ret = templetTreeService.updateObj(obj);
		String json = null;
		if (ret != -1) {
			json = "{\"result\":\"" + 1 + "\"}";
		} else {
			json = "{\"result\":\"" + -1 + "\"}";
		}
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	/**
	 * 
	 * @Title: listScript
	 * @Description: 展示脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String listScript() {
		if (theScriptForm == null) {
			theScriptForm = new ScriptForm();
		}
		ScriptObj obj = new ScriptObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		obj.setName(theScriptForm.getName());
		List lst = scriptService.listScript(obj);
		theScriptForm.setResultList(lst);
		return "listScript";
	}

	/**
	 * 
	 * @Title: addScript
	 * @Description: 增加脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String addScript() {
		String flag = Struts2Utils.getRequest().getParameter("flag");
		theScriptForm.setFlag(flag);
		return "addScript";
	}

	/**
	 * 
	 * @Title: updateScript
	 * @Description: 修改脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String updateScript() {
		if (theScriptForm == null) {
			theScriptForm = new ScriptForm();
		}
		int id = theScriptForm.getId();
		ScriptObj obj = new ScriptObj();
		obj.setId(id);
		List lst = scriptService.listScript(obj);
		ScriptObj reObj = null;
		if (lst != null) {
			reObj = (ScriptObj) lst.get(0);
		}
		String flag = Struts2Utils.getRequest().getParameter("flag");
		theScriptForm.setFlag(flag);
		theScriptForm.setContent(reObj.getContent());
		theScriptForm.setName(reObj.getName());
		theScriptForm.setDes(reObj.getDes());
		theScriptForm.setId(id);
		return "updateScript";
	}

	/**
	 * 
	 * @Title: saveScript
	 * @Description: 保存脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String saveScript() {
		if (theScriptForm == null) {
			theScriptForm = new ScriptForm();
		}
		ScriptObj obj = new ScriptObj();
		String flag = theScriptForm.getFlag();
		obj.setName(theScriptForm.getName());
		obj.setContent(theScriptForm.getContent());
		obj.setDes(theScriptForm.getDes());
		if (theScriptForm.getId() != 0) {
			obj.setId(theScriptForm.getId());
		}
		int ret = 0;
		if (flag.equals("0")) {
			ret = scriptService.insertByObj(obj);
		} else {
			ret = scriptService.updateByObj(obj);
		}
		return "saveScript";
	}

	/**
	 * 
	 * @Title: deleteScript
	 * @Description: 删除脚本
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String deleteScript() {
		if (theScriptForm == null) {
			theScriptForm = new ScriptForm();
		}
		ScriptObj obj = new ScriptObj();
		obj.setId(theScriptForm.getId());
		int ret = scriptService.deleteByObj(obj);
		ScriptConObj conObj = new ScriptConObj();
		conObj.setScriptId(String.valueOf(theScriptForm.getId()));
		int delRet = scriptService.deleteScriptRelation(conObj);
		return "deleteScript";
	}

	/**
	 * 
	 * @Title: relationRequest
	 * @Description: 建立模板与脚本的关系
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @throws IOException
	 * @throws
	 * @createtime Oct 11, 2012 11:42:00 AM
	 */
	public String relationRequest() throws IOException {
		HttpServletRequest request = Struts2Utils.getRequest();
		String allIds = request.getParameter("allIds");
		templet_id = request.getParameter("templet_id");
		templet_name = URLDecoder.decode(request.getParameter("templet_name"), "UTF-8");
		String chks = request.getParameter("chks");
		ScriptConObj delObj = new ScriptConObj();
		delObj.setTempletId(templet_id);
		String ids[] = allIds.split(":");
		for (int i = 0; i < ids.length; i++) {
			switch (i) {
			case 0:
				delObj.setId1(ids[i]);
				break;
			case 1:
				delObj.setId2(ids[i]);
				break;
			case 2:
				delObj.setId3(ids[i]);
				break;
			case 3:
				delObj.setId4(ids[i]);
				break;
			case 4:
				delObj.setId5(ids[i]);
				break;
			case 5:
				delObj.setId6(ids[i]);
				break;
			case 6:
				delObj.setId7(ids[i]);
				break;
			case 7:
				delObj.setId8(ids[i]);
				break;
			case 8:
				delObj.setId9(ids[i]);
				break;
			case 9:
				delObj.setId10(ids[i]);
				break;
			}
		}
		// 删除当前页面中脚本的关联
		int delRet = scriptService.deleteRelation(delObj);
		// 建立关联
		int ret = 0;
		if (chks != null && !chks.equals("")) {
			String arr[] = chks.split(":");
			for (int i = 0; i < arr.length; i++) {
				ScriptConObj conObj = new ScriptConObj();
				conObj.setTempletId(templet_id);
				conObj.setType("0");
				conObj.setScriptId(arr[i]);
				ret = scriptService.addRelation(conObj);
			}
		}
		String json = null;
		if (ret != -1) {
			json = "{\"result\":\"" + 1 + "\"}";
		} else {
			json = "{\"result\":\"" + -1 + "\"}";
		}
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		p.print(json);
		p.close();
		return null;
	}

	public String flashMenu() {
		HttpServletRequest request = Struts2Utils.getRequest();
		templet_id = request.getParameter("templet_id");
		try {
			templet_name = URLDecoder.decode(request.getParameter("templet_name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "relationRequest";
	}

	/**
	 * 
	 * @Title: listRelationScript
	 * @Description: 分类展示脚本和模板是否关联
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 12, 2012 11:42:00 AM
	 */
	public String listRelationScript() {
		// 查询所有脚本
		if (theScriptForm == null) {
			theScriptForm = new ScriptForm();
		}
		ScriptObj obj = new ScriptObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List lst = scriptService.listScript(obj);
		theScriptForm.setResultList(lst);
		// 查询和模板关联的脚本id
		templet_id = Struts2Utils.getRequest().getParameter("templet_id");
		try {
			templet_name = URLDecoder.decode(
					Struts2Utils.getRequest().getParameter("templet_name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ScriptConObj conObj = new ScriptConObj();
		conObj.setTempletId(templet_id);
		conObj.setType("0");
		List conLst = scriptService.queryScriptListByTempletObj(conObj);
		String chks = "";
		for (Object o : conLst) {
			ScriptConObj scrObj = (ScriptConObj) o;
			chks += scrObj.getScriptId() + ":";
		}
		theScriptForm.setChks(chks);
		Struts2Utils.getRequest().setAttribute("templet_id", templet_id);
		Struts2Utils.getRequest().setAttribute("templet_name", templet_name);
		return "listRelationScript";
	}

	/**
	 * 
	 * @Title: creatMainMenu
	 * @Description: 创建根目录
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 16, 2012 11:42:00 AM
	 */
	public String creatMainMenu() {
		int flag = Integer.parseInt(Struts2Utils.getRequest().getParameter("flag"));
		Struts2Utils.getRequest().setAttribute("flag", flag);
		return "creatMenu";
	}

	/**
	 * 
	 * @Title: imageInfo
	 * @Description: 查看模板信息
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Oct 16, 2012 11:42:00 AM
	 */
	public String imageInfo() {
		String entityId = (String) Struts2Utils.getParameter("id");// 得到实体Id，即镜像Id
		VMHostObj obj = new VMHostObj();
		if (entityId != "" && entityId != null) {
			// obj.setVH_UNITID(entityId);
			obj.setID(Integer.parseInt(entityId));
		}
		List lst = imageService.queryVMHostByVMObj(obj);
		if (imageForm == null) {
			imageForm = new ImageForm();
		}
		VMHostObj reObj = null;
		if (lst.size() > 0) {
			reObj = (VMHostObj) lst.get(0);
		}
		imageForm.setIM_NAME(reObj.getVH_NAME());
		imageForm.setIM_DESC(reObj.getVH_DESC());
		imageForm.setIM_STATE(reObj.getVH_STAT());
		imageForm.setIM_CPU(reObj.getVH_CPU());
		if (reObj.getVH_STORAGE() != null && !"".equals(reObj.getVH_STORAGE())) {
			int storage = Integer.parseInt(reObj.getVH_STORAGE()) / 1024 / 1024;
			imageForm.setIM_STORAGE(String.valueOf(storage));
		}
		// imageForm.setIM_STORAGE(reObj.getVH_STORAGE());
		imageForm.setIM_MEM(reObj.getVH_MEM());
		if (reObj.getVH_MEM() != null && !reObj.getVH_MEM().equals("")) {
			int memory = Integer.parseInt(reObj.getVH_MEM());
			imageForm.setIM_MEM(String.valueOf(memory));
		}
		imageForm.setIM_SYSTEM(reObj.getVH_SYSTEM());

		// 获取模板的路径
		String path = "";
		TempletTreeObj templetTreeObj = new TempletTreeObj();
		templetTreeObj.setName(reObj.getVH_NAME());
		TempletTreeObj reTempletTreeObj = templetTreeService.queryForTree(templetTreeObj).get(0);
		path += reTempletTreeObj.getName();
		for (; reTempletTreeObj.getParent_id() != -1;) {
			TempletTreeObj queryObj = new TempletTreeObj();
			queryObj.setId(reTempletTreeObj.getParent_id());
			reTempletTreeObj = templetTreeService.queryForTree(queryObj).get(0);
			path = reTempletTreeObj.getName() + "/" + path;
		}
		imageForm.setTempletPath(path);

		ScriptConObj scriptConObj = new ScriptConObj();
		scriptConObj.setTempletId(entityId);
		scriptConObj.setType("0");
		List resultList = new ArrayList<ScriptObj>();
		List relationList = scriptService.queryScriptListByTempletObj(scriptConObj);
		for (Object o : relationList) {
			ScriptConObj scrCon = (ScriptConObj) o;
			ScriptObj scriptObj = new ScriptObj();
			scriptObj.setId(Integer.parseInt(scrCon.getScriptId()));
			List scriptList = scriptService.listScript(scriptObj);
			scriptObj = (ScriptObj) scriptList.get(0);
			resultList.add(scriptObj);
		}
		imageForm.setResultList(resultList);

		return "imageInfo";
	}
}
