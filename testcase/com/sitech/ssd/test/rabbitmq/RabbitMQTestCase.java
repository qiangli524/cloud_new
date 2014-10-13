package com.sitech.ssd.test.rabbitmq;

import org.junit.Test;

import com.rabbitmq.client.Connection;
import com.sitech.basd.util.AppContext;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;

/**
 * 
 * <p>
 * Title: RabbitMQTestCase
 * </p>
 * <p>
 * Description: RabbitMQ-Spring配置测试用例
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
 * @createtime 2013-10-30 上午9:55:27
 * 
 */
public class RabbitMQTestCase {
	@Test
	public void testSpringRabbitMQ() {
		RabbitMQUtil rabbitMQUtil = AppContext.getBean("rabbitmqUtil", RabbitMQUtil.class);
		Connection connection = rabbitMQUtil.getConnection();
		System.out.println(connection);
		try {
			rabbitMQUtil.publishMessage("", "ibm.monitor.data2", "{'huohu':123}");
		} catch (RabbitMQException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(300 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
