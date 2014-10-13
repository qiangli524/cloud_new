package com.sitech.basd.sxcloud.rsmu.web.system.action;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.TbCloud2SecurityConfigObj;
import com.sitech.basd.sxcloud.rsmu.service.system.TbCloud2SecurityConfigService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;

public class SecurityConfigAction  extends CRUDBaseAction{
	private TbCloud2SecurityConfigService securityConfigService;
	
	private List resultList;
	private TbCloud2SecurityConfigObj securityConfigObj;
	
	public TbCloud2SecurityConfigObj getSecurityConfigObj() {
		return securityConfigObj;
	}

	public void setSecurityConfigObj(TbCloud2SecurityConfigObj securityConfigObj) {
		this.securityConfigObj = securityConfigObj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public void setSecurityConfigService(
			TbCloud2SecurityConfigService securityConfigService) {
		this.securityConfigService = securityConfigService;
	}

	/**
	 * 
	 * @Title: listSecurityConfig
	 * @Description: 列出所有安全配置
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-2 下午6:23:54
	 */
	public String listSecurityConfig(){
		if(securityConfigObj==null){
			securityConfigObj = new TbCloud2SecurityConfigObj();
		}
		TbCloud2SecurityConfigObj seObj = new TbCloud2SecurityConfigObj();
		if(securityConfigObj.getQueryStatus()!=null && !"".equals(securityConfigObj.getQueryStatus())){
			seObj.setStatus(securityConfigObj.getQueryStatus());
		}
		if(securityConfigObj.getQueryType()!=null && !"".equals(securityConfigObj.getQueryType())){
			seObj.setType(securityConfigObj.getQueryType());
		}
		seObj.setPagination(this.getPaginater().initPagination(request));// 分页
		resultList = securityConfigService.queryForAllList(seObj);
		return "listSecConfig";
	}
	
	/**
	 * 
	 * @Title: addSecurityConfig
	 * @Description: 跳转到添加安全配置页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-2 下午7:31:52
	 */
	public String addSecurityConfig(){
		return "add";
	}
	
	/**
	 * 
	 * @Title: modSecurityConfig
	 * @Description: 跳转到修改安全配置界面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 下午1:06:34
	 */
	public String modSecurityConfig(){
		int secType = Integer.parseInt(request.getParameter("secType"));
		TbCloud2SecurityConfigObj secObj = new TbCloud2SecurityConfigObj();
		secObj.setType(secType);
		List<TbCloud2SecurityConfigObj> lst = securityConfigService.queryForAllList(secObj);
		if(lst!=null && lst.size()>0){
			securityConfigObj = lst.get(0);
		}
		return "mod";
	}
	
	/**
	 * 
	 * @Title: saveSecurityConfig
	 * @Description: 保存安全配置
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 上午10:58:42
	 */
	public String saveSecurityConfig(){
		if(securityConfigObj==null){
			securityConfigObj = new TbCloud2SecurityConfigObj();
		}
		securityConfigService.updateObjByObj(securityConfigObj);
		return "save";
	}
	
	/**
	 * 
	 * @Title: delSecurityConfig
	 * @Description:删除安全配置
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-3 下午4:43:16
	 */
	public String delSecurityConfig(){
		int secType = Integer.parseInt(request.getParameter("secType"));
		TbCloud2SecurityConfigObj secObj = new TbCloud2SecurityConfigObj();
		secObj.setType(secType);
		securityConfigService.deleteByObj(secObj);
		return "del";
	}
}
