/**
 * Sx4aRoleService.java
 * com.sitech.ssd.sx.aaaa.service
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 26 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * ClassName:Sx4aRoleService Function: 4A用户角色管理类
 * 
 * @author duangh
 * @since Ver 1.0
 * @Date 2014 三月 26 10:45:22
 */
@WebService
public interface Sx4aRole {
	/**
	 * 
	 * AddRole:4A用户增加角色
	 * 
	 * @param addRoleRequest
	 * @return AddRoleResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "AddRoleResponse")
	public String addRole(@WebParam(name = "addRoleRequest")
	String addRoleRequest);

	/**
	 * 
	 * editRole:4A用户管理编辑角色
	 * 
	 * @param EditRoleRequest
	 * @return EditRoleResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "EditRoleResponse")
	public String editRole(@WebParam(name = "editRoleRequest")
	String editRoleRequest);

	/**
	 * 
	 * delRole:4A用户管理删除角色
	 * 
	 * @param delRoleRequest
	 * @return DelRoleResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "DelRoleResponse")
	public String delRole(@WebParam(name = "delRoleRequest")
	String delRoleRequest);

	/**
	 * 
	 * selRole:4A用户管理查询角色信息
	 * 
	 * @param SelRoleRequest
	 * @return SelRoleResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "SelRoleResponse")
	public String selRole(@WebParam(name = "selRoleRequest")
	String selRoleRequest);
}
