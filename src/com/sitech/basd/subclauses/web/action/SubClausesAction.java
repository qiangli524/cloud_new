package com.sitech.basd.subclauses.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.subclauses.domain.ResourceListObj;
import com.sitech.basd.subclauses.domain.SubClausesObj;
import com.sitech.basd.subclauses.service.SubClausesService;
import com.sitech.basd.subclauses.web.form.SubClausesForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.servlet.PrintWriterOut;


@Controller("subClausesAction")
@Scope("prototype")
public class SubClausesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SubClausesService subClausesService;

	private SubClausesForm theForm;
	
	private SubClausesObj obj;
	
	private List<ResourceListObj> ResourceList;
	
	
	public SubClausesObj getObj() {
		return obj;
	}


	public void setObj(SubClausesObj obj) {
		this.obj = obj;
	}


	public List<ResourceListObj> getResourceList() {
		return ResourceList;
	}


	public void setResourceList(List<ResourceListObj> resourceList) {
		ResourceList = resourceList;
	}


	public SubClausesForm getTheForm() {
		return theForm;
	}


	public void setTheForm(SubClausesForm theForm) {
		this.theForm = theForm;
	}


	/**
	 * @Title: subClausesList
	 * @Description: 查询所有
	 * @param
	 * @return String
	 */
	public String subClausesList() {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		
		SubClausesObj obj = new SubClausesObj();
		if("-1".equals(theForm.getState())){
			obj.setState(null);
		}else{
			obj.setState(theForm.getState());
		}
		obj.setId(theForm.getId());
		obj.setName(theForm.getName());
		obj.setIndate(theForm.getIndate());
		obj.setRole(theForm.getRole());
		obj.setResource_id(theForm.getResource_id());
		obj.setResource_name(theForm.getResource_name());
		obj.setPublish_state(theForm.getPublish_state());
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<?> resultList = subClausesService.queryAllSubClauses(obj);
		theForm.setResultList(resultList);
		return "subClauseslist";
	}
	
	/**
	 * @Title: subClausesListForPublish
	 * @Description: 发布条目LIST页面
	 * @param
	 * @return String
	 */
	public String subClausesListForPublish() {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		
		SubClausesObj obj = new SubClausesObj();
		if("-1".equals(theForm.getState())){
			obj.setState(null);
		}else{
			obj.setState(theForm.getState());
		}
		obj.setId(theForm.getId());
		obj.setName(theForm.getName());
		obj.setIndate(theForm.getIndate());
		obj.setRole(theForm.getRole());
		obj.setResource_id(theForm.getResource_id());
		obj.setResource_name(theForm.getResource_name());
		obj.setPublish_state(theForm.getPublish_state());
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<?> resultList = subClausesService.queryAllSubClauses(obj);
		theForm.setResultList(resultList);
		return "subClauseslistForPublish";
	}
	
	/**
	 * @Title: subClausesListForView
	 * @Description: 查看条目LIST页面
	 * @param
	 * @return String
	 */
	public String subClausesListForView() {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		
		SubClausesObj obj = new SubClausesObj();
		if("-1".equals(theForm.getState())){
			obj.setState(null);
		}else{
			obj.setState(theForm.getState());
		}
		obj.setId(theForm.getId());
		obj.setName(theForm.getName());
		obj.setIndate(theForm.getIndate());
		obj.setRole(theForm.getRole());
		obj.setResource_id(theForm.getResource_id());
		obj.setResource_name(theForm.getResource_name());
		obj.setPublish_state("1");//已经发布的条目
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<?> resultList = subClausesService.queryAllSubClauses(obj);
		theForm.setResultList(resultList);
		return "subClauseslistForView";
	}
	
	/**
	 * 
	 * @Title: viewSubClause
	 * @Description: 查看条目信息
	 * @param
	 * @return String
	 */
	public String viewSubClause() {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		//查看类型 1:添加，2：修改
		String viewType = Struts2Utils.getRequest().getParameter("viewType");
		List<ResourceListObj> ss = new ArrayList<ResourceListObj>();
		ResourceListObj obj1 = new ResourceListObj();
		obj1.setId("");
		obj1.setName("--请选择--");
		ss.add(obj1);
		this.setResourceList(ss);
		if("1".equals(viewType)){
			theForm.setAcType("1");
		}else{
			String checkid = Struts2Utils.getRequest().getParameter("checkid");
			SubClausesObj obj = new SubClausesObj();
			obj = subClausesService.querySubClausesById(checkid);
			theForm.setId(obj.getId());
			theForm.setName(obj.getName());
			theForm.setDeclare(obj.getDeclare());
			theForm.setIndate(obj.getIndate());
			theForm.setResource_id(obj.getResource_id());
			theForm.setResource_info(obj.getResource_info());
			theForm.setResource_name(obj.getResource_name());
			theForm.setRole(obj.getRole());
			theForm.setType_id(obj.getType_id());
			theForm.setState(obj.getState());
			theForm.setResource_type(obj.getResource_type());
			ResourceList = (List<ResourceListObj>) subClausesService.getResourceNameList(obj.getResource_type());
			theForm.setAcType("2");
		}
		return "viewInfo";
	}
	
	/**
	 * 
	 * @Title: validateSubClauses
	 * @Description: 服务编码唯一性验证
	 * @param
	 * @return String
	 */
	public String validateSubClauses() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String clauseId = Struts2Utils.getRequest().getParameter("clauseId");
		String returnmsg = "";
		int count = subClausesService.validateSubClauses(clauseId);;
		if(count>=1){
			returnmsg = clauseId+"已经存在!";
		}
		PrintWriterOut.printWirter(response, returnmsg);
		return null;
	}
	
	/**
	 * 
	 * @Title: saveSubClauses
	 * @Description: 保存数据
	 * @param
	 * @return String
	 */
	public String saveSubClauses() {
			SubClausesObj obj = new SubClausesObj();
			obj.setId(theForm.getId());
			obj.setName(theForm.getName());
			obj.setIndate(theForm.getIndate());
			obj.setResource_type(theForm.getResource_type());
			obj.setResource_id(theForm.getResource_id());
			obj.setResource_info(theForm.getResource_info());
			obj.setResource_name(theForm.getResource_name());
			obj.setRole(theForm.getRole());
			obj.setType_id(theForm.getType_id());
			obj.setState(theForm.getState());
			obj.setDeclare(theForm.getDeclare());
			if("1".equals(theForm.getAcType())){
				subClausesService.AddSubClauses(obj);
			}else{
				int rsCount = subClausesService.UpdateSubClauses(obj);
			}
		
		return null;
	}
	
	/**
	 * 
	 * @Title: getResourceNameList
	 * @Description: 获取模板列表
	 * @param
	 * @return String
	 */
	public String getResourceNameList() {
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String resourceType = Struts2Utils.getRequest().getParameter("resourceType");
		String options = "";
		
		ResourceList = (List<ResourceListObj>) subClausesService.getResourceNameList(resourceType);
		this.setResourceList(ResourceList);
		theForm.setResultList(ResourceList);
		for(int i=0;i<ResourceList.size();i++){
			ResourceListObj arr = (ResourceListObj)ResourceList.get(i);
			String option = arr.getId()+","+arr.getName();
			if(i==0){
				options = option;
			}else{
				options = options + "|" +option;
			}
		}
		PrintWriterOut.printWirter(response, options);
		return null;
	}
	
	/**
	 * 
	 * @Title: deleteSubClause
	 * @Description: 删除条目
	 * @param
	 * @return String
	 */
	public String deleteSubClause(){
		if (!CommonUtil.isNotEmpty(theForm)) {theForm = new SubClausesForm();}
		String idstr = Struts2Utils.getRequest().getParameter("idstr");
		subClausesService.deleteSubClause(idstr);
		return "subClauseslist";
	}
	
	/**
	 * 
	 * @Title: publishState
	 * @Description: 发布条目
	 * @param
	 * @return String
	 */
	public String publishState(){
		String instr = Struts2Utils.getRequest().getParameter("clauseId");
		SubClausesObj obj = new SubClausesObj();
		String[] parameters = instr.split(",");
		String id = parameters[0];
		String state = parameters[1];
		if("0".equals(state)){
			obj.setPublish_state("1");
		}else{
			obj.setPublish_state("0");
		}
		obj.setId(id);
		subClausesService.publishState(obj);
		return null;
	}
	
	/**
	 * 
	 * @Title: getResourceInfo
	 * @Description: 获取模板规格
	 * @param
	 * @return String
	 */
	public String getResourceInfo() {
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String resourceType = Struts2Utils.getRequest().getParameter("resourceType");
		String resourceId = Struts2Utils.getRequest().getParameter("resourceId");
		String options = "";
		
		String rInfo = subClausesService.getResourceInfo(resourceType,resourceId);
		
		PrintWriterOut.printWirter(response, rInfo);
		return null;
	}
}
