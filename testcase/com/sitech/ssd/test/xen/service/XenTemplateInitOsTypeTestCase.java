package com.sitech.ssd.test.xen.service;

import org.junit.Test;

import com.sitech.basd.yicloud.service.xen.XenManServiceImpl;
import com.sitech.ssd.test.base.spring.AppContext;

/**
 * 
 * <p>
 * Title: XenTemplateInitOsTypeTestCase
 * </p>
 * <p>
 * Description: Xen-OS类型表数据实例测试
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
 * @createtime 2013-6-5 下午6:51:59
 * 
 */
public class XenTemplateInitOsTypeTestCase {
	/**
	 * 
	 * @Title: testInitXenOsType
	 * @Description: 实例化Xen-支持OS列表及uuid
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-5 下午6:54:54
	 */
	@Test
	public void testInitXenOsType() {
		XenManServiceImpl impl = AppContext.getBean("xenManService",
				XenManServiceImpl.class);
		impl.listDefaultTem("92149f61-e70f-b28d-ba06-1dd47c972183");
	}
}
