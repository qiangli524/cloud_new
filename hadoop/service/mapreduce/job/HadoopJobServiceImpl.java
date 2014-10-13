package service.mapreduce.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.mapreduce.job.HadoopJobDao;
import domain.mapreduce.job.HadoopJobObj;

/**
 * 
 * <p>
 * Title: HadoopApplicationServiceImpl
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
 * @createtime 2014-1-11 下午4:57:25
 * 
 */
@Service("hadoopJobService")
public class HadoopJobServiceImpl implements HadoopJobService {
	@Autowired
	private HadoopJobDao hadoopJobDao;

	/**
	 * 
	 * @Title: queryJobListByObj
	 * @Description: 查询作业管理列表
	 * @param
	 * @return List<HadoopJobObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-11 下午4:55:16
	 */
	@Override
	public List<HadoopJobObj> queryJobListByObj(HadoopJobObj obj) {
		return hadoopJobDao.queryJobListByObj(obj);
	}
}
