package com.sitech.ssd.test.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sitech.basd.sxcloud.rsmu.config.DeployOperation;
import com.sitech.basd.yicloud.util.JSONUtil;

/**
 * 
 * <p>
 * Title: JSONUtilTestCase
 * </p>
 * <p>
 * Description: Jackson工具类测试用例
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
 * @createtime 2013-6-4 下午3:28:09
 * 
 */
public class JSONUtilTestCase {
	/**
	 * 
	 * @Title: testJacksonMap
	 * @Description: 测试Jackson转换Map功能
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-6-4 下午3:28:47
	 */
	@Test
	public void testJacksonMap() {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("deplotId", 123);
		msgMap.put("deployStatus", DeployOperation.START);
		String message = JSONUtil.toJSON(msgMap);
		Map<String, Object> resultMap = (Map<String, Object>) JSONUtil
				.fromJSON(message, HashMap.class);
	}
}
