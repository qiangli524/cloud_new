package com.sitech.ssd.test.rsmu.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysFunctionsDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;
import com.sitech.ssd.test.base.spring.AppContext;

/**
 * 
 * <p>
 * Title: TbSysFunctionsDaoTestCase
 * </p>
 * <p>
 * Description: tb_sys_functionsDao测试
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
 * @createtime 2013-8-27 上午10:49:54
 * 
 */
public class TbSysFunctionsDaoTestCase {
	private TbSysFunctionsDao tbSysFunctionsDao;

	@Before
	public void before() {
		tbSysFunctionsDao = AppContext.getBean("tbSysFunctionsDao", TbSysFunctionsDao.class);
	}

	/**
	 * 
	 * @Title: queryThreeFuncNode
	 * @Description: 查询所有三级节点
	 * @param
	 * @return List<TbSysFunctionsObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 上午10:48:25
	 */
	@Test
	public void testQueryThreeFuncNode() {
		String funcidTwo = "1101000000";
		List<TbSysFunctionsObj> list = tbSysFunctionsDao.queryThreeFuncNode(funcidTwo.substring(0,
				4));
	}
}
