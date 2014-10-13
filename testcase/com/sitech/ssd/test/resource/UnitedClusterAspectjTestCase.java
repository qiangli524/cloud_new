package com.sitech.ssd.test.resource;

import org.junit.Test;

import com.sitech.ssd.test.base.spring.AppContext;

public class UnitedClusterAspectjTestCase {
	@Test
	public void testAspectj() {
		TestAspectj testAspectj = AppContext.getBean("testAspectj", TestAspectj.class);
		testAspectj.println("Huojin", new TestAspectj());
		testAspectj.print("Huojin");
	}
}
