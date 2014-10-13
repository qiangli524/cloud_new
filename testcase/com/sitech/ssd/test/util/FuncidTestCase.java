package com.sitech.ssd.test.util;

import org.junit.Test;

/**
 * 
 * <p>
 * Title: FuncidTestCase
 * </p>
 * <p>
 * Description: 功能id测试用例
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
 * @createtime 2013-8-27 上午10:10:57
 * 
 */
public class FuncidTestCase {
	/**
	 * 
	 * @Title: testFuncid
	 * @Description: 测试功能ID
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 上午10:11:28
	 */
	@Test
	public void testFuncid() {
		String funcidTwo = "1101000000";
		String funcidThree = "1102010000";

		int twoIndex = funcidTwo.lastIndexOf("000000");
		twoIndex = funcidThree.lastIndexOf("000000");

		boolean twoBoolean = funcidTwo.matches("^[0-9]{3}[1-9]000000");
		twoBoolean = funcidThree.matches("^[0-9]{3}[1-9]000000");

		boolean threeBoolean = funcidTwo.matches("^[0-9]{5}[1-9]0000");
		threeBoolean = funcidThree.matches("^[0-9]{5}[1-9]0000");

	}
}
