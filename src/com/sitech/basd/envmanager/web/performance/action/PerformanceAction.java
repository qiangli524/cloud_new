package com.sitech.basd.envmanager.web.performance.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.envmanager.domain.performance.AddressObj;
import com.sitech.basd.envmanager.domain.performance.PerformanceObj;
import com.sitech.basd.envmanager.service.performance.PerformanceService;
import com.sitech.basd.envmanager.web.performance.form.PerformanceForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

public class PerformanceAction extends BaseAction{
	
	PerformanceService performanceService;
	
	private PerformanceForm theForm ;
	
	private String theForm_CF_IP;
	
	private String theForm_MEM_USED;
	
	private String MEM_FREE;
	
	private String TIME;
	
	private String CPU_IDLE;
	
	
	private String ip;
	
	private String name;
	
	private String keyword;
	
	private String shell;
	
	private String msg = "";
	
	
	/**
	 * @Title:查询资源使用信息
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryPerformanceList(){
		if(theForm==null){
			theForm = new PerformanceForm();
		}
		PerformanceObj obj = new PerformanceObj();
		if(theForm_CF_IP!=null&&!theForm_CF_IP.equals("")){
			obj.setCF_IP(theForm_CF_IP.trim());
		}
		if(theForm_MEM_USED!=null&&!theForm_MEM_USED.equals("")){
			obj.setMEM_USED(theForm_MEM_USED.trim());
		}
		if(MEM_FREE!=null&&!MEM_FREE.equals("")){
			obj.setMEM_FREE(MEM_FREE.trim());
		}
		if(CPU_IDLE!=null&&!CPU_IDLE.equals("")){
			obj.setCPU_IDLE(CPU_IDLE.trim());
		}
		
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List perforList = performanceService.queryPerformanceObj(obj);
		theForm.setPerfor_List(perforList);
		return "success";
	}
	
	/**
	 * @Title:查询IP与MAC地址变更
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String queryAddress(){
		if(theForm==null){
			theForm = new PerformanceForm();
		}
		AddressObj obj =new AddressObj();
		if(theForm.getIP_OLD()!=null&&!"".equals(theForm.getIP_OLD())){
			obj.setIP_OLD(theForm.getIP_OLD().trim());
		}
		if(theForm.getMAC_OLD()!=null&&!"".equals(theForm.getMAC_OLD())){
			obj.setMAC_OLD(theForm.getMAC_OLD().trim());
		}
		if(theForm.getIP_NEW()!=null&&!"".equals(theForm.getIP_NEW())){
			obj.setIP_NEW(theForm.getIP_NEW().trim());
		}
		if(theForm.getMAC_NEW()!=null&&!"".equals(theForm.getMAC_NEW())){
			obj.setMAC_NEW(theForm.getMAC_NEW().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(
				Struts2Utils.getRequest()));// 分页
		List addressList = performanceService.queryAddressInfo(obj);
		theForm.setAddressList(addressList);
		
		return "address";
	}
	
	
	
	
	
	
	/**
	 * @Title:MAC地址检测
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String checkInfo(){
		
		
		return "check";
	}
	/**
	 * @Title:执行MAC地址检测
	 * @Copyright: Copyright (c) 2013-4
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public String startCheckInfo(){
		SshResourceFactory ssh = SshResourceFactory.getInstance();
		try {
			SshConnection conn=new SshConnection(ip, 22, name, keyword);
			List result =null;
			result= ssh.executeCommand(conn,shell);
			if(result.size()>0)
			{
				msg = "脚本执行成功请查看日志";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("".equals(msg)){
			msg = "脚本执行失败请重新操作";
		}
		return "check";
		
	}
	
	
	
	
	
	
	
	
	
	

	public PerformanceService getPerformanceService() {
		return performanceService;
	}

	public void setPerformanceService(PerformanceService performanceService) {
		this.performanceService = performanceService;
	}

	public PerformanceForm getTheForm() {
		return theForm;
	}

	public void setTheForm(PerformanceForm theForm) {
		this.theForm = theForm;
	}

	public String getTheForm_CF_IP() {
		return theForm_CF_IP;
	}
	public void setTheForm_CF_IP(String theForm_CF_IP) {
		this.theForm_CF_IP = theForm_CF_IP;
	}

	public String getTheForm_MEM_USED() {
		return theForm_MEM_USED;
	}

	public void setTheForm_MEM_USED(String theForm_MEM_USED) {
		this.theForm_MEM_USED = theForm_MEM_USED;
	}

	public String getMEM_FREE() {
		return MEM_FREE;
	}

	public void setMEM_FREE(String mem_free) {
		MEM_FREE = mem_free;
	}

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String time) {
		TIME = time;
	}

	public String getCPU_IDLE() {
		return CPU_IDLE;
	}

	public void setCPU_IDLE(String cpu_idle) {
		CPU_IDLE = cpu_idle;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getShell() {
		return shell;
	}

	public void setShell(String shell) {
		this.shell = shell;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
