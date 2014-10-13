package com.sitech.ssd.test.gx;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitech.ssd.gx.service.sync.SyncHuaweiDataImpl;
import com.sitech.utils.exception.HttpClientException;

public class SyncHuaweiDataTest {
	
//	@Test
	public void queryAllSite(){
		SyncHuaweiDataImpl syncHuaweiData = new ClassPathXmlApplicationContext("classpath:spring/app-config-huaweiBean.xml").getBean("syncHuaweiData",
				SyncHuaweiDataImpl.class);
		try {
			syncHuaweiData.queryAllSite();
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void queryAllClusters(){
		SyncHuaweiDataImpl syncHuaweiData = new ClassPathXmlApplicationContext("classpath:spring/app-config-huaweiBean.xml").getBean("syncHuaweiData",
				SyncHuaweiDataImpl.class);
		try {
			syncHuaweiData.queryAllClusters("45C707FC");
		} catch (HttpClientException e) {
			e.printStackTrace();
		} 
	}
	
//	@Test
	public void queryAllHosts(){
		SyncHuaweiDataImpl syncHuaweiData = new ClassPathXmlApplicationContext("classpath:spring/app-config-huaweiBean.xml").getBean("syncHuaweiData",
				SyncHuaweiDataImpl.class);
		try {
			syncHuaweiData.queryAllHosts("45C707FC");
		} catch (HttpClientException e) {
			e.printStackTrace();
		} 
	}
	
//	@Test
	public void queryAllVMs(){
		SyncHuaweiDataImpl syncHuaweiData = new ClassPathXmlApplicationContext("classpath:spring/app-config-huaweiBean.xml").getBean("syncHuaweiData",
				SyncHuaweiDataImpl.class);
		try {
			syncHuaweiData.queryAllVMs("45C707FC");
		} catch (HttpClientException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void initAllEntity(){
		SyncHuaweiDataImpl syncHuaweiData = new ClassPathXmlApplicationContext("classpath:spring/app-config-huaweiBean.xml").getBean("syncHuaweiData",
				SyncHuaweiDataImpl.class);
		try {
			syncHuaweiData.initRestEntities();
		} catch (HttpClientException e) {
			e.printStackTrace();
		}
	}
}
