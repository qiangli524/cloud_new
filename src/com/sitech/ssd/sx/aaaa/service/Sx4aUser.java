/**
 * Users4AService.java
 * com.sitech.ssd.sx.aaaa
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 25 		duangh
 *
 * Copyright (c) 2014, TNT All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * ClassName:Users4AService Function: 4A用户管理 对外提供接口
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 14:09:40
 */
@WebService
public interface Sx4aUser {
	/**
	 * 
	 * selAllUser:4A用户管理查询所有用户信息
	 * 
	 * @param selAllUserRequest
	 * @return SelAllUserResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "SelAllUserResponse")
	@WebMethod
	public String selAllUser(@WebParam(name = "selAllUserRequest")
	String selAllUserRequest);

	/**
	 * 
	 * selUser:4A用户管理查询单个用户信息类
	 * 
	 * @param selUserRequest
	 * @return SelUserResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "SelUserResponse")
	@WebMethod
	public String selUser(@WebParam(name = "selUserRequest")
	String selUserRequest);

	/**
	 * 
	 * addUser:4A用户管理添加用户信息
	 * 
	 * @param addUserRequest
	 * @return AddUserResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "AddUserResponse")
	@WebMethod
	public String addUser(@WebParam(name = "addUserRequest")
	String addUserRequest);

	/**
	 * 
	 * editUser:4A用户管理编辑用户信息
	 * 
	 * @param editUserRequest
	 * @return EditUserResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "EditUserResponse")
	@WebMethod
	public String editUser(@WebParam(name = "editUserRequest")
	String editUserRequest);

	/**
	 * 
	 * delUser:4A用户管理删除用户信息
	 * 
	 * @param
	 * @return DelUserResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "DelUserResponse")
	@WebMethod
	public String delUser(@WebParam(name = "delUserRequest")
	String delUserRequest);

	/**
	 * 
	 * operationUserPwd:4A用户管理修改用户密码信息
	 * 
	 * @param OperationUserPwdRequest
	 * @return OperationUserPwdResponse
	 * @since duangh Ver 1.0
	 */
	@WebResult(name = "OperationUserPwdResponse")
	@WebMethod
	public String operationUserPwd(@WebParam(name = "operationUserPwdRequest")
	String operationUserPwdRequest);

	@WebResult(name = "OperationUserResponse")
	@WebMethod
	public String operationUser(@WebParam(name = "operationUserRequest")
	String operationUserRequest);
}
