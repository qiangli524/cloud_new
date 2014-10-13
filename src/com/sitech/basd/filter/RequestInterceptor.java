package com.sitech.basd.filter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysFunctionsDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;

/**
 * 
 * <p>
 * Title: RequestInterceptor
 * </p>
 * <p>
 * Description: Request拦截器，用户获取请求连接*。do
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-7-29 上午9:56:19
 * 
 */
@Service("requestInterceptor")
public class RequestInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 3244973830196015811L;
	@Autowired
	private TbSysFunctionsDao tbSysFunctionsDao;
	private Map<String, Object> session;

	public String intercept(ActionInvocation aInvocation) throws Exception {
		// 获取请求的action名称
		String actionName = aInvocation.getInvocationContext().getName();
		// 获取action后附带参数
		Map<String, Object> parameters = aInvocation.getInvocationContext().getParameters();
		session = aInvocation.getInvocationContext().getSession();
		TbSysFunctionsObj functionObj = new TbSysFunctionsObj();
		functionObj.setFUNCREQUEST(actionName);
		// 根据action查询功能点id
		TbSysFunctionsObj result = tbSysFunctionsDao.queryByObj(functionObj);
		if (result != null) {
			session.put(Constant.FUNCID, result.getID());
		}
		return aInvocation.invoke();
	}

}
