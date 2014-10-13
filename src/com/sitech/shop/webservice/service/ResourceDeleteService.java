/**   
 * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
* @Title: ResourceDeleteService.java 
* @Package com.sitech.shop.webservice.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wanglei_bj@si-tech.com.cn 
* @date 2014-9-12 下午2:34:32 
* @version V1.0   
*/
package com.sitech.shop.webservice.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.shop.domain.balance.LoadBalanceObj;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;

/** 
 * @ClassName: ResourceDeleteService 
 * @Description: 资源删除接口
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-9-12 下午2:34:32 
 * @version 1.0 
 */
@Path("/deleteResource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ResourceDeleteService {
	
	/** 
	*
	* @Title: systemDeletePublicIP 
	* @Description: 系统删除公网IP，不需要通知计费
	* @param @param obj
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	 * @throws Exception 
	* @throws 
	* @createtime 2014-9-12
	*/
	Boolean systemDeletePublicIP(PublicIPObj obj) throws Exception;
	
	/** 
	*
	* @Title: userDeletePublicIP 
	* @Description: 用户删除公网IP，通知计费
	* @param @param obj
	* @param @return    设定文件 
	* @return string  PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL 删除失败  返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	 * @throws Exception 
	* @throws 
	* @createtime 2014-9-12
	*/
	@POST
    @Path("/publicip")
	String userDeletePublicIP(PublicIPObj obj) throws Exception;
	
	/** 
	*
	* @Title: systemDeleteBandWidth 
	* @Description: 系统删除带宽，不需要通知计费
	* @param obj
	* @return Boolean    返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-9-12
	*/
	Boolean systemDeleteBandWidth(PhysicalVlanObj vlan);
	
	/** 
	*
	* @Title: userDeleteBandWidth 
	* @Description: 用户删除带宽，通知计费
	* @param @param obj
	* @return string  PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL 删除失败   返回类型 
	* @author wanglei_bj@si-tech.com.cn 
	* @throws 
	* @createtime 2014-9-12
	*/
	@POST
    @Path("/bandwidth")
	String userDeleteBandWidth(PhysicalVlanObj vlan);
	
	/**
	 * 系统删除虚拟机
	 */
	Boolean systemDeleteVm(VMHostObj vm);
	/**
	* 用户删除虚拟机，通知计费
	* @return string  PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL 删除失败
	*/
	@POST
    @Path("/vm")
	String userDeleteVm(VMHostObj vm);
	/**
	 * 系统删除磁盘
	 * @throws Exception 
	 */
	Boolean systemDeleteDisk(VirtualDiskObj disk) throws Exception;
	/**
	* 用户删除磁盘，通知计费
	* @return string  PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL 删除失败
	 * @throws Exception 
	*/
	@POST
    @Path("/disk")
	String userDeleteDisk(VirtualDiskObj disk) throws Exception;
	/**
	 * 系统删除负载均衡
	 */
	Boolean systemDeleteLoadBalance(LoadBalanceObj lb);
	/**
	* 用户删除负载均衡，通知计费
	* @return string  PublicCloudConstant.SUCCESS 删除成功 PublicCloudConstant.FAIL 删除失败
	*/
	@POST
    @Path("/loadbalance")
	String userDeleteLoadBalance(LoadBalanceObj lb);
}
