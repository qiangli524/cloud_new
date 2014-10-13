package com.sitech.ssd.test.cas;

import org.junit.Test;

import com.sitech.basd.util.AppContext;
import com.sitech.cas.login.SysSingleSignOnUtil;

public class SysSingleSignOnActionTestCase {
	@Test
	public void loginTest() {
		SysSingleSignOnUtil sysSingleSignOnAction = AppContext.getBean("sysSingleSignOnUtil",
				SysSingleSignOnUtil.class);
		try {
			sysSingleSignOnAction.initHttpCookie();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
