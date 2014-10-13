package com.sitech.basd.syslog.service;

import java.sql.Timestamp;

import java.util.List;
import java.util.Map;

import com.sitech.basd.syslog.dao.TbUserOperationLogDAO;
import com.sitech.basd.syslog.domain.TbUserOperationLog;
import com.sitech.basd.syslog.domain.TbUserOperationLogExample;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.randomid.RandomUUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: TbUserOperationLogService</p>
 * <p>Description: 用户操作日志Service</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH</p>
 * @author liming_bj
 * @version 1.0
 * @createtime 2014-05-14 19:49:15
 * 
 */

@Service("userOperationLogService")
public class TbUserOperationLogService {

	@Autowired
	private TbUserOperationLogDAO userOperationLogDao;

	/**
	 * 用操作日志
	 * @param operationType  操作类型
	 * @param operationResult 操作结果
	 * @param operationObject  操作目标
	 * @param operationContent  操作内容
	 * @param startTime  执行开始时间
	 * @param sessionMap  Action中的session对象
	 * @param className  处理类的Class
	 */
	public void insertUserOperationLog(String operationType,Integer operationResult,String operationObject,String operationContent,Timestamp startTime, Map<?, ?> sessionMap, Class<?> className) {

		Logger logger = LoggerFactory.getLogger(className);
		
		//执行结束时间
		Timestamp endTime=new Timestamp(System.currentTimeMillis());
		//创建日志对象
		TbUserOperationLog obj=new TbUserOperationLog();
		// 主键ID
		obj.setId(RandomUUID.getUuid());
		// 操作人ID
		obj.setOperatorId(sessionMap.get("id").toString());
		// 操作人姓名
		obj.setOperatorName(sessionMap.get("name").toString());
	    // 操作类型
	    obj.setOperationType(operationType);
	    // 操作结果
	    obj.setOperationResult(operationResult);
	    // 操作对象
	    obj.setOperationObject(operationObject);
	    // 操作内容
	    obj.setOperationContent(operationContent);
	    // 操作开始时间
	    obj.setOperationStartDate(startTime);
	    // 操作结束时间
	    obj.setOperationEndDate(endTime);
		//消耗时间
		//obj.setElapsedTime(getTimeDifference(startTime, endTime));
		//插入记录
		userOperationLogDao.insertSelective(obj);
	}
	
	private String getTimeDifference(Timestamp startTime, Timestamp endTime) {

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







	/**
	 * 查询日志记录
	 * @param obj
	 * @return 日志列表
	 */
	public List<?> selectUserOperationLogList(TbUserOperationLogExample obj) {
		return userOperationLogDao.selectByExample(obj);
	}
	

}
