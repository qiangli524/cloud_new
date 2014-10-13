package dao.mapreduce.task;

import java.util.List;

import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopTaskDao
 * </p>
 * <p>
 * Description: 任务相关操作
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
 * @createtime 2014-1-11 下午4:23:11
 * 
 */
public interface HadoopTaskDao {
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
