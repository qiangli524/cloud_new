package com.sitech.basd.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import rabbitmq.QueueDefind;

import com.sitech.basd.scheduler.service.ResourceSchedulerService;
import com.sitech.basd.util.AppContext;
import com.sitech.ssd.sx.OperateVMObj;
import com.sitech.ssd.sx.comstant.VmOperateConstant;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;

/**
 * 关机任务处理类
 * <p>Title: ResourcePowerOffJob</p>
 * <p> Description:关机任务Job</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p> Company: SI-TECH</p>
 * @author liming_bj
 * @version 1.0
 * @createtime 2013-11-5 下午2:34:43
 * 
 */
public class ResourcePowerOffJob extends QuartzJobBean {

	private static Logger logger = LoggerFactory.getLogger(ResourcePowerOnJob.class);

	// 队列工具类
	private RabbitMQUtil rabbitmqUtil = AppContext.getBean("rabbitmqUtil", RabbitMQUtil.class);

	// 资源调度任务类
	private ResourceSchedulerService resourceSchedulerService = AppContext.getBean("resourceSchedulerService", ResourceSchedulerService.class);

	// 虚拟机idVHUUID
	public String parameter;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		try {

			// 取得要发送的队列实体
			OperateVMObj operateVMObj = resourceSchedulerService.getOperateVMObj(parameter);
			// 设置执行动作
			operateVMObj.setOperate(VmOperateConstant.POWEREDOFF);
			// 发送队列消息
			rabbitmqUtil.publishMessage("", QueueDefind.SX_OPERATEVM_QUEUE, operateVMObj);

			logger.info("ResourcePowerOffJob");
		} catch (RabbitMQException e) {
			logger.error("" + e.getMessage() + e);
		}

	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
