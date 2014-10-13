package com.sitech.ssd.test.yicloud.dao;

import java.util.List;

import org.junit.Test;

import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.ssd.test.base.spring.AppContext;

/**
 * 
 * <p>
 * Title: TemManDaoTestCase
 * </p>
 * <p>
 * Description: 虚拟机版本测试
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
 * @createtime 2013-8-3 上午9:58:06
 * 
 */
public class TemManDaoTestCase {
	@Test
	public void testTemList() {
		TemManDao dao = AppContext.getBean("temManDao", TemManDao.class);
		TemManObj obj = new TemManObj();
		obj.setType("1");
		List<String> list = dao.getAllTemplateEntity(obj);
		for (String s : list) {
			
		}
	}
}
