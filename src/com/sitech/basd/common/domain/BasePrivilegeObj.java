package com.sitech.basd.common.domain;

import util.DomainUtil;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: BasePrivilegeObj
 * </p>
 * <p>
 * Description: 权限基础Obj
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-5-5 下午4:24:58
 * 
 */
public class BasePrivilegeObj extends BaseObj {
	// 用户所在域
	protected String domain = DomainUtil.getSessionStringDomain();
//	// 用户所在域--tb_cloud2_net_info独用
	protected String user_domain = DomainUtil.getSessionStringDomain();
	
//	protected String domain = "";
	// 用户所在域--tb_cloud2_net_info独用
//	protected String user_domain = "";

	// protected String domain = "";
	// // 用户所在域--tb_cloud2_net_info独用
	// protected String user_domain = "";

	public String getUser_domain() {
		return user_domain;
	}

	public void setUser_domain(String user_domain) {
		this.user_domain = user_domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
