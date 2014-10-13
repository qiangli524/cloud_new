package com.sitech.basd.sxcloud.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * <p>
 * Title: SpringUtil
 * </p>
 * <p>
 * Description: Spring工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Oct 11, 2012 2:31:49 PM
 * 
 */
public class SpringUtil {
	private static WebApplicationContext wac = WebApplicationContextUtils
			.getRequiredWebApplicationContext(Struts2Utils.getServletContext());

	/**
	 * 
	 * @Title: getBean
	 * @Description: 获取Spring中bean
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Oct 11, 2012 2:32:43 PM
	 */
	public static Object getBean(String name) {
		return wac.getBean(name);
	}
}
