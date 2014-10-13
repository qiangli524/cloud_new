package com.sitech.ssd.test.resource;

import java.sql.SQLException;

import org.junit.Test;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.resource.service.united.VMwareDataCompareService;
import com.sitech.basd.resource.service.united.XenDataCompareService;
import com.sitech.ssd.test.base.spring.AppContext;

/**
 * 
 * <p>
 * Title: VMwareDataCompareServiceTestCase
 * </p>
 * <p>
 * Description: VMwareDataCompareService测试
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
 * @createtime 2013-7-26 下午3:01:33
 * 
 */
public class VMwareDataCompareServiceTestCase {
	/**
	 * 
	 * @Title: testInitCloudUnitedApiDataMap
	 * @Description: 测试接口Map
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午3:27:17
	 */
	// @Test
	public void testInitCloudUnitedApiDataMap() {
		VMwareDataCompareService service = AppContext.getBean("vmwareDataCompareService",
				VMwareDataCompareService.class);
	}

	/**
	 * 
	 * @Title: testInitCloudDbDataMap
	 * @Description: 测试数据库数据Map
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-26 下午3:28:32
	 */
	@Test
	public void testVmwareDataCompare() {
		VMwareDataCompareService service = AppContext.getBean("vmwareDataCompareService",
				VMwareDataCompareService.class);
		XenDataCompareService xenService = AppContext.getBean("xenDataCompareService",
				XenDataCompareService.class);
		xenService.xenDataCompare();
		try {
			service.vmwareDataCompare();
		} catch (DataSynchroException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
