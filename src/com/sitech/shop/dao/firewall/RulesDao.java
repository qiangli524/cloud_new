package com.sitech.shop.dao.firewall;

/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
* @Title: RulesDao.java 
* @Package com.sitech.basd.resource.dao.united 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-4-23 上午11:14:49 
* @version V1.0   
*/
import java.sql.SQLException;
import java.util.List;

import com.sitech.shop.domain.firewall.RulesObj;

/** 
 * @ClassName: RulesDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 上午11:14:49 
 * @version 1.0 
 */
public interface RulesDao {
	
	/** 
	*
	* @Title: queryForListByObj 
	* @Description: TODO(查询) 
	* @param @param obj
	* @param @return    设定文件 
	* @return List<RulesObj>    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public List<RulesObj> queryForListByObj(RulesObj obj);
	
	/** 
	*
	* @Title: insertByObj 
	* @Description: TODO(新增) 
	* @param @param obj
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public int insertByObj(RulesObj obj);
	/** 
	*
	* @Title: deleteByObj 
	* @Description: TODO(删除) 
	* @param @param obj
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public int deleteByObj(RulesObj obj);
	/** 
	*
	* @Title: updateByObj 
	* @Description: TODO(更新) 
	* @param @param obj
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public int updateByObj(RulesObj obj);
	
	/** 
	*
	* @Title: getRulesByObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj
	* @param @return    设定文件 
	* @return RulesObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public RulesObj getRulesByObj(RulesObj obj);

	/**
	 * @throws SQLException  
	*
	* @Title: queryForCount 
	* @Description: TODO(查询防火墙数量传入参数不限于 user_id,area_id) 
	* @param @param obj
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer queryForCount(RulesObj obj) throws SQLException;

}
