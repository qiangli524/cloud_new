package com.sitech.basd.envmanager.web.scriptmanage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.sitech.basd.envmanager.domain.scriptmanage.ScriptmanageObj;
import com.sitech.basd.envmanager.service.scriptmanage.ScriptmanageService;
import com.sitech.basd.envmanager.web.scriptmanage.form.ScriptmanageForm;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.UUIDGenerator;
import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

public class ScriptmanageAction  extends BaseAction{ 
	
	private ScriptmanageForm theForm;
	
	private ScriptmanageService scriptmanageService;
	
	private String msg = "";
	
	private HostInfoService hostInfoService;
	
	
	
	/**
	 * @Title:查询主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryScriptmanageList(){
		if(theForm==null){
			theForm = new ScriptmanageForm();}
		
		ScriptmanageObj obj = new ScriptmanageObj (); 	
		if(theForm.getSCRIPT_USERNAME()!=null&&!"".equals(theForm.getSCRIPT_USERNAME())){
			obj.setSCRIPT_USERNAME(theForm.getSCRIPT_USERNAME().trim());
		}
		if(theForm.getSCRIPT_IP()!=null&&!"".equals(theForm.getSCRIPT_IP())){
			obj.setSCRIPT_IP(theForm.getSCRIPT_IP().trim());
		}
		
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List scriptmanageList = scriptmanageService.queryScriptmanageObj(obj);
		theForm.setScriptmanageList(scriptmanageList);
		return "list";
	}
	
	/**
	 * @Title:检测IP与用户是否存在相同
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String checkManager()throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String SCRIPT_IP = request.getParameter("SCRIPT_IP");
		String SCRIPT_USERNAME = request.getParameter("SCRIPT_USERNAME");
		
		ScriptmanageObj obj = new ScriptmanageObj (); 
		List scriptmanageList =null;
			obj.setSCRIPT_IP(SCRIPT_IP);
			obj.setSCRIPT_USERNAME(SCRIPT_USERNAME);
			scriptmanageList = scriptmanageService.queryScriptmanageObj(obj);
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		int result=-1;
		if(scriptmanageList!=null&&scriptmanageList.size()>0){
			result=1;
		}
		JSONObject json=new JSONObject();
		json.put("result", result);
		p.print(json);
		p.close();
		return null;
		
	}

	public String getCheckHostName() throws Exception{
		
		HttpServletRequest request = Struts2Utils.getRequest();
		String EQ_IP = request.getParameter("EQ_IP");
		List scriptmanageList =null;
		ScriptmanageObj obj = new ScriptmanageObj (); 
		if(EQ_IP!=null){
			obj.setEQ_IP(EQ_IP);
			 scriptmanageList = scriptmanageService.queryScriptmanageObj(obj);
		}
		
		PrintWriter p = Struts2Utils.getResponse().getWriter();
		int result=-1;
		if(scriptmanageList!=null&&scriptmanageList.size()>0){
			result=1;
		}
		JSONObject json=new JSONObject();
		json.put("result", result);
		p.print(json);
		p.close();
		return null;
		
	}
	/**
	 * @Title:进入添加页面
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String addScriptmanageObj(){
		if(theForm==null){
			theForm = new ScriptmanageForm();}
		ScriptmanageObj obj = new ScriptmanageObj (); 	
//		List hostipList =  scriptmanageService.queryHostipObj(obj);
//		theForm.setHostipList(hostipList);
		return "add";
	}
	
	/**
	 * @Title:选择服务器IP
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String jumpScriptmanageObj(){
		if(theForm==null){
			theForm = new ScriptmanageForm();}
		ScriptmanageObj obj = new ScriptmanageObj (); 	
		obj.setEQ_IP(theForm.getEQ_IP());
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List hostipList =  scriptmanageService.queryHostipObj(obj);
		theForm.setHostipList(hostipList);
		return "jump";
	}
	/**
	 * @Title:修改脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String editScriptmanageObj(){
		ScriptmanageObj obj = new ScriptmanageObj (); 	
		obj.setS_id(theForm.getS_id());
		ScriptmanageObj scObj = scriptmanageService.queryScriptmanageOne(obj);
		
		theForm.setS_id(scObj.getS_id());
		theForm.setSCRIPT_IP(scObj.getSCRIPT_IP());
		theForm.setSCRIPT_USERNAME(scObj.getSCRIPT_USERNAME());
		theForm.setSCRIPT_PASSWORD(scObj.getSCRIPT_PASSWORD());
		theForm.setSCRIPT_CONTENT(scObj.getSCRIPT_CONTENT());
		theForm.setSCRIPT_USE(scObj.getSCRIPT_USE());
		
		theForm.setFlag(1);
		return "update";
	}
	/**
	 * @Title:保存主机脚本
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String saveScriptmanageObj(){
		if(theForm==null){
			theForm = new ScriptmanageForm();}
		ScriptmanageObj obj = new ScriptmanageObj ();

		UUIDGenerator uuid = new UUIDGenerator();
		int flag = theForm.getFlag();
		
		if(flag==1){
			obj.setS_id(theForm.getS_id());
		}
		else{
			obj.setS_id(uuid.getUUID());
		}
		
		obj.setSCRIPT_IP(theForm.getSCRIPT_IP());
		obj.setSCRIPT_USERNAME(theForm.getSCRIPT_USERNAME());
		obj.setSCRIPT_PASSWORD(theForm.getSCRIPT_PASSWORD());
		obj.setSCRIPT_CONTENT(theForm.getSCRIPT_CONTENT());
		obj.setSCRIPT_USE(theForm.getSCRIPT_USE());
		
		if(flag==1){
			scriptmanageService.updateScriptmanageObj(obj);
		}
		else{
			scriptmanageService.insertScriptmanageObj(obj);
		}
		return "save";
	}
	
	/**
	 * @Title:删除主机脚本信息
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String deletecriptmanageObj(){
		if(theForm==null){
			theForm = new ScriptmanageForm();}
		ScriptmanageObj obj = new ScriptmanageObj (); 	
		obj.setS_id(theForm.getS_id());
		scriptmanageService.deleteScriptmanageObj(obj);
		return "del";
	}
	/**
	 * @Title:执行主机脚本信息进行备
	 * @Copyright: Copyright (c) 2013-5
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String  actionBackup(){
		HttpServletRequest request = Struts2Utils.getRequest();
		ScriptmanageObj obj = new ScriptmanageObj (); 	
		String s_id = request.getParameter("s_id");
		obj.setS_id(s_id);
		ScriptmanageObj scObj = scriptmanageService.queryScriptmanageOne(obj);
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn=new SshConnection(scObj.getSCRIPT_IP(), 22, scObj.getSCRIPT_USERNAME(), scObj.getSCRIPT_PASSWORD());
			List result = null;
			result = ssh.executeCommand(conn,scObj.getSCRIPT_CONTENT());
			if(result.size()>0) //现只能判断用户密码是否正确，但无法准确的判断脚本执行是否成功
			{
				msg = "脚本执行成功请查看日志";//脚本执行成功请查看日志
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(msg)){
			msg = "脚本执行失败请检查执行用户与密码";
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List scriptmanageList = scriptmanageService.queryScriptmanageObj(obj);
		theForm.setScriptmanageList(scriptmanageList);
		return "list";
	}
 
	public ScriptmanageForm getTheForm() {
		return theForm;
	}

	public void setTheForm(ScriptmanageForm theForm) {
		this.theForm = theForm;
	}

	public ScriptmanageService getScriptmanageService() {
		return scriptmanageService;
	}

	public void setScriptmanageService(ScriptmanageService scriptmanageService) {
		this.scriptmanageService = scriptmanageService;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public HostInfoService getHostInfoService() {
		return hostInfoService;
	}
	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	
	
	
	
	
	
	
	
	

}
