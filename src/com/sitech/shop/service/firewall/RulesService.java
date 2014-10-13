package com.sitech.shop.service.firewall;

import java.util.List;

import com.sitech.shop.domain.firewall.RulesObj;

public interface RulesService {
	/** 
	*
	* @Title: getRulesList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj
	* @param @return    设定文件 
	* @return List<RulesObj>    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public List<RulesObj> getRulesList(RulesObj obj);
	
	/** 
	*
	* @Title: createRules 
	* @Description: TODO(createRules) 
	* @param @param obj
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public String createRules(RulesObj obj);
	
	/** 
	*
	* @Title: updateRules 
	* @Description: TODO(updateRules) 
	* @param @param obj
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public String updateRules(RulesObj obj);
	
	/** 
	*
	* @Title: deleteRules 
	* @Description: TODO(deleteRules) 
	* @param @param obj
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public String deleteRules(RulesObj obj);
	

	/** 
	*
	* @Title: getRules 
	* @Description: TODO(获得一个Vlan) 
	* @param @param obj
	* @param @return    设定文件 
	* @return RulesObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public RulesObj getRules(RulesObj obj);

	/** 
	*
	* @Title: queryForCount 
	* @Description: TODO() 
	* @param @param obj
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer queryForCount(RulesObj obj);

}
