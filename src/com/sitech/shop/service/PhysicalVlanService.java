/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: PhysicalVlanService.java 
 * @Package service.vlan 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 下午1:21:01 
 * @version V1.0   
 */
package com.sitech.shop.service;

import java.util.List;

import com.sitech.shop.domain.vlan.PhysicalVlanObj;


/**
 * @ClassName: PhysicalVlanService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 下午3:18:28
 * @version 1.0
 */
public interface PhysicalVlanService {

	/**
	 * 
	 * @Title: getPhysicalVlanList
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public List<PhysicalVlanObj> getPhysicalVlanList(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: getPhysicalVlanList
	 * @Description: 获取一条记录
	 * @param @param obj
	 * @param @return 设定文件
	 * @return List<PhysicalVlanObj> 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlanObj(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: createPhysicalVlan
	 * @Description: TODO(createPhysicalVlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String createPhysicalVlan(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: updatePhysicalVlan
	 * @Description: TODO(updatePhysicalVlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String updatePhysicalVlan(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: deletePhysicalVlan
	 * @Description: TODO(deletePhysicalVlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String deletePhysicalVlan(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: collectPhysicalVlan
	 * @Description: TODO(从用户那回收Vlan)
	 * @param @param obj.id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String collectPhysicalVlan(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: getPhysicalVlan
	 * @Description: TODO(获得一个Vlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return PhysicalVlanObj 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getPhysicalVlan(PhysicalVlanObj obj);

	/**
	 * 
	 * @Title: getARandomVlan
	 * @Description: TODO(获得一个随机的Vlan)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public PhysicalVlanObj getARandomVlan(PhysicalVlanObj obj);

	public Integer queryForCount(PhysicalVlanObj obj);


}
