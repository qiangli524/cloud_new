package service.mapreduce.task;

import java.util.List;

import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopTaskService
 * </p>
 * <p>
 * Description: 任务列表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-14 下午5:46:52
 * 
 */
public interface HadoopTaskService {
	/**
	 * 
	 * @Title: queryTaskListByObj
	 * @Description: 任务管理列表
	 * @param
	 * @return List<HadoopTaskObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	public List<HadoopTaskObj> queryTaskListByObj(HadoopTaskObj obj);
}
