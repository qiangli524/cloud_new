package com.sitech.ssd.test.yicloud.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.ssd.test.base.spring.AppContext;

/**
 * 
 * <p>
 * Title: DataStoreDaoTestCase
 * </p>
 * <p>
 * Description: TB_YICLOUD_DATASTORE 数据存储测试用例
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
 * @createtime 2013-6-7 下午8:36:20
 * 
 */
public class DataStoreDaoTestCase extends AppContext {
	/**
	 * 
	 * @Title: testQueryVmwareDataStoreAllAndFree
	 * @Description: 测试QueryVmwareDataStoreAllAndFree
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-7 下午8:37:01
	 */
	// @Test
	public void testQueryVmwareDataStoreAllAndFree() {
		DataStoreDao dao = getBean("dataStoreDao", DataStoreDao.class);
		Map<String, Double> map = dao.queryVmwareDataStoreAllAndFree(null);
	}

	@Test
	public void getAllDatastoreEntity() {
		DataStoreDao dao = getBean("dataStoreDao", DataStoreDao.class);
		List<String> list = dao.getAllDatastoreEntity(null);
		if (list != null) {
			for (String result : list) {
				
			}
		}
	}
}
