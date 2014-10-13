package com.sitech.ssd.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 *<P>
 * spring上下文工具类
 *<p/>
 * 2012-11-13,上午11:43:29
 * 
 * @author xugang
 * 
 * @version 1.0
 */
@Component("SpringContextUtil")
public class SpringContextUtil implements ApplicationContextAware {   
	public  static ApplicationContext applicationContext;

	public static ApplicationContext getInsance(){
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}   
	public static Object getBean(String beanId) throws BeansException{
		return applicationContext.getBean(beanId);
	}
}