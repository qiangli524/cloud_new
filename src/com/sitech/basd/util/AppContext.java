package com.sitech.basd.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>
 * Title: AppContext
 * </p>
 * <p>
 * Description: 获取Spring-ApplicationContext
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
 * @createtime 2013-4-20 上午11:59:52
 * 
 */
@Service
public class AppContext implements ApplicationContextAware {
	private static ApplicationContext appContext;

	/**
	 * spring容器初始化注入 ApplicationContext 对象
	 */
	public void setApplicationContext(ApplicationContext tc) throws BeansException {
		appContext = tc;
	}

	/**
	 * 
	 * @Title: getAppContext
	 * @Description: 获取Spring--ApplicationContext
	 * @param
	 * @return ApplicationContext
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-20 下午12:01:56
	 */
	public synchronized static ApplicationContext getAppContext() {
		if (appContext == null) {
			appContext = new ClassPathXmlApplicationContext("classpath:spring/app-config.xml");
		}
		return appContext;
	}

	/**
	 * 
	 * @Title: getBean
	 * @Description: 根据Name获取SpringBean
	 * @param
	 * @return Object
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-1 下午5:10:13
	 */
	public static Object getBean(String id) {
		Object obj = getAppContext().getBean(id);
		return obj;
	}

	/**
	 * 
	 * @Title: getBean
	 * @Description: 通过反射，获取SpringBean
	 * @param
	 * @return T
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-1 下午5:11:57
	 */
	public static <T> T getBean(String id, Class<T> clazz) {
		T obj = getAppContext().getBean(id, clazz);
		return obj;
	}

}
