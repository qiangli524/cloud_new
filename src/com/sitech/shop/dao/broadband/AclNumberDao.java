package com.sitech.shop.dao.broadband;

import java.sql.SQLException;
import java.util.List;

import com.sitech.shop.domain.broadband.AclNumberObj;

/**
 * 用户购买宽带时需要的ACL号
 * @author duangh
 *
 */
public interface AclNumberDao {
	/**
	 * 查询acl号列表
	 * @param AclNumberObj
	 * @return acl number list
	 */
	public List<AclNumberObj> queryAclNumList(AclNumberObj obj);
	
	/**
	 * 更改acl号信息
	 * @param 要更改的acl号clNumberObj对象
	 * @return 更改的acl号的ID
	 */
	public int updateAclNum(AclNumberObj obj);
	
	/**
	 * 删除acl号
	 * @param 要删除的acl号信息obj
	 * @return 删除的acl号ID
	 */
	public int delAclNum(AclNumberObj obj);
	
	/**
	 * 增加acl号信息
	 * @param 要增加的acl号信息
	 * @return 增加的acl号ID
	 */
	public int insertAclNum(AclNumberObj obj);
	
	/** 
	*
	* @Title: getFirewallByObj 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param obj
	* @param @return    设定文件 
	* @return AclNumberObj    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public AclNumberObj getAclNumberByObj(AclNumberObj obj);

	/**
	 * @throws SQLException  
	*
	* @Title: queryForCount 
	* @Description: TODO(查询ACL策略号数量传入参数不限于 user_id,area_id) 
	* @param @param obj
	* @param @return    设定文件 
	* @return Integer    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	*/
	public Integer queryForCount(AclNumberObj obj) throws SQLException;
}
