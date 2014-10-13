package com.sitech.basd.aspectj.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.vo.log.OperationLogVO;

/**
 * 
 * <p>
 * Title: UnitedResourceHandle
 * </p>
 * <p>
 * Description: 统一资源切面数据处理工具类
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
 * @createtime 2013-9-4 下午5:48:02
 * 
 */
public class UnitedResourceHandle {
	private static final Logger LOG = LoggerFactory.getLogger(UnitedResourceHandle.class);
	@Autowired
	private RabbitMQUtil rabbitmqUtil;

	/**
	 * 
	 * @Title: dealUnitedLogTo4A
	 * @Description: 统一处理4A日志
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-5 上午9:35:41
	 */
	public void dealUnitedLogTo4A(OperationLogVO vo) {
		try {
			rabbitmqUtil.publishMessage("", "ah.4a.log.queue", vo);
		} catch (RabbitMQException e) {
			LOG.error("记录安徽移动4A操作日志异常" + e.getMessage() + e);
		}
	}
}
