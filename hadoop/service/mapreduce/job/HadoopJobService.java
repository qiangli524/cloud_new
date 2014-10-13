package service.mapreduce.job;

import java.util.List;

import domain.mapreduce.job.HadoopJobObj;

/**
 * 
 * <p>
 * Title: HadoopJobService
 * </p>
 * <p>
 * Description: 作业管理相关操作
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
 * @createtime 2014-1-11 下午4:56:59
 * 
 */
public interface HadoopJobService {
	/**
	 * 
	 * @Title: queryJobListByObj
	 * @Description: 查询作业管理列表
	 * @param
	 * @return List<HadoopApplicationObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	public List<HadoopJobObj> queryJobListByObj(HadoopJobObj obj);
}
