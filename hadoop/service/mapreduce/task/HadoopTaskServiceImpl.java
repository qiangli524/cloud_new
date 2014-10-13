package service.mapreduce.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.mapreduce.task.HadoopTaskDao;
import domain.mapreduce.task.HadoopTaskObj;

/**
 * 
 * <p>
 * Title: HadoopTaskServiceImpl
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
 * @createtime 2014-1-11 下午4:57:25
 * 
 */
@Service("hadoopTaskService")
public class HadoopTaskServiceImpl implements HadoopTaskService {
	@Autowired
	private HadoopTaskDao hadoopTaskDao;

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
	@Override
	public List<HadoopTaskObj> queryTaskListByObj(HadoopTaskObj obj) {
		return hadoopTaskDao.queryTaskListByObj(obj);
	}
}
