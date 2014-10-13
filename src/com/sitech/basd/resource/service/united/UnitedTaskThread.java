package com.sitech.basd.resource.service.united;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sitech.basd.yicloud.dao.globaltask.GlobalTaskDao;
import com.sitech.basd.yicloud.domain.globaltask.GlobalTaskObj;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;

/**
 * 
 * <p>
 * Title: UnitedTaskThread
 * </p>
 * <p>
 * Description:克隆虚拟机等插入更改任务
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Aug 11, 2013
 */
@Component("unitedTaskThread")
public class UnitedTaskThread {
	public static final String ING = "1";
	public static final String END = "2";
	@Resource
	private GlobalTaskDao globalTaskDao;
	@Resource
	private RabbitMQUtil rabbitmqUtil;

	/**
	 * 
	 * @Title: updateTaskProgress
	 * @Description:定时更新任务的百分比
	 * @author duangh
	 * @date Aug 11, 2013 3:10:56 PM
	 * @param obj
	 */
	public void updateTaskProgress(GlobalTaskObj obj) {
		int timeInterval = 1 * 60 * 1000;// 间隔2分钟
		int progressInterVal = 15;// 每次更新15%
		int endProgress = 90;// 只更新到90%
		obj.setProgress("0");
		globalTaskDao.insertByObj(obj);
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "start");
		map.put("id", obj.getId());
		map.put("content", obj.getContent());
		try {
			rabbitmqUtil.publishMessage("", "socket.io.task", map);
		} catch (RabbitMQException e) {
			// just send a message to rabbitmq
		}
		GlobalTaskObj queryTaskObj = new GlobalTaskObj();
		queryTaskObj.setId(obj.getId());
		for (int i = progressInterVal; i <= endProgress; i += progressInterVal) {
			obj.setStatus(ING);// 1代表执行中
			obj.setProgress(String.valueOf(i));
			try {
				GlobalTaskObj ifCompleted = globalTaskDao.queryForListByObj(queryTaskObj).get(0);
				if (ifCompleted.getProgress().equals("0")
						|| Integer.parseInt(ifCompleted.getProgress()) < Integer.parseInt(obj.getProgress())) {
					globalTaskDao.updateByObj(obj);
				}
				map.put("type", "process");
				map.put("progress", String.valueOf(i));
				try {
					rabbitmqUtil.publishMessage("", "socket.io.task", map);
				} catch (RabbitMQException e) {
					// just send a message to rabbitmq
				}
				Thread.sleep(timeInterval);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	/**
	 * 
	 * @Title: endTask
	 * @Description:任务结束
	 * @author duangh
	 * @date Aug 11, 2013 3:11:29 PM
	 * @param obj
	 */
	public void endTask(GlobalTaskObj obj) {
		int progress = 100;
		String status = END;// 已完成
		obj.setStatus(status);
		obj.setProgress(String.valueOf(progress));
		globalTaskDao.updateByObj(obj);
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "finished");
		map.put("id", obj.getId());
		map.put("progress", "100");
		map.put("content", obj.getContent());
		try {
			rabbitmqUtil.publishMessage("", "socket.io.task", map);
		} catch (RabbitMQException e) {
			// just send a message to rabbitmq
		}
		// 当任务结束，成功或者失败 update By huojla @ 20130919
		try {
			// thead is interrupted when the task is failed or finished
			map.put("type", "process");
			map.put("progress", "100");
			rabbitmqUtil.publishMessage("", "socket.io.task", map);
		} catch (RabbitMQException rme) {
			// just send a message to rabbitmq
		}
	}
}
