package com.sitech.basd.sxcloud.rsmu.web.cloudschedu.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.VirtualServerObj;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysUserinfoObj;
import com.sitech.basd.sxcloud.rsmu.lbsms.LbsClient;
import com.sitech.basd.sxcloud.rsmu.lbsms.LbsResult;
import com.sitech.basd.sxcloud.rsmu.lbsms.VirtualServiceBean;
import com.sitech.basd.sxcloud.rsmu.service.cloudschedu.VirtualServerService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.cloudschedu.form.VirtualServerForm;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.sxcloud.util.exception.BaseException;

public class VirtualServerAction extends CRUDBaseAction {
	private final String username="u1";
    private final String password="1234";
    private final String url="http://172.21.3.161:8090/lbsms/services/LbsService";
    private VirtualServerForm theForm;
    
	public VirtualServerForm getTheForm() {
		return theForm;
	}

	public void setTheForm(VirtualServerForm theForm) {
		this.theForm = theForm;
	}

	/**
	 * @Title:查询出所有虚拟服务器信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public String listVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		theForm.setId(0);
		VirtualServerObj obj = new VirtualServerObj();
		if (theForm.getName() != null && !"".equals(theForm.getName())) {
			obj.setName(theForm.getName().trim());
		}
		if (theForm.getVirtualAddress() != null
				&& !"".equals(theForm.getVirtualAddress())) {
			obj.setVirtualAddress(theForm.getVirtualAddress().trim());
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List appList = virtualServerService.queryForListByObj(obj);
		theForm.setResultList(appList);
		return "listVirtualServer";

	}

	/**
	 * @Title:删除虚拟服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String delVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		VirtualServerObj obj = new VirtualServerObj();
		obj.setId(theForm.getId());
		
		obj=virtualServerService.queryByObj(obj);
		virtualServerService.deleteByObj(obj);

        //删除虚拟服务器成功后,主机调用调度中心接口
		LbsClient client = new LbsClient(username, password, url);
		VirtualServiceBean vsBean = new VirtualServiceBean();
		vsBean.setId(obj.getINFO());
		vsBean.setLoginNo(username);
		LbsResult result = client.DeleteVirtualService(vsBean);
		
		return listVirtualServer();

	}

	/**
	 * @Title:新增虚拟服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String addVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		theForm.setPersistent("600");
		theForm.setNetmask("255.255.255.255");
		//theForm.setFallback("127.0.0.1:80");

		return "addVirtualServer";

	}

	/**
	 * @Title:保存虚拟服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureAddVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		VirtualServerObj obj = new VirtualServerObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		TbSysUserinfoObj tempObj = (TbSysUserinfoObj) Struts2Utils.getRequest().getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setOptr_id(session.get("account").toString());
		obj.setOptr_name(session.get("name").toString());
		
		
        //添加虚拟服务器成功后,主机调用调度中心接口
		LbsClient client = new LbsClient(username, password, url);
		VirtualServiceBean vsBean = new VirtualServiceBean();
		try {
			BeanUtils.copyProperties(vsBean, theForm);
			vsBean.setLoginNo(username);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        //添加成功,info值为服务器的数据库id项
		LbsResult result = client.AddVirtualService(vsBean);
		if(result.getCode()==0){
			obj.setINFO(result.getInfo());
			virtualServerService.insertByObj(obj);
		}
	//	theForm.reset(mapping, request);
		return listVirtualServer();

	}

	/**
	 * @Title:修改虚拟服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String modVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		VirtualServerObj obj = new VirtualServerObj();
		obj.setId(theForm.getId());

		VirtualServerObj tempObj = virtualServerService.queryByObj(obj);
		theForm.setId(tempObj.getId());
		theForm.setName(tempObj.getName());
		theForm.setVirtualAddress(tempObj.getVirtualAddress());
		theForm.setScheduler(tempObj.getScheduler());
		theForm.setProtocol(tempObj.getProtocol());
		theForm.setPersistentType(tempObj.getPersistentType());
		theForm.setPersistent(tempObj.getPersistent());
		theForm.setNetmask(tempObj.getNetmask());
		theForm.setFallback(tempObj.getFallback());
		theForm.setChecktype(tempObj.getChecktype());
		theForm.setService(tempObj.getService());
		theForm.setCheckport(tempObj.getCheckport());
		theForm.setVirtualHost(tempObj.getVirtualHost());
		theForm.setLogin(tempObj.getLogin());
		theForm.setPassword(tempObj.getPassword());
		theForm.setCheckdb(tempObj.getCheckdb());
		theForm.setHttpMethod(tempObj.getHttpMethod());
		theForm.setRequest(tempObj.getRequest());
		theForm.setReceive(tempObj.getReceive());
		theForm.setINFO(tempObj.getINFO());
		return "modVirtualServer";
	}

	/**
	 * @Title:修改虚拟服务器信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author chenjl
	 * @version 1.0
	 */
	public String sureModVirtualServer() throws BaseException {
		if(theForm==null){
			theForm = new VirtualServerForm();
		}
		VirtualServerObj obj = new VirtualServerObj();
		try {
			BeanUtils.copyProperties(obj, theForm);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		TbSysUserinfoObj tempObj = (TbSysUserinfoObj) Struts2Utils.getRequest().getSession()
//				.getAttribute(Constant.USER_SESSION_KEY);
		obj.setOptr_id(session.get("account").toString());
		obj.setOptr_name(session.get("name").toString());
		virtualServerService.updateByObj(obj);
		
        //修改虚拟服务器成功后,主机调用调度中心接口
		LbsClient client = new LbsClient(username, password, url);
		VirtualServiceBean vsBean = new VirtualServiceBean();
		try {
			BeanUtils.copyProperties(vsBean, obj);
			vsBean.setId(obj.getINFO());
			vsBean.setLoginNo(username);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LbsResult result = client.UpdateVirtualService("",
				"", vsBean);
		
	//	theForm.reset(mapping, request);
		return listVirtualServer();
	}

	VirtualServerService virtualServerService;

	public void setVirtualServerService(
			VirtualServerService virtualServerService) {
		this.virtualServerService = virtualServerService;
	}

}
