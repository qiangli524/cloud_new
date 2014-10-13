package com.sitech.basd.yicloud.util.xenTimeSyn;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sitech.basd.yicloud.web.xensyndata.action.XenDataTimingSyn;


public class XenServletContextListener implements ServletContextListener {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		logger.debug("容器销毁时触发--->"+event.getServletContext().getContextPath());
	}

	@Override
	public void contextInitialized(final ServletContextEvent event) {
		// TODO Auto-generated method stub
		new Timer().schedule(new TimerTask(){
		       public void run() {
		 logger.debug("容器创建时触发--->");
		 WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		 final XenDataTimingSyn xenDataTimingSyn=(XenDataTimingSyn)wac.getBean("xenDataTimingSyn");
		 ScheduledThreadPoolExecutor exec=new ScheduledThreadPoolExecutor(1); 
		 exec.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					xenDataTimingSyn.xenTimingSys();
//					xenDataTimingSyn.xenTimingSysVmState();
				}
			}, 30*1000, 30*1000, TimeUnit.MILLISECONDS);
		 }}, 30*1000);
	}

}
