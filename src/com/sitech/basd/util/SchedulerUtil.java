package com.sitech.basd.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.calendar.AnnualCalendar;

import scheduler.SchedulerParameterObj;

import com.sitech.basd.scheduler.SchedulerConstant;
import com.sitech.utils.common.CommonUtil;

public class SchedulerUtil {
	// private static Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);

	private static Scheduler scheduler = AppContext.getBean("scheduler", Scheduler.class);

	/**
	 * 
	 * @Title: addJob 动态添加任务
	 * @Description:  动态添加任务
	 * @param taskname 任务ID
	 * @param taskGroupName 任务的组名称
	 * @param obj 定时任务相关参数对象
	 * @return void
	 * @throws  SchedulerException, ParseException 
	 * @author liming_bj
	 * @version 1.0
	 * @createtime 2013-11-14 下午3:10:25
	 */
	public static void addJob(String taskname, String taskGroupName, SchedulerParameterObj obj) throws SchedulerException, ParseException {

		// 建立任务对象
		JobDetail job = new JobDetail("job_" + taskname, taskGroupName, obj.getJobClass());

		// 处理类中传入实体类
		if (CommonUtil.isNotEmpty(obj.getObject())) {
			job.getJobDataMap().put(obj.getObject().getClass().getSimpleName(), obj.getObject());
		}

		// 处理类中传入普通字符串
	    job.getJobDataMap().put(SchedulerConstant.PARAMETER, obj.getParamValue());

		// 根据参数判断创建那种触发器
		if (CommonUtil.isNotEmpty(obj.getCronExpression())) {
			// 创建轮询触发器
			CronTrigger cronTrigger = new CronTrigger("trigger_" + taskname, taskGroupName);
			// 添加轮询表达式
			cronTrigger.setCronExpression(obj.getCronExpression());

			// 设置执行开始时间
			if (CommonUtil.isNotEmpty(obj.getStartDate())) {
				cronTrigger.setStartTime(obj.getStartDate());
			}
			// 设置执行结束时间
			if (CommonUtil.isNotEmpty(obj.getEndDate())) {
				cronTrigger.setEndTime(obj.getEndDate());
			}
			// 设置排除的日期
			if (obj.getExcludeDateList().size() > 0) {
				AnnualCalendar excluded = new AnnualCalendar();
				for (Calendar calendar : obj.getExcludeDateList()) {
					excluded.setDayExcluded(calendar, true);
				}
				scheduler.addCalendar("Excluded_" + taskname, excluded, false, false);
				cronTrigger.setCalendarName("Excluded_" + taskname);
			}

			// 加入调度器
			scheduler.scheduleJob(job, cronTrigger);
		} else {
			// 创建简单触发器
			SimpleTrigger trigger = new SimpleTrigger("trigger_" + taskname, taskGroupName, obj.getStartDate());
			// 加入调度器
			scheduler.scheduleJob(job, trigger);
		}

	}

	/**
	 * 根据任务ID删除定时任务
	 * @param taskname 任务ID
	 * @param taskGroupName 任务组名称
	 * @throws SchedulerException
	 */
	public static void deleteJob(String taskId, String taskGroupName) throws SchedulerException {
		scheduler.deleteJob("job_" + taskId, taskGroupName);
		// scheduler.unscheduleJob("trigger_" + taskname, taskGroupName);
	}

	/**
	 * 根据任务ID修改触发器时间
	 */
	public static void editJob(String taskId) {

	}
	
	/**
	 * 计算两个日期的时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDifference(Timestamp startTime, Timestamp endTime) {
		StringBuffer returnString = new StringBuffer();
		long t1 = 0L;
		long t2 = 0L;
		t1 = endTime.getTime();
		t2 = startTime.getTime();
		int hours = (int) ((t1 - t2) / 3600000);
		int minutes = (int) (((t1 - t2) / 1000 - hours * 3600) / 60);
		int second = (int) ((t1 - t2) / 1000 - hours * 3600 - minutes * 60);

		if (hours > 0) {
			returnString.append(hours).append("小时");
		} else if (minutes > 0) {
			returnString.append(minutes).append("分");
		} else {
			returnString.append(second).append("秒");
		}

		return returnString.toString();
	}
}
