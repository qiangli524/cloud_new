package com.sitech.ssd.test.ibm;

import org.junit.Test;

import com.sitech.basd.exception.DataSynchroException;
import com.sitech.basd.ibmmanager.service.IBMDataCompareService;
import com.sitech.basd.util.AppContext;

/**
 * 
 * <p>
 * Title: IbmDataCompareServiceTestCase
 * </p>
 * <p>
 * Description:ibmDataCompareService测试
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
 * @createtime 2013-11-14 下午3:10:55
 * 
 */
public class IbmDataCompareServiceTestCase {
	/**
	 * 
	 * @Title: testIBMDataCompare
	 * @Description: IBM数据比对
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-11-14 下午3:12:26
	 */
	@Test
	public void testIBMDataCompare() {
		IBMDataCompareService ibmDataCompareService = AppContext.getBean("ibmDataCompareService",
				IBMDataCompareService.class);
		try {
			ibmDataCompareService.IBMDataCompare();
		} catch (DataSynchroException e) {
			e.printStackTrace();
		}

		// String params = "http://localhost:8080/cloud" +
		// "/WebService/service/syn/syn/servicemanage";
		// String jsonString =
		// "{\"vmCode\":\"vm-272\",\"user_name\":\"taoxue\",\"dataStoreCode\":\"datastore-11\",\"hostCode\":\"host-10\",\"project_id\":\"ef911a58360e491ca3962b2dea2dca4b\",\"storageSizeInMb\":51200,\"memoryMB\":\"4096\",\"type\":\"1\",\"numCPUs\":\"2\",\"createType\":\"templatetovm\",\"newVmName\":\"1117_1\",\"connectCode\":\"VCENTER.m0\"}";
		// try {
		// HttpClientUtil.post(params, jsonString);
		// } catch (HttpClientException e) {
		// e.printStackTrace();
		// }
	}
}
