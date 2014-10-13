package com.sitech.ssd.test.cq.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.sitech.basd.util.AppContext;

import dao.TbDirectoryCodeDao;
import domain.TbDirectoryCodeVO;

/**
 * 
 * <p>
 * Title: TbDirectoryCodeTestCase
 * </p>
 * <p>
 * Description: TbDirectoryCode Dao 测试
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-5-5 上午11:28:29
 * 
 */
public class TbDirectoryCodeTestCase {
	/**
	 * 
	 * @Title: testQueryForList
	 * @Description: 测试查询数据字典列表
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-5-5 上午11:29:12
	 */
	@Test
	public void testQueryForList() {
		TbDirectoryCodeDao tbDirectoryCodeDao = AppContext.getBean("tbDirectoryCodeDao",
				TbDirectoryCodeDao.class);
		TbDirectoryCodeVO vo = new TbDirectoryCodeVO();
		vo.setDitypecode("domain.type");
		try {
			List<TbDirectoryCodeVO> list = tbDirectoryCodeDao.queryForList(vo);
			System.out.println(list.get(0).getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
