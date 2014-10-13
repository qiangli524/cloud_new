package com.sitech.ssd.test.util;

import org.junit.Test;

import com.sitech.utils.capture.GetTarFromStandardUtil;

/**
 * 
 * <p>
 * Title: GetTarFromStandardUtilTestCase
 * </p>
 * <p>
 * Description: GetTarFromStandardUtil测试
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
 * @createtime 2013-7-8 上午10:11:29
 * 
 */
public class GetTarFromStandardUtilTestCase {
	@Test
	public void testGetTarFromStandardUtil() {
		GetTarFromStandardUtil
				.getTarFromStandard(
						"172.21.3.13",
						22,
						"root",
						"huojl1234",
						"/opt/apache-tomcat-6.0.35/webapps/apponline",
						new String[] {
								"/opt/apache-tomcat-6.0.35/webapps/apponline/second.jsp",
								"/opt/apache-tomcat-6.0.35/webapps/apponline/third.jsp",
								"/opt/apache-tomcat-6.0.35/webapps/apponline/WEB-INF/web.xml" },
						"20130708.tar", "D:/");
	}
}
