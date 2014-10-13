/**
 * Copyright (c) 2013 SI-TECH Software, Inc.
 * All right reserved. 
 * 
 * 云管理平台
 */
package com.sitech.ssd.cq.service;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.system.FuncRoleObj;

/**
 *<P>
 * 类功能简述（例如：XXX功能）
 *</p>
 * Jun 20, 2014,10:44:58 AM
 * 
 * @author xugang
 * 
 * @version 1.0
 */
public interface HomeService {
	public List getMenus(FuncRoleObj funcRoleObj);
}
