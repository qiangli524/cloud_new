package com.sitech.ssd.test.resource;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.sitech.basd.resource.dao.united.ResourceStatisticsDao;
import com.sitech.basd.resource.domain.united.ResourceStatisticsObj;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2NetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.net.NetService;
import com.sitech.basd.sxcloud.cloud.service.net.NetServiceImpl;
import com.sitech.basd.yicloud.web.scheduler.action.StrategyAction;
import com.sitech.ssd.test.base.spring.AppContext;

public class Testsch {
	@Ignore
	public void testGetHost(){
		StrategyAction  action = AppContext.getBean("strategyAction", StrategyAction.class);
//		List<UnitedTreeObj> list = action.getHost();
		
	}
	@Test
	public void testNet(){
		TbCloud2NetInfoObj obj = new TbCloud2NetInfoObj();
		obj.setVLAN_ID("");
		NetService netService = AppContext.getBean("netService",NetServiceImpl.class);
		List<TbCloud2NetInfoObj> list = netService.queryByNetObjForList(obj);
	}
}
