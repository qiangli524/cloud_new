package com.sitech.ssd.test.resource;

import org.junit.Test;

import com.sitech.basd.resource.util.ReourceInDomainUtil;
import com.sitech.basd.util.AppContext;

public class ReourceInDomainUtilTestCase {
	@Test
	public void testInitResourceDomainBySessionAndConfig() {
		ReourceInDomainUtil util = AppContext.getBean("reourceInDomainUtil",
				ReourceInDomainUtil.class);
		System.out.println(util.initResourceDomainBySessionAndConfig("VCENTER.m0"));

		System.out.println(util
				.initResourceDomainBySessionAndConfig("964a5f4a-312e-5790-bef4-7ce8f49ad057"));
	}
}
